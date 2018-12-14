package commons.inputcomponent.exception;

public class ColumnNotDefineException extends InputBaseException{

	private static final long serialVersionUID = 3583566093089790852L;

	public ColumnNotDefineException() {
		super();
	}

	public ColumnNotDefineException(String message) {
		super(message);
	}

	public ColumnNotDefineException(Throwable cause) {
		super(cause);
	}

	public ColumnNotDefineException(String message, Throwable cause) {
		super(message, cause);
	}
}
