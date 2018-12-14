/**
 * @Title: IExceptionManageService.java
 * @Copyright (C) 2015 太极华青
 * @Description: 通用设置功能接口
 * @Revision 6.0 2015-11-23  ZhengQ
 */
package commons.setting.exception.service;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.Table;

/**
 * @ClassName: IExceptionManageService
 * @Description: 异常管理功能接口
 * @author: ZhengQ 2015-11-26 上午11:12:37
 */

public interface IExceptionManageService {
    /**.
     * 获取异常管理表表头
     * @return Table对象
     * @throws ServiceException 异常
     * @throws
     */
     Table getTableDefine() throws ServiceException;

     /**.
      * 获取异常管理表数据
     * @param grid Grid对象
     * @return Object
     * @throws ServiceException 取数异常
     * @throws
     */
    Object getTableData(Grid grid) throws ServiceException;

    /**.
     * 保存表格数据
     * @param table 表格对象
     * @return boolean
     * @throws ServiceException 保存数据时异常
     */
     boolean saveData(Table table) throws ServiceException;
}
