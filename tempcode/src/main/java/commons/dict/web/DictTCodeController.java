package commons.dict.web;


import java.io.IOException;
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


import com.fasterxml.jackson.databind.JsonMappingException;
import com.tjhq.commons.dict.dao.DictTAppregisterMapper;
import com.tjhq.commons.dict.external.dao.DictTModelcodeMapper;
import com.tjhq.commons.dict.external.po.DictTAppregisterPO;
import com.tjhq.commons.dict.external.po.DictTDealtypePO;
import com.tjhq.commons.dict.external.po.DictTDefaultcolPO;
import com.tjhq.commons.dict.external.po.DictTFactorcodePO;
import com.tjhq.commons.dict.external.po.DictTModelcodePO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.dict.external.service.impl.DictTFactorcodeService;
import com.tjhq.commons.dict.external.service.impl.DictTModelcodeService;
import com.tjhq.commons.dict.service.IDictDBExecuteService;
import com.tjhq.commons.dict.util.DictDBConstants;
import com.tjhq.commons.dict.util.SortList;
import com.tjhq.commons.inputcomponent.constants.RowState;
import com.tjhq.commons.inputcomponent.grid.basegrid.service.impl.BaseGridService;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.jackson.ObjectMapper;
import com.tjhq.commons.setting.external.po.TreeNode;
import com.tjhq.commons.utils.ConvertUtil;
/**
 * 
 * @author 
 *
 */

@Controller
@RequestMapping(value = "/commons/dict/dicttcode")
public class DictTCodeController extends  BaseGridService {
	@Resource
	private IDictTFactorService dictTFactorService;
	@Resource
	private DictTAppregisterMapper dictTAppregisterMapper;
	@Resource
	private DictTModelcodeService dictTModelcodeService;
	@Resource
	private DictTModelcodeMapper dictTModelcodeMapper;
	@Resource
	private DictTFactorcodeService dictTFactorcodeService;
	@Resource
	private IDictDBExecuteService dictDBExecuteService;

	private static String returnStr = "commons/dict/code/dict";

	/**
	 * 指定页面   dictModelCodeAndFactorCode.jsp
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws Exception 
	 */
	@RequestMapping(value="")
	public String page(HttpServletRequest request,HttpServletResponse response)
			throws Exception, JsonMappingException, IOException {
		String appid = request.getParameter("appid");
		String tableid =request.getParameter("tableid");
		String tabletype =request.getParameter("tabletype");
		String flag = request.getParameter("flag");
		String page = ""; 
		if(appid!=null&&!"".equals(appid)){
			if(flag!=null&&"0".equals(flag)){//平台 登记
				 request.setAttribute("appid", appid);
				 DictTAppregisterPO dictTAppregister = dictTAppregisterMapper.getDictTAppregisterByAppid(appid);
				 request.setAttribute("dictTAppregister", dictTAppregister);
				 page = "ModelCodeAndFactorCodeInsert";
			}else if("1".equals(flag)){//自定义 登记
				 request.setAttribute("appid", appid);
				 DictTAppregisterPO dictTAppregister = dictTAppregisterMapper.getDictTAppregisterByAppid(appid);
				 request.setAttribute("dictTAppregister", dictTAppregister);
				 page = "ModelCodeAndFactorCodeInsertForCustom";
			}else if("2".equals(flag)){//新增代码表 ( 复用update的代码，tableid为空)
				DictTModelcodePO dtmc = new DictTModelcodePO();
				dtmc.setDbtablename("CODE_T_");
				dtmc.setAppid(appid);
				request.setAttribute("dtmc", dtmc);
				request.setAttribute("flag", flag);
				page = "ModelCodeForUpdate";
			}else if("3".equals(flag)){//添加代码表数据
				page = "ModelCodeForAddData";
			}else{//列管理
				// 数据类型默认长度设置
				String lengthSet = (new ObjectMapper()).writeValueAsString(dictTFactorService.getAllDefaultDataLength());
				request.setAttribute("lengthSet", lengthSet);
				request.setAttribute("editMode", dictTFactorService.getEditMode());
				request.setAttribute("appid", appid);
				request.setAttribute("tableid", tableid);
                request.setAttribute("tabletype", tabletype);
				DictTModelcodePO dtm = this.dictTModelcodeService.getDictTModelcodePOByID(tableid);
				dtm.setTabletype(tabletype);
				request.setAttribute("dtm", dtm);
				page = "ModelCodeAndFactorCodeEdit";
			}
		}else if(tableid!=null&&!"".equals(tableid)){
			DictTModelcodePO dtmc = this.dictTModelcodeService.getDictTModelcodePOByID(tableid);
			request.setAttribute("dtmc", dtmc);
			page = "ModelCodeForUpdate";
		}else{
			List<DictTAppregisterPO> listDictTAppregister = dictTAppregisterMapper.getAllDictTAppregister();
			String apps = "";
			try {
				apps = (new ObjectMapper()).writeValueAsString(listDictTAppregister);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("apps", apps);
			page = "ModelCodeAndFactorCode";
		}
		return returnStr +page;
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
			HttpServletResponse response,String tableId) throws Exception {
		String gridHead = "";
		Grid grid = dictTModelcodeService
				.getDictTDefaultcolHead(tableId, "");
		gridHead = (new ObjectMapper()).writeValueAsString(grid);
		return gridHead;
	}
	
	/**
	 * 加载右侧数据
	 * 
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value = "getDefaultcolListData")
	@ResponseBody
	public Object getDefaultcolListData(HttpServletRequest request, String grid,String tablename)
			throws Exception {
		Grid table = (Grid) (new ObjectMapper()).readValue(grid, Grid.class);
		Map<String, Object> map = table.getExtProperties();
		List<DictTModelcodePO> dtdList = null;
		// 根据左侧树 节点id suitId
		try {
			if (tablename != null && !"".equals(tablename)) {
				dtdList = dictTModelcodeService
						.getDictTDefaultcols4Show(tablename);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (dtdList != null) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for (DictTModelcodePO po : dtdList) {
				Map poMap = BeanUtils.describe(po);
				poMap.put(RowState.COLUMN, RowState.NORMAL);
				list.add(poMap);
			}
			setGridData(list, table.getPageInfo());
			return table.getPageInfo();
		}
		return null;
	}
	
	
	/**
	 * 添加代码表数据____加载左侧树 ztree <DictTSuitPO>
	 * 
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value = "getDealTypeTrees")
	@ResponseBody
	public Object getAddDataTrees(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<TreeNode> listTree = new ArrayList<TreeNode>();
		try {
			listTree = dictTModelcodeService.findDictTModelcodeListForZTree();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listTree;
	}

	/**
	 * 获取现有列已调用的所有数据元
	 * @author wzq
	 * @param tableID
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "getAllDatascode")
	public Object getAllDatas(String tableID) throws Exception {
		return dictTFactorcodeService.getDictTFactorcodePOsByTableId(tableID);
	}
	
	/**
	 * 
	 * @Title: 
	 * @Description: 获取视图中没有被使用的列明
	 * @param  设定文件
	 * @return  返回类型
	 * @throws
	 */
	@RequestMapping(value = "getFactorcodeFromView")
	@ResponseBody
	public Object getFactorcodeFromView(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String tableid = request.getParameter("tableid");
		String str = "";
		DictTModelcodePO dtm = this.dictTModelcodeService.getDictTModelcodePOByID(tableid);
//		/**
//		 * dblist为登记的所有列名
//		 */
//		List<Map<String, Object>> dblist = this.dictDBExecuteService.getColumnByViewName(dtm.getDbtablename());
		
		List<DictTFactorcodePO>  dblist = this.dictTModelcodeService.getElementCode2DictFactorCode(dtm.getDbtablename());
		
		/**
		 * listFactorcode为实际视图的所有列
		 */
		List<DictTFactorcodePO> listFactorcode = this.dictTFactorcodeService.getDictTFactorcodePOsByTableId(tableid);
		Map<String, String> map = new HashMap<String, String>();
		for (DictTFactorcodePO dtf : listFactorcode) {
			// 标题列跳过
			if (dtf.getDatatype() == 4) {
				continue;
			}
			map.put(dtf.getDbcolumnname().toUpperCase(), "yes");
		}
		if(dblist!=null && dblist.size()>0){
			for (int i = 0; i < dblist.size(); i++) {
				DictTFactorcodePO factorcode = dblist.get(i);
				String dbColumnName = factorcode.getDbcolumnname();
				if (map.get(dbColumnName) != null) {
					dblist.remove(i);
					i--;
				}
			}
		}
		return dblist;
	}
	/**
	 * 删除数据 
	 * @return json 
	 * @throws Exception
	 */
	@RequestMapping(value="saveTableData")
	@ResponseBody
	public Object saveTableData(String grid,HttpServletRequest request ) throws Exception{
		Map<String,String> remap = new HashMap<String, String>();
		Grid table =(Grid) (new ObjectMapper()).readValue(grid, Grid.class);
		//获取删除数据
		String str = "";
		List<Map<String,Object>> deleteList = table.getDeleteValues();
		if(deleteList!=null&&deleteList.size()>0){		//删除数据
			for(Map<String, Object> map :deleteList){
				String tableid = map.get("tableid").toString();
				DictTModelcodePO dtmc = dictTModelcodeService.getDictTModelcodePOByID(tableid);
				if(dtmc!=null){
					try {
						this.dictTModelcodeService.deleteDictTModelcode(tableid);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						str=str+e.getMessage();
					}
					
				}else{
					str=str+"选择对象为空，删除失败.\n";
				}
			}
	    }
		if("".equals(str)){
			str = "删除成功.";
		}
		remap.put("key", str.replace("\n", ""));
		return remap;
	}
	
/**
	 * 修改列
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updateDictTFactorcode")
	@ResponseBody
	public Object updateDictTFactor(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "json") String poStr) throws Exception {
		Map str = new HashMap();
		DictTFactorcodePO dtf = (DictTFactorcodePO) (new ObjectMapper()).readValue(
				poStr, DictTFactorcodePO.class);
		DictTFactorcodePO oldDtf = this.dictTFactorcodeService.getDictTFactorcodePOByColumnId(dtf.getColumnid());
		if (dtf != null) {
			oldDtf.setDeid(dtf.getDeid());
			oldDtf.setName(dtf.getName());
			oldDtf.setDatatype(dtf.getDatatype());
			oldDtf.setScale(dtf.getScale());   //po.getDefaultvalue()==null?"0":po.getDefaultvalue()
			oldDtf.setDbcolumnname(dtf.getDbcolumnname());
			oldDtf.setDatalength(dtf.getDatalength());
			oldDtf.setDefaultvalue(dtf.getDefaultvalue());
			oldDtf.setIsreserve(dtf.getIsreserve()==null?"0":dtf.getIsreserve());
			oldDtf.setIsvisible(dtf.getIsvisible()==null?"0":dtf.getIsvisible());
			try {
				this.dictTFactorcodeService.updateDictTFactorcode(oldDtf);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				str.put("error", e1.getMessage().replace("\"", "\'"));
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
	 * 创建列
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveDictTFactorcode")
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
		DictTFactorcodePO dtf = (DictTFactorcodePO) (new ObjectMapper()).readValue(
				poStr, DictTFactorcodePO.class);
		String columnId = dictDBExecuteService.getUUID();
       dtf.setColumnid(columnId);
       dtf.setBgtlvl("1");
		if (dtf != null) {
//			String tableId = dtf.getTableid();
//			DictTFactorcodePO dtm = (DictTFactorcodePO) this.dictTFactorcodeService.getDictTFactorcodePOsByTableId(tableId);

			try {
				dictTFactorcodeService.insertDictTFactorcode(dtf);
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
	 * 加载左侧树 ztree <DictTSuitPO>
	 * 
	 * @return json
	 * @throws Exception
	 */
	@RequestMapping(value = "getFactorcodeTrees")
	@ResponseBody
	public Object getFactorcodeTrees(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String tableid = request.getParameter("tableid");
		Map<String, String> remap = new HashMap<String, String>();
		List<DictTFactorcodePO> dtfList = null;
		if (tableid != null && !"".equals(tableid)) {
			dtfList = dictTFactorcodeService.getDictTFactorcodePOsByTableId(tableid);
			for (DictTFactorcodePO dtf : dtfList) {
				dtf.setOpen(true);
			}
		}
		remap.put("key", (new ObjectMapper()).writeValueAsString(dtfList));
		return remap;
	}
	
	
	
	/**
	 * 查询列表 表头  
	 * 
	 */
	@RequestMapping(value="getDictTModelcodeListDataHead")
	@ResponseBody
	public Object getDictTModelcodeListDataHead(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String gridHead = "";
		Grid grid = dictTModelcodeService.getDictTModelCodeHead("myTable");
		gridHead = (new ObjectMapper()).writeValueAsString(grid);
		return gridHead;
	}

	/**
	 * 查询列表 数据  
	 * 
	 */
	@RequestMapping(value="getDictTModelcodeListData")
	@ResponseBody
	public Object getDictTModelcodeListData(HttpServletRequest request,String grid) throws Exception{
		Grid table = (Grid)(new ObjectMapper()).readValue(grid, Grid.class);
		Map<String, Object> map1=table.getExtProperties();
		String appid = (String) map1.get("appid");
		String col = (String) map1.get("col");
		String updown = (String) map1.get("updown");
		String name_like = (String) map1.get("name_like");
		String dbtablename_like = (String) map1.get("dbtablename_like");
		if(name_like!=null && !"".equals(name_like)){
			map1.put("name_like", "%"+name_like.trim()+"%");
		}
		if(dbtablename_like!=null && !"".equals(dbtablename_like)){
			map1.put("dbtablename_like", "%"+dbtablename_like.trim()+"%");
		}
	/*	String adaptertype = (String) map1.get("adapterType");
		Integer adapterType = Integer.parseInt(adaptertype);*/
		List<DictTModelcodePO> dtmCodeList = null;
//		Map<String, Object> map = new HashMap<String, Object>();
		if(appid!=null&&!"".equals(appid)){
			dtmCodeList = dictTModelcodeService.findDictTModelcodeByArgs(map1);
			if(col!=null&&!"".equals(col)){
				SortList<DictTModelcodePO> sortList = new SortList<DictTModelcodePO>();
				sortList.Sort(dtmCodeList,col,updown);
			}
		}
		if(dtmCodeList!=null){
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			for(DictTModelcodePO po:dtmCodeList){
				list.add(ConvertUtil.toMap(po));
			}
			setGridData(list, table.getPageInfo());		
			return table.getPageInfo();
		}
		return null;
	}
	
	@RequestMapping(value="getSortCols")
	@ResponseBody
	public Object getSortCols(HttpServletRequest request) throws Exception{
//		<option value="getName">中文名称</option>
//	    <option value="getDbtablename">物理名称</option>
//	    <option value="getIsrepbase">保留</option>
//	    <option value="getIslvl">层次编码</option>
//	    <option value="getIsorgid">组织机构</option>
		String gridHead="";
		Grid grid= dictTModelcodeService.getDictTModelCodeHead("myTable");
		gridHead=(new ObjectMapper()).writeValueAsString(grid);
		return gridHead;
	}
	
	
	
	/**
	 * 删除数据 
	 * @return json 
	 * @throws Exception
	 */
	@RequestMapping(value="deleteFactorCodeColumn")
	@ResponseBody
	public Object deleteFactorCodeColumn(String grid,HttpServletRequest request ) throws Exception{
		Map<String,String> remap = new HashMap<String, String>();
		Grid table =(Grid) (new ObjectMapper()).readValue(grid, Grid.class);
		//获取删除数据
		String str = "";
		List<Map<String,Object>> deleteList = table.getDeleteValues();
		if(deleteList!=null&&deleteList.size()>0){		//删除数据
			for(Map<String, Object> map :deleteList){
				String columnid = map.get("columnid").toString();
				DictTFactorcodePO dtmc = dictTFactorcodeService.getDictTFactorcodePOByColumnId(columnid);
				if(dtmc!=null){
					try {
						this.dictTFactorcodeService.deleteDictTFactorcode(columnid);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						str=str+e.getMessage();
					}
					
				}else{
					str=str+"选择对象为空，删除失败.\n";
				}
			}
	    }
		if("".equals(str)){
			str = "删除成功.";
		}
		remap.put("key", str.replace("\n", ""));
		return remap;
	}
	
	
	
	
	
	
	
	
	/**
	 * 添加
	 * 加载左侧 代码表树 ztree 
	 * @return json 
	 * @throws Exception
	 */
	@RequestMapping(value="getCodeTrees")
	@ResponseBody
	public Object getCodeTrees(HttpServletRequest request,HttpServletResponse response) throws Exception{
	    String appid = request.getParameter("appid");
		List<DictTModelcodePO>  dicTableDTOList = dictTModelcodeService.getDictModelCodeElementCodeList(appid); 
		return dicTableDTOList;
	}
	
	/**
	 * 保存 代码表 by APPID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="saveCode")
	@ResponseBody
	public Object saveCode(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String appid = request.getParameter("appid");
		String tablecodes = request.getParameter("tablecodes");
		Map<String,String> remap = new HashMap<String, String>();
		remap.put("msg", "登记成功.");
		List<String> selectedTableCodeList = new ArrayList<String>();
		if (tablecodes != null && !tablecodes.trim().equals("")){
			String[] tempTableCodes = tablecodes.split(",");
			for (int i = 0; i < tempTableCodes.length; i++) {
				selectedTableCodeList.add(tempTableCodes[i]);
			}
		}
		try{
			dictTModelcodeService.saveElementCode2DictModelCode(appid, selectedTableCodeList);
		}
		catch (Exception e){
			remap.put("msg", e.getMessage().replace("\"", "\'"));
			return remap;
		}
		return remap;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value="toFasp")
	@ResponseBody
	public Object toFasp(@RequestParam(value = "toFaspIDs") String toFaspIDs) throws Exception {
		List<String> selecteds
			= (List<String>) (new ObjectMapper()).readValue(toFaspIDs, List.class);
		Map<String,String> remap = new HashMap<String, String>();
		List<DictTModelcodePO> toFaspTbs = new ArrayList<DictTModelcodePO>();
		for(String tbID : selecteds) {
			DictTModelcodePO dto = this.dictTModelcodeService.getDictTModelcodePOByID(tbID);
// 平台代码表登记到平台才修改是否平台字段为1  16-03-21
//			dto.setIsfasp("1");
			dto.setIsfasp(dto.getDbtablename().indexOf("FASP_") == 0 ? "1" : "0");
			dictTModelcodeService.updateDictTModelcode(dto);	// 先修改是否平台字段
			toFaspTbs.add(dto);
		}

		try {
			dictTModelcodeService.toFasp(toFaspTbs);
			remap.put("msg", "登记到平台成功!");
		} catch(Exception e) {
			e.printStackTrace();
			remap.put("msg",
				(e.getMessage() == null || e.getMessage().equals(""))
					? "登记到平台出错!" : e.getMessage());
		}
		return remap;
	}

	/**
	 * 自定义代码表 登记
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="saveForCustom")
	@ResponseBody
	public Object saveForCustom(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "json") String poStr) throws Exception{
		Map<String,String> remap = new HashMap<String, String>();
		try {
			DictTModelcodePO dtmc = (DictTModelcodePO)(new ObjectMapper()).readValue(poStr,DictTModelcodePO.class);
			remap.put("msg", "");
			if(!("1".equals(dtmc.getIslvl()))){
				dtmc.setIslvl("0");
			}
			if(!("1".equals(dtmc.getIsorgid()))){
				dtmc.setIsorgid("0");
			}
			if(!("1".equals(dtmc.getIsrepbase()))){
				dtmc.setIsrepbase("0");
			}
			dtmc.setIsfasp("0");
			String appid =  dtmc.getAppid();
			String dbname = dtmc.getDbtablename().toUpperCase().trim();
			dtmc.setDbtablename(dbname);
			String ifExistsViewSql = DictDBConstants.IF_EXISTS_TYPE_VIEW + "'"+dbname+"'";
			Integer r_view = dictDBExecuteService.getIfExistsInDB(ifExistsViewSql);//同名视图
			if(r_view==1){
				Map<String,Object> findmap = new HashMap<String, Object>();
				findmap.put("dbtablename", dbname);
				findmap.put("appid", appid);
				List<DictTModelcodePO> dtmcolds =  dictTModelcodeMapper.findDictTModelcode(findmap);
				if(dtmcolds==null||dtmcolds.size()==0){
					try {
						this.dictTModelcodeService.insertDictTModelForCustomcode(dtmc);

/*						// 注册到平台
						if(dtmc.getIsfasp() != null && dtmc.getIsfasp().equals("1")) {
							List<DictTModelcodePO> list = new ArrayList<DictTModelcodePO>();
							list.add(dtmc);
							dictTModelcodeService.toFasp(list);
						}*/
					} catch (Exception e) {                                            
						e.printStackTrace();
						throw new Exception(e.getMessage());
					}
				}else{
					throw new Exception("已经登记过 '"+dbname+"'视图.不需要再次登记.");
				}
			}else{
				throw new Exception("未找到 '"+dbname+"'视图.");
			}
		
		} catch (Exception e) {
			remap.put("msg", e.getMessage().replace("\"", "\'"));
			e.printStackTrace();
		}
		return remap;
	}

	/**
	 * 修改 代码表 by APPID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="updateCode")
	@ResponseBody
	public Object updateCode(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "json") String poStr) throws Exception{
		Map<String,String> remap = new HashMap<String, String>();
		DictTModelcodePO dtmc = (DictTModelcodePO)(new ObjectMapper()).readValue(poStr,DictTModelcodePO.class);
		remap.put("msg", "");
		if(!("1".equals(dtmc.getIslvl()))){
			dtmc.setIslvl("0");
		}
		if(!("1".equals(dtmc.getIsorgid()))){
			dtmc.setIsorgid("0");
		}
		if(!("1".equals(dtmc.getIsrepbase()))){
			dtmc.setIsrepbase("0");
		}
		if(!("1".equals(dtmc.getIsfasp()))){
			dtmc.setIsfasp("0");
		}
		try {
			String flag = request.getParameter("flag");
			if("2".equalsIgnoreCase(flag)){
				String appid = request.getParameter("appid1");
				dtmc.setAppid(appid);
				this.dictTModelcodeService.insertDictTModelcodeForPhysics(dtmc);
			}else{
				this.dictTModelcodeService.updateDictTModelcode(dtmc);
				// 注册到平台
				if(dtmc.getIsfasp().equals("1")) {
					dtmc = dictTModelcodeService.getDictTModelcodePOByID(dtmc.getTableid());
					List<DictTModelcodePO> list = new ArrayList<DictTModelcodePO>();
					list.add(dtmc);
					dictTModelcodeService.toFasp(list);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			remap.put("msg", e.getMessage().replace("\"", "\'"));
			e.printStackTrace();
		}
		return remap;
	
	}
	
	
	
	
	/******************************************代码表 的列 展示**************************************************************
	 */
	
	/**
	 * 查询列 表头  
	 * 
	 */
	@RequestMapping(value="getDictTFactorcodeListDataHead")
	@ResponseBody
	public Object getDictTFactorcodeListDataHead(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String gridHead="";
		Grid grid= dictTFactorcodeService.getDictTFactorCodeHead("myTable");
		gridHead=(new ObjectMapper()).writeValueAsString(grid);
		return gridHead;
	}
	
	
	/**
	 * 查询列 数据  
	 * 
	 */
	@RequestMapping(value="getDictTFactorcodeListData")
	@ResponseBody
	public Object getDictTFactorcodeListData(HttpServletRequest request,String grid) throws Exception{
		Grid table = (Grid)(new ObjectMapper()).readValue(grid, Grid.class);
		Map<String, Object> map1=table.getExtProperties();
		String tableid = (String) map1.get("tableid");
		String columnid= (String) map1.get("columnid");
		List<DictTFactorcodePO> dtfCodeList = null;
		if(tableid!=null&&!"".equals(tableid)){
			dtfCodeList = dictTFactorcodeService.getDictTFactorcodePOsByTableIdName(tableid,null,columnid);
		} 
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
//		Map<Integer,String> sjlx= DictDBConstants.dataType_ZH;
//		Map<Integer,String> wlsjlx= DictDBConstants.dataType;
		for(DictTFactorcodePO po:dtfCodeList){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("name", po.getName());
			map.put("dbcolumnname", po.getDbcolumnname());
			map.put("editMode", dictTFactorService.getEditMode());
			//物理字段类型
			map.put("datatype",po.getDatatype());   
			map.put("columnid",po.getColumnid());   
			map.put("scale", po.getScale());
			map.put("defaultvalue", po.getDefaultvalue()==null?"0":po.getDefaultvalue());
			map.put("datalength", po.getDatalength());
			map.put("isreserve", po.getIsreserve());
			map.put("isvisible", po.getIsvisible());
			map.put("tableid", po.getTableid());
			map.put("deid", po.getDeid());

			list.add(map);
		}
		setGridData(list, table.getPageInfo());		
		return table.getPageInfo();
	}
	
	/** 
	 *  获取当前代码表是否已注册到平台 
	 * @param tableID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getCodeTableRegisted")
	public boolean getCodeTableRegisted(@RequestParam(value = "tableID") String tableID) {
		return dictTFactorcodeService.getCodeTableRegisted(tableID);
	}

	/** 
	 *  获取当前代码表是否被引用 
	 * @param tableID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getCodeTableExist")
	public boolean getCodeTableExist(@RequestParam(value = "tableID") String tableID) {
		return dictTFactorcodeService.getCodeTableExist(tableID);
	}
	
	/** 
	 *  获取当前where条件是否有效 
	 * @param tableID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="valiWhere")
	public String valiWhere(
			@RequestParam(value = "dbtablename") String dbtablename,
			@RequestParam(value = "dynamicwhere") String dynamicwhere) {
		return this.dictTModelcodeService.valiWhere(dbtablename, dynamicwhere);
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
