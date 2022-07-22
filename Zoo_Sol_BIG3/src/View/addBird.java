package View;

import java.io.IOException;

import Exceptions.FailedToException;
import Exceptions.idNotFoundException;
import Exceptions.EmptyFieldException;
import Exceptions.ErrorMessage;
import Model.Bird;
import Model.Visitor;
import Model.Zoo;
import Utils.AnimalFood;
import Utils.Gender;
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

public class addBird {
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
	private RadioButton flyTrue;

	@FXML
	private ToggleGroup fly;

	@FXML
	private RadioButton flyFalse;

	@FXML
	private RadioButton photoTrue;

	@FXML
	private ToggleGroup photo;

	@FXML
	private RadioButton PhotoFalse;

	@FXML
	void submit(ActionEvent event) {
		Gender gender2 = Gender.Unknown;//checks selected gender
		if (male.equals(gender.getSelectedToggle()))
			gender2 = Gender.Male;
		if (female.equals(gender.getSelectedToggle()))
			gender2 = Gender.Female;
		try {
			
			int sectionId = Integer.parseInt(section.getText());//converts string to int
			if (name.getText().equals("") || bday.getValue() == null)//checks if there's empty fields
				throw new EmptyFieldException();
			if (Zoo.getInstance().getRealSection(sectionId) == null)//checks if the section exists
				throw new idNotFoundException();
			boolean flag = Zoo.getInstance()
					.addBirdById(new Bird(name.getText(), bday.getValue(), food.getValue(), gender2,
							Zoo.getInstance().getRealSection(sectionId), flyTrue.isSelected(), photoTrue.isSelected()));//adds the object

			if (flag) {
				MyMethods.infoMessage("Success!", "Successfully added");
			} else {
				throw new FailedToException("add");
			}

		} catch (NumberFormatException e) {
			MyMethods.infoMessage("Error!", "Invalid Input!\nPlease enter the values again");
		} catch (ErrorMessage e) {//errorMessage is the class dad of most exceptions (מחלקת האב)
			e.takeCare();//a pop up error message
		}

	}

	@FXML
	private Pane screen;

	@FXML
	void back(ActionEvent event) {
		MyMethods.newPage(screen, "addByAdmin.fxml");//new page
	}

	@FXML
	void initialize() {
		food.setItems(foodList);//initialize list
		food.setValue(AnimalFood.Peanuts);
		flyTrue.setSelected(true);//initialize buttons
		photoTrue.setSelected(true);
		female.setSelected(true);

	}
}
