package com.xiaoyue.controller;

import com.xiaoyue.pojo.EasyUIResult;
import com.xiaoyue.pojo.TbItem;
import com.xiaoyue.pojo.TbItemDesc;
import com.xiaoyue.service.ItemService;
import com.xiaoyue.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId) {
        TbItem tbItem = itemService.getItemById(itemId);
        return tbItem;
    }

    @RequestMapping("/list")
    @ResponseBody
    public EasyUIResult getItemlist(@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "30") Integer rows) throws Exception {
        //查询商品列表
        EasyUIResult result = itemService.getItemList(page, rows);
        return result;
    }

    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult addItem(TbItem tbItem, String itemParams, String desc) {
        //查询商品列表
        itemService.saveItem(tbItem, desc, itemParams);
        return TaotaoResult.ok();
    }

    @RequestMapping("/update")
    @ResponseBody
    public TaotaoResult updateItem(TbItem tbItem, String desc) {
        //查询商品列表
        itemService.updateItem(tbItem, desc);
        return TaotaoResult.ok();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteItems(String ids) {
        String[] itemIds = ids.split(",");
        Long[] idArray = new Long[itemIds.length];
        for (int i = 0; i < itemIds.length; i++) {
            idArray[i] = Long.valueOf(itemIds[i]);
        }
        itemService.deleteItems(idArray);
        //查询商品列表
        return TaotaoResult.ok();
    }

    @RequestMapping("/desc/{itemId}")
    @ResponseBody
    public TaotaoResult queryItemDesc(@PathVariable Long itemId) {
        TbItemDesc itemDesc = itemService.getItemDesc(itemId);
        TaotaoResult taotaoResult = TaotaoResult.ok(itemDesc);
        return taotaoResult;
    }


}
