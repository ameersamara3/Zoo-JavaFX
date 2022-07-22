package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import Utils.Gender;

public abstract class Person implements Comparable<Person> , Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String firstName;
	private String lastName;
	private LocalDate birthDay;
	private Gender gender;
	private Section section;
	
	public Person(int id, String firstName, String lastName, LocalDate date, Gender gender, Section section) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDay = date;
		this.gender = gender;
		this.section = section;
	}
	
	public Person(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	
	@Override
	public int compareTo(Person o) {
		if(this.getFirstName().equals(o.getFirstName()))
			
			return this.getLastName().compareTo(o.getLastName()) ; 
		else
			return this.getFirstName().compareTo(o.getFirstName());
	}

	
	
	
}
