/*
* Created on :2016年6月17日
* Author     :songlin
* Change History
* Version       Date         Author           Reason
* <Ver.No>     <date>        <who modify>       <reason>
* Copyright 2014-2020 www.ibmall.cn All right reserved.
*/
package cn.wuxia.project.security.core.user.service.impl;

import cn.wuxia.project.security.core.user.entity.UserPortraitTag;
import cn.wuxia.project.security.core.user.service.UserPortraitTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wuxia.project.security.core.user.dao.UserPortraitTagDao;
import cn.wuxia.project.common.dao.CommonMongoDao;
import cn.wuxia.project.common.service.impl.CommonMongoServiceImpl;

@Service
public class UserPortraitTagServiceImpl extends CommonMongoServiceImpl<UserPortraitTag, String> implements UserPortraitTagService {

    @Autowired
    private UserPortraitTagDao portraitTagDao;

    @Override
    protected CommonMongoDao<UserPortraitTag, String> getCommonDao() {
        return portraitTagDao;
    }

    @Override
    public UserPortraitTag findByUserAndGroup(String userid, String groupcode) {
        return portraitTagDao.findByUserAndGroup(userid, groupcode);
    }
}
