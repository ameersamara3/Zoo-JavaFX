package View;

import Exceptions.ErrorMessage;
import Exceptions.FailedToException;
import Exceptions.NothingIsSelectedException;
import Exceptions.idNotFoundException;
import Model.Animal;
import Model.Bird;
import Model.Mammal;
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

public class visitAnimalByEmployee {
	@FXML
	private AnchorPane screen;

	@FXML
	private ListView<Animal> animalList;

	@FXML
	private TextField animalID;

	@FXML
	private RadioButton mammal;

	@FXML
	private ToggleGroup animal;

	@FXML
	private RadioButton bird;

	@FXML
	void back(ActionEvent event) {
		MyMethods.newPage(screen, "MenuEmployee.fxml");
	}

	// the visit methods sets call the method visitcount after getting the input objects either by id or selection
	// the methods check for exceptions first
	// the person that treats the animal can only be the user (employee)
	@FXML
	void visitID(ActionEvent event) {
		try {
			int animalId = Integer.parseInt(animalID.getText());
			if (mammal.isSelected()) {
				if (Zoo.getInstance().getRealMammal(animalId) == null)
					throw new idNotFoundException();
				if (!MyMethods.getEmployee().getSection().getMammals()
						.contains(Zoo.getInstance().getRealMammal(animalId))) {
					MyMethods.infoMessage("Error!", "This animal is not in your section!");
					return;
				}
				if (Zoo.getInstance().getRealMammal(animalId).visitcount(MyMethods.getEmployee()))
					MyMethods.infoMessage("Success!",
							"You have successfuully treated " + Zoo.getInstance().getRealMammal(animalId));
				else
					MyMethods.infoMessage("message", "You have visited this animal but it doesn't need treatment yet.");

			} else {
				if (Zoo.getInstance().getRealBird(animalId) == null)
					throw new idNotFoundException();
				if (!MyMethods.getEmployee().getSection().getBirds()
						.contains(Zoo.getInstance().getRealBird(animalId))) {
					MyMethods.infoMessage("Error!", "This animal is not in your section!");
					return;
				}
				if (Zoo.getInstance().getRealBird(animalId).visitcount(MyMethods.getEmployee()))
					MyMethods.infoMessage("Success!",
							"You have successfuully treated " + Zoo.getInstance().getRealBird(animalId));
				else
					MyMethods.infoMessage("message", "You have visited this animal but it doesn't need treatment yet.");
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

			if (animalList.getSelectionModel().getSelectedItem() == null)
				throw new NothingIsSelectedException();
			if (mammal.isSelected())
				if (((Mammal) animalList.getSelectionModel().getSelectedItem()).visitcount(MyMethods.getEmployee()))
					MyMethods.infoMessage("Success!", "You have successfuully treated "
							+ animalList.getSelectionModel().getSelectedItem().getName());
				else
					MyMethods.infoMessage("message", "You have visited this animal but it doesn't need treatment yet.");
			else {
				if (((Bird) animalList.getSelectionModel().getSelectedItem()).visitcount(MyMethods.getEmployee()))
					MyMethods.infoMessage("Success!", "You have successfuully treated "
							+ animalList.getSelectionModel().getSelectedItem().getName());
				else
					MyMethods.infoMessage("message", "You have visited this animal but it doesn't need treatment yet.");

			}
		} catch (NumberFormatException e) {
			MyMethods.infoMessage("Error!", "Invalid Input!\nPlease enter the values again");
		} catch (ErrorMessage e) {
			e.takeCare();
		}
	}

	@FXML
	void update(MouseEvent event) {//updates the animal list
		if (mammal.isSelected()) {
			animalList.getItems().removeAll(animalList.getItems());
			animalList.getItems().addAll(MyMethods.getEmployee().getSection().getMammals());
		} else {
			animalList.getItems().removeAll(animalList.getItems());
			animalList.getItems().addAll(MyMethods.getEmployee().getSection().getBirds());
		}
	}

	@FXML
	void initialize() {

		mammal.setSelected(true);
		update(null);

	}
}
