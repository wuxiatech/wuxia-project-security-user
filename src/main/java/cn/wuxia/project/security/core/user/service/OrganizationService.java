package cn.wuxia.project.security.core.user.service;

import cn.wuxia.project.security.core.user.entity.Organization;
import cn.wuxia.project.common.service.CommonService;

import java.util.List;

public interface OrganizationService extends CommonService<Organization, String> {

    public List<Organization> findAll(String appid);
}
