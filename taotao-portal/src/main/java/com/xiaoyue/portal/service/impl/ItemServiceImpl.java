package com.xiaoyue.portal.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xiaoyue.pojo.TbItemDesc;
import com.xiaoyue.pojo.TbItemParamItem;
import com.xiaoyue.portal.pojo.ItemInfo;
import com.xiaoyue.portal.service.ItemService;
import com.xiaoyue.utils.HttpClientUtil;
import com.xiaoyue.utils.JsonUtils;
import com.xiaoyue.utils.TaotaoResult;

/**
 * 〈商品查询〉
 *
 * @author xiaoyue
 * @create 2018/8/29 16:08
 * @since 1.0.0
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${ITEMS_ITEM_URL}")
    private String ITEMS_ITEM_URL;
    @Value("${ITEMS_ITEMDESC_URL}")
    private String ITEMS_ITEMDESC_URL;
    @Value("${ITEMS_ITEMPARAM_URL}")
    private String ITEMS_ITEMPARAM_URL;

    @Override
    public ItemInfo getItemById(Long id) throws Exception {
        // 查询商品信息
        String result = HttpClientUtil.doGet(REST_BASE_URL + ITEMS_ITEM_URL + id);
        // 转换成java对象
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(result, ItemInfo.class);
        ItemInfo item = null;
        if (taotaoResult.getStatus() == 200) {
            item = (ItemInfo) taotaoResult.getData();
        }

        return item;
    }

    @Override
    public TbItemDesc geTbItemDescById(Long id) throws Exception {
        // 查询商品信息
        String result = HttpClientUtil.doGet(REST_BASE_URL + ITEMS_ITEMDESC_URL + id);
        // 转换成java对象
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(result, TbItemDesc.class);
        TbItemDesc itemDesc = null;
        if (taotaoResult.getStatus() == 200) {
            itemDesc = (TbItemDesc) taotaoResult.getData();
        }

        return itemDesc;
    }

    @Override
    public String geTbItemParamItemById(Long id) throws Exception {
        // 查询商品信息
        String result = HttpClientUtil.doGet(REST_BASE_URL + ITEMS_ITEMPARAM_URL + id);
        // 转换成java对象
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(result, TbItemParamItem.class);
        String resultHtml = "";
        if (taotaoResult.getStatus() == 200) {
            try {
                TbItemParamItem itemParamItem = (TbItemParamItem) taotaoResult.getData();
                // 取规格参数信息
                String paramData = itemParamItem.getParamData();
                // 把规格参数转换成java对象
                List<Map> paramList = JsonUtils.jsonToList(paramData, Map.class);
                // 拼装html
                resultHtml = "<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n"
                        + "    <tbody>\n";
                for (Map map : paramList) {
                    resultHtml += "        <tr>\n" + "            <th class=\"tdTitle\" colspan=\"2\">"
                            + map.get("group") + "</th>\n" + "        </tr>\n";
                    List<Map> params = (List<Map>) map.get("params");
                    for (Map map2 : params) {

                        resultHtml += "        <tr>\n" + "            <td class=\"tdTitle\">" + map2.get("k")
                                + "</td>\n" + "            <td>" + map2.get("v") + "</td>\n" + "        </tr>\n";
                    }

                }
                resultHtml += "    </tbody>\n" + "</table>";
            } catch (Exception e) {
                // 如果转换发送异常，忽略。返回一个空字符串。
                e.printStackTrace();
            }
        }

        return resultHtml;
    }

}