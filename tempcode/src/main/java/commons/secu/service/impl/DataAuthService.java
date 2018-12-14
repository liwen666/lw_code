package commons.secu.service.impl;

import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.Constants;
import com.tjhq.commons.cache.service.IDataCacheService;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.secu.constants.SecuCacheKey;
import com.tjhq.commons.secu.dao.ColumnLimitMapper;
import com.tjhq.commons.secu.dao.SecuTAgencyMapper;
import com.tjhq.commons.secu.dao.SecuTColMapper;
import com.tjhq.commons.secu.dao.SecuTLimitcolMapper;
import com.tjhq.commons.secu.dao.SecuTLimittabMapper;
import com.tjhq.commons.secu.dao.SecuTLockMapper;
import com.tjhq.commons.secu.dao.SecuTRowMapper;
import com.tjhq.commons.secu.dao.SecuTTableMapper;
import com.tjhq.commons.secu.external.IDataAuthService;
import com.tjhq.commons.secu.po.ColumnLimitPO;
import com.tjhq.commons.secu.po.SecuTColPO;
import com.tjhq.commons.secu.po.SecuTLockPO;
import com.tjhq.commons.secu.po.SecuTRowPO;
import com.tjhq.commons.secu.po.SecuTTablePO;
import com.tjhq.exp.task.po.TaskTTaskinstancePO;

/**
 * 数据权限接口实现类（单位系统使用)
 * @version 1.0
 * @CreateDate 2014/1/8
 * @author zushuxin
 *
 */
@Component
@Transactional(readOnly=true)
public class DataAuthService implements IDataAuthService {
	
	public static final String USERINFO_USERTYPE_CZ = "1";//财政
	public static final String USERINFO_USERTYPE_DW = "0";//单位
	
	@Resource
	private SecuTLockMapper secuTLockMapper; 
	@Resource
	private SecuTColMapper secuTColMapper;
	@Resource
	private SecuTRowMapper secuTRowMapper;
	@Resource
	private SecuTTableMapper secuTTableMapper;
	@Resource 
	private SecuTAgencyMapper secuTAgencyMapper;
	@Resource
	private IDictTModelService dictTModelService;
	@Resource 
	private SecuTLimittabMapper secuTLimittabMapper;
	@Resource 
	private SecuTLimitcolMapper secuTLimitcolMapper;
	@Resource
	private IDictTFactorService dictTFactorService;
	@Resource
	private ColumnLimitMapper columnLimitMapper;
	@Resource
	private IDataCacheService dataCacheService;
	
	public static final int DATA_AUTHORITY_WRITEN   = 3;   //数据权限常量 --可写
	public static final int DATA_AUTHORITY_READONLY = 2;   //数据权限常量 --只读
	public static final int DATA_AUTHORITY_HIDDEN   = 1;   //数据权限常量 --不可见
	
	public static final int DATA_AUTHORITY_EXT_TRUE = 1;   //可写 --功能开启
	public static final int DATA_AUTHORITY_EXT_FLASE = 0;  //可写 --功能关闭
	
	public static final int DATA_AUTHORITY_MANTYPE_USER = 1;//关联类型 ：用户
	public static final int DATA_AUTHORITY_MANTYPE_ROLE = 0;//关联类型 : 角色
	
	public static final int DATA_AUTHORITY_ISSUIT_TRUE=1; //是否为套表ID :是 
	public static final int DATA_AUTHORITY_ISSUIT_FLASE=0;//是否为套表ID :否 
	
	
	public static final String DATA_AUTHORITY_ACCTITEM_COLNAME = "EXPFUNCID"; //科目编码 -》数据库表-》列名
	
	
	/**
     * 获得表权限通过用户id
     * @param tableID --表ID
     * @param userID --用户ID
     * @param appID --子系统ID
     * @param cascade --级联查询此表的行权限和列权限
     * @return SecuTTable --表权限实体类
     */
    public SecuTTablePO getTableAuthByUser(String tableID, String userID, String userType, String appID, boolean cascade) {
    	// 获得角色对表/套表的表权限
        SecuTTablePO secuTTable = null;
    	// 缓存key
		String[] key = { SecuCacheKey.CACHE_KEY_SECU, "getTableAuthByUser", tableID, userID, userType, appID, cascade+""};
		// 先取缓存数据
		if (dataCacheService.get(key) != null) {
			secuTTable = (SecuTTablePO) dataCacheService.get(key);
			return secuTTable;
		}
    	
        // 获得角色对表/套表的表权限
        secuTTable = getSecuTtableLimit(tableID, userID);

        if (cascade) {
	        String tableRowSQL = this.getTableRowAuthByUser(tableID, userID, appID, "1");
	        String tableRowSQLReadOnly = this.getTableRowAuthByUser(tableID, userID, appID, "2");
	        secuTTable.setSecuTRow(tableRowSQL);
	        secuTTable.setSecuTRowReadOnly(tableRowSQLReadOnly);
	
	        Map<String, SecuTColPO> secuTCols = this.getTableColsAuthByUser(tableID, userID);
	        for (String colid : secuTCols.keySet()) {
	            SecuTColPO po = secuTCols.get(colid);
	            // 列权限要和表权限交集
	            if (Integer.parseInt(secuTTable.getBaseSecu()) < Integer.parseInt(po.getBaseSecu())) {
	                po.setBaseSecu(secuTTable.getBaseSecu());
	            }
	        }
	        secuTTable.setSecuTCols(secuTCols);
        }
        //放入缓存
        dataCacheService.put(key, secuTTable);
        return secuTTable;
    }
	
	/**
	 * 获得表行限制的SQL条件通过用户id
	 * @param tableID --表ID
	 * @param userId --用户ID
	 * @param appId --子系统ID
	 * @return String --获取所有行数据及行权限，单元格权限的SQL语句 例:select rowsecu,CELLAUTH,a.* from ...
	 */
	public String getTableRowAuthByUser(String tableID, String userId,String appId) {
		// 1.获得该用户的所有角色
		List<Map<String,String>> roleIds = secuTAgencyMapper.getRoleIdListByUserId(userId);
		// 2.转掉内部查询缓存的方法
		//1. 获得科目锁集合
		List<SecuTLockPO> acctItemLocks = getAcctItemLockList(appId);
		//2. 获得表输入限制-》行限制-》科目限制集合
		List<Map<String, String>> acctItemLimits = getAcctItemInputLimitList(tableID,appId);
		//3. 获得每一个角色对表的行限制的集合
		List<SecuTRowPO> role2TableRowLimits = getRole2TableRowLimitLists(tableID,roleIds);
		//4. 获得输入限制中单元格权限的集合
		Map<String,Map<String,String>> tableCellLimits = getTableCellLimits(tableID);
		//5. 通过tableID 查询出DictTModel的实体类
		DictTModelPO dictTModul = dictTModelService.getDictTModelOFID(tableID,true);
		//5. 生成表行限制的SQL语句
		return  generateTableRowLimitSQLString(acctItemLocks,acctItemLimits,role2TableRowLimits,tableCellLimits,dictTModul);
	}
	
	
	/**
     * 部门预算系统数据采集流程的表权限： true: 可写，false : 只读
     * @param tableID --表ID
     * @param userId --用户ID
     * @param toDoAgencyId --要填写报表数据的单位ID
     */
	public boolean getTableCanWriteAuthForDataInput(String tableId,String userId,String userType,String toDoAgencyId){
		return true;
	}
	
	/**
	 * 获得角色对表/套表的表权限
	 * @param tableID
	 * @param userId
	 * @return
	 */
    private SecuTTablePO getSecuTtableLimit(String tableID, String userID) {
        SecuTTablePO secuTTable = secuTTableMapper.getTableSecu(userID, tableID);

        // 如果还是未发现有权限定义， 那么secuTTable则为最大的可写操作包括扩展操作(增删改)
        if (secuTTable == null) {
            secuTTable = new SecuTTablePO();
            secuTTable.setBaseSecu(DATA_AUTHORITY_WRITEN + "");
            secuTTable.setExtAdd(DATA_AUTHORITY_EXT_TRUE + "");
            secuTTable.setExtUpdate(DATA_AUTHORITY_EXT_TRUE + "");
            secuTTable.setExtDel(DATA_AUTHORITY_EXT_TRUE + "");

        }
        // 统一进行设置用户ID，是否为套表ID 和表ID 。 前面计算后都没有设置用户ID，表ID及是否为套表ID。
        secuTTable.setManId(userID);
        secuTTable.setTableId(tableID);
        secuTTable.setIsSuit(DataAuthService.DATA_AUTHORITY_ISSUIT_FLASE + "");
        return secuTTable;
    }

	/**
	 * 获得表行限制的SQL条件通过用户id
	 * @param tableID --表ID
	 * @param userID --用户ID
	 * @param appId --子系统ID
	 * @return String --获取所有行数据及行权限，单元格权限的SQL语句 例:select rowsecu,CELLAUTH,a.* from ...
	 */
	public String getTableRowAuthByUser(String tableID, String userID,String appId,String baseSecu) {
		return this.getTableRowSecuSql(tableID, userID, baseSecu); 
	}
	/**
	 * 获得角色集合对行限制的SQL语句
	 * @param tableID 表ID
	 * @param roleIds 角色ID集合
	 * @return String 行限制结果集的SQL语句
	 */
    private String getTableRowSecuSql(String tableID, String userID, String baseSecu) {
        List<String> rowSecuList = secuTRowMapper.getTableRowSecu(userID, tableID, baseSecu);
        //int roleCount = secuTRowMapper.getUserRoleCount(userID);
        //if (rowSecuList == null || rowSecuList.size() == 0 || roleCount != rowSecuList.size()) {
        if (rowSecuList == null || rowSecuList.size() == 0) {
            return null;
        }
        StringBuffer secuBuffer = new StringBuffer(" ( ");
        for (String secuStr : rowSecuList) {
            secuBuffer.append(" OR ( ").append(secuStr).append(" ) ");
        }
        secuBuffer.append(" ) ");
        return secuBuffer.toString().replaceFirst("OR", "");
    }
	/**
	 * 生成表行限制的SQL语句
	 * @param acctItemLocks 科目锁集合
	 * @param acctItemLimits 输入限制中科目限制
	 * @param role2TableRowLimits 角色对表的行限制的集合
	 * @param tableCellLimits 输入限制中单元格权限的集合
	 * @param dictTModul DictTModel的实体类
	 * @return String 表行限制的SQL语句
	 */
	private String generateTableRowLimitSQLString(
			List<SecuTLockPO> acctItemLocks,
			List<Map<String, String>> acctItemLimits,
			List<SecuTRowPO> role2TableRowLimits,
			Map<String,Map<String,String>> tableCellLimits,DictTModelPO dictTModul) {
		String roleId;
		List<SecuTRowPO> secuTRowForRoleTmp;
		 //secuTRowListGroupByRole变量是用来存储将行权限按照角色进行分组的行限制数据
		 //key为roleId,List<SecuTRow>为行权限限制结合，大小最大为2，一个为只读，一个为不可见。
		Map<String,List<SecuTRowPO>> secuTRowListGroupByRole = new HashMap<String,List<SecuTRowPO>>();
		//1.循环role2TableRowLimits，先按照角色将行权限进行分组
		for(SecuTRowPO secuTRow : role2TableRowLimits){
			secuTRowForRoleTmp = null;
			roleId = secuTRow.getManId();
			secuTRowForRoleTmp = secuTRowListGroupByRole.get(roleId);
			if(secuTRowForRoleTmp == null){
				secuTRowForRoleTmp = new ArrayList<SecuTRowPO>();
			}
			secuTRowForRoleTmp.add(secuTRow);
			secuTRowListGroupByRole.put(roleId,secuTRowForRoleTmp);
		}
		secuTRowForRoleTmp = null;
		//leastLimitCount变量是记录了拥有最少限制的数量，2为只读和不可见，1为只读或不可见，0为没有进行限制。
		int leastLimitCount = 2;
		//leastLimitRole变量是记录了拥有最少限制的角色ID。记录下来，供下面的计算进行使用
		String leastLimitRole = "";
		//2.计算出所有角色中行限制最少的数量为多少与拥有最少行限制的角色ID。
		for(Map.Entry<String,List<SecuTRowPO>> entry: secuTRowListGroupByRole.entrySet()) { 
			secuTRowForRoleTmp = entry.getValue();
			if(secuTRowForRoleTmp != null){
				if(secuTRowForRoleTmp.size() <=leastLimitCount){
					leastLimitCount = secuTRowForRoleTmp.size();
					leastLimitRole = entry.getKey();
				}
			}else{
				leastLimitCount = 0;
				leastLimitRole = entry.getKey();
			}
			
		}
		
		if(secuTRowListGroupByRole.entrySet().size()==0){
			leastLimitCount=0;
		}
		secuTRowForRoleTmp = null;

		String finalRowLimitSQLScript = "";
		//通过判断leastLimitCount来进行选择生成行权限的SQL语句
		switch (leastLimitCount){
			//当leastLimitCount==0时，则表示用户的所有角色中至少有一个角色没有进行设置只读和不可见的限制，所以在生成行限制的话不用考虑角色对表的行限制条件
			case 0: finalRowLimitSQLScript = generateTableRowLimitSQLStringWithoutRowListOfRole(acctItemLocks,acctItemLimits,tableCellLimits,dictTModul);break;
			//当leastLimitCount==1时，则表示用户的所有角色中至少有一个角色只进行了只读或不可见的限制，所以在生成行限制的话要考虑角色对表的行限制条件：加入只读，或是加入不可见
			case 1: finalRowLimitSQLScript = generateTableRowLimitSQLStringWithoneRowListOfRole(acctItemLocks,acctItemLimits,tableCellLimits,secuTRowListGroupByRole,dictTModul);break;
			//当leastLimitCount==2时，则表示用户中的所有角色都进行只读和不可见的限制，要完全考虑角色对表的行限制条件
			case 2: finalRowLimitSQLScript = generateTableRowLimitSQLStringWithtwoRowListOfRole(acctItemLocks,acctItemLimits,tableCellLimits,secuTRowListGroupByRole,dictTModul);break;
		}
		return finalRowLimitSQLScript;
	}
	/**
	 * 只读和不可见的限制，要完全考虑角色对表的行限制条件条件进行生成表行限制的SQL语句
	 * @param acctItemLocks 科目锁集合
	 * @param acctItemLimits 输入限制中科目限制
	 * @param role2TableRowLimits 角色对表的行限制的集合
	 * @param tableCellLimits 输入限制中单元格权限的集合
	 * @param dictTModul DictTModel的实体类
	 * @return String 表行限制的SQL语句
	 */
	private String generateTableRowLimitSQLStringWithtwoRowListOfRole(
			List<SecuTLockPO> acctItemLocks,
			List<Map<String, String>> acctItemLimits,
			Map<String,Map<String, String>> tableCellLimits,
			Map<String, List<SecuTRowPO>> secuTRowListGroupByRole,
			DictTModelPO dictTModul) {
		List<SecuTRowPO> secuTRowListHidden = new ArrayList<SecuTRowPO>();
		List<SecuTRowPO> secuTRowListReadonly = new ArrayList<SecuTRowPO>();
		for(Map.Entry<String,List<SecuTRowPO>> entry: secuTRowListGroupByRole.entrySet()) {
			for(SecuTRowPO secuTRowTmp : entry.getValue()){
				if(secuTRowTmp.getBaseSecu().equals(DataAuthService.DATA_AUTHORITY_HIDDEN+"")){
					secuTRowListHidden.add(secuTRowTmp);
				}else{
					secuTRowListReadonly.add(secuTRowTmp);
				}
			}
		}
		List<Map<String,String>> acctItemLimitReadonlyList = new ArrayList<Map<String,String>>();
		List<Map<String,String>> acctItemLimitHiddenList = new ArrayList<Map<String,String>>();
		//数据库表名
		boolean acctItemLocksFlag = false;
		String dbTablename =  dictTModul.getDbtablename();
		int dbcolSize = 0;

		List<DictTFactorPO> dictTFactorsTmp =  dictTModul.getDictTFactorList();
		List<DictTFactorPO> dictTFactors = new ArrayList<DictTFactorPO>();
		//1.过滤标题列
		for(DictTFactorPO dictTFactor : dictTFactorsTmp){
			if(DataAuthService.DATA_AUTHORITY_ACCTITEM_COLNAME.equalsIgnoreCase(dictTFactor.getDbcolumnname())){
				acctItemLocksFlag =true;
			}
			dictTFactors.add(dictTFactor);
		}
		dbcolSize = dictTFactors.size();
		StringBuffer cellLimitStringForWrite = new StringBuffer();
		StringBuffer cellLimitStringForReadOnly = new StringBuffer();
		
		String cellConditionLimit="";
		Map<String,String> tableCellLimit= new HashMap<String,String>();
		//2.初始化可写，只读列权限字符串。
		
		for (int index=0;index<dbcolSize;index++){
			DictTFactorPO dictTFactor = dictTFactors.get(index);
			tableCellLimit =tableCellLimits.get(dictTFactor.getColumnid());

			if(tableCellLimit != null){
				cellConditionLimit = "(CASE WHEN ("+tableCellLimit.get("LIMITCON")+") THEN '0' ELSE '1' END)";
				cellLimitStringForWrite.append(cellConditionLimit);
			}else{
				cellLimitStringForWrite.append("'"+DataAuthService.DATA_AUTHORITY_EXT_TRUE+"'");
			}
			if(index != (dbcolSize-1)){
				cellLimitStringForWrite.append("||");
			}
			cellLimitStringForReadOnly.append("0");
		}
		//3.先将输入限制行限制进行分类。
		if(acctItemLocksFlag){
			for(Map<String,String> acctItemLimit : acctItemLimits){
				
				if(Integer.parseInt(acctItemLimit.get("LIMITCON"))==DataAuthService.DATA_AUTHORITY_HIDDEN){//当等于1的时候是不可见
					acctItemLimitHiddenList.add(acctItemLimit);
				}else if(Integer.parseInt(acctItemLimit.get("LIMITCON"))==DataAuthService.DATA_AUTHORITY_READONLY){//当等于2的时候是只读
					acctItemLimitReadonlyList.add(acctItemLimit);
				}
			}
		}else{
			acctItemLimits= new ArrayList<Map<String,String>>();
			acctItemLocks = new ArrayList<SecuTLockPO>();
		}
		
		StringBuffer resultSetSQLForWriteLimitWhere = new StringBuffer();
		resultSetSQLForWriteLimitWhere.append("(");
		
		//4.先获得可写的结果集 可写的结果集=去掉只读+不可见的。
		StringBuffer acctItemLockWriteSQLWhere = new StringBuffer();
		if(acctItemLocksFlag){
		acctItemLockWriteSQLWhere.append("SELECT '1' as ROWSECU,"+cellLimitStringForWrite+" as CELLAUTH,a.* FROM "+dbTablename+" a WHERE a."+DataAuthService.DATA_AUTHORITY_ACCTITEM_COLNAME+" NOT IN (");
		for(SecuTLockPO acctItemLock : acctItemLocks){
			acctItemLockWriteSQLWhere.append("'"+acctItemLock.getObjectId()+"',");
		}
		for(Map<String,String> acctItemLimit : acctItemLimits){
			acctItemLockWriteSQLWhere.append("'"+acctItemLimit.get("LIMITID")+"',");
		}
		acctItemLockWriteSQLWhere.replace(acctItemLockWriteSQLWhere.length()-1, acctItemLockWriteSQLWhere.length(), " ");
		}else{
			acctItemLockWriteSQLWhere.append("SELECT '1' as ROWSECU,"+cellLimitStringForWrite+" as CELLAUTH,a.* FROM "+dbTablename+" a WHERE 1=1 ");
		}
		
		if(secuTRowListHidden.size() != 0 ||secuTRowListReadonly.size() != 0 ){
			if(acctItemLimits.size()!=0 ||acctItemLocks.size()!=0){
				acctItemLockWriteSQLWhere.append(" ) ");
			}
			acctItemLockWriteSQLWhere.append(" and not exists (select DATAKEY from "+dbTablename+" where ( ");
		}else{
			if(acctItemLimits.size()!=0 ||acctItemLocks.size()!=0){
				acctItemLockWriteSQLWhere.append(" ) ");
			}
		}
		for(SecuTRowPO secuTRow : secuTRowListHidden){
			acctItemLockWriteSQLWhere.append(secuTRow.getSqlWhere()+" OR ");
		}
		for(SecuTRowPO secuTRow : secuTRowListReadonly){
			acctItemLockWriteSQLWhere.append(secuTRow.getSqlWhere()+" OR ");
		}
		if(secuTRowListHidden.size() != 0 ||secuTRowListReadonly.size() != 0 ){
			acctItemLockWriteSQLWhere.replace(acctItemLockWriteSQLWhere.length()-3, acctItemLockWriteSQLWhere.length(), " ").append(" ) and a.DATAKEY=DATAKEY ) ");
		}
		
		//5.获得只读的结果集 只读的结果集
		String finalRowLimitSQL="";
		if(acctItemLocks.size() !=0 || acctItemLimitReadonlyList.size()!=0|| secuTRowListReadonly.size()!=0 ){
		StringBuffer acctItemLockReadonlySQLWhere = new StringBuffer();
		acctItemLockReadonlySQLWhere.append("SELECT '0' as ROWSECU,'"+cellLimitStringForReadOnly+"' as CELLAUTH,a.* FROM "+dbTablename+" a ");
		
		
		if(acctItemLocks.size() !=0 || acctItemLimitReadonlyList.size()!=0 || secuTRowListReadonly.size()!=0){
			acctItemLockReadonlySQLWhere.append("WHERE  ");
		}
		if(acctItemLocks.size() !=0 || acctItemLimitReadonlyList.size()!=0){
			acctItemLockReadonlySQLWhere.append("a."+DataAuthService.DATA_AUTHORITY_ACCTITEM_COLNAME+" IN (");
		}
		for(SecuTLockPO acctItemLock : acctItemLocks){
			acctItemLockReadonlySQLWhere.append("'"+acctItemLock.getObjectId()+"',");
		}
		for(Map<String,String> acctItemLimitReadonly : acctItemLimitReadonlyList){
			acctItemLockReadonlySQLWhere.append("'"+acctItemLimitReadonly.get("LIMITID")+"',");
		}
		acctItemLockReadonlySQLWhere.replace(acctItemLockReadonlySQLWhere.length()-1, acctItemLockReadonlySQLWhere.length(), " ");
		if(acctItemLocks.size() !=0 || acctItemLimitReadonlyList.size()!=0){
			if(secuTRowListReadonly.size()!=0){
				acctItemLockReadonlySQLWhere.append(") and exists (select DATAKEY from  "+dbTablename+"  where (");
			}
		}else{
			if(secuTRowListReadonly.size()!=0){
				acctItemLockReadonlySQLWhere.append(" exists (select DATAKEY from "+dbTablename+" where (");
			}
		}
		for(SecuTRowPO secuTRow : secuTRowListReadonly){
			acctItemLockReadonlySQLWhere.append(secuTRow.getSqlWhere()+" OR ");
		}
		
		
		if(secuTRowListReadonly.size()!=0 || secuTRowListHidden.size()!=0 ){
			acctItemLockReadonlySQLWhere.replace(acctItemLockReadonlySQLWhere.length()-3, acctItemLockReadonlySQLWhere.length(), " ").append(" ) and a.DATAKEY=DATAKEY )");
		}
		
		if(secuTRowListHidden != null &&secuTRowListHidden.size()>0){
			acctItemLockReadonlySQLWhere.append(" and not exists (select DATAKEY from "+dbTablename+" where (");
			for(SecuTRowPO secuTRow : secuTRowListHidden){
				acctItemLockReadonlySQLWhere.append(secuTRow.getSqlWhere()+" OR ");
			}
			acctItemLockReadonlySQLWhere.replace(acctItemLockReadonlySQLWhere.length()-3, acctItemLockReadonlySQLWhere.length(), " ").append(" ) and a.DATAKEY=DATAKEY )");
		}
		
		//6.生成最终查询语句 可写结果集UNION 只读结果集
		finalRowLimitSQL = "select b.* from ( "+acctItemLockWriteSQLWhere.toString()+" UNION ALL "+acctItemLockReadonlySQLWhere.toString()+") b";
		}else{
		//6.生成最终查询语句 可写结果集UNION 只读结果集
			finalRowLimitSQL  = "select b.* from ( "+acctItemLockWriteSQLWhere.toString()+") b";
		}
		
		return finalRowLimitSQL;
		
	}
	/**
	 * 在生成行限制的SQL语句时要考虑角色对表的行限制条件：加入只读，或是加入不可见，或是两个都不加。
	 * @param acctItemLocks 科目锁集合
	 * @param acctItemLimits 输入限制中科目限制
	 * @param tableCellLimits 输入限制中单元格权限的集合
	 * @param secuTRowListGroupByRole 按照角色分组后的表行角色集合
	 * @param dictTModul DictTModel的实体类
	 * @return String 表行限制的SQL语句
	 */
	private String generateTableRowLimitSQLStringWithoneRowListOfRole(
			List<SecuTLockPO> acctItemLocks,
			List<Map<String, String>> acctItemLimits,
			Map<String,Map<String, String>> tableCellLimits,
			Map<String, List<SecuTRowPO>> secuTRowListGroupByRole,
			DictTModelPO dictTModul) {
		boolean AddReadOnlyWhereFlag = true;
		boolean AddHiddenWhereFlag = true;
		List<SecuTRowPO> secuTRowListHidden = new ArrayList<SecuTRowPO>();
		List<SecuTRowPO> secuTRowListReadonly = new ArrayList<SecuTRowPO>();
		for(Map.Entry<String,List<SecuTRowPO>> entry: secuTRowListGroupByRole.entrySet()) {
			 List<SecuTRowPO> secuTRowLists = entry.getValue();
			 if(secuTRowLists.size() ==1){
				 SecuTRowPO secuTRow = secuTRowLists.get(0);
				 if(secuTRow.getBaseSecu().equals(DataAuthService.DATA_AUTHORITY_HIDDEN+"")){
					 AddReadOnlyWhereFlag = false;
					 secuTRowListHidden.add(secuTRow);
				 }else{
					 AddHiddenWhereFlag =false;
					 secuTRowListReadonly.add(secuTRow);
				 }
			 }else{
					for(SecuTRowPO secuTRowTmp :secuTRowLists){
						if(secuTRowTmp.getBaseSecu().equals(DataAuthService.DATA_AUTHORITY_HIDDEN+"")){
							secuTRowListHidden.add(secuTRowTmp);
						}else{
							secuTRowListReadonly.add(secuTRowTmp);
						}
					}
			 }

		}
		
		List<Map<String,String>> acctItemLimitReadonlyList = new ArrayList<Map<String,String>>();
		List<Map<String,String>> acctItemLimitHiddenList = new ArrayList<Map<String,String>>();
		//数据库表名
		String dbTablename =  dictTModul.getDbtablename();

		int dbcolSize = 0;
		boolean acctItemLocksFlag = false;
		List<DictTFactorPO> dictTFactorsTmp =  dictTModul.getDictTFactorList();
		List<DictTFactorPO> dictTFactors = new ArrayList<DictTFactorPO>();
		//1.过滤标题列
		for(DictTFactorPO dictTFactor : dictTFactorsTmp){
			if(DataAuthService.DATA_AUTHORITY_ACCTITEM_COLNAME.equalsIgnoreCase(dictTFactor.getDbcolumnname())){
				acctItemLocksFlag =true;
			}
			dictTFactors.add(dictTFactor);
		}
		dbcolSize = dictTFactors.size();
		StringBuffer cellLimitStringForWrite = new StringBuffer();
		StringBuffer cellLimitStringForReadOnly = new StringBuffer();
		
		String cellConditionLimit="";
		Map<String,String> tableCellLimit= new HashMap<String,String>();
		//2.初始化可写，只读列权限字符串。
		for (int index=0;index<dbcolSize;index++){
			DictTFactorPO dictTFactor = dictTFactors.get(index);
			tableCellLimit =tableCellLimits.get(dictTFactor.getColumnid());

			if(tableCellLimit != null){
				
				cellConditionLimit = "(CASE WHEN ("+tableCellLimit.get("LIMITCON")+") THEN '0' ELSE '1' END)";
				cellLimitStringForWrite.append(cellConditionLimit);
			}else{
				cellLimitStringForWrite.append("'"+DataAuthService.DATA_AUTHORITY_EXT_TRUE+"'");
			}
			if(index != (dbcolSize-1)){
				cellLimitStringForWrite.append("||");
			}
			cellLimitStringForReadOnly.append("0");
		}
		//3.先将输入限制行限制进行分类。
		if(acctItemLocksFlag){
			for(Map<String,String> acctItemLimit : acctItemLimits){
				
				if(Integer.parseInt(acctItemLimit.get("LIMITCON"))==DataAuthService.DATA_AUTHORITY_HIDDEN){//当等于1的时候是不可见
					acctItemLimitHiddenList.add(acctItemLimit);
				}else if(Integer.parseInt(acctItemLimit.get("LIMITCON"))==DataAuthService.DATA_AUTHORITY_READONLY){//当等于2的时候是只读
					acctItemLimitReadonlyList.add(acctItemLimit);
				}
			}
		}else{
			acctItemLimits= new ArrayList<Map<String,String>>();
			acctItemLocks = new ArrayList<SecuTLockPO>();
		}
		
		StringBuffer resultSetSQLForWriteLimitWhere = new StringBuffer();
		resultSetSQLForWriteLimitWhere.append("(");
		
		//4.先获得可写的结果集 可写的结果集=去掉只读+不可见的。
		StringBuffer acctItemLockWriteSQLWhere = new StringBuffer();
		acctItemLockWriteSQLWhere.append("SELECT '1' as ROWSECU,"+cellLimitStringForWrite+" as CELLAUTH,a.* FROM "+dbTablename+" a WHERE 1=1 ");
		if(acctItemLocks.size()!=0||acctItemLimits.size()!=0){
			acctItemLockWriteSQLWhere.append("and a."+DataAuthService.DATA_AUTHORITY_ACCTITEM_COLNAME+" NOT IN (");
		}
		for(SecuTLockPO acctItemLock : acctItemLocks){
			acctItemLockWriteSQLWhere.append("'"+acctItemLock.getObjectId()+"',");
		}
		for(Map<String,String> acctItemLimit : acctItemLimits){
			acctItemLockWriteSQLWhere.append("'"+acctItemLimit.get("LIMITID")+"',");
		}
		
		if(AddReadOnlyWhereFlag==false && AddHiddenWhereFlag==false){
			acctItemLockWriteSQLWhere.replace(acctItemLockWriteSQLWhere.length()-1, acctItemLockWriteSQLWhere.length(), " ").append(" )");
		}else{
			acctItemLockWriteSQLWhere.replace(acctItemLockWriteSQLWhere.length()-1, acctItemLockWriteSQLWhere.length(), " ");
			
			if(acctItemLocks.size()!=0||acctItemLimits.size()!=0){
				acctItemLockWriteSQLWhere.append(") ");
			}
			
			if(secuTRowListHidden.size() != 0 ||secuTRowListReadonly.size() != 0 ){
				acctItemLockWriteSQLWhere.append(" and  not exists (select DATAKEY from "+dbTablename+" where ( ");
			}
			if(AddHiddenWhereFlag == true){
				for(SecuTRowPO secuTRow : secuTRowListHidden){
					acctItemLockWriteSQLWhere.append(secuTRow.getSqlWhere()+" OR ");
				}
			}
			if(AddReadOnlyWhereFlag==true){
				for(SecuTRowPO secuTRow : secuTRowListReadonly){
					acctItemLockWriteSQLWhere.append(secuTRow.getSqlWhere()+" OR ");
				}
			}
			if(secuTRowListHidden.size() != 0 ||secuTRowListReadonly.size() != 0 ){
				acctItemLockWriteSQLWhere.replace(acctItemLockWriteSQLWhere.length()-3, acctItemLockWriteSQLWhere.length(), " ").append(" ) and a.DATAKEY=DATAKEY ) ");
			}
		}
		
		//5.获得只读的结果集 只读的结果集
		String finalRowLimitSQL="";
		if((acctItemLocks.size()!=0 ||acctItemLimitReadonlyList.size()!=0 || secuTRowListReadonly.size()!=0) && AddReadOnlyWhereFlag==true){
			StringBuffer acctItemLockReadonlySQLWhere = new StringBuffer();
			acctItemLockReadonlySQLWhere.append("SELECT '0' as ROWSECU,'"+cellLimitStringForReadOnly+"' as CELLAUTH,a.* FROM "+dbTablename+" a ");
			
			
			if(acctItemLocks.size() !=0 || acctItemLimitReadonlyList.size()!=0 || secuTRowListReadonly.size()!=0){
				acctItemLockReadonlySQLWhere.append("WHERE  ");
			}
			if(acctItemLocks.size() !=0 || acctItemLimitReadonlyList.size()!=0){
				acctItemLockReadonlySQLWhere.append("a."+DataAuthService.DATA_AUTHORITY_ACCTITEM_COLNAME+" IN (");
			}
			for(SecuTLockPO acctItemLock : acctItemLocks){
				acctItemLockReadonlySQLWhere.append("'"+acctItemLock.getObjectId()+"',");
			}
			for(Map<String,String> acctItemLimitReadonly : acctItemLimitReadonlyList){
				acctItemLockReadonlySQLWhere.append("'"+acctItemLimitReadonly.get("LIMITID")+"',");
			}
			acctItemLockReadonlySQLWhere.replace(acctItemLockReadonlySQLWhere.length()-1, acctItemLockReadonlySQLWhere.length(), " ");
			if(acctItemLocks.size() !=0 || acctItemLimitReadonlyList.size()!=0){
				if(secuTRowListReadonly.size()!=0){
					acctItemLockReadonlySQLWhere.append(") and exists (select "+dbTablename+" from  "+dbTablename+"  where (");
				}
			}else{
				if(secuTRowListReadonly.size()!=0){
					acctItemLockReadonlySQLWhere.append(" exists (select DATAKEY from "+dbTablename+" where (");
				}
			}
			for(SecuTRowPO secuTRow : secuTRowListReadonly){
				acctItemLockReadonlySQLWhere.append(secuTRow.getSqlWhere()+" OR ");
			}
			if(secuTRowListReadonly.size()!=0 || secuTRowListHidden.size()!=0){
				acctItemLockReadonlySQLWhere.replace(acctItemLockReadonlySQLWhere.length()-3, acctItemLockReadonlySQLWhere.length(), " ").append(" ) and a.DATAKEY=DATAKEY )");
			}
			if(secuTRowListHidden != null &&secuTRowListHidden.size()>0){
				acctItemLockReadonlySQLWhere.append(" and not exists (select DATAKEY from "+dbTablename+" where (");
				for(SecuTRowPO secuTRow : secuTRowListHidden){
					acctItemLockReadonlySQLWhere.append(secuTRow.getSqlWhere()+" OR ");
				}
				acctItemLockReadonlySQLWhere.replace(acctItemLockReadonlySQLWhere.length()-3, acctItemLockReadonlySQLWhere.length(), " ").append(" ) and a.DATAKEY=DATAKEY )");
			}
			//6.生成最终查询语句 可写结果集UNION 只读结果集
			finalRowLimitSQL = "select b.* from ( "+acctItemLockWriteSQLWhere.toString()+" UNION ALL "+acctItemLockReadonlySQLWhere.toString()+") b";
		}else{
			finalRowLimitSQL = "select b.* from ( "+acctItemLockWriteSQLWhere.toString()+") b";
		}
		
		return finalRowLimitSQL;
	}
	

	/**
	 * 生成行限制的SQL语句时不用考虑角色对表的行限制条件
	 * @param acctItemLocks 科目锁集合
	 * @param acctItemLimits 输入限制中科目限制
	 * @param tableCellLimits 输入限制中单元格权限的集合
	 * @param dictTModul DictTModel的实体类
	 * @return  String 表行限制的SQL语句
	 */
	private String generateTableRowLimitSQLStringWithoutRowListOfRole(
			List<SecuTLockPO> acctItemLocks,
			List<Map<String, String>> acctItemLimits,
			Map<String, Map<String,String>> tableCellLimits,
			DictTModelPO dictTModul) {
		List<Map<String,String>> acctItemLimitReadonlyList = new ArrayList<Map<String,String>>();
		List<Map<String,String>> acctItemLimitHiddenList = new ArrayList<Map<String,String>>();
		//数据库表名
		String dbTablename =  dictTModul.getDbtablename();
		int dbcolSize = 0;
		boolean acctItemLocksFlag =false;
		List<DictTFactorPO> dictTFactorsTmp =  dictTModul.getDictTFactorList();
		List<DictTFactorPO> dictTFactors = new ArrayList<DictTFactorPO>();
		//1.过滤标题列
		for(DictTFactorPO dictTFactor : dictTFactorsTmp){
			if(DataAuthService.DATA_AUTHORITY_ACCTITEM_COLNAME.equalsIgnoreCase(dictTFactor.getDbcolumnname())){
				acctItemLocksFlag =true;
			}
			dictTFactors.add(dictTFactor);
		}
		dbcolSize = dictTFactors.size();
		StringBuffer cellLimitStringForWrite = new StringBuffer();
		StringBuffer cellLimitStringForReadOnly = new StringBuffer();
		
		String cellConditionLimit="";
		Map<String,String> tableCellLimit= new HashMap<String,String>();
		//2.初始化可写，只读列权限字符串。
		for (int index=0;index<dbcolSize;index++){
			DictTFactorPO dictTFactor = dictTFactors.get(index);
			tableCellLimit =tableCellLimits.get(dictTFactor.getColumnid());

			if(tableCellLimit != null){
				cellConditionLimit = "(CASE WHEN ("+tableCellLimit.get("LIMITCON")+") THEN '0' ELSE '1' END)";
				cellLimitStringForWrite.append(cellConditionLimit);
			}else{
				cellLimitStringForWrite.append("'"+DataAuthService.DATA_AUTHORITY_EXT_TRUE+"'");
			}
			if(index != (dbcolSize-1)){
				cellLimitStringForWrite.append("||");
			}
			cellLimitStringForReadOnly.append("0");
		}
		//3.先将输入限制行限制进行分类。
		if(acctItemLocksFlag){
			for(Map<String,String> acctItemLimit : acctItemLimits){
				
				if(Integer.parseInt(acctItemLimit.get("LIMITCON"))==DataAuthService.DATA_AUTHORITY_HIDDEN){//当等于1的时候是不可见
					acctItemLimitHiddenList.add(acctItemLimit);
				}else if(Integer.parseInt(acctItemLimit.get("LIMITCON"))==DataAuthService.DATA_AUTHORITY_READONLY){//当等于2的时候是只读
					acctItemLimitReadonlyList.add(acctItemLimit);
				}
			}
		}else{
			acctItemLimits= new ArrayList<Map<String,String>>();
			acctItemLocks = new ArrayList<SecuTLockPO>();
		}
		StringBuffer resultSetSQLForWriteLimitWhere = new StringBuffer();
		resultSetSQLForWriteLimitWhere.append("(");
		
		//4.先获得可写的结果集 可写的结果集=去掉只读+不可见的。
		StringBuffer acctItemLockWriteSQLWhere = new StringBuffer();
		acctItemLockWriteSQLWhere.append("SELECT '1' as ROWSECU,"+cellLimitStringForWrite+" as CELLAUTH,a.* FROM "+dbTablename+" a  ");
		
		
		if(acctItemLocks.size() != 0 ||acctItemLimits.size() != 0 ){
			acctItemLockWriteSQLWhere.append(" WHERE a."+DataAuthService.DATA_AUTHORITY_ACCTITEM_COLNAME+" NOT IN (");
		}
		
		
		for(SecuTLockPO acctItemLock : acctItemLocks){
			acctItemLockWriteSQLWhere.append("'"+acctItemLock.getObjectId()+"',");
		}
		for(Map<String,String> acctItemLimit : acctItemLimits){
			acctItemLockWriteSQLWhere.append("'"+acctItemLimit.get("LIMITID")+"',");
		}
		if(acctItemLocks.size() != 0 ||acctItemLimits.size() != 0 ){
			acctItemLockWriteSQLWhere.replace(acctItemLockWriteSQLWhere.length()-1, acctItemLockWriteSQLWhere.length(), " ").append(")");
		}else{
			acctItemLockWriteSQLWhere.replace(acctItemLockWriteSQLWhere.length()-1, acctItemLockWriteSQLWhere.length(), " ");
		}
		String finalRowLimitSQL = "";
		if(acctItemLocks.size()!= 0|| acctItemLimitReadonlyList.size()!=0){
			//5.获得只读的结果集 只读的结果集
			StringBuffer acctItemLockReadonlySQLWhere = new StringBuffer();
			acctItemLockReadonlySQLWhere.append("SELECT '0' as ROWSECU,'"+cellLimitStringForReadOnly+"' as CELLAUTH,a.* FROM "+dbTablename+" a WHERE a."+DataAuthService.DATA_AUTHORITY_ACCTITEM_COLNAME+" IN (");
			for(SecuTLockPO acctItemLock : acctItemLocks){
				acctItemLockReadonlySQLWhere.append("'"+acctItemLock.getObjectId()+"',");
			}
			for(Map<String,String> acctItemLimitReadonly : acctItemLimitReadonlyList){
				acctItemLockReadonlySQLWhere.append("'"+acctItemLimitReadonly.get("LIMITID")+"',");
			}
			acctItemLockReadonlySQLWhere.replace(acctItemLockReadonlySQLWhere.length()-1, acctItemLockReadonlySQLWhere.length(), " ").append(")");
			
			//6.生成最终查询语句 可写结果集UNION 只读结果集
			finalRowLimitSQL = "select b.* from ( "+acctItemLockWriteSQLWhere.toString()+" UNION ALL "+acctItemLockReadonlySQLWhere.toString()+") b";
			
		}else{
			finalRowLimitSQL = "select b.* from ( "+acctItemLockWriteSQLWhere.toString()+") b";
		}
		
		return finalRowLimitSQL;
		
	}
	
	/**
	 * 通过表ID获得表的单元格限制
	 * @param tableID 表ID
	 * @return List<SecuTLimitcolPO>
	 */
	private Map<String,Map<String,String>> getTableCellLimits(String tableId) {
		List<Map<String,String>> cellLimitsList = secuTLimitcolMapper.getTableCellLimits(tableId);
		Map<String,Map<String,String>> cellLimitsMap = new HashMap<String,Map<String,String>>();
		for(Map<String,String> celllimit : cellLimitsList){
			cellLimitsMap.put(celllimit.get("COLUMNID"), celllimit);
		}
		return cellLimitsMap;
	}
	
	private List<SecuTRowPO> getRole2TableRowLimitLists(String tableID,
			List<Map<String,String>> roleIds) {
		Map param =new HashMap();
		param.put("tableId", tableID);
		param.put("roleIds", roleIds);
		return secuTRowMapper.getRole2TableRowLimitLists(param);
	}
	private List<Map<String, String>> getAcctItemInputLimitList(String tableId,String appId) {
		String dbNameForAcctItem = this.getTableDatabaseNameBy( appId, Constants.COMMON_DictTAPPCODE_TABLETYPE_EXPFUNC);
		Map param = new HashMap();
		param.put("dbNameForAcctItem", dbNameForAcctItem);
		param.put("tableId", tableId);
		
		return secuTLimittabMapper.getInputLimitItemList(param);
	}
	private List<SecuTLockPO> getAcctItemLockList(String appId) {
		return secuTLockMapper.getAcctItemLockList(appId);
	}



	/**
	 * 获得表列限制集合通过用户id
	 * @param tableID --表ID
	 * @param userID --用户ID
	 * @return  List<SecuTCol> --列权限实体类
	 */
    public Map<String, SecuTColPO> getTableColsAuthByUser(String tableID, String userID) {

        return this.getTableColsAuthByRoles(tableID, userID);
    }

	/**
	 * 获得表列限制集合通过角色id
	 * @param tableID --表ID
	 * @param roleIds --用户角色ID集合
	 * @return  List<SecuTCol> --列权限实体类
	 */
	private Map<String,SecuTColPO> getTableColsAuthByRoles(String tableID,String userID) {
		Map<String,SecuTColPO> secuTcols = new HashMap<String,SecuTColPO>();
		List<SecuTColPO> secuTColList = secuTColMapper.getTableColSecu(userID, tableID);
		for(int i=0;i<secuTColList.size();i++){
			SecuTColPO secuTColPO = (SecuTColPO)secuTColList.get(i);
			secuTcols.put(secuTColPO.getColId(), secuTColPO); 
		}
	    return secuTcols; 
	}

	@Override
	public List<Map<String, String>> getAgencyListByUserId(String userId,String appId) {
		
		List<Map<String,String>> roleIds = secuTAgencyMapper.getRoleIdListByUserId(userId);
		String agencyId = SecureUtil.getCurrentUser().getUpagencyid();
		String userType = SecureUtil.getCurrentUser().getUsertype().toString();
		//需要加缓存进行查询
		return this.getAgencyListByRoleId(roleIds,agencyId,appId,userType);
	}
	private List<Map<String, String>> getAgencyListByRoleId(List<Map<String,String>> roleIds,String agencyId,String appId,String userType) {
		String dbNameForAgency = this.getTableDatabaseNameBy(appId, Constants.COMMON_DictTAPPCODE_TABLETYPE_AGENCY);
		Map param = new HashMap();
		param.put("dbNameForAgency", dbNameForAgency);
		param.put("roleIds", roleIds);
		param.put("agencyId", agencyId);
		if(userType.equals(DataAuthService.USERINFO_USERTYPE_CZ)){
			return secuTAgencyMapper.getAgencyListByRoleId(param);
		}else{
			return secuTAgencyMapper.getAgencyListByRoleIdForDW(param);
		}
		
	}
	@Override
	public List<DictTModelPO> getTableListViaSecu(String userId, String suitId,
			String appId) {
		String agencyId = SecureUtil.getCurrentUser().getUpagencyid();
		Map param = new HashMap();
		param.put("suitid", suitId);
		param.put("userId", userId);
		param.put("appId", appId);
		param.put("agencyId", agencyId); 
		//List<Map<String,String>> suits = secuTTableMapper.getSuitIsHiddenBySuitId(param);
		//如果该suit设置不可见权限了 则列表= 设置了<>不可见的表列表，否则列表=设置了<>不可见的表列表+没设置的表列表
		/*if(suits == null ||suits.size()==0){
			return secuTTableMapper.getTableListWithNoSuitSecu(param);
		}else{
			return secuTTableMapper.getTableListWithSuitSecu(param);
		}*/
		
		return secuTTableMapper.getTableListWithSuitSecu(param);
	}
	@Override
	public String getTableDatabaseNameBy(String appId, String tableType) {
		Map param = new HashMap(); 
		param.put("appId", appId);
		param.put("tableType", tableType);
		List<Map<String,String>> list = secuTTableMapper.getTableDatabaseNameBy(param);
		if(list == null || list.size()==0){
			return "";
		}else{
			return list.get(0).get("TABLENAME");
		}
	}
	@Override
	public Set<String> getAffectColumnListForTableCellLimit(
			String tableId) {
		List<DictTFactorPO> factors = dictTFactorService.getDictTFactorByTableidAndType(tableId, "1");
		List<Map<String,String>> colLimits = secuTLimitcolMapper.getTableCellLimits(tableId);
		String LIMITCONFUNC;
		Set<String> dbColumnNames = new HashSet<String>();
		for(Map<String,String> colLimit : colLimits){
			LIMITCONFUNC = colLimit.get("LIMITCONFUNC");
			dbColumnNames = filterTheAffectColumnFunc(dbColumnNames,LIMITCONFUNC,factors);
		}
		return dbColumnNames;
	}
	private Set<String> filterTheAffectColumnFunc(
			Set<String> affectColumnList, String lIMITCONFUNC,
			List<DictTFactorPO> factors) {
		String column = "";

		int start = lIMITCONFUNC.indexOf(Constants.COLUMN_START);
		int end = lIMITCONFUNC.indexOf(Constants.COLUMN_END);
		if(start <0 || end<0){
			return affectColumnList;
		}
		column = lIMITCONFUNC.substring(start,end+1);
		for(DictTFactorPO factor : factors){
			if(column.equalsIgnoreCase(Constants.COLUMN_START+factor.getColumnid()+Constants.COLUMN_END)){
				affectColumnList.add(factor.getDbcolumnname());
				break;
			}
		}
		lIMITCONFUNC = lIMITCONFUNC.replace(column, "");
		 return filterTheAffectColumnFunc(affectColumnList,lIMITCONFUNC,factors);
		
	}
	

	@Override
	public String getRowCellSecuBy(String tableId,
			String jsonRowDataStr) {
		JSONObject jsonBean = JSONObject.fromObject(jsonRowDataStr); 
		
		List<Map<String,String>> colLimits = secuTLimitcolMapper.getTableCellLimits(tableId);
		Map<String,Map<String,String>> colLimitMaps = new HashMap<String,Map<String,String>>();
		
		for(Map<String,String> colLimit : colLimits){
			colLimitMaps.put(colLimit.get("DBCOLUMNNAME"), colLimit);
		}
		Iterator iter = jsonBean.keys();
		String dataBaseColumn="";
		String dataBaseValue="";
		StringBuffer dataRowStr = new StringBuffer("select ");
		String dataRowResultSet = ""; 
		String cellConditionLimit = "";
		while(iter.hasNext()){
			dataBaseColumn = (String)iter.next();
			if(jsonBean.get(dataBaseColumn) == null){
				dataBaseValue= "";
			}else{
				if(jsonBean.get(dataBaseColumn) == null){
					dataBaseValue="";
				}else{
					dataBaseValue = jsonBean.get(dataBaseColumn).toString();
				}
				
			}
			dataRowStr.append("'"+dataBaseValue+"' as "+dataBaseColumn+" ,");
			Map<String,String> colLimit = colLimitMaps.get(dataBaseColumn);
			if(colLimit == null){
				dataRowResultSet=dataRowResultSet+" '1' ||";
			}else{
				cellConditionLimit = " (CASE WHEN ("+colLimit.get("LIMITCON")+") THEN '0' ELSE '1' END) ||";
				dataRowResultSet=dataRowResultSet+cellConditionLimit;
			}
		}
		String dataRowStrFinal = dataRowStr.toString();
		dataRowResultSet = dataRowResultSet.substring(0,dataRowResultSet.length()-2);
		dataRowStrFinal = dataRowStrFinal.substring(0,dataRowStrFinal.length()-1);
		dataRowStrFinal = dataRowStrFinal+" from dual ";
		Map param = new HashMap();
		param.put("resultSet", dataRowResultSet);
		param.put("tableData", "( "+dataRowStrFinal+" )");
		List<Map<String,String>> result = secuTLimitcolMapper.getRowCellSecuBy(param);
		return result.get(0).get("CELLSECU");
	}


	@Override
	public SecuTTablePO getTableAuthByUser(String tableID, String userId,String userType,
			String appId, String targetDivId) {
		List<TaskTTaskinstancePO> taskPOs = new ArrayList();//taskTTaskinstanceMapper.getCurrentStartTaskBy();
		SecuTTablePO tableSecu = new SecuTTablePO();
		tableSecu.setBaseSecu("2");
		tableSecu.setExtAdd("0");
		tableSecu.setExtUpdate("0");
		tableSecu.setExtDel("0");
		SecuTTablePO tableSecure = this.getTableAuthByUser(tableID, userId, userType,appId, false);
		if(taskPOs == null || taskPOs.size()==0){
			if(!"1".equals(userType)){
				return tableSecu;
			}else{
				return tableSecure;
			}
		}
		TaskTTaskinstancePO taskPO = taskPOs.get(0);
		List<Map<String,String>> targetTaskPOs = new ArrayList();//taskTTaskinstanceMapper.getRunningTaskByTaskId(targetTaskId);
		if(targetTaskPOs == null || targetTaskPOs.size()==0){
			if(!"1".equals(userType)){
				return tableSecu;
			}else{
				return tableSecure;
			}
		}else{
			Map<String,String> targetTaskPO = targetTaskPOs.get(0);
			String taskDesc = targetTaskPO.get("DESCRIPTION_");
			String taskDivLevelno = taskDesc.substring(1,2);
			String dbNameForAgency = this.getTableDatabaseNameBy(appId, Constants.COMMON_DictTAPPCODE_TABLETYPE_AGENCY);
			Map param = new HashMap();
			param.put("dbNameForAgency", dbNameForAgency);
			param.put("userId", userId);
			List<Map<String,Object>> list =secuTAgencyMapper.getUserInfoByUserId(param);
			Map<String,Object> userInfo = list.get(0);
			String upDivLevelno = ((BigDecimal)userInfo.get("LEVELNO")).toString();
			String divLevelno = ((BigDecimal)userInfo.get("SELFLEVELNO")).toString();
			String orgId = (String)userInfo.get("ORGID");
			if(taskDivLevelno.equalsIgnoreCase(upDivLevelno) ||(orgId.equalsIgnoreCase(targetDivId)&&taskDivLevelno.equalsIgnoreCase(divLevelno) )){
				return tableSecure;
			}else{
				return tableSecu;
			}
		}
		
	}
	

	@Override
	public String isContrTableSecu(String tableID, String taskID) {
		String result = "";
		
		// 缓存key
		String[] key = { "HQ.BGT", "TASKTYPE", "isContrTableSecu", taskID};
		// 先取缓存数据
		if (dataCacheService.get(key) != null) {
			result = (String) dataCacheService.get(key);
			return result;
		}
		result = secuTTableMapper.isContrTableSecu(taskID);
		
		return result;
	}
	
	@Override
	public SecuTTablePO getTableAuthForWorkflowByUser(String appId,
			String businessKey, String userId,String userType,String tableId,
			boolean cascade) {
		String bpmnType = null;
		SecuTTablePO tableSecu = this.getTableAuthByUser(tableId, userId, userType,appId, cascade);
		tableSecu.setIsSuit("0");
		tableSecu.setTableId(tableId);
		tableSecu.setManId(userId);
		tableSecu.setManType("0");
		tableSecu.setBaseSecu("3");
		tableSecu.setExtAdd("1");
		tableSecu.setExtUpdate("1");
		tableSecu.setExtDel("1");
		Map<String,SecuTColPO> colSecuFinal;
		/*
			SecuTTablePO tableSecureForRole = this.getTableAuthByUser(tableId, userId, userType,appId, cascade);
			String baseSecuForRole = tableSecureForRole.getBaseSecu();
			if(Integer.parseInt(baseSecuForRole)<3){
				tableSecu.setBaseSecu(baseSecuForRole);
				tableSecu.setExtAdd("0");
				tableSecu.setExtUpdate("0");
				tableSecu.setExtDel("0");
			}else{
				tableSecu.setExtAdd(tableSecureForRole.getExtAdd());
				tableSecu.setExtUpdate(tableSecureForRole.getExtUpdate());
				tableSecu.setExtDel(tableSecureForRole.getExtDel());
			}
		*/
		SecuTTablePO tableSecureForWorkFlow = calculateWorkFlowTableAuth(userId,bpmnType,businessKey,tableId,cascade);
		
		if(Integer.parseInt(tableSecureForWorkFlow.getBaseSecu())<Integer.parseInt(tableSecu.getBaseSecu())){
			tableSecu.setBaseSecu(tableSecureForWorkFlow.getBaseSecu());
			tableSecu.setExtAdd(tableSecureForWorkFlow.getExtAdd());
			tableSecu.setExtUpdate(tableSecureForWorkFlow.getExtUpdate());
			tableSecu.setExtDel(tableSecureForWorkFlow.getExtDel());
		}else if(Integer.parseInt(tableSecureForWorkFlow.getBaseSecu()) == 3){
			if("1".equals(tableSecu.getExtAdd())){
				tableSecu.setExtAdd(tableSecureForWorkFlow.getExtAdd());
			}
			if("1".equals(tableSecu.getExtUpdate())){
				tableSecu.setExtUpdate(tableSecureForWorkFlow.getExtUpdate());
			}
			if("1".equals(tableSecu.getExtDel())){
				tableSecu.setExtDel(tableSecureForWorkFlow.getExtDel());
			}
		}
		if(cascade){
			Map<String,SecuTColPO> colSecuForRoles = this.getTableColsAuthByUser(tableId, userId);
			Map<String,SecuTColPO> colSecuForWorkFlow = tableSecureForWorkFlow.getSecuTCols();
			colSecuFinal = calculateWorkFlowTableAuth(colSecuForRoles,colSecuForWorkFlow);
			tableSecu.setSecuTCols(colSecuFinal);
		}
		return tableSecu;
	}


	private Map<String, SecuTColPO> calculateWorkFlowTableAuth(
			Map<String, SecuTColPO> colSecuForRoles,
			Map<String, SecuTColPO> colSecuForWorkFlow) {
		Map<String, SecuTColPO> colSecuFinal = new HashMap<String,SecuTColPO>();
		
		if(colSecuForRoles.entrySet() != null){
			for(Map.Entry<String, SecuTColPO> entry : colSecuForRoles.entrySet()) {
				SecuTColPO compareSecuTCol = colSecuForWorkFlow.get(entry.getKey());
				if(compareSecuTCol != null){
					if(Integer.parseInt(compareSecuTCol.getBaseSecu())>Integer.parseInt(entry.getValue().getBaseSecu())){
						compareSecuTCol = entry.getValue();
					}
				}else{
					compareSecuTCol = entry.getValue();
				}
				SecuTColPO secuTCol = colSecuFinal.get(entry.getKey());
				if(secuTCol == null){
					colSecuFinal.put(entry.getKey(), compareSecuTCol);
				}
        
			}
		}
		
		
		if(colSecuForWorkFlow.entrySet() != null){
			for(Map.Entry<String, SecuTColPO> entry : colSecuForWorkFlow.entrySet()) {
				SecuTColPO compareSecuTCol = colSecuForRoles.get(entry.getKey());
				if(compareSecuTCol != null){
					if(Integer.parseInt(compareSecuTCol.getBaseSecu())>Integer.parseInt(entry.getValue().getBaseSecu())){
						compareSecuTCol = entry.getValue();
					}
				}else{
					compareSecuTCol = entry.getValue();
				}
				SecuTColPO secuTCol = colSecuFinal.get(entry.getKey());
				if(secuTCol == null){
					colSecuFinal.put(entry.getKey(), compareSecuTCol);
				}
        
			}
		}
		
		return colSecuFinal;
	}


	private SecuTTablePO calculateWorkFlowTableAuth(String userId,
			String bpmnType, String businessKey,String tableId,boolean cascade) {
		return null;	
	}
	

	@Override
	public List<String> getBeAffectedColumnListForTableCellLimit(String tableId) {
		return secuTLimitcolMapper.getBeAffectedColumnListForTableCellLimit(tableId);
	}

	@Override
	public String getAgencySqlByUserId(String userId,String appId){
		StringBuffer sqlStr = new StringBuffer("");
		List<Map<String,String>> roleIds = secuTAgencyMapper.getRoleIdListByUserId(userId);
		String agencyId = SecureUtil.getCurrentUser().getUpagencyid();
		String userType = SecureUtil.getCurrentUser().getUsertype().toString();
		String dbNameForAgency = this.getTableDatabaseNameBy(appId, Constants.COMMON_DictTAPPCODE_TABLETYPE_AGENCY);
		Map param = new HashMap();
		param.put("dbNameForAgency", dbNameForAgency);
		param.put("roleIds", roleIds);
		param.put("agencyId", agencyId);
		StringBuffer roleStr = new StringBuffer("(");
		List roleList = new ArrayList();
		for(Map<String,String> role:roleIds){
			roleList.add("'"+role.get("ROLEID")+"'");
		}
		roleStr.append(StringUtils.join(roleList, ","));
		roleStr.append(")");
		if(userType.equals(DataAuthService.USERINFO_USERTYPE_CZ)){
			sqlStr.append("select GUID,SUPERGUID,NAME,CODE,DIVTYPE,ISLEAF  from ( "+
				"select distinct GUID,SUPERGUID,NAME,CODE,DIVTYPE,ISLEAF "+
				"from (select t3.code,t3.guid,"+
				"t3.superguid, t3.NAME,t3.divtype,t3.isLeaf "+
				"from "+dbNameForAgency+" t3 start with t3.guid=(select guid from "+dbNameForAgency+ 
				" where GUID='"+agencyId+"' ) connect by prior t3.guid = t3.superguid ) t3 start " + 
				"with t3.guid in (select distinct t2.agencyId from secu_t_agency t2 where t2.manId in "+ 
				roleStr+" and manType = '1') connect by prior t3.guid = t3.superguid union "+
				"select distinct GUID,SUPERGUID,NAME,CODE,DIVTYPE,ISLEAF from (select "+
				"t3.code,t3.guid, t3.superguid, t3.NAME,t3.divtype,t3.isLeaf "+
				"from "+dbNameForAgency+" t3 start with t3.guid=(select guid from "+dbNameForAgency+
				" where GUID='"+agencyId+"') connect by prior t3.guid = t3.superguid) t3 "+
				"start with t3.guid in (select distinct t2.agencyId from secu_t_agency t2 where t2.manId in"+roleStr+
				" and manType = '1') connect by prior t3.superguid= t3.guid ) t7 order by t7.code asc");
		}else{ 
			sqlStr.append("select GUID,SUPERGUID,NAME,CODE,DIVTYPE,ISLEAF from ("+
				"select t3.guid,t3.superguid, t3.NAME,t3.code,t3.divtype,t3.isLeaf"+
				"from "+dbNameForAgency+" t3 start with t3.guid=(select guid from "+dbNameForAgency+
				" where GUID='"+agencyId+"')"+
				"connect by prior t3.guid = t3.superguid) t7 order by t7.code asc"); 
		}
		return sqlStr.toString();   
	}

	/**
	 * 通过tableID获取列限制信息
	 * @param tableID 
	 * @return ColumnLimitPO
	 */
	public List<ColumnLimitPO> getColumnLimitList(String userID, String tableID) throws ServiceException {
		if(StringUtils.isEmpty(tableID)){
			return null;
		}
		List<ColumnLimitPO> list = this.columnLimitMapper.queryColumnLimitList("'***'", tableID);//业务修改，此功能不再使用角色，角色id存***，20161101
		return list;
		
		/*
		//获取到用户的角色，然后查询角色对应的列限制信息。
		if(StringUtils.isEmpty(userID) || StringUtils.isEmpty(tableID)){
			return null;
		}
		List<Map<String, String>> roleList = columnLimitMapper.queryRolesByUserid(userID);//SecureUtil.getCurrentUser().getGuid());
		String roleid = "";
		for(Map<String, String> map : roleList){
			roleid += map.get("ROLEID")+"','";
		}
		if(StringUtils.isNotEmpty(roleid)){
			roleid = "'" + roleid.substring(0, roleid.length() -2);
			List<ColumnLimitPO> list = this.columnLimitMapper.queryColumnLimitList(roleid, tableID);
			return list;
		}else{
			return null;
		}*/
	}
}
