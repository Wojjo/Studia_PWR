package application;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

	private static Stage primaryStage;
	static String language="PL";
	static ResourceBundle bundle;
	
	public static Stage getPrimaryStage()
	{
		return primaryStage;
	}
	
	public static void setLang(String choose)
	{
		language = choose;
	}
	public static ResourceBundle getBundle()
	{
		return bundle;
	}

	@Override
	public void start(Stage stage) {

		try {
			Locale.setDefault(new Locale(language));
			bundle = ResourceBundle.getBundle("lang");
			Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"), bundle);
			Scene scene = new Scene(root, 600, 400);
			stage.setScene(scene);
			stage.setTitle(bundle.getString("title.application"));
			primaryStage = stage;
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}