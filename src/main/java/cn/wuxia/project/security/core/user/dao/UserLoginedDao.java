/*
 * Created on :2013年8月12日 Author :songlin.li Change History Version Date Author
 * Reason <Ver.No> <date> <who modify> <reason>
 */
package cn.wuxia.project.security.core.user.dao;

import cn.wuxia.common.util.ListUtil;
import cn.wuxia.common.util.reflection.BeanUtil;
import cn.wuxia.project.basic.core.common.BaseCommonDao;
import cn.wuxia.project.security.core.user.bean.AdminUserLoginedData;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UserLoginedDao extends BaseCommonDao {

    public AdminUserLoginedData findByCasUserId(String casId) {
        String sql = "select * from vw_user_logined_data where userid=?";
        List<Map<String, Object>> list = queryToMap(sql, casId);
        if (ListUtil.isEmpty(list)) {
            return null;
        }
        return BeanUtil.mapToBean(list.get(0), AdminUserLoginedData.class);
    }

    public AdminUserLoginedData findByAccountName(String accountName) {
        String sql = "select * from vw_user_logined_data where accountName=?";
        List<Map<String, Object>> list = queryToMap(sql, accountName);
        if (ListUtil.isEmpty(list)) {
            return null;
        }
        return BeanUtil.mapToBean(list.get(0), AdminUserLoginedData.class);
    }

    public AdminUserLoginedData findByUnionid(String unionid) {
        String sql = "select * from vw_user_logined_data where unionid=?";
        List<Map<String, Object>> list = queryToMap(sql, unionid);
        if (ListUtil.isEmpty(list)) {
            return null;
        }
        return BeanUtil.mapToBean(list.get(0), AdminUserLoginedData.class);
    }

    public AdminUserLoginedData findByOpenid(String openid) {
        String sql = "select * from vw_user_logined_data where openid=?";
        List<Map<String, Object>> list = queryToMap(sql, openid);
        if (ListUtil.isEmpty(list)) {
            return null;
        }
        return BeanUtil.mapToBean(list.get(0), AdminUserLoginedData.class);
    }
}
