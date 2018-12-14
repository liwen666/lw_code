package commons.execscript.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.inputcomponent.po.Table;

public interface IScriptService {
	/**
	 * 根据表类型获取不同的表定义
	 * @param gridType
	 * @return
	 */
	public Table setTableDefine(String gridType);
	/**
	 * 获取未执行脚本表格数据
	 * @return
	 */
	public List<Map<String, Object>> getScriptDataList(String appID, String province) throws Exception;
	/**
	 * 获取脚本执行日志表格数据
	 * common和当前分区的
	 * @return
	 */
	public List<Map<String, Object>> getLogDataList(Map<String, Object> param);
	/**
	 * 获取所有批次号
	 */
	public List<String> getBatchCodeList(Map<String, String> param);
	/**
	 * 执行脚本
	 * @throws Exception
	 */
	public void execScript(Map<String, String> param) throws Exception;
	/**
	 * 编译失效对象
	 */
	public void compileInvalidObjects();
	/**
	 * 获取单个脚本
	 * @param param
	 * @return
	 */
	public Map<String, String> getScriptMap(Map<String, String> param);
	/**
	 * 检查数据库中是否存在本模块依赖的数据结构，没有就创建
	 */
	public void initDbScriptObject();
	/**
	 * 修改执行脚本日志表，编译完失效对象后，将失效对象日志置为成功
	 */
	public void updateLogTableInvalidObjects();
	/**
	 * 根据xml获取已编号但未执行过的脚本
	 */
	public List<Map<String, Object>> getNotExecScriptFormXml(String appID, String province)throws Exception;
}
