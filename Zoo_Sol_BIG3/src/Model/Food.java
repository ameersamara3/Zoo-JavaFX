package Model;

import Utils.SnackType;

public class Food extends Snack {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean plate;
	private boolean spaciy; 
	private boolean glutenFree; 

	/**
	 * @param type
	 * @param snackName
	 * @param bar
	 * @param isFattening
	 * @param price
	 * @param plate
	 * @param spaciy
	 * @param foodType
	 */
	public Food(SnackType type, String snackName, SnackBar bar, boolean isFattening, double price, boolean plate,
			boolean spaciy,boolean glutenFree) {
		super(type, snackName, bar, isFattening, price);
		this.plate = plate;
		this.spaciy = spaciy;
		this.glutenFree= glutenFree;
	}
	/**
	 * @return the plate
	 */
	public boolean isPlate() {
		return plate;
	}
	/**
	 * @return the spaciy
	 */
	public boolean isSpaciy() {
		return spaciy;
	}

	/**
	 * @param plate the plate to set
	 */
	public void setPlate(boolean plate) {
		this.plate = plate;
	}
	/**
	 * @param spaciy the spaciy to set
	 */
	public void setSpaciy(boolean spaciy) {
		this.spaciy = spaciy;
	}
	/**
	 * @param foodType the foodType to set
	 */

	@Override
	public String toString() {
		return super.toString()+ " [plate=" + plate + ", spaciy=" + spaciy + "]";
	}
	public boolean isGlutenFree() {
		return glutenFree;
	}
	public void setGlutenFree(boolean glutenFree) {
		this.glutenFree = glutenFree;
	}
	
	

}
