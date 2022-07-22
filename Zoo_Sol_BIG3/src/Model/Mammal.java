package Model;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;

import Exceptions.AnimalsVisitsException;
import Utils.AnimalFood;
import Utils.Gender;
import Utils.Job;

public class Mammal extends Animal implements AnimalsVisits{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int idCounter = 1;
	public static int getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(int idCounter) {
		Mammal.idCounter = idCounter;
	}
	private boolean meatEater;
	private boolean canTakePic;

	
	public Mammal(String name, LocalDate date, AnimalFood food, Gender gender, Section section
			, boolean meatEater, boolean canTakePic) {
		super(idCounter++, name, date, food, gender, section);
		this.meatEater = meatEater;
		this.setCanTakePic(canTakePic);
	}
	
	public Mammal(int id) {
		super(id);
	}

	public boolean isMeatEater() {
		return meatEater;
	}

	public void setMeatEater(boolean meatEater) {
		this.meatEater = meatEater;
	}

	@Override
	public String toString() {
		return super.toString()+" Name: "+getName()+", Meat Eater: "+meatEater +",take a pic" +this.isCanTakePic();
	}

	public boolean isCanTakePic() {
		return canTakePic;
	}

	public void setCanTakePic(boolean canTakePic) {
		this.canTakePic = canTakePic;
	}

	@Override
	public boolean visitcount(Person p) throws AnimalsVisitsException {//we changed the type of this method to boolean so we can have more info
		if (getVisitCounter()<30) {
			setVisitCounter(getVisitCounter()+1);
			return hasVistedAnimal(p);
		}
		else {
			throw new AnimalsVisitsException();
		}
	}


	@Override
	public boolean hasVistedAnimal(Person p) {
			if(p instanceof ZooEmployee && getVisitCounter()>29) {
				ZooEmployee e= (ZooEmployee)p;
				if(!Zoo.getInstance().getAnimalTreatedByZooEmployee().containsKey(e)) {
					Zoo.getInstance().getAnimalTreatedByZooEmployee().put(e, new HashSet<Animal>());
					Zoo.getInstance().getAnimalTreatedByZooEmployee().get(e).add(this);
					return true;
				}
			}
			else if(p instanceof Visitor && getVisitCounter()<30) {
				Visitor v= (Visitor)p;
				if(!Zoo.getInstance().getAnimalVisitsByPeople().containsKey(v)) {
					Zoo.getInstance().getAnimalVisitsByPeople().put(v,new HashSet<Animal>());
					Zoo.getInstance().getAnimalVisitsByPeople().get(v).add(this);
					return true;
				}
				
		}
				
		return false;
	}
}
