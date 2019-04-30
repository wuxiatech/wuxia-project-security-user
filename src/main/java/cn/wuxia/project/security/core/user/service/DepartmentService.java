package cn.wuxia.project.security.core.user.service;

import cn.wuxia.project.security.core.user.bean.DepartmentBean;
import cn.wuxia.project.security.core.user.entity.Department;
import cn.wuxia.project.common.service.CommonService;

import java.util.List;

public interface DepartmentService extends CommonService<Department, String> {

    /**
     * 
     * @param organizationId
     * @return
     */
    public List<Department> findByOrganizationId(String organizationId);

    /**
     * 根据父id查找
      * @param parentId
     * @return
     */
    public List<Department> findByParentId(String parentId);


    /**
     *
     * @param id
     * @return
     */
    public DepartmentBean findByDeptId(String id);


    /**
     *
     * @param appid
     * @return
     */
    public List<Department> findAll(String appid);
}
