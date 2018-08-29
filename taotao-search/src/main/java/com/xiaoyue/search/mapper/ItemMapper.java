package com.xiaoyue.search.mapper;

import com.xiaoyue.search.pojo.Item;

import java.util.List;

/**
 * 〈商品查询〉
 *
 * @author xiaoyue
 * @create 2018/8/29 9:58
 * @since 1.0.0
 */
public interface ItemMapper {
	
	List<Item> getItemList();
	
}