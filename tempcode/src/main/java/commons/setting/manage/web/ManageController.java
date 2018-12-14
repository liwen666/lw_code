package commons.setting.manage.web;

import gov.mof.fasp2.ca.user.dto.UserDTO;
import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjhq.commons.inputcomponent.grid.settinggrid.service.ISettingGridService;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.setting.input.po.TreeNode;
import com.tjhq.commons.setting.manage.constants.Constants;
import com.tjhq.commons.setting.manage.service.IManageService;
import com.tjhq.commons.setting.manage.service.IMviewRefreshService;
/**
 * 系统维护控制类
 * @author bizaiyi
 * 2015-8-5 下午4:04:32
 * ManageController.java
 */
@Controller
@RequestMapping(value = "/commons/setting/manage")
public class ManageController {
	@Resource 
	private ISettingGridService settingGridService;
	@Resource 
	private IMviewRefreshService mviewRefreshService;
	@Resource
	private IManageService manageService;
	
	/**
	 * 跳转到系统维护主页面
	 * @param model
	 * @param appId
	 * @return
	 */
	@RequestMapping(value="main")
	public String page(Model model,String appId)  throws Exception{
		model.addAttribute("appId", appId);
		model.addAttribute("province", SecureUtil.getUserSelectProvince());
		return "/commons/setting/manage/manage";
	}
	
	/**
	 * 获取增加分区表格列定义
	 * 
	 * @explain
	 * @return Object
	 * @throws Exception
	 */
	@RequestMapping(value = "getTableHead")
	@ResponseBody
	public Object getTableHead(@RequestParam(value = "curTab") String curTab)
			throws Exception {
		try {
			Table grid = manageService.setTableDefine(curTab);
			UserDTO user = SecureUtil.getCurrentUser();
			Table table = settingGridService
					.initStructure(grid, user.getGuid());
			return table;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	@RequestMapping(value="getTableData")
	@ResponseBody
	public Object getTableData(String grid) throws Exception{
		Grid data = (Grid)(new ObjectMapper()).readValue(grid,Grid.class);
		String curTab = data.getExtProperties().get("curTab").toString();
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		if(Constants.PARTITION.equals(curTab)){//是分区维护表
			dataList = manageService.getPartitionDataList();
		}else if(Constants.INITPARTITION.equals(curTab)){//是初始化分区表
			String province = SecureUtil.getUserSelectProvince();
            String gridFlag = data.getExtProperties().get("gridFlag").toString();
            String year = data.getExtProperties().get("year")==null || data.getExtProperties().get("year").toString().trim().equals("")?SecureUtil.getUserSelectYear().toString():data.getExtProperties().get("year").toString();
			dataList = manageService.getInitPartitionDataList(gridFlag, province, year);
		}
		settingGridService.transferGridData(dataList, data.getPageInfo()); 
		return data.getPageInfo();
	}

    @RequestMapping(value="districtTree")
    @ResponseBody
    public Object getDistrictTree() throws Exception{
        List<TreeNode> treeList = new ArrayList<TreeNode> ();
        treeList = manageService.getDistinctTree();
        return treeList;
    }
    @RequestMapping(value="getSysYears")
    @ResponseBody
    public Object getSysYears() throws Exception{
        List<TreeNode> treeList = new ArrayList<TreeNode> ();
        treeList = manageService.getSysYears();
        return treeList;
    }

	@RequestMapping(value="savaPartition")
	@ResponseBody
	public String savaPartition(HttpServletRequest request) throws Exception{
		String resMsg = "保存成功！";
		try {
			String codeStrs = request.getParameter("data");
			//String year = SecureUtil.getUserSelectYear().toString();
            String year = request.getParameter("year");
            if(year==null || "".equals(year)){
                year = SecureUtil.getUserSelectYear().toString();
            }
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("codeArr", codeStrs.split(","));
			map.put("year", year);
			manageService.savaPartition(map);
		} catch (Exception e) {
			e.printStackTrace();
			resMsg = "保存失败！";
		}
		return (new ObjectMapper()).writeValueAsString(resMsg);
	}

	@RequestMapping(value = "initPartition")
	@ResponseBody
	public String initPartition(HttpServletRequest request) throws Exception {
		String resMsg = "初始化成功！";
		try {
            String lDistrictid = request.getParameter("lDistrictid");
            String rCodeStrs = request.getParameter("rCodeStrs");
            String sourceYear = request.getParameter("sourceYear");
            String targetYear = request.getParameter("targetYear");
			String[] rCodeStrArr = rCodeStrs.split(",");
			//String year = SecureUtil.getUserSelectYear().toString();

			Map<String, Object> map = new HashMap<String, Object>();
            map.put("sourceYear", sourceYear);
            map.put("targetYear", targetYear);
			if(lDistrictid!=null && !lDistrictid.trim().equals("")){
	            map.put("sourceDistrictid", lDistrictid);
			}

			for (int i = 0; i < rCodeStrArr.length; i++) {// 每一个分区一个事务
	            if(lDistrictid==null || lDistrictid.trim().equals("")){
	                map.put("sourceDistrictid", rCodeStrArr[i]);
	            }
				map.put("targetDistrictid", rCodeStrArr[i]);
				manageService.initPartition(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resMsg = "初始化失败:"+e.getCause();
		}
		return (new ObjectMapper()).writeValueAsString(resMsg);
	}
	/**
	 * 刷AGENCY物化视图
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "refreshMview")
	@ResponseBody
	public String refreshMview(HttpServletRequest request) throws Exception {
		String resMsg = "刷新成功！";
		try {
			mviewRefreshService.refreshMview();
		} catch (Exception e) {
			e.printStackTrace();
			resMsg = "刷新失败:"+e.getCause();
		}
		return (new ObjectMapper()).writeValueAsString(resMsg);
	}
	/**
	 * 刷CODE_M_DISTRICT,CODE_M_DEPARTMENT物化视图
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "mviewRefreshDisDept")
	@ResponseBody
	public String mviewRefreshDisDept(HttpServletRequest request) throws Exception {
		String resMsg = "刷新成功！";
		try {
			mviewRefreshService.mviewRefreshDisDept();
		} catch (Exception e) {
			e.printStackTrace();
			resMsg = "刷新失败:"+e.getCause();
		}
		return (new ObjectMapper()).writeValueAsString(resMsg);
	}
}
