package commons.setting.billdef.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.inputcomponent.po.TreePO;
import com.tjhq.commons.jackson.ObjectMapper;
import com.tjhq.commons.setting.billdef.service.IBillDefTypeService;

@Controller
@RequestMapping(value="/commons/setting/billdeftype")
public class BillDefTypeController {
	@Resource
	private IBillDefTypeService billDefTypeService;
	
	@RequestMapping(value="mpage")
	public String getPage(HttpServletRequest request, HttpServletResponse response, ModelMap model, String appId, String all){
		model.addAttribute("appId", appId);
		return "commons/setting/billdef/billdeftype";
	}
	
	/**
	 * 获取记账类型列表定义 
	 * @param appId
	 * @return
	 */
	@RequestMapping(value="getTableDefined")
	@ResponseBody
	public Object getTableDefined(String appId){
		ResultPO res = null;
		try {
			Object obj = billDefTypeService.getTableDefined(appId);
			res = new ResultPO(obj);
		} catch (ServiceException e) {
			e.printStackTrace();
			res = new ResultPO(e.getCode(), e.getErrorMessage());
		}
		return res;
	}
	
	/**
	 * 获取记账定义类型列表数据
	 * @param grid
	 * @return
	 */
	@RequestMapping(value="getTableData")
	@ResponseBody
	public Object getTableData(String grid) {
		ResultPO res = null;
		try {
			Grid data = (Grid)(new ObjectMapper()).readJson(grid, Grid.class);
			Object obj = billDefTypeService.getTableData(data);
			res = new ResultPO(obj);
		} catch (ServiceException e) {
			e.printStackTrace();
			res = new ResultPO(e.getCode(), e.getErrorMessage());
		}
		return res;
	}
	
	/**
	 * 保存
	 * @param table
	 * @return
	 */
	@RequestMapping(value="saveData")
	@ResponseBody
	public Object saveData(String grid){
		ResultPO res = null;
		try {
			Grid data = (Grid)(new ObjectMapper()).readJson(grid, Grid.class);
			billDefTypeService.saveData(data);
			res = new ResultPO("保存成功！");
		} catch (ServiceException e) {
			e.printStackTrace();
			res = new ResultPO(e.getCode(), e.getErrorMessage());
		}
		return res;
	}
	
	/**
	 * 获取记账定义数据
	 * @param appId
	 * @return
	 */
	@RequestMapping(value="getBillDefList")
	@ResponseBody
	public Object getBillDefList(String appId){
		ResultPO res = null;
		try {
			List<TreePO> treeList = billDefTypeService.getBillDefList(appId);
			res = new ResultPO(treeList);
		} catch (ServiceException e) {
			e.printStackTrace();
			res = new ResultPO(e.getCode(), e.getErrorMessage());
		}
		return res;
	}

}
