package commons.dict.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.dict.external.po.ConsistencyPO;

public interface DictDBExecuteMapper extends SuperMapper {

	/**
	 * <!-- 获取数据库状态位-->
	 */
	public void getGlobalIsmultdb(Map<String, String> map) throws Exception;

	/**
	 * 执行sql
	 */
	public String callExecDllLong(String dbSql) throws Exception;

	/**
	 * 执行sql 带返回错误信息
	 */
	public String callExecDllLongForParam(Map<String, String> map)
			throws Exception;

	/**
	 * 动态在factor 表中添加区域的分区表权限
	 * 
	 * @param map
	 * @throws Exception
	 */
	public void addPartition(Map<String, String> map) throws Exception;

	/**
	 * <!-- 查看数据库是否存在 0 不存在 1 存在 -->
	 */
	public void getIfExistsInDB(Map<String, Object> map) throws Exception;

	/**
	 * <!-- 查询视图的字段 -->
	 * 
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getColumnByViewName(String viewName)
			throws Exception;

	/**
	 * <!-- 查询物理表的PK的字段 -->
	 * 
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getTableColumnPK(String tableName)
			throws Exception;

	/**
	 * <!-- 查询物理表的数据的 条数 -->
	 * 
	 * @param tableName
	 * @return int
	 * @throws Exception
	 */
	public int getTableDataCount(@Param("tableName") String tableName)
			throws Exception;

	/**
	 * 检查 数据库中 factor表的列 和真正P# 物理表的列 一致性
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ConsistencyPO> getAllConsistency() throws Exception;

	/**
	 * 检查 数据库中 factor表的列 和真正P# 物理表的列 一致性 ； 指定 某个表 tableName
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ConsistencyPO> getConsistencyByTableName(
            @Param("tableName") String tableName) throws Exception;

	/**
	 * 获得需要修复到BAK表中的列信息集合
	 * 
	 * @param appId
	 * @return
	 */
	public List<Map> getNeedRecoverColsForBakTable(String appId);

	public List<Map> getNeedRecoverColsViewForBakTable(String appId);

	/**
	 * 刷新视图触发器
	 * 
	 * @param tableID
	 *            表ID
	 * @return 刷新视图触发器
	 * @throws Exception
	 */
	public void createFormulaTrigger(String tableID) throws Exception;
	
	/**
	 * 刷新表视图
	 * 
	 * @param tableID 表ID
	 * @return 刷新表视图
	 * @throws Exception
	 */
	public void recreateViews(@Param("tableID") String tableID) throws Exception;

}
