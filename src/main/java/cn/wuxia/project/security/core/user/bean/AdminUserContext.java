package cn.wuxia.project.security.core.user.bean;

import cn.wuxia.project.common.security.UserContext;

import java.util.Collection;

public class AdminUserContext extends UserContext {

    private String departmentId;

    private String userType;

    public AdminUserContext(String id, String name, String mobile, String headImg, Collection<String> authorities) {
        super(id, name, mobile, headImg, authorities);
    }

    public AdminUserContext(String id, String name, String mobile) {
        super(id, name, mobile);
    }


    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
