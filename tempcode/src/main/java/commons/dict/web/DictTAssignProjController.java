package commons.dict.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjhq.commons.dict.service.IDictTAssignProjService;
import com.tjhq.commons.setting.input.po.TreeNode;

/**
 * @author bizaiyi
 * Apr 23, 2015 11:01:12 AM
 * DictTAssignProjController.java
 */
@Controller
@RequestMapping(value = "/commons/dict/dicttAssignProj")
public class DictTAssignProjController {
	private static String returnURL = "commons/dict/";

	@Resource
	private IDictTAssignProjService dictTAssignProjService;
	/**
	 * 指定页面
	 * @return
	 */
	@RequestMapping(value = "")
	public String page(HttpServletRequest request) {
		try{
			request.setAttribute("appid", request.getParameter("appid"));
			return returnURL + "dictTAssignProj";
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	/**
	 * 初始化项目下发设置树
	 * @param appID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="suitTree")
	@ResponseBody
	public Object getSuitTree(String appID) throws Exception{
		try {
			List<TreeNode> treeList = dictTAssignProjService.selectAssignProj(appID);
			return treeList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	/**
	 * 保存项目下发设置
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="saveTable")
	@ResponseBody
	public String saveTable(HttpServletRequest request) throws Exception{
		try {
			String tableIds = request.getParameter("tableIds");
			String[] tableIdArray = tableIds.split(",");
			List<String> tableIdList = new ArrayList<String>();
			for(String tableId : tableIdArray){
				tableIdList.add(tableId);
			}
			dictTAssignProjService.saveTable(tableIdList);
			return "保存成功！";
		} catch (Exception e) {
			e.printStackTrace();
			return "保存失败："+e.getMessage();
		}
	}
}
