package Exceptions;

public class idNotFoundException  extends ErrorMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	public idNotFoundException() {

		super("ID does not exist!\nPlease Enter an existing ID");
	}
}
