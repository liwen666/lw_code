/**
 * @Title: ExceptionManageController.java
 * @Copyright (C) 2015 太极华青
 * @Description: 通用设置功能
 * @Revision 6.0 2015-11-23  ZhengQ
 */
package commons.setting.exception.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.jackson.ObjectMapper;
import com.tjhq.commons.setting.exception.service.IExceptionManageService;

/**
 * @ClassName: ExceptionManageController
 * @Description: 异常管理功能
 * @author: ZhengQ 2015-11-26 上午11:03:57
 */

@Controller
@RequestMapping(value = "/commons/setting/exception/")
public class ExceptionManageController {
    /**
     * @Fields exceptionManageService : 异常管理功能接口
     */
    @Resource
    private IExceptionManageService exceptionManageService;

    /**
     * @return exceptionManageService
     */
    public IExceptionManageService getExceptionManageService() {
        return exceptionManageService;
    }

    /**
     * @param exceptionManageService 要设置的 exceptionManageService
     */
    public void setExceptionManageService(IExceptionManageService exceptionManageService) {
        this.exceptionManageService = exceptionManageService;
    }

    /**.
     * 转到异常管理页面
     * @return String返回页面的URL
     * @throws
     */
    @RequestMapping(value = "page")
    public String page() {
        return "commons/setting/exception/ExceptionManage";
    }

    /**.
     * 获取异常管理表表定义
     * @return ResultPO
     * @throws
     */
    @RequestMapping(value = "getTableDefine")
    @ResponseBody
    public Object getTableDefine() {
        ResultPO resPO = null;
        try {
            Object result = getExceptionManageService().getTableDefine();
            resPO = new ResultPO(result);
        } catch (ServiceException e) {
            e.printStackTrace();
            resPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }
        return resPO;
    }

    /**.
     * 获取表数据
     * @param grid 表对象
     * @return ResultPO
     */
    @RequestMapping(value = "getTableData")
    @ResponseBody
    public Object getTableData(String grid) {
        ResultPO resPO = null;
        CommonGrid commonGrid = null;
        try {
            commonGrid = (CommonGrid) (new ObjectMapper()).readJson(grid, CommonGrid.class);

            resPO = new ResultPO(getExceptionManageService().getTableData(commonGrid));
        } catch (ServiceException e) {
            e.printStackTrace();
            resPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }
        return resPO;
    }

    /**.
     * 保存表数据
     * @param grid 表对象
     * @return ResultPO
     */
    @RequestMapping(value = "saveTableData")
    @ResponseBody
    public Object saveTableData(String grid) {
        ResultPO resPO = null;
        CommonGrid commonGrid = null;
        try {
            commonGrid = (CommonGrid) (new ObjectMapper()).readJson(grid, CommonGrid.class);

            getExceptionManageService().saveData(commonGrid);
            resPO = new ResultPO("操作成功！！");
        } catch (ServiceException se) {
            se.printStackTrace();
            resPO = new ResultPO(se.getCode(), se.getErrorMessage());
        }
        return resPO;
    }
}
