/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserServiceImpl
 * Author:   Administrator
 * Date:     2018/8/27 16:50
 * Description: 用户管理实现类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xiaoyue.portal.service.impl;

import com.xiaoyue.pojo.TbUser;
import com.xiaoyue.portal.service.UserService;
import com.xiaoyue.utils.HttpClientUtil;
import com.xiaoyue.utils.JsonUtils;
import com.xiaoyue.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 〈一句话功能简述〉<br>
 * 〈用户管理实现类〉
 *
 * @author Administrator
 * @create 2018/8/27
 * @since 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("${SSO_BASE_URL}")
    private String SSO_BASE_URL;
    @Value("${SSO_USER_TOKEN}")
    private String SSO_USER_TOKEN;
    @Value("${SSO_PAGE_LOGIN}")
    private String SSO_PAGE_LOGIN;

    @Override
    public TbUser getUserByToken(String token) {
        TbUser tbUser = null;
        try {
            String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);
            TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbUser.class);
            if (taotaoResult.getStatus() == 200) {
                tbUser = (TbUser) taotaoResult.getData();

            }
        } catch (Exception e) {
            e.printStackTrace();
            tbUser = null;
        }

        return tbUser;
    }
    
    public String getSSO_BASE_URL() {
        return SSO_BASE_URL;
    }
    
    public String getSSO_USER_TOKEN() {
        return SSO_USER_TOKEN;
    }
    
    public String getSSO_PAGE_LOGIN() {
        return SSO_PAGE_LOGIN;
    }
}