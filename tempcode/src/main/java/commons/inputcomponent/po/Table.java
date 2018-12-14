package commons.inputcomponent.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tjhq.commons.secu.po.ColumnLimitPO;

public class Table implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean isPagination = false;
	private String tableID;
	private String tableName;
	private String shortTitle;
	private String tableDBName;
	private String appID;
	private String xType;//展现类型
	private boolean isTotal;//是否合计
	private List<Column> columnList = new ArrayList<Column>();
	private boolean visiable = true;
	private boolean readOnly = false;// 新加
	private boolean canAdd = true;
	private boolean canDelete = true;
	private boolean canModify = true;
	private String rowSecu;
	private String rowWriteSecu;
	private String rowVisibleSecu;
	private List<ColumnLimitPO> columnLimitList = new ArrayList<ColumnLimitPO>();
	private List<Map<String, Object>> insertValues = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> updateValues = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> deleteValues = new ArrayList<Map<String, Object>>();
	private Map<String, Object> extProperties;// 例如为分页等进行参数扩展
	private Map<String, List<String>> formulaList;// 新加
	private Map<String, List<String>> relatedCoumnList;// 新加

	public String getTableID() {
		return tableID;
	}

	public void setTableID(String tableID) {
		this.tableID = tableID;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public String getTableDBName() {
		return tableDBName;
	}

	public void setTableDBName(String tableDBName) {
		this.tableDBName = tableDBName;
	}
	
	public String getxType() {
		return xType;
	}

	public void setxType(String xType) {
		this.xType = xType;
	}

	public List<Column> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<Column> columnList) {
		this.columnList = columnList;
	}

	public boolean isVisiable() {
		return visiable;
	}

	public void setVisiable(boolean visiable) {
		this.visiable = visiable;
	}

	public Map<String, Object> getExtProperties() {
		return extProperties;
	}

	public void setExtProperties(Map<String, Object> extProperties) {
		this.extProperties = extProperties;
	}

	public List<Map<String, Object>> getInsertValues() {
		return insertValues;
	}

	public void setInsertValues(List<Map<String, Object>> insertValues) {
		this.insertValues = insertValues;
	}

	public List<Map<String, Object>> getUpdateValues() {
		return updateValues;
	}

	public void setUpdateValues(List<Map<String, Object>> updateValues) {
		this.updateValues = updateValues;
	}

	public List<Map<String, Object>> getDeleteValues() {
		return deleteValues;
	}

	public void setDeleteValues(List<Map<String, Object>> deleteValues) {
		this.deleteValues = deleteValues;
	}

	public boolean isCanAdd() {
		return canAdd;
	}

	public void setCanAdd(boolean canAdd) {
		this.canAdd = canAdd;
	}

	public boolean isCanDelete() {
		return canDelete;
	}

	public void setCanDelete(boolean canDelete) {
		this.canDelete = canDelete;
	}

	public boolean isCanModify() {
		return canModify;
	}

	public void setCanModify(boolean canModify) {
		this.canModify = canModify;
	}

	public String getRowSecu() {
		return rowSecu;
	}

	public void setRowSecu(String rowSecu) {
		this.rowSecu = rowSecu;
	}

    public String getRowWriteSecu() {
        return rowWriteSecu;
    }

	public void setRowWriteSecu(String rowWriteSecu) {
		this.rowWriteSecu = rowWriteSecu;
	}

	public String getRowVisibleSecu() {
		return rowVisibleSecu;
	}

	public void setRowVisibleSecu(String rowVisibleSecu) {
		this.rowVisibleSecu = rowVisibleSecu;
	}

	/**
     * @return columnLimitList
     */
    public List<ColumnLimitPO> getColumnLimitList() {
        return columnLimitList;
    }

    /**
     * @param columnLimitList 要设置的 columnLimitList
     */
    public void setColumnLimitList(List<ColumnLimitPO> columnLimitList) {
        this.columnLimitList = columnLimitList;
    }

    public Map<String, List<String>> getFormulaList() {
		return formulaList;
	}

	public void setFormulaList(Map<String, List<String>> formulaList) {
		this.formulaList = formulaList;
	}

	public Map<String, List<String>> getRelatedCoumnList() {
		return relatedCoumnList;
	}

	public void setRelatedCoumnList(Map<String, List<String>> relatedCoumnList) {
		this.relatedCoumnList = relatedCoumnList;
	}

	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public boolean isPagination() {
		return isPagination;
	}

	public void setPagination(boolean isPagination) {
		this.isPagination = isPagination;
	}

	public boolean isTotal() {
		return isTotal;
	}

	public void setTotal(boolean isTotal) {
		this.isTotal = isTotal;
	}
	@JsonIgnore
	public String getTaskID() {
		if (this.extProperties != null && this.extProperties.containsKey("taskID")) {
			return (String)this.extProperties.get("taskID");
		}
		return null;
	}
	
	@JsonIgnore
	@Override
	public String toString() {
		StringBuffer tableInfo = new StringBuffer();
		tableInfo.append("tableID:").append(tableID).append(",");
		tableInfo.append("tableName:").append(tableName).append(",");
		tableInfo.append("tableDBName:").append(tableDBName).append(",");
		tableInfo.append("shortTitle:").append(shortTitle).append(",");
		tableInfo.append("appID:").append(appID).append(",");
		tableInfo.append("xType:").append(xType).append(",");
		tableInfo.append("columnList:").append(columnList == null ? "0" : columnList.size()).append(",");
		
		tableInfo.append("visiable:").append(visiable).append(",");
		tableInfo.append("readOnly:").append(readOnly).append(",");
		tableInfo.append("canAdd:").append(canAdd).append(",");
		tableInfo.append("canDelete:").append(canDelete).append(",");
		tableInfo.append("canModify:").append(canModify).append(",");
		tableInfo.append("rowWriteSecu:").append(rowWriteSecu).append(",");
		tableInfo.append("rowVisibleSecu:").append(rowVisibleSecu).append(",");
		
		return tableInfo.toString();
	}
	
	

}
