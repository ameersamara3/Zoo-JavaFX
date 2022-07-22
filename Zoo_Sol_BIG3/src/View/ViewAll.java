package View;

import java.nio.file.Path;
import java.nio.file.Paths;

import Model.Zoo;
import Model.ZooEmployee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

public class ViewAll {
	ObservableList<String> animals = FXCollections.observableArrayList("Mammal", "Reptile", "Bird");
	@FXML
	private AnchorPane screen;

	@FXML
	private ChoiceBox<String> animalChoice;
	  @FXML
	    private Pane photoPane;
	@FXML
	private RadioButton animal;
	@FXML
	private ImageView imageView;
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
	private ListView<Object> list;

	@FXML
	void uploadImage(MouseEvent event) {
		try {
		if (list.getSelectionModel().getSelectedItem() != null
				&& list.getSelectionModel().getSelectedItem() instanceof ZooEmployee) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			Path file = Paths.get(fileChooser.showOpenDialog(screen.getScene().getWindow()).toURI());
			String type = file.getFileName().toString();
			type = type.substring(type.length() - 3);
			if (!(type.equals("PNG") || type.equals("JPG") || type.equals("peg") || type.equals("png")
					|| type.equals("jpg"))) {
				file = null;
				MyMethods.infoMessage("Error!", "Please enter a jpg or png file");
				return;
			}
			((ZooEmployee)list.getSelectionModel().getSelectedItem()).setPhoto(file.toUri().toString());
			showImage(null);
		}
		}catch(Exception e)
		{
			return;
		}

	}

	@FXML
	private ImageView upload;
	
	@FXML
	void view(ActionEvent event) {//updates the list according to the selected object
		if (employee.isSelected()) {
			list.getItems().removeAll(list.getItems());
			list.getItems().addAll(Zoo.getInstance().getEmployees().values());
		} else if (visitor.isSelected()) {
			list.getItems().removeAll(list.getItems());
			list.getItems().addAll(Zoo.getInstance().getVisitors().values());
		} else if (section.isSelected()) {
			list.getItems().removeAll(list.getItems());
			list.getItems().addAll(Zoo.getInstance().getSections().values());
		} else if (snack.isSelected()) {
			list.getItems().removeAll(list.getItems());
			list.getItems().addAll(Zoo.getInstance().getSnacks().values());
		} else if (bar.isSelected()) {
			list.getItems().removeAll(list.getItems());
			list.getItems().addAll(Zoo.getInstance().getBars().values());
		} else {
			if (animalChoice.getValue().equals("Mammal")) {
				list.getItems().removeAll(list.getItems());
				list.getItems().addAll(Zoo.getInstance().getMammals().values());
			} else if (animalChoice.getValue().equals("Reptile")) {
				list.getItems().removeAll(list.getItems());
				list.getItems().addAll(Zoo.getInstance().getReptiles().values());
			} else {
				list.getItems().removeAll(list.getItems());
				list.getItems().addAll(Zoo.getInstance().getBirds().values());
			}
		}
	}

	@FXML
	void back(ActionEvent event) {
		if (MyMethods.getEmployee() == null)// => then its an admin
			MyMethods.newPage(screen, "MenuAdmin.fxml");
		else
			MyMethods.newPage(screen, "MenuEmployee.fxml");

	}

	@FXML
	void showImage(MouseEvent event) {//checks if the selected object is an employee and then shows his image
		if (list.getSelectionModel().getSelectedItem() != null
				&& list.getSelectionModel().getSelectedItem() instanceof ZooEmployee) {
			photoPane.setVisible(true);
			ZooEmployee e = (ZooEmployee) list.getSelectionModel().getSelectedItem();
			if (e.getPhoto() != null) {
				Image image = new Image(e.getPhoto());
				imageView.setImage(image);
			} else {
				Image image = new Image(getClass().getResource("View/photoszoo/user.jpg").toString());
				imageView.setImage(image);
			}
		} else {
			photoPane.setVisible(false);
		}
	}

	@FXML
	void initialize() {
		list.getItems().removeAll(list.getItems());
		list.getItems().addAll(Zoo.getInstance().getMammals().values());
		animalChoice.setItems(animals);
		animalChoice.setValue("Mammal");
		animal.setSelected(true);
		photoPane.setVisible(false);

	}

}
