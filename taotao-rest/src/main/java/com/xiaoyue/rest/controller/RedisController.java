package com.xiaoyue.rest.controller;

import com.xiaoyue.rest.service.RedisService;
import com.xiaoyue.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cache/sync")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @RequestMapping("/content/{cid}")
    @ResponseBody
    public TaotaoResult clearContentCache(@PathVariable Long cid) {
        return redisService.clearContentCache(cid);
    }

    @RequestMapping("/itemcat/{parentId}")
    @ResponseBody
    public TaotaoResult clearItemcatCache(@PathVariable Long parentId) {
        return redisService.clearItemcatCache(parentId);
    }

}
