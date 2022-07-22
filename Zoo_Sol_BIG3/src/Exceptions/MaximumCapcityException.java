package Exceptions;

public class MaximumCapcityException extends ErrorMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MaximumCapcityException() {
		super("you reach to less then 50% of capacty canot move to other section");
	}

}
