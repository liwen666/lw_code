package commons.sql.service.impl;

import gov.mof.fasp2.ca.user.dto.UserDTO;
import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

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
import com.tjhq.commons.sql.dao.SqlManagerMapper;
import com.tjhq.commons.sql.po.SqlManagerPO;
import com.tjhq.commons.sql.service.ISqlManagerService;

@Service
@Transactional(readOnly=true)
public class SqlManagerService implements ISqlManagerService {
	@Resource
	private SqlManagerMapper sqlManagerMapper;

	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void insertSql(Map<String, String> row) {
		this.sqlManagerMapper.insertSqlstatement(row);
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void updateSql(Map<String, String> row) {
		this.sqlManagerMapper.updateSqlstatement(row);
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void deleteSql(String sqlid) {
		sqlManagerMapper.deleteSqlstatement(sqlid);
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void executeSql(String sqlid) throws Exception {
		SqlManagerPO po = sqlManagerMapper.getSqlstatementById(sqlid);
		if (null != po) {
			String sql = po.getSql_statement();
			UserDTO current = SecureUtil.getCurrentUser();// 从此处获取当前登录用户的guid
			String userid = current.getGuid();
			sql = sql.toUpperCase().replace(
					"${USERID}", userid);
			sqlManagerMapper.execSql(sql);
			
			//记录日志
			HashMap<String,String> map=new HashMap<String, String>();
			
			map.put("LOGINNAME", current.getCode());
			map.put("SQLSTATEMENT", sql);
			sqlManagerMapper.insertSqlLog(map);
		}
	}

	@Override
	public Table setTableDefine() {
		Table grid=new Table();
		grid.setTableID("SPF_T_SQL");
		grid.setAppID("SPF");
		grid.setTableDBName("SPF_T_SQL");
		grid.setTableName("SQL语句管理");
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
		
		//SQLID-1
		Column guid=new Column();
		guid.setColumnID("SQLID");
		guid.setColumnName("主键");
		guid.setColumnDBName("SQLID");
		guid.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		guid.setShowType(ShowType.SHOW_TYPE_HTML);
		guid.setDataLength(32);
		guid.setKey(true);
		guid.setOrderID(0);
		guid.setNullable(false);
		guid.setVisible(false);
		guid.setReadOnly(true);
		
		//SQL_STATEMENT-2
		Column sql=new Column();
		sql.setColumnID("SQL_STATEMENT");
		sql.setColumnName("SQL");
		sql.setColumnDBName("SQL_STATEMENT");
		sql.setAlias("SQL");
		sql.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		sql.setShowType(ShowType.SHOW_TYPE_HTML);
		sql.setDataLength(2000);
		sql.setOrderID(2);
		sql.setReadOnly(true);
		
		//DBVERSION-3
		Column dbversion=new Column();
		dbversion.setColumnID("DBVERSION");
		dbversion.setColumnName("修改时间");
		dbversion.setColumnDBName("DBVERSION");
		dbversion.setAlias("修改时间");
		dbversion.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		dbversion.setShowType(ShowType.SHOW_TYPE_HTML);
		dbversion.setDataLength(200);
		dbversion.setOrderID(3);
		dbversion.setReadOnly(true);

		
			
		//ISPASS-4
		Column opt=new Column();
		opt.setColumnID("OPT");
		opt.setColumnName("操作");
		opt.setColumnDBName("OPT");
		opt.setAlias("操作");
		opt.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		opt.setShowType(ShowType.SHOW_TYPE_HTML);
		opt.setDataLength(32);
		opt.setOrderID(4);
		opt.setReadOnly(true);
		
		//
		list.add(guid);
		list.add(sql);
		list.add(dbversion);
		list.add(opt);

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
		List<SqlManagerPO> gridData = sqlManagerMapper.getGridData(extProperties);
		List<Object> dataList = new ArrayList<Object>();// 显示数据
		if (null != gridData && gridData.size() > 0) {
			for (SqlManagerPO temp : gridData) {
				List<Object> rowData = new ArrayList<Object>();
				rowData.add(temp.getSqlid());
				rowData.add(temp.getSql_statement());
				rowData.add(temp.getDbversion());
				rowData.add(temp.getSqlid());//对应操作
	
				dataList.add(rowData);
			}

		}
		int total = 0;
		String count = sqlManagerMapper.getGridDataCount(extProperties);// 总记录数
		if (count != null) {
			total = Integer.parseInt(count);
		}

		// dataList
		pageInfo.setDataList(dataList);

		// columnList
		List<String> columns = new ArrayList<String>();
		columns.add("SQLID");
		columns.add("SQL_STATEMENT");
		columns.add("DBVERSION");
		columns.add("OPT");
		pageInfo.setColumns(columns);
		// 总数
		pageInfo.setTotal(total);
		return pageInfo;
	}

}
