package commons.setting.dataaudit.auditrule.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.dao.UtilsMapper;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.grid.settinggrid.service.ISettingGridService;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.po.TreePO;

import com.tjhq.commons.setting.dataaudit.auditrule.service.IAuditRuleDefService;
import com.tjhq.commons.setting.dataaudit.auditrule.dao.AuditRuleDefDAO;
import com.tjhq.commons.setting.dataaudit.auditrule.util.AuditRuleCheckDefTableHelper;
import com.tjhq.commons.setting.dataaudit.auditrule.util.AuditRuleDefCommonHelper;
import com.tjhq.commons.setting.dataaudit.auditrule.util.AuditRuleSysfnTableHelper;

import com.tjhq.commons.utils.UserUtil;

/**
* @ClassName: AuditRuleDefService
* @Description: 审核规则定义新的服务类
* @author xiehongtao
* @date 2017-6-13 下午4:16:12
*
 */
@Service
@Transactional(readOnly=true)
public class AuditRuleDefService implements IAuditRuleDefService {

	private String AUDITINSERT = "0"; // 审核新增还是修改
	
	private static String AUDITTYPETIN = "0"; // 表内审核
	private static String AUDITTYPETOUT = "1"; // 表间审核
	
	/**
	 * 审核规则定义数据库操作类
	 */
	@Resource
	private AuditRuleDefDAO auditRuleDefDAO;
	
	/**
	 * 表接口对象
	 */
	@Resource
	private IDictTModelService dictTModelService; // 表接口
	
	/**
	 * 表格设置服务对象
	 */
	@Resource
	private ISettingGridService settingGridService;
	
	/**
	 * 审核定义公共方法工具类
	 */
	@Resource
	private AuditRuleDefCommonHelper auditRuleDefCommonHelper;
	
	/**
	 * 数据库操作工具类
	 */
	@Resource
	private UtilsMapper utilsMapper;
	
	@Override
	public Map<String, Object> getAuditData(String checkid) {
		
		try {
			// 使用新的方法 从数据库 获取审核的定义信息
			Map<String, Object> map = auditRuleDefDAO.getAuditDataAdv(checkid);
			// 处理审核信息 转换 数据库存储格式 为 前台定义格式
			return auditRuleDefCommonHelper.AuditDataToFace(map);

		} catch (ServiceException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}


	@Override
	public ResultPO saveAuditData(String formids) {
		
		ResultPO pro = null ;	//结果返回对象

		try {
			//转换为java map
			Map<String,Object> formMap = (new ObjectMapper()).readValue(formids, Map.class);
			
			formMap.put("USERDISTRICTID", UserUtil.getUserInfo().getAdmdiv());	//用户所属行政区划
			formMap.put("userAgencyID", UserUtil.getUserInfo().getAgency());	//用户单位id
			formMap.put("userUpagencyID", UserUtil.getUserInfo().getUpAgencyID());	//用户的上级单位ID
			formMap.put("CREATEBGTLEVEL",auditRuleDefDAO.findDistLevel((String)formMap.get("userUpagencyID")));	//审核规则创建的用户级次
			
			//验证视图和业务对象设置是否正确 start
			String l_tableID = (String) formMap.get("LTABLEID");
			String r_tableID = (String) formMap.get("RTABLEID");
		
			String errMsg="";
			errMsg=checkViewAndBusiObj(l_tableID);
			if(null!=errMsg && !"".equals(errMsg)){
				return new ResultPO("",errMsg);
			}
	        if(null!=r_tableID && !"".equals(r_tableID)){
	    		errMsg=checkViewAndBusiObj(r_tableID);
	    		if(null!=errMsg && !"".equals(errMsg)){
	    			return new ResultPO("",errMsg);
	    		}
	        }
	        //验证视图和业务对象设置是否正确 end
	        
	        //自定义审核直接验证check sql 即可 不需要解析列 分组 等 
	        if(formMap.get("CHECKTYPE").toString().equals(AUDITTYPETIN) || formMap.get("CHECKTYPE").toString().equals(AUDITTYPETOUT)){
	        	//调用工具类 解析为数据库格式
	        	pro = auditRuleDefCommonHelper.AuditDataToDB(formMap);
	        }
	        
	        // 验证CHECKSQL 是否错误
	        pro = auditRuleDefCommonHelper.CheckAuditDataSql(formMap);
	        
	        //进行保存
	        if (pro.isSuccessFlag()) {
				// 如果是新增操作
				if (formMap.get("APPROVEFLAG").toString().equals(AUDITINSERT)) {
					String checkID = utilsMapper.getDBUniqueID();	
					formMap.put("CHECKID", checkID);
					auditRuleDefDAO.insertAuditData(formMap);
				} else { // 如果是修改操作
					auditRuleDefDAO.modifyAuditData(formMap);
				}
				
				return new ResultPO("");
				
	        } else {
	        	return pro;
	        }
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResultPO("",e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultPO("",e.getMessage().replace("\"", "'").replace("\n", ""));
		}
	}
	
	/**
	* @Title: checkViewAndBusiObj
	* @Description: 检查试图或者业务对象
	* @param @param l_tableID
	* @param @return
	* @return String
	* @throws
	 */
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
	/**
	* @Title: checkViewObj
	* @Description: 检查视图
	* @param @param modelPO
	* @param @return
	* @return String
	* @throws
	 */
	private String checkViewObj(DictTModelPO modelPO) {
			int n1=auditRuleDefDAO.findMaterialTalbleCount(modelPO.getTableid());
			int n2=auditRuleDefDAO.findDbversionColCount(modelPO.getDbtablename());
			if(n1==0 && n2==0){//视图没有dbversion 也没有登记物理表
				return "表["+modelPO.getName()+"]是视图，请先设置其对应的物理表,或 给该视图增加dbversion字段!";
			}
		return null;
	}
	
	/** 
	* @Title: checkBusiObj
	* @Description: 检查业务对象
	* @param @param modelPO
	* @param @return
	* @return String
	* @throws
	 */
	private String checkBusiObj(DictTModelPO modelPO) {
		int n2=auditRuleDefDAO.findDbversionColCount(modelPO.getDbtablename());
		if(n2==0){
			DictTModelPO reModelPO=dictTModelService.getDictTModelByID(modelPO.getRelatab(),false);
			if(!"1".equals(reModelPO.getTabletype())){//如果业务对象引用的表不是物理表 则提示 他登记其所用的物理表
					return "表["+modelPO.getName()+"]是业务对象，但是其引用的表不是物理表，请先设置其对应的物理表!";
			}
		}
		return null;
	}
	
	@Override
	public ResultPO checkAuditData(String formids)  {
		
		ResultPO pro = null ;	//结果返回对象
		
		//转换为java map
		try {
			
			Map<String,Object> formMap = (new ObjectMapper()).readValue(formids, Map.class);
	        
	        //自定义审核直接验证check sql 即可 不需要解析列 分组 等 
	        if(formMap.get("CHECKTYPE").toString().equals(AUDITTYPETIN) || formMap.get("CHECKTYPE").toString().equals(AUDITTYPETOUT)){
	        	//调用工具类 解析为数据库格式
	        	pro = auditRuleDefCommonHelper.AuditDataToDB(formMap);
	        	
		        //验证列是否正确 表达式 、where条件、分组等 
		        pro = auditRuleDefCommonHelper.checkAuditData(formMap);
	        }
	        
	        // 验证CHECKSQL 是否错误
	        pro = auditRuleDefCommonHelper.CheckAuditDataSql(formMap);

	        return pro;
	     
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResultPO("",e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultPO("",e.getMessage());
		}

	} 


	@Override
	public ResultPO parseAuditData(String formids) {
		
		//转换为java map
		try {
			
			ResultPO pro = null ;	//结果返回对象
			String checkSql = "";
			
			Map<String,Object> formMap = (new ObjectMapper()).readValue(formids, Map.class);
	        
	        //自定义审核直接验证check sql 即可 不需要解析列 分组 等 
	        if(formMap.get("CHECKTYPE").toString().equals(AUDITTYPETIN) || formMap.get("CHECKTYPE").toString().equals(AUDITTYPETOUT)){
	        	//调用工具类 解析为数据库格式
	        	pro = auditRuleDefCommonHelper.AuditDataToDB(formMap);
	        }
	        
	        checkSql =(String) formMap.get("CHECKSQLPARSE");
	        
	        return new ResultPO(checkSql) ;

		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResultPO("",e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultPO("",e.getMessage().replace("\"", "'").replace("\n", ""));
		}
	}


	@Override
	public Integer findBudgetLevel(String userAgencyID) {
		return auditRuleDefDAO.findDistLevel(userAgencyID);
	}

	@Override
	public Table getAuditRuleTableDefine() {
		
		Table table=new Table();
		table.setTableID("BGT_T_CHECKDEF");
		table.setAppID("BGT");
		table.setTableDBName("BGT_T_CHECKDEF");
		table.setTableName("审核规则列表");

		AuditRuleCheckDefTableHelper.setColumns(table);
		return table;
	}
	
	@Override
	public List<Map<String, Object>> getDataAuditRuleList(
			Map<String, Object> param) throws ServiceException {
		return auditRuleDefDAO.getDataAuditRuleList(param);
	}

	@Override
	public int getDataAuditRuleList4count(Map<String, Object> param)
			throws ServiceException {
		return auditRuleDefDAO.getDataAuditRuleList4count(param);
	}

	@Override
	public int findRelationBusiness(String checkIds) {
		 return auditRuleDefDAO.findRelationBusiness(checkIds);
	}
	
	@Override
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public void batchDelAuditRule(Table table) throws Exception {
		settingGridService.saveData(table);//保存数据
		
	}
	
    /**
     * 得到审核验证数据
     */
	@Override
	public List<Map<String, Object>> getAuditRuleCheckList(Map<String, String> map) {
		 return auditRuleDefDAO.getAuditRuleCheckList(map);
	}
	
	@Override
	public Table getSysfnTabDefine() {
		Table table=new Grid();
		table.setTableID("FASP_V_PUBSYSFNTAB");
		table.setAppID("BGT");
		table.setTableDBName("FASP_V_PUBSYSFNTAB");
		table.setTableName("系统函数");
		AuditRuleSysfnTableHelper.setColumns(table);
		return table;
	}

}
