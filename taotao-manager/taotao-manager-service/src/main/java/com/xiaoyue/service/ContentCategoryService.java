package com.xiaoyue.service;

import com.xiaoyue.pojo.EasyUITreeNode;
import com.xiaoyue.pojo.TbContentCategory;

import java.util.List;

public interface ContentCategoryService {

    List<TbContentCategory> queryAllContentCategory();

    List<EasyUITreeNode> getContentCategoryList(Long parentId);

    void addNode(TbContentCategory contentCategory);

    void updateNode(TbContentCategory contentCategory);

    void deleteNode(Long id);
}
