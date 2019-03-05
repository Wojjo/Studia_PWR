package application;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import zad1.Instancja;
import zad1.Przedmiot;

public class EnterDataController
{
	static String algorithm = "brak";
	static String algorithm2 = "brak";
	String maxWeightField;
	int maxWeight;
	float value;
	int weight;

	@FXML private TextField maxWeightTextField;
	@FXML private TextField valueTextField;
	@FXML private TextField weightTextField;
	@FXML private TableView<Przedmiot> tableView;
	@FXML private TableColumn<Przedmiot, String> indexColumn;
	@FXML private TableColumn<Przedmiot, String> valueColumn;
	@FXML private TableColumn<Przedmiot, String> weightColumn;
	
	
	public static void setAlg(int alg1, int alg2) {
		if (alg1 == 1) {
			algorithm = "Brute Force";

		}

		if (alg2 == 1) {
			algorithm2 = "Greedy";

		}

	}
	
	public void setMaxWeightButtonClicked()
	{
		maxWeight = Integer.parseInt(maxWeightTextField.getText()); 
			
		Instancja.setMaxWeight(maxWeight);
	}
	
	public void addItemButtonClicked()
	{
		
		
		//tableView.getItems().add(newItem);
		value = Float.parseFloat(valueTextField.getText());
		weight = Integer.parseInt(weightTextField.getText());	
		Instancja.enterData(value, weight);
	}
	
	
	public void DoButtonClicked() {

		Instancja problem = new Instancja();

		if (algorithm.equals("Brute Force")) {

			problem.solveKnapsackProblem(algorithm);

		}

		if (algorithm2.equals("Greedy")) {

			problem.solveKnapsackProblem(algorithm2);

		}

	}
	

}
