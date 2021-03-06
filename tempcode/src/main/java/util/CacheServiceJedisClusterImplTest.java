package util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context-wf.xml")
public class CacheServiceJedisClusterImplTest {
    @Autowired
    private JedisCluster jedisCluster;
    @Autowired
    private JedisPool jedisPool;
//    @Autowired
//    private CacheServiceJedisImpl cacheServiceJedisImpl;
//    @Autowired
//    private CacheServiceJedisClusterImpl cacheServiceJedisClusterImpl;
    @Qualifier("busiDataSource")
    @Autowired
    private DataSource dataSource;


    @Test
    public void cacheMembershipUser() throws Exception {
        System.out.println(jedisCluster);
        jedisCluster.set("111666","2222");
        System.out.println(jedisCluster.get("111666"));
        String keyAddrByJedis = BpmnJedisClusterUtil.getKeyAddrByJedis(jedisCluster, "111666");
        System.out.println(keyAddrByJedis);
        Jedis resource = jedisPool.getResource();
        resource.set("33333","99999");
        System.out.println(resource.get("33333"));
    }

    @Test
    public void cacheMembershipGroup() throws Exception {
        long first = System.currentTimeMillis();
        Connection connection = dataSource.getConnection();
//        String sql ="select * from act_hq_procinst  where id_ in('79c8373f61f14640be2f2b5e3e9c8f39','d03a617c053a4b9a91d8a5e7edd5ddd9')";
        String sql ="select * from act_hq_procinst";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println(resultSet.last());
        int row = resultSet.getRow();
        System.out.println(row);
        ResultSetMetaData rsm =resultSet.getMetaData(); //获得列集
        int col = rsm.getColumnCount();   //获得列的个数
        System.out.println("获得列的个数===="+col);
        String colName[] = new String[col];
        //取结果集中的表头名称, 放在colName数组中
        for (int i = 0; i < col; i++) {   //第一列,从1开始.所以获取列名,或列值,都是从1开始
            colName[i] = rsm.getColumnName( i + 1 ); //获得列值的方式一:通过其序号
        }//End for
        resultSet.beforeFirst();
        System.out.println("列名-------"+ Arrays.toString(colName));
        String data[][] = new String[row][col];
        //取结果集中的数据, 放在data数组中
        for (int i = 0; i < row; i++) {
            resultSet.next();
            for (int j = 0; j < col; j++) {
                data[i][j] = resultSet.getString (j + 1);
                //System.out.println (data[i][j]);
            }
        }//End for
        String colProcessIns[]={"piId","bpmnType","ticketId","pdId","startUserId",null,"startTime",null};
        System.out.println("数据-----"+ Arrays.toString(data[0]));
        System.out.print("查询数据系统耗时：");
        System.out.println(System.currentTimeMillis()-first);

        long cacheFirst = System.currentTimeMillis();
        /**
         * 将数据发送到redis
         */
        long count =0L;
            for (String[]s:data){
                ProcessInstanceCache p = new ProcessInstanceCache();
                for(int i=0;i<colProcessIns.length;i++){
                    Field[] declaredFields = p.getClass().getDeclaredFields();
                    for(Field f:declaredFields){
                        f.setAccessible(true);
                        if(f.getName().equals(colProcessIns[i])){
                            if("java.util.Date".equals(f.getType().getName())){
                                f.set(p, DateUtil.getDate(s[i]));
                                continue;
                            }
                            f.set(p,s[i]);
                        }
                    }
                }
//                long l = cacheServiceJedisImpl.cacheProcessInstance(s[2], p);
//                count+=l;
            }
        System.out.println("缓存的实例数量："+count);
        System.out.print("缓存实例耗时：");
        System.out.print(System.currentTimeMillis()-cacheFirst);

    }


}