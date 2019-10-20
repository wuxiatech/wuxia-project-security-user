package cn.wuxia.project.security.handler.service;

import cn.wuxia.common.util.StringUtil;
import cn.wuxia.project.basic.core.user.service.UserOperationHistoryService;
import cn.wuxia.project.common.security.UserContextUtil;
import cn.wuxia.project.security.common.MyUserDetails;
import cn.wuxia.project.security.core.user.bean.AdminUserContext;
import cn.wuxia.project.security.core.user.bean.AdminUserLoginedData;
import cn.wuxia.project.security.core.user.bean.UserLoginedDetails;
import cn.wuxia.project.security.core.user.dao.UserLoginedDao;
import cn.wuxia.project.security.core.user.enums.UserGenderEnum;
import cn.wuxia.project.security.core.user.enums.UserOperationEnum;
import cn.wuxia.project.security.core.user.enums.UserTypeEnum;
import cn.wuxia.project.security.handler.bean.UserLoginedData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 定义登录时使用的接口类
 *
 * @author songlin
 * @ Version : V<Ver.No> <2017年5月10日>
 */
@Service
public class UserLoginedServiceImpl implements UserLoginedService {

    @Autowired
    protected UserLoginedDao userLoginedDao;
    @Autowired
    protected UserOperationHistoryService operationHistoryService;

    /**
     * 验证密码
     *
     * @param accountName
     * @param password
     * @return
     * @author songlin
     */
    @Override
    public boolean validatePassword(String accountName, String password) {
        return false;
    }

    @Override
    public UserLoginedData loginByAccountname(String accountName) {
        return userLoginedDao.findByAccountName(accountName);
    }

    @Override
    public UserLoginedData loginByCasUserid(String casUserid) {
        return userLoginedDao.findByCasUserId(casUserid);
    }

    @Override
    public UserLoginedData loginByWxUnionid(String appid, String unionid) {
        return userLoginedDao.findByUnionid(unionid);
    }

    @Override
    public UserLoginedData loginByWxOpenid(String appid, String openid) {
        return userLoginedDao.findByOpenid(openid);
    }

    /**
     * 登录之后的操作
     *
     * @author songlin
     */
    @Override
    public MyUserDetails afterLogin(UserLoginedData loginedDataBean, MyUserDetails myUserDetails) {
        UserLoginedDetails doctorLoginedDetails = new UserLoginedDetails(myUserDetails.getUsername(), myUserDetails.getPassword(),
                myUserDetails.isEnabled(), myUserDetails.isAccountNonExpired(), myUserDetails.isCredentialsNonExpired(),
                myUserDetails.isAccountNonLocked(), myUserDetails.getAuthorities());
        copyPropertiesFromUserToUserDetails((AdminUserLoginedData) loginedDataBean, doctorLoginedDetails);

        operationHistoryService.saveUserOperation(loginedDataBean.getId(), UserOperationEnum.DENGLU.name(), "登录后台");
        return doctorLoginedDetails;
    }

    /**
     * copy Properties From User To UserDetails
     *
     * @param loginedUserData
     * @param userLoginedDetails
     * @author songlin.li
     */
    protected void copyPropertiesFromUserToUserDetails(AdminUserLoginedData loginedUserData, UserLoginedDetails userLoginedDetails) {
        userLoginedDetails.setUid(loginedUserData.getId());
        userLoginedDetails.setDisplayName(loginedUserData.getRealName());
        userLoginedDetails.setMobile(loginedUserData.getAccountName());
        userLoginedDetails.setOpenid(loginedUserData.getOpenid());
        userLoginedDetails.setLogo(loginedUserData.getLogo());
        if (StringUtil.isNotBlank(loginedUserData.getType())) {
            userLoginedDetails.setType(UserTypeEnum.valueOf(loginedUserData.getType()));
        }
        if (StringUtil.isNotBlank(loginedUserData.getGender())) {
            userLoginedDetails.setGender(UserGenderEnum.valueOf(loginedUserData.getGender()));
        }
        AdminUserContext userContext = new AdminUserContext(userLoginedDetails.getUid(), userLoginedDetails.getDisplayName(), userLoginedDetails.getMobile());
        userContext.setDepartmentId(loginedUserData.getDepartmentId());
        userContext.setHeadImg(loginedUserData.getLogo());
        UserContextUtil.saveUserContext(userContext);
    }
}
