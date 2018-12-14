package commons.setting.dataaudit.rule.web;

import gov.mof.fasp2.ca.user.dto.UserDTO;
import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjhq.commons.Constants;
import com.tjhq.commons.dict.dao.DictTAppregisterMapper;
import com.tjhq.commons.dict.external.po.DictTAppregisterPO;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.po.DictTModelcodePO;
import com.tjhq.commons.dict.external.po.DictTSuitPO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.dict.external.service.IDictTModelcodeService;
import com.tjhq.commons.dict.external.service.IDictTSuitService;
import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.grid.settinggrid.service.ISettingGridService;
import com.tjhq.commons.inputcomponent.po.Condition;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.po.TreePO;
import com.tjhq.commons.setting.dataaudit.rule.exception.AuditRuleException;
import com.tjhq.commons.setting.dataaudit.rule.po.TreeNodePO;
import com.tjhq.commons.setting.dataaudit.rule.service.IAuditRuleService;
import com.tjhq.commons.setting.dataaudit.rule.util.CheckDefTableHelper;
import com.tjhq.commons.setting.dataaudit.rule.util.CheckSortTableHelper;
import com.tjhq.commons.setting.dataaudit.rule.util.ReturnMsg;
import com.tjhq.commons.setting.dataaudit.rule.util.SysfnTabHelper;
import com.tjhq.commons.setting.external.service.IAuditRuleOutService;
import com.tjhq.commons.setting.formula.service.IFormulaService;
import com.tjhq.commons.setting.input.po.TreeNode;
import com.tjhq.commons.setting.input.web.ConverTables;
import com.tjhq.commons.utils.StringUtil;

/**
 * 
 * @desc:审核规则定义
 * @author wxn
 * @date:2014-10-11下午3:05:45
 */
@Controller
@RequestMapping(value = "/commons/setting/auditrule")
public class AuditRuleController {
	//jsos返回操作的结果
	public static String SUCCESS="操作成功";
	public static String ERROR="操作失败";
	private static String AUDITINSERT = "0"; // 审核新增
	private static String AUDITOUT = "1"; // 表间审核
	private static String AUDITCUSTOM = "2"; // 自定义
	@Resource
	private IAuditRuleService auditRuleService;
	@Resource
	private ISettingGridService settingGridService;
	@Resource
	private IDictTSuitService dictTSuitService;
	@Resource
	private IDictTModelService dictTModelService; // 表接口
	@Resource
	private IDictTFactorService dictTFactorService; // 列的接口
	@Resource
	private IFormulaService formulaService;
	@Resource
	private IAuditRuleOutService auditRuleOutService;
	@Resource
	private IDictTModelcodeService modelCodeService;
	@Resource
	private DictTAppregisterMapper dictTAppregisterMapper;

	
	@RequestMapping(value = "auditRulePage")
	public String auditRulePage(HttpServletRequest request,
			@RequestParam(value = "appId") String appId) throws Exception {
		request.setAttribute("appId", appId);
		request.setAttribute("userBgtLevel",auditRuleService.findBudgetLevel(SecureUtil.getCurrentUser().getUpagencyid()));
	 //List list = auditRuleService.getBusiType(appId);
		///request.setAttribute("busiType", (new ObjectMapper()).writeValueAsString(list));
	
		return "commons/setting/dataaudit/rule/auditRuleDefine";
	}
	
	/**
	 * 
	 * 查询树形结构
	 * @param adapterType
	 * @param appId
	 * @param checkType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getAuditCategoryTree")
	@ResponseBody
//	public List<Map> getAuditCategoryTree(@RequestParam(value = "appId") String appId)throws Exception {
//		return auditRuleService.getAuditCategoryTree(appId);
//	}
	public List<Map> getAuditCategoryTree(HttpServletRequest req)throws Exception {
		System.out.println(1);
	return auditRuleService.getAuditCategoryTree("BGT");
    }
	@RequestMapping(value = "getNewAuditCategoryTree")
	@ResponseBody
	public Object getNewAuditCategoryTree(HttpServletRequest req)throws Exception {
		List<TreePO> nodes=auditRuleService.getNewAuditCategoryTree(req.getParameter("appID"));
		TreeNodePO po = new TreeNodePO();
		po.setId("0");
		po.setName("全部");
		po.setOpen(true);
		nodes.add(po);
	return nodes;
    }

	/**
	 * 查询审核规则列表数据
	 * @param adapterType
	 * @param appId
	 * @param checkType
	 * @return
	 * @throws Exception
	 */
	/**
	@RequestMapping(value = "getAuditRuleDef")
	@ResponseBody
	public Object getAuditRuleDef(@RequestParam(value = "adapterType") int adapterType,@RequestParam(value = "appId") String appId,@RequestParam(value = "checkType") String checkType)throws Exception {
	List records = null;
	Map<String, String> map = new HashMap<String, String>();
	map.put("appId", appId);
	records = auditRuleService.getAuditRuleDef(map);
	return Grid.getData(adapterType, records);
	}**/
	/**
	 * 得到审核规则定义表的表头信息
	 * 
	 * @return
	 */
	
	@RequestMapping(value = "getAuditRuleTableDefine")
	@ResponseBody
	public Table getAuditRuleTableDefine()throws Exception{
    	Table grid = auditRuleService.getAuditRuleTableDefine();
    	UserDTO user = SecureUtil.getCurrentUser();
    	return settingGridService.initStructure(grid,user.getGuid());
	}
	/**
	 * 得到审核类型表的列定义
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getCheckTypeTableDef")
	@ResponseBody
	public Table getCheckTypeTableDef()throws Exception{
    	Table grid = auditRuleService.getCheckTypeTableDefine();
    	UserDTO user = SecureUtil.getCurrentUser();
    	return settingGridService.initStructure(grid,user.getGuid());
	}
	/**
	 * 取业务数据
	 */
    @RequestMapping(value = "getAuditRuleDef")
	@ResponseBody
	public Object getAuditRuleDef(String grid) throws Exception{
    	Grid data = (Grid)(new ObjectMapper()).readValue(grid,Grid.class);
    	//添加sqlwhere
    	CheckDefTableHelper.setColumns(data);
    	Map<String,Object> paraMap=new HashMap<String,Object>();
    	paraMap.put("appID",data.getExtProperties().get("APPID"));
    	Boolean pagination=(Boolean)data.getExtProperties().get("pagination");
    	paraMap.put("pagination",data.getExtProperties().get("pagination"));
    	if(pagination){
    		paraMap.put("startRow", data.getPageInfo().getStart());
    		paraMap.put("endRow",(data.getPageInfo().getStart()+data.getPageInfo().getLimit()));
    	}
    
    	//List<Condition> conditions=new ArrayList<Condition>(); 
    	
    	//审核分类
    	//Map<String,String> paraMap=new HashMap<String,String>();
       String checkSortId_str=(String)data.getExtProperties().get("checkSortId");
       if(!StringUtil.isEmpty(checkSortId_str)){
    	   paraMap.put("checkSortId", checkSortId_str);
    	   /**
    	   Condition checkSortId=new Condition();
       	checkSortId.setDataType(DataType.STRING);
       	checkSortId.setColumnID("CHECKSORTID");
       	checkSortId.setColumnDbName("CHECKSORTID");
       	checkSortId.setOperator("IN");
       	paraMap.put("CHECKSORTID", checkSortId_str);
       	checkSortId.setExpression(CheckDefTableHelper.getCondition4SortId(paraMap).toString());
       	conditions.add(checkSortId);**/
       }
       //审核分类的appid
       /**
       Condition appIdCondition=new Condition();
       appIdCondition.setDataType(DataType.STRING);
       appIdCondition.setColumnID("APPID");
       appIdCondition.setColumnDbName("APPID");
       appIdCondition.setOperator("=");
       appIdCondition.setQueryValue(data.getExtProperties().get("APPID")+"");
       conditions.add(appIdCondition);
       **/
       
   	//审核类型 0表内 1表间审核
       String checkType_str=(String)data.getExtProperties().get("checkType");
       if(!StringUtil.isEmpty(checkType_str)){
    	   paraMap.put("checkType", checkType_str);
    	   /**
    	   Condition checkType=new Condition();
    	   checkType.setDataType(DataType.STRING);
    	   checkType.setColumnID("CHECKTYPE");
    	   checkType.setColumnDbName("CHECKTYPE");
    	   checkType.setOperator("=");
    	   checkType.setQueryValue(checkType_str);
           conditions.add(checkType);**/
       }
       String defName_str=(String)data.getExtProperties().get("defName");
       if(!StringUtil.isEmpty(defName_str)){
    	   paraMap.put("defName", defName_str);
    	   }
     	//data.setQueryPanelParamList(conditions);
       
       List<Map<String,Object>> dataList=auditRuleService.getDataAuditRuleList(paraMap);
   	if(dataList.size()>0){
		// 取查询数据和列
		data.getPageInfo().setColumns(new ArrayList<String>(dataList.get(0).keySet()));
		List<Object> datas = new ArrayList<Object>();
		List<Object> rowData = null;
		for (Map<String, Object> mapedRecord : dataList) {
			rowData = new ArrayList<Object>();

			// 根据列定义拷贝数据
			for (String column : data.getPageInfo().getColumns()) {
				Object rawValue = mapedRecord.get(column);
				if (rawValue == null) {
					rowData.add(null);
					continue;
				}else{
					rowData.add(rawValue);
				}
				}
			datas.add(rowData);
			}
		
		data.getPageInfo().setDataList(datas);
		
	}
   	   data.getPageInfo().setTotal(auditRuleService.getDataAuditRuleList4count(paraMap));
    	return data.getPageInfo();//settingGridService.getData(data);
		
		
	}
	/**
	 * 得到审核类型表的列定义
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getAuditRuleDefCheckTable")
	@ResponseBody
	public Object getAuditRuleDefCheckTable()throws Exception{
		Table table=new Grid();
		table.setTableID("BGT_T_CHECKSORT");
		table.setAppID("BGT");
		table.setTableDBName("BGT_T_CHECKSORT");
		table.setTableName("审核定义检查");

		CheckDefTableHelper.setColumns4getAuditRuleDefCheck((Grid)table);
		
    	UserDTO user = SecureUtil.getCurrentUser();
    	return settingGridService.initStructure(table,user.getGuid());
	}
    /**
	 * 审核定义检查数据
	 */
    @RequestMapping(value = "getAuditRuleDefCheck")
	@ResponseBody
	public Object getAuditRuleDefCheck(String grid) throws Exception{
    	Grid data = (Grid)(new ObjectMapper()).readValue(grid,Grid.class);
    	//添加sqlwhere
    	CheckDefTableHelper.setColumns4getAuditRuleDefCheck(data);
    	List<Condition> conditions=new ArrayList<Condition>(); 
    	List<String> columsList = new ArrayList<String>();
    	//审核分类
    	Map<String,String> paraMap=new HashMap<String,String>();
       String checkSortId_str=(String)data.getExtProperties().get("checkSortId");
       String APPID_str=(String)data.getExtProperties().get("APPID");
       if(!StringUtil.isEmpty(checkSortId_str)){
    	   paraMap.put("checkSortId", checkSortId_str);
       }
       if(!StringUtil.isEmpty(APPID_str)){
    	   paraMap.put("APPID", APPID_str);
       }
       
        List<Map<String,Object>> dataList=auditRuleService.getAuditRuleCheckList(paraMap);
        List<Object> resultDataList=new ArrayList<Object>();
        for(Map<String,Object> ele:dataList){
			String checkSql = (String)ele.get("CHECKSQL");
			try {
				checkSql=checkSql.replaceAll("@WHERE@", " and 1=2");
				// 验证CHECKSQL 是否错误
				formulaService.callErrorMessage(checkSql); 
				//auditDataService.verifyCheckSql(checkSql);
			} catch (Exception e) {
			   resultDataList.add(new Object[]{ele.get("CHECKID"),ele.get("SERID"),ele.get("DEFNAME"),ele.get("CHECKTYPE")});			
			}
        	
        	
        }
        
        columsList.add("CHECKID");
        columsList.add("SERID");
        columsList.add("DEFNAME");
        columsList.add("CHECKTYPE");
        data.getPageInfo().setDataList(resultDataList);
       data.getPageInfo().setColumns(columsList);
        data.getPageInfo().setTotal(resultDataList.size());
    	return data.getPageInfo();
		
		
	}

	/**
	 * 删除审核规则定义
	 */
    @RequestMapping(value = "deleteCheckDef")
	@ResponseBody
    public Object deleteCheckDef(String grid){
    	Table table;
		try {
			table = (Table) (new ObjectMapper()).readValue(grid, Table.class);
			CheckDefTableHelper.setColumns(table);//列信息
			//校验数据有没有被业务所引用 如果被引用则不能删除
			List<Map<String, Object>>  delrow=table.getDeleteValues();
			String checkIds="";
			for(Map<String, Object> ele:delrow){
				if("".equals(checkIds)){
					checkIds+="'"+ele.get("CHECKID")+"'";
				}else{
					checkIds+=",'"+ele.get("CHECKID")+"'";
				}
			}
			if(!"".equals(checkIds)){//检查是否被业务引用了。如果引用了就不让删
				int a=auditRuleService.findRelationBusiness(checkIds);
				if(a>0){
					 return toJson(ReturnMsg.getMessage(false, "所选规则有业务模块引用无法删除!"));//ReturnMsg.getMessage(false, "所选规则有业务模块引用无法删除!");
				}
				
			}
			
			
			auditRuleService.batchDelAuditRule(table);//保存数据
			return toJson(ReturnMsg.getMessage(true, ReturnMsg.OK));
		} catch (Exception e) {
			e.printStackTrace();
			 return toJson(ReturnMsg.getMessage(false, ReturnMsg.FALSE));
		}
		

    	
    }
    public String toJson(Object o){
    	try {
			return (new ObjectMapper()).writeValueAsString(o);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return "{}";
    }
    
    /**
	 * 取业务数据
	 */
    @RequestMapping(value = "getCheckTypeData")
	@ResponseBody
	public Object getCheckTypeData(String grid) throws Exception{
    	Grid data = (Grid)(new ObjectMapper()).readValue(grid,Grid.class);
    	//添加sqlwhere
    	CheckSortTableHelper.setColumns(data);
    
    	List<Condition> conditions=new ArrayList<Condition>(); 
    
    	//审核分类
    	Map<String,String> paraMap=new HashMap<String,String>();
    	data.getExtProperties().put("sortSQL", "to_number(lvlid)");
       String checkSortId_str=(String)data.getExtProperties().get("checkSortId");
  	   Condition appId_contion=new Condition();
		 appId_contion.setDataType(DataType.STRING);
		appId_contion.setColumnID("APPID");
		appId_contion.setColumnDbName("APPID");
		appId_contion.setOperator("=");
		appId_contion.setQueryValue(data.getExtProperties().get("appId")+"");
		conditions.add(appId_contion); 
       
       if(!StringUtil.isEmpty(checkSortId_str)){
	    	Condition checkSortId=new Condition();
	       	checkSortId.setDataType(DataType.STRING);
	       	checkSortId.setColumnID("SUPERID");
	       	checkSortId.setColumnDbName("SUPERID");
	       	checkSortId.setOperator("=");
	       	checkSortId.setQueryValue(checkSortId_str);
	       	conditions.add(checkSortId);
	       }else{//如果是null 
	    	 Condition checkSortId=new Condition();
	       	checkSortId.setDataType(DataType.STRING);
	       	checkSortId.setColumnID("SUPERID");
	       	checkSortId.setColumnDbName("SUPERID");
	       	checkSortId.setExpression(CheckSortTableHelper.getNULLExpression("SUPERID"));
	       	conditions.add(checkSortId);   
    	   
       }
     	data.setQueryPanelParamList(conditions);
    	return settingGridService.getData(data);
		
		
	}
    /**
     * 删除 审核分类
     * @param grid
     * @return
     */
    @RequestMapping(value = "delCheckSort")
	@ResponseBody
    public Object delCheckSort(String grid){
    	Grid table = null;
      	try {
			table = (Grid) (new ObjectMapper()).readValue(grid, Grid.class);
			CheckSortTableHelper.setColumns(table);
			String checkSortIds="";
			String superId="";
			superId=(null==table.getExtProperties().get("checkSortId")?"":table.getExtProperties().get("checkSortId").toString());
			
//			if(table.getDeleteValues().size()>0){//获得超类id
//				superId=(String)table.getDeleteValues().get(0).get("SUPERID");
//				
//			}
			//组合要删除的Ids
			for(Map<String,Object> dataMap:table.getDeleteValues()){
				Object id=dataMap.get("CHECKSORTID");
				if(null!=id && !"".equals(id.toString().trim())){
					if("".equals(checkSortIds)){
						checkSortIds="'"+id.toString().trim()+"'";
					}else{
						checkSortIds=checkSortIds+",'"+id.toString().trim()+"'";
					}
				}
				
			}
			if(!"".equals(checkSortIds)){
				Map<String,String> paraMap = new HashMap<String,String>();
				paraMap.put("checkSortIds",checkSortIds);
				if(auditRuleService.getCheckDefTypeNum(paraMap)>0){
					return ReturnMsg.getMessage(false,"有分类被引用,无法删除");
					
				}
				auditRuleService.delCheckSort(table);
				//settingGridService.saveData(table);
			}
			
			return ReturnMsg.getMessage(true, ReturnMsg.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnMsg.getMessage(false, ReturnMsg.FALSE);
		} 
      	
    }
    /**
     * 修改 审核分类
     * @param grid
     * @return
     */
    @RequestMapping(value = "saveCheckSortData")
	@ResponseBody
    public Object saveCheckSortData(String grid){
    	Grid table =null;
      	try {
			table = (Grid) (new ObjectMapper()).readValue(grid, Grid.class);
			CheckSortTableHelper.setColumns(table);
			String appID=(String)table.getExtProperties().get("appId");//table.getAppID();
			//判断序号的唯一性
			
			ReturnMsg msg =ReturnMsg.getMessage(true,ReturnMsg.OK);
			if(table.getInsertValues().size()>0){//新增
				for(Map<String,Object> m :table.getInsertValues()){
					m.put("APPID", appID);
				  if(auditRuleService.checkLvUnique(m)>0){
					  msg.setFlag(false);
					  msg.setMsg("序号 "+m.get("LVLID")+" 重复");
					  break;
				  }
				  String sortName=(String)m.get("CHECKSORTNAME");
				  if(null!=sortName && !"".equals(sortName)){
					  if(auditRuleService.checkAuditSortNameUnique(m)>0){
						  msg.setFlag(false);
						  msg.setMsg("名称  "+m.get("CHECKSORTNAME")+" 重复");
						  break;
					  }
				  }
					
				} 
				
				
			}
			if(!msg.isFlag()){
				return msg;
			}
			
			//判断序号的唯一性
			if(table.getUpdateValues().size()>0){//更新
				for(Map<String,Object> m :table.getUpdateValues()){
					
					 Object lvid=m.get("LVLID");
					 if(lvid!=null && !"".equals(lvid)){
							m.put("APPID", appID);
						  if(auditRuleService.checkLvUnique(m)>0){
							  msg.setFlag(false);
							  msg.setMsg("序号 "+m.get("LVLID")+" 重复");
							  break;
						  }
					 }
					  String sortName=(String)m.get("CHECKSORTNAME");
					  if(null!=sortName && !"".equals(sortName)){
						  m.put("APPID", appID);
						  if(auditRuleService.checkAuditSortNameUnique(m)>0){
							  msg.setFlag(false);
							  msg.setMsg("名称  "+m.get("CHECKSORTNAME")+" 重复");
							  break;
						  }
					  }
					} 
				
			}
			if(!msg.isFlag()){
				return msg;
			}
			String superId="";
			if(table.getInsertValues().size()>0){
				superId=(String)table.getInsertValues().get(0).get("SUPERID");
				
			}
			if(!"".equals(superId)){//验证父亲是否被引用，如果有引用不添加
				if(auditRuleService.checkRelationBySuperId(superId)>0){
					return ReturnMsg.getMessage(false, "该分类已使用,不允许添加子分类"); 
					
				}
				
			}
			//验证code是否存在 
			auditRuleService.saveCheckSortData(table);
			
			return ReturnMsg.getMessage(true, ReturnMsg.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnMsg.getMessage(false, ReturnMsg.FALSE);
		} 
    }
    /**
     * 根据表ID查询表的列信息
     * @param tableid
     * @param auditTab
     * @return
     * @throws Exception
     */
	@RequestMapping(value = "getColumnTree")
	@ResponseBody
	public Object getColumnTree(@RequestParam(value = "tableid") String tableid,
			@RequestParam(value = "auditTab") String auditTab)throws Exception {
		List<TreeNodePO> dataList=new ArrayList<TreeNodePO>();
		List<DictTFactorPO> list = null;	
		list = dictTFactorService.getDictTFactorsByTableId(tableid);
		wraperToTreeNode(dataList,list);
		return dataList;
	}
    private void wraperToTreeNode(List<TreeNodePO> dataList,
			List<DictTFactorPO> list) {
    	for(DictTFactorPO bo:list){
    		TreeNodePO treeBo = new TreeNodePO();
    		treeBo.setId(bo.getColumnid());
    		treeBo.setName(bo.getName());
    		treeBo.setPId(bo.getSuperid());
    		treeBo.setIsleaf(bo.getIsleaf());
    		treeBo.setCsid(bo.getCsid());
    		dataList.add(treeBo);
    		
    	}
		
	}

	/**
     * 查询已经登记注册的视图数据
     * @param appID
     * @return
     * @throws Exception
     */
	@RequestMapping(value="suitTree")
	@ResponseBody
	public Object getSuitTree(String appID) throws Exception{
		
		if(null==appID || ("").equals(appID)){
			appID = "BGT";
		}
		List<TreeNodePO> treeList = new ArrayList<TreeNodePO> ();
		List<DictTAppregisterPO> apps=dictTAppregisterMapper.getAllDictTAppregister();
		
		for(DictTAppregisterPO appBO :apps){
			appID=appBO.getAppid();
			TreeNodePO tree = new TreeNodePO();
			tree.setId(appBO.getAppid());
			tree.setName(appBO.getAppname());
			tree.setPId("0");
			/**
			tree.setIsLeaf(suit.getIsleaf());
			tree.setLevelNo(suit.getLevelno());
			tree.setAppID(suit.getAppid());
			tree.setIsParent("true");
			**/
			tree.setParent(true);
			//tree.setAuditTab("1");
			tree.setOpen(true);
			treeList.add(tree);
			
		//引用 获取 所有套表及下属表
		List<DictTSuitPO> suitList = dictTSuitService.getDictTSuits(appID, "0", true);
		//suitList.add(tree);
		
		//数据审核视图登记表 暂时不需要 edit by wxn 20150821
		/**
		List<TreeNodePO> viewList = auditRuleService.getRegViewTable(appID);
		if(viewList.size()>0){
			TreeNodePO vRoot=new TreeNodePO();
			vRoot.setId("REGVIEW");
			vRoot.setCode("REGVIEW");
			vRoot.setName("审核视图登记");
			vRoot.setIsleaf("0");
			vRoot.setPId(appID);
			vRoot.setAuditTab("2");
			vRoot.setTableType("1");
			viewList.add(vRoot);
			
		}**/
        
		treeList = treeData(treeList,suitList);
		    /**  edit by wxn 20150821
			if(viewList.size()>0){
				treeList.addAll(viewList);
			}**/
		}
		return treeList;
	}  
	private List<TreeNodePO> treeData(List<TreeNodePO> treeList,List <DictTSuitPO>list) throws Exception{	
		for(DictTSuitPO suit : list){
			TreeNodePO tree = new TreeNodePO();
			tree.setId(suit.getSuitid());
			tree.setName(suit.getSuitname());
			tree.setPId(suit.getAppid());
			/**
			tree.setIsLeaf(suit.getIsleaf());
			tree.setLevelNo(suit.getLevelno());
			tree.setAppID(suit.getAppid());
			tree.setIsParent("true");
			**/
			tree.setParent(true);
			tree.setAuditTab("1");
			//tree.setOpen(true);
			
			//物理表
			List<DictTModelPO> modelList = suit.getDictTModelList();
		    if(ConverTables.isNotNullList(modelList)){
		    	treeList.add(tree);
		    }
			if(ConverTables.isNotNullList(modelList)){
			    for(DictTModelPO model:modelList){
			    	TreeNodePO child = new TreeNodePO();
					child.setId(model.getTableid());
					child.setName(model.getName());
					child.setPId(model.getSuitid());
					/**
					child.setIsLeaf(suit.getIsleaf());
					child.setDealType(model.getDealtype()); //表处理类型
					child.setDealName(model.getDealName());
					child.setTableType(model.getTabletype()); //表类型
					**/
					child.setAppID(model.getAppid());
					child.setParent(true);
					child.setAuditTab("1"); //来源 DICT_T_MODEL
				
					treeList.add(child);
				}	
			}
			List<DictTSuitPO> suitList = suit.getDictTSuitList();
			if(ConverTables.isNotNullList(suitList)){					
				treeData(treeList,suitList);					
			}
		}
		return treeList;
	 }
	@RequestMapping(value = "checkFormula")
	@ResponseBody
	public String check(@RequestParam(value = "formids") String formids){
		Map formMap;
		try {
			formMap = (Map) (new ObjectMapper()).readValue(formids, Map.class);
		} catch (Exception e1) {
			e1.printStackTrace();
			 return "{\"flag\":\"true\"}";
		} 
		Map<String, Object> mapL = new HashMap<String, Object>();
		Map<String, Object> mapR = new HashMap<String, Object>();
		Map<String, Object> mapExp = new HashMap<String, Object>();
		String LtableName = "";
		String RtableName = "";

		if(formMap.get("CHECKTYPE").toString().equals(AUDITCUSTOM)){
			try {
				String checkSql=formMap.get("CHECKSQL").toString();
				if(checkSql.indexOf("@WHERE@")>0){
					formMap.put("CHECKSQL", checkSql.replaceAll("@WHERE@", ""));
					
				}
				// 验证CHECKSQL 是否错误
				formulaService.callErrorMessage(checkSql.replaceAll("@WHERE@", "")); 
				return "{\"flag\":\"true\"}";
				//auditRuleService.verifyCheckSql(checkSql);
			} catch (Exception e) {
				e.printStackTrace();
				return "{\"flag\":\"false\",\"error\":\""+e.getMessage().replace("\"", "'").replace("\n", "")+"\"}";
			}
			
			
		}
		List<DictTFactorPO> factorsL = new ArrayList<DictTFactorPO>();
		List<DictTFactorPO> factorsR = new ArrayList<DictTFactorPO>();
		// 表
		String l_tableID = (String) formMap.get("LTABLEID");
		String r_tableID = (String) formMap.get("RTABLEID");
		// 列
		String l_col = (String) formMap.get("LCOL");
		String r_col = (String) formMap.get("RCOL");
		// 分组
		String l_group = (String) formMap.get("LGROUP");
		String r_group = (String) formMap.get("RGROUP");
		// 条件
		String l_where = (String) formMap.get("LWHERE");
		String r_where = (String) formMap.get("RWHERE");
		//1、来源DICT_T_MODEL 2、来源 CHECK_T_REGVIEW
		String lauditTab = (String) formMap.get("LAUDITTAB");
		String rauditTab = (String) formMap.get("RAUDITTAB");

		if (formMap.get("CHECKTYPE").toString().equals(AUDITOUT)){// 表间审核

			LtableName = dictTModelService.getDictTModelByID(l_tableID,false).getDbtablename();
			factorsL = dictTFactorService.getDictTFactorByTableidAndType(l_tableID, "1");
			RtableName = dictTModelService.getDictTModelByID(r_tableID,false).getDbtablename();
			factorsR = dictTFactorService.getDictTFactorByTableidAndType(r_tableID, "1");

			
			String Lcol="";
			String Rcol="";
			String Lwhere="";
			String Rwhere="";
			try {
				Lcol = trans(l_col, l_col, factorsL,LtableName);
				Rcol = trans(r_col, r_col, factorsR,RtableName);
				
				Lwhere = trans(l_where, l_where, factorsL,LtableName);
				Rwhere = trans(r_where, r_where, factorsR,RtableName);
			} catch (Exception e1) {
				
				e1.printStackTrace();
				return "{\"flag\":\"verify\",\"error\":\""+e1.getMessage()+"\"}";
			} 

			String Lgroup = transd(l_group, l_group, factorsL); // 分组
			Lgroup = Lgroup.substring(0, Lgroup.trim().length() - 1);

			String Rgroup = transd(r_group, r_group, factorsR);
			Rgroup = Rgroup.substring(0, Rgroup.trim().length() - 1);
			//左表
			mapL.put("TABLENAME", LtableName);
			mapL.put("WHERE", Lwhere);
			mapL.put("GROUP", Lgroup);
			//右表
			mapR.put("TABLENAME", RtableName);
			mapR.put("WHERE", Rwhere);
			mapR.put("GROUP", Rgroup);

			try {
				if (Lgroup.split(",").length != Rgroup.split(",").length)
					return "{\"flag\":\"group\"}";

				// 左条件 与 分组
				auditRuleService.check(mapL);
				// 右条件 与 分组
				auditRuleService.check(mapR);

				mapExp.put("TABLENAME", LtableName);// 左表达式
				mapExp.put("COL", Lcol); 
				auditRuleService.checkNum(mapExp);
				
				mapExp.put("TABLENAME", RtableName);// 右表达式
				mapExp.put("COL", Rcol);
				auditRuleService.checkNum(mapExp);
				mapExp.put("factorsL", factorsL);
				mapExp.put("factorsR", factorsR);
				mapExp.put("LCOL", Lcol);
				mapExp.put("RCOL", Rcol);
				mapExp.put("RELATYPE", formMap.get("RELATYPE"));
				mapExp.put("ERRORDEF", formMap.get("ERRORDEF"));
				mapExp.put("LWHERE", Lwhere);
				mapExp.put("LGROUP", Lgroup);
				mapExp.put("RWHERE", Rwhere);
				mapExp.put("RGROUP", Rgroup);
				mapExp.put("LTABLENAME", LtableName);
				mapExp.put("RTABLENAME", RtableName);
				Map<String,Object> dataMap = auditRuleService.makeSql(mapExp, "1");
				String checkSql=(String)dataMap.get("CHECKSQL");
				try {
					if(checkSql.indexOf("@WHERE@")>0){
						checkSql=checkSql.replaceAll("@WHERE@", "");
						
					}
					// 验证CHECKSQL 是否错误
					formulaService.callErrorMessage(checkSql); 
					//auditRuleService.verifyCheckSql(checkSql);
				} catch (Exception e) {
					e.printStackTrace();
					return "{\"flag\":\"verify\",\"error\":\""+e.getMessage().replace("\"", "'").replace("\n", "")+"\"}";
				}
				return "{\"flag\":\"true\"}";

			} catch (Exception e) {
				e.printStackTrace();
				return "{\"flag\":\"false\"}";
			}
		} else {// 表内审核
			
			LtableName = dictTModelService.getDictTModelByID(l_tableID,false).getDbtablename();
			factorsL = dictTFactorService.getDictTFactorByTableidAndType(l_tableID, "1");
			
			
			String Lcol="";
			String Rcol="";
			String Lwhere="";
			try {
				Lcol = trans(l_col, l_col, factorsL,LtableName);
				Rcol = trans(r_col, r_col, factorsL,LtableName);
				
				Lwhere = trans(l_where, l_where, factorsL,LtableName);
			} catch (Exception e1) {
				e1.printStackTrace();
				return "{\"flag\":\"verify\",\"error\":\""+e1.getMessage()+"\"}";
			}
			String Lgroup = (String) formMap.get("LGROUP");
			String group="";
			if(null!=Lgroup && !"".equals(Lgroup)){
				group = transd(Lgroup, Lgroup, factorsL);
				group = group.substring(0, group.trim().length() - 1);
			}
			
			mapExp.put("TABLENAME", LtableName);
			
			mapL.put("TABLENAME", LtableName);
			mapL.put("WHERE", Lwhere);	
			mapL.put("GROUP", group);
			try {
				auditRuleService.check(mapL);
	
				// 左表达式
				mapExp.put("COL", Lcol);
				auditRuleService.checkNum(mapExp);
				// 右表达式
				mapExp.put("COL", Rcol);
				auditRuleService.checkNum(mapExp);
				mapExp.put("COL", Lcol);
				mapExp.put("factorsL", factorsL);
				mapExp.put("factorsR", factorsR);
				mapExp.put("LCOL", Lcol);
				mapExp.put("RCOL", Rcol);
				mapExp.put("RELATYPE", formMap.get("RELATYPE"));
				mapExp.put("ERRORDEF", formMap.get("ERRORDEF"));
				mapExp.put("LWHERE", Lwhere);
				mapExp.put("LGROUP", group);
				mapExp.put("LWHERE", Lwhere);
				mapExp.put("LTABLENAME", LtableName);
				Map<String,Object> dataMap = auditRuleService.makeSql(mapExp, "0");
				String checkSql=(String)dataMap.get("CHECKSQL");
				try {
					if(checkSql.indexOf("@WHERE@")>0){
						checkSql=checkSql.replaceAll("@WHERE@", "");
						
					}
					// 验证CHECKSQL 是否错误
					formulaService.callErrorMessage(checkSql); 
					//auditRuleService.verifyCheckSql(checkSql);
				} catch (Exception e) {
					e.printStackTrace();
					return "{\"flag\":\"verify\",\"error\":\""+e.getMessage().replace("\"", "'").replace("\n", "")+"\"}";
				}
				
				return "{\"flag\":\"true\"}";
			} catch (Exception e) {
				e.printStackTrace();
				return "{\"flag\":\"false\"}";
			}

		}
	}
	/**
	 * 将 中文列 转换为 dbColumnName 后缀 逗号 (,)
	 * @param finalSqlWhere
	 * @param sqlWhere
	 * @param factors
	 * @return
	 */
	public String transd(String finalSqlWhere, String sqlWhere,List<DictTFactorPO> factors) {

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
	 * 将 中文列 转换为 dbColumnName
	 * @param finalSqlWhere
	 * @param sqlWhere
	 * @param factors
	 * @return
	 * @throws Exception 
	 */
	public String transRef(String sqlWhere,List<DictTFactorPO> factors,String dbTableName) throws Exception {
		
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
			throw new AuditRuleException("列【"+column+"】  不存在");
		}
		sqlWhere = sqlWhere.replace(Constants.COLUMN_START + column + Constants.COLUMN_END, columnTran);
		//sqlWhere = sqlWhere.replace(Constants.COLUMN_START + column + Constants.COLUMN_END, "");
		return transRef(sqlWhere, factors,dbTableName);

	}
	public String trans(String finalSqlWhere, String sqlWhere,List<DictTFactorPO> factors,String dbTableName) throws Exception{
		String tempfinalSqlWhere=transRefTableCol(finalSqlWhere);
		return transRef(tempfinalSqlWhere,factors,dbTableName);
		
	}
	
	/**
	 * 将 中文列 转换为 columnID 不保存 格式
	 * @param finalSqlWhere
	 * @param sqlWhere
	 * @param factors
	 * @return
	 */
	public String transID(String finalSqlWhere, String sqlWhere,List<DictTFactorPO> factors) {
		
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
		finalSqlWhere = finalSqlWhere.replace(Constants.COLUMN_START + column + Constants.COLUMN_END, columnTran);
		
		sqlWhere = sqlWhere.replace(Constants.COLUMN_START + column + Constants.COLUMN_END, "");
		
		return transID(finalSqlWhere, sqlWhere, factors);
	}
	
	/**
	 * 将 中文列 转换为 columnID 保存格式 『columnID』
	 * @param finalSqlWhere
	 * @param sqlWhere
	 * @param factors
	 * @return
	 * @throws Exception 
	 */
	public String transIDFormat(String finalSqlWhere, String sqlWhere,List<DictTFactorPO> factors) throws Exception {
		String tempSql=transRefTableCol(finalSqlWhere);
		//return transIDFormatNew(transRefTableCol(finalSqlWhere),  sqlWhere,factors);
		return transIDFormatNew(tempSql,  tempSql,factors);
	}
	public String transIDFormatNew(String finalSqlWhere, String sqlWhere,List<DictTFactorPO> factors){
		//String tempSqlWhere=finalSqlWhere;
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
	 * 将 columnID 转换为 中文列
	 * @param finalSqlWhere
	 * @param sqlWhere
	 * @param factors
	 * @return
	 * @throws Exception 
	 */
	public String transName(String finalSqlWhere, String sqlWhere,List<DictTFactorPO> factors) throws Exception {
		return transRefIdtoName(transColName(finalSqlWhere,sqlWhere,factors));
		
	}
	//翻译引用列
	private String transRfName(String inputStr) {
		if(null==inputStr || "".equals(inputStr)){
			return inputStr;
		}
		if(inputStr.indexOf(Constants.RF_START)==-1){
			return inputStr;
		}else{
			Pattern p = Pattern.compile("RF\\(.+?\\)\\.\\{.+?\\}");
			Matcher m= p.matcher(inputStr);
			while(m.find()){
				
				
				
			}
			
			
			
			
			return "";
		}
		
		
		
	}

	public String transColName(String finalSqlWhere, String sqlWhere,List<DictTFactorPO> factors){
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
	 * 审核公示定义的保存
	 * 1、通过审核表 获取表 | 列 信息
	 * 2、拼写CHECKSQL
	 * LrowCol,RrowCol,LCol,LGroup,LWhere,RCol,RGroup,RWhere,PubGroup 存储columnID
	 * @param formids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveAuditDataIn")
	@ResponseBody
	public String saveAuditDataIn(@RequestParam(value = "formids") String formids) throws Exception {
		
		String LtableName = "";
		String RtableName = "";
		List<DictTFactorPO> factorsL = new ArrayList<DictTFactorPO>();
		List<DictTFactorPO> factorsR = new ArrayList<DictTFactorPO>();
		String checkSql = "";
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String,Object> formMap = (Map<String,Object>) (new ObjectMapper()).readValue(formids, Map.class);
		formMap.put("USERDISTRICTID", SecureUtil.getCurrentUser().getAdmdiv());
		formMap.put("userAgencyID", SecureUtil.getCurrentUser().getAgency());
		formMap.put("userUpagencyID", SecureUtil.getCurrentUser().getUpagencyid());
		String errorMsg=auditRuleService.checkView(formMap);//验证是否存在物理表
		if(null!=errorMsg && !"".equals(errorMsg)){
			return "{\"flag\":\"verify\",\"error\":\""+errorMsg+"\"}";
		}
		clearSpecialChar(formMap);
		if(formMap.get("CHECKTYPE").toString().equals(AUDITCUSTOM)){//如果是自定义审核
			String checkId=(String)formMap.get("CHECKID");//checkId
			 checkSql=(String)formMap.get("CHECKSQL");//checkSql
				if(checkSql.indexOf("@WHERE@")>0){
					checkSql=checkSql.replaceAll("@WHERE@", "");
					
				}
				try {
					// 验证CHECKSQL 是否错误
					formulaService.callErrorMessage(checkSql); 
					//auditRuleService.verifyCheckSql(checkSql);
				} catch (Exception e) {
					e.printStackTrace();
					return "{\"flag\":\"verify\",\"error\":\""+e.getMessage().replace("\"", "'").replace("\n", "")+"\"}";
				}
				try{
				if(null!=checkId && !"".equals(checkId)){//修改
					auditRuleService.modifyAuditData(formMap);
					
				}else{//新增
					auditRuleService.saveAuditData(formMap);
				}
				}catch(Exception e){
					e.printStackTrace();
					return "{\"flag\":\"false\"}";
				}
			  return "{\"flag\":\"true\"}";
			
		}
		
		//1、来源DICT_T_MODEL 2、来源 CHECK_T_REGVIEW
		String lauditTab = (String) formMap.get("LAUDITTAB");
		String rauditTab = (String) formMap.get("RAUDITTAB");
		try {
			// 表
			String l_tableID = (String) formMap.get("LTABLEID");
			String r_tableID = (String) formMap.get("RTABLEID");
			// 列
			String l_col = (String) formMap.get("LCOL");
			String r_col = (String) formMap.get("RCOL");
			// 分组
			String l_group = (String) formMap.get("LGROUP");
			String r_group = (String) formMap.get("RGROUP");
			// 条件
			String l_where = (String) formMap.get("LWHERE");
			String r_where = (String) formMap.get("RWHERE");
			
			String l_rowCol = (String) formMap.get("LROWCOL");
			String r_rowCol = (String) formMap.get("RROWCOL");

			if (formMap.get("APPROVEFLAG").toString().equals(AUDITINSERT)) { // 新增
				if (formMap.get("CHECKTYPE").toString().equals(AUDITOUT)){ // 表间审核
					
					LtableName = dictTModelService.getDictTModelByID(l_tableID,false).getDbtablename();
					factorsL = dictTFactorService.getDictTFactorByTableidAndType(l_tableID, "1");
					RtableName = dictTModelService.getDictTModelByID(r_tableID,false).getDbtablename();
					factorsR = dictTFactorService.getDictTFactorByTableidAndType(r_tableID, "1");


					map.put("LTABLENAME", LtableName);
					map.put("RTABLENAME", RtableName);
					// 表达式
					String Lcol = trans(l_col, l_col, factorsL,LtableName);
					String Rcol = trans(r_col, r_col, factorsR,RtableName);
					map.put("LCOL", Lcol);
					map.put("RCOL", Rcol);
					// 分组
					String Lgroup = transd(l_group, l_group, factorsL);
					Lgroup = Lgroup.substring(0, Lgroup.length() - 1);

					String Rgroup = transd(r_group, r_group, factorsR);
					Rgroup = Rgroup.substring(0, Rgroup.length() - 1);

					map.put("LGROUP", Lgroup);
					map.put("RGROUP", Rgroup);

					// 条件定义
					if (ConverTables.isNotNull(l_where)) {
						String Lwhere = trans(l_where, l_where, factorsL,LtableName);
						map.put("LWHERE", Lwhere);
					} else {
						map.put("LWHERE", "");
					}
					if (ConverTables.isNotNull(r_where)) {
						String Rwhere = trans(r_where, r_where, factorsR,RtableName);
						map.put("RWHERE", Rwhere);
					} else {
						map.put("RWHERE", "");
					}
					map.put("RELATYPE", formMap.get("RELATYPE"));
					map.put("ERRORDEF", formMap.get("ERRORDEF"));
					map.put("factorsL", factorsL);
					map.put("factorsR", factorsR);
					Map<String,Object> dataMap = auditRuleService.makeSql(map, "1");
					checkSql = (String)dataMap.get("CHECKSQL");
					
					formMap.put("CHECKSQL", checkSql);
					formMap.put("PUBGROUP", dataMap.get("PUBGROUP"));
					
					//转换 名称列 为coumnID | LCOL、RCOL、LWHERE、RWHERE、LGROUP、RGROUP
					formMap.put("LCOL", transIDFormat(l_col, l_col, factorsL));
					formMap.put("LWHERE", transIDFormat(l_where, l_where, factorsL));
					formMap.put("LGROUP",transIDFormat(l_group, l_group, factorsL));
					formMap.put("RCOL", transIDFormat(r_col, r_col, factorsR));
					formMap.put("RWHERE", transIDFormat(r_where, r_where, factorsR));
					formMap.put("RGROUP", transIDFormat(r_group, r_group, factorsR));

					try {
						if(checkSql.indexOf("@WHERE@")>0){
							checkSql=checkSql.replaceAll("@WHERE@", "");
							
						}
						// 验证CHECKSQL 是否错误
						formulaService.callErrorMessage(checkSql); 
						//auditRuleService.verifyCheckSql(checkSql);
					} catch (Exception e) {
						e.printStackTrace();
						return "{\"flag\":\"verify\",\"error\":\""+e.getMessage().replace("\"", "'").replace("\n", "")+"\"}";
					}

				} else {// 表内审核
					LtableName = dictTModelService.getDictTModelByID(l_tableID,false).getDbtablename();
					factorsL = dictTFactorService.getDictTFactorByTableidAndType(l_tableID, "1");
					// 将分组处理为数据库的
					
					String lGroup = "";
					if(null!=l_group && !"".equals(l_group)){
						lGroup=transd(l_group, l_group, factorsL);
						lGroup = lGroup.substring(0,lGroup.trim().length() - 1);
					}

					// 条件定义--中文处理
					if (ConverTables.isNotNull(l_where)) {
						String lwhere = trans(l_where, l_where, factorsL,LtableName);
						map.put("LWHERE", lwhere);
					} else {
						map.put("LWHERE", "");
					}
					// 表达式定义
					String lCOL = trans(l_col, l_col, factorsL,LtableName);
					String rCOL = trans(r_col, r_col, factorsL,LtableName);

					map.put("LTABLENAME", LtableName);
					map.put("RELATYPE", formMap.get("RELATYPE"));
					map.put("ERRORDEF", formMap.get("ERRORDEF"));
					map.put("LGROUP", lGroup);
					map.put("LCOL", lCOL);
					map.put("RCOL", rCOL);
					map.put("RTABLENAME", "");
					map.put("RWHERE", "");
					map.put("RGROUP", "");
					map.put("factorsL", factorsL);
					Map<String,Object> dataMap = auditRuleService.makeSql(map, "0");
					checkSql = (String)dataMap.get("CHECKSQL");
					
					formMap.put("CHECKSQL", checkSql);
					formMap.put("PUBGROUP", dataMap.get("PUBGROUP"));
					
					//转换 名称列 为coumnID | LCOL、RCOL、LWHERE、LGROUP、LROWCOL
					formMap.put("LCOL", transIDFormat(l_col, l_col, factorsL));
					formMap.put("RCOL", transIDFormat(r_col, r_col, factorsL));
					formMap.put("LWHERE", transIDFormat(l_where, l_where, factorsL));
					formMap.put("LGROUP",transIDFormat(l_group, l_group, factorsL));
					//formMap.put("LROWCOL", transID(l_rowCol, l_rowCol, factorsL));	
				}

				try {
					if(checkSql.indexOf("@WHERE@")>0){
						 checkSql=checkSql.replaceAll("@WHERE@", "");
						
					}
					// 验证CHECKSQL 是否错误
					formulaService.callErrorMessage(checkSql); 
					//auditRuleService.verifyCheckSql(checkSql);
				} catch (Exception e) {
					e.printStackTrace();
					return "{\"flag\":\"verify\",\"error\":\""+e.getMessage().replace("\"", "'").replace("\n", "")+"\"}";
				}
				auditRuleService.saveAuditData(formMap);

			} else { // 修改
				if (formMap.get("CHECKTYPE").toString().equals(AUDITOUT)) {// 表间审核
					
					LtableName = dictTModelService.getDictTModelByID(l_tableID,false).getDbtablename();
					factorsL = dictTFactorService.getDictTFactorByTableidAndType(l_tableID, "1");
					RtableName = dictTModelService.getDictTModelByID(r_tableID,false).getDbtablename();
					factorsR = dictTFactorService.getDictTFactorByTableidAndType(r_tableID, "1");

					map.put("LTABLENAME", LtableName);
					map.put("RTABLENAME", RtableName);
					// 表达式
					String Lcol = trans(l_col, l_col, factorsL,LtableName);
					String Rcol = trans(r_col, r_col, factorsR,RtableName);
					map.put("LCOL", Lcol);
					map.put("RCOL", Rcol);
					// 分组
					String Lgroup = transd(l_group, l_group, factorsL);
					Lgroup = Lgroup.substring(0, Lgroup.length() - 1);

					String Rgroup = transd(r_group, r_group, factorsR);
					Rgroup = Rgroup.substring(0, Rgroup.length() - 1);

					map.put("LGROUP", Lgroup);
					map.put("RGROUP", Rgroup);

					// 条件定义
					if (ConverTables.isNotNull(l_where)) {
						String Lwhere = trans(l_where, l_where, factorsL,LtableName);
						map.put("LWHERE", Lwhere);
					} else {
						map.put("LWHERE", "");
					}
					if (ConverTables.isNotNull(r_where)) {
						String Rwhere = trans(r_where, r_where, factorsR,RtableName);
						map.put("RWHERE", Rwhere);
					} else {
						map.put("RWHERE", "");
					}
					map.put("RELATYPE", formMap.get("RELATYPE"));
					map.put("ERRORDEF", formMap.get("ERRORDEF"));
					map.put("factorsL", factorsL);
					map.put("factorsR", factorsR);
					Map<String,Object> dataMap = auditRuleService.makeSql(map, "1");
					checkSql = (String)dataMap.get("CHECKSQL");
					
					formMap.put("CHECKSQL", checkSql);
					formMap.put("PUBGROUP", dataMap.get("PUBGROUP"));
					
					//转换 名称列 为coumnID | LCOL、RCOL、LWHERE、RWHERE、LGROUP、RGROUP
					formMap.put("LCOL", transIDFormat(l_col, l_col, factorsL));
					formMap.put("LWHERE", transIDFormat(l_where, l_where, factorsL));
					formMap.put("LGROUP",transIDFormat(l_group, l_group, factorsL));
					formMap.put("RCOL", transIDFormat(r_col, r_col, factorsR));
					formMap.put("RWHERE", transIDFormat(r_where, r_where, factorsR));
					formMap.put("RGROUP", transIDFormat(r_group, r_group, factorsR));
					
					try {
						// 验证CHECKSQL 是否错误
						if(checkSql.indexOf("@WHERE@")>0){
							 checkSql=checkSql.replaceAll("@WHERE@", "");
							
						}
						formulaService.callErrorMessage(checkSql); 
						//auditRuleService.verifyCheckSql(checkSql);
					} catch (Exception e) {
						e.printStackTrace();
						return "{\"flag\":\"verify\",\"error\":\""+e.getMessage().replace("\"", "'").replace("\n", "")+"\"}";
					}

				} else {// 表内审核
					LtableName = dictTModelService.getDictTModelByID(l_tableID,false).getDbtablename();
					factorsL = dictTFactorService.getDictTFactorByTableidAndType(l_tableID, "1");
					// 将分组处理为数据库的
					String lGroup = "";
					if(null!=l_group && !"".equals(l_group)){
						lGroup=transd(l_group, l_group, factorsL);
						lGroup = lGroup.substring(0,lGroup.trim().length() - 1);
					}
					

					// 条件定义--中文处理
					if (ConverTables.isNotNull(l_where)) {
						String lwhere = trans(l_where, l_where, factorsL,LtableName);
						map.put("LWHERE", lwhere);
					} else {
						map.put("LWHERE", "");
					}
					// 表达式定义
					String lCOL = trans(l_col, l_col, factorsL,LtableName);
					String rCOL = trans(r_col, r_col, factorsL,LtableName);

					map.put("LTABLENAME", LtableName);
					map.put("RELATYPE", formMap.get("RELATYPE").toString());
					map.put("ERRORDEF", formMap.get("ERRORDEF").toString());
					map.put("LGROUP", lGroup);
					map.put("LCOL", lCOL);
					map.put("RCOL", rCOL);
					map.put("RtableName", "");
					map.put("RGROUP", "");
					map.put("RWHERE", "");
					map.put("factorsL", factorsL);
					Map<String,Object> dataMap = auditRuleService.makeSql(map, "0");
					checkSql = (String)dataMap.get("CHECKSQL");
					
					formMap.put("CHECKSQL", checkSql);
					formMap.put("PUBGROUP", dataMap.get("PUBGROUP"));
					
					//转换 名称列 为COUMNID | LCOL、RCOL、LWHERE、LGROUP、LROWCOL
					formMap.put("LCOL", transIDFormat(l_col, l_col, factorsL));
					formMap.put("RCOL", transIDFormat(r_col, r_col, factorsL));
					formMap.put("LWHERE", transIDFormat(l_where, l_where, factorsL));
					formMap.put("LGROUP",transIDFormat(l_group, l_group, factorsL));
					//formMap.put("LROWCOL", transID(l_rowCol, l_rowCol, factorsL));	
				}
				
				try {
					// 验证CHECKSQL 是否错误
					if(checkSql.indexOf("@WHERE@")>0){
						 checkSql=checkSql.replaceAll("@WHERE@", "");
						
					}
					formulaService.callErrorMessage(checkSql); 
					//auditRuleService.verifyCheckSql(checkSql);
				} catch (Exception e) {
					e.printStackTrace();
					return "{\"flag\":\"verify\",\"error\":\""+e.getMessage().replace("\"", "'").replace("\n", "")+"\"}";
				}
				auditRuleService.modifyAuditData(formMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"flag\":\"false\"}";
		}
		return "{\"flag\":\"true\"}";
	}
	//替换特殊字符
	private void clearSpecialChar(Map<String, Object> formMap) {
		String msg=(String)formMap.get("SHOWTEXT");
		if(null!=msg && !"".equals(msg)){
			msg=msg.replaceAll("\"", "“");
			formMap.put("SHOWTEXT",msg);
		}
		 msg=(String)formMap.get("RDESC");
		if(null!=msg && !"".equals(msg)){
			msg=msg.replaceAll("\"", "“");
			formMap.put("RDESC",msg);
		}
		 msg=(String)formMap.get("LDESC");
		if(null!=msg && !"".equals(msg)){
			msg=msg.replaceAll("\"", "“");
			formMap.put("LDESC",msg);
		}
		
	}

	/**
	 * 查询 审核数据 根据id
	 * @param request
	 * @param checkid 审核数据的id
	 * @param appId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "queryAuditData")
	@ResponseBody
	public Object queryAuditData(HttpServletRequest request,@RequestParam(value = "checkid") String checkid,
			@RequestParam(value = "appId") String appId) throws Exception {
		Map<String,Object> map = auditRuleService.getAuditData(checkid);
		if(map.get("CHECKTYPE").equals(AUDITCUSTOM)){//自定义审核
			String checkSortTypeId=(String)map.get("BUSITYPEID");
		    String checkSortTypeIdName=auditRuleService.getAuditCategoryNameById(checkSortTypeId);
		    map.put("BUSITYPENAME", checkSortTypeIdName);//名称
			//return map;
		}
		
		
		List<TreeNodePO> node = (List<TreeNodePO>)this.getSuitTree(appId);
		
		String ltableId = (String)map.get("LTABLEID"); //左表
		String rtableId = (String)map.get("RTABLEID"); //右表
		String lauditTab = "";
		String rauditTab = "";
		DictTModelPO model = new DictTModelPO();
		List<DictTFactorPO> factorsL = new ArrayList<DictTFactorPO>();		
		List<DictTFactorPO> factorsR = new ArrayList<DictTFactorPO>();
		
		for(TreeNodePO n : node){ //获取 审核表来源 
			if(ConverTables.isNotNull(ltableId)) if(n.getId().equals(ltableId)) lauditTab = n.getAuditTab();
			if(ConverTables.isNotNull(rtableId)) if(n.getId().equals(rtableId)) rauditTab = n.getAuditTab();	
		}
		
		if(ConverTables.isNotNull(lauditTab)){
			model = dictTModelService.getDictTModelByID(ltableId, false);
			factorsL = dictTFactorService.getDictTFactorByTableidAndType(ltableId, "1");
			if (model != null) map.put("ltableName", model.getName());
		}

		if(ConverTables.isNotNull(rauditTab)){
			
				model = dictTModelService.getDictTModelByID(rtableId, false);
				factorsR = dictTFactorService.getDictTFactorByTableidAndType(rtableId, "1");
			
			if (model != null) map.put("rtableName", model.getName());
		}

		if(ConverTables.isNotNull(map)){
			String checkType = (String)map.get("CHECKTYPE");
			
			if(checkType.equals("0")){
				//表内审核 需要 转换 列 LCOL RCOL LWHERE LGROUP LROWCOL
				String lCol = (String)map.get("LCOL");
				String rCol = (String)map.get("RCOL");
				String lWhere = (String)map.get("LWHERE");
				String lGroup = (String)map.get("LGROUP");
				String lRowCol = (String)map.get("LROWCOL");
				String checkSortTypeId=(String)map.get("BUSITYPEID");
			    String checkSortTypeIdName=auditRuleService.getAuditCategoryNameById(checkSortTypeId);
			    map.put("BUSITYPENAME", checkSortTypeIdName);//名称
				if(ConverTables.isNotNull(lCol)) map.put("LCOL", transName(lCol, lCol, factorsL));
				if(ConverTables.isNotNull(rCol)) map.put("RCOL", transName(rCol, rCol, factorsL));
				if(ConverTables.isNotNull(lWhere)) map.put("LWHERE", transName(lWhere, lWhere, factorsL));
				if(ConverTables.isNotNull(lGroup)) map.put("LGROUP", transName(lGroup, lGroup, factorsL));
				
				if(ConverTables.isNotNullList(factorsL)){ //审核定位 列
					for(DictTFactorPO f : factorsL){
						if(f.getColumnid().equals(lRowCol)){
							map.put("LROWCOL", Constants.COLUMN_START + f.getName() + Constants.COLUMN_END);
						}
					}
				}else{
					map.put("LROWCOL", "");
				}
			}else{
				//表间审核 需要 转换 列 LCOL、RCOL、LWHERE、RWHERE、LGROUP、RGROUP
				if(checkType.equals("1")){
				String lCol = (String)map.get("LCOL");
				String lWhere = (String)map.get("LWHERE");
				String lGroup = (String)map.get("LGROUP");
				String rCol = (String)map.get("RCOL");
				String rWhere = (String)map.get("RWHERE");
				String rGroup = (String)map.get("RGROUP");
				String checkSortTypeId=(String)map.get("BUSITYPEID");
			    String checkSortTypeIdName=auditRuleService.getAuditCategoryNameById(checkSortTypeId);
			    map.put("BUSITYPENAME", checkSortTypeIdName);//名称
				if(ConverTables.isNotNull(lCol)) map.put("LCOL", transName(lCol, lCol, factorsL));
				if(ConverTables.isNotNull(lWhere)) map.put("LWHERE", transName(lWhere, lWhere, factorsL));
				if(ConverTables.isNotNull(lGroup)) map.put("LGROUP", transName(lGroup, lGroup, factorsL));
				if(ConverTables.isNotNull(rCol)) map.put("RCOL", transName(rCol, rCol, factorsR));
				if(ConverTables.isNotNull(rWhere)) map.put("RWHERE", transName(rWhere, rWhere, factorsR));
				if(ConverTables.isNotNull(rGroup)) map.put("RGROUP", transName(rGroup, rGroup, factorsR));	
				}else{//自定义审核
					map.put("LAUDITTAB", lauditTab);
					map.put("RAUDITTAB", rauditTab);
					return map;
				}
			}		
		}

		map.remove("CHECKSQL");
		map.put("LAUDITTAB", lauditTab);
		map.put("RAUDITTAB", rauditTab);

		return map;
	}
	/**
	 * 得到系统函数表定义
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getSysfnTabDefine")
	@ResponseBody
	public Object getSysfnTabDefine()throws Exception{
    	Table grid = auditRuleService.getSysfnTabDefine();
    	UserDTO user = SecureUtil.getCurrentUser();
    	return settingGridService.initStructure(grid,user.getGuid());
	}
	/**
	 * 得到系统函数表数据
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getSysfnTabData")
	@ResponseBody
	public Object getSysfnTabData(String grid)throws Exception{
		Grid data = (Grid)(new ObjectMapper()).readValue(grid,Grid.class);
		SysfnTabHelper.setColumns(data);
    	return settingGridService.getData(data);
	}
	@RequestMapping(value = "auditRuleData")
	public void auditRuleData(){
		try {
			Map<String,Object> param=new HashMap<String,Object>();
			//param.put("tableId", "F92B20A079A57483E040A8C020032390");
			//System.out.println(businessAuditOutService.getBusinessAuditData(param));
			String s=auditRuleOutService.getAuditResult("086FC360667927EAE0531A03A8C09583",null);
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	/**
	 * 解析SQL
	 * @param formids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "parseSql")
	@ResponseBody
	public String parseSql(@RequestParam(value = "formids") String formids){
		
		String LtableName = "";
		String RtableName = "";
		List<DictTFactorPO> factorsL = new ArrayList<DictTFactorPO>();
		List<DictTFactorPO> factorsR = new ArrayList<DictTFactorPO>();
		String checkSql = "";
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> formMap;
		try {
			formMap = (Map<String,Object>) (new ObjectMapper()).readValue(formids, Map.class);
		} catch (Exception e1) {
			return "解析错误";
		}
		
		//1、来源DICT_T_MODEL 2、来源 CHECK_T_REGVIEW
		String lauditTab = (String) formMap.get("LAUDITTAB");
		String rauditTab = (String) formMap.get("RAUDITTAB");
			// 表
			String l_tableID = (String) formMap.get("LTABLEID");
			String r_tableID = (String) formMap.get("RTABLEID");
			// 列
			String l_col = (String) formMap.get("LCOL");
			String r_col = (String) formMap.get("RCOL");
			// 分组
			String l_group = (String) formMap.get("LGROUP");
			String r_group = (String) formMap.get("RGROUP");
			// 条件
			String l_where = (String) formMap.get("LWHERE");
			String r_where = (String) formMap.get("RWHERE");
			
			String l_rowCol = (String) formMap.get("LROWCOL");
			String r_rowCol = (String) formMap.get("RROWCOL");
			//引用列 信息
			Map<String,Object> refFactor = new HashMap<String,Object>();
			try{
            String testStr= transRefTableCol(l_where);  
		
				if (formMap.get("CHECKTYPE").toString().equals(AUDITOUT)){ // 表间审核
					
					LtableName = dictTModelService.getDictTModelByID(l_tableID,false).getDbtablename();
					factorsL = dictTFactorService.getDictTFactorByTableidAndType(l_tableID, "1");
					RtableName = dictTModelService.getDictTModelByID(r_tableID,false).getDbtablename();
					factorsR = dictTFactorService.getDictTFactorByTableidAndType(r_tableID, "1");

					map.put("LTABLENAME", LtableName);
					map.put("RTABLENAME", RtableName);
					// 表达式
					String Lcol = trans(l_col, l_col, factorsL,LtableName);
					String Rcol = trans(r_col, r_col, factorsR,RtableName);
					map.put("LCOL", Lcol);
					map.put("RCOL", Rcol);
					// 分组
					String Lgroup ="";
					if(null!=l_group && !"".equals(l_group)){
						Lgroup=transd(l_group, l_group, factorsL);
					    Lgroup = Lgroup.substring(0, Lgroup.length() - 1);
					}
					String Rgroup ="";
					if(null!=r_group && !"".equals(r_group)){
						Rgroup= transd(r_group, r_group, factorsR);
						Rgroup = Rgroup.substring(0, Rgroup.length() - 1);
					} 
					map.put("LGROUP", Lgroup);
					map.put("RGROUP", Rgroup);

					// 条件定义
					if (ConverTables.isNotNull(l_where)) {
						String Lwhere = trans(l_where, l_where, factorsL,LtableName);
						map.put("LWHERE", Lwhere);
					} else {
						map.put("LWHERE", "");
					}
					if (ConverTables.isNotNull(r_where)) {
						String Rwhere = trans(r_where, r_where, factorsR,RtableName);
						map.put("RWHERE", Rwhere);
					} else {
						map.put("RWHERE", "");
					}
					map.put("RELATYPE", formMap.get("RELATYPE"));
					map.put("ERRORDEF", formMap.get("ERRORDEF"));
					map.put("factorsL", factorsL);
					map.put("factorsR", factorsR);
					Map<String,Object> dataMap = auditRuleService.makeSql(map, "1");
					checkSql = (String)dataMap.get("CHECKSQL");
					
					formMap.put("CHECKSQL", checkSql);
					formMap.put("PUBGROUP", dataMap.get("PUBGROUP"));
					
					//转换 名称列 为coumnID | LCOL、RCOL、LWHERE、RWHERE、LGROUP、RGROUP
					formMap.put("LCOL", transIDFormat(l_col, l_col, factorsL));
					formMap.put("LWHERE", transIDFormat(l_where, l_where, factorsL));
					formMap.put("LGROUP",transIDFormat(l_group, l_group, factorsL));
					formMap.put("RCOL", transIDFormat(r_col, r_col, factorsR));
					formMap.put("RWHERE", transIDFormat(r_where, r_where, factorsR));
					formMap.put("RGROUP", transIDFormat(r_group, r_group, factorsR));
					if(checkSql.indexOf("@WHERE@")>0){
						checkSql=checkSql.replaceAll("@WHERE@", "");
					}
					return formatSql(checkSql);


				} else {// 表内审核
					
					LtableName = dictTModelService.getDictTModelByID(l_tableID,false).getDbtablename();
					factorsL = dictTFactorService.getDictTFactorByTableidAndType(l_tableID, "1");

					// 将分组处理为数据库的
					String lGroup = "";
					if(null!=l_group && !"".equals(l_group)){
						lGroup=transd(l_group, l_group, factorsL);
						lGroup = lGroup.substring(0,lGroup.trim().length() - 1);
					}


					// 条件定义--中文处理
					if (ConverTables.isNotNull(l_where)) {
						String lwhere = trans(l_where, l_where, factorsL,LtableName);
						map.put("LWHERE", lwhere);
					} else {
						map.put("LWHERE", "");
					}
					// 表达式定义
					String lCOL = trans(l_col, l_col, factorsL,LtableName);
					String rCOL = trans(r_col, r_col, factorsL,LtableName);

					map.put("LTABLENAME", LtableName);
					map.put("RELATYPE", formMap.get("RELATYPE"));
					map.put("ERRORDEF", formMap.get("ERRORDEF"));
					map.put("LGROUP", lGroup);
					map.put("LCOL", lCOL);
					map.put("RCOL", rCOL);
					map.put("RTABLENAME", "");
					map.put("RWHERE", "");
					map.put("RGROUP", "");
					Map<String,Object> dataMap=null;
					try{
						map.put("factorsL", factorsL);
						dataMap = auditRuleService.makeSql(map, "0");
					}catch(Exception e){
						e.printStackTrace();
					}
					checkSql = (String)dataMap.get("CHECKSQL");
					
					formMap.put("CHECKSQL", checkSql);
					formMap.put("PUBGROUP", dataMap.get("PUBGROUP"));
					
					//转换 名称列 为coumnID | LCOL、RCOL、LWHERE、LGROUP、LROWCOL
					formMap.put("LCOL", transIDFormat(l_col, l_col, factorsL));
					formMap.put("RCOL", transIDFormat(r_col, r_col, factorsL));
					formMap.put("LWHERE", transIDFormat(l_where, l_where, factorsL));
					formMap.put("LGROUP",transIDFormat(l_group, l_group, factorsL));
					//formMap.put("LROWCOL", transID(l_rowCol, l_rowCol, factorsL));	
					if(checkSql.indexOf("@WHERE@")>0){
						 checkSql=checkSql.replaceAll("@WHERE@", "");
						
					}
                   return formatSql(checkSql);
				 }

			}catch(Exception e){
				if(e instanceof AuditRuleException){
					return "error:"+e.getMessage();
				}else{
					return "error:"+"函数解析错误";
				}
				
			}

			} 
	/**
	 * 将RF(表名).{『字段名称1』,『字段名称2』} 替换成 'value01','value02'
	 * @param refFactor
	 * @param l_col
	 * @param formMap
	 * @throws Exception 
	 */
	private String transRefTableCol( String inputStr) throws Exception {
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
					List<TreeNode> nodes = formulaService.getModelCodeDataByCsid(csDbTableName);
					
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
								throw new AuditRuleException("代码表【"+tableName+"】中的引用值  ' "+colName+"'  不存在");
							}
						
						}
						cm.appendTail(resultStr);
					}else{
						//throw 抛出异常 
						throw new AuditRuleException("引用表 "+tableName+"  无数据");
					}
					
		
				}else{
					// throw 抛出异常 表名不存在
					throw new AuditRuleException(tableName+" 该表不存在");
				} 
					
				m.appendReplacement(finalResult, Constants.RF_START+csDbTableId+Constants.RF_END+Constants.COMCOL_START+resultStr.toString()+Constants.COMCOL_END);
					
			}
			
			m.appendTail(finalResult);
			return finalResult.toString();
		}
		return inputStr;
		
		
	}
	//将引用列的id翻译成中文
	private String transRefIdtoName( String inputStr) throws Exception {
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
				String tempStr = inputStr.substring(m.start(), m.end());
				
				String tableId =tempStr.substring(tempStr.indexOf(Constants.RF_START)+3, tempStr.indexOf(Constants.RF_END));
				String colMatchStr = tempStr.replace(Constants.RF_START, "")
						                    .replace(Constants.RF_END, "")
						                    .replace(tableId, "")
						                    .replace(Constants.COMCOL_START, "")
						                    .replace(Constants.COMCOL_END, "");
				String csDbTableName="";
				String tableName="";
				String csDbTableId="";
				StringBuffer  idToNames=new StringBuffer(50);
				DictTModelcodePO mode =modelCodeService.getDictTModelcodePOByID(tableId.trim());
				//查询表的物理名称
				if (mode != null) {
					tableName = mode.getName(); // 表ID
					csDbTableId=mode.getTableid();
					csDbTableName=mode.getDbtablename();
					//String csDbTableName=mode.getDbtablename();
					colMatchStr=colMatchStr.replace("'", "");
					String[] idList = colMatchStr.split(",");
					List<TreeNode> nodes = formulaService.getModelCodeDataByCsid(csDbTableName);
					
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
						//throw 抛出异常 
					}
					
		
				}else{
					// throw 抛出异常 表名不存在
				} 
					
				m.appendReplacement(finalResult, Constants.RF_START+tableName+Constants.RF_END+Constants.COMCOL_START+idToNames.toString()+Constants.COMCOL_END);
					
			}
			
			m.appendTail(finalResult);
			return finalResult.toString();
		}
		return inputStr;
		
		
	}
	public String formatSql(String sql){
		sql=sql.replaceAll("FROM", "\\\n  FROM");
		sql=sql.replaceAll("WHERE", "\\\n     WHERE");
		sql=sql.replaceAll("GROUP", "\\\n           GROUP");
		sql=sql.replaceAll("UNION ALL", "\\\n UNION ALL \\\n");
		sql=sql.replaceAll("HAVING", "\\\n  HAVING");
		sql=sql.replaceAll("SUM", "\\\n SUM");
		return sql;
	}
	/**
	 * 查询应用的列
	 * @param csid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="modelCodeTree")
	@ResponseBody
	public Object getModelCodeDataByCsid(@RequestParam(value = "csid") String csid) throws Exception{
		DictTModelcodePO po = modelCodeService.getDictTModelcodePOByID(csid);
		Map<String,Object> dataMap = new HashMap<String, Object>();
		if(po!=null){
			String dbTableName = po.getDbtablename();
			String tableName = po.getName();
			List<TreeNode> dataList=formulaService.getModelCodeDataByCsid(dbTableName);
			if(null!=dataList ||dataList.size()>0){
				for(TreeNode node:dataList){
					node.setName("["+node.getCode()+"]"+node.getName());
				}
				
			}
			dataMap.put("treeNode", dataList);
			dataMap.put("tableName", tableName);
		}	
		return dataMap;
	}
	//将RF(表名).{『字段名称1』,『字段名称2』} 替换成 'value01','value02'
	public static void main(String[] args){
//		Map<String,String> dicMap = new HashMap<String,String>();
//		dicMap.put("表名", "dd");
//		Pattern p = Pattern.compile("RF\\(.+?\\)\\.\\{.+?\\}");
//		String testInput = "dsfsdf RF(表名).{『字段名称1』,『字段名称2』}sdfsdfsdfs RF(表名).{『字段名称5』,『字段名称9』} 88822";
//		Matcher m= p.matcher(testInput);
//		StringBuffer sb = new StringBuffer(50);
//	
//		 int count=0;
//		while(m.find()){
//			System.out.println(testInput);
//			testInput=m.replaceFirst("TEST0002");
//			m.reset(testInput);
//			System.out.println((++count)+" 次:"+testInput);
//			
//			
//		}
//        m.appendTail(sb);
//        System.out.println(sb);
		Integer p = null;
		Object o =null;
		String s = null;
		o=p;
		Class c = String.class;
		
		System.out.println(o instanceof String);
		System.out.println(o instanceof Integer);
		o=s;
		System.out.println(o instanceof String);
		System.out.println(o instanceof Integer);
	}
}

