package Exceptions;

import java.io.File;
import java.net.MalformedURLException;

import View.MyMethods;
import javafx.scene.control.Alert;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ErrorMessage extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ErrorMessage(String message) {

		super(message);
		title = "Error!";
	}

	public void takeCare() {
		Media media;
		
			media = new Media((new MyMethods()).getClass().getResource("View/photoszoo/error.mp3").toExternalForm());
			MediaPlayer mediaplayer = new MediaPlayer(media);
			mediaplayer.setVolume(View.MyMethods.getSound());
			mediaplayer.play();
			MyMethods.infoMessage(title, getMessage());
		
	}

}
