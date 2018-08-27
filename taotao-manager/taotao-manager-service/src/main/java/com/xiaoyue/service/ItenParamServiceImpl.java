package com.xiaoyue.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyue.mapper.TbItemParamMapper;
import com.xiaoyue.pojo.EasyUIResult;
import com.xiaoyue.pojo.TbItemCat;
import com.xiaoyue.pojo.TbItemParam;
import com.xiaoyue.pojo.TbItemParamExample;
import com.xiaoyue.pojo.TbItemParamExample.Criteria;
import com.xiaoyue.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItenParamServiceImpl implements ItemParamService {

    @Autowired
    private TbItemParamMapper itemParamMapper;

    @Override
    public EasyUIResult getItemParamList(Integer page, Integer rows) {
        TbItemParamExample tbItemParamExample = new TbItemParamExample();
        tbItemParamExample.setOrderByClause("id");
        PageHelper.startPage(page, rows);
        List<TbItemParam> tbItemParams = itemParamMapper.selectByExampleWithBLOBs(tbItemParamExample);

        PageInfo pageInfo = new PageInfo(tbItemParams);
        long total = pageInfo.getTotal();

        EasyUIResult easyUIResult = new EasyUIResult(total, tbItemParams);
        return easyUIResult;
    }

    @Override
    public TaotaoResult getItemParamById(Long id) {
        TbItemParamExample tbItemParamExample = new TbItemParamExample();
        Criteria criteria = tbItemParamExample.createCriteria();
        criteria.andItemCatIdEqualTo(id);

        List<TbItemParam> tbItemParams = itemParamMapper.selectByExampleWithBLOBs(tbItemParamExample);

        if (tbItemParams != null && tbItemParams.size() > 0) {
            return TaotaoResult.ok(tbItemParams.get(0));
        }
        return TaotaoResult.ok();
    }

    @Override
    public void saveItemParam(TbItemParam tbItemParam) {
        itemParamMapper.insert(tbItemParam);
    }
}
