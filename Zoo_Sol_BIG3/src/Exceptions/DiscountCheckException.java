package Exceptions;

public class DiscountCheckException  extends ErrorMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public DiscountCheckException() {

		super("cant be more then 25% discount your reach the max amount");
	}
}
