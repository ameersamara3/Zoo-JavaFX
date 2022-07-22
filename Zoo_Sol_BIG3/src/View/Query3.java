package View;

import Exceptions.ErrorMessage;
import Exceptions.NothingIsSelectedException;
import Exceptions.idNotFoundException;
import Model.Animal;
import Model.Section;
import Model.Snack;
import Model.SnackBar;
import Model.Zoo;
import Model.ZooEmployee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class Query3 {

	@FXML
	private AnchorPane screen;

	@FXML
	private TextField id;

	@FXML
	private ListView<ZooEmployee> inputList;
	//the view methods sets call the method allAnimalsByWorker after getting the input objects either by id or selection
	//the methods check for exceptions first
	@FXML
	void ViewSelected(ActionEvent event) {
		try {
			if (inputList.getSelectionModel().getSelectedItem() == null) {
				throw new NothingIsSelectedException();

			}
			
			outputList.getItems().removeAll(outputList.getItems());
			if(Zoo.getInstance().allAnimalsByWorker(inputList.getSelectionModel().getSelectedItem())==null)
				return;
			outputList.getItems()
					.addAll(Zoo.getInstance().allAnimalsByWorker(inputList.getSelectionModel().getSelectedItem()));
		} catch (ErrorMessage e) {
			e.takeCare();
		}
	}

	@FXML
	private ListView<Animal> outputList;

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
			if(Zoo.getInstance().allAnimalsByWorker(Zoo.getInstance().getRealEmployee(Id))==null) {
				outputList.getItems().removeAll(outputList.getItems());
				return;
			}
				
			if (Zoo.getInstance().getRealEmployee(Id) == null)
				throw new idNotFoundException();
			outputList.getItems().removeAll(outputList.getItems());
			outputList.getItems().addAll(Zoo.getInstance().allAnimalsByWorker(Zoo.getInstance().getRealEmployee(Id)));
		} catch (NumberFormatException e) {
			MyMethods.infoMessage("Error!", "Invalid Input!\nPlease enter the values again");
		} catch (ErrorMessage e) {
			e.takeCare();
		}
	}

	@FXML
	void initialize() {

		inputList.getItems().removeAll(inputList.getItems());
		inputList.getItems().addAll(Zoo.getInstance().getEmployees().values());
	}
}
