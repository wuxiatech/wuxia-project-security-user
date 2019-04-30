/*
* Created on :2016年5月26日
* Author     :Administrator
* Change History
* Version       Date         Author           Reason
* <Ver.No>     <date>        <who modify>       <reason>
* Copyright 2014-2020 www.ibmall.cn All right reserved.
*/
package cn.wuxia.project.security.core.user.dao;

import cn.wuxia.project.basic.core.common.BaseCommonMongoDao;
import cn.wuxia.project.security.core.user.entity.UserPortraitTag;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserPortraitTagDao extends BaseCommonMongoDao<UserPortraitTag, String> {

    public UserPortraitTag findByUserAndGroup(String userId, String group){
        return findUnique(Query.query(Criteria.where("userId").is(userId).and("group").is(group)));
    }
}
