package commons.setting.input.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.setting.input.po.DictTSetMainSubRela;
import com.tjhq.commons.setting.input.po.TreeNode;


public interface MainSubRelaMapper extends SuperMapper{
	
	/**
	 * 获取采集类型表
	 * @return
	 */
	public List<TreeNode> getCollectTypeTreeList();
	
	/**
	 * 根据采集类型 查询数据
	 * @param collTypeID 采集类型ID
	 * @return
	 */
	public List<Map<String, Object>> getTableData(String collTypeID);
	
	
	/**
	 * 插入 数据
	 * @param lists
	 * @return
	 */
	public Integer insertTableData(List<Map<String, Object>> lists);
	
	/**
	 * 更新 数据
	 * @param lists
	 * @return
	 */
	public Integer updateTableData(List<Map<String, Object>> lists);

	/**
	 * 删除 数据
	 * @param lists
	 * @return
	 */
	public Integer deleteTableData(List<Map<String, Object>> lists);
	
	/**
	 * DictTSetMainSubRela || DictTSetMainSubTab
	 * @param mainSubID (外键)
	 * @return
	 */
	public List<DictTSetMainSubRela> selectMainSubRela(String mainSubID);

	/**
	 * 新增 主子表关系 数据
	 * @param relaMap
	 * @return
	 */
	public Integer insertMainSubRela(Map<String, Object> relaMap);
	/**
	 * 更新 主子表关系 数据
	 * @param relaMap
	 * @return
	 */
	public Integer updateMainSubRela(Map<String, Object> relaMap);
	/**
	 * 删除 主子表关系 数据
	 * @param lists <GUID>
	 * @return
	 */
	public Integer deleteMainSubRela(List<Map<String, Object>> lists);
	/**
	 * 新增左树数据
	 * @param map
	 * @return
	 */
	 Integer insertLeftTree(Map<String, String> map);
	/**
	 * 根据code查出所有主从表ID
	 * @param code
	 * @return
	 * @throws
	 */
	 List<Map<String, String>> getMainTabIDByCode(String code);
	
	 /**
	  * 根据mainTabID删除DICT_T_SETMAINSUBRELA表数据
	  * @param mainTabID
	  * @throws
	  */
	 void delMainSubRelaDataByMainId(@Param(value = "mainTabID") String mainTabID, @Param(value = "subTabID") String subTabID);
	 /**
	  * 根据tableid从DICT_T_SETREC表中删除数据
	  * @param tableID
	  * @throws
	  */
	 //void delRecByMainTabID(String code,String tableID);
	 /**
	  * 根据code删除DICT_T_SETMAINSUBTAB表数据
	  * @param code
	  * @throws
	  */
	 void delMainSubTabByColltypeID(String code);
	 /**
	  * Code_t_Collecttype
	  * @param map
	  * @throws
	  */
	 void deleteTreeData(Map<String, String> map);
}
