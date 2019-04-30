/*
* Created on :2015年10月12日
* Author     :wuwenhao
* Change History
* Version       Date         Author           Reason
* <Ver.No>     <date>        <who modify>       <reason>
* Copyright 2014-2020 www.ibmall.cn All right reserved.
*/
package cn.wuxia.project.security.core.user.service.impl;

import cn.wuxia.project.common.security.UserContext;
import cn.wuxia.project.common.security.UserContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wuxia.project.security.core.user.dao.UserOperationHistoryDao;
import cn.wuxia.project.security.core.user.entity.UserOperationHistory;
import cn.wuxia.project.security.core.user.service.UserOperationHistoryService;
import cn.wuxia.project.common.dao.CommonDao;
import cn.wuxia.project.common.service.impl.CommonServiceImpl;
import cn.wuxia.common.util.DateUtil;
import cn.wuxia.common.util.StringUtil;

@Service
public class UserOperationHistoryServiceImpl extends CommonServiceImpl<UserOperationHistory, String> implements UserOperationHistoryService {

    @Autowired
    private UserOperationHistoryDao userOperationHistoryDao;
    
    
    @Override
    protected CommonDao<UserOperationHistory, String> getCommonDao() {
        return userOperationHistoryDao;
    }


    @Override
    public void saveUserOperation(String uid, String userOperation, String remark) {
        UserOperationHistory history = new UserOperationHistory();
        history.setRemark(remark);
       UserContext userContext = UserContextUtil.getUserContext();
//        String ip = SpringSecurityUtils.getCurrentUserIp();
        //if (StringUtil.isBlank(ip) || StringUtil.equals(ip, "127.0.0.1") || StringUtil.equals(ip, "0:0:0:0:0:0:0:1")) {
        //ip = request.getRemoteAddr();
        //}
//        history.setOperationIp(ip);
        history.setOperationType(userOperation);

        history.setOperationDatetime(DateUtil.newInstanceDate());

        if (StringUtil.isNotBlank(uid)) {
            history.setOperationUserId(uid);
        }
        if (userContext != null) {
            history.setOperationUserId(userContext.getId());
        }
        logger.info("用户操作信息：" + history.toString());
        //        UserOperationHistoryDao userOperationHistoryDao = SpringContextHolder.getBean(UserOperationHistoryDao.class);
        userOperationHistoryDao.save(history);
        
    }


    @Override
    public Boolean findUserOperation(String uid) {
        return userOperationHistoryDao.findUserOperation(uid);
    }



}
