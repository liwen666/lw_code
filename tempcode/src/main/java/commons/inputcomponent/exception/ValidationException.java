package commons.inputcomponent.exception;

public class ValidationException extends InputBaseException{
	
	private static final long serialVersionUID = 3583566093089790852L;

	public ValidationException() {
		super();
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}
}
