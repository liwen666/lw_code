package commons.mongodb.po;

import com.longtu.framework.log.enums.ConditionTypeEnum;

public class MongoConditionDTO {
	private String key;
	private String value;
	private MongoConditionType type;
	
	
	
	
	
	
	
	
	
	
	public MongoConditionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public MongoConditionType getType() {
		return type;
	}
	public void setType(MongoConditionType type) {
		this.type = type;
	}
	

}
