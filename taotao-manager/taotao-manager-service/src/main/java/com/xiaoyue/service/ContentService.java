package com.xiaoyue.service;

import com.xiaoyue.pojo.TbContent;
import com.xiaoyue.utils.EasyUIResult;

import java.util.List;

public interface ContentService {

    EasyUIResult getContentList(Long categoryId, Integer page, Integer rows);

    void addContent(TbContent content);

    void updateContent(TbContent content);

    void deleteContents(List<Long> ids);
}
