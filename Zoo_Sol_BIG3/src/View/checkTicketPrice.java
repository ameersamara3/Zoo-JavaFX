package View;

import Exceptions.ErrorMessage;
import Exceptions.NothingIsSelectedException;
import Exceptions.idNotFoundException;
import Model.Visitor;
import Model.Zoo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class checkTicketPrice {

	@FXML
	private AnchorPane screen;

	@FXML
	private ListView<Visitor> list;

	@FXML
	private Label label;

	@FXML
	private TextField id;

	@FXML
	void back(ActionEvent event) {
		MyMethods.newPage(screen, "MenuAdmin.fxml");

	}
	//the check methods sets call the method checkTicketPrice after getting the input visitor either by id or selection
	//the methods check for exceptions first
	@FXML
	void checkID(ActionEvent event) {
		try {
			int visitorId = Integer.parseInt(id.getText());
			if (Zoo.getInstance().getRealVisitor(visitorId) == null)
				throw new idNotFoundException();
			label.setText("" + Zoo.getInstance().getRealVisitor(visitorId).checkTicketPrice());
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
			label.setText("" + list.getSelectionModel().getSelectedItem().checkTicketPrice());
		} catch (ErrorMessage e) {
			e.takeCare();
		}
	}

	@FXML
	void initialize() {
		list.getItems().removeAll(list.getItems());//initialize list
		list.getItems().addAll(Zoo.getInstance().getVisitors().values());

	}
}
