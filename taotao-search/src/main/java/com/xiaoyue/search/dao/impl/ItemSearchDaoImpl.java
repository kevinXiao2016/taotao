package com.xiaoyue.search.dao.impl;

import com.xiaoyue.search.pojo.Item;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Service;

import com.xiaoyue.search.dao.ItemSearchDao;
import com.xiaoyue.search.pojo.SearchResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 〈商品搜索〉
 *
 * @author xiaoyue
 * @create 2018/8/29 13:27
 * @since 1.0.0
 */
@Service
public class ItemSearchDaoImpl implements ItemSearchDao {

    @Override
    public SearchResult searchItem(SolrQuery solrQuery) throws Exception {
        HttpSolrClient.Builder builder = new HttpSolrClient.Builder("http://localhost:8983/solr/ikcore");
        HttpSolrClient solrClient = builder.build();

        // 根据条件查询索引库
        QueryResponse response = solrClient.query(solrQuery);
        // 取出商品列表
        SolrDocumentList documentList = response.getResults();
        List<Item> itemList = new ArrayList<>();
        for (SolrDocument document : documentList) {
            Item item = new Item();
            item.setId((String) document.get("id"));
            // 高亮

            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            List<String> list = highlighting.get(document.get("id")).get("item_title");
            String title = "";
            if (null != list && !list.isEmpty()) {
                title = list.get(0);
            } else {
                title = (String) document.get("item_title");
            }
            item.setTitle(title);
            item.setPrice((Long) document.get("item_price"));
            item.setSell_point((String) document.get("item_sell_point"));
            item.setImage((String) document.get("item_image"));
            item.setCategory_name((String) document.get("item_category_name"));
            
            itemList.add(item);
        }
        
        SearchResult searchResult = new SearchResult();
        searchResult.setItemList(itemList);
        searchResult.setRecordCount(documentList.getNumFound());

        return searchResult;
    }
}