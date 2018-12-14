package commons.setting.external.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.setting.external.po.DictTSetAngleViewPO;
import com.tjhq.commons.setting.external.po.DictTSetBaseNumTab;
import com.tjhq.commons.setting.external.po.DictTSetFddefPO;
import com.tjhq.commons.setting.external.po.DictTSetFixPO;
import com.tjhq.commons.setting.external.po.DictTSetGroupPO;
import com.tjhq.commons.setting.external.po.DictTSetMainSubTab;
import com.tjhq.commons.setting.external.po.DictTSetQuerydPO;
import com.tjhq.commons.setting.external.po.DictTSetRefrelaPO;
import com.tjhq.commons.setting.external.po.DictTSetSortPO;
import com.tjhq.commons.setting.external.po.RECDetailPO;
import com.tjhq.commons.setting.external.po.RECPO;

/**
* 类名称：IEntryOuterService 
* 类描述：录入界面定义 
* 创建人：shishj
* 创建时间：Jan 26, 2014 2:09:04 AM
* @version 1.0
*/
public interface IEntryOuterService{
	/**
	 * 缓存相关key常量
	 */
	String INPUT = "HQ.COMM";//录入界面定义
	String ANGLEVIEW = "INPUT_ANGLEVIEW";//视角
	String GROUP = "INPUT_GROUP";//分组设置
	String SORT = "INPUT_SORT";//排序设置
	String REFRELA = "INPUT_REFRELA";//引用列关系定义
	String ANALY = "INPUT_ANALY";//查询（分析）引用定义
	String QUERYD = "INPUT_QUERYD";//查询条件设置
	String FDDEF = "INPUT_FDDEF";//浮动表设置
	String SETFDDEF = "INPUT_SETFDDEF";//浮动表整表设置
	String FIXED = "INPUT_FIXED";//固定行列表设置
	String SETFIXED = "INPUT_SETFIXED";//固定行列表整表设置
	String BASENUM = "INPUT_BASENUM";//基本数字表设置
	String SINREC = "INPUT_SINREC";//单记录表设置
	
	// year 、 version
	/**
	 * 根据数据库ID获取 系统ID 、系统名称
	 * @param dbid 
	 * @return
	 */
	//public List<DictTAppRegister> getAppOfView();

	/**
	 * 根据系统ID获取 系统下的套表,并根据套表ID获取 套表下的表
	 * @param appid
	 * @param suitid
	 * @return
	 */
	//public List<DictTSuit> getSuitByAppTree(String appID,String suitID);
	
	/**
	 * 根据表ID获取 表下的所有列
	 * @param tableID
	 * @return
	 */
	//public List<DictTFactor> getFactorByTableTree(String tableID);
	
		//********--------------------------------*********//	
	/**
	 * 根据 表ID 获取视角设置信息
	 * @param tableID
	 * @return List<DictTSetGroupPO>
	 */
	public List<DictTSetAngleViewPO> getDataAngleViewList(String tableID);	
	/**
	 * 根据 表ID 获取分组设置信息
	 * @param tableID
	 * @return List<DictTSetGroupPO> 分组设置信息
	 */
	public List<DictTSetGroupPO> getDataGroupList(String tableID);
	
	/**
	 * 根据 表ID 获取排序设置信息
	 * @param tableID
	 * @return List<DictTSetSortPO> 排序设置信息
	 */
	public List<DictTSetSortPO> getDataSortList(String tableID);
	
	/**
	 * 根据 表ID 获取引用列关系定义
	 * @param tableID
	 * @return List<DictTSetRefrelaPO> 引用列
	 */
	public List<DictTSetRefrelaPO> getDataRefrelaList(String tableID);
	
	/**
	 * 根据 tableID , columnID 获取 被关联列与关联列之间的对应 值集
	 * @param tableID
	 * @param columnID (被关联列)
	 * @return List<DictTSetRefrelaPO> 引用列值集
	 */
	public List<DictTSetRefrelaPO> getDataRefrelaByColumn(String tableID, String columnID);
	
	/**
	 * 根据 表ID 获取查询（分析）引用定义
	 * @param tableID
	 * @return List<Map> 生成查询路径，包含参数
	 */
	public List<Map<String,String>> getDataAnalyList(String tableID);
	
	/**
	 * 根据 表ID 获取查询条件设置
	 * @param tableID
	 * @return DictTSetQuerydPO 查询设置参数
	 */
	public DictTSetQuerydPO getDataQuerydList(String tableID);
	
	public String createQueryPage(String tableID);
	/**
	 * 根据 表ID 获取查询面板 行数
	 * @param tableID
	 * @return
	 */
	public int queryPanelLine(String tableID);
	
	/**
	 * 根据 表ID 获取浮动表设置
	 * 在业务表管理 新增界面 选择表处理类型为 “浮动表” 才能出现浮动表设置界面
	 * 不同的选项，显示方式不同
	 *	-- 是“其中项”，用黄色显示
	 *	-- 数据不可改，整行灰色，否则白色
	 *	-- 可增加下级名称前增加... 特殊标志
	 * @param tableID
	 * @return  DictTSetFddefPO
	 */
	public DictTSetFddefPO getDataFddefList(String tableID);
	
	
	// 需要获取 业务表中列 及 缺省列 生成表格
	
	/**
	 * 根据 表ID 获取固定行列表设置
	 * 在业务表管理 新增界面 选择表处理类型为 “固定行列表” 才能出现固定行列表设置界面
	 * 固定行列表 存在多个 FDcode
	 * @param tableID
	 * @return List<DictTSetFixPO>
	 */
	public List<DictTSetFixPO> getDataFixList(String tableID);
	
	/**
	 * 根据 类型ID 获取 该类型下的 主表与子表
	 * isMainTable 1、主表 0、子表
	 * List<DictTSetMainSubRela> mainSubRela 关系设置表
	 * @param collTypeID 采集类型ID  || BASEOUTPUT
	 * @return
	 */
	public List<DictTSetMainSubTab> getDataMainSubTabList(String collTypeID);
	
	/**
	 * 根据 表ID 基本数字表设置
	 * @param tableID
	 * @return DictTSetBaseNumTab
	 */
	public DictTSetBaseNumTab getDataBaseTabList(String tableID);
	
	/**
	 * 主从表关系设置 详细页面-主表（DICT_T_SETREC）
	 * @param map(objectId,tableId)
	 * @return
	 */
	public List<RECPO> getSetRECList(Map<String, String> map); 
	
	/**
	 * 主从表关系设置 详细页面-子表（DICT_T_SETRECDETAIL）
	 * @param recId
	 * @return
	 */
	public Map<String, List<RECDetailPO>> getSetRECDetailList(String recId);
	/**
	 * 获取单记录设置主表信息
	 */
	public List<RECPO> getSingleFormList(String tableId);

	/**
	 * 获取单记录设置明细表信息
	 * @return
	 */
	public Map<String, List<RECDetailPO>> getSinFormDetailList(String recId);
	
// ------------------- 项目库 阶段
	
	/**
	 * 根据 表ID | 阶段 获取分组设置信息
	 * @param tableID
	 * @param stage
	 * @return List<DictTSetGroupPO> 分组设置信息
	 */
	public List<DictTSetGroupPO> getDataGroupByStage(String tableID, String stage);
	
	/**
	 * 根据 表ID | 阶段 获取排序设置信息
	 * @param tableID
	 * @param stage
	 * @return List<DictTSetSortPO> 排序设置信息
	 */
	public List<DictTSetSortPO> getDataSortByStage(String tableID, String stage);
	
	/**
	 * 根据 表ID 获取引用列关系定义
	 * @param tableID
	 * @param stage
	 * @return List<DictTSetRefrelaPO>
	 */
	public List<DictTSetRefrelaPO> getDataRefrelaByStage(String tableID, String stage);
	
	/**
	 * 根据 表ID 获取查询（分析）引用定义
	 * @param tableID
	 * @param stage
	 * @return List<Map> 生成查询路径，包含参数
	 */
	public List<Map> getDataAnalyByStage(String tableID, String stage);
	
	/**
	 * 根据 表ID 获取查询条件设置
	 * @param tableID
	 * @return DictTSetQuerydPO 查询设置参数
	 */
	public DictTSetQuerydPO getDataQuerydByStage(String tableID, String stage);
	/**
	 * 判断表在CODE_T_COLLECTTYPE中是否存在
	 * @param tableDbName
	 * @return
	 */
	public boolean  isTabExits(String tableDbName);
}
