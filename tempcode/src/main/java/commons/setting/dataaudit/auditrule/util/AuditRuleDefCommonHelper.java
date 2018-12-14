package commons.setting.dataaudit.auditrule.util;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.setting.formula.service.IFormulaService;
import com.tjhq.commons.setting.input.web.ConverTables;


/**
* @ClassName: AuditRuleDefCommonHelper
* @Description: 审核规则定义 表内、表间、自定义的公共方法工具类
* @author xiehongtao
* @date 2017-6-16 下午3:00:36
*
 */
@Component
@Transactional(readOnly=true)
public class AuditRuleDefCommonHelper {
	
	
	private String AUDITTYPETIN = "0"; // 表内审核
	private String AUDITTYPETOUT = "1"; // 表间审核
	
	/**
	 * 定义表列信息的服务接口
	 */
	@Resource
	private IDictTFactorService dictTFactorService; 

	/**
	 * 列表达式解析帮助类
	 */
	@Resource
	private AuditRuleParseHelper auditRuleParseHelper;
	
	/**
	 * 表接口对象
	 */
	@Resource
	private IDictTModelService dictTModelService; // 表接口
	
	/**
	 * 表达式服务对象
	 */
	@Resource
	private IFormulaService formulaService;
	
	/**
	* @Title: AuditDataToFace
	* @Description: 解析从数据库取得的数据到前台的显示格式
	* @param @param map 
	* @param @return
	* @return Map 
	* @throws
	 */
	public Map<String, Object> AuditDataToFace(Map<String, Object> map) throws ServiceException{
		
		String ltableId = (String) map.get("LTABLEID"); // 左表ID
		String rtableId = (String)map.get("RTABLEID"); //右表ID
		String lauditTab = (String) map.get("LTABLENAME"); // 左表的表名
		String rauditTab = (String) map.get("RTABLENAME");	// 右表的表名
		
		//获取列信息开始
		List<DictTFactorPO> factorsL = new ArrayList<DictTFactorPO>(); //左表列信息
		List<DictTFactorPO> factorsR = new ArrayList<DictTFactorPO>(); //右表列信息
		
		//左表名称是否为null
		if (ConverTables.isNotNull(lauditTab)) {
			factorsL = dictTFactorService.getDictTFactorByTableidAndType(ltableId, "1");
		}
		//右表名称是否为null
		if(ConverTables.isNotNull(rauditTab)){
			factorsR = dictTFactorService.getDictTFactorByTableidAndType(rtableId, "1");
		}
		//获取列信息结束
		
		// 需要 转换 列 LCOL、RCOL、LWHERE、RWHERE、LGROUP、RGROUP 为前台显示格式 列id -> 中文列名 引用代码表 -> 引用内容
		String lCol = (String)map.get("LCOL");
		String lWhere = (String)map.get("LWHERE");
		String lGroup = (String)map.get("LGROUP");
		String rCol = (String)map.get("RCOL");
		String rWhere = (String)map.get("RWHERE");
		String rGroup = (String)map.get("RGROUP");

		//将数据库存储的内容解析为界面显示格式 start 
		if (ConverTables.isNotNull(lCol)) {
			//左表表达式
			map.put("LCOL", auditRuleParseHelper.transName(lCol, lCol,factorsL));
		}
		
		if (ConverTables.isNotNull(lWhere)) {
			//左表过滤条件
			map.put("LWHERE", auditRuleParseHelper.transName(lWhere, lWhere, factorsL));
		}
		
		if (ConverTables.isNotNull(lGroup)) {
			//左表分组
			map.put("LGROUP", auditRuleParseHelper.transName(lGroup, lGroup, factorsL));
		}
		
		if (ConverTables.isNotNull(rCol)) {
			//右表表达式
			if(map.get("CHECKTYPE").toString().equals(AUDITTYPETIN)){
				map.put("RCOL", auditRuleParseHelper.transName(rCol, rCol, factorsL));
			} else{
				map.put("RCOL", auditRuleParseHelper.transName(rCol, rCol, factorsR));
			}
		}
		if (ConverTables.isNotNull(rWhere)) {
			//右表过滤条件
			if(map.get("CHECKTYPE").toString().equals(AUDITTYPETIN)){
				map.put("RWHERE", auditRuleParseHelper.transName(rWhere, rWhere, factorsL));
			} else{
				map.put("RWHERE", auditRuleParseHelper.transName(rWhere, rWhere, factorsR));
			}	
		}
		
		if (ConverTables.isNotNull(rGroup)) {
			//右表分组
			if(map.get("CHECKTYPE").toString().equals(AUDITTYPETIN)){
				map.put("RGROUP", auditRuleParseHelper.transName(rGroup, rGroup, factorsL));
			} else{
				map.put("RGROUP", auditRuleParseHelper.transName(rGroup, rGroup, factorsR));
			}
		}			
		//将数据库存储的内容解析为界面显示格式	end
		
		//表内和表间审核 移除 CHECKSQL 
		if(map.get("CHECKTYPE").toString().equals(AUDITTYPETIN) || map.get("CHECKTYPE").toString().equals(AUDITTYPETOUT)) {
			map.remove("CHECKSQL");
		}

		return map;
	}
	
	/**
	* @Title: AuditDataToDB
	* @Description: 解析前台的审核数据到数据库存储的格式
	* @param @param formMap
	* @param @return
	* @return ResultPO
	* @throws
	 */
	public ResultPO AuditDataToDB(Map<String, Object> formMap) throws ServiceException{
		//检查类型
		String checkType = formMap.get("CHECKTYPE").toString();
		
		//替换 左描述 、 右描述 、说明中的 英文双引号 为中文双引号
        clearSpecialChar(formMap);
		
		//审核表
		String l_tableID = (String) formMap.get("LTABLEID");
		String r_tableID = (String) formMap.get("RTABLEID");
		//表达式列
		String l_col = (String) formMap.get("LCOL");
		String r_col = (String) formMap.get("RCOL");
		// 分组
		String l_group = (String) formMap.get("LGROUP");
		String r_group = (String) formMap.get("RGROUP");
		// 条件
		String l_where = (String) formMap.get("LWHERE");
		String r_where = (String) formMap.get("RWHERE");

		String LtableName = "";
		String RtableName = "";
		
		List<DictTFactorPO> factorsL = new ArrayList<DictTFactorPO>();
		List<DictTFactorPO> factorsR = new ArrayList<DictTFactorPO>();
		
		String checkSql = "";
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		LtableName = dictTModelService.getDictTModelByID(l_tableID,false).getDbtablename();
		factorsL = dictTFactorService.getDictTFactorByTableidAndType(l_tableID, "1");
		
		map.put("LTABLENAME", LtableName);
		
		if(checkType.equals(AUDITTYPETOUT)){ 
			RtableName = dictTModelService.getDictTModelByID(r_tableID,false).getDbtablename();
			factorsR = dictTFactorService.getDictTFactorByTableidAndType(r_tableID, "1");
		}
		
		map.put("RTABLENAME", RtableName);
		
		String Lcol = "",Rcol = "";
		String Lwhere = "",Rwhere = "";
		
		// 表达式转换 将中文列名称转换为列的id 表的中文名称转换为id

		Lcol = auditRuleParseHelper.trans(l_col, l_col, factorsL,LtableName);
		
		if(checkType.equals(AUDITTYPETIN)){
			Rcol = auditRuleParseHelper.trans(r_col, r_col, factorsL,LtableName);
		} else {
			Rcol = auditRuleParseHelper.trans(r_col, r_col, factorsR,RtableName);
		}	
		
		map.put("LCOL", Lcol);
		map.put("RCOL", Rcol);

		//进行格式转换 将中文列名称 集合 转换为 列的
		String Lgroup = auditRuleParseHelper.transd(l_group, l_group, factorsL);
		if ( Lgroup.length() > 0 ) {
			Lgroup = Lgroup.substring(0, Lgroup.length() - 1);
		}
		
		String Rgroup = "";
		if(checkType.equals(AUDITTYPETOUT)){
			Rgroup = auditRuleParseHelper.transd(r_group, r_group, factorsR);
			if ( Rgroup.length() > 0 ) {
				Rgroup = Rgroup.substring(0, Rgroup.length() - 1);
			}	
		}
		
		map.put("LGROUP", Lgroup);
		map.put("RGROUP", Rgroup);

		//条件定义转换
		if (ConverTables.isNotNull(l_where)) {
			Lwhere = auditRuleParseHelper.trans(l_where, l_where, factorsL,LtableName);
			map.put("LWHERE", Lwhere);
		} else {
			map.put("LWHERE", "");
		}
		if (ConverTables.isNotNull(r_where) && checkType.equals(AUDITTYPETOUT) ) {
			Rwhere = auditRuleParseHelper.trans(r_where, r_where, factorsR,RtableName);
			map.put("RWHERE", Rwhere);
		} else {
			map.put("RWHERE", "");
		}

		map.put("RELATYPE", formMap.get("RELATYPE"));
		map.put("ERRORDEF", formMap.get("ERRORDEF"));
		
		map.put("factorsL", factorsL);
		map.put("factorsR", factorsR);
	
		Map<String,Object> dataMap = auditRuleParseHelper.makeSql(map, checkType);
		
		checkSql = (String)dataMap.get("CHECKSQL");
		
		formMap.put("CHECKSQL", checkSql);
		formMap.put("PUBGROUP", dataMap.get("PUBGROUP"));
		
		//将中文的列名称和列分组存储下来 后续检查可以直接使用 
		formMap.put("LTABLENAMECHECK", LtableName);
		formMap.put("LCOLCHECK", auditRuleParseHelper.clearRF(Lcol));
		formMap.put("LWHERECHECK", auditRuleParseHelper.clearRF(Lwhere));
		formMap.put("LGROUPCHECK",Lgroup);
		formMap.put("RCOLCHECK", auditRuleParseHelper.clearRF(Rcol));
		
		if (checkType.equals(AUDITTYPETOUT) ) {
			formMap.put("RTABLENAMECHECK", RtableName);
			formMap.put("RWHERECHECK", auditRuleParseHelper.clearRF(Rwhere));
			formMap.put("RGROUPCHECK", Rgroup);
		}
		//end
		
		//将checksql 去掉 where 的存储一份 后续 查看语法时 使用
		if(checkSql.indexOf("@WHERE@")>0){
			formMap.put("CHECKSQLPARSE",auditRuleParseHelper.formatSql(checkSql.replaceAll("@WHERE@", "")));
		} else {
			formMap.put("CHECKSQLPARSE",checkSql);
		}
		//end 
		
		//转换 名称列 为coumnID | LCOL、RCOL、LWHERE、RWHERE、LGROUP、RGROUP
		formMap.put("LCOL", auditRuleParseHelper.transIDFormat(l_col, l_col, factorsL));
		formMap.put("LWHERE", auditRuleParseHelper.transIDFormat(l_where, l_where, factorsL));
		formMap.put("LGROUP",auditRuleParseHelper.transIDFormat(l_group, l_group, factorsL));
		
		if (checkType.equals(AUDITTYPETOUT) ) {
			formMap.put("RCOL", auditRuleParseHelper.transIDFormat(r_col, r_col, factorsR));
			formMap.put("RWHERE", auditRuleParseHelper.transIDFormat(r_where, r_where, factorsR));
			formMap.put("RGROUP", auditRuleParseHelper.transIDFormat(r_group, r_group, factorsR));
		} else {
			formMap.put("RCOL", auditRuleParseHelper.transIDFormat(r_col, r_col, factorsL));
		}
		
		return new ResultPO("true");
	}
	
	
	/**
	* @Title: CheckAuditDataSql
	* @Description: 检查sql语句
	* @param @param formMap
	* @param @return
	* @return ResultPO
	* @throws
	 */
	public ResultPO CheckAuditDataSql(Map<String, Object> formMap) throws ServiceException{
		
		String checkSql = (String)formMap.get("CHECKSQL");

		// 验证CHECKSQL 是否错误
		if(checkSql.indexOf("@WHERE@")>0){
			checkSql=checkSql.replaceAll("@WHERE@", "");
		}
		
		try {
			formulaService.callErrorMessage(checkSql); 
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.AUD_24208,e.getErrorMessage(),false);
		} catch (Exception e) {
			e.printStackTrace();
			
			//细化sql的提示信息
			String msg="";
			
			if( e instanceof BadSqlGrammarException || e instanceof SQLSyntaxErrorException ){
				msg = e.getMessage().substring(e.getMessage().indexOf("ORA"));
				msg = msg.substring(0, msg.indexOf("##"));
			} else {
				msg = "验证sql语法失败,请查看sql语法进行检查";
			}
	
			throw new ServiceException(ExceptionCode.AUD_24208,msg,false);
		}
		
		return new ResultPO("true");
	}
	
	/**
	* @Title: clearSpecialChar
	* @Description: 替换 左描述 、 右描述 、说明中的 英文双引号 为中文双引号
	* @param @param formMap
	* @return void
	* @throws
	 */
	private void clearSpecialChar(Map<String, Object> formMap) {
		
		String msg = (String) formMap.get("SHOWTEXT");
		
		if (null != msg && !"".equals(msg)) {
			msg = msg.replaceAll("\"", "“");
			formMap.put("SHOWTEXT", msg);
		}
		
		msg = (String) formMap.get("RDESC");
		if (null != msg && !"".equals(msg)) {
			msg = msg.replaceAll("\"", "“");
			formMap.put("RDESC", msg);
		}
		
		msg = (String) formMap.get("LDESC");
		if (null != msg && !"".equals(msg)) {
			msg = msg.replaceAll("\"", "“");
			formMap.put("LDESC", msg);
		}

	}
	
	/**
	* @Title: checkAuditData
	* @Description: 检查定义信息
	* @param @param formMap
	* @param @return
	* @return String
	* @throws
	 */
	public ResultPO checkAuditData(Map<String, Object> formMap) throws ServiceException {

		//检查类型
		String checkType = formMap.get("CHECKTYPE").toString();
					
		String Ltablename = (String)formMap.get("LTABLENAMECHECK");
		String Rtablename = (String)formMap.get("RTABLENAMECHECK");
		
		String Lcol = (String)formMap.get("LCOLCHECK");
		String Rcol = (String)formMap.get("RCOLCHECK");
		
		String Lwhere = (String)formMap.get("LWHERECHECK");
		String Rwhere = (String)formMap.get("RWHERECHECK");

		String Lgroup = (String)formMap.get("LGROUPCHECK"); 
		String Rgroup = (String)formMap.get("RGROUPCHECK");

		Map<String, Object> mapL = new HashMap<String, Object>();
		Map<String, Object> mapR = new HashMap<String, Object>();
		Map<String, Object> mapExp = new HashMap<String, Object>();
		
		//表间审核检查左右分组数组长度是否相等
		if (checkType.equals(AUDITTYPETOUT) ){
			if (Lgroup.split(",").length != Rgroup.split(",").length) return new ResultPO("group","");
		} 
		
		//左表
		mapL.put("TABLENAME", Ltablename);
		mapL.put("WHERE", Lwhere);
		mapL.put("GROUP", Lgroup);
		
		//检查左分组与 分组
		auditRuleParseHelper.check(mapL);
		
		if (checkType.equals(AUDITTYPETOUT) ){ 
			
			//右表
			mapR.put("TABLENAME", Rtablename);
			mapR.put("WHERE", Rwhere);
			mapR.put("GROUP", Rgroup);

			// 右条件 与 分组
			auditRuleParseHelper.check(mapR);
		
		}
	
		mapExp.put("TABLENAME", Ltablename);// 左表达式
		mapExp.put("COL", Lcol); 
		auditRuleParseHelper.checkNum(mapExp);
		
		if (checkType.equals(AUDITTYPETOUT) ){ 
			mapExp.put("TABLENAME", Rtablename);// 右表达式
			mapExp.put("COL", Rcol);
			auditRuleParseHelper.checkNum(mapExp);
		}
		
		return new ResultPO("true");
		
	}
}
