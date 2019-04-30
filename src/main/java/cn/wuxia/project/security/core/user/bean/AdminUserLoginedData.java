/*
* Created on :08 Nov, 2014
* Author     :huangzhihua
* Change History
* Version       Date         Author           Reason
* <Ver.No>     <date>        <who modify>       <reason>
* Copyright 2014-2020 www.ibmall.cn All right reserved.
*/
package cn.wuxia.project.security.core.user.bean;

import cn.wuxia.project.security.handler.bean.UserLoginedData;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@JsonAutoDetect
@XmlRootElement(name = "User")
public class AdminUserLoginedData extends UserLoginedData implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;

    private String pcOpenId;//pc端微信应用opendId

    private String unionId; //微信联合id，用于统一用户在每个微信应用唯一标识

    private String departmentId;

    private String type;

    public String getPcOpenId() {
        return pcOpenId;
    }

    public void setPcOpenId(String pcOpenId) {
        this.pcOpenId = pcOpenId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
