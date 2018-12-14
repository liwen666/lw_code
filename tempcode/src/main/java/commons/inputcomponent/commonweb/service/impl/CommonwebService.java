/**
 * @Title: CommonwebService.java
 * @Copyright (C) 2015 太极华青
 * @Description: 通用录入底层类
 * @Revision 6.0 2015-11-24  CAOK
 */

package commons.inputcomponent.commonweb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.longtu.demo.logger.Logger;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTFactorcodePO;
import com.tjhq.commons.dict.external.po.DictTModelcodePO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.dict.external.service.IDictTFactorcodeService;
import com.tjhq.commons.dict.external.service.IDictTModelcodeService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.commonweb.dao.ICommonwebDAO;
import com.tjhq.commons.inputcomponent.commonweb.service.ICommonwebService;
import com.tjhq.commons.inputcomponent.constants.Constants;
import com.tjhq.commons.inputcomponent.utils.ColumnUtil;
import com.tjhq.commons.inputcomponent.utils.dao.IUtilDAO;
import com.tjhq.commons.setting.external.po.DictTSetRefrelaPO;
import com.tjhq.commons.setting.external.service.IEntryOuterService;

/**
 * @ClassName: CommonwebService
 * @Description: 为通用录入提供服务
 * @author: CAOK 2015-11-24 下午04:33:57
 */

@Service("commonInputService")
@Transactional(readOnly = true)
public class CommonwebService implements ICommonwebService {

    /**
     * @Fields logger : 日志服务
     */
    private Logger logger = Logger.getLogger(CommonwebService.class);

    /**
     * @Fields dictTFactorService : 业务表查询列服务
     */
    @Resource
    private IDictTFactorService dictTFactorService;
    /**
     * @Fields dictTModelcodeService : 代码表查询服务
     */
    @Resource
    private IDictTModelcodeService dictTModelcodeService;
    /**
     * @Fields dictTFactorcodeService : 代码表列查询服务
     */
    @Resource
    private IDictTFactorcodeService dictTFactorcodeService;
    /**
     * @Fields entryOuterService : 通用录入设置服务
     */
    @Resource
    private IEntryOuterService entryOuterService;
    /**
     * @Fields commonwebMapper : 数据库服务
     */
    @Resource
    private ICommonwebDAO commonwebMapper;
    
    /**
     * @Fields utilMapper : 数据库服务
     */
    @Resource
    private IUtilDAO utilMapper;

    /**
     * @return dictTFactorService
     */
    public IDictTFactorService getDictTFactorService() {
        return dictTFactorService;
    }

    /**
     * @param dictTFactorService 要设置的 dictTFactorService
     */
    public void setDictTFactorService(IDictTFactorService dictTFactorService) {
        this.dictTFactorService = dictTFactorService;
    }

    /**
     * @return dictTModelcodeService
     */
    public IDictTModelcodeService getDictTModelcodeService() {
        return dictTModelcodeService;
    }

    /**
     * @param dictTModelcodeService 要设置的 dictTModelcodeService
     */
    public void setDictTModelcodeService(IDictTModelcodeService dictTModelcodeService) {
        this.dictTModelcodeService = dictTModelcodeService;
    }

    /**
     * @return dictTFactorcodeService
     */
    public IDictTFactorcodeService getDictTFactorcodeService() {
        return dictTFactorcodeService;
    }

    /**
     * @param dictTFactorcodeService 要设置的 dictTFactorcodeService
     */
    public void setDictTFactorcodeService(IDictTFactorcodeService dictTFactorcodeService) {
        this.dictTFactorcodeService = dictTFactorcodeService;
    }

    /**
     * @return entryOuterService
     */
    public IEntryOuterService getEntryOuterService() {
        return entryOuterService;
    }

    /**
     * @param entryOuterService 要设置的 entryOuterService
     */
    public void setEntryOuterService(IEntryOuterService entryOuterService) {
        this.entryOuterService = entryOuterService;
    }

    /**
     * @return commonwebMapper
     */
    public ICommonwebDAO getCommonwebMapper() {
        return commonwebMapper;
    }

    /**
     * @param commonwebMapper 要设置的 commonwebMapper
     */
    public void setCommonwebMapper(ICommonwebDAO commonwebMapper) {
        this.commonwebMapper = commonwebMapper;
    }

    /**
     * @return utilMapper
     */
    public IUtilDAO getUtilMapper() {
        return utilMapper;
    }

    /**
     * @param utilMapper 要设置的 utilMapper
     */
    public void setUtilMapper(IUtilDAO utilMapper) {
        this.utilMapper = utilMapper;
    }

    /**
     * .
     * <p>
     * Title: getAssociationData
     * </p>
     * <p>
     * Description: 加载级联引用列数据
     * </p>
     * @param tableID 表ID
     * @param columnID 数据列ID
     * @param superGUID 数据父级ID
     * @param relatedID 条件列ID
     * @param value 级联值
     * @param dynaParams 动态条件参数
     * @param format 前台显示格式，默认为ZTree
     * @return 引用结果集
     * @throws ServiceException 获取异常
     * @see com.tjhq.commons.inputcomponent.commonweb.service.ICommonwebService#getAssociationData
     *      (java.lang.String, java.lang.String, java.lang.String,
     *       java.lang.String, java.lang.String, java.util.Map, java.lang.String)
     */
    @Override
    public Object getAssociationData(String tableID, String columnID, String superGUID, String relatedID, String value,
            Map<String, String> dynaParams, String format) throws ServiceException {
        
        Map<String, Object> paramMap = new HashMap<String, Object>(); // 封装代码表查询参数
        List<Map<String, Object>> dataList = null; // 引用表数据
        paramMap.putAll(getModelCodeParam(columnID, superGUID, dynaParams));
        paramMap.put("relaTabs", getRelaQueryParam(tableID, columnID, relatedID, value));
       
        // 引用表数据
        try {
            dataList = commonwebMapper.queryRelData(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.INP_00210, "查询引用列数据异常!", false);
        }

        return transformRefColumn(dataList, format);
    }
    
    protected Map<String, Object> getModelCodeParam (String columnID, String superGuid, Map<String, String> dynaParams) throws ServiceException  {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        DictTFactorPO dictTFactorPO = null; // 当前列定义
        DictTModelcodePO dictTModelcodePO = null; // 当前列引用的代码表定义
        List<String> codeDbColumnlist = null; // 代码表列名称集合
        List<DictTFactorcodePO> dictTFactorcodePOList = null; // 代码表列定义
        String sqlWhere = null; // 查询条件
        
        dictTFactorPO = getDictTFactorService().getDictTFactorByColumnId(columnID);
        dictTModelcodePO = getDictTModelcodeService().getDictTModelcodePOByID(dictTFactorPO.getCsid());
        codeDbColumnlist = new ArrayList<String>();
        
        try {
            dictTFactorcodePOList = dictTFactorcodeService
                    .getDictTFactorcodePOsByTableId(dictTModelcodePO.getTableid());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.INP_00208, "加载列对应的引用表异常!", false);
        }
        
        // 设置WHERE查询条件
        if (dictTModelcodePO.getDynamicwhere() != null && dynaParams != null) {
            // WHERE SQL变量替换
            for (String key : dynaParams.keySet()) {
                if (dictTModelcodePO.getDynamicwhere().indexOf("$") == -1) {
                    break;
                }
                dictTModelcodePO.setDynamicwhere(dictTModelcodePO.getDynamicwhere().replaceAll("\\$" + key.toUpperCase() + "\\$",
                        dynaParams.get(key)));
            }
            sqlWhere = dictTModelcodePO.getDynamicwhere();
        }
        
        if (superGuid != null) {
            if (sqlWhere != null && sqlWhere.trim().length() > 0) {
                sqlWhere = " AND ";
            } else {
                sqlWhere = "";
            }
            sqlWhere += " SUPERGUID IN ('" + superGuid + "') ";
        }
        
        paramMap.put("sqlWhere", sqlWhere);
        
        if (dictTModelcodePO.getSqlcon() != null && dictTModelcodePO.getSqlcon().trim().length() > 0) {
            String querySql = dictTModelcodePO.getSqlcon();
            paramMap.put("tableName", " (" + querySql + " ) ");
            
            Pattern patternSelect = Pattern.compile("\\bselect\\b",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
            Matcher matcherSelect = patternSelect.matcher(querySql);
            
            Pattern patternFrom = Pattern.compile("\\bfrom\\b",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
            Matcher matcherFrom = patternFrom.matcher(querySql);
            
           if (!matcherSelect.find() || !matcherFrom.find()) {
               throw new ServiceException(ExceptionCode.INP_00208, "自定义sql错误!", false);
           }
           
           String [] fields = querySql.substring(matcherSelect.end(), matcherFrom.start()).split(",");
           for (String field : fields) {
               codeDbColumnlist.add(field);
           }
           
        } else {
            paramMap.put("tableName", dictTModelcodePO.getDbtablename()); // 代码表名称
            for (DictTFactorcodePO dictTFactorcodePO : dictTFactorcodePOList) {
                codeDbColumnlist.add(dictTFactorcodePO.getDbcolumnname());
            }
        }
        
        paramMap.put("fields", codeDbColumnlist);
        
        return paramMap;
    }
    
    protected List<Map<String, String>> getRelaQueryParam(String tableID, String columnID, String relatedIDs,
                                                          String values) throws ServiceException {

        if (relatedIDs == null || "".equals(relatedIDs) || values == null || "".equals(values)
                || relatedIDs.split(",").length != values.split(",").length) {
            return null;
        }

        List<Map<String, String>> paramList = new ArrayList<Map<String, String>>();

        List<DictTSetRefrelaPO> dictTSetRefrelaPOs = null; // 列级联引用定义

        try {
            // 获取级联引用关系定义
            dictTSetRefrelaPOs = getEntryOuterService().getDataRefrelaByColumn(tableID, columnID);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.INP_00214, "加载引用关系定义异常!", false);
        }

        if (dictTSetRefrelaPOs == null || dictTSetRefrelaPOs.size() == 0) {
            throw new ServiceException(ExceptionCode.INP_00215, "当前列没有级联关系定义!", false);
        }

        Map<String, String> paramMap = null;
        String [] relaArray = relatedIDs.split(",");
        for (DictTSetRefrelaPO po : dictTSetRefrelaPOs) {
            if (relatedIDs.contains(po.getCondColumnID())) { // 查找当前引用关系定义
                int index = 0;
                for (index = 0; index < relaArray.length; index++) {
                    if (po.getCondColumnID().equals(relaArray[index])) {
                        break;
                    }
                    
                }
                paramMap = new HashMap<String, String>();
                paramMap.put("refTableName", po.getRelaDbTab()); // 关系存放表名称
                paramMap.put("refValue", values.split(",")[index]); // 引用列值
                paramList.add(paramMap);
            }
        }

        if (paramList.size() != relatedIDs.split(",").length) {
            logger.error("columnID:" + columnID + ", relatedID:" + relatedIDs);
            throw new ServiceException(ExceptionCode.INP_00216, "没有找到对应的级联关系定义!", false);
        }

        return paramList;
    }
       
    

    @Override
    public Object getReferenceData(String columnID, String superGuid, Map<String, String> dynaParams, String format)
            throws ServiceException {

        Map<String, Object> paramMap = new HashMap<String, Object>(); // 封装代码表查询参数
        List<Map<String, Object>> dataList = null; // 引用表数据
        paramMap.putAll(getModelCodeParam(columnID, superGuid, dynaParams));
        
        // 引用表数据
        try {
            dataList = commonwebMapper.queryListData(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.INP_00210, "查询引用数据异常!", false);
        }
        return transformRefColumn(dataList, format);
    }

    /**.
     * 数据格式转换
     * @param refDataList 需要转换的数据
     * @param format 转换类型
     * @return 按格式返回数据
     * @throws ServiceException 业务异常
     */
    private Object transformRefColumn(List<Map<String, Object>> refDataList, String format) throws ServiceException {
        if (format == null || (format != null && format.equalsIgnoreCase("ztree"))) {
            return createRefColumnZTree(refDataList);
        } else if (format.equalsIgnoreCase("checkbox") || format.equalsIgnoreCase("radio")) {
            return createRefColumnNameValue(refDataList);
        } else {
            return "{}";
        }
    }

    /**.
     * 转换成ZTree格式
     * @param refDataList 需要转换的数据
     * @return 按格式返回数据
     * @throws
     */
    private Object createRefColumnZTree(List<Map<String, Object>> refDataList) {
        
        // CK 修改于2016-5-10， 修改内容：把代码表中所有列带到前台，解决绑定列问题
        List<Map<String, Object>> nodeList = new ArrayList<Map<String, Object>>();
        Map<String, Object> nodeMap = null;
        for (Map<String, Object> row : refDataList) {
            nodeMap = new HashMap<String, Object>();
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                if ("GUID".equals(entry.getKey())) {
                    nodeMap.put("id", entry.getValue());
                } else if ("NAME".equals(entry.getKey())) {
                    nodeMap.put("name", entry.getValue());
                    nodeMap.put("realname", entry.getValue());
                } else if ("CODE".equals(entry.getKey())) {
                    nodeMap.put("code", entry.getValue());
                } else if ("ISLEAF".equals(entry.getKey())) {
                    nodeMap.put("isleaf", entry.getValue());
                } else if ("SUPERGUID".equals(entry.getKey())) {
                    nodeMap.put("pId", entry.getValue());
                } else if (!nodeMap.containsKey(entry.getKey())
                        && !nodeMap.containsKey(entry.getKey().toLowerCase())) {
                    nodeMap.put(entry.getKey(), entry.getValue());
                }
            }
            nodeList.add(nodeMap);
        }
        
        return nodeList;
        
    }

    /**.
     * 转换成Check、Radio格式
     * @param refDataList 需要转换的数据
     * @return 按格式返回数据
     * @throws
     */
    private Object createRefColumnNameValue(List<Map<String, Object>> refDataList) {
        List<Map<String, String>> nameValues = new ArrayList<Map<String, String>>();
        Map<String, String> nameValue = null;
        for (Map<String, Object> row : refDataList) {
            nameValue = new HashMap<String, String>();
            nameValue.put("name", (String) row.get("NAME"));
            nameValue.put("value", (String) row.get("GUID"));
            nameValues.add(nameValue);
        }
        return nameValues;
    }

    @Override
    public List<Map<String, Object>> queryForList(String sql) throws ServiceException {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("querySql", sql);
        try {
            return commonwebMapper.queryForList(queryParams);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.INP_00210, "查询引用数据异常!", false);
        }
    }
    
    @Override
    public Map<String, Object> getTableDefaultValue(String tableID) throws ServiceException {
        List<DictTFactorPO> factorList = null;
        try {
            factorList = getDictTFactorService().getDictTFactorByTableidAndType(tableID, "1");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.INP_20100, "根据表ID和表类型获取列报错！", false);
        }
        return getTableDefaultValue(factorList);
    }
    
    /**
     * . 获取表的默认值
     * @param factorList List<DictTFactorPO>
     * @return Map<String, Object>
     * @throws ServiceException 业务异常
     */
    private Map<String, Object> getTableDefaultValue(List<DictTFactorPO> factorList) throws ServiceException {
        StringBuffer defaultValueSql = new StringBuffer();
        for (DictTFactorPO factorPO : factorList) {
            String defaltColumnValueSql = ColumnUtil.getDefaultValueSql(factorPO);
            if (StringUtils.isNotEmpty(defaltColumnValueSql)) {
                if (defaultValueSql.toString().length() > 0) {
                    defaultValueSql.append(" UNION ").append(defaltColumnValueSql);
                } else {
                    defaultValueSql.append(defaltColumnValueSql);
                }
            }
        }
        Map<String, Object> defaultValueMap = getDefaultValMapper(defaultValueSql.toString(), factorList);

        return defaultValueMap;
    }

    /**
     * . 取得默认值，并将其封装在一个MAP中，以COLUMNNAME为KEY
     * @param sql SQL
     * @param factorList List<DictTFactorPO>
     * @return Map<String, Object>
     * @throws ServiceException 业务异常
     */
    private Map<String, Object> getDefaultValMapper(String sql, List<DictTFactorPO> factorList)
            throws ServiceException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, DictTFactorPO> factorMap = new HashMap<String, DictTFactorPO>();
        for (DictTFactorPO factorPO : factorList) {
            factorMap.put(factorPO.getDbcolumnname(), factorPO);
        }
        
        if (StringUtils.isEmpty(sql.trim())) {
            return resultMap;
        }
        
        try {
            List<Map<String, Object>> list = getUtilMapper().queryForList(sql);
            for (Map<String, Object> map : list) {
                if (!factorMap.containsKey((String) map.get("COLUMNNAME"))) {
                    continue;
                }
                if (map.containsKey("DEFAULTVALUE") && map.get("DEFAULTVALUE") != null) {
                    resultMap.put((String) map.get("COLUMNNAME"), map.get("DEFAULTVALUE"));
                }
                if (factorMap.get((String) map.get("COLUMNNAME")).getCsid() != null
                        && factorMap.get((String) map.get("COLUMNNAME")).getCsid().length() > 0
                        && map.containsKey("DEFAULTSHOWVALUE") && map.get("DEFAULTSHOWVALUE") != null) {
                    resultMap.put(Constants.SNAME_START.concat((String) map.get("COLUMNNAME")), map
                            .get("DEFAULTSHOWVALUE"));
                }

            }
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.INP_20000, "执行查询SQL报错！", false);
        }

    }

}
