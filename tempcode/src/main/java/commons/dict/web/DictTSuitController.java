package commons.dict.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjhq.commons.dict.dao.DictTAppregisterMapper;
import com.tjhq.commons.dict.external.dao.DictTModelMapper;
import com.tjhq.commons.dict.external.dao.DictTSuitMapper;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.po.DictTSuitPO;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.dict.external.service.IDictTSuitService;
import com.tjhq.commons.dict.service.IDictDBExecuteService;
import com.tjhq.commons.dict.service.IDictTSuitSelfService;
import com.tjhq.commons.inputcomponent.grid.basegrid.service.impl.BaseGridService;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.jackson.ObjectMapper;

import com.tjhq.commons.setting.external.po.TreeNode;
import com.tjhq.commons.utils.ConvertUtil;

@Controller
@RequestMapping(value = "/commons/dict/dicttsuit")
public class DictTSuitController extends BaseGridService {

	@Resource
	private IDictTSuitService dictTSuitService;

	@Resource
	private IDictTSuitSelfService dictTSuitSelfService;

	@Resource
	private DictTAppregisterMapper dictTAppregisterMapper;

	@Resource
	private DictTModelMapper dictTModelMapper;

	@Resource
	private IDictDBExecuteService dictDBExecuteService;

	@Resource
	private DictTSuitMapper dictTSuitMapper;

	@Resource
	private IDictTModelService dictTModelService;

	private static String returnStr = "commons/dict/dict";

	/**
	 * 指定页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "")
	public String page(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("appid", request.getParameter("appid"));
		return returnStr + "TSuit";
	}

	/**
	 * 加载左侧树 ztree <DictTSuitPO>
	 * 
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value = "getSuitTrees")
	@ResponseBody
	public Object getSuitTrees(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String appid = request.getParameter("appid");
		String showApp = request.getParameter("showApp");
		Map<String, String> remap = new HashMap<String, String>(); 
		List<TreeNode> suitList  = null;
		if (appid != null && !"".equals(appid)) {
			suitList = dictTSuitService.getDictTSuitTree(appid,showApp,"0"); 
		} 
		for(int i=0;i<suitList.size();i++){
			TreeNode node = suitList.get(i); 
			node.setOpen(true);  
		}
		return suitList;   
	}

	/**
	 * 加载右侧表头 Suit
	 * 
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value = "getSuitListDataHead")
	@ResponseBody
	public Object getSuitListDataHead(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Grid grid = dictTSuitSelfService.getDictTSuitHead("MyTableid");  
		return grid;
	}

	/**
	 * 加载右侧数据 Suit
	 * 
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value = "getSuitListData")
	@ResponseBody
	public Object getSuitListData(HttpServletRequest request, String grid)
			throws Exception {
		Grid table = (Grid) (new ObjectMapper()).readValue(grid, Grid.class);
		Map<String, Object> map = table.getExtProperties();
		String suitid = (String) map.get("suitid");
		String appid = (String) map.get("appid");
		// Integer adapterType =
		// Integer.parseInt((String)map1.get("adapterType"));
		List<DictTModelPO> dtmList = null;

		Integer adapterType = Integer.parseInt(map.get("adapterType")
				.toString());
		List<DictTSuitPO> dtsList = null;
		// 根据左侧树 节点id suitId
		if (suitid != null && !"".equals(suitid)) {
			dtsList = this.dictTSuitService.getDictTSuitsBySupperid(appid,
					suitid);
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (dtsList != null) {
			for (DictTSuitPO dictTSuitPO : dtsList) {
				list.add(ConvertUtil.toMap(dictTSuitPO));
			}
		}
		setGridData(list, table.getPageInfo());
		return table.getPageInfo();
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
		Grid table = (Grid) (new ObjectMapper()).readValue(grid, Grid.class);
		Map<String, Object> paramap = table.getExtProperties();
		// 获取插入数据
		List<Map<String, Object>> insertList = table.getInsertValues();
		// 获取更新数据
		List<Map<String, Object>> updateList = table.getUpdateValues();
		// 获取删除数据
		List<Map<String, Object>> deleteList = table.getDeleteValues();
		String appid = (String) paramap.get("appid");
		Map str = new HashMap();
		if (insertList != null && insertList.size() > 0) {// 插入数据
			for (Map<String, Object> map : insertList) {
				String name = map.get("suitname") == null ? null : map.get(
						"suitname").toString();
				String type = map.get("suittype") == null ? null : map.get(
						"suittype").toString();
				String superid = map.get("superid") == null ? null : map.get(
						"superid").toString();
				Integer orderid = map.get("orderid") == null ? null
						: Integer.parseInt(map.get("orderid") + "");
//				String orderid = map.get("_sortid").toString();
				Integer orderid_ = (Integer)(map.get("_sortid")) + 1;
				if (name != null && !"".equals(name) && type != null
						&& !"".equals(type)) {
					if (!(superid != null && !"".equals(superid))) {
						superid = "0";
					}
					Map<String, Object> selectMap = new HashMap<String, Object>();
					selectMap.put("appid", appid);
					selectMap.put("suitname", name);
					selectMap.put("superid", superid);
					List<DictTSuitPO> dictTSuitPOList = this.dictTSuitMapper
							.findDictTSuit(selectMap);
					if (!(dictTSuitPOList != null && dictTSuitPOList.size() > 0)) {
						Map<String, Object> dtmMap = new HashMap<String, Object>();
						dtmMap.put("suitid", superid);
						List<DictTModelPO> dtmList = this.dictTModelMapper
								.findDictTModel(dtmMap);
						if (!(dtmList != null && dtmList.size() > 0)) {
							DictTSuitPO dts = new DictTSuitPO();
							dts.setAppid(appid);
							dts.setIsleaf("1");
							dts.setLevelno(Integer.parseInt(map.get("levelno")
									+ ""));
							dts.setRemark(map.get("remark") + "");
							dts.setSuitid(dictDBExecuteService.getUUID());
							dts.setSuitname(name);
							dts.setSuittype(type);
							dts.setOrderid(orderid);
							dts.setSuperid(superid);
							this.dictTSuitSelfService.insertDictTSuit(dts);
							if (!"0".equals(superid)) {
								DictTSuitPO supper = this.dictTSuitService
										.getDictTSuitByID(superid);
								String oldleaf = supper.getIsleaf();
								if (!"0".equals(oldleaf)) {
									supper.setIsleaf("0");
									this.dictTSuitSelfService.updateDictTSuit(supper);
								}
							}
						} else {
							str.put("error", "第" + orderid_
									+ "行 ,套表已经存在表,不能添加子套");
						}
					} else {
						str.put("error", "第" + orderid_ + "行 ,<" + name
								+ ">套表名称在同级下有重复");
					}
				} else {
					str.put("error", "第" + orderid_ + "行 ,套表名称、套表类型必填");
				}
			}
		}
		if (updateList != null && updateList.size() > 0) { // 更新数据
			for (Map<String, Object> map : updateList) {
				String name = map.get("suitname") == null ? null : map.get(
						"suitname").toString();
				String type = map.get("suittype") == null ? null : map.get(
						"suittype").toString();
				String isleaf = map.get("isleaf") == null ? null : map.get(
						"isleaf").toString();
				String remark = map.get("remark") == null ? null : map.get(
						"remark").toString();
				String suitid = map.get("suitid") == null ? null : map.get(
						"suitid").toString();
				Integer orderid = map.get("orderid") == null ? null
                        : Integer.parseInt(map.get("orderid") + "");
				if (suitid != null && !"".equals(suitid)) {
					// if (name != null && !"".equals(name) && type != null&&
					// !"".equals(type)) {
					DictTSuitPO dts = dictTSuitService.getDictTSuitByID(suitid);
					dts.setIsleaf(isleaf);
					dts.setRemark(remark);
					dts.setSuitname(name);
					dts.setSuittype(type);
					dts.setOrderid(orderid);
					this.dictTSuitSelfService.updateDictTSuit(dts);
					/*
					 * }else{ str = "{\"error\":\"套表名称、套标类型必填 \"}"; }
					 */
				} else {
					str.put("error", "系统异常,请刷新再试");
				}
			}
		}
		if (deleteList != null && deleteList.size() > 0) { // 删除数据
			for (Map<String, Object> map : deleteList) {
				String suitid = map.get("suitid") == null ? null : map.get(
						"suitid").toString();
				if (suitid != null && !"".equals(suitid)) {
					DictTSuitPO dtspo = this.dictTSuitService
							.getDictTSuitByID(suitid);
					List<DictTSuitPO> list = this.dictTSuitService
							.getDictTSuits(appid, suitid, false);
					if (dtspo != null) {
						list.add(dtspo);
					}
					Map<String, String> map_suitid = new HashMap<String, String>();
					Map<String, Object> dtmMap = new HashMap<String, Object>();
					boolean flag = true;
					for (DictTSuitPO dts : list) {
						dtmMap.put("suitid", dts.getSuitid());
						map_suitid.put(dts.getSuitid(), dts.getSuitid());
						List<DictTModelPO> dtms = this.dictTModelMapper
								.findDictTModel(dtmMap);
						if (dtms != null && dtms.size() > 0) {
							flag = false;
							break;
						}
					}
					if (flag) {
						for (Map.Entry<String, String> s : map_suitid
								.entrySet()) {
							this.dictTSuitSelfService.deleteDictTSuit(s
									.getKey());
						}
					} else {
						str.put("error", "套表中已经存在表");
					}
				}
			}
		}
		if (str.get("error") == null) {
			str.put("success", "保存成功");
		}
		return str;
	}

}
