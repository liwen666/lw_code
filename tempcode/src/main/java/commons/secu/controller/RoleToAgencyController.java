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

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.po.DictTSuitPO;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.dict.external.service.IDictTSuitService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.grid.settinggrid.service.ISettingGridService;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.secu.po.SecuTColPO;
import com.tjhq.commons.secu.po.SecuTRowPO;
import com.tjhq.commons.secu.po.SecuTTablePO;
import com.tjhq.commons.secu.po.TreeNode;
import com.tjhq.commons.secu.po.TreeNodeForTable;
import com.tjhq.commons.secu.service.IRoleToAgencyService;
@Controller
@RequestMapping(value = "/commons/secu/roleToAgency")
public class RoleToAgencyController {
		
		@Resource
		private IDictTSuitService dictTSuitService;
		
		@Resource
		private IRoleToAgencyService roleToAgencyService;
		
		@Resource
		private IDictTModelService dictTModelService; 
		
		@Resource
		private ISettingGridService settingGrid;
		
		@RequestMapping(value="")
		public String page(Model model,String type) {
			//取app
			List dictApps = dictTSuitService.getAllDictTAppregister();
			model.addAttribute("apps", dictApps); 
			//type=1角色对单位 type=2用户对单位
			if(type!=null&&type.equals("2")){ 
				return "/commons/secu/userToAgency";  
			}else{
				return "/commons/secu/roleRightMgt";  
			}
		}
		
		@RequestMapping(value="tableSecuList/head")
		@ResponseBody
		public Object getTableSecuListHead(HttpServletRequest request,HttpServletResponse response) throws Exception{
			//Grid head = roleToAgencyService.getTableListHead();
			//return head.getDefine(); 
			Table grid = roleToAgencyService.setTableSecuDefine();
			UserDTO user = SecureUtil.getCurrentUser();
			Table table = settingGrid.initStructure(grid, user.getGuid());
			return table;	
		}
		
		
		@RequestMapping(value="tableSecuList/list")
		@ResponseBody
		public Object getTableSecuList(HttpServletRequest request,HttpServletResponse response,String grid) throws Exception{
			
			
			/*String roleId = request.getParameter("roleId"); 
			if(roleId == null || "".equals(roleId)){
				return null;
			}
			List<SecuTTablePO> tableSecuList = roleToAgencyService.getTableSecuList(roleId);
			if(tableSecuList== null || tableSecuList.size()==0){
				return Grid.getData(1, new ArrayList<SecuTTablePO>());
			}
			return  Grid.getData(1, tableSecuList);*/
			
			Grid data = (Grid)(new ObjectMapper()).readValue(grid,Grid.class);
			String roleId = data.getExtProperties().get("roleId").toString();
			String appId = data.getExtProperties().get("appId").toString();
			List<SecuTTablePO> tableSecuList = roleToAgencyService.getTableSecuList(roleId,appId); 
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			for(SecuTTablePO po:tableSecuList){
				dataList.add(convertBean(po));
			}
			settingGrid.transferGridData(dataList, data.getPageInfo()); 
			return data.getPageInfo();
		}
		
		
		
		@RequestMapping(value="tableColSecuList/head")
		@ResponseBody
		public Object getTableColSecuListHead(HttpServletRequest request,HttpServletResponse response) throws Exception{
			//Grid head = roleToAgencyService.getTableColSecuListHead();
			Table grid = roleToAgencyService.setColSecuDefine(); 
			UserDTO user = SecureUtil.getCurrentUser();
			Table table = settingGrid.initStructure(grid, user.getGuid());
			return table;
		}
		
		
		@RequestMapping(value="tableColSecuList/list")
		@ResponseBody
		public Object getTableColSecuList(HttpServletRequest request,HttpServletResponse response) throws Exception{
			JSONObject jsonObj=null;
			String appId = request.getParameter("appId"); 
			String queryParam = request.getParameter("parameter");
			//List<SecuTLockPO> deptLocks = lockSettingService.getDeptLockList(appId, queryParam);
			return null;
		}
		
		@RequestMapping(value="tableRowSecuList/head")
		@ResponseBody
		public Object getTableRowSecuListHead(HttpServletRequest request,HttpServletResponse response) throws Exception{
			//Grid head = roleToAgencyService.getTableRowSecuListHead();
			Table grid = roleToAgencyService.setRowSecuSecuDefine(); 
			UserDTO user = SecureUtil.getCurrentUser();
			Table table = settingGrid.initStructure(grid, user.getGuid());
			return table;
		}
		
		@RequestMapping(value="tableColList/head")
		@ResponseBody
		public Object getTableColListHead(HttpServletRequest request,HttpServletResponse response) throws Exception{
			//Grid head = roleToAgencyService.getTableColListHead();
			Table grid = roleToAgencyService.setColTableDefine(); 
			UserDTO user = SecureUtil.getCurrentUser();
			Table table = settingGrid.initStructure(grid, user.getGuid());
			return table;	
		}
		
		@RequestMapping(value="getRoleToAgencyList")
		@ResponseBody
		public Object getRoleToAgencyList(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String roleId = request.getParameter("roleId");
			String appId = request.getParameter("appId");
			String manType = request.getParameter("manType");
			Map<String,List<TreeNode>> roleToAgencyList = roleToAgencyService.getRoleToAgencyList(roleId,appId,manType);
			return roleToAgencyList;
		}
		
		@RequestMapping(value="addSelectedAgencyToRole")
		@ResponseBody
		public Object addSelectedAgencyToRole(HttpServletRequest request,HttpServletResponse response) throws Exception{
			try{
				String roleId = request.getParameter("roleId");
				String appId = request.getParameter("appId");
				String selectAgencyJson = request.getParameter("selectAgencyList");
				String mantype = request.getParameter("manType");
				List<Map> selectAgencyList = JsonToMap(selectAgencyJson);
				if(mantype==null){
					mantype="1"; 
				}
				roleToAgencyService.addSelectedAgencyToRole(selectAgencyList,roleId,appId,mantype);
				return "添加选中单位成功";
			}catch(Exception e){
				return "添加选择单位出错!";
			}
		}
		
		
		@RequestMapping(value="removeSelectedAgencyToRole")
		@ResponseBody
		public Object removeSelectedAgencyToRole(HttpServletRequest request,HttpServletResponse response) throws Exception{
			try{
				String roleId = request.getParameter("roleId");
				String appId = request.getParameter("appId");
				String manType = request.getParameter("manType");
				String unselectAgencyJson = request.getParameter("unSelectAgencyList");
				List<Map> unselectAgencyList = JsonToMap(unselectAgencyJson);
				roleToAgencyService.removeSelectedAgencyToRole(unselectAgencyList,roleId,appId, manType);
				return "取消选中单位成功"; 
			}catch(Exception e){
				return "取消选择单位出错!";
			}
		}
		
		
		@RequestMapping(value="deleteTableSecuLimit")
		@ResponseBody
		public Object deleteTableSecuLimit(HttpServletRequest request,HttpServletResponse response) throws Exception{
			try{
				String roleId = request.getParameter("roleId");
				String appId = request.getParameter("appId");
				String tableId = request.getParameter("tableId");
				String isSuit = request.getParameter("isSuit");
				roleToAgencyService.deleteTableSecuLimit(roleId,appId,tableId,isSuit); 
				return "删除成功";
			}catch(Exception e){
				return "删除出错!";
			}
		}
		
		@RequestMapping(value="getTableTreeData")
		@ResponseBody
		public Object getTableTreeDataList(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String appId = request.getParameter("appId");
			List<DictTSuitPO> tables = dictTSuitService.getDictTSuits(appId, "0", true);
			List<TreeNodeForTable> treeList = new ArrayList<TreeNodeForTable> ();
			getTableTreeData(treeList,tables);
			//return (new ObjectMapper()).writeValueAsString(treeList);
			return treeList;

		}
		
		@RequestMapping(value="getRoleTreeData")
		@ResponseBody
		public Object getRoleTreeData(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String appId="BGT";
			UserDTO user = SecureUtil.getCurrentUser();
			String userId = user.getGuid();
			String roleType = request.getParameter("roleType");
			String fromRoleID = request.getParameter("fromRoleID");
			List<Map<String,String>> roleDatas = roleToAgencyService.getRoleTreeData(appId,userId,roleType);
			List<Map<String,String>> newRoleDatas = new ArrayList<Map<String,String>>();
			for(Map<String,String> map: roleDatas){
				if(!(StringUtils.isNotEmpty(fromRoleID) && fromRoleID.equals(map.get("GUID")))){
					map.put("name", map.get("NAME"));  
					map.put("pId", map.get("SUPERGUID"));
					map.put("id", map.get("GUID"));
					newRoleDatas.add(map);
				}
			}
			return newRoleDatas;
		}
		
		@RequestMapping(value="getUserTreeData")
		@ResponseBody
		public Object getUserTreeData(HttpServletRequest request,HttpServletResponse response) throws Exception{
			UserDTO user = SecureUtil.getCurrentUser(); 
			String userId = user.getGuid();
			String roleType = request.getParameter("roleType");
			List<Map<String,String>> roleDatas = roleToAgencyService.getUserTreeData();
			for(Map<String,String> map:roleDatas){
				map.put("name", map.get("NAME"));  
				map.put("pId", map.get("SUPERGUID"));
				map.put("id", map.get("GUID"));  
				map.put("leaf", map.get("LEAF"));     
			} 
			return roleDatas;
		}
		
		
		@RequestMapping(value="getTableListBySuitId")
		@ResponseBody
		public Object getTableListBySuitId(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String appId = request.getParameter("appId");
			String suitId = request.getParameter("suitId");
			List<DictTModelPO> tables = dictTModelService.getDictTModelsBySuitId(suitId, false);
			//return (new ObjectMapper()).writeValueAsString(tables);
			return tables;

		}
		
		//根据表获得列权限 
		@RequestMapping(value="getColListByTableId")
		@ResponseBody
		public Object getColListByTableId(HttpServletRequest request,HttpServletResponse response,String grid) throws Exception{
			Grid data = (Grid)(new ObjectMapper()).readValue(grid,Grid.class);
			String roleId = data.getExtProperties().get("roleId").toString();
			String tableId = data.getExtProperties().get("tableId").toString();
			List<SecuTColPO> cols = roleToAgencyService.getColLimitListByTableId(tableId,roleId);
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();  
			for(SecuTColPO po:cols){
				dataList.add(convertBean(po));
			}
			settingGrid.transferGridData(dataList, data.getPageInfo()); 
			return data.getPageInfo();
		}
		
		
		@RequestMapping(value="getTableColList")
		@ResponseBody
		public Object getTableColList(HttpServletRequest request,HttpServletResponse response,String grid) throws Exception{
			Grid data = (Grid)(new ObjectMapper()).readValue(grid,Grid.class); 
			String roleId = data.getExtProperties().get("roleId").toString();
			String tableId = data.getExtProperties().get("tableId").toString();
			//List<DictTFactorPO> cols = dictTFactorService.getDictTFactorsByTableId(tableId);
			List<SecuTColPO> cols = roleToAgencyService.getColLimitListByTableId(tableId,roleId);
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();  
			for(SecuTColPO po:cols){
				dataList.add(convertBean(po)); 
			}
			settingGrid.transferGridData(dataList, data.getPageInfo()); 
			return data.getPageInfo(); 
		}
		
		
		@RequestMapping(value="getTableRowSecuList")
		@ResponseBody
		public Object getTableRowSecuList(HttpServletRequest request,
				HttpServletResponse response,String grid) throws Exception{
			Grid data = (Grid)(new ObjectMapper()).readValue(grid,Grid.class);
			String roleId = data.getExtProperties().get("roleId").toString();
			String tableId = data.getExtProperties().get("tableId").toString();
			List<SecuTRowPO> cols = roleToAgencyService.getRowLimitListByTableId(tableId,roleId);
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();  
			for(SecuTRowPO po:cols){ 
				dataList.add(convertBean(po)); 
			}
			settingGrid.transferGridData(dataList, data.getPageInfo());                                                                       
			return data.getPageInfo();  
		}
		
		@RequestMapping(value="saveOrUpdateTableRowLimit")
		@ResponseBody
		public Object saveTableRowLimit(HttpServletRequest request,HttpServletResponse response) throws Exception{
			try{
				String roleId = request.getParameter("roleId");
				String tableId =request.getParameter("tableId");
				String baseSecu =request.getParameter("baseSecu");
				String sqlWhere =request.getParameter("sqlWhere");
				String operationFlag = request.getParameter("operationFlag");
				Map<String,String> param = new HashMap<String,String>();
				param.put("roleId", roleId);
				param.put("tableId", tableId);
				param.put("baseSecu", baseSecu);
				param.put("sqlWhere", sqlWhere);
				if("add".equalsIgnoreCase(operationFlag)){
					roleToAgencyService.saveTableRowLimit(param);
				}else{
					roleToAgencyService.updateTableRowLimit(param);
				}
				return "保存成功";
			}catch(Exception e){
				return "保存失败";
			}
		}
		
		
		@RequestMapping(value="transferTableRowLimit")
		@ResponseBody
		public Object transferTableRowLimit(HttpServletRequest request,HttpServletResponse response) throws Exception{
			try{
				String tableId =  request.getParameter("tableId");
				String showWhere = request.getParameter("showWhere");
				return roleToAgencyService.transferTableRowLimit(tableId,showWhere); 
			}catch(Exception e){
				return "";
			}
		}
		
		@RequestMapping(value="verifyTableRowLimit")
		@ResponseBody
		public Object verifyTableRowLimit(HttpServletRequest request,HttpServletResponse response) throws Exception{
			try{
				String tableId =request.getParameter("tableId");
				DictTModelPO table = dictTModelService.getDictTModelByID(tableId, false);
				String sqlWhere =request.getParameter("sqlWhere");
				Map<String,String> param = new HashMap<String,String>();
				param.put("sqlWhere", sqlWhere);
				param.put("tableDBName",table.getDbtablename());
				param.put("tableId", tableId);
					roleToAgencyService.verifyTableRowLimit(param);
				return "公式正确";
			}catch(Exception e){
				return "公式不正确："+e.getMessage();
			}

		}
		@RequestMapping(value="cancelTableRowLimitLimit")
		@ResponseBody
		public Object cancelTableRowLimitLimit(HttpServletRequest request,HttpServletResponse response) throws Exception{
			try{
				String roleId = request.getParameter("roleId");
				String tableId =request.getParameter("tableId");
				String baseSecu =request.getParameter("baseSecu");
				Map<String,String> param = new HashMap<String,String>();
				param.put("roleId", roleId);
				param.put("tableId", tableId);
				param.put("baseSecu", baseSecu);
				roleToAgencyService.cancelTableRowLimitLimit(param);
				return "保存成功";
			}catch(Exception e){
				return "保存失败";
			}

		}
		@RequestMapping(value="saveTableSecuLimit")
		@ResponseBody
		public Object saveTableSecuLimit(HttpServletRequest request,HttpServletResponse response) throws Exception{
			try{
				String roleId = request.getParameter("roleId");
				String editTableColLimitDatas =request.getParameter("editTableColLimitDatas");
				String addTableSecuLimitDatas =request.getParameter("addTableSecuLimitDatas");
				String editTableSecuLimitDatas =request.getParameter("editTableSecuLimitDatas");
				
				List<Map<String,String>> editTableColLimitList = this.JsonToMap(editTableColLimitDatas);
				List<Map<String,String>> addTableSecuLimitList = this.JsonToMap(addTableSecuLimitDatas);
				List<Map<String,String>> editTableSecuLimitList = this.JsonToMap(editTableSecuLimitDatas);
				roleToAgencyService.saveTableSecuLimit(editTableColLimitList,addTableSecuLimitList,editTableSecuLimitList,roleId);
				return "保存成功";
			}catch(Exception e){
				return "保存失败";
			}

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
		
		
	    public List JsonToMap(String jsonList) throws IOException, Exception {
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

    @RequestMapping(value="copy")
	@ResponseBody
	public Object copy(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	//返回结果
    	ResultPO resultPO = null;
    	try {
    		String appID = request.getParameter("appID");
			String fromRoleID = request.getParameter("fromRoleID");
			String toRoleIDs = request.getParameter("toRoleIDs");
    		roleToAgencyService.copy(appID, fromRoleID, toRoleIDs);
    		resultPO = new ResultPO("复制成功");
    	} catch (ServiceException e) {
            e.printStackTrace();
            resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }
    	return resultPO;
	}
}
