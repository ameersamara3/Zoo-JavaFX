package View;

import Model.Zoo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class Query6 {

    @FXML
    private AnchorPane screen;

    @FXML
    private Label output1;

    @FXML
	void back(ActionEvent event) {
    	if (MyMethods.getEmployee() == null)// => then its an admin
			MyMethods.newPage(screen, "MenuAdmin.fxml");
		else
			MyMethods.newPage(screen, "MenuEmployee.fxml");
	}
    @FXML
	void initialize() {
    	if(Zoo.getInstance().getMaxVisitorsVSMaxWorkers()!=null)
		output1.setText(Zoo.getInstance().getMaxVisitorsVSMaxWorkers().toString());
    	else
    		output1.setText("There's no sections yet");
	}
}
