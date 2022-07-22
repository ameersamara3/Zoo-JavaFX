import autopilot.AutoUtils;
import autopilot.OutputDocument;
import autopilot.Section;
import Model.*;
import Utils.AnimalFood;
import Utils.CSVExporter;
import Utils.Discount;
import Utils.Gender;
import Utils.Job;
import Utils.MyFileLogWriter;
import Utils.SnackType;
import Utils.TicketType;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.util.*;
import java.util.regex.Pattern;

import javax.management.modelmbean.ModelMBean;

import Exceptions.AnimalsVisitsException;
import Exceptions.DuplicatedValues;
import Exceptions.MaximumCapcityException;

public class Main {
	static Snack s;
	private static Zoo zoo = Zoo.getInstance();

	private static OutputDocument document = new OutputDocument();
	private static Map<String, Command> commands = new HashMap<>();
	private static Map<String, Section> sections = new HashMap<>();
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	private static final String PIPELINE = Pattern.quote("|");
	private static final String OUTPUT_FILE = "output.txt";
	private static final String AP_FILE = "ap_test_output.xml";

	static {

		Section space = document.nextSection();
		Section finish = document.nextSection();

		Section sectionSection = document.nextSection();
		Section barSection = document.nextSection();
		Section empSection = document.nextSection();
		Section visitorSection = document.nextSection();
		Section mammalSection = document.nextSection();
		Section reptielSection = document.nextSection();
		Section birdSection = document.nextSection();
		Section snackSection = document.nextSection();

		Section removeEmp = document.nextSection();
		Section removeVisitor = document.nextSection();
		Section removeMammal = document.nextSection();
		Section removeReptile = document.nextSection();
		Section removeBird = document.nextSection();
		Section removeSection = document.nextSection();
		Section removeSnack = document.nextSection();
		Section removeSnackBar = document.nextSection();

		Section getSectionDetails = document.nextSection();
		Section moveVisitorToSection = document.nextSection();
		Section purchaseSnack = document.nextSection();
		Section checkTotalRevenue = document.nextSection();
		Section getAllAnimalsBySectionMaxVisits = document.nextSection();
		Section allAnimalsByWorker = document.nextSection();
		Section findAllSnackByWorker = document.nextSection();
		Section reptilesSleepAtSeasson = document.nextSection();
		Section geAllDiscountAmount = document.nextSection();
		Section getMaxVisitorsVSMaxWorkers = document.nextSection();
		Section VisitAnimalsByVisitor = document.nextSection();
		Section TreatAnimalByWorker = document.nextSection();

		sections.put("addSection", sectionSection);
		sections.put("addSnackBar", barSection);
		sections.put("addEmployee", empSection);
		sections.put("addVisitor", visitorSection);
		sections.put("addMammal", mammalSection);
		sections.put("addReptile", reptielSection);
		sections.put("addBird", birdSection);
		sections.put("addSnack", snackSection);

		sections.put("removeEmployee", removeEmp);
		sections.put("removeVisitor", removeVisitor);
		sections.put("removeMammal", removeMammal);
		sections.put("removeReptile", removeReptile);
		sections.put("removeBird", removeBird);
		sections.put("removeSection", removeSection);
		sections.put("removeSnackBar", removeSnackBar);
		sections.put("removeSnack", removeSnack);

		sections.put("getSectionDetails", getSectionDetails);
		sections.put("moveVisitorToSection", moveVisitorToSection);
		sections.put("purchaseSnack", purchaseSnack);
		sections.put("checkTotalRevenue", checkTotalRevenue);
		sections.put("getAllAnimalsBySectionMaxVisits", getAllAnimalsBySectionMaxVisits);
		sections.put("allAnimalsByWorker", allAnimalsByWorker);
		sections.put("findAllSnackByWorker", findAllSnackByWorker);
		sections.put("reptilesSleepAtSeasson", reptilesSleepAtSeasson);
		sections.put("geAllDiscountAmount", geAllDiscountAmount);
		sections.put("getMaxVisitorsVSMaxWorkers", getMaxVisitorsVSMaxWorkers);
		sections.put("VisitAnimalsByVisitor", VisitAnimalsByVisitor);
		sections.put("TreatAnimalByWorker", TreatAnimalByWorker);

		sections.put("space", space);
		sections.put("finish", finish);

		commands.put("space", (section, args) -> {
			MyFileLogWriter.println("");
		});

		commands.put("finish", (section, args) -> {
			MyFileLogWriter.saveLogFile();
		});

		// non binary args: (string name,string specialization, int numberOfDepartments)
		commands.put("addSection", (section, args) -> {
			Model.Section s = new Model.Section(args[0], Integer.parseInt(args[1]));
			if (zoo.addSection(s))
				MyFileLogWriter.println("successfully added Section " + args[0]);
			else
				MyFileLogWriter.println("failed to add Section " + args[0]);

		});

		// non binary args: (string firstName, string lastName, string specialization,
		// int subID)
		commands.put("addSnackBar", (section, args) -> {
			SnackBar.setZooPercentage(0.2);
			Model.Section s = zoo.getRealSection(Integer.parseInt(args[1]));
			SnackBar sb = new SnackBar(args[0], s);
			if (zoo.addSnackBar(sb, s))
				MyFileLogWriter.println("successfully added SnackBar " + args[0]);
			else
				MyFileLogWriter.println("failed to add SnackBar " + args[0]);
		});

		// non binary args: (string firstName, string lastName, string treatment, int
		// subID)
		commands.put("addEmployee", (section, args) -> {
			LocalDate date = LocalDate.of(Integer.parseInt(args[2]), Integer.parseInt(args[3]),
					Integer.parseInt(args[4]));
			ZooEmployee e = new ZooEmployee(args[0], args[1], date, Gender.valueOf(args[5]),
					zoo.getRealSection(Integer.parseInt(args[6])), Job.valueOf(args[7]));

			if (zoo.addEmployee(e)) {
				MyFileLogWriter.println("successfully added Employee " + args[0] + " " + args[1]);
				try {
					zoo.getRealBird(Integer.parseInt(args[8])).visitcount(e);
					zoo.getRealMammal(Integer.parseInt(args[9])).visitcount(e);
				} catch (AnimalsVisitsException e1) {
					MyFileLogWriter.println(e1.getMessage());
				}
			} else
				MyFileLogWriter.println("failed to add Employee " + args[0] + " " + args[1]);

		});

		// non binary args: (string firstName, string lastName, int status, int subID)
		commands.put("addVisitor", (section, args) -> {
			LocalDate date = LocalDate.of(Integer.parseInt(args[2]), Integer.parseInt(args[3]),
					Integer.parseInt(args[4]));
			int year = Integer.parseInt(args[2]);
			TicketType ticket;

			if (year < 1960)
				ticket = TicketType.Old;
			else if (year >= 1960 && year < 1990)
				ticket = TicketType.Adult;
			else if (year >= 1990 && year < 2002)
				ticket = TicketType.Student;
			else if (year >= 2002 && year < 2005)
				ticket = TicketType.Soldiar;
			else
				ticket = TicketType.Child;

			Visitor v = new Visitor(args[0], args[1], date, Gender.valueOf(args[5]), ticket, Discount.valueOf(args[7]));

			try {
				if (zoo.newVisitorInZoo(v, zoo.getRealSection(Integer.parseInt(args[6])))) {
					MyFileLogWriter.println("successfully added Visitor " + args[0] + " " + args[1]);
					try {
						zoo.getRealBird(Integer.parseInt(args[8])).visitcount(v);
						zoo.getRealMammal(Integer.parseInt(args[9])).visitcount(v);
					} catch (AnimalsVisitsException e) {
						MyFileLogWriter.println(e.getMessage());
					}
				} else
					MyFileLogWriter.println("failed to add Visitor " + args[0] + " " + args[1]);
			} catch (DuplicatedValues e) {
				MyFileLogWriter.println(e.getMessage());
			}

		});

		// non binary args: (string name, strings symptoms)
		commands.put("addMammal", (section, args) -> {
			LocalDate date = LocalDate.of(Integer.parseInt(args[1]), Integer.parseInt(args[2]),
					Integer.parseInt(args[3]));
			Mammal m = new Mammal(args[0], date, AnimalFood.valueOf(args[4]), Gender.valueOf(args[5]),
					zoo.getRealSection(Integer.parseInt(args[6])), (args[7].equals("TRUE") ? true : false),
					args[8].equals("TRUE") ? true : false);
			m.setVisitCounter(Integer.parseInt(args[9]));
			if (zoo.addMammalById(m))
				MyFileLogWriter.println("successfully added Mammal " + args[0]);
			else
				MyFileLogWriter.println("failed to add Mammal " + args[0]);
		});

		// non binary args: (int patientID, int docID, int DiseaseID, String note)
		commands.put("addReptile", (section, args) -> {
			LocalDate date = LocalDate.of(Integer.parseInt(args[1]), Integer.parseInt(args[2]),
					Integer.parseInt(args[3]));
			Reptile r = new Reptile(args[0], date, AnimalFood.valueOf(args[4]), Gender.valueOf(args[5]),
					zoo.getRealSection(Integer.parseInt(args[6])), (args[7].equals("TRUE") ? true : false),
					args[8].equals("TRUE") ? true : false);
			r.setVisitCounter(Integer.parseInt(args[9]));
			if (zoo.addReptileById(r))
				MyFileLogWriter.println("successfully added Reptile " + args[0]);
			else
				MyFileLogWriter.println("failed to add Reptile " + args[0]);
		});

		// non binary args: (int patientID)
		commands.put("addBird", (section, args) -> {
			LocalDate date = LocalDate.of(Integer.parseInt(args[1]), Integer.parseInt(args[2]),
					Integer.parseInt(args[3]));
			Bird b = new Bird(args[0], date, AnimalFood.valueOf(args[4]), Gender.valueOf(args[5]),
					zoo.getRealSection(Integer.parseInt(args[6])), (args[7].equals("TRUE") ? true : false),
					args[8].equals("TRUE") ? true : false);
			b.setVisitCounter(Integer.parseInt(args[9]));
			if (zoo.addBirdById(b))
				MyFileLogWriter.println("successfully added Bird " + args[0]);
			else
				MyFileLogWriter.println("failed to add Bird " + args[0]);
		});

		commands.put("addSnack", (section, args) -> {
			if (!SnackType.valueOf(args[1]).equals(SnackType.Drink)) {
				s = new Food(SnackType.valueOf(args[1]), args[0], zoo.getRealSnackBar(Integer.parseInt(args[4])),
						(args[2].equals("TRUE") ? true : false), Double.parseDouble(args[3]),
						(args[5].equals("TRUE") ? true : false), (args[6].equals("TRUE") ? true : false),
						(args[7].equals("TRUE") ? true : false));
			} else if (args.length > 6)
				s = new Drink(SnackType.valueOf(args[1]), args[0], zoo.getRealSnackBar(Integer.parseInt(args[4])),
						(args[2].equals("TRUE") ? true : false), Double.parseDouble(args[3]),
						args[5].equals("TRUE") ? true : false, args[6].equals("TRUE") ? true : false,
						args[7].equals("TRUE") ? true : false);

			if (zoo.addSnack(s))
				MyFileLogWriter.println("successfully added Snack " + s);
			else
				MyFileLogWriter.println("failed to add Snack " + args[0]);

		});

		commands.put("getSectionDetails", (section, args) -> {
			for (Model.Section s : zoo.getSections().values()) {
				s.getSectionDetails();
			}
		});

		commands.put("moveVisitorToSection", (section, args) -> {
			Visitor v = zoo.getRealVisitor(Integer.parseInt(args[0]));
			Model.Section s = zoo.getRealSection(Integer.parseInt(args[1]));

			v.moveVisitorToSection(s);

		});

		commands.put("purchaseSnack", (section, args) -> {
			Snack s = zoo.getRealSnack(Integer.parseInt(args[0]));
			Visitor v = zoo.getRealVisitor(Integer.parseInt(args[1]));
			;
			v.purchaseSnack(s);
		});

		commands.put("checkTotalRevenue", (section, args) -> {
			zoo.checkTotalRevenue();
		});

		commands.put("removeEmployee", (section, args) -> {
			ZooEmployee e = zoo.getRealEmployee(Integer.parseInt(args[0]));
			if (zoo.removeEmployee(e))
				MyFileLogWriter.println("successfully removed Employee " + args[0]);
			else
				MyFileLogWriter.println("failed to remove Employee " + args[0]);
		});

		commands.put("removeVisitor", (section, args) -> {
			Visitor v = zoo.getRealVisitor(Integer.parseInt(args[0]));
			if (zoo.removeVisitor(v))
				MyFileLogWriter.println("successfully removed Visitor " + args[0]);
			else
				MyFileLogWriter.println("failed to remove Visitor " + args[0]);
		});

		commands.put("removeMammal", (section, args) -> {
			Mammal m = zoo.getRealMammal(Integer.parseInt(args[0]));
			if (zoo.removeMammal(m))
				MyFileLogWriter.println("successfully removed Mammal " + args[0]);
			else
				MyFileLogWriter.println("failed to remove Mammal " + args[0]);
		});

		commands.put("removeReptile", (section, args) -> {
			Reptile r = zoo.getRealReptile(Integer.parseInt(args[0]));
			if (zoo.removeReptile(r))
				MyFileLogWriter.println("successfully removed Reptile " + args[0]);
			else
				MyFileLogWriter.println("failed to remove Reptile " + args[0]);
		});

		commands.put("removeBird", (section, args) -> {
			Bird b = zoo.getRealBird(Integer.parseInt(args[0]));
			if (zoo.removeBird(b))
				MyFileLogWriter.println("successfully removed Bird " + args[0]);
			else
				MyFileLogWriter.println("failed to remove Bird " + args[0]);
		});

		commands.put("removeSection", (section, args) -> {
			Model.Section old = zoo.getRealSection(Integer.parseInt(args[0]));
			Model.Section newS = zoo.getRealSection(Integer.parseInt(args[1]));

			if (zoo.removeSection(old, newS))
				MyFileLogWriter.println("successfully removed Section " + args[0]);
			else
				MyFileLogWriter.println("failed to remove Section " + args[0]);

		});

		commands.put("removeSnackBar", (section, args) -> {
			SnackBar sb = zoo.getRealSnackBar(Integer.parseInt(args[0]));
			if (zoo.removeSnackBar(sb))
				MyFileLogWriter.println("successfully removed SnackBar " + args[0]);
			else
				MyFileLogWriter.println("failed to remove SnackBar " + args[0]);
		});

		commands.put("removeSnack", (section, args) -> {
			Snack s = zoo.getRealSnack(Integer.parseInt(args[0]));
			if (zoo.removeSnack(s))
				MyFileLogWriter.println("successfully removed Snack " + args[0]);
			else
				MyFileLogWriter.println("failed to remove Snack " + args[0]);
		});

		commands.put("VisitAnimalsByVisitor", (section, args) -> {
			HashSet<Animal> ani = new HashSet<Animal>();
			Animal a = zoo.getRealBird(Integer.parseInt(args[0]));
			Visitor e = zoo.getRealVisitor(Integer.parseInt(args[1]));
			Animal a1 = zoo.getRealMammal(Integer.parseInt(args[2]));
			Animal a2 = zoo.getRealReptile(Integer.parseInt(args[3]));
			ani.add(a1);
			ani.add(a);
			ani.add(a2);

			if (zoo.getAnimalVisitsByPeople() != null) {

				zoo.getAnimalVisitsByPeople().put(e, ani);
				MyFileLogWriter.println("successfully added to visits");
			} else
				MyFileLogWriter.println("failed added to visits ");
		});
		commands.put("TreatAnimalByWorker", (section, args) -> {
			HashSet<Animal> ani = new HashSet<Animal>();
			Animal a = zoo.getRealBird(Integer.parseInt(args[0]));
			ZooEmployee employee = zoo.getRealEmployee(Integer.parseInt(args[1]));
			Animal a1 = zoo.getRealMammal(Integer.parseInt(args[2]));
			Animal a2 = zoo.getRealReptile(Integer.parseInt(args[3]));
			ani.add(a1);
			ani.add(a);
			ani.add(a2);

			if (zoo.getAnimalTreatedByZooEmployee() != null) {
				zoo.getAnimalTreatedByZooEmployee().put(employee, ani);
				MyFileLogWriter.println("successfully added to treat by VET ");
			} else
				MyFileLogWriter.println("failed added to treat by VET");
		});

		commands.put("getAllAnimalsBySectionMaxVisits", (section, args) -> {
			MyFileLogWriter.println("Query 1: [getAllAnimalsBySectionMaxVisits]");
			Model.Section s = zoo.getRealSection(Integer.parseInt(args[0]));
			String str = "";
			if (s != null) {

				ArrayList<Animal> animal = zoo.getAllAnimalsBySectionMaxVisits(s);
				if (!animal.isEmpty()) {

					for (Animal a : animal) {
						str += "\n" + a + "\n";
					}

					MyFileLogWriter.println("successfully get all animals by section " + str);
				}

				else
					MyFileLogWriter.println("failed get all animals by section " + args[0]);

			}

		});

		commands.put("allAnimalsByWorker", (section, args) -> {
			MyFileLogWriter.println("Query 2: [allAnimalsByWorker]");
			ZooEmployee employee = zoo.getRealEmployee(Integer.parseInt(args[0]));
			String t = "";

			ArrayList<Animal> ani = zoo.allAnimalsByWorker(employee);
			if (ani != null && !ani.isEmpty()) {
				for (Animal a : ani) {

					t += "\n" + a + "\n";
				}

				MyFileLogWriter.println("successfully to get all animals from worker " + t);
			}

			else
				MyFileLogWriter.println("failed to get all animals from worker " + args[0]);
		});

		commands.put("findAllSnackByWorker", (section, args) -> {
			MyFileLogWriter.println("Query 3: [findAllSnackByWorker]");
			SnackBar sb = zoo.getRealSnackBar(Integer.parseInt(args[0]));
			String t = "";
			if (sb != null) {
				for (Snack s : zoo.findAllSnackByWorker(sb)) {
					t += "\n" + s + "\n";
				}
				MyFileLogWriter.println("successfully to find snacks  " + t);
			} else
				MyFileLogWriter.println("failed to find snacks  ");
		});
		commands.put("reptilesSleepAtSeasson", (section, args) -> {
			MyFileLogWriter.println("Query 4: [reptilesSleepAtSeasson]");
			String s = "";
			if (zoo.getReptiles() != null) {
				for (Reptile r : zoo.reptilesSleepAtSeasson()) {
					s += "\n" + r + "\n";
				}

				MyFileLogWriter.println("successfully  to find reptiels" + s);
			} else
				MyFileLogWriter.println("failed to find repties that sleep an the sesson");
		});

		commands.put("geAllDiscountAmount", (section, args) -> {
			MyFileLogWriter.println("Query 5: [geAllDiscountAmount]");
			String s = "";
			if (zoo.getVisitors() != null) {
				for (Visitor v : zoo.geAllDiscountAmount().keySet()) {
					s += v.toString() + " " + zoo.geAllDiscountAmount().get(v) + "\n";
				}
				MyFileLogWriter.println("there is the list of visitors and their amoubt " + "needed to pay  " + s);
			} else
				MyFileLogWriter.println("canot find anu discounts  " + args[0]);
		});
		commands.put("getMaxVisitorsVSMaxWorkers", (section, args) -> {
			MyFileLogWriter.println("Query 6: [getMaxVisitorsVSMaxWorkers]");

			if (zoo.getVisitors() != null && zoo.getEmployees() != null) {
				MyFileLogWriter.println("The Max Section is  " + zoo.getMaxVisitorsVSMaxWorkers());
			} else
				MyFileLogWriter.println("their is no section to check  " + args[0]);
		});

	}

	public static void main(String[] args) throws IOException {
		MyFileLogWriter.initializeMyFileWriter();

		try {
			List<String[]> input = CSVExporter.importCSV("input.csv");

			for (int i = 1; i < input.size(); i++) {

				// get row
				String[] values = input.get(i);

				if (values.length == 0)
					continue;
				// get command
				String command = values[0];

				// get params
				String[] params = Arrays.copyOfRange(values, 1, values.length);

				// send to func
				try {
					func(command, params);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} finally {
			// Shared s = Shared.getInstance();
			System.out.println("All commands executed. Please check \"" + OUTPUT_FILE + "\".");
			Zoo.getInstance().serialize();
		}

	}

	private static void func(String command, String[] args) {
		// extract command
		Command c = commands.get(command);

		// check that command exists
		if (c != null) {
			// get relevant section
			Section section = sections.get(command);

			// execute
			c.execute(section, args);
		}
	}

	@FunctionalInterface
	private interface Command {
		void execute(Section section, String... args);
	}

}
