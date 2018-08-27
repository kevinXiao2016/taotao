/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: PageController
 * Author:   Administrator
 * Date:     2018/8/23 16:30
 * Description: 页面跳转控制
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xiaoyue.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 〈一句话功能简述〉<br>
 * 〈页面跳转控制〉
 *
 * @author Administrator
 * @create 2018/8/23
 * @since 1.0.0
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page) {
        return page;
    }
}