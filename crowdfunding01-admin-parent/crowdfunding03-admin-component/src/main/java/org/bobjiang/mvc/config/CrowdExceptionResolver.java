package org.bobjiang.mvc.config;

import com.bobjiang.crowd.constant.CrowdConstant;
import com.bobjiang.crowd.exception.LoginAcctAlreadyInUseException;
import com.bobjiang.crowd.exception.LoginAcctAlreadyInUseForUpdateException;
import com.bobjiang.crowd.exception.LoginFailedException;
import com.bobjiang.crowd.util.CrowdUtil;
import com.bobjiang.crowd.util.ResultEntity;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author BobJiang
 * @version 1.0
 * @date 2021-01-22 16:01
 * 基于注解的异常处理类（实测，注解版异常处理比xml版本优先生效）
 */
@ControllerAdvice
public class CrowdExceptionResolver {
    /**
     * 处理登录异常
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = {LoginFailedException.class})
    public ModelAndView resolveException(LoginFailedException exception,
                                         HttpServletRequest request, HttpServletResponse response
    ) throws IOException {
        String viewName = "admin-login";

        return commonCode(exception,request,response,viewName);
    }

    /**
     * 处理添加账户时账户名重复异常
     * @param exception
     * @param request
     * @param response
     * @return admin-add page
     * @throws IOException
     */
    @ExceptionHandler(value = {LoginAcctAlreadyInUseException.class})
    public ModelAndView resolveException(LoginAcctAlreadyInUseException exception,
                                         HttpServletRequest request, HttpServletResponse response
    ) throws IOException {
        String viewName = "admin-add";

        return commonCode(exception,request,response,viewName);
    }

    /**
     * 处理更新账户时账户名重复异常
     * @param exception
     * @param request
     * @param response
     * @return error page
     * @throws IOException
     */
    @ExceptionHandler(value = {LoginAcctAlreadyInUseForUpdateException.class})
    public ModelAndView resolveException(LoginAcctAlreadyInUseForUpdateException exception,
                                         HttpServletRequest request, HttpServletResponse response
    ) throws IOException {
        String viewName = "system-error";

        return commonCode(exception,request,response,viewName);
    }


    /**
     * 不同异常的可重用代码
     * @param exception
     * @param request
     * @param response
     * @param viewName
     * @return
     * @throws IOException
     */
    private ModelAndView commonCode(
            //触发的异常，此处借助多态
            Exception exception,
            //客户器端的请求
            HttpServletRequest request,
            //服务端的响应
            HttpServletResponse response,
            //指定普通页面请求时，去的错误页面
            String viewName
    ) throws IOException {
        boolean judgeRequestType = CrowdUtil.judgeRequestType(request);
        if (judgeRequestType){
            //if判断-是json请求
            ResultEntity<Object> failed = ResultEntity.failed(exception.getMessage());
            //创建Gson对象
            Gson gson = new Gson();
            //将ResultEntity对象转换成json格式
            String json = gson.toJson(failed);
            //通过原生servlet的response传回异常内容
            response.getWriter().write(json);
            //此时只需要返回null（因为是通过json格式返回数据）
            return null;
        } else {
            //if判断-是普通页面请求
            //创建ModelAndView对象
            ModelAndView modelAndView = new ModelAndView();
            //设置触发异常跳转的页面（会自动被视图解析器加上前后缀）
            modelAndView.setViewName(viewName);
            //将异常信息加入
            modelAndView.addObject(CrowdConstant.ATTR_NAME_EXCEPTION, exception);
            //返回设置完成的ModelAndView
            return modelAndView;
        }
    }
}




