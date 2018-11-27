package com.temp.code.exception.web;

import java.sql.SQLException;

import org.slf4j.Logger;

/**
 * @author  ���� :lw E-mail:
 * @date ����ʱ�䣺2016��11��28�� ����11:12:00
 * @version 1.0 * @parameter 
 * @since 
 * @return 
 */
public class ErrMsgMaker4Default implements IErrMsgMaker {

	@Override
	public boolean isCause(Throwable e) {
		return Throwable.class.isInstance(e);
	}

	@Override
	public String process(final String defalutErrMsg, final Throwable t,final Logger log) {
		String errMsg=defalutErrMsg ==null ? "":defalutErrMsg;
		if(t instanceof NullPointerException){
			errMsg=errMsg+"��ָ���쳣";
			log.error(errMsg,t);
		}else if(t instanceof AppException){   //Ӧ�ó�����������쳣
			errMsg = (t.getMessage())==null?errMsg:t.getMessage();
			if(t.getCause()!=null){
				log.error(errMsg,t);
			}else{
				log.debug(errMsg);
			}
		}else if (ExceptionTools.isNestedCauseException(t,SQLException.class)){
			errMsg=errMsg+":��̨���������쳣��";
		}else{
			if(!"".equals(errMsg)){
				errMsg=errMsg+":"+t.getMessage();
				
			}else{
				errMsg=t.getMessage();
			}
			log.error(errMsg,t);
		}
		return errMsg;
	}


}
