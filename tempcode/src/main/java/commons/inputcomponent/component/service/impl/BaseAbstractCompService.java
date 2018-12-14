/**
 * @Title: BaseAbstractCompService.java
 * @Copyright (C) 2015 太极华青
 * @Description: 通用录入底层类
 * @Revision 6.0 2015-11-23  CAOK
 */
package commons.inputcomponent.component.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.component.service.IComponentService;
import com.tjhq.commons.inputcomponent.po.Table;

/**
 * @ClassName: BaseAbstractCompService
 * @Description: 抽象基类，此类只对表格的行为进行抽象
 * @author: CAOK 2015-11-23 下午06:06:07
 */
@Service("abstractCompService")
@Transactional(readOnly = true)
public abstract class BaseAbstractCompService implements IComponentService {

    /**.
     * <p>Title: initStructure</p>
     * <p>Description: 初始化表结构 </p>
     * @param table 表格对象
     * @param userID 用户ID
     * @return 设置完成的表格对象
     * @throws ServiceException 业务异常
     * @see com.tjhq.commons.inputcomponent.component.service.IComponentService#initStructure
     *         (com.tjhq.commons.inputcomponent.po.Table, java.lang.String)
     */
    public Table initStructure(Table table, String userID) throws ServiceException {
        setTableDefine(table);
        setTableSecu(table, userID);
        setTableFormula(table);
        setTableRelatedColumns(table);
        return table;
    }

    /**.
     * 设置表定义信息
     * @param table 表格对象
     * @throws ServiceException 业务异常
     */
    protected abstract void setTableDefine(Table table) throws ServiceException;

    /**.
     * 设置表权限
     * @param table 表格对象
     * @param userID 用户ID
     * @throws ServiceException 业务异常
     */
    protected abstract void setTableSecu(Table table, String userID) throws ServiceException;

    /**.
     * 设置表公式信息
     * @param table 表格对象
     * @throws ServiceException 业务异常
     */
    protected abstract void setTableFormula(Table table) throws ServiceException;

    /**.
     * 设置表引用列信息
     * @param table 表格对象
     * @throws ServiceException 业务异常
     */
    protected abstract void setTableRelatedColumns(Table table) throws ServiceException;

    /**.
     * <p>Title: getData</p>
     * <p>Description: 获取表格数据</p>
     * @param table 表格对象
     * @return 表数据
     * @throws ServiceException 业务异常
     * @see com.tjhq.commons.inputcomponent.component.service.IComponentService#getData
     *         (com.tjhq.commons.inputcomponent.po.Table)
     */
    public abstract Object getData(Table table) throws ServiceException;

    /**.
     * <p>Title: saveData</p>
     * <p>Description: 保存数据</p>
     * @param table 表格对象
     * @return 是否保存成功
     * @throws ServiceException 异常代码
     * @see com.tjhq.commons.inputcomponent.component.service.IComponentService#saveData
     *       (com.tjhq.commons.inputcomponent.po.Table)
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public boolean saveData(Table table) throws ServiceException {

        // 删除数据
        deleteData(table);

        // 校验数据正确性与完整性
        if (!validateData(table)) {
            return false;
        }

        // 修改数据
        updateData(table);
        // 新增数据
        addData(table);
        // 保存后执行方法
        afterSaveData(table);

        return true;
    }

    /**.
     * 保存数据后执行
     * @param table 表格对象
     * @throws ServiceException 业务异常
     */
    protected abstract void afterSaveData(Table table) throws ServiceException;

    /**.
     * 新增数据
     * @param table 表格对象
     * @throws ServiceException 业务异常
     */
    protected abstract void addData(Table table) throws ServiceException;

    /**.
     * 删除数据
      * @param table 表格对象
     * @throws ServiceException 业务异常
     */
    protected abstract void deleteData(Table table) throws ServiceException;

    /**.
     * 修改数据
     * @param table 表格对象
     * @throws ServiceException 业务异常
     */
    protected abstract void updateData(Table table) throws ServiceException;

    /**.
     * 验证数据 校验非空，正则，逻辑主键重复
     * @param table 表格对象
     * @throws ServiceException 业务异常
     * @return 是否保存成功
     */
    protected boolean validateData(Table table) throws ServiceException {
        return validateNullAndReg(table);
    }

    /**.
     * 验证数据 校验非空，正则，
      *@param table 表格对象
     * @throws ServiceException 业务异常
     * @return 是否保存成功
     */
    protected abstract boolean validateNullAndReg(Table table) throws ServiceException;

    /**.
     * 验证数据 逻辑主键重复
      * @param table 表格对象
     * @throws ServiceException 业务异常
     * @return 是否保存成功
     */
    protected abstract boolean validateLkRepeat(Table table) throws ServiceException;

    /**.
      * @param table 表格对象
     * @throws ServiceException 业务异常
     * @return 是否保存成功
     */
    protected boolean isHasRepeatData(Table table) throws ServiceException {
        return false;
    }
}
