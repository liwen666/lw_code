package commons.setting.input.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjhq.commons.cache.service.IDataCacheService;
import com.tjhq.commons.dict.external.po.DictTDefaultcolPO;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.dict.service.IDictTDefaultcolService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.setting.external.service.IEntryOuterService;
import com.tjhq.commons.setting.formula.po.FormulaTFormulaDefPO;
import com.tjhq.commons.setting.formula.service.IFormulaService;
import com.tjhq.commons.setting.formula.service.impl.FormulaService;
import com.tjhq.commons.setting.input.po.DictTSetFddefPO;
import com.tjhq.commons.setting.input.po.DictTSetFixPO;
import com.tjhq.commons.setting.input.po.TreeNode;
import com.tjhq.commons.setting.input.service.IEntryService;
import com.tjhq.commons.setting.input.service.IFloatService;
import com.tjhq.commons.setting.input.service.IGeneralService;
/**
* 类名称：OpenSetController  
* 创建人：shishj
* 创建时间：Jan 26, 2014 2:09:04 AM
* @version 1.0
*/
@Controller
@RequestMapping(value = "/commons/setting/input/openset")
public class OpenSetController {
	@Resource
	private IEntryService entryService;
	@Resource
	private IDictTFactorService dictTFactorService;
	@Resource
	private IFormulaService formulaService;
	@Resource
	private IFloatService floatService;
	@Resource
	private IDictTDefaultcolService dictTDefaultcolService;
	@Resource
	private FormulaService formulaMethod;
	@Resource 
	private IDataCacheService dataCacheService;
	@Resource
	private IGeneralService generalService;

	/**
	 * 引用列关系定义
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "refrelaData")
	public String getRefrelaData(HttpServletRequest request) throws Exception {
		String tableID = request.getParameter("tableID");
		String condColumnID = request.getParameter("condColumnID");
		String columnID = request.getParameter("columnID");
		String relaDbTab = request.getParameter("relaDbTab");
		String relaID = request.getParameter("relaID");

		try {
			//关联列tree | 被关联列tree
//			List<TreeNode> leftTree = entryService.getRefrelaDbTableTree(leftTable); 
//			List<TreeNode> rightTree = entryService.getRefrelaDbTableTree(rightTable);
			List<TreeNode> leftTree = entryService.getRefrelaTree(tableID, condColumnID);
			List<TreeNode> rightTree = entryService.getRefrelaTree(tableID, columnID);
			request.setAttribute("leftTree", (new ObjectMapper()).writeValueAsString(leftTree));
			request.setAttribute("rightTree", (new ObjectMapper()).writeValueAsString(rightTree));
		} catch(ServiceException e) {
			request.setAttribute("errorMessage", e.getMessage());
		}
		request.setAttribute("tableID", tableID);
		request.setAttribute("relaDbTab", relaDbTab);
		request.setAttribute("relaID", relaID);

		String leftTable = entryService.getRefrelaDbTableName(tableID,condColumnID); //左侧表
		String rightTable = entryService.getRefrelaDbTableName(tableID,columnID); //右侧表
		request.setAttribute("columnID", columnID);
		request.setAttribute("leftTable", leftTable);
		request.setAttribute("rightTable",rightTable);
		return "/commons/setting/input/refrelaData";
	}

	/**
	 * 获取已设置的关系树
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "getRelaTree")
	public Object getRelaTree(HttpServletRequest request) throws Exception {
		String tableID = request.getParameter("tableID");
		String relaDbTab = request.getParameter("relaDbTab");
		String columnID = request.getParameter("columnID");
		String condDataID = request.getParameter("condDataID");
		String rTable = request.getParameter("rightTable");
		List<TreeNode> nodes = null;
		try {
			nodes = entryService.getRefrelaTree(tableID, relaDbTab, columnID, condDataID, rTable);
		} catch (ServiceException e) {
            e.printStackTrace();
		}
		return nodes;
	}

	/**
	 * 列关系的保存方法（tree）
	 * @author ZK
	 * @date 16-11-30
	 * @param tableID
	 * @param refrelas
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveRefrela")
	public Object saveRefrela(@RequestParam(value = "tableID") String tableID,
			@RequestParam(value = "condDataID") String condDataID,
			@RequestParam(value = "refrelas") String refrelas,
			@RequestParam(value = "relaID") String relaID) throws Exception {
		ResultPO rstPo = null;
		try {
			List<Map<String, Object>> relas
				= (List<Map<String, Object>>) (new ObjectMapper()).readValue(refrelas, List.class);
			for(Map<String, Object> r : relas) {
				r.put("dataID", r.get("id"));
			}
			generalService.saveRefrela(tableID, relaID, condDataID, relas);
			rstPo = new ResultPO("保存成功!");
			rstPo.setSuccessFlag(true);

			if(tableID != null) {
				String [] keys = {"HQ.COMM", IEntryOuterService.REFRELA, tableID};
				Object commInputCatch = dataCacheService.get(keys);
				if(commInputCatch != null) {
					dataCacheService.put(keys, null);
				}
			}
		} catch (Exception e) {
			String msg = e.getMessage();
			msg = (msg == null || msg.equals("")) ? "保存失败!" : msg;
			rstPo = new ResultPO(e.getLocalizedMessage(), msg);
		}
		return rstPo;
	}

	/**
	 * 获取引用代码表数据
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getCodeTabData")
	@ResponseBody
	public Object getCodeTabData(@RequestParam(value = "dbTableName") String dbTableName)throws Exception {
		List<TreeNode> treeData = treeData = entryService.getRefrelaDbTableTree(dbTableName);
		return treeData;
	}

	/**
	 * 创建引用关系表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createRelaTab")
	@ResponseBody
	public Object createRelaTab(HttpServletRequest request) throws Exception {
		String relaDbTab = request.getParameter("relaDbTab");
		String is_success = "{\"flag\":\"true\"}";
		try{
			String is_exits = entryService.createRelaTab(relaDbTab);
			//表已经 存在 
			if(Integer.valueOf(is_exits) > 0) is_success = "{\"flag\":\"false\"}";
			
		}catch(Exception e){
			e.printStackTrace();
			is_success = "{\"flag\":\"error\"}";
		}
		
		return is_success;
	}
	
	
	/**
	 * 查询分析（引用）定义
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "hrefParm")
	public String getHrefParm(HttpServletRequest request) throws Exception {
		String hrefParmID = request.getParameter("hrefParmID");
		String tableID = request.getParameter("tableID");

		request.setAttribute("hrefParmID", hrefParmID);
		request.setAttribute("tableID", tableID);

		return "/commons/setting/input/hrefParam";
	}
	
	/**
	 * 1、使用查询面板 引用 queryView.JS
	 * 2、调用查询面板 接口
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "outQueryd")
	public String outQueryd(HttpServletRequest request) throws Exception {
		String tableID = request.getParameter("tableID");
		request.setAttribute("tableID", tableID);
		
		return "/commons/setting/input/outQueryd";
	}
	
	
	/**
	 * 浮动表整表设置 页面
	 * @request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getFloatWhole")
	public String getFloatWhole(HttpServletRequest request) throws Exception {
		String tableID = request.getParameter("tableID");
		String dealType = request.getParameter("dealType");

		//根据TableID 查询浮动表设置信息
		DictTSetFddefPO fddef = entryService.getDataFddefList(tableID);

		if(null != fddef){
			DictTFactorPO po = dictTFactorService.getDictTFactorByColumnId(fddef.getLvlNanmeCol());
			if(po != null) fddef.setLvlNanmeColName(po.getName());//中文名称列
			request.setAttribute("fddefPo",(new ObjectMapper()).writeValueAsString(fddef)); 
		}
		
		request.setAttribute("tableID", tableID);
		request.setAttribute("dealType", dealType);
		return "/commons/setting/input/floatWhole";
	}
	
	/**
	 * 固定行列表整表设置 页面
	 * @request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getFixedWhole")
	public String getFixedWhole(HttpServletRequest request) throws Exception {
		String tableID = request.getParameter("tableID");
		String dealType = request.getParameter("dealType");

		//根据TableID 查询浮动表设置信息
		List<DictTFactorPO> factor = dictTFactorService.getDictTFactorsByTableId(tableID);
		Pattern pattern = Pattern.compile("FDCODE",Pattern.CASE_INSENSITIVE); //显示列
		int len = 0;
		for(DictTFactorPO col : factor){
			if(col.getDatatype()==4){//中间级
				continue;
			}
			String columnName = col.getDbcolumnname();
			if(pattern.matcher(columnName).find()) len += 1;
		}

		request.setAttribute("fdCode_len",len); 
		request.setAttribute("tableID", tableID);
		request.setAttribute("dealType", dealType);
		request.setAttribute("factor", (new ObjectMapper()).writeValueAsString(this.getFdCodeToCols(tableID, dealType)));
		return "/commons/setting/input/fixedWhole";
	}
	
	/**
	 * 浮动表 单元格权限 公式定义  页面
	 * 2014-6-19
	 * FORMULA_T_DEF 中 FORWHERE 由 FDCODE/ORDERID 修改为 TEMPLATEID 关联
	 * @param request
	 * @return
	 */
	@RequestMapping(value="cellFormula")
	public String cellFormula(HttpServletRequest request) throws Exception {	
		String tableID = request.getParameter("tableID");//表
		String forWhere = request.getParameter("forWhere");// TEMPLATEID
		String forComcol = request.getParameter("forComcol");// 计算列
		String columnId = request.getParameter("columnId");
		String dataKey = request.getParameter("dataKey"); //行权限
		String dealType = request.getParameter("dealType");// A1、固定行列表 A2、 浮动表
		
		String cellSecu = this.getFloatDataByKey(tableID,dataKey);
		//获取详细信息 --- 更新表
		FormulaTFormulaDefPO formula =formulaService.selectCellFormula(tableID, forComcol, forWhere);
		if(null != formula){
			String formulaDefChi = formula.getFormulaDefEng();
			
			formulaDefChi = formulaMethod.dealRowTable(formulaDefChi, tableID,dealType, "show");
			formula.setFormulaDefChi(formulaMethod.replaceString(formulaDefChi));
			request.setAttribute("formulaPo",(new ObjectMapper()).writeValueAsString(formula)); 
		}else{
			//获取公式 最大序号
			int orderId = formulaService.formulaOrderID(tableID, "1");
			request.setAttribute("orderID", String.valueOf(orderId));
		}
		Map<String,Object> cSecu = this.getCellSecu(tableID, columnId, cellSecu ,dealType);
		//列中文名
		DictTFactorPO po = dictTFactorService.getDictTFactorByColumnId(columnId);
		request.setAttribute("name", po != null ? po.getName() : forComcol);
		
		request.setAttribute("cellSecu", cellSecu);//
		request.setAttribute("cSecu", cSecu.get("cSecu")); //单元格权限
		request.setAttribute("cIndex", cSecu.get("cIndex"));//位置
		if(dealType.equals("A2")) request.setAttribute("tempType", "FDCODE");
		if(dealType.equals("A1")) request.setAttribute("tempType", "ORDERID");

		return "/commons/setting/input/cellFormula";
	}
	
	/**
	 * 获取 单元格 权限
	 * @param tableID  表ID
	 * @param columnId 列ID
	 * @param cellSecu 单元格权限
	 * @param dealType 处理类型
	 * @return
	 */
	public Map<String,Object> getCellSecu(String tableID, String columnId, String cellSecu,String dealType) {
		String cSecu = "";
		String colOrder = "" ;
		int index = 0;
		Map<String,Object> map = new HashMap<String,Object>();
		if(dealType.equals("A2")) {
			DictTSetFddefPO po = entryService.getDataFddefList(tableID);
			if(po != null) colOrder = po.getColOrder(); // 排序列
		}
		if(dealType.equals("A1")) {
			List<DictTSetFixPO> fixed = entryService.getDataFixList(tableID);
			if(ConverTables.isNotNullList(fixed)){
				for(DictTSetFixPO po : fixed){					
					if(!ConverTables.isNotNull(colOrder)) colOrder = po.getColOrder();		
				}
			}
		}
		if (ConverTables.isNotNull(colOrder)) {
			String col[] = colOrder.split(",");
			for (int i = 0; i < col.length; i++) { if (col[i].equals(columnId)) index = i; }
			// 截取 0 1
			if (ConverTables.isNotNull(cellSecu))
				cSecu = String.valueOf(cellSecu.charAt(index));
		}
		map.put("cSecu", cSecu);
		map.put("cIndex", String.valueOf(index));
		return map;
	}
	
	/**
	 * 根据DATAKEY 查询浮动CELLSECU
	 * @param tableID
	 * @param dataKey
	 * @return
	 * @throws Exception
	 */
	public String getFloatDataByKey(String tableID,String dataKey) throws Exception{
		String cellSecu = "";
		String whereCondition = ConverTables.float_where + " AND DATAKEY = '" + dataKey + "'";	
		List<Map<String, Object>> floatData = floatService.getFloatData(tableID, whereCondition);
		// 获取 单元格权限
		if(ConverTables.isNotNullList(floatData)){
			Map<String,Object> map = (Map<String, Object>)floatData.get(0);
			cellSecu = (String)map.get("CELLSECU");
		}
		return cellSecu;
	}
	

	/**
	 * 获取DICT_T_FACTOR 表中 列
	 * 条件 ： 在factor表中isVisible 为 1, 并且不包含缺省列。
	 * @param tableID
	 * @param dealType
	 * @return
	 */
	public List<DictTFactorPO> getFdCodeToCols(String tableID,String dealType) throws Exception{
		List<DictTFactorPO> factor = dictTFactorService.getDictTFactorByTableidAndIsvisible(tableID, "1");

		//获取缺省列
		List<DictTDefaultcolPO> list = dictTDefaultcolService.getDictTDefaultcols(dealType);
		List<DictTFactorPO> remove = new ArrayList<DictTFactorPO>();
		
		for(DictTFactorPO po:factor){
			for(DictTDefaultcolPO col:list){
				if(ConverTables.isNotNull(po.getDbcolumnname())){
					if(po.getDbcolumnname().equalsIgnoreCase(col.getDbcolumnname())){			
						remove.add(po);
					}	
				}	
			}	
		}
		//移除缺省列
		factor.removeAll(remove);	
		return factor;
	}

}
