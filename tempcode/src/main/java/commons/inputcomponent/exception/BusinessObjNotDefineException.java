package commons.inputcomponent.exception;

public class BusinessObjNotDefineException extends InputBaseException{

	private static final long serialVersionUID = 3583566093089790852L;

	public BusinessObjNotDefineException() {
		super();
	}

	public BusinessObjNotDefineException(String message) {
		super(message);
	}

	public BusinessObjNotDefineException(Throwable cause) {
		super(cause);
	}

	public BusinessObjNotDefineException(String message, Throwable cause) {
		super(message, cause);
	}
}
