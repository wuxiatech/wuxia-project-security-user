package cn.wuxia.project.security.core.user.entity;

import cn.wuxia.project.common.model.ModifyInfoEntity;
import cn.wuxia.project.security.core.user.enums.UserGenderEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * The persistent class for the u_user database table.
 */
@Entity
@Table(name = "u_admin_user")
@Where(clause = ModifyInfoEntity.ISOBSOLETE_DATE_IS_NULL)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminUser extends ModifyInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String casUserId;

    @Length(max = 32)
    private String realName;

    @Length(max = 32)
    private String title;

    private UserGenderEnum gender;

    private String mobile;

    private Integer age;

    private String logo;

    private String qrcode;

    @Length(max = 18)
    private String idCard;

    private String departmentId;

    @Length(max = 64)
    private String goodAt;

    private String cityId;

    private Timestamp registerTime;

    private Boolean status;

    private String type;

    private String openid;

    private String devOpenid;

    private String unionid;

    private String description;

    private String order_;

    private String extNo;

    private String appid;

    public AdminUser() {
        super();
    }

    public AdminUser(String id) {
        super(id);
    }

    @Column(name = "CAS_USER_ID")
    public String getCasUserId() {
        return this.casUserId;
    }

    public void setCasUserId(String casUserId) {
        this.casUserId = casUserId;
    }

    @Column(name = "REAL_NAME")
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Enumerated(EnumType.STRING)
    public UserGenderEnum getGender() {
        return gender;
    }

    public void setGender(UserGenderEnum gender) {
        this.gender = gender;
    }

    @Column(columnDefinition = "tinyint")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Column(name = "DEPARTMENT_ID")
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Column(name = "OPEN_ID")
    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Column(name = "DEV_OPEN_ID")
    public String getDevOpenid() {
        return devOpenid;
    }

    public void setDevOpenid(String devOpenid) {
        this.devOpenid = devOpenid;
    }

    @Column(name = "UNION_ID")
    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    @Column(name = "CITY_ID")
    public String getCityId() {
        return this.cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    @Column(name = "IDCARD")
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Column(name = "GOOD_AT")
    public String getGoodAt() {
        return goodAt;
    }

    public void setGoodAt(String goodAt) {
        this.goodAt = goodAt;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "REGISTER_TIME")
    public Timestamp getRegisterTime() {
        return this.registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrder_() {
        return order_;
    }

    public void setOrder_(String order_) {
        this.order_ = order_;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    @Column(name = "ext_no")
    public String getExtNo() {
        return extNo;
    }

    public void setExtNo(String extNo) {
        this.extNo = extNo;
    }


    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
}
