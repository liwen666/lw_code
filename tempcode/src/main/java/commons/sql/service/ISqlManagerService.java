package commons.sql.service;

import java.util.Map;

import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.Table;

public interface ISqlManagerService {
	
	void insertSql(Map<String, String> param);

	void updateSql(Map<String, String> row);

	void deleteSql(String sqlid);

	void executeSql(String sqlid) throws Exception;

	//新的
	Table setTableDefine();
	public void setColumns(Table grid);

	Object getData(Grid data);

}
