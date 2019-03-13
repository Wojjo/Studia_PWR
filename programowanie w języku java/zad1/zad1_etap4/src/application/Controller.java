package application;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import zad1.Brute_force;
import zad1.Greedy;
import zad1.Instancja;

public class Controller  {
	@FXML CheckBox checkBoxBF;
	@FXML CheckBox checkBoxGr;
	@FXML TextField readTextField;
	@FXML Button enterDataButton;
	@FXML Button readDataButton;
	@FXML Button exitButton;
	@FXML MenuItem about_program;
	@FXML MenuItem about_BF;
	@FXML MenuItem about_GR;
	@FXML MenuItem language;
	

	String filename;
	String algorithm;
	static String choose="pl_PL";

	public void exitButtonClicked() {
		System.exit(0);
	}

	public void readDataButtonClicked(ActionEvent event) throws IOException {
		if (checkBoxBF.isSelected() && checkBoxGr.isSelected()) {
			showReadDataView(event);
			ReadDataController.setAlg(1, 1);

		} else if (checkBoxBF.isSelected()) {

			showReadDataView(event);
			ReadDataController.setAlg(1, 0);

		} else if (checkBoxGr.isSelected()) {
			showReadDataView(event);
			ReadDataController.setAlg(0, 1);
		} else {
			ResourceBundle b = Main.getBundle();
			new Alert(Alert.AlertType.INFORMATION,b.getString("alert1")).showAndWait();
			
		}
	}

	public void enterDataButtonClicked(ActionEvent event) throws IOException {
		Instancja.setItemList();
		if (checkBoxBF.isSelected() && checkBoxGr.isSelected()) 
		{
			showEnterDataView(event);
			EnterDataController.setAlg(1, 1);
		} else if (checkBoxBF.isSelected()) {
			
			showEnterDataView(event);
			EnterDataController.setAlg(1, 0);
		} else if (checkBoxGr.isSelected()) {
			showEnterDataView(event);
			EnterDataController.setAlg(0, 1);
		}else {
			ResourceBundle b = Main.getBundle();
			new Alert(Alert.AlertType.INFORMATION,b.getString("alert1")).showAndWait();
		}
	}
	public void showEnterDataView(ActionEvent event) throws IOException
	{
		Locale.setDefault(new Locale(choose));
		ResourceBundle bundle = ResourceBundle.getBundle("lang");
		AnchorPane enterData = (AnchorPane) FXMLLoader.load(getClass().getResource("EnterData.fxml"), bundle);
		Scene scene = new Scene(enterData, 700, 400);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(scene);
		window.show();
	}
	
	public void showReadDataView(ActionEvent event) throws IOException
	{
		Locale.setDefault(new Locale(choose));
		ResourceBundle bundle = ResourceBundle.getBundle("lang");
		AnchorPane enterData = (AnchorPane) FXMLLoader.load(getClass().getResource("ReadData.fxml"), bundle);
		Scene scene = new Scene(enterData, 600, 400);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(scene);
		window.show();

	}
	
	public void englishLanguage(ActionEvent event) throws IOException
	{
		
		Stage s = Main.getPrimaryStage();
		s.close();
		choose ="en_US";
		Main.setLang(choose);
		Platform.runLater( () -> new Main().start( new Stage() ) );
	
	}
	
	public void polishLanguage()
	{
		Stage s = Main.getPrimaryStage();
		s.close();
		choose ="pl_PL";
		Main.setLang(choose);
		Platform.runLater( () -> new Main().start( new Stage() ) );
		
	}
	
	
	public void aboutProgram(ActionEvent event) {
		ResourceBundle b = Main.getBundle();
		String version = " 1.2";
		new Alert(Alert.AlertType.INFORMATION,b.getString("program.info") + version).showAndWait();
	}

	public void aboutBF() {
		ResourceBundle b = Main.getBundle();
		new Alert(Alert.AlertType.INFORMATION,b.getString("program.info2")).showAndWait();
	}

	public void aboutGR() {
		ResourceBundle b = Main.getBundle();
		new Alert(Alert.AlertType.INFORMATION,b.getString("program.info3")).showAndWait();
	}


}
