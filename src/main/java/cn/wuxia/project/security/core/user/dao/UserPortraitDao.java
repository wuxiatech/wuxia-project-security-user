/*
* Created on :2016年5月26日
* Author     :Administrator
* Change History
* Version       Date         Author           Reason
* <Ver.No>     <date>        <who modify>       <reason>
* Copyright 2014-2020 www.ibmall.cn All right reserved.
*/
package cn.wuxia.project.security.core.user.dao;

import java.util.List;

import cn.wuxia.project.basic.core.common.BaseCommonDao;
import cn.wuxia.project.security.core.user.entity.UserPortrait;
import org.springframework.stereotype.Repository;

import cn.wuxia.common.util.ListUtil;

@Repository
public class UserPortraitDao extends BaseCommonDao<UserPortrait, String> {

    public List<UserPortrait> findByUserIdAndCategoryId(String userId, String categoryId) {
        String hql = "FROM UserPortrait WHERE categoryId = ? AND userId = ? AND is_Obsolete_Date IS NULL";
        return find(hql, categoryId, userId);
    }

    public UserPortrait findByPatientIdAndTagId(String patientId, String tagId) {
        String hql = "FROM UserPortrait WHERE tagId = ? AND userId = ?";
        List<UserPortrait> list = find(hql, tagId, patientId);
        return ListUtil.isNotEmpty(list) ? list.get(0) : null;
    }

    public void deleteByPatientIdAndTagIds(String patientId, List<String> tagIds) {
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        for (String string : tagIds) {
            if (flag) {
                result.append(",");
            } else {
                flag = true;
            }
            result.append(string);
        }
        String sql = "DELETE FROM u_user_portrait_tag_ref WHERE USER_ID = ? AND TAG_ID IN ('" + result.toString() + "')";
        batchExecuteSql(sql, patientId);
    }

    public void deleteByPatientId(String patientId) {
        String sql = "DELETE FROM u_user_portrait_tag_ref WHERE USER_ID = ?";
        batchExecuteSql(sql, patientId);
    }

    public void deleteByUserIdAndCategoryId(String patientId, String categoryId) {
        String hql = "delete FROM UserPortrait WHERE categoryId = ? AND userId = ?";
        batchExecute(hql, categoryId, patientId);
    }
}
