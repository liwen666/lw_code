package commons.inputcomponent.datatrace.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.Constants;
import com.tjhq.commons.inputcomponent.datatrace.dao.IDataTraceDAO;
import com.tjhq.commons.inputcomponent.datatrace.service.IDataTraceService;
import com.tjhq.commons.inputcomponent.grid.basegrid.service.impl.BaseGridService;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.utils.ColumnUtil;
import com.tjhq.commons.inputcomponent.utils.TableUtil;
import com.tjhq.commons.mongodb.service.ILogService;

@Service("dataTraceService")
@Transactional(readOnly = true)
public class DataTraceService extends BaseGridService implements IDataTraceService {
	
	@Resource
	private IDataTraceDAO traceDAO;
	
	public IDataTraceDAO getTraceDAO() {
		return traceDAO;
	}

	public void setTraceDAO(IDataTraceDAO traceDAO) {
		this.traceDAO = traceDAO;
	}
	@Resource
	private ILogService logService;
	
	private String keyInsert = "insertLogs";
	
	private String keyUpdate = "updateLogs";
	
	private String keyDelete = "deleteLogs";

	/**
	 * 根据列属性转sql
	 * @param column
	 * @return
	 */
	public static String getSqlName(Column column, String value) {
		String fieldSqlName = "";
		value = "'" + value + "'";
		//引用表
		if(column.getShowType()!=null && column.getRefTableDBName()!=null && column.getShowType().equalsIgnoreCase("combo")){
			fieldSqlName = "" + value + " as "+ column.getColumnDBName() + ",(SELECT NAME FROM "+column.getRefTableDBName()+" WHERE GUID = "+value+" ) AS SNAME_"+column.getColumnDBName()+"";
		//附件表
		}else if(column.getShowType()!=null && column.getShowType().equalsIgnoreCase("fileuploadfield")){
			fieldSqlName = "" + value + " as "+ column.getColumnDBName() + ",(SELECT ATTACHNAME FROM PUB_T_ATTACH WHERE ATTACHID = "+value+" ) AS SNAME_"+column.getColumnDBName()+"";
		}else{
			fieldSqlName = "" + value + " as "+ column.getColumnDBName();
		}
		return fieldSqlName;
	}
	
	/**
	 * 处理日志数据
	 */
	public void handleLogData(Table table, String operation) {
		try {
			String tableID = table.getTableID();
			if(table == null || StringUtils.isEmpty(tableID)){
				return;
			}
			super.setTableColumns(table);
			//获取table的column
			List<Column> leafColumns = TableUtil.getLeafColumnList(table);
			if(leafColumns == null || leafColumns.size() == 0){
				return;
			}
			//表名
			String tableName = table.getTableDBName();
			
			Map<String, Object> extProperties = table.getExtProperties();
			//每条数据的datakey
			String datakey = "";
			//查询结果集
			List<Map<String, Object>> resultList = null;
			//查询结果
			Map<String, Object> resultMap = null;
			//查询sql
			String sql = "";
			
			String primaryKey = "DATAKEY";
			
			//table中的新增信息
			List<Map<String, Object>> insertList = table.getInsertValues();
			
			if (insertList != null && insertList.size() > 0) {
				//日志新增数据
				List<Map<String, Object>> insertLogs = new ArrayList<Map<String, Object>>();
				
				for (Map<String, Object> map : insertList) {
					datakey = map.get(primaryKey) != null ? map.get(primaryKey).toString() : "";
					
					StringBuffer sqlSelectBuffer = new StringBuffer();
					for(Column leafColumn : leafColumns){
						if(sqlSelectBuffer.length() != 0){
							sqlSelectBuffer.append(",");
						}
						int length = sqlSelectBuffer.length();
						for (Map.Entry<String, Object> entry : map.entrySet()) {
							if(leafColumn.getColumnDBName().equals(entry.getKey())){
								String value = entry.getValue() != null ? entry.getValue().toString() : "";
								sqlSelectBuffer.append(getSqlName(leafColumn, value));
							}
						}
						if(length == sqlSelectBuffer.length()){
							sqlSelectBuffer.append(getSqlName(leafColumn, ""));
						}
					}
					sql = "select " + sqlSelectBuffer.toString() + " from dual";
					resultList = traceDAO.queryData(sql);
					resultMap = resultList.get(0);
					insertLogs.add(resultMap);
				}
				//放入table中在after方法中存入mongodb
				extProperties.put(keyInsert, insertLogs);
			}
			
			//table中修改后的数据信息
			List<Map<String, Object>> updateList = table.getUpdateValues();
			
			if (updateList != null && updateList.size() > 0) {
				//日志删除数据
				List<Map<String, Object>> updateLogs = new ArrayList<Map<String, Object>>();
				
				for (Map<String, Object> map : updateList) {
					datakey = map.get(primaryKey) != null ? map.get(primaryKey).toString() : "";
					
					//修改 新值
					StringBuffer sqlSelectBuffer = new StringBuffer();
					for(Column leafColumn : leafColumns){
						if(sqlSelectBuffer.length() != 0){
							sqlSelectBuffer.append(",");
						}
						int length = sqlSelectBuffer.length();
						for (Map.Entry<String, Object> entry : map.entrySet()) {
							if(leafColumn.getColumnDBName().equals(entry.getKey())){
								String value = entry.getValue() != null ? entry.getValue().toString() : "";
								sqlSelectBuffer.append(getSqlName(leafColumn, value));
							}
						}
						if(length == sqlSelectBuffer.length()){
							sqlSelectBuffer.append(ColumnUtil.getSqlName(leafColumn));
						}
					}
					sql = "select " + sqlSelectBuffer.toString() + " from " + tableName + " where datakey = '" + datakey + "'";
					resultList = traceDAO.queryData(sql);
					Map<String, Object> resultMapNew = resultList.get(0);
					updateLogs.add(resultMapNew);
				}
				//放入table中在after方法中存入mongodb
				extProperties.put(keyUpdate, updateLogs);
			}
			
			//table中删除的数据信息
			List<Map<String, Object>> deleteList = table.getDeleteValues();
			if (deleteList != null && deleteList.size() > 0) {
				//sql中的查询结果集
				String selectElement = TableUtil.getSqlSelect(table);
				//日志删除数据
				List<Map<String, Object>> deleteLogs = new ArrayList<Map<String, Object>>();
				for (Map<String, Object> map : deleteList) {
					//从数据库查询数据
					datakey = map.get(primaryKey) != null ? map.get(primaryKey).toString() : "";
					sql = "select " + selectElement + " from " + tableName + " where datakey = '" + datakey + "'";
					resultList = traceDAO.queryData(sql);
					resultMap = resultList.get(0);
					deleteLogs.add(resultMap);
				}
				//放入table中在after方法中存入mongodb
				extProperties.put(keyDelete, deleteLogs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存日志数据
	 */
	public void saveLogData(Table table, String operation) {
		try {
			String tableID = table.getTableID();
			if(table == null || StringUtils.isEmpty(tableID)){
				return;
			}
			String taskID = table.getTaskID();
			Map<String, Object> extProperties = table.getExtProperties();
			if(extProperties.containsKey(keyInsert)){
				List<Map<String, Object>> insertLogs = (List<Map<String, Object>>) table.getExtProperties().get(keyInsert);
				if(insertLogs != null && insertLogs.size() > 0){
					this.logService.saveData(insertLogs, tableID, Constants.MONGO_FUNCTION_INSERT, taskID, operation);
				}
				extProperties.remove(keyInsert);
			}
			if(extProperties.containsKey(keyUpdate)){
				List<Map<String, Object>> updateLogs = (List<Map<String, Object>>) table.getExtProperties().get(keyUpdate);
				if(updateLogs != null && updateLogs.size() > 0){
					this.logService.saveData(updateLogs, tableID, Constants.MONGO_FUNCTION_UPDATE, taskID, operation);
				}
				extProperties.remove(keyUpdate);
			}
			if(extProperties.containsKey(keyDelete)){
				List<Map<String, Object>> deleteLogs = (List<Map<String, Object>>) table.getExtProperties().get(keyDelete);
				if(deleteLogs != null && deleteLogs.size() > 0){
					this.logService.saveData(deleteLogs, tableID, Constants.MONGO_FUNCTION_DELETE, taskID, operation);
				}
				extProperties.remove(keyDelete);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
