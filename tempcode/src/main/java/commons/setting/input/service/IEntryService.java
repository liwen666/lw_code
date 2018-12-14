package commons.setting.input.service;

import java.util.List;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.setting.external.po.DictTSetAngleViewPO;
import com.tjhq.commons.setting.external.po.DictTSetGroupPO;
import com.tjhq.commons.setting.external.po.DictTSetQuerydDetPO;
import com.tjhq.commons.setting.external.po.DictTSetQuerydPO;
import com.tjhq.commons.setting.external.po.DictTSetRefrelaPO;
import com.tjhq.commons.setting.external.po.DictTSetSortPO;
import com.tjhq.commons.setting.input.po.DictTSetAnalyPO;
import com.tjhq.commons.setting.input.po.DictTSetBaseNumSub;
import com.tjhq.commons.setting.input.po.DictTSetBaseNumTab;
import com.tjhq.commons.setting.input.po.DictTSetFddefPO;
import com.tjhq.commons.setting.input.po.DictTSetFixPO;
import com.tjhq.commons.setting.input.po.DictTSetHrefParmPO;
import com.tjhq.commons.setting.input.po.TreeNode;
/**
* 类名称：IEntryService  
* 类描述：录入界面定义接口类
* 创建人：shishj
* 创建时间：Jan 16, 2014 2:53:03 AM
* @version 1.0
 */
public interface IEntryService{	
	/**
	 * 根据 表ID 获取视角设置信息
	 * @param tableID
	 * @return List<DictTSetGroupPO>
	 */
	public List<DictTSetAngleViewPO> getDataAngleViewList(String tableID);
	/**
	 * 根据 表ID 获取分组设置信息
	 * @param tableID
	 * @return List<DictTSetGroupPO>
	 */
	public List<DictTSetGroupPO> getDataGroupList(String tableID);
	
	/**
	 * 根据 表ID 获取排序设置信息
	 * @param tableID
	 * @return List<DictTSetSortPO>
	 */
	public List<DictTSetSortPO> getDataSortList(String tableID);
	
	/**
	 * 根据 表ID 获取引用列关系定义
	 * @param tableID
	 * @return List<DictTSetGroupPO>
	 */
	public List<DictTSetRefrelaPO> getDataRefrelaList(String tableID);

	/**
	 * 获取引用表数据
	 * 			(获取数据的方法从下面的方法中提出，因下一方法使用表名获取到的SQLWhere不正确，不同系统下会存在同名代码表)
	 * @param tableID
	 * @param columnID
	 * @return
	 * @throws ServiceException
	 */
	List<TreeNode> getRefrelaTree(String tableID, String columnID) throws ServiceException;
	/**
	 * 获取已设置的关系树
	 * @author ZK
	 * @param tableID
	 * @param relaDbTab
	 * @param columnID
	 * @param condDataID
	 * @param rTable
	 * @return
	 * @throws ServiceException
	 */
	List<TreeNode> getRefrelaTree(String tableID, String relaDbTab, String columnID, String condDataID, String rTable) throws ServiceException;

	/**
	 * 获取关联列、被关联列 所引用的物理代码表名
	 * @param dbTableName
	 * @return 
	 */
	public String getRefrelaDbTableName(String tableID, String columnID);
	/**
	 * 根据物理代码表名 获取 引用表树
	 * @param dbTableName
	 * @return
	 */
	public List getRefrelaDbTableTree(String dbTableName) throws Exception;
	/**
	 * 获得当前登录用户所在地区的下属地区数据集
	 */
	public List<TreeNode> getSubCityTreeNodes(String dbTableName, String code) throws Exception;

	/**
	 * 根据 物理表名 获取关联列、被关联列 的引用数据
	 * @param relaDbTab 物理表名
	 * @param dataID 被关联列
	 * @param rightTable 关联列表名
	 * @return
	 */
	public List getRefrelaDbTableData(String relaDbTab, String dataID, String rightTable);

	/**
	 * 根据 表ID 获取查询（分析）引用定义
	 * @param tableID
	 * @return List<Map>
	 */
	public List<DictTSetAnalyPO> getDataAnalyList(String tableID);
	//查询（分析）引用 详细
	public List<DictTSetHrefParmPO> selectHrefParm(String hrefParmID);

	/**
	 * 根据 表ID 获取查询条件设置
	 * @param tableID
	 * @return
	 */
	public List<DictTSetQuerydPO> getDataQuerydList(String tableID);
	//查询条件 详细
	public List<DictTSetQuerydDetPO> selectQuerydDet(String tableID);

	/**
	 * 根据 表ID 获取浮动表设置
	 * 在业务表管理 新增界面 选择表处理类型为 “浮动表” 才能出现浮动表设置界面
	 * 不同的选项，显示方式不同
	 *	-- 是“其中项”，用黄色显示
	 *	-- 数据不可改，整行灰色，否则白色
	 *	-- 可增加下级名称前增加... 特殊标志
	 * @param tableID
	 * @return
	 */
	public DictTSetFddefPO getDataFddefList(String tableID);
	
	/**
	 * 根据 表ID 获取固定行列表设置
	 * 在业务表管理 新增界面 选择表处理类型为 “固定行列表” 才能出现固定行列表设置界面
	 * 固定行列表 存在多个 FDcode
	 * @param tableID
	 * @return
	 */
	public List<DictTSetFixPO> getDataFixList(String tableID);
	/**
	 * 根据 表ID  | 类型ID
	 * @param tableID
	 * @param typeID 类型，1表示fdcode_1,2表示fdcode_2,3表示fdcode_3
	 * @return
	 */
	public DictTSetFixPO getDataFixByTypeList(String tableID, String typeID);
	//不包含 当前 typeID 的 fdCodeToCols
	public String getOtherToCols(String tableID, String typeID);
	
	
	/**
	 * 创建关系引用定义 物理表
	 * @param relaDbTab 表名
	 * @return
	 */
	public String createRelaTab(String relaDbTab);

	/**
	 * 根据 表ID 基本数字表设置
	 * @param tableID
	 * @return DictTSetBaseNumTab
	 */
	public DictTSetBaseNumTab getDataBaseTabList(String tableID);
	//通过 主表guID 查询
	public List<DictTSetBaseNumSub> getDataBaseSubList(String columnID, String tableID);
	
	/**
	 * 根据tableID 查询 物理表名
	 * @param columnID
	 * @return
	 */
	
	public String getDbTableName(String tableID);
	/**
	 * 根据columnID  查询 物理列名
	 * @param columnID
	 * @return
	 */
	public String getDbColumnName(String columnID);
	
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
	 * 根据 表ID | 阶段 获取引用列关系定义
	 * @param tableID
	 * @param stage
	 * @return List<DictTSetRefrelaPO>
	 */
	public List<DictTSetRefrelaPO> getDataRefrelaByStage(String tableID, String stage);

	/**
	 * 根据 表ID | 阶段 获取查询（分析）引用定义
	 * @param tableID
	 * @param stage
	 * @return List<DictTSetAnalyPO>
	 */
	public List<DictTSetAnalyPO> getDataAnalyByStage(String tableID, String stage);
	
	/**
	 * 根据 表ID | 阶段 获取查询条件设置 DICT_T_SETQUERYD
	 * @param tableID
	 * @param stage
	 * @return
	 */
	public List<DictTSetQuerydPO> getDataQuerydByStage(String tableID, String stage);
	
	/**
	 * 根据 表ID | 阶段 获取查询条件 详细设置 DICT_T_SETQUERYDDET
	 * @param tableID
	 * @param stage
	 * @return
	 */
	public List<DictTSetQuerydDetPO> selectQuerydDetByStage(String tableID, String stage);

	/**
	 * 获取基本数字表当前年度是否包含数据
	 * @param tableID
	 * @param year
	 * @return
	 * @throws ServiceException
	 */
	public Integer getDataCount(String tableID, String year, String dbColumnName, String columnValue) throws ServiceException;
	
}
