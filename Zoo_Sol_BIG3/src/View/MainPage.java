package View;

import java.io.File;

import Model.Animal;
import Model.Zoo;
import Model.ZooEmployee;
import Utils.MyFileLogWriter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainPage extends Application {//we used jre 1.8_261
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		MyFileLogWriter.initializeMyFileWriter();//initialize the file writer (doesn't have much use)
		FXMLLoader loader = new FXMLLoader(getClass().getResource("logIn.fxml"));//opens the Main Page

		Parent root = loader.load();
		Scene scene = new Scene(root, 957, 730);
		primaryStage.setOnCloseRequest(e -> {
			e.consume();
			MyMethods.closeProgram();
		});
		primaryStage.setTitle("Zoo");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.initStyle(StageStyle.DECORATED);
		primaryStage.show();
		Zoo.getInstance().deserialize();//gets saved content
		Zoo.getInstance().staticDeserialize();
		MyMethods.makeMenuBar();//initialize the menu bar
		}

}