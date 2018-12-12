package com.temp.code.jedis;

import redis.clients.jedis.*;

import java.util.*;

public class ShardedJedisTest {
    private Jedis jedis;//非切片额客户端连接
    private JedisPool jedisPool;//非切片连接池
    private ShardedJedis shardedJedis;//切片额客户端连接
    private ShardedJedisPool shardedJedisPool;//切片连接池

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public ShardedJedis getShardedJedis() {
        return shardedJedis;
    }

    public void setShardedJedis(ShardedJedis shardedJedis) {
        this.shardedJedis = shardedJedis;
    }

    public ShardedJedisPool getShardedJedisPool() {
        return shardedJedisPool;
    }

    public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
        this.shardedJedisPool = shardedJedisPool;
    }

    public Jedis getJedis() {
        return jedis;
    }

    public void setJedis(Jedis jedis) {
        this.jedis = jedis;
    }

    public ShardedJedisTest()
    {
        initialPool();
        initialShardedPool();
        shardedJedis = shardedJedisPool.getResource();
        jedis = jedisPool.getResource();
        jedis.select(10);
        /**
         * shardedJedis选择分库
         */
        Collection<Jedis> collection=shardedJedis.getAllShards();
        Iterator<Jedis> jediss = collection.iterator();
        while(jediss.hasNext()){
            jediss.next().select(10);
        }
    }
    /**
     * 初始化非切片池
     */
    private void initialPool()
    {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(1000l);
        config.setTestOnBorrow(false);
        jedisPool = new JedisPool(config,"192.168.100.109",6379);
    }
    /**
     * 初始化切片池
     */
    private void initialShardedPool()
    {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(1000l);
        config.setTestOnBorrow(false);
        // slave链接
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
//        shards.add(new JedisShardInfo("192.168.100.109", 6379, "master"));
//        shards.add(new JedisShardInfo("192.168.100.109", 6379, "master"));
//        shards.add(new JedisShardInfo("192.168.100.109", 6379,500));
        shards.add(new JedisShardInfo("192.168.100.109", 6380, 500));
//        //设置Redis信息
//        String host = "127.0.0.1";
//        JedisShardInfo shardInfo1 = new JedisShardInfo(host, 6379, 500);
//        shardInfo1.setPassword("test123");
//        JedisShardInfo shardInfo2 = new JedisShardInfo(host, 6380, 500);
//        shardInfo2.setPassword("test123");
//        JedisShardInfo shardInfo3 = new JedisShardInfo(host, 6381, 500);
//        shardInfo3.setPassword("test123");
        // 构造池
        shardedJedisPool = new ShardedJedisPool(config, shards);

    }
     public void closs(){
         if(shardedJedis!=null) {
             shardedJedis.close();
         }
         if(jedis!=null) {
             jedis.close();
         }
         if(jedisPool!=null){
             jedisPool.close();
         }
         if(shardedJedisPool!=null){
             shardedJedisPool.close();
         }
     }
    public static void main(String[] args) {

        ShardedJedisTest shardTest = new ShardedJedisTest();
//        ShardedJedisPool shardedJedisPool = shardTest.getShardedJedisPool();
//            ShardedJedis shardedJedis = shardedJedisPool.getResource();
        ShardedJedis shardedJedis = shardTest.getShardedJedis();
        shardedJedis.set("test1", "test");
            shardedJedis.set("test11", "test1");
            String test = shardedJedis.get("test");
            System.out.println(test);

        shardedJedis.set("cnblog", "cnblog");
        shardedJedis.set("redis", "redis");
//        shardedJedis.set("test", "test");
        shardedJedis.set("123456", "1234567");
        shardedJedis.del("66666");
        System.out.println(shardedJedis.get("test"));
        System.out.println(shardedJedis.hgetAll("user01"));
        System.out.println("删除KEY   test   "+shardedJedis.del("test"));
        Client client1 = shardedJedis.getShard("cnblog").getClient();
        Client client2 = shardedJedis.getShard("redis").getClient();
        Client client3 = shardedJedis.getShard("test").getClient();
        Client client4 = shardedJedis.getShard("123456").getClient();
        Client client5 = shardedJedis.getShard("66666").getClient();
        Client client6 = shardedJedis.getShard("user01").getClient();

        ////打印key在哪个server中
        System.out.println("cnblog in server:" + client1.getHost() + " and port is:" + client1.getPort());
        System.out.println("redis  in server:" + client2.getHost() + " and port is:" + client2.getPort());
        System.out.println("test   in server:" + client3.getHost() + " and port is:" + client3.getPort());
        System.out.println("123456 in server:" + client4.getHost() + " and port is:" + client4.getPort());
        System.out.println("66666 in server:" + client5.getHost() + " and port is:" + client4.getPort());
        System.out.println("user01 in server:" + client6.getHost() + " and port is:" + client4.getPort());
        shardTest.closs();

    }

}
/*
清理数据库数据
 */
class DeclareRedis{
    public static void main(String[] args) {
        ShardedJedisTest shardedJesisTest = new ShardedJedisTest();
        ShardedJedis shardedJedis = shardedJesisTest.getShardedJedis();
        Collection<Jedis> allShards = shardedJedis.getAllShards();
        Iterator<Jedis> iterator = allShards.iterator();
        if(iterator.hasNext()){
            Jedis next = iterator.next();
            Client client = next.getClient();
            System.out.println( client.getHost()+":"+client.getPort());
//            System.out.println(next.info());
//            System.out.println(next.info("Server")+"============");
            System.out.println(client.getDB());
            System.out.println(next.flushDB());
        }



    }
}

class TestByte{
    public static void main(String[] args) {
        ShardedJedisTest shardedJesisTest = new ShardedJedisTest();
        Jedis jedis = shardedJesisTest.getJedis();

//        jedis.h

    }
}
