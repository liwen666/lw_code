package com.temp.code.cache.bean;

/**
 * redis key prefix
 *
 * @author Administrator
 * @date 2018/12/10 15:06
 */
public class RedisKeyPrefixConstant {


    // redis key 前缀的前缀  [start,total:5]
    public static final String REDIS_STRING = "str:";
    public static final String REDIS_HASH = "h:";
    public static final String REDIS_LIST = "l:";
    public static final String REDIS_SET = "s:";
    public static final String REDIS_ZSET = "zs:";
    // redis key 前缀的前缀  [end,total:5]

    public static final String REDIS_SPLIT = ":";

    /**
     * 每个用户所对组set集合
     * key=prefix+userId
     */
    public static final String MEMBERSHIP_USER = "membership:user:";

    /**
     * 每个组所包含用户set集合
     * key=prefix+groupId
     */
    public static final String MEMBERSHIP_GROUP = "membership:group:";

    /**
     * 流程实例信息hash对象
     * key=prefix+ticketId+
     */
    public static final String PROCESS_INSTANCE = "pi:";

    public static String getRedisStringKey(String prefix, String id) {
        return RedisKeyPrefixConstant.REDIS_STRING + prefix + id;
    }

    public static String getRedisListKey(String prefix, String id) {
        return RedisKeyPrefixConstant.REDIS_LIST + prefix + id;
    }

    public static String getRedisSetKey(String prefix, String id) {
        return RedisKeyPrefixConstant.REDIS_SET + prefix + id;
    }

}
