package com.xiaoyue.rest.dao;

public interface JedisClient {

    String get(String key);

    String set(String key, String value);

    Long del(String key);

    String hget(String hkey, String key);

    Long hset(String hkey, String key, String value);

    Long hdel(String hkey, String key);

    Long incr(String key);

    Long expire(String key, int second);

    Long ttl(String key);

}
