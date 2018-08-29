package com.xiaoyue.portal.service;

/**
 * @Auther: xiaoyue
 * @Date: 2018/8/29 14:54
 * @Description:
 */
public interface SearchService {

    com.xiaoyue.portal.pojo.SearchResult searchItemList(String queryString, Integer page) throws Exception;
}
