package View;

import java.io.IOException;

import Exceptions.EmptyFieldException;
import Exceptions.ErrorMessage;
import Exceptions.FailedToException;
import Exceptions.idNotFoundException;
import Model.Section;
import Model.Visitor;
import Model.Zoo;
import Utils.Job;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class addSection {

    @FXML
    private TextField name;

    @FXML
    private TextField capacity;


    @FXML
    void submit(ActionEvent event) {//the comments are the same as add bird
    	try {
			int cap = Integer.parseInt(capacity.getText());
			if(name.getText().equals(""))
				throw new EmptyFieldException();
		    boolean flag=Zoo.getInstance().addSection(new Section(name.getText(),cap));

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

}
