package commons.secu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.Constants;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTModelcodePO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.dict.external.service.IDictTModelcodeService;
import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.constants.ShowType;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.utils.JSTypeUtils;

import com.tjhq.commons.secu.dao.SecuTLimitcolMapper;
import com.tjhq.commons.secu.dao.SecuTLimittabMapper;
import com.tjhq.commons.secu.dao.SecuTTableMapper;
import com.tjhq.commons.secu.external.IDataAuthService;
import com.tjhq.commons.secu.po.SecuTLimitcolPO;
import com.tjhq.commons.secu.po.SecuTLimittabPO;
import com.tjhq.commons.secu.po.TreeNode;
import com.tjhq.commons.secu.service.IInputLimitService;

@Component
@Transactional(readOnly=true)
public class InputLimitService implements IInputLimitService {
	
	private static final String DATA_AUTHORITY_ACCTITEM_COLNAME = "EXPFUNCID"; //科目编码 -》数据库表-》列名
	@Resource 
	private SecuTTableMapper secuTTableMapper;
	@Resource
	private SecuTLimittabMapper secuTLimittabMapper;
	@Resource
	private SecuTLimitcolMapper secuTLimitcolMapper;
	@Resource
	private IDataAuthService dataAuthService;
	@Resource
	private IDictTFactorService dictTFactorService;
	@Resource
	private IDictTModelcodeService dictTModelcodeService;
	@Override
	public Grid getInputLimitTableListHead() {
		// 创建Grid
		/*Grid grid = new Grid();
		// 设置tableID
		grid.setTableId("");
		// Grid类型
		grid.setgType("tableid");
		// 适配类型
		grid.setAdapterType(1);

		grid.setDbName("mytableName");
		grid.setEditable(true);
		grid.setCanCheck(false);
		grid.setCheckType(false);
		grid.setReadOnly(true);
		grid.setWidth(0);
		grid.setHeight(600);
		// 创建表头对象
		Head gridHead = new Head();
		// 创建列
		Column tableId = new Column("报表序号", "TABLEID");
		tableId.setVisible(false);
		Column tableName = new Column("报表名称", "NAME");
		tableName.setEditable(false);
		Column operateButton = new Column("操作", "LIMITFLAG");
		operateButton.setEditable(false);
		List<Column> columnList = new ArrayList<Column>();
		columnList.add(tableId);
		columnList.add(tableName);
		columnList.add(operateButton);
		gridHead.setColumns(columnList);
		grid.setHead(gridHead);
		return grid;*/
		return null;
	}

	@Override
	public List<Map<String, String>> getTableInputLimitListBySuitId(String objId) {
		return secuTTableMapper.getTableInputLimitListBySuitId(objId);
	}

	@Override
	public List<Map<String, String>> getTableInputLimitListByTableId(
			String objId) {
		return secuTTableMapper.getTableInputLimitListByTableId(objId);
	}

	@Override
	public Grid getInputLimitItemListHead() {
		// 创建Grid
		/*Grid grid = new Grid();
		// 设置tableID
		grid.setTableId("");
		// Grid类型
		grid.setgType("tableid");
		// 适配类型
		grid.setAdapterType(1);

		grid.setDbName("mytableName");
		grid.setEditable(true);
		grid.setCanCheck(false);
		grid.setCheckType(false);
		grid.setReadOnly(true);
		grid.setWidth(0);
		grid.setHeight(600);
		// 创建表头对象
		Head gridHead = new Head();
		// 创建列
		Column limitId = new Column("科目序号", "limitId");
		limitId.setVisible(false);
		limitId.setEditable(false);
		Column columnName = new Column("科目名称", "columnName");
		columnName.setWidth(250);
		columnName.setVisible(true);
		columnName.setEditable(false);
		Column limitCon = new Column("权限", "limitCon");
		limitCon.setEditable(false);
		limitCon.setWidth(250);
		List<Column> columnList = new ArrayList<Column>();
		columnList.add(limitId);
		columnList.add(columnName);
		columnList.add(limitCon);
		gridHead.setColumns(columnList);
		grid.setHead(gridHead);
		return grid;*/
		return null;
	}

	@Override
	public List<SecuTLimittabPO> getInputLimitItemList(String tableId) {
		String dbNameForAcctItem = dataAuthService.getTableDatabaseNameBy( "BGT", Constants.COMMON_DictTAPPCODE_TABLETYPE_EXPFUNC);
		Map param = new HashMap();
		param.put("dbNameForAcctItem", dbNameForAcctItem);
		param.put("tableId", tableId);
		return secuTLimittabMapper.getInputLimitItemsList(param);
	}

	@Override
	public List<TreeNode> getAcctItemTreeData(String tableId) {
		boolean exists = false;
		String factorCsId = "";
		List<DictTFactorPO> factors = dictTFactorService.getDictTFactorByTableidAndType(tableId, "1");
		for(DictTFactorPO factor : factors){
			if(factor.getDbcolumnname().equalsIgnoreCase(this.DATA_AUTHORITY_ACCTITEM_COLNAME)){
				exists = true;
				factorCsId = factor.getCsid();
			}
		}
		if(!exists){
			return new ArrayList<TreeNode>();
		}
		DictTModelcodePO  modelCode = dictTModelcodeService.getDictTModelcodePOByID(factorCsId);
		
		String dbNameForAcctItem = dataAuthService.getTableDatabaseNameBy( "BGT", Constants.COMMON_DictTAPPCODE_TABLETYPE_EXPFUNC);
		Map param = new HashMap();
		param.put("dbNameForAcctItem", dbNameForAcctItem);
		param.put("tableId", tableId);
		param.put("tableName",modelCode.getDbtablename());
		return secuTTableMapper.getAcctItemTreeData(param);
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void deleteAcctItemTreeData(Map param) throws Exception{
		secuTLimittabMapper.deleteAcctItemTreeData(param);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveAcctItemTreeData(List editDatasList, List addDatasList) throws Exception{
		Map<String,String> object;
			if (editDatasList != null && editDatasList.size() != 0) {
				Map<String,String> editData = new HashMap<String,String>();
				for (Object obj : editDatasList) {
					object = (Map<String,String>)obj;
					editData.put("tableId", (String)object.get("tableId"));
					editData.put("limitCon", (String)object.get("limitCon"));
					editData.put("limitId", (String)object.get("limitId"));
					secuTLimittabMapper.updateSecuTLimittabData(editData);
				}
			}
			if (addDatasList != null && addDatasList.size() != 0) {
				Map<String, String> addData;
				for (Object obj : addDatasList) {
					addData = (Map<String, String>) obj;
					secuTLimittabMapper.insertSecuTLimittabData(addData);
				}
			}
	}

	@Override
	public List<Map<String, String>> getTableInputUnlimitListBySuitId(
			String objId) {
		return secuTTableMapper.getTableInputUnlimitListBySuitId(objId);
	}

	@Override
	public List<Map<String, String>> getTableInputLimitedListBySuitId(
			String objId) {
		return secuTTableMapper.getTableInputLimitedListBySuitId(objId);
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void cancelInputLimit(String tableId) throws Exception{
		secuTLimittabMapper.deleteTableInputLimit(tableId);
		secuTLimitcolMapper.deleteColInputLimit(tableId);
	}

	@Override
	public SecuTLimitcolPO queryInputColLimit(String tableId, String columnId) {
		List<DictTFactorPO> factors = dictTFactorService.getDictTFactorByTableidAndType(tableId, "1");
		Map param = new HashMap();
		param.put("tableId", tableId);
		param.put("columnId", columnId);
		SecuTLimitcolPO secuTLimit = new SecuTLimitcolPO();
		
		secuTLimit=  secuTLimitcolMapper.queryInputColLimit(param);
		if(secuTLimit == null){
			return null;
		}
		String limitConFunc = secuTLimit.getLimitConFunc();
		String finalLimitConFunc=limitConFunc;
		finalLimitConFunc = reverseLimitConFuncForDisplay(finalLimitConFunc,limitConFunc,factors);
		secuTLimit.setLimitConFunc(finalLimitConFunc);
		return secuTLimit;
	}

	private String reverseLimitConFuncForDisplay(String finalLimitConFunc,
			String limitConFunc, List<DictTFactorPO> factors) {
		String column = "";
		String columnTran = "";
		int start = limitConFunc.indexOf(Constants.COLUMN_START);
		int end = limitConFunc.indexOf(Constants.COLUMN_END);
		if(start <0 || end<0){
			return finalLimitConFunc;
		}
		column = limitConFunc.substring(start,end+1);
		for(DictTFactorPO factor : factors){
			if(column.equalsIgnoreCase(Constants.COLUMN_START+factor.getColumnid()+Constants.COLUMN_END)){
				columnTran = Constants.COLUMN_START+factor.getName()+Constants.COLUMN_END;
				break;
			}
		}
		finalLimitConFunc=finalLimitConFunc.replace(column, columnTran);
		limitConFunc = limitConFunc.replace(column, "");
		 return reverseLimitConFuncForDisplay(finalLimitConFunc,limitConFunc,factors);
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveColLimitData(String tableId, String columnId,
			String showWhere, String sqlWhere,String existFlag) throws Exception{
		
		List<DictTFactorPO> factors = dictTFactorService.getDictTFactorByTableidAndType(tableId, "1");
		String limitCon = sqlWhere;
		String limitConFunc = sqlWhere; 
		limitCon = transferLimitToLimitCon(limitCon,sqlWhere,factors);
		limitConFunc = transferLimitToLimitConFunc(limitConFunc,sqlWhere,factors);
		
		
		Map<String, String> param = new HashMap<String, String>();
		param.put("tableId", tableId);
		param.put("columnId", columnId);
		param.put("showWhere", showWhere);
		param.put("limitCon", limitCon);
		param.put("limitConFunc", limitConFunc);
		
		if("1".equals(existFlag)){
			secuTLimitcolMapper.updateColLimitData(param);
		}else{
			secuTLimitcolMapper.saveColLimitData(param);
		}
		
	}

	private String transferLimitToLimitConFunc(String finalSqlWhere,String sqlWhere,List<DictTFactorPO> factors) {
		String column = "";
		String columnTran = "";
		int start = sqlWhere.indexOf(Constants.COLUMN_START);
		int end = sqlWhere.indexOf(Constants.COLUMN_END);
		if(start <0 || end<0){
			return finalSqlWhere;
		}
		column = sqlWhere.substring(start,end+1);
		for(DictTFactorPO factor : factors){
			if(column.equalsIgnoreCase(Constants.COLUMN_START+factor.getName()+Constants.COLUMN_END)){
				columnTran = Constants.COLUMN_START+factor.getColumnid()+Constants.COLUMN_END;
				break;
			}
		}
		finalSqlWhere=finalSqlWhere.replace(column, columnTran);
		sqlWhere = sqlWhere.replace(column, "");
		 return transferLimitToLimitConFunc(finalSqlWhere,sqlWhere,factors);
	}

	private String transferLimitToLimitCon(String finalSqlWhere,String sqlWhere, List<DictTFactorPO> factors) {
		String column = "";
		String columnTran = "";
		int start = sqlWhere.indexOf(Constants.COLUMN_START);
		int end = sqlWhere.indexOf(Constants.COLUMN_END);
		if(start <0 || end<0){
			return finalSqlWhere;
		}
		column = sqlWhere.substring(start+1,end);
		for(DictTFactorPO factor : factors){
			if(column.equalsIgnoreCase(factor.getName())){
				columnTran = factor.getDbcolumnname();
				break;
			}
		}
		finalSqlWhere=finalSqlWhere.replace(Constants.COLUMN_START+column+Constants.COLUMN_END, columnTran);
		sqlWhere = sqlWhere.replace(Constants.COLUMN_START+column+Constants.COLUMN_END, "");
		 return transferLimitToLimitCon(finalSqlWhere,sqlWhere,factors);
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void clearLimitWindow(String tableId, String columnId) throws Exception{
		Map param = new HashMap();
		param.put("tableId", tableId);
		param.put("columnId", columnId);
		secuTLimitcolMapper.clearLimitWindow(param);
		
	}

	@Override
	public Table getTableColListHead() {
		Table table = new Table();
		table.setAppID("BGT");
		table.setTableName("列"); 
		table.setTableID("mytableName");
		table.setTableDBName("mytableName");
		List<Column> columnList = new ArrayList<Column>(); 
		Column columnIdCol=new Column();
		columnIdCol.setColumnID("columnid");
		columnIdCol.setColumnName("columnid");
		columnIdCol.setColumnDBName("columnid"); 
		columnIdCol.setAlias("列Id");
		columnIdCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		columnIdCol.setShowType(ShowType.SHOW_TYPE_HTML);
		columnIdCol.setDataLength(32);
		columnIdCol.setKey(true);
		columnIdCol.setOrderID(0);
		columnIdCol.setVisible(false);
		columnList.add(columnIdCol);
		Column colNameCol=new Column();
		colNameCol.setColumnID("name");
		colNameCol.setColumnName("name");
		colNameCol.setColumnDBName("name");  
		colNameCol.setAlias("要素名称");
		colNameCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		colNameCol.setShowType(ShowType.SHOW_TYPE_HTML);
		colNameCol.setDataLength(32);
		colNameCol.setKey(true);
		colNameCol.setOrderID(0);
		colNameCol.setVisible(true); 
		colNameCol.setWidth(130); 
		columnList.add(colNameCol);
		table.setColumnList(columnList); 
		return table;
	}

	@Override
	public void verifyTableCellLimit(Map<String, String> tableCellLimit) {
		String sqlWhere  = (String)tableCellLimit.get("sqlWhere");
		String tableId = (String)tableCellLimit.get("tableId"); 
		List<DictTFactorPO> factors = dictTFactorService.getDictTFactorByTableidAndType(tableId, "1");
		String limitCon = sqlWhere; 
		limitCon = transferLimitToLimitCon(limitCon,sqlWhere,factors);
		String caseWhen = "(CASE WHEN ("+limitCon+") THEN '1' ELSE '0' END)"; 
		tableCellLimit.put("sqlWhere", caseWhen);
		secuTTableMapper.verifyTableCellLimit(tableCellLimit);
	}

	@Override
	public Table getInputLimitGrid() {
		Table table = new Table();
		table.setAppID("BGT");
		table.setTableName("输入限制"); 
		table.setTableID("mytableName");
		table.setTableDBName("mytableName");
		List<Column> columnList = new ArrayList<Column>();  
		Column columnIdCol=new Column();
		columnIdCol.setColumnID("TABLEID");
		columnIdCol.setColumnName("TABLEID");
		columnIdCol.setColumnDBName("TABLEID"); 
		columnIdCol.setAlias("报表序号");
		columnIdCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		columnIdCol.setShowType(ShowType.SHOW_TYPE_HTML);
		columnIdCol.setDataLength(32);
		columnIdCol.setKey(true);
		columnIdCol.setOrderID(0);
		columnIdCol.setVisible(false); 
		columnIdCol.setReadOnly(true);
		columnList.add(columnIdCol);
		Column tableNameCol=new Column();
		tableNameCol.setColumnID("NAME");
		tableNameCol.setColumnName("NAME");
		tableNameCol.setColumnDBName("NAME"); 
		tableNameCol.setAlias("报表名称");
		tableNameCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		tableNameCol.setShowType(ShowType.SHOW_TYPE_HTML);
		tableNameCol.setDataLength(32);
		tableNameCol.setKey(true);
		tableNameCol.setOrderID(0);
		tableNameCol.setVisible(true);
		tableNameCol.setReadOnly(true);
		tableNameCol.setWidth(500);   
		columnList.add(tableNameCol);
		Column limitFlagCol=new Column();
		limitFlagCol.setColumnID("LIMITFLAG");
		limitFlagCol.setColumnName("LIMITFLAG");
		limitFlagCol.setColumnDBName("LIMITFLAG"); 
		limitFlagCol.setAlias("操作");
		limitFlagCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		limitFlagCol.setShowType(ShowType.SHOW_TYPE_HTML); 
		limitFlagCol.setDataLength(32); 
		limitFlagCol.setWidth(300);   
		limitFlagCol.setKey(true);
		limitFlagCol.setOrderID(0);
		limitFlagCol.setVisible(true);  
		limitFlagCol.setReadOnly(true);
		columnList.add(limitFlagCol);
		table.setColumnList(columnList);
		return table; 
	}

	@Override
	public Table getInputLimitItemGrid() {
		Table table = new Table();
		table.setAppID("BGT");
		table.setTableName("表限制"); 
		table.setTableID("mytableName");
		table.setTableDBName("mytableName");
		List<Column> columnList = new ArrayList<Column>();  
		Column limitIdCol=new Column();
		limitIdCol.setColumnID("limitId");
		limitIdCol.setColumnName("limitId");
		limitIdCol.setColumnDBName("limitId"); 
		limitIdCol.setAlias("科目序号");
		limitIdCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		limitIdCol.setShowType(ShowType.SHOW_TYPE_HTML);
		limitIdCol.setDataLength(32); 
		limitIdCol.setKey(true);
		limitIdCol.setOrderID(0);
		limitIdCol.setVisible(false); 
		limitIdCol.setReadOnly(true);
		columnList.add(limitIdCol);
		Column columnNameCol=new Column();
		columnNameCol.setColumnID("columnName");
		columnNameCol.setColumnName("columnName");
		columnNameCol.setColumnDBName("columnName"); 
		columnNameCol.setAlias("科目名称");
		columnNameCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		columnNameCol.setShowType(ShowType.SHOW_TYPE_HTML);
		columnNameCol.setDataLength(32);
		columnNameCol.setKey(true);
		columnNameCol.setOrderID(0);
		columnNameCol.setVisible(true);
		columnNameCol.setReadOnly(true);
		columnNameCol.setWidth(300);    
		columnList.add(columnNameCol);
		Column limitConCol=new Column();
		limitConCol.setColumnID("limitCon"); 
		limitConCol.setColumnName("limitCon");
		limitConCol.setColumnDBName("limitCon"); 
		limitConCol.setAlias("权限");
		limitConCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		limitConCol.setShowType(ShowType.SHOW_TYPE_HTML); 
		limitConCol.setDataLength(32); 
		limitConCol.setWidth(200);     
		limitConCol.setKey(true);
		limitConCol.setOrderID(0);
		limitConCol.setVisible(true);  
		limitConCol.setReadOnly(true);
		columnList.add(limitConCol);
		table.setColumnList(columnList); 
		return table; 
	}

}
