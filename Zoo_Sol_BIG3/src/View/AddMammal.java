package View;

import java.io.IOException;

import Exceptions.EmptyFieldException;
import Exceptions.ErrorMessage;
import Exceptions.FailedToException;
import Exceptions.idNotFoundException;
import Model.Bird;
import Model.Mammal;
import Model.Zoo;
import Utils.AnimalFood;
import Utils.Discount;
import Utils.Gender;
import Utils.TicketType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

public class AddMammal {
	ObservableList<AnimalFood> foodList = FXCollections.observableArrayList(AnimalFood.values());

	@FXML
	private TextField name;

	@FXML
	private DatePicker bday;

	@FXML
	private RadioButton male;

	@FXML
	private ToggleGroup gender;

	@FXML
	private RadioButton female;

	@FXML
	private RadioButton unknown;

	@FXML
	private ChoiceBox<AnimalFood> food;

	@FXML
	private TextField section;

	@FXML
	private RadioButton meatTrue;

	@FXML
	private ToggleGroup meat;

	@FXML
	private RadioButton meatFalse;

	@FXML
	private RadioButton photoTrue;

	@FXML
	private ToggleGroup photo;

	@FXML
	private RadioButton photoFalse;

	@FXML
	void submit(ActionEvent event) {//the comments are the same as add bird
		Gender gender2 = Gender.Unknown;
		if (male.equals(gender.getSelectedToggle()))
			gender2 = Gender.Male;
		if (female.equals(gender.getSelectedToggle()))
			gender2 = Gender.Female;
		try {
			int sectionId = Integer.parseInt(section.getText());
			if (name.getText().equals("") || bday.getValue() == null)
				throw new EmptyFieldException();
			if (Zoo.getInstance().getRealSection(sectionId) == null)
				throw new idNotFoundException();
			boolean flag = Zoo.getInstance()
					.addMammalById(new Mammal(name.getText(), bday.getValue(), food.getValue(), gender2,
							Zoo.getInstance().getRealSection(sectionId), meatTrue.isSelected(),
							photoTrue.isSelected()));
			if (flag) {
				MyMethods.infoMessage("Success!", "Successfully added");
			} else {
				throw new FailedToException("add");
			}

		} catch (NumberFormatException e) {
			MyMethods.infoMessage("Error!", "Invalid Input!\nPlease enter the values again");
		} catch (ErrorMessage e) {
			e.takeCare();
		}
	}

	@FXML
	private Pane screen;

	@FXML
	void back(ActionEvent event) {
		MyMethods.newPage(screen, "addByAdmin.fxml");

	}

	@FXML
	void initialize() {
		food.setItems(foodList);
		food.setValue(AnimalFood.Peanuts);
		meatTrue.setSelected(true);
		photoTrue.setSelected(true);
		female.setSelected(true);

	}

}
