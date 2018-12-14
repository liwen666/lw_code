package commons.mongodb.po;

import org.springframework.data.domain.Sort.Direction;


public class MongoOrderDTO {

	private String key;
	private Direction order;
	private String creatTime;
	private String guid;
	private int orderNO;
	public MongoOrderDTO() {
		super();
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	public Direction getOrder() {
		return order;
	}

	public void setOrder(Direction order) {
		this.order = order;
	}

	public String getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public int getOrderNO() {
		return orderNO;
	}
	public void setOrderNO(int orderNO) {
		this.orderNO = orderNO;
	}
	
}
