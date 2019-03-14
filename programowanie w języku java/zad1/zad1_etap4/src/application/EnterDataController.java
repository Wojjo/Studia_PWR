package application;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import zad1.Instancja;
import zad1.Przedmiot;

public class EnterDataController implements Initializable {
	@FXML
	Button addItemButton;
	@FXML
	Button deleteItemButton;
	@FXML
	Button backButton;
	@FXML
	Button doButton;
	@FXML
	Button setWeightButton;

	static String algorithm = "brak";
	static String algorithm2 = "brak";
	String maxWeightField;
	int maxWeight;
	float value;
	int weight;
	int index = 0;

	@FXML
	private TextField maxWeightTextField;
	@FXML
	private TextField valueTextField;
	@FXML
	private TextField weightTextField;
	@FXML
	private TableView<Przedmiot> tableView;
	@FXML
	private TableColumn<Przedmiot, Integer> indexColumn;
	@FXML
	private TableColumn<Przedmiot, Float> valueColumn;
	@FXML
	private TableColumn<Przedmiot, Integer> weightColumn;

	public static void setAlg(int alg1, int alg2) {
		if (alg1 == 1) {
			algorithm = "Brute Force";

		}

		if (alg2 == 1) {
			algorithm2 = "Greedy";

		}

	}

	public void setMaxWeightButtonClicked() {

		if (!maxWeightTextField.getText().isEmpty()) {
			maxWeight = Integer.parseInt(maxWeightTextField.getText());
			if (maxWeight > 0) {
				Instancja.setMaxWeight(maxWeight);
			} else {
				ResourceBundle b = Main.getBundle();
				new Alert(Alert.AlertType.INFORMATION, b.getString("alert2")).showAndWait();
			}
		} else {
			ResourceBundle b = Main.getBundle();
			new Alert(Alert.AlertType.INFORMATION, b.getString("alert2")).showAndWait();
		}

	}

	public void addItemButtonClicked() {

		if (!valueTextField.getText().isEmpty() && !weightTextField.getText().isEmpty()) {
			value = Float.parseFloat(valueTextField.getText());
			weight = Integer.parseInt(weightTextField.getText());

			if (value > 0 && weight > 0) {
				index += 1;
				Przedmiot addItem = new Przedmiot(index, value, weight);
				tableView.getItems().add(addItem);
				Instancja.enterData(value, weight);
			} else {
				ResourceBundle b = Main.getBundle();
				new Alert(Alert.AlertType.INFORMATION, b.getString("alert3")).showAndWait();
			}
		} else {
			ResourceBundle b = Main.getBundle();
			new Alert(Alert.AlertType.INFORMATION, b.getString("alert3")).showAndWait();
		}

	}

	public void DoButtonClicked() {
		if (index > 0) {
			Instancja problem = new Instancja();

			if (algorithm.equals("Brute Force")) {
				ResourceBundle b = Main.getBundle();
				problem.solveKnapsackProblem(algorithm,b);

			}

			if (algorithm2.equals("Greedy")) {
				ResourceBundle b = Main.getBundle();
				problem.solveKnapsackProblem(algorithm2,b);

			}
		}

		else {
			ResourceBundle b = Main.getBundle();
			new Alert(Alert.AlertType.INFORMATION, b.getString("alert4")).showAndWait();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle bundle) {

		indexColumn.setCellValueFactory(new PropertyValueFactory<Przedmiot, Integer>("index"));
		valueColumn.setCellValueFactory(new PropertyValueFactory<Przedmiot, Float>("value"));
		weightColumn.setCellValueFactory(new PropertyValueFactory<Przedmiot, Integer>("weight"));

	}

}
