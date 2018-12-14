package commons.setting.input.web;

import gov.mof.fasp2.ca.user.dto.UserDTO;
import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.po.DictTModelcodePO;
import com.tjhq.commons.dict.external.po.DictTSuitPO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.dict.external.service.IDictTModelcodeService;
import com.tjhq.commons.dict.external.service.IDictTSuitService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.inputcomponent.grid.settinggrid.service.ISettingGridService;
import com.tjhq.commons.inputcomponent.po.PageInfo;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.jackson.ObjectMapper;
import com.tjhq.commons.setting.external.po.DictTSetQuerydPO;
import com.tjhq.commons.setting.external.service.IEntryOuterService;
import com.tjhq.commons.setting.input.dao.GeneralMapper;
import com.tjhq.commons.setting.input.po.TreeNode;
import com.tjhq.commons.setting.input.service.IEntryService;
import com.tjhq.commons.setting.input.service.IGeneralService;
import com.tjhq.commons.setting.input.service.ISinRecService;
import com.tjhq.commons.utils.ConvertUtil;
/**
* 类名称：EntryController  
* 创建人：shishj
* 创建时间：Jan 26, 2014 2:09:04 AM
* @version 1.0
*/
@Controller
@RequestMapping(value = "/commons/setting/input")
public class EntryController {

	@Resource
	private IEntryService entryService;
	@Resource
	private IGeneralService generalService;
	@Resource
	private GeneralMapper generalMapper;
	@Resource
	private IDictTSuitService dictTSuitService;
	@Resource
	private IDictTFactorService dictTFactorService;
	@Resource
	private IDictTModelcodeService dictTModelcodeService;
	@Resource
	private IEntryOuterService entryOuterService;
	@Resource 
	private ISettingGridService settingGridService;
	@Resource
	private ISinRecService sinRecService;
	
	@RequestMapping(value="")
	public String page() {
		return "/commons/setting/input/setGroup";
	}
	//业务表预览界面
	@RequestMapping(value="previewTableData")
	public String previewTableData() {
		return "/commons/setting/input/previewTable";
	}
	@RequestMapping(value="suitTree")
	@ResponseBody
	public Object getSuitTree(String appID) throws Exception{
		
		if(null==appID || ("").equals(appID)){
			appID = "BGT";
		}
		//引用 获取 所有套表及下属表
		List<DictTSuitPO> suitList = dictTSuitService.getDictTSuits(appID, "0", true);
		List<TreeNode> treeList = new ArrayList<TreeNode> ();

		treeList = treeData(treeList,suitList);
		
		return treeList;
	}

	 private List<TreeNode> treeData(List<TreeNode> treeList,List <DictTSuitPO>list) throws Exception{	
		for(DictTSuitPO suit : list){
			TreeNode tree = new TreeNode();
			tree.setId(suit.getSuitid());
			tree.setName(suit.getSuitname());
			tree.setPId(suit.getSuperid());
			tree.setIsLeaf(suit.getIsleaf());
			tree.setLevelNo(suit.getLevelno());
			tree.setAppID(suit.getAppid());
			tree.setIsParent("true");
			tree.setOpen(true);					
			treeList.add(tree);
			//物理表
			List<DictTModelPO> modelList = suit.getDictTModelList();
		
			if(modelList!=null&&modelList.size()>0){
			    for(DictTModelPO model:modelList){
			    	TreeNode child = new TreeNode();
					child.setId(model.getTableid());
					child.setName(model.getName());
					child.setPId(model.getSuitid());
					child.setIsLeaf(suit.getIsleaf());
					child.setDealType(model.getDealtype()); //表处理类型
					child.setDealName(model.getDealName());
					child.setTableType(model.getTabletype()); //表类型
					child.setAppID(model.getAppid());
					child.setIsParent("false");				    	
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
/*	@RequestMapping(value="appTree")
	@ResponseBody
	public Object getAppTree() throws Exception{	
		//获取 业务系统
		List appList = auditRegViewService.getAppList();
		List<TreeNode> treeList = new ArrayList<TreeNode>();
		
		if(ConverTables.isNotNullList(appList)){
			for(int i=0;i<appList.size();i++){
				TreeNode tree = new TreeNode();
				Map<String, Object> obj = (Map<String, Object>) appList.get(i);	
				tree.setId((String)obj.get("APPID"));
				tree.setName((String)obj.get("APPNAME"));
				treeList.add(tree);
			}
		}
		return treeList;
	}*/

	//通过tableID 查询表中所有不可空的列
	@RequestMapping(value="factoryTree")
	@ResponseBody
	public Object getTableFactory(String tableID) throws Exception{
		List<DictTFactorPO> treeList = dictTFactorService.getNotNullDictTFactorsByTableId(tableID);
		return treeList;
	}
	//通过tableID 查询表中所有的列
	@RequestMapping(value="allFactoryTree")
	@ResponseBody
	public Object getAllTableFactory(String tableID) throws Exception{
		List<DictTFactorPO> treeList = dictTFactorService.getDictTFactorsByTableId(tableID);
		return treeList;
	}
	//通过tableID 查询表中所有的可见并且是引用列
	@RequestMapping(value="factorVisibleRefTree")
	@ResponseBody
	public Object getFactorVisibleRefTree(String tableID) throws Exception{
		List<DictTFactorPO> treeList = dictTFactorService.getVisibleRefTreeByTableId(tableID);		
		return treeList;
	}
	
	//通过tableID 获取代码表列
	@RequestMapping(value="queryFactorCode")
	@ResponseBody
	public Object queryFactorCode(String tableID) throws Exception{
		DictTModelcodePO  dictTModelcodePO = dictTModelcodeService.getDictTModelcodePOByID(tableID);
		List<Map<String,Object>> codeList= generalMapper.queryCodeTable(dictTModelcodePO.getDbtablename());	
		List<TreeNode> treeList = new ArrayList<TreeNode>();
		if(ConverTables.isNotNull(codeList)){
			for(Map<String,Object> m : codeList){
				TreeNode node = new TreeNode();
				node.setId((String)m.get("id"));
				node.setCode((String)m.get("code"));
				node.setName((String)m.get("name"));
				node.setPId((String)m.get("pId"));
				node.setIsLeaf(m.get("isLeaf").toString());
//				node.setLevelNo(((BigDecimal)m.get("levelNo")).intValue());
				BigDecimal levelNo = new BigDecimal(m.get("levelNo")+"");
				node.setLevelNo(levelNo.intValue());
				treeList.add(node);
			}
		}
		return treeList;/*
		List<TreeNode> treeList = new ArrayList<TreeNode> ();
		if(ConverTables.isNotNullList(querydList)){
			for(DictTFactorcodePO po : querydList){
				TreeNode tree = new TreeNode();
				tree.setId(po.getColumnid());
				tree.setName(po.getName());
				tree.setPId("0");
				tree.setIsLeaf("1");
				tree.setLevelNo(1);
				tree.setDbColumnName(po.getDbcolumnname());
				tree.setColumnName(po.getName());
				tree.setColumnId(po.getColumnid());
				tree.setCsid(po.getCsid());
				String isRef = "0";
				if(po.getCsid()!=null && !"".equals(po.getCsid().trim())){
					isRef = "1";
				}
				tree.setIsRef(isRef);
				treeList.add(tree);	
			}
		}
		return treeList;*/
	}
	//通过tableID 查询条件设置
	@RequestMapping(value="querydDet")
	@ResponseBody
	public String getQuerydDet(String tableID) throws Exception{
		List<DictTSetQuerydPO> querydList= entryService.getDataQuerydList(tableID);
		return (new ObjectMapper()).writeValueAsString(querydList);
	}

	//新增、保存 查询条件设置
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveQuerydDet")
	@ResponseBody
	public String saveQuerydDet(HttpServletRequest request) throws Exception{
		String data = request.getParameter("data");

		Map<String,Object> queryd = (Map<String, Object>)ConverTables.jsonToMap(data).get(0);
		String recID = generalService.saveQuerydDet(queryd);
		return (new ObjectMapper()).writeValueAsString(recID);
	}

	/**
	 * 列表展示
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="outInterTest")
	@ResponseBody
	public Object outInterTest(String tableID) throws Exception{
		
/*		List<DictTSetGroupPO> group = entryService.getDataGroupList(tableID);
		
		List<DictTSetSortPO> sort = entryService.getDataSortList(tableID);
			 
		List<Map> analy = entryService.getDataAnalyList(tableID);
		entryService.getDataQuerydList(tableID);

		entryService.getDataFddefList(tableID);
		entryService.getDataFixList(tableID);*/		

/*		List<DictTSetRefrelaPO> refrela = entryOuterService.getDataRefrelaList(tableID);
		for(int i=0; i<refrela.size(); i++){
			System.out.println(refrela.get(i).getColumnID());
			//子表
			List<DictTSetRefrelaDataPO> data= refrela.get(i).getRefrelaData();
			for(DictTSetRefrelaDataPO d: data){	
				System.out.println(d.getDataID() + "-----" +d.getToDataID());
			}
		}*/
		
		/*String data = "{\"_locationposition\":5,\"ISLEAF\":1,\"YSSQS\":10,\"LEVELNO\":6,\"PROJECTNAME\":null}";
		String tableId = "F0D4B6FA51D9DF8EE040A8C020035DEF";
		formulaOuterService.oneTableFormula(data, tableId);
		//BASEOUTPUT
		entryOuterService.getDataMainSubTabList("BASEOUTPUT");*/
		String queryPanel = entryOuterService.createQueryPage(tableID);
				
		return (new ObjectMapper()).writeValueAsString(queryPanel);
	}

	/**
	 * 获取表格列定义
	 * @explain 
	 * @param tableId
	 * @return Object
	 * @throws Exception 
	 * @author  bizy
	 */
	@RequestMapping(value = "getTableHead")
	@ResponseBody
	public Object getTableHead(
				@RequestParam(value = "tableID") String tableId,
				@RequestParam(value = "defineID")String defineID,
				HttpServletRequest request
			) throws Exception{
        ResultPO resultPO = null;
		try {
			String columnID = "";
			//基本数字表设置
			if (defineID.equals("basenum")) columnID = request.getParameter("columnID");
			
			Table grid = generalService.setTableDefine(tableId,columnID,defineID);
			UserDTO user = SecureUtil.getCurrentUser();
			Table table = settingGridService.initStructure(grid, user.getGuid());
			resultPO = new ResultPO(table);
		} catch (ServiceException e) {
			e.printStackTrace();
            resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
		}
		return resultPO;
	}
	/**
	 * 获取表格数据
	 * @explain 
	 * @return
	 * @throws Exception 
	 * Object
	 * @author  bizy
	 * May 30, 2014 9:15:47 PM
	 */
	@ResponseBody
	@RequestMapping(value="getTableData")
	public Object getTableData(String grid)throws Exception{
        ResultPO resultPO = null;
		try {
			CommonGrid commonGrid = new CommonGrid();
			commonGrid = (new ObjectMapper()).readValue(grid, CommonGrid.class);

			String defineID = (String)commonGrid.getExtProperties().get("defineID");
			String tableID = (String)commonGrid.getExtProperties().get("tableID");

			List resultData = new ArrayList();	
			//视角
			if(defineID.equals("angleview")) resultData = entryService.getDataAngleViewList(tableID);
			//分组设置
			if(defineID.equals("group")) resultData = entryService.getDataGroupList(tableID);

			//排序设置
			if(defineID.equals("sort"))resultData = entryService.getDataSortList(tableID);

			//引用列关系定义
			if(defineID.equals("refrela")) resultData = entryService.getDataRefrelaList(tableID);
			if(defineID.equals("refrelaDetail")) {
				/*String condDataID = request.getParameter("condDataID");
				String rightTable = request.getParameter("rightTable");*/
				String condDataID = (String)commonGrid.getExtProperties().get("condDataID");
				String rightTable = (String)commonGrid.getExtProperties().get("rightTable");
				resultData = entryService.getRefrelaDbTableData(tableID,condDataID,rightTable);	
			}
			//主表 查询（分析）引用定义 
			if(defineID.equals("analy")) resultData = entryService.getDataAnalyList(tableID);
			//子表 查询 参数设置
			if(defineID.equals("analyDetail")) resultData = entryService.selectHrefParm(tableID);

			//查询 条件设置
			if(defineID.equals("queryd")) resultData = entryService.selectQuerydDet(tableID);
			//基本数字表设置
			if(defineID.equals("basenum")) {
				//String columnID = request.getParameter("columnID");
				String columnID = (String)commonGrid.getExtProperties().get("columnID");
				resultData = entryService.getDataBaseSubList(columnID, tableID);
			}
			//单记录表设置
			if(defineID.equals("sinrec")) resultData = sinRecService.selectSingleRecord(tableID);	
			//return Grid.getData(adapterType,resultData);

			PageInfo pageInfo = new PageInfo();
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			if(resultData!=null){
				if(defineID.equals("sinrec")) {
					resultList = resultData;
				}else{
					for(Object obj : resultData){//object转map
						//resultList.add(this.getFieldVlaue(obj));
						Map<String,Object> convertMap = ConvertUtil.toMap(obj);
						convertMap.put("C_STATUS", "0");
						resultList.add(convertMap);
					}
				}
			}
			this.setGridData(resultList, pageInfo);

            resultPO = new ResultPO(pageInfo); 
        } catch (ServiceException e) {
            e.printStackTrace();
            resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
		}
        return resultPO;
	}
	/**
	 * 获取 一般录入表 表头
	 * @param tableID
	 * @param DefineID 
	 */
	/*@RequestMapping(value = "getTableHead")
	@ResponseBody
	public Object getTableHead(@RequestParam(value = "tableID") String tableID, 
			@RequestParam(value = "defineID")String defineID,HttpServletRequest request) throws Exception {
		String columnID = "";
		//基本数字表设置
		if (defineID.equals("basenum")) columnID = request.getParameter("columnID");

		return generalService.getDefineHead(tableID, defineID, columnID).getDefine();

	}*/
	/**
	 * 获取 一般录入表 数据
	 * @param tableID
	 * @param DefineID
	 * @param adapterType
	 */
	/*@RequestMapping(value="getTableData")
	@ResponseBody
	public Object getTableData(@RequestParam(value = "tableID") String tableID,
			@RequestParam(value = "defineID") String defineID,
			@RequestParam(value = "adapterType") int adapterType, HttpServletRequest request) throws Exception{
		List resultData = new ArrayList();		
		//视角
		if(defineID.equals("angleview")) resultData=entryService.getDataAngleViewList(tableID);
		//分组设置
		if(defineID.equals("group")) resultData = entryService.getDataGroupList(tableID);
		
		//排序设置
		if(defineID.equals("sort"))resultData = entryService.getDataSortList(tableID);
		
		//引用列关系定义
		if(defineID.equals("refrela")) resultData = entryService.getDataRefrelaList(tableID);
		if(defineID.equals("refrelaDetail")) {
			String condDataID = request.getParameter("condDataID");
			String rightTable = request.getParameter("rightTable");

			resultData = entryService.getRefrelaDbTableData(tableID,condDataID,rightTable);	
		}
		//主表 查询（分析）引用定义
		if(defineID.equals("analy")) resultData = entryService.getDataAnalyList(tableID);
		//子表 查询 参数设置
		if(defineID.equals("analyDetail")) resultData = entryService.selectHrefParm(tableID);
		
		//查询 条件设置
		if(defineID.equals("queryd")) resultData = entryService.selectQuerydDet(tableID);
		//基本数字表设置
		if(defineID.equals("basenum")){
			String columnID = request.getParameter("columnID");
			resultData = entryService.getDataBaseSubList(columnID,tableID);
		}		
		return Grid.getData(adapterType,resultData);
	}*/
 
	@RequestMapping(value="saveTableData")
	@ResponseBody
	public String saveTableData(String grid) throws Exception{
		CommonGrid commonGrid
			= (new ObjectMapper()).readValue(grid, CommonGrid.class);
		String is_success = "{\"flag\":\"true\"}";
		String defineID = (String)commonGrid.getExtProperties().get("defineID");
		try {
			generalService.saveTableData(commonGrid, defineID);
		} catch (ServiceException e) {
			is_success = "{\"flag\":\"" + e.getErrorMessage() + "\"}";
		} catch (Exception e) {
			e.printStackTrace();
			is_success = "{\"flag\":\""+e.getMessage()+"\"}";
		}
		if(ConverTables.isNotNull(defineID) && is_success.indexOf("true") > 0) {
			if("refrela".equals(defineID)) is_success="{\"flag\":\"refrelaflag\"}";
		}
		return is_success;
	}

	/**
	 * 适用于列 COMBO
	 * @param tableID
	 * @param defineID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getFactorCombo")
	@ResponseBody
	public Object getFactorCombo(@RequestParam(value = "tableID") String tableID,
			@RequestParam(value = "defineID") String defineID) throws Exception {
		
		List<DictTFactorPO> factor = dictTFactorService.getDictTFactorsByTableId(tableID);
		
		if(defineID.equals("refrela")){ //只获取 引用列
			List<DictTFactorPO> factorList = new ArrayList<DictTFactorPO>();
			if(ConverTables.isNotNullList(factor)){
				for(DictTFactorPO po : factor){
					if(po.getDatatype() != 6){ 
						factorList.add(po);
					}
				}
				factor.removeAll(factorList);
			}
		}
		List<TreeNode> treeList = new ArrayList<TreeNode> ();
		if(ConverTables.isNotNullList(factor)){
			for(DictTFactorPO po : factor){
				TreeNode tree = new TreeNode();
				tree.setId(po.getColumnid());
				tree.setName(po.getName());
				tree.setPId(po.getSuperid());
				tree.setIsLeaf(po.getIsleaf()==null ? "1" : po.getIsleaf());
				tree.setLevelNo(po.getLevelno()==null ? 1 : po.getLevelno());
				tree.setDbColumnName(po.getDbcolumnname());
				tree.setColumnName(po.getName());
				tree.setColumnId(po.getColumnid());
				tree.setCsid(po.getCsid());
				String isRef = "0";
				if(po.getCsid()!=null && !"".equals(po.getCsid().trim())){
					isRef = "1";
				}
				tree.setIsRef(isRef);
				treeList.add(tree);	
			}
		}
		return treeList;
	}

	/**
	 * 适用于 升序 降序 COMBO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getSortCombo")
	@ResponseBody
	public Object getSortCombo() throws Exception{
		List<TreeNode> treeList = new ArrayList<TreeNode> ();
		
		TreeNode node1 = new TreeNode();
		node1.setId("1");
		node1.setName("升序");
		TreeNode node2 = new TreeNode();
		node2.setId("0");
		node2.setName("降序");
	
		treeList.add(node1);
		treeList.add(node2);
		return treeList;
	}
	/**
	 * 适用于 查询代码表Code
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getCodeTableCombo")
	@ResponseBody
	public Object getCodeTableCombo(@RequestParam(value = "defineID") String defineID) throws Exception{
		List<TreeNode> treeList = new ArrayList<TreeNode> ();
		String cString = "";	  
		if(defineID.equals("analy")) cString = "CODE_T_QUERYPICLOGO";
		if(defineID.equals("queryd")) cString = "CODE_T_QUERYOPERATOR";

		List<Map<String,Object>> code= generalMapper.queryCodeTable(cString);	
		if(ConverTables.isNotNull(code)){
			for(Map<String,Object> m : code){
				TreeNode node = new TreeNode();
				//node.setId((String)m.get("code"));//毕再一注释：20150425，查询分析引用列图片的值改为guid
				if(defineID.equals("analy")) node.setId((String)m.get("id"));
				if(defineID.equals("queryd")) node.setId((String)m.get("code"));
				
				node.setCode((String)m.get("code"));
				node.setName((String)m.get("name"));
				node.setPId((String)m.get("pId"));
				node.setIsLeaf((String)m.get("isLeaf"));
				node.setLevelNo(((BigDecimal)m.get("levelNo")).intValue());
				treeList.add(node);
			}
		}
		return treeList;
	}
	/**
	 * 适用于 查询图片代码表Code
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getPictureCodeTableCombo")
	@ResponseBody
	public Object getPictureCodeTableCombo(@RequestParam(value = "defineID") String defineID) throws Exception{
		List<TreeNode> treeList = new ArrayList<TreeNode> ();
		String cString = "";	  
		if(defineID.equals("analy")) cString = "CODE_T_QUERYPICLOGO";
		if(defineID.equals("queryd")) cString = "CODE_T_QUERYOPERATOR";

		List<Map<String,Object>> code= generalMapper.queryCodeTable(cString);	
		if(ConverTables.isNotNull(code)){
			for(Map<String,Object> m : code){
				TreeNode node = new TreeNode();
				node.setId((String)m.get("id"));
				node.setName((String)m.get("name"));
				node.setPId((String)m.get("pId"));
				node.setIsLeaf((String)m.get("isLeaf"));
				node.setLevelNo(((BigDecimal)m.get("levelNo")).intValue());
				treeList.add(node);
			}
		}
		return treeList;
	}
	
	/**
	 * 适用于 基本数字表COMBO
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="getBaseNumCombo")
	@ResponseBody
	public Object getBaseNumCombo(@RequestParam(value = "tableID") String tableID,
			@RequestParam(value = "columnID") String columnID,@RequestParam(value = "params") String params) throws Exception{
		List<TreeNode> treeList = new ArrayList<TreeNode> ();
		 Map<String, String> paramsMap = null;
			 if (StringUtils.isNotEmpty(params)) {
	             paramsMap = (Map) (new ObjectMapper()).readJson(params, Map.class);
	         }
		
		//如果columnID 为 引用代码表、 获取引用表数据
		String dbTableName = entryService.getRefrelaDbTableName(tableID, paramsMap.get("baseColumnID"));
		if(ConverTables.isNotNull(dbTableName)) {
			treeList = entryService.getRefrelaDbTableTree(dbTableName);
		}
		return treeList;
	}
	/**
	 * 查找对应表的基本信息表中是否有数据，如果有数据，则录入界面的基本数字表的新增或删除操作无效
	 * 
	 */
	@RequestMapping(value="getDataCount")
	@ResponseBody
	public Object getDataCount(@RequestParam(value="tableID") String tableID,
			@RequestParam(value="year") String year,@RequestParam(value="dbColumnName") String dbColumnName,@RequestParam(value="columnValue") String columnValue ) {
		ResultPO rp = null;
		try {
			Integer count = this.entryService.getDataCount(tableID, year, dbColumnName,columnValue);
			if(ConverTables.isNotNull(count)) {
				rp = new ResultPO(count);
			}
		} catch(ServiceException e) {
			rp = new ResultPO(e.getCode(), e.getErrorMessage());
		}
		return rp;
	}

	 /**
     * 返回由对象的属性为key,值为map的value的Map集合
     */
	/*
	private Map<String, Object> getFieldVlaue(Object obj) throws Exception {
        Map<String, Object> mapValue = new HashMap<String, Object>();
        Class<?> cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            if("serialVersionUID".equals(name)){
            	continue;
            }
            String strGet = "get" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
            Method methodGet = cls.getDeclaredMethod(strGet);
            Object object = methodGet.invoke(obj);
            String value = object != null ? object.toString() : "";
            mapValue.put(name, value);
        }
        return mapValue;
    }*/
	/**
	 * 对获取的数据进行格式转换
	 * 
	 * @param resultMap
	 * @param pageInfo
	 * @throws Exception
	 */
	private void setGridData(List<Map<String, Object>> resultList,
			PageInfo pageInfo) throws Exception {
	
		if (resultList == null || resultList.size() == 0)
			return;
	
		// 取查询数据和列
		List<String> columns = new ArrayList<String>(resultList.get(0).keySet());
		List<Object> datas = new ArrayList<Object>();
	
		List<Object> rowData = null;
		for (Map<String, Object> mapedRecord : resultList) {
			rowData = new ArrayList<Object>();
	
			// 根据列定义拷贝数据
			for (String column : columns) {
				Object rawValue = mapedRecord.get(column);
				if (rawValue == null) {
					rowData.add(null);
					continue;
				}
	
				if (Clob.class.isAssignableFrom(rawValue.getClass())) {// clob
					Clob clobValue = (Clob) rawValue;
					Reader clobStream = clobValue.getCharacterStream();
					char[] clobBuffer = new char[(int) clobValue.length()];
					clobStream.read(clobBuffer);
					clobStream.close();
					rowData.add(new String(clobBuffer));
	
				} else if (BigDecimal.class.isAssignableFrom(rawValue
						.getClass())) {// BigDecimal
					BigDecimal bigDecimalValue = (BigDecimal) rawValue;
					String strval = bigDecimalValue.toString();
					rowData.add(strval);
				} else {
					rowData.add(rawValue);
				}
			}
			datas.add(rowData);
		}
		pageInfo.setDataList(datas);
		pageInfo.setColumns(columns);
	}
}
