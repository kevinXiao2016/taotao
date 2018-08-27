package com.xiaoyue.controller;

import com.xiaoyue.pojo.TbContent;
import com.xiaoyue.service.ContentService;
import com.xiaoyue.utils.EasyUIResult;
import com.xiaoyue.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/query/list")
    @ResponseBody
    public EasyUIResult getContentList(Long categoryId, Integer page, Integer rows) {
        EasyUIResult result = contentService.getContentList(categoryId, page, rows);
        return result;
    }

    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult addContent(TbContent content) {
        contentService.addContent(content);
        return TaotaoResult.ok();
    }

    @RequestMapping("/edit")
    @ResponseBody
    public TaotaoResult updateContent(TbContent content) {
        contentService.updateContent(content);
        return TaotaoResult.ok();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteContent(String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        List<Long> newList = new ArrayList<>(idList.size());
        for (String id : idList) {
            newList.add(Long.valueOf(id));
        }
        contentService.deleteContents(newList);
        return TaotaoResult.ok();
    }
}
