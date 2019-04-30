package cn.wuxia.project.security.core.user.entity;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Document;

import cn.wuxia.project.security.core.user.bean.PortraitTagDto;
import cn.wuxia.project.common.model.ModifyInfoMongoEntity;

/**
 * The persistent class for the UserPortrait database table.
 * 
 * @author songlin.li
 * @since 2017-04-27
 */
@Document(collection = "user_portrait_tag")
public class UserPortraitTag extends ModifyInfoMongoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    /**
     * 标签分组名称
     */
    private String group;

    private String operator;

    List<PortraitTagDto> tags;

    public UserPortraitTag() {
        super();
    }

    @NotBlank
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public List<PortraitTagDto> getTags() {
        return tags;
    }

    public void setTags(List<PortraitTagDto> tags) {
        this.tags = tags;
    }
}
