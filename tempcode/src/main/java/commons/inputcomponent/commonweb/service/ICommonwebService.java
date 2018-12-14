/**
 * @Title: ICommonwebService.java
 * @Copyright (C) 2015 太极华青
 * @Description: 通用录入底层类接口
 * @Revision 6.0 2015-11-26  CAOK
 */

package commons.inputcomponent.commonweb.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.exception.ServiceException;

/**
 * @ClassName: ICommonwebService
 * @Description: 通用录入底层类接口
 * @author: CAOK 2015-11-26 下午02:12:33
 */

public interface ICommonwebService {

    /**
     * . 加载引用列数据
     * @param columnID 引用列ID
     * @param superGuid 引用代码表父级数据ID
     * @param dynaParams 动态参数
     * @param format 显示类型
     * @return 引用结果
     * @throws ServiceException 业务异常
     */
    Object getReferenceData(String columnID, String superGuid, Map<String, String> dynaParams, String format)
            throws ServiceException;

    /**
     * . 获得关联表数据
     * @param tableID 表ID
     * @param columnID 数据列ID
     * @param superGuid 引用代码表父级数据ID
     * @param relatedID 条件列ID
     * @param value 条件列值
     * @param dynaParams 动态参数
     * @param format 显示类型
     * @return 引用结果
     * @throws ServiceException 业务异常
     */
    Object getAssociationData(String tableID, String columnID, String superGuid, String relatedID, String value,
                              Map<String, String> dynaParams, String format) throws ServiceException;

    /**
     * . 通用查询接口
     * @param sql 查询SQL
     * @return 查询结果
     * @throws ServiceException 业务异常
     */
    List<Map<String, Object>> queryForList(String sql) throws ServiceException;
    
    /**.
     * 获取表格默认值
     * @param tableID
     * @return
     * @throws ServiceException
     * @throws
     */
    Map<String, Object> getTableDefaultValue(String tableID) throws ServiceException;
}
