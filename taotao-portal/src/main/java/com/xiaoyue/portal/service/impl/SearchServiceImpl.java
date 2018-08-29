package com.xiaoyue.portal.service.impl;

import com.xiaoyue.portal.pojo.SearchResult;
import com.xiaoyue.portal.service.SearchService;
import com.xiaoyue.utils.HttpClientUtil;
import com.xiaoyue.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈商品搜索〉
 *
 * @author xiaoyue
 * @create 2018/8/29 14:57
 * @since 1.0.0
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Value("${SEARCH_BASE_URL}")
    private String SEARCH_BASE_URL;

    @Override
    public SearchResult searchItemList(String queryString, Integer page) throws Exception {
        Map<String, String> param = new HashMap<>();
        param.put("kw", queryString);
        param.put("page", page == null ? "1" : page.toString());

        String resultString = HttpClientUtil.doGet(SEARCH_BASE_URL, param);
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(resultString, SearchResult.class);

        SearchResult searchResult = null;
        // 查询成功
        if (taotaoResult.getStatus() == 200) {
            // 取查询结果
            searchResult = (SearchResult) taotaoResult.getData();
        }
        return searchResult;
    }
}