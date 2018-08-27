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

import com.xiaoyue.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 〈一句话功能简述〉<br>
 * 〈登录拦截器〉
 *
 * @author Administrator
 * @create 2018/8/24
 * @since 1.0.0
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        
        //没有登录
        if (StringUtils.isBlank(token)) {
            // 跳转登录页面
            response.sendRedirect();
            return false;
        }
        
        //token有，说明已登录，判断是否超时
        
        
        
        
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }
}