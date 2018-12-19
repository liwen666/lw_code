//package com.temp.code.jedis;
//
//import com.hq.bpmn.cache.bean.ProcessInstanceCache;
//import com.hq.bpmn.cache.bean.RedisKeyPrefixConstant;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import redis.clients.jedis.*;
//
//import java.io.IOException;
//import java.lang.reflect.Field;
//import java.util.*;
//
//public class BpmnJedisClusterUtil {
//    private static final Logger LOGGER = LoggerFactory.getLogger(BpmnJedisClusterUtil.class);
//
//    public static String getKeyAddrByJedis(JedisCluster jedisCluster, String keyName) {
//        Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
//        for (String ip : clusterNodes.keySet()) {
//            JedisPool jp = clusterNodes.get(ip);
//            try (Jedis jes = jp.getResource()) {
//                if (jes.exists(keyName)) {
//                    System.out.println(keyName + "这个key在-->" + ip);
//                    return ip;
//                }
//                jes.close();
//            } catch (Exception e) {
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 将一个list均分成n个list,主要通过偏移量来实现的
//     *
//     * @param source
//     * @return
//     */
//    public static <T> List<List<T>> averageAssign(List<T> source, int n) {
//        List<List<T>> result = new ArrayList<List<T>>();
//        int remaider = source.size() % n;  //(先计算出余数)
//        int number = source.size() / n;  //然后是商
//        int offset = 0;//偏移量
//        for (int i = 0; i < n; i++) {
//            List<T> value = null;
//            if (remaider > 0) {
//                value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
//                remaider--;
//                offset++;
//            } else {
//                value = source.subList(i * number + offset, (i + 1) * number + offset);
//            }
//            result.add(value);
//        }
//        return result;
//    }/**
//     * 将一个list均分成n个list,主要通过偏移量来实现的
//     *
//     * @param source
//     * @return
//     */
//    public static <T> List<List<T>> byCountAssign(List<T> source, int n) {
//        List<List<T>> result = new ArrayList<List<T>>();
//        int number = source.size() / n;  //然后是商
//        int offset = 0;//偏移量
//        for (int i = 0; i < number+1; i++) {
//            List<T> val = null;
//            if (number > 0) {
//                if(i==number){
//                    val = source.subList(i * n  ,source.size()  );
//                }else{
//                    val = source.subList(i * n  , (i + 1) * n );
//                }
//            } else {
//                val = source.subList(i * n ,source.size() );
//            }
//            result.add(val);
//        }
//        return result;
//    }
//
//    /**
//     * 将一个list均分成n个list,主要通过偏移量来实现的
//     *
//     * @param source
//     * @return
//     */
//    public static <T> Object[] listToArray(List<T> source) {
//        ArrayList<T> list = new ArrayList<T>();
//
//        Object[] objects = new Object[list.size()];
//        list.toArray(objects);
//        return objects;
//    }
//
//
//    public static Map<String, String> objectToMap(Object obj) throws IllegalAccessException {
//        Map<String, String> map = new HashMap<>();
//        Class<?> clazz = obj.getClass();
//        for (Field field : clazz.getDeclaredFields()) {
//            String fieldName = field.getName();
//            String value = null;
//            field.setAccessible(true);
//            if("java.util.Date".equals(field.getType().getName())){
//               value = DateUtil.dateDisplayToYMD((Date) field.get(obj));
//                map.put(fieldName, value);
//               continue;
//            }
//            try {
//                value = (String) field.get(obj);
//                if(null==value){
//                    value="";
//                }
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//            map.put(fieldName, value);
//        }
//        return map;
//    }
//    public static <T>T mapToObject(Map<String,String> map, T t) throws IllegalAccessException {
//        Field[] fields = t.getClass().getDeclaredFields();
//        for(Field f: fields){
//            f.setAccessible(true);
//            if("java.util.Date".equals(f.getType().getName())){
//                f.set(t,DateUtil.getDate(map.get(f.getName())));
//                continue;
//            }
//                f.set(t,map.get(f.getName()));
//        }
//        return t;
//    }
//    public static TreeMap<Long, String> getSlotHostMap(String anyHostAndPortStr) {
//        TreeMap<Long, String> tree = new TreeMap<Long, String>();
//        String parts[] = anyHostAndPortStr.split(":");
//        HostAndPort anyHostAndPort = new HostAndPort(parts[0], Integer.parseInt(parts[1]));
//        try {
//            Jedis jedis = new Jedis(anyHostAndPort.getHost(), anyHostAndPort.getPort());
//            List<Object> list = jedis.clusterSlots();
//            for (Object object : list) {
//                List<Object> list1 = (List<Object>) object;
//                List<Object> master = (List<Object>) list1.get(2);
//                String hostAndPort = new String((byte[]) master.get(0)) + ":" + master.get(1);
//                tree.put((Long) list1.get(0), hostAndPort);
//                tree.put((Long) list1.get(1), hostAndPort);
//            }
//            jedis.close();
//        } catch (Exception e) {
//
//        }
//        return tree;
//    }
//
//    public static long clusterCacheProcessInstance(Jedis jedis, Map<String, ProcessInstanceCache> processInstanceCacheMap) throws IOException {
//        Pipeline pipelined = jedis.pipelined();
//        long count = 0L;
//        Set<Map.Entry<String, ProcessInstanceCache>> entries = processInstanceCacheMap.entrySet();
//        for (Map.Entry<String, ProcessInstanceCache> m : entries) {
//            String isOK = null;
//            try {
//                pipelined.hmset(RedisKeyPrefixConstant.getRedisHashKey(RedisKeyPrefixConstant.REDIS_HASH,m.getKey(), m.getValue().getBpmnType()), BpmnJedisClusterUtil.objectToMap(m.getValue()));
//
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//            if (count % 50000 == 0) {
//
//                pipelined.sync();
//            }
//            count++;
//        }
//        pipelined.sync();
//        pipelined.close();
//        jedis.close();
//        if (LOGGER.isInfoEnabled()) {
//            LOGGER.info("cluster分片缓存实例数量是：{}  hostPort---------->{}", count, jedis.getClient().getHost() + ":" + jedis.getClient().getPort());
//        }
//        return count;
//    }
//
//}
