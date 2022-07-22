package Exceptions;

public class EmployeeAlreadyHasAnAccountException  extends ErrorMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	public EmployeeAlreadyHasAnAccountException() {

		super("This employee already has an account!");
	}
}
