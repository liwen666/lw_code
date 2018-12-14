package commons.sql.web;

import gov.mof.fasp2.ca.user.dto.UserDTO;
import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjhq.commons.inputcomponent.grid.settinggrid.service.ISettingGridService;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.sql.service.ISysFuncDefService;

@Controller
@RequestMapping(value = "/commons/sysFuncDef")
public class SysFuncDefController {
	@Resource
	private ISysFuncDefService sysFuncDefService;
	
	@Resource
	private ISettingGridService settingGridService;

	/**
	 * sysFuncDef页面
	 */
	@RequestMapping(value = "")
	public String page() {
		return "/commons/sql/sysFuncDef";
	}

	/**
	* 初始化表结构  
	 * @throws Exception 
	*/
    @RequestMapping(value = "getGridDefine")
	@ResponseBody
	public Object getGridDefine() throws Exception{
    	Table grid = sysFuncDefService.setTableDefine();
    	UserDTO user = SecureUtil.getCurrentUser();
    	Table table = settingGridService.initStructure(grid,user.getGuid());
		return table;
		
	}
	
	/**
	 * 取业务数据
	 */
    @RequestMapping(value = "getGridData")
	@ResponseBody
	public Object getData(String grid) throws Exception{
    	Grid data = (Grid)(new ObjectMapper()).readValue(grid,Grid.class);
		return sysFuncDefService.getData(data);
		
	}
    /**
	 * 新增 修改页面
	 */
	@RequestMapping(value = "sysFuncDefPage")
	public String sysFuncDefPage(){
		return "/commons/sql/sysFuncDefInfo";	
	}
	
	/**
	 *	查询定义信息
	 * @throws Exception 
	*/
    @RequestMapping(value = "getDefInfo")
	@ResponseBody
	public HashMap<String,String> getDefInfo(String guid){
    	return sysFuncDefService.getDefInfoByID(guid);
    }
    
    /**
	 * 保存定义信息
	 * 
	 * @param businessObjForm
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveData")
	@ResponseBody
	public HashMap<String,String> saveData(String saveData,HttpServletRequest request,HttpServletResponse response){
		HashMap<String,String> result = new HashMap<String,String>();
		try{
			String opt=request.getParameter("opt");//ADD-添加 ；UPDATE-修改
			HashMap<String,String> params = (HashMap<String,String>) (new ObjectMapper()).readValue(saveData,HashMap.class);
			if(null!=params){
				sysFuncDefService.saveData(params,opt);	
			}
			result.put("result", "保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result.put("result", "保存失败"+e.getMessage());
		}
		return result;
	}
	 /**
     * 删除数据
     * @param: @param grid
     */
	@RequestMapping(value = "delete")
	@ResponseBody
	public Object delete(String grid) {
		HashMap<String,String> map=new HashMap<String, String>();
		try {
			Grid table = (Grid) (new ObjectMapper()).readValue(grid, Grid.class);
			List<Map<String,Object>> deleteValues = table.getDeleteValues();
			if(null!=deleteValues && deleteValues.size()>0){
				for(Map<String,Object> param: deleteValues){
					sysFuncDefService.deleteData(param);
				}
			}
			map.put("result", "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result","删除失败"+e.getMessage());
		}
		return map;
	}
	
	/**
	 * 获取内容
	 */
	@RequestMapping(value = "/getContent")
	@ResponseBody
	public String getContent(String guid){
		return sysFuncDefService.getContentByID(guid);
	}

}
