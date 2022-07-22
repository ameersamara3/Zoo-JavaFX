package View;

import Exceptions.ErrorMessage;
import Exceptions.FailedToException;
import Exceptions.NothingIsSelectedException;
import Exceptions.idNotFoundException;
import Model.Section;
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

public class moveVisitor {
    @FXML
    private AnchorPane screen;

    @FXML
    private ListView<Visitor> visitorList;

    @FXML
    private ListView<Section> sectionList;

    @FXML
    private TextField visitorID;

    @FXML
    private TextField sectionID;

    @FXML
    void back(ActionEvent event) {
    	if (MyMethods.getEmployee() == null)// => then its an admin
			MyMethods.newPage(screen, "MenuAdmin.fxml");
		else
			MyMethods.newPage(screen, "MenuEmployee.fxml");
    }
  //the move methods sets call the method moveVisitorToSection after getting the input objects either by id or selection
  		//the methods check for exceptions first
    @FXML
    void moveID(ActionEvent event) {
    	try {
    		int visitorId = Integer.parseInt(visitorID.getText());
    		int sectionId = Integer.parseInt(sectionID.getText());
			if(Zoo.getInstance().getRealVisitor(visitorId)==null||Zoo.getInstance().getRealSection(sectionId)==null)
				throw new idNotFoundException();
			Zoo.getInstance().getRealVisitor(visitorId).moveVisitorToSection(Zoo.getInstance().getRealSection(sectionId));
			update(null);
    	}
    	catch (NumberFormatException e) {
    		MyMethods.infoMessage("Error!", "Invalid Input!\nPlease enter the values again");
		} catch (ErrorMessage e) {
			e.takeCare();
		}
    }
    @FXML
    void moveSelected(ActionEvent event) {
    	try {
			if(sectionList.getSelectionModel().getSelectedItem()==null||visitorList.getSelectionModel().getSelectedItem()==null)
				throw new NothingIsSelectedException();
			visitorList.getSelectionModel().getSelectedItem().moveVisitorToSection(sectionList.getSelectionModel().getSelectedItem());
			update(null);
    	}
    	catch (NumberFormatException e) {
    		MyMethods.infoMessage("Error!", "Invalid Input!\nPlease enter the values again");
		} catch (ErrorMessage e) {
			e.takeCare();
		}
    }
    	@FXML
        void update(MouseEvent event) {//updates the section list (removes the visitor section from the list)
    		if(visitorList.getSelectionModel().getSelectedItem()!=null) {
    			sectionList.getItems().removeAll(sectionList.getItems());
    			sectionList.getItems().addAll(Zoo.getInstance().getSections().values());
    			sectionList.getItems().remove(visitorList.getSelectionModel().getSelectedItem().getSection());
        		}
        		else {
        			return;
        		}
        }
    	@FXML
    	void initialize() {
    		
    		if(MyMethods.getEmployee()==null) {//if the user is admin
    		visitorList.getItems().removeAll(visitorList.getItems());
    		visitorList.getItems().addAll(Zoo.getInstance().getVisitors().values());
    		}
    		else {//the user is employee
    			visitorList.getItems().removeAll(visitorList.getItems());
        		visitorList.getItems().addAll(MyMethods.getEmployee().getSection().getVisitors());
    		}
    		

    	}
}