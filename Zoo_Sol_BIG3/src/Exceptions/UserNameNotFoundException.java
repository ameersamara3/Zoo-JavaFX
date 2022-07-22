package Exceptions;

public class UserNameNotFoundException  extends ErrorMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserNameNotFoundException() {

		super("Username isn't valid\nPlease enter a valid username");
	}
}
