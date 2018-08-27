package com.xiaoyue.rest.controller;

import com.xiaoyue.rest.domain.ItemCatResult;
import com.xiaoyue.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/itemcat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    /*@RequestMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String queryAllCat(String callback) {
        ItemCatResult itemCatResult = itemCatService.queryAllCategory();
        String json = JsonUtils.objectToJson(itemCatResult);

        String result = callback + "(" + json + ")";
        return result;
    }*/

    @RequestMapping(value = "/all")
    @ResponseBody
    public Object queryAllCat(String callback) {
        ItemCatResult itemCatResult = itemCatService.queryAllCategory();

        MappingJacksonValue jacksonValue = new MappingJacksonValue(itemCatResult);
        jacksonValue.setJsonpFunction(callback);

        return jacksonValue;
    }

}
