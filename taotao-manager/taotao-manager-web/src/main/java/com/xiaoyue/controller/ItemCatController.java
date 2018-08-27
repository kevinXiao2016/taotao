package com.xiaoyue.controller;

import com.xiaoyue.pojo.TbItem;
import com.xiaoyue.pojo.TbItemCat;
import com.xiaoyue.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/item/cat/")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/list")
    @ResponseBody
    public List<Map> categoryList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
        List<Map> catList = new ArrayList();
        List<TbItemCat> itemCatList = itemCatService.getItemCatList(parentId);
        for (TbItemCat tbItemCat : itemCatList) {
            Map node = new HashMap<>();
            node.put("id", tbItemCat.getId());
            node.put("text", tbItemCat.getName());
            node.put("state", tbItemCat.getIsParent() ? "closed" : "open");
            catList.add(node);
        }
        return catList;
    }

}
