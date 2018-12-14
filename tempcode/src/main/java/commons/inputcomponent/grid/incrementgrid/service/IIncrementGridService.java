/**
 * @Title: IIncrementGridService.java
 * @Copyright (C) 2016 太极华青
 * @Description:
 * @Revision 1.0 2016-1-4  CAOK
 */
 

package commons.inputcomponent.grid.incrementgrid.service;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.grid.commonGrid.service.ICommonGridService;
import com.tjhq.commons.inputcomponent.po.Table;

/**
 * @ClassName: IIncrementGridService
 * @Description: Description of this class
 * @author: CAOK 2016-1-4 上午10:12:10
 */

public interface IIncrementGridService extends ICommonGridService {
    
    void backUpData(String tableID, String bakWhere) throws ServiceException;
    
    Object getBackupData(Table table) throws ServiceException;
    
}
