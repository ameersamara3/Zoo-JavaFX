package Exceptions;

public class EmptyFieldException  extends ErrorMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	public EmptyFieldException() {

		super("Empty Input!\nPlease Fill all the values");
	}
}
