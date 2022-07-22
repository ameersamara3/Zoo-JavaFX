package Model;

import java.io.Serializable;

import Utils.SnackType;

public class Snack implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static int idCounter = 1;
	public static int getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(int idCounter) {
		Snack.idCounter = idCounter;
	}
	private int id;
	private SnackType type;
	private SnackBar bar;
	private String snackName;
	private boolean isFattening;
	private double price;
	
	public Snack(SnackType type, String snackName, SnackBar bar, boolean isFattening, double price) {
		super();
		this.id = idCounter++;
		this.type = type;
		this.bar = bar;
		this.snackName = snackName;
		this.isFattening = isFattening;
		this.price = price;
	}
	
	public Snack(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SnackType getType() {
		return type;
	}

	public void setType(SnackType type) {
		this.type = type;
	}

	public String getSnackName() {
		return snackName;
	}

	public void setSnackName(String snackName) {
		this.snackName = snackName;
	}

	public boolean isFattening() {
		return isFattening;
	}

	public void setFattening(boolean isFattening) {
		this.isFattening = isFattening;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	

	public SnackBar getBar() {
		return bar;
	}

	public void setBar(SnackBar bar) {
		this.bar = bar;
	}

	@Override
	public String toString() {
		return  this.getClass().getSimpleName()+" [id=" + id + ", type=" + type + ", snackName=" + snackName + ", isFattening=" + isFattening
				+ ", price=" + price + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Snack other = (Snack) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
}
