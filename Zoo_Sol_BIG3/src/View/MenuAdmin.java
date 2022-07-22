package View;

import java.util.Optional;

import Model.Zoo;
import Utils.MyFileLogWriter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.AnchorPane;

public class MenuAdmin {

    @FXML
    private AnchorPane screen;

    @FXML
    void Revenue(ActionEvent event) {
		MyMethods.newPage(screen, "Revenue.fxml");

    }

    @FXML
    void add(ActionEvent event) {
		MyMethods.newPage(screen, "addByAdmin.fxml");

    }

    @FXML
    void checkprice(ActionEvent event) {
		MyMethods.newPage(screen, "checkTicketPrice.fxml");

    }

    @FXML
    void covid(ActionEvent event) {
		MyMethods.newPage(screen, "Covid_19.fxml");

    }

    @FXML
    void discount(ActionEvent event) {
		MyMethods.newPage(screen, "setDiscount.fxml");

    }

    @FXML
    void getReal(ActionEvent event) {
		MyMethods.newPage(screen, "getReal.fxml");

    }

    @FXML
    void logout(ActionEvent event) {
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
    void percentage(ActionEvent event) {
		MyMethods.newPage(screen, "setZooPercentage.fxml");

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
		MyMethods.newPage(screen, "RemoveByAdmin.fxml");

    }

    @FXML
    void restData(ActionEvent event) {//confirmation dialog
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation Dialog");
    	alert.setHeaderText("Are you sure you want to reset all data?");
    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    		Zoo.getInstance().resetDataBase();
        	MyMethods.infoMessage("Success!", "You have successfully reseted Data Base");
    	} else{
    	} 
    	
    	
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
    void visit(ActionEvent event) {
		MyMethods.newPage(screen, "visitAnimalByPerson.fxml");

    }

    @FXML
    void workingHours(ActionEvent event) {
		MyMethods.newPage(screen, "workingHours.fxml");

    }

}
