package View;

import Exceptions.ErrorMessage;
import Model.SnackBar;
import Model.Zoo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class setZooPercentage {

	@FXML
	private AnchorPane screen;

	@FXML
	private TextField percentage;

	@FXML
	void back(ActionEvent event) {
		if (MyMethods.getEmployee() == null)// => then its an admin
			MyMethods.newPage(screen, "MenuAdmin.fxml");
		else
			MyMethods.newPage(screen, "MenuEmployee.fxml");
	}

	@FXML
	void set(ActionEvent event) {//sets the zoo percentage
		try {
			double percent = Double.parseDouble(percentage.getText());
			if (percent > 1 || percent < 0) {
				MyMethods.infoMessage("Error!", "you must enter a value between 0 and 1");
				return;
			}
			SnackBar.setZooPercentage(percent);
			MyMethods.infoMessage("Success", "You successfully setted the zoo percentage!");
		} catch (NumberFormatException e) {
			MyMethods.infoMessage("Error!", "Invalid Input!\nPlease enter the values again");
		}
	}

}
