package commons.inputcomponent.grid.fixgrid.web;

import gov.mof.fasp2.ca.user.dto.UserDTO;
import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.grid.fixgrid.po.FixGrid;
import com.tjhq.commons.inputcomponent.grid.fixgrid.service.IFixGridService;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.jackson.ObjectMapper;

@Controller("fixGridController")
@RequestMapping(value = "/commponent/grid/fixGrid")
public class FixGridController {
	
	@Resource
	private IFixGridService fixGridService;
	 
	/**
	 * 获得固定行列表定义
	 * @param tableID
	 * @return
	 * @throws Exception
	 * @throws
	 */
	@RequestMapping(value="getDefine")
	@ResponseBody
	public Object getDefine(@RequestParam(value = "tableID") String tableID) throws Exception{
        FixGrid table = new FixGrid(); 
        ResultPO resultPO = null;

        table.setTableID(tableID);

        UserDTO user = null;
        try {
            user = SecureUtil.getCurrentUser();
        } catch (Exception e) {
            e.printStackTrace();
            resultPO = new ResultPO(ExceptionCode.ERR_00000, ExceptionCode.ERR_00000 + ":加载用户信息出错!");
            return resultPO;
        }

        if (user == null) {
            resultPO = new ResultPO(ExceptionCode.ERR_00000, ExceptionCode.ERR_00000 + ":加载用户信息出错!");
            return resultPO;
        }

        try {
            fixGridService.initStructure(table, user.getGuid()); 
            resultPO = new ResultPO(table);
            return resultPO;
        } catch (ServiceException e) {
            e.printStackTrace();
            resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
            return resultPO;
        }

       
	}

    /**
     * 取业务数据
     * @param grid
     * @return
     * @throws Exception
     * @throws
     */
    @RequestMapping(value = "getData")
	@ResponseBody
	public Object getData(String grid) throws Exception{
		FixGrid fixGrid = null ;
        ResultPO resultPO = null;
        
        try {
            fixGrid = (FixGrid) (new ObjectMapper()).readJson(grid, FixGrid.class);
            resultPO = new ResultPO(fixGridService.getData(fixGrid));
        } catch (ServiceException e) {
            e.printStackTrace();
            resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }
        
        return resultPO;
        
	}
	
	/**
	 * 获得固定行列表保存数据
	 * @param grid
	 * @return
	 * @throws Exception
	 * @throws
	 */
	@RequestMapping(value="saveData")
	@ResponseBody
	public Object saveData(@RequestParam(value = "grid") String grid) throws Exception{
		FixGrid fixGrid = null ;
        ResultPO resultPO = null;
        
        try {
            fixGrid = (FixGrid) (new ObjectMapper()).readJson(grid, FixGrid.class);
            fixGridService.saveData(fixGrid);
            resultPO = new ResultPO("数据保存成功!");
        } catch (ServiceException e) {
            e.printStackTrace();
            resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }
       
        return resultPO;
	}
}
