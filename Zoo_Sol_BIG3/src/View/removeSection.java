package View;

import Exceptions.ErrorMessage;
import Exceptions.FailedToException;
import Exceptions.NothingIsSelectedException;
import Exceptions.idNotFoundException;
import Model.Section;
import Model.Snack;
import Model.Visitor;
import Model.Zoo;
import Utils.Discount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class removeSection {
	@FXML
	private AnchorPane screen;

	@FXML
	private ListView<Section> section2List;

	@FXML
	private ListView<Section> section1List;

	@FXML
	private TextField section2ID;

	@FXML
	private TextField section1ID;

	@FXML
	void back(ActionEvent event) {
		MyMethods.newPage(screen, "RemoveByAdmin.fxml");
	}

	@FXML
	void removeID(ActionEvent event) {//same as remove by admin
		try {
			int section1Id = Integer.parseInt(section1ID.getText());
			int section2Id = Integer.parseInt(section2ID.getText());
			if (Zoo.getInstance().getRealVisitor(section1Id) == null
					|| Zoo.getInstance().getRealSection(section2Id) == null)
				throw new idNotFoundException();
			boolean flag = Zoo.getInstance().removeSection(Zoo.getInstance().getRealSection(section1Id),
					Zoo.getInstance().getRealSection(section2Id));
			update(null);
			if (flag) {
				MyMethods.infoMessage("Seccess!",
						"Seccessfully removed " +  Zoo.getInstance().getRealSection(section1Id));
				section1List.getItems().removeAll(section1List.getItems());
				section1List.getItems().addAll(Zoo.getInstance().getSections().values());

			} else
				throw new FailedToException("remove");
		} catch (NumberFormatException e) {
			MyMethods.infoMessage("Error!", "Invalid Input!\nPlease enter the values again");
		} catch (ErrorMessage e) {
			e.takeCare();
		}
	}

	@FXML
    void removeSelected(ActionEvent event) {
    	try {
			if(section2List.getSelectionModel().getSelectedItem()==null||section1List.getSelectionModel().getSelectedItem()==null)
				throw new NothingIsSelectedException();
			boolean flag=Zoo.getInstance().removeSection(section1List.getSelectionModel().getSelectedItem(),section2List.getSelectionModel().getSelectedItem());
			update(null);
			if(flag) {
				MyMethods.infoMessage("Seccess!", "Seccessfully removed "+section1List.getSelectionModel().getSelectedItem().getSectionName());
				section1List.getItems().removeAll(section1List.getItems());
				section1List.getItems().addAll(Zoo.getInstance().getSections().values());
			}
			else
				throw new FailedToException("remove");
    	}
    	catch (NumberFormatException e) {
    		MyMethods.infoMessage("Error!", "Invalid Input!\nPlease enter the values again");
		} catch (ErrorMessage e) {
			e.takeCare();
		}
    }

	@FXML
	void update(MouseEvent event) {
		if (section1List.getSelectionModel().getSelectedItem() != null) {
			section2List.getItems().removeAll(section2List.getItems());
			section2List.getItems().addAll(Zoo.getInstance().getSections().values());
			section2List.getItems().remove(section1List.getSelectionModel().getSelectedItem());
		} else {
			return;
		}
	}

	@FXML
	void initialize() {
		section1List.getItems().removeAll(section1List.getItems());
		section1List.getItems().addAll(Zoo.getInstance().getSections().values());

	}
}