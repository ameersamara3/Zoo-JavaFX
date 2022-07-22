package View;

import Model.Zoo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MenuEmployee {

    @FXML
    private AnchorPane screen;
    @FXML
    private ImageView imageView;
    @FXML
    private Label label;
    @FXML
    void Revenue(ActionEvent event) {
		MyMethods.newPage(screen, "Revenue.fxml");

    }

    @FXML
    void add(ActionEvent event) {
		MyMethods.newPage(screen, "addByEmployee.fxml");

    }

    @FXML
    void covid(ActionEvent event) {
		MyMethods.newPage(screen, "Covid_19.fxml");

    }

    @FXML
    void setDiscount(ActionEvent event) {
		MyMethods.newPage(screen, "setDiscount.fxml");

    }

    @FXML
    void getReal(ActionEvent event) {
		MyMethods.newPage(screen, "getReal.fxml");

    }

    @FXML
    void logout(ActionEvent event) {
    	MyMethods.setEmployee(null);
		MyMethods.newPage(screen, "logIn.fxml");

    }

    @FXML
    void map(ActionEvent event) {
		MyMethods.newPage(screen, "Map.fxml");

    }

    @FXML
    void moveVisitor(ActionEvent event) {
		MyMethods.newPage(screen, "moveVisitor.fxml");

    }

    @FXML
    void purchaseSnack(ActionEvent event) {
		MyMethods.newPage(screen, "purchaseSnack.fxml");

    }

    @FXML
    void q1(ActionEvent event) {
		MyMethods.newPage(screen, "Query1.fxml");

    }

    @FXML
    void q2(ActionEvent event) {
		MyMethods.newPage(screen, "Query2.fxml");

    }

    @FXML
    void q3(ActionEvent event) {
		MyMethods.newPage(screen, "Query3.fxml");

    }

    @FXML
    void q4(ActionEvent event) {
		MyMethods.newPage(screen, "Query4.fxml");

    }

    @FXML
    void q5(ActionEvent event) {
		MyMethods.newPage(screen, "Query5.fxml");

    }

    @FXML
    void q6(ActionEvent event) {
		MyMethods.newPage(screen, "Query6.fxml");

    }

    @FXML
    void remove(ActionEvent event) {
		MyMethods.newPage(screen, "RemoveByEmployee.fxml");

    }


    @FXML
    void sectionDetails(ActionEvent event) {
		MyMethods.newPage(screen, "getSectionDetails.fxml");

    }

    @FXML
    void viewData(ActionEvent event) {
		MyMethods.newPage(screen, "ViewAll.fxml");

    }

    @FXML
    void treat(ActionEvent event) {
		MyMethods.newPage(screen, "visitAnimalByEmployee.fxml");

    }

    @FXML
    void workingHours(ActionEvent event) {
		MyMethods.newPage(screen, "workingHours.fxml");

    }
    @FXML
	void initialize() {
		label.setText("Welcome, "+MyMethods.getEmployee().getFirstName());
		if (MyMethods.getEmployee().getPhoto() != null) {//get the employee photo if he has if not he uses a default one
			Image image = new Image(MyMethods.getEmployee().getPhoto());
			imageView.setImage(image);
		} else {
			Image image = new Image(getClass().getResource("View/photoszoo/user.jpg").toString());
			imageView.setImage(image);
		}
		}
    


 
   

}
