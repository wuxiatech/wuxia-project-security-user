package cn.wuxia.project.security.core.user.dao;

import cn.wuxia.project.basic.core.common.BaseCommonDao;
import cn.wuxia.project.security.core.user.entity.Department;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartmentDao extends BaseCommonDao<Department, String> {

    public List<Department> findAll(String appid){
       String hql = "select d from Department d inner join Organization h on d.organizationId=h.id where h.platform=?";
       return find(hql, appid);
    }
}
