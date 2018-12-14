package commons.inputcomponent.datatrace.service;

import com.tjhq.commons.inputcomponent.po.Table;

public interface IDataTraceService {
	
	/**
	 * 处理日志数据，放入table的extProperties中
	 * @param table
	 * @param operation
	 */
	public void handleLogData(Table table, String operation);
	
	/**
	 * 保存日志数据，从table的extProperties中取出数据并保存
	 * @param table
	 * @param operation
	 */
	public void saveLogData(Table table, String operation);
	
}
