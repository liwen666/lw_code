package commons.secu.service.impl;

import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.Constants;
import com.tjhq.commons.cache.service.IDataCacheService;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.constants.ShowType;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.utils.JSTypeUtils;
import com.tjhq.commons.secu.constants.SecuCacheKey;
import com.tjhq.commons.secu.dao.SecuTAgencyMapper;
import com.tjhq.commons.secu.dao.SecuTColMapper;
import com.tjhq.commons.secu.dao.SecuTRowMapper;
import com.tjhq.commons.secu.dao.SecuTTableMapper;
import com.tjhq.commons.secu.external.IDataAuthService;
import com.tjhq.commons.secu.po.SecuTColPO;
import com.tjhq.commons.secu.po.SecuTRowPO;
import com.tjhq.commons.secu.po.SecuTTablePO;
import com.tjhq.commons.secu.po.TreeNode;
import com.tjhq.commons.secu.service.IRoleToAgencyService;

@Component
@Transactional(readOnly=true)
public class RoleToAgencyService implements IRoleToAgencyService {
	
	@Resource
	private SecuTAgencyMapper secuTAgencyMapper;
	@Resource
	private SecuTTableMapper secuTTableMapper;
	@Resource
	private SecuTColMapper secuTColMapper;
	@Resource
	private SecuTRowMapper secuTRowMapper;
	@Resource
	private IDataAuthService dataAuthService;
	@Resource
	private IDictTFactorService dictTFactorService;
	@Resource
	private IDataCacheService dataCacheService;
	@Override
	public Grid getTableListHead() {
		/*
		// 创建Grid
		Grid grid = new Grid();
		// 设置tableID
		grid.setTableId("");
		// Grid类型
		grid.setgType("tableid");
		// 适配类型
		grid.setAdapterType(1);

		grid.setDbName("mytableName");
		grid.setEditable(true);
		grid.setCanCheck(true);
		grid.setCheckType(true);
		grid.setReadOnly(true);
		grid.setWidth(0);
		grid.setHeight(0);
		// 创建表头对象
		Head gridHead = new Head();
		// 创建列
		Column tableId = new Column("表/套表Id", "tableId");
		tableId.setVisible(false);
		tableId.setEditable(false);
		Column tableName = new Column("表/套表", "tableName");
		tableName.setEditable(false);
		tableName.setWidth(200);
		Column tableSecu = new Column("权限", "tableSecu");
		tableSecu.setEditable(false);
		tableSecu.setWidth(180);
		Column tableExt = new Column("扩展操作", "tableExt");
		tableExt.setEditable(false);
		tableExt.setWidth(120);
		Column deleteButton = new Column("操作", "deleteButton");
		deleteButton.setEditable(false);
		deleteButton.setWidth(100);
		List<Column> columnList = new ArrayList<Column>();

		columnList.add(tableId);
		columnList.add(tableName);
		columnList.add(tableSecu);
		columnList.add(tableExt);
		columnList.add(deleteButton);
		gridHead.setColumns(columnList);
		grid.setHead(gridHead);*/
		return null; 
	}

	@Override
	public Grid getTableColSecuListHead() {
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
		grid.setCanCheck(true);
		grid.setCheckType(true);
		grid.setReadOnly(true);
		grid.setWidth(0);
		grid.setHeight(0);
		// 创建表头对象
		Head gridHead = new Head();
		// 创建列
		Column colId = new Column("列ID", "colId");
		colId.setVisible(false);
		colId.setEditable(false);
		Column colName = new Column("列名称", "columnName");
		colName.setEditable(false);
		Column colSecu = new Column("权限", "baseSecu");
		colSecu.setEditable(false);
		List<Column> columnList = new ArrayList<Column>();
		columnList.add(colId);
		columnList.add(colName);
		columnList.add(colSecu);
		gridHead.setColumns(columnList);
		grid.setHead(gridHead);*/
		return null;
	}

	@Override
	public Grid getTableRowSecuListHead() {
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
		grid.setCanCheck(true);
		grid.setCheckType(true);
		grid.setReadOnly(true);
		grid.setWidth(0);
		grid.setHeight(0);
		// 创建表头对象
		Head gridHead = new Head();
		// 创建列
		Column tableId = new Column("表Id", "tableId");
		tableId.setVisible(false);
		tableId.setEditable(false);
		Column rowSecuButton = new Column("行条件", "rowSecuButton");
		rowSecuButton.setEditable(false);
		Column rowSecu = new Column("权限", "baseSecu");
		rowSecu.setEditable(false);
		List<Column> columnList = new ArrayList<Column>();
		columnList.add(tableId);
		columnList.add(rowSecuButton);
		columnList.add(rowSecu);
		gridHead.setColumns(columnList);
		grid.setHead(gridHead);*/
		return null;
	}

	@Override
	public Map<String, List<TreeNode>> getRoleToAgencyList(String roleId,String appId,String manType) {
		
		String dbNameForAgency = dataAuthService.getTableDatabaseNameBy(appId, Constants.COMMON_DictTAPPCODE_TABLETYPE_AGENCY);
		Map param = new HashMap();
		param.put("dbNameForAgency", dbNameForAgency);
		param.put("roleId",roleId);
		param.put("appId",appId);  
		param.put("userId", SecureUtil.getCurrentUser().getGuid());
		String agencyId = SecureUtil.getCurrentUser().getUpagencyid();
		param.put("agencyId", agencyId);
		manType = "0".equals(manType)?"0":"1";
		param.put("manType", manType);
		Map<String, List<TreeNode>> roleToAgencyList = new HashMap<String, List<TreeNode>>();
		//1.获得已选择单位
		List<TreeNode> selectedList = secuTAgencyMapper.getSelectedRoleToAgency(param);
		//2.获得未选择单位
		List<TreeNode> unSelectedList = secuTAgencyMapper.getUnSelectRoleToAgency(param);  
		roleToAgencyList.put("selected", selectedList); 
		roleToAgencyList.put("unselect", unSelectedList);
		return roleToAgencyList;
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void addSelectedAgencyToRole(List<Map> selectAgencyList,String roleId,String appId,String manType) throws Exception{
		for(Map selectAgency: selectAgencyList ){
			selectAgency.put("roleId", roleId);
			selectAgency.put("appId", appId);
			selectAgency.put("manType", manType); 
			secuTAgencyMapper.addSelectedAgencyToRole(selectAgency);
		}
		// 缓存key
		dataCacheService.put(new String[]{ SecuCacheKey.CACHE_KEY_SECU }, null);//清缓存
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void removeSelectedAgencyToRole(List<Map> unselectAgencyList,
			String roleId, String appId, String manType) throws Exception{
		Map param = new HashMap();
		param.put("unselectAgencyList", unselectAgencyList);
		param.put("roleId", roleId);
		param.put("appId", appId);
		param.put("manType", manType);
		secuTAgencyMapper.removeSelectedAgencyToRole(param);
		
		dataCacheService.put(new String[]{ SecuCacheKey.CACHE_KEY_SECU }, null);//清缓存
	}

	@Override
	public List<SecuTTablePO> getTableSecuList(String roleId,String appId) {
		Map params = new HashMap();
		params.put("roleId", roleId);
		params.put("appId", appId);
		return secuTTableMapper.getTableSecuList(params); 
	}

	@Override
	public List<SecuTColPO> getColLimitListByTableId(String tableId,String roleId) {
		Map<String,String> param = new HashMap<String,String>();
		param.put("tableId", tableId);
		param.put("roleId", roleId);
		return secuTColMapper.getColLimitListByTableId(param);
	}

	@Override
	public List<SecuTRowPO> getRowLimitListByTableId(String tableId,
			String roleId) {
		Map<String,String> param = new HashMap<String,String>();
		param.put("tableId", tableId);
		param.put("roleId", roleId);
		List<SecuTRowPO> rowLimits = secuTRowMapper.getRowLimitListByTableId(param);
		if(rowLimits.size() != 2){
			rowLimits = handleRowLimits(rowLimits,tableId,roleId);
		}
		return rowLimits;
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	private List<SecuTRowPO> handleRowLimits(List<SecuTRowPO> rowLimits,String tableId,String roleId) {
		List<SecuTRowPO> finalRowLimits = new ArrayList<SecuTRowPO>();
		if(rowLimits.size() ==0){
			SecuTRowPO rowLimitForReadonly =new SecuTRowPO();
			rowLimitForReadonly.setBaseSecu("2");
			rowLimitForReadonly.setManId(roleId);
			rowLimitForReadonly.setManType("1");
			rowLimitForReadonly.setShowWhere("");
			rowLimitForReadonly.setSqlWhere("");
			rowLimitForReadonly.setTableId(tableId);
			SecuTRowPO rowLimitForHidden =new SecuTRowPO();
			rowLimitForHidden.setBaseSecu("1");
			rowLimitForHidden.setManId(roleId);
			rowLimitForHidden.setManType("1");
			rowLimitForHidden.setShowWhere("");
			rowLimitForHidden.setSqlWhere("");
			rowLimitForHidden.setTableId(tableId);
			finalRowLimits.add(rowLimitForReadonly);
			finalRowLimits.add(rowLimitForHidden);
		}else{
			SecuTRowPO secuTRow = rowLimits.get(0);
			finalRowLimits.add(secuTRow);
			if("1".equals(secuTRow.getBaseSecu())){
				SecuTRowPO rowLimitForReadonly =new SecuTRowPO();
				rowLimitForReadonly.setBaseSecu("2");
				rowLimitForReadonly.setManId(roleId);
				rowLimitForReadonly.setManType("1");
				rowLimitForReadonly.setShowWhere("");
				rowLimitForReadonly.setSqlWhere("");
				rowLimitForReadonly.setTableId(tableId);
				finalRowLimits.add(rowLimitForReadonly);
			}else{
				SecuTRowPO rowLimitForHidden =new SecuTRowPO();
				rowLimitForHidden.setBaseSecu("1");
				rowLimitForHidden.setManId(roleId);
				rowLimitForHidden.setManType("1");
				rowLimitForHidden.setShowWhere("");
				rowLimitForHidden.setSqlWhere("");
				rowLimitForHidden.setTableId(tableId);
				finalRowLimits.add(rowLimitForHidden);
			}
		}
		return finalRowLimits;
	}

	@Override
	public Grid getTableColListHead() {
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
		grid.setCanCheck(true);
		grid.setCheckType(true);
		grid.setReadOnly(true);
		grid.setWidth(0);
		grid.setHeight(0);
		// 创建表头对象
		Head gridHead = new Head();
		// 创建列
		Column colId = new Column("列ID", "columnid");
		colId.setVisible(false);
		Column colName = new Column("要素名称", "name");
		colName.setWidth(230);
		List<Column> columnList = new ArrayList<Column>();
		columnList.add(colId);
		columnList.add(colName);
		gridHead.setColumns(columnList);
		grid.setHead(gridHead);*/
		return null;
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveTableRowLimit(Map<String, String> param) throws Exception{
		String sqlWhere = param.get("sqlWhere");
		String tableId = param.get("tableId");
		List<DictTFactorPO> factors = dictTFactorService.getDictTFactorByTableidAndType(tableId, "1");
		String limitCon = sqlWhere;
		String limitConFunc = sqlWhere; 
		limitCon = transferLimitToLimitCon(limitCon,sqlWhere,factors);
		limitConFunc = transferLimitToLimitConFunc(limitConFunc,sqlWhere,factors);
		param.put("sqlWhere", limitCon);
		param.put("showWhere", limitConFunc);
		secuTRowMapper.saveTableRowLimit(param);
		dataCacheService.put(new String[]{ SecuCacheKey.CACHE_KEY_SECU }, null);//清缓存
	} 

	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void updateTableRowLimit(Map<String, String> param) throws Exception{
		String sqlWhere = param.get("sqlWhere");
		String tableId = param.get("tableId");
		List<DictTFactorPO> factors = dictTFactorService.getDictTFactorByTableidAndType(tableId, "1");
		String limitCon = sqlWhere;
		String limitConFunc = sqlWhere; 
		limitCon = transferLimitToLimitCon(limitCon,sqlWhere,factors);
		limitConFunc = transferLimitToLimitConFunc(limitConFunc,sqlWhere,factors);
		param.put("sqlWhere", limitCon);
		param.put("showWhere", limitConFunc);
		secuTRowMapper.updateTableRowLimit(param);
		dataCacheService.put(new String[]{ SecuCacheKey.CACHE_KEY_SECU }, null);//清缓存
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void cancelTableRowLimitLimit(Map<String, String> param) throws Exception{
		secuTRowMapper.cancelTableRowLimitLimit(param);
		dataCacheService.put(new String[]{ SecuCacheKey.CACHE_KEY_SECU }, null);//清缓存
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveTableSecuLimit (
			List<Map<String, String>> editTableColLimitList,
			List<Map<String, String>> addTableSecuLimitList,
			List<Map<String, String>> editTableSecuLimitList, String roleId) throws Exception {
		
		try{
			saveTableSecuLimitList(addTableSecuLimitList,editTableSecuLimitList,roleId);
			saveEditTableColLimitList(editTableColLimitList,roleId);
		}catch(Exception e ){
			throw e;
		}
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	private void saveTableSecuLimitList(
			List<Map<String, String>> addTableSecuLimitList,
			List<Map<String, String>> editTableSecuLimitList, String roleId) throws Exception  {
		JSONObject object;
		try {
			if (editTableSecuLimitList != null && editTableSecuLimitList.size() != 0) {
				String editJsonData;
				Map<String,String> editData = new HashMap<String,String>();
				for (Map<String, String> obj : editTableSecuLimitList) {
					editData.put("tableId", obj.get("tableId"));
					editData.put("isSuit", obj.get("isSuit"));
					editData.put("roleId", obj.get("manId"));
					editData.put("extAdd", obj.get("extAdd"));
					editData.put("extDel", obj.get("extDel"));
					editData.put("extUpdate", obj.get("extUpdate"));
					editData.put("baseSecu", obj.get("baseSecu"));
					secuTTableMapper.updateSecuTTableData(editData);
				}
			}
			if (addTableSecuLimitList != null && addTableSecuLimitList.size() != 0) {
				Map<String, String> addData;
				for (Object obj : addTableSecuLimitList) {
					addData = (Map<String, String>) obj;
					addData.put("roleId", roleId);
					secuTTableMapper.insertSecuTTableData(addData);
				}
			}
			dataCacheService.put(new String[]{ SecuCacheKey.CACHE_KEY_SECU }, null);//清缓存
		} catch (Exception e) {
			throw e;
		}
		
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	private void saveEditTableColLimitList(
			List<Map<String, String>> editTableColLimitList, String roleId) throws Exception {
		JSONObject object;
		if (editTableColLimitList != null && editTableColLimitList.size() != 0) {
			String editJsonData;
			String baseSecu="";
			String tableId="";
			String colId="";
			String manId="";
			String isleaf = "";//是否子节点 --20160906
			Map deleteData = new HashMap();
			List deleteColIds = new ArrayList();
			for(Map<String,String> obj : editTableColLimitList){
				colId = obj.get("colId");
				Map columnSecu = new HashMap();
				columnSecu.put("colId", colId);
				deleteColIds.add(columnSecu);
			}
			tableId = editTableColLimitList.get(0).get("tableId");
			deleteData.put("tableId",tableId);
			deleteData.put("manId",roleId);
			deleteData.put("editTableColLimitList", deleteColIds);
			secuTColMapper.deleteSecuTColData(deleteData);
			Map<String,String> editData = new HashMap<String,String>();
			for (Map<String,String> obj : editTableColLimitList) {
				baseSecu = obj.get("baseSecu");
				tableId = obj.get("tableId");
				colId = obj.get("colId");
				manId =obj.get("manId");
				isleaf = obj.get("isleaf");//是否子节点 --20160906
				editData.put("tableId", tableId);
				editData.put("colId", colId);
				editData.put("roleId", manId);
				editData.put("baseSecu", baseSecu);
				//子节点保存，中间级不保存 --20160906
				if(!"3".equals(baseSecu) && "1".equals(isleaf)){
					secuTColMapper.insertSecuTColData(editData);
				}
			}
		}
		dataCacheService.put(new String[]{ SecuCacheKey.CACHE_KEY_SECU }, null);//清缓存
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void deleteTableSecuLimit(String roleId, String appId,
			String tableId, String isSuit) throws Exception{
		Map param = new HashMap();
		param.put("roleId", roleId);
		param.put("appId", appId);
		param.put("tableId", tableId);
		param.put("isSuit", isSuit);
		secuTTableMapper.deleteTableSecuLimit(param);
		if("0".equals(isSuit)){
			secuTColMapper.deleteColSecuLimit(param);
			secuTRowMapper.deleteRowSecuLimit(param);
		}
		dataCacheService.put(new String[]{ SecuCacheKey.CACHE_KEY_SECU }, null);//清缓存
	}

	@Override
	public void verifyTableRowLimit(Map<String, String> param) {
		String sqlWhere  = param.get("sqlWhere");
		String tableId = param.get("tableId");
		List<DictTFactorPO> factors = dictTFactorService.getDictTFactorByTableidAndType(tableId, "1");
		String limitCon = sqlWhere;
		limitCon = transferLimitToLimitCon(limitCon,sqlWhere,factors);
		param.put("sqlWhere", limitCon);
		secuTTableMapper.verifyTableRowLimit(param);
		dataCacheService.put(new String[]{ SecuCacheKey.CACHE_KEY_SECU }, null);//清缓存
	}

	@Override
	public List<Map<String, String>> getRoleTreeData(String appId, String userId,String roleType) {
		
		String orgId = SecureUtil.getCurrentUser().getUpagencyid();
		Map param = new HashMap();
		param.put("orgId", orgId);
		param.put("roleType", roleType);
		return secuTAgencyMapper.getRoleListByOrgId(param);
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
	public Object transferTableRowLimit(String tableId, String showWhere) {
		List<DictTFactorPO> factors = dictTFactorService.getDictTFactorByTableidAndType(tableId, "1");
		String limitConFunc =showWhere;
		String finalLimitConFunc=limitConFunc;
		finalLimitConFunc = reverseLimitConFuncForDisplay(finalLimitConFunc,limitConFunc,factors);
		return  finalLimitConFunc;
		
	}

	@Override
	public Table setTableSecuDefine() {
		// TODO Auto-generated method stub
		Table table = new Table();
		table.setAppID("BGT");
		table.setTableName("角色对表权限");
		table.setTableID("mytableName");
		table.setTableDBName("mytableName");
		List<Column> columnList = new ArrayList<Column>(); 
		Column tableIdCol=new Column();
		tableIdCol.setColumnID("tableId");
		tableIdCol.setColumnName("tableId");
		tableIdCol.setColumnDBName("tableId"); 
		tableIdCol.setAlias("表/套表Id");
		tableIdCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		tableIdCol.setShowType(ShowType.SHOW_TYPE_HTML);
		tableIdCol.setDataLength(32);
		tableIdCol.setKey(true);
		tableIdCol.setOrderID(0);
		tableIdCol.setVisible(false);
		columnList.add(tableIdCol);
		Column tableNameCol=new Column();
		tableNameCol.setColumnID("tableName");
		tableNameCol.setColumnName("tableName");
		tableNameCol.setColumnDBName("tableName");
		tableNameCol.setAlias("表/套表");
		tableNameCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		tableNameCol.setShowType(ShowType.SHOW_TYPE_HTML);
		tableNameCol.setDataLength(32);
		tableNameCol.setKey(false);
		tableNameCol.setOrderID(1);
		tableNameCol.setVisible(true);
		tableNameCol.setReadOnly(true); 
		tableNameCol.setWidth(250);  
		columnList.add(tableNameCol);
		Column tableSecuCol=new Column();
		tableSecuCol.setColumnID("tableSecu");
		tableSecuCol.setColumnName("tableSecu");
		tableSecuCol.setColumnDBName("tableSecu");
		tableSecuCol.setAlias("权限");
		tableSecuCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		tableSecuCol.setShowType(ShowType.SHOW_TYPE_HTML);
		tableSecuCol.setDataLength(32);
		tableSecuCol.setKey(false);
		tableSecuCol.setOrderID(2);
		tableSecuCol.setReadOnly(true);
		tableSecuCol.setVisible(true);
		tableSecuCol.setWidth(180);      
		columnList.add(tableSecuCol);
		Column tableExtCol=new Column();
		tableExtCol.setColumnID("tableExt");
		tableExtCol.setColumnName("tableExt");
		tableExtCol.setColumnDBName("tableExt");
		tableExtCol.setAlias("扩展操作");
		tableExtCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		tableExtCol.setShowType(ShowType.SHOW_TYPE_HTML);
		tableExtCol.setDataLength(32);
		tableExtCol.setKey(false);
		tableExtCol.setOrderID(3);
		tableExtCol.setVisible(true);
		tableExtCol.setReadOnly(true);
		tableExtCol.setWidth(130);     
		columnList.add(tableExtCol);
		/*Column tableOptCol=new Column();
		tableOptCol.setColumnID("deleteButton");
		tableOptCol.setColumnName("deleteButton"); 
		tableOptCol.setColumnDBName("deleteButton");
		tableOptCol.setAlias("操作");
		tableOptCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		tableOptCol.setShowType(ShowType.SHOW_TYPE_HTML);
		tableOptCol.setDataLength(32);
		tableOptCol.setKey(false);
		tableOptCol.setOrderID(4); 
		tableOptCol.setVisible(true);
		tableOptCol.setReadOnly(true);
		tableOptCol.setWidth(150);  
		columnList.add(tableOptCol); */
		table.setColumnList(columnList);
		return table;
	}

	@Override
	public Table setColSecuDefine() {
		Table table = new Table();
		table.setAppID("BGT");
		table.setTableName("角色对列权限");
		table.setTableID("mytableName");
		table.setTableDBName("mytableName");
		List<Column> columnList = new ArrayList<Column>(); 
		Column colIdCol=new Column();
		colIdCol.setColumnID("colId");
		colIdCol.setColumnName("colId");
		colIdCol.setColumnDBName("colId"); 
		colIdCol.setAlias("列ID");
		colIdCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		colIdCol.setShowType(ShowType.SHOW_TYPE_HTML);
		colIdCol.setDataLength(32);
		colIdCol.setKey(true);
		colIdCol.setOrderID(0);
		colIdCol.setVisible(false);
		columnList.add(colIdCol);
		Column columnNameCol=new Column();
		columnNameCol.setColumnID("columnName");
		columnNameCol.setColumnName("columnName");
		columnNameCol.setColumnDBName("columnName");
		columnNameCol.setAlias("列名称");
		columnNameCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		columnNameCol.setShowType(ShowType.SHOW_TYPE_HTML);
		columnNameCol.setDataLength(32);
		columnNameCol.setKey(false);
		columnNameCol.setOrderID(1);
		columnNameCol.setVisible(true);
		columnNameCol.setReadOnly(true);
		columnNameCol.setWidth(200); 
		columnList.add(columnNameCol);
		Column baseSecuCol=new Column();
		baseSecuCol.setColumnID("baseSecu");
		baseSecuCol.setColumnName("baseSecu");
		baseSecuCol.setColumnDBName("baseSecu");
		baseSecuCol.setAlias("权限");
		baseSecuCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		baseSecuCol.setShowType(ShowType.SHOW_TYPE_HTML);
		baseSecuCol.setDataLength(32);
		baseSecuCol.setKey(false);
		baseSecuCol.setWidth(200);  
		baseSecuCol.setOrderID(2);
		baseSecuCol.setVisible(true);
		baseSecuCol.setReadOnly(true);  
		columnList.add(baseSecuCol); 
		table.setColumnList(columnList);
		return table;
	}

	@Override
	public Table setRowSecuSecuDefine() {
		Table table = new Table();
		table.setAppID("BGT"); 
		table.setTableName("行权限");
		table.setTableID("mytableName");
		table.setTableDBName("mytableName");
		List<Column> columnList = new ArrayList<Column>(); 
		Column tableIdCol=new Column();
		tableIdCol.setColumnID("tableId");
		tableIdCol.setColumnName("tableId");
		tableIdCol.setColumnDBName("tableId"); 
		tableIdCol.setAlias("表Id");
		tableIdCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		tableIdCol.setShowType(ShowType.SHOW_TYPE_HTML);
		tableIdCol.setDataLength(32);
		tableIdCol.setKey(true);
		tableIdCol.setOrderID(0);
		tableIdCol.setVisible(false); 
		columnList.add(tableIdCol);
		Column rowSecuBtnCol=new Column();
		rowSecuBtnCol.setColumnID("rowSecuButton");
		rowSecuBtnCol.setColumnName("rowSecuButton");
		rowSecuBtnCol.setColumnDBName("rowSecuButton");
		rowSecuBtnCol.setAlias("行条件");
		rowSecuBtnCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		rowSecuBtnCol.setShowType(ShowType.SHOW_TYPE_HTML);
		rowSecuBtnCol.setDataLength(32);
		rowSecuBtnCol.setKey(false);
		rowSecuBtnCol.setOrderID(1);
		rowSecuBtnCol.setVisible(true);
		rowSecuBtnCol.setReadOnly(true); 
		rowSecuBtnCol.setWidth(200);  
		columnList.add(rowSecuBtnCol); 
		Column baseSecuCol=new Column();
		baseSecuCol.setColumnID("baseSecu");
		baseSecuCol.setColumnName("baseSecu");
		baseSecuCol.setColumnDBName("baseSecu");
		baseSecuCol.setAlias("权限");
		baseSecuCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		baseSecuCol.setShowType(ShowType.SHOW_TYPE_HTML);
		baseSecuCol.setDataLength(32);
		baseSecuCol.setKey(false);
		baseSecuCol.setWidth(200);  
		baseSecuCol.setOrderID(2);
		baseSecuCol.setVisible(true);
		baseSecuCol.setReadOnly(true);  
		columnList.add(baseSecuCol); 
		table.setColumnList(columnList);
		return table; 
	}

	@Override
	public Table setColTableDefine() {
		// TODO Auto-generated method stub
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
		colNameCol.setColumnID("columnName");
		colNameCol.setColumnName("columnName");
		colNameCol.setColumnDBName("columnName");  
		colNameCol.setAlias("要素名称");
		colNameCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		colNameCol.setShowType(ShowType.SHOW_TYPE_HTML);
		colNameCol.setDataLength(32);
		colNameCol.setKey(true);
		colNameCol.setOrderID(0);
		colNameCol.setVisible(true);  
		colNameCol.setWidth(230);
		columnList.add(colNameCol);
		table.setColumnList(columnList);
		return table;
	}

	@Override
	public List<Map<String, String>> getUserTreeData() {
		// TODO Auto-generated method stub\
		Map param = new HashMap();
		return secuTAgencyMapper.getUserList(param); 
	}

	/**
	 * 复制权限
	 * @param appID
	 * @param fromRoleID
	 * @param toRoleIDs
	 * @throws ServiceException
	 */
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void copy(String appID, String fromRoleID, String toRoleIDs) throws ServiceException {
		try{
			String[] toRoleIDArray = toRoleIDs.split(",");
			for(int i = 0; i < toRoleIDArray.length; i++){
				String toRoleID = toRoleIDArray[i];
				String sql = "delete from secu_t_agency where appid = '"+appID+"' and manid = '" +toRoleID+ "'";
				secuTAgencyMapper.updateSql(sql);
				sql = "insert into secu_t_agency (AGENCYID,APPID,GUID,MANID,MANTYPE) select AGENCYID,'"+appID+"',SYS_GUID(),'"+toRoleID+"',MANTYPE from secu_t_agency where manid = '"+fromRoleID+"'";
				secuTAgencyMapper.updateSql(sql);
				
				sql = "delete from secu_t_table where manid = '" +toRoleID+ "'";
				secuTAgencyMapper.updateSql(sql);
				sql = "insert into secu_t_table (BASESECU,EXTADD,EXTDEL,EXTUPDATE,GUID,ISSUIT,MANID,MANTYPE,TABLEID) select BASESECU,EXTADD,EXTDEL,EXTUPDATE,SYS_GUID(),ISSUIT,'"+toRoleID+"',MANTYPE,TABLEID from secu_t_table where manid = '"+fromRoleID+"'";
				secuTAgencyMapper.updateSql(sql);
				
				sql = "delete from secu_t_col where manid = '" +toRoleID+ "'";
				secuTAgencyMapper.updateSql(sql);
				sql = "insert into secu_t_col (BASESECU,COLID,GUID,MANID,MANTYPE,TABLEID) select BASESECU,COLID,SYS_GUID(),'"+toRoleID+"',MANTYPE,TABLEID from secu_t_col where manid = '"+fromRoleID+"'";
				secuTAgencyMapper.updateSql(sql);
				
				sql = "delete from secu_t_row where manid = '" +toRoleID+ "'";
				secuTAgencyMapper.updateSql(sql);
				sql = "insert into secu_t_row (BASESECU,GUID,MANID,MANTYPE,SHOWWHERE,SQLWHERE,TABLEID) select BASESECU,SYS_GUID(),'"+toRoleID+"',MANTYPE,SHOWWHERE,SQLWHERE,TABLEID from secu_t_row where manid = '"+fromRoleID+"'";
				secuTAgencyMapper.updateSql(sql);
			}
			
			// 清除所有业务表类缓存
			dataCacheService.put(new String[]{ SecuCacheKey.CACHE_KEY_SECU }, null);//清缓存
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, "复制权限错误", false);
		}
	}

}
