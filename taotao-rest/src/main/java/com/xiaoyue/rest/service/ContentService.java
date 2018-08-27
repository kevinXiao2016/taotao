package com.xiaoyue.rest.service;

import com.xiaoyue.pojo.TbContent;

import java.util.List;

public interface ContentService {

    List<TbContent> getContentList(Long contendCid);
}
