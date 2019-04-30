package cn.wuxia.project.security.core.user.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import cn.wuxia.project.basic.core.conf.entity.CustomTag;
import cn.wuxia.project.common.model.ModifyInfoEntity;

/**
 * The persistent class for the UserPortrait database table.
 * 
 * @author songlin.li
 * @since 2017-04-27
 */
@Entity
@Table(name = "u_user_portrait_tag_ref")
public class UserPortrait extends ModifyInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private Double weight;

    private String tagId;

    private String categoryId; // 标签分类 - 可为空

    private String categoryName; // 标签分类 - 可为空

    /**
     * 标签是属于自定义标签还是属于字典配置标签
     */
    private TagType tagType;

    private String tagName;

    public enum TagType {
        patient, doctor, dic
    }

    public UserPortrait() {
        super();
    }

    private UserPortrait(CustomTag tag) {
        super();
        this.tagId = tag.getId();
        this.tagName = tag.getTagName();
        this.categoryId = tag.getCategoryId();
        this.categoryName = tag.getCategoryName();
    }

    public UserPortrait(String userId, CustomTag tag, TagType tagType) {
        this(tag);
        this.userId = userId;
        this.tagType = tagType;
    }

    public UserPortrait(String userId, CustomTag tag, TagType tagType, double weight) {
        this(userId, tag, tagType);
        this.weight = weight;
    }

    @Column(name = "TAG_ID")
    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    @Column(name = "TAG_WEIGHT")
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @NotBlank
    @Column(name = "USER_ID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "CATEGORY_ID")
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Length(max = 32)
    @NotBlank
    @Column(name = "CATEGORY_NAME")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Column(name = "TAG_NAME")
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @NotNull
    @Column(name = "TAG_TYPE")
    @Enumerated(EnumType.STRING)
    public TagType getTagType() {
        return tagType;
    }

    public void setTagType(TagType tagType) {
        this.tagType = tagType;
    }
}
