//package com.temp.code.jedis;
//
//import com.hq.bpmn.cache.bean.ProcessInstanceCache;
//import com.hq.bpmn.cache.bean.RedisKeyPrefixConstant;
//import com.hq.bpmn.common.util.BpmnJedisClusterUtil;
//import com.hq.bpmn.common.util.DateUtil;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisCluster;
//import redis.clients.jedis.JedisPool;
//
//import javax.sql.DataSource;
//import java.lang.reflect.Field;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.util.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:application-context-wf.xml")
//public class CacheServiceJedisClusterImplTest {
//    @Autowired
//    private JedisCluster jedisCluster;
//    @Autowired
//    private JedisPool jedisPool;
//    @Autowired
//    private CacheServiceJedisImpl cacheServiceJedisImpl;
//    @Autowired
//    private CacheServiceJedisClusterImpl cacheServiceJedisClusterImpl;
//    @Qualifier("busiDataSource")
//    @Autowired
//    private DataSource dataSource;
//
//
//    @Test
//    public void cacheMembershipUser() throws Exception {
//        System.out.println(jedisCluster);
//        jedisCluster.set("111666", "2222");
//        System.out.println(jedisCluster.get("111666"));
//        String keyAddrByJedis = BpmnJedisClusterUtil.getKeyAddrByJedis(jedisCluster, "111666");
//        System.out.println(keyAddrByJedis);
//        Jedis resource = jedisPool.getResource();
//        resource.set("33333", "99999");
//        System.out.println(resource.get("33333"));
//    }
//
//    //    @Test
//    public Map<String, ProcessInstanceCache> cacheMembershipGroup() throws Exception {
//        long first = System.currentTimeMillis();
//        Connection connection = dataSource.getConnection();
////        String sql ="select * from act_hq_procinst  where id_ in('79c8373f61f14640be2f2b5e3e9c8f39','d03a617c053a4b9a91d8a5e7edd5ddd9')";
////        String sql ="select * from act_hq_procinst";
//        String sql = "select ahp.* from act_hq_procinst  ahp where rownum<20";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//        ResultSet resultSet = preparedStatement.executeQuery();
//        System.out.println(resultSet.last());
//        int row = resultSet.getRow();
//        System.out.println(row);
//        ResultSetMetaData rsm = resultSet.getMetaData(); //获得列集
//        int col = rsm.getColumnCount();   //获得列的个数
//        System.out.println("获得列的个数====" + col);
//        String colName[] = new String[col];
//        //取结果集中的表头名称, 放在colName数组中
//        for (int i = 0; i < col; i++) {   //第一列,从1开始.所以获取列名,或列值,都是从1开始
//            colName[i] = rsm.getColumnName(i + 1); //获得列值的方式一:通过其序号
//        }//End for
//        resultSet.beforeFirst();
//        System.out.println("列名-------" + Arrays.toString(colName));
//        String data[][] = new String[row][col];
//        //取结果集中的数据, 放在data数组中
//        for (int i = 0; i < row; i++) {
//            resultSet.next();
//            for (int j = 0; j < col; j++) {
//                data[i][j] = resultSet.getString(j + 1);
//                //System.out.println (data[i][j]);
//            }
//        }//End for
//        String colProcessIns[] = {"piId", "bpmnType", "ticketId", "pdId", "startUserId", null, "startTime", null};
//        System.out.println("数据-----" + Arrays.toString(data[0]));
//        System.out.print("查询数据系统耗时：");
//        System.out.println(System.currentTimeMillis() - first);
//
//        long conversionFirst = System.currentTimeMillis();
//        /**
//         * 将数据发送到redis
//         */
//        Map<String, ProcessInstanceCache> map = new HashMap<String, ProcessInstanceCache>();
//        long count = 0L;
//        for (String[] s : data) {
//            ProcessInstanceCache p = new ProcessInstanceCache();
//            for (int i = 0; i < colProcessIns.length; i++) {
//                Field[] declaredFields = p.getClass().getDeclaredFields();
//                for (Field f : declaredFields) {
//                    f.setAccessible(true);
//                    if (f.getName().equals(colProcessIns[i])) {
//                        if ("java.util.Date".equals(f.getType().getName())) {
//                            f.set(p, DateUtil.getDateYYMMDDhhmmss(s[i]));
//                            continue;
//                        }
//                        f.set(p, s[i]);
//                    }
//                }
//            }
////                long l = cacheServiceJedisImpl.cacheProcessInstance(s[2], p);
//            map.put(s[2], p);
////                long l = cacheServiceJedisImpl.cacheProcessInstance(s[2], p);
////                long l = cacheServiceJedisClusterImpl.cacheProcessInstance(s[2], p);
////                count+=l;
//        }
//        System.out.println("数据转换耗时：");
//        System.out.println(System.currentTimeMillis() - conversionFirst);
////        System.out.println(data.length);
//        System.out.println(map.size());
//        long cacheFirst = System.currentTimeMillis();
////        long countval = cacheServiceJedisImpl.cacheProcessInstance(map);
////        long countval = cacheServiceJedisClusterImpl.cacheProcessInstance(map);
////        System.out.println("缓存的实例数量："+countval);
////        System.out.print("缓存实例耗时：");
////        System.out.print(System.currentTimeMillis()-cacheFirst);
////        System.out.println("-----------------------------------------");
//        return map;
//    }
//
//    @Test
//    public void delete() throws Exception {
////        Jedis resource = jedisPool.getResource();
////        resource.flushAll();
//
//
//    }
//
//    @Test
//    public void findObject() throws Exception {
//        Jedis resource = jedisPool.getResource();
//        Map<String, String> stringStringMap = resource.hgetAll("h:pi:0101:5FCF97C8E88F6118E0533003A8C06E81");
//        System.out.println(stringStringMap);
//        ProcessInstanceCache processInstanceCache = cacheServiceJedisImpl.getProcessInstanceCache("5FCF97C8E88F6118E0533003A8C06E81", "0101");
//        System.out.println(processInstanceCache);
//        ProcessInstanceCache processInstanceCache1 = cacheServiceJedisClusterImpl.getProcessInstanceCache("businessKey114074", "z");
//        System.out.println(processInstanceCache1);
//
//
//    }
//
//    @Test
//    public void testCacheServiceJedisImpl() throws Exception {
//        List<String> list = new ArrayList<String>();
//        list.add("22333");
//        list.add("88888");
//        list.add("3333");
//        cacheServiceJedisImpl.cacheMembershipGroup("g11111", list);
//        cacheServiceJedisImpl.cacheMembershipGroup("g22222", list);
//        cacheServiceJedisImpl.cacheMembershipUser("u11111", list);
//        cacheServiceJedisImpl.cacheMembershipUser("u22222", list);
//        System.out.println(cacheServiceJedisImpl.listGroupIdList("u11111"));
//        System.out.println(cacheServiceJedisImpl.listUserIdList("g11111"));
////        System.out.println( cacheServiceJedisImpl.removeMembershipGroupCache("g11111"));
////        System.out.println( cacheServiceJedisImpl.removeMembershipUserCache("u11111"));
//        List<String> keyList = new ArrayList<String>();
//        keyList.add("g11111");
//        keyList.add("g22222");
////        System.out.println( cacheServiceJedisImpl.removeMembershipGroupCache(keyList));
//        keyList.clear();
//        keyList.add("u11111");
//        keyList.add("u22222");
////        System.out.println( cacheServiceJedisImpl.removeMembershipUserCache(keyList));
//        Map<String, ProcessInstanceCache> stringProcessInstanceCacheMap = cacheMembershipGroup();
//        cacheServiceJedisImpl.cacheProcessInstance(stringProcessInstanceCacheMap.get("7EC08AF84FB4954EFB1CF15CEE1170E8").getTicketId(), stringProcessInstanceCacheMap.get("7EC08AF84FB4954EFB1CF15CEE1170E8"));
//        cacheServiceJedisImpl.cacheProcessInstance(stringProcessInstanceCacheMap);
//        System.out.println(cacheServiceJedisImpl.getProcessInstanceCache("7EC08AF84FB4954EFB1CF15CEE1170E8", "0203"));
////        cacheServiceJedisImpl.removeProcessInstanceCache("7EC08AF84FB4954EFB1CF15CEE1170E8","0203");
////        keyList.clear();
////        keyList.add("004185E11E42F10DE0C66709FA191DD0");
////        keyList.add("B8EB07C463E578678B53E61A1EC68CD8");
////        keyList.add("CBDE0B7E1526BA950738BBCB27F67B5F");
////        Map<String ,List<String> >proceIns = new HashMap<String ,List<String>>();
////        proceIns.put("0203",keyList);
////        System.out.println( cacheServiceJedisImpl.removeProcessInstanceCache(proceIns));
//        Jedis resource = jedisPool.getResource();
//        resource.eval("local t1 = redis.call('keys','s:*') for k,v in pairs(t1) do redis.call('del',v) print(v) end");
////        jedisCluster.eval("local t1 = redis.call('keys','s:*') for k,v in pairs(t1) do redis.call('del',v) print(v) end",1);
////        cacheServiceJedisImpl.listGroupIdList();
//    }
//
//    @Test
//    public void testCacheServiceJedisClusterImpl() throws Exception {
//        List<String> list = new ArrayList<String>();
//        list.add("22333");
//        list.add("88888");
//        list.add("3333");
//        cacheServiceJedisClusterImpl.cacheMembershipGroup("g11111", list);
//        cacheServiceJedisClusterImpl.cacheMembershipGroup("g22222", list);
//        cacheServiceJedisClusterImpl.cacheMembershipUser("u11111", list);
//        cacheServiceJedisClusterImpl.cacheMembershipUser("u22222", list);
//        System.out.println(cacheServiceJedisClusterImpl.listGroupIdList("u11111"));
//        System.out.println(cacheServiceJedisClusterImpl.listUserIdList("g11111"));
////        System.out.println( cacheServiceJedisClusterImpl.removeMembershipGroupCache("g11111"));
////        System.out.println( cacheServiceJedisClusterImpl.removeMembershipUserCache("u11111"));
//        List<String> keyList = new ArrayList<String>();
//        keyList.add(RedisKeyPrefixConstant.getRedisSetKey(RedisKeyPrefixConstant.MEMBERSHIP_GROUP,"g11111"));
//        keyList.add(RedisKeyPrefixConstant.getRedisSetKey(RedisKeyPrefixConstant.MEMBERSHIP_GROUP,"g22222"));
//        Map<String, List<String>> hostPortMapMap = BpmnJedisClusterUtil.getHostPortMapMap(keyList, cacheServiceJedisClusterImpl.slotHostMap);
//        System.out.println("==d==================="+hostPortMapMap);
//        Jedis resource = cacheServiceJedisClusterImpl.nodeMap.get("192.168.100.109:3791").getResource();
//        resource.eval("local t1 = {'s:membership:group:g11111','s:membership:group:g22222'} for k,v in pairs(t1) do redis.call('del',v) print(v) end",0);
//
////        System.out.println(cacheServiceJedisClusterImpl.removeMembershipGroupCache(keyList));
////        keyList.clear();
////        keyList.add("u11111");keyList.add("u22222");
////        System.out.println( cacheServiceJedisClusterImpl.removeMembershipUserCache(keyList));
//        Map<String, ProcessInstanceCache> stringProcessInstanceCacheMap = cacheMembershipGroup();
//        cacheServiceJedisClusterImpl.cacheProcessInstance(stringProcessInstanceCacheMap.get("7EC08AF84FB4954EFB1CF15CEE1170E8").getTicketId(), stringProcessInstanceCacheMap.get("7EC08AF84FB4954EFB1CF15CEE1170E8"));
//        cacheServiceJedisClusterImpl.cacheProcessInstance(stringProcessInstanceCacheMap);
//        System.out.println(cacheServiceJedisClusterImpl.getProcessInstanceCache("7EC08AF84FB4954EFB1CF15CEE1170E8", "0203"));
//
////        cacheServiceJedisClusterImpl.removeProcessInstanceCache("7EC08AF84FB4954EFB1CF15CEE1170E8","0203");
////        keyList.clear();
////        keyList.add("004185E11E42F10DE0C66709FA191DD0");
////        keyList.add("B8EB07C463E578678B53E61A1EC68CD8");
////        keyList.add("CBDE0B7E1526BA950738BBCB27F67B5F");
////        Map<String ,List<String> >proceIns = new HashMap<String ,List<String>>();
////        proceIns.put("0203",keyList);
////        System.out.println( cacheServiceJedisClusterImpl.removeProcessInstanceCache(proceIns));
////
//
////        cacheServiceJedisImpl.listGroupIdList();
//    }
//}