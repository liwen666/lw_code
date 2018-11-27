package com.temp.code.exception.app;

import java.util.Arrays;

/**
 * @author  ���� : lw E-mail:
 * @date ����ʱ�䣺2016��11��28�� ����8:50:41
 * @version 1.0 * @parameter 
 * @since 
 * @return 
 */
public class DataBaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6395694839780530821L;
	/**
	 * ������Ϣ
	 */
	private final String errMessage;
	private final String sql;
	private String[] param;
	public DataBaseException(String errMessage, String sql) {
		super(errMessage);
		this.errMessage = errMessage;
		this.sql = sql;
	}
	public DataBaseException(String errMessage, String sql ,Throwable e) {
		super(errMessage,e);
		this.errMessage = errMessage;
		this.sql = sql;
	}
	public DataBaseException(String errMessage, String sql, String[] param) {
		super(errMessage);
		this.errMessage = errMessage;
		this.sql = sql;
		this.param = param;
	}
	public DataBaseException(String errMessage, String sql, String[] param,Throwable e) {
		super(errMessage,e);
		this.errMessage = errMessage;
		this.sql = sql;
		this.param = param;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getErrMessage() {
		return errMessage;
	}
	public String getSql() {
		return sql;
	}
	public String[] getParam() {
		return param;
	}
	@Override
	public String toString() {
		return "DataBaseException [errMessage=" + errMessage + ", sql=" + sql + ", param=" + Arrays.toString(param)
				+ "]";
	}
	
	
}
