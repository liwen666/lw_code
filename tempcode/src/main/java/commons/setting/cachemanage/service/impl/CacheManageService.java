
package commons.setting.cachemanage.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.distribution.CachePeer;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.constants.ShowType;
import com.tjhq.commons.inputcomponent.grid.commonGrid.service.impl.CommonGridService;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.PageInfo;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.utils.JSTypeUtils;
import com.tjhq.commons.setting.cachemanage.service.ICacheManageService;
import com.tjhq.commons.setting.external.po.TreeNode;
import com.tjhq.commons.utils.DateUtil;

/**
 * @ClassName: CacheManageService
 * @Description:缓存管理实现类
 * @author: ZhengQ 2016-12-9 上午10:10:36
 */

@Service
@Transactional(readOnly = true)
public class CacheManageService extends CommonGridService implements ICacheManageService {
    private CacheManager cacheManager;

    protected CacheManager getCacheManager() {
        if (cacheManager == null) {
            cacheManager = CacheManager.getCacheManager("hqCacheManager");
        }
        return cacheManager;
    }

    protected Cache getCache() {
        return getCacheManager().getCache("hqCacheData");
    }

    @Override
    public List<TreeNode> getIpTree() throws ServiceException {
        List<TreeNode> ipTreeList = new ArrayList<TreeNode>();
        List<CachePeer> cacheListenerList = getCacheManager().getCachePeerListener("RMI").getBoundCachePeers();
        List<CachePeer> cachePeerList = getCacheManager().getCacheManagerPeerProviders().get("RMI")
                .listRemoteCachePeers(cacheManager.getCache("hqCacheData"));
        
        cachePeerList.addAll(0,cacheListenerList);
        Map<String, String> ipKeyMap = new HashMap<String, String>();
        try {
            for (CachePeer cachePeer : cachePeerList) {
                TreeNode ipTree = new TreeNode();
                String url = cachePeer.getUrl();
                url = url.substring(url.indexOf("//")+2, url.lastIndexOf("/"));
                ipTree.setId(url);
                ipTree.setName(url);
                if(!ipKeyMap.containsKey(url)){
                    ipTreeList.add(ipTree);
                    ipKeyMap.put(url,"key");
                }
               
            }
        } catch (Exception e) {
            throw new ServiceException(ExceptionCode.ERR_00000, "获取缓存集群IP异常！原因：" + e.getCause().getMessage(), false);
        }
        return ipTreeList;
    }

    @Override
    public Table getTableHead() throws ServiceException {
        Table grid = new Table();
        grid.setTableID("");
        List<Column> list = new ArrayList<Column>();

        Column cacheKeyCol = new Column("cacheKey", "缓存KEY", "缓存KEY", "cacheKey", JSTypeUtils
                .getJSDateType(DataType.STRING), ShowType.SHOW_TYPE_HTML, 300, 0, 400, true, true, false, true);
        Column cacheSizeCol = new Column("cacheSize", "缓存大小", "缓存大小", "cacheSize", JSTypeUtils
                .getJSDateType(DataType.STRING), ShowType.SHOW_TYPE_HTML, 100, 0, 100, true, true, false, true);
        Column cacheVersionCol = new Column("cacheVersion", "缓存版本", "缓存版本", "cacheVersion", JSTypeUtils
                .getJSDateType(DataType.STRING), ShowType.SHOW_TYPE_HTML, 100, 0, 100, false, true, false, true);
        Column cacheUpdateTime = new Column("cacheUpdateTime", "最后更新时间", "最后更新时间", "cacheUpdateTime", JSTypeUtils
                .getJSDateType(DataType.DATE), ShowType.SHOW_TYPE_DATE, 100, 0, 150, false, true, false, true);
        Column cacheAccessTime = new Column("cacheAccessTime", "最近访问时间", "最近访问时间", "cacheAccessTime", JSTypeUtils
                .getJSDateType(DataType.DATE), ShowType.SHOW_TYPE_DATE, 100, 0, 150, false, true, false, true);
        list.add(cacheKeyCol);
        list.add(cacheSizeCol);
        list.add(cacheUpdateTime);
        list.add(cacheAccessTime);
       // list.add(cacheVersionCol);
        grid.setColumnList(list);
        return grid;
    }

    @Override
    public Object getTableData() throws ServiceException {
        List<Map<String, Object>> cacheList = new ArrayList<Map<String, Object>>();
        PageInfo pageInfo = new PageInfo();
        if(getCache() == null){
            return pageInfo;
        }
        List<String> keys = getCache().getKeys();
            for (String key : keys) {
                Map<String, Object> cacheMap = new HashMap<String, Object>();
                Long version =  getCache().get(key).getVersion();
                getCache().get(key).getLastUpdateTime();
               long size = getCache().get(key).getSerializedSize();
                cacheMap.put("cacheKey", key);
                cacheMap.put("cacheSize", bytes2kb(size));
                cacheMap.put("cacheUpdateTime",   DateUtil.dateDisplay(getCache().get(key).getLastUpdateTime()));
                cacheMap.put("cacheAccessTime",  DateUtil.dateDisplay(getCache().get(key).getLastAccessTime()));
                cacheList.add(cacheMap);
            }
       
        try {
            super.setGridData(cacheList, pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.FRU_00001, "获取缓存内容出现异常！" + e.getCause().getMessage(), false);
        }
        return pageInfo;
    }
    @Override
    public Object getCacheInfo() throws ServiceException {
        Map<String,Object> cacheInfo = new HashMap<String, Object>();
        if(getCache() == null){
            return cacheInfo;
        }
        List<String> keys = getCache().getKeys();
        long totalSize = 0;
        for (String key : keys) {
            totalSize += getCache().get(key).getSerializedSize();
        }
        cacheInfo.put("CacheTotalSize", bytes2kb(totalSize));
        cacheInfo.put("TimeToLiveSeconds",  getCache().getCacheConfiguration().getTimeToLiveSeconds()+"秒");
        cacheInfo.put("TimeToIdleSeconds",   getCache().getCacheConfiguration().getTimeToIdleSeconds()+"秒");
        cacheInfo.put("CacheNum", getCache().getSize());
        cacheInfo.put("DiskSpoolBufferSizeMB", getCache().getCacheConfiguration().getDiskSpoolBufferSizeMB()+"MB");
        cacheInfo.put("CacheLoaderTimeoutMillis", getCache().getCacheConfiguration().getCacheLoaderTimeoutMillis());
        cacheInfo.put("DiskAccessStripes", getCache().getCacheConfiguration().getDiskAccessStripes());
        cacheInfo.put("DiskExpiryThreadIntervalSeconds", getCache().getCacheConfiguration().getDiskExpiryThreadIntervalSeconds());
        cacheInfo.put("MaxBytesLocalHeap", bytes2kb(getCache().getCacheConfiguration().getMaxBytesLocalHeap()));
        return cacheInfo;
    }
    @Override
    public void clearCache( String keys) throws ServiceException {
        //根据key清除缓存
       String [] keyList = keys.split(",");
       for(String key : keyList){
           getCache().remove(key);
       }
       //getCache().removeQuiet(keyList);//本级不同步其他服务器
    }

    @Override
    public void clearAllCache() throws ServiceException {
        getCache().removeAll();
    }
    /**.
     * byte根据长度转成kb和mb
     * @param bytes
     * @return
     */
    private static String bytes2kb(long bytes) {
        BigDecimal filesize = new BigDecimal(bytes);
        BigDecimal megabyte = new BigDecimal(1024 * 1024);
        float returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP)
                .floatValue();
        if (returnValue > 1)
            return (returnValue + "MB");
        BigDecimal kilobyte = new BigDecimal(1024);
        returnValue = filesize.divide(kilobyte, 2, BigDecimal.ROUND_UP)
                .floatValue();
        return (returnValue + "KB");
    }
}