package Model;

import Utils.SnackType;

public class Drink extends Snack {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean sprinkel;
	private boolean  straw;
	private boolean iceCube;
	
	
	/**
	 * @param type
	 * @param snackName
	 * @param bar
	 * @param isFattening
	 * @param price
	 * @param sprinkel
	 * @param straw
	 * @param iceCube
	 */
	public Drink(SnackType type, String snackName, SnackBar bar, boolean isFattening, double price, boolean sprinkel,
			boolean straw, boolean iceCube) {
		super(type, snackName, bar, isFattening, price);
		this.sprinkel = sprinkel;
		this.straw = straw;
		this.iceCube = iceCube;
	}


	/**
	 * @return the sprinkel
	 */
	public boolean isSprinkel() {
		return sprinkel;
	}


	/**
	 * @return the straw
	 */
	public boolean isStraw() {
		return straw;
	}


	/**
	 * @return the iceCube
	 */
	public boolean isIceCube() {
		return iceCube;
	}


	/**
	 * @param sprinkel the sprinkel to set
	 */
	public void setSprinkel(boolean sprinkel) {
		this.sprinkel = sprinkel;
	}


	/**
	 * @param straw the straw to set
	 */
	public void setStraw(boolean straw) {
		this.straw = straw;
	}


	/**
	 * @param iceCube the iceCube to set
	 */
	public void setIceCube(boolean iceCube) {
		this.iceCube = iceCube;
	}


	@Override
	public String toString() {
		return super.toString()+ " [sprinkel=" + sprinkel + ", straw=" + straw + ", iceCube=" + iceCube + "]";
	}
	

	
	
}
