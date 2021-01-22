package org.bobjiang.mvc.handler;

import com.bobjiang.crowd.constant.CrowdConstant;
import org.bobjiang.entity.Admin;
import org.bobjiang.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author BobJiang
 * @version 1.0
 * @date 2021-01-23 2:38
 */
@Controller
public class AdminHandler {
    @Autowired
    AdminService adminService;

    @RequestMapping("/admin/login/doLogout.html")
    public String doLogout(HttpSession session){
        //传入session对象,通过invalidate将当前session设置为失效（相当于清除登录信息）
        session.invalidate();
        return "redirect:/admin/login/page.html";
    }

    //登录操作的handler
    @RequestMapping("/admin/login/doLogin.html")
    public String doLogin(
            @RequestParam("login-user") String username,
            @RequestParam("login-pwd") String password,
            HttpSession session) {
        //通过service层方法得到Admin对象
        Admin admin = adminService.getAdminByUsername(username,password);

        //将Admin对象放入Session域
        session.setAttribute(CrowdConstant.LOGIN_ADMIN_NAME, admin);

        //重定向到登录完成后的主页面（重定向防止重复提交表单，增加不必要的数据库访问）
        return "redirect:/admin/main/page.html";
    }
}
