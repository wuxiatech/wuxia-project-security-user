package cn.wuxia.project.security.core.user.service.impl;

import cn.wuxia.project.security.core.user.bean.DepartmentBean;
import cn.wuxia.project.security.core.user.dao.DepartmentDao;
import cn.wuxia.project.security.core.user.entity.Department;
import cn.wuxia.project.security.core.user.entity.Organization;
import cn.wuxia.project.security.core.user.service.DepartmentService;
import cn.wuxia.project.security.core.user.service.OrganizationService;
import cn.wuxia.project.common.dao.CommonDao;
import cn.wuxia.project.common.service.impl.CommonServiceImpl;
import cn.wuxia.project.common.support.CacheConstants;
import cn.wuxia.common.util.StringUtil;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl extends CommonServiceImpl<Department, String> implements DepartmentService {

    @Autowired
    private DepartmentDao hospitalDepartmentDao;

    @Autowired
    private OrganizationService hospitalService;

    @Override
    protected CommonDao<Department, String> getCommonDao() {
        return hospitalDepartmentDao;
    }

    @Override
    public List<Department> findByOrganizationId(String organizationId) {
        logger.info("organizationId={}", organizationId);
        return hospitalDepartmentDao.findBy("organizationId", organizationId);
    }

    @Override
    public List<Department> findByParentId(String parentId) {
        logger.info("parentId={}", parentId);
        return hospitalDepartmentDao.find(Restrictions.eqOrIsNull("parentDepartmentId", parentId));
    }

    @Cacheable(key = CacheConstants.CACHED_KEY_DEFAULT + "+#id", value = CacheConstants.CACHED_VALUE_1_DAY)
    @Override
    public DepartmentBean findByDeptId(String id) {
        logger.info("id={}", id);
        Department department = findById(id);
        if (department == null)
            return null;
        DepartmentBean departmentDto = new DepartmentBean();
        departmentDto.setDepartmentId(department.getId());
        departmentDto.setDepartmentName(department.getDepartmentName());
        if (StringUtil.isNotBlank(department.getParentDepartmentId())) {
            Department parentDepartment = findById(department.getParentDepartmentId());
            departmentDto.setParentDepartmentId(parentDepartment.getId());
            departmentDto.setParentDepartmentName(parentDepartment.getDepartmentName());
        }
        if (StringUtil.isNotBlank(department.getOrganizationId())) {
            Organization organization = hospitalService.findById(department.getOrganizationId());
            departmentDto.setOrganizationId(organization.getId());
            departmentDto.setOrganizationName(organization.getName());
        }
        return departmentDto;
    }

    @Override
    public List<Department> findAll(String appid) {
        return hospitalDepartmentDao.findAll(appid);
    }
}
