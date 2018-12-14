package com.temp.code.cache.service;


import com.temp.code.cache.bean.ProcessInstanceCache;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @date 2018/12/13 10:17
 */
public interface CacheService {

    // c
    long cacheMembershipUser(String userIdKey, String groupIdValue);

    long cacheMembershipUser(String userIdKey, List<String> groupIdListValue);

    long cacheMembershipGroup(String groupIdKey, String userIdValue);

    long cacheMembershipGroup(String groupIdKey, List<String> userIdListValue);

    long cacheProcessInstance(String ticketIdKey, ProcessInstanceCache processInstanceCacheValue);

    long cacheProcessInstance(Map<String, ProcessInstanceCache> processInstanceCacheMap) throws IOException;


    // r
    List<String> listGroupIdList(String userIdKey);

    List<String> listUserIdList(String groupIdKey);

    ProcessInstanceCache getProcessInstanceCache(String ticketIdKey, String bpmnTypeKey);

    List<ProcessInstanceCache> listProcessInstanceCache(String ticketIdKey, String bpmnTypeKey);

    // u

    // d
    long removeMembershipUserCache(String userIdKey);

    long removeMembershipUserCache(List<String> userIdKeyList);

    long removeMembershipGroupCache(String groupIdKey);

    long removeMembershipGroupCache(List<String> groupIdKeyList);

    long removeProcessInstanceCache(String ticketIdKey);

    long removeProcessInstanceCache(List<String> ticketIdKeyList);


}
