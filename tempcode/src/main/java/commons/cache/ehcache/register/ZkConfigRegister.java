package commons.cache.ehcache.register;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

import com.tjhq.commons.cache.context.CacheContext;
import com.tjhq.commons.cache.ehcache.service.HqCacheManager;


public class ZkConfigRegister {
    
    static {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void init() throws Exception {
        String rmiIP = System.getProperty("fasp2.zkserver.dubbohost");
        System.setProperty("java.rmi.server.hostname", rmiIP);
 
        String zkServer = System.getProperty("fasp2.zkserver.address");
        
        String rmiPort = System.getProperty("java.rmi.server.port");
        if (rmiPort == null || rmiPort.trim().length() == 0) {
            //rmiPort = "40001";
        	CacheContext.getInstance().disable();
        	return;
        }
        
        HqCacheManager cacheManager = new HqCacheManager();
        cacheManager.create(rmiIP, rmiPort);
        
        ZkClient zk = new ZkClient(zkServer, 30000);
        
        IZkChildListener configWh = new EhCacheConfigRegister(zk); 

        if (zk != null) {
            if (!zk.exists("/hqcache")) {
                zk.create("/hqcache", "hqcache config dir".getBytes(), CreateMode.PERSISTENT);
            }
            
            zk.subscribeChildChanges("/hqcache", configWh);
            
            String node = rmiIP+":"+rmiPort;
            
            if (!zk.exists("/hqcache/"+node) || zk.delete("/hqcache/"+node)) {
                zk.create("/hqcache/"+node, "//"+node+"/hqCacheData", CreateMode.EPHEMERAL);
                
            }
            
        }
        
        

    }
    
}
