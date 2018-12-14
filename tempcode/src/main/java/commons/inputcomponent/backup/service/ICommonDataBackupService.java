/**
 * @Title: ICommonDataBackupService.java
 * @Copyright (C) 2016 太极华青
 * @Description:
 * @Revision 1.0 2016-1-7  CAOK
 */
 

package commons.inputcomponent.backup.service;

import com.tjhq.commons.exception.ServiceException;

/**
 * @ClassName: ICommonDataBackupService
 * @Description: Description of this class
 * @author: CAOK 2016-1-7 上午09:26:28
 */

public interface ICommonDataBackupService {
    
    /**.
     * @param tableID 备份表ID
     * @param bakWhere 备份数据过滤条件
     * @throws ServiceException 业务异常
     */
    void backupData(String tableID, String bakWhere) throws ServiceException;
    
    /**.
     * @param tableID 备份表ID
     * @param bakWhere 备份数据过滤条件
     * @param bakType 数据备份类型
     * @throws ServiceException 业务异常
     */
    void backupData(String tableID, String bakWhere, String bakType) throws ServiceException;
}
