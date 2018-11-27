package com.temp.code.exception.app;
/**
 * @author  ���� : lw E-mail:
 * @date ����ʱ�䣺2016��11��28�� ����8:50:41
 * @version 1.0 * @parameter 
 * @since 
 * @return 
 */
public class ControlException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1673899116480947580L;
	/**
	 * ������
	 */
	private final String errCode="";
	/**
	 * ������Ϣ
	 */
	private final String errMessage;
	public ControlException(String errCode, String errMessage) {
		super(errMessage);
		this.errMessage = errMessage;
	}
	public ControlException(String errCode, String errMessage,Throwable e) {
		super(errMessage,e);
		this.errMessage = errMessage;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getErrCode() {
		return errCode;
	}
	public String getErrMessage() {
		return errMessage;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((errCode == null) ? 0 : errCode.hashCode());
		result = prime * result + ((errMessage == null) ? 0 : errMessage.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ControlException other = (ControlException) obj;
		if (errCode == null) {
			if (other.errCode != null)
				return false;
		} else if (!errCode.equals(other.errCode))
			return false;
		if (errMessage == null) {
			if (other.errMessage != null)
				return false;
		} else if (!errMessage.equals(other.errMessage))
			return false;
		return true;
	}
	
	
}
