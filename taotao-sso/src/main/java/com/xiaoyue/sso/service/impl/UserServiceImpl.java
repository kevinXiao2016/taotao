/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserServiceImpl
 * Author:   Administrator
 * Date:     2018/8/23 11:52
 * Description: 检查数据是否可用
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xiaoyue.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.xiaoyue.utils.CookieUtils;
import com.xiaoyue.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.xiaoyue.mapper.TbUserMapper;
import com.xiaoyue.pojo.TbUser;
import com.xiaoyue.pojo.TbUserExample;
import com.xiaoyue.sso.service.UserService;
import com.xiaoyue.utils.TaotaoResult;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 〈一句话功能简述〉<br>
 * 〈检查数据是否可用〉
 *
 * @author Administrator
 * @create 2018/8/23
 * @since 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {

    public static final int USERNAME = 1;
    public static final int PHONE = 2;
    public static final int EMAIL = 3;

    @Autowired
    private TbUserMapper userMapper;
    @Autowired
    private JedisCluster jedisCluster;

    @Value("${USER_TOKEN_KEY}")
    private String USER_TOKEN_KEY;
    @Value("${SESSION_EXPIRE_TIME}")
    private Integer SESSION_EXPIRE_TIME;

    @Override
    public TaotaoResult checkDate(String message, Integer type) {
        TbUserExample userExample = new TbUserExample();
        TbUserExample.Criteria criteria = userExample.createCriteria();

        if (type == USERNAME) {
            criteria.andUsernameEqualTo(message);
        } else if (type == PHONE) {
            criteria.andPhoneEqualTo(message);
        } else if (type == EMAIL) {
            criteria.andEmailEqualTo(message);
        }
        List<TbUser> tbUsers = userMapper.selectByExample(userExample);
        if (tbUsers == null || tbUsers.isEmpty()) {
            return TaotaoResult.ok(true);
        }
        return TaotaoResult.ok(false);
    }

    @Override
    public TaotaoResult register(TbUser user) {
        // 有效性验证
        if (StringUtils.isBlank(user.getUsername())) {
            return TaotaoResult.build(400, "用户名不能为空");
        }
        if (StringUtils.isBlank(user.getPassword())) {
            return TaotaoResult.build(400, "密码不能为空");
        }
        if (StringUtils.isBlank(user.getPhone())) {
            return TaotaoResult.build(400, "手机不能为空");
        }
        // 转换md5
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        // 完善user信息
        user.setCreated(new Date());
        user.setUpdated(new Date());

        // 添加到数据库
        userMapper.insert(user);

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult login(String username, String password, HttpServletRequest request,
            HttpServletResponse response) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();

        criteria.andUsernameEqualTo(username);

        List<TbUser> tbUsers = userMapper.selectByExample(example);
        if (null == tbUsers || tbUsers.isEmpty()) {
            return TaotaoResult.build(400, "用户不存在");
        }

        // 校验密码
        TbUser tbUser = tbUsers.get(0);
        if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(tbUser.getPassword())) {
            return TaotaoResult.build(400, "密码错误");
        }

        // 写入redis，返回token
        String token = UUID.randomUUID().toString();
        jedisCluster.set(USER_TOKEN_KEY + ":" + token, JsonUtils.objectToJson(tbUser));
        jedisCluster.expire(USER_TOKEN_KEY + ":" + token, SESSION_EXPIRE_TIME);

        // 添加cookie
        CookieUtils.setCookie(request, response, "TT_TOKEN", token);

        return TaotaoResult.ok(token);
    }

    @Override
    public TaotaoResult getUserByToken(String token) throws Exception {
        // 从redis中取用户信息
        String userJson = jedisCluster.get(USER_TOKEN_KEY + ":" + token);

        if (StringUtils.isBlank(userJson)) {
            return TaotaoResult.build(400, "该用户已过期");
        }
        // 把json转换成user对象
        TbUser user = JsonUtils.jsonToPojo(userJson, TbUser.class);
        // 更新用户有效期
        jedisCluster.expire(USER_TOKEN_KEY + ":" + token, SESSION_EXPIRE_TIME);

        return TaotaoResult.ok(user);
    }

    @Override
    public TaotaoResult logout(String token, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // 更新用户有效期
        jedisCluster.expire(USER_TOKEN_KEY + ":" + token, 0);
        // 删除cookie
        CookieUtils.deleteCookie(request, response, "TT_TOKEN");
        return TaotaoResult.ok();
    }

}