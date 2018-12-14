package commons.setting.input.service.impl;

import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.cache.service.IDataCacheService;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.setting.external.service.IEntryOuterService;
import com.tjhq.commons.setting.formula.service.IFormulaService;
import com.tjhq.commons.setting.input.dao.GeneralMapper;
import com.tjhq.commons.setting.input.service.IGeneralService;
import com.tjhq.commons.setting.input.web.ConverTables;

@Service
@Transactional(readOnly = true)
public class GeneralService implements IGeneralService {
	@Resource
	private GeneralMapper generalMapper;
	@Resource
	private IFormulaService formulaService;
	@Resource 
	private IDataCacheService dataCacheService;
	@Resource
	private IDictTModelService dictTModelService;

	@Override
	public Table setTableDefine(String tableId,String columnID, String defineID) {
		Table table = new Table();
		table.setTableID(tableId);
		table.setReadOnly(true);	
		
		DictTModelPO tablePO = dictTModelService.getDictTModelOFID(tableId,true);
		if(tablePO!=null){
			table.setAppID(tablePO.getAppid());
			table.setTableDBName(tablePO.getDbtablename());
			table.setTableName(tablePO.getName());
		}else{
			table.setAppID("xxAppId");
			table.setTableDBName("xxDBName");
			table.setTableName("xxDealName");
		}
		setColumns(table,defineID,columnID);
		return table;
	}
	private void setColumns(Table grid, String defineID, String columnID) {
		List<Column> list = new ArrayList<Column>();
		String tableID = grid.getTableID();
		if(ConverTables.isNotNull(defineID)){
			
			if(defineID.equals("angleview")) list = this.setAngleView(tableID);
			if(defineID.equals("group")) list = this.setGroup(tableID);
			if(defineID.equals("sort")) list = this.setSort(tableID);
			if(defineID.equals("refrela")) list = this.setRefrela(tableID);
			if(defineID.equals("refrelaDetail")) list = this.setRefrelaDetail();
			if(defineID.equals("analy")) list = this.setAnaly(tableID);
			if(defineID.equals("analyDetail")) list = this.setAnalyDetail();
			if(defineID.equals("queryd")) list = this.setQueryd(tableID);
			if(defineID.equals("basenum")) list = this.setBaseNum(columnID,tableID);
			if(defineID.equals("sinrec")) list = this.setSinRec(tableID);//单记录表
		}
		
		grid.setColumnList(list);
	}
	/*public Grid getDefineHead(String tableID, String defineID, String columnID) {
		//创建Grid
		Grid grid = new Grid();
		//设置tableID
		grid.setTableId(tableID);
		//Grid类型
		grid.setgType("GeneralInput");
		//适配类型
		grid.setAdapterType(1);
		grid.setTableName(defineID);
		grid.setDbName(defineID);	
		grid.setEditable(true);	
		grid.setCanCheck(true);	
		grid.setCheckType(true);	
		grid.setReadOnly(true);	
		grid.setWidth(0);		
		grid.setHeight(0);
		
		Head gridHead = new Head();
		if(ConverTables.isNotNull(defineID)){
			
			if(defineID.equals("angleview")) gridHead = this.setAngleView(tableID);
			if(defineID.equals("group")) gridHead = this.setGroup(tableID);
			if(defineID.equals("sort")) gridHead = this.setSort(tableID);
			if(defineID.equals("refrela")) gridHead = this.setRefrela(tableID);
			if(defineID.equals("refrelaDetail")) gridHead = this.setRefrelaDetail();
			if(defineID.equals("analy")) gridHead = this.setAnaly(tableID);
			if(defineID.equals("analyDetail")) gridHead = this.setAnalyDetail();
			if(defineID.equals("queryd")) gridHead = this.setQueryd(tableID);
			if(defineID.equals("basenum")) gridHead = this.setBaseNum(columnID,tableID);
		}
		

		//创建表头
		grid.setHead(gridHead);	
		return grid;
	}
	*/
	/*
	 * 单记录表
	 */
	private List<Column> setSinRec(String tableID) {
		//创建表头对象
		Column column1 = ConverTables.ColumnEditNew("属性名称","CTRLNAME", true,200);	
		Column column2 = ConverTables.ColumnEditNew("是否显示","ISSHOW", true,100);	
		Column column3 = ConverTables.ColumnVisableNew("是否大文本","ISTEXT", false);	//目前没用
		Column column4 = ConverTables.ColumnEditNew("所占列数","COLSPAN", true,100);	
		Column column5 = ConverTables.ColumnEditNew("所占行数","ROWSPAN", true,100);		
		Column column6 = ConverTables.ColumnEditNew("距左列数","LEFTCOLS", true,100);	
		Column column7 = ConverTables.ColumnEditNew("距上行数","TOPROWS", true,100);		
		Column column8 = ConverTables.ColumnEditNew("距上公组框个数","TOPGROUPS", true,100);		
		Column column9 = ConverTables.ColumnEditNew("公组框是否有边框","BORDER", true,100);		
		Column column10 = ConverTables.ColumnEditNew("排序","ORDERID", true,100);	
		
		Column column11 = ConverTables.ColumnVisableNew("PROCESSID","PROCESSID", false);
		Column column12 = ConverTables.ColumnVisableNew("ID","ID", false);
		Column column13 = ConverTables.ColumnVisableNew("CTRLID","CTRLID", false);
		column8.setKey(true);
		
		//设置表头
		List<Column> columns = new ArrayList<Column>();
		columns.add(column1);
		columns.add(column2);
		columns.add(column3);
		columns.add(column4);
		columns.add(column5);
		columns.add(column6);
		columns.add(column7);
		columns.add(column8);
		columns.add(column9);			
		columns.add(column10);			
		columns.add(column11);		
		columns.add(column12);		
		columns.add(column13);				
		
		return columns;
	}
	public List<Column> setAngleView(String tableID){
		//创建表头对象
		Column column1 = ConverTables.ColumnEditNew("序号","orderID", true,200);	
		Column column2 = ConverTables.ColumnEditNew("物理列名","name", false,500);
		Column column3 = ConverTables.ColumnEditNew("是否默认","isDefault", false,300);				
		Column column4 = ConverTables.ColumnVisableNew("表","tableID", false);
		Column column5 = ConverTables.ColumnVisableNew("列ID","columnID", false);
		column5.setKey(true);
		
		//设置表头
		List<Column> columns = new ArrayList<Column>();
		columns.add(column1);
		columns.add(column2);
		columns.add(column3);
		columns.add(column4);
		columns.add(column5);				
		
		return columns;
	}
	public List<Column> setGroup(String tableID){
		Column column1 = ConverTables.ColumnEditNew("序号","orderID", true,100);
		
		Column column2 = ConverTables.ColumnEditNew("分组物理列名","dbColumnName", false,200);
		Column column3 = ConverTables.ColumnEditNew("分组中文列名","name", false,200);
				
 		/*//根据TalbeID 查询表中所有列
		List factory = dictTFactorService.getDictTFactorsByTableId(tableID);
		
		//分组列编码位置
		RefColumn column4 = ConverTables.RefColumnList("分组列编码位置","idShowCol", factory, true, "columnid", "superid","name",200);
		//分组列中文位置
		RefColumn column5 = ConverTables.RefColumnList("分组列中文位置","nameShowCol", factory, true, "columnid", "superid","name",200);
		*/
		
		
		Column column4 = ConverTables.ColumnEditNew("分组列编码位置","idShowCol", true,200);
		Column column5 = ConverTables.ColumnEditNew("分组列中文位置","nameShowCol", true,200);

		/*Map<String,String[]> map =new HashMap<String,String[]>();
		map.put("f1", new String[]{"1","0"});
		map.put("f2", new String[]{"升序","降序"});
		
		RefColumn column6 = ConverTables.RefColumnBean("排序方式","isasc", map, true, "f1", "f2",100);
		*/
		Column column6 = ConverTables.ColumnEditNew("排序方式","isasc", true,100);
		Column column7 = ConverTables.ColumnVisableNew("表","tableID", false);
		Column column8 = ConverTables.ColumnVisableNew("列ID","columnID", false);
		column8.setKey(true);
		
		//设置表头
		List<Column> columns = new ArrayList<Column>();
		columns.add(column1);
		columns.add(column2);
		columns.add(column3);
		columns.add(column4);
		columns.add(column5);
		columns.add(column6);
		columns.add(column7);
		columns.add(column8);

		return columns;
	}
	public List<Column> setSort(String tableID){
		Column column1 = ConverTables.ColumnEditNew("序号","orderID", true,100);
		Column column2 = ConverTables.ColumnEditNew("排序物理列名","dbColumnName", false,200);
		Column column3 = ConverTables.ColumnEditNew("排序中文列名","name", false,250);

		Map<String,String[]> map =new HashMap<String,String[]>();
		map.put("f1", new String[]{"1","0"});
		map.put("f2", new String[]{"升序","降序"});
		
		//RefColumn column4 = ConverTables.RefColumnBean("排序方式","ascFlag", map, true, "f1", "f2",150);
		
		Column column4 = ConverTables.ColumnEditNew("排序方式","ascFlag", true,150);
		Column column5 = ConverTables.ColumnEditNew("是否默认","isDefault", false,150);
		Column column6 = ConverTables.ColumnEditNew("是否保留","isReserve", false,150);

		Column column7 = ConverTables.ColumnVisableNew("表","tableID", false);
		Column column8 = ConverTables.ColumnVisableNew("列ID","columnID", false);
		column8.setKey(true);
		//设置表头
		List<Column> columns = new ArrayList<Column>();
		columns.add(column1);
		columns.add(column2);
		columns.add(column3);
		columns.add(column4);
		columns.add(column5);
		columns.add(column6);
		columns.add(column7);
		columns.add(column8);

		return columns;
	}
	
	public List<Column> setRefrela(String tableID) {
		/*//查询表中的所有列
		List<DictTFactorPO> factorList = new ArrayList<DictTFactorPO>();
		List<DictTFactorPO> factor = dictTFactorService.getDictTFactorsByTableId(tableID);
		if(ConverTables.isNotNullList(factor)){
			for(DictTFactorPO po : factor){
				int dataType = po.getDatatype();
				if(dataType == 6){ //引用类型
					factorList.add(po);
				}
			}
		}*/

		List<Column> columns = new ArrayList<Column>();
		Column relaName = ConverTables.ColumnEditNew("关联关系名称","relaName", true, 300);
		relaName.setKey(true);
		columns.add(relaName);
		Column relaDbTab = ConverTables.ColumnEditNew("关系设置表名","relaDbTab", true, 200);
		relaDbTab.setKey(true);
		columns.add(relaDbTab);
		Column condColumnID = ConverTables.ColumnEditNew("条件列","condColumnID", true,200);
		columns.add(condColumnID);
		Column columnID = ConverTables.ColumnEditNew("显示列","columnID", true,200);
		columns.add(columnID);
		Column setValue = ConverTables.ColumnEditNew("值集关联设置","setValue", false,100);
		columns.add(setValue);
        Column tableType = ConverTables.ColumnEditNew("表类型","tableType", false,200);
		tableType.setKey(true);
		columns.add(tableType);
		Column tblID = ConverTables.ColumnVisableNew("表","tableID", false);
        tblID.setKey(true);
		columns.add(tblID);
		Column relaID = ConverTables.ColumnVisableNew("关联ID","relaID", false);
        columns.add(relaID);
		Column status = ConverTables.ColumnVisableNew("行状态","C_STATUS", false);//0：原始的,1：新增,2：修改,3：删除
        columns.add(status);
		return columns;
	}
	public List<Column> setAnaly(String tableID){
		Column column0 = ConverTables.ColumnEditNew("序号","orderID", true,50);
		Column column1 = ConverTables.ColumnEditNew("查询名称","hrefName", true,300);
		Column column2 = ConverTables.ColumnEditNew("查询地址","hrefLoc", true,400);
		Column column3 = ConverTables.ColumnEditNew("参数设置","setValue", false,100);
		
//		Map<String,String[]> map =new HashMap<String,String[]>();
//		map.put("f1", new String[]{"1","2"});
//		map.put("f2", new String[]{"图片一","图片二"});
//		RefColumn column4 = ConverTables.RefColumnBean("图片标志","pictureID", map, true, "f1", "f2",0);
		
		/*List<Map<String,Object>>  pic= generalMapper.queryCodeTable("CODE_T_QUERYPICLOGO");
		Column column4 = new Column();
		try{
			column4 = ConverTables.RefColumnBean("图片标志","pictureID", (new ObjectMapper()).writeValueAsString(pic), true, "code", "name",200);
		}catch (Exception e) {
			e.printStackTrace();
		}*/
		
		Column column4 = ConverTables.ColumnEditNew("图片标志","pictureID", true,200);
		Column column5 = ConverTables.ColumnVisableNew("表","tableID", false);
		Column column6 = ConverTables.ColumnVisableNew("参数ID","hrefParmID", false);
		column6.setKey(true);
		//设置表头
		List<Column> columns = new ArrayList<Column>();
		columns.add(column0);
		columns.add(column1);
		columns.add(column2);
		columns.add(column3);
		columns.add(column4);
		columns.add(column5);
		columns.add(column6);
		return columns;
	}

	public List<Column> setAnalyDetail(){
		Column column1 = ConverTables.ColumnEditNew("序号","orderID", true,50);
		Column column2 = ConverTables.ColumnEditNew("参数名称","parmName", true,200);
		Column column3 = ConverTables.ColumnEditNew("参数表达式","parmCon", false,300);
		Column column4 = ConverTables.ColumnEditNew(" ","setValue", false,50);

		Column column5 = ConverTables.ColumnVisableNew("参数ID","hrefParmID", false);
		Column column6 = ConverTables.ColumnVisableNew("主键ID","hrefID", false);
		column6.setKey(true);

		//设置表头
		List<Column> columns = new ArrayList<Column>();
		columns.add(column1);
		columns.add(column2);
		columns.add(column3);
		columns.add(column4);
		columns.add(column5);
		columns.add(column6);
		return columns;
	}
	
	public List<Column> setQueryd(String tableID){
		
		Column column1 = ConverTables.ColumnEditNew("序号","orderID", true,100);
		
		//根据TalbeID 查询表中所有列
		//List factory = dictTFactorService.getDictTFactorsByTableId(tableID);
		//Column column2 = ConverTables.RefColumnList("属性名称","ctrlID", factory, true, "columnid", "superid","name",300);
		
//		Map<String,String[]> map =new HashMap<String,String[]>();
//		map.put("f1", new String[]{">","=","%"});
//		map.put("f2", new String[]{"大于","等于","相似"});
		
//		RefColumn column3 = ConverTables.RefColumnBean("默认查询条件","operator", map, true, "f1", "f2",0);
		
		/*List<Map<String,Object>>  operator = generalMapper.queryCodeTable("CODE_T_QUERYOPERATOR");
		Column column3 = new Column();
		try{
			column3 = ConverTables.RefColumnBean("默认查询条件","operator", (new ObjectMapper()).writeValueAsString(operator), true, "code", "name",200);
		}catch (Exception e) {
			e.printStackTrace();
		}*/
		
		Column column2 = ConverTables.ColumnEditNew("属性名称","ctrlID", true,300);
		Column column3 = ConverTables.ColumnEditNew("默认查询条件","operator", true,200);
		
		Column column4 = ConverTables.ColumnEditNew("默认值","defvalue", true,200);
		Column column5 = ConverTables.ColumnEditNew("所占列数","colspan", true,100);
		Column column6 = ConverTables.ColumnEditNew("是否显示","setValue", false,100);
		Column column7 = ConverTables.ColumnVisableNew(" ","isShow", false);
		Column column8 = ConverTables.ColumnVisableNew("关联ID","recID", false);
		Column column9 = ConverTables.ColumnVisableNew("主键ID","guID", false);
		Column column10 = ConverTables.ColumnVisableNew("属性列是否引用列","isRef", false);
		Column column11 = ConverTables.ColumnVisableNew("引用列tableID","csid", false);
		column8.setKey(true);
		column9.setKey(true);

		//设置表头
		List<Column> columns = new ArrayList<Column>();
		columns.add(column1);
		columns.add(column2);
		columns.add(column3);
		columns.add(column4);
		columns.add(column5);
		columns.add(column6);
		columns.add(column7);
		columns.add(column8);
		columns.add(column9);
		columns.add(column10);
		columns.add(column11);
		return columns;
	}
	public List<Column> setRefrelaDetail(){
		Column column1 = ConverTables.ColumnEditNew("编码","code", false,80);
		Column column2 = ConverTables.ColumnEditNew("名称","name", false,170);

		Column column3 = ConverTables.ColumnVisableNew("条件列","condDataID", false);
		Column column4 = ConverTables.ColumnVisableNew("显示列","dataID", false);
		Column column5 = ConverTables.ColumnVisableNew("关联ID","relaID", false);
		column5.setKey(true);

		//设置表头
		List<Column> columns = new ArrayList<Column>();
		columns.add(column1);
		columns.add(column2);
		columns.add(column3);
		columns.add(column4);
		columns.add(column5);
		return columns;
	}
	public List<Column> setBaseNum(String columnID,String tableID){
		Column column1 = ConverTables.ColumnEditNew("序号","orderID", true,200);
		
		/*//第一次加载表头时，查询 该tableID 是否存在 设置列
		if(!ConverTables.isNotNull(columnID)){
			DictTSetBaseNumTab po = entryService.getDataBaseTabList(tableID);
			if(po != null) columnID = po.getColumnID();
		}
			
		//如果columnID 为 引用代码表、 获取引用表数据
		String dbTableName = entryService.getRefrelaDbTableName(tableID, columnID);
		Column column2 = new Column();
		if(ConverTables.isNotNull(dbTableName)) {
			try {
				List codeTree = entryService.getRefrelaDbTableTree(dbTableName);
				column2 = ConverTables.RefColumnList("基本信息值","colValue", codeTree, true, "id", "pId","name",0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			column2 = ConverTables.ColumnEditNew("基本信息值","colValue", true,600);
		}
		*/
		Column column2 = ConverTables.ColumnEditNew("基本信息值","colValue", true,600);
		Column column3 = ConverTables.ColumnVisableNew("外键","columnID", false);
		Column column4 = ConverTables.ColumnVisableNew("主键","guID", false);
		column4.setKey(true);

		//设置表头
		List<Column> columns = new ArrayList<Column>();
		columns.add(column1);
		columns.add(column2);
		columns.add(column3);
		columns.add(column4);
		return columns;
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void saveTableData(CommonGrid commonGrid, String defineID)
			throws ServiceException {
		//获取插入数据
		List<Map<String, Object>> insertList = commonGrid.getInsertValues();
		//获取更新数据
		List<Map<String,Object>> updateList = commonGrid.getUpdateValues();
		//获取删除数据
		List<Map<String,Object>> deleteList = commonGrid.getDeleteValues();

		String relaDbTab = (String)commonGrid.getExtProperties().get("relaDbTab");
		String recID = (String)commonGrid.getExtProperties().get("recID");
		int deleteSize = deleteList.size();
		int insertSize = insertList.size();
		int updateSize = updateList.size();
		int sumSize = deleteSize + insertSize + updateSize;


		if(deleteSize > 0 && insertSize > 0) {
			List<Map<String, Object>> maxMap = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> minMap = new ArrayList<Map<String, Object>>();
			if(deleteSize > insertSize) {
				maxMap.addAll(deleteList);
				minMap.addAll(insertList);
			} else {
				maxMap.addAll(insertList);
				minMap.addAll(deleteList);
			}
			for(Map<String, Object> data1 : maxMap) {
				for(Map<String,Object> data2 : minMap) {
					if(data1.get("relaDbTab")!=null && data2.get("relaDbTab") !=null && data1.get("relaDbTab").toString().equalsIgnoreCase(data2.get("relaDbTab").toString())) {
						maxMap.remove(data1);
						minMap.remove(data2);
						deleteSize--;
						insertSize--;
						if(minMap.size() < 1)
							break;
					}
					if(maxMap.size() < 1)
						break;
				}
			}
			deleteList.clear();
			insertList.clear();
			if(deleteSize > insertSize) {
				deleteList.addAll(maxMap);
				insertList.addAll(minMap);
			} else {
				deleteList.addAll(minMap);
				insertList.addAll(maxMap);
			}
		}
		if(deleteSize > 0) deleteTableData(deleteList, defineID, relaDbTab);
		if(insertSize > 0) insertTableData(insertList, defineID, relaDbTab, recID);
		if(updateSize > 0) updateTableData(updateList, defineID);

		if(sumSize > 0 &&  defineID != null
				&& !defineID.equals("fddef") && !defineID.equals("fixed")) {

			String keyString = "";
			if(defineID.equals("angleview")) {	//视角
				keyString = IEntryOuterService.ANGLEVIEW;
			}
			if(defineID.equals("group")) {		//分组设置
				keyString = IEntryOuterService.GROUP;
			}
			if(defineID.equals("sort")) {		//排序设置
				keyString =  IEntryOuterService.SORT;
			}
			if(defineID.equals("refrela") || relaDbTab != null) {//引用列关系定义(relaDbTab!=null是值级联关系设置)
				keyString =  IEntryOuterService.REFRELA;
			}
			if(defineID.equals("analy")) {		//查询（分析）引用定义
				keyString =  IEntryOuterService.ANALY;
			}
			if(defineID.equals("queryd")) {		//查询设置
				keyString =  IEntryOuterService.QUERYD;
			}
			if (defineID.equals("basenum")) {
				keyString =  IEntryOuterService.BASENUM;
			}
			String tableID = (String)commonGrid.getExtProperties().get("tableID");
			String[] keys = {"HQ.COMM", keyString, tableID};
			this.clearCatch(keys);
		}
	}

	/**
	 * 清缓存
	 * @param keys
	 */
	private void clearCatch(String[] keys) {
		Object commInputCatch = dataCacheService.get(keys);
		if(commInputCatch!=null){
			dataCacheService.put(keys, null);
		}
	}

	//一般录入表插入
	@Transactional
	public String insertTableData(List<Map<String, Object>> lists,String defineID,String relaDbTab,String recID) throws ServiceException{
		if(ConverTables.isNotNull(defineID)){
			String flag1="";
			if(defineID.equals("angleview")) generalMapper.insertAngleView(lists);	
			if(defineID.equals("group")) generalMapper.insertGroup(lists);	
			if(defineID.equals("sort")) generalMapper.insertSort(lists);	
			if(defineID.equals("refrela")){
				try{
					Set<String> set=new HashSet<String>();
					for(Map<String,Object> map:lists){
						set.add(((String) map.get("relaName")).trim());
					}					
					if(set.size()<lists.size()){
						flag1="1";
						throw new Exception();
					}
					
					for(Map<String,Object> map:lists){				
					    String reladbtab = (String)map.get("relaDbTab");
					    reladbtab = reladbtab.trim();
					    map.put("relaDbTab",reladbtab);
						String relaName=(String) map.get("relaName");
						Map<String,String> relaNameMap=new HashMap<String,String>();
						relaNameMap.put("relaName", relaName);
						relaNameMap.put("TABLEID", (String) map.get("tableID"));
						
						int count=this.generalMapper.findRepeatNameCount(relaNameMap);
						if(count>0) {//左边树对应的右侧引用列关系中，已存在相同名称的关联表
							flag1="2";
							throw new Exception();
						}
					}
					generalMapper.insertRefrela(lists);		
				} catch (Exception e) {// 如果引用关系列定义 保存失败，删除表
					e.printStackTrace();
					// dropRefrelaTable(lists);
					for (Map<String, Object> map : lists) {
						Map<String, Object> mapa = new HashMap<String, Object>();
						mapa.put("list", lists);
						String reladbtab = (String) map.get("relaDbTab");
						reladbtab = reladbtab.trim();
						mapa.put("relaDbTab", reladbtab);
						mapa.put("year", SecureUtil.getUserSelectYear());
						if (map.get("relaID") != null && !"".equals(map.get("relaID"))) {
							int count = this.generalMapper.findOtherSameRelName(mapa);
							if (count < 1) {// 如果P#DICT_T_SETREFRELA不存在表数据，就删除表
								dropRefrelaTable(lists);
							}
						}
						// throw new RuntimeException(returnString);
						if ("".equals(flag1)) {//一般错误
							throw new ServiceException(ExceptionCode.INP_00101, "关联关系表保存失败", false);
						}else {//已存在关联关系名称
							throw new ServiceException(ExceptionCode.INP_00101, " 列名不允许重复！", false);
						}
					}

				}	
			} 
			if(defineID.equals("refrelaDetail")){// relaDbTab 物理表名
				generalMapper.insertRefrelaDetail(lists, relaDbTab);
			} 
			if(defineID.equals("analy")) generalMapper.insertAnaly(lists);
			if(defineID.equals("analyDetail")) generalMapper.insertAnalyDetail(lists);	
			if(defineID.equals("queryd")){
				for(Map<String,Object> map:lists){	
					map.put("recID", recID);
					//Integer colspan = new Integer(map.get("colspan").toString());
					if(map.get("colspan")==null ||new Integer(map.get("colspan").toString())==0){
						map.put("colspan", 1);
					}
					
					
				}
				generalMapper.insertQuerydDet(lists);
			}
			if(defineID.equals("basenum")) generalMapper.insertBaseNumSub(lists);	
		}
				
		return "true";
	}

	//一般录入表更新
	@Transactional
	public String updateTableData(List<Map<String, Object>> lists,String defineID) {
	
		if(ConverTables.isNotNull(defineID)){
			if(defineID.equals("angleview")) generalMapper.updateAngleView(lists);	
			if(defineID.equals("group")) generalMapper.updateGroup(lists);	
			if(defineID.equals("sort")) generalMapper.updateSort(lists);
			//引用列关系表不能修改
			if(defineID.equals("refrela")){
				try{
				List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
				Set<String> set=new HashSet<String>();
				for(Map<String,Object> map:lists){
					set.add((String) map.get("relaName"));
				}	
				if(set.size()<lists.size()){
					throw new Exception("关联关系名称不允许重复！");
				}
				for(Map<String, Object> data : lists){
					Map<String,String> relaNameMap=new HashMap<String,String>();
					relaNameMap.put("relaName", (String) data.get("relaName"));
					relaNameMap.put("tableID", (String) data.get("tableID"));
					relaNameMap.put("RELAID", (String) data.get("relaID"));
					int count=this.generalMapper.findRepeatNameCount(relaNameMap);
					if(count>0) throw new Exception("关联关系名称不允许重复！");
					if(ConverTables.isNotNull(data.get("relaName")) || ConverTables.isNotNull(data.get("tableType"))) {
					    dataList.add(data);
					}
				}
				if(ConverTables.isNotNullList(dataList)) generalMapper.updateRefrela(dataList);
			}catch (Exception e){
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		  }
			if(defineID.equals("analy")) generalMapper.updateAnaly(lists);	
			if(defineID.equals("analyDetail")) generalMapper.updateAnalyDetail(lists);	
			if(defineID.equals("queryd")){
				/*for(Map<String,Object> map:lists){	
				    Object colspanObj = map.get("colspan");
					Integer colspan = colspanObj==null?null:Integer.valueOf((String)map.get("colspan"));
					if(colspan==null || colspan==0){
						map.put("colspan", 1);
					}
				}*/
				generalMapper.updateQuerydDet(lists);
			}
			if(defineID.equals("basenum")) generalMapper.updateBaseNumSub(lists);
		}
		
		return "true";
	}

	//一般录入表删除
	@Transactional
	public String deleteTableData(List<Map<String, Object>> lists, String defineID,String relaDbTab) {
		if(ConverTables.isNotNull(defineID)){
			if(defineID.equals("angleview")) generalMapper.deleteAngleView(lists);
			if(defineID.equals("group")) generalMapper.deleteGroup(lists);	
			if(defineID.equals("sort")) generalMapper.deleteSort(lists);	
			if(defineID.equals("refrela")) { //删除主表信息
				int refrelaDel = generalMapper.deleteRefrela(lists);
				if(refrelaDel > 0){ //删除 引用物理表
					for(Map<String, Object> map:lists){
						Map<String,Object> mapa=new HashMap<String,Object>();
						String tableType = (String)map.get("tableType");
						if("2".equals(tableType)){//反登记视图不操作
						    continue;
						}
						mapa.put("list", lists);
						mapa.put("relaDbTab",(String)map.get("relaDbTab"));
						String relaDbTabName = map.get("relaDbTab").toString();
						String relaId = map.get("relaID").toString();
						mapa.put("year", SecureUtil.getUserSelectYear());
						int count = this.generalMapper.findOtherSameRelName(mapa);
						if(count < 1)	{//如果除了当前表之外，没有同名的表，则删表
						    List<Map<String, Object>> dropList = new ArrayList<Map<String,Object>>();
						    dropList.add(map);
							dropRefrelaTable(dropList);
						} else {//如果有同名的，则删当前relaID的数据
							generalMapper.deleteRefrelaData(relaDbTabName,relaId);
						}
							
					}
				} 
			}
			if(defineID.equals("refrelaDetail")){
				//relaDbTab 物理表名
				generalMapper.deleteRefrelaDetail(lists, relaDbTab);
			} 
			if(defineID.equals("analy")) { //级联删除
				int analyDel = generalMapper.deleteAnaly(lists);
				if(analyDel>0) generalMapper.deleteAnalyCascHref(lists);
			}

			if(defineID.equals("analyDetail")) generalMapper.deleteAnalyDetail(lists);	
			if(defineID.equals("queryd")) generalMapper.deleteQuerydDet(lists);
			if(defineID.equals("basenum")) generalMapper.deleteBaseNumSub(lists);
		}
		
		return "true";
	}
	//删除 引用关系表
	public void dropRefrelaTable(List<Map<String, Object>> lists){	
		try{//如果表不存在，报错ORA-00942: table or view does not exist
			for(Map<String,Object> data:lists){
				if(ConverTables.isNotNull(data.get("relaDbTab"))){
					String dropView = "DROP VIEW " + data.get("relaDbTab");
					String dropTable = "DROP TABLE P#" + data.get("relaDbTab") + " CASCADE CONSTRAINTS";
					
					formulaService.callErrorMessage(dropView);
					
					formulaService.callErrorMessage(dropTable);
				}
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	//新增 更新 查询条件定义
	@Transactional
	public String saveQuerydDet(Map<String, Object> map) {
		//如果为 空
		if(!ConverTables.isNotNull(map.get("recID"))){
			generalMapper.insertQueryd(map); //插入
		} else {	
			generalMapper.updateQueryd(map); //更新
		}
		//清缓存
		String tableID = (String)map.get("tableID");
		String [] keys = {"HQ.COMM", IEntryOuterService.QUERYD, tableID};
		this.clearCatch(keys);
		return (String)map.get("recID");
	}

	//根据CODE_T_ 代码表 查询代码表中数据
	public List<Map<String, Object>> queryCodeTable(String codeTableName) {
		return generalMapper.queryCodeTable(codeTableName);
	}


	@Override
	public void saveRefrela(String relaDbTab, String relaID, String condDataID,
			List<Map<String, Object>> relas) {
		// 采用先删除所有再批量添加的方式
		generalMapper.deleteAllRefrelaDetail(relaDbTab, condDataID);

		Map<String, Object> params = new HashMap<String, Object>();
		if(relas.size() > 0) {
			params.put("relas", relas);
			params.put("relaID", relaID);
			params.put("relaDbTab", relaDbTab);
			params.put("condDataID", condDataID);
			generalMapper.addRefrelaDetail(params);
		}
	}
}
