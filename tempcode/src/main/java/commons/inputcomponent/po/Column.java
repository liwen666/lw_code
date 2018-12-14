
package commons.inputcomponent.po;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 列信息 Author:CAOK 2015-11-17 下午03:53:36 Version 1.0
 */
public class Column implements Serializable {
    private static final long serialVersionUID = 1L;

    // 列ID
    private String columnID;

    // 列中文名称
    private String columnName;

    // 列数据库名称
    private String columnDBName;
    
    // 数据类型
    private String dataType;

    public enum DataType {
        N("N"), S("S"), D("D");

        private String value;

        private DataType(String value) {
            this.value = value;
        }

        public String getType() {
            return this.value;
        }
    }

    // 数据源ID
    private String dataSourceID;
    // 显示类型
    // 界面显示处理方式：TEXT = 0 MASK_TEXT = 1 Date=2 CHECK = 3 LIST=4 BUTTON=5 RADIO =
    // 6 SHORT DATE=7 POP_WINDOW = 8 HTML=9 大文本=A Picture=B File=C
    private String showType;
    // 数据长度
    private int dataLength;
    // 精度
    private int scale = 0;

    // 是否正则验证
    private boolean isRegular = false;

    // 正则表达式
    private String regExpression;

    // 正则验证提示信息
    private String regInfo;

    // 是否可更新
    private boolean readOnly = false;

    // 中文别名
    private String alias;

    // 引用表ID
    private String refTableID;

    // 引用表对应的物理表
    private String refTableDBName;

    // 显示掩码
    private String mask;

    // 显示宽度
    private int width;

    // 如果是树，是否可以全选，默认只能选末级
    private boolean isCheckAll = false;

    // 是否可见
    private boolean visible = true;

    // 是否可空
    private boolean nullable = true;

    // 排序
    private int orderID;

    // 列索引
    private int columnIndex;

    // 默认值
    private String defaultValue;

    // 默认显视值
    private String defaultShowValue;

    // 列的tips说明
    private String tips;

    // 是否物理主键
    private boolean isKey = false;

    // 是否逻辑主键
    private boolean isLogicKey = false;

    // 级次码
    private int levelNo = 0;

    // 是否是叶节点
    private boolean isLeaf = true;

    // 是否合计行
    private boolean isSum = false;

    // 是否绑定列
    private boolean isBindcol = false;

    // 绑定的引用列Id
    private String bindRefColID;

    // 绑定的引用列中引用的字段
    private String bindRefCodeTableCol;

    // 是否超链接
    private boolean isHref = false;

    // 超链接地址
    private String hrefUrl;

    // 超链接参数ID,参见DICT_T_SETHREFPARM
    private Map<String, String> hrefParms;

    // 是否是虚拟列
    private boolean isVirtual = false;

    // 父对象
    @JsonIgnore
    private Column parentColumn;

    // 子对象
    private List<Column> childrenColumnList;

    // 业务扩展属性
    private Map<String, Object> extProperties;
    
    // 系统扩展属性
    private String sysExtPro;

    private List<Map<String, String>> dataList;

    public Column() {

    }

    /**
     * Column常用的属性
     * @param columnID
     * @param columnName
     * @param columnDbName
     * @param dataType
     * @param showType
     * @param dataLength
     * @param scale
     * @param width
     * @param isKey
     * @param isVisible
     * @param readOnly
     */
    public Column(String columnID, String columnName, String alias, String columnDBName, String dataType, String showType,
            int dataLength, int scale, int width, boolean isKey, boolean visible, boolean readOnly, boolean nullable) {
        this.columnID = columnID;
        this.columnName = columnName;
        this.alias = alias;
        this.columnDBName = columnDBName;
        this.dataType = dataType;
        this.showType = showType;
        this.dataLength = dataLength;
        this.scale = scale;
        this.width = width;
        this.isKey = isKey;
        this.visible = visible;
        this.readOnly = readOnly;
        this.nullable = nullable;
    }

    public List<Map<String, String>> getDataList() {
        return dataList;
    }

    public void setDataList(List<Map<String, String>> dataList) {
        this.dataList = dataList;
    }

    public String getColumnID() {
        return columnID;
    }

    public void setColumnID(String columnID) {
        this.columnID = columnID;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnDBName() {
        return columnDBName;
    }

    public void setColumnDBName(String columnDBName) {
        this.columnDBName = columnDBName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataSourceID() {
        return dataSourceID;
    }

    public void setDataSourceID(String dataSourceID) {
        this.dataSourceID = dataSourceID;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public int getDataLength() {
        return dataLength;
    }

    public void setDataLength(int dataLength) {
        this.dataLength = dataLength;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public boolean isRegular() {
        return isRegular;
    }

    public void setRegular(boolean isRegular) {
        this.isRegular = isRegular;
    }

    public String getRegExpression() {
        return regExpression;
    }

    public void setRegExpression(String regExpression) {
        this.regExpression = regExpression;
    }

    public String getRegInfo() {
        return regInfo;
    }

    public void setRegInfo(String regInfo) {
        this.regInfo = regInfo;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getRefTableID() {
        return refTableID;
    }

    public void setRefTableID(String refTableID) {
        this.refTableID = refTableID;
    }

    public String getRefTableDBName() {
        return refTableDBName;
    }

    public void setRefTableDBName(String refTableDBName) {
        this.refTableDBName = refTableDBName;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isCheckAll() {
        return isCheckAll;
    }

    public void setCheckAll(boolean isCheckAll) {
        this.isCheckAll = isCheckAll;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDefaultShowValue() {
        return defaultShowValue;
    }

    public void setDefaultShowValue(String defaultShowValue) {
        this.defaultShowValue = defaultShowValue;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public boolean isKey() {
        return isKey;
    }

    public void setKey(boolean isKey) {
        this.isKey = isKey;
    }

    public boolean isLogicKey() {
        return isLogicKey;
    }

    public void setLogicKey(boolean isLogicKey) {
        this.isLogicKey = isLogicKey;
    }

    public int getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(int levelNo) {
        this.levelNo = levelNo;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public boolean isSum() {
        return isSum;
    }

    public void setSum(boolean isSum) {
        this.isSum = isSum;
    }

    public boolean isBindcol() {
        return isBindcol;
    }

    public void setBindcol(boolean isBindcol) {
        this.isBindcol = isBindcol;
    }

    public String getBindRefColID() {
        return bindRefColID;
    }

    public void setBindRefColID(String bindRefColID) {
        this.bindRefColID = bindRefColID;
    }

    public String getBindRefCodeTableCol() {
        return bindRefCodeTableCol;
    }

    public void setBindRefCodeTableCol(String bindRefCodeTableCol) {
        this.bindRefCodeTableCol = bindRefCodeTableCol;
    }

    public boolean isHref() {
        return isHref;
    }

    public void setHref(boolean isHref) {
        this.isHref = isHref;
    }

    public String getHrefUrl() {
        return hrefUrl;
    }

    public void setHrefUrl(String hrefUrl) {
        this.hrefUrl = hrefUrl;
    }

    public Map<String, String> getHrefParms() {
        return hrefParms;
    }

    public void setHrefParms(Map<String, String> hrefParms) {
        this.hrefParms = hrefParms;
    }

    public boolean isVirtual() {
        return isVirtual;
    }

    public void setVirtual(boolean isVirtual) {
        this.isVirtual = isVirtual;
    }

    public Column getParentColumn() {
        return parentColumn;
    }

    public void setParentColumn(Column parentColumn) {
        this.parentColumn = parentColumn;
    }

    public List<Column> getChildrenColumnList() {
        return childrenColumnList;
    }

    public void setChildrenColumnList(List<Column> childrenColumnList) {
        this.childrenColumnList = childrenColumnList;
    }

    public Map<String, Object> getExtProperties() {
        return extProperties;
    }

    public void setExtProperties(Map<String, Object> extProperties) {
        this.extProperties = extProperties;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    /**
     * @return sysExtPro
     */
    public String getSysExtPro() {
        return sysExtPro;
    }

    /**
     * @param sysExtPro 要设置的 sysExtPro
     */
    public void setSysExtPro(String sysExtPro) {
        this.sysExtPro = sysExtPro;
    }
    
    

}
