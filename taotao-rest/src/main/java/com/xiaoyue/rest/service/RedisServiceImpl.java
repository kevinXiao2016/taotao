package com.xiaoyue.rest.service;

import com.xiaoyue.rest.dao.JedisClient;
import com.xiaoyue.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    @Qualifier("jedisClientCluster")
    private JedisClient jedisClient;

    @Value("${TB_CONTENT_KEY}")
    private String TB_CONTENT_KEY;
    @Value("${TB_ITEMCAT_KEY}")
    private String TB_ITEMCAT_KEY;

    @Override
    public TaotaoResult clearContentCache(Long cid) {
        try {
            jedisClient.hdel(TB_CONTENT_KEY, cid + "");
            return TaotaoResult.ok();
        } catch (Exception e) {
            return TaotaoResult.build(500, "缓存清除失败");
        }
    }

    @Override
    public TaotaoResult clearItemcatCache(Long parentId) {
        try {
            jedisClient.hdel(TB_ITEMCAT_KEY, parentId + "");
            return TaotaoResult.ok();
        } catch (Exception e) {
            return TaotaoResult.build(500, "缓存清除失败");
        }
    }
}
