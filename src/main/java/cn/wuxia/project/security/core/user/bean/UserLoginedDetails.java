package cn.wuxia.project.security.core.user.bean;

import cn.wuxia.project.security.core.user.enums.UserGenderEnum;
import cn.wuxia.project.security.core.user.enums.UserTypeEnum;
import cn.wuxia.project.security.common.MyUserDetails;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

/**
 * 用户访问请求的上下文信息
 * 
 * @author songlin.li
 */
@JsonAutoDetect
@JsonIgnoreProperties(value = { "uid", "password", "authorities", "salt", "accountNonExpired", "accountNonLocked", "credentialsNonExpired",
        "enabled" })
public class UserLoginedDetails extends MyUserDetails implements Serializable {

    private static final long serialVersionUID = 542959949959396895L;

    private UserTypeEnum type;

    private UserGenderEnum gender;

    private String openid;

    private String logo;// 用户头像全路径
    public UserLoginedDetails(String loginName, String userPassword, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
                              boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) throws IllegalArgumentException {
        super(loginName, userPassword, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }


    public UserTypeEnum getType() {
        return type;
    }

    public void setType(UserTypeEnum type) {
        this.type = type;
    }

    public UserGenderEnum getGender() {
        return gender;
    }

    public void setGender(UserGenderEnum gender) {
        this.gender = gender;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
