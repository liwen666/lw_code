package commons.sql.web;

import gov.mof.fasp2.ca.user.dto.UserDTO;
import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjhq.commons.inputcomponent.grid.settinggrid.service.ISettingGridService;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.sql.service.ISqlManagerService;

@Controller
@RequestMapping(value = "/commons/sql")
public class SqlManagerController {
	@Resource
	private ISqlManagerService sqlManagerService;
	
	@Resource
	private ISettingGridService settingGridService;

	/**
	 * sql页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "")
	public String page() {
		return "/commons/sql/sqlManager";
	}

	/**
	* 初始化表结构  
	 * @throws Exception 
	*/
    @RequestMapping(value = "getGridDefine")
	@ResponseBody
	public Object getGridDefine() throws Exception{
    	Table grid = sqlManagerService.setTableDefine();
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
		return sqlManagerService.getData(data);
		
	}
	/**
	 * sql添加、修改
	 * 
	 * @param saveData
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/save")
	@ResponseBody
	public Object save(String saveData, String flag){
		HashMap<String,String> map=new HashMap<String, String>();
		try {
			HashMap<String,String> param = (HashMap<String,String>) (new ObjectMapper()).readValue(saveData,
					HashMap.class);
			if ("add".equals(flag)) {
				sqlManagerService.insertSql(param);

			} else if ("update".equals(flag)) {

				sqlManagerService.updateSql(param);

			}
			map.put("result", "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result","保存失败"+e.getMessage());
		}
		return map;
	}

	/**
	 * sql删除
	 * 
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(String grid){
		HashMap<String,String> map=new HashMap<String, String>();
		try {
			Grid table = (Grid) (new ObjectMapper()).readValue(grid, Grid.class);
			List<Map<String,Object>> deleteValues = table.getDeleteValues();
			if(null!=deleteValues && deleteValues.size()>0){
				for(Map<String,Object> param: deleteValues){
					sqlManagerService.deleteSql((String) param.get("SQLID"));
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
	 * sql执行
	 * 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/executeSql")
	@ResponseBody
	public Object execute(String saveData){
		HashMap<String,String> result=new HashMap<String, String>();
		try {
			HashMap<String,String> param = (HashMap<String,String>) (new ObjectMapper()).readValue(saveData,
					HashMap.class);
			String sqlid = param.get("SQLID");
			if(StringUtils.isNotEmpty(sqlid)){
				sqlManagerService.executeSql(sqlid);
			}
			result.put("result", "执行成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result","执行失败"+e.getMessage());
		}
		return result;
	}

}
