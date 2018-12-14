package commons.inputcomponent.form.queryfrom.po;

import java.io.Serializable;

import com.tjhq.commons.inputcomponent.po.FormItem;

public class QueryFormItem extends FormItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String operator; //操作符号[这里为引用]
	
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
}
