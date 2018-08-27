package com.xiaoyue.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyue.mapper.TbItemDescMapper;
import com.xiaoyue.mapper.TbItemMapper;
import com.xiaoyue.mapper.TbItemParamItemMapper;
import com.xiaoyue.mapper.TbItemParamMapper;
import com.xiaoyue.pojo.*;
import com.xiaoyue.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Override
    public TbItem getItemById(Long itemId) {
        return tbItemMapper.selectByPrimaryKey(itemId);
    }

    @Override
    public EasyUIResult getItemList(Integer page, Integer rows) {
        TbItemExample itemExample = new TbItemExample();
        itemExample.setOrderByClause("created");
        PageHelper.startPage(page, rows);

        List<TbItem> items = tbItemMapper.selectByExample(itemExample);

        PageInfo pageInfo = new PageInfo(items);
        long total = pageInfo.getTotal();

        EasyUIResult easyUIResult = new EasyUIResult(total, items);

        return easyUIResult;
    }

    @Override
    public void saveItem(TbItem tbItem, String description, String itemParams) {
        long itemId = IDUtils.genItemId();
        tbItem.setId(itemId);
        tbItem.setStatus(TbItem.NORMAL);
        Date createDate = new Date();
        tbItem.setCreated(createDate);
        tbItem.setUpdated(createDate);
        tbItemMapper.insert(tbItem);

        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setItemDesc(description);
        tbItemDesc.setCreated(createDate);
        tbItemDesc.setUpdated(createDate);
        tbItemDescMapper.insert(tbItemDesc);

        //添加商品规格
        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setItemId(itemId);
        tbItemParamItem.setParamData(itemParams);
        tbItemParamItem.setCreated(createDate);
        tbItemParamItem.setUpdated(createDate);
        itemParamItemMapper.insert(tbItemParamItem);
    }

    @Override
    public void updateItem(TbItem tbItem, String description) {
        tbItem.setUpdated(new Date());
        tbItemMapper.updateByPrimaryKeySelective(tbItem);

        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setUpdated(new Date());
        tbItemDesc.setItemId(tbItem.getId());
        tbItemDesc.setItemDesc(description);
        tbItemDescMapper.updateByPrimaryKeySelective(tbItemDesc);

    }

    @Override
    public void deleteItems(Long[] items) {
        for (Long itemId : items) {
            tbItemMapper.deleteByPrimaryKey(itemId);
        }
    }


    @Override
    public TbItemDesc getItemDesc(Long itemId) {
        TbItemDesc tbItemDesc = tbItemDescMapper.selectByPrimaryKey(itemId);
        return tbItemDesc;
    }


}
