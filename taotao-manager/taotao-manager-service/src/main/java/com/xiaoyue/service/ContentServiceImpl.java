package com.xiaoyue.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyue.mapper.TbContentMapper;
import com.xiaoyue.pojo.TbContent;
import com.xiaoyue.pojo.TbContentExample;
import com.xiaoyue.pojo.TbContentExample.Criteria;
import com.xiaoyue.utils.EasyUIResult;
import com.xiaoyue.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_CONTENT_SYNC_URL}")
    private String REST_CONTENT_SYNC_URL;


    @Override
    public EasyUIResult getContentList(Long categoryId, Integer page, Integer rows) {

        TbContentExample example = new TbContentExample();
        Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        PageHelper.startPage(page, rows);
        List<TbContent> tbContents = contentMapper.selectByExampleWithBLOBs(example);

        PageInfo pageInfo = new PageInfo(tbContents);
        long total = pageInfo.getTotal();

        EasyUIResult easyUIResult = new EasyUIResult(total, tbContents);

        return easyUIResult;
    }

    @Override
    public void addContent(TbContent content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.insert(content);

        //刷新缓存
        try {
            HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateContent(TbContent content) {
        content.setUpdated(new Date());
        contentMapper.updateByPrimaryKeySelective(content);

        //刷新缓存
        try {
            HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteContents(List<Long> ids) {

        TbContentExample example = new TbContentExample();
        Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        List<TbContent> contents = contentMapper.selectByExample(example);
        contentMapper.deleteByExample(example);

        //刷新缓存,做个接收参数List的方法即可
        try {
            for (TbContent content : contents) {
                HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
