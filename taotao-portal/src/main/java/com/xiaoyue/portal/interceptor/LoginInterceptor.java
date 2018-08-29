/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: LoginInterceptor
 * Author:   Administrator
 * Date:     2018/8/24 13:06
 * Description: 登录拦截器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xiaoyue.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xiaoyue.pojo.TbUser;
import com.xiaoyue.portal.service.impl.UserServiceImpl;
import com.xiaoyue.utils.CookieUtils;

/**
 * 〈一句话功能简述〉<br>
 * 〈登录拦截器〉
 *
 * @author Administrator
 * @create 2018/8/24
 * @since 1.0.0
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserServiceImpl userService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");

        // 没有登录
        if (StringUtils.isBlank(token)) {
            // 跳转登录页面
            response.sendRedirect(userService.getSSO_BASE_URL() + userService.getSSO_PAGE_LOGIN() + "?redirect="
                    + request.getRequestURL());
            return false;
        }

        // token有，说明已登录，判断是否超时
        TbUser user = userService.getUserByToken(token);
        if (user == null) {
            // 登录超时了，或者查询出错
            // 重新登录
            response.sendRedirect(userService.getSSO_BASE_URL() + userService.getSSO_PAGE_LOGIN() + "?redirect="
                    + request.getRequestURL());
            return false;
        } else {
            // 取到用户信息，已登录
            request.setAttribute("user", user);
            return true;
        }
    }
    
    private String getBaseURL(HttpServletRequest request) {
        String url = request.getScheme()
                + "://"
                + request.getServerName()
                + ":"
                + request.getServerPort()
                + request.getContextPath()
                + request.getRequestURI();
        return url;
    }
    
    
}