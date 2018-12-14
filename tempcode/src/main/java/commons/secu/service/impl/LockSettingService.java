package commons.secu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.Constants;
import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.constants.ShowType;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.utils.JSTypeUtils;
import com.tjhq.commons.secu.dao.SecuTLockMapper;
import com.tjhq.commons.secu.external.IDataAuthService;
import com.tjhq.commons.secu.po.SecuTLockPO;
import com.tjhq.commons.secu.service.ILockSettingService;

/**
 * 锁设置接口实现类（单位系统使用)
 * @version 1.0
 * @CreateDate 2014/1/8
 * @author zushuxin
 *
 */
@Component
@Transactional(readOnly=true)
public class LockSettingService implements ILockSettingService {
	
	public static final String LOCKSETTING_PARAM_ALL= "all"; //查询条件 全部
	public static final String LOCKSETTING_PARAM_LOCKED= "locked";//查询条件 已加锁
	public static final String LOCKSETTING_PARAM_UNLOCK= "unlock";//查询条件 未加锁
	
	@Resource
	private SecuTLockMapper secuTLockMapper; 
	@Resource
	private IDataAuthService dataAuthService;
	@Override
	public List<SecuTLockPO> getDeptLockList(String appId, String queryParam) {
		if(LOCKSETTING_PARAM_ALL.equals(queryParam)){
			return secuTLockMapper.getAllDeptLockList(appId);
		}else if(LOCKSETTING_PARAM_LOCKED.equals(queryParam)){
			return secuTLockMapper.getLockedDeptLockList(appId);
		}else{
			return secuTLockMapper.getUnlockDeptLockList(appId);
		}
	}

	@Override
	public Table getDeptLockListHead() {
		Table table = new Table();
		table.setAppID("BGT");
		table.setTableName("司局锁");
		table.setTableID("mytableName");
		table.setTableDBName("mytableName");
		List<Column> columnList = new ArrayList<Column>(); 
		Column objectSeqCol=new Column();
		objectSeqCol.setColumnID("objectSeq");
		objectSeqCol.setColumnName("objectSeq");
		objectSeqCol.setColumnDBName("objectSeq"); 
		objectSeqCol.setAlias("序号");
		objectSeqCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		objectSeqCol.setShowType(ShowType.SHOW_TYPE_HTML);
		objectSeqCol.setDataLength(32);
		objectSeqCol.setKey(true);
		objectSeqCol.setOrderID(0);
		objectSeqCol.setVisible(false);
		columnList.add(objectSeqCol);
		Column objectcodeCol=new Column();
		objectcodeCol.setColumnID("objectcode");
		objectcodeCol.setColumnName("objectcode");
		objectcodeCol.setColumnDBName("objectcode");
		objectcodeCol.setAlias("司局编码");
		objectcodeCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		objectcodeCol.setShowType(ShowType.SHOW_TYPE_HTML);
		objectcodeCol.setDataLength(32);
		objectcodeCol.setKey(false);
		objectcodeCol.setOrderID(1);
		objectcodeCol.setVisible(true);
		objectcodeCol.setReadOnly(true); 
		objectcodeCol.setWidth(200);  
		columnList.add(objectcodeCol);
		Column objectNameCol=new Column();
		objectNameCol.setColumnID("objectName");
		objectNameCol.setColumnName("objectName");
		objectNameCol.setColumnDBName("objectName");
		objectNameCol.setAlias("司局名称");
		objectNameCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		objectNameCol.setShowType(ShowType.SHOW_TYPE_HTML);
		objectNameCol.setDataLength(32);
		objectNameCol.setKey(false);
		objectNameCol.setWidth(400);   
		objectNameCol.setOrderID(2);
		objectNameCol.setVisible(true);
		objectNameCol.setReadOnly(true);  
		columnList.add(objectNameCol); 
		table.setColumnList(columnList);
		return table; 
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void addLockForDept(List<Map> lockDatas,String appId) throws Exception{
		String lockFlag="";
		for(Map lockData: lockDatas ){
			lockFlag = (String)lockData.get("lockFlag");
			if("0".equals(lockFlag)){
			lockData.put("appId", appId);
			secuTLockMapper.addLockForDept(lockData);
			}
		}
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void dropLockForDept(List<Map> unlockDatas, String appId) throws Exception{
			Map param = new HashMap();
			param.put("unlockDatas", unlockDatas);
			param.put("appId", appId);
			secuTLockMapper.dropLockForDept(param);
	}

	@Override
	public Table getAgencyLockListHead() {
		Table table = new Table();
		table.setAppID("BGT");
		table.setTableName("部门锁");
		table.setTableID("mytableName");
		table.setTableDBName("mytableName");
		List<Column> columnList = new ArrayList<Column>(); 
		Column objectSeqCol=new Column();
		objectSeqCol.setColumnID("objectSeq");
		objectSeqCol.setColumnName("objectSeq");
		objectSeqCol.setColumnDBName("objectSeq"); 
		objectSeqCol.setAlias("序号");
		objectSeqCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		objectSeqCol.setShowType(ShowType.SHOW_TYPE_HTML);
		objectSeqCol.setDataLength(32);
		objectSeqCol.setKey(true);
		objectSeqCol.setOrderID(0);
		objectSeqCol.setVisible(false);
		columnList.add(objectSeqCol);
		Column objectcodeCol=new Column();
		objectcodeCol.setColumnID("objectcode");
		objectcodeCol.setColumnName("objectcode");
		objectcodeCol.setColumnDBName("objectcode");
		objectcodeCol.setAlias("部门编码");
		objectcodeCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		objectcodeCol.setShowType(ShowType.SHOW_TYPE_HTML);
		objectcodeCol.setDataLength(32);
		objectcodeCol.setKey(false);
		objectcodeCol.setOrderID(1);
		objectcodeCol.setVisible(true);
		objectcodeCol.setReadOnly(true); 
		objectcodeCol.setWidth(200);  
		columnList.add(objectcodeCol);
		Column objectNameCol=new Column();
		objectNameCol.setColumnID("objectName");
		objectNameCol.setColumnName("objectName");
		objectNameCol.setColumnDBName("objectName");
		objectNameCol.setAlias("部门名称");
		objectNameCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		objectNameCol.setShowType(ShowType.SHOW_TYPE_HTML);
		objectNameCol.setDataLength(32);
		objectNameCol.setKey(false);
		objectNameCol.setWidth(400);   
		objectNameCol.setOrderID(2);
		objectNameCol.setVisible(true);
		objectNameCol.setReadOnly(true);  
		columnList.add(objectNameCol);  
		table.setColumnList(columnList);
		return table; 
	}

	@Override
	public List<SecuTLockPO> getAgencyLockList(String appId, String queryParam) {
		Map param = new HashMap();
		String dbNameForAcctItem = dataAuthService.getTableDatabaseNameBy(appId, Constants.COMMON_DictTAPPCODE_TABLETYPE_AGENCY);
		param.put("dbNameForAgency", dbNameForAcctItem);
		param.put("appId", appId);
		
		if(LOCKSETTING_PARAM_ALL.equals(queryParam)){
			return secuTLockMapper.getAllAgencyLockList(param);
		}else if(LOCKSETTING_PARAM_LOCKED.equals(queryParam)){
			return secuTLockMapper.getLockedAgencyLockList(param);
		}else{
			return secuTLockMapper.getUnlockAgencyLockList(param);
		}
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void addLockForAgency(List<Map> lockDatas, String appId) throws Exception{
		String lockFlag="";
		for(Map lockData: lockDatas ){
			lockFlag = (String)lockData.get("lockFlag");
			if("0".equals(lockFlag)){
			lockData.put("appId", appId);
			secuTLockMapper.addLockForAgency(lockData);
			}
		}
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void dropLockForAgency(List<Map> unlockDatas, String appId) throws Exception{
		Map param = new HashMap();
		param.put("unlockDatas", unlockDatas);
		param.put("appId", appId);
		secuTLockMapper.dropLockForAgency(param);
		
	}

	@Override
	public Table getAcctItemLockListHead() {
		Table table = new Table();
		table.setAppID("BGT");
		table.setTableName("科目锁");
		table.setTableID("mytableName");
		table.setTableDBName("mytableName");
		List<Column> columnList = new ArrayList<Column>(); 
		Column objectSeqCol=new Column();
		objectSeqCol.setColumnID("objectSeq");
		objectSeqCol.setColumnName("objectSeq");
		objectSeqCol.setColumnDBName("objectSeq"); 
		objectSeqCol.setAlias("序号");
		objectSeqCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		objectSeqCol.setShowType(ShowType.SHOW_TYPE_HTML); 
		objectSeqCol.setDataLength(32);
		objectSeqCol.setKey(true);
		objectSeqCol.setOrderID(0);
		objectSeqCol.setVisible(false);
		columnList.add(objectSeqCol);
		Column objectcodeCol=new Column();
		objectcodeCol.setColumnID("objectcode");
		objectcodeCol.setColumnName("objectcode");
		objectcodeCol.setColumnDBName("objectcode");
		objectcodeCol.setAlias("科目编码");
		objectcodeCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		objectcodeCol.setShowType(ShowType.SHOW_TYPE_HTML);
		objectcodeCol.setDataLength(32);
		objectcodeCol.setKey(false);
		objectcodeCol.setOrderID(1);
		objectcodeCol.setVisible(true);
		objectcodeCol.setReadOnly(true); 
		objectcodeCol.setWidth(200);  
		columnList.add(objectcodeCol);
		Column objectNameCol=new Column();
		objectNameCol.setColumnID("objectName");
		objectNameCol.setColumnName("objectName");
		objectNameCol.setColumnDBName("objectName");
		objectNameCol.setAlias("科目名称");
		objectNameCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		objectNameCol.setShowType(ShowType.SHOW_TYPE_HTML);
		objectNameCol.setDataLength(32);
		objectNameCol.setKey(false);
		objectNameCol.setWidth(400);   
		objectNameCol.setOrderID(2);
		objectNameCol.setVisible(true);
		objectNameCol.setReadOnly(true);  
		columnList.add(objectNameCol);  
		table.setColumnList(columnList);
		return table; 
	}

	@Override
	public List<SecuTLockPO> getAcctItemLockList(String appId, String queryParam) {
		Map param = new HashMap();
		String dbNameForAcctItem = dataAuthService.getTableDatabaseNameBy(appId, Constants.COMMON_DictTAPPCODE_TABLETYPE_EXPFUNC);
		param.put("dbNameForAcctItem", dbNameForAcctItem);
		param.put("appId", appId);
		if(LOCKSETTING_PARAM_ALL.equals(queryParam)){
			return secuTLockMapper.getAllAcctItemLockList(param);
		}else if(LOCKSETTING_PARAM_LOCKED.equals(queryParam)){
			return secuTLockMapper.getLockedAcctItemLockList(param);
		}else{
			return secuTLockMapper.getUnlockAcctItemLockList(param);
		}
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void addLockForAcctItem(List<Map> lockDatas, String appId) throws Exception{
		String lockFlag="";
		for(Map<String, String> lockData: lockDatas ){
			lockFlag = (String)lockData.get("lockFlag");
			if("0".equals(lockFlag)){
			lockData.put("appId", appId);
			secuTLockMapper.addLockForAcctItem(lockData);
			}
		}
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void dropLockForAcctItem(List<Map> unlockDatas, String appId) throws Exception{
		Map param = new HashMap();
		param.put("unlockDatas", unlockDatas);
		param.put("appId", appId);
		secuTLockMapper.dropLockForAcctItem(param);
	}

	@Override
	public Table getAppLockListHead() {
		Table table = new Table();
		table.setAppID("BGT");
		table.setTableName("全局锁");
		table.setTableID("mytableName");
		table.setTableDBName("mytableName");
		List<Column> columnList = new ArrayList<Column>(); 
		Column objectSeqCol=new Column();
		objectSeqCol.setColumnID("objectSeq");
		objectSeqCol.setColumnName("objectSeq");
		objectSeqCol.setColumnDBName("objectSeq"); 
		objectSeqCol.setAlias("序号");
		objectSeqCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		objectSeqCol.setShowType(ShowType.SHOW_TYPE_HTML); 
		objectSeqCol.setDataLength(32);
		objectSeqCol.setKey(true);
		objectSeqCol.setOrderID(0);
		objectSeqCol.setVisible(false);
		columnList.add(objectSeqCol);
		Column objectcodeCol=new Column();
		objectcodeCol.setColumnID("objectcode");
		objectcodeCol.setColumnName("objectcode");
		objectcodeCol.setColumnDBName("objectcode");
		objectcodeCol.setAlias("系统编码");
		objectcodeCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		objectcodeCol.setShowType(ShowType.SHOW_TYPE_HTML);
		objectcodeCol.setDataLength(32);
		objectcodeCol.setKey(false);
		objectcodeCol.setOrderID(1);
		objectcodeCol.setVisible(true);
		objectcodeCol.setReadOnly(true); 
		objectcodeCol.setWidth(200);  
		columnList.add(objectcodeCol);
		Column objectNameCol=new Column();
		objectNameCol.setColumnID("objectName");
		objectNameCol.setColumnName("objectName");
		objectNameCol.setColumnDBName("objectName");
		objectNameCol.setAlias("系统名称");
		objectNameCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		objectNameCol.setShowType(ShowType.SHOW_TYPE_HTML);
		objectNameCol.setDataLength(32);
		objectNameCol.setKey(false);
		objectNameCol.setWidth(400);   
		objectNameCol.setOrderID(2);
		objectNameCol.setVisible(true);
		objectNameCol.setReadOnly(true);  
		columnList.add(objectNameCol);  
		table.setColumnList(columnList);
		return table; 
	}

	@Override
	public List<SecuTLockPO> getAppLockListHead(String queryParam) {
		if(LOCKSETTING_PARAM_ALL.equals(queryParam)){
			return secuTLockMapper.getAllAppLockList();
		}else if(LOCKSETTING_PARAM_LOCKED.equals(queryParam)){
			return secuTLockMapper.getLockedAppLockList();
		}else{
			return secuTLockMapper.getUnlockAppLockList();
		}
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void addLockForApp(List<Map> lockDatas) throws Exception{
		String lockFlag="";
		for(Map lockData: lockDatas ){
			lockFlag = (String)lockData.get("lockFlag");
			if("0".equals(lockFlag)){
			secuTLockMapper.addLockForApp(lockData);
			}
		}
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void dropLockForApp(List<Map> unlockDatas) throws Exception{
		Map param = new HashMap();
		param.put("unlockDatas", unlockDatas);
		secuTLockMapper.dropLockForApp(param);
	}

	@Override
	public Table getTableLockListHead() {
		Table table = new Table();
		table.setAppID("BGT");
		table.setTableName("全局锁");
		table.setTableID("mytableName");
		table.setTableDBName("mytableName");
		List<Column> columnList = new ArrayList<Column>(); 
		Column objectSeqCol=new Column();
		objectSeqCol.setColumnID("objectSeq");
		objectSeqCol.setColumnName("objectSeq");
		objectSeqCol.setColumnDBName("objectSeq"); 
		objectSeqCol.setAlias("序号");
		objectSeqCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		objectSeqCol.setShowType(ShowType.SHOW_TYPE_HTML); 
		objectSeqCol.setDataLength(32);
		objectSeqCol.setKey(true);
		objectSeqCol.setOrderID(0);
		objectSeqCol.setVisible(false);
		columnList.add(objectSeqCol); 
		Column objectcodeCol=new Column();
		objectcodeCol.setColumnID("objectId");
		objectcodeCol.setColumnName("objectId");
		objectcodeCol.setColumnDBName("objectId"); 
		objectcodeCol.setAlias("报表编码");
		objectcodeCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		objectcodeCol.setShowType(ShowType.SHOW_TYPE_HTML);
		objectcodeCol.setDataLength(32);
		objectcodeCol.setKey(false);
		objectcodeCol.setOrderID(1);
		objectcodeCol.setVisible(false);
		objectcodeCol.setReadOnly(true); 
		objectcodeCol.setWidth(300);   
		columnList.add(objectcodeCol); 
		Column objectNameCol=new Column();
		objectNameCol.setColumnID("objectName"); 
		objectNameCol.setColumnName("objectName");
		objectNameCol.setColumnDBName("objectName");
		objectNameCol.setAlias("报表名称");
		objectNameCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		objectNameCol.setShowType(ShowType.SHOW_TYPE_HTML);
		objectNameCol.setDataLength(32);
		objectNameCol.setKey(false);
		objectNameCol.setWidth(400);   
		objectNameCol.setOrderID(2);
		objectNameCol.setVisible(true);
		objectNameCol.setReadOnly(true);  
		columnList.add(objectNameCol);  
		table.setColumnList(columnList);
		return table; 
	}

	@Override
	public List<SecuTLockPO> getTableLockList(Map<String, String> param,
			String isSuit,String queryParam) {
		if("true".equalsIgnoreCase(isSuit)){
			if("all".equalsIgnoreCase(queryParam)){
				return queryTableLockListBySuitId(param);
			}else if("unlock".equalsIgnoreCase(queryParam)){
				return queryTableUnLockListBySuitId(param);
			}else if("locked".equalsIgnoreCase(queryParam)){
				return queryTableLockedListBySuitId(param);
			}else{
				return null;
			}
		}else{
			if("all".equalsIgnoreCase(queryParam)){
				param.put("islock",null);
			}else if("unlock".equalsIgnoreCase(queryParam)){
				param.put("islock", "0");
			}else if("locked".equalsIgnoreCase(queryParam)){
				param.put("islock", "1");
			}else{
				param.put("islock", null);  
			}
			return queryTableLockListByTableId(param);
		}	
	}

	private List<SecuTLockPO> queryTableLockedListBySuitId(
			Map<String, String> param) {
		
		return secuTLockMapper.queryTableLockedListBySuitId(param);
	}

	private List<SecuTLockPO> queryTableUnLockListBySuitId(
			Map<String, String> param) {
		return secuTLockMapper.queryTableUnLockListBySuitId(param);
	}

	private List<SecuTLockPO> queryTableLockListByTableId(
			Map<String, String> param) {
		
		return secuTLockMapper.queryTableLockListByTableId(param);
	}

	private List<SecuTLockPO> queryTableLockListBySuitId(
			Map<String, String> param) {
		return secuTLockMapper.queryTableLockListBySuitId(param);
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void addLockForTable(List<Map> lockDatas,String appId) throws Exception{
		String lockFlag="";
		for(Map lockData: lockDatas ){
			lockFlag = (String)lockData.get("lockFlag");
			if("0".equals(lockFlag)){
				lockData.put("appId", appId);
				secuTLockMapper.addLockForTable(lockData);
			}
		}
	}
	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void dropLockForTable(List<Map> unlockDatas) throws Exception{
		Map param = new HashMap();
		param.put("unlockDatas", unlockDatas);
		secuTLockMapper.dropLockForTable(param); 
	}

	@Override
	public Table getAgencySuitLockListHead() {
		Table table = new Table();
		table.setAppID("BGT");
		table.setTableName("套表(部门)锁"); 
		table.setTableID("mytableName");
		table.setTableDBName("mytableName"); 
		List<Column> columnList = new ArrayList<Column>(); 
		Column objectSeqCol=new Column();
		objectSeqCol.setColumnID("objectSeq");
		objectSeqCol.setColumnName("objectSeq");
		objectSeqCol.setColumnDBName("objectSeq"); 
		objectSeqCol.setAlias("序号");
		objectSeqCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		objectSeqCol.setShowType(ShowType.SHOW_TYPE_HTML); 
		objectSeqCol.setDataLength(32);
		objectSeqCol.setKey(true);
		objectSeqCol.setOrderID(0);
		objectSeqCol.setVisible(false);
		columnList.add(objectSeqCol); 
		Column objectIdCol=new Column();
		objectIdCol.setColumnID("objectId");
		objectIdCol.setColumnName("objectId");
		objectIdCol.setColumnDBName("objectId");
		objectIdCol.setAlias("报表编码");
		objectIdCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		objectIdCol.setShowType(ShowType.SHOW_TYPE_HTML);
		objectIdCol.setDataLength(32);
		objectIdCol.setKey(false);
		objectIdCol.setOrderID(1);
		objectIdCol.setVisible(false);  
		objectIdCol.setReadOnly(true); 
		objectIdCol.setWidth(200);
		columnList.add(objectIdCol); 
		Column toIdCol=new Column();
		toIdCol.setColumnID("toId");
		toIdCol.setColumnName("toId"); 
		toIdCol.setColumnDBName("toId");
		toIdCol.setAlias("部门ID");
		toIdCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		toIdCol.setShowType(ShowType.SHOW_TYPE_HTML);
		toIdCol.setDataLength(32);
		toIdCol.setKey(false);
		toIdCol.setOrderID(1);
		toIdCol.setVisible(false);
		toIdCol.setReadOnly(true); 
		toIdCol.setWidth(200);  
		columnList.add(toIdCol);
		Column objectNameCol=new Column(); 
		objectNameCol.setColumnID("objectName");  
		objectNameCol.setColumnName("objectName");
		objectNameCol.setColumnDBName("objectName");
		objectNameCol.setAlias("部门名称");
		objectNameCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		objectNameCol.setShowType(ShowType.SHOW_TYPE_HTML); 
		objectNameCol.setDataLength(32);
		objectNameCol.setKey(false);
		objectNameCol.setWidth(400);   
		objectNameCol.setOrderID(2);
		objectNameCol.setVisible(true); 
		objectNameCol.setReadOnly(true);  
		columnList.add(objectNameCol);  
		table.setColumnList(columnList);
		return table; 
	}

	@Override
	public List<SecuTLockPO> getAgencySuitLockList(Map<String, String> param,
			String queryParam,String appId) {
		String dbNameForAgency = dataAuthService.getTableDatabaseNameBy(appId, Constants.COMMON_DictTAPPCODE_TABLETYPE_AGENCY);
		param.put("dbNameForAgency", dbNameForAgency);
		try{
			if("all".equals(queryParam)){
				return secuTLockMapper.getAgencySuitLockList(param);
			}else if("unlock".equals(queryParam)){
				return secuTLockMapper.getAgencySuitUnLockList(param);
			}else if("locked".equals(queryParam)){
				return secuTLockMapper.getAgencySuitLockedList(param);
			}else{
				return null;  
			}
		}catch(Exception e){
			return null;
		}
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void addLockForAgencySuit(List<Map> lockDatas, String appId) throws Exception{
		String lockFlag="";
		for(Map lockData: lockDatas ){
			lockFlag = (String)lockData.get("lockFlag");
			if("0".equals(lockFlag)){
				lockData.put("appId", appId);
				secuTLockMapper.addLockForAgencySuit(lockData);
			}
		}
		
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void dropLockForAgencySuit(List<Map> unlockDatas, String appId,
			String suitId) throws Exception{
		Map param = new HashMap();
		param.put("appId", appId);
		param.put("tableId", suitId);
		param.put("unlockDatas", unlockDatas);
		secuTLockMapper.dropLockForAgencySuit(param);
	}
	
	@Override
	public Table getDeptSuitLockListHead() {
		Table table = new Table();
		table.setAppID("BGT");
		table.setTableName("套表(司局)锁"); 
		table.setTableID("mytableName");
		table.setTableDBName("mytableName"); 
		List<Column> columnList = new ArrayList<Column>(); 
		Column objectSeqCol=new Column();
		objectSeqCol.setColumnID("objectSeq");
		objectSeqCol.setColumnName("objectSeq");
		objectSeqCol.setColumnDBName("objectSeq"); 
		objectSeqCol.setAlias("序号");
		objectSeqCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		objectSeqCol.setShowType(ShowType.SHOW_TYPE_HTML); 
		objectSeqCol.setDataLength(32);
		objectSeqCol.setKey(true);
		objectSeqCol.setOrderID(0);
		objectSeqCol.setVisible(false);
		columnList.add(objectSeqCol); 
		Column objectIdCol=new Column();
		objectIdCol.setColumnID("objectId");
		objectIdCol.setColumnName("objectId");
		objectIdCol.setColumnDBName("objectId");
		objectIdCol.setAlias("报表编码");
		objectIdCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		objectIdCol.setShowType(ShowType.SHOW_TYPE_HTML);
		objectIdCol.setDataLength(32);
		objectIdCol.setKey(false);
		objectIdCol.setOrderID(1);
		objectIdCol.setVisible(false);
		objectIdCol.setReadOnly(true); 
		objectIdCol.setWidth(200);  
		columnList.add(objectIdCol); 
		Column toIdCol=new Column();
		toIdCol.setColumnID("toId");
		toIdCol.setColumnName("toId"); 
		toIdCol.setColumnDBName("toId");
		toIdCol.setAlias("司局ID");
		toIdCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		toIdCol.setShowType(ShowType.SHOW_TYPE_HTML);
		toIdCol.setDataLength(32);
		toIdCol.setKey(false);
		toIdCol.setOrderID(1);
		toIdCol.setVisible(false);
		toIdCol.setReadOnly(true); 
		toIdCol.setWidth(200);  
		columnList.add(toIdCol);
		Column objectNameCol=new Column();
		objectNameCol.setColumnID("objectName"); 
		objectNameCol.setColumnName("objectName");
		objectNameCol.setColumnDBName("objectName");
		objectNameCol.setAlias("司局名称"); 
		objectNameCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		objectNameCol.setShowType(ShowType.SHOW_TYPE_HTML);
		objectNameCol.setDataLength(32);
		objectNameCol.setKey(false);
		objectNameCol.setWidth(400);   
		objectNameCol.setOrderID(2);
		objectNameCol.setVisible(true);
		objectNameCol.setReadOnly(true);  
		columnList.add(objectNameCol);  
		table.setColumnList(columnList); 
		return table; 
	}

	@Override
	public List<SecuTLockPO> getDeptSuitLockList(Map<String, String> param,
			String queryParam) {
		if("all".equals(queryParam)){
			return secuTLockMapper.getDeptSuitLockList(param);
		}else if("unlock".equals(queryParam)){
			return secuTLockMapper.getDeptSuitUnLockList(param);
		}else if("locked".equals(queryParam)){
			return secuTLockMapper.getDeptSuitLockedList(param);
		}else{
			return null;
		}
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void addLockForDeptSuit(List<Map> lockDatas, String appId) throws Exception{
		String lockFlag="";
		for(Map lockData: lockDatas ){
			lockFlag = (String)lockData.get("lockFlag");
			if("0".equals(lockFlag)){
				lockData.put("appId", appId);
				secuTLockMapper.addLockForDeptSuit(lockData);
			}
		}
		
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void dropLockForDeptSuit(@SuppressWarnings("rawtypes") List<Map> unlockDatas, String appId,
			String suitId) throws Exception{
		Map<String,Object> param = new HashMap<String,Object>(); 
		param.put("appId", appId);
		param.put("tableId", suitId);
		param.put("unlockDatas", unlockDatas);
		secuTLockMapper.dropLockForDeptSuit(param);
	}
	
}
