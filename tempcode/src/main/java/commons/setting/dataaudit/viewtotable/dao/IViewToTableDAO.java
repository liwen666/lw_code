/**
 * @Title: IViewToTableDAO.java
 * @Copyright (C) 2015 太极华青
 * @Description: 通用设置功能数据服务接口
 * @Revision 6.0 2015-11-23  ZhengQ
 */

package commons.setting.dataaudit.viewtotable.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.setting.input.po.TreeNode;

/**
 * @ClassName: IViewToTableDAO
 * @Description: 视图对应物理表登记功能数据服务接口
 * @author: ZhengQ 2015-11-27 下午04:54:54
 */

public interface IViewToTableDAO extends SuperMapper {
    /**
     * . 根据viewID查询表信息
     * @param viewID 视图ID
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> getTableDataByViewID(@Param("viewID") String viewID);

    /**
     * . 保存viewID 和 tableid到关系表
     * @param viewID 视图ID
     * @param tableID 物理表ID
     */
    void saveViewToTab(@Param("viewID") String viewID, @Param("tableID") String tableID);

    /**
     * . 根据viewID tableid 删除在关系表中的数据
     * @param viewID 视图ID
     * @param tableID 物理表ID
     */
    void delViewToTab(@Param("viewID") String viewID, @Param("tableID") String tableID);

    /**
     * . 保存条件
     * @param viewID 视图ID
     * @param tableID 物理表ID
     * @param tabWhere 条件
     */
    void updViewToTab(@Param("viewID") String viewID, @Param("tableID") String tableID,
                      @Param("tabWhere") String tabWhere);

    /**
     * . 校验条件
     * @param con 条件
     * @param tableID 物理表ID
     * @return Integer
     */
    Integer vetifyCon(@Param("con") String con, @Param("dbTableName") String dbTableName);

    /**
     * . 获取所有套标下的视图
     * @param tableType 表类型
     * @return List<TreeNode>
     */
    List<TreeNode> getViewTree(@Param("tableType") String tableType);
}
