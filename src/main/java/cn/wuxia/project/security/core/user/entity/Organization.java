package cn.wuxia.project.security.core.user.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import cn.wuxia.project.common.model.ModifyInfoEntity;

@Entity
@Table(name = "u_org_organization")
@Where(clause = ModifyInfoEntity.ISOBSOLETE_DATE_IS_NULL)
public class Organization extends ModifyInfoEntity {

    private static final long serialVersionUID = 1L;

    private String platform;

    private String name;

    private String simpleName;

    private List<Department> departments;

    public Organization() {
        super();
    }

    public Organization(String orgName) {
        super();
        this.name = orgName;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Column(name = "ORG_NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column(name = "ORG_SIMPLE_NAME")
    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    @OneToMany
    @JoinColumn(name = "organization_id")
    @OrderBy(value = "createdBy desc")
    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
