package commons.setting.input.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.inputcomponent.po.Table;

public interface IGeneralService{
	/**
	 * 根据不同的定义类型 ，创建不同的表头
	 * @param tableID 表ID
	 * @param defineID 定义类型 （分组、排序等）
	 * @param columnID 列ID
	 * @return
	 */
	//public Grid getDefineHead(String tableID,String defineID,String columnID);

	/**
	 * 所有grid的保存方法
	 * @author ZK
	 * @param commonGrid
	 * @param defineID
	 * @throws ServiceException
	 */
	void saveTableData(CommonGrid commonGrid, String defineID) throws ServiceException;

	/**
	 *  根据不同的定义类型 ，插入不同的表数据
	 * @param lists
	 * @param defineID
	 * @param relaDbTab 引用关系定义物理表名
	 * @return
	 */
	public String insertTableData(List<Map<String, Object>> lists, String defineID, String relaDbTab, String recID) throws ServiceException;

	/**
	 *  根据不同的定义类型 ，更新不同的表数据
	 * @param lists
	 * @param defineID
	 * @return
	 */
	public String updateTableData(List<Map<String, Object>> lists, String defineID);

	/**
	 *  根据不同的定义类型 ，删除不同的表数据
	 * @param lists
	 * @param defineID
	 * @param relaDbTab 引用关系定义物理表名
	 * @return
	 */
	public String deleteTableData(List<Map<String, Object>> lists, String defineID, String relaDbTab);

	/**
	 * 新增 更新 查询条件定义
	 * @param map
	 * @return
	 */
	public String saveQuerydDet(Map<String, Object> map);

	/**
	 * 根据CODE_T_ 代码表 查询代码表中数据
	 * @param codeTableName 代码表名
	 * @return
	 */
	public List<Map<String,Object>> queryCodeTable(String codeTableName);

	/**
	 * 获取表定义 2.0
	 * @return
	 */
	public Table setTableDefine(String tableId, String columnID, String defineID);

	/**
	 * 列关系的保存方法（tree）
	 * @author ZK
	 * @date 16-11-30
	 * @param tableID
	 * @param relaID
	 * @param condDataID
	 * @param refrelas
	 * @return
	 * @throws Exception
	 */
	void saveRefrela(String tableID, String relaID, String condDataID,
                     List<Map<String, Object>> relas);
}
