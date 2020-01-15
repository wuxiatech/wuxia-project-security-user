/*
* Created on :08 Nov, 2014
* Author     :huangzhihua
* Change History
* Version       Date         Author           Reason
* <Ver.No>     <date>        <who modify>       <reason>
* Copyright 2014-2020 wuxia.tech All right reserved.
*/
package cn.wuxia.project.security.core.user.bean;

import cn.wuxia.common.validator.ValidationEntity;
import cn.wuxia.project.security.core.user.enums.UserGenderEnum;
import cn.wuxia.project.security.core.user.enums.UserTypeEnum;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@JsonAutoDetect
@XmlRootElement(name = "User")
public class RegisterUserDto extends ValidationEntity implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private String appid;

    private String id; // 用户id

    private String mobile;// 账号

    private String password;// 用户密码

    private String openid; // 详细信息id

    private String logo;// 用户头像，可以为url

    private String qrcode; // 二维码

    private String createdBy; // 绑定此账号的创建人

    private String city; // 用户所在城市

    private UserGenderEnum gender; // 用户性别

    private String realName; // 用户真实姓名

    private String cityId; // 所在城市id

    private String title; // 职称

    @JsonIgnore
    private String locationAt;

    @JsonIgnore
    private String departmentId;

    @JsonIgnore
    private String goodAt;

    private Timestamp registerTime;

    @JsonIgnore
    private Boolean status;

    @JsonIgnore
    private UserTypeEnum type;

    private String description;

    private String order_;

    private String extNo;

    @JsonIgnore
    private Boolean answerCall;

    @JsonIgnore
    private String transferCall;

}
