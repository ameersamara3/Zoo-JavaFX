package View;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Setting {
	private static logIn l=null;//this variable is used to update the music right away as soon as the user hits "ok"
    public static logIn getL() {
		return l;
	}
	public static void setL(logIn l) {
		Setting.l = l;
	}
    @FXML
    private Pane screen;

    @FXML
    private Slider sound;

    @FXML
    private Slider music;

    @FXML
    void ok(ActionEvent event) {
    	MyMethods.setMusic(music.getValue()*0.01);
    	MyMethods.setSound(sound.getValue()*0.01);
    	if(l!=null)
    		l.initialize();//updates the sound
    	((Stage)screen.getScene().getWindow()).close();
    	
    }

}
