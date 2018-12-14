package commons.setting.dataaudit.rule.service.impl;

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
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.grid.settinggrid.service.ISettingGridService;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.po.TreePO;
import com.tjhq.commons.setting.dataaudit.rule.dao.AuditRuleMapper;
import com.tjhq.commons.setting.dataaudit.rule.service.IAuditRuleService;
import com.tjhq.commons.setting.dataaudit.rule.util.CheckDefTableHelper;
import com.tjhq.commons.setting.dataaudit.rule.util.CheckSortTableHelper;
import com.tjhq.commons.setting.dataaudit.rule.util.SysfnTabHelper;
import com.tjhq.commons.setting.input.service.IEntryService;
import com.tjhq.commons.setting.input.web.ConverTables;

/**
 *@desc:审核定义service
 *@author： wxn
 *@date:2014-10-11下午2:38:00
 */

@Service
@Transactional(readOnly=true)
public class AuditRuleService  implements IAuditRuleService {
	@Resource
	private AuditRuleMapper auditRuleMapper;
	@Resource
	private ISettingGridService settingGridService;
	@Resource
	UtilsMapper utilsMapper;
	@Resource
	IEntryService entryService;
	@Resource
	private IDictTModelService dictTModelService; // 表接口
    
	/**
	 * 查找审核分类数据 
	 * @param appId 暂时不用
	 * @return
	 */
	public List<Map> getAuditCategoryTree(String appId) {
		Map paraMap = new HashMap();
		
		return auditRuleMapper.getAuditCategoryTree(paraMap);
	}


    /**
     * 审核规则定义数据
     * @param map
     * @return
     */
	public List getAuditRuleDef(Map<String, String> map) {
		 List<Map<String, String>> auditRuleListData =auditRuleMapper.getAuditRuleDef(map);
		return auditRuleListData;
	}
	
	public Table getAuditRuleTableDefine() {
		
		Table table=new Table();
		table.setTableID("BGT_T_CHECKDEF");
		table.setAppID("BGT");
		table.setTableDBName("BGT_T_CHECKDEF");
		table.setTableName("审核规则列表");

		CheckDefTableHelper.setColumns(table);
		return table;
		
	}
	@Override
	public Table getCheckTypeTableDefine() {
		Table table=new Table();
		table.setTableID("BGT_T_CHECKSORT");
		table.setAppID("BGT");
		table.setTableDBName("BGT_T_CHECKSORT");
		table.setTableName("审核类型");

		CheckSortTableHelper.setColumns(table);
		return table;
	}
	/**
	 * 保存查询分类
	 * @param grid
	 * @return
	 * @throws Exception 
	 */
	@Transactional(readOnly=false,rollbackFor=Exception.class)
    public Map<String,String> saveCheckSortData(Grid  grid) throws Exception{
    	Map msg=new HashMap<String,String>();
    	settingGridService.saveData(grid);
    	if(grid.getInsertValues().size()>0 || grid.getUpdateValues().size()>0){
    		Map<String, Object> ele=new HashMap<String,Object>();
    		if(grid.getInsertValues().size()>0){
    			ele=grid.getInsertValues().get(0);
    		}else{
    			ele=grid.getUpdateValues().get(0);
    		}
    	
    	
    	if(null!=ele.get("SUPERID")){
    		msg.put("checkSortId", ele.get("SUPERID"));
    		msg.put("endFlag","0");
    		auditRuleMapper.updateEndFlag(msg);
    	}
    	}
    	
    	return msg;
    }
	@Override
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public Map<String, String> delCheckSort(Grid grid) throws Exception {
		Map msg=new HashMap<String,String>();
		//检查改节点是否可以删除 若有下级 则下级一起删除 或如果 有规则引用了该节点也不能删除
		//settingGridService.saveData(grid);
		Map<String,String> paraMap = new HashMap<String,String>();
		String checkSortIds="";
		for(Map<String,Object> dataMap:grid.getDeleteValues()){
			Object id=dataMap.get("CHECKSORTID");
			if(null!=id && !"".equals(id.toString().trim())){
				if("".equals(checkSortIds)){
					checkSortIds="'"+id.toString().trim()+"'";
				}else{
					checkSortIds=checkSortIds+",'"+id.toString().trim()+"'";
				}
			}
			
		}
		paraMap.put("checkSortIds", checkSortIds);
		if(!"".equals(checkSortIds)){
			auditRuleMapper.delCheckType(paraMap);
		}
		
		Map<String, Object> ele=grid.getDeleteValues().get(0);
		String superId =(null==grid.getExtProperties().get("checkSortId")?"":grid.getExtProperties().get("checkSortId").toString());
    	if(null!=superId && !"".equals(superId)){
    		msg.put("checkSortId",superId);
    		auditRuleMapper.updateEndFlag(msg);
    	}
		return msg;
	}
	/**
	 * 删除审核分类
	 */
	@Override
	@Transactional(readOnly=false,rollbackFor=Exception.class)
    public void delCheckType(Map<String,String> paraMap)throws Exception{
		auditRuleMapper.delCheckType(paraMap);
    }
	/**
	 * 得到审核分类被审核的定义所引用的数量
	 */
	@Override
	public int getCheckDefTypeNum(Map<String, String> paraMap) {
		return auditRuleMapper.getCheckDefTypeNum(paraMap);
	}
	/**
	 * 验证父亲节点是否被引用
	 */
	@Override
	public int checkRelationBySuperId(String superId) {
		return auditRuleMapper.checkRelationBySuperId(superId);
	}


	@Override
	public List<TreePO> getNewAuditCategoryTree(String appId) {
		Map<String,String> paraMap = new HashMap<String,String>();
		paraMap.put("appId", appId);
		return auditRuleMapper.getNewAuditCategoryTree(paraMap);
	}

	@Override
	public void check(Map map) {
		if(ConverTables.isNotNull(map.get("WHERE"))){
			map.put("WHERE", clearRF(map.get("WHERE").toString()));
		}
		
		if(ConverTables.isNotNull(map.get("WHERE")))
			auditRuleMapper.checkByWhere(map);
		else{
			if(ConverTables.isNotNull(map.get("GROUP"))){
			  auditRuleMapper.check(map);
			}
		}
		
	}


	@Override
	public void checkNum(Map map) {
		if(ConverTables.isNotNull(map.get("COL"))){
			map.put("COL", clearRF(map.get("COL").toString()));
		}
		auditRuleMapper.checkNum(map);
		
	}


	@Override
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public void saveAuditData(Map formMap) throws ServiceException {
		String checkID = utilsMapper.getDBUniqueID();	
		formMap.put("CHECKID", checkID);
		formMap.put("CREATEBGTLEVEL",this.findBudgetLevel((String)formMap.get("userUpagencyID")));
		//保存 主表 信息
		auditRuleMapper.insertAuditData(formMap);
	}


	@Override
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public void modifyAuditData(Map map) {
		auditRuleMapper.modifyAuditData(map);
	}


	@Override
	public Map<String, Object> makeSql(Map map, String checkType) {
     Map<String, Object> dataMap = new HashMap<String, Object>();
		
		int relatype = Integer.parseInt((String)map.get("RELATYPE"));// 关系符
		String errordef = (String)map.get("ERRORDEF");// 误差值

		String pubGroup = ""; //公共分组

		String Ltable = (String)map.get("LTABLENAME");
		String Rtable = (String)map.get("RTABLENAME");
		String LCOL = clearRF((String)map.get("LCOL"));//清除 引用表格式
		String RCOL = clearRF((String)map.get("RCOL"));
		String Lwhere = clearRF((String)map.get("LWHERE"));
		String Rwhere = clearRF((String)map.get("RWHERE"));	
		String Lgroup = (String)map.get("LGROUP");
		String Rgroup = (String)map.get("RGROUP");
		List<DictTFactorPO> factorsL=null;
		List<DictTFactorPO> factorsR=null;
		StringBuffer sql = new StringBuffer();
		if (checkType.equals("0"))// 表内审核
		{   
			String group = "";
			String groupby = "";
			String groupbyDesc = "";
			String dataKey="";//表内的是dataKey
			factorsL=(List<DictTFactorPO>)map.get("factorsL");
			if(null!=Lgroup && !"".equals(Lgroup)){//如果有分组的话,解析分组
				String[] g = Lgroup.split(",");// 对分组定义的分割数组
				for (int i = 0; i < g.length; i++) {
					group = group + g[i]+",";
					pubGroup =pubGroup + "col" + (i + 1)+"," ;
					groupby = groupby + g[i] + " AS col" + (i + 1) +",";
					groupbyDesc+=wrapGroupby(g[i],factorsL,(i+1),"",Ltable);
				}
				group = group.substring(0, group.length() - 1);
				pubGroup = pubGroup.substring(0, pubGroup.length() - 1);
				//groupby = groupby.substring(0, groupby.length() - 1);
			}else{//如果没有分组的话，需要加datakey,如果检验有没有datakey
				/**
				if(LCOL.indexOf("DATAKEY")==-1 && RCOL.indexOf("DATAKEY")==-1){
					dataKey="DATAKEY as col1,'NAME:'||DATAKEY||',CODE:'||DATAKEY as col1desc,STATUS as col,";
				}**/
				//dataKey="DATAKEY as col1,'NAME:'||DATAKEY||',CODE:'||DATAKEY||',COLUMNDBNAME:DATAKEY,COLUMNNAME:"+findColumnName(factorsL,"DATAKEY")+"' as col1desc,STATUS as col2,'name:'||STATUS||',code:'||STATUS||',COLUMNDBNAME:STATUS,COLUMNNAME:STATUS' as col2desc, ";
				dataKey="DATAKEY as col1,'\"name\":\"'||DATAKEY||'\",\"code\":\"'||DATAKEY||'\",\"columndbname\":\"DATAKEY\",\"columnname\":\""+findColumnName(factorsL,"DATAKEY")+"\",\"guid\":\"'||DATAKEY||'\"' as col1desc,STATUS as col2,'\"name\":\"'||STATUS||'\",\"code\":\"'||STATUS||'\",\"columndbname\":\"STATUS\",\"columnname\":\"STATUS\",\"guid\":\"'||STATUS||'\"' as col2desc, ";
				
				
			}
			sql.append("SELECT " + LCOL + "  CDATA1,"+RCOL+"  CDATA2,"+groupby+groupbyDesc+dataKey+ "'"+Ltable+"' as DBNAME ");
			sql.append(" FROM " + Ltable + " WHERE 1=1 @WHERE@ ");

			if (!Lwhere.equals("")) {
				sql.append("and  (" + Lwhere+")");
			}
			if(null!=Lgroup && !"".equals(Lgroup)){//如果有分组拼接分组表达式
				
				sql.append(" GROUP BY "+group);
				//暂时注释掉abs 
			    //sql.append(" HAVING ABS("+LCOL+"-"+RCOL+")");
				sql.append(" HAVING ("+LCOL+"-"+RCOL+")");
				
			}else{
				//sql.append(" AND ABS("+LCOL+"-"+RCOL+")");
				sql.append(" AND ("+LCOL+"-"+RCOL+")");
			}
			appendRelatype(sql,relatype);
			sql.append(errordef);
			dataMap.put("CHECKSQL", replaceCNChar(sql.toString()));
			dataMap.put("PUBGROUP", pubGroup);
			return dataMap;
		} else if (checkType.equals("1")) { //表间
			String groupL = ""; //左分组 列
			String groupR = ""; //右分组 列
			String groupLby = ""; //左 GROUP BY 
			String groupRby = ""; //右 GROUP BY
			String groupbyDescL = "";//分组描述
			String groupbyDescR = "";//分组描述
			String maxColDesc="";//分组列描述
			String substrMaxColDesc="";//最外层分组列描述
			String[] L = Lgroup.split(",");
			String[] R = Rgroup.split(",");
			factorsL=(List<DictTFactorPO>)map.get("factorsL");
			factorsR=(List<DictTFactorPO>)map.get("factorsR");
			

			for (int i = 0; i < L.length; i++) {
				groupL = groupL + L[i] + ",";
				groupLby = groupLby + L[i] + " AS col" + (i + 1) + ",";
				pubGroup = pubGroup + "col" + (i + 1) + ",";
				maxColDesc = maxColDesc +"max("+ "col" + (i + 1) + "desc) AS col"+(i+1)+"desc,";
				substrMaxColDesc = substrMaxColDesc +"substr(CONNSTRA('$$$$' || "+ "col" + (i + 1) + "desc),5) AS col"+(i+1)+"desc,";
				groupbyDescL+=wrapGroupby(L[i],factorsL,(i+1),"2",Ltable);//参数2只是为了讲codedesc中的左表进行定位
			}
			for (int i = 0; i < R.length; i++) {
				groupR = groupR + R[i] + ",";
				groupRby = groupRby + R[i] + " AS col" + (i + 1) + ",";
				groupbyDescR+=wrapGroupby(R[i],factorsR,(i+1),"1",Rtable);
			}
			for (int i = R.length; i < L.length; i++) {
				groupRby = groupRby + " '' AS col" + (i + 1) + ",";
			}

			groupL = groupL.substring(0, groupL.length() - 1);
			groupR = groupR.substring(0, groupR.length() - 1);
			groupLby = groupLby.substring(0, groupLby.length() - 1);
			groupRby = groupRby.substring(0, groupRby.length() - 1);

			pubGroup = pubGroup.substring(0, pubGroup.length() - 1);
			sql.append("SELECT " + pubGroup + ","+substrMaxColDesc);
			sql.append("SUM(CDATA1) AS CDATA1,");
			sql.append("SUM(CDATA2) AS CDATA2,");
			//sql.append(" substr(connstra(','||dbname),2) as dbname ");
			sql.append("(case when max(dbname)=min(dbname) then substr(max(dbname), 2) else substr(max(dbname), 2)||','||substr(min(dbname), 2) end)as dbname ");
			sql.append(" from (");
			sql.append("SELECT " + pubGroup + ","+maxColDesc);
			sql
					.append(
							"SUM(CASE WHEN FLAG = '0' THEN CDATA ELSE 0 END) AS CDATA1,")
					.append(
							"SUM(CASE WHEN FLAG = '1' THEN CDATA ELSE 0 END) AS CDATA2, ")
					.append(
							" dbname  AS dbname  FROM ( SELECT '0' FLAG," + LCOL + "  CDATA,'2" +Ltable+"' as dbname,"+groupbyDescL+ groupLby);

			sql.append(" FROM " + Ltable + " WHERE 1=1 @WHERE@  ");

			if (!Lwhere.equals("")) {
				sql.append("and  (" + Lwhere+")");
			}
			sql.append(" GROUP BY  " + groupL).append(
					"   UNION ALL SELECT '1' FLAG," + RCOL + "  CDATA,'1"+Rtable+"' as dbname,"+groupbyDescR
							+ groupRby + " FROM " + Rtable
							+ " WHERE 1=1 @WHERE@ ");
			if (!Rwhere.equals("")) {
				sql.append("and  (" + Rwhere+")");
			}
			sql.append(" GROUP BY  " + groupR + " )  x ")

			.append("GROUP BY " + pubGroup+",dbname ");
		}
		sql.append(" )y ");
		sql.append(" GROUP BY "+pubGroup);
		//sql.append(" HAVING ABS(SUM(CDATA1) - SUM(CDATA2))");
		sql.append(" HAVING (SUM(CDATA1) - SUM(CDATA2))");
		appendRelatype(sql,relatype);
		sql.append(errordef);

		dataMap.put("CHECKSQL", replaceCNChar(sql.toString()));
		dataMap.put("PUBGROUP", pubGroup);

		return dataMap;
	}
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


	private String findColumnName(List<DictTFactorPO> factors, String colName) {
		String columnName="";
		for(DictTFactorPO e:factors){
			if(colName.equalsIgnoreCase(e.getDbcolumnname())){
				columnName=e.getName();
				break;
			}
		}
		return columnName;
	}


	private String wrapGroupby(String colName, List<DictTFactorPO> factorsL, int i,String prefix,String dbTableName) {
		DictTFactorPO tempPO=null;
		StringBuffer wrapSql =new StringBuffer(50);
		for(DictTFactorPO e:factorsL){
			if(colName.equalsIgnoreCase(e.getDbcolumnname())){
				tempPO=e;
				break;
			}
		}
		/**
		if(null!=tempPO){
			if(null!=tempPO.getCsid() && !"".equals(tempPO.getCsid())){//引用列
				wrapSql.append("(select '"+prefix+"NAME:'||name||',CODE:'||code||',COLUMNDBNAME:"+tempPO.getDbcolumnname()+",COLUMNNAME:"+tempPO.getName()+"' from "+tempPO.getCsdbtablename()+" where guid="+colName+") as col"+i+"desc,");
				return wrapSql.toString();
				
			}else{//物理列
				wrapSql.append("'"+prefix+"NAME:'||"+colName+"||',CODE:'||"+colName+"||',COLUMNDBNAME:"+tempPO.getDbcolumnname()+",COLUMNNAME:"+tempPO.getName()+"'  as col"+i+"desc,");
				return wrapSql.toString();

			}
			
			
			
		}**/
		/***
		if(null!=tempPO){
			if(null!=tempPO.getCsid() && !"".equals(tempPO.getCsid())){//引用列
				wrapSql.append("(select '"+prefix+"\"name\":\"'||name||'\",\"code\":\"'||code||'\",\"columndbname\":\""+tempPO.getDbcolumnname()+"\",\"columnname\":\""+tempPO.getName()+"\"' from "+tempPO.getCsdbtablename()+" where guid="+colName+") as col"+i+"desc,");
				return wrapSql.toString();
				
			}else{//物理列
				wrapSql.append("'"+prefix+"\"name\":\"'||"+colName+"||'\",\"code\":\"'||"+colName+"||'\",\"columndbname\":\""+tempPO.getDbcolumnname()+"\",\"columnname\":\""+tempPO.getName()+"\"'  as col"+i+"desc,");
				return wrapSql.toString();

			}
			
			
			
		}***/
		if(null!=tempPO){
            if (null != tempPO.getCsid() && !"".equals(tempPO.getCsid()) && (null == tempPO.getExtprop()
                    || tempPO.getExtprop().length() <= 1 || tempPO.getExtprop().charAt(1) == '0')) {// 引用列
                wrapSql.append("(select '\"name\":\"'||REPLACE_JSON_RESERVED_CHAR(name)||'\",\"code\":\"'||REPLACE_JSON_RESERVED_CHAR(code)||'\",\"columndbname\":\""+tempPO.getDbcolumnname()+"\",\"columnname\":\""+tempPO.getName()+"\",\"guid\":\"'||REPLACE_JSON_RESERVED_CHAR(guid)||'\",\"tableID\":\""+tempPO.getTableid()+"\"' from "+tempPO.getCsdbtablename()+" where guid="+dbTableName+"."+colName+" and rownum<2) as col"+i+"desc,");
				return wrapSql.toString();
				
			}else{//物理列
				wrapSql.append("'\"name\":\"'||REPLACE_JSON_RESERVED_CHAR("+colName+")||'\",\"code\":\"'||REPLACE_JSON_RESERVED_CHAR("+colName+")||'\",\"columndbname\":\""+tempPO.getDbcolumnname()+"\",\"columnname\":\""+tempPO.getName()+"\",\"guid\":\"'||REPLACE_JSON_RESERVED_CHAR("+colName+")||'\",\"tableID\":\""+tempPO.getTableid()+"\"'  as col"+i+"desc,");
				return wrapSql.toString();

			}
			
			
			
		}
		
		return colName;
	}

	private void appendRelatype(StringBuffer sql,int relatype){
		switch (relatype) {
		case 0:
			sql.append("<=");
			break;
		case 1:
			sql.append(">=");
			break;
		case 2:
			sql.append("<>");
			break;
		case 3:
			sql.append("<");
			break;
		case 4:
			sql.append(">");
			break;
		case 5:
			sql.append("=");
			break;
		}
	}
    /**
     * 根据id查询审核数据
     */
	public Map getAuditData(String checkid) {
		return auditRuleMapper.getAuditData(checkid);
	}


	@Override
	public String getAuditCategoryNameById(String typeId) {
		 return auditRuleMapper.getAuditCategoryNameById(typeId);
	}

    /**
     * 得到审核验证数据
     */
	@Override
	public List<Map<String, Object>> getAuditRuleCheckList(Map map) {
		 return auditRuleMapper.getAuditRuleCheckList(map);
	}


	@Override
	public Table getSysfnTabDefine() {
		Table table=new Grid();
		table.setTableID("FASP_V_PUBSYSFNTAB");
		table.setAppID("BGT");
		table.setTableDBName("FASP_V_PUBSYSFNTAB");
		table.setTableName("系统函数");
		SysfnTabHelper.setColumns(table);
		return table;
	}


	@Override
	public int findRelationBusiness(String checkIds) {
		// TODO Auto-generated method stub
		 return auditRuleMapper.findRelationBusiness(checkIds);
	}


	@Override
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public void batchDelAuditRule(Table table) throws Exception {
		settingGridService.saveData(table);//保存数据
		
	}


	@Override
	public List<Map<String, Object>> getAuditRuleBaseInfo(
			Map<String, Object> param) throws Exception {
		return auditRuleMapper.getAuditRuleBaseInfo(param);
	}


	@Override
	public int checkLvUnique(Map<String, Object> m) throws Exception {
		 return auditRuleMapper.checkLvUnique(m);
	}


	@Override
	public String checkView(Map<String, Object> formMap) {
		// 表
		String l_tableID = (String) formMap.get("LTABLEID");
		String r_tableID = (String) formMap.get("RTABLEID");
	
		String errMsg="";
		errMsg=checkViewAndBusiObj(l_tableID);
		if(null!=errMsg && !"".equals(errMsg)){
			return errMsg;
		}
        if(null!=r_tableID && !"".equals(r_tableID)){
    		errMsg=checkViewAndBusiObj(r_tableID);
    		if(null!=errMsg && !"".equals(errMsg)){
    			return errMsg;
    		}
        }

		return errMsg;
	}
	private String checkViewAndBusiObj(String l_tableID) {
		String errMsg="";
		DictTModelPO modelPO=dictTModelService.getDictTModelByID(l_tableID,false);
		if("2".equals(modelPO.getTabletype())){
			errMsg=checkViewObj(modelPO);
			
		}
		if("3".equals(modelPO.getTabletype())){
			errMsg=checkBusiObj(modelPO);
			
		}
		return errMsg;
	}


	private String checkViewObj(DictTModelPO modelPO) {
			int n1=auditRuleMapper.findMaterialTalbleCount(modelPO.getTableid());
			int n2=auditRuleMapper.findDbversionColCount(modelPO.getDbtablename());
			if(n1==0 && n2==0){//视图没有dbversion 也没有登记物理表
				return "表["+modelPO.getName()+"]是视图，请先设置其对应的物理表,或 给该视图增加dbversion字段!";
			}
		
		return null;
	}
	private String checkBusiObj(DictTModelPO modelPO) {
		int n2=auditRuleMapper.findDbversionColCount(modelPO.getDbtablename());
		if(n2==0){
			DictTModelPO reModelPO=dictTModelService.getDictTModelByID(modelPO.getRelatab(),false);
			if(!"1".equals(reModelPO.getTabletype())){//如果业务对象引用的表不是物理表 则提示 他登记其所用的物理表
					return "表["+modelPO.getName()+"]是业务对象，但是其引用的表不是物理表，请先设置其对应的物理表!";
			}
		}
		
		return null;
	}

   private String replaceCNChar(String str){
	   String tempStr="";
	   tempStr=str.replaceAll("（", "(");
	   tempStr=tempStr.replaceAll("）", ")");
	   return tempStr;
   }


	@Override
	public int checkAuditSortNameUnique(Map<String, Object> m) {
		Map<String,Object> paraMap=new HashMap<String,Object>();
		paraMap.put("CHECKSORTNAME", m.get("CHECKSORTNAME"));
		String checkSortID= (String)m.get("CHECKSORTID");
		paraMap.put("CHECKSORTID", m.get("CHECKSORTID"));
		paraMap.put("SUPERID", m.get("SUPERID"));
		paraMap.put("APPID", m.get("APPID"));
		if(null!=checkSortID && !"".equals(checkSortID)){
			String superID=auditRuleMapper.findAuditSortSuperID(paraMap);
			paraMap.put("SUPERID",superID);
		}
		
		return auditRuleMapper.findAuditSortNameCount(paraMap);
	}


	@Override
	public Integer findBudgetLevel(String userAgencyID) throws ServiceException {
		return auditRuleMapper.findDistLevel(userAgencyID);
	}


	@Override
	public List<Map<String, Object>> getDataAuditRuleList(
			Map<String, Object> param) throws ServiceException {
		
		
		return auditRuleMapper.getDataAuditRuleList(param);
	}


	@Override
	public int getDataAuditRuleList4count(Map<String, Object> param)
			throws ServiceException {
		return auditRuleMapper.getDataAuditRuleList4count(param);
	}


}

