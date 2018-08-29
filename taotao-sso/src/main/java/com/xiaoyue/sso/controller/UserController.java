/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserController
 * Author:   Administrator
 * Date:     2018/8/23 13:04
 * Description: 用户注册Controller
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xiaoyue.sso.controller;

import com.xiaoyue.pojo.TbUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.xiaoyue.sso.service.UserService;
import com.xiaoyue.utils.ExceptionUtil;
import com.xiaoyue.utils.TaotaoResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 〈一句话功能简述〉<br>
 * 〈用户注册Controller〉
 *
 * @author Administrator
 * @create 2018/8/23
 * @since 1.0.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/check/{param}/{type}", method = RequestMethod.GET)
    @ResponseBody
    public Object checkData(@PathVariable String param, @PathVariable Integer type, String callback) {
        TaotaoResult taotaoResult;
        try {
            taotaoResult = userService.checkDate(param, type);
        } catch (Exception e) {
            taotaoResult = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }

        if (!StringUtils.isEmpty(callback)) {
            MappingJacksonValue jacksonValue = new MappingJacksonValue(taotaoResult);
            jacksonValue.setJsonpFunction(callback);
            return jacksonValue;
        }

        return taotaoResult;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult register(TbUser tbUser) {
        TaotaoResult taotaoResult;
        try {
            taotaoResult = userService.register(tbUser);
        } catch (Exception e) {
            e.printStackTrace();
            taotaoResult = TaotaoResult.build(400, "注册失败. 请校验数据后请再提交数据.");
        }
        return taotaoResult;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult login(String username, String password, HttpServletRequest request,
            HttpServletResponse response) {
        TaotaoResult taotaoResult;
        try {
            taotaoResult = userService.login(username, password, request, response);
        } catch (Exception e) {
            e.printStackTrace();
            taotaoResult = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return taotaoResult;
    }

    @RequestMapping(value = "/token/{token}", method = RequestMethod.GET)
    @ResponseBody
    public Object getUserByToken(@PathVariable String token, String callback) {
        TaotaoResult taotaoResult;
        try {
            taotaoResult = userService.getUserByToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            taotaoResult = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }

        if (!StringUtils.isEmpty(callback)) {
            MappingJacksonValue jacksonValue = new MappingJacksonValue(taotaoResult);
            jacksonValue.setJsonpFunction(callback);
            return jacksonValue;
        }

        return taotaoResult;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public Object logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TaotaoResult taotaoResult;
        try {
            taotaoResult = userService.logout(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            taotaoResult = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }

//        if (!StringUtils.isEmpty(callback)) {
//            MappingJacksonValue jacksonValue = new MappingJacksonValue(taotaoResult);
//            jacksonValue.setJsonpFunction(callback);
//            return jacksonValue;
//        }

        return taotaoResult;
    }

}