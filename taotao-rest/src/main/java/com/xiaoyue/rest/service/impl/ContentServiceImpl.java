package com.xiaoyue.rest.service.impl;

import com.xiaoyue.mapper.TbContentMapper;
import com.xiaoyue.pojo.TbContent;
import com.xiaoyue.pojo.TbContentExample;
import com.xiaoyue.pojo.TbContentExample.Criteria;
import com.xiaoyue.rest.dao.JedisClient;
import com.xiaoyue.rest.service.ContentService;
import com.xiaoyue.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    @Qualifier("jedisClientCluster")
    private JedisClient jedisClient;
    @Autowired
    private TbContentMapper contentMapper;

    @Value("${TB_CONTENT_KEY}")
    private String TB_CONTENT_KEY;


    @Override
    public List<TbContent> getContentList(Long contendCid) {
        //1、从redis缓存中查询
        try {
            String contentStr = jedisClient.hget(TB_CONTENT_KEY, contendCid + "");
            if (!StringUtils.isBlank(contentStr)) {
                //把json字符串转换成对象列表
                return JsonUtils.jsonToList(contentStr, TbContent.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //缓存不能影响正常逻辑
        }

        //2、从数据库中查询
        TbContentExample example = new TbContentExample();
        Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(contendCid);
        List<TbContent> tbContents = contentMapper.selectByExampleWithBLOBs(example);

        //3、存入redis缓存
        try {
            String json = JsonUtils.objectToJson(tbContents);
            jedisClient.hset(TB_CONTENT_KEY, contendCid + "", json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tbContents;
    }
}
