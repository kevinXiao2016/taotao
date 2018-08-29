package com.xiaoyue.portal.pojo;

import com.xiaoyue.pojo.TbItem;
import org.apache.commons.lang3.StringUtils;

/**
 * 〈商品基本信息展示类〉
 *
 * @author xiaoyue
 * @create 2018/8/29 17:58
 * @since 1.0.0
 */
public class ItemInfo extends TbItem {

    public String[] getImages() {
        String image = getImage();
        if (!StringUtils.isBlank(image)) {
            return image.split(",");
        } else {
            return null;
        }
    }

}