package com.xiaoyue.service;

import com.xiaoyue.pojo.EasyUIResult;
import com.xiaoyue.pojo.TbItem;
import com.xiaoyue.pojo.TbItemDesc;

public interface ItemService {

    TbItem getItemById(Long itemId);

    EasyUIResult getItemList(Integer page, Integer rows);

    void saveItem(TbItem tbItem, String description, String itemParams);

    void updateItem(TbItem tbItem, String description);

    void deleteItems(Long[] items);

    TbItemDesc getItemDesc(Long itemId);

}
