package View;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import Exceptions.EmptyFieldException;
import Exceptions.ErrorMessage;
import Exceptions.UserNameNotFoundException;
import Exceptions.idNotFoundException;
import Exceptions.wrongPasswordException;
import Model.Zoo;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class logIn {
	@FXML
	private MediaView mv;
	@FXML
	private AnchorPane screen;

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;
	private MediaPlayer mediaplayer=null;
	private Media media;

	@FXML
	void signin(ActionEvent event) {//this method check the user is an admin or an employee 
		//the method checks for exceptions first

		try {
			if (username.getText().equals("") || password.getText().equals(""))
				throw new EmptyFieldException();
			if (Zoo.getInstance().signInAdmin(username.getText(), password.getText()))
				MyMethods.newPage(screen, "MenuAdmin.fxml");
			else {
				Zoo.getInstance().signIn(username.getText(), password.getText());
				MyMethods.newPage(screen, "MenuEmployee.fxml");
			}
			mediaplayer.pause();
		} catch (ErrorMessage e) {
			e.takeCare();
		}

	}

	@FXML
	void settings(MouseEvent event) throws IOException {
		MyMethods.openSettings();
		Setting.setL(this);

	}

	@FXML
	void exit(ActionEvent event) {
		MyMethods.closeProgram();
	}

	@FXML
	void signup(ActionEvent event) {
		MyMethods.newPage(screen, "signUp.fxml");
		mediaplayer.pause();

	}

	@FXML
	public void initialize() {
			if (mediaplayer == null) {//starts the background video
				media = new Media(getClass().getResource("View/photoszoo/video.mp4").toExternalForm());
				mediaplayer = new MediaPlayer(media);
				mv.setMediaPlayer(mediaplayer);
				mv.setFitWidth(1050);
				mv.setFitHeight(750);
				mediaplayer.setOnEndOfMedia(new Runnable() {
					@Override
					public void run() {
						mediaplayer.seek(Duration.ZERO);
						mediaplayer.play();
					}
				});
				mediaplayer.play();
			}
			mediaplayer.setVolume(0.2 * MyMethods.getMusic());
			mv.toBack();

	}
}