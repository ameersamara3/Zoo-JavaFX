package View;

import java.io.IOException;

import Exceptions.EmptyFieldException;
import Exceptions.ErrorMessage;
import Exceptions.FailedToException;
import Exceptions.idNotFoundException;
import Model.Food;
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

public class addFood {
	ObservableList<SnackType> types = FXCollections.observableArrayList(SnackType.values());

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
    private RadioButton plateTrue;

    @FXML
    private ToggleGroup plate;

    @FXML
    private RadioButton plateFalse;

    @FXML
    private RadioButton spaciyTrue;

    @FXML
    private ToggleGroup spaciy;

    @FXML
    private RadioButton spaciyFalse;

    @FXML
    private RadioButton glutenTrue;

    @FXML
    private ToggleGroup gluten;

    @FXML
    private RadioButton glutenFalse;

    @FXML
    void submit(ActionEvent event) {//the comments are the same as add bird
    	try {
			int Id = Integer.parseInt(id.getText());
			double price2 = Double.parseDouble(price.getText());
			if(name.getText().equals(""))
				throw new EmptyFieldException();
			if(Zoo.getInstance().getRealSection(Id)==null)
				throw new idNotFoundException();
			boolean flag=Zoo.getInstance().addSnack(new Food(type.getValue(),name.getText(), Zoo.getInstance().getRealSnackBar(Id),
					 fatTrue.isSelected(),price2,plateTrue.isSelected(),spaciyTrue.isSelected(),glutenTrue.isSelected()));
			if(flag)
	    	{
				MyMethods.infoMessage("Success!", "Successfully added");
	    	}
	    	else {
	    		throw new FailedToException("add");	    		
	    	}
		} catch (NumberFormatException e) {
			MyMethods.infoMessage("Error!", "Invalid Input!\nPlease enter the values again");
		}catch (ErrorMessage e) {
			e.takeCare();
		}

	}

    
    @FXML
    private Pane screen;
    @FXML
	void back(ActionEvent event) {

		if (MyMethods.getEmployee() == null)// => then its an admin
			MyMethods.newPage(screen, "addByAdmin.fxml");
		else
			MyMethods.newPage(screen, "addByEmployee.fxml");

	}
    @FXML
	void initialize() {
    	types.remove(SnackType.Drink);
		type.setItems(types);
		type.setValue(SnackType.Snack);
		fatTrue.setSelected(true);
		plateTrue.setSelected(true);
		spaciyTrue.setSelected(true);
		glutenTrue.setSelected(true);

	}

}
