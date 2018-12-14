/**
 * @Title: IFormulaXmlService.java
 * @Copyright (C) 2016 太极华青
 * @Description:公式导入导出
 * @Revision 1.0 2016-3-29  ZhengQ
 */
package commons.setting.formula.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tjhq.commons.exception.ServiceException;

/**
 * @ClassName: IFormulaXmlService
 * @Description: 公式导入导出xml接口
 * @author: ZhengQ 2016-3-29 上午11:17:52
 */

public interface IFormulaXmlService {
     /**.
      * 导出xml
     * @param appID 系统ID
     * @param formulaIDs 导出公式的IDS
     * @param response 重定向
     * @throws ServiceException 业务异常
     * @throws
     */
    void expFormula(String appID, String formulaIDs, HttpServletResponse response) throws Exception;
    
    void impFormula(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "files[]", required = false) MultipartFile[] files);
}
