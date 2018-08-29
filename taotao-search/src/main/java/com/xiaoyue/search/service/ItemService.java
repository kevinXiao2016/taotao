package com.xiaoyue.search.service;

import com.xiaoyue.utils.TaotaoResult;

/**
 * @Auther: xiaoyue
 * @Date: 2018/8/29 10:01
 * @Description: 商品业务接口
 */
public interface ItemService {
	
	TaotaoResult importItemToIndex() throws Exception;

}
