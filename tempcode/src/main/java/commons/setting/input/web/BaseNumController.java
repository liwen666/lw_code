package commons.setting.input.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjhq.commons.setting.input.po.DictTSetBaseNumTab;
import com.tjhq.commons.setting.input.service.IBaseNumService;
import com.tjhq.commons.setting.input.service.IEntryService;
/**
* 类名称：BaseNumController  
* 类描述：基本数字表设置
* 创建人：shishj
* 创建时间：Mar 20, 2014 6:50:34 AM
* @version
 */
@Controller
@RequestMapping(value = "/commons/setting/input/basenum")
public class BaseNumController {
	@Resource
	private IEntryService entryService;
	@Resource
	private IBaseNumService baseNumService;
	/**
	 * 查询 基本数字表设置 主表 
	 * @param tableID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getBaseNumData")
	@ResponseBody
	public String getBaseNumData(@RequestParam(value = "tableID") String tableID) throws Exception{	
		DictTSetBaseNumTab baseNum = entryService.getDataBaseTabList(tableID);
		
		return (new ObjectMapper()).writeValueAsString(baseNum);
	}
	
	//新增、保存 基本数字表设置
	@RequestMapping(value="saveBaseNumData")
	@ResponseBody
	public String saveBaseNumData(HttpServletRequest request) throws Exception{
		String data = request.getParameter("data");
		Map<String,Object> baseNum = (Map)ConverTables.jsonToMap(data).get(0);
		
		String guID = baseNumService.saveBaseNumTab(baseNum);
		//return (new ObjectMapper()).writeValueAsString(guID);
		return guID;
	}
	
}
