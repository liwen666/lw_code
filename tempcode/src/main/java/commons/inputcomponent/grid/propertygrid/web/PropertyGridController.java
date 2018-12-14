package commons.inputcomponent.grid.propertygrid.web;

import gov.mof.fasp2.ca.user.dto.UserDTO;
import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.grid.propertygrid.po.PropertyGrid;
import com.tjhq.commons.inputcomponent.grid.propertygrid.service.IPropertyGridService;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.jackson.ObjectMapper;

@Controller("propertyGridController")
@RequestMapping(value = "/commponent/grid/propertyGrid")
public class PropertyGridController {
	@Resource 
	private IPropertyGridService propertyGridService;
	
	
	/**
	 * @param tableID
	 * @return
	 * @throws Exception
	 * @throws
	 */
	@RequestMapping(value = "getDefine")
	@ResponseBody
	public Object getDefine(String tableID)throws Exception{
		PropertyGrid propertyGrid = new PropertyGrid();
        ResultPO resultPO = null;

        propertyGrid.setTableID(tableID);

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
            propertyGridService.initStructure(propertyGrid, user.getGuid());
            resultPO = new ResultPO(propertyGrid);
            return resultPO;
        } catch (ServiceException e) {
            e.printStackTrace();
            resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
            return resultPO;
        }
	}
	
	/**
	 * @param grid
	 * @return
	 * @throws Exception
	 * @throws
	 */
	@RequestMapping(value = "getData")
	@ResponseBody
	public Object getData(String grid)throws Exception{
		PropertyGrid propertyGrid = null ;
        ResultPO resultPO = null;
        
        try {
            propertyGrid = (PropertyGrid) (new ObjectMapper()).readJson(grid, PropertyGrid.class);
            resultPO = new ResultPO(propertyGridService.getData(propertyGrid));
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
	@RequestMapping(value = "saveData")
	@ResponseBody
	public Object saveData(String grid)throws Exception{
		PropertyGrid propertyGrid = null;
        ResultPO resultPO = null;
        
        try {
            propertyGrid = (PropertyGrid) (new ObjectMapper()).readJson(grid, PropertyGrid.class);
            propertyGridService.saveData(propertyGrid);
            resultPO = new ResultPO("数据保存成功!");
        } catch (ServiceException e) {
            e.printStackTrace();
            resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }
       
        return resultPO;
	}
}
