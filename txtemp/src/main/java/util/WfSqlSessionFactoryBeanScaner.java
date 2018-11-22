package util;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.TransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;


public class WfSqlSessionFactoryBeanScaner extends SqlSessionFactoryBean {

	private DataSource dataSource;

	private Map<String, Resource[]> mapperLocationsMap;
	
	@SuppressWarnings("unused")
	private TransactionFactory transactionFactory;
	
	public void setTransactionFactory(TransactionFactory transactionFactory) {
		this.transactionFactory = transactionFactory;
		super.setTransactionFactory(transactionFactory);
	}

	public void setDataSource(DataSource dataSource) {
		if (dataSource instanceof TransactionAwareDataSourceProxy) {
			this.dataSource = ((TransactionAwareDataSourceProxy) dataSource)
					.getTargetDataSource();
		} else {
			this.dataSource = dataSource;
		}
		super.setDataSource(this.dataSource);
	}


	public Map<String, Resource[]> getMapperLocationsMap() {
		return mapperLocationsMap;
	}

	public void setMapperLocationsMap(Map<String, Resource[]> mapperLocationsMap) {
		this.mapperLocationsMap = mapperLocationsMap;
	}

	public Resource[] getMapperLocation() {
		return getDatabaseConfig();
	}

	public Resource[] getDatabaseConfig() {
		String databaseType = "";
		try {
			databaseType = this.getDatabaseIdProvider().getDatabaseId(
					dataSource);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(StringUtils.isEmpty(BpmnProcessConstant.BPMN_DATABASE_TYPE)){
			BpmnProcessConstant.BPMN_DATABASE_TYPE = databaseType;
		}
		System.out.println("##" + "database type is " + databaseType);
		return this.mapperLocationsMap.get(databaseType == "" ? "oracle"
				: databaseType.toLowerCase());
	}

	public SqlSessionFactory buildSqlSessionFactory() throws IOException {
		this.setMapperLocations(getMapperLocation());
		return super.buildSqlSessionFactory();
	}

}
