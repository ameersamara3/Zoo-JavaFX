package View;

import Exceptions.ErrorMessage;
import Exceptions.FailedToException;
import Exceptions.NothingIsSelectedException;
import Model.Bird;
import Model.Mammal;
import Model.Reptile;
import Model.Section;
import Model.Snack;
import Model.SnackBar;
import Model.Visitor;
import Model.Zoo;
import Model.ZooEmployee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class RemoveByEmployee {

	@FXML
	private AnchorPane screen;
	@FXML
    private Label welcome;

	@FXML
	private ToggleGroup choose;

	@FXML
	private RadioButton visitor;

	@FXML
	private RadioButton employee;

	@FXML
	private RadioButton snack;

	@FXML
	private ListView<Object> list;
	@FXML
	private TextField id;

	@FXML
	void RemoveSelected(ActionEvent event) {//same as remove by admin
		try {
			if(list.getSelectionModel().getSelectedItem()==null)
				throw new NothingIsSelectedException();
			boolean flag;
			if (employee.isSelected()) {
				flag = Zoo.getInstance().removeEmployee((ZooEmployee)list.getSelectionModel().getSelectedItem());
				list.getItems().removeAll(list.getItems());
				list.getItems().addAll(Zoo.getInstance().getEmployees().values());
			} else if (visitor.isSelected()) {
				flag = Zoo.getInstance().removeVisitor((Visitor)list.getSelectionModel().getSelectedItem());
				list.getItems().removeAll(list.getItems());
				list.getItems().addAll(Zoo.getInstance().getVisitors().values());
			}  else{
				flag = Zoo.getInstance().removeSnack((Snack)list.getSelectionModel().getSelectedItem());
				list.getItems().removeAll(list.getItems());
				list.getItems().addAll(Zoo.getInstance().getSnacks().values());
			} 
			

			
			if (flag) {
				MyMethods.infoMessage("Success!", "Successfully removed");
			} else {
				throw new FailedToException("remove");
			}
		} catch (NumberFormatException e) {
			MyMethods.infoMessage("Error!", "Invalid Input!\nPlease enter the values again");
		} catch (ErrorMessage e) {
			e.takeCare();
		}
	}

	@FXML
	void back(ActionEvent event) {
		MyMethods.newPage(screen, "MenuEmployee.fxml");
	}
	@FXML
    void click(MouseEvent event) {
		if (employee.isSelected()) {
			list.getItems().removeAll(list.getItems());
			list.getItems().addAll(Zoo.getInstance().getEmployees().values());
		} else if (visitor.isSelected()) {
			list.getItems().removeAll(list.getItems());
			list.getItems().addAll(Zoo.getInstance().getVisitors().values());
		}  else{
			list.getItems().removeAll(list.getItems());
			list.getItems().addAll(Zoo.getInstance().getSnacks().values());
		} 
    }
	@FXML
	void removeID(ActionEvent event) {
		try {
			int Id = Integer.parseInt(id.getText());
			boolean flag;
			if (employee.isSelected()) {
				flag = Zoo.getInstance().removeEmployee(Zoo.getInstance().getRealEmployee(Id));
				list.getItems().removeAll(list.getItems());
				list.getItems().addAll(Zoo.getInstance().getEmployees().values());
			} else if (visitor.isSelected()) {
				flag = Zoo.getInstance().removeVisitor(Zoo.getInstance().getRealVisitor(Id));
				list.getItems().removeAll(list.getItems());
				list.getItems().addAll(Zoo.getInstance().getVisitors().values());
			}  else{
				flag = Zoo.getInstance().removeSnack(Zoo.getInstance().getRealSnack(Id));
				list.getItems().removeAll(list.getItems());
				list.getItems().addAll(Zoo.getInstance().getSnacks().values());
			} 

			if (flag) {
				Alert a = new Alert(Alert.AlertType.INFORMATION);
				a.setTitle("Success!!");
				a.setContentText("Successfully removed");
				a.setHeaderText(null);
				a.showAndWait();
			} else {
				throw new FailedToException("remove");
			}
		}catch (NumberFormatException e) {
			MyMethods.infoMessage("Error!", "Invalid Input!\nPlease enter the values again");
		} catch (ErrorMessage e) {
			e.takeCare();
		}

	}

	@FXML
	void initialize() {
		snack.setSelected(true);
		click(null);
	}

}
