package com.xiaoyue.controller;

import com.xiaoyue.pojo.EasyUIResult;
import com.xiaoyue.pojo.TbItemCat;
import com.xiaoyue.pojo.TbItemParam;
import com.xiaoyue.service.ItemCatService;
import com.xiaoyue.service.ItemParamService;
import com.xiaoyue.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@RequestMapping("/item/param")
@Controller
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;
    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/list")
    @ResponseBody
    public EasyUIResult queryItemParamList(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "30") Integer rows) {
        EasyUIResult itemParamList = itemParamService.getItemParamList(page, rows);
        return itemParamList;
    }

    @RequestMapping("/query/itemcatid/{id}")
    @ResponseBody
    public TaotaoResult queryItemCatById(@PathVariable Long id) {
        TaotaoResult taotaoResult = itemParamService.getItemParamById(id);
        return taotaoResult;
    }

    @RequestMapping("/save/{cid}")
    @ResponseBody
    public TaotaoResult addItemParam(@PathVariable Long cid, @RequestParam String paramData) {
        TbItemParam tbItemParam = new TbItemParam();
        tbItemParam.setItemCatId(cid);
        tbItemParam.setParamData(paramData);
        tbItemParam.setCreated(new Date());
        tbItemParam.setUpdated(new Date());

        itemParamService.saveItemParam(tbItemParam);

        return TaotaoResult.ok();
    }


}
