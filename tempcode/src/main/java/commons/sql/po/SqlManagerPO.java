package commons.sql.po;

public class SqlManagerPO {
	private String sql_statement;
	private String sqlid;
	private String dbversion;

	public String getDbversion() {
		return dbversion;
	}

	public void setDbversion(String dbversion) {
		this.dbversion = dbversion;
	}

	public String getSqlid() {
		return sqlid;
	}

	public void setSqlid(String sqlid) {
		this.sqlid = sqlid;
	}

	public String getSql_statement() {
		return sql_statement;
	}

	public void setSql_statement(String sqlStatement) {
		sql_statement = sqlStatement;
	}
	

}
