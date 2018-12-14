package commons.inputcomponent.grid.floatgrid.web;

import gov.mof.fasp2.ca.user.dto.UserDTO;
import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.grid.floatgrid.po.FloatGrid;
import com.tjhq.commons.inputcomponent.grid.floatgrid.service.IFloatGridService;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.jackson.ObjectMapper;

@Controller("floatGridController")
@RequestMapping(value = "/commponent/grid/floatGrid")
public class FloatGridController {
    
	@Resource
	private IFloatGridService floatGridService;
	
	
	/**
	 * 获得浮动表定义
	 * @param tableID
	 * @return
	 * @throws Exception
	 * @throws
	 */
	@RequestMapping(value="getDefine")
	@ResponseBody
	public Object getDefine(@RequestParam(value = "tableID") String tableID) throws Exception{
        FloatGrid grid = new FloatGrid();
        ResultPO resultPO = null;

        grid.setTableID(tableID);

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
            floatGridService.initStructure(grid, user.getGuid());
            resultPO = new ResultPO(grid);
            return resultPO;
        } catch (ServiceException e) {
            e.printStackTrace();
            resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
            return resultPO;
        }
        
	}

	/**
	 * 获得浮动表数据
	 * @param grid
	 * @return
	 * @throws Exception
	 * @throws
	 */
	@RequestMapping(value="getData") 
	@ResponseBody
	public Object getData(@RequestParam(value = "grid") String grid) throws Exception{
		FloatGrid floatGrid = null ;
        ResultPO resultPO = null;
        
        try {
            floatGrid = (FloatGrid) (new ObjectMapper()).readJson(grid, FloatGrid.class);
            resultPO = new ResultPO(floatGridService.getData(floatGrid));
        } catch (ServiceException e) {
            e.printStackTrace();
            resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }
        
        return resultPO;
	}
	
	/**
	 * @param grid
	 * @return
	 * @throws Exception
	 * @throws
	 */
	@RequestMapping(value="saveData")
	@ResponseBody
	public Object saveData(@RequestParam(value = "grid") String grid) throws Exception{
		FloatGrid floatGrid = null;
        ResultPO resultPO = null;
        
        try {
            floatGrid = (FloatGrid) (new ObjectMapper()).readJson(grid, FloatGrid.class);
            floatGridService.saveData(floatGrid);
            resultPO = new ResultPO("数据保存成功!");
        } catch (ServiceException e) {
            e.printStackTrace();
            resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }
       
        return resultPO;
	}
	
}
