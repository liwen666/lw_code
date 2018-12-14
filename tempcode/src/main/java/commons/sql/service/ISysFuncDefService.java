package commons.sql.service;

import java.util.HashMap;
import java.util.Map;

import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.Table;


public interface ISysFuncDefService {

	public Table setTableDefine();
	
	public void setColumns(Table grid);

	public Object getData(Grid data);

	public HashMap<String, String> getDefInfoByID(String guid);

	public void saveData(HashMap<String, String> params, String opt)throws Exception ;

	public void deleteData(Map<String, Object> param);

	public String getContentByID(String guid);	
}
