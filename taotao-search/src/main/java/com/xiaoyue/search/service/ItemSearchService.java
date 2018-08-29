package com.xiaoyue.search.service;

import com.xiaoyue.search.pojo.SearchResult;

/**
 * @Auther: xiaoyue
 * @Date: 2018/8/29 13:48
 * @Description:
 */
public interface ItemSearchService {
	
	/**
	 * 查询
	 * @param queryString
	 * @param page
	 * @return
	 * @throws Exception
	 */
	SearchResult searchItem(String queryString, Integer page) throws Exception;
}
