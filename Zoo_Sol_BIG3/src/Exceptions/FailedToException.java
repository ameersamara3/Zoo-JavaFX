package Exceptions;

public class FailedToException  extends ErrorMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FailedToException(String message) {

		super("Failed to "+message+"!");
	}
}
