package com.xiaoyue.service;

import com.xiaoyue.pojo.EasyUIResult;
import com.xiaoyue.pojo.TbItemCat;
import com.xiaoyue.pojo.TbItemParam;
import com.xiaoyue.utils.TaotaoResult;

public interface ItemParamService {
    /**
     * 获取规格参数列表
     *
     * @param page
     * @param rows
     * @return
     */
    EasyUIResult getItemParamList(Integer page, Integer rows);

    /**
     * 根据分类id获取分类模板
     *
     * @param id
     * @return
     */
    TaotaoResult getItemParamById(Long id);

    /**
     * 保存
     *
     * @param tbItemParam
     */
    void saveItemParam(TbItemParam tbItemParam);
}
