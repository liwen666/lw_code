package commons.setting.dataaudit.auditrule.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjhq.commons.dict.dao.DictTAppregisterMapper;
import com.tjhq.commons.dict.external.po.DictTAppregisterPO;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.po.DictTModelcodePO;
import com.tjhq.commons.dict.external.po.DictTSuitPO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.dict.external.service.IDictTModelcodeService;
import com.tjhq.commons.dict.external.service.IDictTSuitService;
import com.tjhq.commons.inputcomponent.grid.settinggrid.service.ISettingGridService;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.po.TreePO;
import com.tjhq.commons.setting.dataaudit.auditrule.service.IAuditRuleDefService;
import com.tjhq.commons.setting.dataaudit.auditrule.service.IAuditRuleTypeService;
import com.tjhq.commons.setting.dataaudit.auditrule.po.AuditRuleTreeNodePO;
import com.tjhq.commons.setting.formula.service.IFormulaService;
import com.tjhq.commons.setting.input.po.TreeNode;
import com.tjhq.commons.setting.input.web.ConverTables;

import com.tjhq.commons.setting.dataaudit.auditrule.util.AuditRuleCheckDefTableHelper;
import com.tjhq.commons.setting.dataaudit.auditrule.util.AuditRuleSysfnTableHelper;
import com.tjhq.commons.utils.StringUtil;
import com.tjhq.commons.utils.UserInfo;
import com.tjhq.commons.utils.UserUtil;

/**
* @ClassName: AuditRuleDefController
* @Description: 新的 数据审核定义控制器
* @author xiehongtao
* @date 2017-6-6 下午6:03:23
* 
*	@RequestMapping(value = "/commons/setting/auditrule") //原来的请求路径
*
*/
@Controller
@RequestMapping(value = "/commons/setting/dataaudit/auditrule/")
public class AuditRuleDefController {
	
	//jsos返回操作的结果
	public static String SUCCESS="操作成功";
	public static String ERROR="操作失败";

	/**
	 * Grid 设置 操作服务接口
	 */
	@Resource
	private ISettingGridService settingGridService;	
	
	/**
	 * 公式 sql 服务接口
	 */
	@Resource
	private IFormulaService formulaService;
	
	/**
	 * 获取定义的子系统服务接口
	 */
	@Resource
	private DictTAppregisterMapper dictTAppregisterMapper;
	
	/**
	 * 获取定义的套表服务接口
	 */
	@Resource
	private IDictTSuitService dictTSuitService;
	
	/**
	 * 定义表列信息的服务接口
	 */
	@Resource
	private IDictTFactorService dictTFactorService; 
	
	/**
	 * 定义表对应的代码表服务接口
	 */
	@Resource
	private IDictTModelcodeService modelCodeService;
	
	
	/**
	 * 新的审核定义服务对象 逐步将旧的内容移入新的
	 */
	@Resource
	private IAuditRuleDefService auditRuleDefService;
	
	/**
	 * 审核类型 审核分类 服务接口
	 */
	@Resource
	private IAuditRuleTypeService auditRuleTypeService;
	
	
	
	/**
	* @Title: auditRuleMainPage
	* @Description: 数据审核定义规则 main page 
	* @param @param request	
	* @param @param appId	子系统ID
	* @param @return
	* @param @throws Exception    参数
	* @return String    返回类型
	* @throws
	* 
	*    @RequestMapping(value = "auditRulePage") //原来的请求路径
	* 
	* 
	 */
	@RequestMapping(value = "auditRuleMainPage")
	public String auditRuleMainPage(HttpServletRequest request,
			@RequestParam(value = "appId") String appId) throws Exception {
		request.setAttribute("appId", appId.trim());	//子系统id
		request.setAttribute("userBgtLevel",auditRuleDefService.findBudgetLevel(UserUtil.getUserInfo().getUpAgencyID())); //用户财政级次
	
		return "commons/setting/dataaudit/auditrule/auditruledefine";
	}
	
	/**
	* @Title: getNewAuditCategoryTree
	* @Description:获取审核分类树
	* @param @param req
	* @param @return
	* @param @throws Exception    参数
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value = "getNewAuditCategoryTree")
	@ResponseBody
	public Object getNewAuditCategoryTree(HttpServletRequest req)throws Exception {
		List<TreePO> nodes = auditRuleTypeService.getCheckTypeTree(req.getParameter("appID"));
		AuditRuleTreeNodePO po = new AuditRuleTreeNodePO();
		po.setId("0");
		po.setName("全部");
		po.setOpen(true);
		nodes.add(po);
		return nodes;
    }
	
	/**
	 * 
	* @Title: getCheckTypeTableDef
	* @Description:  得到审核分类的grid列表定义信息
	* @param @return
	* @param @throws Exception    参数
	* @return Table    返回类型
	* @throws
	 */
	@RequestMapping(value = "getCheckTypeTableDef")
	@ResponseBody
	public Table getCheckTypeTableDef()throws Exception{
    	return auditRuleTypeService.getCheckTypeTableDefine();
	}
	
	/**
	* @Title: getCheckTypeData
	* @Description: 得到审核分类数据
	* @param @param grid
	* @param @return
	* @param @throws Exception    参数
	* @return Object    返回类型
	* @throws
	 */
    @RequestMapping(value = "getCheckTypeData")
	@ResponseBody
	public Object getCheckTypeData(String grid) throws Exception{
    	return auditRuleTypeService.getCheckTypeTableData(grid);
	}
	
    /**
    * @Title: delCheckType
    * @Description: 删除 审核分类
    * @param @param grid
    * @param @return    参数
    * @return Object    返回类型
    * @throws
     */
    @RequestMapping(value = "delCheckSort")
	@ResponseBody
    public Object delCheckType(String grid){
    	return auditRuleTypeService.delCheckType(grid);
    }
    
    /**
    * @Title: saveCheckType
    * @Description: 修改 保存 审核分类
    * @param @param grid
    * @param @return    参数
    * @return Object    返回类型
    * @throws
     */
    @RequestMapping(value = "saveCheckSortData")
	@ResponseBody
    public Object saveCheckType(String grid){
    	return auditRuleTypeService.saveCheckType(grid);
    }
    
	
	/**
	* @Title: getAuditRuleTableDefine
	* @Description: 获取审核定义的grid列表定义信息
	* @param @return
	* @param @throws Exception    参数
	* @return Table    返回类型
	* @throws
	 */
	@RequestMapping(value = "getAuditRuleTableDefine")
	@ResponseBody
	public Table getAuditRuleTableDefine()throws Exception{
    	Table grid = auditRuleDefService.getAuditRuleTableDefine();
    	UserInfo user = UserUtil.getUserInfo();
    	return settingGridService.initStructure(grid,user.getGuid());
	}
	
	/**
	 * 
	* @Title: getAuditRuleDef
	* @Description: 获取审核定义的数据信息
	* @param @param grid
	* @param @return
	* @param @throws Exception    参数
	* @return Object    返回类型
	* @throws
	 */
    @RequestMapping(value = "getAuditRuleDef")
	@ResponseBody
	public Object getAuditRuleDef(String grid) throws Exception{
    	//获取列表的定义信息
		Grid data = (new ObjectMapper()).readValue(grid, Grid.class);
		
		AuditRuleCheckDefTableHelper.setColumns(data);
		//查询参数
		Map<String, Object> paraMap = new HashMap<String, Object>();
		//获取子系统ID
		paraMap.put("appID", data.getExtProperties().get("APPID"));		
		//是否分页
		Boolean pagination = (Boolean) data.getExtProperties().get("pagination");
		paraMap.put("pagination", data.getExtProperties().get("pagination"));
		if (pagination) {
			//开始行，结束行
			paraMap.put("startRow", data.getPageInfo().getStart());
			paraMap.put("endRow", (data.getPageInfo().getStart() + data.getPageInfo().getLimit()));
		}

		//审核分类
		String checkSortId_str = (String) data.getExtProperties().get("checkSortId");
		if (!StringUtil.isEmpty(checkSortId_str)) {
			paraMap.put("checkSortId", checkSortId_str);
		}

		// 审核类型 0表内 1表间审核
		String checkType_str = (String) data.getExtProperties().get("checkType");
		if (!StringUtil.isEmpty(checkType_str)) {
			paraMap.put("checkType", checkType_str);
		}
		String defName_str = (String) data.getExtProperties().get("defName");
		if (!StringUtil.isEmpty(defName_str)) {
			paraMap.put("defName", defName_str);
		}
		
		//查询结果集
		List<Map<String, Object>> dataList = auditRuleDefService.getDataAuditRuleList(paraMap);
		
		if (dataList.size() > 0) {
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
					} else {
						rowData.add(rawValue);
					}
				}
				datas.add(rowData);
			}

			data.getPageInfo().setDataList(datas);

		}
		
		data.getPageInfo().setTotal(auditRuleDefService.getDataAuditRuleList4count(paraMap));
		
		return data.getPageInfo();
	}
	
    /**
     * 
    * @Title: deleteCheckDef
    * @Description: 删除审核规则
    * @param @param grid
    * @param @return    参数
    * @return Object    返回类型
    * @throws
     */
    @RequestMapping(value = "deleteCheckDef")
	@ResponseBody
    public Object deleteCheckDef(String grid){
    	Table table;
		try {
			table = (new ObjectMapper()).readValue(grid, Table.class);
			
			AuditRuleCheckDefTableHelper.setColumns(table);//列信息
			
			List<Map<String, Object>>  delrow = table.getDeleteValues();
			
			String checkIds="";
			
			for(Map<String, Object> ele:delrow){
				if("".equals(checkIds)){
					checkIds+="'"+ele.get("CHECKID")+"'";
				}else{
					checkIds+=",'"+ele.get("CHECKID")+"'";
				}
			}
			
			if(!"".equals(checkIds)){
				//检查是否被业务引用了。如果引用了就不让删
				int a=auditRuleDefService.findRelationBusiness(checkIds);
				if(a>0){
					return new ResultPO("false","所选规则有业务模块引用无法删除!"); 
				}
			}
			
			auditRuleDefService.batchDelAuditRule(table);//保存数据
			
			return new ResultPO(SUCCESS);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultPO("false",ERROR);
		}
 
    }   
    
   /**
     * 
    * @Title: getAuditRuleDefCheckTable
    * @Description: 审核的检查grid定义
    * @param @return
    * @param @throws Exception    参数
    * @return Object    返回类型
    * @throws
     */
	@RequestMapping(value = "getAuditRuleDefCheckTable")
	@ResponseBody
	public Object getAuditRuleDefCheckTable()throws Exception{
		
		Table table=new Grid();
		table.setTableID("BGT_T_CHECKSORT");
		table.setAppID("BGT");
		table.setTableDBName("BGT_T_CHECKSORT");
		table.setTableName("审核定义检查");

		AuditRuleCheckDefTableHelper.setColumns4getAuditRuleDefCheck((Grid)table);
		
		UserInfo user = UserUtil.getUserInfo();
    	
    	return settingGridService.initStructure(table,user.getGuid());
	}
    
	/**
	 * 
	* @Title: getAuditRuleDefCheck
	* @Description: 审核的检查数据
	* @param @param grid
	* @param @return
	* @param @throws Exception    参数
	* @return Object    返回类型
	* @throws
	 */
    @RequestMapping(value = "getAuditRuleDefCheck")
	@ResponseBody
	public Object getAuditRuleDefCheck(String grid) throws Exception{
    	
    	Grid data = (new ObjectMapper()).readValue(grid,Grid.class);
    	//添加列
    	AuditRuleCheckDefTableHelper.setColumns4getAuditRuleDefCheck(data);
    	
    	List<String> columsList = new ArrayList<String>();
    	
    	//查询参数
    	Map<String,String> paraMap=new HashMap<String,String>();
    	//审核分组
    	String checkSortId_str=(String)data.getExtProperties().get("checkSortId");
    	//子系统
    	String APPID_str=(String)data.getExtProperties().get("APPID");
    	
	    if(!StringUtil.isEmpty(checkSortId_str)){
	    	paraMap.put("checkSortId", checkSortId_str);
	    }
	    
	    if(!StringUtil.isEmpty(APPID_str)){
	    	paraMap.put("APPID", APPID_str);
	    }
       
        List<Map<String,Object>> dataList=auditRuleDefService.getAuditRuleCheckList(paraMap);
        
        List<Object> resultDataList=new ArrayList<Object>();
        
        for(Map<String,Object> ele:dataList){
			String checkSql = (String)ele.get("CHECKSQL");
			try {
				checkSql=checkSql.replaceAll("@WHERE@", " and 1=2");
				// 验证CHECKSQL 是否错误
				formulaService.callErrorMessage(checkSql); 
				
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
    * @Title: getColumnTree
    * @Description: 根据表ID查询表的列信息 并组转为 TreeNode形式
    * @param @param tableid
    * @param @param auditTab
    * @param @return
    * @param @throws Exception    参数
    * @return Object    返回类型
    * @throws
     */
	@RequestMapping(value = "getColumnTree")
	@ResponseBody
	public Object getColumnTree(@RequestParam(value = "tableid") String tableid,
			@RequestParam(value = "auditTab") String auditTab)throws Exception {
		List<AuditRuleTreeNodePO> dataList=new ArrayList<AuditRuleTreeNodePO>();
		List<DictTFactorPO> list = null;	
		list = dictTFactorService.getDictTFactorsByTableId(tableid);
		wraperToTreeNode(dataList,list);
		return dataList;
	}
	
	/**
	* @Title: wraperToTreeNode
	* @Description: 将列信息转换为ToTreeNode形式
	* @param @param dataList
	* @param @param list    参数
	* @return void    返回类型
	* @throws
	 */
    private void wraperToTreeNode(List<AuditRuleTreeNodePO> dataList,
			List<DictTFactorPO> list) {
    	for(DictTFactorPO bo:list){
    		AuditRuleTreeNodePO treeBo = new AuditRuleTreeNodePO();
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
		List<AuditRuleTreeNodePO> treeList = new ArrayList<AuditRuleTreeNodePO> ();
		
		List<DictTAppregisterPO> apps=dictTAppregisterMapper.getAllDictTAppregister();
		
		for(DictTAppregisterPO appBO :apps){
			appID=appBO.getAppid();
			AuditRuleTreeNodePO tree = new AuditRuleTreeNodePO();
			tree.setId(appBO.getAppid());
			tree.setName(appBO.getAppname());
			tree.setPId("0");
			tree.setParent(true);
			tree.setOpen(true);
			treeList.add(tree);
			
		//引用 获取 所有套表及下属表
		List<DictTSuitPO> suitList = dictTSuitService.getDictTSuits(appID, "0", true);

		treeList = treeData(treeList,suitList);

		}
		return treeList;
	}  
	
	/**
	* @Title: treeData
	* @Description: 将套表数据转换为TreeNode 形式
	* @param @param treeList
	* @param @param list
	* @param @return
	* @param @throws Exception    参数
	* @return List<AuditRuleTreeNodePO>    返回类型
	* @throws
	 */
	private List<AuditRuleTreeNodePO> treeData(List<AuditRuleTreeNodePO> treeList,List <DictTSuitPO>list) throws Exception{	
		for(DictTSuitPO suit : list){
			AuditRuleTreeNodePO tree = new AuditRuleTreeNodePO();
			tree.setId(suit.getSuitid());
			tree.setName(suit.getSuitname());
			tree.setPId(suit.getAppid());
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
			    	AuditRuleTreeNodePO child = new AuditRuleTreeNodePO();
					child.setId(model.getTableid());
					child.setName(model.getName());
					child.setPId(model.getSuitid());
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
	
	/**
	* @Title: check
	* @Description: 检查定义的语法是否正确
	* @param @param formids
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value = "checkFormula")
	@ResponseBody
	public ResultPO check(@RequestParam(value = "formids") String formids) {
		return auditRuleDefService.checkAuditData(formids);
	}

	
	/**
	* @Title: saveAuditDataIn
	* @Description: 保存审核公式的定义信息
	* @param @param formids
	* @param @return
	* @param @throws Exception    参数
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value = "saveAuditDataIn")
	@ResponseBody
	public Object saveAuditDataIn(@RequestParam(value = "formids") String formids) throws Exception {
		
		return auditRuleDefService.saveAuditData(formids);
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
		
		return auditRuleDefService.getAuditData(checkid);
	}	
	
	/**
	 * 得到系统函数表定义
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getSysfnTabDefine")
	@ResponseBody
	public Object getSysfnTabDefine()throws Exception{
    	Table grid = auditRuleDefService.getSysfnTabDefine();
    	UserInfo user = UserUtil.getUserInfo();
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
		Grid data = (new ObjectMapper()).readValue(grid,Grid.class);
		AuditRuleSysfnTableHelper.setColumns(data);
    	return settingGridService.getData(data);
	}
	
	/**
	 * 解析SQL
	 * @param formids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "parseSql")
	@ResponseBody
	public ResultPO parseSql(@RequestParam(value = "formids") String formids) throws Exception{
		return auditRuleDefService.parseAuditData(formids);
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
	
}
