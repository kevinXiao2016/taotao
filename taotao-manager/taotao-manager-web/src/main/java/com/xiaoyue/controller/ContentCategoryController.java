package com.xiaoyue.controller;

import com.xiaoyue.pojo.EasyUITreeNode;
import com.xiaoyue.pojo.TbContentCategory;
import com.xiaoyue.service.ContentCategoryService;
import com.xiaoyue.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EasyUITreeNode> queryAllCatgory(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
        List<EasyUITreeNode> treeNodes = contentCategoryService.getContentCategoryList(parentId);
        return treeNodes;
    }

    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult addNode(@RequestParam Long parentId, @RequestParam String name) {
        TbContentCategory node = new TbContentCategory();
        node.setParentId(parentId);
        node.setName(name);
        //1 正常   2：删除
        node.setStatus(1);
        node.setSortOrder(1);
        node.setIsParent(false);
        node.setCreated(new Date());
        node.setUpdated(new Date());

        contentCategoryService.addNode(node);

        return TaotaoResult.ok(node);
    }

    @RequestMapping("/update")
    @ResponseBody
    public TaotaoResult editNode(Long id, String name) {
        TbContentCategory node = new TbContentCategory();
        node.setId(id);
        node.setName(name);
        node.setUpdated(new Date());
        contentCategoryService.updateNode(node);
        return TaotaoResult.ok();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteNode(Long id) {
        contentCategoryService.deleteNode(id);
        return TaotaoResult.ok();
    }

}
