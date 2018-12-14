package commons.setting.input.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.cache.service.IDataCacheService;
import com.tjhq.commons.dao.UtilsMapper;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.constants.ShowType;
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.inputcomponent.grid.commonGrid.service.impl.CommonGridService;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.PageInfo;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.utils.JSTypeUtils;
import com.tjhq.commons.jackson.ObjectMapper;
import com.tjhq.commons.setting.external.po.RECPO;
import com.tjhq.commons.setting.input.dao.MainRefMapper;
import com.tjhq.commons.setting.input.service.IMainRefService;
import com.tjhq.commons.utils.StringUtil;

@Service
@Transactional(readOnly=true)
public class MainRefService extends CommonGridService implements IMainRefService {

	@Resource
	private MainRefMapper mainRefMapper;
	@Resource
	private IDataCacheService dataCacheService;
	@Resource
	private UtilsMapper utilsMapper;
	
	private static String GROUPFLAG ="0"; //分组标志 0 ：未分组。
	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void saveProjTabEntity( Map<String, String> map ) throws ServiceException{
		//清空缓存
		String[] keys = {"HQ.BGT","COMMONSETTING_MAINSUB"};
		dataCacheService.put(keys, null);
		mainRefMapper.insertProjTabEntity( map );
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void updateProjTabEntity( Map<String, String> map) throws ServiceException{
		//清空缓存
		String[] keys = {"HQ.BGT","COMMONSETTING_MAINSUB"};
		dataCacheService.put(keys, null);
		mainRefMapper.updateProjTabEntity( map );
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void deleteProjTabEntityById( String id ) throws ServiceException{
		//清空缓存
		String[] keys = {"HQ.BGT","COMMONSETTING_MAINSUB"};
		dataCacheService.put(keys, null);
		mainRefMapper.deleteProjTabEntityById( id );
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String saveSetREC( Table table, String objectid, String tableid, 
            String showcols,String titlewidth, String recid  ) throws ServiceException{
		//清空缓存
		String[] keys = {"HQ.BGT","COMMONSETTING_MAINSUB"};
		dataCacheService.put(keys, null);
            Map<String, Object> map = new HashMap<String, Object>();
            //保存主表
            map.put( "OBJECTID", objectid );
            map.put( "SHOWCOLS", Integer.parseInt( showcols ) );
            map.put( "TITLEWIDTH", Integer.parseInt( titlewidth )  );
            map.put( "TABLEID", tableid );
            if(StringUtil.isEmpty( recid )){
                mainRefMapper.insertSetREC( map );
                List<RECPO> list = this.getSetRECList( map );
                if(list.size() > 1){
                    throw new ServiceException(ExceptionCode.ERR_00001,"页面定义表中单行返回多行！",false);
                }
                if(list.size()>0){
                    RECPO po = ( RECPO )list.get(0);
                    recid = po.getRECID();
                }
            }else{
                this.updateSetREC( map );
            }
            
            if(!StringUtil.isEmpty( recid )){
                
                //保存明细表
                CommonGrid commonGrid =(CommonGrid) table;
                List<Map<String,Object>> insertList = commonGrid.getInsertValues();
                List<Map<String,Object>> deleteList = commonGrid.getDeleteValues();
                // 更新
                List<Map<String,Object>> updateList = commonGrid.getUpdateValues();
                String guid="";
                Map<String,String> groupMap = new HashMap<String, String>();
                
                // 公组框数据保存
                for(int i=0;i<insertList.size();i++){
                    String ctrlName=(String) insertList.get(i).get("CTRLNAME");
                    if("".equals(ctrlName) || ctrlName==null){
                        throw new ServiceException(ExceptionCode.SET_02104,"保存失败！属性名称不能为空！",false);
                    }
                    map = insertList.get(i);
                    map.put( "RECID", recid );
                    if(map.get( "CTRLID" ).toString().contains( "group_" ) || !map.get( "ISGROUPCTRL" ).toString().equals( GROUPFLAG )){
                        guid=this.getDBUniqueID();
                        groupMap.put( map.get( "CTRLID" ).toString(), guid );
                        map.put( "CTRLID", guid );
                        this.saveSetRECDetail( map );
                    }
                }
                // 属性保存
                for(int i=0;i<insertList.size();i++){
                    map = insertList.get( i );
                    map.put( "RECID", recid );
                    if(map.get( "SUPERID" ).toString().contains( "group_" )|| !map.get( "SUPERID" ).toString().equals( "0" )){
                        if(groupMap.get( map.get( "SUPERID" ).toString())!=null){
                            map.put( "SUPERID", groupMap.get( map.get( "SUPERID" ).toString()));
                        }
                        this.saveSetRECDetail( map );
                    }else if(map.get( "ISGROUPCTRL" ).toString().equals( GROUPFLAG )){
                        this.saveSetRECDetail( map );
                    }
                }
    
                // 删除
                for(int i=0;i<deleteList.size();i++){
                    map = deleteList.get( i );
                    map.put( "RECID", recid );
                    this.deleteSetRECDetail( map );
                }
                
                for(int i=0;i<updateList.size();i++){
                    map = updateList.get( i );
                    map.put( "RECID", recid );
                    this.updateSetRECDetail( map );
                }
            }
            return recid;
	}

	private void updateSetREC( Map<String, Object> map ) throws ServiceException{
		//清空缓存
		String[] keys = {"HQ.BGT","COMMONSETTING_MAINSUB"};
		dataCacheService.put(keys, null);
		try {
            mainRefMapper.updateSetREC( map );
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.SET_02105,"修改详细设置页面出现异常！",false);
        }
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void deleteSetRECById( String id ) throws ServiceException{
		//清空缓存
		String[] keys = {"HQ.BGT","COMMONSETTING_MAINSUB"};
		dataCacheService.put(keys, null);
		try {
            mainRefMapper.deleteSetRECById( id );
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.SET_02105,"删除详细页面设置出现异常!",false);
        }
	}

	private void saveSetRECDetail( Map<String, Object> map ) throws ServiceException{
		//清空缓存
		String[] keys = {"HQ.BGT","COMMONSETTING_MAINSUB"};
		dataCacheService.put(keys, null);
		try {
            mainRefMapper.insertSetRECDetail( map );
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.SET_02105,"保存详细页面设置出现异常!",false);
        }
	}

	private void updateSetRECDetail( Map<String, Object> map )throws ServiceException {
		//清空缓存
		String[] keys = {"HQ.BGT","COMMONSETTING_MAINSUB"};
		dataCacheService.put(keys, null);
		try {
            mainRefMapper.updateSetRECDetail( map );
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.SET_02105,"修改详细页面设置出现异常！",false);
        }
	}

	private void deleteSetRECDetail( Map<String, Object> map ) throws ServiceException{
		//清空缓存
		String[] keys = {"HQ.BGT","COMMONSETTING_MAINSUB"};
		dataCacheService.put(keys, null);
		mainRefMapper.deleteSetRECDetail( map );
	}

/*@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Table getProjReportDefine() {
	Table grid = new Table();
	grid.setReadOnly(false);
	grid.setxType("GeneralInput");
	setColumns2(grid);
	return grid;
}*/
/*public void setColumns2(Table data) {

	List<Column> list = new ArrayList<Column>();

	Column tableIDCol=new Column();
	tableIDCol.setColumnID("TABLEID");
	tableIDCol.setColumnDBName("TABLEID");
	tableIDCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
	tableIDCol.setShowType(ShowType.SHOW_TYPE_HTML);
	tableIDCol.setDataLength(10);
	tableIDCol.setWidth(200);
	tableIDCol.setKey(true);
	tableIDCol.setVisible(false);
	tableIDCol.setOrderID(1);

	Column objectIDCol=new Column();
	objectIDCol.setColumnID("OBJECTID");
	objectIDCol.setColumnDBName("OBJECTID");
	objectIDCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
	objectIDCol.setShowType(ShowType.SHOW_TYPE_HTML);
	objectIDCol.setDataLength(10);
	objectIDCol.setWidth(100);
	objectIDCol.setKey(true);
	objectIDCol.setVisible(false);
	objectIDCol.setOrderID(2);
	
	Column isShowCol=new Column();
	isShowCol.setColumnID("ISSHOW");
	isShowCol.setColumnName("是否显示");
	isShowCol.setAlias("是否显示");
	isShowCol.setLeaf(true);
	isShowCol.setColumnDBName("ISSHOW");
	isShowCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
	isShowCol.setShowType(ShowType.SHOW_TYPE_LIST);
	isShowCol.setDataLength(10);
	isShowCol.setWidth(100);
	isShowCol.setKey(false);
	isShowCol.setReadOnly(false);
	isShowCol.setVisible(true);
	isShowCol.setOrderID(3);
	
	Column flagCol=new Column();
	flagCol.setColumnID("FLAG");
	flagCol.setColumnDBName("FLAG");
	flagCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
	flagCol.setShowType(ShowType.SHOW_TYPE_HTML);
	flagCol.setDataLength(10);
	flagCol.setWidth(100);
	flagCol.setKey(true);
	flagCol.setVisible(false);
	flagCol.setOrderID(4);
	
	Column colSpanCol=new Column();
	colSpanCol.setColumnID("COLSPAN");
	colSpanCol.setColumnName("所占列数");
	colSpanCol.setAlias("所占列数");
	colSpanCol.setLeaf(true);
	colSpanCol.setColumnDBName("COLSPAN");
	colSpanCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
	colSpanCol.setShowType(ShowType.SHOW_TYPE_HTML);
	colSpanCol.setDataLength(10);
	colSpanCol.setWidth(100);
	colSpanCol.setKey(false);
	colSpanCol.setReadOnly(false);
	colSpanCol.setVisible(true);
	colSpanCol.setOrderID(5);
	
	Column orderIDCol=new Column();
	orderIDCol.setColumnID("ORDERID");
	orderIDCol.setColumnName("显示顺序");
	orderIDCol.setAlias("显示顺序");
	orderIDCol.setLeaf(true);
	orderIDCol.setColumnDBName("ORDERID");
	orderIDCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
	orderIDCol.setShowType(ShowType.SHOW_TYPE_HTML);
	orderIDCol.setDataLength(10);
	orderIDCol.setWidth(100);
	orderIDCol.setKey(true);
	orderIDCol.setReadOnly(false);
	orderIDCol.setVisible(true);
	orderIDCol.setOrderID(6);
	
	Column tableNameCol=new Column();
	tableNameCol.setColumnID("TABLENAME");
	tableNameCol.setColumnName("显示顺序");
	tableNameCol.setAlias("显示顺序");
	tableNameCol.setLeaf(true);
	tableNameCol.setColumnDBName("TABLENAME");
	tableNameCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
	tableNameCol.setShowType(ShowType.SHOW_TYPE_HTML);
	tableNameCol.setDataLength(10);
	tableNameCol.setWidth(100);
	tableNameCol.setKey(false);
	tableNameCol.setReadOnly(false);
	tableNameCol.setVisible(true);
	tableNameCol.setOrderID(7);
	
	Column dealNameCol=new Column();
	dealNameCol.setColumnID("DEALNAME");
	dealNameCol.setColumnName("处理类型");
	dealNameCol.setAlias("处理类型");
	dealNameCol.setLeaf(true);
	dealNameCol.setColumnDBName("DEALNAME");
	dealNameCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
	dealNameCol.setShowType(ShowType.SHOW_TYPE_HTML);
	dealNameCol.setDataLength(10);
	dealNameCol.setWidth(100);
	dealNameCol.setKey(false);
	dealNameCol.setReadOnly(false);
	dealNameCol.setVisible(true);
	dealNameCol.setOrderID(8);
	
	Column remarkCol=new Column();
	remarkCol.setColumnID("REMARK");
	remarkCol.setColumnName("说明");
	remarkCol.setAlias("说明");
	remarkCol.setLeaf(true);
	remarkCol.setColumnDBName("REMARK");
	remarkCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
	remarkCol.setShowType(ShowType.SHOW_TYPE_HTML);
	remarkCol.setDataLength(10);
	remarkCol.setWidth(100);
	remarkCol.setKey(false);
	remarkCol.setReadOnly(false);
	remarkCol.setVisible(true);
	remarkCol.setOrderID(9);
	
	Column detailCol=new Column();
	detailCol.setColumnID("DETAIL");
	detailCol.setColumnName("详细信息");
	detailCol.setAlias("详细信息");
	detailCol.setLeaf(true);
	detailCol.setColumnDBName("DETAIL");
	detailCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
	detailCol.setShowType(ShowType.SHOW_TYPE_HTML);
	detailCol.setDataLength(10);
	detailCol.setWidth(100);
	detailCol.setKey(false);
	detailCol.setReadOnly(false);
	detailCol.setVisible(true);
	detailCol.setOrderID(10);
	
	Column mainTableCol=new Column();
	mainTableCol.setColumnID("mainTable");
	mainTableCol.setColumnName("关系设置");
	mainTableCol.setAlias("关系设置");
	mainTableCol.setLeaf(true);
	mainTableCol.setColumnDBName("mainTable");
	mainTableCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
	mainTableCol.setShowType(ShowType.SHOW_TYPE_HTML);
	mainTableCol.setDataLength(10);
	mainTableCol.setWidth(100);
	mainTableCol.setKey(false);
	mainTableCol.setReadOnly(false);
	mainTableCol.setVisible(true);
	mainTableCol.setOrderID(11);
	
	Column isMainTableCol=new Column();
	isMainTableCol.setColumnID("ISMAINTABLE");
	isMainTableCol.setColumnName("是否主表");
	isMainTableCol.setAlias("是否主表");
	isMainTableCol.setLeaf(true);
	isMainTableCol.setColumnDBName("ISMAINTABLE");
	isMainTableCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
	isMainTableCol.setShowType(ShowType.SHOW_TYPE_LIST);
	isMainTableCol.setDataLength(10);
	isMainTableCol.setWidth(100);
	isMainTableCol.setKey(false);
	isMainTableCol.setReadOnly(false);
	isMainTableCol.setVisible(true);
	isMainTableCol.setOrderID(12);
	
	Column isSeserveCol=new Column();
	isSeserveCol.setColumnID("ISRESERVE");
	isSeserveCol.setColumnName("是否保留");
	isSeserveCol.setAlias("是否保留");
	isSeserveCol.setLeaf(true);
	isSeserveCol.setColumnDBName("ISRESERVE");
	isSeserveCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
	isSeserveCol.setShowType(ShowType.SHOW_TYPE_LIST);
	isSeserveCol.setDataLength(10);
	isSeserveCol.setWidth(100);
	isSeserveCol.setKey(false);
	isSeserveCol.setReadOnly(false);
	isSeserveCol.setVisible(true);
	isSeserveCol.setOrderID(12);
	
	Column isreadOnlyCol=new Column();
	isreadOnlyCol.setColumnID("ISREADONLY");
	isreadOnlyCol.setColumnName("是否只读");
	isreadOnlyCol.setAlias("是否只读");
	isreadOnlyCol.setLeaf(true);
	isreadOnlyCol.setColumnDBName("ISREADONLY");
	isreadOnlyCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
	isreadOnlyCol.setShowType(ShowType.SHOW_TYPE_HTML);
	isreadOnlyCol.setDataLength(10);
	isreadOnlyCol.setWidth(100);
	isreadOnlyCol.setKey(false);
	isreadOnlyCol.setReadOnly(false);
	isreadOnlyCol.setVisible(true);
	isreadOnlyCol.setOrderID(13);
	
	list.add(tableIDCol);
	list.add(objectIDCol);
	list.add(isShowCol);
	list.add(flagCol);
	list.add(colSpanCol);
	list.add(orderIDCol);
	list.add(tableNameCol);
	list.add(dealNameCol);
	list.add(remarkCol);
	list.add(detailCol);
	list.add(mainTableCol);
	list.add(isMainTableCol);
	list.add(isSeserveCol);
	list.add(isreadOnlyCol);
	data.setColumnList(list);
}*/
	
	@Override
	public List getProjReportListData( Map<String, String> map ) {
		if(map.get("selectID").toString().equals("1")){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("OBJECTID", map.get("objectid").toString());
			params.put("TYPEFLAG", map.get("typeflag").toString());
			params.put("PROCESSID", map.get("processId").toString());
			int tableNumber  = mainRefMapper.getTabList(params).size();
			if(tableNumber>0){
				map.put("spfType", "1");
			}
		}
		return mainRefMapper.getProjReportListData(map);
	}

	@Override
	
	public Table getPageColumnDefine() throws ServiceException{
	Table grid = new Table();
	grid.setTableID("column");
	grid.setTableDBName("mytableName");
	grid.setTableName("表格的列");
	grid.setReadOnly(false);
	grid.setxType("GeneralInput");
	try {
        setColumns(grid);
    } catch (Exception e) {
        e.printStackTrace();
        throw new ServiceException(ExceptionCode.ERR_00000,"设置表头出现异常！",false);
    }
	return grid;
	}
	public void setColumns(Table data) {

		List<Column> list = new ArrayList<Column>();

		Column ctrNameCol=new Column();
		ctrNameCol.setColumnID("CTRLNAME");
		ctrNameCol.setColumnName("属性名称");
		ctrNameCol.setAlias("属性名称");
		ctrNameCol.setLeaf(true);
		ctrNameCol.setColumnDBName("CTRLNAME");
		ctrNameCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		ctrNameCol.setShowType(ShowType.SHOW_TYPE_HTML);
		ctrNameCol.setDataLength(10);
		ctrNameCol.setWidth(200);
		ctrNameCol.setKey(false);
		ctrNameCol.setNullable(false);
		ctrNameCol.setReadOnly(false);
		ctrNameCol.setVisible(true);
		ctrNameCol.setOrderID(1);

		Column ctrLIDCol=new Column();
		ctrLIDCol.setColumnID("CTRLID");
		ctrLIDCol.setLeaf(true);
		ctrLIDCol.setColumnDBName("CTRLID");
		ctrLIDCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		ctrLIDCol.setShowType(ShowType.SHOW_TYPE_HTML);
		ctrLIDCol.setDataLength(10);
		ctrLIDCol.setWidth(100);
		ctrLIDCol.setKey(true);
		ctrLIDCol.setVisible(false);
		ctrLIDCol.setOrderID(2);
		
		Column idCol=new Column();
		idCol.setColumnID("ID");
		idCol.setColumnDBName("ID");
		idCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		idCol.setShowType(ShowType.SHOW_TYPE_HTML);
		idCol.setDataLength(10);
		idCol.setWidth(100);
		idCol.setKey(true);
		idCol.setVisible(false);
		idCol.setOrderID(3);
		
		Column isShowCol=new Column();
		isShowCol.setColumnID("ISSHOW");
		isShowCol.setColumnName("是否显示");
		isShowCol.setAlias("是否显示");
		isShowCol.setLeaf(true);
		isShowCol.setColumnDBName("ISSHOW");
		isShowCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		isShowCol.setShowType(ShowType.SHOW_TYPE_LIST);
		isShowCol.setDataLength(10);
		isShowCol.setDefaultValue("1");
		isShowCol.setDefaultShowValue("是");
		isShowCol.setWidth(100);
		isShowCol.setKey(false);
		isShowCol.setReadOnly(false);
		isShowCol.setVisible(true);
		isShowCol.setOrderID(4);
		
		Column isTextCol=new Column();
		isTextCol.setColumnID("ISTEXT");
		isTextCol.setColumnName("是否大文本");
		isTextCol.setAlias("是否大文本");
		isTextCol.setLeaf(true);
		isTextCol.setColumnDBName("ISTEXT");
		isTextCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		isTextCol.setShowType(ShowType.SHOW_TYPE_LIST);
		isTextCol.setDataLength(10);
		isTextCol.setDefaultShowValue("否");
		isTextCol.setDefaultValue("0");
		isTextCol.setWidth(100);
		isTextCol.setKey(false);
		isTextCol.setReadOnly(false);
		isTextCol.setVisible(true);
		isTextCol.setOrderID(5);
		
		Column processIDCol=new Column();
		processIDCol.setColumnID("PROCESSID");
		processIDCol.setColumnDBName("PROCESSID");
		processIDCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		processIDCol.setShowType(ShowType.SHOW_TYPE_HTML);
		processIDCol.setDataLength(10);
		processIDCol.setWidth(100);
		processIDCol.setKey(true);
		processIDCol.setVisible(false);
		processIDCol.setOrderID(6);
		
		Column colSpanCol=new Column();
		colSpanCol.setColumnID("COLSPAN");
		colSpanCol.setColumnName("所占列数");
		colSpanCol.setAlias("所占列数");
		colSpanCol.setLeaf(true);
		colSpanCol.setColumnDBName("COLSPAN");
		colSpanCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		colSpanCol.setShowType(ShowType.SHOW_TYPE_HTML);
		colSpanCol.setDataLength(10);
		colSpanCol.setWidth(100);
		colSpanCol.setKey(false);
		colSpanCol.setReadOnly(false);
		colSpanCol.setVisible(true);
		colSpanCol.setOrderID(7);
		
		Column rowSpanCol=new Column();
		rowSpanCol.setColumnID("ROWSPAN");
		rowSpanCol.setColumnName("所占行数");
		rowSpanCol.setAlias("所占行数");
		rowSpanCol.setLeaf(true);
		rowSpanCol.setColumnDBName("ROWSPAN");
		rowSpanCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		rowSpanCol.setShowType(ShowType.SHOW_TYPE_HTML);
		rowSpanCol.setDataLength(10);
		rowSpanCol.setWidth(100);
		rowSpanCol.setKey(false);
		rowSpanCol.setReadOnly(false);
		rowSpanCol.setVisible(true);
		rowSpanCol.setOrderID(8);
		
		Column orderIDCol=new Column();
		orderIDCol.setColumnID("ORDERID");
		orderIDCol.setColumnName("排序");
		orderIDCol.setAlias("排序");
		orderIDCol.setLeaf(true);
		orderIDCol.setColumnDBName("ORDERID");
		orderIDCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		orderIDCol.setShowType(ShowType.SHOW_TYPE_HTML);
		orderIDCol.setDataLength(10);
		orderIDCol.setWidth(100);
		orderIDCol.setKey(false);
		orderIDCol.setReadOnly(false);
		orderIDCol.setVisible(true);
		orderIDCol.setOrderID(9);
		
		
		list.add(ctrNameCol);
		list.add(ctrLIDCol);
		list.add(idCol);
		list.add(isShowCol);
		list.add(isTextCol);
		list.add(processIDCol);
		list.add(colSpanCol);
		list.add(rowSpanCol);
		list.add(orderIDCol);

		data.setColumnList(list);
	}
	@Override
    public Object getPageColumnListData(Map<String, String> map) throws ServiceException {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> dataList = null;
        try {
            dataList = mainRefMapper.getPageColumnListData(map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.SET_02104,"获取详细设置出现异常！",false);
        }
        super.setGridData(dataList, pageInfo);
        return pageInfo;
    }

	@Override
	public String getDBUniqueID() {
		return utilsMapper.getDBUniqueID();
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void deleteSetREC(Map map) throws ServiceException{
		//清空缓存
		String[] keys = {"HQ.BGT","COMMONSETTING_MAINSUB"};
		dataCacheService.put(keys, null);
		mainRefMapper.deleteSetREC(map);
	}

	@Override
	public int getMainCount(String OBJECTID, String TYPEFLAG) {
		return mainRefMapper.getMainCount(OBJECTID,TYPEFLAG);
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void deleteMainTable(String Id) throws ServiceException{
		//清空缓存
		String[] keys = {"HQ.BGT","COMMONSETTING_MAINSUB"};
		dataCacheService.put(keys, null);
		mainRefMapper.deleteMainTable(Id);
	}

/*	@Override
	public Table getMainTabDefine() {
		Table grid = new Table();
		grid.setTableID("column");
		grid.setTableDBName("mytableName");
		grid.setTableName("表格的列");
		grid.setReadOnly(false);
		grid.setxType("GeneralInput");
		setColumnName(grid);
		return grid;
	}*/
	/*public void setColumnName(Table data) {

		List<Column> list = new ArrayList<Column>();

		Column ctrlnameColumn=new Column();
		ctrlnameColumn.setColumnID("NAME");
		ctrlnameColumn.setColumnName("列名称");
		ctrlnameColumn.setAlias("列名称");
		ctrlnameColumn.setLeaf(true);
		ctrlnameColumn.setColumnDBName("NAME");
		ctrlnameColumn.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		ctrlnameColumn.setShowType(ShowType.SHOW_TYPE_HTML);
		ctrlnameColumn.setDataLength(10);
		ctrlnameColumn.setWidth(200);
		ctrlnameColumn.setKey(false);
		ctrlnameColumn.setReadOnly(false);
		ctrlnameColumn.setVisible(true);
		ctrlnameColumn.setOrderID(1);
		list.add(ctrlnameColumn);
		data.setColumnList(list);
	}

*/	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String saveMain(Map<String, Object> map) throws ServiceException{
		//清空缓存
		String[] keys = {"HQ.BGT","COMMONSETTING_MAINSUB"};
		dataCacheService.put(keys, null);
		String saveType = "";
		if(map.get("MAINFKID")!=null && !map.get("MAINFKID").toString().equals("")){
			if(map.get("reserveFlag").toString().equals("1")){
				
				List<Map<String, Object>> tabList = mainRefMapper.getProjTabById(map.get("PROJTABID").toString());
				
				String pObjectId = tabList.get(0).get("OBJECTID").toString();
				String objectId = map.get("OBJECTID").toString();
				String tableId = tabList.get(0).get("TABLEID").toString();
				String processId = tabList.get(0).get("PROCESSID").toString();
				
				//复制报表设置其他信息
				Map<String, Object> tabListMap = new HashMap<String, Object>();
				tabListMap.put("OBJECTID", pObjectId);
				tabListMap.put("TYPEFLAG", tabList.get(0).get("TYPEFLAG").toString());
				tabListMap.put("PROCESSID", processId);	
				
				List<Map<String, Object>> tableList = mainRefMapper.getTabList(tabListMap);
				if(tabList.size()>0){
					for(Map<String, Object> row : tableList){
						if(!row.get("TABLEID").toString().equals(tableId) && row.get("ISRESERVE").toString().equals("0")){
							String Id = this.getDBUniqueID();//生成主表主键
							List<Map<String, Object>> mainRow = this.getMainList(row.get("GUID").toString());
							//复制主表记录
							row.put("GUID", Id);
							row.put("OBJECTID", objectId);
							mainRefMapper.insertTab(row);
							
							//复制表关系记录
							if(mainRow.size()>0){
								mainRow.get(0).put("PROJTABID", Id);
								mainRefMapper.saveMainTable(mainRow.get(0));
							}
							
							String oTableId = row.get("TABLEID").toString();
							//保存明细信息			
							this.saveReportDetail(processId, oTableId, pObjectId, objectId);
						}
						
					}
				}
				
				this.saveReportDetail(processId, tableId, pObjectId, objectId);
				
				String guid = this.getDBUniqueID();
				tabList.get(0).put("GUID", guid);
				tabList.get(0).put("OBJECTID", objectId);
				mainRefMapper.insertTab(tabList.get(0));
				
				map.put("PROJTABID", guid);
				mainRefMapper.saveMainTable(map);
				
			}else{
				if(map.get("savetype").toString().equals("0")){
					mainRefMapper.saveMainTable(map);
				}else if(map.get("savetype").toString().equals("1")){
					mainRefMapper.updateMainTable(map);
				}
			}
			saveType = "1";
		}else{
			mainRefMapper.deleteMainTable(map.get("PROJTABID").toString());
			saveType = "0";
		}
		
		return saveType;
	}

	@Override
	public List getMainList(String id) {
		return mainRefMapper.getMainList(id);
	}
	
	@Override
	public List<RECPO> getSetRECList( Map<String, Object> map ) throws ServiceException{
		
		try {
            return mainRefMapper.getSetRECList( map );
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.SET_02104,"获取报表明细主表信息出现异常！",false);
        }
	}

	@Override
	public List<Map<String, Object>> getProcessList(String typeflag) {
		List<Map<String, Object>> list = null;
		if(typeflag.equals("0")){
			list = mainRefMapper.getSPFjProcess();
		}else if(typeflag.equals("1")){
			list = mainRefMapper.getProjProcess();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getTabList(Map<String, Object> params){
		return mainRefMapper.getTabList(params);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String saveTabInfo(String objectId, String typeflag, String processId, List list)  throws ServiceException{
		
		//清空缓存
		String[] keys = {"HQ.BGT","COMMONSETTING_MAINSUB"};
		dataCacheService.put(keys, null);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("objectId", objectId);
		params.put("typeflag", typeflag);
		params.put("processId", processId);	
		
		for(int i=0; i<list.size(); i++){
			String copyId = list.get(i).toString();
			this.deleteAllReportInfo(objectId, typeflag, copyId);
			List<Map<String, Object>> tabList = mainRefMapper.getTabList(params);
			if(tabList.size()>0){
				for(Map<String, Object> tabRow : tabList){
					
					String lineId = this.getDBUniqueID();//生成主表主键
					List<Map<String, Object>> mainRow = this.getMainList(tabRow.get("GUID").toString());
					
					//复制主表记录
					tabRow.put("GUID", lineId);
					tabRow.put("PROCESSID", copyId);
					mainRefMapper.insertTab(tabRow);
					
					//复制表关系记录
					if(mainRow.size()>0){
						mainRow.get(0).put("PROJTABID", lineId);
						mainRefMapper.saveMainTable(mainRow.get(0));
					}
					
					params.put("tableId", tabRow.get("TABLEID").toString());
					List<Map<String, Object>> recRow = mainRefMapper.getTabRecList(params);
					
					if(recRow.size()>0){
						String recId = recRow.get(0).get("RECID").toString();
						List<Map<String, Object>> detailRows = mainRefMapper.getTabDetail(recId);
						
						recId = this.getDBUniqueID();
						recRow.get(0).put("RECID", recId);
						recRow.get(0).put("PROCESSID", copyId);						
						//复制明细主表信息
						mainRefMapper.insertRec(recRow.get(0));
						
						if(detailRows.size()>0){
							Map<String, Object> groupIdMap = new HashMap<String, Object>();
							
							//复制公组框信息
							for(Map<String, Object> detailRow : detailRows){
								if(detailRow.get("ISGROUPCTRL").toString().equals("1")){
									String groupId = this.getDBUniqueID();
									groupIdMap.put(detailRow.get("CTRLID").toString(), groupId);
									detailRow.put("CTRLID", groupId);
									detailRow.put("RECID", recId);
									this.saveSetRECDetail(detailRow);
									
								}
							}
							
							//复制详细属性信息
							for(Map<String, Object> detailRow : detailRows){
								String ctrId = this.getDBUniqueID();
								if(detailRow.get("ISGROUPCTRL").toString().equals("0")){
									if(!detailRow.get("SUPERID").toString().equals("0")){
										String superId = detailRow.get("SUPERID").toString();
										//detailRow.put("CTRLID", ctrId);
										detailRow.put("RECID", recId);
										detailRow.put("SUPERID", groupIdMap.get(superId));
										this.saveSetRECDetail(detailRow);
									}else{
										//detailRow.put("CTRLID", ctrId);
										detailRow.put("RECID", recId);
										this.saveSetRECDetail(detailRow);	
									}
									
								}
							}
						}						
					}
				}
			}
		}		
		return "1";
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void delDetailByRecId(String RECID) throws ServiceException{
		//清空缓存
		String[] keys = {"HQ.BGT","COMMONSETTING_MAINSUB"};
		dataCacheService.put(keys, null);
		mainRefMapper.delDetailByRecId(RECID);		
	}

	@Override
	public int getDetailCountByRecId(String RECID) {
		return mainRefMapper.getDetailCountByRecId(RECID);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void savePColumn(String paramsMap) throws ServiceException{
		//清空缓存
		String[] keys = {"HQ.BGT","COMMONSETTING_MAINSUB"};
		dataCacheService.put(keys, null);
		
		Map<String, Object> data = (Map<String, Object>)(new ObjectMapper()).readJson(paramsMap, Map.class);
		String recId = this.getDBUniqueID();
		Map<String, Object> recData = new HashMap<String, Object>();
		
		Map<String, Object> tabRow = (Map<String, Object>)(new ObjectMapper()).readJson(data.get("tabData").toString(), Map.class);
		
		Map<String, Object> tabListMap = new HashMap<String, Object>();
		tabListMap.put("objectId", tabRow.get("OBJECTID").toString());
		tabListMap.put("typeflag", tabRow.get("TYPEFLAG").toString());
		tabListMap.put("processId", data.get("processId"));	
		
		List<Map<String, Object>> tabList = mainRefMapper.getTabList(tabListMap);
		if(tabList.size()>0){
			for(Map<String, Object> row : tabList){
				if(!row.get("TABLEID").toString().equals(data.get("tableid").toString()) && row.get("ISRESERVE").toString().equals("0")){
					String Id = this.getDBUniqueID();//生成主表主键
					List<Map<String, Object>> mainRow = this.getMainList(row.get("GUID").toString());
					String sObjectId = tabRow.get("OBJECTID").toString();
					//复制主表记录
					row.put("GUID", Id);
					row.put("OBJECTID", data.get("objectid").toString());
					mainRefMapper.insertTab(row);
					
					//复制表关系记录
					if(mainRow.size()>0){
						mainRow.get(0).put("PROJTABID", Id);
						mainRefMapper.saveMainTable(mainRow.get(0));
					}
					
					String tableId = row.get("TABLEID").toString();
					//保存明细信息			
					this.saveReportDetail(data.get("processId").toString(), tableId, sObjectId, data.get("objectid").toString());
				}
				
			}
		}
		
		String lineId = this.getDBUniqueID();//生成主表主键
		List<Map<String, Object>> mainRow = this.getMainList(tabRow.get("ID").toString());
		
		//复制主表记录
		tabRow.put("GUID", lineId);
		tabRow.put("OBJECTID", data.get("objectid").toString());
		mainRefMapper.insertTab(tabRow);
		
		//复制表关系记录
		if(mainRow.size()>0){
			mainRow.get(0).put("PROJTABID", lineId);
			mainRefMapper.saveMainTable(mainRow.get(0));
		}
		
		//保存明细主表信息
		recData.put("OBJECTID", data.get("objectid"));
		recData.put("RECID", recId);
		recData.put("SHOWCOLS", data.get("showcols"));
		recData.put("TITLEWIDTH", data.get("titlewidth"));
		recData.put("PROCESSID", data.get("processId"));
		recData.put("TABLEID", data.get("tableid"));
		mainRefMapper.insertRec(recData);
		
		List<Map<String, Object>> detailData = (List<Map<String, Object>>)(new ObjectMapper()).readJson(data.get("pageData").toString(), List.class);
		if(detailData.size()>0){
			Map<String, Object> groupIdMap = new HashMap<String, Object>();
			
			//复制公组框信息
			for(Map<String, Object> detailRow : detailData){
				if(detailRow.get("ISGROUPCTRL").toString().equals("1")){
					String groupId = this.getDBUniqueID();
					groupIdMap.put(detailRow.get("CTRLID").toString(), groupId);
					detailRow.put("CTRLID", groupId);
					detailRow.put("RECID", recId);
					this.saveSetRECDetail(detailRow);
					
				}
			}
			
			//复制详细属性信息
			for(Map<String, Object> detailRow : detailData){
				if(detailRow.get("ISGROUPCTRL").toString().equals("0")){
					if(!detailRow.get("SUPERID").toString().equals("0")){
						String superId = detailRow.get("SUPERID").toString();
						//detailRow.put("CTRLID", ctrId);
						detailRow.put("RECID", recId);
						detailRow.put("SUPERID", groupIdMap.get(superId));
						this.saveSetRECDetail(detailRow);
					}else{
						//detailRow.put("CTRLID", ctrId);
						detailRow.put("RECID", recId);
						this.saveSetRECDetail(detailRow);	
					}
					
				}
			}
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void saveTabFormParent(String objectId, List<Map<String, Object>> list) throws ServiceException {
		//清空缓存
		String[] keys = {"HQ.BGT","COMMONSETTING_MAINSUB"};
		dataCacheService.put(keys, null);
		String processId = "";
		String pObjectId = "";
		String tableId = "";
		for(Map<String, Object> tabRow : list){
			if(tabRow.get("ISRESERVE").toString().equals("0")){
				processId = tabRow.get("PROCESSID").toString();
				pObjectId = tabRow.get("OBJECTID").toString();
				tableId = tabRow.get("TABLEID").toString();
				
				List<Map<String, Object>> mainRow = this.getMainList(tabRow.get("ID").toString());
				
				//复制主表记录
				String guid = this.getDBUniqueID();
				tabRow.put("OBJECTID", objectId);			
				tabRow.put("GUID", guid);
				mainRefMapper.insertTab(tabRow);
				
				//复制表关系记录
				if(mainRow.size()>0){
					mainRow.get(0).put("PROJTABID", guid);
					mainRefMapper.saveMainTable(mainRow.get(0));
				}
				
				//保存明细信息			
				this.saveReportDetail(processId, tableId, pObjectId, objectId);
			}
			
			
		}
		
	}
	
	/**
	 * 保存明细信息
	 * @param processId
	 * @param tableId
	 * @param pObjectId
	 * @param objectId
	 */
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void saveReportDetail(String processId, String tableId, String pObjectId, String objectId) throws ServiceException{
		//清空缓存
		String[] keys = {"HQ.BGT","COMMONSETTING_MAINSUB"};
		dataCacheService.put(keys, null);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("processId", processId);
		params.put("tableId", tableId);
		params.put("objectId", pObjectId);
		List<Map<String, Object>> recRow = mainRefMapper.getTabRecList(params);
		
		if(recRow.size()>0){
			String RECID = recRow.get(0).get("RECID").toString();
			List<Map<String, Object>> detailRows = mainRefMapper.getTabDetail(RECID);
			
			RECID = this.getDBUniqueID();
			recRow.get(0).put("RECID", RECID);
			recRow.get(0).put("OBJECTID", objectId);						
			//复制明细主表信息
			mainRefMapper.insertRec(recRow.get(0));
			
			if(detailRows.size()>0){
				Map<String, Object> groupIdMap = new HashMap<String, Object>();
				
				//复制公组框信息
				for(Map<String, Object> detailRow : detailRows){
					if(detailRow.get("ISGROUPCTRL").toString().equals("1")){
						String groupId = this.getDBUniqueID();
						groupIdMap.put(detailRow.get("CTRLID").toString(), groupId);
						detailRow.put("CTRLID", groupId);
						detailRow.put("RECID", RECID);
						this.saveSetRECDetail(detailRow);
						
					}
				}
				
				//复制详细属性信息
				for(Map<String, Object> detailRow : detailRows){
					if(detailRow.get("ISGROUPCTRL").toString().equals("0")){
						if(!detailRow.get("SUPERID").toString().equals("0")){
							String superId = detailRow.get("SUPERID").toString();
							detailRow.put("RECID", RECID);
							detailRow.put("SUPERID", groupIdMap.get(superId));
							this.saveSetRECDetail(detailRow);
						}else{
							detailRow.put("RECID", RECID);
							this.saveSetRECDetail(detailRow);	
						}
						
					}
				}
			}						
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String saveBatchCopy(String objectId, String typeflag,
			String processId, List list) throws ServiceException{
		//清空缓存
		String[] keys = {"HQ.BGT","COMMONSETTING_MAINSUB"};
		dataCacheService.put(keys, null);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("objectId", objectId);
		params.put("typeflag", typeflag);
		params.put("processId", processId);	
		
		for(int i=0; i<list.size(); i++){
			String copyId = list.get(i).toString();
			this.deleteAllReportInfo(copyId, typeflag, processId);
			
			List<Map<String, Object>> tabList = mainRefMapper.getTabList(params);
			if(tabList.size()>0){
				for(Map<String, Object> tabRow : tabList){
					
					String lineId = this.getDBUniqueID();//生成主表主键
					List<Map<String, Object>> mainRow = this.getMainList(tabRow.get("GUID").toString());
					
					//复制主表记录
					tabRow.put("GUID", lineId);
					tabRow.put("OBJECTID", copyId);
					mainRefMapper.insertTab(tabRow);
					
					//复制表关系记录
					if(mainRow.size()>0){
						mainRow.get(0).put("PROJTABID", lineId);
						mainRefMapper.saveMainTable(mainRow.get(0));
					}
					
					String tableId = tabRow.get("TABLEID").toString();
					//保存明细信息			
					this.saveReportDetail(processId, tableId, objectId, copyId);
				}
			}
		}		
		return "1";
	}
	
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	private void deleteAllReportInfo(String objectId, String typeflag, String processId) throws ServiceException{
		//清空缓存
		String[] keys = {"HQ.BGT","COMMONSETTING_MAINSUB"};
		dataCacheService.put(keys, null);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("OBJECTID", objectId);
		params.put("TYPEFLAG", typeflag);
		params.put("PROCESSID", processId);
		List<Map<String, Object>> tabList = mainRefMapper.getTabList(params);
		if(tabList.size()>0){
			for(Map<String, Object> tabRow : tabList){
				mainRefMapper.deleteMainTable(tabRow.get("GUID").toString());
				mainRefMapper.deleteProjTabEntityById(tabRow.get("GUID").toString());
				params.put("tableId", tabRow.get("TABLEID").toString());
				List<Map<String, Object>> recList = mainRefMapper.getTabRecList(params);
				if(recList.size()>0){
					mainRefMapper.deleteSetRECById(recList.get(0).get("RECID").toString());
					mainRefMapper.deleteSetREC(tabRow);
				}				
			}
		}
	}

}
