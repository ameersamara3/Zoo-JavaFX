package View;

import Exceptions.ErrorMessage;
import Exceptions.NothingIsSelectedException;
import Exceptions.idNotFoundException;
import Model.Snack;
import Model.SnackBar;
import Model.Zoo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class Query1 {

	@FXML
	private AnchorPane screen;

	@FXML
	private TextField id;

	@FXML
	private ListView<SnackBar> inputList;
	//the view methods sets call the method findAllSnackByWorker after getting the input objects either by id or selection
			//the methods check for exceptions first
	@FXML
	void ViewSelected(ActionEvent event) {
		try {
		if (inputList.getSelectionModel().getSelectedItem() == null) {
			throw new NothingIsSelectedException();

		}
		if(Zoo.getInstance().findAllSnackByWorker(inputList.getSelectionModel().getSelectedItem())==null)
			return;
		outputList.getItems().removeAll(outputList.getItems());
		outputList.getItems()
				.addAll(Zoo.getInstance().findAllSnackByWorker(inputList.getSelectionModel().getSelectedItem()));
	}
		catch (ErrorMessage e) {
			e.takeCare();
		}
	}

	@FXML
	private ListView<Snack> outputList;

	@FXML
	void back(ActionEvent event) {
		if (MyMethods.getEmployee() == null)// => then its an admin
			MyMethods.newPage(screen, "MenuAdmin.fxml");
		else
			MyMethods.newPage(screen, "MenuEmployee.fxml");
	}

	@FXML
	void viewID(ActionEvent event) {
		try {
			int Id = Integer.parseInt(id.getText());
			if (Zoo.getInstance().getRealSnackBar(Id) == null)
				throw new idNotFoundException();
			if(Zoo.getInstance().findAllSnackByWorker(Zoo.getInstance().getRealSnackBar(Id))==null) {
				outputList.getItems().removeAll(outputList.getItems());
				return;}
			outputList.getItems().removeAll(outputList.getItems());
			outputList.getItems().addAll(Zoo.getInstance().findAllSnackByWorker(Zoo.getInstance().getRealSnackBar(Id)));
		} catch (NumberFormatException e) {
			MyMethods.infoMessage("Error!", "Invalid Input!\nPlease enter the values again");
		} catch (ErrorMessage e) {
			e.takeCare();
		}
	}

	@FXML
	void initialize() {

		inputList.getItems().removeAll(inputList.getItems());
		inputList.getItems().addAll(Zoo.getInstance().getBars().values());
	}
}
