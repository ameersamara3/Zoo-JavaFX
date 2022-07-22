package View;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import Exceptions.DuplicatedValues;
import Exceptions.EmptyFieldException;
import Exceptions.ErrorMessage;
import Exceptions.FailedToException;
import Exceptions.idNotFoundException;
import Model.Reptile;
import Model.Zoo;
import Model.ZooEmployee;
import Utils.Gender;
import Utils.Job;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class addEmployeeByEmployee {
	ObservableList<Job> olist = FXCollections.observableArrayList(Job.values());
	private Path file = null;
	@FXML
	private TextField fname;

	@FXML
	private TextField lname;

	@FXML
	private ChoiceBox<Job> job;

	@FXML
	private DatePicker bday;

	@FXML
	private RadioButton male;

	@FXML
	private RadioButton female;

	@FXML
	private RadioButton unknown;
	@FXML
	private ToggleGroup gender;
	@FXML
	private TextField photoPath;
	@FXML
	void submit(ActionEvent event) {//the comments are the same as add bird
		
		Gender gender2 = Gender.Unknown;
		if (male.equals(gender.getSelectedToggle()))
			gender2 = Gender.Male;
		if (female.equals(gender.getSelectedToggle()))
			gender2 = Gender.Female;
		try {
			if(fname.getText().equals("")||bday.getValue()==null||lname.getText().equals(""))
				throw new EmptyFieldException();
			ZooEmployee e=new ZooEmployee(fname.getText(), lname.getText(), bday.getValue(), gender2,
					MyMethods.getEmployee().getSection(), job.getValue());
			boolean flag=Zoo.getInstance().addEmployee(e);
			if(flag)
	    	{
				if(file!=null)
					e.setPhoto(file.toUri().toString());
				MyMethods.infoMessage("Success!", "Successfully added");
	    	}
	    	else {
	    		throw new FailedToException("add");	    		
	    	}
			
		}  catch (ErrorMessage e) {
			e.takeCare();
		}
		catch (Exception e) {
			return;
		}
	}
	@FXML
	void upload(MouseEvent event) {//uploads photo from a local place (cannot carry over to another computer)
		try {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			file = Paths.get(fileChooser.showOpenDialog(screen.getScene().getWindow()).toURI());
			String fileName = file.getFileName().toString();
			String type = file.getFileName().toString();
			type = type.substring(type.length() - 3);
			if (!(type.equals("PNG") || type.equals("JPG") || type.equals("peg") || type.equals("png")//checks if the file is a photo
					|| type.equals("jpg"))) {
				file = null;
				MyMethods.infoMessage("Error!", "Please enter a jpg or png file");
				return;
			}
			photoPath.setText(fileName);
		} catch (Exception e) {
			return;
		}
	}
	@FXML
	private Pane screen;

	@FXML
	void back(ActionEvent event) {
		MyMethods.newPage(screen, "addByEmployee.fxml");

	}

	@FXML
	void initialize() {
		job.setItems(olist);
		job.setValue(Job.Vet);
		female.setSelected(true);

	}
}
