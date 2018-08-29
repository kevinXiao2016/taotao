package com.xiaoyue.rest.service.impl;

import com.xiaoyue.mapper.TbItemDescMapper;
import com.xiaoyue.mapper.TbItemMapper;
import com.xiaoyue.mapper.TbItemParamItemMapper;
import com.xiaoyue.pojo.TbItem;
import com.xiaoyue.pojo.TbItemDesc;
import com.xiaoyue.pojo.TbItemParamItem;
import com.xiaoyue.pojo.TbItemParamItemExample;
import com.xiaoyue.rest.service.ItemService;
import com.xiaoyue.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.util.List;

/**
 * 〈商品信息业务类〉
 *
 * @author xiaoyue
 * @create 2018/8/29 16:02
 * @since 1.0.0
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Value("${TB_ITEM_KEY}")
    private String TB_ITEM_KEY;
    @Value("${TB_ITEM_KEY_BASE}")
    private String TB_ITEM_KEY_BASE;
    @Value("${TB_ITEM_KEY_DESC}")
    private String TB_ITEM_KEY_DESC;
    @Value("${TB_ITEM_KEY_PARAM}")
    private String TB_ITEM_KEY_PARAM;
    @Value("${TB_ITEM_EXPIRE}")
    private Integer TB_ITEM_EXPIRE;

    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemDescMapper itemDescMapper;
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;
    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public TbItem getItemById(Long id) throws Exception {
        // 从缓存取信息
        try {
            String itemString = jedisCluster.get(TB_ITEM_KEY + id + TB_ITEM_KEY_BASE);
            if (!StringUtils.isBlank(itemString)) {
                return JsonUtils.jsonToPojo(itemString, TbItem.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 缓存取不到，从数据库取
        TbItem tbItem = itemMapper.selectByPrimaryKey(id);

        // 存入缓存
        try {
            jedisCluster.set(TB_ITEM_KEY + id + TB_ITEM_KEY_BASE, JsonUtils.objectToJson(tbItem));
            jedisCluster.expire(TB_ITEM_KEY + id + TB_ITEM_KEY_BASE, TB_ITEM_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbItem;
    }

    @Override
    public TbItemDesc getItemDescById(Long id) throws Exception {
        // 从缓存取信息
        try {
            String itemString = jedisCluster.get(TB_ITEM_KEY + id + TB_ITEM_KEY_DESC);
            if (!StringUtils.isBlank(itemString)) {
                return JsonUtils.jsonToPojo(itemString, TbItemDesc.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(id);

        // 存入缓存
        try {
            jedisCluster.set(TB_ITEM_KEY + id + TB_ITEM_KEY_DESC, JsonUtils.objectToJson(itemDesc));
            jedisCluster.expire(TB_ITEM_KEY + id + TB_ITEM_KEY_DESC, TB_ITEM_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemDesc;
    }

    @Override
    public TbItemParamItem getItemParamById(Long id) throws Exception {
        // 从缓存取信息
        try {
            String itemString = jedisCluster.get(TB_ITEM_KEY + id + TB_ITEM_KEY_PARAM);
            if (!StringUtils.isBlank(itemString)) {
                return JsonUtils.jsonToPojo(itemString, TbItemParamItem.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(id);
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        TbItemParamItem itemParamItem = null;
        if (null != list && !list.isEmpty()) {
            itemParamItem = list.get(0);
        }

        // 存入缓存
        try {
            if (null != itemParamItem) {
                jedisCluster.set(TB_ITEM_KEY + id + TB_ITEM_KEY_PARAM, JsonUtils.objectToJson(itemParamItem));
                jedisCluster.expire(TB_ITEM_KEY + id + TB_ITEM_KEY_PARAM, TB_ITEM_EXPIRE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemParamItem;
    }

}