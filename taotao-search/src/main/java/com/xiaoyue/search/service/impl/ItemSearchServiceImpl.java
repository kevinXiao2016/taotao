package com.xiaoyue.search.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xiaoyue.search.dao.ItemSearchDao;
import com.xiaoyue.search.pojo.SearchResult;
import com.xiaoyue.search.service.ItemSearchService;

/**
 * 〈商品搜索业务类〉
 *
 * @author xiaoyue
 * @create 2018/8/29 13:49
 * @since 1.0.0
 */
@Service
public class ItemSearchServiceImpl implements ItemSearchService {

    @Value("${SEARCH_PAGE_SIZE}")
    private Integer PAGE_SIZE;

    @Autowired
    private ItemSearchDao itemSearchDao;

    @Override
    public SearchResult searchItem(String queryString, Integer page) throws Exception {

        SolrQuery solrQuery = new SolrQuery();
        if (StringUtils.isBlank(queryString)) {
            solrQuery.setQuery("*:*");
        } else {
            solrQuery.setQuery(queryString);
        }
        // 分页条件
        if (page == null || page < 1) {
            page = 1;
        }
        solrQuery.setStart((page - 1) * PAGE_SIZE);
        solrQuery.setRows(PAGE_SIZE);

        // 高亮显示
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
        solrQuery.setHighlightSimplePost("</em>");
        // 设置默认搜索域
        solrQuery.set("df", "item_keywords");

        // 执行查询
        SearchResult searchResult = itemSearchDao.searchItem(solrQuery);
        // 计算分页
        Long recordCount = searchResult.getRecordCount();
        int pageCount = (int) (recordCount / PAGE_SIZE);
        if (recordCount % PAGE_SIZE > 0) {
            pageCount++;
        }
        searchResult.setCurPage(page);
        searchResult.setPageCount(pageCount);

        return searchResult;

    }
}