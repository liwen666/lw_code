package commons.setting.input.web;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.setting.input.service.IPreViewTableService;

@Controller
@RequestMapping(value = "/commons/setting/input/preview/")
public class PreViewTableController {
	@Resource
	private IPreViewTableService preViewTableService;
	
	
	@RequestMapping(value = "getDefine")
	@ResponseBody
	public Object getDefine(String tableID,String dealType, String typeID, String processID) {
	    ResultPO resPO = null;
		try {
            resPO = new ResultPO(preViewTableService.getDefine(tableID, dealType,typeID, processID));
        } catch (ServiceException e) {
            e.printStackTrace();
            resPO = new ResultPO(e.getCode(),e.getErrorMessage());
        }
        return resPO;
	}

	@RequestMapping(value = "getData")
	@ResponseBody
	public Object getData(String grid,String dealType){
	    ResultPO resPO = null;
        try {
            resPO = new ResultPO(preViewTableService.getData(grid,dealType));
        } catch (ServiceException e) {
            e.printStackTrace();
            resPO = new ResultPO(e.getCode(),e.getErrorMessage());
        }
        return resPO;
	}
}
