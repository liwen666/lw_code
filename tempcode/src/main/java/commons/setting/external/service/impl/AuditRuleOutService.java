package commons.setting.external.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.setting.dataaudit.rule.util.AuditRuleSqlFactory;
import com.tjhq.commons.setting.external.dao.AuditRuleOutMapper;
import com.tjhq.commons.setting.external.po.AuditRuleBaseInfoPO;
import com.tjhq.commons.setting.external.po.AuditRulePO;
import com.tjhq.commons.setting.external.service.IAuditRuleOutService;

/**
 *@desc:数据审核对外接口实现类
 *@author： wxn
 *@date:2014-10-28上午9:15:30
 */
@Service
@Transactional(readOnly=true)
public class AuditRuleOutService implements IAuditRuleOutService {
	Logger logger = Logger.getLogger(AuditRuleOutService.class);
	@Autowired
    private AuditRuleOutMapper auditRuleOutMapper;
	@Resource
	private IDictTModelService dictTModelService; // 表接口
	@Override
	public String getAuditResult(String checkId) throws Exception{
		return getAuditResult(checkId,null,null);
	}
	public String getAuditResult(String checkId,String condition) throws Exception{
		return getAuditResult(checkId,condition,null);
	}
	@Override
	public String getAuditResult(String checkId,
			String conditions,String deltaData)throws Exception{
		AuditRulePO map=new AuditRulePO();
		map.setCheckId(checkId);
		map.setConditions(conditions);
		map.setDeltaData(deltaData);
		auditRuleOutMapper.getAuditResult(map);
		String auditResult=map.getResultData();
		 if(null!=auditResult && !auditResult.equals("")){
		     auditResult = auditResult.replaceAll("\\$\\$\\$\\$", "},{");
			// auditResult=auditResult.replaceAll("\n", "").replaceAll("\r", "").replaceAll("\0", "");
		    auditResult=auditResult.replaceAll("[\n\r\0]", "");
		 }
		return auditResult;
	}
	@Override
	public List<AuditRuleBaseInfoPO> getAuditRuleList(List<String> tableIDList, String appID)
			throws ServiceException {
		 return auditRuleOutMapper.queryAuditRuleList(AuditRuleSqlFactory.getQueryAuditRuleSql(arrayToString(tableIDList), appID));
	}
	public String getAuditRuleList4DbversionSql(List<String> tableIDList, String appID)
			throws ServiceException {
	     if(tableIDList.size()==0)return null;
		 return AuditRuleSqlFactory.getAuditRuleList4DbversionSql(arrayToString(tableIDList), appID);
	}
	@Override
	public String getAuditRuleListSQL(List<String> tableIDList, String appID)
			throws Exception {
	     if(tableIDList.size()==0)return null;
		
		return AuditRuleSqlFactory.getQueryAuditRuleSql(arrayToString(tableIDList), appID);
	}
	public String arrayToString(List<String> str){
		return str.toString().replace("[", "'").replace("]", "'").replace(" ", "").replace(",", "','");
		
	}
	@Override
	public boolean canDelViewTOMaterialRaltion(String tableID)
			throws ServiceException {
		logger.debug("tableID="+tableID);
		DictTModelPO modelPO=dictTModelService.getDictTModelByID(tableID, false);
		Map<String,String> paraMap = new HashMap<String,String>();
		paraMap.put("tableID", tableID);
		if("2".equals(modelPO.getTabletype())){//视图
			int dbvNum=auditRuleOutMapper.findDbversionColCount(modelPO.getDbtablename());
			logger.debug("dbversion的数量:"+dbvNum);
			if(dbvNum==0){//没有dbversion字段
				int sl=auditRuleOutMapper.findAuditRuleCountInUsed(paraMap);
				logger.debug("tableType=2 规则数量:"+sl);
				return sl>0?false:true;
			}else{
				return true;
			}
		}
		if("3".equals(modelPO.getTabletype())){//业务对象
			DictTModelPO reModelPO=dictTModelService.getDictTModelByID(modelPO.getRelatab(), false);
			if(!"1".equals(reModelPO.getTabletype())){
				int dbvNum=auditRuleOutMapper.findDbversionColCount(modelPO.getDbtablename());
				logger.debug("dbversion的数量:"+dbvNum);
				if(dbvNum==0){
					int sl=auditRuleOutMapper.findAuditRuleCountInUsed(paraMap);
					logger.debug("tableType=3 and 引用了非物理表 规则数量:"+sl);
					return sl>0?false:true;
				}else{
					return true;
				}
			}

		}
		return true;
	}


}

