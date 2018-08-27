package com.xiaoyue.service;

import com.xiaoyue.mapper.TbContentCategoryMapper;
import com.xiaoyue.pojo.EasyUITreeNode;
import com.xiaoyue.pojo.TbContentCategory;
import com.xiaoyue.pojo.TbContentCategoryExample;
import com.xiaoyue.pojo.TbContentCategoryExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    @Override
    public List<TbContentCategory> queryAllContentCategory() {
        TbContentCategoryExample example = new TbContentCategoryExample();
        List<TbContentCategory> contentCategories = contentCategoryMapper.selectByExample(example);
        return contentCategories;
    }

    @Override
    public List<EasyUITreeNode> getContentCategoryList(Long parentId) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> contentCategories = contentCategoryMapper.selectByExample(example);

        List<EasyUITreeNode> nodeList = new ArrayList<>();

        for (TbContentCategory category : contentCategories) {
            EasyUITreeNode treeNode = new EasyUITreeNode();

            treeNode.setId(category.getId());
            treeNode.setText(category.getName());
            if (category.getIsParent()) {
                treeNode.setState("closed");
            } else {
                treeNode.setState("open");
            }
            nodeList.add(treeNode);
        }
        return nodeList;
    }

    @Override
    public void addNode(TbContentCategory contentCategory) {
        contentCategoryMapper.insert(contentCategory);

        TbContentCategory parentNode = contentCategoryMapper.selectByPrimaryKey(contentCategory.getParentId());
        if (!parentNode.getIsParent()) {
            parentNode.setIsParent(true);
            contentCategoryMapper.updateByPrimaryKey(parentNode);
        }

    }

    @Override
    public void updateNode(TbContentCategory contentCategory) {
        contentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
    }

    @Override
    public void deleteNode(Long id) {
        contentCategoryMapper.deleteByPrimaryKey(id);
    }


}
