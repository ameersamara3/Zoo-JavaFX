package Exceptions;

public class illegalPasswordException  extends ErrorMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	public illegalPasswordException() {

		super("illegal password!\nPassword should pass the following condtions:\n1.at least 8 characters\n2.at least 1 number\n3.at least one capital letter");
	}
}
