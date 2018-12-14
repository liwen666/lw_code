package commons.cache.ehcache.register;

import java.util.ArrayList;
import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import com.tjhq.commons.cache.ehcache.service.HqCacheManager;

public class EhCacheConfigRegister implements IZkChildListener  {
    
    private  ZkClient zk;
    
    public EhCacheConfigRegister( ZkClient zk) {
        this.zk = zk;
    }

    @Override
    public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
        HqCacheManager hqCacheManager = new HqCacheManager();
        List<String> rmiUrls = new ArrayList<String>();
        for (String node : currentChilds) {
            rmiUrls.add(zk.readData("/hqcache/" + node).toString());
        }
        
        hqCacheManager.addManagerPeerProvider(rmiUrls);
        
    }
    

    
    
}
