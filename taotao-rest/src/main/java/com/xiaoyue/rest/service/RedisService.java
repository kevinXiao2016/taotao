package com.xiaoyue.rest.service;

import com.xiaoyue.utils.TaotaoResult;

public interface RedisService {

    TaotaoResult clearContentCache(Long cid);

    TaotaoResult clearItemcatCache(Long parentId);

}
