/**
 * @Title: ModelToAuditObjController.java
 * @Copyright (C) 2015 太极华青
 * @Description: 通用设置功能
 * @Revision 6.0 2015-11-23  ZhengQ
 */
package commons.setting.dataaudit.modeltoauditobj.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.setting.dataaudit.modeltoauditobj.service.IModelToAuditObjService;

/**
 * @ClassName: ModelToAuditObjController
 * @Description: 录入对象登记功能
 * @author: ZhengQ 2015-11-27 下午04:49:09
 */

@Controller
@RequestMapping(value = "/commons/setting/dataaudit/modelToAuditobj/")
public class ModelToAuditObjController {
    /**
     * @Fields modelToAuditObjService : 录入对象登记功能接口
     */
    @Resource
    private IModelToAuditObjService modelToAuditObjService;

    /**
     * @return URL
     * @throws
     */
    @RequestMapping(value = "page")
    public String page() {
        return "/commons/setting/dataaudit/modeltoauditobj/ModelToAuditobj";
    }

    /**.
     * 获取右侧报表树
     * @param inputTableID 录入对象ID
     * @return Object
     * @throws
     */
    @RequestMapping(value = "tabTree")
    @ResponseBody
    public Object tabTree(String inputTableID) {
        ResultPO resPO = null;
        try {
            resPO = new ResultPO(modelToAuditObjService.getTabTree(inputTableID));
        } catch (ServiceException e) {
            e.printStackTrace();
            resPO = new ResultPO(e.getCode(),e.getErrorMessage());
        }
        return resPO;
    }

    /**.
     * 获取左侧录入表树
     * @param tableType 表类型
     * @return Object
     */
    @RequestMapping(value = "objTree")
    @ResponseBody
    public Object objTree(String tableType) {
        ResultPO resPO = null;
        try {
            resPO = new ResultPO(modelToAuditObjService.getObjTree(tableType));
        } catch (ServiceException e) {
            e.printStackTrace();
            resPO = new ResultPO(e.getCode(),e.getErrorMessage());
        }
        return resPO;
    }

    /**.
     * 保存到关系表
     * @param addModelIDs 新勾选的对象ID
     * @param delModelIDs 取消选中的对象ID
     * @param inputTableID 录入对象ID
     * @return Object
     * @throws
     */
    @RequestMapping(value = "saveModelToAuditobj")
    @ResponseBody
    public Object saveModelToAuditobj(String addModelIDs,String delModelIDs, String inputTableID) {
        ResultPO resPO = null;
        try {
            modelToAuditObjService.saveModelToAuditobj(addModelIDs,delModelIDs, inputTableID);
           resPO = new ResultPO("操作成功！");
        } catch (ServiceException e) {
            e.printStackTrace();
            resPO = new ResultPO(e.getCode(),e.getErrorMessage());
        }
        return resPO;
    }
}
