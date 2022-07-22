package Model;

import java.time.LocalDate;
import java.util.Date;

import Utils.AnimalFood;
import Utils.Gender;

public class Reptile extends Animal{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int idCounter = 1;
	public static int getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(int idCounter) {
		Reptile.idCounter = idCounter;
	}
	private boolean isDangerous;
	private boolean seasonSleep;
	
	public Reptile(String name, LocalDate date, AnimalFood food, Gender gender, Section section
			, boolean isDangerous,boolean seasonSleep) {
		super(idCounter++, name, date, food, gender, section);
		this.isDangerous = isDangerous;
		this.seasonSleep= seasonSleep;
	}
	
	public Reptile(int id) {
		super(id);
	}

	public boolean isDangerous() {
		return isDangerous;
	}

	public void setDangerous(boolean isDangerous) {
		this.isDangerous = isDangerous;
	}
	
	@Override
	public String toString() {
		return  super.toString()+ " Name: "+getName()+", Dangerous: "+isDangerous+", sleep at the sesson: "+this.isSeasonSleep();
	}

	public boolean isSeasonSleep() {
		return seasonSleep;
	}

	public void setSeasonSleep(boolean seasonSleep) {
		this.seasonSleep = seasonSleep;
	}
}
