package Model;

import Exceptions.AnimalsVisitsException;

public interface AnimalsVisits {
	
	public boolean visitcount(Person p) throws AnimalsVisitsException ;
	public boolean hasVistedAnimal(Person p);

	
	

}
