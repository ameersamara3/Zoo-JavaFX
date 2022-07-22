package View;

import Exceptions.EmployeeAlreadyHasAnAccountException;
import Exceptions.EmptyFieldException;
import Exceptions.ErrorMessage;
import Exceptions.FailedToException;
import Exceptions.UsernameAlreadyExistsException;
import Exceptions.idNotFoundException;
import Exceptions.illegalPasswordException;
import Exceptions.passwordsDiffException;
import Model.Zoo;
import Utils.AnimalFood;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class signUp {

	@FXML
	private AnchorPane screen;

	@FXML
	private TextField username;

	@FXML
	private TextField id;

	@FXML
	private PasswordField password;

	@FXML
	private PasswordField confirmPassword;

	@FXML
	void create(ActionEvent event) {//this method creates an account // checks for exceptions first
		try {
			if(username.getText().equals("")||password.getText().equals(""))
				throw new EmptyFieldException();
			if (!password.getText().equals(confirmPassword.getText())) {
				throw new passwordsDiffException();
			}
			if (!MyMethods.isLegal(password.getText())) {
				throw new illegalPasswordException();
			}
			int Id = Integer.parseInt(id.getText());
			Zoo.getInstance().signUp(username.getText(), password.getText(), Id);
			MyMethods.setEmployee(Zoo.getInstance().getRealEmployee(Id));
			MyMethods.newPage(screen, "MenuEmployee.fxml");

		} catch (NumberFormatException e) {
			MyMethods.infoMessage("Error!", "Invalid Input!\nPlease enter the values again");
		} catch (ErrorMessage e) {
			e.takeCare();
		}

	}

	@FXML
	void back(ActionEvent event) {
		MyMethods.newPage(screen, "logIn.fxml");
	}

}