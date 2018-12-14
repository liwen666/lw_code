package commons.secu.controller;
import gov.mof.fasp2.ca.user.dto.UserDTO;
import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.po.DictTSuitPO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.dict.external.service.IDictTSuitService;
import com.tjhq.commons.inputcomponent.grid.settinggrid.service.ISettingGridService;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.secu.external.IDataAuthService;
import com.tjhq.commons.secu.po.SecuTLimitcolPO;
import com.tjhq.commons.secu.po.SecuTLimittabPO;
import com.tjhq.commons.secu.po.TreeNode;
import com.tjhq.commons.secu.po.TreeNodeForTable;
import com.tjhq.commons.secu.service.IInputLimitService;
@Controller
@RequestMapping(value = "/commons/secu/inputLimit")
public class InputLimitController {
		@Resource
		private IInputLimitService inputLimitService;
		@Resource
		private IDictTSuitService dictTSuitService;
		@Resource
		private IDictTModelService dictTModelService;
		@Resource
		private IDictTFactorService dictTFactorService;
		@Resource
		private IDataAuthService dataAuthService; 
		@Resource
		private ISettingGridService settingGrid;
		
		@RequestMapping(value="")
		public String page() {
			//下面注释的代码为测试代码
			//List<Map<String,String>> agencyList = dataAuthService.getAgencyListByUserId("1234567890","BGT");
			//List<DictTModelPO> list = dataAuthService.getTableListViaSecu("1234567890", "EF9424787D2AE618E040A8C02003067E", "BGT");
			//SecuTTablePO secuTmodel = dataAuthService.getTableAuthByUser("F7358D54C309B7BCE040A8C020036165", "F7A7CF6C10734D1AAFD8187FA4DEB3AQ","1","BGT",false);
			//String sql = dataAuthService.getTableRowAuthByUser("EF9654A30D8C6EB5E040A8C020032948", "1234567890","BGT");
			//Map<String,SecuTColPO> secuCols = dataAuthService.getTableColsAuthByUser("F7358D54C309B7BCE040A8C020036165", "F7A7CF6C10734D1AAFD8187FA4DEB3AQ");
			//String tableName = dataAuthService.getTableDatabaseNameBy("BGT", Constants.COMMON_DictTAPPCODE_TABLETYPE_AGENCY);
			//String jsonStr = "{EXPFUNCID:'12345678',AGENCYID:'adsasdfwerxfges',AGENCYNAME:'nihao'} ";
			//String tableId = "EFCEBA43A7F44225E040A8C02003204D";
			//dataAuthService.getRowCellSecuBy(tableId, jsonStr);
			//List<DictTFactorPO> pos = dictTFactorService.getDictTFactorsByTableIdForTree("F92EB3E156104E20E040A8C020033DAA");
			//UserDTO user = SecureUtil.getCurrentUser();
			//String userId = 	user.getGuid();
			//List<Map<String,String>> agencyList = dataAuthService.getAgencyListByUserId(userId,"BGT");
			//List<PendingTaskDTO> tasklist = taskManageService.getCanDoListByUserId(userId); 
			return "/commons/secu/inputLimitC";  
		}
		
		@RequestMapping(value="inputLimitItemList/head")
		@ResponseBody
		public Object getInputLimitItemListHead(HttpServletRequest request,HttpServletResponse response) throws Exception{
			Table grid = inputLimitService.getInputLimitItemGrid(); 
			UserDTO user = SecureUtil.getCurrentUser();
			Table table = settingGrid.initStructure(grid, user.getGuid());
			return table; 	
		} 
		
		@RequestMapping(value="inputLimitItemList/list")
		@ResponseBody
		public Object getInputLimitItemList(HttpServletRequest request,HttpServletResponse response,String grid) throws Exception{
			Grid data = (Grid)(new ObjectMapper()).readValue(grid,Grid.class);
			String tableId = data.getExtProperties().get("tableId").toString();
			List<SecuTLimittabPO> tableLimits = inputLimitService.getInputLimitItemList(tableId); 
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>(); 
			for(SecuTLimittabPO po:tableLimits){
				dataList.add(convertBean(po));
			}
			settingGrid.transferGridData(dataList, data.getPageInfo()); 
			return data.getPageInfo();  
		}
		
		
		
		@RequestMapping(value="verifyTableCellLimit")
		@ResponseBody
		public Object verifyTableCellLimit(HttpServletRequest request,HttpServletResponse response) throws Exception{
			try{
				String tableId =request.getParameter("tableId");
				DictTModelPO table = dictTModelService.getDictTModelByID(tableId, false);
				String sqlWhere =request.getParameter("sqlWhere");
				Map<String,String> param = new HashMap<String,String>();
				param.put("sqlWhere", sqlWhere);
				param.put("tableDBName",table.getDbtablename());
				param.put("tableId", tableId);
				inputLimitService.verifyTableCellLimit(param);
				return "公式正确";
			}catch(Exception e){
				return "公式不正确："+e.getMessage();
			}

		}
		
		@RequestMapping(value="inputLimitTableList/head")
		@ResponseBody
		public Object getInputLimitTableListHead(HttpServletRequest request,HttpServletResponse response) throws Exception{
			Table grid = inputLimitService.getInputLimitGrid(); 
			UserDTO user = SecureUtil.getCurrentUser();
			Table table = settingGrid.initStructure(grid, user.getGuid()); 
			return table;	
		}
		
		@RequestMapping(value="/showAddWindow")
		public String showInputLimitAddWin() {
			return "/commons/secu/inputLimit_addLimit";
		}
		@RequestMapping(value="inputLimitTableList/list")
		@ResponseBody
		public Object getInputLimitTableListList(HttpServletRequest request,HttpServletResponse response,String grid) throws Exception{
			
			/*String suitFlag = request.getParameter("suitFlag");
			String objId = request.getParameter("objId");
			String queryCond = request.getParameter("queryCond");
			List<Map<String,String>> tableInputLimitList = new ArrayList<Map<String,String>>();
			if("".equals(objId) || objId == null ){
				return null;
			}
			if("true".equals(suitFlag)){
				if("all".equals(queryCond)){
					tableInputLimitList = inputLimitService.getTableInputLimitListBySuitId(objId);
				}else if("unLimit".equals(queryCond)){
					tableInputLimitList = inputLimitService.getTableInputUnlimitListBySuitId(objId);
				}else if("limited".equals(queryCond)){
					tableInputLimitList = inputLimitService.getTableInputLimitedListBySuitId(objId);
				}
			}else{
					tableInputLimitList = inputLimitService.getTableInputLimitListByTableId(objId);
			}
			if(tableInputLimitList == null || tableInputLimitList.size()==0){
				return null;
			}
			return Grid.getData(1, tableInputLimitList);*/
			Grid data = (Grid)(new ObjectMapper()).readValue(grid,Grid.class);
			String suitFlag = data.getExtProperties().get("suitFlag").toString();
			String objId = data.getExtProperties().get("objId").toString();
			String queryCond = data.getExtProperties().get("queryCond").toString(); 
			List<Map<String,String>> tableInputLimitList = new ArrayList<Map<String,String>>();
			if("".equals(objId) || objId == null ){
				return null;
			}
			if("true".equals(suitFlag)){
				if("all".equals(queryCond)){
					tableInputLimitList = inputLimitService.getTableInputLimitListBySuitId(objId);
					
				}else if("unLimit".equals(queryCond)){
					tableInputLimitList = inputLimitService.getTableInputUnlimitListBySuitId(objId);
				}else if("limited".equals(queryCond)){
					tableInputLimitList = inputLimitService.getTableInputLimitedListBySuitId(objId);
				} 
			}else{
					tableInputLimitList = inputLimitService.getTableInputLimitListByTableId(objId);
			}
			if(tableInputLimitList == null || tableInputLimitList.size()==0){
				return null;
			}
			List<Map<String,Object>> dataList= new ArrayList<Map<String, Object>>();
			for(int i=0;i<tableInputLimitList.size();i++){
				Map<String,String> map = (Map<String,String>)tableInputLimitList.get(i);
				Map<String,Object> mapnew = new HashMap<String,Object>(); 
				 for (String key : map.keySet()) {
					   mapnew.put(key, map.get(key)); 
				}
				dataList.add(mapnew);
			}
			settingGrid.transferGridData(dataList, data.getPageInfo()); 
			return data.getPageInfo();
			
		}
		@RequestMapping(value="queryInputColLimit")
		@ResponseBody
		public Object queryInputColLimit(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String tableId = request.getParameter("tableId");
			String columnId = request.getParameter("columnId");
			SecuTLimitcolPO secuLimitcoPO = inputLimitService.queryInputColLimit(tableId,columnId);
			if(secuLimitcoPO==null){
				return new SecuTLimitcolPO();   
			}
			return secuLimitcoPO; 
		}
		
		@RequestMapping(value="getTableTreeData")
		@ResponseBody
		public Object getTableTreeData(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String appId = request.getParameter("appId");
			List<DictTSuitPO> tables = dictTSuitService.getDictTSuits(appId, "0", true);
			List<TreeNodeForTable> treeList = new ArrayList<TreeNodeForTable>();

			getTableTreeData(treeList,tables);
			
			return (new ObjectMapper()).writeValueAsString(treeList);

		}
		
		@RequestMapping(value="cancelInputLimit")
		@ResponseBody
		public Object cancelInputLimit(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String tableId = request.getParameter("tableId");
			try{
				inputLimitService.cancelInputLimit(tableId);
				return "取消输入限制成功";
			}catch(Exception e ){
				return "取消输入限制操作错误！"; 
			}
		}
		
		@RequestMapping(value="saveColLimitData")
		@ResponseBody
		public Object saveColLimitData(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String tableId = request.getParameter("tableId");
			String columnId = request.getParameter("columnId");
			String showWhere = request.getParameter("showWhere");
			String sqlWhere = request.getParameter("sqlWhere");
			String existFlag = request.getParameter("existFlag");
			try{
				inputLimitService.saveColLimitData(tableId,columnId,showWhere,sqlWhere,existFlag);
				return "保存成功";
			}catch(Exception e ){
				return "保存操作错误！";
			}
		}
		
		@RequestMapping(value="clearLimitWindow")
		@ResponseBody
		public Object clearLimitWindow(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String tableId = request.getParameter("tableId");
			String columnId = request.getParameter("columnId");
			try{
				inputLimitService.clearLimitWindow(tableId,columnId);
				return "删除成功";
			}catch(Exception e ){
				//throw e;
				return "删除操作错误！";
			}
		}
		
		@RequestMapping(value="getColsOfTableTreeData")
		@ResponseBody
		public Object getColsOfTableTreeData(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String tableId = request.getParameter("tableId");
			List<DictTFactorPO> cols = dictTFactorService.getDictTFactorsByTableId(tableId);
			List<TreeNode> treeList = new ArrayList<TreeNode>();
			for(DictTFactorPO po:cols){
				TreeNode tree = new TreeNode();
				tree.setId(po.getColumnid());
				tree.setName(po.getName());
				tree.setPId(po.getSuperid());
				treeList.add(tree);    
			}
			return treeList;  
		} 
		
		
		public List getTableTreeData(List clist,List <DictTSuitPO>list) throws Exception{	
			for(DictTSuitPO suit : list){
				TreeNodeForTable tree = new TreeNodeForTable();
				tree.setId(suit.getSuitid());
				tree.setName(suit.getSuitname());
				tree.setPId(suit.getSuperid());
				tree.setIsLeaf(suit.getIsleaf());
				tree.setLevelNo(suit.getLevelno());
				tree.setIsParent("true");
				tree.setOpen(true);
				clist.add(tree);
				//物理表
				List<DictTModelPO> modelList = suit.getDictTModelList();
			
				if(modelList!=null&&modelList.size()>0){
				    for(DictTModelPO model:modelList){
				    	TreeNodeForTable leafTree = new TreeNodeForTable();
				    	leafTree.setId(model.getTableid());
				    	leafTree.setName(model.getName());
				    	leafTree.setPId(model.getSuitid());
				    	leafTree.setIsLeaf(suit.getIsleaf());
				    	leafTree.setDealType(model.getDealtype()); //表处理类型
				    	leafTree.setIsParent("false");
				    	clist.add(leafTree);
					}	
				}
				List<DictTSuitPO> suitList = suit.getDictTSuitList();
				if(suitList!=null&&suitList.size()>0){
					
					getTableTreeData(clist,suitList);
					
				}
			}
			return clist;
		}		
		
		@RequestMapping(value="tableColList/head")
		@ResponseBody
		public Object getTableColListHead(HttpServletRequest request,HttpServletResponse response) throws Exception{
			/*Grid head = inputLimitService.getTableColListHead();
			return head.getDefine();*/
			Table grid = inputLimitService.getTableColListHead(); 
			UserDTO user = SecureUtil.getCurrentUser();
			Table table = settingGrid.initStructure(grid, user.getGuid()); 
			return table;	
		}
		@RequestMapping(value="getTableColList")
		@ResponseBody
		public Object getTableColList(HttpServletRequest request,HttpServletResponse response,String grid) throws Exception{
			/*String tableId = request.getParameter("tableId");
			List<DictTFactorPO> finalCols = dictTFactorService.getDictTFactorByTableidAndType(tableId, "1");
			if(finalCols== null ||finalCols.size()==0 ){
				return null;
			}
			return Grid.getData(1, finalCols);*/
			Grid data = (Grid)(new ObjectMapper()).readValue(grid,Grid.class);
			String tableId = data.getExtProperties().get("tableId").toString();
			List<DictTFactorPO> finalCols = dictTFactorService.getDictTFactorByTableidAndType(tableId, ""); 
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>(); 
			for(DictTFactorPO po:finalCols){
				dataList.add(convertBean(po));    
			}
			settingGrid.transferGridData(dataList, data.getPageInfo());  
			return data.getPageInfo();   
		}
		
		@RequestMapping(value="getAcctItemTreeData")
		@ResponseBody
		public Object getAcctItemTreeData(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String tableId = request.getParameter("tableId");
			List<TreeNode> acctItemTreeData = inputLimitService.getAcctItemTreeData(tableId);
			return acctItemTreeData; 
		}
		
		@RequestMapping(value="deleteAcctItemTreeData")
		@ResponseBody
		public Object deleteAcctItemTreeData(HttpServletRequest request,HttpServletResponse response) throws Exception{
			try{
				String tableId = request.getParameter("tableId");
				String deleteJsonData = request.getParameter("deleteJsonData");
				List deleteData = InputLimitController.JsonToMap(deleteJsonData);
				Map param = new HashMap();
				param.put("tableId", tableId);
				param.put("deleteData", deleteData);
				inputLimitService.deleteAcctItemTreeData(param);
				return "删除成功";
			}catch(Exception e){
				return "删除操作出现错误!";
			}
		}		
		@RequestMapping(value="saveAcctItemTreeData")
		@ResponseBody
		public Object saveAcctItemTreeData(HttpServletRequest request,HttpServletResponse response) throws Exception{
			try{
				String editTableDatas = request.getParameter("editTableDatas");
				String addTableDatas = request.getParameter("addTableDatas");
				List editDatasList = InputLimitController.JsonToMap(editTableDatas);
				List addDatasList = InputLimitController.JsonToMap(addTableDatas);
				inputLimitService.saveAcctItemTreeData(editDatasList,addDatasList);
				return "保存成功";
			}catch(Exception e){
				return "保存操作出现错误!";
			}
		}	
		
	    public static List JsonToMap(String jsonList) throws IOException, Exception {
	        List l = new ArrayList();
	        JSONArray jsonArray = new JSONArray(jsonList);
	        for (int i = 0; i < jsonArray.length(); i++) {
	            JSONObject jsonObject = jsonArray.getJSONObject(i);
	            Map map = new HashMap();
	            Iterator keyIter = jsonObject.keys();
	            while (keyIter.hasNext()) {
	                String key = (String) keyIter.next();
	                Object value = (Object) jsonObject.get(key);
	                if (null != value && JSONObject.NULL != value) {
	                    map.put(key, value.toString());
	                } else {
	                    map.put(key, null);
	                }
	            }
	            l.add(map);
	        }
	        return l;
	    }
	    /** 
	     * 将一个 JavaBean 对象转化为一个  Map 
	     * @param bean 要转化的JavaBean 对象 
	     * @return 转化出来的  Map 对象 
	     * @throws IntrospectionException 如果分析类属性失败 
	     * @throws IllegalAccessException 如果实例化 JavaBean 失败 
	     * @throws InvocationTargetException 如果调用属性的 setter 方法失败 
	     */ 
	    public static Map convertBean(Object bean) 
	            throws IntrospectionException, IllegalAccessException, InvocationTargetException { 
	        Class type = bean.getClass(); 
	        Map returnMap = new HashMap(); 
	        BeanInfo beanInfo = Introspector.getBeanInfo(type); 

	        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors(); 
	        for (int i = 0; i< propertyDescriptors.length; i++) { 
	            PropertyDescriptor descriptor = propertyDescriptors[i]; 
	            String propertyName = descriptor.getName(); 
	            if (!propertyName.equals("class")) { 
	                Method readMethod = descriptor.getReadMethod(); 
	                Object result = readMethod.invoke(bean, new Object[0]); 
	                if (result != null) { 
	                    returnMap.put(propertyName, result); 
	                } else { 
	                    returnMap.put(propertyName, ""); 
	                } 
	            } 
	        } 
	        return returnMap; 
	    } 
}
