package cn.wuxia.project.security.core.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wuxia.project.security.core.user.dao.OrganizationDao;
import cn.wuxia.project.security.core.user.entity.Organization;
import cn.wuxia.project.security.core.user.service.OrganizationService;
import cn.wuxia.project.common.dao.CommonDao;
import cn.wuxia.project.common.service.impl.CommonServiceImpl;

import java.util.List;

@Service
public class OrganizationServiceImpl extends CommonServiceImpl<Organization, String> implements OrganizationService {

	@Autowired
	private OrganizationDao hospitalDao;

	@Override
	protected CommonDao<Organization, String> getCommonDao() {
		return hospitalDao;
	}

	@Override
	public List<Organization> findAll(String appid) {
		return hospitalDao.findBy("platform", appid);
	}
}
