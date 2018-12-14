package commons.inputcomponent.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Container 前台与后台交互结构
 * Author:CAOK
 * 2014-9-11 上午10:35:33 
 * Version 1.0
 */
public class Container implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2777690225906709601L;
	
	/**
	 * 整个容器是否只读 方便控制操作按钮 提高整体效率
	 */
	private boolean readOnly;
	/**
	 * 当前容器的布局  如果值为空 则使用前台设置的布局
	 */
	private String layout;
	/**
	 * 当前容器的子项  根据dealType来确定具体显示类型
	 */
	private List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
	
	
	public boolean isReadOnly() {
		return readOnly;
	}
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
	public String getLayout() {
		return layout;
	}
	public void setLayout(String layout) {
		this.layout = layout;
	}
	public List<Map<String, Object>> getItems() {
		return items;
	}
	public void setItems(List<Map<String, Object>> items) {
		this.items = items;
	}
	
	
}
