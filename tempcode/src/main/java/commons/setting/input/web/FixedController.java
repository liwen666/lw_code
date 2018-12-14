package commons.setting.input.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjhq.commons.cache.service.IDataCacheService;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.dict.service.IDictTDefaultcolService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.grid.fixgrid.po.FixGrid;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.setting.external.service.IEntryOuterService;
import com.tjhq.commons.setting.input.po.DictTSetFixPO;
import com.tjhq.commons.setting.input.service.IEntryService;
import com.tjhq.commons.setting.input.service.IFixedService;
import com.tjhq.commons.utils.UserUtil;

/**
* 类名称：FixedController  
* 类描述：固定行列表设置
* 创建人：shishj
* 创建时间：Mar 27, 2014 6:16:45 AM
* @version
 */
@Controller
@RequestMapping(value = "/commons/setting/input/fixed")
public class FixedController {
	//获取缺省列 接口
	@Resource
	public IDictTDefaultcolService dictTDefaultcolService;
	@Resource
	public IDictTFactorService dictTFactorService;
	@Resource
	public IEntryService entryService;
	@Resource
	public IFixedService fixedService;
	@Resource 
	private IDataCacheService dataCacheService;
	
	/**
	 * 获得固定行列表定义
	 * @param businessObjId
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value="getDefine")
	@ResponseBody
	public Object getDefine(@RequestParam(value = "tableID") String tableID) throws Exception{
		String userId= UserUtil.getUserInfo().getGuid();  
		FixGrid table = new FixGrid(); 
		table.setTableID(tableID);    
		FixGrid grid =(FixGrid) fixedService.initStructure(table, userId); 
        return grid;   
	}
	
	/**
	 * 根据 tableID | typeID 查询 固定行列表设置信息
	 * @param tableID
	 * @param typeID 类型，1表示fdcode_1,2表示fdcode_2,3表示fdcode_3
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getFixedWhole")
	@ResponseBody
	public DictTSetFixPO getFixedWhole(@RequestParam(value = "tableID") String tableID,
			@RequestParam(value = "typeID") String typeID) throws Exception{		
		DictTSetFixPO fix = entryService.getDataFixByTypeList(tableID,typeID);
		
		if(null != fix){
			//层次名称列
			DictTFactorPO po = dictTFactorService.getDictTFactorByColumnId(fix.getLvlNanmeCol());
			if(po != null) fix.setLvlNanmeColName(po.getName());//中文名称列
			//编码对应列
			String cols = fix.getFdCodeToCols();
			if(ConverTables.isNotNull(cols)){
				String colName = "";
				for(String c : cols.split(",")){
					po = dictTFactorService.getDictTFactorByColumnId(c);
					if(po != null) colName += po.getName() + ",";
				}
				if(ConverTables.isNotNull(colName)) colName = ConverTables.removeLastChar(colName,",");
				//每组FDCODE 对应列
				fix.setFdCodeToColsName(colName);	
			}	
		}
		return fix;
	}
	@RequestMapping(value = "getOtherToCols")
	@ResponseBody
	public String getOtherToCols(@RequestParam(value = "tableID") String tableID,
			@RequestParam(value = "typeID") String typeID) throws Exception{	
		
		String otherToCol = entryService.getOtherToCols(tableID,typeID);
		//不包含 当前TypeId 的 fdCodeToCols
		if(otherToCol.equals(",")) otherToCol = "";
		return (new ObjectMapper()).writeValueAsString(otherToCol);
	}
	
	@RequestMapping(value = "saveFixedWhole")
	@ResponseBody
	public String saveFixedWhole(HttpServletRequest request) throws Exception{
		//JSON数据
		String jsonTable= request.getParameter("jsonTable");
		String operator= request.getParameter("operator"); //操作
		String dealType = request.getParameter("dealType"); 

		String flag = "" ;
		DictTSetFixPO fixed = (new ObjectMapper()).readValue(jsonTable,DictTSetFixPO.class);
		if(ConverTables.isNotNull(operator)){		
			flag = fixedService.saveFixedWhole(fixed, operator,dealType);
			this.clearFixedCatch(IEntryOuterService.SETFIXED, fixed.getTableID());
		}
		
		return flag;
	}

	/**
	 * 取业务数据
	* @Title: getData 
	* @param @param table
	* @param @return
	* @param @throws Exception    设定文件 
	* @return List    返回类型 
	* @throws
	 */
    @RequestMapping(value = "getData")
	@ResponseBody
	public Object getData(String grid) throws Exception{
		FixGrid fixGrid = (FixGrid)(new ObjectMapper()).readValue(grid,FixGrid.class); 

		Object fixData = fixedService.getData(fixGrid);

		return fixData;            
	}
	/**
	 *  固定行列表保存数据
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="saveData")
	@ResponseBody
	public Object saveData(@RequestParam(value = "grid") String grid) throws Exception{
        ResultPO resultPO = null;
		try {
			FixGrid fixGrid = (FixGrid)(new ObjectMapper()).readValue(grid,FixGrid.class);
			fixedService.saveData(fixGrid);      
			this.clearFixedCatch(IEntryOuterService.FIXED,fixGrid.getTableID());

			resultPO = new ResultPO("保存成功");
		} catch(ServiceException e) {
			e.printStackTrace();
            resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
		}
		return resultPO;
	}
	/**
	 * 清缓存
	 * @param keys
	 */
	private void clearFixedCatch(String subKey ,String tableID) {
		String [] keys = {"HQ.COMM",subKey,tableID};
		Object commInputCatch = dataCacheService.get(keys);
		if(commInputCatch!=null){
			dataCacheService.put(keys, null);
		}
	}
	/**
	 * 发布 浮动表 、 固定行列表
	 *  //一、将表中模板数据 插入 不同的单位数据中
	 *  //二、对于修改 、 删除 的模板数据、 对单位数据进行同步更新
	 *  将SYNSTATUS字段置为1
	 *  edit by bizaiyi 2015.4.24
	 *  
	 * @param tableID
	 * @param dealType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "publishFloat")
	@ResponseBody
	public String publishFloat(@RequestParam(value = "tableID") String tableID,
			@RequestParam(value = "dealType") String dealType,@RequestParam(value = "appID") String appID) throws Exception{
		String is_success = "{\"flag\":\"true\"}";
		try{
			fixedService.publishData(tableID);
		}catch (Exception e) {
			e.printStackTrace();
			is_success = "{\"flag\":\"false\"}";
		}
		return is_success;
	}
}
