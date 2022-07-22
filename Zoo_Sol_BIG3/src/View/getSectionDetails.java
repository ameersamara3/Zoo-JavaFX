package View;

import Exceptions.ErrorMessage;
import Exceptions.NothingIsSelectedException;
import Exceptions.idNotFoundException;
import Model.Section;
import Model.Visitor;
import Model.Zoo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class getSectionDetails {

	@FXML
	private AnchorPane screen;

	@FXML
	private ListView<Section> list;

	@FXML
	private Label label;

	@FXML
	private TextField id;

	@FXML
	void back(ActionEvent event) {
		if (MyMethods.getEmployee() == null)// => then its an admin
			MyMethods.newPage(screen, "MenuAdmin.fxml");
		else
			MyMethods.newPage(screen, "MenuEmployee.fxml");

	}
	//the check methods sets call the method getSectionDetails after getting the input section either by id or selection
		//the methods check for exceptions first
	@FXML
	void checkID(ActionEvent event) {
		try {
			int SectionId = Integer.parseInt(id.getText());
			if (Zoo.getInstance().getRealSection(SectionId) == null)
				throw new idNotFoundException();
			label.setText(Zoo.getInstance().getRealSection(SectionId).getSectionDetails());
			MyMethods.solve(label);
		} catch (NumberFormatException e) {
			MyMethods.infoMessage("Error!", "Invalid Input!\nPlease enter the values again");
		} catch (ErrorMessage e) {
			e.takeCare();
		}
		
	}

	@FXML
	void checkSelected(ActionEvent event) {
		try {
			if (list.getSelectionModel().getSelectedItem() == null)
				throw new NothingIsSelectedException();
			label.setText(list.getSelectionModel().getSelectedItem().getSectionDetails());
			MyMethods.solve(label);
		} catch (ErrorMessage e) {
			e.takeCare();
		}
		
	}

	@FXML
	void initialize() {
		list.getItems().removeAll(list.getItems());
		list.getItems().addAll(Zoo.getInstance().getSections().values());

	}
}
