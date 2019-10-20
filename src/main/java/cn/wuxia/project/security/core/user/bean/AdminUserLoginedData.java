/*
 * Created on :08 Nov, 2014
 * Author     :huangzhihua
 * Change History
 * Version       Date         Author           Reason
 * <Ver.No>     <date>        <who modify>       <reason>
 * Copyright 2014-2020 wuxia.tech All right reserved.
 */
package cn.wuxia.project.security.core.user.bean;

import cn.wuxia.project.security.handler.bean.UserLoginedData;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author songlin
 */
@Getter
@Setter
@JsonAutoDetect
public class AdminUserLoginedData extends UserLoginedData implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;

    private String unionid; //微信联合id，用于统一用户在每个微信应用唯一标识

    private String departmentId;

    private String type;
}
