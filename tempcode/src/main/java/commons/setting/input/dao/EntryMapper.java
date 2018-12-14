package commons.setting.input.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.setting.input.po.DictTSetAnalyPO;
import com.tjhq.commons.setting.input.po.DictTSetBaseNumSub;
import com.tjhq.commons.setting.input.po.DictTSetBaseNumTab;
import com.tjhq.commons.setting.input.po.DictTSetFddefPO;
import com.tjhq.commons.setting.input.po.DictTSetFixPO;
import com.tjhq.commons.setting.external.po.DictTSetGroupPO;
import com.tjhq.commons.setting.input.po.DictTSetHrefParmPO;
import com.tjhq.commons.setting.external.po.DictTSetAngleViewPO;
import com.tjhq.commons.setting.external.po.DictTSetQuerydDetPO;
import com.tjhq.commons.setting.external.po.DictTSetQuerydPO;
import com.tjhq.commons.setting.external.po.DictTSetRefrelaDataPO;
import com.tjhq.commons.setting.external.po.DictTSetRefrelaPO;
import com.tjhq.commons.setting.external.po.DictTSetSortPO;
import com.tjhq.commons.setting.input.po.TreeNode;

public interface EntryMapper extends SuperMapper{
	
	/**
	 * 根据 表ID 获取视角设置信息
	 * @param map<tableID,stage>
	 * @return
	 */
	public List<DictTSetAngleViewPO> getDataAngleViewList(Map<String, Object> map);
	/**
	 * 根据 表ID 获取分组设置信息
	 * @param map<tableID,stage>
	 * @return
	 */
	public List<DictTSetGroupPO> getDataGroupList(Map<String, Object> map);
	/**
	 * 根据 表ID 获取排序设置信息
	 * @param map<tableID,stage>
	 * @return
	 */
	public List<DictTSetSortPO> getDataSortList(Map<String, Object> map);
	
	/**
	 * 根据 表ID 获取引用列关系定义
	 * @param map<tableID,stage>
	 * @return
	 */
	public List<DictTSetRefrelaPO> getDataRefrelaList(Map<String, Object> map);
	
	/**
	 * 获取关联列、被关联列 所引用的物理代码表
	 * @param map
	 * @return
	 */
	public String getRefrelaDbTable(Map<String, Object> map); //获取表名
	public List<TreeNode> getRefrelaDbTableTree(Map<String, Object> map);//获取代码表数据
	/**
	 * 获取已设置的关系树
	 * @param map
	 * @return
	 */
	List<TreeNode> getRefrelaTree(Map<String, Object> map);

	public List<TreeNode> getSubCityTreeNodes(Map<String, Object> map);//获得当前登录用户所在地区的下属地区数据集

	/**
	 * 根据 物理表名 获取关联列、被关联列 的引用数据
	 * @param map
	 * @return
	 */
	public List<DictTSetRefrelaDataPO> getRefrelaDbTableData(Map<String, Object> map);
	
	/**
	 * 根据 表ID 获取查询（分析）引用定义
	 * @param map<tableID,stage>
	 * @return
	 */
	public List<DictTSetAnalyPO> getDataAnalyList(Map<String, Object> map);
	//获取查询（分析）详细
	public List<DictTSetHrefParmPO> selectHrefParm(String hrefParmID);
	
	/**
	 * 根据 表ID 获取查询条件设置
	 * @param map<tableID,stage>
	 * @return
	 */
	public List<DictTSetQuerydPO> getDataQuerydList(Map<String, Object> map);
	//查询条件 详细
	public List<DictTSetQuerydDetPO> selectQuerydDet(Map<String, Object> map);
	/**
	 * 根据 表ID 获取浮动表设置
	 * @param tableID
	 * @return
	 */
	public DictTSetFddefPO getDataFddefList(String tableID);
		
	/**
	 * 根据 表ID 获取固定行列表设置
	 * @param tableID
	 * @return
	 */
	public List<DictTSetFixPO> getDataFixList(String tableID);
	
	public DictTSetFixPO getDataFixByTypeList(@Param("tableID") String tableID, @Param("typeID") String typeID);
	//不包含 当前 typeID 的 fdCodeToCols
	public String getOtherToCols(@Param("tableID") String tableID, @Param("typeID") String typeID);
	
	/**
	 * 创建关系引用定义 物理表
	 * @param map("paramSQL")
	 * @return
	 */
	public Integer createRelaTab(Map<String, Object> map);

	/**
	 * 根据 表ID 基本数字表设置
	 * @param tableID
	 * @return DictTSetBaseNumTab
	 */
	public DictTSetBaseNumTab getDataBaseTabList(String tableID);
	//通过 主表columnID 查询
	public List<DictTSetBaseNumSub> getDataBaseSubByColumnID(
            @Param("columnID") String columnID, @Param("csTBName") String csTBName);
	//通过 主表tableID 查询
	public List<DictTSetBaseNumSub> getDataBaseSubByTableID(
            @Param("tableID") String tableID, @Param("csTBName") String csTBName);
	// 查看该表中是否存在数据
	public int getDataCount(Map<String, String> map);
	public int getDataCount2(Map<String, String> map);

	/**
	 * 查看该列是否在引用列关系定义中被设定
	 */
	public int getDataRefrelaCount(Map<String, String> map);
}
