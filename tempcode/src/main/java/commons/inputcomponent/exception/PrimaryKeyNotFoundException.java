package commons.inputcomponent.exception;

public class PrimaryKeyNotFoundException extends InputBaseException{

	private static final long serialVersionUID = 3583566093089790852L;

	public PrimaryKeyNotFoundException() {
		super();
	}

	public PrimaryKeyNotFoundException(String message) {
		super(message);
	}

	public PrimaryKeyNotFoundException(Throwable cause) {
		super(cause);
	}

	public PrimaryKeyNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
