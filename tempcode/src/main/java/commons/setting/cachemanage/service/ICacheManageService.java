package commons.setting.cachemanage.service;

import java.util.List;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.setting.external.po.TreeNode;

/**
 * @ClassName: ICacheManageService
 * @Description: 缓存管理接口
 * @author: ZhengQ 2016-12-14 上午11:32:18
 */

public interface ICacheManageService {
     /**.获取集群环境内所有的IP
     * @return
     * @throws ServiceException
     * @throws
     */
    List<TreeNode> getIpTree() throws ServiceException;
    
    /**.获取表头
     * @return
     * @throws ServiceException
     * @throws
     */
    Table getTableHead() throws ServiceException;
    
    /**.获取缓存信息element
     * @return
     * @throws ServiceException
     * @throws
     */
    Object getTableData() throws ServiceException;
    /**.获取缓存信息
     * @return
     * @throws ServiceException
     * @throws
     */
    Object getCacheInfo() throws ServiceException;
    
    /**.根据key清空某个服务器的缓存
     * @param ip
     * @param keys
     * @throws ServiceException
     * @throws
     */
    void clearCache(String keys) throws ServiceException;
    
    /**.清空全部缓存
     * @throws ServiceException
     * @throws
     */
    void clearAllCache() throws ServiceException;
}
