
package commons.inputcomponent.grid.commonGrid.web;

import gov.mof.fasp2.ca.user.dto.UserDTO;
import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.inputcomponent.grid.commonGrid.service.ICommonGridService;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.jackson.ObjectMapper;

@Controller("commonGridController")
@RequestMapping(value = "/commponent/grid/commonGrid")
public class CommonGridController {
    @Resource
    private ICommonGridService commonGridService;

    /**
     * 初始化表结构
     * @throws
     */
    @RequestMapping(value = "getDefine")
    @ResponseBody
    public Object getDefine(String tableID) {
        CommonGrid commonGrid = new CommonGrid();
        ResultPO resultPO = null;

        commonGrid.setTableID(tableID);

        UserDTO user = null;
        try {
            user = SecureUtil.getCurrentUser();
        } catch (Exception e) {
            e.printStackTrace();
            resultPO = new ResultPO(ExceptionCode.ERR_00000, ExceptionCode.ERR_00002 + ":加载用户信息出错!");
            return resultPO;
        }

        if (user == null) {
            resultPO = new ResultPO(ExceptionCode.ERR_00000, ExceptionCode.ERR_00002 + ":加载用户信息出错!");
            return resultPO;
        }

        try {
            commonGridService.initStructure(commonGrid, user.getGuid());
            resultPO = new ResultPO(commonGrid);
            return resultPO;
        } catch (ServiceException e) {
            e.printStackTrace();
            resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
            return resultPO;
        }

    }

    /**
     * 取业务数据
     * @Title: getData
     * @throws
     */
    @RequestMapping(value = "getData")
    @ResponseBody
    public Object getData(String grid) {
        CommonGrid commonGrid = null ;
        ResultPO resultPO = null;
        
        try {
            commonGrid = (CommonGrid) (new ObjectMapper()).readJson(grid, CommonGrid.class);
            resultPO = new ResultPO(commonGridService.getData(commonGrid));
        } catch (ServiceException e) {
            e.printStackTrace();
            resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }
        
        return resultPO;

    }

    /**
     * @Title: saveData
     * @throws
     */
    @RequestMapping(value = "saveData")
    @ResponseBody
    public Object saveData(String grid) {
        CommonGrid commonGrid = null;
        ResultPO resultPO = null;
        
        try {
            commonGrid = (CommonGrid) (new ObjectMapper()).readJson(grid, CommonGrid.class);
            commonGridService.saveData(commonGrid);
            resultPO = new ResultPO("数据保存成功!");
        } catch (ServiceException e) {
            e.printStackTrace();
            resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }
       
        return resultPO;
    }

}
