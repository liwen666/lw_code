package com.temp.code.exception.web;
/**
 * @author  ���� :lw E-mail:
 * @date ����ʱ�䣺2016��11��28�� ����10:53:59
 * @version 1.0 * @parameter 
 * @since 
 * @return 
 */
public class UncheckedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1074054068462676061L;
	public UncheckedException(String msg){
		super(msg);
	}
	/**
	 * e �������쳣����Ϣ
	 * @param msg
	 */
	public UncheckedException(String msg,Throwable e){
		super(msg,e);
	}
	/**
	 * �������쳣
	 * @param e
	 */
	
	public UncheckedException(Throwable e){
		super(e);
	}

}
