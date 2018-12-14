package commons.inputcomponent.grid.levelgrid.web;

import gov.mof.fasp2.ca.user.dto.UserDTO;
import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjhq.commons.inputcomponent.grid.levelgrid.po.LevelGrid;
import com.tjhq.commons.inputcomponent.grid.levelgrid.service.ILevelGridService;

@Controller("levelGridController")
@RequestMapping(value = "/component/grid/levelGrid")
public class LevelGridController {
	
	@Resource
	private ILevelGridService levelGridService;
	
	 public ILevelGridService getLevelGridService() {
		return levelGridService;
	}


	public void setLevelGridService(ILevelGridService levelGridService) {
		this.levelGridService = levelGridService;
	}


	/**
	 * 获取层次表定义
	 * @param tableID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getDefine")
	public @ResponseBody LevelGrid getDefine(String tableID) throws Exception {
		LevelGrid levelGrid = new LevelGrid();
		levelGrid.setTableID(tableID);
		UserDTO user = SecureUtil.getCurrentUser();
		getLevelGridService().initStructure(levelGrid, user.getGuid());
		return levelGrid;

	}
	
	@RequestMapping(value = "getData")
	@ResponseBody
	public Object getData(@RequestBody LevelGrid levelGrid) throws Exception {
		return getLevelGridService().getData(levelGrid);
	}
}
