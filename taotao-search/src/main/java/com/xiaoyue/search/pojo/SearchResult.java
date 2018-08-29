package com.xiaoyue.search.pojo;

import java.util.List;

/**
 * 〈搜索结果封装对象〉
 *
 * @author xiaoyue
 * @create 2018/8/29 13:24
 * @since 1.0.0
 */
public class SearchResult {
    private Long recordCount;
    private List<Item> itemList;
    private Integer pageCount;
    private Integer curPage;

    public Long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Long recordCount) {
        this.recordCount = recordCount;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SearchResult{");
        sb.append("recordCount=").append(recordCount);
        sb.append(", itemList=").append(itemList);
        sb.append(", pageCount=").append(pageCount);
        sb.append(", curPage=").append(curPage);
        sb.append('}');
        return sb.toString();
    }
}