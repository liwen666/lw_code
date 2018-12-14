/**
 * @Title: ConditionSetController.java
 * @Copyright (C) 2015 太极华青
 * @Description: 固定行列表浮动表模板条件设置Mapper接口
 * @Revision 6.0 2015-11-23  ZhengQ
 */

package commons.setting.input.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.setting.input.po.ConditionSetPo;

/**
 * @ClassName: ConditionSetMapper
 * @Description: 固定行列表浮动表模板条件设置Mapper接口
 * @author: ZhengQ 2015-11-24 上午11:28:49
 */

public interface ConditionSetMapper extends SuperMapper {
    /**
     * 校验条件是否可以执行成功.
     * @param tableName 表名
     * @param condition 条件
     * @return 校验是否成功
     * @throws Exception 异常
     */
     Integer vertifyCon(@Param("tableName") String tableName, @Param("condition") String condition)
            throws Exception;

    /**
     * 保存模板条件设置 .
     * @param map 新增参数
     * @throws Exception 异常
     */
     void insertCon(Map<String, Object> map) throws Exception;

    /**
     * 修改模板条件设置 .
     * @param map 修改
     * @throws Exception 异常
     */
     void updateCon(Map<String, Object> map) throws Exception;

    /**
     * 删除模板设置条件.
     * @param tableID tableID
     * @throws Exception 异常
     */
     void deleteCon(@Param("tableID") String tableID) throws Exception;

    /**
     * 查询表中是否已经存在该表的设置条件.
     * @param tableID tableID
     * @return 模板条件PO
     */
     ConditionSetPo getCondition(@Param("tableID") String tableID);

    /**
     * 根据tableID获取tableNAME.
     * @param tableID tableID
     * @return 表名
     */
     String getTableName(@Param("tableID") String tableID);
}
