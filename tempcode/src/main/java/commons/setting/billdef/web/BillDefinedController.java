package commons.setting.billdef.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjhq.commons.cache.service.IDataCacheService;
import com.tjhq.commons.dict.constants.DictCacheKey;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.jackson.ObjectMapper;
import com.tjhq.commons.setting.billdef.po.BillDefinedPO;
import com.tjhq.commons.setting.billdef.service.IBillDefinedService;

@Controller
@RequestMapping(value="/commons/setting/billdef")
public class BillDefinedController {
	@Resource
	private IBillDefinedService billDefinedService;
	@Resource
	private IDataCacheService dataCacheService;
	
	/**
	 * http://127.0.0.1:8001/spf/commons/setting/billdef/mpage.do?appId=SPF&all=1&tokenid=347581817096127C611BD534349174E2QN6TJKxW
	 * @param request
	 * @param response
	 * @param model
	 * @param appId
	 * @return
	 */
	@RequestMapping(value="mpage")
	public String getPage(HttpServletRequest request, HttpServletResponse response, ModelMap model, String appId, String all){
		model.addAttribute("appId", appId);
		model.addAttribute("all", all);
		return "commons/setting/billdef/billdefined";
	}
	
	/**
	 * 获取记账列表定义 
	 * @param appId
	 * @return
	 */
	@RequestMapping(value="getBillTableDefined")
	@ResponseBody
	public Object getBillTableDefined(String appId){
		ResultPO res = null;
		try {
			Object obj = billDefinedService.getBillTableDefined(appId);
			res = new ResultPO(obj);
		} catch (ServiceException e) {
			e.printStackTrace();
			res = new ResultPO(e.getCode(), e.getErrorMessage());
		}
		return res;
	}
	
	/**
	 * 获取记账定义列表数据
	 * @param grid
	 * @return
	 */
	@RequestMapping(value="getBillTableData")
	@ResponseBody
	public Object getBillTableData(String grid) {
		ResultPO res = null;
		try {
			Grid data = (Grid)(new ObjectMapper()).readJson(grid, Grid.class);
			Object obj = billDefinedService.getBillTableData(data);
			res = new ResultPO(obj);
		} catch (ServiceException e) {
			e.printStackTrace();
			res = new ResultPO(e.getCode(), e.getErrorMessage());
		}
		return res;
	}
	
	/**
	 * 获取model表树数据
	 * @param all
	 * @param appId
	 * @return
	 */
	@RequestMapping(value="getTableTree")
	@ResponseBody
	public List<Map<String, Object>> getTableTree(String all, String appId, String isDb){
		List<Map<String, Object>> treeList = null;
		try {
			treeList = billDefinedService.getTableTree(all, appId, isDb);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return treeList;
		
	}
	/**
	 * 获取factor列树数据
	 * @param tableId
	 * @return
	 */
	@RequestMapping(value="getColumnTree")
	@ResponseBody
	public List<Map<String, Object>> getColumnTree(String tableId){
		List<Map<String, Object>> treeList = null;
		try {
			treeList = billDefinedService.getColumnTree(tableId);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return treeList;
		
	}
	
	/**
	 * 获取factor列树数据
	 * @param tableId
	 * @return
	 */
	@RequestMapping(value="getMutiTree")
	@ResponseBody
	public List<Map<String, Object>> getMutiTree(String tableId, String billDefId){
		List<Map<String, Object>> treeList = null;
		try {
			treeList = billDefinedService.getColumnTree(tableId, billDefId);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return treeList;
		
	}
	/**
	 * 获取存储过程列表
	 * @param appId
	 * @return
	 */
	@RequestMapping(value="getProcList")
	@ResponseBody
	public List<Map<String, Object>> getProcList(String appId){
		Map<String, Object> params = new HashMap<String, Object>();
		List<Map<String, Object>> list = null;
		try {
			params.put("appId", appId);
			list = billDefinedService.getProcList(params);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 保存记账定义数据
	 * @param row
	 * @return
	 */
	@RequestMapping(value="saveData")
	@ResponseBody
	public Object saveData(String row){
		ResultPO res = null;
		try {
			BillDefinedPO po = (BillDefinedPO)(new ObjectMapper()).readJson(row, BillDefinedPO.class);
			Map<String, Object> data = billDefinedService.saveData(po);
			data.put("success", "保存成功！");
			res = new ResultPO(data);
		} catch (ServiceException e) {
			e.printStackTrace();
			res = new ResultPO(e.getCode(), e.getErrorMessage());
		}
		return res;
	}
	/**
	 * 检查where条件定义
	 * @param row
	 * @return
	 */
	@RequestMapping(value="checkWhere")
	@ResponseBody
	public Object checkWhere(String row){
		ResultPO res = null;
		try {
			BillDefinedPO po = (BillDefinedPO)(new ObjectMapper()).readJson(row, BillDefinedPO.class);
			Map<String, Object> data = billDefinedService.checkWhere(po);
			data.put("success", "保存成功！");
			res = new ResultPO(data);
		} catch (ServiceException e) {
			e.printStackTrace();
			res = new ResultPO(e.getCode(), e.getErrorMessage());
		}
		return res;
	}
	/**
	 * 生成来源表
	 * @param row
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="genSrcTable")
	@ResponseBody
	public Object genSrcTable(String row){
		ResultPO res = null;
		try {
			Map<String, Object> params = (Map<String, Object>)(new ObjectMapper()).readJson(row, Map.class);
			Map<String, Object> result = billDefinedService.execProc(params);
			// 清除所有业务表类缓存
			String[] allDICTKeys = {DictCacheKey.CACHE_KEY_DICT };
			dataCacheService.put(allDICTKeys, null);

			res = new ResultPO(result);
		} catch (ServiceException e) {
			e.printStackTrace();
			res = new ResultPO(e.getCode(), e.getErrorMessage());
		}
		return res;
	}
	
	/**
	 * 删除记账定义数据
	 * @param row
	 * @return
	 */
	@RequestMapping(value="deleteData")
	@ResponseBody
	public Object deleteData(String row){
		ResultPO res = null;
		try {
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> list = (List<Map<String, Object>>)(new ObjectMapper()).readJson(row, List.class);
			List<BillDefinedPO> delList = new ArrayList<BillDefinedPO>();
			for(Map<String, Object> po : list){
				BillDefinedPO bill = new BillDefinedPO();
				bill.setGuid(po.get("guid").toString());
				delList.add(bill);
			}
			billDefinedService.deleteBillDef(delList);
			Map<String, String> data = new HashMap<String, String>();
			data.put("success", "删除成功！");
			res = new ResultPO(data);
		} catch (ServiceException e) {
			e.printStackTrace();
			res = new ResultPO(e.getCode(), e.getErrorMessage());
		}
		return res;
	}
	
	/**
	 * 查询记账规则是否已经使用
	 * @return
	 */
	@RequestMapping(value="isConfirmfuncUse")
	@ResponseBody
	public Object isConfirmfuncUse(String row){
		ResultPO res = null;
		try {
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> list = (List<Map<String, Object>>)(new ObjectMapper()).readJson(row, List.class);
			res = new ResultPO(billDefinedService.getConfirmfuncUse(list));
		} catch (ServiceException e) {
			e.printStackTrace();
			res = new ResultPO(e.getCode(), e.getErrorMessage());
		}
		return res;
	}
	/**
	 * 获取动态属性列表定义
	 * @return
	 */
	@RequestMapping(value="getPropDefined")
	@ResponseBody
	public Object getPropDefined(){
		ResultPO res = null;
		try {
			Object obj = billDefinedService.getPropDefined();
			res = new ResultPO(obj);
		} catch (ServiceException e) {
			e.printStackTrace();
			res = new ResultPO(e.getCode(), e.getErrorMessage());
		}
		return res;
	}
	/**
	 * 获取动态属性列表数据
	 * @return
	 */
	@RequestMapping(value="getPropData")
	@ResponseBody
	public Object getPropData(String grid){
		ResultPO res = null;
		try {
			Grid data = (Grid)(new ObjectMapper()).readJson(grid, Grid.class);
			Object obj = billDefinedService.getPropData(data);
			res = new ResultPO(obj);
		} catch (ServiceException e) {
			e.printStackTrace();
			res = new ResultPO(e.getCode(), e.getErrorMessage());
		}
		return res;
	}
	
	/**
	 * 获取目标表列绑定列表定义
	 * @return
	 */
	@RequestMapping(value="getColsDefined")
	@ResponseBody
	public Object getColsDefined(){
		ResultPO res = null;
		try {
			Object obj = billDefinedService.getColsDefined();
			res = new ResultPO(obj);
		} catch (ServiceException e) {
			e.printStackTrace();
			res = new ResultPO(e.getCode(), e.getErrorMessage());
		}
		return res;
	}
	/**
	 * 获取目标表列绑定列表数据
	 * @return
	 */
	@RequestMapping(value="getColsData")
	@ResponseBody
	public Object getColsData(String grid){
		ResultPO res = null;
		try {
			Grid data = (Grid)(new ObjectMapper()).readJson(grid, Grid.class);
			Object obj = billDefinedService.getColsData(data);
			res = new ResultPO(obj);
		} catch (ServiceException e) {
			e.printStackTrace();
			res = new ResultPO(e.getCode(), e.getErrorMessage());
		}
		return res;
	}
	
	/**
	 * 校验列公式
	 * @param params
	 * @return
	 */
	@RequestMapping(value="checkColSql")
	@ResponseBody
	public Object checkColSql(String params){
		ResultPO res = null;
		try {
			@SuppressWarnings("unchecked")
			Map<String, String> paramsMap = (Map<String, String>)(new ObjectMapper()).readJson(params, Map.class);
			res = new ResultPO(billDefinedService.checkColumnSql(paramsMap));
		} catch(ServiceException e){
			e.printStackTrace();
			res = new ResultPO(e.getCode(), e.getErrorMessage());
		}
		return res;
	}

	/**
	 * 保存目标表列公式设置
	 * @param table
	 * @return
	 */
	@RequestMapping(value="saveColsRela")
	@ResponseBody
	public Object saveColsRela(String grid){
		ResultPO res = null;
		try {
			Grid data = (Grid)(new ObjectMapper()).readJson(grid, Grid.class);
			billDefinedService.saveColsRela(data);
			res = new ResultPO("保存成功！");
		} catch (ServiceException e) {
			e.printStackTrace();
			res = new ResultPO(e.getCode(), e.getErrorMessage());
		}
		return res;
	}
	
	/**
	 * 检查原始来源表是否已经存在定义中
	 * @param map
	 * @return
	 */
	@RequestMapping(value="isExistSrcTable")
	@ResponseBody
	public boolean isExistSrcTable(String tableId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableId", tableId);
		return billDefinedService.isExistSrcTable(map);
	}
}
