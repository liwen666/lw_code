package com.temp.code.exception.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.portlet.mvc.AbstractController;

/**
 * @author  ���� :lw E-mail:
 * @date ����ʱ�䣺2016��11��28�� ����11:05:56
 * @version 1.0 * @parameter 
 * @since 
 * @return 
 */
public class ExceptionTools {
	
	private static final Logger LOG=LoggerFactory.getLogger(ExceptionTools.class);
	/**
	 * Ĭ���쳣��Ϣ������
	 */
	private static ErrMsgMaker4Default errMsgMaker4Default = new ErrMsgMaker4Default();
	/**
	 * �û�����û����Ƶ��쳣��Ϣ������
	 */
	private static List<IErrMsgMaker> msgMakerList = new ArrayList<IErrMsgMaker>();
	
	/**
	 * �����û��Զ����쳣������
	 * @param list
	 * @return
	 * @see
	 */
	public static List<IErrMsgMaker> setMsgMakerList(List<IErrMsgMaker> list) {
		ExceptionTools.msgMakerList.addAll(list);
		return ExceptionTools.msgMakerList ;
	}
	/**
	 * �����쳣������־
	 * @param defaultErrMsg
	 * @param e
	 * @param log
	 * @return
	 * @see
	 */
	public static String captureException(String defaultErrMsg,Throwable e,Logger log){
		if(e!=null){
			LOG.error("�����쳣����Ϊ�գ�");
			return null;
		}
		if(log!=null){
			LOG.error("������־����Ϊ�գ�");
			return null;
		}
		if(e instanceof UncheckedException ){
			throw (UncheckedException)e;
		}else {
			for(IErrMsgMaker maker:msgMakerList){
				if(maker.isCause(e)){
					return maker.process(defaultErrMsg,e, log);
				}
			}
		}
		return errMsgMaker4Default.process(defaultErrMsg, e, log);
	}
	/**
	 * �����쳣������־������־����actionʵ��
	 * @param defaultErrMsg
	 * @param e
	 * @param log
	 * @param controller
	 * @see
	 */
//	public static void captureException4Controller(String defaultErrMsg,Throwable e,Logger log,AbstractController controller){
//		String errMsg = captureException(defaultErrMsg, e, log);
//		if(errMsg!=null){
//			controller.addActionError(errMsg);
//		}
//		return ;
//	}

	/**
	 * �ж��쳣�����Ƿ���ָ�����Ͳ�����
	 * @param e �쳣����
	 * @param exClass ָ������
	 * @return
	 * @see
	 */
	public static <T extends Throwable> boolean isNestedCauseException(Throwable e, Class<T> exClass) {
		if(exClass.isInstance(e)){
			return true;
		}else if (e.getCause()!=null){
			return isNestedCauseException(e.getCause(), exClass);
		}else{
			return false;
		}
	}
	/**
	 * ���쳣��Ϣ�в���ָ���쳣���Ͷ�Ӧ���쳣����
	 * @param e
	 * @param exClass
	 * @return
	 * @see
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Throwable> T getNestedCauseException(Throwable e, Class<T> exClass) {
		if(exClass.isInstance(e)){
			return (T)e;
		}else if (e.getCause()!=null){
			return getNestedCauseException(e.getCause(), exClass);
		}else{
			return null;
		}
	}
	
	
}
