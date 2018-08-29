package com.xiaoyue.rest.controller;

import com.xiaoyue.pojo.TbItem;
import com.xiaoyue.pojo.TbItemDesc;
import com.xiaoyue.pojo.TbItemParamItem;
import com.xiaoyue.rest.service.ItemService;
import com.xiaoyue.utils.ExceptionUtil;
import com.xiaoyue.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 〈商品信息查询〉
 *
 * @author xiaoyue
 * @create 2018/8/29 16:06
 * @since 1.0.0
 */
@Controller
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{id}")
    @ResponseBody
    public TaotaoResult getItemById(@PathVariable Long id) {
        // 有效性验证
        if (id == null) {
            return TaotaoResult.build(400, "参数中必须包含id");
        }
        TbItem tbItem = null;
        // 根据id查询商品信息
        try {
            tbItem = itemService.getItemById(id);
        } catch (Exception e) {
            e.printStackTrace();
            // 发生异常时返回异常信息
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return TaotaoResult.ok(tbItem);
    }

    @RequestMapping("/itemdesc/{id}")
    @ResponseBody
    public TaotaoResult getItemDescById(@PathVariable Long id) {
        // 有效性验证
        if (id == null) {
            return TaotaoResult.build(400, "参数中必须包含id");
        }
        TbItemDesc tbItemDesc = null;
        // 根据id查询商品明细信息
        try {
            tbItemDesc = itemService.getItemDescById(id);
        } catch (Exception e) {
            e.printStackTrace();
            // 发生异常时返回异常信息
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return TaotaoResult.ok(tbItemDesc);
    }

    @RequestMapping("/itemparam/{id}")
    @ResponseBody
    public TaotaoResult getItemParamById(@PathVariable Long id) {
        // 有效性验证
        if (id == null) {
            return TaotaoResult.build(400, "参数中必须包含id");
        }
        TbItemParamItem tbItemParamItem = null;
        // 根据id查询商品规格参数信息
        try {
            tbItemParamItem = itemService.getItemParamById(id);
        } catch (Exception e) {
            e.printStackTrace();
            // 发生异常时返回异常信息
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return TaotaoResult.ok(tbItemParamItem);
    }

}