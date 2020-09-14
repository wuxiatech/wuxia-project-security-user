/*
 * Created on :2013年8月12日 Author :songlin.li Change History Version Date Author
 * Reason <Ver.No> <date> <who modify> <reason>
 */
package cn.wuxia.project.security.core.user.service.impl;

import cn.wuxia.common.exception.AppDaoException;
import cn.wuxia.common.exception.AppServiceException;
import cn.wuxia.common.orm.query.Pages;
import cn.wuxia.common.util.DateUtil;
import cn.wuxia.common.util.StringUtil;
import cn.wuxia.common.util.reflection.BeanUtil;
import cn.wuxia.project.basic.core.conf.entity.CustomTagCategory;
import cn.wuxia.project.basic.core.conf.service.CustomTagCategoryService;
import cn.wuxia.project.basic.core.conf.support.DTools;
import cn.wuxia.project.common.dao.CommonDao;
import cn.wuxia.project.common.service.impl.CommonServiceImpl;
import cn.wuxia.project.common.support.CacheConstants;
import cn.wuxia.project.security.common.MyPasswordEncoder;
import cn.wuxia.project.security.common.MyUserDetails;
import cn.wuxia.project.security.common.SpringSecurityUtils;
import cn.wuxia.project.security.core.entity.SecurityUser;
import cn.wuxia.project.security.core.service.SecurityUserService;
import cn.wuxia.project.security.core.user.bean.ImportUserBean;
import cn.wuxia.project.security.core.user.bean.ListUsers;
import cn.wuxia.project.security.core.user.bean.RegisterUserDto;
import cn.wuxia.project.security.core.user.dao.AdminUserDao;
import cn.wuxia.project.security.core.user.entity.AdminUser;
import cn.wuxia.project.security.core.user.service.AdminUserService;
import cn.wuxia.tools.excel.ImportExcelUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class AdminUserServiceImpl extends CommonServiceImpl<AdminUser, String> implements AdminUserService {
    @Autowired
    AdminUserDao adminUserDao;

    @Autowired
    SecurityUserService securityUserService;

    @Autowired
    private CustomTagCategoryService tagCategoryService;

    @Override
    protected CommonDao<AdminUser, String> getCommonDao() {
        return adminUserDao;
    }

    @Override
    public AdminUser findByMobile(String appid, String mobile) {
        return adminUserDao.findByMobile(appid, mobile);
    }

    @Override
    public AdminUser registerUser(RegisterUserDto dto) throws Exception {
        logger.info("校验手机是否已注册......{}{}", dto.getRealName(), dto.getMobile());
        AdminUser checkdbuser = adminUserDao.findByMobile(dto.getAppid(), dto.getMobile());
        if (checkdbuser != null) {
            logger.info("手机已注册......id:{},name:{},mobile:{}", checkdbuser.getId(), checkdbuser.getRealName(), checkdbuser.getMobile());
            throw new AppServiceException("手机已注册");
        }
        logger.info("开始注册......{}{}", dto.getRealName(), dto.getMobile());
        // 注册用户
        AdminUser user = new AdminUser();
        BeanUtils.copyProperties(dto, user);
        // 注册用户信息
        user.setRegisterTime(DateUtil.newInstanceDate());
        if (StringUtil.isBlank(user.getOrder_())) {
            user.setOrder_("1000");
        }
        BeanUtils.copyProperties(user, dto);
        // if (StringUtil.isNotBlank(dto.getOpenid())) {
        // user.setCreateType(UserCreateTypeEnum.WECHAT.name());
        // } else {
        // user.setCreateType(UserCreateTypeEnum.FDD.name());
        // }

        // 推送到CAS用户表中，单点登录
        SecurityUser securityUser = saveCasUser(dto);

        try {
            logger.info("CAS userId:{}", securityUser.getId());
            // 返回CAS用户的ID到平台记录
            user.setCasUserId(securityUser.getId());
            this.save(user);
            dto.setId(user.getId());

        } catch (Exception e) {
            logger.error("注册失败，推送用户异常！", e);
            throw new AppServiceException("注册失败，请联系管理员！");
        }

        /**
         * 为每个用户创建一个自己的自定义标签库
         */
        tagCategoryService.save(
                new CustomTagCategory(user.getId(), user.getRealName(), CustomTagCategory.TagCategoryType.custom));
        /**
         * 保存有效后再推送到CAS
         */
        // 记录用户操作：用户注册
//        userOperationHistoryService.saveUserOperation(user.getId(), UserOperationEnum.ZHUCE.name(),
//                UserOperationEnum.ZHUCE.getDisplayValue());
        // 注册成功发送邮件
        // registerRemind(userd, user.getRegisterTime());
        logger.info("完成注册......{}", securityUser.getAccountName());
        return user;
    }

    private SecurityUser saveCasUser(RegisterUserDto dto) {
        try {
            String platform = DTools.dic(dto.getAppid());
            SecurityUser casUser = securityUserService.findByAccountName(platform, dto.getMobile());
            if (casUser != null) {
                return casUser;
            }
            // 推送CAS的对象
            casUser = new SecurityUser();
            casUser.setPlatform(platform);
            // 设置基础联系数据到CAS对象中
            // 设置密码
            // 新增账号情况防止id被初始化
            casUser.setId(null);
            // 复制手机号到cas的accountname
            casUser.setAccountName(dto.getMobile());
            String md5password = null;
            MyPasswordEncoder myPasswordEncoder = new MyPasswordEncoder();
            if (StringUtil.isNotBlank(dto.getPassword())) {
                md5password = myPasswordEncoder.encodeMD5Password(dto.getPassword());
            } else {
                // 设置默认密码, 账号后6位
                String defaultPw = StringUtil.substring(casUser.getAccountName(),
                        (casUser.getAccountName().length() - 5), casUser.getAccountName().length());
                md5password = myPasswordEncoder.encodeMD5Password(defaultPw);
            }
            casUser.setPassword(MyPasswordEncoder.extractPassword(md5password));
            casUser.setSalt(MyPasswordEncoder.extractSalt(md5password));
            securityUserService.save(casUser);
            return casUser;
        } catch (Exception ex) {
            logger.error("注册失败，推送用户到CAS异常：" + ex.getMessage());
            throw new AppServiceException("注册失败，请联系管理员！");
        }
    }

    /**
     * 删除用户及登录账号
     *
     * @param id
     * @author songlin
     */
    @Override
    public AdminUser deleteUser(String id) {
        AdminUser ud = findById(id);
        if (ud != null && StringUtil.isNotBlank(ud.getCasUserId())) {
            securityUserService.delete(ud.getCasUserId());
        }
        delete(ud);
        return ud;
    }

    /**
     * 回滚已删除用户及登录账号
     *
     * @param id
     * @author songlin
     */
    @Override
    public AdminUser fallbackDeleteUser(String id) {
        AdminUser ud = adminUserDao.findByIdIncludeLogicalDelete(id);
        SecurityUser cu = securityUserService.findById(ud.getCasUserId());
        cu.setIsObsoleteDate(null);
        ud.setIsObsoleteDate(null);
        try {
            securityUserService.save(cu);
            save(ud);
        } catch (AppDaoException e) {
            throw new AppServiceException("保存失败");
        }
        return ud;
    }

    /**
     * 修改基本资料
     *
     * @param dto
     * @author songlin
     */
    @Override
    public void updateInfo(RegisterUserDto dto) {
        AdminUser ud = findById(dto.getId());
        try {
            /**
             * 如果修改手机则更新手机账号
             */
            if (!StringUtil.equals(dto.getMobile(), ud.getMobile()) && StringUtil.isNotBlank(dto.getMobile())) {
                SecurityUser cu = securityUserService.findById(ud.getCasUserId());

                cu.setAccountName(dto.getMobile());
                securityUserService.save(cu);
            }
            BeanUtil.copyPropertiesWithoutNullValues(ud, dto);
            save(ud);
        } catch (AppDaoException e) {
            throw new AppServiceException("保存失败");
        }
    }

    /**
     * 修改密码
     *
     * @param dto
     * @author songlin
     */
    @Override
    public void updatePasswd(RegisterUserDto dto) {
        AdminUser ud = findById(dto.getId());
        /**
         * 如是管理员则不需验证旧密码（当前用户例外）
         */
        MyUserDetails currentUser = SpringSecurityUtils.getCurrentUser();
        if (StringUtil.equals(currentUser.getUid(), dto.getId())) {
//            securityUserService.updatePasswd(ud.getCasUserId(), dto.getPassword(), dto.getDescription());
            /**
             * 暂时忽略旧密码
             * TODO
             */
            securityUserService.updatePasswd(ud.getCasUserId(), dto.getPassword());
        } else if (StringUtil.isNotBlank(currentUser.getRoles())) {
            if (currentUser.getRoles().contains("root") || currentUser.getRoles().contains("admin")) {
                /**
                 * 隐藏指定的角色名称
                 */
                securityUserService.updatePasswd(ud.getCasUserId(), dto.getPassword());
            }
        }
    }

    @Override
    @Deprecated
    public void importUser(InputStream inputStream) throws Exception {
        List<ImportUserBean> list = ImportExcelUtil.importExcel(inputStream, ImportUserBean.class);
        // 如果错误消息不为空，则处理出错信息并返回错误的消息

        List<RegisterUserDto> docs = Lists.newArrayList();

        Map<String, RegisterUserDto> doctors = getNameMap();
        int i = 100;
        for (ImportUserBean importBean : list) {

            RegisterUserDto doc = doctors.get(importBean.getName());
            if (doc == null) {
                doc = new RegisterUserDto();
            }
            BeanUtil.copyPropertiesWithoutNullValues(doc, importBean);
            doc.setRealName(importBean.getName());
            List<String> ga = Lists.newArrayList();
            if (StringUtil.isNotBlank(importBean.getGoodat1())) {
                ga.add(importBean.getGoodat1());
            }
            if (StringUtil.isNotBlank(importBean.getGoodat2())) {
                ga.add(importBean.getGoodat2());
            }
            doc.setGoodAt(StringUtil.join(ga, "，"));

            doc.setType(importBean.getType());
            docs.add(doc);
            if (jodd.util.StringUtil.isBlank(doc.getMobile())) {
                doc.setMobile(doc.getRealName());
            }

            doc.setOrder_(i + "");
            i = i + 10;
            registerUser(doc);
        }

    }

    // @CacheEvict(key="", value="")
    public Map<String, RegisterUserDto> getNameMap() {
        // String key = CacheConstants.CACHED_VALUE_1_HOUR +
        // getClass().getName() + ".getNameMap";
        // Object v = CacheSupport.get(key);
        // if (v == null) {
        List<AdminUser> doctors = findAll();
        Map<String, RegisterUserDto> map = Maps.newHashMap();
        for (AdminUser doc : doctors) {
            RegisterUserDto dto = new RegisterUserDto();
            BeanUtil.copyProperties(dto, doc);
            map.put(doc.getRealName(), dto);
        }
        // CacheSupport.set(key, map);
        // v = CacheSupport.get(key);
        // }
        // return (Map<String, RegisterUserDto>) v;
        return map;
    }

    @Override
    @Cacheable(value = CacheConstants.CACHED_VALUE_1_HOUR, key = CacheConstants.CACHED_KEY_DEFAULT + "+#openid")
    public AdminUser findByOpenid(String openid) {
        return adminUserDao.findByOpenid(openid);
    }


    @Override
    public List<AdminUser> findAll(String appid) {
        return adminUserDao.findAllUser(appid);
    }

    /**
     * 普通的用户列表
     *
     * @param page
     * @return
     */
    @Override
    public Pages<ListUsers> findForUserList(Pages page) {
        return adminUserDao.findForUserList(page);
    }

    /**
     * 查询已删除了的账号
     *
     * @param page
     * @return
     * @author songlin
     */
    @Override
    public Pages<AdminUser> findLockUser(Pages page) {
        return adminUserDao.findLockUser(page);
    }

}
