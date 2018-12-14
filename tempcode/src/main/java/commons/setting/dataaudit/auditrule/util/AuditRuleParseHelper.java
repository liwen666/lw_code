package commons.setting.dataaudit.auditrule.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.Constants;
import com.tjhq.commons.dict.external.po.DictTModelcodePO;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.service.IDictTModelcodeService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.setting.dataaudit.auditrule.dao.AuditRuleDefDAO;
import com.tjhq.commons.setting.formula.service.IFormulaService;
import com.tjhq.commons.setting.input.po.TreeNode;
import com.tjhq.commons.setting.input.web.ConverTables;

/**
* @ClassName: AuditRuleParseHelper
* @Description: 审核定义的列解析工具类
* @author xiehongtao
* @date 2017-6-13 下午2:57:54
 */
@Component
@Transactional(readOnly=true)
public class AuditRuleParseHelper {
	
	
	/**
	 * 定义表对应的代码表服务接口
	 */
	@Resource
	private IDictTModelcodeService modelCodeService;
	
	/**
	 * 公式 sql 服务接口
	 */
	@Resource
	private IFormulaService formulaService;
	
	/**
	 * 审核规则数据库操作对象
	 */
	@Resource
	private AuditRuleDefDAO auditRuleDefDAO;

	/**
	 * @Title: transName
	 * @Description:  将 columnID 转换为 中文列名称 <br/> 
	 * 					将引用代码表 转换为代码表名称  <br/> 
	 * 					将代码表的代码转换为其名称 <br/>
	 * 					『5056A058F7142D11E0533A06A8C084A7』 in ( RF(D525D48EBC2D47EABFB5DFA7A79B3DF6).{'01687htvYjHtO','01687wN2h1Ud5'} ) <br/>
	 * 					『收入分类科目名称』 in ( RF([CS0606]支出功能分类科目代码).{『[2010101][2010101]行政运行』,『[2010102][2010102]一般行政管理事务』} ) <br/>
	 * @param finalSqlWhere
	 * @param sqlWhere
	 * @param factors
	 * @return
	 * @throws Exception 
	 */
	public String transName(String finalSqlWhere, String sqlWhere,List<DictTFactorPO> factors) throws ServiceException {
		return transRefIdtoName(transColName(finalSqlWhere,sqlWhere,factors));
		
	}
	
	/**
	* @Title: transColName
	* @Description:  将 columnID 转换为 中文列名称 <br/> 
	* 					『5056A058F7142D11E0533A06A8C084A7』 <br/> 
	* 					『收入分类科目名称』 <br/> 
	* @param @param finalSqlWhere
	* @param @param sqlWhere
	* @param @param factors
	* @param @return    参数
	* @return String    返回类型
	* @throws
	 */
	private String transColName(String finalSqlWhere, String sqlWhere,List<DictTFactorPO> factors){
		String column = "";
		String columnTran = "";
		int start = sqlWhere.indexOf(Constants.COLUMN_START);
		int end = sqlWhere.indexOf(Constants.COLUMN_END);
		if (start < 0 || end < 0) {
			return finalSqlWhere;
		}
		column = sqlWhere.substring(start + 1, end);
		for (DictTFactorPO factor : factors) {
			if (column.equalsIgnoreCase(factor.getColumnid())) {
				columnTran = factor.getName();
				break;
			}
		}
		if(ConverTables.isNotNull(columnTran)) //如果为空 、 去除格式
			finalSqlWhere = finalSqlWhere.replace(column, columnTran);
		else 
			finalSqlWhere = finalSqlWhere.replace(Constants.COLUMN_START + column + Constants.COLUMN_END, columnTran );
		
		sqlWhere = sqlWhere.replace(Constants.COLUMN_START + column + Constants.COLUMN_END, "");
		
		return transColName(finalSqlWhere, sqlWhere, factors);
	}
	
	/**
	* @Title: transRefIdtoName
	* @Description: 将引用代码表 转换为代码表名称 将代码表的代码转换为其名称  <br/> 
	* 				RF(D525D48EBC2D47EABFB5DFA7A79B3DF6).{'01687htvYjHtO','01687wN2h1Ud5'} <br/> 
	* 				RF([CS0606]支出功能分类科目代码).{『[2010101][2010101]行政运行』,『[2010102][2010102]一般行政管理事务』}  <br/> 
	* @param @param inputStr
	* @param @return
	* @param @throws ServiceException
	* @return String
	* @throws
	 */
	private String transRefIdtoName( String inputStr) throws ServiceException {
		
		if(null==inputStr || "".equals(inputStr.trim())){
			return inputStr;
		}
		
		if(inputStr.indexOf(Constants.RF_START)==-1){
			return inputStr;
		}
		
		Pattern p = Pattern.compile("RF\\(.+?\\)\\.\\{.+?\\}");
		StringBuffer finalResult = new StringBuffer(50);
		if(inputStr.indexOf(Constants.RF_START)!=-1){//如果存在 则开始解析
			Matcher m= p.matcher(inputStr);
			while(m.find()){
				String tempStr = inputStr.substring(m.start(), m.end());
				
				String tableId =tempStr.substring(tempStr.indexOf(Constants.RF_START)+3, tempStr.indexOf(Constants.RF_END));
				String colMatchStr = tempStr.replace(Constants.RF_START, "")
						                    .replace(Constants.RF_END, "")
						                    .replace(tableId, "")
						                    .replace(Constants.COMCOL_START, "")
						                    .replace(Constants.COMCOL_END, "");
				String csDbTableName="";
				String tableName="";
				StringBuffer  idToNames=new StringBuffer(50);
				DictTModelcodePO mode =modelCodeService.getDictTModelcodePOByID(tableId.trim());
				//查询表的物理名称
				if (mode != null) {
					tableName = mode.getName(); // 表ID
					csDbTableName=mode.getDbtablename();
					colMatchStr=colMatchStr.replace("'", "");
					String[] idList = colMatchStr.split(",");
					
					List<TreeNode> nodes = null ;
					
					try {
						nodes = formulaService.getModelCodeDataByCsid(csDbTableName);
					} catch (Exception e ) {
						//获取代码表定义信息失败
						e.printStackTrace();
						throw new ServiceException(ExceptionCode.AUD_24209,"获取代码表 "+ csDbTableName +"失败",false);
					}

					if(nodes!=null && nodes.size()>0){
						for(String id :idList){
						
							String idName = ""; //代码表GUID
							for(TreeNode node :nodes){
								if(node.getId().equals(id)){
									idName = "["+node.getCode()+"]"+node.getName();
									break;
									}
							}
							if(!"".equals(idName)){
								if("".equals(idToNames.toString())){
									idToNames.append(Constants.COLUMN_START+idName+Constants.COLUMN_END);
								}else{
									idToNames.append(","+Constants.COLUMN_START+idName+Constants.COLUMN_END);
								}
							}
						
						}
					}else{
						//该定义录入表没有代码内容 throw 抛出异常 
						throw new ServiceException(ExceptionCode.AUD_24206,"代码表 "+ csDbTableName +"无数据",false);
					}

				}else{
					//根据表名 查询 表定义信息 失败 不存在 throw 抛出异常 
					throw new ServiceException(ExceptionCode.AUD_24209,"获取代码表 "+ tableId +"失败",false);
				} 
					
				m.appendReplacement(finalResult, Constants.RF_START+tableName+Constants.RF_END+Constants.COMCOL_START+idToNames.toString()+Constants.COMCOL_END);
			}
			
			m.appendTail(finalResult);
			return finalResult.toString();
		}
		return inputStr;
	}

	/**
	 * 将 中文列 转换为 dbColumnName 后缀 逗号 (,)
	 * @param finalSqlWhere
	 * @param sqlWhere
	 * @param factors
	 * @return
	 */
	public String transd(String finalSqlWhere, String sqlWhere,List<DictTFactorPO> factors) throws ServiceException {

		String column = "";
		String columnTran = "";
		int start = sqlWhere.indexOf(Constants.COLUMN_START);
		int end = sqlWhere.indexOf(Constants.COLUMN_END);
		if (start < 0 || end < 0) {
			return finalSqlWhere;
		}
		column = sqlWhere.substring(start + 1, end);
		for (DictTFactorPO factor : factors) {
			if (column.equalsIgnoreCase(factor.getName())) {
				columnTran = factor.getDbcolumnname();
				break;
			}
		}
		finalSqlWhere = finalSqlWhere.replace(Constants.COLUMN_START + column + Constants.COLUMN_END, columnTran + ",");
		
		sqlWhere = sqlWhere.replace(Constants.COLUMN_START + column + Constants.COLUMN_END, "");
		
		return transd(finalSqlWhere, sqlWhere, factors);

	}
	
	/**
	* @Title: trans
	* @Description: 转换引用列
	* @param @param finalSqlWhere
	* @param @param sqlWhere
	* @param @param factors
	* @param @param dbTableName
	* @param @return
	* @param @throws Exception    参数
	* @return String    返回类型
	* @throws
	 */
	public String trans(String finalSqlWhere, String sqlWhere,List<DictTFactorPO> factors,String dbTableName) throws ServiceException{
		String tempfinalSqlWhere=transRefTableCol(finalSqlWhere);
		return transRef(tempfinalSqlWhere,factors,dbTableName);
		
	}
	
	/**
	 * 将RF(表名).{『字段名称1』,『字段名称2』} 替换成 'value01','value02'
	 * @param refFactor
	 * @param l_col
	 * @param formMap
	 * @throws Exception 
	 */
	private String transRefTableCol( String inputStr) throws ServiceException {
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
					
					List<TreeNode> nodes = null ;
					
					try {
						nodes = formulaService.getModelCodeDataByCsid(csDbTableName);
					} catch (Exception e ) {
						e.printStackTrace();
						throw new ServiceException(ExceptionCode.AUD_24209,"获取代码表 "+ csDbTableName +"失败",false);
					}

					if(nodes!=null && nodes.size()>0){
						while(cm.find()){
							String colName = colMatchStr.substring(cm.start()+1, cm.end()-1);
							String guID = ""; //代码表GUID
							for(TreeNode node :nodes){
								String tempName="["+node.getCode()+"]"+node.getName();
								if(tempName.equals(colName.trim())||node.getName().equals(colName.trim())){
									guID = node.getId();
									break;
									}
							}
							if(!"".equals(guID)){
								cm.appendReplacement(resultStr, "'"+guID+"'");
							}else{
								throw new ServiceException(ExceptionCode.AUD_24206,"代码表【"+tableName+"】中的引用值  ' "+colName+"'  不存在",false);
							}
						}
						cm.appendTail(resultStr);
					}else{
						//该定义录入表没有代码内容 throw 抛出异常 
						throw new ServiceException(ExceptionCode.AUD_24206,"引用表 "+ csDbTableName +"无数据",false);
					}
				}else{
					//根据表名 查询 表定义信息 失败 不存在 throw 抛出异常 
					throw new ServiceException(ExceptionCode.AUD_24209,"获取代码表 "+ tableName +"失败",false);
				} 
				m.appendReplacement(finalResult, Constants.RF_START+csDbTableId+Constants.RF_END+Constants.COMCOL_START+resultStr.toString()+Constants.COMCOL_END);
			}
			
			m.appendTail(finalResult);
			return finalResult.toString();
		}
		return inputStr;
	
	}
	
	/**
	 * 将 中文列 转换为 dbColumnName
	 * @param finalSqlWhere
	 * @param sqlWhere
	 * @param factors
	 * @return
	 * @throws Exception 
	 */
	private String transRef(String sqlWhere,List<DictTFactorPO> factors,String dbTableName) throws ServiceException {
		
		String column = "";
		String columnTran = "";
		int start = sqlWhere.indexOf(Constants.COLUMN_START);
		int end = sqlWhere.indexOf(Constants.COLUMN_END);
		if (start < 0 || end < 0) {
			return sqlWhere;
		}
		column = sqlWhere.substring(start + 1, end);
		for (DictTFactorPO factor : factors) {
			if (column.equals(factor.getName())) {
				columnTran = dbTableName+"."+factor.getDbcolumnname();
				break;
			}
		}
		if(null==columnTran || "".equals(columnTran)){
			throw new ServiceException(ExceptionCode.AUD_24210,"列【"+column+"】  不存在",false);
		}
		sqlWhere = sqlWhere.replace(Constants.COLUMN_START + column + Constants.COLUMN_END, columnTran);
		return transRef(sqlWhere, factors,dbTableName);
	}
	
	/**
	 * 将 中文列 转换为 columnID 保存格式 『columnID』
	 * @param finalSqlWhere
	 * @param sqlWhere
	 * @param factors
	 * @return
	 * @throws Exception 
	 */
	public String transIDFormat(String finalSqlWhere, String sqlWhere,List<DictTFactorPO> factors) throws ServiceException {
		String tempSql=transRefTableCol(finalSqlWhere);
		return transIDFormatNew(tempSql,  tempSql,factors);
	}
	
	/**
	* @Title: transIDFormatNew
	* @Description: 将 中文列 转换为 columnID 保存格式 『columnID』
	* @param @param finalSqlWhere
	* @param @param sqlWhere
	* @param @param factors
	* @param @return    参数
	* @return String    返回类型
	* @throws
	 */
	private String transIDFormatNew(String finalSqlWhere, String sqlWhere,List<DictTFactorPO> factors){
		String column = "";
		String columnTran = "";
		int start = sqlWhere.indexOf(Constants.COLUMN_START);
		int end = sqlWhere.indexOf(Constants.COLUMN_END);
		if (start < 0 || end < 0) {
			return finalSqlWhere;
		}
		column = sqlWhere.substring(start + 1, end);
		for (DictTFactorPO factor : factors) {
			if (column.equalsIgnoreCase(factor.getName())) {
				columnTran = factor.getColumnid();
				break;
			}
		}
		finalSqlWhere = finalSqlWhere.replace(Constants.COLUMN_START +column+ Constants.COLUMN_END, Constants.COLUMN_START +columnTran+Constants.COLUMN_END);
		sqlWhere = sqlWhere.replace(Constants.COLUMN_START + column + Constants.COLUMN_END, "");
		return transIDFormatNew(finalSqlWhere, sqlWhere, factors);
	}	
	
	/**
	* @Title: makeSql
	* @Description: 转换为sql
	* @param @param map
	* @param @param checkType
	* @param @return
	* @return Map<String,Object>
	* @throws
	 */
	public Map<String, Object> makeSql(Map<String, Object> map, String checkType)  throws ServiceException {
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
	
	/**
	* @Title: clearRF
	* @Description: 移除 RF 开头内容
	* @param @param inputStr
	* @param @return
	* @return String
	* @throws
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
	* @Title: wrapGroupby
	* @Description: 处理分组
	* @param @param colName
	* @param @param factorsL
	* @param @param i
	* @param @param prefix
	* @param @param dbTableName
	* @param @return
	* @return String
	* @throws
	 */
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
	
	/**
	* @Title: appendRelatype
	* @Description: 处理 关系符号
	* @param @param sql
	* @param @param relatype
	* @return void
	* @throws
	 */
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
	 * @Title: replaceCNChar
	 * @Description: 处理中文括号
	 * @param @param str
	 * @param @return
	 * @return String
	 * @throws
	 */
	private String replaceCNChar(String str) {
		String tempStr = "";
		tempStr = str.replaceAll("（", "(");
		tempStr = tempStr.replaceAll("）", ")");
		return tempStr;
	}
	
	/**
	* @Title: findColumnName
	* @Description: 查找列名称
	* @param @param factors
	* @param @param colName
	* @param @return
	* @return String
	* @throws
	 */
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
	
	/**
	* @Title: check
	* @Description:检查公式
	* @param @param map
	* @return void
	* @throws
	 */
	public void check(Map map) throws ServiceException {
		
		if(ConverTables.isNotNull(map.get("WHERE"))){
			map.put("WHERE", clearRF(map.get("WHERE").toString()));
		}
		if(ConverTables.isNotNull(map.get("WHERE")))
			
			try{
				auditRuleDefDAO.checkByWhere(map);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(ExceptionCode.AUD_24211,"验证:【"+ map.get("TABLENAME") + "】\n【" + map.get("WHERE")+"】\n【" + map.get("GROUP")+"】 失败",false);
			}
			
		else{
			if(ConverTables.isNotNull(map.get("GROUP"))){
				try{
					auditRuleDefDAO.check(map);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServiceException(ExceptionCode.AUD_24211,"验证:【"+ map.get("TABLENAME") + "】\n【" + map.get("GROUP")+"】失败",false);
				}
			}
		}
	}
	
	
	/**
	* @Title: checkNum
	* @Description:检查数值
	* @param @param map
	* @return void
	* @throws
	 */
	public void checkNum(Map map) throws ServiceException {

		if(ConverTables.isNotNull(map.get("COL"))){
			map.put("COL", clearRF(map.get("COL").toString()));
		}

		try{
			auditRuleDefDAO.checkNum(map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.AUD_24211,"验证:【"+ map.get("TABLENAME") + "】\n【" + map.get("COL")+"】失败",false);
		}
		
	}
	
	/**
	* @Title: formatSql
	* @Description: 格式化sql
	* @param @param sql
	* @param @return
	* @return String
	* @throws
	 */
	public String formatSql(String sql){
		sql=sql.replaceAll("FROM", "\\\n  FROM");
		sql=sql.replaceAll("WHERE", "\\\n     WHERE");
		sql=sql.replaceAll("GROUP", "\\\n           GROUP");
		sql=sql.replaceAll("UNION ALL", "\\\n UNION ALL \\\n");
		sql=sql.replaceAll("HAVING", "\\\n  HAVING");
		sql=sql.replaceAll("SUM", "\\\n SUM");
		return sql;
	}
	
}
