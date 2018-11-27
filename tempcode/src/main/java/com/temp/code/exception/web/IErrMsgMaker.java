package com.temp.code.exception.web;

import org.slf4j.Logger;

/**
 * @author  ���� :lw E-mail:
 * @date ����ʱ�䣺2016��11��28�� ����10:58:43
 * @version 1.0 * @parameter 
 * @since 
 * @return 
 */
public interface IErrMsgMaker {
	/**
	 * �ж��쳣������ԭ���Ƿ��Ǵ��쳣���������Դ�����쳣����
	 */
	public boolean isCause(Throwable e);
	/**
	 * Ĭ����Ϣ
	 * �쳣����
	 * ��־
	 * @param defalutErrMsg
	 * @param t
	 * @param log
	 * @return
	 * @see
	 */
	public String process(String defalutErrMsg,Throwable t,Logger log);
}
