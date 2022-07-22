package Exceptions;

public class UsernameAlreadyExistsException  extends ErrorMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UsernameAlreadyExistsException() {

		super("This username already exists!\nPlease Enter a another username");
	}
}
