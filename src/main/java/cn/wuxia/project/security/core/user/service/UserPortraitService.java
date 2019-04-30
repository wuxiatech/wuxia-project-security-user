/*
* Created on :2016年6月17日
* Author     :songlin
* Change History
* Version       Date         Author           Reason
* <Ver.No>     <date>        <who modify>       <reason>
* Copyright 2014-2020 www.ibmall.cn All right reserved.
*/
package cn.wuxia.project.security.core.user.service;

import java.util.List;

import cn.wuxia.project.security.core.user.entity.UserPortrait;
import cn.wuxia.project.common.service.CommonService;

public interface UserPortraitService extends CommonService<UserPortrait, String> {

    public List<UserPortrait> findByUserId(String userid);

    /**
     * 根据患者id和分类id查找患者标签
     * 
     * @param patientId
     * @param categoryId
     * @return
     */
    public List<UserPortrait> findByPatientIdAndCategoryId(String patientId, String categoryId);

    /**
     * 通过患者id和tagid查找患者标签中间表信息
     * 
     * @param patientId
     * @param tagId
     * @return
     */
    public UserPortrait findByPatientIdAndTagId(String patientId, String tagId);

    /**
     * 根据患者id和多个tagid删除中间表信息
     * 
     * @param patientId
     * @param tagIds
     * @return
     */
    public boolean deleteByPatientIdAndTagIds(String patientId, List<String> tagIds);

    @Deprecated
    public void deleteByPatientId(String patientId);

    /**
     * 根据分类删除
     * @param patientId
     * @param categoryId
     */
    public void deleteByPatientIdAndCategoryId(String patientId, String categoryId);
}
