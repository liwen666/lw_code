/**
 * @Title: IBaseGridService.java
 * @Copyright (C) 2015 太极华青
 * @Description: 通用录入底层服务类
 * @Revision 6.0 2015-11-26  CAOK
 */

package commons.inputcomponent.commonweb.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.commonweb.service.ICommonwebService;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.jackson.ObjectMapper;
import com.tjhq.commons.setting.external.service.IFormulaOuterService;

/**
 * @ClassName: CommonwebController
 * @Description: 通用录入底层服务类
 * @author: CAOK 2015-11-26 上午11:08:02
 */

@Controller("commonwebController")
@RequestMapping(value = "/component/commonweb")
public class CommonwebController {

    /**
     * @Fields commonInputService : 数据录入通用服务
     */
    @Resource
    private ICommonwebService commonInputService;

    /**
     * @Fields formulaOuterService : 公式服务
     */
    @Resource
    private IFormulaOuterService formulaOuterService;

    /**
     * . 获取引用列表数据
     * @param columnID 引用列ID
     * @param superGuid 代码表数据父级ID
     * @param format 格式
     * @param params 参数
     * @return 数据对象
     */
    @SuppressWarnings("all")
    @RequestMapping(value = "ref")
    @ResponseBody
    public Object getTableFactorHead(@RequestParam(value = "columnId") String columnID,
                                     @RequestParam(value = "pId", required = false) String superGuid,
                                     @RequestParam(value = "format", required = false) String format,
                                     @RequestParam(value = "params", required = false) String params,
                                     @RequestParam(value = "row", required = false) String row) {
        Map<String, String> paramsMap = null;
        Map<String, Object> rowMap = null;
        ResultPO resultPO = null;

        try {
            if (StringUtils.isNotEmpty(params)) {
                paramsMap = (Map) (new ObjectMapper()).readJson(params, Map.class);
            }
            if (StringUtils.isNotEmpty(row)) {
                rowMap = (Map) (new ObjectMapper()).readJson(row, Map.class);
            }
            if (paramsMap == null) {
                paramsMap = new HashMap<String, String>();
            }
            
            if (rowMap != null) {
                for (Map.Entry<String, Object> entry : rowMap.entrySet()) {
                    if (entry.getValue() != null) {
                        paramsMap.put(entry.getKey(), String.valueOf(entry.getValue()));
                    } else {
                        paramsMap.put(entry.getKey(), "");
                    }
                }
            }
            
            resultPO = new ResultPO(commonInputService.getReferenceData(columnID, superGuid, paramsMap, format));
        } catch (ServiceException e) {
            e.printStackTrace();
            resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }

        return resultPO;
    }

    /**
     * . 查询级联引用代码表数据
     * @param tableID 表格ID
     * @param columnID 数据列ID
     * @param relatedID 条件列ID
     * @param value 条件列值
     * @param superGuid 代码表低级数据ID
     * @param format 数据格式
     * @param params 参数
     * @return 代码表数据
     */
    @SuppressWarnings("all")
    @RequestMapping(value = "assoc")
    @ResponseBody
    public Object getAssociationData(@RequestParam(value = "tableID") String tableID,
                                     @RequestParam(value = "columnId") String columnID,
                                     @RequestParam(value = "relatedId") String relatedID,
                                     @RequestParam(value = "value") String value,
                                     @RequestParam(value = "pId", required = false) String superGuid,
                                     @RequestParam(value = "format", required = false) String format,
                                     @RequestParam(value = "params", required = false) String params,
                                     @RequestParam(value = "row", required = false) String row) {
        Map<String, String> paramsMap = null;
        Map<String, Object> rowMap = null;
        ResultPO resultPO = null;

        try {
            if (StringUtils.isNotEmpty(params)) {
                paramsMap = (Map) (new ObjectMapper()).readJson(params, Map.class);
            }
            if (StringUtils.isNotEmpty(row)) {
                rowMap = (Map) (new ObjectMapper()).readJson(row, Map.class);
            }
            if (paramsMap == null) {
                paramsMap = new HashMap<String, String>();
            }
            
            if (rowMap != null) {
                for (Map.Entry<String, Object> entry : rowMap.entrySet()) {
                    if (entry.getValue() != null) {
                        paramsMap.put(entry.getKey(), String.valueOf(entry.getValue()));
                    } else {
                        paramsMap.put(entry.getKey(), "");
                    }
                }
            }
            resultPO = new ResultPO(commonInputService.getAssociationData(tableID, columnID, superGuid, relatedID,
                    value, paramsMap, format));
        } catch (ServiceException e) {
            e.printStackTrace();
            resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }

        return resultPO;
    }

    /**
     * . 公式计算
     * @param tableID 表ID
     * @param row 行数据
     * @return 计算结果
     */
    @RequestMapping(value = "calculateExp")
    @ResponseBody
    public Object calculateExp(@RequestParam(value = "tableID") String tableID,
                               @RequestParam(value = "row") String row) {
        ResultPO resultPO = null;
        try {
            resultPO = new ResultPO(formulaOuterService.oneTableFormula(row, tableID));
        } catch (Exception e) {
            e.printStackTrace();
            resultPO = new ResultPO(ExceptionCode.INP_00217, "公式计算错误 !");
        }

        return resultPO;
    }
    
    /**
     * . 批量公式计算
     * @param tableID 表ID
     * @param row 行数据
     * @return 计算结果
     */
    @SuppressWarnings("all")
    @RequestMapping(value = "batchCalculateExp")
    @ResponseBody
    public Object batchCalculateExp(@RequestParam(value = "tableID") String tableID,
                                    @RequestParam(value = "rows") String rows) {
        ResultPO resultPO = null;
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        JSONArray dataArray = null;
        try {
            dataArray = new JSONArray(rows);
            if (dataArray == null || dataArray.length() == 0) {
                resultPO = new ResultPO(resultList);
                return resultPO;
            }
            for (int i = 0; i < dataArray.length(); i++) {
                resultList.add(formulaOuterService.oneTableFormula(dataArray.getJSONObject(i).toString(), tableID));
            }

            resultPO = new ResultPO(resultList);
        } catch (Exception e) {
            e.printStackTrace();
            resultPO = new ResultPO(ExceptionCode.INP_00217, "公式计算错误 !");
        }

        return resultPO;
    }

    /**
     * . 单元格公式
     * @param tableID 表ID
     * @param formulaCell 公式单元格
     * @param refFormulaCell 公式引用的单元格
     * @param params 参数
     * @return 计算后的结果
     */
    @SuppressWarnings("all")
    @RequestMapping(value = "cellExp")
    @ResponseBody
    public Object calculateCellFormula(@RequestParam(value = "tableID") String tableID,
                                       @RequestParam(value = "formulaCell") String formulaCell,
                                       @RequestParam(value = "refFormulaCell") String refFormulaCell,
                                       @RequestParam(value = "params") String params) {
        List<String> refFormulaCellList = null;
        List<String> formulaCellList = null;
        Map<String, Object> paramMap = null;
        ResultPO resultPO = null;
        try {
            refFormulaCellList = (ArrayList) (new ObjectMapper()).readJson(refFormulaCell, ArrayList.class);
            formulaCellList = (ArrayList) (new ObjectMapper()).readJson(formulaCell, ArrayList.class);
            paramMap = (new ObjectMapper()).readJson(params, HashMap.class);
        } catch (ServiceException e) {
            e.printStackTrace();
            resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
            return resultPO;
        }

        if (refFormulaCellList == null || refFormulaCellList.size() == 0) {
            return null;
        }
        refFormulaCell = "";
        for (String value : refFormulaCellList) {
            refFormulaCell += "[" + value + "],";
        }
        refFormulaCell = refFormulaCell.substring(0, refFormulaCell.length() - 1);

        if (formulaCellList == null || formulaCellList.size() == 0) {
            return null;
        }
        formulaCell = "";
        for (String value : formulaCellList) {
            formulaCell += "[" + value + "],";
        }
        formulaCell = formulaCell.substring(0, formulaCell.length() - 1);
        try {
            resultPO = new ResultPO(formulaOuterService.calculateCellFormula(tableID, formulaCell, refFormulaCell,
                    paramMap));
        } catch (Exception e) {
            e.printStackTrace();
            resultPO = new ResultPO(ExceptionCode.INP_00218, "单元格公式计算错误 !");
        }
        return resultPO;
    }
    
    @RequestMapping(value = "getTableDefaultValue")
    @ResponseBody
    public Object getTableDefaultValue(String tableID) {
        ResultPO resultPO = null;
        try {
            resultPO = new ResultPO(commonInputService.getTableDefaultValue(tableID));
        } catch (ServiceException e) {
            e.printStackTrace();
            resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }
        
        return resultPO;
    }

}
