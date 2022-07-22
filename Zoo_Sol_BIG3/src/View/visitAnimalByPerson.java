package View;

import Exceptions.ErrorMessage;
import Exceptions.FailedToException;
import Exceptions.NothingIsSelectedException;
import Exceptions.idNotFoundException;
import Model.Animal;
import Model.Bird;
import Model.Mammal;
import Model.Person;
import Model.Section;
import Model.Snack;
import Model.Visitor;
import Model.Zoo;
import Model.ZooEmployee;
import Utils.Discount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class visitAnimalByPerson {
	@FXML
	private AnchorPane screen;

	@FXML
	private ListView<Person> personList;

	@FXML
	private ListView<Animal> animalList;

	@FXML
	private TextField animalID;

	@FXML
	private TextField personID;

	@FXML
	private RadioButton mammal;

	@FXML
	private ToggleGroup animal;

	@FXML
	private RadioButton bird;
	@FXML
	private RadioButton employee;

	@FXML
	private ToggleGroup person;

	@FXML
	private RadioButton visitor;

	@FXML
	void back(ActionEvent event) {
		MyMethods.newPage(screen, "MenuAdmin.fxml");
	}
	// the visit methods sets call the method visitcount after getting the input objects either by id or selection
		// the methods check for exceptions first
	//the method is long i know but i am too lazy to make it shorter (don't have alot of time
	@FXML
	void visitID(ActionEvent event) {
		try {
			boolean flag;
			int employeeId = Integer.parseInt(personID.getText());
			int animalId = Integer.parseInt(animalID.getText());
			if (mammal.isSelected()) {
				if (Zoo.getInstance().getRealMammal(animalId) == null
						|| Zoo.getInstance().getRealEmployee(employeeId) == null)
					throw new idNotFoundException();
				Person p=employee.isSelected()?Zoo.getInstance().getRealEmployee(employeeId):Zoo.getInstance().getRealEmployee(employeeId);
				Animal a=mammal.isSelected()?Zoo.getInstance().getRealMammal(animalId):Zoo.getInstance().getRealBird(animalId);

				if (!p.getSection().getMammals()
						.contains(a)) {
					MyMethods.infoMessage("Error!", "This employe and animal aren't in the same section");
					return;
				}
				flag = Zoo.getInstance().getRealMammal(animalId)
						.visitcount(Zoo.getInstance().getRealEmployee(employeeId));
				if (employee.isSelected()) {
					if (flag) {
						MyMethods.infoMessage("Success!",
								Zoo.getInstance().getRealEmployee(employeeId).getFirstName() + " has have successfully treated "
										+ Zoo.getInstance().getRealMammal(animalId).getName());
					} else
						MyMethods.infoMessage("message", Zoo.getInstance().getRealEmployee(employeeId).getFirstName()
								+ " has have visited this animal but it doesn't need treatment yet.");
				} else {
					MyMethods.infoMessage("Success!", Zoo.getInstance().getRealEmployee(employeeId).getFirstName()
							+ " has have successfully visited " + Zoo.getInstance().getRealMammal(animalId).getName());
				}
			} else {
				
				if (Zoo.getInstance().getRealBird(animalId) == null
						|| Zoo.getInstance().getRealEmployee(employeeId) == null)
					throw new idNotFoundException();
				Person p=employee.isSelected()?Zoo.getInstance().getRealEmployee(employeeId):Zoo.getInstance().getRealEmployee(employeeId);
				Animal a=mammal.isSelected()?Zoo.getInstance().getRealMammal(animalId):Zoo.getInstance().getRealBird(animalId);

				if (!p.getSection().getMammals()
						.contains(a)) {
					MyMethods.infoMessage("Error!", "This employe and animal aren't in the same section");
					return;
				}
				flag = Zoo.getInstance().getRealBird(animalId)
						.visitcount(Zoo.getInstance().getRealEmployee(employeeId));
				if (employee.isSelected()) {
					if (flag) {
						MyMethods.infoMessage("Success!", Zoo.getInstance().getRealVisitor(employeeId).getFirstName()
								+ " has have successfully treated " + Zoo.getInstance().getRealBird(animalId).getName());
					} else
						MyMethods.infoMessage("message", Zoo.getInstance().getRealVisitor(employeeId).getFirstName()
								+ " has have visited this animal but it doesn't need treatment yet.");
				} else {
					MyMethods.infoMessage("Success!", Zoo.getInstance().getRealVisitor(employeeId).getFirstName()
							+ " has have successfully visited " + Zoo.getInstance().getRealBird(animalId).getName());
				}
			}
		} catch (NumberFormatException e) {
			MyMethods.infoMessage("Error!", "Invalid Input!\nPlease enter the values again");
		} catch (ErrorMessage e) {
			e.takeCare();
		}
	}

	@FXML
	void visitSelected(ActionEvent event) {
		try {
			boolean flag;
			if (animalList.getSelectionModel().getSelectedItem() == null
					|| personList.getSelectionModel().getSelectedItem() == null)
				throw new NothingIsSelectedException();
			if (mammal.isSelected()) {
				flag = ((Mammal) animalList.getSelectionModel().getSelectedItem())
						.visitcount(personList.getSelectionModel().getSelectedItem());
				if (employee.isSelected()) {
					if (flag) {
						MyMethods.infoMessage("Success!",
								personList.getSelectionModel().getSelectedItem().getFirstName() + " has successfully treated "
										+ animalList.getSelectionModel().getSelectedItem().getName());
					} else
						MyMethods.infoMessage("message", personList.getSelectionModel().getSelectedItem().getFirstName()
								+ " has have visited this animal but it doesn't need treatment yet.");
				} else {
					MyMethods.infoMessage("Success!",
							personList.getSelectionModel().getSelectedItem().getFirstName() + " has have successfully visited "
									+ animalList.getSelectionModel().getSelectedItem().getName());
				}
			} else {
				flag = ((Bird) animalList.getSelectionModel().getSelectedItem())
						.visitcount(personList.getSelectionModel().getSelectedItem());
			if (employee.isSelected()) {
				if (flag) {
					MyMethods.infoMessage("Success!",
							personList.getSelectionModel().getSelectedItem().getFirstName() + " has have successfully treated "
									+ animalList.getSelectionModel().getSelectedItem().getName());
				} else
					MyMethods.infoMessage("message", personList.getSelectionModel().getSelectedItem().getFirstName()
							+ " has have visited this animal but it doesn't need treatment yet.");
			} else {
				MyMethods.infoMessage("Success!",
						personList.getSelectionModel().getSelectedItem().getFirstName() + " has have successfully visited "
								+ animalList.getSelectionModel().getSelectedItem().getName());
			}
		}
		}
		catch (NumberFormatException e) {
			MyMethods.infoMessage("Error!", "Invalid Input!\nPlease enter the values again");
		} catch (ErrorMessage e) {
			e.takeCare();
		}
	}

	@FXML
	void update1(MouseEvent event) {//updates the person list
		if (employee.isSelected()) {
			personList.getItems().removeAll(personList.getItems());
			personList.getItems().addAll(Zoo.getInstance().getEmployees().values());
		} else {
			personList.getItems().removeAll(personList.getItems());
			personList.getItems().addAll(Zoo.getInstance().getVisitors().values());
		}
	}

	@FXML
	void update2(MouseEvent event) {//updates the animal list
		if (personList.getSelectionModel().getSelectedItem() != null) {
			if (mammal.isSelected()) {
				animalList.getItems().removeAll(animalList.getItems());
				animalList.getItems()
						.addAll(personList.getSelectionModel().getSelectedItem().getSection().getMammals());
			} else {
				animalList.getItems().removeAll(animalList.getItems());
				animalList.getItems().addAll(personList.getSelectionModel().getSelectedItem().getSection().getBirds());
			}
		} else {
			return;
		}
	}

	@FXML
	void initialize() {
		mammal.setSelected(true);
		employee.setSelected(true);
		update1(null);

	}
}
