package util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BpmnJedisClusterUtil {

    public static String getKeyAddrByJedis(JedisCluster jedisCluster, String keyName) {
        Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
        for (String ip : clusterNodes.keySet()) {
            JedisPool jp = clusterNodes.get(ip);
            try (Jedis jes = jp.getResource()) {
                if (jes.exists(keyName)) {
                    System.out.println(keyName + "这个key在-->" + ip);
                    return ip;
                }
                ;
            } catch (Exception e) {
            }


        }
        return null;
    }

    /**
     * 将一个list均分成n个list,主要通过偏移量来实现的
     *
     * @param source
     * @return
     */
    public static <T> List<List<T>> averageAssign(List<T> source, int n) {
        List<List<T>> result = new ArrayList<List<T>>();
        int remaider = source.size() % n;  //(先计算出余数)
        int number = source.size() / n;  //然后是商
        int offset = 0;//偏移量
        for (int i = 0; i < n; i++) {
            List<T> value = null;
            if (remaider > 0) {
                value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
                remaider--;
                offset++;
            } else {
                value = source.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }
        return result;
    }/**
     * 将一个list均分成n个list,主要通过偏移量来实现的
     *
     * @param source
     * @return
     */
    public static <T> List<List<T>> byCountAssign(List<T> source, int n) {
        List<List<T>> result = new ArrayList<List<T>>();
        int number = source.size() / n;  //然后是商
        int offset = 0;//偏移量
        for (int i = 0; i < number+1; i++) {
            List<T> val = null;
            if (number > 0) {
                if(i==number){
                    val = source.subList(i * n  ,source.size()  );
                }else{
                    val = source.subList(i * n  , (i + 1) * n );
                }
            } else {
                val = source.subList(i * n ,source.size() );
            }
            result.add(val);
        }
        return result;
    }

    /**
     * 将一个list均分成n个list,主要通过偏移量来实现的
     *
     * @param source
     * @return
     */
    public static <T> Object[] listToArray(List<T> source) {
        ArrayList<T> list = new ArrayList<T>();

        Object[] objects = new Object[list.size()];
        list.toArray(objects);
        return objects;
    }


    public static Map<String, String> objectToMap(Object obj){
        Map<String, String> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        System.out.println(clazz);
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            String value = null;
            try {
                value = (String) field.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            map.put(fieldName, value);
        }
        return map;
    }
}
