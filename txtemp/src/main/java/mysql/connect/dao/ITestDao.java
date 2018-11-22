package mysql.connect.dao;

import java.util.List;

import mysql.connect.BpmnTemplateDef;

public interface ITestDao {
	/** 查询所有的流程模版定义 */
	
	List<BpmnTemplateDef> selectTemplateDef();
	void createTempDefTable();
	/** 根据id删除流程模版定义 */
	int deleteTempDefById(String id);
}
