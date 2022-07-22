package View;

import java.io.IOException;

import Exceptions.EmptyFieldException;
import Exceptions.ErrorMessage;
import Exceptions.FailedToException;
import Exceptions.idNotFoundException;
import Model.Section;
import Model.SnackBar;
import Model.Zoo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class addSnackBar {

	@FXML
	private TextField name;

	@FXML
	private TextField section;
	@FXML
	private Pane screen;

	@FXML
	void back(ActionEvent event) {
		MyMethods.newPage(screen, "addByAdmin.fxml");

	}

	@FXML
	void submit(ActionEvent event) {
		try {

			int id = Integer.parseInt(section.getText());
			if (name.getText().equals(""))
				throw new EmptyFieldException();
			if(Zoo.getInstance().getRealSection(id)==null)
				throw new idNotFoundException();
			boolean flag = Zoo.getInstance().addSnackBar(
					new SnackBar(name.getText(), Zoo.getInstance().getRealSection(id)),
					Zoo.getInstance().getRealSection(id));
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

}
