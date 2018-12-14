package com.temp.code.cache.service.impl;

import com.hq.bpmn.cache.bean.ProcessInstanceCache;
import com.hq.bpmn.cache.bean.RedisKeyPrefixConstant;
import com.hq.bpmn.cache.service.CacheService;
import com.hq.bpmn.common.util.BpmnJedisClusterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CacheServiceJedisImpl implements CacheService {
    @Autowired
    private JedisPool jedisPool;
    private int batchSendCount = 50000;

    @Override
    public long cacheMembershipUser(String userIdKey, String groupIdValue) {
        Jedis jedis = jedisPool.getResource();
        Long sadd = jedis.sadd(userIdConversionToKey(userIdKey), groupIdValue);
        jedis.close();
        return sadd;
    }

    @Override
    public long cacheMembershipUser(String userIdKey, List<String> groupIdListValue) {
        Jedis jedis = jedisPool.getResource();
        long sendResult = 0L;
        List<List<String>> lists = BpmnJedisClusterUtil.byCountAssign(groupIdListValue, batchSendCount);
        for (List<String> l : lists) {
            String[] groupIds = (String[]) BpmnJedisClusterUtil.listToArray(l);
            Long sadd = jedis.sadd(userIdConversionToKey(userIdKey), groupIds);
            sendResult += sadd;
        }
        jedis.close();
        return sendResult;
    }

    @Override
    public long cacheMembershipGroup(String groupIdKey, String userIdValue) {
        Jedis jedis = jedisPool.getResource();
        Long sadd = jedis.sadd(groupIdConversionToKey(groupIdKey), userIdValue);
        jedis.close();
        return sadd;
    }

    @Override
    public long cacheMembershipGroup(String groupIdKey, List<String> userIdListValue) {
        Jedis jedis = jedisPool.getResource();
        long sendResult = 0L;
        List<List<String>> lists = BpmnJedisClusterUtil.byCountAssign(userIdListValue, batchSendCount);
        for (List<String> l : lists) {
            String[] userIds = (String[]) BpmnJedisClusterUtil.listToArray(l);
            Long sadd = jedis.sadd(groupIdConversionToKey(groupIdKey), userIds);
            sendResult += sadd;
        }
        jedis.close();
        return sendResult;
    }

    @Override
    public long cacheProcessInstance(String ticketIdKey, ProcessInstanceCache processInstanceCacheValue) {
        Jedis jedis = jedisPool.getResource();
        String isOK = null;
        try {
            isOK = jedis.hmset(ticketIdConversionToKey(ticketIdKey,processInstanceCacheValue.getBpmnType()), BpmnJedisClusterUtil.objectToMap(processInstanceCacheValue));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        jedis.close();
        return isOK.equals("OK") ? 1 : 0;

    }

    @Override
    public long cacheProcessInstance(Map<String, ProcessInstanceCache> processInstanceCacheMap) throws IOException {
        Jedis jedis = jedisPool.getResource();
        Pipeline pipelined = jedis.pipelined();
        long count = 0L;
        Set<Map.Entry<String, ProcessInstanceCache>> entries = processInstanceCacheMap.entrySet();
        for (Map.Entry<String,ProcessInstanceCache> m :entries) {
            String isOK = null;
            try {
                pipelined.hmset(ticketIdConversionToKey(m.getKey(), m.getValue().getBpmnType()), BpmnJedisClusterUtil.objectToMap(m.getValue()));

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if(count%batchSendCount==0){

                pipelined.sync();
            }
            count++;
        }
        pipelined.sync();
        pipelined.close();
        jedis.close();
        System.out.println(processInstanceCacheMap.size());
        return count;
    }

    @Override
    public List<String> listGroupIdList(String userIdKey) {
        Jedis jedis = jedisPool.getResource();
        Set<String> sGroupMembers = jedis.smembers(userIdConversionToKey(userIdKey));
        jedis.close();
        return new ArrayList<String>(sGroupMembers);
    }

    @Override
    public List<String> listUserIdList(String groupIdKey) {
        Jedis jedis = jedisPool.getResource();
        Set<String> sUserMembers = jedis.smembers(groupIdConversionToKey(groupIdKey));
        jedis.close();
        return new ArrayList<String>(sUserMembers);
    }

    @Override
    public ProcessInstanceCache getProcessInstanceCache(String ticketIdKey, String bpmn) {
        Jedis jedis = jedisPool.getResource();
        Map<String, String> map = jedis.hgetAll(ticketIdConversionToKey(ticketIdKey,bpmn ));
        ProcessInstanceCache processInstance = null;
        try {

            processInstance = BpmnJedisClusterUtil.mapToObject(map,new ProcessInstanceCache());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        jedis.close();
        return processInstance;
    }

    @Override
    public List<ProcessInstanceCache> listProcessInstanceCache(String ticketIdKey, String bpmnTypeKey) {
        return null;
    }

    List<ProcessInstanceCache> listProcessInstanceCache(String ticketIdKey) {
        return null;
    }

    @Override
    public long removeMembershipUserCache(String userIdKey) {
        return 0;
    }

    @Override
    public long removeMembershipUserCache(List<String> userIdKeyList) {
        return 0;
    }

    @Override
    public long removeMembershipGroupCache(String groupIdKey) {
        return 0;
    }

    @Override
    public long removeMembershipGroupCache(List<String> groupIdKeyList) {
        return 0;
    }

    @Override
    public long removeProcessInstanceCache(String ticketIdKey) {
        return 0;
    }

    @Override
    public long removeProcessInstanceCache(List<String> ticketIdKeyList) {
        return 0;
    }

    private String userIdConversionToKey(String id) {
        String key = RedisKeyPrefixConstant.REDIS_SET + RedisKeyPrefixConstant.MEMBERSHIP_USER + id;
        return key;
    }

    private String groupIdConversionToKey(String id) {
        String key = RedisKeyPrefixConstant.REDIS_SET + RedisKeyPrefixConstant.MEMBERSHIP_GROUP + id;
        return key;
    }

    private String ticketIdConversionToKey(String id, String bpmnType) {
        String key = RedisKeyPrefixConstant.REDIS_HASH + RedisKeyPrefixConstant.PROCESS_INSTANCE +bpmnType+ RedisKeyPrefixConstant.REDIS_SPLIT +id;
        return key;
    }


}
