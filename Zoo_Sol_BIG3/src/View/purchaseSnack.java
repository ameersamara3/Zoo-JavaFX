package View;

import Exceptions.ErrorMessage;
import Exceptions.FailedToException;
import Exceptions.NothingIsSelectedException;
import Exceptions.idNotFoundException;
import Model.Snack;
import Model.Visitor;
import Model.Zoo;
import Utils.Discount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class purchaseSnack {

    @FXML
    private AnchorPane screen;

    @FXML
    private ListView<Visitor> visitorList;

    @FXML
    private ListView<Snack> snackList;

    @FXML
    private TextField visitorID;

    @FXML
    private TextField snackID;

    @FXML
    void back(ActionEvent event) {
    	if (MyMethods.getEmployee() == null)// => then its an admin
		MyMethods.newPage(screen, "MenuAdmin.fxml");
	else
		MyMethods.newPage(screen, "MenuEmployee.fxml");
    }
    //the purchase methods sets call the method purchaseSnack after getting the input objects either by id or selection
		//the methods check for exceptions first
    @FXML
    void purchaseID(ActionEvent event) {
    	try {
    		int visitorId = Integer.parseInt(visitorID.getText());
    		int snackId = Integer.parseInt(snackID.getText());
			if(Zoo.getInstance().getRealVisitor(visitorId)==null||Zoo.getInstance().getRealSnack(snackId)==null)
				throw new idNotFoundException();
			Zoo.getInstance().getRealVisitor(visitorId).purchaseSnack(Zoo.getInstance().getRealSnack(snackId));
			update(null);
    	}
    	catch (NumberFormatException e) {
    		MyMethods.infoMessage("Error!", "Invalid Input!\nPlease enter the values again");
		} catch (ErrorMessage e) {
			e.takeCare();
		}
    }
    @FXML
    void purshaseSelected(ActionEvent event) {
    	try {
			if(snackList.getSelectionModel().getSelectedItem()==null||visitorList.getSelectionModel().getSelectedItem()==null)
				throw new NothingIsSelectedException();
			visitorList.getSelectionModel().getSelectedItem().purchaseSnack(snackList.getSelectionModel().getSelectedItem());
			update(null);
    	}
    	catch (NumberFormatException e) {
    		MyMethods.infoMessage("Error!", "Invalid Input!\nPlease enter the values again");
		} catch (ErrorMessage e) {
			e.takeCare();
		}
    }
    	@FXML
        void update(MouseEvent event) {//updates the second list according to the visitor
    		if(visitorList.getSelectionModel().getSelectedItem()!=null) {
    			snackList.getItems().removeAll(snackList.getItems());
    			snackList.getItems().addAll(visitorList.getSelectionModel().getSelectedItem().getSection().getBar().getSnacks());
        		}
        		else {
        			return;
        		}
        }
    	@FXML
    	void initialize() {
    		
    		if(MyMethods.getEmployee()==null) {//->admin
    		visitorList.getItems().removeAll(visitorList.getItems());
    		visitorList.getItems().addAll(Zoo.getInstance().getVisitors().values());
    		}
    		else {//->employee
    			visitorList.getItems().removeAll(visitorList.getItems());
        		visitorList.getItems().addAll(MyMethods.getEmployee().getSection().getVisitors());
    		}
    		

    	}
}