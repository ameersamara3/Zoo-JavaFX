package Utils;

import Model.Zoo;
import Model.ZooEmployee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class addEmployee {
	ObservableList<Job> olist=FXCollections.observableArrayList(Job.values());

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private TextField section;

    @FXML
    private RadioButton male;

    @FXML
    private RadioButton female;

    @FXML
    private RadioButton unknown;

    @FXML
    private ChoiceBox<Job> job;

    @FXML
    private DatePicker bday;
    @FXML
    void submit(ActionEvent event) {
    	//System.out.println(male.isSelected());
    	/*Gender gender;
    	if(male.isSelected())
    		gender=Gender.Male;
    	else if(female.isSelected())
    		gender=Gender.Female;
    	else if(unknown.isSelected())
    		gender=Gender.Unknown;
    	else gender=null;
    	System.out.println(gender);*/
    	try
        {
          // the String to int conversion happens here
          int i = Integer.parseInt(section.getText());
      	//Zoo.getInstance().addEmployee(new ZooEmployee(fname.getText(),lname.getText(),bday.getValue(),Gender.Male,Zoo.getInstance().getRealSection(i),job.getValue()));
      	//System.out.println(Zoo.getInstance().getEmployees());
          // print out the value after the conversion
          System.out.println("int i = " + i);
        }
        catch (NumberFormatException nfe)
        {
          System.out.println("NumberFormatException: " + nfe.getMessage());
        }

      }

    
    @FXML
    void initialize() {
    	job.setItems(olist);
    	job.setValue(Job.Vet);
        

    }
}
