/*
 * Created on :2013年8月12日 Author :songlin.li Change History Version Date Author
 * Reason <Ver.No> <date> <who modify> <reason>
 */
package cn.wuxia.project.security.core.user.dao;

import cn.wuxia.project.basic.core.common.BaseCommonDao;
import cn.wuxia.project.security.core.user.bean.ListUsers;
import cn.wuxia.project.security.core.user.entity.AdminUser;
import cn.wuxia.project.security.core.user.enums.UserTypeEnum;
import cn.wuxia.common.orm.query.Pages;
import cn.wuxia.common.spring.SpringContextHolder;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class AdminUserDao extends BaseCommonDao<AdminUser, String> {

    private Map<String, String> queryMap = SpringContextHolder.getBean("admin_user-query");

    public AdminUser findByMobile(String appid, String mobile) {
        return findUnique(Restrictions.eq("appid", appid), Restrictions.eq("mobile", mobile));
    }

    public AdminUser findByOpenid(String openid) {
        return findUniqueBy("openid", openid);
    }


    public List<AdminUser> findAllDoctor(String appid) {
        String hql = "from AdminUser WHERE type = ? order by order_";
        String sql = "SELECT * FROM u_admin_user WHERE appid=? and type=? ORDER BY ORDER_";
        return query(sql, AdminUser.class, appid, UserTypeEnum.NORMAL);
        // return find(hql, UserTypeEnum.DOCTOR);
    }

    /**
     * 普通的用户列表
     *
     * @param page
     * @return
     */
    public Pages<ListUsers> findForUserList(Pages page) {
        String sql = queryMap.get("findUserList_sql");
        return super.findPageBySql(page, ListUsers.class, sql);
    }

    /**
     * 查询已删除了的账号
     *
     * @param page
     * @return
     * @author songlin
     */
    public Pages<AdminUser> findLockUser(Pages page) {
        String sql = "select d.* from u_admin_User d where is_Obsolete_Date is not null";
        return findPageBySql(page, AdminUser.class, sql);
    }
}
