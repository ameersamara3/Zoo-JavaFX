package View;

import Model.Zoo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class Revenue {

    @FXML
    private AnchorPane screen;

    @FXML
    private Label label;

    @FXML
	void back(ActionEvent event) {
    	if (MyMethods.getEmployee() == null)// => then its an admin
			MyMethods.newPage(screen, "MenuAdmin.fxml");
		else
			MyMethods.newPage(screen, "MenuEmployee.fxml");
	}
    @FXML
	void initialize() {

		label.setText("Zoo Revenue: "+Zoo.getInstance().checkTotalRevenue());

	}
}
