package application;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application
{

	
	@Override
	public void start(Stage primaryStage) {
			
		try {
			
			ResourceBundle bundle = ResourceBundle.getBundle("lang");
			Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"),bundle);

			Scene scene = new Scene(root, 600, 400);
			primaryStage.setScene(scene);
			primaryStage.setTitle(bundle.getString("title.application"));

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
