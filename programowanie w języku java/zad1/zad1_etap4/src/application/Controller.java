package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import zad1.Brute_force;
import zad1.Greedy;
import zad1.Instancja;

public class Controller implements Initializable {
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

	public void exitButtonClicked() {
		System.exit(0);
	}

	public void readDataButtonClicked(ActionEvent event) throws IOException {
		if (checkBoxBF.isSelected() && checkBoxGr.isSelected()) {
			AnchorPane enterData = (AnchorPane) FXMLLoader.load(getClass().getResource("ReadData.fxml"));
			Scene scene = new Scene(enterData, 600, 400);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

			window.setScene(scene);
			window.show();

			ReadDataController.setAlg(1, 1);

		} else if (checkBoxBF.isSelected()) {

			AnchorPane enterData = (AnchorPane) FXMLLoader.load(getClass().getResource("ReadData.fxml"));
			Scene scene = new Scene(enterData, 600, 400);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

			window.setScene(scene);
			window.show();

			ReadDataController.setAlg(1, 0);

		} else if (checkBoxGr.isSelected()) {
			AnchorPane enterData = (AnchorPane) FXMLLoader.load(getClass().getResource("ReadData.fxml"));
			Scene scene = new Scene(enterData, 600, 400);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

			window.setScene(scene);
			window.show();

			ReadDataController.setAlg(0, 1);
		} else {
			new Alert(Alert.AlertType.INFORMATION, "Wybierz algorytm!").showAndWait();
		}
	}

	public void enterDataButtonClicked(ActionEvent event) throws IOException {
		Instancja.setItemList();
		if (checkBoxBF.isSelected() && checkBoxGr.isSelected()) {
			
			AnchorPane enterData = (AnchorPane) FXMLLoader.load(getClass().getResource("EnterData.fxml"));
			Scene scene = new Scene(enterData, 700, 400);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

			window.setScene(scene);
			window.show();
			EnterDataController.setAlg(1, 1);
		} else if (checkBoxBF.isSelected()) {
			
			AnchorPane enterData = (AnchorPane) FXMLLoader.load(getClass().getResource("EnterData.fxml"));
			Scene scene = new Scene(enterData, 700, 400);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

			window.setScene(scene);
			window.show();
			EnterDataController.setAlg(1, 0);
		} else if (checkBoxGr.isSelected()) {
			AnchorPane enterData = (AnchorPane) FXMLLoader.load(getClass().getResource("EnterData.fxml"));
			Scene scene = new Scene(enterData, 700, 400);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

			window.setScene(scene);
			window.show();
			EnterDataController.setAlg(0, 1);
		}else {
			new Alert(Alert.AlertType.INFORMATION, "Wybierz algorytm!").showAndWait();
		}
	}

	public void aboutProgram() {
		FXMLLoader loader = new FXMLLoader();
		ResourceBundle bundles = ResourceBundle.getBundle("Language_pl_PL");
		loader.setResources(bundles);
		String version = "\n  Wersja 1.1";
		new Alert(Alert.AlertType.INFORMATION,bundles.getString("program.info") + version).showAndWait();
	}

	public void aboutBF() {
		Brute_force.info();
	}

	public void aboutGR() {
		Greedy.info();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle bundles)
	{
		FXMLLoader loader = new FXMLLoader();
		bundles = ResourceBundle.getBundle("Language_pl_PL");
		loader.setResources(bundles);
		

	}

}
