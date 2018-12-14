package commons.setting.busimanage.web;

import java.util.List;
import java.util.Map;

import gov.mof.fasp2.ca.user.dto.UserDTO;
import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.inputcomponent.grid.settinggrid.service.ISettingGridService;
import com.tjhq.commons.inputcomponent.po.PageInfo;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.jackson.ObjectMapper;
import com.tjhq.commons.setting.busimanage.service.IBusimanageService;

@Controller
@RequestMapping(value = "/commons/setting/busimanage")
public class BusiController {

	@Resource
	private ISettingGridService settingGridService;

	@Resource
	private IBusimanageService busimanageService;

	@RequestMapping(value = "busimanage")
	public String page(Model model) throws Exception {
		return "/commons/setting/busimanage/busimanage";
	}

	/**
	 * 获取表数据
	 * 
	 * @param grid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getData")
	@ResponseBody
	public Object getData(String grid) throws Exception {

		CommonGrid data = (CommonGrid) (new ObjectMapper()).readValue(grid,
				CommonGrid.class);
		PageInfo pageInfo = busimanageService.getDataList(data);
		return pageInfo;
	}

	// 保存数据
	@RequestMapping(value = "saveData")
	@ResponseBody
	public Object saveData(String grid) throws Exception {
		return busimanageService.saveData(grid);
	}

	/**
	 * 获取表格列定义
	 * 
	 * @explain
	 * @return Object
	 * @throws Exception
	 */
	@RequestMapping(value = "getDefine")
	@ResponseBody
	public Object getDefine() throws Exception {
		try {
			Table grid = busimanageService.setTableDefine();
			UserDTO user = SecureUtil.getCurrentUser();
			Table table = settingGridService
					.initStructure(grid, user.getGuid());
			return table;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	
	/**
	 * 系统功能按钮展示
	 * 
	 * @explain
	 * @return String
	 * @throws Exception
	 */

	@RequestMapping(value = "busiView")
	public String page(Model model, String appId) throws Exception {
		model.addAttribute("appId", appId);
		List<Map<String, Object>> busiList = busimanageService.getDataList();
		model.addAttribute("busiList", busiList);
		return "/commons/setting/busimanage/busiView";
	}

}
