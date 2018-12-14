/**
 * @Title: FormulaXmlService.java
 * @Copyright (C) 2016 太极华青
 * @Description:公式导入导出XML
 * @Revision 1.0 2016-3-29  ZhengQ
 */

package commons.setting.formula.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tjhq.commons.Constants;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.po.DictTModelcodePO;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.dict.external.service.IDictTModelcodeService;
import com.tjhq.commons.dict.external.service.impl.DictTFactorService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.setting.dataaudit.rule.po.ColPO;
import com.tjhq.commons.setting.formula.dao.FormulaDAO;
import com.tjhq.commons.setting.formula.dao.IFormulaXmlDAO;
import com.tjhq.commons.setting.formula.service.IFormulaService;
import com.tjhq.commons.setting.formula.service.IFormulaXmlService;
import com.tjhq.commons.setting.input.service.IFloatService;

/**
 * @ClassName: FormulaXmlService
 * @Description: 公式导入导出XML实现类
 * @author: ZhengQ 2016-3-29 上午11:18:13
 */
@Service
@Transactional(readOnly = true)
public class FormulaXmlService implements IFormulaXmlService {
    /**
     * @Fields log : 日志
     */
    Logger log = Logger.getLogger(FormulaXmlService.class);

    /**
     * @Fields formulaXmlDAO : 公式导入导出xml
     */
    @Resource
    private IFormulaXmlDAO formulaXmlDAO;
    @Resource
    private IFloatService floatService;
    /**
     * @Fields dictTFactorService : 列接口实现
     */
    @Resource
    private DictTFactorService dictTFactorService;
    @Resource
    private IDictTModelService dictTModelService;
    @Resource
    private IFormulaService formulaService;
    @Resource
    private FormulaDAO formulaMapper;

    /**
     * @return floatService
     */
    public IFloatService getFloatService() {
        return floatService;
    }

    /**
     * @param floatService 要设置的 floatService
     */
    public void setFloatService(IFloatService floatService) {
        this.floatService = floatService;
    }

    /**
     * @return dictTModelService
     */
    public IDictTModelService getDictTModelService() {
        return dictTModelService;
    }

    /**
     * @param dictTModelService 要设置的 dictTModelService
     */
    public void setDictTModelService(IDictTModelService dictTModelService) {
        this.dictTModelService = dictTModelService;
    }

    /**
     * @return formulaService
     */
    public IFormulaService getFormulaService() {
        return formulaService;
    }

    /**
     * @param formulaService 要设置的 formulaService
     */
    public void setFormulaService(IFormulaService formulaService) {
        this.formulaService = formulaService;
    }

    /**
     * @return formulaMapper
     */
    public FormulaDAO getFormulaMapper() {
        return formulaMapper;
    }

    /**
     * @param formulaMapper 要设置的 formulaMapper
     */
    public void setFormulaMapper(FormulaDAO formulaMapper) {
        this.formulaMapper = formulaMapper;
    }

    /**
     * @return formulaXmlDAO
     */
    public IFormulaXmlDAO getFormulaXmlDAO() {
        return formulaXmlDAO;
    }

    /**
     * @param formulaXmlDAO 要设置的 formulaXmlDAO
     */
    public void setFormulaXmlDAO(IFormulaXmlDAO formulaXmlDAO) {
        this.formulaXmlDAO = formulaXmlDAO;
    }

    /**
     * @return dictTFactorService
     */
    public DictTFactorService getDictTFactorService() {
        return dictTFactorService;
    }

    /**
     * @param dictTFactorService 要设置的 dictTFactorService
     */
    public void setDictTFactorService(DictTFactorService dictTFactorService) {
        this.dictTFactorService = dictTFactorService;
    }

    @Override
    public void expFormula(String appID, String formulaIDs, HttpServletResponse response) throws Exception {

        Map<String, Object> paraMap = new HashMap<String, Object>();
        XMLWriter writer = null;
        OutputStream os = null;
        try {
            if (null != formulaIDs || "".equals(formulaIDs)) {
                formulaIDs = "'" + formulaIDs.replaceAll(",", "','") + "'";
            }
            List<ColPO> colList = getAllFormulaCol();
            paraMap.put("appID", appID);
            paraMap.put("formulaIDs", formulaIDs);
            // 公式表数据
            List<Map<String, String>> formulaList = getFormulaXmlDAO().getAllFormulaCols(paraMap);
            // 创建写出对象
            Document doc = DocumentHelper.createDocument();
            Element rootEle = doc.addElement("FormulaCols");

            rootEle.addComment("公式 导出时间:" + getSysDate());
            Element sysEle = rootEle.addElement("SYSID");
            sysEle.setText(appID);

            for (Map<String, String> ele : formulaList) {
                appendXmpNode("FormulaCol", rootEle, ele, colList);

            }
            // 导出xml名字
            String strFileName = "公式定义数据集合.xml";
            strFileName = java.net.URLEncoder.encode(strFileName, "UTF-8");// 处理中文文件名的问题
            strFileName = new String(strFileName.getBytes("UTF-8"), "GBK");
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=" + strFileName);
            os = response.getOutputStream();
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            writer = new XMLWriter(os, format);
            writer.write(doc);

        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.ERR_00000, "导出xml出现异常！", false);
        } finally {
            writer.flush();
            writer.close();
        }
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void impFormula(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "files[]", required = false) MultipartFile[] files) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter localPrintWriter = null;
        JSONObject localJSONObject = new JSONObject();
        
        try {
            localPrintWriter = response.getWriter();
            HashMap<String, Object> paramMap = new HashMap<String, Object>();

            if (!ServletFileUpload.isMultipartContent(request)) {
                localJSONObject.put("success", false);
                localPrintWriter.print(localJSONObject.toString());
                localPrintWriter.close();
                return;
            }
            if (null == files || files.length == 0) {
                localJSONObject.put("success", false);
                localJSONObject.put("errorInfo", "上传文件集合和空");
                localPrintWriter.print(localJSONObject.toString());
                localPrintWriter.close();
                return;
            }
            SAXReader reader = new SAXReader();
            MultipartFile fileItem = files[0];
            String fileName = fileItem.getOriginalFilename();
            if (fileName.indexOf("\\") != -1) {
                fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
            }

            // 加入文件名单引号限制，防止引起内外网同步出错
            if (fileName.indexOf("'") != -1) {
                throw new Exception("上传的文件名称中不能包含单引号。");
            }

            Document document = reader.read(fileItem.getInputStream());
            Element root = document.getRootElement();
            impXmlData(paramMap, root);

            // 刷新列间公式
            localJSONObject.put("success", true);

            localPrintWriter.print(localJSONObject.toString());
        } catch (FileUploadBase.SizeLimitExceededException localSizeLimitExceededException) {
            localJSONObject.put("success", false);
            localJSONObject.put("errorInfo", "文件大小限制");
            localPrintWriter.print(localJSONObject.toString());
        } catch (ServiceException localException) {
            localException.printStackTrace();
            localJSONObject.put("success", false);
            localJSONObject.put("errorInfo", localException.getErrorMessage());
            localPrintWriter.print(localJSONObject.toString());
        } catch (Exception localException) {
            localException.printStackTrace();
            localJSONObject.put("success", false);
            localJSONObject.put("errorInfo", localException.getMessage());
            localPrintWriter.print(localJSONObject.toString());
        } finally {
            localPrintWriter.flush();
            localPrintWriter.close();
        }

    }
    /**
     * . 解析文件并写入数据库
     * @param paramMap
     * @param root
     * @throws ServiceException 业务异常
     * @throws
     */
    private void impXmlData(HashMap<String, Object> paramMap, Element root) throws ServiceException {
        //获取公式表的所有列
        List<ColPO> colList = getAllFormulaCol();
        List<Element> nodeList = root.elements("FormulaCol");
        for (Element ele : nodeList) {
            //解析xml
            Map<String, Object> formulaData = this.parseXmlData(ele, colList);
            //formulaData.put("APPID", paramMap.get("appID"));
            String formulaID = (String) formulaData.get("FORMULAID");
            String formulaType = (String) formulaData.get("FORMULATYPE");
            //校验公式的正确性
            verifyData(formulaData);
            // 判断是否存在改公式 如果存在就是update 否则就是insert
            int formulaCount = getFormulaXmlDAO().formulaCountByFormulaID(formulaID);
            if (formulaCount == 0) {
                getFormulaXmlDAO().insertFormulaByXml(formulaData);
            } else {
                getFormulaXmlDAO().updateFormulaByXml(formulaData);
            }
            // 单元格公式刷新表格权限
            if(formulaType != null && "1".equals(formulaType)){
               updateCellSecu(formulaData);
            }
            // 调用存储过程
            Map<String, String> map0 = new HashMap<String, String>();
            Map<String, String> map1 = new HashMap<String, String>();
            map0.put("tableId", "%");
            map1.put("tableId", (String) formulaData.get("TABLEID"));

            try {
                getFormulaMapper().callPDropVoidTriggerForParam(map0);
                getFormulaMapper().callPCreateTrigger8ForParam(map1);
                getFormulaXmlDAO().clearFormulaData();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException(ExceptionCode.FRU_00001, e.getCause().getMessage(), false);
            }
        }
    }

    /**.
     * 检验公式是否正确，包括该表在数据库中是否存在、列是否存在、公式能否校验通过
     * @param paraMap map
     * @return boolean
     * @throws ServiceException 业务异常
     * @throws
     */
    private boolean verifyData(Map<String, Object> paraMap) throws ServiceException {
        String tableID = (String) paraMap.get("TABLEID");
        // 判断是否存在表
        if (!isExistTable(tableID)) {
            throw new ServiceException(ExceptionCode.FRU_00001, "数据库内不存在该表，表ID=" + tableID, false);
        }
        checkColExists(paraMap);
        return true;
    }

    /**.判断是否存在表
     * @param tableID 表ID
     * @return boolean
     * @throws ServiceException 业务异常
     * @throws
     */
    private boolean isExistTable(String tableID) throws ServiceException {
        int n = getFormulaXmlDAO().tableCount(tableID);
        return n > 0 ? true : false;
    }

    /**
     * .修改浮动表 固定行列表权限
     * @param paramMap
     * @throws ServiceException 业务异常
     * @throws
     */
    private void updateCellSecu(Map<String, Object> paramMap) throws ServiceException {
        String tableID = (String) paramMap.get("TABLEID");
        String formulaCol = (String) paramMap.get("FORCOMCOL");
        String forWhere = (String) paramMap.get("FORWHERE");
        DictTModelPO tablePO = getDictTModelService().getDictTModelByID(tableID, false);
        String dbTableName = tablePO.getDbtablename();
        List<DictTFactorPO> colList = getDictTFactorService().getDictTFactorsByTableIdForTree(tableID);
        Map<String, String> dataMap = null;
        try {// 取出要修改权限的行
            dataMap = getFormulaXmlDAO().getFloatRowByTemplateid(dbTableName, forWhere);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.FRU_00001, e.getCause().getMessage(), false);
        }
        String cIndex = "";
        String dataKey = "";
        String cellSecu = "";
        if (dataMap != null) {
            dataKey = dataMap.get("DATAKEY");
            cellSecu = dataMap.get("CELLSECU");
        } else {
            throw new ServiceException(ExceptionCode.FRU_00001, "该表中不存在此条数据！", false);
        }
        // 获取该列在表中的index
        for (DictTFactorPO col : colList) {
            if (col.getDictTFactorList() != null && col.getDictTFactorList().size() > 0) {
                cIndex = getColumnIndex(col.getDictTFactorList(), formulaCol);
                if(cIndex!=null){
                    break;
                }
            } else if (formulaCol.equals(col.getDbcolumnname())) {
                cIndex = String.valueOf(col.getColumnindex());
                break;
            }
        }
        // 刷新权限 因为存在公式 所以权限值默认给0
        getFloatService().saveCellSecu(dataKey, cellSecu, cIndex, "0", tableID);
    }

    /**.
     * 获取该列在表中的index
     * @param cols 所有列
     * @param forCol 目标列
     * @return String
     * @throws
     */
    private String getColumnIndex(List<DictTFactorPO> cols, String forCol) {
        for (DictTFactorPO col : cols) {
            if (col.getDictTFactorList() != null && col.getDictTFactorList().size() > 0) {
                getColumnIndex(col.getDictTFactorList(), forCol);
            } else if (forCol.equals(col.getDbcolumnname())) {
                return String.valueOf(col.getColumnindex());
            }
        }
        return null;
    }

    /**
     * 验证列是否存在
     * @param paraMap
     * @throws ServiceException 业务异常
     */
    private void checkColExists(Map<String, Object> paraMap) throws ServiceException {

        String formulaType = (String) paraMap.get("FORMULATYPE");
        String formulaDefChi = (String) paraMap.get("FORMULADEFCHI");
        String tableID = (String) paraMap.get("TABLEID");
        String formulaCol = (String) paraMap.get("FORCOMCOL");
        String dealType = getDictTModelService().getDictTModelByID(tableID, false).getDealtype();

        formulaDefChi = formulaDefChi.trim();
        // 表内列公式 表内行公式 表间公式  校验公式列是否存在
        if (formulaType != null && !"A0".equals(formulaType)) {
            checkColExist(tableID, formulaCol);
        }
         //表间公式与列公式需要取“=”右侧的公式内容
        if (!"1".equals(formulaType) && !"A0".equals(formulaType)) {
            formulaDefChi = formulaDefChi.substring(formulaDefChi.indexOf("}=") + 2, formulaDefChi.length());
        }
        getFormulaService().covertAndVerify(formulaDefChi, formulaType, tableID, dealType);
    }

    /**.获取所有列
     * @param tableID
     * @return
     * @throws
     */
    private List<DictTFactorPO> getAllCols(String tableID) {
        List<DictTFactorPO> factorList = getDictTFactorService().getDictTFactorByTableidAndType(tableID, "1");
        return factorList;
    }

    /**
     * 验证列ID是否存在
     * @param lCompcol
     * @return
     * @throws ServiceException 业务异常
     */
    private void checkColExist(String tableID, String lCompcol) throws ServiceException {
        Map<String, DictTFactorPO> colMap = new HashMap<String, DictTFactorPO>();
        List<DictTFactorPO> factorList = getAllCols(tableID);
        for (DictTFactorPO colPO : factorList) {
            colMap.put(colPO.getDbcolumnname(), colPO);
        }
        if (!colMap.containsKey(lCompcol.trim())) {
            throw new ServiceException(ExceptionCode.FRU_00001, lCompcol + "列不存在", false);
        }
    }

    /**
     * 解析xml数据
     * @param root
     * @param colList
     */
    private Map<String, Object> parseXmlData(Element element, List<ColPO> colList) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        for (ColPO col : colList) {
            Element childNode = element.element(col.getColDbName());
            String v = childNode.getTextTrim();
            if (null != v && !"".equals(v)) {
                dataMap.put(col.getColDbName(), v);
            }
        }
        return dataMap;
    }

    /**
     * . 获取当前系统时间
     * @return String
     * @throws ServiceException 业务异常 
     * @throws
     */
    private String getSysDate() throws ServiceException {
        return getFormulaXmlDAO().getSysDate();
    }

    private List<ColPO> getAllFormulaCol() {
        // datatype 1 int 2 浮点数 3 引用,4 字符 5 clob
        List<ColPO> dataList = new ArrayList<ColPO>();
        dataList.add(new ColPO("公式ID", "FORMULAID", 4, false));
        dataList.add(new ColPO("公式名称", "FORMULANAME", 4, false));
        dataList.add(new ColPO("公式类型", "FORMULATYPE", 4, false));
        dataList.add(new ColPO("FORMULALVL", "FORMULALVL", 4, false));
        dataList.add(new ColPO("表ID", "TABLEID", 4, false));
        dataList.add(new ColPO("公式解析内容", "FORMULADEF", 5, true));
        dataList.add(new ColPO("公式中文内容", "FORMULADEFCHI", 5, true));
        dataList.add(new ColPO("公式英文内容", "FORMULADEFENG", 5, true));
        dataList.add(new ColPO("ISPUBLIC", "ISPUBLIC", 4, false));
        dataList.add(new ColPO("FORCOMCOL", "FORCOMCOL", 4, false));
        dataList.add(new ColPO("FORWHERE", "FORWHERE", 4, false));
        dataList.add(new ColPO("FORPARMCOL", "FORPARMCOL", 4, false));

        dataList.add(new ColPO("序号", "ORDERID", 1, false));
        dataList.add(new ColPO("相关列", "REFCOLUMN", 4, false));
        dataList.add(new ColPO("RELAID", "RELAID", 4, false));

        dataList.add(new ColPO("DEFTYPE", "DEFTYPE", 4, false));
        dataList.add(new ColPO("ISEDIT", "ISEDIT", 4, false));
        return dataList;

    }

    /**.
     * xml增加节点
     * @param elementName
     * @param rootEle
     * @param ele
     * @param colList
     * @throws
     */
    private void appendXmpNode(String elementName, Element rootEle, Map<String, String> ele, List<ColPO> colList) {
        Element auditRuleEle = rootEle.addElement(elementName);
        for (ColPO col : colList) {
            // if(col.isVirtual())continue;
            Element childNode = auditRuleEle.addElement(col.getColDbName());
            String v = ele.get(col.getColDbName());
            if (null != v && !"".equals(v)) {
                if (col.isNeedCDATA()) {
                    childNode.addCDATA(v);
                } else {
                    childNode.setText(v);
                }
            } else {
                childNode.setText("");
            }
        }
    }
}
