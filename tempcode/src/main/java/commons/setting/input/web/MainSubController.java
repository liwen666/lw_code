package commons.setting.input.web;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.jackson.ObjectMapper;
import com.tjhq.commons.setting.external.po.RECPO;
import com.tjhq.commons.setting.input.po.DictTSetMainSubRela;
import com.tjhq.commons.setting.input.po.TreeNode;
import com.tjhq.commons.setting.input.service.IMainRefService;
import com.tjhq.commons.setting.input.service.IMainSubRelaService;

/**
* 类名称：CollectEntryController  
* 类描述：主从关系表设置
* 创建人：zz
* 创建时间：Mar 24, 2014 6:15:41 AM
* @version
 */
@Controller
@RequestMapping(value = "/commons/setting/input/mainSubRela")
public class MainSubController {
	
	@Resource
	public IMainSubRelaService mainSubRelaService;
	@Resource
	private IDictTFactorService dictTFactorService;
	@Resource
	private IMainRefService mainRefService;

	@RequestMapping(value="")
	public String page() {
		return "/commons/setting/input/mainSubRela";
	}
	/**
	 * 获取采集类型树
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="collectTypeTree")
	@ResponseBody
	public Object collectTypeTree(){
		ResultPO resPO = null;
        try {
            resPO  = new ResultPO(mainSubRelaService.getCollectTypeTreeList());
        } catch (ServiceException e) {
            e.printStackTrace();
            resPO = new ResultPO(e.getCode(),e.getErrorMessage());
        }
		
		return resPO;
	}
	
	/**
	 * 获取 采集录入表关系设置 表头
	 * @param tableID
	 * @param DefineID 
	 */
	@RequestMapping(value="getTableHead")
	@ResponseBody
	public Object getTableHead(String collTypeID){
	    ResultPO resPO = null;
			
		try {
            resPO = new ResultPO(mainSubRelaService.getTableHead(collTypeID));
        } catch (ServiceException e) {
            e.printStackTrace();
            resPO = new ResultPO(e.getCode(),e.getErrorMessage());
        }
        return resPO;

	}
	/**
	 * 获取 一般录入表 数据
	 * @param tableID
	 * @param DefineID
	 * @param adapterType
	 */
	@RequestMapping(value="getTableData")
	@ResponseBody
    public Object getTableData(String grid) {
        ResultPO resPO = null;
        try {
            CommonGrid commonGrid = (CommonGrid) (new ObjectMapper()).readJson(grid, CommonGrid.class);
            String collTypeID = (String) commonGrid.getExtProperties().get("collTypeID");
            resPO = new ResultPO(mainSubRelaService.getTableData(collTypeID));
        } catch (ServiceException e) {
            e.printStackTrace();
            resPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }
        return resPO;
    }
	
	@RequestMapping(value="saveTableData")
	@ResponseBody
    public Object saveTableData(String grid) {
        ResultPO resPO = null;
        try {
            CommonGrid commonGrid = (CommonGrid) (new ObjectMapper()).readJson(grid, CommonGrid.class);
            // 获取插入数据
            List<Map<String, Object>> insertList = commonGrid.getInsertValues();
            // 获取更新数据
            List<Map<String, Object>> updateList = commonGrid.getUpdateValues();
            // 获取删除数据
            List<Map<String, Object>> deleteList = commonGrid.getDeleteValues();

            if (deleteList.size() > 0)
                mainSubRelaService.deleteTableData(deleteList);

            if (insertList.size() > 0)
                mainSubRelaService.insertTableData(insertList);

            if (updateList.size() > 0)
                mainSubRelaService.updateTableData(updateList);
            resPO = new ResultPO("操作成功！");
        } catch (ServiceException e) {
            e.printStackTrace();
            resPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }
        return resPO;
    }

	/**
	 * 查询 主子表 关系设置
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getMainSubRela")
	@ResponseBody
	public String getMainSubRela(HttpServletRequest request) throws Exception{
		String mainSubID = request.getParameter("mainSubID");
	
		List<DictTSetMainSubRela> rela = mainSubRelaService.selectMainSubRela(mainSubID);
		
		return (new ObjectMapper()).writeValueAsString(rela);
	}
	/**
	 * 保存 主子表关系设置
	 * @param request <object>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="saveMainSubRela")
	@ResponseBody
	public Object saveMainSubRela(HttpServletRequest request){
	    ResultPO resPO = null;
		String relaObject = request.getParameter("relaObject"); //Object 对象
		String operator = request.getParameter("operator"); //
        try {
            Map<String, Object> relaMap  = (new ObjectMapper()).readJson(relaObject, HashMap.class);
       
        	mainSubRelaService.saveMainSubRela(relaMap,operator);
        	resPO = new ResultPO("操作成功！");
        } catch (ServiceException e1) {
            e1.printStackTrace();
        }
		return resPO;
	}
	
	//----------- 详细设置 功能	
	private static String SHOWCOLS="2";  //默认设置为2列。
	private static String TITLEWIDTH="150"; // 列宽默认为150
	
	/**
	 * 单页面整体设置
	 * @param objectid 
	 * @param tableid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getProjREC")
	@ResponseBody
	public Object getProjREC(String objectid, String tableid) {
		ResultPO resPO = null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("OBJECTID", objectid);
		paramMap.put("TABLEID", tableid);
		
		List<RECPO> list = null;
        try {
            list = mainRefService.getSetRECList(paramMap);
        } catch (ServiceException e) {
            e.printStackTrace();
            resPO = new ResultPO(e.getCode(),e.getErrorMessage());
        }
		
		Map<String, String> map = new HashMap<String, String>();
		if(list.size() > 1){
		    resPO = new ResultPO(ExceptionCode.ERR_00001,"页面定义表中单行返回多行！");
		    return resPO ;
		}
		if (list.size() > 0) {
		    
			RECPO po = (RECPO) list.get(0);
			map.put("showcols", po.getSHOWCOLS());
			map.put("titlewidth", po.getTITLEWIDTH());
			map.put("recid", po.getRECID());
			map.put("tableid", tableid);
		} else {
			map.put("showcols", SHOWCOLS);
			map.put("titlewidth", TITLEWIDTH);
			map.put("recid", "");
			map.put("tableid", tableid);
		}
		resPO = new ResultPO(map);
		return resPO;
	}
	/**
	 * 获取表中列树
	 * @param tableid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getInputColumnTree")
	@ResponseBody
	public Object getInputColumnTree(String tableid) {
	    ResultPO resPO = null;
		//Map<String, Object> map = new HashMap<String, Object>();
		List<DictTFactorPO> columnList = dictTFactorService.getDictTFactorsByTableId(tableid);
		Iterator<DictTFactorPO> it = columnList.iterator();
			        while (it.hasNext()) {//isvisible=0不显示在选择中
		            String isVisible = it.next().getIsvisible();
		            if ("0" .equals(isVisible)) {
			                it.remove();
		            }
			        }
		resPO  =new ResultPO(columnList);
		return resPO;
	}
	/**
	 * 保存 单页面详细设置
	 * @param businessObjForm
	 * @param objectid   采集类型collTypeID
	 * @param tableid    表
	 * @param showcols   总列数
	 * @param titlewidth 列宽
	 * @param recid 主键 || 外键
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "savePageColumn")
	@ResponseBody
	public Object saveBusinessObjForm(String grid, String objectid, String tableid, 
			String showcols,String titlewidth, String recid ) {
       ResultPO resPO = null;
        try {
            CommonGrid commonGrid = (CommonGrid) (new ObjectMapper()).readJson(grid, CommonGrid.class);
            String recID = mainRefService.saveSetREC(commonGrid, objectid, tableid, showcols, titlewidth, recid);
            resPO = new ResultPO(recID);
        }catch (ServiceException e) {
			e.printStackTrace();
			resPO = new ResultPO(e.getCode(),e.getErrorMessage());
		}
		return resPO;
	}
	
	/**
	 * 详细设置 表头定义
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "pageColumnDefine")
	@ResponseBody
	public Object getPageColumnDefine() {
	    ResultPO resPO = null;
		try {
            resPO = new ResultPO(mainRefService.getPageColumnDefine());
        } catch (ServiceException e) {
            e.printStackTrace();
            resPO = new ResultPO(e.getCode(),e.getErrorMessage());
        }
        return resPO;
	}

	/**
	 * 详细设置 列表数据查询
	 * @param adapterType
	 * @param objectid
	 * @param tableid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "pageColumnListData")
	@ResponseBody
	public Object getPageColumnListData(String grid) {
	    ResultPO resPO = null;
		try {
            CommonGrid commonGrid = (CommonGrid) (new ObjectMapper()).readJson(grid, CommonGrid.class);

            Map<String, String> map = new HashMap<String, String>();
            map.put("OBJECTID", (String) commonGrid.getExtProperties().get("objectid"));
            map.put("TABLEID", (String) commonGrid.getExtProperties().get("tableid"));
            
            resPO = new ResultPO( mainRefService.getPageColumnListData(map));
        } catch (ServiceException e) {
            e.printStackTrace();
            resPO = new ResultPO(e.getCode(),e.getErrorMessage());
        }
        return resPO;
	}
	/**
	 * 获取左侧树 数据
	 * @param appID
	 * @return
	 */
	@RequestMapping(value = "getLeftTreeData")
	@ResponseBody
	public Object getLeftTreeData(String appID){
	    ResultPO resPO = null;
	    try {
	        resPO = new ResultPO( mainSubRelaService.getLeftTreeData(appID));
        } catch (ServiceException e) {
            e.printStackTrace();
            resPO = new ResultPO(e.getCode(),e.getErrorMessage());
        }
        return resPO;
	}
	/**
	 * 新增左侧树
	 * @param name 表名
	 * @param code 表类型
	 * @param reftable 关联表ID
	 * return
	 */
	@RequestMapping(value = "saveLeftTreeData")
	@ResponseBody
	public Object saveLeftTreeData(String name,String code,String reftable){
	    ResultPO resPO = null;
		Map<String, String> map=new HashMap<String, String>();
		map.put("name",name);
		map.put("code", code);
		map.put("reftable", reftable);
		map.put("superid", "0");
		try {
            mainSubRelaService.saveLeftTree(map);
            resPO = new ResultPO("操作成功！");
        } catch (ServiceException e) {
            e.printStackTrace();
            resPO = new ResultPO(e.getCode(),e.getErrorMessage());
        }
        return resPO;
	}
	/**
	 * 删除左树的节点
	 * @param suitID
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteLeftTreeData")
	@ResponseBody
	public Object deleteLeftTreeData(String suitID,String code){
	    ResultPO resPO = null;
		Map<String, String> map=new HashMap<String, String>();
		map.put("suitID", suitID);
		map.put("code", code);
        try {
             mainSubRelaService.deleteLeftTreeData(map);
             resPO = new ResultPO("操作成功!");
        } catch (ServiceException e) {
            e.printStackTrace();
            resPO = new ResultPO(e.getCode(),e.getErrorMessage());
        }
		return resPO;
	}
	@RequestMapping(value = "suitTreeMainSub")
	@ResponseBody
	public Object suitTreeMainSub(String appID,String collTypeID){
	    ResultPO resPO = null;
	    if(null==appID || ("").equals(appID)){
            appID = "BGT";
        }
	    try {
            resPO = new ResultPO(mainSubRelaService.suitTreeMainSub(appID, collTypeID));
        } catch (ServiceException e) {
            e.printStackTrace();
            resPO = new ResultPO(e.getCode(),e.getErrorMessage());
        }
        return resPO;
       
	}

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "getMainSubColumn")
    @ResponseBody
    public Object getMainSubColumn(String tableID) {
        List<TreeNode> treeList = new ArrayList<TreeNode>();
        if (null == tableID || ("").equals(tableID)) {
            return null;
        }
        List<DictTFactorPO> columnList = dictTFactorService.getNotNullDictTFactorsByTableId(tableID);

        for (DictTFactorPO po : columnList) {
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
        return treeList;
    }
}
