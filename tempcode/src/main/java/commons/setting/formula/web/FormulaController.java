package commons.setting.formula.web;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjhq.commons.cache.service.IDataCacheService;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.po.DictTModelcodePO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.dict.external.service.IDictTModelcodeService;
import com.tjhq.commons.dict.external.service.IDictTSuitService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.inputcomponent.grid.propertygrid.po.PropertyGrid;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.jackson.ObjectMapper;
import com.tjhq.commons.setting.formula.po.FormulaTFormulaDefPO;
import com.tjhq.commons.setting.formula.service.IFormulaService;
import com.tjhq.commons.setting.formula.service.IRefreshFormulaService;
import com.tjhq.commons.setting.input.po.TreeNode;
import com.tjhq.commons.setting.input.service.IFloatService;
import com.tjhq.commons.setting.input.web.ConverTables;

/**
 * 类名称：FormulaController 类描述：公式定义 创建人：shishj 创建时间：Feb 20, 2014 6:29:39 AM
 * 
 * @version
 */
@Controller
@RequestMapping(value = "/commons/setting/formula")
public class FormulaController {

	@Resource
	private IFormulaService formulaService;
	@Resource
	private IRefreshFormulaService refreshFormulaService;
	@Resource
	private IDictTFactorService dictTFactorService;
	@Resource
	private IDictTSuitService dictTSuitService;
	@Resource
	private IFloatService floatService;
	@Resource
	private IDictTModelService dictTModelService;
	@Resource
	private IDictTModelcodeService modelCodeService;
	@Resource
	private IDataCacheService dataCacheService;

	@RequestMapping(value = "")
	public String page() {
		return "/commons/setting/formula/formulaTable";
	}
	@RequestMapping(value = "new")
	public String pageNew( HttpServletRequest request) throws Exception {
	    String tableID = request.getParameter("tableID");
		DictTModelPO dictTMedolPO= dictTModelService.getDictTModelByID( tableID,false);
		String  tableName = URLDecoder.decode(dictTMedolPO.getName(),  "UTF-8");// 表名
		request.setAttribute("appID",request.getParameter("appID") );
		request.setAttribute("tableID",tableID );
		request.setAttribute("dealType", dictTMedolPO.getDealtype());
		request.setAttribute("tableName",tableName );
		return "/commons/setting/formula/formulaNew";
	}
	@RequestMapping(value="suitTree")
	@ResponseBody
	public Object getSuitTree(String appID) throws Exception{
		
		if(null==appID || ("").equals(appID)){
			appID = "BGT";
		}
		List<com.tjhq.commons.setting.external.po.TreeNode> treeList =  dictTSuitService.getAllDictTSuitWithModelTree4Formula(appID);

		return treeList;
	}
	
	@RequestMapping(value = "factorTree")
	@ResponseBody
	public Object getFactorTree(String tableID,String isFormulaCol,String defineID) throws Exception {

		List<DictTFactorPO> factor = dictTFactorService
				.getDictTFactorsByTableId(tableID);
		List<Map<String, Object>> dataList = formulaService.getExitData(tableID, defineID);
		if ("1".equals(isFormulaCol)) {//获取公式列数据时要进行特殊处理   不是数字类型的列过滤掉

			Iterator<DictTFactorPO> it = factor.iterator();
			while (it.hasNext()) {// 删除不是数字类型的列
				Integer datatype = it.next().getDatatype();
				if (datatype != 1 && datatype != 2) {
					it.remove();
				}
			}
		}
		List<TreeNode> treeList = new ArrayList<TreeNode>();
		boolean flag = false;
        for (DictTFactorPO po : factor) {
            flag = true;
            if (treeList.size() > 0) {
                for (Map<String, Object> mm : dataList) {
                    if (po.getDbcolumnname().equals(mm.get("FORCOMCOL"))) {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                TreeNode tree = new TreeNode();
                tree.setId(po.getColumnid());
                tree.setName(po.getName());
                tree.setPId(po.getSuperid());
                tree.setIsLeaf(po.getIsleaf() == null ? "1" : po.getIsleaf());
                tree.setLevelNo(po.getLevelno() == null ? 1 : po.getLevelno());
                tree.setDbColumnName(po.getDbcolumnname());
                tree.setColumnName(po.getName());
                tree.setColumnId(po.getColumnid());
                tree.setCsid(po.getCsid());
                treeList.add(tree);
            }
        }
		return treeList;
	}

	/**
	 * 根据CSID 获取 引用代码表数据
	 * 
	 * @param csid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modelCodeTree")
	@ResponseBody
	public Object getModelCodeDataByCsid(
			@RequestParam(value = "csid") String csid) throws Exception {
		DictTModelcodePO po = modelCodeService.getDictTModelcodePOByID(csid);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		if (po != null) {
			String dbTableName = po.getDbtablename();
			String tableName = po.getName();
			dataMap.put("treeNode", formulaService
					.getModelCodeDataByCsid(dbTableName));
			dataMap.put("tableName", tableName);
		}
		return dataMap;
	}

	/**
	 * 获取 公式定义 表头
	 * 
	 * @param tableID
	 * @param DefineID
	 */
	@RequestMapping(value = "getTableHead")
	@ResponseBody
	public Object getTableHead(@RequestParam(value = "defineID") String defineID){
	        ResultPO resPO = null;
	        try {
                resPO = new ResultPO(formulaService.getDefineHead(null, defineID));
            } catch (Exception e) {
                e.printStackTrace();
                resPO = new ResultPO();
            }
		return resPO;
	}

	/**
	 * 获取 公式定义 数据
	 * 
	 * @param tableID
	 * @param DefineID
	 * @param adapterType
	 */
	@RequestMapping(value = "getTableData")
	@ResponseBody
	public Object getTableData(String grid){
		CommonGrid commonGrid = null;
		ResultPO resPO = null;
        try {
            commonGrid = (CommonGrid) (new ObjectMapper()) .readJson(grid, CommonGrid.class);
            resPO = new ResultPO(formulaService.getData(commonGrid));
        } catch (ServiceException e) {
            e.printStackTrace();
            resPO = new ResultPO(e.getCode(),e.getErrorMessage());
        }
          return resPO;
	}

	/**
	 * 跳转到相应的公式定义 页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "toFormulaPage")
	public String toFormulaPage(HttpServletRequest request) throws Exception {
		String forwardPage = "";
		String tableID = request.getParameter("tableID");// 表
		String defineID = request.getParameter("defineID");// 公式类型
		String orderID = request.getParameter("orderID");// 公式序号
		String tableName = URLDecoder.decode(request.getParameter("tableName"),
				"UTF-8");// 表名
		String dealType = request.getParameter("dealType");// 表处理类型

		if (null != defineID && !("").equals(defineID)) {
			if (defineID.equals("0") || defineID.equals("1")) {
				forwardPage = "/commons/setting/formula/oneTable";
			}

			if (defineID.equals("8") || defineID.equals("A8"))
				forwardPage = "/commons/setting/formula/betweenTable";
			if (defineID.equals("A0"))
				forwardPage = "/commons/setting/formula/fetchTable";
		}

		request.setAttribute("defineID", defineID);
		request.setAttribute("tableID", tableID);
		request.setAttribute("orderID", orderID);
		request.setAttribute("tableName", tableName);
		request.setAttribute("dealType", dealType);

		// 根据公式ID 获取详细信息 --- 更新表
		String formulaID = request.getParameter("formulaID");// 表
		if (ConverTables.isNotNull(formulaID)) {
			List<FormulaTFormulaDefPO> list = formulaService.selectFormulaData(
					tableID, formulaID, defineID);
			if (ConverTables.isNotNullList(list)) {
				FormulaTFormulaDefPO formula = list.get(0);
				DictTFactorPO po = dictTFactorService
						.getDictTFactorByDBColumnName(formula.getForComcol(),
								formula.getTableID());
				if (po != null)
					formula.setForComcolName(po.getName()); // 公式列中文名

				if (defineID.equals("1")) { // 单元格公式
					formula = this.dealRowFormula(tableID, formula, dealType);
				}
				request.setAttribute("formulaPo", (new ObjectMapper())
						.writeValueAsString(formula));
			}
		}
		return forwardPage;
	}

	/**
	 * 处理单元格公式
	 * 
	 * @param tableID
	 * @param formula
	 * @return
	 * @throws Exception 
	 */
	private FormulaTFormulaDefPO dealRowFormula(String tableID,FormulaTFormulaDefPO formula, String dealType) throws Exception {
		List<Map<String, Object>> dataList = floatService.getFloatData(tableID,"");//不根据条件查询数据
		if (ConverTables.isNotNullList(dataList)) {
			for (Map<String, Object> m : dataList) {
				if(m.get("TEMPLATEID")!=null){
				if (m.get("TEMPLATEID").equals(formula.getForWhere())) {
					// 区分 浮动表FDCODE | 固定行列表ORDERID
					String tempType = dealType.equals("A2") ? "FDCODE"
							: "ORDERID";
					formula.setForWhere(m.get(tempType).toString());
				}
			}
			}
		}
		return formula;
	}

	/*// 表内列公式
	public StringBuffer oneTableColumn(List<DictTFactorPO> factor) {
		StringBuffer option = new StringBuffer();
		for (int i = 0; i < factor.size(); i++) {
			DictTFactorPO po = factor.get(i);
			if (po.getDatatype() != 4)
				option.append("<option value='" + po.getColumnid() + "'>"
						+ po.getName() + "</option>");
		}
		return option;
	}*/

	/**
	 * 保存 公式定义
	 * 
	 * @param tableID
	 * @param DefineID
	 */
	@RequestMapping(value = "saveFormulaData")
	@ResponseBody
	public Object saveFormulaData(HttpServletRequest request){
		ResultPO resPO = null;
	    String jsonTable = request.getParameter("jsonTable");
		String operator = request.getParameter("operator"); // 操作

		FormulaTFormulaDefPO formula = null;
        try {
            formula = (new ObjectMapper()).readJson(jsonTable, FormulaTFormulaDefPO.class);
        } catch (ServiceException e1) {
            e1.printStackTrace();
            resPO = new ResultPO(e1.getCode(),e1.getErrorMessage());
        }
		try {
		    resPO = new ResultPO(formulaService.saveFormulaData(formula, operator));
		} catch (ServiceException e) {
			e.printStackTrace();
			 resPO = new ResultPO(e.getCode(),e.getErrorMessage());
		}
		 //清空缓存
        String[] forCache = {"HQ.BGT","FORMULA"};
        dataCacheService.put(forCache,null);
        
		 return resPO;
	}

	@RequestMapping(value = "saveTableData")
	@ResponseBody
	public Object saveTableData(String grid) throws Exception {
		PropertyGrid propertyGrid = (PropertyGrid) (new ObjectMapper())
		.readValue(grid, PropertyGrid.class);

		// 获取删除数据
		List<Map<String, Object>> deleteList = propertyGrid.getDeleteValues();
		boolean flag = true;
		if (deleteList.size() > 0) {
			flag = formulaService.deleteFormulaData(deleteList);
		}
		
		 //清空缓存
        String[] forCache = {"HQ.BGT","FORMULA"};
        dataCacheService.put(forCache,null);
        
		return flag;
	}

	@RequestMapping(value = "delTableData")
	@ResponseBody
	public Object delTableData(String grid) throws Exception {
		PropertyGrid propertyGrid = (PropertyGrid) (new ObjectMapper())
				.readValue(grid, PropertyGrid.class);

		// 获取删除数据
		List<Map<String, Object>> deleteList = propertyGrid.getDeleteValues();
		boolean flag = true;
		if (deleteList.size() > 0) {
			for (Map<String, Object> dataMap : deleteList) {
				String tableID = (String) propertyGrid.getExtProperties().get("tableID");
				String defineID = (String) propertyGrid.getExtProperties().get("defineID");
				
				dataMap.put("tableID", tableID);
				dataMap.put("defineID", defineID);
			}
			flag = formulaService.deleteFormulaData(deleteList);
		}
		 //清空缓存
        String[] forCache = {"HQ.BGT","FORMULA"};
        dataCacheService.put(forCache,null);
        
		return flag;
	}
	/**
	 * 刷新公式 将物理列名 解析为 中文列名
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "refreshFormulaData")
	@ResponseBody
	public String refreshFormulaData(HttpServletRequest request)
			throws Exception {
		String tableID = request.getParameter("tableID");
		String formulaID = request.getParameter("formulaID");
		String defineID = request.getParameter("defineID");
		String dealType = request.getParameter("dealType");

		String flag = "{\"flag\":\"false\"}";
		// 刷新公式
		flag = refreshFormulaService.updateFormulaDefChi(tableID, formulaID,
				defineID, dealType);

		 //清空缓存
        String[] forCache = {"HQ.BGT","FORMULA"};
        dataCacheService.put(forCache,null);
        
		return flag;
	}

	/**
	 * 根据公式类型 获取 公式定义 数据
	 * 
	 * @param tableID
	 * @param DefineID
	 */
	@RequestMapping(value = "getFormulaData")
	@ResponseBody
	public Object getFormulaData(
			@RequestParam(value = "tableID") String tableID,
			@RequestParam(value = "defineID") String defineID) throws Exception {

		return formulaService.selectFormulaData(tableID, "", defineID);
	}
	/**
	 * 获取最大orderID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getMaxOrderID")
	@ResponseBody
	public String getMaxOrderID(
			@RequestParam(value = "tableID") String tableID,
			@RequestParam(value = "defineID") String defineID) throws Exception{
		List<FormulaTFormulaDefPO> formulaList= formulaService.selectFormulaData(tableID, "", defineID);
		int maxOrderID = 0;
		for(FormulaTFormulaDefPO po : formulaList){
			int orderID = po.getOrderID();
			if(orderID > maxOrderID)
				maxOrderID = orderID;
		}
		maxOrderID = maxOrderID +1;
		return ""+maxOrderID;
	}
	@RequestMapping(value="appTree")
    @ResponseBody
    public Object getAppTree(){    
        //获取 业务系统
        List<Map<String, Object>> appList = formulaService.getAppList();
        List<TreeNode> treeList = new ArrayList<TreeNode>();
        
        if(ConverTables.isNotNullList(appList)){
            for(int i=0;i<appList.size();i++){
                TreeNode tree = new TreeNode();
                Map<String, Object> obj = appList.get(i); 
                tree.setId((String)obj.get("APPID"));
                tree.setName((String)obj.get("APPNAME"));
                treeList.add(tree);
            }
        }
        return treeList;
    }
}
