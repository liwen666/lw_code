package commons.setting.external.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.setting.external.po.DictTSetAnalyPO;
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
* 类名称：EntryMapper  
* 类描述：录入界面定义接口类
* 创建人：shishj
* 创建时间：Jan 16, 2014 2:53:03 AM
* @version 1.0
 */
public interface EntryOuterMapper extends SuperMapper{
	
	/**
	 * 根据 表ID 获取视角设置信息
	 * @param map<tableID,stage>
	 * @return
	 */
	public List<DictTSetAngleViewPO> getDataAngleViewList(Map map);
	
	/**
	 * 根据 表ID 获取分组设置信息
	 * @param map<tableID,stage>
	 * @return
	 */
	public List<DictTSetGroupPO> getDataGroupList(Map map);
	/**
	 * 根据 表ID 获取排序设置信息
	 * @param map<tableID,stage>
	 * @return
	 */
	public List<DictTSetSortPO> getDataSortList(Map map);
	
	/**
	 * 根据 表ID 获取引用列关系定义
	 * @param map<tableID,stage>
	 * @return
	 */
	public List<DictTSetRefrelaPO> getDataRefrelaList(Map map);
	
	/**
	 * 根据 表ID 获取查询（分析）引用定义
	 * @param map<tableID,stage>
	 * @return
	 */
	public List<DictTSetAnalyPO> getDataAnalyList(Map map);
	
	/**
	 * 根据 表ID 获取查询条件设置
	 * @param map<tableID,stage>
	 * @return
	 */
	public DictTSetQuerydPO getDataQuerydList(Map map);
	
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
	
	/**
	 * 根据 表ID 基本数字表设置
	 * @param tableID
	 * @return DictTSetBaseNumTab
	 */
	public DictTSetBaseNumTab getDataBaseTabList(String tableID);

	
	/**
	 * 根据 类型ID 获取 该类型下的 主表与子表
	 * isMainTable 1、主表 0、子表
	 * @param collTypeID 采集类型ID  || BASEOUTPUT
	 * @return
	 */
	public List<DictTSetMainSubTab> getDataMainSubTabList(String collTypeID);
	
	
	/**
	 * 主从关系设置 详细页面 整体设置
	 * @param map(objectId,tableId)
	 * @return
	 */
	public List<RECPO> getSetRECList(Map<String, String> map);

	/**
	 * 主从关系设置 详细页面 明细设置
	 * @param map
	 * @return
	 */
	public List<RECDetailPO> getSetRECDetailList(Map<String, String> map);
	/**
	 * 判断表是否在CODE_T_COLLECTTYPE中存在
	 * @param tableDbName
	 * @return
	 */
	public Integer isTabExits(@Param("tableDbName") String tableDbName);
}
