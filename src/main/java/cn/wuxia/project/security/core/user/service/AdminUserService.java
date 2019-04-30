/*
 * Created on :2013年8月12日 Author :songlin.li Change History Version Date Author
 * Reason <Ver.No> <date> <who modify> <reason>
 */
package cn.wuxia.project.security.core.user.service;

import cn.wuxia.project.security.core.user.bean.ListUsers;
import cn.wuxia.project.security.core.user.bean.RegisterUserDto;
import cn.wuxia.project.security.core.user.entity.AdminUser;
import cn.wuxia.project.common.service.CommonService;
import cn.wuxia.common.orm.query.Pages;

import java.io.InputStream;
import java.util.List;

public interface AdminUserService extends CommonService<AdminUser, String> {

    public AdminUser findByMobile(String appid, String mobile);

    public AdminUser registerUser(RegisterUserDto dto) throws Exception;

    public void importDoctor(InputStream inputStream) throws Exception;

    /**
     * 修改基本资料
     *
     * @param dto
     * @author songlin
     */
    public void updateInfo(RegisterUserDto dto);

    /**
     * 修改密码
     *
     * @param dto
     * @author songlin
     */
    public void updatePasswd(RegisterUserDto dto);

    /**
     * 删除医生及登录账号
     *
     * @param id
     * @author songlin
     */
    public AdminUser deleteDoctor(String id);

    public AdminUser findByOpenid(String openid);


    /**
     * 回滚已删除的用户
     *
     * @param id
     * @author songlin
     */
    public AdminUser fallbackDeleteDoctor(String id);


    public List<AdminUser> findAll(String appid);

    /**
     * 普通的用户列表
     *
     * @param page
     * @return
     */
    public Pages<ListUsers> findForUserList(Pages page);

    /**
     * 查询已删除了的账号
     *
     * @param page
     * @return
     * @author songlin
     */
    public Pages<AdminUser> findLockUser(Pages page);
}
