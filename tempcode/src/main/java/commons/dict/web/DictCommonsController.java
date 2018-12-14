package commons.dict.web;

import gov.mof.fasp2.ca.user.dto.UserDTO;
import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjhq.commons.dict.dao.DictTAppregisterMapper;
import com.tjhq.commons.dict.dao.DictTDealtypeMapper;
import com.tjhq.commons.dict.dao.DictTDefaultcolMapper;
import com.tjhq.commons.dict.dao.DictTModelSelfMapper;
import com.tjhq.commons.dict.dao.DictTUpdateviewMapper;
import com.tjhq.commons.dict.external.dao.DictTFactorMapper;
import com.tjhq.commons.dict.external.po.ConsistencyPO;
import com.tjhq.commons.dict.external.po.DictElementPO;
import com.tjhq.commons.dict.external.po.DictTAppregisterPO;
import com.tjhq.commons.dict.external.po.DictTDealtypePO;
import com.tjhq.commons.dict.external.po.DictTDefaultcolPO;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.po.DictTSuitPO;
import com.tjhq.commons.dict.external.po.DictTTabextpropPO;
import com.tjhq.commons.dict.external.po.DictTUpdateviewPO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.dict.external.service.IDictTSuitService;
import com.tjhq.commons.dict.external.service.IDictTTabextpropService;
import com.tjhq.commons.dict.service.IDictDBExecuteService;
import com.tjhq.commons.dict.service.IDictTFactorSelfService;
import com.tjhq.commons.dict.service.IDictTModelSelfService;
import com.tjhq.commons.dict.util.DictDBConstants;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.grid.basegrid.service.impl.BaseGridService;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.inputcomponent.po.TreePO;
import com.tjhq.commons.jackson.ObjectMapper;
import com.tjhq.commons.setting.input.po.TreeNode;
import com.tjhq.commons.setting.input.service.IEntryService;
import com.tjhq.commons.setting.input.web.ConverTables;
import com.tjhq.commons.utils.ConvertUtil;
import com.tjhq.synch2.remote.bd.IBdOaReportService;

@Controller
@RequestMapping(value = "/commons/dict/dictCommons")
public class DictCommonsController extends BaseGridService {

	@Resource
	private IDictTFactorService dictTFactorService;

	@Resource
	private IDictTModelService dictTModelService;

	@Resource
	private IDictTSuitService dictTSuitService;

	@Resource
	private IDictTModelSelfService dictTModelSelfService;

	@Resource
	private DictTDefaultcolMapper dictTDefaultcolMapper;

	@Resource
	private DictTDealtypeMapper dictTDealtypeMapper;

	@Resource
	private IDictDBExecuteService dictDBExecuteService;

	@Resource
	private IDictTTabextpropService dictTTabextpropService;

	@Resource
	private IDictTFactorSelfService dictTFactorSelfService;

	@Resource
	private DictTUpdateviewMapper dictTUpdateviewMapper;
	
	@Resource
	private DictTFactorMapper dictTFactorMapper;
	
	@Resource
	private DictTModelSelfMapper dictTModelSelfMapper;
	
	@Resource
	private DictTAppregisterMapper dictTAppregisterMapper;
	@Resource
	private IEntryService entryService;
	private static String returnStr = "commons/dict/dict";

	@Resource(name="bgtAssignSpecifiedTableStructImpl")
	private IBdOaReportService bgtAssignSpecifiedTableStructImpl;
	
	/**
	 * 指定页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "")
	public String page(HttpServletRequest request, HttpServletResponse response) {
		List<DictTAppregisterPO> listDictTAppregister = dictTAppregisterMapper
				.getAllDictTAppregister();
		String apps = "";
		try {
			apps = (new ObjectMapper())
					.writeValueAsString(listDictTAppregister);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("apps", apps);
		request.setAttribute("appid", listDictTAppregister.get(0).getAppid());
		request.setAttribute("appname", listDictTAppregister.get(0).getAppname());
		request.setAttribute("editMode", dictTFactorService.getEditMode());
		
		request.setAttribute("sendData", request.getParameter("sendData"));
		return returnStr + "Commons";
	}

	// 选择系统
	@RequestMapping(value = "getDictCommonsApps")
	@ResponseBody
	public Object getDictCommonsApps(HttpServletRequest request,
			HttpServletResponse response) {
		List<DictTAppregisterPO> listDictTAppregister = dictTAppregisterMapper
				.getAllDictTAppregister();
		List<TreeNode> appList = new ArrayList<TreeNode>();
		appList = treeData(appList, listDictTAppregister);
		return appList;
	}

	private List<TreeNode> treeData(List<TreeNode> treeList,
			List<DictTAppregisterPO> list) {
		for (DictTAppregisterPO dict : list) {
			TreeNode tree = new TreeNode();
			tree.setId(dict.getAppid());
			tree.setName(dict.getAppname());
			// tree.setIsLeaf("1");
			tree.setPId("#");
			treeList.add(tree);
		}
		return treeList;
	}

	/**
	 * 获得财年
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getFinyearForAjax")
	@ResponseBody
	public Object getCsidForAjax(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<TreeNode> list = new ArrayList<TreeNode>();
		List<Map<String, String>> listFinyear = dictTAppregisterMapper
				.getAllFinyear();
		for (Map<String, String> map : listFinyear) {
			TreeNode treeNode = new TreeNode();
			treeNode.setId(map.get("CODE"));
			treeNode.setName(map.get("NAME"));
			list.add(treeNode);
		}
		return list;
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
		String appid = request.getParameter("appId");
		Map<String, String> remap = new HashMap<String, String>();
		List<DictTSuitPO> dtsList = null;
		if (appid != null && !"".equals(appid)) {
			dtsList = dictTSuitService.getDictTSuits(appid, "0", false);
		}
		remap.put("key", (new ObjectMapper()).writeValueAsString(dtsList));
		return remap;
	}

	/**
	 * 复制表到其他财年
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "copyTableDefinitionToModel")
	@ResponseBody
	public Object copyTableDefinitionToModel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map result = new HashMap();
		try {
			String finyear = request.getParameter("finyear");
			String tableString = request.getParameter("table");
			// tableString = tableString.substring(1,tableString.length()-1);
			// tableString = tableString.replace("\\\"", "");
			List<Map<String, String>> tableData = JsonToMap(tableString);
			String message = dictTModelSelfService.copyTableDefinitionToModel(
					finyear, tableData);
			result.put("key", message);
		} catch (Exception e) {
			result.put("key", "复制失败 : " + e.getMessage());
		}

		return result;
	}

	/**
	 * 加载右侧表头 Suit
	 * 
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value = "getSuitListDataHead")
	@ResponseBody
	public String getSuitListDataHead(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// String suitId = request.getParameter("suitId");
		String gridHead = "";
		Grid grid = dictTModelSelfService.getDictTModelHead("myTable");
		gridHead = (new ObjectMapper()).writeValueAsString(grid);
		return gridHead;
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
		Map<String, Object> paramap = table.getExtProperties();
		String suitId = (String) paramap.get("suitId");
		// Integer adapterType =
		// Integer.parseInt((String)map1.get("adapterType"));
		List<DictTModelPO> dtmList = null;
		// 根据左侧树 节点id suitId
		if (suitId != null && !"".equals(suitId)) {
			dtmList = dictTModelSelfService.getDictTModelsBySuitId(suitId,
					false);

		} else {
			// 多条件查询
			String name_cn = (String) paramap.get("name_cn");
			String name_ph = (String) paramap.get("name_ph");
			String suit_id = (String) paramap.get("suit_id");
			String t_type = (String) paramap.get("t_type");
			String appid = (String) paramap.get("appid");
			Map<String, Object> map = new HashMap<String, Object>();
			if (name_cn != null && !"".equals(name_cn)&& !"".equals(name_cn.trim())) {
				name_cn = name_cn.trim();
				map.put("name_like", "%" + name_cn + "%");
			}
			if (name_ph != null && !"".equals(name_ph)&& !"".equals(name_ph.trim())) {
				name_ph = name_ph.trim();
				map.put("dbtablename_like", "%" + name_ph.toUpperCase() + "%");
			}
			if (t_type != null && !"".equals(t_type)&& !"".equals(t_type.trim())) {
				t_type = t_type.trim();
				map.put("tabletype", t_type);
			}
			if (appid != null && !"".equals(appid)&& !"".equals(appid.trim())) {
				appid = appid.trim();
				map.put("appid", appid);
			}
			if (suit_id != null && !"".equals(suit_id)&&!"".equals(suit_id.trim())) {
				suit_id = suit_id.trim();
				dtmList = dictTModelSelfService.getDictTModelsBySuitIdmap(
						suit_id, map, false);
			} else {
				dtmList = dictTModelSelfService.getDictTModels(map);
			}
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(dtmList!=null&&dtmList.size()>0){
			for (DictTModelPO dictTModelPO : dtmList) {
				list.add(ConvertUtil.toMap(dictTModelPO));
			}
		}
		setGridData(list, table.getPageInfo());
		return table.getPageInfo();
	}

	/**
	 * 获得一个对象各个属性的字节流
	 */
	@SuppressWarnings("rawtypes")
	public static void getProperty(Object entityName) throws Exception {
		Class c = entityName.getClass();
		Field field[] = c.getDeclaredFields();
		for (Field f : field) {
			Object v = invokeMethod(entityName, f.getName(), null);
			System.out.println(f.getName() + "\t" + v + "\t" + f.getType());
		}
	}

	/**
	 * 获得对象属性的值
	 */
	@SuppressWarnings("rawtypes")
	private static Object invokeMethod(Object owner, String methodName,
			Object[] args) throws Exception {
		Class ownerClass = owner.getClass();
		methodName = methodName.substring(0, 1).toUpperCase()
				+ methodName.substring(1);
		Method method = null;
		try {
			method = ownerClass.getMethod("get" + methodName);
		} catch (SecurityException e) {
		} catch (NoSuchMethodException e) {
			return " can't find 'get" + methodName + "' method";
		}
		return method.invoke(owner);
	}

	/**
	 * 打开增加物理表 页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "findDictTModle")
	public String findDictTModle(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
 		String suitId = request.getParameter("suitId");
		String ver = request.getParameter("ver");
		request.setAttribute("suitId", suitId);

		if (suitId != null && !"".equals(suitId)) {
			DictTSuitPO dts = this.dictTSuitService.getDictTSuitByID(suitId);
			if (dts != null) {
				request.setAttribute("suit_name", dts.getSuitname());
				request.setAttribute("appid", dts.getAppid());
				map.put("appid", dts.getAppid());
			}
		}
		String strPage = "TModel";

		String tableId = request.getParameter("tableId");
		if (tableId != null && !"".equals(tableId)) {

			DictTModelPO dtm = this.dictTModelService.getDictTModelByID(
					tableId, false);
			if (dtm != null) {
				request.setAttribute("dtm", dtm);
				if ("1".equals(dtm.getTabletype())) {
					strPage = "TModel";
				} else if ("2".equals(dtm.getTabletype())) {
					strPage = "TModelForView";
				} else if ("3".equals(dtm.getTabletype())) {
					dtm.getMainuptab();
					List<DictTModelPO> listModel = new ArrayList<DictTModelPO>();
					String ids = dtm.getRelatab();
					if (ids != null && !"".equals(ids)) {
						String[] str = ids.split(",");
						for (String id : str) {
							DictTModelPO fromDtm = this.dictTModelService
									.getDictTModelByID(id, false);
							Map<String, String> queryMap = new HashMap<String, String>();
							queryMap.put("fromTableID", id);
							queryMap.put("tableID", tableId);
							List<DictTFactorPO> dtfList = this.dictTModelSelfService
									.getDictTFactorByFromTableID(queryMap);
							if (dtfList != null) {
								fromDtm.setDictTFactorList(dtfList);
							}
							if (fromDtm != null) {
								listModel.add(fromDtm);
							}
						}
					}
					request.setAttribute("relatab",
							(new ObjectMapper()).writeValueAsString(listModel));
					strPage = "TModelForBusiness";
				}
			}
		} else {
			if ("1".equals(ver)) {
				strPage = "TModel";
			} else if ("2".equals(ver)) {
				strPage = "TModelForView";
			} else if ("3".equals(ver)) {
				strPage = "TModelForBusiness";
			}
		}
		String status = this.dictDBExecuteService.getGlobalIsmultdb();
		if (status != null && !"".equals(status)) {
			status = DictDBConstants.status_DB.get(status);
			request.setAttribute("v_status", "[" + status + "]");
		}
		List<DictTDealtypePO> listdtdt = null;
		map.put("withoutUsed", '1');	// 已引用过的只能被引用一次的表处理类型不列出
		listdtdt = this.dictTDealtypeMapper.findDictTDealtype(map);
		String selectJson = "";
		if (listdtdt != null && listdtdt.size() > 0) {
			selectJson = (new ObjectMapper()).writeValueAsString(listdtdt);
		}
		request.setAttribute("selectJson", selectJson);

		return returnStr + strPage;
	}

	// SELECT * FROM USER_TAB_COLS WHERE TABLE_NAME=UPPER('dict_t_factor')

	/**
	 * 创建 物理表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveOrUpdateDictTModel")
	@ResponseBody
	public Object saveOrUpdateDictTModel(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "json") String poStr) throws Exception {
		Map str = new HashMap();
		DictTModelPO dtm = (DictTModelPO) (new ObjectMapper()).readValue(poStr,
				DictTModelPO.class);
		String tableId = request.getParameter("tableId");
		// 物理表
		dtm.setTabletype(DictDBConstants.TABLE_TYPE_TABLE);
		String extprop = dtm.getExtprop();
		if (extprop == null || extprop.trim().equals("")) {
			extprop = "";
			for (int i = 0; i < DictDBConstants.DICT_DB_EXEPROP - 1; i++) {
				extprop = extprop + "0";
			}
			dtm.setExtprop(extprop);
		}
		try {
			if (tableId != null && !"".equals(tableId)) {
				dtm.setTableid(tableId);
				this.dictTModelSelfService.updateDictTModel(dtm);
			} else {
				// 创建物理表
				this.dictTModelSelfService.insertDictTModelForPhysics(dtm);
			}
		} catch (Exception e) {
			str.put("error", e.getMessage().replace("\"", "\'"));
			return str;
		}
		if (str.get("error") == null) {
			str.put("success", "保存成功");
		}
		return str;
	}

	/**
	 * 登记视图
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveOrUpdateDictTModelForView")
	@ResponseBody
	public Object saveOrUpdateDictTModelForView(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "json") String poStr) throws Exception {
		Map str = new HashMap();
		DictTModelPO dtm = (DictTModelPO) (new ObjectMapper()).readValue(poStr,
				DictTModelPO.class);
		String tableId = request.getParameter("tableId");
		if (dtm.getIspartition() == null)
			dtm.setIspartition("0");
		if (dtm.getIsreserved() == null)
			dtm.setIsreserved("0");
		if (dtm.getIssumtab() == null)
			dtm.setIssumtab("0");
		if (dtm.getIsshow() == null)
			dtm.setIsshow("0");
		dtm.setIsReadOnly("1");
		// 视图（不可更新）
		dtm.setTabletype(DictDBConstants.TABLE_TYPE_VIEW);
		String extprop = dtm.getExtprop();
		if (extprop == null || extprop.trim().equals("")) {
			extprop = "";
			for (int i = 0; i < DictDBConstants.DICT_DB_EXEPROP - 1; i++) {
				extprop = extprop + "0";
			}
			dtm.setExtprop(extprop);
		}
		if (tableId != null && !"".equals(tableId)) {
			dtm.setTableid(tableId);
			this.dictTModelSelfService.updateDictTModel(dtm);
		} else {
			if (dtm.getDealtype() != null && !"".equals(dtm.getDealtype())) {
				try {
					// 2（不更新）视图表
					this.dictTModelSelfService
							.insertDictTModelForNoUpdateView(dtm);
				} catch (Exception e) {
					e.printStackTrace();
					str.put("error", e.getMessage().replace("\"", "\'"));
				}
			}
		}
		if (str.get("error") == null) {
			str.put("success", "登记成功");
		}
		return str;
	}

	/**
	 * 更改物理表名称
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveDictModleForTableName")
	@ResponseBody
	public Object saveDictModleForTableName(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map str = new HashMap();
		String tableid = request.getParameter("tableid");
		DictTModelPO dtm = this.dictTModelService.getDictTModelByID(tableid,
				false);
		String tabletype = dtm.getTabletype();
		String newDBTableName = request.getParameter("dbTableName");

		if (newDBTableName != null && !"".equals(newDBTableName)) {
			newDBTableName = newDBTableName.toUpperCase();
			if (newDBTableName.length() > 0 && newDBTableName.length() <= 20) {
				if (!dtm.getDbtablename().toUpperCase().equals(newDBTableName)) {
					try {
						/**
						 * 表类型：1物理表、2（不更新）视图表、3（可更新）视图表
						 */
						if ("1".equals(tabletype)) {// 1物理表
							this.dictTModelSelfService
									.saveDictModleForTableName(newDBTableName,
											dtm);
						} else if ("2".equals(tabletype)) {// 2（不更新）视图表
							dtm.setDbtablename(newDBTableName);
							this.dictTModelSelfService.updateDictTModel(dtm);
						} else if ("3".equals(tabletype)) {// 3（可更新）视图表
							Map<String, List<DictTFactorPO>> talMap = new HashMap<String, List<DictTFactorPO>>();
							List<Map<String, String>> settingList = new ArrayList<Map<String, String>>();
							List<DictTFactorPO> listFactor = this.dictTFactorService
									.getDictTFactorByTableidAndType(tableid,
											"1");

							List<DictTFactorPO> dtfsList = new ArrayList<DictTFactorPO>();
							for (DictTFactorPO dtf : listFactor) {
								DictTFactorPO dtfold = this.dictTFactorService
										.getDictTFactorByColumnId(dtf
												.getFrmcolid());
								String oldDBname = dtfold.getDbcolumnname()
										.toUpperCase();
								String newDBname = dtf.getDbcolumnname()
										.toUpperCase();
								if (oldDBname != null && newDBname != null
										&& !oldDBname.equals(newDBname)) {
									// dtf.setDbcolumnname(oldDBname);
									dtfold.setChangename(newDBname);
								}
								List<DictTFactorPO> dtfs = talMap.get(dtf
										.getFrmtabid());
								if (dtfs == null) {
									dtfs = new ArrayList<DictTFactorPO>();
									dtfs.add(dtfold);
									talMap.put(dtfold.getTableid(), dtfs);
								} else {
									dtfs.add(dtfold);
									talMap.put(dtfold.getTableid(), dtfs);
								}
								dtfsList.add(dtfold);
							}
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("", tableid);
							List<DictTUpdateviewPO> listDictTUpdateview = dictTUpdateviewMapper
									.findDictTUpdateview(map);
							for (DictTUpdateviewPO dtu : listDictTUpdateview) {
								Map<String, String> m = new HashMap<String, String>();
								m.put("columnid", dtu.getColumnid());
								m.put("tocolumnid", dtu.getTocolumnid());
								settingList.add(m);
							}
							this.dictTModelSelfService
									.saveDictModleForTableNameForUpdateView(
											dtfsList, newDBTableName, dtm,
											talMap, settingList);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						str.put("error", e.getMessage().replace("\"", "\'"));
					}
				} else {
					str.put("error", "未更改.新表名和就表名相同");
				}
			} else {
				str.put("error", "未更改.新表名长度应大于0且不大于20");
			}
		} else {
			str.put("error", "未找到新的物理表名");
		}

		if (str.get("error") == null) {
			str.put("success", "保存成功");
		}

		return str;
	}

	/**
	 * 删除数据
	 * 
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteTableData")
	@ResponseBody
	public Object deleteTableData(String grid, HttpServletRequest request)
			throws Exception {
		Grid table = (Grid) (new ObjectMapper()).readValue(grid, Grid.class);
		Map<String, String> remap = new HashMap<String, String>();
		String prefix = DictDBConstants.PREFIX_TABLE;
		// 获取删除数据
		String str = "";
		List<Map<String, Object>> deleteList = table.getDeleteValues();
		if (deleteList != null && deleteList.size() > 0) { // 删除数据
			for (Map<String, Object> map : deleteList) {
				String tableid = map.get("tableid").toString();
				DictTModelPO dtm = dictTModelService.getDictTModelByID(tableid,
						false);
				if (dtm.getIspartition().equals("0")) {
					prefix = "";
				}
				if (dtm != null) {
					String tabletype = dtm.getTabletype();
					if (!("1".equals(dtm.getIsreserved()))) {
						int count = this.dictDBExecuteService
								.getTableDataCount(prefix
										+ dtm.getDbtablename().toUpperCase());
						if (!(count > 0)) {
							/**
							 * 表类型：1物理表、2（不更新）视图表、3（可更新）视图表
							 */
							try {
								boolean existRela = this.dictTModelService.getExistRela(dtm.getTableid());
								if(existRela) {
									str += dtm.getDbtablename() + "该表已被关联，无法删除.\n";
								} else {
									if ("1".equals(tabletype)) {// 1物理表
										this.dictTModelSelfService
										.deleteDictTModelForPhysics(dtm);
									} else if ("2".equals(tabletype)) {// 2（不更新）视图表
										this.dictTModelSelfService
										.deleteDictTModelForNoUpdateView(dtm);
									} else if ("3".equals(tabletype)) {// 3（可更新）视图表
										this.dictTModelSelfService
										.deleteDictTModelForUpdateView(dtm);
									}
								} 
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
							str = str + dtm.getDbtablename() + "存在数据，不可删除.\n";
						}
					} else {
						str = str + dtm.getDbtablename() + " 状态为保留，不可删除.\n";
					}
				} else {
					str = str + "DictTModel对象为空，删除失败.\n";
				}
			}
		}
		if ("".equals(str)) {
			str = "删除成功.";
		}
		remap.put("key", str.replace("\n", ""));
		return remap;
	}

	/**
	 * 加载右侧表头 DictTTabextprop
	 * 
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value = "getDictTTabextpropListDataHead")
	@ResponseBody
	public Object getDictTTabextpropListDataHead(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// String suitId = request.getParameter("suitId");
		String gridHead = "";
		Grid grid = dictTTabextpropService.getDictTTabextpropHead("myTable");
		gridHead = (new ObjectMapper()).writeValueAsString(grid);
		return gridHead;
	}

	/**
	 * 加载右侧数据 DictTTabextprop
	 * 
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value = "getDictTTabextpropListData")
	@ResponseBody
	public Object getDictTTabextpropListData(String grid,
			HttpServletResponse response) throws Exception {
		Grid table = (Grid) (new ObjectMapper()).readValue(grid, Grid.class);
		Map<String, Object> map = table.getExtProperties();
		String appid = (String) map.get("appid");
		Integer adapterType = Integer.parseInt(map.get("adapterType")
				.toString());
		List<DictTTabextpropPO> dttList = null;
		if (appid != null && !"".equals(appid)) {
			dttList = dictTTabextpropService
					.getAllDictTTabextpropByAppid(appid);
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (DictTTabextpropPO dictTTabextpropPO : dttList) {
			list.add(ConvertUtil.toMap(dictTTabextpropPO));
		}
		setGridData(list, table.getPageInfo());
		return table.getPageInfo();
	}

	/**
	 * ********************************************可更新视图************************
	 * *************************************************************************
	 * **************
	 */

	/**
	 * 加载table ztree
	 * 
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value = "getTableTrees")
	@ResponseBody
	public Object getTableTrees(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<DictTFactorPO> dtfList = new ArrayList<DictTFactorPO>();
		String appid = request.getParameter("appid");
		String tableid = request.getParameter("tableid");
		if (tableid == null || "".equals(tableid)) {
			dtfList = dictTFactorService.getAllFactorListTreeByAppID(appid);
			// List<DictTSuitPO> dtsList = this.dictTSuitService.getDictTSuits(
			// appid, "0", false);
			// for (DictTSuitPO dts : dtsList) {
			// DictTFactorPO dtf = new DictTFactorPO();
			// dtf.setColumnid(dts.getSuitid());
			// dtf.setTableid(dts.getSuperid());
			// dtf.setName(dts.getSuitname());
			// dtf.setDbcolumnname("");
			// dtf.setNocheck(true);
			// dtf.setOpen(true);
			// dtfList.add(dtf);
			// if ("1".equals(dts.getIsleaf())) {
			// List<DictTModelPO> dtms = dictTModelService
			// .getDictTModelsBySuitId(dts.getSuitid(), false);
			// for (DictTModelPO dtm : dtms) {
			// if ("1".equals(dtm.getTabletype())) {
			// DictTFactorPO d = new DictTFactorPO();
			// d.setColumnid(dtm.getTableid());
			// d.setTableid(dts.getSuitid());
			// d.setName(dtm.getName());
			// d.setIsleaf("0");
			// d.setDbcolumnname(dtm.getDbtablename());
			// dtfList.add(d);
			// List<DictTFactorPO> listFactor = this.dictTFactorService
			// .getDictTFactorByTableidAndType(
			// dtm.getTableid(), "1");
			// for (DictTFactorPO dtfp : listFactor) {
			// dtfList.add(dtfp);
			// }
			// }
			// }
			// }
			// }
		} else {
			// 修改 查询
			List<String> tableids = this.dictTFactorService
					.getTableidsByGroupByFrmtabid(tableid);
			for (String tid : tableids) {
				DictTModelPO dtm = this.dictTModelService.getDictTModelByID(
						tid, false);
				DictTFactorPO d = new DictTFactorPO();
				d.setColumnid(dtm.getTableid());
				d.setTableid(dtm.getSuitid());
				d.setName(dtm.getName());
				d.setIsleaf("0");
				d.setDbcolumnname(dtm.getDbtablename());
				d.setOpen(true);
				dtfList.add(d);
				List<DictTFactorPO> listFactor = this.dictTFactorService
						.getDictTFactorByTableidAndType(dtm.getTableid(), "1");
				for (DictTFactorPO dtfp : listFactor) {
					dtfList.add(dtfp);
				}
			}
		}
		return dtfList;
	}

	/**
	 * 修改时 加载 已设置 factor
	 * 
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value = "getTableTreesReslut")
	@ResponseBody
	public String getTableTreesReslut(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<DictTFactorPO> dtfList = new ArrayList<DictTFactorPO>();
		String tableid = request.getParameter("tableid");

		if (tableid != null && !"".equals(tableid)) {
			List<String> tableids = this.dictTFactorService
					.getTableidsByGroupByFrmtabid(tableid);
			for (String tid : tableids) {
				DictTModelPO dtm = this.dictTModelService.getDictTModelByID(
						tid, false);
				DictTFactorPO d = new DictTFactorPO();
				d.setColumnid(dtm.getTableid());
				d.setFrmtabid(dtm.getTableid());
				d.setTableid(dtm.getSuitid());
				d.setName(dtm.getName());
				d.setIsleaf("0");
				d.setDbcolumnname(dtm.getDbtablename());
				d.setOpen(true);
				dtfList.add(d);
			}
			List<DictTFactorPO> dtfLists = this.dictTFactorService
					.getDictTFactorByTableidAndType(tableid, "1");
			for (DictTFactorPO dtfp : dtfLists) {
				dtfList.add(dtfp);
			}
		}
		return (new ObjectMapper()).writeValueAsString(dtfList);
	}

	/**
	 * 加载右侧表头
	 * 
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value = "getColumnDataHead")
	@ResponseBody
	public Object getColumnDataHead(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// String suitId = request.getParameter("suitId");
		String gridHead = "";
		Grid grid = this.dictTFactorSelfService.getColumnDataHead("tabley");
		gridHead = (new ObjectMapper()).writeValueAsString(grid);
		return gridHead;
	}

	/**
	 * 设置视图关系 表头
	 * 
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value = "getSettingColumnDataHead")
	@ResponseBody
	public Object getSettingColumnDataHead(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "datas") String datas) throws Exception {
		datas = datas.substring(1, datas.length() - 1);
		datas = datas.replace("\\\"", "");

		Map<String, List<DictTFactorPO>> talMap = new HashMap<String, List<DictTFactorPO>>();
		List<Map<String, String>> list = JsonToMap(datas);
		// 遍历 新 Column 组织数据结构
		for (Map<String, String> map : list) {
			String tableid = map.get("tableid");
			DictTFactorPO dtf = new DictTFactorPO();
			dtf.setColumnid(map.get("columnid"));
			dtf.setName(map.get("name"));
			dtf.setDbcolumnname(map.get("dbcolumnname"));
			dtf.setChangename(map.get("changename"));
			dtf.setTableid(tableid);
			List<DictTFactorPO> dtfs = talMap.get(tableid);
			if (dtfs == null) {
				dtfs = new ArrayList<DictTFactorPO>();
				dtfs.add(dtf);
				talMap.put(tableid, dtfs);
			} else {
				dtfs.add(dtf);
				talMap.put(tableid, dtfs);
			}
		}
		String gridHead = "";
		Grid grid = this.dictTFactorSelfService.getSettingColumnDataHead(
				"tablet", talMap);
		gridHead = (new ObjectMapper()).writeValueAsString(grid);
		return gridHead;

	}

	/**
	 * 单击 第一列
	 * 
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value = "getSettingFinddata")
	@ResponseBody
	public Object getSettingFinddata(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// String suitId = request.getParameter("suitId");
		String datas = request.getParameter("params");
		String format = request.getParameter("format");
		datas = datas.substring(1, datas.length() - 1);
		datas = datas.replace("\\\"", "");
		List<Map<String, String>> list = JsonToMap(datas);
		List<TreePO> treePoList = new ArrayList<TreePO>();

		// 遍历 新 Column 组织数据结构
		List<DictTFactorPO> dictFactorPOList = dictTFactorSelfService
				.getSettingFinddata(list);
		for (DictTFactorPO dictTFactorPO : dictFactorPOList) {
			TreePO po = new TreePO();
			po.setId(dictTFactorPO.getColumnid());
			po.setName(dictTFactorPO.getName());
			po.setPId(dictTFactorPO.getTableid());
			treePoList.add(po);

		}
		return treePoList;
	}

	// 设置视图关系数据
	@RequestMapping(value = "getSettingColumnData")
	@ResponseBody
	public Object getSettingColumnData(String grid, HttpServletResponse response)
			throws Exception {
		Grid table = (Grid) (new ObjectMapper()).readValue(grid, Grid.class);
		Map<String, Object> paramap = table.getExtProperties();
		Integer adapterType = Integer.parseInt(paramap.get("adapterType")
				.toString());
		String tableid = paramap.get("tableid").toString();
		List<DictTUpdateviewPO> dtuList = null;
		if (tableid != null && !"".equals(tableid)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tableid", tableid);
			dtuList = this.dictTUpdateviewMapper.findDictTUpdateview(map);
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (ConverTables.isNotNullList(dtuList)) {
			for (DictTUpdateviewPO dictTUpdateviewPO : dtuList) {
				list.add(ConvertUtil.toMap(dictTUpdateviewPO));
			}
		}
		setGridData(list, table.getPageInfo());
		return table.getPageInfo();
	}

	/**
	 * 二次渲染 设置视图关系 表头
	 * 
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value = "getSettingColumnChangeHead")
	@ResponseBody
	public Object getSettingColumnChangeHead(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// String suitId = request.getParameter("suitId");
		// String datas = request.getParameter("params[datas]");
		String datas = request.getParameter("params");
		String format = request.getParameter("format");
		String relateColId = request.getParameter("relateColId");
		DictTFactorPO factor = this.dictTFactorService
				.getDictTFactorByColumnId(relateColId);
		datas = datas.substring(1, datas.length() - 1);
		datas = datas.replace("\\\"", "");
		Map<String, List<DictTFactorPO>> talMap = new HashMap<String, List<DictTFactorPO>>();
		List<Map<String, String>> list = JsonToMap(datas);
		String table_id = factor.getTableid();
		// 遍历 新 Column 组织数据结构
		for (Map<String, String> map : list) {
			String tableid = map.get("tableid");
			DictTFactorPO dtf = new DictTFactorPO();
			dtf.setColumnid(map.get("columnid"));
			dtf.setName(map.get("name"));
			dtf.setDbcolumnname(map.get("dbcolumnname"));
			dtf.setChangename(map.get("changename"));
			dtf.setTableid(tableid);
			List<DictTFactorPO> dtfs = talMap.get(tableid);
			if (dtfs == null) {
				dtfs = new ArrayList<DictTFactorPO>();
				dtfs.add(dtf);
				talMap.put(tableid, dtfs);
			} else {
				dtfs.add(dtf);
				talMap.put(tableid, dtfs);
			}
			talMap.remove(table_id);
		}
		List<DictTFactorPO> dictFactorPOList = this.dictTFactorSelfService
				.getSettingColumnChangeData("table0", talMap);
		List<TreePO> treePoList = new ArrayList<TreePO>();
		for (DictTFactorPO dictTFactorPO : dictFactorPOList) {
			TreePO po = new TreePO();
			po.setId(dictTFactorPO.getColumnid());
			po.setName(dictTFactorPO.getName());
			po.setPId(dictTFactorPO.getTableid());
			treePoList.add(po);

		}
		/*
		 * return AdapterManager.createRefColumn(this.dictTFactorSelfService.
		 * getSettingColumnChangeData("table0",talMap), format);
		 */
		return treePoList;
	}

	/**
	 * /report/dict/rel.do?businessObjId=EFCEBA43A7F44225E040A8C02003204D&
	 * columnId=EFCEBA43A8224225E040A8C02003204D&relatedId=
	 * EFCEBA43A8234225E040A8C02003204D&value=0 获得关联表数据
	 * 
	 * @param businessObjId
	 *            业务对象ID
	 * @param columnId
	 *            被关联列ID
	 * @param relatedId
	 *            关联列ID
	 * @param value
	 *            被关联列选择值
	 * @param request
	 * @return
	 * @throws Exception
	 */
	/*
	 * @RequestMapping(value="rel")
	 * 
	 * @ResponseBody public Object getRelated(@RequestParam(value =
	 * "businessObjId") String businessObjId,
	 * 
	 * @RequestParam(value = "async",required = false,defaultValue = "false")
	 * boolean async,
	 * 
	 * @RequestParam(value = "columnId") String columnId,
	 * 
	 * @RequestParam(value = "relatedId") String relatedId,
	 * 
	 * @RequestParam(value = "value") String value, HttpServletRequest request)
	 * throws Exception{
	 * 
	 * String format = request.getParameter("format"); String superGUID =
	 * request.getParameter("pId"); if(async){ if(superGUID == null) superGUID =
	 * "#"; }else{ superGUID = null; } Map<String,String> params = null; if(
	 * StringUtils.isNotEmpty( request.getParameter("params")) &&
	 * !request.getParameter("params").equalsIgnoreCase("null") ) params =
	 * (Map)(new
	 * ObjectMapper()).readValue(request.getParameter("params"),Map.class);
	 * return null;
	 * 
	 * return
	 * AdapterManager.createRefColumn(dictService.getRelColumn(businessObjId
	 * ,columnId,superGUID,relatedId,value,params),format); }
	 */

	/**
	 * 加载右侧数据 Suit
	 * 
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value = "getColumnData")
	@ResponseBody
	public Object getColumnData(String grid, HttpServletResponse response)
			throws Exception {
		Grid table = (Grid) (new ObjectMapper()).readValue(grid, Grid.class);
		Map<String, Object> map = table.getExtProperties();
		Integer adapterType = Integer.parseInt(map.get("adapterType")
				.toString());
		String ids = map.get("ids").toString();
		List<DictTFactorPO> dtmList = new ArrayList<DictTFactorPO>();
		String tableid = map.get("tableid").toString();
		if (ids != null) {
			String[] columnids = ids.split(",");
			for (String id : columnids) {
				DictTFactorPO dtf = this.dictTFactorService
						.getDictTFactorByColumnId(id);
				if (dtf != null) {
					if (tableid != null && !"".equals(tableid)) {
						String frmcolid = dtf.getFrmcolid();
						if (frmcolid != null && !"".equals(frmcolid)) {
							DictTFactorPO olddtf = this.dictTFactorService
									.getDictTFactorByColumnId(frmcolid);
							String oldName = olddtf.getDbcolumnname() == null ? null
									: olddtf.getDbcolumnname().toUpperCase();
							String newName = dtf.getDbcolumnname() == null ? null
									: dtf.getDbcolumnname().toUpperCase();
							if (oldName != null && newName != null
									&& !newName.equals(oldName)) {
								dtf.setDbcolumnname(oldName);
								dtf.setChangename(newName);
							}
							dtf.setColumnoldid(dtf.getColumnid());
							dtf.setColumnid(frmcolid);
							dtf.setTableid(dtf.getFrmtabid());
						}
					}
					dtmList.add(dtf);
				}
			}
		}
		if (adapterType != 1) {
			return null;
		} else {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for (DictTFactorPO dictTFactorPO : dtmList) {
				list.add(ConvertUtil.toMap(dictTFactorPO));
			}
			setGridData(list, table.getPageInfo());
		}
		return table.getPageInfo();
	}

	/**
	 * 
	 * @param jsonList
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
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
				if (null != value && JSONObject.NULL != value
						&& !"undefined".equals(value)) {
					map.put(key, DictDBConstants.fromEncodedUnicode(value
							.toString().toCharArray(), 0, value.toString()
							.length()));
				} else {
					map.put(key, null);
				}
			}
			l.add(map);
		}
		return l;
	}

	/**
	 * 添加 3 可更新视图
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveOrUpdateDictTModelForBusiness")
	@ResponseBody
	public Object saveOrUpdateDictTModelForBusiness(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "json") String poStr,
			@RequestParam(value = "datas") String datas,
			@RequestParam(value = "filters") String filters) throws Exception {
		Map str = new HashMap();
		DictTModelPO dtm = (DictTModelPO) (new ObjectMapper()).readValue(poStr,
				DictTModelPO.class);
		String tableId = request.getParameter("tableId");
		if (dtm.getIspartition() == null)
			dtm.setIspartition("0");
		if (dtm.getIsreserved() == null)
			dtm.setIsreserved("0");
		if (dtm.getIssumtab() == null)
			dtm.setIssumtab("0");
		if (dtm.getIsshow() == null)
			dtm.setIsshow("0");

		if (tableId != null && !"".equals(tableId)) { // -----
			DictTModelPO olddtm = this.dictTModelService.getDictTModelByID(
					tableId, false);
			dtm.setDbtablename(olddtm.getDbtablename().toUpperCase());
			dtm.setDealtype(olddtm.getDealtype());
			dtm.setTabletype(olddtm.getTabletype());
			dtm.setIsbak(olddtm.getIsbak());
			dtm.setTableid(tableId);
		}
		String extprop = dtm.getExtprop();
		if (extprop == null || extprop.trim().equals("")) {
			extprop = "";
			for (int i = 0; i < DictDBConstants.DICT_DB_EXEPROP - 1; i++) {
				extprop = extprop + "0";
			}
			dtm.setExtprop(extprop);
		}
//		datas = datas.substring(1, datas.length() - 1);
//		datas = datas.replace("\\\"", "");
//		filters = filters.substring(1, filters.length() - 1);
//		filters = filters.replace("\\\"", "");
//		List<Map<String, String>> sourceColumnList = JsonToMap(datas);
//		List<Map<String, String>> settingList = JsonToMap(filters);
		@SuppressWarnings("unchecked")
		List<Map<String, String>> sourceColumnList
		= (List<Map<String, String>>)(
			new ObjectMapper()).readValue(datas, List.class);
		List<DictTFactorPO> sourceList = (List<DictTFactorPO>)(
			new ObjectMapper()).readValue(datas, List.class);
		List<Map<String, String>> settingList = new ArrayList<Map<String, String>>();
		if(filters != null && !filters.equals(""));
			settingList = (List<Map<String, String>>)new ObjectMapper().readValue(filters, List.class);

		if (tableId != null && !"".equals(tableId)) {
			try {
				// ---------------------修改
				this.dictTModelSelfService.updateDictTModelForUpdateView(dtm,
						sourceColumnList, settingList);
			} catch (Exception e) {
				e.printStackTrace();
				str.put("error", e.getMessage().replace("\"", "\'"));
			}
		} else {
			// --------------增加
			try {
				// 3可更新视图表
				this.dictTModelSelfService.insertDictTModelForUpdateView(dtm,
						sourceColumnList, settingList);
			} catch (Exception e) {
				e.printStackTrace();
				str.put("error", e.getMessage().replace("\"", "\'"));
			}
		}
		if (str.get("error") == null) {
			str.put("success", "可更新视图登记成功");
		}
		return str;
	}

	/**
	 * 创建 备份表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createBakTable")
	@ResponseBody
	public Object createBakTable(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String msg = "";
		String prefix = DictDBConstants.PREFIX_TABLE;
		String strs = request.getParameter("tableids");
		Map<String, String> remap = new HashMap<String, String>();
		String[] tableids = strs.split(",");
		try {
			for (String tableid : tableids) {
				DictTModelPO dtm = this.dictTModelService.getDictTModelByID(
						tableid, false);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("dealid", dtm.getDealtype());
				List<DictTDefaultcolPO> dtdList = this.dictTDefaultcolMapper
						.findDictTDefaultcol(map);
				String tableType = dtm.getTabletype();
				//为了解决已经备份过的表，bak表被误删之后，再次备份失败的问题
				String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
						+ "'" + prefix + dtm.getDbtablename() + "_BAK'";
				Integer isTableExist = dictDBExecuteService
						.getIfExistsInDB(ifExistsTableSql);// 同名物理分区表
				if (isTableExist == 0&&"1".equals(dtm.getIsbak())) {
					dtm.setIsbak("0");
				}

				if (!"1".equals(dtm.getIsbak())) {
					if (dtdList != null && dtdList.size() > 0) {
						try {
							// 1物理表
							if ("1".equals(tableType)) {

								this.dictTModelSelfService
										.createDictTModelForBak(dtm, dtdList);
							}
							if ("2".equals(tableType)) {
								remap.put("key", "视图表不能创建备份表");
								return remap;
							}
							// 可更新视图
							if ("3".equals(tableType)) {
								this.dictTModelSelfService
										.createDictTModelForUpdateViewBak(dtm);
							}
						} catch (Exception e) {
							String eMsg = e.getMessage();
							if(null == eMsg || eMsg.isEmpty())
								eMsg = "所需备份的表数据异常!";
							msg = msg + dtm.getName() + "["
									+ dtm.getDbtablename() + "]" + ":"
									+ eMsg + ".\n";
						}
					} else {
						msg = msg + dtm.getName() + "[" + dtm.getDbtablename()
								+ "]" + ":未找到缺省列信息.\n";
					}
				}else{
					map = new HashMap<String, Object>();
					map.put("tableid", dtm.getTableid());
					map.put("dbtablename", prefix + dtm.getDbtablename()+"_BAK");
					List<DictTFactorPO> pFactorListP0 = this.dictTFactorMapper.findNewPDictTFactor(map);
					if (pFactorListP0 != null && pFactorListP0.size() > 0) {
						try {
							// 1物理表
							if ("1".equals(tableType)) {

								for(DictTFactorPO dTFPO : pFactorListP0){
									
									this.dictTFactorSelfService.createDictTFactorForBak(dtm,dTFPO,true);
								}
							
							}
							if ("2".equals(tableType)) {
								remap.put("key", "视图表不能创建备份表");
								return remap;
							}
							// 可更新视图
							if ("3".equals(tableType)) {
								this.dictTModelSelfService
										.createDictTModelForUpdateViewBak(dtm);
							}
						} catch (Exception e) {
							String eMsg = e.getMessage();
							if(null == eMsg || eMsg.isEmpty())
								eMsg = "所需备份的表数据异常!";
							msg = msg + dtm.getName() + "["
									+ dtm.getDbtablename() + "]" + ":"
									+ eMsg + ".\n";
						}
					} else {
						msg = msg + dtm.getName() + "[" + dtm.getDbtablename()
								+ "]" + ":未找到该表新增的列.\n";
					}
				}
			}

		} catch (Exception e) {
			String eMsg = e.getMessage();
			if(null == eMsg || eMsg.isEmpty())
				eMsg = "所需备份的表数据异常!";
			msg = "备份出错." + eMsg;
			e.printStackTrace();
		}
		if ("".equals(msg)) {
			msg = "备份成功.\n";
		}
		remap.put("key", msg);
		return remap;
	}

	/**
	 * 删除 备份表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteBakTable")
	@ResponseBody
	public Object deleteBakTable(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String msg = "";
		String strs = request.getParameter("tableids");
		Map<String, String> remap = new HashMap<String, String>();
		String[] tableids = strs.split(",");
		try {
			for (String tableid : tableids) {
				DictTModelPO dtm = this.dictTModelService.getDictTModelByID(
						tableid, false);
				DictTModelPO dtmold = (DictTModelPO) dtm.clone();
				dtmold.setIsbak("0");
				this.dictTModelSelfService.updateDictTModel(dtmold);
				try {
					this.dictTModelSelfService.deleteTableForBak(dtm);
					// msg =msg +
					// dtm.getName()+"<"+dtm.getDbtablename()+">"+":删除备份表成功.\n";
				} catch (Exception e) {
					msg = msg + dtm.getName() + "<" + dtm.getDbtablename()
							+ ">" + ":" + e.getMessage() + ".\n";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = msg + "系统出现异常，请稍后再试.\n";
		}
		if ("".equals(msg)) {
			msg = "删除成功.\n";
		}
		remap.put("key", msg);
		return remap;
	}

	/**
	 * 检查 一致性
	 * 
	 */
	@RequestMapping(value = "getConsistency")
	@ResponseBody
	public Object getConsistency(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String msg = "";
		Map<String, String> remap = new HashMap<String, String>();
		String tablenames = request.getParameter("tablenames");
		try {
			String[] str = tablenames.split(",");
			for (String tablename : str) {
				List<ConsistencyPO> listConsistency = this.dictDBExecuteService
						.getConsistencyByArgs(tablename.toUpperCase());
				if (listConsistency != null && listConsistency.size() > 0) {
					for (ConsistencyPO consis : listConsistency) {
						msg += "表 [P#" + consis.getTableName() + "] 中缺失 ["
								+ consis.getFactorName() + "] 列.  ";
					}
				}
			}
		} catch (Exception e) {
			msg = e.getMessage();
			e.printStackTrace();
		}
		if ("".equals(msg)) {
			msg = "一致性正常.\n";
		}
		remap.put("key", msg);
		return remap;
	}

	/**
	 * 修复bak表，修复规则： 将bak表中缺少的列重复添加进去， 然后重新按照主表的视图来进行生成。
	 * 
	 */
	@RequestMapping(value = "recoverBakTable")
	@ResponseBody
	public Object recoverBakTable(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String msg = "";
		String appId = request.getParameter("appId");
		Map<String, String> remap = new HashMap<String, String>();
		try {

			// BAK表物理表与主表物理表的列不一致的集合
			List<Map> needRecoverColsList = this.dictDBExecuteService
					.getNeedRecoverColsForBakTable(appId, true);

			// if(needRecoverColsList != null && needRecoverColsList.size()>0){
			// this.dictTModelSelfService.recoverBakTable(needRecoverColsList,true);
			// }
			// BAK表视图与主表视图的列不一致的集合
			List<Map> needRecoverColsViewList = this.dictDBExecuteService
					.getNeedRecoverColsForBakTable(appId, false);
			if ((needRecoverColsList == null || needRecoverColsList.size() == 0)
					&& (needRecoverColsViewList == null || needRecoverColsViewList
							.size() == 0)) {
				msg = "没有需要修复的备份(BAK)表";
				remap.put("key", msg);
				return remap;
			}
			this.dictTModelSelfService.recoverBakTable(needRecoverColsViewList,
					false);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			msg = "系统异常！修复失败";
		}

		//
		// String tablenames = request.getParameter("tablenames");
		// try {
		// String[] str = tablenames.split(",");
		// for(String tablename:str){
		// List<ConsistencyPO> listConsistency =
		// this.dictDBExecuteService.getConsistencyByArgs(tablename.toUpperCase());
		// if(listConsistency!=null&&listConsistency.size()>0){
		// for(ConsistencyPO consis :listConsistency){
		// msg
		// +="表 <P#"+consis.getTableName()+"> 中缺失 <"+consis.getFactorName()+"> 列.\n";
		// }
		// }
		// }
		// } catch (Exception e) {
		// //
		// msg = e.getMessage();
		// e.printStackTrace();
		// }
		if ("".equals(msg)) {
			msg = "成功修复备份(BAK)表";
		}
		remap.put("key", msg);
		return remap;
	}

	/**
	 * 下发功能的实现接口
	 */
	@RequestMapping(value = "sendTablesToSubCity")
	@ResponseBody
	public Object sendTablesToSubCity(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String tableidString = request.getParameter("tableids");// 下发的表集合
		String subCityString = request.getParameter("subcitys");// 被下发的城市集合
		String[] subCityData = subCityString.split(",");
		String[] tableIdData = tableidString.split(",");
		return dictTModelSelfService.sendTablesToSubCity(tableIdData,
				subCityData);
	}

	/**
	 * 跳转到列管理页面的form表单
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getParamsToFactorFormPage")
	public Object getParamsToFactorFormPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return returnStr + "TFactorFormPage";
	}

	/**
	 * 获得当前登录用户所在地区的下属地区数据集
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getLocalAreaSubAreas")
	@ResponseBody
	public Object getLocalAreaSubAreas(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<TreeNode> leftTree = null;
		String treeNodes = "";
		Map<String, String> remap = new HashMap<String, String>();
		try {
			UserDTO user = SecureUtil.getCurrentUser();
			String userProvince = user.getProvince();
			if (ConverTables.isNotNull(userProvince)) {
				if (userProvince.endsWith("00")) {
					userProvince = userProvince.substring(0,
							userProvince.length() - 2);
				}
				leftTree = entryService.getSubCityTreeNodes(
						DictDBConstants.CODETDISTRICT, userProvince);
				remap.put("key",
						(new ObjectMapper()).writeValueAsString(leftTree));
			} else {
				throw new Exception("当前登录用户所在地区为空！");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return leftTree;
	}
	
	/**
	 * 获得当前登录用户所在地区的下属地区数据集
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getSubAreas")
	@ResponseBody
	public Object getSubAreas(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<TreeNode> leftTree = null;
		try {
			String agencyID = SecureUtil.getCurrentUser().getUpagencyid();//当前操作员的单位id
			leftTree = this.dictTModelSelfMapper.getSubAreas(agencyID);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return leftTree;
	}

	/**
	 * 获取平台数据元树
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getDataElementTree")
	@ResponseBody
	public List<DictElementPO> getDataElementTreeNodes(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return dictTModelSelfService.getDataElementTreeNodes();
	}

	/**
	 * 打开增加物理表 页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "copyTableDefine")
	public String copyTableDefine(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String suitId = request.getParameter("suitId");
		request.setAttribute("suitId", suitId);

		if (suitId != null && !"".equals(suitId)) {
			DictTSuitPO dts = this.dictTSuitService.getDictTSuitByID(suitId);
			if (dts != null) {
				request.setAttribute("suit_name", dts.getSuitname());
				request.setAttribute("appid", dts.getAppid());
				map.put("appid", dts.getAppid());
			}
		}

		String tableId = request.getParameter("tableID");
		if (tableId != null && !"".equals(tableId)) {
			DictTModelPO dtm = this.dictTModelService.getDictTModelByID(
					tableId, false);
			if (dtm != null) {
				request.setAttribute("dtm", dtm);
			}
		}
		String status = this.dictDBExecuteService.getGlobalIsmultdb();
		if (status != null && !"".equals(status)) {
			status = DictDBConstants.status_DB.get(status);
			request.setAttribute("v_status", "[" + status + "]");
		}
		List<DictTDealtypePO> listdtdt = null;
		listdtdt = this.dictTDealtypeMapper.findDictTDealtype(map);
		String selectJson = "";
		if (listdtdt != null && listdtdt.size() > 0) {
			selectJson = (new ObjectMapper()).writeValueAsString(listdtdt);
		}
		request.setAttribute("selectJson", selectJson);

		return returnStr + "TModelForCopy";
	}

	/**
	 * 复制表创建
	 * 
	 * @param request
	 * @param sourceTableID
	 * @param poStr
	 * @throws Exception
	 */
	@RequestMapping(value = "replicateTable")
	@ResponseBody
	public Object replicateDictTModelForPhysics(HttpServletRequest request,
			String sourceTableID, @RequestParam(value = "json") String poStr)
			throws Exception {
		Map<String, String> str = new HashMap<String, String>();
		DictTModelPO dtm = (DictTModelPO) (new ObjectMapper()).readValue(poStr,
				DictTModelPO.class);
		try {
			// 复制创建表
			dictTModelSelfService.replicateDictTModelForPhysics(sourceTableID,
					dtm);
		} catch (Exception e) {
			str.put("error", e.getMessage().replace("\"", "\'"));
			return str;
		}
		if (str.get("error") == null) {
			str.put("success", "表创建成功");
		}
		return str;

	}
	
	
	/**
	 * 批量同步全部注册平台表
	 * 
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "registerAllTableToDic")
	@ResponseBody
	public Object registerAllTableToDic(HttpServletRequest request)
			throws Exception {
		try {
			// 批量同步全部注册平台表
			dictTModelService.registerAllTableToDic();
		} catch (Exception e) {
			return "Register AllTable To FASPDic Fail";
		}
		return "Register AllTable To FASPDic Success";
	}

	/**
	 * 批量同步全部注册平台表  业务表管理页面打开时调用
	 * @author ZK
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "registerAllTable")
	@ResponseBody
	public String registerAllTable(HttpServletRequest request) throws Exception {
		try {
			// 批量同步全部注册平台表
			dictTModelService.registerAllTableToDic();
		} catch (Exception e) {
			return "Register AllTable To FASPDic Fail";
		}
		return "";
	}
	
	/**
	 * 表定义下发
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "copyDistrictFinyear")
	@ResponseBody
	public Object copyDistrictFinyear(HttpServletRequest request) {
		//返回结果
    	ResultPO resultPO = null;
    	try {
    		String appID = request.getParameter("appID");
    		String fromFinyear = request.getParameter("finYear");
	    	String tables = request.getParameter("tables");
	    	String toArea = request.getParameter("toArea");
	    	
	    	String fromAreaID = SecureUtil.getCurrentUser().getUpagencyCode();
	    	
	    	net.sf.json.JSONArray tablesArray = net.sf.json.JSONArray.fromObject(tables);
	    	List<String> busiTypeIds = new ArrayList<String>();
	    	if(tablesArray.size() > 0){
				for(int i = 0; i < tablesArray.size(); i++){
					net.sf.json.JSONObject jObject = net.sf.json.JSONObject.fromObject(tablesArray.get(i));
					String tableID = jObject.get("tableid").toString();
					busiTypeIds.add(tableID);
				}
			}else{
				throw new ServiceException(ExceptionCode.ERR_00000, "请选择表", false);
			}
	    	
	    	net.sf.json.JSONArray toAreaArray = net.sf.json.JSONArray.fromObject(toArea);
	    	for(int j = 0; j < toAreaArray.size(); j++){
				net.sf.json.JSONObject jObject1 = net.sf.json.JSONObject.fromObject(toAreaArray.get(j));
				String toAreaID = jObject1.get("code").toString();
				if(StringUtils.isNotEmpty(appID) && StringUtils.isNotEmpty(fromFinyear) && StringUtils.isNotEmpty(fromAreaID) && StringUtils.isNotEmpty(toAreaID)){
					bgtAssignSpecifiedTableStructImpl.sendData("", toAreaID, fromFinyear, busiTypeIds, fromAreaID, appID);
				}
			}
			resultPO = new ResultPO("下发完成");
    	} catch (ServiceException e) {
            e.printStackTrace();
            resultPO = new ResultPO(e.getCode(), e.getMessage());
        }
    	return resultPO;
	}
}
