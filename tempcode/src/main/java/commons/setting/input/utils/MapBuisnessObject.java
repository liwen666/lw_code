package commons.setting.input.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import com.tjhq.commons.inputcomponent.po.Column;

/**
 * 动态业务对象类型
 */
public class MapBuisnessObject {

	//表名
	private String tableName;
	
	//主键、联合主键
	private List<MapField> keys = new ArrayList<MapField>();
	
	//列定义
	private List<MapField> fields = new ArrayList<MapField>();
	
	//Map结构的列定义
	private Map<String,MapField> mapFields = new HashMap<String,MapField>();

	//数据对象
	private List<List<MapField>> values = new ArrayList<List<MapField>>();
	
	/*
	 * 将数组对象转换为支持新增、更新、删除操作的数据结构
	 * oprationRow:{
	 * 	insert:[MapField],//新增字段数组，完成新增操作
	 *  update:[MapField],//更新字段数组，完成更新操作需联合使用pks字段数组
	 *  delete/pks:[MapField],//主键字段数组，完成删除操作
	 * }
	 */
	private Map<String,List<MapField>> oprationRow = new HashMap<String,List<MapField>>();
	
	//行数据索引
	private Integer currentRowIndex = 0;
	
	//sql String
	private String sqlSelect;

	private String sqlFrom;
	
	private String sqlWhere;
	
	private String sqlQuery;
	
	private String sqlInsert;
	
	private String sqlUpdate;
	
	private String sqlDelete;
	
	/**
	 * 加载数据，将Map<Stirng,String>动态数据转换为List<List<MapField>>格式的数据结构
	 * @param values
	 */
	public void readValues(List<Map<String,Object>> mapValues) throws Exception{
		
		values.clear();
		currentRowIndex = 0;
		//行遍历
		for(Map<String,Object> data:mapValues){
			List<MapField> row = new ArrayList<MapField>();
			//列遍历
			for(MapField field:fields){
				MapField rowField = new MapField();
				copyFieldProperties(rowField,field);
				//所有的Object对象转为String对象处理
				//rowField.setFieldValue(StringUtils.trimToNull(data.get(field.getFieldName())==null?null:data.get(field.getFieldName()).toString()));	//"" to Null
				//保留空字符串提交 20140610
				rowField.setFieldValue(data.get(field.getFieldName())==null?null:data.get(field.getFieldName()).toString().trim());	//保留空字符串 ""不能等同为null
				row.add(rowField);
			}
			values.add(row);
		}
	}
	
	/**
	 * MapField对象属性拷贝
	 * @param dest
	 * @param orig
	 * @throws Exception
	 */
	private static void copyFieldProperties(MapField dest,MapField orig) throws Exception{
		
		Map<String,Object> columnDescribe = PropertyUtils.describe(new Column());
		columnDescribe.remove("class");
		
		//仅拷贝Column属性
		for(String field:columnDescribe.keySet()){
			BeanUtils.copyProperty(dest,field,PropertyUtils.getProperty(orig,field));
		}
		
		dest.setFieldName(orig.getFieldName());
		dest.setFieldValue(orig.getFieldValue());
		dest.setOperator(orig.getOperator());
	}
	
	/**
	 * 获得当前行数据，支持新增、更新、删除操作
	 * @return
	 */
	public MapBuisnessObject nextRow(){
		
		if(currentRowIndex >= size()) return null;
		
		//获得当前行数据
		List<MapField> row = values.get(currentRowIndex);
		
		List<MapField> insert = new ArrayList<MapField>();
		List<MapField> update = new ArrayList<MapField>();
		List<MapField> delete = new ArrayList<MapField>();
		
		//缓存主键
		Set<String> keysSet =new HashSet<String>();
		for(MapField keyField:keys) keysSet.add(keyField.getFieldName());
		
		for(MapField field:row){
			//如果是虚列、绑定列 不进行新增、更新操作
			if(field.isVirtual() || field.isBindcol()) continue;
			//新增操作需要所有的字段
			if(field.getFieldValue()!=null && !field.getFieldValue().toString().equalsIgnoreCase(""))
				insert.add(field);
			if(keysSet.contains(field.getFieldName())){
				//删除操作加入主键即可
				delete.add(field);
			}else{
				//更新操作字段，联合使用删除操作字段生成SQL语句
				//当前字段不为空或主键时加入更新字段
				if(field.getFieldValue()!=null || field.isKey())
					update.add(field);
			}
		}
		
		oprationRow.clear();
		oprationRow.put("insert",insert);
		oprationRow.put("update",update);
		oprationRow.put("delete",delete);
		oprationRow.put("pks",delete);
		currentRowIndex++;
		
		return this;
	}
	
	public Map<String, MapField> getMapFields() {
		if(fields.size() != mapFields.size())
			for(MapField f:fields) mapFields.put(f.getFieldName(),f);
		return mapFields;
	}
	
	public int size(){
		return values==null?0:values.size();
	}
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<MapField> getKeys() {
		return keys;
	}

	public void setKeys(List<MapField> keys) {
		this.keys = keys;
	}

	public List<MapField> getFields() {
		return fields;
	}

	public void setFields(List<MapField> fields) {
		this.fields = fields;
	}

	public List<List<MapField>> getValues() {
		return values;
	}

	public void setValues(List<List<MapField>> values) {
		this.values = values;
	}

	public Map<String, List<MapField>> getOprationRow() {
		return oprationRow;
	}

	public void setOprationRow(Map<String, List<MapField>> oprationRow) {
		this.oprationRow = oprationRow;
	}

	public Integer getCurrentRowIndex() {
		return currentRowIndex;
	}

	public void setCurrentRowIndex(Integer currentRowIndex) {
		this.currentRowIndex = currentRowIndex;
	}

	public void setMapFields(Map<String, MapField> mapFields) {
		this.mapFields = mapFields;
	}
	
	public String getSqlSelect() {
		if(this.fields == null || this.fields.size() == 0) return null;
		
		StringBuffer sqlSelectBuffer = new StringBuffer();
		for(MapField field:this.fields){
			if(sqlSelectBuffer.length()==0)
				sqlSelectBuffer.append(field.getFieldSqlName());
			else
				sqlSelectBuffer.append(","+field.getFieldSqlName());
		}
		sqlSelect = sqlSelectBuffer.toString();
		return sqlSelect.toUpperCase();
	}

	public void setSqlSelect(String sqlSelect) {
		this.sqlSelect = sqlSelect;
	}

	public String getSqlFrom() {
		sqlFrom = tableName;
		return sqlFrom.toUpperCase();
	}

	public void setSqlFrom(String sqlFrom) {
		this.sqlFrom = sqlFrom;
		this.tableName = sqlFrom;
	}

	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}

	public String getSqlQuery() {
		sqlQuery = "select "+getSqlSelect()+" where "+getSqlWhere();
		return sqlQuery.toUpperCase();
	}

	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	public String getSqlInsert() {
		return sqlInsert;
	}

	public void setSqlInsert(String sqlInsert) {
		this.sqlInsert = sqlInsert;
	}

	public String getSqlUpdate() {
		return sqlUpdate;
	}

	public void setSqlUpdate(String sqlUpdate) {
		this.sqlUpdate = sqlUpdate;
	}

	public String getSqlDelete() {
		return sqlDelete;
	}

	public void setSqlDelete(String sqlDelete) {
		this.sqlDelete = sqlDelete;
	}
	
	/**
	 * 业务对象描述
	 */
	public String describe(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n\rtableName:"+tableName);
		buffer.append("\n\r");
		for(MapField f:fields){
			buffer.append("\ngetFieldName:"+f.getFieldName()+"\tgetDataType:"+f.getDataType()+"\tgetDefaultValue:"+f.getDefaultValue()+"\tisKey:"+f.isKey()+"\tisNullable:"+f.isNullable());
		}
		return buffer.toString();
	}
}