package org.bobjiang.mvc.handler;

import com.bobjiang.crowd.constant.CrowdConstant;
import com.github.pagehelper.PageInfo;
import org.bobjiang.entity.Admin;
import org.bobjiang.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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


    /**
     * 登录操作handler
     * @param username
     * @param password
     * @param session
     * @return page
     */
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

    /**
     * 退出登录
     * @param session
     * @return login page
     */
    @RequestMapping("/admin/login/doLogout.html")
    public String doLogout(HttpSession session){
        //传入session对象,通过invalidate将当前session设置为失效（相当于清除登录信息）
        session.invalidate();
        return "redirect:/admin/login/page.html";
    }


    /**
     * 显示admin的数据
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @param modelMap
     * @return admin-page
     */
    @RequestMapping("/admin/page/page.html")
    public String getAdminPage(
            // 传入的关键字，若未传入，默认值为一个空字符串（不是null）
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            // 传入的页码，默认值为1
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            // 传入的页面大小，默认值为5
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            ModelMap modelMap) {
        //从AdminService中得到对应传参的列表
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);
        //将得到的PageInfo存入modelMap，传给前端
        modelMap.addAttribute(CrowdConstant.NAME_PAGE_INFO,pageInfo);
        //进入对应的显示管理员信息的页面（/WEB-INF/admin-page.jsp）
        return "admin-page";
    }
}
