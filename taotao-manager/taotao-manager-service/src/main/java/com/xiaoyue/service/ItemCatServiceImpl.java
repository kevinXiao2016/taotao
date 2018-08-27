package com.xiaoyue.service;

import com.xiaoyue.mapper.TbItemCatMapper;
import com.xiaoyue.pojo.TbItemCat;
import com.xiaoyue.pojo.TbItemCatExample;
import com.xiaoyue.pojo.TbItemCatExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_ITEMCAT_SYNC_URL}")
    private String REST_ITEMCAT_SYNC_URL;

    @Override
    public List<TbItemCat> getItemCatList(Long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> tbItemCats = tbItemCatMapper.selectByExample(example);
        return tbItemCats;
    }

    @Override
    public TbItemCat getItemCatById(Long id) {
        TbItemCat tbItemCat = tbItemCatMapper.selectByPrimaryKey(id);
        return tbItemCat;
    }
}
