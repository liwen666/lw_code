package commons.inputcomponent.exception;

public class NullableException extends InputBaseException{

	private static final long serialVersionUID = 3583566093089790852L;

	public NullableException() {
		super();
	}

	public NullableException(String message) {
		super(message);
	}

	public NullableException(Throwable cause) {
		super(cause);
	}

	public NullableException(String message, Throwable cause) {
		super(message, cause);
	}
}
