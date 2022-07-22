package View;

import java.io.IOException;

import Exceptions.EmptyFieldException;
import Exceptions.ErrorMessage;
import Exceptions.FailedToException;
import Exceptions.idNotFoundException;
import Model.Drink;
import Model.Snack;
import Model.Zoo;
import Utils.SnackType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

public class addDrink {
	ObservableList<SnackType> types = FXCollections.observableArrayList(SnackType.Drink);

	@FXML
	private TextField name;

	@FXML
	private TextField price;

	@FXML
	private RadioButton fatTrue;

	@FXML
	private ToggleGroup fat;

	@FXML
	private RadioButton fatFalse;

	@FXML
	private ChoiceBox<SnackType> type;

	@FXML
	private TextField id;
	@FXML
	private RadioButton sprinkelTrue;

	@FXML
	private ToggleGroup sprinkel;

	@FXML
	private RadioButton sprinkelFalse;

	@FXML
	private RadioButton strawTrue;

	@FXML
	private ToggleGroup straw;

	@FXML
	private RadioButton iceCubeTrue;

	@FXML
	private ToggleGroup iceCube;

	@FXML
	private RadioButton iceCubeFalse;
	@FXML
	private Pane screen;

	@FXML
	void submit(ActionEvent event) {//the comments are the same as add bird
		try {
			int Id = Integer.parseInt(id.getText());
			double price2 = Double.parseDouble(price.getText());
			if (name.getText().equals(""))
				throw new EmptyFieldException();
			if (Zoo.getInstance().getRealSection(Id) == null)
				throw new idNotFoundException();
			boolean flag = Zoo.getInstance()
					.addSnack(new Drink(type.getValue(), name.getText(), Zoo.getInstance().getRealSnackBar(Id),
							fatTrue.isSelected(), price2, sprinkelTrue.isSelected(), strawTrue.isSelected(),
							iceCubeTrue.isSelected()));
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
	void back(ActionEvent event) {

		if (MyMethods.getEmployee() == null)// => then its an admin
			MyMethods.newPage(screen, "addByAdmin.fxml");
		else
			MyMethods.newPage(screen, "addByEmployee.fxml");

	}

	@FXML
	void initialize() {
		type.setItems(types);
		type.setValue(SnackType.Drink);
		fatTrue.setSelected(true);
		sprinkelTrue.setSelected(true);
		strawTrue.setSelected(true);
		iceCubeTrue.setSelected(true);

	}

}
