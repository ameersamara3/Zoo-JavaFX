package View;

import Exceptions.ErrorMessage;
import Exceptions.idNotFoundException;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class getReal {
	ObservableList<String> animals = FXCollections.observableArrayList("Mammal", "Reptile", "Bird");
	@FXML
	private AnchorPane screen;

	@FXML
	private ChoiceBox<String> animalChoice;

	@FXML
	private RadioButton animal;

	@FXML
	private ToggleGroup choose;

	@FXML
	private RadioButton visitor;

	@FXML
	private RadioButton employee;

	@FXML
	private RadioButton snack;

	@FXML
	private RadioButton bar;

	@FXML
	private RadioButton section;

	@FXML
	private TextField id;
	@FXML
	private Label label;

	@FXML
	void view(ActionEvent event) {//sets the label accordingly to the id and selected object
		try {
			int Id = Integer.parseInt(id.getText());
			if (employee.isSelected()) {
				ZooEmployee e = Zoo.getInstance().getRealEmployee(Id);
				if (e == null)
					throw new idNotFoundException();
				label.setText(e.toString());

			} else if (visitor.isSelected()) {
				Visitor e = Zoo.getInstance().getRealVisitor(Id);
				if (e == null)
					throw new idNotFoundException();
				label.setText(e.toString());

			} else if (section.isSelected()) {
				Section e = Zoo.getInstance().getRealSection(Id);
				if (e == null)
					throw new idNotFoundException();
				label.setText(e.toString());

			} else if (snack.isSelected()) {
				Snack e = Zoo.getInstance().getRealSnack(Id);
				if (e == null)
					throw new idNotFoundException();
				label.setText(e.toString());

			} else if (bar.isSelected()) {
				SnackBar e = Zoo.getInstance().getRealSnackBar(Id);
				if (e == null)
					throw new idNotFoundException();
				label.setText(e.toString());

			} else {
				if (animalChoice.getValue().equals("Mammal")) {
					Mammal e = Zoo.getInstance().getRealMammal(Id);
					if (e == null)
						throw new idNotFoundException();
					label.setText(e.toString());

				} else if (animalChoice.getValue().equals("Reptile")) {
					Reptile e = Zoo.getInstance().getRealReptile(Id);
					if (e == null)
						throw new idNotFoundException();
					label.setText(e.toString());

				} else {
					Bird e = Zoo.getInstance().getRealBird(Id);
					if (e == null)
						throw new idNotFoundException();
					label.setText(e.toString());

				}

			}
			MyMethods.solve(label);//comments are in the method
		} catch (NumberFormatException e) {
			MyMethods.infoMessage("Error!", "Invalid Input!\nPlease enter the values again");
		} catch (ErrorMessage e) {
			e.takeCare();
		}
	}

	
	@FXML
	void back(ActionEvent event) {
			MyMethods.newPage(screen,"MenuAdmin.fxml");
	}

	@FXML
	void initialize() {
		animalChoice.setItems(animals);
		animalChoice.setValue("Mammal");
		animal.setSelected(true);

	}

}
