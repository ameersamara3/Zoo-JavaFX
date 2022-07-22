package Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import Exceptions.AnimalsVisitsException;
import Exceptions.DiscountCheckException;
import Exceptions.DuplicatedValues;
import Exceptions.EmployeeAlreadyHasAnAccountException;
import Exceptions.ErrorMessage;
import Exceptions.MaximumCapcityException;
import Exceptions.UserNameNotFoundException;
import Exceptions.UsernameAlreadyExistsException;
import Exceptions.idNotFoundException;
import Exceptions.wrongPasswordException;
import Utils.Gender;
import Utils.MyFileLogWriter;

public class Zoo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Zoo zoo = null;

	private HashMap<Integer, ZooEmployee> employees;
	private HashMap<Integer, Visitor> visitors;
	private HashMap<Integer, Mammal> mammals;
	private HashMap<Integer, Reptile> reptiles;
	private HashMap<Integer, Bird> birds;
	private HashMap<Integer, Section> sections;
	private HashMap<Integer, Snack> snacks;
	private HashMap<Integer, SnackBar> bars;
	private HashMap<ZooEmployee, HashSet<Animal>> animalTreatedByZooEmployee;
	private HashMap<Visitor, HashSet<Animal>> AnimalVisitsByPeople;
	private HashMap<String, Integer> loginInfo;// Username -> Employee(includes password)
	private HashMap<String, Double> staticMap;
	private HashSet<String> avoidDuplicates;// this hashset avoids making a duplicate visitor that is an employee

	public static Zoo getInstance() {
		if (zoo == null)
			zoo = new Zoo();
		return zoo;
	}

	private Zoo() {
		employees = new HashMap<>();
		visitors = new HashMap<>();
		mammals = new HashMap<>();
		reptiles = new HashMap<>();
		birds = new HashMap<>();
		sections = new HashMap<>();
		snacks = new HashMap<>();
		bars = new HashMap<>();
		animalTreatedByZooEmployee = new HashMap<>();
		AnimalVisitsByPeople = new HashMap<>();
		loginInfo = new HashMap<>();
		staticMap = new HashMap<>();
		avoidDuplicates = new HashSet<>();
		staticMap.put("Mammal", 1.0);
		staticMap.put("Bird", 1.0);
		staticMap.put("Reptile", 1.0);
		staticMap.put("Visitor", 1.0);
		staticMap.put("Employee", 1.0);
		staticMap.put("Section", 1.0);
		staticMap.put("Snack", 1.0);
		staticMap.put("SnackBar", 1.0);
		staticMap.put("ZooPercentage", 0.0);
	}

	public HashMap<String, Double> getStaticMap() {
		return staticMap;
	}

	public void setStaticMap(HashMap<String, Double> staticMap) {
		this.staticMap = staticMap;
	}

	public HashMap<Integer, ZooEmployee> getEmployees() {
		return employees;
	}

	public void setEmployees(HashMap<Integer, ZooEmployee> employees) {
		this.employees = employees;
	}

	public HashMap<Integer, Visitor> getVisitors() {
		return visitors;
	}

	public void setVisitorsById(HashMap<Integer, Visitor> visitors) {
		this.visitors = visitors;
	}

	public HashMap<Integer, Mammal> getMammals() {
		return mammals;
	}

	public void setMammalsById(HashMap<Integer, Mammal> mammals) {
		this.mammals = mammals;
	}

	public HashMap<Integer, Reptile> getReptiles() {
		return reptiles;
	}

	public void setReptilesById(HashMap<Integer, Reptile> reptiles) {
		this.reptiles = reptiles;
	}

	public HashMap<Integer, Bird> getBirds() {
		return birds;
	}

	public void setBirdsById(HashMap<Integer, Bird> birds) {
		this.birds = birds;
	}

	public HashMap<Integer, Section> getSections() {
		return sections;
	}

	public void setSections(HashMap<Integer, Section> sections) {
		this.sections = sections;
	}

	public HashMap<Integer, Snack> getSnacks() {
		return snacks;
	}

	public void setSnacksById(HashMap<Integer, Snack> snacks) {
		this.snacks = snacks;
	}

	public HashMap<Integer, SnackBar> getBars() {
		return bars;
	}

	public void setBars(HashMap<Integer, SnackBar> bars) {
		this.bars = bars;
	}

	/**
	 * @return the animalTreatedByZooEmployee
	 */
	public HashMap<ZooEmployee, HashSet<Animal>> getAnimalTreatedByZooEmployee() {
		return animalTreatedByZooEmployee;
	}

	/**
	 * @param animalTreatedByZooEmployee the animalTreatedByZooEmployee to set
	 */
	public void setAnimalTreatedByZooEmployee(HashMap<ZooEmployee, HashSet<Animal>> animalTreatedByZooEmployee) {
		this.animalTreatedByZooEmployee = animalTreatedByZooEmployee;
	}

	/**
	 * @return the animalVisitsByPeople
	 */
	public HashMap<Visitor, HashSet<Animal>> getAnimalVisitsByPeople() {
		return AnimalVisitsByPeople;
	}

	/**
	 * @param animalVisitsByPeople the animalVisitsByPeople to set
	 */
	public void setAnimalVisitsByPeople(HashMap<Visitor, HashSet<Animal>> animalVisitsByPeople) {
		AnimalVisitsByPeople = animalVisitsByPeople;
	}

	public boolean addEmployee(ZooEmployee employee) {
		if (employee == null)
			return false;
		if (!getEmployees().containsValue(employee)) {
			{
				avoidDuplicates
						.add(employee.getFirstName() + " " + employee.getLastName() + " " + employee.getBirthDay());
				getEmployees().put(employee.getId(), employee);
			}
			employee.getSection().getEmployees().add(employee);
		} else
			return false;

		return true;
	}

	public boolean addVisitor(Visitor v) throws DuplicatedValues {
		if (v == null)
			return false;

		if (!getVisitors().containsValue(v)) {
			if (!avoidDuplicates.contains(v.getFirstName() + " " + v.getLastName() + " " + v.getBirthDay()))// still
																											// O<1>
				getVisitors().put(v.getId(), v);
			else
				throw new DuplicatedValues();
		} else
			return false;
		return true;
	}

	public boolean addMammalById(Mammal m) {
		if (m == null)
			return false;

		if (!getMammals().containsValue(m)) {
			getMammals().put(m.getId(), m);
			m.getSection().getMammals().add(m);
		} else
			return false;
		return true;
	}

	public boolean addReptileById(Reptile r) {
		if (r == null)
			return false;

		if (!getReptiles().containsValue(r)) {
			getReptiles().put(r.getId(), r);
			r.getSection().getReptiles().add(r);
		} else
			return false;
		return true;
	}

	public boolean addBirdById(Bird b) {
		if (b == null)
			return false;

		if (!getBirds().containsValue(b)) {
			getBirds().put(b.getId(), b);

			b.getSection().getBirds().add(b);
		} else
			return false;
		return true;
	}

	public boolean addSection(Section s) {
		if (s == null)
			return false;
		if (!getSections().containsKey(s.getId()))
			getSections().put(s.getId(), s);
		else
			return false;

		return true;
	}

	public boolean addSnack(Snack s) {
		if (s == null)
			return false;

		if (!getSnacks().containsValue(s))
			getSnacks().put(s.getId(), s);
		else
			return false;
		s.getBar().getSnacks().add(s);
		return true;
	}

	public boolean addSnackBar(SnackBar sb, Section s) {
		if (sb == null || s == null)
			return false;

		if (!getBars().containsValue(sb))
			getBars().put(sb.getId(), sb);
		else
			return false;

		s.setBar(sb);
		return true;
	}

	public boolean removeEmployee(ZooEmployee employee) {
		if (employee == null)
			return false;

		getEmployees().remove(employee.getId());
		employee.getSection().getEmployees().remove(employee);
		loginInfo.remove(employee.getUserName());
		return true;
	}

	public boolean removeVisitor(Visitor v) {
		if (v == null)
			return false;
		avoidDuplicates.remove(v.getFirstName() + " " + v.getLastName() + " " + v.getBirthDay());
		getVisitors().remove(v.getId());
		v.getSection().getVisitors().remove(v);

		return true;
	}

	public boolean removeMammal(Mammal m) {
		if (m == null)
			return false;

		getMammals().remove(m.getId());
		m.getSection().getMammals().remove(m);
		return true;
	}

	public boolean removeReptile(Reptile r) {
		if (r == null)
			return false;

		getReptiles().remove(r.getId());
		r.getSection().getReptiles().remove(r);
		return true;
	}

	public boolean removeBird(Bird b) {
		if (b == null)
			return false;

		getBirds().remove(b.getId());
		b.getSection().getBirds().remove(b);
		return true;
	}

	public boolean removeSection(Section oldSection, Section newSection) {
		if (oldSection == null || newSection == null)
			return false;

		newSection.getEmployees().addAll(oldSection.getEmployees());
		newSection.getVisitors().addAll(oldSection.getVisitors());
		newSection.getMammals().addAll(oldSection.getMammals());
		newSection.getReptiles().addAll(oldSection.getReptiles());
		newSection.getBirds().addAll(oldSection.getBirds());
		removeSnackBar(oldSection.getBar());

		getSections().remove(oldSection.getId());

		return true;
	}

	public boolean removeSnackBar(SnackBar sb) {
		if (sb == null)
			return false;

		sb.getSection().setBar(null);
		getBars().remove(sb.getId());
		return true;
	}

	public boolean removeSnack(Snack s) {
		if (s == null)
			return false;

		for (SnackBar sb : getBars().values()) {
			sb.getSnacks().remove(s);
		}

		getSnacks().remove(s.getId());
		return true;
	}

	public ZooEmployee getRealEmployee(int id) {
		return getEmployees().get(id);
	}

	public Visitor getRealVisitor(int id) {

		return getVisitors().get(id);
	}

	public Mammal getRealMammal(int id) {
		return getMammals().get(id);
	}

	public Reptile getRealReptile(int id) {

		return getReptiles().get(id);
	}

	public Bird getRealBird(int id) {

		return getBirds().get(id);
	}

	public Section getRealSection(int id) {
		return getSections().get(id);
	}

	public SnackBar getRealSnackBar(int id) {
		return getBars().get(id);
	}

	public Snack getRealSnack(int id) {

		return getSnacks().get(id);
	}

	public double checkTotalRevenue() {
		double revenue = 0;
		for (Section s : getSections().values()) {
			revenue += s.getTodayRevenue();
			try {
				revenue += s.getBar().getProfit() * SnackBar.getZooPercentage();
			} // added catch in case that there's no snackbar for this section
			catch (NullPointerException e) {
			}

		}
		//MyFileLogWriter.println("Zoo Revenue: " + revenue);
		return revenue;
	}

	public boolean newVisitorInZoo(Visitor v, Section s) throws DuplicatedValues {
		boolean isValid = true;
		if (v == null || s == null)
			isValid = false;

		if (!addVisitor(v))
			isValid = false;

		if (isValid && !s.getVisitors().contains(v)) {
			s.getVisitors().add(v);
			v.setSection(s);
			double price = v.checkTicketPrice();
			s.setTodayRevenue(s.getTodayRevenue() + price);
			MyFileLogWriter.println("New " + v + " Paid " + price + " NIS");
			return true;
		}
		MyFileLogWriter.println(v + " didn't enter the zoo");
		return false;
	}

	public ArrayList<Animal> getAllAnimalsBySectionMaxVisits(Section s) {
		if (s == null) {
			return null;
		}
		ArrayList<Animal> animals = new ArrayList<Animal>();

		for (Animal a : s.getBirds()) {

			animals.add(a);

		}

		for (Animal a : s.getReptiles()) {
			animals.add(a);

		}

		for (Animal a : s.getMammals()) {
			animals.add(a);

		}

		animals.sort(new Comparator<Animal>() {

			@Override
			public int compare(Animal o1, Animal o2) {
				String o1Class = o1.getClass().getSimpleName();
				String o2Class = o2.getClass().getSimpleName();
				if (o1Class.equals(o2Class)) {
					Integer count1 = o1.getVisitCounter();
					Integer count2 = o2.getVisitCounter();
					return count1.compareTo(count2);
				} else
					return o1Class.compareTo(o2Class);
			}

		});
		return animals;

	}

	@SuppressWarnings("null")
	public ArrayList<Animal> allAnimalsByWorker(ZooEmployee employee) {

		ArrayList<Animal> treatsBy = new ArrayList<Animal>();
		if (getEmployees().containsValue(employee)) {

			HashSet<Animal> animalsOfEmployee = getAnimalTreatedByZooEmployee().get(employee);

			if (animalsOfEmployee == null) {
				return null;
			}
			for (Bird b : getBirds().values()) {
				if (!animalsOfEmployee.contains(b) && (!b.isCanFly()))
					treatsBy.add(b);
			}
			for (Mammal m : getMammals().values()) {
				if (!animalsOfEmployee.contains(m) && (!m.isMeatEater()))
					treatsBy.add(m);
			}
			for (Reptile r : getReptiles().values()) {
				if (!animalsOfEmployee.contains(r) && (r.isDangerous()))
					treatsBy.add(r);
			}

			treatsBy.sort(null);

		}
		return treatsBy;
	}

	public ArrayList<Snack> findAllSnackByWorker(SnackBar sb) {
		ArrayList<Snack> snacks = new ArrayList<Snack>();

		for (Snack s : sb.getSnacks()) {
			if (s instanceof Drink) {
				Drink d = (Drink) s;
				if (!d.isSprinkel() && d.isIceCube()) {

					snacks.add(d);
				}
			} else if (s instanceof Food) {
				Food f = (Food) s;
				if (f.isSpaciy() && !f.isPlate()) {
					snacks.add(f);
				}
			}
		}

		snacks.sort(new Comparator<Snack>() {

			@Override
			public int compare(Snack o1, Snack o2) {

				return o1.getSnackName().compareTo(o2.getSnackName());
			}

		});

		return snacks;

	}

	public ArrayList<Reptile> reptilesSleepAtSeasson() {
		ArrayList<Reptile> reptiles = new ArrayList<Reptile>();
		if (getReptiles() != null) {

			for (Reptile r : getReptiles().values()) {
				if (r.isSeasonSleep() && (!r.isDangerous())) {
					reptiles.add(r);
				}
			}
		}

		reptiles.sort(new Comparator<Reptile>() {

			@Override
			public int compare(Reptile o1, Reptile o2) {
				if (o1.getName().equals(o2.getName())) {
					return o1.getGender().compareTo(o2.getGender());
				}
				return o1.getName().compareTo(o2.getName());
			}

		});
		return reptiles;

	}

	public TreeMap<Visitor, Double> geAllDiscountAmount() {

		TreeMap<Visitor, Double> Discount = new TreeMap<Visitor, Double>(new Comparator<Visitor>() {

			@Override
			public int compare(Visitor o1, Visitor o2) {
				Double o1Price = o1.getTicket().getValue() - o1.checkTicketPrice();
				Double o2Price = o2.getTicket().getValue() - o2.checkTicketPrice();
				return o1Price.compareTo(o2Price);
			}
		});

		for (Visitor v : getVisitors().values()) {

			Discount.put(v, v.getTicket().getValue() - v.checkTicketPrice());

		}

		return Discount;
	}

	public Section getMaxVisitorsVSMaxWorkers() {
		PriorityQueue<Section> pqSection = new PriorityQueue<Section>(new Comparator<Section>() {

			@Override
			public int compare(Section o1, Section o2) {
				int sub1 = o1.getVisitors().size() - o1.getEmployees().size();
				int sub2 = o2.getVisitors().size() - o2.getEmployees().size();

				return sub2 - sub1;
			}

		});

		for (Section s : getSections().values()) {

			pqSection.add(s);

		}
		return pqSection.poll();
	}

	public void serialize() {//saving
		try {
			staticMap.put("Mammal", (double) Mammal.getIdCounter());
			staticMap.put("Bird", (double) Bird.getIdCounter());
			staticMap.put("Reptile", (double) Reptile.getIdCounter());
			staticMap.put("Visitor", (double) Visitor.getIdCounter());
			staticMap.put("Employee", (double) ZooEmployee.getIdCounter());
			staticMap.put("Section", (double) Section.getIdCounter());
			staticMap.put("Snack", (double) Snack.getIdCounter());
			staticMap.put("SnackBar", (double) SnackBar.getIdCounter());
			staticMap.put("ZooPercentage", SnackBar.getZooPercentage());
			FileOutputStream fileout = new FileOutputStream("Zoo.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileout);
			out.writeObject(this);
			out.close();
			fileout.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deserialize() {//loading
		try {
			FileInputStream filein = new FileInputStream("Zoo.ser");
			ObjectInputStream in = new ObjectInputStream(filein);
			zoo = (Zoo) in.readObject();
			in.close();
			filein.close();
			return;
		} catch (IOException i) {
			zoo = new Zoo();
			serialize();
			return;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void resetDataBase() {
		zoo = new Zoo();
		Zoo.getInstance().resetDataBase2();
	}

	public void resetDataBase2() {
		staticDeserialize();
	}

	public void staticDeserialize() {//loads the static variables

		Mammal.setIdCounter(staticMap.get("Mammal").intValue());
		Bird.setIdCounter(staticMap.get("Bird").intValue());
		Reptile.setIdCounter(staticMap.get("Reptile").intValue());
		Visitor.setIdCounter(staticMap.get("Visitor").intValue());
		ZooEmployee.setIdCounter(staticMap.get("Employee").intValue());
		Section.setIdCounter(staticMap.get("Section").intValue());
		Snack.setIdCounter(staticMap.get("Snack").intValue());
		SnackBar.setIdCounter(staticMap.get("SnackBar").intValue());
		SnackBar.setZooPercentage(staticMap.get("ZooPercentage").intValue());
	}

	public void signUp(String username, String password, int id)//adds the account to the database
			throws UsernameAlreadyExistsException, idNotFoundException, EmployeeAlreadyHasAnAccountException {
		if (loginInfo.containsKey(username))
			throw new UsernameAlreadyExistsException();
		if (getRealEmployee(id) == null)
			throw new idNotFoundException();
		if (getRealEmployee(id).getPassword() != null)
			throw new EmployeeAlreadyHasAnAccountException();
		getRealEmployee(id).setPassword(password);
		getRealEmployee(id).setUserName(username);
		loginInfo.put(username, id);
		View.MyMethods.setEmployee(getRealEmployee(id));
	}

	public boolean signInAdmin(String username, String password) throws wrongPasswordException {//checks if the account is an admin
		if (username.equals("admin")) {
			if (password.equals("admin"))
				return true;
			else
				throw new wrongPasswordException();
		}
		return false;
	}
	//checks if the account is an employee account that is already in the database
	public void signIn(String username, String password) throws UserNameNotFoundException, wrongPasswordException {
		if (!loginInfo.containsKey(username))
			throw new UserNameNotFoundException();
		if (getRealEmployee(loginInfo.get(username)).getPassword().equals(password))
			View.MyMethods.setEmployee(getRealEmployee(loginInfo.get(username)));
		else
			throw new wrongPasswordException();
	}
}
