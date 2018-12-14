package commons.inputcomponent.exception;

public class InputBaseException extends RuntimeException{

	private static final long serialVersionUID = 3583566093089790852L;

	public InputBaseException() {
		super();
	}

	public InputBaseException(String message) {
		super(message);
	}

	public InputBaseException(Throwable cause) {
		super(cause);
	}

	public InputBaseException(String message, Throwable cause) {
		super(message, cause);
	}
}
