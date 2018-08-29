package com.xiaoyue.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyue.pojo.TbItemDesc;
import com.xiaoyue.portal.pojo.ItemInfo;
import com.xiaoyue.portal.service.ItemService;

/**
 * 〈商品查询〉
 *
 * @author xiaoyue
 * @create 2018/8/29 16:09
 * @since 1.0.0
 */
@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/{id}")
    public String showItem(@PathVariable Long id, Model model) throws Exception {
        // 取商品信息
        ItemInfo item = itemService.getItemById(id);
        // 把结果传递给页面
        model.addAttribute("item", item);
        // 返回逻辑视图
        return "item";
    }

    @RequestMapping(value = "/desc/{id}", produces = MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
    @ResponseBody
    public String showItemDesc(@PathVariable Long id) throws Exception {
        // 取商品描述
        TbItemDesc itemDesc = itemService.geTbItemDescById(id);
        // 返回商品描述信息
        return itemDesc.getItemDesc();
    }

    @RequestMapping(value = "/param/{id}", produces = MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
    @ResponseBody
    public String showItemParam(@PathVariable Long id) throws Exception {
        // 取规格参数
        String itemParamItem = itemService.geTbItemParamItemById(id);
        // 返回规格参数信息
        return itemParamItem;
    }

}