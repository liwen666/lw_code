/**
 * @Title: IComponentService.java
 * @Copyright (C) 2015 太极华青
 * @Description: 通用录入底层类接口
 * @Revision 6.0 2015-11-23  CAOK
 */
package commons.inputcomponent.component.service;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.po.Table;

/**
 * @ClassName: IComponentService
 * @Description: 表格对外接口
 * @author: CAOK 2015-11-23 下午06:03:46
 */

public interface IComponentService {


    /**.
     * 初始化表结构
     * @param table 表格对象
     * @param userID  用户ID
     * @return 设置完表格信息的对象
     * @throws ServiceException 业务异常
     */
    Table initStructure(Table table, String userID) throws ServiceException;

    /**.
     * 取业务数据
     * @param table 表格对象
     * @return 数据对象
     * @throws ServiceException 业务异常
     */
    Object getData(Table table) throws ServiceException;

    /**.
     * 保存表格数据
     * @param table 表格对象
     * @return 是否保存成功
     * @throws ServiceException 业务异常
     * @throws
     */
    boolean saveData(Table table) throws ServiceException;

}
