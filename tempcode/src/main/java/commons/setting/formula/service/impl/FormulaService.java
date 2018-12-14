package commons.setting.formula.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.Constants;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.po.DictTModelcodePO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.dict.external.service.IDictTModelcodeService;
import com.tjhq.commons.dict.service.impl.DictDBExecuteService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.constants.ShowType;
import com.tjhq.commons.inputcomponent.grid.commonGrid.service.impl.CommonGridService;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.PageInfo;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.utils.JSTypeUtils;
import com.tjhq.commons.setting.formula.dao.FormulaDAO;
import com.tjhq.commons.setting.formula.po.FormulaTFormulaDefPO;
import com.tjhq.commons.setting.formula.service.IFormulaService;
import com.tjhq.commons.setting.input.po.DictTSetFddefPO;
import com.tjhq.commons.setting.input.po.DictTSetFixPO;
import com.tjhq.commons.setting.input.po.TreeNode;
import com.tjhq.commons.setting.input.service.IEntryService;
import com.tjhq.commons.setting.input.service.IFloatService;
import com.tjhq.commons.setting.input.web.ConverTables;
import com.tjhq.commons.utils.StringUtil;

/**
 * @ClassName: FormulaService
 * @Description: Description of this class
 * @author: ZhengQ 2016-3-31 下午02:56:56
 */

@Service
@Transactional(readOnly = true)
public class FormulaService extends CommonGridService implements IFormulaService {
    @Resource
    private FormulaDAO formulaMapper;
    @Resource
    private IDictTFactorService dictTFactorService;
    @Resource
    private IDictTModelService dictTModelService;
    @Resource
    private IDictTModelcodeService dictTModelCodeService;
    @Resource
    private IFloatService floatService;
    @Resource
    private IEntryService entryService;
    @Resource
    private DictDBExecuteService dictDBExecuteService;


    /**.
     * <p>Title: getDefineHead</p>
     * <p>Description: 表定义</p>
     * @param tableID 表ID
     * @param defineID 公式类型
     * @return
     * @throws ServiceException
     * @see com.tjhq.commons.setting.formula.service.IFormulaService#getDefineHead(String, String)
     */
    public Table getDefineHead(String tableID, String defineID) throws ServiceException{
        Table grid = new Table();
        grid.setTableID(tableID);
        grid.setTableDBName(defineID);
        grid.setTableName(defineID);
        setColumns(grid,defineID);
        return grid;
    }
    /**.
     * 列定义
     * @param data
     * @param defineID
     * @throws
     */
    private void setColumns(Table data,String defineID) {

        List<Column> list = new ArrayList<Column>();

        Column orderIDCol = new Column("ORDERID", "公式序号", "公式序号", "ORDERID", JSTypeUtils.getJSDateType(DataType.STRING), 
                ShowType.SHOW_TYPE_HTML, 10, 0, 100, false, true, true, true);

        Column formulaNameCol = new Column("FORMULANAME", "公式名称", "公式名称", "FORMULANAME", JSTypeUtils.getJSDateType(DataType.STRING), 
                ShowType.SHOW_TYPE_HTML, 20, 0, 200, true, true, true, true);
        
        Column formulaDefChiCol = new Column("FORMULADEFCHI", "公式内容", "公式内容", "FORMULADEFCHI", JSTypeUtils.getJSDateType(DataType.STRING),
                ShowType.SHOW_TYPE_HTML, 60, 0, 500, true, true, true, true);

        Column formulaIDCol = new Column("FORMULAID", "公式ID", "公式ID", "FORMULAID", JSTypeUtils.getJSDateType(DataType.STRING),
                ShowType.SHOW_TYPE_HTML, 32, 0, 32, true, false, true, true);

        list.add(orderIDCol);//公式序号
        list.add(formulaNameCol);//公式名称
        
        if (null != defineID && !("").equals(defineID)) {
            if (!defineID.equals("A0")) {
                Column formulaColNameCol = new Column("FORMULACOLNAME", "公式列中文名称", "公式列中文名称", "FORMULACOLNAME", JSTypeUtils.getJSDateType(DataType.STRING),
                        ShowType.SHOW_TYPE_HTML, 60, 0, 200, true, true, true, true);// 公式列中文名

                Column formulaCol = new Column("FORCOMCOL", "公式列名", "公式列名", "FORCOMCOL", JSTypeUtils.getJSDateType(DataType.STRING),
                        ShowType.SHOW_TYPE_HTML, 60, 0, 200, true, true, true, true);// 公式列数据库名称
                
                list.add(formulaColNameCol);//公式列中文名
                list.add(formulaCol);//公式列名
            
            }
            if (defineID.equals("0")) {
                Column ISEDIT = new Column("ISEDIT", "公式列是否可编辑", "公式列是否可编辑", "ISEDIT", JSTypeUtils.getJSDateType(DataType.STRING),
                        ShowType.SHOW_TYPE_HTML, 60, 0, 100, true, true, true, true);// 公式列数据库名称
                
                list.add(ISEDIT);//公式列是否可编辑
            }
        }
        list.add(formulaDefChiCol);//公式内容
        list.add(formulaIDCol);

        data.setColumnList(list);
    }

    private static String format = "TT(%s).〖%s〗{『%s』}";
    private static String in_format = "TT(%s).〖%s〗{%s}";
    //private static String cell_format = "〖FDCODE='%s'〗{%s}";
    //private static String order_format = "〖ORDERID='%s'〗{%s}";
    private static String template_format = "〖TEMPLATEID='%s'〗{%s}";

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public String saveFormulaData(FormulaTFormulaDefPO formula, String operator) throws ServiceException {
        String flag = "保存失败！";
        // 处理 表达式 转化为 tableId,dbColumnName
        String formulaDefChi = specialChar(formula.getFormulaDefChi().trim());
        String tableID = formula.getTableID();
        String tableName = formula.getTableName();
        String forComcol = formula.getForComcol();
        String forWhere = formula.getForWhere();
        String formulaType = formula.getFormulaType();
        String forComcolName = formula.getForComcolName();
        String dealType = formula.getDealType();
        String leftComDef = "";
        String leftComDefChi = "";
        String leftComDefEng = "";
        //表间公式与取数列公式 把条件中@aaa@转为@AAA@   160601修改
        if("A8".equals(formulaType) || "8".equals(formulaType)){
            String where = formulaDefChi.substring(
                        formulaDefChi.indexOf(Constants.WHERE_START) + 1, formulaDefChi
                                .indexOf(Constants.WHERE_END));
           if(!where.isEmpty()){
               if(where.contains(Constants.COLUMN_AT)){
                   formulaDefChi =formulaDefChi.replace(where, "%s");
                   String dbcolumnname = where.substring(where.indexOf(Constants.COLUMN_AT)+1, where.lastIndexOf(Constants.COLUMN_AT));
                   where = where.replace(dbcolumnname, dbcolumnname.toUpperCase());
                   formulaDefChi =formulaDefChi.replace("%s",where);
               }
           }
        }
        //表内行公式 表内列公式  表间公式 0,1,8，A8
            if (null != formulaDefChi && !("").equals(formulaDefChi)) {
               //公式校验与转换
              Map<String, String> reMap =this.covertAndVerify(formulaDefChi, formulaType, tableID, dealType);
                // 分别保存 到 formulaDef | formulaDefEnd
             if (!formulaType.equals("A0")) {  
              String defEndCovert = reMap.get("covertR"); // 存在列标识符 『』
                String defCovert = reMap.get("covert").replaceAll(Constants.COLUMN_START, "").replaceAll(Constants.COLUMN_END, ""); // 不存在列标识符 『』

                // 向数据库中 存储 三列
                leftComDef = String.format(in_format, tableID, "", forComcol) + "="; // 物理 格式
                leftComDefChi = String.format(format, tableName, "", forComcolName) + "=";// 中文 格式
                leftComDefEng = String.format(format, tableID, "", forComcol) + "=";// 英文 格式

                if (formulaType.equals("0")) {
                    defCovert = String.format(in_format, tableID, "", defCovert); // 右侧

                }
                if (!formulaType.equals("1")) { // -----
                    formula.setFormulaDef(leftComDef + defCovert);
                    formula.setFormulaDefChi(leftComDefChi + formulaDefChi);
                    formula.setFormulaDefEng(leftComDefEng + defEndCovert);
                } else { // 表内行公式
                    String leftCell = "";
                    if (ConverTables.isNotNull(forWhere)) {
                        /**
                         * 2014-1-19 浮动表FDCODE 固定行列表ORDERID 修改为 TEMPLATEID dealType.equals("A2")? cell_format : order_format
                         */
                        String formate = template_format;
                        leftCell = String.format(formate, forWhere, forComcol) + "=";
                    }

                    formula.setFormulaDef(leftCell + defCovert);
                    formula.setFormulaDefChi(formulaDefChi);
                    formula.setFormulaDefEng(defEndCovert);
                }
            }else{
                formula.setFormulaDef(formulaDefChi);
            }
            }
        if (operator.equals("verify")){
            flag = "校验成功！";
            return flag;
        }
        //保存公式
        Integer is_insert = 0;
        
            // ---------- INSERT 根据公式列 判断是否存在公式
            if (formulaType.equals("0") || formulaType.equals("1")
                    || formulaType.equals("8") || formulaType.equals("A8")) {

                if (formulaMapper.formulaExist(formula) > 0) {
                    throw new ServiceException(ExceptionCode.FRU_00001,"所选公式列：存在公式数据！",false);
                }
            }
            //判断表中needupdate字段长度
        if ((formulaType.equals("8") || formulaType.equals("A8")) && operator.equals("insert")) {
            Map<String, String> needlenmap = new HashMap<String, String>();
            needlenmap.put("tableId", formula.getTableID());
            needlenmap.put("len", "");
            this.formulaMapper.get_Needupdate_Length(needlenmap);
            // needupdate字段长度
            if (needlenmap.get("len") != null) {
                int len = Integer.parseInt(needlenmap.get("len")) + (","+formula.getForComcol() + ",8,").length();
                if (len > 4000) {
                    throw new ServiceException(ExceptionCode.FRU_00002, "表间公式needupdate字段已超过4000，无法保存！", false);
                }
            }
        }
           try {
            if (operator.equals("insert")){
                is_insert = formulaMapper.insertFormulaData(formula); // 插入
            }
            if (operator.equals("update"))
                is_insert = formulaMapper.updateFormulaData(formula); // 更新
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.FRU_00001,"执行公式触发器错误！"+e.getCause().getMessage(),false);
        }
        flag = callTrigger(operator, formula, is_insert);
        return flag;
    }
    
    public String covertFormulaSql(String covert, String tableID,
            String formulaType) throws ServiceException {
        String sql = "";
        covert = covert.replace(Constants.COLUMN_START, "").replace(
                Constants.COLUMN_END, "");
        try {
            //校验是否有字符列 使用下面的covertFormulaSqlTO0X（）方法，此处代码 暂时不删
            if (formulaType.equals("0")) { // 表内公式
                String dbTableName = this.getDbTableName(tableID);
                sql = "SELECT " + covert + " FROM " + dbTableName
                        + " WHERE ROWNUM<2";
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.FRU_00001,"公式内容无法解析，请检查！"+e.getCause().getMessage(),false);
        }
            // 表间公式 | 取数列公式 | 表内行公式
            if (formulaType.equals("8") || formulaType.equals("A8")
                    || formulaType.equals("1")) {
                sql = this.combinationSql(covert, tableID, formulaType);
            }
        
        return sql;
    }
    //新增专门校验字符与数字160428 例如『数字3』+『字符』+1000
       private String covertFormulaSqlTO0X(String covert, String tableID,
                String formulaType) throws ServiceException {
            String sql = "";
            Integer colType = 0;
            try {
                // 表内公式
                    String colAndContans = "";
                    String col = "";
                    while(covert.indexOf(Constants.COLUMN_START)!=-1 && covert.indexOf(Constants.COLUMN_END)!=-1){
                        colAndContans = covert.substring(covert.indexOf(Constants.COLUMN_START), covert.indexOf(Constants.COLUMN_END)+1);
                        col = colAndContans.replace(Constants.COLUMN_START, "").replace(Constants.COLUMN_END, "");
                        colType =   getColumnType(col,tableID);
                        //数字类型
                        if(colType == 1 || colType == 2){
                            covert = covert.replace(colAndContans, " 1 ");
                        }else{
                            covert = covert.replace(colAndContans, " \'X\' ");
                        }
                        covert = covert.replace(colAndContans, "["+col+"]");
                    }
                    String dbTableName = this.getDbTableName(tableID);
                    covert = covert.replace("[", "").replace("]", "");
                    sql = "SELECT " + covert + " FROM " + dbTableName
                            + " WHERE ROWNUM<2";
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException(ExceptionCode.FRU_00001,"公式内容无法解析，请检查！"+e.getCause().getMessage(),false);
            }
            return sql;
        }
    @Override
    public Map<String, String> covertAndVerify(String formulaDefChi, String formulaType, String tableID, String dealType)
            throws ServiceException {
        Map<String, String> reMap = new HashMap<String, String>();
        String sql = "";
        //复杂取数公式
        if ("A0".equals(formulaType)) {
                String complex = formulaDefChi;
                Map<String, String> tempMap = new HashMap<String, String>();
                Pattern p = Pattern.compile("@(.*?)@", Pattern.CASE_INSENSITIVE); // 过滤@...@之间的内容
                Matcher m = p.matcher(complex);
                while (m.find()) {
                    String offset = m.group();
                    if (tempMap.containsKey(offset)) {
                        continue;
                    }
                    tempMap.put(offset, null);
                    complex = complex.replaceAll(offset, "'" + offset + "'"); // 替换@...@之间的内容
                }
                callErrorMessage(complex); // 验证SQL
        } else {
            String covert = this.dealFormula(formulaDefChi, formulaType, tableID, dealType);// 根据列校验
            String covertR = this.dealRfTableCol(formulaType, tableID, dealType, formulaDefChi);// 根据列校验
             
            //新增专门校验字符列不能设置列公式 
            if (formulaType.equals("0")) {
                sql = this.covertFormulaSqlTO0X(covert, tableID, formulaType);
                if (!sql.equals("")) {
                        sql = "select  pkg_formula.F_CHECKFORMULA(Q'{SELECT (" + sql + ") TT FROM DUAL}') from dual";
                    // 校验是否是数字类型
                    String resultStr = formulaMapper.verifyFormula(sql);
                    if (!"".equals(resultStr) && null != resultStr) {
                        throw new ServiceException(ExceptionCode.FRU_00001, "公式中存在字符列", false);
                    }
                }
            }
            // 根据表校验 解析SQL
            sql = this.covertFormulaSql(covert, tableID, formulaType);
            if (!sql.equals("")) {
                // 进行数据库校验
                try {
                    sql = "select  pkg_formula.F_CHECKFORMULA(Q'{SELECT (" + sql + ") TT FROM DUAL}') from dual";
                    this.dictDBExecuteService.ExecDllLongForParam(sql);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new ServiceException(ExceptionCode.FRU_00001, "公式内容无法解析，请检查！"+e.getCause().getMessage(), false);
                }
                // 校验是否是数字类型
                String resultStr = formulaMapper.verifyFormula(sql);
                if (!"".equals(resultStr) && null != resultStr) {
                    throw new ServiceException(ExceptionCode.FRU_00001, resultStr, false);
                }
            }
            reMap.put("covert", covert);
            reMap.put("covertR", covertR);
        }
        return reMap;
    }

    /**
     * 将业务表中的字段进行sql转换   （不用，废代码，但是不删）
     */
    public String convertLeftFormula(String covert, String tableId) throws ServiceException{
            while (covert.indexOf(Constants.COLUMN_START) != -1
                    && covert.indexOf(Constants.COLUMN_END) != -1) {
                covert = covert.replace(Constants.COLUMN_START, " ").replace(
                        Constants.COLUMN_END, " ");
            }
        return covert;
    }

    /**
     * 组合SQL 语句
     * 
     * @param covert
     *            转换后的SQL语句
     * @param tableID
     * @param formulaType
     *            公式类型
     * @return
     */
    public String combinationSql(String covert, String tableID,
            String formulaType) throws ServiceException{
        String start = "";
        String tableDbName = "DUAL";
        String ttTableID = "";//TT表ID
        if (formulaType.equals("8") || formulaType.equals("A8"))
            start = Constants.TT_START;
        else
            start = Constants.WHERE_START;
        while (covert.indexOf(start) != -1
                && covert.indexOf(Constants.COMCOL_END, covert.indexOf(start)) != -1) {
            String temp = covert.substring(covert.indexOf(start), covert
                    .indexOf(Constants.COMCOL_END) + 1);
            covert = covert.replace(temp, "%s");

            if (formulaType.equals("8") || formulaType.equals("A8")) {// 获取
                // TT表的tableID
                ttTableID = temp.substring(temp.indexOf(Constants.TT_START) + 3,
                        temp.indexOf(Constants.TT_END));
                tableDbName = this.getDbTableName(ttTableID); // 根据TableID 获取表名
            }else{
              tableDbName = this.getDbTableName(tableID); // 根据TableID 获取表名
            }
              // 条件
            String where = temp.substring(
                    temp.indexOf(Constants.WHERE_START) + 1, temp
                            .indexOf(Constants.WHERE_END));
            if (formulaType.equals("8") || formulaType.equals("A8")) {
                if(where.contains("@")){
                // 处理条件 @参数@ 取出参数 转换成该条件的类型
                if(!"".equals(where)){
                String dbcolumnname = where.substring(where.indexOf(Constants.COLUMN_AT)+1, where.lastIndexOf(Constants.COLUMN_AT));
                DictTFactorPO po = dictTFactorService
                        .getDictTFactorByDBColumnName(dbcolumnname, tableID);
                if(po == null){
                    throw new ServiceException(ExceptionCode.FRU_00001,"表"+this.getDbTableName(tableID)+"中无 "+dbcolumnname+" 列",false);
                }
                if (1 == po.getDatatype() || 2 == po.getDatatype()) {
                    where = where.replaceAll("@" + dbcolumnname + "@", "" + 0);
                } else {
                    where = where.replaceAll("@" + dbcolumnname + "@", "''");
                }
                }
                }
            }
            // 列
            String column = temp.substring(
                    temp.indexOf(Constants.COMCOL_START) + 1, temp
                            .indexOf(Constants.COMCOL_END));
            String sql = "SELECT " + column + " FROM " + tableDbName
                    + " WHERE ROWNUM =1 ";

            if (ConverTables.isNotNull(where)) {

                sql = sql + " AND " + where;
            }
            // 验证SQL
            Integer value = null;
            try {
                value = verifyFormulaSql(sql);
            } catch (Exception e) {
                throw new ServiceException(ExceptionCode.FRU_00001, e.getMessage(),false);
            }
            //covert = String.format(covert, value);
            covert = covert.replaceAll("%s", ""+value);
        }
        return "SELECT " + covert + " FROM DUAL";
    }

    // 根据SQL 验证公式是否正确
    public Integer verifyFormulaSql(String sql) throws ServiceException {
        Integer value = null;
        if (!sql.equals("")) { // 根据解析SQL 进行数据库校验
            try {
                sql = "SELECT NVL((" + sql + "), 1) FROM DUAL";
                value = formulaMapper.verifyFormulaDef(sql);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException(ExceptionCode.FRU_00001,"无效数字，只有数值类型的列才能进行计算！",false);
            }
        }
        return value;
    }

    /**
     * 处理 公式内容
     * 
     * @param formulaDefChi
     *            公式内容
     * @param formulaType
     *            公式类型
     * @param oneTableID
     *            表内公式 单表ID
     * @return
     */
    //String dataFormula = ""; // 存储SQLCovert

    public String dealFormula(String formulaDefChi, String formulaType,
            String oneTableID, String dealType) throws ServiceException{
            // 处理引用表中条件数据 RF(支出功能科目).{『行政运行』}
            // 根据 公式中的 引用表个数，进行 循环判断
            while (formulaDefChi.indexOf(Constants.RF_START) != -1
                    && formulaDefChi.indexOf(Constants.COMCOL_END,
                            formulaDefChi.indexOf(Constants.RF_START)) != -1) {
                String temp = formulaDefChi.substring(formulaDefChi
                        .indexOf(Constants.RF_START), formulaDefChi.indexOf(
                        Constants.COMCOL_END, formulaDefChi
                                .indexOf(Constants.RF_START)) + 1);
                formulaDefChi = formulaDefChi.replace(temp, "%s");
                String covert = this.dealTableColumn(temp, oneTableID);
                if (!covert.contains("error")) {
                    formulaDefChi = formulaDefChi.replace("%s", covert);
                } 
            }
            if (formulaType.equals("8") || formulaType.equals("A8")) {
                // 根据 公式中的 表个数，进行 循环判断
                while (formulaDefChi.indexOf(Constants.TT_START) != -1
                        && formulaDefChi.indexOf(Constants.COMCOL_END,
                                formulaDefChi.indexOf(Constants.TT_START)) != -1) {
                    String temp = formulaDefChi.substring(formulaDefChi
                            .indexOf(Constants.TT_START), formulaDefChi
                            .indexOf(Constants.COMCOL_END) + 1);
                    formulaDefChi = formulaDefChi.replace(temp, "%s");
                    //校验公式格式
                    this.verifyFormat(temp);
                    
                        String covert = this.dealTableColumn(temp, oneTableID);
                        if (!covert.contains("error")) {
                            formulaDefChi = formulaDefChi.replace("%s", covert);
                        } 
                }
            }
            if (formulaType.equals("0")) {
                formulaDefChi = this.dealTableColumn(formulaDefChi, oneTableID);
            }
            if (formulaType.equals("1")) {
                formulaDefChi = this.dealRowTable(formulaDefChi, oneTableID,
                        dealType, "save");
            }
            // 替换表 格式
            formulaDefChi = replaceString(formulaDefChi);
        System.out.println("解析后的公式内容：" + formulaDefChi);

        return formulaDefChi;
    }

    /**. 替换公式字符    将中文字符转为英文字符
     * @param formulaDefChi 中文公式内容
     * @return String
     * @throws
     */
    public String replaceString(String formulaDefChi) {

        return formulaDefChi.replaceAll("〔", Constants.TT_START).replaceAll(
                "〕", Constants.TT_END).replaceAll("｛", Constants.COMCOL_START).replaceAll("｝", Constants.COMCOL_END)
                .replaceAll("［",Constants.WHERE_START).replaceAll("］",Constants.WHERE_END)
                .replaceAll("「",Constants.COLUMN_START).replaceAll("」",Constants.COLUMN_END);
    }
    /**
     * 引用列处理
     */
    private String dealRfTableCol(String formulaType,String oneTableID, String dealType,String formulaEnglish)throws ServiceException {
    
        // 处理引用表中条件数据 RF(支出功能科目).{『行政运行』}
        // 根据 公式中的 引用表个数，进行 循环判断
    try{while (formulaEnglish.indexOf(Constants.RF_START) != -1
                && formulaEnglish.indexOf(Constants.COMCOL_END,
                        formulaEnglish.indexOf(Constants.RF_START)) != -1) {
            String tem = formulaEnglish.substring(formulaEnglish
                    .indexOf(Constants.RF_START), formulaEnglish.indexOf(
                    Constants.COMCOL_END, formulaEnglish
                            .indexOf(Constants.RF_START)) + 1);
            formulaEnglish = formulaEnglish.replace(tem, "%s");
            String covert=this.dealRfTableColR(oneTableID,tem);
            formulaEnglish = formulaEnglish.replace("%s", covert);
        }
        if (formulaType.equals("8") || formulaType.equals("A8")) {
            // 根据 公式中的 表个数，进行 循环判断
            while (formulaEnglish.indexOf(Constants.TT_START) != -1
                    && formulaEnglish.indexOf(Constants.COMCOL_END,
                            formulaEnglish.indexOf(Constants.TT_START)) != -1) {
                String temp = formulaEnglish.substring(formulaEnglish
                        .indexOf(Constants.TT_START), formulaEnglish
                        .indexOf(Constants.COMCOL_END) + 1);
                formulaEnglish = formulaEnglish.replace(temp, "%s");
                //校验公式格式
                this.verifyFormat(temp);
                    String covert = this.dealTableColumn(temp, oneTableID);
                    if (!covert.contains("error")) {
                        formulaEnglish = formulaEnglish.replace("%s", covert);
                    } else {
                        return covert;
                    }
            }
        }
        if (formulaType.equals("0")) {
            formulaEnglish = this.dealTableColumn(formulaEnglish, oneTableID);
        }
        if (formulaType.equals("1")) {
            formulaEnglish = this.dealRowTable(formulaEnglish, oneTableID,
                    dealType, "save");
        }
        // 替换表 格式
        formulaEnglish = replaceString(formulaEnglish);
    } catch (Exception e) {
        e.printStackTrace();
        throw new ServiceException(ExceptionCode.FRU_00001,e.getMessage(),false);
    }
        formulaEnglish = formulaEnglish.replaceAll("RFW", "RF");
        formulaEnglish = formulaEnglish.replaceAll("【", "『").replaceAll("】",
                "』");
        return formulaEnglish;
    }
    //处理formulaEng
    private String dealRfTableColR(String oneTableID,String formulaEnglish)throws ServiceException {
        Map<String, String> fMap = parseFormula(formulaEnglish, oneTableID,"eng");
        String formula = fMap.get("formula");
        String rfTableID = fMap.get("rfTableID");
        
        formula = "RFW(" + rfTableID + ").{" + formula + "}";
        return formula.replace(Constants.COMCOL_END, "｝"); // 将表达式标识符 替换
    }

    /**
     * 表间公式 、取数列公式
     * 
     * @param formulaDefChi
     * @param oneTableID
     * @return
     * @throws Exception
     */
    public String dealTableColumn(String formulaDefChi, String oneTableID)
            throws ServiceException {
        String formula = parseFormula(formulaDefChi, oneTableID,"chi").get("formula");
        return formula.replace(Constants.COMCOL_END, "｝"); // 将表达式标识符 替换
    }

    /**解析公式里面所有内容
     * @param formula
     * @param oneTableID
     * @return
     * @throws ServiceException
     * @throws
     */
    private Map<String, String> parseFormula(String formula, String oneTableID,String flag) throws ServiceException {
        String tableID="";
        String csDbTableName = ""; // 引用代码表 物理表名
        String csTableName = ""; // 中文 表名
        String rfTableID="";//引用代码表ID
        Map<String, String> resMap = new HashMap<String, String>();
        // 处理表
        while (formula.indexOf(Constants.TT_START) != -1
                && formula.indexOf(Constants.TT_END, formula.indexOf(Constants.TT_START)) != -1) {
            String temp1 = formula
                    .substring(formula.indexOf(Constants.TT_START), formula.indexOf(Constants.TT_END) + 2);
            // 从数据库中查询 获取tableId
            String tableName = temp1.replace(Constants.TT_START, "").replace(Constants.TT_END, "");
            List<DictTModelPO> model = dictTModelService.getDictTModelsByName(tableName);
            if (model != null && model.size() > 0) {
                tableID = model.get(0).getTableid(); // 表ID
                formula = formula.replace(temp1, "〔" + tableID + "〕");
            } else {
                throw new ServiceException(ExceptionCode.FRU_00001, tableName + " 错误，无法从数据库中解析！", false);
            }
        }
        // 处理引用表
        while (formula.indexOf(Constants.RF_START) != -1
                && formula.indexOf(Constants.RF_END, formula.indexOf(Constants.RF_START)) != -1) {
            String temp2 = formula
                    .substring(formula.indexOf(Constants.RF_START), formula.indexOf(Constants.RF_END) + 2);
            // 从数据库中查询 获取tableId
            csTableName = temp2.replace(Constants.RF_START, "").replace(Constants.RF_END, "");
            List<DictTModelcodePO> model = dictTModelCodeService.getDictTModelcodePOByName(csTableName);
            if (model != null && model.size() > 0) {
                csDbTableName = model.get(0).getDbtablename(); // 表ID
                rfTableID=model.get(0).getTableid();
                formula = formula.replace(temp2, "").replace(Constants.COMCOL_START, "").replace(Constants.COMCOL_END,
                        "");
            } else {
                throw new ServiceException(ExceptionCode.FRU_00001, "引用表名：" + csTableName + " 错误，无法从数据库中解析！", false);
            }
        }
        List<TreeNode> nodes = new ArrayList<TreeNode>();
       if (ConverTables.isNotNull(csDbTableName)) {
           nodes = this.getModelCodeDataByCsid(csDbTableName);
        }
        // 处理列
        while (formula.indexOf(Constants.COLUMN_START) != -1
                && formula.indexOf(Constants.COLUMN_END, formula.indexOf(Constants.COLUMN_START)) != -1) {
            String temp3 = formula.substring(formula.indexOf(Constants.COLUMN_START), formula
                    .indexOf(Constants.COLUMN_END) + 1);
            // 从数据库中查询 获取dbColumnName
            String codeColName = temp3.replace(Constants.COLUMN_START, "").replace(Constants.COLUMN_END, "");

            if (ConverTables.isNotNull(csDbTableName)) { // 引用代码表 列处理
                    String guID = ""; // 代码表GUID
                    for (TreeNode node : nodes) {
                        String nodeName = node.getName();
                        String nodeCode = node.getCode();
                        String codeName = "[" + nodeCode + "]" + codeColName;
                        // 寻找代码表中的列id 为了兼容已经定义过的公式，有一下两个判断
                        // 1:根据[01]研究生 这种带有code的名称列进行查找 2:之前定义的列名称加上code
                        if (nodeName.equals(codeColName) || nodeName.equals(codeName)) {
                            guID = node.getId();
                            
                            if("chi".equals(flag)){
                                formula = formula.replace(temp3, "'" + guID // 'RF(TABLEID).{『CODEID1』}'
                                        + "'");
                            }else{
                                formula = formula.replace(temp3, "【" + guID // 'RF(TABLEID).{『CODEID1』}'
                                        + "】");
                            }
                            break;
                        }
                    }
                    if (StringUtil.isEmpty(guID)) {
                        throw new ServiceException(ExceptionCode.FRU_00001, "引用表：" + csTableName + "的【 " + codeColName
                                + "】列无法从数据库解析！", false);
                    }

            } else { // 列 处理
                if (tableID.equals(""))
                    tableID = oneTableID;// 表内公式
                List<DictTFactorPO> list = dictTFactorService.getDictTFactorByName(tableID, codeColName);
                if (list != null && list.size() > 0) {
                    for (DictTFactorPO po : list) {
                        if ("1".equals(po.getIsleaf())) {
                            formula = formula.replace(temp3, "「" + po.getDbcolumnname() + "」");
                        } else {
                            throw new ServiceException(ExceptionCode.FRU_00001, "不存在 『" + codeColName + "』 列,该列或为标题列！",
                                    false);
                        }
                    }

                } else {
                    throw new ServiceException(ExceptionCode.FRU_00001, "列名 『" + codeColName + "』 错误，无法从数据库中解析！", false);
                }
            }
        }
        resMap.put("formula", formula);
        resMap.put("rfTableID", rfTableID);
        return resMap;
}
    /**
     * 表内行公式 、单元格公式
     * 
     * @param formulaDefChi
     *            公式内容
     * @param oneTableID
     *            单表
     * @param convertType
     *            两种方式：1、save 用于存储数据库 转为物理列名 2、show 用于前台显示 转换为中文名
     * @param dealType
     *            处理类型 A2 | A1
     * @return
     * @throws Exception 
     */
    public String dealRowTable(String formulaDefChi, String oneTableID,
            String dealType, String convertType) throws ServiceException {

        while (formulaDefChi.indexOf(Constants.COMCOL_START) != -1
                && formulaDefChi.indexOf(Constants.COMCOL_END) != -1) {
            String temp = formulaDefChi.substring(formulaDefChi
                    .indexOf(Constants.COMCOL_START), formulaDefChi
                    .indexOf(Constants.COMCOL_END) + 1);
            formulaDefChi = formulaDefChi.replace(temp, "%s");
            temp = temp.replace(Constants.COMCOL_START, "").replace(
                    Constants.COMCOL_END, "");
            if (convertType.equals("save")) { // 存储
                // 从数据库中查询 获取dbColumnName
                temp = this.innerNameToDbColumn(temp, oneTableID);
                if (temp.contains("error"))
                    return temp;
            }
            if (convertType.equals("show")) { // 展现
                temp = this.innerDbColumnToName(temp, oneTableID);
            }

            formulaDefChi = formulaDefChi.replace("%s", "｛" + temp + "｝");
        }
        // 浮动表FDCODE 固定行列表ORDERID 修改为 TEMPLATEID
        while (formulaDefChi.indexOf(Constants.WHERE_START) != -1
                && formulaDefChi.indexOf(Constants.WHERE_END) != -1) {
            String temp = formulaDefChi.substring(formulaDefChi
                    .indexOf(Constants.WHERE_START), formulaDefChi
                    .indexOf(Constants.WHERE_END) + 1);
            formulaDefChi = formulaDefChi.replace(temp, "%s");
            temp = temp.replace(Constants.WHERE_START, "").replace(
                    Constants.WHERE_END, "");
            if (temp.indexOf("=") != -1) {
                String forWhere = temp.split("=")[1].replace("'", "");
                String tempType = dealType.equals("A2") ? "FDCODE" : "ORDERID";

                if ("save".equals(convertType)) { // 存储
                    temp = this.fdCodeToTemplateId(oneTableID, forWhere,
                            tempType);
                    if (temp.contains("error"))
                        return temp;
                }
                if ("show".equals(convertType)) { // 展现
                    temp = this.TemplateIdTofdCode(oneTableID, forWhere,
                            tempType);
                }

                formulaDefChi = formulaDefChi.replace("%s", "［" + temp + "］");
            } else {
                throw new ServiceException(ExceptionCode.FRU_00001,"公式内容无法解析，请检查！",false);
            }
        }
        return formulaDefChi;
    }

    // type : 1、获取物理列名 2、获取数据类型
    public String innerNameToDbColumn(String temp, String oneTableID) throws ServiceException {
        while (temp.indexOf(Constants.COLUMN_START) != -1
                && temp.indexOf(Constants.COLUMN_END) != -1) {
            String col_temp = temp.substring(temp
                    .indexOf(Constants.COLUMN_START), temp
                    .indexOf(Constants.COLUMN_END) + 1);
            // 从数据库中查询 获取dbColumnName
            String name = col_temp.replace(Constants.COLUMN_START, "").replace(
                    Constants.COLUMN_END, "");

            List<DictTFactorPO> list = dictTFactorService.getDictTFactorByName(
                    oneTableID, name);
            if (ConverTables.isNotNullList(list)) {
                String dbColumnName = list.get(0).getDbcolumnname();
                temp = temp.replace(col_temp, "「" + dbColumnName + "」");
            } else {
                throw new ServiceException(ExceptionCode.FRU_00001,"列名 『" + name + "』 错误，无法从数据库中解析！",false);
            }
        }
        return temp;
    }

    // FDCODE | ORDERID 通过查询模板数据*中 TEMPLATEID 进行存储
    public String fdCodeToTemplateId(String oneTableID, String forWhere,
            String tempType) throws ServiceException {
        List<Map<String, Object>> dataList = floatService
                .getFloatData(oneTableID,"");//不根据条件
        if (ConverTables.isNotNullList(dataList)) {
            for (Map<String, Object> m : dataList) {
                if (m.get(tempType).toString().equals(forWhere)) {
                    forWhere = "TEMPLATEID='" + m.get("TEMPLATEID") + "'";
                    break;
                }
            }
        }
        // 如果未转换成功，返回错误信息
        if (forWhere.indexOf("TEMPLATEID") == -1)
            throw new ServiceException(ExceptionCode.FRU_00001,tempType + "：" + forWhere + " 错误，无法从数据库中解析！",false);

        return forWhere;
    }

    public String dataTypeValue(int dataType) {
        String data = ""; // 如果是数据 替换为 1 , 不是 替换为 x
        if (dataType == 1 || dataType == 2)
            data = "1";
        else
            data = "\'x\'";
        return data;
    }

    // 将物理列名转换为中文列名
    public String innerDbColumnToName(String temp, String oneTableID) {
        while (temp.indexOf(Constants.COLUMN_START) != -1
                && temp.indexOf(Constants.COLUMN_END) != -1) {
            String col_temp = temp.substring(temp
                    .indexOf(Constants.COLUMN_START), temp
                    .indexOf(Constants.COLUMN_END) + 1);
            // 从数据库中查询 获取name
            String dbColumnName = col_temp.replace(Constants.COLUMN_START, "")
                    .replace(Constants.COLUMN_END, "");

            DictTFactorPO po = dictTFactorService.getDictTFactorByDBColumnName(
                    dbColumnName, oneTableID);
            if (po != null) { // 如果未找到列中文名，返回空
                temp = temp.replace(col_temp, "「" + po.getName() + "」");
            } else {
                temp = temp.replace(col_temp, "「」");
            }
        }
        return temp;
    }

    // 通过查询模板数据*中 TEMPLATEID 转换为 FDCODE | ORDERID 进行展现
    public String TemplateIdTofdCode(String oneTableID, String forWhere,
            String tempType) throws ServiceException {
        List<Map<String, Object>> dataList = floatService
                .getFloatData(oneTableID,"");//不根据条件查找
        if (ConverTables.isNotNullList(dataList)) {
            for (Map<String, Object> m : dataList) {
                if(m.get("TEMPLATEID")!=null){
                if (m.get("TEMPLATEID").equals(forWhere)) {
                    forWhere = tempType + "='" + m.get(tempType) + "'";
                }
            }
            }
        }
        // 如果未转换成功，返回空
        if (forWhere.indexOf(tempType) == -1)
            return tempType + "=''";

        return forWhere;
    }

    /**.判断 公式的格式是否正确  是否缺少列标识符等
     * @param formulaDefChi 中文公式内容
     * @throws ServiceException
     * @throws
     */
    public void verifyFormat(String formulaDefChi) throws ServiceException{

        if (formulaDefChi.indexOf(Constants.TT_START) == -1
                || formulaDefChi.indexOf(Constants.TT_END) == -1) {
            throw new ServiceException(ExceptionCode.FRU_00001,"缺少 表 标识符 " + Constants.TT_START + " " + Constants.TT_END + "！",false);
        }
        if (formulaDefChi.indexOf(Constants.WHERE_START) == -1
                || formulaDefChi.indexOf(Constants.WHERE_END) == -1) {
            throw new ServiceException(ExceptionCode.FRU_00001,"缺少 条件 标识符 " + Constants.WHERE_START + " " + Constants.WHERE_END ,false);
        }
        if (formulaDefChi.indexOf(Constants.COMCOL_START) == -1
                || formulaDefChi.indexOf(Constants.COMCOL_END) == -1) {
            throw new ServiceException(ExceptionCode.FRU_00001, "缺少 表达式 标识符 " + Constants.COMCOL_START + " "
                    + Constants.COMCOL_END + "！",false);
        }
        if (formulaDefChi.indexOf(Constants.COLUMN_START) == -1
                || formulaDefChi.indexOf(Constants.COLUMN_END) == -1) {
            throw new ServiceException(ExceptionCode.FRU_00001,"缺少 列 标识符" + Constants.COLUMN_START + " "
                    + Constants.COLUMN_END + "！",false);
        }
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Boolean deleteFormulaData(List<Map<String, Object>> lists) throws Exception{
        boolean flag = false;
        // 删除公式的同时应该修改单元格权限 只限表内行公式
        for (Map<String, Object> dataMap : lists) {
            String tableID = (String) dataMap.get("tableID");
            String formulaID = (String) dataMap.get("FORMULAID");
            String defineID = (String) dataMap.get("defineID");
            if ("1".equals(defineID)) {// 删除公式时是把单元格权限修改为1
                String dbTableName = dictTModelService.getDictTModelByID(tableID, false).getDbtablename();
                String dealType = dictTModelService.getDictTModelByID(tableID,false).getDealtype();
                List<Map<String, Object>> tableData = new ArrayList<Map<String, Object>>();
                // 获取TEMPLATEID 再使用TEMPLATEID来获取DATAKEY/CELLSECU 
                List<FormulaTFormulaDefPO> formulaTFormulaDefPOs = this.selectFormulaData(tableID, formulaID, defineID);
                if(!formulaTFormulaDefPOs.isEmpty()){
                String formulaDef = formulaTFormulaDefPOs.get(0).getFormulaDef();
                String formulaColName = formulaTFormulaDefPOs.get(0).getForComcol();

                String templateID = formulaDef.substring(formulaDef.indexOf("'") + 1, formulaDef.indexOf("'") + 33);
                tableData = floatService.getFloatDataByTemplateID(dbTableName,templateID);
                if(!tableData.isEmpty()){
                String dataKey = (String) tableData.get(0).get("DATAKEY");
                String cellSecu = (String) tableData.get(0).get("CELLSECU");

                List<DictTFactorPO> factor = dictTFactorService
                        .getDictTFactorsByTableId(tableID);
                String cIndex = "";
                String cSecu = "1";
                for (DictTFactorPO po : factor) {
                    String columnId = "";
                    if (formulaColName != null && !"".equals(formulaColName)) {// 列不为空
                        if (formulaColName.equals(po.getDbcolumnname())) {
                            columnId = po.getColumnid();
                            Map<String, Object> cSecus = this.getCellSecu(
                                    tableID, columnId, cellSecu, dealType);
                            cIndex = (String) cSecus.get("cIndex");
                            floatService.saveCellSecu(dataKey, cellSecu,
                                    cIndex, cSecu, tableID);
                        }
                    }
                }
            }
                }
            }
        }
        // 删除公式
        Integer is_delete = formulaMapper.deleteFormulaData(lists);
        if (is_delete > 0) {
            try {
                Map<String, String> map0 = new HashMap<String, String>();
                Map<String, String> map1 = new HashMap<String, String>();
                map0.put("tableId", "%");
                map1.put("tableId", lists.get(0).get("tableID").toString());
                this.formulaMapper.callPDropVoidTriggerForParam(map0);
                this.formulaMapper.callPCreateTrigger8ForParam(map1);
                flag = true;
            } catch (Exception e) {
                e.printStackTrace();
                flag = false;
                throw new Exception();
            }
        }
        return flag;
    }

    public List<FormulaTFormulaDefPO> selectFormulaData(String tableID,
            String formulaID, String defineID) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tableID", tableID);
        map.put("formulaID", formulaID);
        map.put("formulaType", defineID);
        List<FormulaTFormulaDefPO> result = formulaMapper.selectFormulaData(map);
        return result;
    }

    @Override
    public Object getData(Grid grid) throws ServiceException{
        String defineID=(String) grid.getExtProperties().get("defineID");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tableID", (String) grid.getExtProperties().get("tableID"));
        map.put("formulaID", "");
        map.put("formulaType",defineID );
        if(!"A0".equals(defineID)){
            map.put("factorTableName", ",DICT_T_FACTOR F");
            map.put("colName", "F.NAME AS FORMULACOLNAME,");
            map.put("colWhere", "AND F.TABLEID=#{tableID}  AND F.DBCOLUMNNAME=T.FORCOMCOL");
        }

        // 取得数据
        PageInfo pageInfo = new PageInfo();
        try {
            super.setGridData(formulaMapper.selectFormulaGridData(map),
                    pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.FRU_00001,"获取公式表内容出现异常！"+e.getCause().getMessage(),false);
        }

        return pageInfo;

    }

    // 获取数据库中错误信息
    public void callErrorMessage(String sql) throws ServiceException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("sql", sql);
        map.put("errorMessage", "");
        try {
            this.formulaMapper.callErrorMessage(map);
        } catch (Exception e) {
            throw new ServiceException(ExceptionCode.FRU_00001,e.getMessage(),false);
        }
        String errorMessage = map.get("errorMessage");
        if (ConverTables.isNotNull(errorMessage)) {
            throw new ServiceException(ExceptionCode.FRU_00001,errorMessage,false);
        }
    }

    // 处理特殊字符
    public String specialChar(String covert) {
        if (covert != null) { // 去除字符串中的空格、回车、换行符、制表符 \\s*|\t|\r
            Pattern p = Pattern.compile("\r");
            Matcher m = p.matcher(covert);
            covert = m.replaceAll("");
        }
        // .replaceAll("[‘’“”]", "'")
        covert = covert.replaceAll("‘’", "'").replaceAll("｛",
                Constants.COMCOL_START).replaceAll("｝", Constants.COMCOL_END)
                .replaceAll("〕", ")").replaceAll("〔", "(");
        return covert;
    }

    // 根据tableID 查询 物理表名
    public String getDbTableName(String tableID) {
        String dbTableName = "";
        DictTModelPO model = dictTModelService
                .getDictTModelByID(tableID, false);
        if (model != null) {
            dbTableName = model.getDbtablename();
        }
        return dbTableName;
    }

    // 根据tableID \ 列中文名 查询 物理列名
    public String getDbColumnName(String tableID, String name) {
        String dbColumnName = "";
        List<DictTFactorPO> list = dictTFactorService.getDictTFactorByName(
                tableID, name);
        if (list != null && list.size() > 0) {
            dbColumnName = list.get(0).getDbcolumnname();
        }
        return dbColumnName;
    }

    public int getColumnDataType(String dbColumnName, String tableID) {
        int dataType = 0;
        DictTFactorPO factor = dictTFactorService.getDictTFactorByDBColumnName(
                dbColumnName, tableID);
        if (factor != null) {
            dataType = factor.getDatatype();
        }
        return dataType;
    }

    // 是否包含 等号
    public String includeEqual(String covert) {
        String comcol = Constants.COMCOL_END + "=";
        if (covert.indexOf(comcol) != -1) {
            String leftFormula = covert.substring(0, covert.indexOf("}=") + 1);
            String rightFormula = covert.replace(leftFormula, "");

            return leftFormula + "," + rightFormula;
        }
        return covert;
    }

    // 获取 公式 最大序号
    public Integer formulaOrderID(String tableID, String formulaType) {
        int orderId = formulaMapper.formulaOrderID(tableID, formulaType) + 1;
        return orderId;
    }

    // 查询 表内行公式 （单元格公式）
    public FormulaTFormulaDefPO selectCellFormula(String tableID,
            String forComcol, String forWhere) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tableID", tableID);
        map.put("forComcol", forComcol);
        map.put("forWhere", forWhere);

        return formulaMapper.selectCellFormula(map);
    }

  /*  public List<Map<String, String>> selectFormulaColumn(String tableID,
            String formulaType) {

        return formulaMapper.selectFormulaColumn(tableID, formulaType);
    }*/
    /**.
     * 调用存储过程
     * @param operator
     * @param formula
     * @param is_insert
     * @return
     * @throws ServiceException
     * @throws
     */
    private String callTrigger(String operator,FormulaTFormulaDefPO formula,int is_insert) throws ServiceException{
        String flag = "";
      
            if (operator.equals("insert") || operator.equals("update")) {
                Map<String, String> map0 = new HashMap<String, String>();
                Map<String, String> map1 = new HashMap<String, String>();
                map0.put("tableId", "%");
                map1.put("tableId", formula.getTableID());
               try {    
                //表间公式 
                if("8".equals(formula.getFormulaType()) || "A8".equals(formula.getFormulaType())){
                    this.formulaMapper.callPDropVoidTriggerForParam(map0);
                    this.formulaMapper.callPCreateTrigger8ForParam(map1);
                }
                if (is_insert > 0)
                    flag = formula.getFormulaID();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException(ExceptionCode.FRU_00001,e.getMessage(),false);
            }
            }
        
        return flag;
    }
    // 根据CSID 查询 代码表数据
    public List<TreeNode> getModelCodeDataByCsid(String dbTableName) throws ServiceException {
        List<TreeNode> nodes = null;
        try {
            nodes = entryService.getRefrelaDbTableTree(dbTableName);
            if(nodes == null || nodes.size() == 0){
                throw new ServiceException(ExceptionCode.FRU_00001, "引用表：" + dbTableName + "中无数据！", false);
            }
            //如果没有code则在name上加上code
            if(nodes.get(0).getName().indexOf("]")>0){
                return nodes;
            }
            for(TreeNode node : nodes){
                node.setName("["+node.getCode()+"]"+node.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.FRU_00001,"代码表不存在！",false);
        }
        return nodes;
    }

    /**
     * 获取 单元格 权限
     * 
     * @param tableID
     *            表ID
     * @param columnId
     *            列ID
     * @param cellSecu
     *            单元格权限
     * @param dealType
     *            处理类型
     * @return
     */
    public Map<String, Object> getCellSecu(String tableID, String columnId,
            String cellSecu, String dealType) {
        String cSecu = "";
        String colOrder = "";
        int index = 0;
        Map<String, Object> map = new HashMap<String, Object>();
        if ("A2".equals(dealType)) {
            DictTSetFddefPO po = entryService.getDataFddefList(tableID);
            if (po != null)
                colOrder = po.getColOrder(); // 排序列
        }
        if ("A1".equals(dealType)) {
            List<DictTSetFixPO> fixed = entryService.getDataFixList(tableID);
            if (ConverTables.isNotNullList(fixed)) {
                for (DictTSetFixPO po : fixed) {
                    if (!ConverTables.isNotNull(colOrder))
                        colOrder = po.getColOrder();
                }
            }
        }
        if (ConverTables.isNotNull(colOrder)) {
            String col[] = colOrder.split(",");
            for (int i = 0; i < col.length; i++) {
                if (col[i].equals(columnId))
                    index = i;
            }
            // 截取 0 1
            if (ConverTables.isNotNull(cellSecu))
                cSecu = String.valueOf(cellSecu.charAt(index));
        }
        map.put("cSecu", cSecu);
        map.put("cIndex", String.valueOf(index));
        return map;
    }
    //获取 业务系统
    public List<Map<String, Object>> getAppList() {
        
        return formulaMapper.getAppList();
    }

    @Override
    public List<Map<String, Object>> getExitData(String tableID, String defineID) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tableID",tableID);
        map.put("formulaID", "");
        map.put("formulaType",defineID );
        return formulaMapper.selectFormulaGridData(map);
    }
    //获取列类型160428
    private Integer getColumnType(String name, String tableID) throws ServiceException{
        
        DictTFactorPO factor = null;
        try {
            factor = dictTFactorService.getDictTFactorByDBColumnName(name,tableID);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.DTM_07200,"获取表列『"+name+"』出现异常！",false);
        }
        if(factor == null){
            throw new ServiceException(ExceptionCode.DTM_07200,"获取表列『"+name+"』出现异常！",false);
        }
        return factor.getDatatype();
    }
}
