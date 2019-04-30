package cn.wuxia.project.security.controller;

import cn.wuxia.common.util.PropertiesUtils;
import cn.wuxia.project.basic.mvc.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

@Controller
@RequestMapping("/auth/*")
public class AuthController extends BaseController {

    private Properties prop = PropertiesUtils.loadProperties("classpath:/security.properties", "classpath:/properties/security.properties");

    private String loginUrl = prop.getProperty("cas.loginUrl");

    private String platform = prop.getProperty("cas.platform");

    // 进入登录页面
    @RequestMapping(value = {"/login"})
    public String login(HttpServletRequest request, Model model) {
        return "redirect:" + loginUrl + "?service=" + getServerHttpPath() + "/login/cas&platform=" + getPlatform();
    }

    /**
     * 项目变为登录状态
     *
     * @return
     * @author songlin
     */
    @RequestMapping(value = "/check")
    public String check(String ticket) {
        return "redirect:/j_spring_cas_security_check?" + ticket;
    }

    @RequestMapping(value = "/logout")
    public String logout(String ticket) {
        return "redirect:/logout";
    }


}
