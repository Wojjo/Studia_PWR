package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import zad1.Instancja;

public class EnterDataController
{
	static String algorithm = "brak";
	static String algorithm2 = "brak";
	int maxWeight;
	int value;
	int weight;

	@FXML
	TextField maxWeightTextField;
	@FXML
	TextField valueTextField;
	@FXML
	TextField weightTextField;
	
	public static void setAlg(int alg1, int alg2) {
		if (alg1 == 1) {
			algorithm = "Brute Force";

		}

		if (alg2 == 1) {
			algorithm2 = "Greedy";

		}

	}
	
	public void setMaxWeight()
	{
		maxWeight = Integer.parseInt(maxWeightTextField.getText());
		
	}
	
	public void addItem()
	{
		value = Integer.parseInt(valueTextField.getText());
		weight = Integer.parseInt(weightTextField.getText());
		
		Instancja.enterData(value, weight);
	}

}
