package com.bobjiang.crowd.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author BobJiang
 * @version 1.0
 * @date 2021-01-22 15:39
 */
public class CrowdUtil {

    /**
     * 判断请求类型
     * @param request
     * @return true=json请求;false=普通页面请求
     */
    public static boolean judgeRequestType(HttpServletRequest request){
        String accept = request.getHeader("Accept");
        String header = request.getHeader("X-Requested-With");
        return (accept != null && accept.contains("application/json"))
                ||
                (header != null && header.equals("XMLHttpRequest"));
    }

}
