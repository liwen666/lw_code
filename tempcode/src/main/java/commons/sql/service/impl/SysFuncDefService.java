package commons.sql.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.constants.ShowType;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.PageInfo;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.utils.JSTypeUtils;
import com.tjhq.commons.sql.dao.SysFuncDefMapper;
import com.tjhq.commons.sql.service.ISysFuncDefService;
import com.tjhq.commons.utils.StringUtil;

@Service
@Transactional(readOnly=true)
public class SysFuncDefService implements ISysFuncDefService {
	@Resource
	private SysFuncDefMapper sysFuncDefMapper;
	/**
	 *表定义
	 */
	@Override
	public Table setTableDefine() {
		Table grid=new Table();
		grid.setTableID("BGT_T_SYSFUNCDEF");
		grid.setAppID("BGT");
		grid.setTableDBName("BGT_T_SYSFUNCDEF");
		grid.setTableName("系统函数定义表");
		setColumns(grid);
		return grid;
	}

	/**
	 * 设置采集业务分类信息表列信息
	 * @param: 表
	 */
	@Override
	public void setColumns(Table grid) {
		
		List<Column> list=new ArrayList<Column>();
		
		//GUID-1
		Column guid=new Column();
		guid.setColumnID("GUID");
		guid.setColumnName("内容");
		guid.setColumnDBName("GUID");
		guid.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		guid.setShowType(ShowType.SHOW_TYPE_HTML);
		guid.setDataLength(32);
		guid.setKey(true);
		guid.setOrderID(0);
		guid.setNullable(false);
		guid.setVisible(false);
		guid.setReadOnly(true);
		
		//NAME-2
		Column name=new Column();
		name.setColumnID("NAME");
		name.setColumnName("中文名称");
		name.setColumnDBName("NAME");
		name.setAlias("中文名称");
		name.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		name.setShowType(ShowType.SHOW_TYPE_HTML);
		name.setDataLength(200);
		name.setOrderID(2);
		name.setReadOnly(true);
		
		//TITLE-3
		Column title=new Column();
		title.setColumnID("TITLE");
		title.setColumnName("英文名称");
		title.setColumnDBName("TITLE");
		title.setAlias("英文名称");
		title.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		title.setShowType(ShowType.SHOW_TYPE_HTML);
		title.setDataLength(200);
		title.setOrderID(3);
		title.setReadOnly(true);

		
			
		//ISPASS-4
		Column isPass=new Column();
		isPass.setColumnID("ISPASS");
		isPass.setColumnName("编译是否通过");
		isPass.setColumnDBName("ISPASS");
		isPass.setAlias("编译是否通过");
		isPass.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		isPass.setShowType(ShowType.SHOW_TYPE_HTML);
		isPass.setDataLength(32);
		isPass.setOrderID(4);
		isPass.setReadOnly(true);
		
		//
		list.add(guid);
		list.add(name);
		list.add(title);
		list.add(isPass);

		grid.setColumnList(list);
	}

	@Override
	public Object getData(Grid data) {
		Map<String, Object> extProperties = data.getExtProperties();

		PageInfo pageInfo = data.getPageInfo();
		int start = pageInfo.getStart();// 起始行
		int end = start + pageInfo.getLimit();// 结束行
		extProperties.put("start", start);
		extProperties.put("end", end);
		List<HashMap<String, String>> gridData = sysFuncDefMapper.getGridData(extProperties);
		List<Object> dataList = new ArrayList<Object>();// 显示数据
		if (null != gridData && gridData.size() > 0) {
			for (HashMap<String, String> temp : gridData) {
				List<Object> rowData = new ArrayList<Object>();
				rowData.add(temp.get("GUID"));
				rowData.add(temp.get("NAME"));
				rowData.add(temp.get("TITLE"));
				rowData.add(temp.get("ISPASS"));
	
				dataList.add(rowData);
			}

		}
		int total = 0;
		String count = sysFuncDefMapper.getGridDataCount(extProperties);// 总记录数
		if (count != null) {
			total = Integer.parseInt(count);
		}

		// dataList
		pageInfo.setDataList(dataList);

		// columnList
		List<String> columns = new ArrayList<String>();
		columns.add("GUID");
		columns.add("NAME");
		columns.add("TITLE");
		columns.add("ISPASS");
		pageInfo.setColumns(columns);
		// 总数
		pageInfo.setTotal(total);
		return pageInfo;
	}

	@Override
	public HashMap<String, String> getDefInfoByID(String guid) {
		return sysFuncDefMapper.getDefInfoByID(guid);
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveData(HashMap<String, String> params,String opt) throws Exception {
		/*if(null!=params && StringUtils.isNotEmpty(params.get("TBODY"))){
			String newStr=new String(params.get("TBODY").getBytes("gbk"),"utf-8");
			params.put("TBODY", newStr);
		}*/
		
		String isPass = executeSql(getSqlByMap(params));
		params.put("ISPASS", isPass);
		
		
		if("ADD".equals(opt)){
			sysFuncDefMapper.insertData(params);			
		}else if("UPDATE".equals(opt)){
			sysFuncDefMapper.updateData(params);
		}
		
	}
	/**
	 * 执行
	 * @param sql
	 * @return
	 */
	private String executeSql(String sql){
		String isPass = "1";
		try {
			sysFuncDefMapper.execSql(sql);
		}catch(Exception e){
			e.printStackTrace();
			isPass = "0";
		}
		return isPass;
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void deleteData(Map<String, Object> param) {
		String guid = (String) param.get("GUID");
		HashMap<String,String> defInfo = sysFuncDefMapper.getDefInfoByID(guid);
		if("1".equals(defInfo.get("ISPASS"))){
			String defType = defInfo.get("DEFTYPE");
			String sql ="";
			if("1".equals(defType)){
				sql = "DROP VIEW "+ defInfo.get("TITLE");
			}else if("2".equals(defType)){
				sql = "DROP PROCEDURE  "+ defInfo.get("TITLE");
			}else if("3".equals(defType)){
				sql = "DROP FUNCTION "+ defInfo.get("TITLE");
			}
			executeSql(sql);
		}
		sysFuncDefMapper.deleteData(guid);
		
	}

	@Override
	public String getContentByID(String guid) {
		
		HashMap<String,String> defInfo = sysFuncDefMapper.getDefInfoByID(guid);
		return getSqlByMap(defInfo);
		
	}
	/**
	 * 根据内容解析sql
	 * @param params
	 * @return
	 */
	private String getSqlByMap(HashMap<String,String> params){
		String sql = "";
		String defType = params.get("DEFTYPE");// 1 视图 ，2 存储过程， 3 函数
		String sqlParams = params.get("PARAMS");
		String retVal = params.get("RETVAL");
		if ("1".equals(defType)) {
			sql = "CREATE OR REPLACE VIEW " + params.get("TITLE") + " AS "
					+ params.get("TBODY");
		} else if ("2".equals(defType)) {
			sql = "CREATE OR REPLACE PROCEDURE " + params.get("TITLE");
			if (!StringUtil.isEmpty(sqlParams) || !StringUtil.isEmpty(retVal)) {
				sql += "(";
				if (!StringUtil.isEmpty(sqlParams)) {
					sql += sqlParams;
				}
				if (!StringUtil.isEmpty(retVal)) {
					sql += "," + retVal;
				}
				sql += ")";
			}
			sql += " IS " + params.get("TBODY");
		} else if ("3".equals(defType)) {
			sql = "CREATE OR REPLACE FUNCTION " + params.get("TITLE");
			if (!StringUtil.isEmpty(sqlParams)) {
				sql += "(" + sqlParams + ")";
			}
			if (!StringUtil.isEmpty(retVal)) {
				sql += " RETURN " + retVal;
			}
			sql += " IS " + params.get("TBODY");
		}
		return sql;		
	}
}
