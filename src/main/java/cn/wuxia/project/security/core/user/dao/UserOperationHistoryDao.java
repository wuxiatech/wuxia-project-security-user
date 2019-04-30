package cn.wuxia.project.security.core.user.dao;

import java.util.ArrayList;
import java.util.List;

import cn.wuxia.project.basic.core.common.BaseCommonDao;
import cn.wuxia.project.security.core.user.entity.UserOperationHistory;
import org.springframework.stereotype.Component;


@Component
public class UserOperationHistoryDao extends BaseCommonDao<UserOperationHistory, String> {

    /**
     * 查找用户是否第一次登录
     * @author guwen
     */
    public Boolean findUserOperation(String uid) {
        List<Object> values = new ArrayList<>();
        String sql = "SELECT id FROM u_user_operation_history where operation_User_Id = ? ";
        values.add(uid);
        Long res = super.countSQLResult(sql.toString(), values.toArray());
        if (res <= 0) {
            return true;
        } else {
            return false;
        }
    }
    
}
