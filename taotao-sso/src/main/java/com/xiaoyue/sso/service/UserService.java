package com.xiaoyue.sso.service;

import com.xiaoyue.pojo.TbUser;
import com.xiaoyue.utils.TaotaoResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {

    /**
     * 检查信息有效性
     * 
     * @param message
     * @param type
     * @return
     */
    TaotaoResult checkDate(String message, Integer type);

    /**
     * 注册
     * 
     * @param user
     * @return
     */
    TaotaoResult register(TbUser user);

    /**
     * 登录
     * 
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     */
    TaotaoResult login(String username, String password, HttpServletRequest request, HttpServletResponse response);

    /**
     * 通过token查询用户信息
     * 
     * @param token
     * @return
     * @throws Exception
     */
    TaotaoResult getUserByToken(String token) throws Exception;
	
	TaotaoResult logout(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
