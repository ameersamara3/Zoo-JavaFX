package View;

import Exceptions.ErrorMessage;
import Exceptions.FailedToException;
import Exceptions.NothingIsSelectedException;
import Model.Bird;
import Model.Mammal;
import Model.Reptile;
import Model.Section;
import Model.Snack;
import Model.SnackBar;
import Model.Visitor;
import Model.Zoo;
import Model.ZooEmployee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class RemoveByAdmin {
	ObservableList<String> animals = FXCollections.observableArrayList("Mammal", "Reptile", "Bird");
	@FXML
	private AnchorPane screen;

	@FXML
	private ChoiceBox<String> animalChoice;

	@FXML
	private RadioButton animal;

	@FXML
	private ToggleGroup choose;

	@FXML
	private RadioButton visitor;

	@FXML
	private RadioButton employee;

	@FXML
	private RadioButton snack;

	@FXML
	private RadioButton bar;

	@FXML
	private RadioButton section;

	@FXML
	private ListView<Object> list;
	@FXML
	private TextField id;
	//the remove methods sets call the method removeObject after getting the input objects either by id or selection
	//the methods check for exceptions first
	@FXML
	void RemoveSelected(ActionEvent event) {
		try {
			if (list.getSelectionModel().getSelectedItem() == null)
				throw new NothingIsSelectedException();
			boolean flag;
			if (employee.isSelected()) {
				flag = Zoo.getInstance().removeEmployee((ZooEmployee) list.getSelectionModel().getSelectedItem());
				list.getItems().removeAll(list.getItems());
				list.getItems().addAll(Zoo.getInstance().getEmployees().values());
			} else if (visitor.isSelected()) {
				flag = Zoo.getInstance().removeVisitor((Visitor) list.getSelectionModel().getSelectedItem());
				list.getItems().removeAll(list.getItems());
				list.getItems().addAll(Zoo.getInstance().getVisitors().values());
			} else if (snack.isSelected()) {
				flag = Zoo.getInstance().removeSnack((Snack) list.getSelectionModel().getSelectedItem());
				list.getItems().removeAll(list.getItems());
				list.getItems().addAll(Zoo.getInstance().getSnacks().values());
			} else if (bar.isSelected()) {
				flag = Zoo.getInstance().removeSnackBar((SnackBar) list.getSelectionModel().getSelectedItem());
				list.getItems().removeAll(list.getItems());
				list.getItems().addAll(Zoo.getInstance().getBars().values());
			} else {
				if (animalChoice.getValue().equals("Mammal")) {
					flag = Zoo.getInstance().removeMammal((Mammal) list.getSelectionModel().getSelectedItem());
					list.getItems().removeAll(list.getItems());
					list.getItems().addAll(Zoo.getInstance().getMammals().values());
				} else if (animalChoice.getValue().equals("Reptile")) {
					flag = Zoo.getInstance().removeReptile((Reptile) list.getSelectionModel().getSelectedItem());
					list.getItems().removeAll(list.getItems());
					list.getItems().addAll(Zoo.getInstance().getReptiles().values());
				} else {
					flag = Zoo.getInstance().removeBird((Bird) list.getSelectionModel().getSelectedItem());
					list.getItems().removeAll(list.getItems());
					list.getItems().addAll(Zoo.getInstance().getBirds().values());
				}

			}

			if (flag) {
				MyMethods.infoMessage("Success!", "Successfully removed");
			} else {
				throw new FailedToException("remove");
			}
		} catch (NumberFormatException e) {
			MyMethods.infoMessage("Error!", "Invalid Input!\nPlease enter the values again");
		} catch (ErrorMessage e) {
			e.takeCare();
		}
	}

	@FXML
	void click(MouseEvent event) {//updates the remove list or go to a newpage (removeSection)
		if (employee.isSelected()) {
			list.getItems().removeAll(list.getItems());
			list.getItems().addAll(Zoo.getInstance().getEmployees().values());
		} else if (visitor.isSelected()) {
			list.getItems().removeAll(list.getItems());
			list.getItems().addAll(Zoo.getInstance().getVisitors().values());
		} else if (section.isSelected()) {
			MyMethods.newPage(screen, "removeSection.fxml");
		} else if (snack.isSelected()) {
			list.getItems().removeAll(list.getItems());
			list.getItems().addAll(Zoo.getInstance().getSnacks().values());
		} else if (bar.isSelected()) {
			list.getItems().removeAll(list.getItems());
			list.getItems().addAll(Zoo.getInstance().getBars().values());
		} else {
			if (animalChoice.getValue().equals("Mammal")) {
				list.getItems().removeAll(list.getItems());
				list.getItems().addAll(Zoo.getInstance().getMammals().values());
			} else if (animalChoice.getValue().equals("Reptile")) {
				list.getItems().removeAll(list.getItems());
				list.getItems().addAll(Zoo.getInstance().getReptiles().values());
			} else {
				list.getItems().removeAll(list.getItems());
				list.getItems().addAll(Zoo.getInstance().getBirds().values());
			}
		}
	}

	@FXML
	void back(ActionEvent event) {
		MyMethods.newPage(screen, "MenuAdmin.fxml");
	}

	@FXML
	void removeID(ActionEvent event) {
		try {
			int Id = Integer.parseInt(id.getText());
			boolean flag;
			if (employee.isSelected()) {
				flag = Zoo.getInstance().removeEmployee(Zoo.getInstance().getRealEmployee(Id));
				list.getItems().removeAll(list.getItems());
				list.getItems().addAll(Zoo.getInstance().getEmployees().values());
			} else if (visitor.isSelected()) {
				flag = Zoo.getInstance().removeVisitor(Zoo.getInstance().getRealVisitor(Id));
				list.getItems().removeAll(list.getItems());
				list.getItems().addAll(Zoo.getInstance().getVisitors().values());
			} else if (snack.isSelected()) {
				flag = Zoo.getInstance().removeSnack(Zoo.getInstance().getRealSnack(Id));
				list.getItems().removeAll(list.getItems());
				list.getItems().addAll(Zoo.getInstance().getSnacks().values());
			} else if (bar.isSelected()) {
				flag = Zoo.getInstance().removeSnackBar(Zoo.getInstance().getRealSnackBar(Id));
				list.getItems().removeAll(list.getItems());
				list.getItems().addAll(Zoo.getInstance().getBars().values());
			} else {
				if (animalChoice.getValue().equals("Mammal")) {
					flag = Zoo.getInstance().removeMammal(Zoo.getInstance().getRealMammal(Id));
					list.getItems().removeAll(list.getItems());
					list.getItems().addAll(Zoo.getInstance().getMammals().values());
				} else if (animalChoice.getValue().equals("Reptile")) {
					flag = Zoo.getInstance().removeReptile(Zoo.getInstance().getRealReptile(Id));
					list.getItems().removeAll(list.getItems());
					list.getItems().addAll(Zoo.getInstance().getReptiles().values());
				} else {
					flag = Zoo.getInstance().removeBird(Zoo.getInstance().getRealBird(Id));
					list.getItems().removeAll(list.getItems());
					list.getItems().addAll(Zoo.getInstance().getBirds().values());
				}

			}

			if (flag) {
				Alert a = new Alert(Alert.AlertType.INFORMATION);
				a.setTitle("Success!!");
				a.setContentText("Successfully removed");
				a.setHeaderText(null);
				a.showAndWait();
			} else {
				throw new FailedToException("remove");
			}
		} catch (NumberFormatException e) {
			MyMethods.infoMessage("Error!", "Invalid Input!\nPlease enter the values again");
		} catch (ErrorMessage e) {
			e.takeCare();
		}

	}

	@FXML
	void initialize() {

		list.getItems().removeAll(list.getItems());
		list.getItems().addAll(Zoo.getInstance().getMammals().values());
		animalChoice.setItems(animals);
		animalChoice.setValue("Mammal");
		animal.setSelected(true);

	}

}
