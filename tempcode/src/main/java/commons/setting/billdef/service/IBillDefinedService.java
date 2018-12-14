package commons.setting.billdef.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.setting.billdef.po.BillDefinedPO;

public interface IBillDefinedService {
	/**
	 * 获取记账定义表定义
	 * @return
	 * @throws ServiceException
	 */
	public Object getBillTableDefined(String appId) throws ServiceException;
	/**
	 * 获取记账定义数据
	 * @return
	 * @throws ServiceException
	 */
	public Object getBillTableData(Grid data) throws ServiceException;
	/**
	 * 获取model表树数据
	 * @param all
	 * @param appId
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, Object>> getTableTree(String all, String appId, String isDb) throws ServiceException;
	/**
	 * 获取表列数据
	 * @param tableId
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, Object>> getColumnTree(String tableId) throws ServiceException;
	/**
	 * 获取表列数据
	 * @param tableId
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, Object>> getColumnTree(String tableId, String billDefId) throws ServiceException;
	/**
	 * 获取存储过程列表
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getProcList(Map<String, Object> params) throws ServiceException;
	
	/**
	 * 执行生成来源表存储过程
	 * @param params
	 * @throws Exception
	 */
	public Map<String, Object> execProc(Map<String, Object> params) throws ServiceException;
	
	/**
	 * 检查where条件定义
	 * @param po
	 * @throws ServiceException
	 */
	public Map<String, Object> saveData(BillDefinedPO po) throws ServiceException;
	
	/**
	 * 保存记账定义数据
	 * @param po
	 * @throws ServiceException
	 */
	public Map<String, Object> checkWhere(BillDefinedPO po) throws ServiceException;

	/**
	 * 删除目标表对应来源表列关系
	 * @param list
	 */
	public void deleteBillDef(List<BillDefinedPO> list);
	
	/**
	 * 获取动态属性列表定义
	 * @return
	 * @throws ServiceException
	 */
	public Object getPropDefined() throws ServiceException;
	
	/**
	 * 获取动态属性列表数据
	 * @return
	 * @throws ServiceException
	 */
	public Object getPropData(Grid data) throws ServiceException;
	
	/**
	 * 获取目标表列绑定列表定义
	 * @return
	 * @throws ServiceException
	 */
	public Object getColsDefined() throws ServiceException;
	/**
	 * 获取目标表列绑定列表数据
	 * @return
	 * @throws ServiceException
	 */
	public Object getColsData(Grid data) throws ServiceException;
	/**
	 * 检查列公式定义
	 * @param paramsMap
	 * @throws ServiceException
	 */
	public Map<String, String> checkColumnSql(Map<String, String> paramsMap) throws ServiceException;
	/**
	 * 保存目标表列公式设置
	 * @param data
	 * @throws ServiceException
	 */
	public void saveColsRela(Grid data) throws ServiceException;
	/**
	 * 检查原始来源表是否已经存在定义中
	 * @param map
	 * @return
	 */
	public boolean isExistSrcTable(Map<String, Object> map);
	/**
	 * 获取记账规则是否使用
	 * @param list
	 * @return
	 */
	public Object getConfirmfuncUse(List<Map<String, Object>> list);

}
