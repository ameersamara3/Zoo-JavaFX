package View;

import Exceptions.DiscountCheckException;
import Exceptions.ErrorMessage;
import Exceptions.NothingIsSelectedException;
import Exceptions.idNotFoundException;
import Model.Visitor;
import Model.Zoo;
import Utils.Discount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class setDiscount {
	private Visitor visitor;
	ObservableList<Discount> discounts = FXCollections.observableArrayList(Discount.values());

	@FXML
	private AnchorPane screen;

	@FXML
	private TextField id;

	@FXML
	private ListView<Visitor> list;

	@FXML
	private ChoiceBox<Discount> choice;

	@FXML
	void back(ActionEvent event) {
		if (MyMethods.getEmployee() == null)// => then its an admin
			MyMethods.newPage(screen, "MenuAdmin.fxml");
		else
			MyMethods.newPage(screen, "MenuEmployee.fxml");
	}
//the choose methods updates the visitor variable and the choice box (according to the visitor)
	@FXML
	void chooseID(ActionEvent event) {
		try {
			int visitorId = Integer.parseInt(id.getText());
			if (Zoo.getInstance().getRealVisitor(visitorId) == null)
				throw new idNotFoundException();
			visitor = Zoo.getInstance().getRealVisitor(visitorId);
			choice.setValue(visitor.getDiscount());
		} catch (NumberFormatException e) {
			MyMethods.infoMessage("Error!", "Invalid Input!\nPlease enter the values again");
		} catch (ErrorMessage e) {
			e.takeCare();
		}
	}

	@FXML
	void chooseSelected(ActionEvent event) {
		try {
			if (list.getSelectionModel().getSelectedItem() == null)
				throw new NothingIsSelectedException();
			visitor = list.getSelectionModel().getSelectedItem();
			choice.setValue(visitor.getDiscount());

		} catch (ErrorMessage e) {
			e.takeCare();
		}
	}

	@FXML
	void set(ActionEvent event) {//updates the discount //checks for exceptions first
		try {
			if (visitor != null) {

				if (choice.getValue().getPercentage() <= 25) {
					visitor.setDiscount(choice.getValue());
					MyMethods.infoMessage("Success!",
							"You have changed successfully " + visitor.getFirstName() + "'s discount");
				} else
					throw new DiscountCheckException();

			} else
				throw new NothingIsSelectedException();

		} catch (ErrorMessage e) {
			e.takeCare();
		}
	}

	@FXML
	void initialize() {

		list.getItems().removeAll(list.getItems());
		list.getItems().addAll(Zoo.getInstance().getVisitors().values());
		choice.setItems(discounts);
	}

}
