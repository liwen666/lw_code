package commons.dict.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjhq.commons.dict.external.po.DictTDealtypePO;
import com.tjhq.commons.dict.external.po.DictTDefaultcolPO;
import com.tjhq.commons.dict.external.po.DictTModelcodePO;
import com.tjhq.commons.dict.external.service.IDictTModelcodeService;
import com.tjhq.commons.dict.service.IDictTDealtypeService;
import com.tjhq.commons.dict.service.IDictTDefaultcolService;
import com.tjhq.commons.inputcomponent.constants.RowState;
import com.tjhq.commons.inputcomponent.grid.basegrid.service.impl.BaseGridService;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.TreePO;
import com.tjhq.commons.jackson.ObjectMapper;
import com.tjhq.commons.setting.external.po.TreeNode;

@Controller
@RequestMapping(value = "/commons/dict/dicttdealtype")
public class DictTDealTypeController extends BaseGridService {

	@Resource
	private IDictTDealtypeService dictTDealtypeService;

	@Resource
	private IDictTDefaultcolService dictTDefaultcolService;

	@Resource
	private IDictTModelcodeService dictTModelcodeService;

	private static String returnStr = "commons/dict/dealtype/dict";

	/**
	 * 指定页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "")
	public String page(HttpServletRequest request, HttpServletResponse response) {
		return returnStr + "DealType";
	}

	/**
	 * 加载左侧树 ztree <DictTSuitPO>
	 * 
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value = "getDealTypeTrees")
	@ResponseBody
	public Object getDealTypeTrees(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<DictTDealtypePO> dtsList = null;
		List<TreeNode> listTree = new ArrayList<TreeNode>();
		try {
			dtsList = dictTDealtypeService.findDictTDealtypeListForZTree();
			listTree = treeData(listTree, dtsList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return (new ObjectMapper()).writeValueAsString(dtsList);
		return listTree;
	}

	private List<TreeNode> treeData(List<TreeNode> treeList,
			List<DictTDealtypePO> list) throws Exception {
		for (DictTDealtypePO dict : list) {
			TreeNode tree = new TreeNode();
			tree.setId(dict.getDealid());
			tree.setName(dict.getDealname());
			tree.setPId(dict.getSupperid());
			List<DictTDealtypePO> dictList = dict.getChildren();
			tree.setOpen(false);
			if (dictList != null && dictList.size() > 0) {
				tree.setIsLeaf("0");
				tree.setParent(true);
				treeData(treeList, dictList);
			} else {
				tree.setIsLeaf("1");
				tree.setParent(false);
			}
			treeList.add(tree);
		}
		return treeList;
	}

	/**
	 * 加载右侧表头
	 * 
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value = "getDefaultcolListDataHead")
	@ResponseBody
	public Object getDefaultcolListDataHead(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// String suitId = request.getParameter("suitId");
		// return
		// dictTDefaultcolService.getDictTDefaultcolHead("myTable","").getDefine();
		String gridHead = "";
		Grid grid = dictTDefaultcolService
				.getDictTDefaultcolHead("myTable", "");
		gridHead = (new ObjectMapper()).writeValueAsString(grid);
		return gridHead;
	}

	/**
	 * 获得下拉列表选项值
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getDataType")
	@ResponseBody
	public Object getDataType(HttpServletRequest request) {
		List<TreePO> list = new ArrayList<TreePO>();
		TreePO p1 = new TreePO();
		p1.setId("1");
		p1.setName("number");
		TreePO p2 = new TreePO();
		p2.setId("3");
		p2.setName("VARCHAR2");
		list.add(p1);
		list.add(p2);
		return list;
	}

	@RequestMapping(value = "getIfElse")
	@ResponseBody
	public Object getIfElse(HttpServletRequest request) {
		List<TreePO> list = new ArrayList<TreePO>();
		TreePO p1 = new TreePO();
		p1.setId("1");
		p1.setName("是");
		TreePO p2 = new TreePO();
		p2.setId("0");
		p2.setName("否");

		list.add(p1);
		list.add(p2);
		return list;
	}

	@RequestMapping(value = "getCsId")
	@ResponseBody
	public Object getCsId(HttpServletRequest request,
			@RequestParam(value = "params") String params) throws Exception {
		// req.getParameter("appId")
		// Map<String, Object> map = (new ObjectMapper()).readValue(params,
		// Map.class);
		String appId = params;
		if ("*".equals(appId)){
			appId = "";
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("appid", appId);
		List<DictTModelcodePO> listTModelcode = dictTModelcodeService
				.findDictTModelcodeByArgs(paramMap);
		List<TreePO> list = new ArrayList<TreePO>();
		for (DictTModelcodePO dictTModelcodePO : listTModelcode) {
			TreePO po = new TreePO();
			po.setId(dictTModelcodePO.getTableid());
			po.setName(dictTModelcodePO.getName());
			list.add(po);
		}
		return list;
	}

	@RequestMapping(value = "getCsIdName")
	@ResponseBody
	public Object getCsIdName(HttpServletRequest request, String params)
			throws Exception {

		String appId = request.getParameter("appId");
		String currentValue = request.getParameter("currentValue");
		DictTModelcodePO dictTModelcodePO = dictTModelcodeService
				.findDictTModelName(appId, currentValue);
		if (dictTModelcodePO != null)
			return dictTModelcodePO.getName();
		return currentValue;
	}

	/**
	 * 加载右侧数据
	 * 
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value = "getDefaultcolListData")
	@ResponseBody
	public Object getDefaultcolListData(HttpServletRequest request, String grid)
			throws Exception {
		Grid table = (Grid) (new ObjectMapper()).readValue(grid, Grid.class);
		Map<String, Object> map = table.getExtProperties();
		String dealid = (String) map.get("dealid");
		List<DictTDefaultcolPO> dtdList = null;
		// 根据左侧树 节点id suitId
		try {
			if (dealid != null && !"".equals(dealid)) {
				dtdList = dictTDefaultcolService
						.getDictTDefaultcols4Show(dealid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (dtdList != null) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for (DictTDefaultcolPO po : dtdList) {
				Map poMap = BeanUtils.describe(po);
				poMap.put(RowState.COLUMN, RowState.NORMAL);
				list.add(poMap);
			}
			setGridData(list, table.getPageInfo());
			return table.getPageInfo();
		}
		return null;
		// Grid.getData(adapterType,dtdList);
	}

	/**
	 * 操作右侧数据
	 * 
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value = "saveTableData")
	@ResponseBody
	public Object saveTableData(String grid, HttpServletRequest request)
			throws Exception {
		Map<String, String> remap = new HashMap<String, String>();
		Grid table = (Grid) (new ObjectMapper()).readValue(grid, Grid.class);
		String str = "success";
		try {
			dictTDefaultcolService.saveDictTDefaultcolData(table);
		} catch (Exception e) {
			e.printStackTrace();
			str = e.getMessage();

		}
		remap.put("key", str.replace("\n", ""));
		return remap;
	}

	/**
	 * 获取表处理类型已经设置了多少缺省列
	 * 
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value = "getDefaultcolCountByDeal")
	@ResponseBody
	public Object getDefaultcolCountByDeal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String count ="";
		try {
			String dealID = request.getParameter("dealID");
			String tableId = request.getParameter("tableId");
			count = dictTDefaultcolService.getDefaultcolCountByDeal(dealID,tableId);
		} catch (Exception e) {
			count = "操作失败";
		}
		return count;
	}
}
