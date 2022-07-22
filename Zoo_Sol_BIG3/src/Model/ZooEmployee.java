package Model;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;

import Utils.Gender;
import Utils.Job;

public class ZooEmployee extends Person{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private static int idCounter = 1;
	public static int getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(int idCounter) {
		ZooEmployee.idCounter = idCounter;
	}
	private String photo=null;
	private Job job;
	private String userName;
	private String password;
	public String getUserName() {
		return userName;
	}
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public ZooEmployee(String firstName, String lastName, LocalDate date, Gender gender, Section section, Job job) {
		super(idCounter++,firstName, lastName, date, gender, section);
		this.job = job;
		password=null;
		userName=null;
	}
	
	public ZooEmployee(int id) {
		super(id);
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName()+" ID: "+getId()+" Name: "+getFirstName()+" "+getLastName()+", Job: "+job;
	}

	

	

	
}
