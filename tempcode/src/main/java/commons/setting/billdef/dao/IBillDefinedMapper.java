package commons.setting.billdef.dao;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.setting.billdef.po.BillDefinedPO;

public interface IBillDefinedMapper extends SuperMapper{
	/**
	 * 获取记账定义列表
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<BillDefinedPO> getBillDefList(Map<String, Object> params) throws Exception;
	/**
	 * 新增记账定义
	 * @param po
	 * @throws Exception
	 */
	public void insertBillDef(BillDefinedPO po) throws Exception;
	/**
	 * 修改记账定义
	 * @param po
	 */
	public void deleteBillDef(BillDefinedPO po);
	/**
	 * 删除记账定义
	 * @param po
	 * @throws Exception
	 */
	public void updateBillDef(BillDefinedPO po) throws Exception;
	/**
	 * 获取存储过程列表
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getProcList(Map<String, Object> params) throws Exception;
	/**
	 * 获取选中要素
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getCheckBasic(Map<String, Object> params) throws Exception;
	/**
	 * 执行生成来源表存储过程
	 * @param params
	 * @throws Exception
	 */
	public void execProc(Map<String, Object> params) throws Exception;
	/**
	 * 获取记账定义信息by ID
	 * @param billDefId
	 * @return
	 */
	public List<Map<String, Object>> getBillDefById(String billDefId);
	/**
	 * 删除记账要素信息by ID
	 * @param billDefId
	 * @return
	 */
	public void deleteBasicData(String billDefId);
	/**
	 * 新增记账要素信息
	 * @param map
	 */
	public void insertBasicData(Map<String, Object> map);
	/**
	 * 删除目标表对应来源表列关系
	 * @param billDefId
	 */
	public void deleteTgtRelaData(String billDefId);
	/**
	 * 获取动态属性列表数据
	 * @return
	 */
	public List<Map<String, Object>> getPropData();
	/**
	 * 获取目标表列对应关系数据
	 * @return
	 */
	public List<Map<String, Object>> getColsData(Map<String, Object> map);
	/**
	 * 检查sql语句是否合法
	 * @param params
	 */
	public List<Map<String, Object>> checkSql(Map<String, String> params);
	/**
	 * 检查来源列公式是否合法
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> checkColumnSql(Map<String, String> params);
	/**
	 * 新增列对应关系
	 * @param map
	 */
	public void insertColsRela(Map<String, Object> map);
	/**
	 * 修改列对应关系
	 * @param map
	 */
	public void updateColsRela(Map<String, Object> map);
	/**
	 * 删除对应关系
	 * @param map
	 */
	public void deleteColsRela(Map<String, Object> map);
	/**
	 * 检查原始来源表是否在定义中存在
	 * @param map
	 * @return
	 */
	public int getDefSrcTableCount(Map<String, Object> map);
	/**
	 * 查询已经使用的记账定义
	 * @param confirmFunc
	 * @return
	 */
	public Map<String, Object> getConfirmfuncById(String confirmFunc);
	/**
	 * 根据Guid查询来源表tableid
	 * @param guid
	 * @return
	 */
	public String getGenSrcTableIdByGuid(String guid);
}
