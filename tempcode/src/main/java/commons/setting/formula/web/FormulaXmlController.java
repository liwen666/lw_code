/**
 * @Title: FormulaXmlController.java
 * @Copyright (C) 2016 太极华青
 * @Description: 公式导入导出xml
 * @Revision 1.0 2016-3-29  ZhengQ
 */

package commons.setting.formula.web;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.setting.formula.service.IFormulaXmlService;

/**
 * @ClassName: FormulaXmlController
 * @Description: 公式导入导出xml
 * @author: ZhengQ 2016-3-29 上午11:01:33
 */
@Controller
@RequestMapping(value = "/commons/setting/formula/")
public class FormulaXmlController {
    /**
     * @Fields formulaXmlService : 公式导入导出xml接口
     */
    @Resource
    private IFormulaXmlService formulaXmlService;

    /**
     * @return formulaXmlService
     */
    public IFormulaXmlService getFormulaXmlService() {
        return formulaXmlService;
    }

    /**
     * @param formulaXmlService 要设置的 formulaXmlService
     */
    public void setFormulaXmlService(IFormulaXmlService formulaXmlService) {
        this.formulaXmlService = formulaXmlService;
    }

    /**.
     * 导出xml
     * @param request req
     * @param response rep
     * @return object
     * @throws
     */
    @RequestMapping(value = "expFormula")
    @ResponseBody
    public void expFormula(HttpServletRequest request, HttpServletResponse response) {
        String appID = request.getParameter("appID");
        // 选择下载的
        String formulaIDs = request.getParameter("formulaIDs");
        try {
            getFormulaXmlService().expFormula(appID, formulaIDs, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value ="impXmlFile") 
    public void impXmlFile(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "files[]", required = false) MultipartFile[] files){
        try {
            getFormulaXmlService().impFormula(request, response, files);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
