/*
* Created on :08 Nov, 2014
* Author     :huangzhihua
* Change History
* Version       Date         Author           Reason
* <Ver.No>     <date>        <who modify>       <reason>
* Copyright 2014-2020 www.ibmall.cn All right reserved.
*/
package cn.wuxia.project.security.core.user.bean;

import cn.wuxia.common.entity.ValidationEntity;
import cn.wuxia.project.security.core.user.enums.UserGenderEnum;
import cn.wuxia.project.security.core.user.enums.UserTypeEnum;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Timestamp;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public UserGenderEnum getGender() {
        return gender;
    }

    public void setGender(UserGenderEnum gender) {
        this.gender = gender;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String name) {
        this.realName = name;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocationAt() {
        return locationAt;
    }

    public void setLocationAt(String locationAt) {
        this.locationAt = locationAt;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getGoodAt() {
        return goodAt;
    }

    public void setGoodAt(String goodAt) {
        this.goodAt = goodAt;
    }

    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public UserTypeEnum getType() {
        return type;
    }

    public void setType(UserTypeEnum type) {
        this.type = type;
    }

    public void setType(String type) {
        this.type = UserTypeEnum.valueOf(type);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getOrder_() {
        return order_;
    }

    public void setOrder_(String order_) {
        this.order_ = order_;
    }

    public String getExtNo() {
        return extNo;
    }

    public void setExtNo(String extNo) {
        this.extNo = extNo;
    }

    public Boolean getAnswerCall() {
        return answerCall;
    }

    public void setAnswerCall(Boolean answerCall) {
        this.answerCall = answerCall;
    }

    public String getTransferCall() {
        return transferCall;
    }

    public void setTransferCall(String transferCall) {
        this.transferCall = transferCall;
    }
}
