package commons.inputcomponent.form.queryfrom.po;

import java.io.Serializable;

import com.tjhq.commons.inputcomponent.po.Form;

public class QueryForm extends Form implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3529825431964041171L;
	
	/**
	 * 是否显示标题
	 */
	private boolean showTitle = true;

	public boolean getShowTitle() {
		return showTitle;
	}

	public void setShowTitle(boolean showTitle) {
		this.showTitle = showTitle;
	}
	
	

}
