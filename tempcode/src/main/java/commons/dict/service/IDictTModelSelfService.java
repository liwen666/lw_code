package commons.dict.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.dict.external.po.DictElementPO;
import com.tjhq.commons.dict.external.po.DictTDefaultcolPO;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.inputcomponent.po.Grid;

/**
 * 表接口
 * 
 * @author xujingsi
 * 
 */
public interface IDictTModelSelfService {

	/**
	 * 添加 1物理表
	 * 
	 * @param dictTModel
	 */
	public void insertDictTModelForPhysics(DictTModelPO dictTModel)
			throws Exception;

	/**
	 * 添加 2（不可更新）视图
	 * 
	 * @param dictTModel
	 */
	public void insertDictTModelForNoUpdateView(DictTModelPO dictTModel)
			throws Exception;

	/**
	 * 添加 3 业务对象（可更新）视图
	 * 
	 * @param dictTModel
	 * @param sourceColumnList
	 * @param settingList
	 * @throws Exception
	 */
	public void insertDictTModelForUpdateView(DictTModelPO dictTModel,
                                              List<Map<String, String>> sourceColumnList,
                                              List<Map<String, String>> settingList) throws Exception;

	/**
	 * 删除 1物理表
	 * 
	 * @param String
	 *            tableid
	 */
	public void deleteDictTModelForPhysics(DictTModelPO dictTModel)
			throws Exception;

	/**
	 * 删除 2（不可更新）视图
	 * 
	 * @param String
	 *            tableid
	 */
	public void deleteDictTModelForNoUpdateView(DictTModelPO dictTModel)
			throws Exception;

	/**
	 * 删除 3（可更新）视图
	 * 
	 * @param String
	 *            tableid
	 */
	public void deleteDictTModelForUpdateView(DictTModelPO dictTModel)
			throws Exception;

	/**
	 * 添加表信息
	 * 
	 * @param dictTModel
	 * @param isAsync
	 */
	public void insertDictTModel(DictTModelPO dictTModel, boolean isAsync)
			throws Exception;

	/**
	 * 修改
	 * 
	 * @param dictTModel
	 */
	public void updateDictTModel(DictTModelPO newDtm) throws Exception;

	/**
	 * 删除
	 * 
	 * @param dictTModel
	 * @throws Exception
	 */
	public void deleteDictTModel(String tableid, boolean isAsync)
			throws Exception;

	/**
	 * 修改3（可更新）视图
	 * 
	 * @param dictTModel
	 */
	public void updateDictTModelForUpdateView(DictTModelPO dictTModel,
                                              List<Map<String, String>> sourceColumnList,
                                              List<Map<String, String>> settingList) throws Exception;

	/**
	 * 获取DictTModel grid 表头
	 * 
	 * @param tableid
	 * @return
	 */
	public Grid getDictTModelHead(String tableid);

	/**
	 * 获取某个套表下(包括子套)中的所有 DictTModel
	 * 
	 * @param suitid
	 *            套表ID
	 * @param childColumn
	 *            是否包含表中的 列（默认不带）
	 * @return List<DictTModel>
	 * 
	 */
	public List<DictTModelPO> getDictTModelsBySuitId(String suitid,
                                                     boolean childColumn);

	/**
	 * 查询 多条件
	 * 
	 * @param map
	 *            <"字段 "，“值”> 模糊 ("字段"+"_like");
	 * @return List<DictTModelPO>
	 */
	public List<DictTModelPO> getDictTModels(Map<String, Object> map);

	/**
	 * 更改物理表名称 for 物理表
	 * 
	 * @param newDBTableName新的表明称
	 * @param dictTModel
	 *            原来的表对象
	 */
	public void saveDictModleForTableName(String newDBTableName,
                                          DictTModelPO dictTModel) throws Exception;

	/**
	 * 更改物理表名称 for 可更新视图
	 * 
	 * @param newDBTableName新的表明称
	 * @param dictTModel
	 *            原来的表对象
	 */
	public void saveDictModleForTableNameForUpdateView(
            List<DictTFactorPO> dtfsList, String newDBTableName,
            DictTModelPO dictTModel, Map<String, List<DictTFactorPO>> map,
            List<Map<String, String>> settingList) throws Exception;

	/**
	 * 指定套表ID 多条件查询
	 * 
	 * @param suitid
	 * @param map多条件查询
	 * @param childColumn
	 * @return
	 */
	public List<DictTModelPO> getDictTModelsBySuitIdmap(String suitid,
                                                        Map<String, Object> map, boolean childColumn);

	/**
	 * 添加 物理备份表
	 * 
	 * @param dictTModel
	 */
	public void createDictTModelForBak(DictTModelPO dictTModel,
                                       List<DictTDefaultcolPO> dtdList) throws Exception;

	/**
	 * 创建 业务对象的备份表 _bak ;
	 * 
	 * @param dictTModel
	 * @throws Exception
	 */
	public void createDictTModelForUpdateViewBak(DictTModelPO dictTModel)
			throws Exception;

	/**
	 * 删除 物理备份表
	 * 
	 * @param dictTModel
	 */
	public void deleteTableForBak(DictTModelPO dictTModel) throws Exception;

	/**
	 * 复制 表列定义到指定年份
	 * 
	 * @param finyear
	 * @param tableData
	 */
	public String copyTableDefinitionToModel(String finyear,
                                             List<Map<String, String>> tableData);

	/**
	 * 
	 * @param needRecoverColsList
	 * @param needInsertColsIntoTable
	 * @throws Exception
	 */
	public void recoverBakTable(List<Map> needRecoverColsList,
                                boolean needInsertColsIntoTable) throws Exception;

	/**
	 * 下发功能的实现接口
	 */
	public String sendTablesToSubCity(String[] tableData, String[] citycodes)
			throws Exception;

	/**
	 * 得到平台数据元树
	 * 
	 * @return 平台数据元树
	 */
	public List<DictElementPO> getDataElementTreeNodes();

	/**
	 * 平台同步注册表
	 * 
	 * @param dtm
	 * @throws Exception
	 */
	public void registerDicTable(DictTModelPO dictTModel) throws Exception;

	/**
	 * 删除平台注册表
	 * 
	 * @param tableDbName
	 * @throws Exception
	 */
	public void deleteRegisterDicTable(String tableDbName) throws Exception;

	/**
	 * 根据来源表ID取到业务对象来源表列
	 * 
	 * @param map
	 *            参数Map
	 * @return 来源表列集合
	 */
	public List<DictTFactorPO> getDictTFactorByFromTableID(
            Map<String, String> map);

	/**
	 * 
	 * 复制创建业务表
	 * 
	 * @param sourceTableID
	 *            源表ID
	 * @param dtm
	 *            新建业务表对象
	 * 
	 */
	public void replicateDictTModelForPhysics(String sourceTableID,
                                              DictTModelPO dtm) throws Exception;

	/**
	 * 增加物理表列注释
	 * 
	 * @param dictTModel
	 *            物理表对象
	 * @throws Exception
	 */
	public void addColumnComment4Table(DictTModelPO dictTModel)
			throws Exception;
}
