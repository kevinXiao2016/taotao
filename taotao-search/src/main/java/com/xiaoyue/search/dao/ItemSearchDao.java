package com.xiaoyue.search.dao;

import com.xiaoyue.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

import java.util.List;

/**
 * @Auther: xiaoyue
 * @Date: 2018/8/29 13:26
 * @Description:
 */
public interface ItemSearchDao {
	
	SearchResult searchItem(SolrQuery solrQuery) throws Exception;
}
