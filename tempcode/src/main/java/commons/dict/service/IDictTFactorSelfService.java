package commons.dict.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Grid;

/**
 * 列接口
 * 
 * @author xujingsi
 * 
 */
public interface IDictTFactorSelfService {

	/**
	 * 添加 标题列
	 * 
	 * @param dictTFactor
	 */
	public void insertDictTFactorForTitle(DictTFactorPO dictTFactor)
			throws Exception;

	/**
	 * 删除 标题列
	 * 
	 * @param columnid
	 * @param
	 */
	public void deleteDictTFactorForTitle(String columnid) throws Exception;

	public void initNewFactor(DictTFactorPO dictTFactor, DictTModelPO dictTModel)
			throws Exception;

	/**
	 * 添加 物理表的列
	 * 
	 * @param dictTFactor
	 * @param dictTModel
	 */
	public void insertDictTFactorForPhysics(DictTFactorPO dictTFactor,
                                            DictTModelPO dictTModel, List<Map<String, Object>> listInsert)
			throws Exception;

	/**
	 * 数据库 添加 列
	 * 
	 * @param tableDBName
	 * @param dictTFactor
	 * @param isAsync
	 * @throws Exception
	 */
	public void insertDictTFactor(String tableDBName,
                                  DictTFactorPO dictTFactor, boolean isAsync) throws Exception;

	/**
	 * 数据库 修改 列
	 * 
	 * @param dictTFactor
	 * @param isAsync
	 * @throws Exception
	 */
	public void updateDictTFactor(DictTFactorPO dictTFactor, boolean isAsync)
			throws Exception;

	/**
	 * 数据库 删除 列
	 * 
	 * @param tableDBName
	 * @param dictTFactor
	 * @param isAsync
	 * @throws Exception
	 */
	public void deleteDictTFactor(String tableDBName,
                                  DictTFactorPO dictTFactor, boolean isAsync) throws Exception;

	/**
	 * 修改 物理表的列
	 * 
	 * @param dictTFactor
	 */
	public void updateDictTFactorForPhysics(DictTFactorPO oldDictTFactor,
                                            DictTFactorPO newDictTFactor, DictTModelPO dictTModel)
			throws Exception;

	/**
	 * 修改列 可更新 视图
	 * 
	 * @param dictTFactor
	 */
	public void updateDictTFactorForUpdateView(DictTFactorPO oldDictTFactor,
                                               DictTFactorPO newDictTFactor, DictTModelPO dictTModel)
			throws Exception;

	/**
	 * 删除 物理表 的列
	 * 
	 * @param dictTFactor
	 * @param dictTModel
	 */
	public void deleteDictTFactorForPhysics(DictTFactorPO dictTFactor,
                                            DictTModelPO dictTModel) throws Exception;

	/**
	 * 删除列 可更新 视图
	 * 
	 * @param dictTFactor
	 */
	public void deleteDictTFactorForUpdateView(DictTFactorPO dictTFactor,
                                               DictTModelPO dictTModel) throws Exception;

	/**
	 * 
	 * @param tableid
	 * @return
	 */
	public Grid getDictTFactorHead(String tableid);

	/**
	 * 
	 * @param tableid
	 * @return
	 */
	public Grid getColumnDataHead(String tableid);

	/**
	 * 
	 * @param tableid
	 * @return
	 */
	public Grid getSettingColumnDataHead(String tableid,
                                         Map<String, List<DictTFactorPO>> map);

	public List<DictTFactorPO> getSettingColumnChangeData(String tableid,
                                                          Map<String, List<DictTFactorPO>> map);

	/**
	 * 获取制定表中列 的最大ORDERID
	 * 
	 * @param tableid
	 * @return
	 */
	public Integer getMAXColumnOrderid(Map<String, Object> map);

	public List<DictTFactorPO> getSettingFinddata(List<Map<String, String>> list);

	public String copyColumnDefinitionToFactor(String finyear,
                                               List<Map<String, String>> factorData);

	/**
	 * 平台同步删除注册列
	 * 
	 * @param tableDBName
	 * @param dictTFactorPO
	 * @throws Exception
	 */
	public void deleteRegisterDicColumn(String tableDBName,
                                        DictTFactorPO dictTFactorPO) throws Exception;

	/**
	 * 刷新factor的levelID
	 * 
	 * 
	 * @param tableID
	 */
	public void updateDictTFactorLevelNo(String tableID);

	/**
	 * 增加列 同步 备份表
	 */
	public void createDictTFactorForBak(DictTModelPO dictTModel,
                                        DictTFactorPO dictTFactor, boolean needInsertColsIntoTable)
			throws Exception ;
}
