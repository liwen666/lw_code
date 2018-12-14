package commons.setting.dataaudit.auditrule.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.grid.settinggrid.service.ISettingGridService;
import com.tjhq.commons.inputcomponent.po.Condition;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.po.TreePO;
import com.tjhq.commons.setting.dataaudit.auditrule.dao.AuditRuleTypeDAO;
import com.tjhq.commons.setting.dataaudit.auditrule.service.IAuditRuleTypeService;
import com.tjhq.commons.setting.dataaudit.auditrule.util.AuditRuleCheckTypeTableHelper;
import com.tjhq.commons.utils.StringUtil;
import com.tjhq.commons.utils.UserInfo;
import com.tjhq.commons.utils.UserUtil;


/**
* @ClassName: AuditRuleTypeService
* @Description: 审核类型 审核分类 服务实现类
* @author xiehongtao
* @date 2017-6-27 上午9:46:21
*
 */
@Service
public class AuditRuleTypeService implements IAuditRuleTypeService {

	/**
	 * 表格设置服务对象
	 */
	@Resource
	private ISettingGridService settingGridService;
	
	/**
	 * 审核规则定义数据库操作类
	 */
	@Resource
	private AuditRuleTypeDAO auditRuleTypeDAO;
	
	
	@Override
	public Table getCheckTypeTableDefine() {
		
		Table table=new Table();
		
		table.setTableID("BGT_T_CHECKSORT");
		table.setAppID("BGT");
		table.setTableDBName("BGT_T_CHECKSORT");
		table.setTableName("审核类型");

		AuditRuleCheckTypeTableHelper.setColumns(table);

    	UserInfo user = UserUtil.getUserInfo();
 
    	try {
			return settingGridService.initStructure(table,user.getGuid());
		} catch (ServiceException e) {
			e.printStackTrace();
			return table;
		}
	}

	@Override
	public Object getCheckTypeTableData(String grid) {

    	Grid data = null;
    	//解析列定义信息
		try {
			data = (new ObjectMapper()).readValue(grid,Grid.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
        
		//设置列信息
    	AuditRuleCheckTypeTableHelper.setColumns(data);
    	
    	//检查条件
    	List<Condition> conditions=new ArrayList<Condition>(); 
    	
    	//排序
    	data.getExtProperties().put("sortSQL", "to_number(lvlid)");
    	
    	String checkSortId_str=(String)data.getExtProperties().get("checkSortId");
    	
  	   	Condition appId_contion=new Condition();
  	   	
		appId_contion.setDataType(DataType.STRING);
		appId_contion.setColumnID("APPID");
		appId_contion.setColumnDbName("APPID");
		appId_contion.setOperator("=");
		appId_contion.setQueryValue(data.getExtProperties().get("appId")+"");
		conditions.add(appId_contion); 
       
		if (!StringUtil.isEmpty(checkSortId_str)) {
			Condition checkSortId = new Condition();
			checkSortId.setDataType(DataType.STRING);
			checkSortId.setColumnID("SUPERID");
			checkSortId.setColumnDbName("SUPERID");
			checkSortId.setOperator("=");
			checkSortId.setQueryValue(checkSortId_str);
			conditions.add(checkSortId);
		} else {// 如果是null
			Condition checkSortId = new Condition();
			checkSortId.setDataType(DataType.STRING);
			checkSortId.setColumnID("SUPERID");
			checkSortId.setColumnDbName("SUPERID");
			checkSortId.setExpression(AuditRuleCheckTypeTableHelper.getNULLExpression("SUPERID"));
			conditions.add(checkSortId);
		}
       
		data.setQueryPanelParamList(conditions);

		try {
			return settingGridService.getData(data);
		} catch (ServiceException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public Object delCheckType(String grid) {

    	Grid table = null;
      	try {
			table = (new ObjectMapper()).readValue(grid, Grid.class);
			
			AuditRuleCheckTypeTableHelper.setColumns(table);
			
			StringBuffer checkSortIds = new StringBuffer("");
			
			List<Map<String, Object>>  delrow = table.getDeleteValues() ;
			
			//组合要删除的Ids
			for(Map<String,Object> dataMap:delrow){
				Object id=dataMap.get("CHECKSORTID");
				if(null!=id && !"".equals(id.toString().trim())){
					if("".equals(checkSortIds.toString())){
						checkSortIds.append("'").append(id.toString().trim()).append("'");
					}else{
						checkSortIds.append(",'").append(id.toString().trim()).append("'");
					}
				}
			}
			
			if(!"".equals(checkSortIds.toString())){
				
				Map<String,String> paraMap = new HashMap<String,String>();
				
				paraMap.put("checkSortIds",checkSortIds.toString());
				
				if(auditRuleTypeDAO.getCheckDefTypeNum(paraMap)>0){
					return new ResultPO("false","有分类被引用,无法删除");
				}
				
				auditRuleTypeDAO.delCheckType(paraMap);
				
				String superId = (null == table.getExtProperties().get("checkSortId") ? "": table.getExtProperties().get("checkSortId").toString());

				if (null != superId && !"".equals(superId)) {
					paraMap.put("checkSortId", superId);
					auditRuleTypeDAO.updateEndFlag(paraMap);
				}
				
			}
			
			return new ResultPO("操作成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultPO("false","操作失败");
		} 
      	
	}

	@Override
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public Object saveCheckType(String grid) {

    	Grid table =null;
      	try {
			table = (new ObjectMapper()).readValue(grid, Grid.class);
			AuditRuleCheckTypeTableHelper.setColumns(table);
			String appID=(String)table.getExtProperties().get("appId");
			//判断序号的唯一性
			ResultPO msg = new ResultPO("操作成功");
			if(table.getInsertValues().size()>0){//新增
				for(Map<String,Object> m :table.getInsertValues()){
					m.put("APPID", appID);
				  if(checkLvUnique(m)>0){
					  msg.setSuccessFlag(false);
					  msg.setErrMsg("序号 "+m.get("LVLID")+" 重复");
					  break;
				  }
				  String sortName=(String)m.get("CHECKSORTNAME");
				  if(null!=sortName && !"".equals(sortName)){
					  if(checkAuditSortNameUnique(m)>0){
						  msg.setSuccessFlag(false);
						  msg.setErrMsg("名称  "+m.get("CHECKSORTNAME")+" 重复");
						  break;
					  }
				  }
				} 
			}
			
			if(!msg.isSuccessFlag()){
				return msg;
			}
			
			//更新数据判断序号的唯一性
			if(table.getUpdateValues().size()>0){
				for(Map<String,Object> m :table.getUpdateValues()){
					 Object lvid=m.get("LVLID");
					 if(lvid!=null && !"".equals(lvid)){
							m.put("APPID", appID);
						  if(checkLvUnique(m)>0){
							  msg.setSuccessFlag(false);
							  msg.setErrMsg("序号 "+m.get("LVLID")+" 重复");							  
							  break;
						  }
					 }
					  String sortName=(String)m.get("CHECKSORTNAME");
					  if(null!=sortName && !"".equals(sortName)){
						  m.put("APPID", appID);
						  if(checkAuditSortNameUnique(m)>0){
							  msg.setSuccessFlag(false);
							  msg.setErrMsg("名称  "+m.get("CHECKSORTNAME")+" 重复");
							  break;
						  }
					  }
					} 
			}
			
			if(!msg.isSuccessFlag()){
				return msg;
			}
			
			String superId="";
			if(table.getInsertValues().size()>0){
				superId=(String)table.getInsertValues().get(0).get("SUPERID");
			}
			if(!"".equals(superId)){//验证父亲是否被引用，如果有引用不添加
				if(checkRelationBySuperId(superId)>0){
					return new ResultPO("false","该分类已使用,不允许添加子分类"); 
				}
			}
			
			Map<String, String> parm=new HashMap<String,String>();

			settingGridService.saveData(table);
			
			if(table.getInsertValues().size()>0 || table.getUpdateValues().size()>0){
				Map<String, Object> ele=new HashMap<String,Object>();
				if(table.getInsertValues().size()>0){
					ele=table.getInsertValues().get(0);
				}else{
					ele=table.getUpdateValues().get(0);
				}
		
				if(null!=ele.get("SUPERID")){
					parm.put("checkSortId", ele.get("SUPERID").toString());
					parm.put("endFlag","0");
		    		auditRuleTypeDAO.updateEndFlag(parm);
				}
			}
			
			return new ResultPO("操作成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultPO("false",""); 
		} 
    
	}
	
	@Override
	public int checkLvUnique(Map<String, Object> m) throws Exception {
		 return auditRuleTypeDAO.checkLvUnique(m);
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
			String superID=auditRuleTypeDAO.findAuditSortSuperID(paraMap);
			paraMap.put("SUPERID",superID);
		}
		
		return auditRuleTypeDAO.findAuditSortNameCount(paraMap);
	}
	
	/**
	 * 验证父亲节点是否被引用
	 */
	@Override
	public int checkRelationBySuperId(String superId) {
		return auditRuleTypeDAO.checkRelationBySuperId(superId);
	}

	@Override
	public List<TreePO> getCheckTypeTree(String appId) {

		Map<String, String> paraMap = new HashMap<String, String>();
		
		paraMap.put("appId", appId);
		
		return auditRuleTypeDAO.getNewAuditCategoryTree(paraMap);

	}
	
}