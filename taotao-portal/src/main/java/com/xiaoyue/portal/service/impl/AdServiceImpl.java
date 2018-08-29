package com.xiaoyue.portal.service.impl;

import com.xiaoyue.pojo.TbContent;
import com.xiaoyue.portal.domain.ADItem;
import com.xiaoyue.portal.service.AdService;
import com.xiaoyue.utils.HttpClientUtil;
import com.xiaoyue.utils.JsonUtils;
import com.xiaoyue.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${INDEX_AD1_URL}")
    private String INDEX_AD1_URL;

    @Override
    public String getAdItemList() {
        //调用服务层的服务查询打广告位的数据
        String result = HttpClientUtil.doGet(REST_BASE_URL + INDEX_AD1_URL);

        TaotaoResult taotaoResult = TaotaoResult.formatToList(result, TbContent.class);

        List<ADItem> itemList = new ArrayList<>();
        if (taotaoResult.getStatus() == 200) {
            List<TbContent> contents = (List<TbContent>)taotaoResult.getData();
            for (TbContent content : contents) {
                ADItem item = new ADItem();
                item.setHeight(240);
                item.setWidth(670);
                item.setSrc(content.getPic());
                item.setHeightB(240);
                item.setWidth(550);
                item.setSrcB(content.getPic2());
                item.setAlt(content.getTitleDesc());
                item.setHref(content.getUrl());
                itemList.add(item);
            }
        }
        return JsonUtils.objectToJson(itemList);
    }
}
