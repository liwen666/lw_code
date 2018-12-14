package commons.setting.external.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.cache.service.IDataCacheService;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.setting.external.dao.EntryOuterMapper;
import com.tjhq.commons.setting.external.po.DictTSetAnalyPO;
import com.tjhq.commons.setting.external.po.DictTSetAngleViewPO;
import com.tjhq.commons.setting.external.po.DictTSetBaseNumTab;
import com.tjhq.commons.setting.external.po.DictTSetFddefPO;
import com.tjhq.commons.setting.external.po.DictTSetFixPO;
import com.tjhq.commons.setting.external.po.DictTSetGroupPO;
import com.tjhq.commons.setting.external.po.DictTSetHrefParmPO;
import com.tjhq.commons.setting.external.po.DictTSetMainSubRela;
import com.tjhq.commons.setting.external.po.DictTSetMainSubTab;
import com.tjhq.commons.setting.external.po.DictTSetQuerydDetPO;
import com.tjhq.commons.setting.external.po.DictTSetQuerydPO;
import com.tjhq.commons.setting.external.po.DictTSetRefrelaPO;
import com.tjhq.commons.setting.external.po.DictTSetSortPO;
import com.tjhq.commons.setting.external.po.RECDetailPO;
import com.tjhq.commons.setting.external.po.RECPO;
import com.tjhq.commons.setting.external.service.IEntryOuterService;
import com.tjhq.commons.setting.input.service.ISinRecService;
import com.tjhq.commons.setting.input.web.ConverTables;


@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
public class EntryOuterService implements IEntryOuterService {

	@Resource
	private EntryOuterMapper entryOuterMapper;
	@Resource
	private ISinRecService sinRecService;
	@Resource
	private IDictTFactorService dictTFactorService;
	@Resource
	private IDictTModelService dictTModelService;
	@Resource
	private IDataCacheService dataCacheService;
	//视角	
	public List<DictTSetAngleViewPO> getDataAngleViewList(String tableID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableID", tableID);
		
		String[] keys = {"HQ.COMM",IEntryOuterService.ANGLEVIEW,tableID};
		List<DictTSetAngleViewPO> dataCatch = (List<DictTSetAngleViewPO>) dataCacheService.get(keys);
		
		if(null == dataCatch){
			dataCatch = entryOuterMapper.getDataAngleViewList(map);
			if (null == dataCatch) {
				dataCatch = new ArrayList<DictTSetAngleViewPO>();
			}
			dataCacheService.put(keys, dataCatch);
		}
		return dataCatch;
	}
	// 分组设置
	public List<DictTSetGroupPO> getDataGroupList(String tableID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableID", tableID);
		/*
		String[] keys = {"HQ.COMM",IEntryOuterService.GROUP,tableID};
		List<DictTSetGroupPO> dataCatch = (List<DictTSetGroupPO>) dataCacheService.get(keys);
		
		if(null == dataCatch || dataCatch.size()==0){
			dataCatch = entryOuterMapper.getDataGroupList(map);
			dataCacheService.put(keys, dataCatch);
		}*/
		List<DictTSetGroupPO> dataCatch = entryOuterMapper.getDataGroupList(map);
		return dataCatch;
	}

	// 排序设置
	public List<DictTSetSortPO> getDataSortList(String tableID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableID", tableID);

		String[] keys = {"HQ.COMM",IEntryOuterService.SORT,tableID};
		List<DictTSetSortPO> dataCatch = (List<DictTSetSortPO>) dataCacheService.get(keys);
		
		if(null == dataCatch){
			dataCatch = entryOuterMapper.getDataSortList(map);
			if (null == dataCatch) {
				dataCatch = new ArrayList<DictTSetSortPO>();
			}
			dataCacheService.put(keys, dataCatch);
		}
		return dataCatch;
	}
	//引用列关系定义
	public List<DictTSetRefrelaPO> getDataRefrelaList(String tableID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableID", tableID);
		
		String[] keys = {"HQ.COMM",IEntryOuterService.REFRELA,tableID};
		List<DictTSetRefrelaPO> dataCatch = (List<DictTSetRefrelaPO>) dataCacheService.get(keys);
		
		if(null == dataCatch){
			dataCatch = entryOuterMapper.getDataRefrelaList(map);
			if (null == dataCatch) {
				dataCatch = new ArrayList<DictTSetRefrelaPO>();
			}
			dataCacheService.put(keys, dataCatch);
		}
		return dataCatch;
	}
	//引用列关系定义
	public List<DictTSetRefrelaPO> getDataRefrelaByColumn(String tableID, String columnID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableID", tableID); // 表ID
		// 通过被关联列 与 表ID 结合查询 关联值集
		map.put("columnID", columnID); // 被关联列

		/*String[] keys = {"HQ.COMM",IEntryOuterService.REFRELA,tableID+columnID};
		List<DictTSetRefrelaPO> dataCatch = (List<DictTSetRefrelaPO>) dataCacheService.get(keys);
		
		if(null == dataCatch || dataCatch.size()==0){
			dataCatch = entryOuterMapper.getDataRefrelaList(map);
			dataCacheService.put(keys, dataCatch);
		}*/
		List<DictTSetRefrelaPO> dataCatch = entryOuterMapper.getDataRefrelaList(map);
		return dataCatch;
	}

	// 查询（分析引用定义）
	public List<Map<String,String>> getDataAnalyList(String tableID) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("tableID", tableID);
		
		String[] keys = {"HQ.COMM",IEntryOuterService.ANALY,tableID};
		List<Map<String,String>> dataCatch = (List<Map<String,String>>) dataCacheService.get(keys);
		
		if(null == dataCatch){
			List<DictTSetAnalyPO> list = entryOuterMapper.getDataAnalyList(data);
			
			if (list.size() > 0) {
				dataCatch = new ArrayList<Map<String,String>>();
				for (int i = 0; i < list.size(); i++) {
					DictTSetAnalyPO analy = list.get(i);
					Map<String, String> map = new HashMap<String, String>();
					String hrefLoc = analy.getHrefLoc() + "?";
 					// 返回list <Map> 集合
					map.put("hrefName", analy.getHrefName());
					map.put("picture", analy.getPictureID());
					map.put("tableId", analy.getTableID());
					map.put("pictureUrl", analy.getPictureUrl());
					for (DictTSetHrefParmPO parm : analy.getHrefParm()) {
						String url = parm.getParmName() + "=" + parm.getParmCon();
						hrefLoc = hrefLoc + url + "&";
					}
					if(hrefLoc.contains("&")){
						map.put("hrefLoc", hrefLoc.substring(0, hrefLoc.lastIndexOf("&")));
					}else{
						map.put("hrefLoc", hrefLoc);
					}
					
					// 将Map结果集 添加到List
					dataCatch.add(map);
				}
			}
			if (null == dataCatch) {
				dataCatch = new ArrayList<Map<String,String>>();
			}
			dataCacheService.put(keys, dataCatch);
		}
		return dataCatch;
	}

	// 查询条件设置
	public DictTSetQuerydPO getDataQuerydList(String tableID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableID", tableID);
	/*	
		String[] keys = {"HQ.COMM",IEntryOuterService.QUERYD,tableID};
		DictTSetQuerydPO querydCatch = (DictTSetQuerydPO) dataCacheService.get(keys);
		
		if(null == querydCatch){
			querydCatch = entryOuterMapper.getDataQuerydList(map);
			dataCacheService.put(keys, querydCatch);
		}*/
		DictTSetQuerydPO querydCatch =  entryOuterMapper.getDataQuerydList(map);
		return querydCatch;
		/*Map<String , Map<String, Object>> commInputCatch = 
			(Map<String , Map<String, Object>>)dataCacheService.get("HQ.COMM");
		Map<String, Object> querydCatch = null;
		DictTSetQuerydPO queryd = null;
		
		if(commInputCatch!=null){//有录入页面定义的缓存
			querydCatch = commInputCatch.get(IEntryOuterService.QUERYD);
			if(querydCatch!=null){//有查询条件设置的缓存
				DictTSetQuerydPO tableCatch = (DictTSetQuerydPO)querydCatch.get(tableID);
				if(tableCatch!=null){//有当前表的缓存
					return tableCatch;
				}else{
					queryd = entryOuterMapper.getDataQuerydList(map);//取数据
					querydCatch.put(tableID, queryd);
				}
			}else{
				querydCatch = new HashMap<String, Object>();
				queryd = entryOuterMapper.getDataQuerydList(map);//取数据
				querydCatch.put(tableID, queryd);
				commInputCatch.put(IEntryOuterService.QUERYD, querydCatch);
			}
		}else{
			commInputCatch = new HashMap<String , Map<String, Object>>();
			querydCatch = new HashMap<String, Object>();
			queryd = entryOuterMapper.getDataQuerydList(map);//取数据
			querydCatch.put(tableID, queryd);
			commInputCatch.put(IEntryOuterService.QUERYD, querydCatch);
			dataCacheService.put("HQ.COMM", commInputCatch);
		}
		return queryd;*/
	}

	// 创建查询面板页面
	public String createQueryPage(String tableID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableID", tableID);
		
		DictTSetQuerydPO queryd = entryOuterMapper.getDataQuerydList(map);
		String resultPage = this.createQueryPage(queryd);
		
		return resultPage;
	}

	public int queryPanelLine(String tableID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableID", tableID);
		DictTSetQuerydPO queryd = entryOuterMapper.getDataQuerydList(map);
		int line = 0;
		
		if(queryd != null){
			int colSpan = 0;
			int showCols = queryd.getShowCols();
			
			List<DictTSetQuerydDetPO> det = queryd.getQuerydDet();
			if(ConverTables.isNotNullList(det)){
				for(DictTSetQuerydDetPO po : det)colSpan += po.getColspan();
			}
			
			line = (int) Math.ceil(colSpan/(float)showCols);
		}
		return line;
	}
	
	// 浮动表整表设置
	public DictTSetFddefPO getDataFddefList(String tableID) {
		String[] keys = {"HQ.COMM",IEntryOuterService.SETFDDEF,tableID};
		DictTSetFddefPO fddefCatch = (DictTSetFddefPO) dataCacheService.get(keys);
		if(null == fddefCatch){
			fddefCatch = entryOuterMapper.getDataFddefList(tableID);
			if (null == fddefCatch) {
				fddefCatch = new DictTSetFddefPO();
			}
			dataCacheService.put(keys, fddefCatch);
		}
		return fddefCatch;
	}

	// 固定行列表整表设置
	public List<DictTSetFixPO> getDataFixList(String tableID) {
		String[] keys = {"HQ.COMM",IEntryOuterService.SETFIXED,tableID};
		List<DictTSetFixPO> fixCatch = (List<DictTSetFixPO>) dataCacheService.get(keys);
		if(null == fixCatch){
			fixCatch = entryOuterMapper.getDataFixList(tableID);
			if (null == fixCatch) {
				fixCatch = new ArrayList<DictTSetFixPO>();
			}
			dataCacheService.put(keys, fixCatch);
		}
		return fixCatch;
	}

	// 创建查询页面
	public String createQueryPage(DictTSetQuerydPO query){
		StringBuffer str = new StringBuffer();
		String ctrlName = ""; // 中文列名称
		String ctrlDbName = "";  // 物理列名称
		int dataType = 0; //数据类型
		
		str.append("<table width=\"100%\" class=\"chaxun\" id=\"queryData\" cellpadding=0 cellspacing=0 border=0 >\n");
		if(query != null){
			int cols = query.getShowCols(); // 总列数
			int titleWidth = query.getTitleWidth(); // 标题宽度
			String isShowTitle = query.getIsShowTitle(); // 是否显示标题
			int row = 0;
			for(DictTSetQuerydDetPO queryd : query.getQuerydDet()){
				String ctrlId = queryd.getCtrlID(); // 查询列ID
				DictTFactorPO factor = this.getFactorPO(ctrlId);
				if(factor != null){
					ctrlName = factor.getName();
					ctrlDbName = factor.getDbcolumnname();
					dataType = factor.getDatatype();
				}
				int colspan = queryd.getColspan(); // 所占列数
				String defvalue = queryd.getDefvalue(); // 默认值
				String operator = queryd.getOperator(); // 操作符
				String isShow = queryd.getIsShow(); // 高级显示
				
				if(row == 0) str.append("<tr>\n");
				row += colspan;

				if(cols < row) {
					str.append("</tr>\n<tr>\n");
					row = cols;
				}
				str.append("<td width=\"" + titleWidth + "\" colspan=\"" + colspan + "\" >");
				str.append(ctrlName + "：<input type=\"text\" name=\"" + ctrlDbName
						+ "\" id=\"" + ctrlId + "\" value=\"" + isNull(defvalue) + "\"" 
						+ " operator=\"" + operator + "\" datatype=\"" + dataType + "\" /></td>\n");
				
				// 控制行数
				if(cols == row){
					str.append("</tr>\n");
					row = 0;
				}		
			}
			//查询按钮
			str.append("<a class='button-1-2' href='javascript:void(0);' style='position:relative;float: right;top:15px;margin-right: 10px;z-index: 9999;' onclick='queryPanelData();'>查询</a>");
			str.append("\n</table>");
		}
		return str.toString();	
	}

	// 处理字符
	public static String isNull(String str) {
		return (str == null) ? "" : str;
	}

	//根据 类型ID 获取 该类型下的 主表与子表
	//加缓存
	public List<DictTSetMainSubTab> getDataMainSubTabList(String collTypeID) {
		String[] keys = {"HQ.BGT","COMMONSETTING_MAINSUB","GETDATAMAINSUBTABLIST", collTypeID};
		List<DictTSetMainSubTab> tabList = (List<DictTSetMainSubTab>) dataCacheService.get(keys);
		
		if(null != tabList){
			return tabList;
		} else {
			dataCacheService.put(keys, new ArrayList<DictTSetMainSubTab>());
		}
		
		tabList = entryOuterMapper.getDataMainSubTabList(collTypeID);
		
		for(int i=0; i<tabList.size(); i++){
			DictTSetMainSubTab tab = tabList.get(i);
			List<DictTSetMainSubRela> relaList = tab.getMainSubRela();
			//子表
			if(tab.getMainSubRela()!= null && tab.getMainSubRela().size()>0){
				for(int j=0 ; j<relaList.size(); j++){
					DictTSetMainSubRela rela = relaList.get(j); 
					//获取 dbTableName || dbColumnName
					rela.setMainTabName(this.getDbTableName(rela.getMainTabID()));
					rela.setSubTabName(this.getDbTableName(rela.getSubTabID()));
					rela.setFkName(this.getDbColumnName(rela.getFkID()));
					rela.setMainFkName(this.getDbColumnName(rela.getMainFkID()));
				}
			}
		}
		
		if (null != tabList) {
			dataCacheService.put(keys, tabList);
		}
		
		return tabList;
	}

	//基本数字表设置
	public DictTSetBaseNumTab getDataBaseTabList(String tableID) {
		String[] keys = {"HQ.COMM",IEntryOuterService.BASENUM,tableID};
		DictTSetBaseNumTab baseNumCatch = (DictTSetBaseNumTab) dataCacheService.get(keys);
		if(null == baseNumCatch){
			baseNumCatch = entryOuterMapper.getDataBaseTabList(tableID);
			if (null == baseNumCatch) {
				baseNumCatch = new DictTSetBaseNumTab();
			}
			dataCacheService.put(keys, baseNumCatch);
		}
		return baseNumCatch;
	}
	
	//根据tableID 查询 物理表名
	public String getDbTableName(String tableID){
		String dbTableName = "";
		DictTModelPO model= dictTModelService.getDictTModelByID(tableID, false);
		if(model!=null){
			dbTableName = model.getDbtablename();
		}
		return dbTableName;
	}
	//根据columnID  查询 物理列名
	public String getDbColumnName(String columnID){
		String dbColumnName = "";
		DictTFactorPO factor = dictTFactorService.getDictTFactorByColumnId(columnID);
		if(factor != null){ 
			dbColumnName = factor.getDbcolumnname();
		}
		return dbColumnName;
	}
	//根据columnID  查询 中文列名
	public DictTFactorPO getFactorPO(String columnID){
		
		return dictTFactorService.getDictTFactorByColumnId(columnID);
	}

	/** 是分组控件 */
	private static String GROUPCTRL = "1"; //是分组控件。
	/** 不是分组控件 */
	private static String NOGROUPCTRL = "0";
	/** 末级结点 */
	private static String LEAF = "1";
	/** 顶级结点 */
	private static String TOPNODE = "0";
	
	
	//加缓存
	public List<RECPO> getSetRECList(Map<String, String> map) {
		String[] keys = {"HQ.BGT","COMMONSETTING_MAINSUB",map.get("objectId")+map.get("tableId")};
		List<RECPO> result = (List<RECPO>) dataCacheService.get(keys);
		if(null == result){
			result = entryOuterMapper.getSetRECList(map);
			if (null == result) {
				result = new ArrayList<RECPO>();
			}
			dataCacheService.put(keys, result);
		}
		return result;
	}
	//加缓存
	public Map<String, List<RECDetailPO>> getSetRECDetailList(String recId) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("recId", recId);
		String[] keys = {"HQ.BGT","COMMONSETTING_MAINSUB",recId};
		List<RECDetailPO>  list = (List<RECDetailPO>) dataCacheService.get(keys);
		
		if(null == list ){
			list =  entryOuterMapper.getSetRECDetailList(map);
			if (null == list) {
				list = new ArrayList<RECDetailPO>();
			}
			dataCacheService.put(keys, list);
		}
		Map<String, List<RECDetailPO>> returnmap = new HashMap<String, List<RECDetailPO>>();
		if (list == null || list.size() == 0) {
			return returnmap;
		}
		List<RECDetailPO> fieldsetList = new ArrayList<RECDetailPO>();
		List<RECDetailPO> columnsList = new ArrayList<RECDetailPO>();

		for( int i = 0; i < list.size(); i++ ) {
			RECDetailPO dto = list.get( i );
			if( GROUPCTRL.equals( dto.getIsgroupctrl() ) ) { //是分组控件。
				fieldsetList.add( dto );
			} else if( TOPNODE.equals( dto.getSuperid() ) && NOGROUPCTRL.equals( dto.getIsgroupctrl() ) && LEAF.equals( dto.getIsleaf() ) ) { // 表结构的列(不包括公组框下面的属性的列)
				columnsList.add( dto );
			}
		}

		List<RECDetailPO> childList = null;
		RECDetailPO fatherdto;
		//公组框包含的表结构的列
		for( int j = 0; j < fieldsetList.size(); j++ ) {
			fatherdto = fieldsetList.get( j );
			childList = new ArrayList<RECDetailPO>();
			for( int i = 0; i < list.size(); i++ ) {
				RECDetailPO dto = list.get( i );
				if( NOGROUPCTRL.equals( dto.getIsgroupctrl() ) && !TOPNODE.equals( dto.getSuperid() ) && fatherdto.getCtrlid().equals( dto.getSuperid() ) ) {
					childList.add( dto );
				}
			}
			fatherdto.setListDetail( childList );
		}
		returnmap.put( "fieldset", fieldsetList );
		returnmap.put( "columns", columnsList );

		return returnmap;
	}

	
	@Override
	public Map<String, List<RECDetailPO>> getSinFormDetailList(String recId) {
		return sinRecService.getSetRECDetailList(recId);
	}
	@Override
	public List<RECPO> getSingleFormList(String tableId) {
		return sinRecService.getSetRECList(tableId);
	}

// ------------------- 项目库 阶段
	
	// 分组
	public List<DictTSetGroupPO> getDataGroupByStage(String tableID,String stage) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableID", tableID);
		map.put("stage", stage);
		
		return entryOuterMapper.getDataGroupList(map);
	}
	// 排序
	public List<DictTSetSortPO> getDataSortByStage(String tableID, String stage) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableID", tableID);
		map.put("stage", stage);
		
		return entryOuterMapper.getDataSortList(map);
	}
	// 引用 
	public List<DictTSetRefrelaPO> getDataRefrelaByStage(String tableID,String stage) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableID", tableID);
		map.put("stage", stage);
		
		return entryOuterMapper.getDataRefrelaList(map);
	}
	
	// 查询（分析引用定义）
	public List<Map> getDataAnalyByStage(String tableID,String stage) {
		List<Map> resultList = new ArrayList<Map>();
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("tableID", tableID);
		data.put("stage", stage);
		List<DictTSetAnalyPO> list = entryOuterMapper.getDataAnalyList(data);
		
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				DictTSetAnalyPO analy = list.get(i);
				Map<String, Object> map = new HashMap<String, Object>();
				String hrefLoc = analy.getHrefLoc() + "?";
				// 返回list <Map> 集合
				map.put("hrefName", analy.getHrefName());
				map.put("picutre", analy.getPictureID());
				map.put("tableId", analy.getTableID());
				for (DictTSetHrefParmPO parm : analy.getHrefParm()) {
					String url = parm.getParmName() + "=" + parm.getParmCon();
					hrefLoc = hrefLoc + url + "&";
				}
				map.put("hrefLoc", hrefLoc.substring(0, hrefLoc.lastIndexOf("&")));
				// 将Map结果集 添加到List
				resultList.add(map);
			}
		}
		return resultList;
	}
	
	// 查询条件设置
	public DictTSetQuerydPO getDataQuerydByStage(String tableID,String stage) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableID", tableID);
		map.put("stage", stage);
		DictTSetQuerydPO queryd = entryOuterMapper.getDataQuerydList(map);
		
		return queryd;
	}
	//查询表在CODE_T_COLLECTTYPE中是否存在
	@Override
	public boolean isTabExits(String tableDbName) {

		String[] keys = {"HQ.BGT","COMMONSETTING_MAINSUB","ISTABEXITS",tableDbName};
		String getRes = (String) dataCacheService.get(keys) ;
		if(null == getRes ){
		    Integer resCount = entryOuterMapper.isTabExits(tableDbName);
			if(resCount>0){
				getRes = "1";
			}else{
				getRes = "0";
			}
			dataCacheService.put(keys, getRes);
		}
		if("1".equals(getRes)){
			return true;
		}else{
			return false;
		}
	}
}
