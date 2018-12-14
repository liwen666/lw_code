package commons.dict.web;

import gov.mof.fasp2.dic.dataelement.dto.DataElementDTO;
import gov.mof.fasp2.dic.dataelement.service.IDataElementService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.longtu.framework.util.ServiceFactory;
import com.tjhq.commons.dict.external.dao.DictTFactorMapper;
import com.tjhq.commons.dict.external.po.DictTColextpropPO;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTFactorcodePO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.po.DictTModelcodePO;
import com.tjhq.commons.dict.external.service.IDictTColextpropService;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.dict.external.service.IDictTFactorcodeService;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.dict.external.service.IDictTModelcodeService;
import com.tjhq.commons.dict.external.service.IDictTTabextpropService;
import com.tjhq.commons.dict.service.IDictDBExecuteService;
import com.tjhq.commons.dict.service.IDictTFactorSelfService;
import com.tjhq.commons.dict.service.IDictTSetHrefParmService;
import com.tjhq.commons.dict.util.DictDBConstants;
import com.tjhq.commons.dict.util.SortList;
import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.grid.basegrid.service.impl.BaseGridService;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.jackson.ObjectMapper;
import com.tjhq.commons.setting.external.po.TreeNode;
import com.tjhq.commons.setting.input.po.DictTSetHrefParmPO;
import com.tjhq.commons.setting.input.web.ConverTables;
import com.tjhq.commons.utils.ConvertUtil;
import com.tjhq.commons.utils.StringUtil;

@Controller
@RequestMapping(value = "/commons/dict/dicttfactor")
public class DictTFactorController extends BaseGridService {

	@Resource
	private IDictTFactorService dictTFactorService;

	@Resource
	private IDictTFactorSelfService dictTFactorSelfService;

	@Resource
	private IDictTModelcodeService dictTModelcodeService;

	@Resource
	private IDictTColextpropService dictTColextpropService;

	@Resource
	private IDictTFactorcodeService dictTFactorcodeService;

	@Resource
	private IDictTModelService dictTModelService;

	@Resource
	private IDictDBExecuteService dictDBExecuteService;

	@Resource
	private IDictTTabextpropService dictTTabextpropService;

	@Resource
	private DictTFactorMapper dictTFactorMapper;

	@Resource
	private IDictTSetHrefParmService dictTSetHrefParmService;

	private static String returnStr = "commons/dict/dict";

	/**
	 * 指定页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "")
	public String page(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableid = request.getParameter("tableId");
		request.setAttribute("tableid", tableid);
		request.setAttribute("editMode", dictTFactorService.getEditMode());
		DictTModelPO dtm = this.dictTModelService.getDictTModelByID(tableid, false);
		request.setAttribute("dtm", dtm);
		String tabletype = request.getParameter("tabletype");
		request.setAttribute("tabletype", tabletype);
		String appid = request.getParameter("appid");
		request.setAttribute("appid", appid);

		/*
		 * //引用代码表 List<DictTModelcodePO> listDictTModelcodePO =
		 * dictTModelcodeService.findDictTModelcodeByArgs(appid);
		 * for(DictTModelcodePO d :listDictTModelcodePO){ d.setDynamicwhere("");
		 * } String csid = (new
		 * ObjectMapper()).writeValueAsString(listDictTModelcodePO);
		 * request.setAttribute("csid", csid);
		 */

		// 列显示方式
		List<inner> listInner = new ArrayList<inner>();
		for (Entry<String, String> m : DictDBConstants.showformat.entrySet()) {
			listInner.add(new inner(m.getKey(), m.getValue()));
		}
		String showformat = (new ObjectMapper()).writeValueAsString(listInner);
		request.setAttribute("showformat", showformat);

		// 列扩展属性
		/*
		 * List<DictTColextpropPO> listDictTColextpropPO =
		 * dictTColextpropService.getAllDictTColextpropByAppid(appid); String
		 * extprop = (new
		 * ObjectMapper()).writeValueAsString(listDictTColextpropPO);
		 * request.setAttribute("extprop", extprop);
		 */

		/*
		 * //数据元 ------平台接口 String dataElement = ""; try { IDataElementService
		 * dataElementService = (IDataElementService)
		 * ServiceFactory.getBean("fasp2.dic.dataelement.service");
		 * List<DataElementDTO> dataElementDTOList =
		 * dataElementService.getAllDataElement(); dataElement = (new
		 * ObjectMapper()).writeValueAsString(dataElementDTOList);
		 * 
		 * } catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		// request.setAttribute("dataElement", dataElement);
		// System.out.println("------------------------------------------------------------------------------------------");
		// for(DataElementDTO ded : dataElementDTOList){
		// System.out.println((new ObjectMapper()).writeValueAsString(ded));
		// }
		// System.out.println("------------------------------------------------------------------------------------------");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dbcolumnname", "DATAKEY");
		map.put("tableid", tableid);
		List<DictTFactorPO> dictTFactorDATAKEY = this.dictTFactorMapper
				.findDictTFactor(map);
		if(dictTFactorDATAKEY !=null &&dictTFactorDATAKEY.size()>0){
			request.setAttribute("columnid", dictTFactorDATAKEY.get(0)
					.getColumnid());
		}else{
			request.setAttribute("columnid",null);
		}
		// 数据类型
		List<inner> listDataType = new ArrayList<inner>();
		for (Entry<Integer, String> m : DictDBConstants.dataType_ZH.entrySet()) {
			listDataType.add(new inner(m.getKey() + "", m.getValue()));
		}
		String datatype = (new ObjectMapper()).writeValueAsString(listDataType);
		request.setAttribute("datatype", datatype);

		// 数据类型默认长度设置
		String lengthSet = (new ObjectMapper())
				.writeValueAsString(dictTFactorService
						.getAllDefaultDataLength());
		request.setAttribute("lengthSet", lengthSet);
		request.setAttribute("editMode", dictTFactorService.getEditMode());

		String pageStr = "TFactor";
		if ("1".equals(tabletype)) {
			pageStr = "TFactor";
		} else if ("2".equals(tabletype)) {
			pageStr = "TFactorForView";
		} else if ("3".equals(tabletype)) {
			pageStr = "TFactorForBusiness";
		}
		return returnStr + pageStr;
	}

	@RequestMapping(value = "getDataElementForAjax")
	@ResponseBody
	public Object getDataElementForAjax(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 数据元 ------平台接口
		Map<String, String> remap = new HashMap<String, String>();
		String dataElement = "";
		List<TreeNode> list = new ArrayList<TreeNode>();
		try {
			IDataElementService dataElementService = (IDataElementService) ServiceFactory
					.getBean("fasp2.dic.dataelement.service");
			List<DataElementDTO> dataElementDTOList = dataElementService
					.getAllDataElement();
			SortList<DataElementDTO> sortList = new SortList<DataElementDTO>();
			// 按getName排序
			sortList.Sort(dataElementDTOList, "getName", "asc");
			// for(DataElementDTO dataElementDTO:dataElementDTOList){
			// TreeNode treeNode=new TreeNode();
			// treeNode.setId(dataElementDTO.get)
			// dataElementDTO
			// }
			dataElement = (new ObjectMapper())
					.writeValueAsString(dataElementDTOList);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		remap.put("key", dataElement);
		DataElementDTO dataElementDTO = new DataElementDTO();
		return remap;
	}

	@RequestMapping(value = "getCsidForAjax")
	@ResponseBody
	public Object getCsidForAjax(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String appid = request.getParameter("appid");
		Map paramMap = new HashMap();
		paramMap.put("appid", appid);
		// 引用代码表
		List<DictTModelcodePO> listDictTModelcodePO = dictTModelcodeService
				.findDictTModelcodeByArgs(paramMap);
		return listDictTModelcodePO;
	}

	/**
	 * 复制列定义到其他财年
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "copyColumnDefinitionToFactor")
	@ResponseBody
	public Object copyTableDefinitionToModel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map result = new HashMap();
		try {
			String finyear = request.getParameter("finyear");
			String factorString = request.getParameter("factor");
			List<Map<String, String>> factorData = JsonToMap(factorString);
			String message = dictTFactorSelfService
					.copyColumnDefinitionToFactor(finyear, factorData);
			result.put("key", message);
		} catch (Exception e) {
			result.put("key", "复制失败 : " + e.getMessage());
		}

		return result;
	}

	@RequestMapping(value = "getFactorFromView")
	@ResponseBody
	public Object getFactorFromView(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String tableid = request.getParameter("tableid");
		String str = "";
		DictTModelPO dtm = this.dictTModelService.getDictTModelByID(tableid,
				false);
		List<Map<String, Object>> dblist = this.dictDBExecuteService
				.getColumnByViewName(dtm.getDbtablename());
		List<DictTFactorPO> listFactor = this.dictTFactorService
				.getDictTFactorByTableidAndType(tableid, "1");
		Map<String, String> map = new HashMap<String, String>();
		for (DictTFactorPO dtf : listFactor) {
			// 标题列跳过
			if (dtf.getDatatype() == 4) {
				continue;
			}
			map.put(dtf.getDbcolumnname().toUpperCase(), dtf.getDbcolumnname()
					.toUpperCase());
		}
		for (int i = 0; i < dblist.size(); i++) {
			Map<String, Object> col = dblist.get(i);
			String dbColumnName = ((String) col.get("COLUMN_NAME"))
					.toUpperCase();
			if (map.get(dbColumnName) != null) {
				dblist.remove(i);
				i--;
			} else {
				// 处理类型转换
				// 数字型
				String dataType = (String) col.get("DATA_TYPE");
				String newDataType = DataType.STRING;
				if ("NUMBER".equals(dataType)) {
					// 如果是没有精度，是整型
					String dataScale = col.get("DATA_SCALE") == null ? "0"
							: col.get("DATA_SCALE").toString();
					if (dataScale == null || dataScale.equals("0")) {
						newDataType = DataType.INT;
					} else {
						newDataType = DataType.NUMBER;
					}
				}
				// 字符型
				else if ("VARCHAR2".equals(dataType) || "CHAR".equals(dataType)) {
					newDataType = DataType.STRING;
				}
				col.put("DATA_TYPE", newDataType);
			}
		}
		return dblist;
	}

	@RequestMapping(value = "getFactorByCsid")
	@ResponseBody
	public Object getFactorByCsid(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String tableid = request.getParameter("tableid");
		String str = "";
		// 待选绑定列 引用csid
		List<DictTFactorPO> listbandcolumnid = new ArrayList<DictTFactorPO>();
		List<DictTFactorPO> dtfList = this.dictTFactorService
				.getDictTFactorByTableidAndType(tableid, "1");
		for (DictTFactorPO dtf : dtfList) {
			if (dtf.getCsid() != null && !"".equals(dtf.getCsid())) {
				listbandcolumnid.add(dtf);
			}
		}
		// if(listbandcolumnid.size()>0){
		// str = (new ObjectMapper()).writeValueAsString(listbandcolumnid);
		// }
		// if(str ==null || str.equals("")){
		// str="[]";
		// }
		return listbandcolumnid;
	}

	/**
	 * 加载左侧树 ztree <DictTSuitPO>
	 * 
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value = "getFactorTrees")
	@ResponseBody
	public Object getFactorTrees(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String tableid = request.getParameter("tableid");
		Map<String, String> remap = new HashMap<String, String>();
		List<DictTFactorPO> dtfList = null;
		if (tableid != null && !"".equals(tableid)) {
			dtfList = dictTFactorService.getDictTFactorsByTableId(tableid);
			for (DictTFactorPO dtf : dtfList) {
				if ("0".equals(dtf.getIsleaf())
						&& (dtf.getSuperid() == null
								|| "".equals(dtf.getSuperid()) || "0"
									.equals(dtf.getSuperid()))) {// 跟标题
					dtf.setDrag(true);
					dtf.setDropInner(true);
					dtf.setDropRoot(false);
				} else if ("0".equals(dtf.getIsleaf())) {// 子标题
					dtf.setDrag(true);
					dtf.setDropInner(true);
					dtf.setDropRoot(false);
				} else { // 普通列
					dtf.setDrag(true);
					dtf.setDropInner(true);
					dtf.setDropRoot(true);
				}
				dtf.setOpen(true);
			}
		}
		remap.put("key", (new ObjectMapper()).writeValueAsString(dtfList));
		return remap;
	}

	/**
	 * 根据代码表tableid 加载代码表的列
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getDictTFactorcodeSelect")
	@ResponseBody
	public Object getDictTFactorcodeSelect(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String columnid = request.getParameter("columnid");
		String str = "";
		List<DictTFactorcodePO> dtfList = new ArrayList<DictTFactorcodePO>();
		if (columnid != null && !"".equals(columnid)) {
			DictTFactorPO dtf = this.dictTFactorService
					.getDictTFactorByColumnId(columnid);
			if (dtf != null) {
				dtfList = dictTFactorcodeService
						.getDictTFactorcodePOsByTableId(dtf.getCsid());
			}
		}
		return dtfList;
	}

	/**
	 * 加载右侧表头
	 * 
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value = "getFactorListDataHead")
	@ResponseBody
	public String getFactorListDataHead(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String suitId = request.getParameter("suitId");
		String gridHead = "";
		Grid grid = dictTFactorSelfService.getDictTFactorHead("myTable");
		gridHead = (new ObjectMapper()).writeValueAsString(grid);
		return gridHead;
	}

	/**
	 * 加载右侧数据
	 * 
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value = "getFactorListData")
	@ResponseBody
	public Object getFactorListData(HttpServletRequest request, String grid)
			throws Exception {
		Grid table = (Grid) (new ObjectMapper()).readValue(grid, Grid.class);
		Map<String, Object> map = table.getExtProperties();
		String suitId = map.get("adapterType").toString();
		// Integer adapterType =
		// Integer.parseInt((String)map1.get("adapterType"));
		List<DictTModelPO> dtmList = null;

		String columnid = map.get("columnid").toString();
		String tableid = (String) map.get("tableid");
		Integer adapterType = Integer.parseInt(suitId);
		List<DictTFactorPO> dtfList = new ArrayList<DictTFactorPO>();
		// 根据左侧树 节点id suitId
		if (columnid != null && !"".equals(columnid) && !"-1".equals(columnid)) {
			DictTFactorPO dtf = this.dictTFactorService
					.getDictTFactorByColumnId(columnid);
			if (dtf != null) {
				if ("0".equals(dtf.getIsleaf())) {
					// dtfList =
					// dictTFactorService.getDictTFactorListByColumnId(
					// columnid, dtf.getTableid(), true);
					dtfList = dictTFactorService.getDictTFactorListBySuperID(
							columnid, dtf.getTableid());
				}
				dtfList.add(0, dtf);
			}
		} else if ("-1".equals(columnid)) {
			dtfList = dictTFactorService.getDictTFactorListByColumnId(columnid,
					tableid, true);
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (DictTFactorPO dictTFactorPO : dtfList) {
			list.add(ConvertUtil.toMap(dictTFactorPO));
		}
		setGridData(list, table.getPageInfo());
		return table.getPageInfo();
	}

	/**
	 * ztree 拖拽
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updateDictTFactorForTree")
	@ResponseBody
	public Object updateDictTFactorForTree(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> msg = new HashMap<String, String>();
		String targetid = request.getParameter("targetid");
		String sourseid = request.getParameter("sourseid");
//		String orderid = request.getParameter("orderid");
		String levelno = request.getParameter("levelno");
		String supperid = request.getParameter("supperid");
		String moveType = request.getParameter("moveType");
		try {
			DictTFactorPO dtf = this.dictTFactorService
					.getDictTFactorByColumnId(sourseid);

			if (dtf != null) {
				String tableid = dtf.getTableid();
				dtf.setSuperid(supperid);
				dtf.setLevelno(Integer.parseInt(levelno));// 级次

				// List<DictTFactorPO> listDictTFactor =
				// this.dictTFactorService.getDictTFactorsByTableIdForTree(tableid);
				List<DictTFactorPO> listDictTFactor = this.dictTFactorService
						.getDictTFactorsByTableIdForTreeAndChild(tableid,
								dtf.getSuperid());
				DictTFactorPO sourseiddtf = recursionTitle(sourseid,
						listDictTFactor);
				if (targetid == null || "0".equals(targetid)) {
					// 没有 目标ID orderid 排序在最后面 级次为1级
					Map<String, Object> m = new HashMap<String, Object>();
					m.put("tableid", tableid);
					m.put("levelno", 1);
					Integer max = this.dictTFactorSelfService
							.getMAXColumnOrderid(m);
					dtf.setOrderid(max + 1);
					// dtf.setLevelno(1);
					if (!"1".equals(dtf.getIsleaf())) {
						recursionLevelno(dtf, sourseiddtf.getDictTFactorList());
					}
				} else {	// 有目标ID 查找目标id 的所在 级次
					// DictTFactorPO root = new DictTFactorPO();
					// root.setColumnid("0");
					// root.setSuperid("-1");
					// root.setDictTFactorList(listDictTFactor);
					// DictTFactorPO dd = recursion(targetid,root);
					List<DictTFactorPO> list = new ArrayList<DictTFactorPO>();
					// if(dd.getDictTFactorList()!=null&&dd.getDictTFactorList().size()>0){
					// list = dd.getDictTFactorList();
					// }else{
					list = listDictTFactor;
					// }
					boolean falg = false;
					for (DictTFactorPO ddd : list) {
						if (falg) {
							Boolean isTrue0 = true;
							Boolean isTrue1 = true;
							if (!(StringUtil.isEmpty(ddd.getSuperid()) || StringUtil
									.isNull(ddd.getSuperid()))) {
								if (ddd.getSuperid().equals(dtf.getColumnid()))
									isTrue0 = false;
							}
							if (ddd.getColumnid().equals(dtf.getColumnid()))
								isTrue1 = false;
							if (isTrue0 && isTrue1) {
								ddd.setOrderid(ddd.getOrderid() + 2);
								this.dictTFactorSelfService.updateDictTFactor(
										ddd, true);
							}
						}
						if (targetid.equals(ddd.getColumnid())) {
							if ("inner".equals(moveType)) {
								List<DictTFactorPO> listchild = ddd
										.getDictTFactorList();
								int j = 1;
								for (DictTFactorPO d_ : listchild) {
									if (d_.getOrderid() > j) {
										j = d_.getOrderid();
									}
								}
								dtf.setOrderid(j + 1);
							} else if ("prev".equals(moveType)) {
								Integer i = ddd.getOrderid();
								dtf.setOrderid(i);
								ddd.setOrderid(i + 1);
								this.dictTFactorSelfService.updateDictTFactor(
										ddd, true);
								falg = true;
							} else if ("next".equals(moveType)) {
								Integer i = ddd.getOrderid();
								dtf.setOrderid(i + 1);
								falg = true;
							}
							if (null != sourseiddtf) {
								if (!"1".equals(sourseiddtf.getIsleaf())) {
									recursionLevelno(dtf,
											sourseiddtf.getDictTFactorList());
								}
							}

						}
					}
				}
				this.dictTFactorSelfService.updateDictTFactor(dtf, true);
				dictTFactorSelfService.updateDictTFactorLevelNo(tableid);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg.put("error", e.getMessage().replace("\"", "\'"));
		}

		if (msg.get("error") == null) {
			msg.put("success", "保存成功");
		}
		return msg;
	}

	/**
	 * 
	 * @param soursedtf
	 * @param listDictTFactor
	 */
	public void recursionLevelno(DictTFactorPO soursedtf,
			List<DictTFactorPO> listDictTFactor) {
		for (DictTFactorPO d : listDictTFactor) {
			d.setLevelno(soursedtf.getLevelno() + 1);
			try {
				this.dictTFactorSelfService.updateDictTFactor(d, true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (d.getDictTFactorList() != null
					&& d.getDictTFactorList().size() > 0) {
				recursionLevelno(d, d.getDictTFactorList());
			}
		}
	}

	public DictTFactorPO recursion(String targetid, DictTFactorPO dictTFactor) {
		DictTFactorPO dtf = null;
		List<DictTFactorPO> listDictTFactor = dictTFactor.getDictTFactorList();
		for (DictTFactorPO d : listDictTFactor) {
			if (targetid.equals(d.getColumnid())) {
				dtf = d;
				return dtf;
			} else {
				dtf = recursion(targetid, d);
			}
			if (dtf != null)
				break;
		}
		return dtf;
	}

	public DictTFactorPO recursionTitle(String sourseid,
			List<DictTFactorPO> listDictTFactor) {
		DictTFactorPO dtf = null;
		for (DictTFactorPO d : listDictTFactor) {
			if (sourseid.equals(d.getColumnid())) {
				dtf = d;
				return dtf;
			} else {
				if (d.getDictTFactorList() != null
						&& d.getDictTFactorList().size() > 0) {
					dtf = recursionTitle(sourseid, d.getDictTFactorList());
				}
			}
		}
		return dtf;
	}

	/**
	 * xiu gai biaoti
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updateDictTFactorForTitle")
	@ResponseBody
	public Object updateDictTFactorForTitle(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> str = new HashMap<String, String>();
		String updateTitle = request.getParameter("updateTitle");
		String columnid = request.getParameter("columnid");
		String isVisible = request.getParameter("isVisible");
		String alias = request.getParameter("alias");
		try {
			DictTFactorPO dtf = this.dictTFactorService
					.getDictTFactorByColumnId(columnid);
			dtf.setName(updateTitle);
			dtf.setIsvisible(isVisible);
			dtf.setAlias(alias);
			// 标题列不需要同步到平台
			this.dictTFactorSelfService.updateDictTFactor(dtf, false);
		} catch (Exception e) {
			e.printStackTrace();
			str.put("error", e.getMessage().replace("\"", "\'"));
		}

		if (str.get("error") == null) {
			str.put("success", "保存成功");
		}
		return str;
	}

	/**
	 * 创建列
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveDictTFactor")
	@ResponseBody
	public Object saveDictTFactor(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "json") String poStr) throws Exception {
		Map str = new HashMap();
		String hrefparmInsertArray = request
				.getParameter("hrefparmInsertArray");
		String hrefparmUpdateArray = request
				.getParameter("hrefparmUpdateArray");
		String hrefparmDeleteArray = request
				.getParameter("hrefparmDeleteArray");
		DictTFactorPO dtf = (DictTFactorPO) (new ObjectMapper()).readValue(
				poStr, DictTFactorPO.class);

		// dtf.setTableid(request.getParameter("tableid"));
		// dtf.setSuperid(request.getParameter("superid"));
		// dtf.setIsleaf(request.getParameter("isleaf"));
		// if (ConverTables.isNotNull(request.getParameter("orderid"))) {
		// dtf.setOrderid(Integer.parseInt(request.getParameter("orderid")));
		// } else {
		// dtf.setOrderid(null);
		// }
		// if (ConverTables.isNotNull(request.getParameter("levelno"))) {
		// dtf.setLevelno(Integer.parseInt(request.getParameter("levelno")));
		// } else {
		// dtf.setLevelno(null);
		// }
		// dtf.setColumnid(request.getParameter("columnid"));
		if (dtf != null) {
			String tableId = dtf.getTableid();
			DictTModelPO dtm = this.dictTModelService.getDictTModelByID(
					tableId, false);
			String tabletype = dtm.getTabletype();
			try {
				/**
				 * 表类型：1物理表、2（不更新）视图表、3（可更新）视图表
				 */
				dictTFactorSelfService.initNewFactor(dtf, dtm);
				List<Map<String, Object>> listInsert = null;
				/*
				 * List<Map<String,Object>> listUpdate = null;
				 * List<Map<String,Object>> listDelete = null;
				 */
				if ("1".equals(dtf.getIshref())) {
					dtf.setHrefparmid(dtf.getColumnid());
					if(hrefparmInsertArray.length() > 0)
						listInsert = JsonToMap(hrefparmInsertArray);
					/*
					 * listUpdate = JsonToMap(hrefparmUpdateArray); listDelete =
					 * JsonToMap(hrefparmDeleteArray);
					 */
				}
				if ("1".equals(tabletype)) {// 1物理表

					this.dictTFactorSelfService.insertDictTFactorForPhysics(
							dtf, dtm, listInsert);

				} else if ("2".equals(tabletype)) {// 2（不更新）视图表
					List<Map<String, Object>> list = this.dictDBExecuteService
							.getColumnByViewName(dtm.getDbtablename());
					for (Map<String, Object> col : list) {
						String dbColumnName = col.get("COLUMN_NAME").toString()
								.toUpperCase();
						if (dbColumnName.equals(dtf.getDbcolumnname())) {
							dtf.setColumnid(this.dictDBExecuteService.getUUID());
							// 处理类型转换
							// 数字型
							String dataType = (String) col.get("DATA_TYPE");
							String dataLength = col.get("DATA_LENGTH") == null ? "0"
									: col.get("DATA_LENGTH").toString();
							String newDataType = DataType.STRING;
							if ("NUMBER".equals(dataType)) {
								// 如果是没有精度，是整型
								String dataScale = col.get("DATA_SCALE") == null ? "0"
										: col.get("DATA_SCALE").toString();
								if (dataScale == null || dataScale.equals("0")) {
									newDataType = DataType.INT;
									dtf.setScale(0);
								} else {
									newDataType = DataType.NUMBER;
									dtf.setScale(Integer.parseInt(dataScale));
								}
								dataLength = col.get("DATA_PRECISION") == null ? "0"
										: col.get("DATA_PRECISION").toString();
							}
							// 字符型
							else if ("VARCHAR2".equals(dataType)
									|| "CHAR".equals(dataType)) {
								newDataType = DataType.STRING;
							}
							if (dataLength == null || dataLength.equals("")) {
								dataLength = "0";
							}
							dtf.setDatatype(Integer.parseInt(newDataType));
							dtf.setDatalength(Integer.parseInt(dataLength));

							dtf.setDbcolumnname(dbColumnName.toUpperCase());
							// dtf.setName(dbColumnName.toUpperCase());
							dtf.setIskey("0");
							String nullable = "0";
							if ("Y".equals(col.get("NULLABLE"))) {
								nullable = "1";
							}
							dtf.setNullable(nullable);
							dtf.setIsreserve("0");
							dtf.setIsleaf("1");
							if (dtf.getSuperid() == null
									|| "".equals(dtf.getSuperid())) {
								dtf.setSuperid("0");
							}
							dtf.setDefaultvalue(col.get("DATA_DEFAULT") == null ? null
									: col.get("DATA_DEFAULT").toString());
							if (dtf.getDefaultvalue() == null) {
								dtf.setDefaultvalue("");
							}
							/**
							 * -----------------------超链接参数----------------
							 * ------------
							 */
							boolean flag = true;
							if ("1".equals(dtf.getIshref())) {
								dtf.setHrefparmid(dtf.getColumnid());
								String msg = "";
								if (listInsert != null && listInsert.size() > 0) { // 插入数据
									for (Map<String, Object> mapinsert : listInsert) {
										String parmName = mapinsert
												.get("parmName") + "";
										String parmCon = mapinsert
												.get("parmCon") + "";
										String orderid = mapinsert
												.get("_sortid") == null
												|| "".equals(mapinsert
														.get("_sortid")) ? ""
												: mapinsert.get("_sortid")
														.toString();
										if (orderid != null
												&& !"".equals(orderid)) {
											mapinsert.put("orderID",
													Integer.parseInt(orderid)
															+ 1 + "");
										}
										if ((parmName != null
												&& !"".equals(parmName.trim()) && !"null"
													.equals(parmName))
												&& (parmCon != null
														&& !"".equals(parmCon
																.trim()) && !"null"
															.equals(parmCon))) {
											mapinsert.put("hrefParmID",
													dtf.getColumnid());
											List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
											lists.add(mapinsert);
											this.dictTSetHrefParmService
													.insertDictTSetHrefParmPO(lists);
										} else {
											msg = msg
													+ "第"
													+ (Integer
															.parseInt(orderid) + 1)
													+ "行,参数名称,参数表达式设置不能为空值";
											break;
										}
									}
									if (!"".equals(msg)) {
										flag = false;
										str.put("error",
												" 超链接参数设置："
														+ msg.replace("\"",
																"\'"));
									}
								}

							}
							if (flag) {
								this.dictTFactorSelfService.insertDictTFactor(
										dtm.getDbtablename().toUpperCase(),
										dtf, true);
							}
							break;
						}
					}
				} else if ("3".equals(tabletype)) {// 3（可更新）视图表

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				str.put("error", e.getMessage().replace("\"", "\'"));
				return str;
			}
		} else {
			str.put("error", "系统出现异常，请稍后再试");
			return str;
		}
		if (str.get("error") == null) {
			str.put("success", "保存成功");
		}
		return str;
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
	 * 修改列
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updateDictTFactor")
	@ResponseBody
	public Object updateDictTFactor(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "json") String poStr) throws Exception {
		Map str = new HashMap();
		DictTFactorPO dtf = (DictTFactorPO) (new ObjectMapper()).readValue(
				poStr, DictTFactorPO.class);
		DictTFactorPO oldDtf = this.dictTFactorService
				.getDictTFactorByColumnId(dtf.getColumnid());
		if (dtf != null) {
			if (oldDtf.getDatatype() != 4) {
				String tableId = dtf.getTableid();
				if (!("1".equals(dtf.getIsbandcol()))) {
					dtf.setIsbandcol("0");
					dtf.setBandcolumnid(null);
					dtf.setBandrefdwcol(null);
				}
				if (!("1".equals(dtf.getIsregex()))) {
					dtf.setIsregex("0");
					dtf.setRegexpr("");
					dtf.setRegexprinfo("");
				}
				if (!("1".equals(dtf.getIsvirtual()))) {
					dtf.setIsvirtual("0");
					dtf.setVircontext(null);
				}
				if (!("1".equals(dtf.getIshref()))) {
					dtf.setIshref("0");
					dtf.setHrefloc("");
					dtf.setHrefparmid("");
				}
				if (dtf.getIsvisible() == null) {
					dtf.setIsvisible("0");
				}
				String extprop = dtf.getExtprop();
				if (extprop == null || extprop.trim().equals("")) {
					extprop = "";
					for (int i = 0; i < DictDBConstants.DICT_DB_EXEPROP - 1; i++) {
						extprop = extprop + "0";
					}
					dtf.setExtprop(extprop);
				}
				if (oldDtf.getDefaultvalue() == null) {
					oldDtf.setDefaultvalue("");
				}
				if (oldDtf.getIsvirtual() == null) {
					oldDtf.setIsvirtual("0");
				}
				if (oldDtf.getScale() == null) {
					oldDtf.setScale(0);
				}
				if (oldDtf.getIsbandcol() == null) {
					oldDtf.setIsbandcol("0");
				}
				if (dtf.getDefaultvalue() == null) {
					dtf.setDefaultvalue("");
				}
				if (ConverTables.isNotNull(dtf.getDefaultvalue())) {
					if (dtf.getDatatype() == 3) {
						String regex = "[0-9]+?";
						Pattern pattern = Pattern.compile(regex);
						Matcher m = pattern.matcher(dtf.getDefaultvalue());
						if (m.matches() == true) {
							dtf.setDefaultvalue("'" + dtf.getDefaultvalue()
									+ "'");
						}
					}
				}
				dtf.setOpenwindowtype(dtf.getOpenwindowtype() == null
						|| "".equals(dtf.getOpenwindowtype()) ? "0" : dtf
						.getOpenwindowtype());
				dtf.setIskey(dtf.getIskey() == null
						|| "".equals(dtf.getIskey()) ? "0" : dtf.getIskey());
				dtf.setIsleaf(dtf.getIsleaf() == null
						|| "".equals(dtf.getIsleaf()) ? "1" : dtf.getIsleaf());

				dtf.setIsreserve(dtf.getIsreserve() == null
						|| "".equals(dtf.getIsreserve()) ? "0" : dtf
						.getIsreserve());
				dtf.setIssum(dtf.getIssum() == null
						|| "".equals(dtf.getIssum()) ? "0" : dtf.getIssum());
				dtf.setIsupdate(dtf.getIsupdate() == null
						|| "".equals(dtf.getIsupdate()) ? "0" : dtf
						.getIsupdate());

				dtf.setNullable(dtf.getNullable() == null
						|| "".equals(dtf.getNullable()) ? "0" : dtf
						.getNullable());
				dtf.setSuperid(dtf.getSuperid() == null
						|| "".equals(dtf.getSuperid()) ? "0" : dtf.getSuperid());
				dtf.setCsid(dtf.getCsid() == null ? "" : dtf.getCsid());
				dtf.setParentNodeCanCheck(dtf.getParentNodeCanCheck() == null
						|| "".equals(dtf.getParentNodeCanCheck()) ? "0" : dtf
						.getParentNodeCanCheck());
				oldDtf.setCsid(oldDtf.getCsid() == null ? "" : oldDtf.getCsid());
				if (!"".equals(oldDtf.getCsid())) {
					if (!dtf.getCsid().equals(oldDtf.getCsid())) {
						Map<String, Object> m = new HashMap<String, Object>();
						m.put("tableid", dtf.getTableid());
						m.put("bandcolumnid", dtf.getColumnid());
						List<DictTFactorPO> dtss = this.dictTFactorMapper
								.findDictTFactor(m);
						if (dtss != null && dtss.size() > 0) {
							str.put("error", "该列为引用列 ，已经被绑定");
							return str;
						}
					}
				}

				if ((dtf.getIsbandcol().equals(oldDtf.getIsbandcol()))
						&& (dtf.getIsvirtual().equals(oldDtf.getIsvirtual()))) {
					// 不是超链接 清除超链接设置
					if (!("1".equals(dtf.getIshref()))) {
						List<Map<String, Object>> listHrefParm = new ArrayList<Map<String, Object>>();
						Map<String, Object> deleteHrefMap = new HashMap<String, Object>();
						deleteHrefMap.put("hrefParmID", dtf.getColumnid());
						listHrefParm.add(deleteHrefMap);
						this.dictTSetHrefParmService
								.deleteDictTSetHrefParmPObyParm(listHrefParm);
					} else {
						dtf.setHrefparmid(dtf.getColumnid());
					}
					// 来源列和表信息
					dtf.setFrmtabid(oldDtf.getFrmtabid());
					dtf.setFrmcolid(oldDtf.getFrmcolid());

					if (!dtf.equals(oldDtf)) {// 复写equals true需要更新实际表 false则需要更新
						DictTModelPO dtm = this.dictTModelService
								.getDictTModelByID(tableId, false);
						String tabletype = dtm.getTabletype();
						try {
							/**
							 * 表类型：1物理表、2（不更新）视图表、3（可更新）视图表
							 */
							if ("1".equals(tabletype)) {
								// 1物理表
								this.dictTFactorSelfService
										.updateDictTFactorForPhysics(oldDtf,
												dtf, dtm);

							} else if ("2".equals(tabletype)) {
								// 2（不更新）视图表

								this.dictTFactorSelfService.updateDictTFactor(
										dtf, true);

							} else if ("3".equals(tabletype)) {
								// 3（可更新）视图表
								this.dictTFactorSelfService
										.updateDictTFactorForUpdateView(oldDtf,
												dtf, dtm);
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							str.put("error", e.getMessage().replace("\"", "\'"));
						}
					} else {
						try {
							this.dictTFactorSelfService.updateDictTFactor(dtf,
									true);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							str.put("error", e.getMessage().replace("\"", "\'"));
						}
					}
				} else {
					str.put("error", "列修改时不能转换绑定列,或虚列");
				}
			} else {
				try {
					this.dictTFactorSelfService.updateDictTFactor(dtf, false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					str.put("error", e1.getMessage().replace("\"", "\'"));
				}
			}
		} else {
			str.put("error", "系统出现异常,请稍后再试");
		}
		if (str.get("error") == null) {
			str.put("success", "修改成功");
		}
		return str;
	}

	/**
	 * 更新 超链接参数 data saveTableDataForHrefparm
	 * 
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value = "saveTableDataForHrefparm")
	@ResponseBody
	public Object saveTableDataForHrefparm(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String str = "";
		Map<String, String> remap = new HashMap<String, String>();
		String hrefparmInsertArray = request
				.getParameter("hrefparmInsertArray");
		String hrefparmUpdateArray = request
				.getParameter("hrefparmUpdateArray");
		String hrefparmDeleteArray = request
				.getParameter("hrefparmDeleteArray");
		String columnid = request.getParameter("columnid");
		String ishref = request.getParameter("ishref");
		List<Map<String, Object>> listInsert = null;
		List<Map<String, Object>> listUpdate = null;
		List<Map<String, Object>> listDelete = null;
		String msg = "";
		if ("1".equals(ishref)) {
			listInsert = JsonToMap(hrefparmInsertArray);
			listUpdate = JsonToMap(hrefparmUpdateArray);
			listDelete = JsonToMap(hrefparmDeleteArray);
			if (listInsert != null && listInsert.size() > 0) { // 插入数据
				for (Map<String, Object> mapinsert : listInsert) {
					String parmName = mapinsert.get("parmName") + "";
					String parmCon = mapinsert.get("parmCon") + "";
					String orderid = mapinsert.get("_sortid") == null
							|| "".equals(mapinsert.get("_sortid")) ? ""
							: mapinsert.get("_sortid").toString();
					if (orderid != null && !"".equals(orderid)) {
						mapinsert.put("orderID", Integer.parseInt(orderid) + 1
								+ "");
					}
					if ((parmName != null && !"".equals(parmName.trim()) && !"null"
							.equals(parmName))
							&& (parmCon != null && !"".equals(parmCon.trim()) && !"null"
									.equals(parmCon))) {
						mapinsert.put("hrefParmID", columnid);
						List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
						lists.add(mapinsert);
						this.dictTSetHrefParmService
								.insertDictTSetHrefParmPO(lists);
					} else {
						msg = msg + "第" + (Integer.parseInt(orderid) + 1)
								+ "行,参数名称,参数表达式设置不能为空值";
						break;
					}
				}
				if (!"".equals(msg)) {
					throw new Exception("超链接参数设置：" + msg);
				}
			}
			if (listUpdate != null && listUpdate.size() > 0) { // 更新数据
				for (Map<String, Object> mapupdate : listUpdate) {
					String parmName = mapupdate.get("parmName") + "";
					String parmCon = mapupdate.get("parmCon") + "";
					String orderid = mapupdate.get("_sortid") == null
							|| "".equals(mapupdate.get("_sortid")) ? ""
							: mapupdate.get("_sortid").toString();
					if ((parmName != null && !"".equals(parmName.trim()) && !"null"
							.equals(parmName))
							&& (parmCon != null && !"".equals(parmCon.trim()) && !"null"
									.equals(parmCon))) {
						List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
						lists.add(mapupdate);
						this.dictTSetHrefParmService
								.updateDictTSetHrefParmPO(lists);
					} else {
						msg = msg + "第" + (Integer.parseInt(orderid) + 1)
								+ "行,参数名称,参数表达式设置不能为空值";
						break;
					}
				}
				if (!"".equals(msg)) {
					throw new Exception("超链接参数设置：" + msg);
				}
			}
			if (listDelete != null && listDelete.size() > 0) { // 删除数据
				for (Map<String, Object> mapdelete : listDelete) {
					this.dictTSetHrefParmService
							.deleteDictTSetHrefParmPO(listDelete);
				}
				if (!"".equals(msg)) {
					throw new Exception("超链接参数设置：" + msg);
				}
			}

		} else {
			str = "该列没有定义为超链接.";
		}
		if ("".equals(str)) {
			str = "保存成功.";
		}
		remap.put("key", str.replace("\n", ""));
		return remap;
	}

	/**
	 * 删除数据
	 * 
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value = "saveTableData")
	@ResponseBody
	public Object saveTableData(String grid, HttpServletRequest request)
			throws Exception {
		Grid table = (Grid) (new ObjectMapper()).readValue(grid, Grid.class);
		// 获取删除数据
		String str = "";
		Map<String, String> remap = new HashMap<String, String>();
		List<Map<String, Object>> deleteList = table.getDeleteValues();
		if (deleteList != null && deleteList.size() > 0) { // 删除数据
			for (Map<String, Object> map : deleteList) {
				String columnid = map.get("columnid").toString();
				DictTFactorPO dtf = this.dictTFactorService
						.getDictTFactorByColumnId(columnid);
				if (dtf != null) {
					Map<String, Object> m = new HashMap<String, Object>();
					m.put("tableid", dtf.getTableid());
					m.put("bandcolumnid", dtf.getColumnid());
					List<DictTFactorPO> dtss = this.dictTFactorMapper
							.findDictTFactor(m);
					if (dtss != null && dtss.size() > 0) {
						remap.put("key", "该列为引用列 ，已经被绑定！");
						return remap;
					}
					DictTModelPO dtm = dictTModelService.getDictTModelByID(
							dtf.getTableid(), false);
					String tabletype = dtm.getTabletype();
					if (!("1".equals(dtf.getIsreserve()))) {
						/**
						 * 表类型：1物理表、2（不更新）视图表、3（可更新）视图表
						 */
						try {
							if ("1".equals(tabletype)) {// 1物理表
								this.dictTFactorSelfService
										.deleteDictTFactorForPhysics(dtf, dtm);
							} else if ("2".equals(tabletype)) {// 2（不更新）视图表
								this.dictTFactorSelfService
										.deleteDictTFactorForTitle(dtf
												.getColumnid());
							} else if ("3".equals(tabletype)) {// 3（可更新）视图表
								this.dictTFactorSelfService
										.deleteDictTFactorForUpdateView(dtf,
												dtm);
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							str = str + dtf.getDbcolumnname() + " "
									+ e.getMessage().replace("\"", "\'") + ".";
						}
					} else {
						str = str + dtf.getDbcolumnname() + " 状态为保留，不可删除.\n";
					}
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
	 * 创建标题 datatype = 4
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveTitle")
	@ResponseBody
	public Object saveTitle(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map str = new HashMap();
		String tableid = request.getParameter("tableid");
		if (tableid != null && !"".equals(tableid)) {
			String superid = request.getParameter("superid");
			String title_name = request.getParameter("title_name");
			if (title_name != null && !"".equals(title_name)) {
				DictTFactorPO dtf = new DictTFactorPO();
				String id = dictDBExecuteService.getUUID();// id
				dtf.setColumnid(id);
				dtf.setTableid(tableid);
				dtf.setDatatype(4);
				dtf.setIsleaf("0");
				if (superid == null || "".equals(superid))
					superid = "0";
				dtf.setSuperid(superid);
				dtf.setName(title_name);
				if (!("1".equals(dtf.getIsbandcol()))) {
					dtf.setIsbandcol("0");
					dtf.setBandcolumnid(null);
					dtf.setBandrefdwcol(null);
				}
				if (!("1".equals(dtf.getIsregex()))) {
					dtf.setIsregex("0");
					dtf.setRegexpr(null);
					dtf.setRegexprinfo(null);
				}
				if (!("1".equals(dtf.getIsvirtual()))) {
					dtf.setIsvirtual("0");
					dtf.setVircontext(null);
				}
				if (dtf.getSuperid() == null || "".equals(dtf.getSuperid())) {
					dtf.setSuperid("0");
				}
				dtf.setIsbandcol(dtf.getIsbandcol() == null
						|| "".equals(dtf.getIsbandcol()) ? "0" : dtf
						.getIsbandcol());
				dtf.setIshref(dtf.getIshref() == null
						|| "".equals(dtf.getIshref()) ? "0" : dtf.getIshref());
				dtf.setOpenwindowtype(dtf.getOpenwindowtype() == null
						|| "".equals(dtf.getOpenwindowtype()) ? "0" : dtf
						.getOpenwindowtype());
				dtf.setIskey(dtf.getIskey() == null
						|| "".equals(dtf.getIskey()) ? "0" : dtf.getIskey());
				dtf.setIsleaf(dtf.getIsleaf() == null
						|| "".equals(dtf.getIsleaf()) ? "1" : dtf.getIsleaf());
				dtf.setIsregex(dtf.getIsregex() == null
						|| "".equals(dtf.getIsregex()) ? "0" : dtf.getIsregex());
				dtf.setIsreserve(dtf.getIsreserve() == null
						|| "".equals(dtf.getIsreserve()) ? "0" : dtf
						.getIsreserve());
				dtf.setIssum(dtf.getIssum() == null
						|| "".equals(dtf.getIssum()) ? "0" : dtf.getIssum());
				dtf.setIsupdate(dtf.getIsupdate() == null
						|| "".equals(dtf.getIsupdate()) ? "1" : dtf
						.getIsupdate());
				dtf.setIsvirtual(dtf.getIsvirtual() == null
						|| "".equals(dtf.getIsvirtual()) ? "0" : dtf
						.getIsvirtual());
				dtf.setIsvisible(dtf.getIsvisible() == null
						|| "".equals(dtf.getIsvisible()) ? "1" : dtf
						.getIsvisible());
				dtf.setNullable(dtf.getNullable() == null
						|| "".equals(dtf.getNullable()) ? "1" : dtf
						.getNullable());
				dtf.setParentNodeCanCheck(dtf.getParentNodeCanCheck() == null
						|| "".equals(dtf.getParentNodeCanCheck()) ? "0" : dtf
						.getParentNodeCanCheck());
				if (dtf.getDefaultvalue() == null) {
					dtf.setDefaultvalue("");
				}
				try {
					if (superid == null || "".equals(superid)
							|| "0".equals(superid)) {
						Map<String, Object> m = new HashMap<String, Object>();
						m.put("tableid", tableid);
						m.put("levelno", 1);
						Integer max = this.dictTFactorSelfService
								.getMAXColumnOrderid(m);
						dtf.setOrderid(max + 1);
						dtf.setLevelno(1);
					} else {
						DictTFactorPO supperDtf = this.dictTFactorService
								.getDictTFactorByColumnId(superid);
						dtf.setLevelno(supperDtf.getLevelno() + 1);
						Map<String, Object> mm = new HashMap<String, Object>();
						mm.put("tableid", tableid);
						mm.put("superid", superid);
						List<DictTFactorPO> listDictTFactor = this.dictTFactorMapper
								.findDictTFactor(mm);
						int j = 1;
						for (DictTFactorPO d_ : listDictTFactor) {
							if (d_.getOrderid() > j) {
								j = d_.getOrderid();
							}
						}
						dtf.setOrderid(j + 1);
					}

					this.dictTFactorSelfService.insertDictTFactorForTitle(dtf);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					str.put("error", e.getMessage().replace("\"", "\'"));
				}
			} else {
				str.put("error", "系统出现异常，未找到<标题值>");
			}
		} else {
			str.put("error", "系统出现异常，未找到<tableid>");
		}
		if (str.get("error") == null) {
			str.put("success", "保存成功");
		}
		return str;
	}

	/**
	 * 删除标题列
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteDictTFactorForTitle")
	@ResponseBody
	public Object deleteDictTFactorForTitle(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map str = new HashMap();
		String columnid = request.getParameter("columnid");
		if (columnid != null && !"".equals(columnid)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("superid", columnid);
			List<DictTFactorPO> dtfs = this.dictTFactorMapper
					.findDictTFactor(map);
			if (dtfs == null || dtfs.size() == 0) {
				this.dictTFactorSelfService.deleteDictTFactorForTitle(columnid);
			} else {
				str.put("error", "该标题下含有子元素，请先删除子元素");
			}

		} else {
			str.put("error", "系统出现异常，未找到<columnid ='" + columnid + "'>的标题列");
		}
		if (str.get("error") == null) {
			str.put("success", "删除成功");
		}
		return str;
	}

	/**
	 * 加载右侧表头 DictTTabextprop
	 * 
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value = "getDictTColextpropListDataHead")
	@ResponseBody
	public Object getDictTColextpropListDataHead(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String gridHead = "";
		Grid grid = dictTTabextpropService
				.getDictTTabextpropHead("myColExtprop");
		gridHead = (new ObjectMapper()).writeValueAsString(grid);
		return gridHead;
	}

	/**
	 * 加载右侧数据 DictTTabextprop
	 * 
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value = "getDictTColextpropListData")
	@ResponseBody
	public Object getDictTColextpropListData(String grid,
			HttpServletResponse response) throws Exception {
		Grid table = (Grid) (new ObjectMapper()).readValue(grid, Grid.class);
		Map<String, Object> map = table.getExtProperties();
		String tableid = (String) map.get("tableid");
		DictTModelPO dtm = this.dictTModelService.getDictTModelByID(tableid,
				false);
		Integer adapterType = Integer.parseInt(map.get("adapterType")
				.toString());
		List<DictTColextpropPO> dtcList = null;
		// DictTTabextprop
		if (dtm.getAppid() != null && !"".equals(dtm.getAppid())) {
			dtcList = dictTColextpropService.getAllDictTColextpropByAppid(dtm
					.getAppid());
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (DictTColextpropPO dictTColextpropPO : dtcList) {
			list.add(ConvertUtil.toMap(dictTColextpropPO));
		}
		setGridData(list, table.getPageInfo());
		return table.getPageInfo();
	}

	/**
	 * 加载 超链接 参数 表头
	 * 
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value = "getDictTHrefParmListDataHead")
	@ResponseBody
	public Object getDictTHrefParmListDataHead(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String hrefparmid = request.getParameter("hrefparmid");
		String gridHead = "";
		Grid grid = dictTSetHrefParmService
				.getDictTSetHrefParmHead("DictTHrefParm");
		gridHead = (new ObjectMapper()).writeValueAsString(grid);
		return gridHead;
	}

	/**
	 * 加载 超链接 参数 数据
	 * 
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value = "getDictTHrefParmListData")
	@ResponseBody
	public Object getDictTHrefParmListData(String grid,
			HttpServletResponse response) throws Exception {
		Grid table = (Grid) (new ObjectMapper()).readValue(grid, Grid.class);
		Map<String, Object> map = table.getExtProperties();
		String hrefParmID = (String) map.get("hrefparmid");
		Integer adapterType = Integer.parseInt(map.get("adapterType")
				.toString());
		List<DictTSetHrefParmPO> dtshfList = new ArrayList<DictTSetHrefParmPO>();
		if (hrefParmID != null && !"".equals(hrefParmID)) {
			dtshfList = this.dictTSetHrefParmService
					.findDictTSetHrefParmByHrefParmID(hrefParmID);
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (DictTSetHrefParmPO dictTSetHrefParmPO : dtshfList) {
			list.add(ConvertUtil.toMap(dictTSetHrefParmPO));
		}
		setGridData(list, table.getPageInfo());
		return table.getPageInfo();
	}

	/**
	 * 获取下拉列表的树 例如：字段的显示类型 列类型
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getComoTree")
	@ResponseBody
	public List<TreeNode> getComoTree(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String dbColumnName = request.getParameter("dbColumnName");// 考虑不区分大小写
		String datatype = request.getParameter("datatype");
		List<TreeNode> list = new ArrayList<TreeNode>();
		if (dbColumnName.equals("showformat")) {
			for (Entry<String, String> m : DictDBConstants.showformat
					.entrySet()) {
				TreeNode treeNode = new TreeNode();
				treeNode.setName(m.getValue());
				treeNode.setId(m.getKey());
				list.add(treeNode);
			}
		}
		if (dbColumnName.equals("datatype")) {
			for (Entry<Integer, String> m : DictDBConstants.dataType_ZH
					.entrySet()) {
				TreeNode treeNode = new TreeNode();
				treeNode.setName(m.getValue());
				treeNode.setId(m.getKey() + "");
				list.add(treeNode);
			}
		}
		return list;
	}

	/**
	 * 获取列管理页面的引用表下拉树
	 * 
	 * @author
	 * 
	 */
	@RequestMapping(value = "getCsidTreeNodes")
	@ResponseBody
	public Object getCsidTreeNodes(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String appid = request.getParameter("appid");
		// appid = "BGT";
		// 数据元 ------平台接口
		// SortList<DictTModelcodePO> sortList = new
		// SortList<DictTModelcodePO>();
		List<TreeNode> list = new ArrayList<TreeNode>();
		String csid = "";
		// 引用代码表
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("appid", appid);
		List<DictTModelcodePO> listDictTModelcodePO = dictTModelcodeService
				.findDictTModelcodeByArgs(paramMap);
		for (DictTModelcodePO d : listDictTModelcodePO) {
			d.setDynamicwhere("");
		}
		if (ConverTables.isNotNullList(listDictTModelcodePO)) {
			for (DictTModelcodePO dictTModelcodePO : listDictTModelcodePO) {
				TreeNode treeNode = new TreeNode();
				treeNode.setId(dictTModelcodePO.getTableid());
				treeNode.setName(dictTModelcodePO.getName());
				list.add(treeNode);
			}
		}
		return list;
	}

	/**
	 * 获取现有列已调用的所有数据元
	 * @author zzk
	 * @param tableID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getAllDatas")
	public Object getAllDatas(String tableID) {
		return dictTFactorService.getDictTFactorsByTableId(tableID);
	}

	public class inner {
		public String key;
		public String value;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public inner(String key, String value) {
			super();
			this.key = key;
			this.value = value;
		}

	}

}
