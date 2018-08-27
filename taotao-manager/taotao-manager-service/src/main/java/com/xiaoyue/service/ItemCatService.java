package com.xiaoyue.service;

import com.xiaoyue.pojo.TbItemCat;

import java.util.List;

public interface ItemCatService {

    List<TbItemCat> getItemCatList(Long parentId);

    TbItemCat getItemCatById(Long id);
}
