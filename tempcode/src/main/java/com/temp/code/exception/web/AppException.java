package com.temp.code.exception.web;
/**
 * @author  ���� :lw E-mail:
 * @date ����ʱ�䣺2016��11��28�� ����12:33:46
 * @version 1.0 * @parameter 
 * @since 
 * @return 
 */
public class AppException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -881818796889464178L;
	public AppException(String msg){
		super(msg);
	}
	/**
	 * e �������쳣����Ϣ
	 * @param msg
	 */
	public AppException(String msg,Throwable e){
		super(msg,e);
	}
	/**
	 * �������쳣
	 * @param e
	 */
	
	public AppException(Throwable e){
		super(e);
	}
}
