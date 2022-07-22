package View;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import Model.Zoo;
import Model.ZooEmployee;
import Utils.MyFileLogWriter;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MyMethods {
	private static MenuBar mb;
	private static double sound=0.5;
	private static double music=0.5;
	private static ZooEmployee employee = null;//this variable is used to determine which employee is logged in
	public static double getMusic() {
		return music;
	}

	public static void setMusic(double music) {
		MyMethods.music = music;
	}


	// static Methods:
	

	public static ZooEmployee getEmployee() {
		return employee;
	}

	public static void setEmployee(ZooEmployee employee) {
		MyMethods.employee = employee;
	}

	public static boolean isLegal(String password) {//checks if the password is legal
		if (password.length() < 8)
			return false;
		int CapitalCounter = 0;
		int numberCounter = 0;
		for (Character c : password.toCharArray()) {
			if (c >= 48 && c <= 57)
				numberCounter++;
			if (c >= 65 && c <= 90)
				CapitalCounter++;
			if (numberCounter > 0 && CapitalCounter > 0)
				return true;
		}
		return false;
	}
	public static void makeMenuBar() {//makes a new menu bar
		Menu menu = new Menu("File");
		MenuItem menuItem1 = new MenuItem("Save");
		MenuItem menuItem2 = new MenuItem("Log Out");
		MenuItem menuItem3 = new MenuItem("Exit");
		MenuItem menuItem4 = new MenuItem("Settings");
		menuItem1.setOnAction(e->{
			MyFileLogWriter.saveLogFile();
			Zoo.getInstance().serialize();
			MyMethods.infoMessage("Success!","You have successfully saved");
		});
		menuItem2.setOnAction(e->{
			employee=null;
			MyMethods.newPage((Pane)mb.getScene().getRoot(), "logIn.fxml");
		});
		menuItem3.setOnAction(e->{
			closeProgram();
		});
		menuItem4.setOnAction(e->{
			openSettings();
		});
		menu.getItems().add(menuItem1);
		menu.getItems().add(menuItem4);
		menu.getItems().add(menuItem2);
		menu.getItems().add(menuItem3);

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().add(menu);
		mb=menuBar;
	}

	public static void infoMessage(String title, String message) {//creates a popup message and plays according sound
		try {
			Alert a;
			if (title.equals("failed!") || title.equals("Error!")) {
				Media media = new Media((new MyMethods()).getClass().getResource("View/photoszoo/error.mp3").toExternalForm());
				MediaPlayer mediaplayer = new MediaPlayer(media);
				mediaplayer.setVolume(sound);
				mediaplayer.play();
				a = new Alert(Alert.AlertType.ERROR);
			} else {
				Media media = new Media((new MyMethods()).getClass().getResource("View/photoszoo/success.mp3").toExternalForm());
				MediaPlayer mediaplayer=new MediaPlayer(media);
				mediaplayer.setVolume(sound);
				mediaplayer.play();
				a = new Alert(Alert.AlertType.INFORMATION);
			}
			a.setTitle(title);
			a.setContentText(message);
			a.setHeaderText(null);
			a.showAndWait();
		} catch (Error e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void solve(Label label) {
		for (int i = 1; i < 10; i++) {// this code does '\n' in the appropriate places
			String s = label.getText();// this code isn't perfect but solves the problem
			if (s.length() > 40 * i) {
				char[] c = s.toCharArray();
				for (int j = i * 40; j < s.length(); j++) {
					if (c[j] == ',') {

						String s1 = s.substring(0, j + 1);
						String s2 = s.substring(j + 1, s.length());
						String s3 = "";
						s3 += s1;
						s3 += "\n";
						s3 += s2;
						label.setText(s3);
						break;
					}

				}
			}

			else
				return;
		}
	}

	public static void newPage(Pane screen, String fxml) {//open a new page and puts menubar in the according pages

		try {
			Media media = new Media((new MyMethods()).getClass().getResource("View/photoszoo/button.mp3").toExternalForm());
			MediaPlayer mediaplayer = new MediaPlayer(media);
			mediaplayer.setVolume(sound);
			mediaplayer.play();
			if(fxml.equals("logIn.fxml")||fxml.equals("signUp.fxml")){
			FXMLLoader loader = new FXMLLoader((new MyMethods()).getClass().getResource(fxml));
			Pane pane = loader.load();
			pane.setMaxSize(screen.getWidth(), screen.getHeight());
			pane.setMinSize(screen.getWidth(), screen.getHeight());
			screen.getChildren().removeAll(screen.getChildren());
			screen.getChildren().add(pane);
		}
			else {
				FXMLLoader loader = new FXMLLoader((new MyMethods()).getClass().getResource(fxml));
				Pane pane = loader.load();
				pane.setMaxSize(screen.getWidth(), screen.getHeight());
				pane.setMinSize(screen.getWidth(), screen.getHeight());
				screen.getChildren().removeAll(screen.getChildren());
				BorderPane borderPane = new BorderPane();
				borderPane.setTop(mb);
				borderPane.setCenter(pane);
				screen.getChildren().add(borderPane);
			}
		} catch (Exception e) {
			infoMessage("Error!", "couldn't open this page");
			e.printStackTrace();
		}
	}

	public static void closeProgram() {//close program dialog (saving dialog)
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Do you want to save the changes?");
		ButtonType buttonSave = new ButtonType("Save");
		ButtonType buttonDontSave = new ButtonType("don't Save");
		ButtonType buttonCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonSave, buttonDontSave, buttonCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonSave) {
			Zoo.getInstance().serialize();
			Platform.exit();
		} else if (result.get() == buttonDontSave) {
			Platform.exit();
		} else {
		}

	}

	public static double getSound() {
		return sound;
	}

	public static void setSound(double sound) {
		MyMethods.sound = sound;
	}


	public static void openSettings() {//opens the settings
		FXMLLoader loader = new FXMLLoader((new MyMethods()).getClass().getResource("Setting.fxml"));
		Parent root;
		try {
		root = loader.load();
		Scene scene = new Scene(root, 366, 136);
		Stage stage = new Stage();
		stage.initStyle(StageStyle.DECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("settings");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
}
