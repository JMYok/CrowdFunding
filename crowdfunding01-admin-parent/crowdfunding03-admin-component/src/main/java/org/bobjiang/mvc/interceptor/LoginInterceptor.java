package org.bobjiang.mvc.interceptor;

import com.bobjiang.crowd.constant.CrowdConstant;
import com.bobjiang.crowd.exception.AccessForbiddenException;
import org.bobjiang.entity.Admin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author BobJiang
 * @version 1.0
 * @date 2021-01-23 3:46
 * 用来在未登录时访问受保护页面时进行拦截并抛出AccessForbiddenException
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 1.通过request获得session对象
        HttpSession session = httpServletRequest.getSession();

        // 2.从session域中取出Admin对象
        Admin admin = (Admin) session.getAttribute(CrowdConstant.LOGIN_ADMIN_NAME);

        // 3.判断admin对象是否为空，若为空表示未登录，抛出异常
        if (admin == null){
            throw new AccessForbiddenException(CrowdConstant.MESSAGE_ACCESS_FORBIDDEN);
        }

        // 4.admin对象不为空，表示已登录，放行
        return true;
    }

}
