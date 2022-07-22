package View;

import java.io.IOException;

import Exceptions.EmptyFieldException;
import Exceptions.ErrorMessage;
import Exceptions.FailedToException;
import Exceptions.idNotFoundException;
import Model.Bird;
import Model.Mammal;
import Model.Reptile;
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

public class addReptile {
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
	private RadioButton dangerTrue;

	@FXML
	private ToggleGroup danger;

	@FXML
	private RadioButton dangerFalse;

	@FXML
	private RadioButton sleepTrue;

	@FXML
	private ToggleGroup sleep;

	@FXML
	private RadioButton sleepFalse;

	@FXML
	void submit(ActionEvent event) {//the comments are the same as add bird
		Gender gender2 = Gender.Unknown;
		if (male.equals(gender.getSelectedToggle()))
			gender2 = Gender.Male;
		if (female.equals(gender.getSelectedToggle()))
			gender2 = Gender.Female;
		try {
			int sectionId = Integer.parseInt(section.getText());
			if(name.getText().equals("")||bday.getValue()==null)
				throw new EmptyFieldException();
			if(Zoo.getInstance().getRealSection(sectionId)==null)
				throw new idNotFoundException();
			boolean flag=Zoo.getInstance().addReptileById(new Reptile(name.getText(), bday.getValue(), food.getValue(), gender2,
					Zoo.getInstance().getRealSection(sectionId), dangerTrue.isSelected(), sleepTrue.isSelected()));
			if(flag)
	    	{
				MyMethods.infoMessage("Success!", "Successfully added");
	    	}
	    	else {
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
		dangerTrue.setSelected(true);
		sleepTrue.setSelected(true);
		female.setSelected(true);

	}

}
