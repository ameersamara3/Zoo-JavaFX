package View;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class AddByAdmin {
	ObservableList<String> animals = FXCollections.observableArrayList("Mammal", "Reptile", "Bird");
	ObservableList<String> snacks = FXCollections.observableArrayList("Food", "Drink");

	@FXML
	private AnchorPane screen;
	@FXML
	private RadioButton employee;

	@FXML
	private RadioButton visitor;

	@FXML
	private RadioButton section;

	@FXML
	private RadioButton snack;

	@FXML
	private RadioButton bar;

	@FXML
	private RadioButton animal;

	@FXML
	private ChoiceBox<String> animalChoice;
	@FXML
	private ChoiceBox<String> snackChoice;

	@FXML
	void submit(ActionEvent event) {
		String fxml;
		if (employee.isSelected())//checks selected buttons
			fxml = "addEmployee.fxml";
		else if (visitor.isSelected())
			fxml = "addVisitor.fxml";
		else if (section.isSelected())
			fxml = "addSection.fxml";
		else if (snack.isSelected()) {
			if (snackChoice.getValue().equals("Drink"))
				fxml = "addDrink.fxml";
			else
				fxml = "addFood.fxml";
		} else if (bar.isSelected())
			fxml = "addSnackBar.fxml";
		else {
			if (animalChoice.getValue().equals("Mammal"))
				fxml = "addMammal.fxml";
			else if (animalChoice.getValue().equals("Reptile"))
				fxml = "addReptile.fxml";
			else
				fxml = "addBird.fxml";
		}

		MyMethods.newPage(screen, fxml);

	}

	@FXML
	void back(ActionEvent event) {
		MyMethods.newPage(screen, "MenuAdmin.fxml");
	}

	@FXML
	void initialize() {
		animalChoice.setItems(animals);//initialize choiceBox
		animalChoice.setValue("Mammal");
		snackChoice.setItems(snacks);
		snackChoice.setValue("Food");
		animal.setSelected(true);
	}
}
