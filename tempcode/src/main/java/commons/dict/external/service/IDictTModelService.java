package commons.dict.external.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.dict.external.po.DictTModelPO;

/**
 * 表接口
 * 
 * @author xujingsi
 * 
 */
public interface IDictTModelService {

	/**
	 * 获取单个DictTModel
	 * 
	 * @param tableid
	 *            表id
	 * @param childColumn
	 *            是否包含表中的 全部列 包括标题 List<DictTFactorPO> （默认不带）
	 * @return DictTModel
	 */
	public DictTModelPO getDictTModelByID(String tableid, boolean childColumn);

	/**
	 * 获取单个DictTModel（包含表中的全部列）
	 * 
	 * @param tableid
	 *            表id
	 * @param isleaf
	 *            是否末端节点List<DictTFactorPO>（默认全部）
	 * @return DictTModel
	 */
	public DictTModelPO getDictTModelOFID(String tableid, boolean isleaf);

	/**
	 * 获取单个DictTModel
	 * 
	 * @param dbtablename
	 *            物理表名称
	 * @param childColumn
	 *            是否包含表中的 列（默认不带）
	 * @return DictTModel
	 */
	public DictTModelPO getDictTModelByDBtableName(String dbtablename,
                                                   boolean childColumn);

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
	 * 获取某个套表下的所有 DictTModel （带用户ID）
	 * 
	 * @param suitid
	 *            套表ID
	 * @param userId
	 *            用户ID
	 * @param childColumn
	 *            是否包含表中的 列（默认不带）
	 * @return List<DictTModel>
	 */
	// public List<DictTModelPO> getDictTModelsBySuitIdAndUser(String
	// suitid,boolean childColumn,String userId);

	/**
	 * 获取DictTModel
	 * 
	 * @param name
	 * @return
	 */
	public List<DictTModelPO> getDictTModelsByName(String name);

	/**
	 * 获取DictTModel
	 * 
	 * @param type
	 *            (”1“ 物理表 “2” 视图 “3” 可更新试图)
	 * @return
	 */
	public List<DictTModelPO> getDictTModelsByTableType(String type);

	/**
	 * 获取全部 DictTModel
	 * 
	 * @param
	 * @return
	 */
	public List<DictTModelPO> getAllDictTModels();

	/**
	 * 通过tableID获取物理表，取出‘执行SQL’字段，进行数据库执行
	 */
	public void getProcessInitSQLByTableId(String tableid);

	/**
	 * 获取model名称和id
	 * 
	 * @param dealtype业务处理类型
	 * @param type
	 *            (”1“ 物理表 “2” 视图 “3” 可更新试图)
	 * @param appId
	 *            ("SPF"项目库"BGT"预算 )
	 * @return
	 */
	public Map<String, Object> getTableByDealtype(String dealtype, String type,
                                                  String appId);

	/**
	 * 批量同步全部注册平台表
	 * 
	 * @throws Exception
	 */
	public void registerAllTableToDic() throws Exception;

	/**
	 * 重新创建Config视图
	 * 
	 * @param tableID
	 *            业务表ID
	 * @throws Exception
	 */
	public void rereateConfigView(String tableID) throws Exception;

	/**
	 * 获取是否存在XX关系
	 * @param tableID
	 * @return
	 * @throws Exception
	 */
	 boolean getExistRela(String tableID) throws Exception;
}
