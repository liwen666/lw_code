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
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.po.DictTSuitPO;
import com.tjhq.commons.dict.external.service.IDictTSuitService;
import com.tjhq.commons.inputcomponent.grid.settinggrid.service.ISettingGridService;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.secu.po.SecuTLockPO;
import com.tjhq.commons.secu.po.SecuTRowPO;
import com.tjhq.commons.secu.po.TreeNodeForTable;
import com.tjhq.commons.secu.service.ILockSettingService;
@Controller
@RequestMapping(value = "/commons/secu/lockSetting")
public class LockSettingController {
		
		@Resource
		private ILockSettingService lockSettingService;
		
		@Resource
		private IDictTSuitService dictTSuitService;
		
		@Resource
		private ISettingGridService settingGrid;
		
		@RequestMapping(value="")
		public String page() {
			return "/commons/secu/lockSettingC"; 
		}
		
		
		/**
		 * 司局锁查询
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="deptLock/list")
		@ResponseBody
		public Object getDeptLockList(HttpServletRequest request,HttpServletResponse response,String grid) throws Exception{
			Grid data = (Grid)(new ObjectMapper()).readValue(grid,Grid.class);
			String appId = (String) data.getExtProperties().get("appId");
			String queryParam = (String) data.getExtProperties().get("parameter");
			List<SecuTLockPO> deptLocks = lockSettingService.getDeptLockList(appId, queryParam);
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();  
			for(SecuTLockPO po:deptLocks){ 
				dataList.add(convertBean(po)); 
			}
			settingGrid.transferGridData(dataList, data.getPageInfo());                                                                       
			return data.getPageInfo();   
		}
		
		/**
		 * 司局锁加锁
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="deptLock/addLock")
		@ResponseBody
		public Object addLockForDept(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String appId = request.getParameter("appId");
			String lockDatasJson = request.getParameter("lockDatas");
			List<Map> lockDatas = JsonToMap(lockDatasJson);
			lockSettingService.addLockForDept(lockDatas,appId);
			//List<SecuTLockPO> deptLocks = lockSettingService.getDeptLockList(appId, queryParam);
			return "加锁成功";
		}
		
		/**
		 * 司局锁解锁
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="deptLock/dropLock")
		@ResponseBody
		public Object dropLockForDept(HttpServletRequest request,HttpServletResponse response) throws Exception{
			JSONObject jsonObj=null;
			String appId = request.getParameter("appId");
			String unLockDatasJson = request.getParameter("unLockDatas");
			List<Map> unlockDatas = JsonToMap(unLockDatasJson);
			lockSettingService.dropLockForDept(unlockDatas,appId);
			return "解锁成功";
		}
		
		
		/**
		 * 部门套表锁加锁
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="agencySuitLock/addLock")
		@ResponseBody
		public Object addLockForAgencySuit(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String appId = request.getParameter("appId");
			String lockDatasJson = request.getParameter("lockDatas");
			List<Map> lockDatas = JsonToMap(lockDatasJson);
			lockSettingService.addLockForAgencySuit(lockDatas,appId);
			//List<SecuTLockPO> deptLocks = lockSettingService.getDeptLockList(appId, queryParam);
			return "加锁成功";
		}
		
		/**
		 * 部门套表锁解锁
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="agencySuitLock/dropLock")
		@ResponseBody
		public Object dropLockForAgencySuit(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String appId = request.getParameter("appId");
			String suitId = request.getParameter("tableId");
			String unLockDatasJson = request.getParameter("unLockDatas");
			List<Map> unlockDatas = JsonToMap(unLockDatasJson);
			lockSettingService.dropLockForAgencySuit(unlockDatas,appId,suitId);
			return "解锁成功";
		}
		
		
		/**
		 * 部门套表锁加锁
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="deptSuitLock/addLock")
		@ResponseBody
		public Object addLockForDeptSuit(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String appId = request.getParameter("appId");
			String lockDatasJson = request.getParameter("lockDatas");
			List<Map> lockDatas = JsonToMap(lockDatasJson);
			lockSettingService.addLockForDeptSuit(lockDatas,appId);
			//List<SecuTLockPO> deptLocks = lockSettingService.getDeptLockList(appId, queryParam);
			return "加锁成功";
		}
		
		/**
		 * 部门套表锁解锁
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="deptSuitLock/dropLock")
		@ResponseBody
		public Object dropLockForDeptSuit(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String appId = request.getParameter("appId");
			String suitId = request.getParameter("tableId");
			String unLockDatasJson = request.getParameter("unLockDatas");
			List<Map> unlockDatas = JsonToMap(unLockDatasJson);
			lockSettingService.dropLockForDeptSuit(unlockDatas,appId,suitId);
			return "解锁成功";
		}
		
		
		/**
		 * 部门锁加锁
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="agencyLock/addLock")
		@ResponseBody
		public Object addLockForAgency(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String appId = request.getParameter("appId");
			String lockDatasJson = request.getParameter("lockDatas");
			List<Map> lockDatas = JsonToMap(lockDatasJson);
			lockSettingService.addLockForAgency(lockDatas,appId);
			//List<SecuTLockPO> deptLocks = lockSettingService.getDeptLockList(appId, queryParam);
			return "加锁成功";
		}
		
		/**
		 * 部门锁解锁
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="agencyLock/dropLock")
		@ResponseBody
		public Object dropLockForAgency(HttpServletRequest request,HttpServletResponse response) throws Exception{
			JSONObject jsonObj=null;
			String appId = request.getParameter("appId");
			String unLockDatasJson = request.getParameter("unLockDatas");
			List<Map> unlockDatas = JsonToMap(unLockDatasJson);
			lockSettingService.dropLockForAgency(unlockDatas,appId);
			return "解锁成功";
		}
		/**
		 * 科目锁加锁
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="acctItemLock/addLock")
		@ResponseBody
		public Object addLockForAcctItem(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String appId = request.getParameter("appId");
			String lockDatasJson = request.getParameter("lockDatas");
			List<Map> lockDatas = JsonToMap(lockDatasJson);
			lockSettingService.addLockForAcctItem(lockDatas,appId);
			//List<SecuTLockPO> deptLocks = lockSettingService.getDeptLockList(appId, queryParam);
			return "加锁成功";
		}
		
		/**
		 * 科目锁解锁
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="acctItemLock/dropLock")
		@ResponseBody
		public Object dropLockForAcctItem(HttpServletRequest request,HttpServletResponse response) throws Exception{
			JSONObject jsonObj=null;
			String appId = request.getParameter("appId");
			String unLockDatasJson = request.getParameter("unLockDatas");
			List<Map> unlockDatas = JsonToMap(unLockDatasJson);
			lockSettingService.dropLockForAcctItem(unlockDatas,appId);
			return "解锁成功";
		}
		/**
		 * 司局锁查询
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="deptLock/list/head")
		@ResponseBody
		public Object getDeptLockListHead(HttpServletRequest request,HttpServletResponse response) throws Exception{
			
			/*Grid head = lockSettingService.getDeptLockListHead();
			return head.getDefine();*/
			Table grid = lockSettingService.getDeptLockListHead(); 
			UserDTO user = SecureUtil.getCurrentUser();
			Table table = settingGrid.initStructure(grid, user.getGuid());
			return table;
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
		 * 部门锁查询
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="agencyLock/list/head")
		@ResponseBody
		public Object getAgencyLockListHead(HttpServletRequest request,HttpServletResponse response) throws Exception{
			Table grid = lockSettingService.getAgencyLockListHead();   
			UserDTO user = SecureUtil.getCurrentUser();
			Table table = settingGrid.initStructure(grid, user.getGuid());
			return table;
		}
		
	    /**
		 * 部门锁查询
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="agencyLock/list")
		@ResponseBody
		public Object getAgencyLockList(HttpServletRequest request,HttpServletResponse response,String grid) throws Exception{
			/*JSONObject jsonObj=null;
			String appId = request.getParameter("appId");  
			String queryParam = request.getParameter("parameter");
			List<SecuTLockPO> agencyLocks = lockSettingService.getAgencyLockList(appId, queryParam);
			if(agencyLocks== null || agencyLocks.size()==0){
				return Grid.getData(1, new ArrayList());
			}
			return Grid.getData(1, agencyLocks);*/
			Grid data = (Grid)(new ObjectMapper()).readValue(grid,Grid.class);
			String appId = (String) data.getExtProperties().get("appId");
			String queryParam = (String) data.getExtProperties().get("parameter");
			List<SecuTLockPO> deptLocks = lockSettingService.getAgencyLockList(appId, queryParam);
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();  
			for(SecuTLockPO po:deptLocks){ 
				dataList.add(convertBean(po)); 
			}
			settingGrid.transferGridData(dataList, data.getPageInfo());                                                                       
			return data.getPageInfo();   
		}
		
		/**
		 * 科目锁查询
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="acctItemLock/list/head")
		@ResponseBody
		public Object getAcctItemLockListHead(HttpServletRequest request,HttpServletResponse response) throws Exception{
			/*Grid head = lockSettingService.getAcctItemLockListHead();
			return head.getDefine();*/
			
			Table grid = lockSettingService.getAcctItemLockListHead(); 
			UserDTO user = SecureUtil.getCurrentUser();
			Table table = settingGrid.initStructure(grid, user.getGuid());
			return table;
		}
		
	    /**
		 * 科目锁查询
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="acctItemLock/list")
		@ResponseBody
		public Object getAcctItemLockList(HttpServletRequest request,HttpServletResponse response,String grid) throws Exception{
			/*
				JSONObject jsonObj=null; 
				String appId = request.getParameter("appId");
				String queryParam = request.getParameter("parameter");
				List<SecuTLockPO> acctItemLocks = lockSettingService.getAcctItemLockList(appId, queryParam);
				if(acctItemLocks== null || acctItemLocks.size()==0){
					return Grid.getData(1, new ArrayList());
				}
				return Grid.getData(1, acctItemLocks); 
			*/
			Grid data = (Grid)(new ObjectMapper()).readValue(grid,Grid.class);
			String appId = (String) data.getExtProperties().get("appId");
			String queryParam = (String) data.getExtProperties().get("parameter");
			List<SecuTLockPO> acctItemLocks =  lockSettingService.getAcctItemLockList(appId, queryParam);
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			for(SecuTLockPO po:acctItemLocks){ 
				dataList.add(convertBean(po));    
			}
			settingGrid.transferGridData(dataList, data.getPageInfo());                                                                                                         
			return data.getPageInfo();  
		}
		/**
		 * 全局锁查询
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="appLock/list/head")
		@ResponseBody
		public Object getAppLockListHead(HttpServletRequest request,HttpServletResponse response) throws Exception{
			/*
			Grid head = lockSettingService.getAppLockListHead();
			return head.getDefine();*/
			Table grid = lockSettingService.getAppLockListHead();
			UserDTO user = SecureUtil.getCurrentUser();
			Table table = settingGrid.initStructure(grid, user.getGuid());
			return table;
		}
	    /**
		 * 全局锁查询
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="appLock/list")
		@ResponseBody
		public Object getAppLockList(HttpServletRequest request,HttpServletResponse response,String grid) throws Exception{
			/*String queryParam = request.getParameter("parameter");
			List<SecuTLockPO> agencyLocks = lockSettingService.getAppLockListHead(queryParam);
			if(agencyLocks== null || agencyLocks.size()==0){
				return Grid.getData(1, new ArrayList());
			}
			return Grid.getData(1, agencyLocks);*/
			Grid data = (Grid)(new ObjectMapper()).readValue(grid,Grid.class);
			//String appId = (String) data.getExtProperties().get("appId");
			String queryParam = (String) data.getExtProperties().get("parameter");
			List<SecuTLockPO> agencyLocks = lockSettingService.getAppLockListHead(queryParam);
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			for(SecuTLockPO po:agencyLocks){ 
				dataList.add(convertBean(po));    
			}
			settingGrid.transferGridData(dataList, data.getPageInfo());                                                                                                         
			return data.getPageInfo();  
		}
		
		/**
		 * 报表锁查询
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="tableLock/list/head")
		@ResponseBody
		public Object getTableLockListHead(HttpServletRequest request,HttpServletResponse response) throws Exception{
			/*Grid head = lockSettingService.getTableLockListHead();
			return head.getDefine();*/
			Table grid = lockSettingService.getTableLockListHead();
			UserDTO user = SecureUtil.getCurrentUser();
			Table table = settingGrid.initStructure(grid, user.getGuid());
			return table;
		}
	    /**
		 * 报表锁查询
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="tableLock/list")
		@ResponseBody
		public Object getTableLockList(HttpServletRequest request,HttpServletResponse response,String grid) throws Exception{
			/*String queryParam = request.getParameter("parameter");
			String tableId =  request.getParameter("tableId");
			String isSuit = request.getParameter("isSuit");
			String appId = request.getParameter("appId");
			Map<String,String> param = new HashMap<String,String>();
			param.put("tableId", tableId);
			param.put("queryParam", queryParam);
			param.put("appId", appId);
			List<SecuTLockPO> tableLocks = lockSettingService.getTableLockList(param,isSuit,queryParam);
			if(tableLocks== null || tableLocks.size()==0){
				return Grid.getData(1, new ArrayList());
			}
			return Grid.getData(1, tableLocks);*/
			Grid data = (Grid)(new ObjectMapper()).readValue(grid,Grid.class); 
			String appId = (String) data.getExtProperties().get("appId");
			String queryParam = (String) data.getExtProperties().get("parameter");
			String isSuit = String.valueOf(data.getExtProperties().get("isSuit"));
			String tableId = data.getExtProperties().get("tableId").toString();    
			Map<String,String> param = new HashMap<String,String>();
			param.put("tableId", tableId);
			param.put("queryParam", queryParam);
			param.put("appId", appId);
			List<SecuTLockPO> tableItemLocks =  lockSettingService.getTableLockList(param,isSuit,queryParam);
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			for(SecuTLockPO po:tableItemLocks){ 
				dataList.add(convertBean(po));    
			}
			settingGrid.transferGridData(dataList, data.getPageInfo());                                                                                                         
			return data.getPageInfo();  
		}
		/**
		 * 部门套表锁查询
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="agencySuitLock/list/head")
		@ResponseBody
		public Object getAgencySuitLockListHead(HttpServletRequest request,HttpServletResponse response) throws Exception{
			/*Grid head = lockSettingService.getAgencySuitLockListHead();
			return head.getDefine();*/
			Table grid = lockSettingService.getAgencySuitLockListHead();
			UserDTO user = SecureUtil.getCurrentUser();
			Table table = settingGrid.initStructure(grid, user.getGuid());
			return table;
		}
	    /**
		 * 部门套表锁查询
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="agencySuitLock/list")
		@ResponseBody
		public Object getAgencySuitLockList(HttpServletRequest request,HttpServletResponse response,String grid) throws Exception{
			/*
				String queryParam = request.getParameter("parameter");
				String tableId =  request.getParameter("tableId");
				String appId = request.getParameter("appId");
				Map<String,String> param = new HashMap<String,String>();
				param.put("tableId", tableId);
				param.put("queryParam", queryParam);
				param.put("appId", appId);
				List<SecuTLockPO> agencySuitLocks = lockSettingService.getAgencySuitLockList(param,queryParam,appId);
				if(agencySuitLocks== null || agencySuitLocks.size()==0){
					return Grid.getData(1, new ArrayList());
				}
				return Grid.getData(1, agencySuitLocks);
			*/
			Grid data = (Grid)(new ObjectMapper()).readValue(grid,Grid.class);   
			String appId = (String) data.getExtProperties().get("appId");        
			String queryParam = (String) data.getExtProperties().get("parameter");
			String tableId = data.getExtProperties().get("tableId").toString();      
			Map<String,String> param = new HashMap<String,String>();
			param.put("tableId", tableId);
			param.put("queryParam", queryParam);
			param.put("appId", appId);
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			if(tableId.equals("")){
				settingGrid.transferGridData(dataList, data.getPageInfo());  
			}else{
				List<SecuTLockPO> agencySuitLocks = lockSettingService.getAgencySuitLockList(param,queryParam,appId);
				for(SecuTLockPO po:agencySuitLocks){ 
					dataList.add(convertBean(po));    
				}
				settingGrid.transferGridData(dataList, data.getPageInfo());  
			}	
			return data.getPageInfo();    
		}
		/**
		 * 司局套表锁查询
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="deptSuitLock/list/head")
		@ResponseBody
		public Object getDeptSuitLockListHead(HttpServletRequest request,HttpServletResponse response) throws Exception{
			/*Grid head = lockSettingService.getDeptSuitLockListHead();
			return head.getDefine();*/
			Table grid =  lockSettingService.getDeptSuitLockListHead(); 
			UserDTO user = SecureUtil.getCurrentUser();
			Table table = settingGrid.initStructure(grid, user.getGuid());
			return table;
		}
	    /**
		 * 司局套表锁查询
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="deptSuitLock/list")
		@ResponseBody
		public Object getDeptSuitLockList(HttpServletRequest request,HttpServletResponse response,String grid) throws Exception{
			Grid data = (Grid)(new ObjectMapper()).readValue(grid,Grid.class); 
			String appId = (String) data.getExtProperties().get("appId");
			String queryParam = (String) data.getExtProperties().get("parameter");
			String tableId = data.getExtProperties().get("tableId").toString();    
			Map<String,String> param = new HashMap<String,String>();
			param.put("tableId", tableId);
			param.put("queryParam", queryParam);
			param.put("appId", appId);
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			if(tableId.equals("")){
				settingGrid.transferGridData(dataList, data.getPageInfo());                                                                                                         
				return data.getPageInfo(); 
			}
			List<SecuTLockPO> agencySuitLocks = lockSettingService.getDeptSuitLockList(param,queryParam);
			for(SecuTLockPO po:agencySuitLocks){ 
				dataList.add(convertBean(po));     
			}
			settingGrid.transferGridData(dataList, data.getPageInfo());                                                                                                         
			return data.getPageInfo();  
		}
		
		/**
		 * 报表锁-报表树查询
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="tableLock/getTableList")
		@ResponseBody
		public Object getTableList(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String appId = request.getParameter("appId");
			List<DictTSuitPO> tables = dictTSuitService.getDictTSuits(appId, "0", true);
			List<TreeNodeForTable> treeList = new ArrayList<TreeNodeForTable>();

			getTableTreeData(treeList,tables);
			
			return treeList; 
		}
		/**
		 * 部门套表锁-套表树查询
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="agencySuitLock/getTableList")
		@ResponseBody
		public Object getAgencySuitTableList(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String appId = request.getParameter("appId");
			List<DictTSuitPO> tables = dictTSuitService.getDictTSuits(appId, "0", false);
			List<TreeNodeForTable> treeList = new ArrayList<TreeNodeForTable>();
			getTableTreeData(treeList,tables);
			return treeList; 
		}
		
		
		/**
		 * 司局套表锁-套表树查询
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="deptSuitLock/getTableList")
		@ResponseBody
		public Object getDeptSuitTableList(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String appId = request.getParameter("appId");
			List<DictTSuitPO> tables = dictTSuitService.getDictTSuits(appId, "0", false);
			List<TreeNodeForTable> treeList = new ArrayList<TreeNodeForTable>();

			getTableTreeData(treeList,tables);
			
			return treeList; 
		}
		
		
		/**
		 * 全局锁加锁
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="appLock/addLock")
		@ResponseBody
		public Object addLockForApp(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String lockDatasJson = request.getParameter("lockDatas");
			List<Map> lockDatas = JsonToMap(lockDatasJson);
			lockSettingService.addLockForApp(lockDatas);
			//List<SecuTLockPO> deptLocks = lockSettingService.getDeptLockList(appId, queryParam);
			return "加锁成功";
		}
		
		/**
		 * 全局锁解锁
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="appLock/dropLock")
		@ResponseBody
		public Object dropLockForApp(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String unLockDatasJson = request.getParameter("unLockDatas");
			List<Map> unlockDatas = JsonToMap(unLockDatasJson);
			lockSettingService.dropLockForApp(unlockDatas);
			return "解锁成功";
		}
		
		
		

		/**
		 * 报表锁加锁
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="tableLock/addLock")
		@ResponseBody
		public Object addLockForTable(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String lockDatasJson = request.getParameter("lockDatas");
			String appId = request.getParameter("appId");
			List<Map> lockDatas = JsonToMap(lockDatasJson);
			lockSettingService.addLockForTable(lockDatas,appId);
			//List<SecuTLockPO> deptLocks = lockSettingService.getDeptLockList(appId, queryParam);
			return "加锁成功";
		}
		
		/**
		 * 报表锁解锁
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="tableLock/dropLock")
		@ResponseBody
		public Object dropLockForTable(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String unLockDatasJson = request.getParameter("unLockDatas");
			List<Map> unlockDatas = JsonToMap(unLockDatasJson);
			lockSettingService.dropLockForTable(unlockDatas);
			return "解锁成功";
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
		   
	    /** 
	     * 将一个 JavaBean 对象转化为一个  Map 
	     * @param bean 要转化的JavaBean 对象 
	     * @return 转化出来的  Map 对象 
	     * @throws IntrospectionException 如果分析类属性失败 
	     * @throws IllegalAccessException 如果实例化 JavaBean 失败 
	     * @throws InvocationTargetException 如果调用属性的 setter 方法失败 
	     */ 
	    public static Map<String, Object> convertBean(Object bean) 
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
