package commons.execscript.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.execscript.po.DbTablePO;

public interface IScriptMapper  extends SuperMapper{
	/**
	 * 获取common和当前分区的脚本
	 * @return
	 */
	public List<Map<String, Object>> getLogDataList(Map<String, String> param);
	/**
	 * 获取log表里，当前脚本执行过的次数
	 * @param param : keyId,typeId
	 * @return
	 */
	public Integer getScriptCount(Map<String, String> param);
	/**
	 * 获取efm_t_dbupdate_4bs脚本表里，脚本记录信息的条数；0/1
	 * @param param
	 * @return
	 */
	public Integer getOldScriptCount(Map<String, String> param);
	/**
	 * 插入脚本主表
	 * @param param
	 */
	public void insertDbTable(Map<String, String> param);
	/**
	 * 插入执行脚本日志表
	 * @param dbTablePO
	 */
	public void insertLogTable(DbTablePO dbTablePO);
	/**
	 * 修改执行脚本日志表
	 * @param param
	 */
	public void updateLogTable(Map<String, String> param);
	/**
	 * 修改执行脚本日志表，编译完失效对象后，将失效对象日志置为成功
	 * @param param
	 */
	public void updateLogTableInvalidObjects(Map<String, String> param);
	/**
	 * 在mybatis中用begin end执行脚本语句
	 * @param sql
	 */
	public void execSql(@Param("sql") String sql);
	/**
	 * 动态执行sql:execute immediate
	 * @param sql
	 */
	public void initScript(@Param("sql") String sql);
	/**
	 * 用存储过程执行脚本
	 * @param logid
	 */
	public void executeScript(@Param("logid") String logid);
	/**
	 * 编译失效对象
	 */
	public void compileInvalidObjects();
	/**
	 * 获取脚本
	 * @param param
	 * @return
	 */
	public Map<String, String> getScriptMap(Map<String, String> param);
	/**
	 * 获取所有批次号
	 */
	public List<String> getBatchCodeList(Map<String, String> param);
	/**
	 * 获取oracle数据字典中对象数量
	 * @param param
	 * @return
	 */
	public Integer getUserObjectsCount(Map<String, String> param);
	/**
	 * 获取oracle数据字典，表中某列数量
	 * @param param
	 * @return
	 */
	public Integer getUserTabColumnsCount(Map<String, String> param);
	
	
	/**
	 * 根据获取已执行过的脚本
	 * @return
	 */
	public List<Map<String, Object>> getAllExecScriptMap(Map<String, String> param);
	/**
	 * 获得所有版本信息
	 * @param param
	 * @return
	 */	
	public List<String> getAllVersionList(Map<String, String> param);
}
