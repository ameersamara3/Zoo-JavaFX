package View;

import java.io.IOException;

import Model.Zoo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class addByEmployee{ 
	ObservableList<String> snacks=FXCollections.observableArrayList("Food","Drink");


    @FXML
    private AnchorPane screen;

    @FXML
    private RadioButton visitor;

    @FXML
    private ToggleGroup choose;

    @FXML
    private RadioButton employee;

    @FXML
    private RadioButton snack;
    @FXML
    private Label welcome;
    @FXML
    private ChoiceBox<String> snackChoice;

    @FXML
	void submit(ActionEvent event) {
		String fxml;
		if (employee.isSelected())
			fxml = "addEmployeeByEmployee.fxml";
		else if (visitor.isSelected())
			fxml = "addVisitor.fxml";
		else{
			if (snackChoice.getValue().equals("Drink"))
				fxml = "addDrink.fxml";
			else
				fxml = "addFood.fxml";
		}

		MyMethods.newPage(screen, fxml);

	}
    @FXML
	void back(ActionEvent event) {
    	MyMethods.newPage(screen, "MenuEmployee.fxml");
	}
    @FXML
    void initialize() {
    	snack.setSelected(true);
    	welcome.setText("Welcome, "+MyMethods.getEmployee().getFirstName());//by employee name
    	snackChoice.setItems(snacks);
    	snackChoice.setValue("Food");
    }

}


