package commons.setting.input.po;

import com.tjhq.commons.inputcomponent.po.Column;


public class RefColumn extends Column{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6492715991284056229L;

	private String idField;
	
	private String nameField;

	private String codeField;
	
	private String superIdField;
	
	private String refShowFormat;
	
	private boolean async = true;

	public String getIdField() {
		return idField;
	}

	public void setIdField(String idField) {
		this.idField = idField;
	}
	
	public String getNameField() {
		return nameField;
	}

	public void setNameField(String nameField) {
		this.nameField = nameField;
	}

	public String getCodeField() {
		return codeField;
	}

	public void setCodeField(String codeField) {
		this.codeField = codeField;
	}

	public String getSuperIdField() {
		return superIdField;
	}

	public void setSuperIdField(String superIdField) {
		this.superIdField = superIdField;
	}

	public String getRefShowFormat() {
		return refShowFormat;
	}

	public void setRefShowFormat(String refShowFormat) {
		this.refShowFormat = refShowFormat;
	}
	
	public boolean isAsync() {
		return async;
	}

	public void setAsync(boolean async) {
		this.async = async;
	}


	
}
