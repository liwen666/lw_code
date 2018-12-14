/**
 * @Title: ViewToTableController.java
 * @Copyright (C) 2015 太极华青
 * @Description: 通用设置功能
 * @Revision 6.0 2015-11-23  ZhengQ
 */
package commons.setting.dataaudit.viewtotable.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.jackson.ObjectMapper;
import com.tjhq.commons.setting.dataaudit.viewtotable.service.IViewToTableService;

/**
 * @ClassName: ViewToTableController
 * @Description: 视图对应物理表登记功能
 * @author: ZhengQ 2015-11-27 下午05:03:49
 */

@Controller
@RequestMapping(value = "/commons/setting/dataaudit/viewTotable/")
public class ViewToTableController {
    /**
     * @Fields viewToTableService : 视图对应物理表登记功能接口
     */
    @Resource
    private IViewToTableService viewToTableService;

    /**
     * @return URL
     * @throws
     */
    @RequestMapping(value = "page")
    public String page() {
        return "/commons/setting/dataaudit/viewtotable/ViewToTable";
    }

    /**.
     * 获取左侧视图树
     * @param tableType 表类型
     * @param viewID 视图ID
     * @return ResultPO
     * @throws
     */
    @RequestMapping(value = "viewTree")
    @ResponseBody
    public Object getViewTree(String tableType, String viewID) {
        ResultPO resPO = null;
         try {
             resPO = new ResultPO(viewToTableService.getModelTree(tableType, viewID));
        } catch (ServiceException e) {
            e.printStackTrace();
            resPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }
        return resPO;
    }

    /**.
     * 获取表头定义
     * @return Object
     * @throws
     */
    @RequestMapping(value = "getTableDefine")
    @ResponseBody
    public Object getTableDefine() {
        ResultPO resPO = null;
        try {
            resPO = new ResultPO(viewToTableService.getTableDefine());
        } catch (ServiceException e) {
            e.printStackTrace();
            resPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }
        return resPO;
    }

    /**.
     * 获取表数据
     * @param grid grid的json串
     * @return Object
     * @throws
     */
    @RequestMapping(value = "getTableData")
    @ResponseBody
    public Object getTableData(String grid) {
        ResultPO resPO = null;
        try {
            CommonGrid commonGrid = (CommonGrid) (new ObjectMapper()).readJson(grid, CommonGrid.class);
             resPO = new ResultPO(viewToTableService.getData(commonGrid));
        } catch (ServiceException e) {
            e.printStackTrace();
            resPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }
         return resPO;
    }

    /**.
     * 保存视图id 表id 到中间表
     * @param tableIDs 表ID
     * @param viewID 视图ID
     * @return Object
     * @throws
     */
    @RequestMapping(value = "saveViewToTab")
    @ResponseBody
    public Object saveViewToTab(String viewID, String tableIDs) {
        ResultPO resPO = null;
        try {
             viewToTableService.saveViewToTab(viewID, tableIDs);
           resPO = new ResultPO("保存成功！");
        } catch (ServiceException e) {
            e.printStackTrace();
            resPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }
        return resPO;
    }

    /**.
     * 删除关系表中数据
     * @param grid grid的json串
     * @return Object
     * @throws
     */
    @RequestMapping(value = "updateViewToTab")
    @ResponseBody
    public Object updateViewToTab(String grid) {
        ResultPO resPO = null;
        try {
            CommonGrid commonGrid = (CommonGrid) (new ObjectMapper()).readJson(grid, CommonGrid.class);
            viewToTableService.updateViewToTab(commonGrid);
            resPO = new ResultPO("操作成功！");
        } catch (ServiceException e) {
            e.printStackTrace();
           resPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }
        return resPO;
    }
}
