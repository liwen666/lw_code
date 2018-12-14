package commons.setting.input.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.setting.input.po.DictTSetMainSubRela;
import com.tjhq.commons.setting.input.po.TreeNode;
/**
* 类名称：ICollectEntryService  
* 类描述：采集录入表关系设置
* 创建人：shishj
* 创建时间：Mar 24, 2014 6:16:30 AM
* @version
 */
public interface IMainSubRelaService {
	/**
	 * 获取采集类型表
	 * @return
	 */
	public List<TreeNode> getCollectTypeTreeList() throws ServiceException;
	/**
	 * 
	 */
	List<TreeNode> getLeftTreeData(String appID) throws ServiceException;
	
	/**
	 * 根据不同的采集类型 创建不同的表头
	 * @param collTypeID 采集类型ID
	 * @return
	 */
	public Table getTableHead(String collTypeID) throws ServiceException;
	
	/**
	 * 根据采集类型 查询数据
	 * @param collTypeID 采集类型ID
	 * @return
	 */
	 Object getTableData(String collTypeID) throws ServiceException;
	
	/**
	 * 插入 数据
	 * @param lists
	 * @return
	 */
	 void insertTableData(List<Map<String, Object>> lists) throws ServiceException;
	
	/**
	 * 更新 数据
	 * @param lists
	 * @return
	 */
	 void updateTableData(List<Map<String, Object>> lists) throws ServiceException;

	/**
	 * 删除 数据
	 * @param lists
	 * @return
	 */
	 void deleteTableData(List<Map<String, Object>> lists) throws ServiceException;
	
	/**
	 * 根据 外键 查询 主子关系设置  DictTSetMainSubRela || DictTSetMainSubTab
	 * @param mainSubID (外键)
	 * @return
	 */
	 List<DictTSetMainSubRela> selectMainSubRela(String mainSubID) throws ServiceException;
	
	/**
	 * 保存 主子关系设置
	 * @param relaMap <mainTabID,subTabID,mainFkID,fKID>
	 * @param operator 操作 insert || update
	 * @return
	 */
	 void saveMainSubRela(Map<String, Object> relaMap, String operator) throws ServiceException;
	/**
	 * 新增左树
	 * @param map
	 * @return
	 * @throws Exception
	 */
	 void saveLeftTree(Map<String, String> map) throws ServiceException;
	/**
	 * 删除左树节点
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public void deleteLeftTreeData(Map<String, String> map) throws ServiceException;
	List<TreeNode> suitTreeMainSub(String appID, String collTypeID) throws ServiceException;
}