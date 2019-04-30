/*
* Created on :2016年6月17日
* Author     :songlin
* Change History
* Version       Date         Author           Reason
* <Ver.No>     <date>        <who modify>       <reason>
* Copyright 2014-2020 www.ibmall.cn All right reserved.
*/
package cn.wuxia.project.security.core.user.service.impl;

import java.util.List;

import cn.wuxia.project.security.core.user.service.UserPortraitService;
import cn.wuxia.project.security.core.user.entity.UserPortrait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wuxia.project.security.core.user.dao.UserPortraitDao;
import cn.wuxia.project.common.dao.CommonDao;
import cn.wuxia.project.common.service.impl.CommonServiceImpl;

@Service
public class UserPortraitServiceImpl extends CommonServiceImpl<UserPortrait, String> implements UserPortraitService {

	@Autowired
	private UserPortraitDao portraitDao;

	@Override
	protected CommonDao<UserPortrait, String> getCommonDao() {
		return portraitDao;
	}

	@Override
	public List<UserPortrait> findByUserId(String userid) {
		return portraitDao.findBy("userId", userid);
	}

	@Override
	public List<UserPortrait> findByPatientIdAndCategoryId(String patientId, String categoryId) {
		return portraitDao.findByUserIdAndCategoryId(patientId, categoryId);
	}

	@Override
	public UserPortrait findByPatientIdAndTagId(String patientId, String tagId) {
		return portraitDao.findByPatientIdAndTagId(patientId, tagId);
	}

	@Override
	public boolean deleteByPatientIdAndTagIds(String patientId, List<String> tagIds) {
		portraitDao.deleteByPatientIdAndTagIds(patientId, tagIds);
		return true;
	}

	@Override
	public void deleteByPatientId(String patientId) {
		portraitDao.deleteByPatientId(patientId);
	}

	@Override
	public void deleteByPatientIdAndCategoryId(String patientId, String categoryId) {
		portraitDao.deleteByUserIdAndCategoryId(patientId, categoryId);
	}

}
