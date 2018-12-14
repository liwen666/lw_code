package commons.inputcomponent.po;

import java.io.Serializable;

public class OrderColumn extends Column implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ORDER_TYPE_ASC = "asc";
	public static final String ORDER_TYPE_DESC = "desc";
	
	private String orderType = ORDER_TYPE_ASC;

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
}
