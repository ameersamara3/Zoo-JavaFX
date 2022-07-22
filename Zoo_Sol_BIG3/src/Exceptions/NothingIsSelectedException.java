package Exceptions;

public class NothingIsSelectedException  extends ErrorMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NothingIsSelectedException() {

		super("Nothing is selected!\nPlease select any of the values");
	}
}
