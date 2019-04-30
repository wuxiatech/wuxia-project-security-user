/*
* Created on :2016年6月17日
* Author     :songlin
* Change History
* Version       Date         Author           Reason
* <Ver.No>     <date>        <who modify>       <reason>
* Copyright 2014-2020 www.ibmall.cn All right reserved.
*/
package cn.wuxia.project.security.core.user.service;

import cn.wuxia.project.security.core.user.entity.UserPortraitTag;
import cn.wuxia.project.common.service.CommonService;

public interface UserPortraitTagService extends CommonService<UserPortraitTag, String> {

    public UserPortraitTag findByUserAndGroup(String userid, String groupcode) ;
}
