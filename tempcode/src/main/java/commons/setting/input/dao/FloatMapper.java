package commons.setting.input.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.setting.input.po.DictTSetFddefPO;

public interface FloatMapper extends SuperMapper{
	
	/**
	 * 根据表ID，where条件查询数据
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getFloatData(Map<String, Object> map);
	/**
	 * 根据表ID查询代码表表名
	 * @param map
	 * @return
	 */
	public Map<String, String> getCodeTableInfoByColID(String TABLEID);

	/**
	 * 获取引用的代码表是否包含status字段
	 * @param tableID
	 * @return
	 */
	int getStatusByTableID(String tableID);
	/**
	 * 获取定义浮动列的代码表数据
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getCodeTableData(Map<String, Object> map);
	/**
	 * 删除浮动表内所有数据  
	 * @param map
	 * @return
	 */
	public Integer delAllFloatData(Map<String, Object> map);
	/**
	 * 向浮动表中插入数据
	 */
	public Integer setFloatData(Map<String, Object> data);
    /**
     * 修改模板数据
     */
    public void updateInitFloatData(Map<String, Object> data);
	/**
	 * 插入 整表设置
	 * @param formula
	 * @return
	 */
	public Integer insertFloatWhole(DictTSetFddefPO fddef);
	
	/**
	 * 更新 整表设置
	 * @param formula
	 * @return
	 */
	public Integer updateFloatWhole(DictTSetFddefPO fddef);
	/**
	 * 更新 colOrder 排序列
	 * @param dbTableName
	 * @param colOrder
	 * @param tableID
	 * @return
	 */
	public Integer updateFloatColOrder(@Param("dbTableName") String dbTableName, @Param("colOrder") String colOrder, @Param("tableID") String tableID);
	/**
	 * 更新浮动表 末级编码
	 * @param lists
	 * @return
	 */
	public Integer updateIsLeaf(@Param("list") List<Map<String, Object>> lists, @Param("DBTABLENAME") String dbTableName);
	/**
	 * 更新浮动表 末级编码
	 * @param statement SQL 更新
	 * @return
	 */
	public Integer updateIsLeafSql(@Param("statement") String statement);
	/**
	 * 设置 单元格权限
	 * @param dataKey
	 * @param renew
	 * @return
	 */
	public Integer saveCellSecu(@Param("dataKey") String dataKey, @Param("renew") String renew, @Param("DBTABLENAME") String dbTableName);
	
	/**
	 * 根据 DICT_T_SETFDDEF 表中 colOrder,整表单元格权限
	 * @param lists
	 * @return
	 */
	public Integer refreshCellSecu(@Param("list") List<Map<String, Object>> lists, @Param("DBTABLENAME") String dbTableName);
	
	/**
	 * 根据FDCODE | TABLEID 删除相应公式
	 * @param forWhere
	 * @param tableID
	 * @return
	 */
	public Integer deleteFormulaByFdCode(@Param("forWhere") String forWhere, @Param("tableID") String tableID);
	/**
	 * 根据FDCODE | TABLEID 查询相应公式
	 * @param forWhere
	 * @param tableID
	 * @return
	 */
	public Integer selectFormulaByFdCode(@Param("forWhere") String forWhere, @Param("tableID") String tableID);

	/**
	 * 根据templateID获取浮动表信息 
	 */
	public List<Map<String, Object>> getFloatDataByTemplateID(Map<String, Object> map);
	public Integer getResCount(@Param("tableName") String tableName);
	String getFloatDataByDatakey(@Param("tableName") String tableName, @Param("datakey") String datakey);
     void refreshAllData(@Param("tableID") String tableID);
}

