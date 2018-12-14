/**
 * @Title: IViewToTableService.java
 * @Copyright (C) 2015 太极华青
 * @Description: 通用设置功能接口
 * @Revision 6.0 2015-11-23  ZhengQ
 */

package commons.setting.dataaudit.viewtotable.service;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.Table;

/**
 * @ClassName: IViewToTableService
 * @Description: 视图对应物理表登记功能接口
 * @author: ZhengQ 2015-11-27 下午04:58:08
 */

public interface IViewToTableService {
    /**
     * . 根据tableType查询所有物理表 或是 视图 ‘1’物理表 ‘2’ 视图 ‘3’业务对象
     * @param tableType 表类型
     * @param viewID 视图ID
     * @return Object
     * @throws ServiceException 业务异常
     */
    Object getModelTree(String tableType, String viewID) throws ServiceException;

    /**
     * . 获取表头
     * @return Table对象
     * @throws ServiceException 业务异常
     */
    Table getTableDefine() throws ServiceException;

    /**
     * . 获取表数据
     * @param grid Grid对象
     * @return Object
     * @throws ServiceException 业务异常
     */
    Object getData(Grid grid) throws ServiceException;

    /**
     * . 保存视图id与表id到关系表
     * @param viewID 视图ID
     * @param tableIDs 表ID
     * @return boolean
     *@throws ServiceException 业务异常
     */
    boolean saveViewToTab(String viewID, String tableIDs) throws ServiceException;

    /**
     * . 删除在关系表中的数据
     * @param grid Grid表对象
     * @return boolean
     * @throws ServiceException 业务异常
     */
    boolean updateViewToTab(Grid grid) throws ServiceException;
}
