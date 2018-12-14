package commons.setting.dataaudit.rule.util;
/**
 *@desc:生成sql
 *@author： wxn
 *@date:2015-11-19上午11:10:09
 */
public class AuditRuleSqlFactory {
	//查询审核规则sql
	public static String getQueryAuditRuleSql(String tableIDs,String appID){
		StringBuffer sb = new StringBuffer(50);
		sb.append("select t.APPID,");
					sb.append(" t.CHECKID,");
					sb.append(" TO_CHAR(t.DBVERSION,'yyyy-MM-DD hh24:mi:ss') as DBVERSION,");
					sb.append(" t.CHECKTYPE,");
					sb.append(" t.CHECKSORTID,");
					sb.append(" t.DEFNAME,");
					sb.append(" t.LMODELID,");
					sb.append(" t.RMODELID,");
					sb.append(" t.LDESC,");
					sb.append(" t.RDESC ");
				sb.append(" FROM BGT_T_CHECKDEF t");
				sb.append(" WHERE 1=1 ");
				if(null!=tableIDs && !"".equals(tableIDs)){
					sb.append(" and (t.LMODELID in ("+tableIDs+") or t.RMODELID in ("+tableIDs+"))");	
				}
				if(null!=appID && !"".equals(appID)){
					sb.append(" and t.APPID='"+appID+"'");
				}
				
		
		return sb.toString();
	}
	//查询审核规则sql
	public static String getAuditRuleList4DbversionSql(String tableIDs,String appID){
		StringBuffer sb = new StringBuffer(50);
		sb.append("select to_char(max(dbversion),'yyyy-MM-DD') as dbversion ");
				sb.append(" FROM BGT_T_CHECKDEF t");
				sb.append(" WHERE 1=1 ");
				sb.append(" and (t.LMODELID in ("+tableIDs+") or t.RMODELID in ("+tableIDs+"))");
				sb.append(" and t.APPID='"+appID+"'");
		
		return sb.toString();
	}

}

