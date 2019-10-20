package cn.wuxia.project.security.core.user.bean;

import cn.wuxia.project.common.security.UserContext;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class AdminUserContext extends UserContext {

    private String departmentId;

    private String userType;

    public AdminUserContext(String id, String name, String mobile, String headImg, Collection<String> authorities) {
        super(id, name, mobile, headImg, authorities);
    }

    public AdminUserContext(String id, String name, String mobile) {
        super(id, name, mobile);
    }

}
