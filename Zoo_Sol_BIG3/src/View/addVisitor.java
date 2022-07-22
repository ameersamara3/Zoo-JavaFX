package View;

import java.io.IOException;

import Exceptions.DiscountCheckException;
import Exceptions.EmptyFieldException;
import Exceptions.ErrorMessage;
import Exceptions.FailedToException;
import Exceptions.idNotFoundException;
import Model.Section;
import Model.Visitor;
import Model.Zoo;
import Model.ZooEmployee;
import Utils.Discount;
import Utils.Gender;
import Utils.Job;
import Utils.TicketType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

public class addVisitor {
	ObservableList<TicketType> tickets = FXCollections.observableArrayList(TicketType.values());
	ObservableList<Discount> discounts = FXCollections.observableArrayList(Discount.values());

	@FXML
	private TextField fname;

	@FXML
	private TextField lname;

	@FXML
	private ChoiceBox<TicketType> ticket;

	@FXML
	private DatePicker bday;

	@FXML
	private RadioButton male;

	@FXML
	private ToggleGroup gender;

	@FXML
	private RadioButton female;

	@FXML
	private RadioButton unknown;
	@FXML
	private TextField section;
	@FXML
	private ChoiceBox<Discount> discount;

	@FXML
	void submit(ActionEvent event) {//the comments are the same as add bird
		Gender gender2 = Gender.Unknown;
		if (male.equals(gender.getSelectedToggle()))
			gender2 = Gender.Male;
		if (female.equals(gender.getSelectedToggle()))
			gender2 = Gender.Female;
		try {
			int sectionId = Integer.parseInt(section.getText());
			if(discount.getValue().getPercentage()>25)//checks if the discount is more than 25% 
				throw new DiscountCheckException();
			if(fname.getText().equals("")||bday.getValue()==null||lname.getText().equals(""))
				throw new EmptyFieldException();
			if(Zoo.getInstance().getRealSection(sectionId)==null)
				throw new idNotFoundException();
			
			boolean flag=Zoo.getInstance().newVisitorInZoo(new Visitor(fname.getText(), lname.getText(), bday.getValue(), gender2,
					ticket.getValue(), discount.getValue()), Zoo.getInstance().getRealSection(sectionId));
			if(flag)
	    	{
				MyMethods.infoMessage("Success!", "Successfully added");
	    	}
	    	else {
	    		throw new FailedToException("add");	    		
	    	}
		} catch (NumberFormatException e) {
			MyMethods.infoMessage("Error!", "Invalid Input!\nPlease enter the values again");
		} catch (ErrorMessage e) {
			e.takeCare();
		}
	}
	@FXML
	private Pane screen;

	@FXML
	void back(ActionEvent event) {

		if (MyMethods.getEmployee() == null)// => then its an admin
			MyMethods.newPage(screen, "addByAdmin.fxml");
		else
			MyMethods.newPage(screen, "addByEmployee.fxml");

	}

	@FXML
	void initialize() {
		ticket.setItems(tickets);
		ticket.setValue(TicketType.Child);
		discount.setItems(discounts);
		discount.setValue(Discount.No_Discount);
		female.setSelected(true);

	}

}
