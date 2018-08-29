/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserService
 * Author:   Administrator
 * Date:     2018/8/27 16:50
 * Description: 用户管理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xiaoyue.portal.service;

import com.xiaoyue.pojo.TbUser;

/**
 * 〈一句话功能简述〉<br>
 * 〈用户管理〉
 *
 * @author Administrator
 * @create 2018/8/27
 * @since 1.0.0
 */
public interface UserService {

    TbUser getUserByToken(String token);
}