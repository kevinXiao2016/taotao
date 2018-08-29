package com.xiaoyue.portal.service;

import com.xiaoyue.pojo.TbItemDesc;
import com.xiaoyue.portal.pojo.ItemInfo;

/**
 * 〈商品详情〉
 *
 * @author xiaoyue
 * @create 2018/8/29 15:56
 * @since 1.0.0
 */
public interface ItemService {

    ItemInfo getItemById(Long id) throws Exception;

    TbItemDesc geTbItemDescById(Long id) throws Exception;

    String geTbItemParamItemById(Long id) throws Exception;
}