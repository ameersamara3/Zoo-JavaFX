package View;

import java.util.TreeMap;

import Exceptions.idNotFoundException;
import Model.Animal;
import Model.Reptile;
import Model.Section;
import Model.Snack;
import Model.SnackBar;
import Model.Visitor;
import Model.Zoo;
import Model.ZooEmployee;
import Utils.Discount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class Query5 {

    @FXML
    private AnchorPane screen;

    @FXML
    private ListView<String> visitorList;

    @FXML
	void back(ActionEvent event) {
    	if (MyMethods.getEmployee() == null)// => then its an admin
			MyMethods.newPage(screen, "MenuAdmin.fxml");
		else
			MyMethods.newPage(screen, "MenuEmployee.fxml");
	}

    @FXML
	void initialize() {
	 	
		TreeMap<Visitor, Double> t=Zoo.getInstance().geAllDiscountAmount();
		for(Visitor v:t.keySet())
		{
			visitorList.getItems().add(v.toString()+" "+t.get(v));	
		}
		}
}
