package commons.setting.billdef.service.impl;

import gov.mof.fasp2.ca.user.dto.UserDTO;
import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.Constants;
import com.tjhq.commons.dao.UtilsMapper;
import com.tjhq.commons.dict.dao.DictTAppregisterMapper;
import com.tjhq.commons.dict.external.po.DictTAppregisterPO;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.po.DictTModelcodePO;
import com.tjhq.commons.dict.external.po.DictTSuitPO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.dict.external.service.IDictTModelcodeService;
import com.tjhq.commons.dict.external.service.IDictTSuitService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.constants.ShowType;
import com.tjhq.commons.inputcomponent.grid.settinggrid.service.ISettingGridService;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.utils.JSTypeUtils;
import com.tjhq.commons.jackson.ObjectMapper;
import com.tjhq.commons.setting.billdef.dao.IBillDefinedMapper;
import com.tjhq.commons.setting.billdef.po.BillDefinedPO;
import com.tjhq.commons.setting.billdef.service.IBillDefinedService;
import com.tjhq.commons.setting.formula.service.IFormulaService;
import com.tjhq.commons.setting.input.po.TreeNode;
import com.tjhq.commons.setting.input.web.ConverTables;

@Service
@Transactional(readOnly=true)
public class BillDefinedService implements IBillDefinedService {
	@Resource
	private ISettingGridService settingGridService;
	@Resource
	private IBillDefinedMapper billMapper;
	@Resource
	private UtilsMapper utilsMapper;
	@Resource
	private IDictTModelService dictTModelService;
	@Resource
	private IDictTFactorService dictTFactorService;
	@Resource
	private DictTAppregisterMapper dictTAppregisterMapper;
	@Resource
	private IDictTSuitService dictTSuitService;
	@Resource
	private IDictTModelcodeService modelCodeService;
	@Resource
	private IFormulaService formulaService;

	@Override
	public Object getBillTableDefined(String appId) throws ServiceException {
		Table table = new Table();
		table.setAppID(appId);
		table.setTableName("记账定义表");
		table.setTableID("DICT_T_BILLDEF");
		table.setTableDBName("DICT_T_BILLDEF");
		table.setColumnList(setColumn());
		UserDTO user = SecureUtil.getCurrentUser();
		settingGridService.initStructure(table, user.getGuid());
		return table;
	}
	
	/**
	 * 设置列属性
	 * @param table
	 * @return
	 */
	private List<Column> setColumn(){
		List<Column> cols = new ArrayList<Column>();
		//guid
		Column guid=new Column();
		guid.setColumnID("guid");
		guid.setColumnName("guid");
		guid.setColumnDBName("guid");
		guid.setDataLength(32);
		guid.setKey(true);
		guid.setVisible(false);
		
		//appId
		Column appId=new Column();
		appId.setColumnID("appId");
		appId.setColumnName("appId");
		appId.setColumnDBName("appId");
		appId.setDataLength(32);
		appId.setKey(true);
		appId.setVisible(false);
		
		//billCol
		Column billCol=new Column();
		billCol.setColumnID("billCol");
		billCol.setColumnName("billCol");
		billCol.setColumnDBName("billCol");
		billCol.setDataLength(32);
		billCol.setKey(true);
		billCol.setVisible(false);
		
		//sumCol
		Column sumCol=new Column();
		sumCol.setColumnID("sumCol");
		sumCol.setColumnName("sumCol");
		sumCol.setColumnDBName("sumCol");
		sumCol.setDataLength(32);
		sumCol.setKey(true);
		sumCol.setVisible(false);
		
		//isIncr
		Column isIncr=new Column();
		isIncr.setColumnID("isIncr");
		isIncr.setColumnName("isIncr");
		isIncr.setColumnDBName("isIncr");
		isIncr.setDataLength(32);
		isIncr.setKey(true);
		isIncr.setVisible(false);
		
		//includeDel
		Column includeDel=new Column();
		includeDel.setColumnID("includeDel");
		includeDel.setColumnName("includeDel");
		includeDel.setColumnDBName("includeDel");
		includeDel.setDataLength(32);
		includeDel.setKey(true);
		includeDel.setVisible(false);
		
		
		//billColName
		Column billColName=new Column();
		billColName.setColumnID("billColName");
		billColName.setColumnName("billColName");
		billColName.setColumnDBName("billColName");
		billColName.setAlias("记账字段名称");
		billColName.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		billColName.setShowType(ShowType.SHOW_TYPE_HTML);
		billColName.setDataLength(200);
		
		//billDefName
		Column billDefName=new Column();
		billDefName.setColumnID("billDefName");
		billDefName.setColumnName("billDefName");
		billDefName.setColumnDBName("billDefName");
		billDefName.setAlias("名称");
		billDefName.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		billDefName.setShowType(ShowType.SHOW_TYPE_HTML);
		billDefName.setDataLength(200);
		billDefName.setKey(true);
		
		//offsetColName
		Column offsetColName=new Column();
		offsetColName.setColumnID("offsetColName");
		offsetColName.setColumnName("offsetColName");
		offsetColName.setColumnDBName("offsetColName");
		offsetColName.setAlias("冲抵字段");
		offsetColName.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		offsetColName.setShowType(ShowType.SHOW_TYPE_HTML);		
		offsetColName.setDataLength(200);
		
		//sumColName
		Column sumColName=new Column();
		sumColName.setColumnID("sumColName");
		sumColName.setColumnName("sumColName");
		sumColName.setColumnDBName("sumColName");
		sumColName.setAlias("金额字段");
		sumColName.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		sumColName.setShowType(ShowType.SHOW_TYPE_HTML);		
		sumColName.setDataLength(200);
		
		//srcDbName
		Column srcDbName=new Column();
		srcDbName.setColumnID("srcDbName");
		srcDbName.setColumnName("srcDbName");
		srcDbName.setColumnDBName("srcDbName");
		srcDbName.setAlias("物理表名称");
		srcDbName.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		srcDbName.setShowType(ShowType.SHOW_TYPE_HTML);		
		srcDbName.setDataLength(200);
		
		//srcName
		Column srcName=new Column();
		srcName.setColumnID("srcName");
		srcName.setColumnName("srcName");
		srcName.setColumnDBName("srcName");
		srcName.setAlias("原始来源表名称");
		srcName.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		srcName.setShowType(ShowType.SHOW_TYPE_HTML);		
		srcName.setDataLength(200);
		
		//tgtName
		Column tgtName=new Column();
		tgtName.setColumnID("tgtName");
		tgtName.setColumnName("tgtName");
		tgtName.setColumnDBName("tgtName");
		tgtName.setAlias("目标表名称");
		tgtName.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		tgtName.setShowType(ShowType.SHOW_TYPE_HTML);		
		tgtName.setDataLength(200);
		
		//tgtTabId
		Column tgtTabId=new Column();
		tgtTabId.setColumnID("tgtTabId");
		tgtTabId.setColumnName("tgtTabId");
		tgtTabId.setColumnDBName("tgtTabId");
		tgtTabId.setDataLength(32);
		tgtTabId.setKey(true);
		tgtTabId.setVisible(false);
		
		//srcWhere
		Column srcWhere=new Column();
		srcWhere.setColumnID("srcWhere");
		srcWhere.setColumnName("srcWhere");
		srcWhere.setColumnDBName("srcWhere");
		srcWhere.setKey(true);
		srcWhere.setVisible(false);
		
		//checkSql
		Column checkSql=new Column();
		checkSql.setColumnID("checkSql");
		checkSql.setColumnName("checkSql");
		checkSql.setColumnDBName("checkSql");
		checkSql.setVisible(false);
		
		//offsetCol
		Column offsetCol=new Column();
		offsetCol.setColumnID("offsetCol");
		offsetCol.setColumnName("offsetCol");
		offsetCol.setColumnDBName("offsetCol");
		offsetCol.setVisible(false);
		
		//srcDbTabId
		Column srcDbTabId=new Column();
		srcDbTabId.setColumnID("srcDbTabId");
		srcDbTabId.setColumnName("srcDbTabId");
		srcDbTabId.setColumnDBName("srcDbTabId");
		srcDbTabId.setVisible(false);
		
		//srcTabId
		Column srcTabId=new Column();
		srcTabId.setColumnID("srcTabId");
		srcTabId.setColumnName("srcTabId");
		srcTabId.setColumnDBName("srcTabId");
		srcTabId.setVisible(false);
			
		//approvedCols
		Column approvedCols=new Column();
		approvedCols.setColumnID("approvedCols");
		approvedCols.setColumnName("approvedCols");
		approvedCols.setColumnDBName("approvedCols");
		approvedCols.setVisible(false);
		
		cols.add(guid);
		cols.add(appId);
		cols.add(billCol);
		cols.add(sumCol);
		cols.add(isIncr);
		cols.add(includeDel);
		cols.add(billDefName);
		cols.add(srcName);
		cols.add(srcDbName);
		cols.add(tgtName);	
		cols.add(offsetColName);
		cols.add(billColName);
		cols.add(sumColName);
		cols.add(tgtTabId);
		cols.add(srcWhere);
		cols.add(checkSql);
		cols.add(offsetCol);
		cols.add(srcDbTabId);
		cols.add(srcTabId);
		cols.add(approvedCols);
		return cols;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getBillTableData(Grid data) throws ServiceException {
		try {
			List<BillDefinedPO> list = billMapper.getBillDefList(data.getExtProperties());
			String listStr = (new ObjectMapper()).writeValueAsString(list);
			List<Map<String, Object>> dataList = (List<Map<String, Object>>)(new ObjectMapper()).readJson(listStr, List.class);
			settingGridService.transferGridData(dataList, data.getPageInfo());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_1H202, e.getCause().getMessage(), false);
		}
		return data.getPageInfo();
	}

	/**
	 * 获取唯一主键
	 * @return
	 */
	private String getSysGUID(){
		String guid = utilsMapper.getDBUniqueID();
		return guid;
	}

	@Override
	public List<Map<String, Object>> getTableTree(String all, String appId, String isDb)
			throws ServiceException {
		boolean isAll = true;
		if(!"1".equals(all)){
			isAll = false;
		}
		List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
		List<DictTAppregisterPO> apps=dictTAppregisterMapper.getAllDictTAppregister();
		for(DictTAppregisterPO app : apps){
			Map<String, Object> treeNode = new HashMap<String, Object>();
			treeNode.put("id", app.getAppid());
			treeNode.put("pId", "0");
			treeNode.put("name", app.getAppname());
			treeNode.put("type", "1");
			treeNode.put("dataType", "sys");
			treeNode.put("open", true);
			treeNode.put("isParent", true);
			if(isAll){
				appId = app.getAppid();
			}
			//引用 获取 所有套表及下属表
			List<DictTSuitPO> suitList = dictTSuitService.getDictTSuits(appId, "0", true);
			
			if(!isAll){
				if(app.getAppid().equals(appId)){
					treeList.add(treeNode);
					this.convertTreeData(treeList, suitList, isDb);
					break;
				}
			}else{
				treeList.add(treeNode);
				this.convertTreeData(treeList, suitList, isDb);
			}
		}
		return treeList;
	}

	/**
	 * 转换物理表数据
	 * @param treeList
	 * @param appId
	 * @return
	 */
	private List<Map<String, Object>> convertTreeData(List<Map<String, Object>> treeList, List<DictTSuitPO> suitList, String isDb){
		for(DictTSuitPO suit : suitList){
			Map<String, Object> treeNode = new HashMap<String, Object>();
			treeNode.put("id", suit.getSuitid());
			treeNode.put("pId", suit.getAppid());
			treeNode.put("name", suit.getSuitname());
			treeNode.put("type", "2");
			treeNode.put("dataType", "suit");
			treeNode.put("open", false);
			treeNode.put("isParent", true);
			
			//物理表
			List<DictTModelPO> modelList = suit.getDictTModelList();
		    if(ConverTables.isNotNullList(modelList)){
		    	treeList.add(treeNode);
		    }
			if(ConverTables.isNotNullList(modelList)){
			    for(DictTModelPO model:modelList){
			    	Map<String, Object> child = new HashMap<String, Object>();
					child.put("id", model.getTableid());
					child.put("name", model.getName());
					child.put("pId", model.getSuitid());
					child.put("type", "3");
					child.put("dataType", model.getTabletype());
					child.put("isParent", false);
					//判断是否为对应物理表字段
					if("1".equals(isDb)){
						if("1".equals(model.getTabletype())){
							treeList.add(child);
						}
					}else{
						treeList.add(child);
					}
				}	
			}
			List<DictTSuitPO> childSuit = suit.getDictTSuitList();
			if(ConverTables.isNotNullList(suitList)){					
				convertTreeData(treeList, childSuit, isDb);					
			}
		}
		
		return treeList;
	}
	@Override
	public List<Map<String, Object>> getColumnTree(String tableId)
			throws ServiceException {
		List<DictTFactorPO> columnList = dictTFactorService.getDictTFactorsByTableId(tableId);
		List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
		if(columnList!=null && columnList.size()>0){
			for(DictTFactorPO po : columnList){
				Map<String, Object> column = new HashMap<String, Object>();
				column.put("id", po.getColumnid());
				column.put("pId", po.getSuperid());
				column.put("name", po.getName());
				treeList.add(column);
			}
		}
		return treeList;
	}

	@Override
	public List<Map<String, Object>> getProcList(Map<String, Object> params)
			throws ServiceException {
		List<Map<String, Object>> list = null;
		try {
			list = billMapper.getProcList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public Map<String, Object> execProc(Map<String, Object> params) throws ServiceException {
		try {
			if(params.get("genSrcTabId")==null || "".equals(params.get("genSrcTabId"))){
				String guid = "";
				if(params.get("billDefId") != null && !params.get("billDefId").equals("")){
					String genSrcTableId = billMapper.getGenSrcTableIdByGuid(params.get("billDefId").toString());
					if(genSrcTableId != null && !genSrcTableId.equals("")){
						guid = genSrcTableId;
					}else{
						guid = this.getSysGUID();
					}
				}
//				DictTModelPO srcModel = this.dictTModelService.getDictTModelByID(params.get("srcTabId").toString(), false);
//				String srcTableName = srcModel.getDbtablename() + "_TP";
//				DictTModelPO genSrcModel = this.dictTModelService.getDictTModelByDBtableName(srcTableName, false);
//				String guid = "";
//				if(genSrcModel==null){
//					guid = this.getSysGUID();
//				}else{
//					guid = genSrcModel.getTableid();
//				}
				 
				params.put("genSrcTabId", guid);
			}
			BillDefinedPO updatePo = new BillDefinedPO();
			updatePo.setGuid(params.get("billDefId").toString());
			updatePo.setGenSrcTabId(params.get("genSrcTabId").toString());
			billMapper.updateBillDef(updatePo);
			billMapper.execProc(params);
			billMapper.deleteTgtRelaData(params.get("billDefId").toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, "生成来源表错误："+e.getCause().getMessage(), false);
		}
		return params;
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public Map<String, Object> saveData(BillDefinedPO po) throws ServiceException {
		Map<String, Object> result = new HashMap<String, Object>();
		String guid = "";
		try {
			if(po.getGuid()!=null && !po.getGuid().equals("")){
				guid = po.getGuid();
				Map<String, Object> billMap = billMapper.getBillDefById(guid).get(0);
				if(po.getApprovedCols()!=null && billMap.get("APPROVEDCOLS")!=null && !po.getApprovedCols().equals(billMap.get("APPROVEDCOLS").toString())){
					saveBasicData(po.getApprovedCols(), guid);
				}
				//处理where条件
				if(po.getWhereDesc()!=null && !"".equals(po.getWhereDesc())){
					String whereStr = transformStr(po.getWhereDesc(), po.getWhereDesc(), po.getGenSrcTabId());
					Map<String, String> paramsMap = new HashMap<String, String>();
					paramsMap.put("whereStr", whereStr);
					paramsMap.put("tableId", po.getGenSrcTabId());
					//this.checkSql(paramsMap);
					String srcWhere = this.transRefID(transRefTableCol(po.getWhereDesc()), po.getWhereDesc(), po.getGenSrcTabId());
					po.setSrcWhere(transProperty(srcWhere, srcWhere, "$"));
				}
				
				billMapper.updateBillDef(po);
			}else{
				guid = this.getSysGUID();
				po.setGuid(guid);
				billMapper.insertBillDef(po);
				saveBasicData(po.getApprovedCols(), guid);
			}
			
			result.put("guid", guid);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, e.getCause().getMessage(), false);
		}
		return result;
		
	}
	
	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public Map<String, Object> checkWhere(BillDefinedPO po) throws ServiceException {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(po.getGuid()!=null && !po.getGuid().equals("")){
				if(po.getWhereDesc()!=null && !"".equals(po.getWhereDesc())){
					String whereStr = transformStr(po.getWhereDesc(), po.getWhereDesc(), po.getGenSrcTabId());
					Map<String, String> paramsMap = new HashMap<String, String>();
					paramsMap.put("whereStr", whereStr);
					paramsMap.put("tableId", po.getGenSrcTabId());
					if(po.getCheckSql()!=null && !"".equals(po.getCheckSql()))
						paramsMap.put("aliasName", po.getCheckSql());//别名
					this.checkSql(paramsMap);
				}
				
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, e.getCause().getMessage(), false);
		}
		return result;
		
	}
	
	/**
	 * 检查设置sql条件是否合法
	 * @param paramsMap
	 * @throws ServiceException
	 */
	private void checkSql(Map<String, String> paramsMap) throws ServiceException{
		if(paramsMap.get("whereStr")!=null && !"".equals(paramsMap.get("whereStr"))){
			paramsMap.put("whereStr", clearRF(paramsMap.get("whereStr")) + " and rownum=1 ");
		}else{
			paramsMap.put("whereStr", " rownum=1 ");
		}
		
		if(paramsMap.get("tableId")!=null && !"".equals(paramsMap.get("tableId"))){
			String tableName = dictTModelService.getDictTModelByID(paramsMap.get("tableId"), false).getDbtablename();
			paramsMap.put("tableName", tableName);
		}else{
			paramsMap.put("tableName", "dual");
		}
		
		if(paramsMap.get("columns")!=null && !"".equals(paramsMap.get("columns"))){
		}else{
			paramsMap.put("columns", " count(1) ");
		}
		
		try {
			billMapper.checkSql(paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, "where条件不正确！", false);
		}
	}
	/**
	 * 清除RF().{}标记
	 * @param inputStr
	 * @return
	 */
	public String clearRF(String inputStr) {
		if(null==inputStr || "".equals(inputStr.trim())){
			return inputStr;
		}
		if(inputStr.indexOf(Constants.RF_START)!=-1){
			StringBuffer temp = new StringBuffer(50);
			Pattern p = Pattern.compile("RF\\(.+?\\)\\.");
			Matcher m= p.matcher(inputStr);
			while(m.find()){
				m.appendReplacement(temp,"");
			}
			m.appendTail(temp);
			
			return temp.toString().replace(Constants.COMCOL_START, "").replace(Constants.COMCOL_END, "");
		}else{
			return inputStr;
		}
	}
	/**
	 * 转换表单值到需要的格式
	 * @param finalStr
	 * @param str
	 * @param tableId
	 * @return
	 * @throws Exception 
	 */
	private String transformStr(String finalStr, String str, String tableId) throws ServiceException {
		
		//转换引用代码表值
		String tempStr = transRefTableCol(finalStr);
		String tempStr2 = transRef(tempStr, str, tableId);
		return transProperty(tempStr2, str, "$");
	}
	/**
	 * 将$参数名$ 转换成 $参数ID$
	 * @param finalStr
	 * @param str
	 * @param splitStr
	 * @return
	 * @throws ServiceException 
	 */
	private String transProperty(String finalStr, String str, String splitStr) throws ServiceException{
		int start = str.indexOf(splitStr, 1);
		int end = str.indexOf(splitStr, start+1);
		if (start < 0 || end < 0) {
			return finalStr;
		}
		String propName = str.substring(start+1, end);
		String propID = "";
		
		List<Map<String, Object>> propList = billMapper.getPropData();
		if(propList.size()>0){
			for(Map<String, Object> map : propList){
				if(propName.equals(map.get("COLUMNNAME").toString())){
					propID = map.get("DBCOLNAME").toString();
					break;
				}
			}
		}
		if(propID.equals("")){
			throw new ServiceException(ExceptionCode.ERR_00000, "动态参数设置不正确！", false);
		}
		finalStr = finalStr.replace(splitStr + propName + splitStr, splitStr + propID + splitStr);
		str = str.replace(splitStr + propName + splitStr, "");
		return transProperty(finalStr, str, splitStr);
	}
	/**
	 * 将 中文列 转换为 dbColumnName
	 * @param finalStr
	 * @param str
	 * @param tableId
	 * @return
	 */
	private String transRef(String finalStr, String str, String tableId) {
		String column = "";
		String columnTran = "";
		int start = str.indexOf(Constants.COLUMN_START);
		int end = str.indexOf(Constants.COLUMN_END);
		if (start < 0 || end < 0) {
			return finalStr;
		}
		column = str.substring(start + 1, end);
		List<DictTFactorPO> factors = dictTFactorService.getDictTFactorByTableidAndType(tableId, "1");
		for (DictTFactorPO factor : factors) {
			if (column.equalsIgnoreCase(factor.getName())) {
				columnTran = factor.getDbcolumnname();
				break;
			}
		}
		finalStr = finalStr.replace(Constants.COLUMN_START + column + Constants.COLUMN_END, columnTran);
		str = str.replace(Constants.COLUMN_START + column + Constants.COLUMN_END, "");
		return transRef(finalStr, str, tableId);
	}
	/**
	 * 将 dbColumnName转换为 columnId
	 * @param finalStr
	 * @param str
	 * @param tableId
	 * @return
	 */
	private String transRefID(String finalStr, String str, String tableId){
		String column = "";
		String columnTran = "";
		int start = str.indexOf(Constants.COLUMN_START);
		int end = str.indexOf(Constants.COLUMN_END);
		if (start < 0 || end < 0) {
			return finalStr;
		}
		column = str.substring(start + 1, end);
		List<DictTFactorPO> factors = dictTFactorService.getDictTFactorByTableidAndType(tableId, "1");
		for (DictTFactorPO factor : factors) {
			if (column.equalsIgnoreCase(factor.getName())) {
				columnTran = factor.getColumnid();
				break;
			}
		}
		finalStr = finalStr.replaceAll(column, columnTran);
		str = str.replace(Constants.COLUMN_START + column + Constants.COLUMN_END, "");
		return transRefID(finalStr, str, tableId);
	}
	
	/**
	 * 将RF(表名).{『字段名称1』,『字段名称2』} 替换成 'value01','value02'
	 * @param inputStr
	 * @return
	 * @throws Exception
	 */
	private String transRefTableCol(String inputStr) throws ServiceException {
		if(null==inputStr || "".equals(inputStr.trim())){
			return inputStr;
		}
		if(inputStr.indexOf(Constants.RF_START)==-1){
			return inputStr;
		}
		
		Pattern p = Pattern.compile("RF\\(.+?\\)\\.\\{.+?\\}");
		Pattern p2 = Pattern.compile(Constants.COLUMN_START+".+?"+Constants.COLUMN_END);
		StringBuffer finalResult = new StringBuffer(50);
		if(inputStr.indexOf(Constants.RF_START)!=-1){//如果存在 则开始解析
			Matcher m= p.matcher(inputStr);
			while(m.find()){
				StringBuffer resultStr = new StringBuffer(50);
				String tempStr = inputStr.substring(m.start(), m.end());
				
				String tableName =tempStr.substring(tempStr.indexOf(Constants.RF_START)+3, tempStr.indexOf(Constants.RF_END));
				String colMatchStr = tempStr.replace(Constants.RF_START, "")
						                    .replace(Constants.RF_END, "")
						                    .replace(tableName, "")
						                    .replace(Constants.COMCOL_START, "")
						                    .replace(Constants.COMCOL_END, "");
				String csDbTableName="";
				String csDbTableId="";
				List<DictTModelcodePO> modelList =modelCodeService.getDictTModelcodePOByName(tableName);
				//查询表的物理名称
				if (modelList != null && modelList.size() > 0) {
					csDbTableName = modelList.get(0).getDbtablename(); // 表ID
					csDbTableId=modelList.get(0).getTableid();
					Matcher cm=p2.matcher(colMatchStr);
					List<TreeNode> nodes;
					try {
						nodes = formulaService.getModelCodeDataByCsid(csDbTableName);
					} catch (Exception e) {
						e.printStackTrace();
						throw new ServiceException(ExceptionCode.ERR_00000, e.getCause().getMessage(), false);
					}
					
					if(nodes!=null && nodes.size()>0){
						while(cm.find()){
							String colName = colMatchStr.substring(cm.start()+1, cm.end()-1);
							String guid = ""; //代码表GUID
							for(TreeNode node :nodes){
								if(node.getName().contains(colName.trim())){
									guid = node.getId();
									break;
								}
							}
							if(!"".equals(guid)){
								cm.appendReplacement(resultStr, "'"+guid+"'");
							}
						
						}
						cm.appendTail(resultStr);
					}else{
						//throw 抛出异常 
						throw new ServiceException(ExceptionCode.ERR_00000, "引用表 "+tableName+"  无数据", false);
					}
					
		
				}else{
					// throw 抛出异常 表名不存在
					throw new ServiceException(ExceptionCode.ERR_00000, tableName+" 该表不存在", false);
				} 
					
				m.appendReplacement(finalResult, Constants.RF_START+csDbTableId+Constants.RF_END+Constants.COMCOL_START+resultStr.toString()+Constants.COMCOL_END);
					
			}
			
			m.appendTail(finalResult);
			return finalResult.toString();
		}
		return inputStr;
	}
	
	public void saveBasicData(String basicCols, String billDefId) throws ServiceException{
		@SuppressWarnings("unchecked")
		Map<String, String> cols = (Map<String, String>)(new ObjectMapper()).readJson(basicCols, Map.class);
		String [] columnArray = cols.get("id").split(",");
		billMapper.deleteBasicData(billDefId);
		for(String columnId : columnArray){
			Map<String, Object> row = new HashMap<String, Object>();
			row.put("billDefId", billDefId);
			row.put("columnId", columnId);
			billMapper.insertBasicData(row);
		}
	}
	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void deleteBillDef(List<BillDefinedPO> list){
		if(list.size()>0){
			for(BillDefinedPO po : list){
				//删除定义相关附表信息
				billMapper.deleteBasicData(po.getGuid());
				billMapper.deleteTgtRelaData(po.getGuid());
				billMapper.deleteBillDef(po);
			}
		}
	}

	@Override
	public Object getPropDefined() throws ServiceException {
		Table table = new Table();
		table.setTableName("动态属性表");
		table.setAppID("SPF");
		table.setTableID("DICT_T_BILLCOLS");
		table.setTableDBName("DICT_T_BILLCOLS");
		
		List<Column> cols = new ArrayList<Column>();
		Column columnName=new Column();
		columnName.setColumnID("COLUMNNAME");
		columnName.setColumnName("COLUMNNAME");
		columnName.setColumnDBName("COLUMNNAME");
		columnName.setAlias("属性名称");
		columnName.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		columnName.setShowType(ShowType.SHOW_TYPE_HTML);		
		columnName.setDataLength(200);
		
		Column columnDbName=new Column();
		columnDbName.setColumnID("DBCOLNAME");
		columnDbName.setColumnName("DBCOLNAME");
		columnDbName.setColumnDBName("DBCOLNAME");
		columnDbName.setAlias("列物理名称");
		columnDbName.setVisible(false);
		
		cols.add(columnName);
		cols.add(columnDbName);
		
		table.setColumnList(cols);
		UserDTO user = SecureUtil.getCurrentUser();
		settingGridService.initStructure(table, user.getGuid());
		return table;
	}

	@Override
	public Object getPropData(Grid data) throws ServiceException {
		try {
			List<Map<String, Object>> list = billMapper.getPropData();
			settingGridService.transferGridData(list, data.getPageInfo());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_1H202, e.getCause().getMessage(), false);
		}
		return data.getPageInfo();
	}

	@Override
	public Object getColsDefined() throws ServiceException {
		Table table = new Table();
		table.setTableName("目标表列对应关系表");
		table.setAppID("SPF");
		table.setTableID("DICT_T_TGTRELATION");
		table.setTableDBName("DICT_T_TGTRELATION");
		table.setColumnList(setColsColumn());
		UserDTO user = SecureUtil.getCurrentUser();
		settingGridService.initStructure(table, user.getGuid());
		return table;
	}
	
	private List<Column> setColsColumn(){
		List<Column> cols = new ArrayList<Column>();
		
		//BILLDEFID
		Column billDefId=new Column();
		billDefId.setColumnID("BILLDEFID");
		billDefId.setColumnName("BILLDEFID");
		billDefId.setColumnDBName("BILLDEFID");
		billDefId.setKey(true);
		billDefId.setVisible(false);
		
		//GUID
		Column guid=new Column();
		guid.setColumnID("GUID");
		guid.setColumnName("GUID");
		guid.setColumnDBName("GUID");
		guid.setKey(true);
		guid.setVisible(false);
		
		//TGTCOLID
		Column tgtColId=new Column();
		tgtColId.setColumnID("TGTCOLID");
		tgtColId.setColumnName("TGTCOLID");
		tgtColId.setColumnDBName("TGTCOLID");
		tgtColId.setKey(true);
		tgtColId.setVisible(false);
		
		//TGTCOLID
		Column columnId=new Column();
		columnId.setColumnID("COLUMNID");
		columnId.setColumnName("COLUMNID");
		columnId.setColumnDBName("COLUMNID");
		columnId.setKey(true);
		columnId.setVisible(false);
		
		//SRCSQL
		Column srcSql=new Column();
		srcSql.setColumnID("SRCSQL");
		srcSql.setColumnName("SRCSQL");
		srcSql.setColumnDBName("SRCSQL");
		srcSql.setKey(true);
		srcSql.setVisible(false);
		
		Column columnName=new Column();
		columnName.setColumnID("COLUMNNAME");
		columnName.setColumnName("COLUMNNAME");
		columnName.setColumnDBName("COLUMNNAME");
		columnName.setAlias("名称");
		columnName.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		columnName.setShowType(ShowType.SHOW_TYPE_HTML);		
		columnName.setDataLength(200);
		
		//SRCSQLDESC
		Column srcSqlDesc=new Column();
		srcSqlDesc.setColumnID("SRCSQLDESC");
		srcSqlDesc.setColumnName("SRCSQLDESC");
		srcSqlDesc.setColumnDBName("SRCSQLDESC");
		srcSqlDesc.setAlias("列公式");
		srcSqlDesc.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		srcSqlDesc.setShowType(ShowType.SHOW_TYPE_HTML);		
		srcSqlDesc.setDataLength(200);
		
		//ISUPDATE
		Column isUpdate=new Column();
		isUpdate.setColumnID("ISUPDATE");
		isUpdate.setColumnName("ISUPDATE");
		isUpdate.setColumnDBName("ISUPDATE");
		isUpdate.setAlias("更新主键");
		isUpdate.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		isUpdate.setShowType(ShowType.SHOW_TYPE_HTML);		
		
		//ISDELETE
		Column isDel=new Column();
		isDel.setColumnID("ISDELETE");
		isDel.setColumnName("ISDELETE");
		isDel.setColumnDBName("ISDELETE");
		isDel.setAlias("删除主键");
		isDel.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		isDel.setShowType(ShowType.SHOW_TYPE_HTML);		
		
		cols.add(columnName);
		cols.add(srcSqlDesc);
		cols.add(isUpdate);
		cols.add(isDel);
		cols.add(guid);
		cols.add(billDefId);
		cols.add(tgtColId);
		cols.add(srcSql);
		return cols;
	}

	@Override
	public Object getColsData(Grid data) throws ServiceException {
		try {
			List<Map<String, Object>> list = billMapper.getColsData(data.getExtProperties());
			settingGridService.transferGridData(list, data.getPageInfo());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_1H202, e.getCause().getMessage(), false);
		}
		return data.getPageInfo();
	}

	@Override
	public Map<String, String> checkColumnSql(Map<String, String> paramsMap)
			throws ServiceException {
		//目标表
		String tgtTableName = dictTModelService.getDictTModelByID(paramsMap.get("tgtTabId"), false).getDbtablename();
		//来源表
		String srcTableName = dictTModelService.getDictTModelByID(paramsMap.get("genSrcTabId"), false).getDbtablename();
		paramsMap.put("tgtTableName", tgtTableName);
		paramsMap.put("srcTableName", srcTableName);
		//目标表列
		String tgtCols = dictTFactorService.getDictTFactorByColumnId(paramsMap.get("tgtColId")).getDbcolumnname();
		paramsMap.put("tgtCols", tgtCols);
		//转换列名称为物理列字段
		String srcSql = this.transformStr(paramsMap.get("srcSqlDesc"), paramsMap.get("srcSqlDesc"), paramsMap.get("genSrcTabId"));
		paramsMap.put("srcSql", srcSql);
		
		try {
			//检查公式是否正确
			billMapper.checkColumnSql(paramsMap);
			//转换列名称为columnid
			String finalSql = this.transRefID(paramsMap.get("srcSqlDesc"), paramsMap.get("srcSqlDesc"), paramsMap.get("genSrcTabId"));
			paramsMap.put("srcSql", finalSql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, e.getCause().getMessage(), false);
		}
		return paramsMap;
	}

	@Override
	public void saveColsRela(Grid data) throws ServiceException {
		List<Map<String, Object>> updateLists = data.getUpdateValues();
		if(updateLists.size()>0){
			for(Map<String, Object> row : updateLists){
				if(row.get("GUID")!=null && !"".equals(row.get("GUID"))){
					//列公式为空删除数据，否则修改
					if(row.get("SRCSQLDESC")!=null && "".equals(row.get("SRCSQLDESC"))){
						billMapper.deleteColsRela(row);
					}else{
						billMapper.updateColsRela(row);
					}
				}else{
					if(row.get("SRCSQLDESC")!=null && !"".equals(row.get("SRCSQLDESC"))){
						billMapper.insertColsRela(row);
					}
					
				}
			}
		}
		
	}

	@Override
	public List<Map<String, Object>> getColumnTree(String tableId,
			String billDefId) throws ServiceException {
		List<DictTFactorPO> columnList = dictTFactorService.getDictTFactorsByTableId(tableId);
		List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
		if(columnList!=null && columnList.size()>0){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("billDefId", billDefId);
			try {
				List<Map<String, Object>> billBase = billMapper.getCheckBasic(params);
				//封装ztree对象
				for(DictTFactorPO po : columnList){
					Map<String, Object> column = new HashMap<String, Object>();
					column.put("id", po.getColumnid());
					column.put("pId", po.getSuperid());
					column.put("name", po.getName());
					if(billBase.size()>0){
						for(Map<String, Object> map : billBase){
							//设置选中状态
							if(map.get("COLUMNID").equals(po.getColumnid())){
								column.put("checked", true);
								break;
							}
						}
					}
					if(column.get("checked")==null){
						column.put("checked", false);
					}
					if(po.getDbcolumnname().equals("DATAKEY")){
						column.put("nocheck", true);
					}
					treeList.add(column);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return treeList;
	}

	@Override
	public boolean isExistSrcTable(Map<String, Object> map) {
		int count = billMapper.getDefSrcTableCount(map);
		if(count==0){
			return true;
		}
		return false;
	}

	@Override
	public Object getConfirmfuncUse(List<Map<String, Object>> list) {
		StringBuffer message = new StringBuffer();
		for(Map<String, Object> map:list){
			String ConfirmFunc = map.get("guid").toString();
			Map<String,Object> ConfirmFuncMap=billMapper.getConfirmfuncById(ConfirmFunc);
			if(ConfirmFuncMap != null){
				message.append("[").append(ConfirmFuncMap.get("NAME")).append("],");
			}
		}
		if(message.length()>0){
			message.deleteCharAt(message.length()-1);
			message.append("已经被使用，不能删除！");
		}
		return message.toString();
	}
}
