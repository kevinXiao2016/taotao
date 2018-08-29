package com.xiaoyue.rest.service;

import com.xiaoyue.pojo.TbItem;
import com.xiaoyue.pojo.TbItemDesc;
import com.xiaoyue.pojo.TbItemParamItem;

/**
 * @Auther: xiaoyue
 * @Date: 2018/8/29 16:02
 * @Description:
 */
public interface ItemService {
    /**
     * 根据id取商品信息
     * 
     * @param id
     * @return
     * @throws Exception
     */
    TbItem getItemById(Long id) throws Exception;

    /**
     * 根据id取商品描述
     * 
     * @param id
     * @return
     * @throws Exception
     */
    TbItemDesc getItemDescById(Long id) throws Exception;

    /**
     * 根据商品id取规格参数
     * 
     * @param id
     * @return
     * @throws Exception
     */
    TbItemParamItem getItemParamById(Long id) throws Exception;
}
