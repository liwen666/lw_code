//package com.temp.code.cache.service.impl;
//
//import com.hq.bpmn.cache.bean.ProcessInstanceCache;
//import com.hq.bpmn.cache.bean.RedisKeyPrefixConstant;
//import com.hq.bpmn.cache.service.CacheService;
//import com.hq.bpmn.common.util.BpmnJedisClusterUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import redis.clients.jedis.JedisCluster;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//@Service
//public class CacheServiceJedisClusterImpl implements CacheService {
//    private int batchSendCount = 50000;
//
//    @Autowired
//    private JedisCluster jedisCluster;
//
//    @Override
//    public long cacheMembershipUser(String userIdKey, String groupIdValue) {
//        Long sadd = jedisCluster.sadd(userIdConversionToKey(userIdKey), groupIdValue);
//        return sadd;
//    }
//
//    @Override
//    public long cacheMembershipUser(String userIdKey, List<String> groupIdListValue) {
//        long sendResult = 0L;
//        List<List<String>> lists = BpmnJedisClusterUtil.byCountAssign(groupIdListValue, batchSendCount);
//        for (List<String> l : lists) {
//            String[] groupIds = (String[]) BpmnJedisClusterUtil.listToArray(l);
//            Long sadd = jedisCluster.sadd(userIdConversionToKey(userIdKey), groupIds);
//            sendResult += sadd;
//        }
//        return sendResult;
//    }
//
//    @Override
//    public long cacheMembershipGroup(String groupIdKey, String userIdValue) {
//        Long sadd = jedisCluster.sadd(groupIdConversionToKey(groupIdKey), userIdValue);
//        return sadd;
//    }
//
//    @Override
//    public long cacheMembershipGroup(String groupIdKey, List<String> userIdListValue) {
//        long sendResult = 0L;
//        List<List<String>> lists = BpmnJedisClusterUtil.byCountAssign(userIdListValue, batchSendCount);
//        for (List<String> l : lists) {
//            String[] userIds = (String[]) BpmnJedisClusterUtil.listToArray(l);
//            Long sadd = jedisCluster.sadd(groupIdConversionToKey(groupIdKey), userIds);
//            sendResult += sadd;
//        }
//        return sendResult;
//    }
//
//    @Override
//    public long cacheProcessInstance(String ticketIdKey, ProcessInstanceCache processInstanceCacheValue) {
//
//        String isOK = null;
//        try {
//            isOK = jedisCluster.hmset(ticketIdConversionToKey(ticketIdKey), BpmnJedisClusterUtil.objectToMap(processInstanceCacheValue));
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return isOK.equals("OK") ? 1 : 0;
//    }
//
//    @Override
//    public long cacheProcessInstance(Map<String, ProcessInstanceCache> processInstanceCacheMap) {
//        long count = 0L;
//        Set<Map.Entry<String, ProcessInstanceCache>> entries = processInstanceCacheMap.entrySet();
//        for (Map.Entry<String, ProcessInstanceCache> m : entries) {
//            String isOK = null;
//            try {
//                isOK = jedisCluster.hmset(ticketIdConversionToKey(m.getKey()), BpmnJedisClusterUtil.objectToMap(m.getValue()));
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//            if ("OK".equals(isOK)) {
//                count++;
//            }
//        }
//        return count;
//    }
//
//    @Override
//    public List<String> listGroupIdList(String userIdKey) {
//        Set<String> sGroupMembers = jedisCluster.smembers(userIdConversionToKey(userIdKey));
//        return new ArrayList<String>(sGroupMembers);
//    }
//
//    @Override
//    public List<String> listUserIdList(String groupIdKey) {
//        Set<String> sUserMembers = jedisCluster.smembers(groupIdConversionToKey(groupIdKey));
//        return new ArrayList<String>(sUserMembers);
//    }
//
//    @Override
//    public ProcessInstanceCache getProcessInstanceCache(String ticketIdKey) {
//        Map<String, String> map = jedisCluster.hgetAll(ticketIdConversionToKey(ticketIdKey));
//        ProcessInstanceCache processInstance = null;
//        try {
//
//            processInstance = BpmnJedisClusterUtil.mapToObject(map, new ProcessInstanceCache());
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return processInstance;
//    }
//
//    @Override
//    public List<ProcessInstanceCache> listProcessInstanceCache(String ticketIdKey) {
//
//        return null;
//    }
//
//    @Override
//    public long removeMembershipUserCache(String userIdKey) {
//        return 0;
//    }
//
//    @Override
//    public long removeMembershipUserCache(List<String> userIdKeyList) {
//        return 0;
//    }
//
//    @Override
//    public long removeMembershipGroupCache(String groupIdKey) {
//        return 0;
//    }
//
//    @Override
//    public long removeMembershipGroupCache(List<String> groupIdKeyList) {
//        return 0;
//    }
//
//    @Override
//    public long removeProcessInstanceCache(String ticketIdKey) {
//        return 0;
//    }
//
//    @Override
//    public long removeProcessInstanceCache(List<String> ticketIdKeyList) {
//        return 0;
//    }
//
//    private String userIdConversionToKey(String id) {
//        String key = RedisKeyPrefixConstant.REDIS_SET + RedisKeyPrefixConstant.MEMBERSHIP_USER + id;
//        return key;
//    }
//
//    private String groupIdConversionToKey(String id) {
//        String key = RedisKeyPrefixConstant.REDIS_SET + RedisKeyPrefixConstant.MEMBERSHIP_GROUP + id;
//        return key;
//    }
//
//    private String ticketIdConversionToKey(String id) {
//        String key = RedisKeyPrefixConstant.REDIS_HASH + RedisKeyPrefixConstant.PROCESS_INSTANCE + id;
//        return key;
//    }
//}
