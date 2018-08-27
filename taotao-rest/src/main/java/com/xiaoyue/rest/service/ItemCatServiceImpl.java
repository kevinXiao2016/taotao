package com.xiaoyue.rest.service;

import com.xiaoyue.mapper.TbItemCatMapper;
import com.xiaoyue.pojo.TbItemCat;
import com.xiaoyue.pojo.TbItemCatExample;
import com.xiaoyue.pojo.TbItemCatExample.Criteria;
import com.xiaoyue.rest.dao.JedisClient;
import com.xiaoyue.rest.domain.ItemCatNode;
import com.xiaoyue.rest.domain.ItemCatResult;
import com.xiaoyue.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    @Qualifier("jedisClientCluster")
    private JedisClient jedisClient;
    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Value("${TB_ITEMCAT_KEY}")
    private String TB_ITEMCAT_KEY;

    @Override
    public ItemCatResult queryAllCategory() {

        ItemCatResult itemCatResult = new ItemCatResult();
        itemCatResult.setData(getCatList(0));

        return itemCatResult;
    }

    private List<?> getCatList(long parentId) {

        //1、从redis缓存中查询
        try {
            String itemCatStr = jedisClient.hget(TB_ITEMCAT_KEY, parentId + "");
            if (!StringUtils.isBlank(itemCatStr)) {
                //把json字符串转换成对象列表
                return JsonUtils.jsonToList(itemCatStr, ItemCatNode.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //2、从数据库查询
        TbItemCatExample example = new TbItemCatExample();
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> tbItemCats = itemCatMapper.selectByExample(example);

        List resultList = new ArrayList<>();
        int count = 0;
        for (TbItemCat itemCat : tbItemCats) {

            if (itemCat.getIsParent()) {
                ItemCatNode catNode = new ItemCatNode();
                if (parentId == 0) {
                    catNode.setName("<a href='/products/" + itemCat.getId() + ".html'>" + itemCat.getName() + "</a>");
                } else {
                    catNode.setName(itemCat.getName());
                }
                catNode.setUrl("/products/" + itemCat.getId() + ".html");
                catNode.setItem(getCatList(itemCat.getId()));

                resultList.add(catNode);

                count++;
                if (parentId == 0 && count >= 14) {
                    break;
                }

            } else {
                String catItem = "/products/" + itemCat.getId() + ".html|" + itemCat.getName();
                resultList.add(catItem);
            }
        }

        //3、写入redis缓存
        try {
            jedisClient.hset(TB_ITEMCAT_KEY, parentId + "", JsonUtils.objectToJson(resultList));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;
    }
}
