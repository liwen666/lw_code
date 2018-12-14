package commons.mongodb.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.jackson.ObjectMapper;
import com.tjhq.commons.mongodb.service.IMongoDbDictService;

@Controller
@RequestMapping(value = "/commons/mongodb/mongoDbDict")
public class MongoDbDictController {
	@Resource
	private IMongoDbDictService mongoDbDictService;
	
	// 主页面
	@RequestMapping("/main")
	public String mainpage(Model model, String style) {
		return "/commons/mongodb/mongoDbDict";
	}
	// 获取表头
	@RequestMapping("/getDefine")
	@ResponseBody
	public Object getDefine() throws Exception {
		ResultPO result = null;
		try {
			result = new ResultPO(mongoDbDictService.getDefineMongoDbDict());

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	// 获取数据
	@RequestMapping("/getData")
	@ResponseBody
	public Object getData(String grid) throws Exception {
		
		ResultPO resultPO = null;
		try {
			CommonGrid commonGrid = (CommonGrid) (new ObjectMapper()).readJson(
					grid, CommonGrid.class);
			resultPO = new ResultPO(mongoDbDictService.getDataMongoDbDict(commonGrid));
		} catch (ServiceException e) {
			e.printStackTrace();
			resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
		}
		return resultPO;
	}
	
	// 保存数据
	@RequestMapping("/saveData")
	@ResponseBody
	public Object saveData(String grid) throws Exception {
		CommonGrid commonGrid = null;
		ResultPO resultPO = null;

		try {
			commonGrid = (CommonGrid) (new ObjectMapper()).readJson(grid,
					CommonGrid.class);
			mongoDbDictService.saveData(commonGrid);
			resultPO = new ResultPO("数据保存成功!");
		} catch (ServiceException e) {
			e.printStackTrace();
			resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
		}

		return resultPO;
	}
	
	
}
