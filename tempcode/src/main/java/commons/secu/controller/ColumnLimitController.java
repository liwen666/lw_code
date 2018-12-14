package commons.secu.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjhq.commons.dict.external.po.DictTModelcodePO;
import com.tjhq.commons.dict.external.service.IDictTModelcodeService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.jackson.ObjectMapper;
import com.tjhq.commons.secu.external.IDataAuthService;
import com.tjhq.commons.secu.po.ColumnLimitPO;
import com.tjhq.commons.secu.service.IColumnLimitService;
@Controller
@RequestMapping(value = "/commons/secu/columnLimit")
public class ColumnLimitController {
	
	@Resource
	private IColumnLimitService columnLimitService;
	
	@Resource
	private IDictTModelcodeService modelCodeService;
	
	@RequestMapping(value="")
	public String page(HttpServletRequest request) {
		request.setAttribute("appID",request.getParameter("appID"));
		
		return "/commons/secu/columnLimit";
	}
	
	@RequestMapping(value="querySuitModel")
	@ResponseBody
	public Object querySuitModel(String appID) throws Exception{
		if(StringUtils.isEmpty(appID)){
			return null;
		}
		List<Map<String,String>> treeList = columnLimitService.querySuitModel(appID);
		return treeList;
	}
	
	/**
     * 获取表头
     * 
     */
    @RequestMapping(value = "getDefine1")
    @ResponseBody
    public Object getDefine1() throws Exception {
    	//返回结果
    	ResultPO resultPO = null;
    	try {
	        resultPO = new ResultPO(columnLimitService.getDefine1());
	    } catch (ServiceException e) {
	        e.printStackTrace();
	        resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
	    }
	    return resultPO;
    }

    /**
     * 获取数据
     * 
     */
    @RequestMapping(value = "getData1")
    @ResponseBody
    public Object getData1(String grid) throws Exception {
    	//返回结果
		ResultPO resultPO = null;
		try{
	    	CommonGrid commonGrid = (CommonGrid) (new ObjectMapper()).readJson(grid, CommonGrid.class);
	    	resultPO = new ResultPO(columnLimitService.getData1(commonGrid));
		}catch(ServiceException e){
			e.printStackTrace();
			resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
		}	
		return resultPO;
    }
    /**
     * 删除数据
     */
    @RequestMapping(value = "deleteColumnLimit")
    @ResponseBody
    public Object deleteColumnLimit(String jsonStr) throws Exception {
    	//返回结果
    	ResultPO resultPO = null;
    	try {
    		String result = columnLimitService.deleteColumnLimit(jsonStr);
    		resultPO = new ResultPO(result);
    	} catch (ServiceException e) {
            e.printStackTrace();
            resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }
    	return resultPO;
    }
	/**
	 * 查询树 某表的列
	 * @param tableID
	 * @param defineID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getFactorTree")
	@ResponseBody
	public Object getFactorTree(String tableid) throws Exception {
		return columnLimitService.queryFactorTree(tableid);
	}
	
	/**
	 * 查询树 某角色 某列 对应的 限制列
	 * @param tableID
	 * @param defineID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "queryFactorColumnLimitDetail")
	@ResponseBody
	public Object queryFactorColumnLimitDetail(String roleid , String tableid, String columnid) throws Exception {
		return columnLimitService.queryFactorColumnLimitDetail(roleid, tableid, columnid);
	}
	
	@RequestMapping(value="saveTableData")
	@ResponseBody
	public Object saveTableData(String operate, ColumnLimitPO po) throws Exception{
		//返回结果
    	ResultPO resultPO = null;
    	try {
    		
    		String result = columnLimitService.saveTableData(operate, po);
    		
    		resultPO = new ResultPO(result);
    	} catch (ServiceException e) {
	        e.printStackTrace();
	        resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
	    }
	    return resultPO;
	}
	@RequestMapping(value="deleteCondition")
	@ResponseBody
	public Object deleteCondition(ColumnLimitPO columnLimitPO) throws Exception{
		//返回结果
    	ResultPO resultPO = null;
    	try {
    		String result = columnLimitService.deleteColumnLimitDetail(columnLimitPO);
    		resultPO = new ResultPO(result);
    	} catch (ServiceException e) {
	        e.printStackTrace();
	        resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
	    }
	    return resultPO;
	}
	/**
     * 获取表头
     * 
     */
    @RequestMapping(value = "getDefine2")
    @ResponseBody
    public Object getDefine2() throws Exception {
    	//返回结果
    	ResultPO resultPO = null;
    	try {
	        resultPO = new ResultPO(columnLimitService.getDefine2());
	    } catch (ServiceException e) {
	        e.printStackTrace();
	        resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
	    }
	    return resultPO;
    }

    /**
     * 获取数据
     * 
     */
    @RequestMapping(value = "getData2")
    @ResponseBody
    public Object getData2(String grid) throws Exception {
    	//返回结果
		ResultPO resultPO = null;
		try{
	    	CommonGrid commonGrid = (CommonGrid) (new ObjectMapper()).readJson(grid, CommonGrid.class);
	    	resultPO = new ResultPO(columnLimitService.getData2(commonGrid));
		}catch(ServiceException e){
			e.printStackTrace();
			resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
		}	
		return resultPO;
    }
    
    /**
	 * 查询应用的列
	 * @param csid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getModelCodeDataByCsid")
	@ResponseBody
	public Object getModelCodeDataByCsid(String csid, String selectId) throws Exception{
		//返回结果
		ResultPO resultPO = null;
		try{
	    	resultPO = new ResultPO(columnLimitService.getModelCodeDataByCsid(csid, selectId));
		}catch(ServiceException e){
			e.printStackTrace();
			resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
		}	
		return resultPO;
	}
}
