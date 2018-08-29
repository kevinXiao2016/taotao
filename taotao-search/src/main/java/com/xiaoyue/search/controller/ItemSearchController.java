package com.xiaoyue.search.controller;

import com.xiaoyue.search.pojo.SearchResult;
import com.xiaoyue.search.service.ItemSearchService;
import com.xiaoyue.utils.ExceptionUtil;
import com.xiaoyue.utils.TaotaoResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 〈商品查询〉
 *
 * @author xiaoyue
 * @create 2018/8/29 14:05
 * @since 1.0.0
 */
@Controller
public class ItemSearchController {

    @Autowired
    private ItemSearchService itemSearchService;

    @RequestMapping("/q")
    @ResponseBody
    public TaotaoResult search(@RequestParam(value = "kw") String queryString,
            @RequestParam(defaultValue = "1") Integer page) {

        if (StringUtils.isBlank(queryString)) {
            return TaotaoResult.build(400, "查询条件是必须的参数");
        }
        SearchResult result = null;
        try {
            result = itemSearchService.searchItem(queryString, page);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }

        return TaotaoResult.ok(result);
    }

}