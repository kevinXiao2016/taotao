package com.xiaoyue.search.controller;

import com.xiaoyue.search.service.ItemService;
import com.xiaoyue.utils.ExceptionUtil;
import com.xiaoyue.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 〈商品控制器〉
 *
 * @author xiaoyue
 * @create 2018/8/29 11:01
 * @since 1.0.0
 */
@Controller
@RequestMapping("/manager")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/importall")
    @ResponseBody
    public TaotaoResult importAll() {
        TaotaoResult result = null;
        try {
            result = itemService.importItemToIndex();
        } catch (Exception e) {
            e.printStackTrace();
            result = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return result;
    }

}