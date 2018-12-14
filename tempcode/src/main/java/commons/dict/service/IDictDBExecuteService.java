package commons.dict.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.dict.external.po.ConsistencyPO;
import com.tjhq.commons.dict.external.po.DictTDefaultcolPO;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTFactorcodePO;
import com.tjhq.commons.dict.external.po.DictTModelPO;

/**
 * 物理表 接口 服务
 * 
 * @author xujingsi
 * 
 */
public interface IDictDBExecuteService {


	/**
	 * 创建视图
	 * 
	 * @param ispartition
	 *            是否分区
	 * @param dbStatus
	 *            分区状态
	 * @param tableName
	 *            表名
	 * @param viewColumMap
	 *            视图列map
	 * @param secusql
	 *            过滤语句
	 * @return
	 */
	public String createFactorcodeView(String ispartition, String dbStatus,
                                       String tableName, Map<String, List<DictTFactorcodePO>> viewColumMap,
                                       String secusql, boolean isAllYear);
	/**
	 * 创建物理表SQL
	 * 
	 * @param dtdList
	 *            缺省列列表
	 * @param tableName
	 *            物理表名
	 * @param name
	 *            中文表名
	 * @param dbStatus
	 *            分区状态
	 * @param ispartition
	 *            是否分区
	 * @param secusql
	 *            过滤SQL
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> createPhysicsTablecodeSql(
            List<DictTDefaultcolPO> dtdList, String tableName, String name,
            String dbStatus, String ispartition, String secusql) throws Exception;
	/**
	 * 创建物理表SQL
	 * 
	 * @param dtdList
	 *            缺省列列表
	 * @param tableName
	 *            物理表名
	 * @param name
	 *            中文表名
	 * @param dbStatus
	 *            分区状态
	 * @param ispartition
	 *            是否分区
	 * @param secusql
	 *            过滤SQL
	 * @param _bak
	 *            是否已经生成Bak表
	 * @param isTask
	 *            是否任务表
	 * @param isConfig
	 *            是否需要配置表
	 * @param isAllDistrict
	 *            是否全地区访问
	 * @param factorList
	 *            物理列列表
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> createPhysicsTableSql(
            List<DictTDefaultcolPO> dtdList, String tableName, String name,
            String dbStatus, String ispartition, String secusql, boolean _bak,
            boolean isTask, boolean isConfig, boolean isAllDistrict, boolean isAllYear,
            List<DictTFactorPO> factorList) throws Exception;

	/**
	 * 获取db 状态
	 * 
	 * @return
	 */
	public String getGlobalIsmultdb() throws Exception;

	/**
	 * 获取db中 是否存在 状态
	 * 
	 * @param sql
	 * @return Integer（0不存在 1 存在）
	 */
	public Integer getIfExistsInDB(String sql) throws Exception;

	/**
	 * 获取db中 UUID
	 * 
	 * @return
	 */
	public String getUUID() throws Exception;


	
	/**
	 * 创建视图
	 * @param ispartition   是否分区
	 * @param dbStatus      分区状态
	 * @param tableName     表名
	 * @param viewColumMap  视图列map
	 * @param secusql       过滤语句
	 * @param isTask        是否任务表
	 * @param isConfig      是否配置变盘
	 * @param isAllDistrict 是否全地区访问
	 * @param isAllYear 是否全地区访问
	 * @return
	 */
	public String createView(String ispartition, String dbStatus,
                             String tableName, Map<String, List<DictTFactorPO>> viewColumMap,
                             String secusql, boolean isTask, boolean isConfig,
                             boolean isAllDistrict, boolean isAllYear);

	/**
	 * 创建业务对象BAK表视图
	 * 
	 * @param tableName
	 * @param viewColumMap
	 * @param secusql
	 * @param isTask
	 * @return
	 */
	public String createBakViewForUpdateView(String tableName,
                                             Map<String, List<DictTFactorPO>> viewColumMap, String secusql,
                                             boolean isTask);

	/**
	 * 获得需要修复到BAK表中的列信息集合
	 * 
	 * @param appId
	 * @param isTable
	 *            是否是物理表
	 * @return
	 */
	public List<Map> getNeedRecoverColsForBakTable(String appId, boolean isTable);

	/**
	 * 创建表分区 sql
	 * 
	 * @param dbStatus
	 *            数据库状态
	 * @param tableName
	 *            屋里表名称
	 * @return
	 */
	public String createPartition(String dbStatus, String tableName);

	/**
	 * 创建触发器
	 * 
	 * @param dbStatus
	 * @param tableName
	 * @return
	 */
	public String createTrigger(String dbStatus, String tableName,
                                boolean isTask);

	/**
	 * 创建物理主键
	 * 
	 * @param dbStatus
	 * @param tableName
	 * @return
	 */
	public String createPK(String dbStatus, String tableName,
                           List<String> listPK);

	/**
	 * 创建index索引
	 * 
	 * @param dbStatus
	 * @param tableName
	 * @return
	 */
	public String createIndex(String dbstatus, String tableName,
                              List<String> listIndex);

	/**
	 * 查询数据库中 某视图的字段
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
	 * 执行sql with Exception
	 * 
	 * @param sql
	 * @throws Exception
	 */
	public void ExecDllLongForParam(String sql) throws Exception;

	/**
	 * 创建 可更新试图 触发器
	 * 
	 * @param viewName
	 * @param settingMap
	 * @param trgs
	 * @param settingmap
	 * @param trg_as
	 * @param factorType
	 * @return
	 */
	/*
	 * public String createTriggerForUpdateView(String viewName, Map<String,
	 * String> setMap, Map<String, Map<String, String>> trgs, Map<String,
	 * Map<String, String>> settingmap, Map<String, String> trg_as, Map<String,
	 * DictTFactorPO> factorType);
	 */
	/**
	 * 创建 视图 and 触发器 for updateView
	 * 
	 * @param viewName
	 * @param dictTModel
	 * @param map
	 * @param settingList
	 * @return
	 */
	public Map<String, String> createViewTriggerForUpdataView(String viewName,
                                                              DictTModelPO dictTModel, Map<String, List<DictTFactorPO>> map,
                                                              List<Map<String, String>> settingList, String secusql, String status)
			throws Exception;

	/**
	 * 查询表中的数据 条数；
	 * 
	 * @param tableName
	 * @return int
	 * @throws Exception
	 */
	public int getTableDataCount(String tableName) throws Exception;

	/**
	 * 检查 数据库中 factor表的列 和真正P# 物理表的列 一致性 ；
	 * 
	 * @param tableName
	 *            指定某个表 （当值为“”或null 检查全部）；
	 * @return
	 * @throws Exception
	 */
	public List<ConsistencyPO> getConsistencyByArgs(String tableName)
			throws Exception;

	/**
	 * 刷新视图公式触发器
	 * 
	 * @param tableID
	 *            表ID
	 * @return 刷新视图公式触发器
	 * @throws Exception
	 */
	public void createFormulaTrigger(String tableID) throws Exception;

	/**
	 * 
	 /** 刷新表视图
	 * 
	 * @param tableID
	 *            表ID
	 * @return 刷新表视图
	 * @throws Exception
	 */
	public void recreateViews(String tableID) throws Exception;
}
