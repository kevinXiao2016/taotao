package com.xiaoyue.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyue.search.mapper.ItemMapper;
import com.xiaoyue.search.pojo.Item;
import com.xiaoyue.search.service.ItemService;
import com.xiaoyue.utils.TaotaoResult;

/**
 * 〈商品业务类〉
 *
 * @author xiaoyue
 * @create 2018/8/29 10:02
 * @since 1.0.0
 */
@Service
public class ItemServiceImpl implements ItemService {
    
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public TaotaoResult importItemToIndex() throws Exception {
        // 查询商品列表
        List<Item> itemList = itemMapper.getItemList();
        // 将商品写入solr
        HttpSolrClient.Builder builder = new HttpSolrClient.Builder("http://localhost:8983/solr/ikcore");
        HttpSolrClient solrClient = builder.build();
        
    
        for (Item item : itemList) {
            SolrInputDocument document = new SolrInputDocument();

            document.addField("id", item.getId());
            document.addField("item_title", item.getTitle());
            document.addField("item_sell_point", item.getSell_point());
            document.addField("item_price", item.getPrice());
            document.addField("item_image", item.getImage());
            document.addField("item_category_name", item.getCategory_name());
    
            solrClient.add(document);
        }

        // 提交
        solrClient.commit();
        return TaotaoResult.ok();
    }
}