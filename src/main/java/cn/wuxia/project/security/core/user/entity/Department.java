package cn.wuxia.project.security.core.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import cn.wuxia.project.common.model.ModifyInfoEntity;

@Entity
@Table(name = "u_org_department")
@Where(clause = ModifyInfoEntity.ISOBSOLETE_DATE_IS_NULL)
public class Department extends ModifyInfoEntity {

    private static final long serialVersionUID = 1L;

    private String departmentName;

    private String organizationId;

    private String parentDepartmentId;

    public Department() {
        super();
    }

    public Department(String departmentName) {
        super();
        this.departmentName = departmentName;
    }

    @Column(name = "DEPARTMENT_NAME")
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Column(name = "ORGANIZATION_ID")
    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }


    @Column(name = "PARENT_DEPARTMENT_ID")
    public String getParentDepartmentId() {
        return parentDepartmentId;
    }

    public void setParentDepartmentId(String parentDepartmentId) {
        this.parentDepartmentId = parentDepartmentId;
    }

}
