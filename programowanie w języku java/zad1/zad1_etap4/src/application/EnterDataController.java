package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import zad1.Instancja;
import zad1.Przedmiot;

public class EnterDataController implements Initializable
{
	@FXML Button addItemButton;
	@FXML Button deleteItemButton;
	@FXML Button backButton;
	@FXML Button doButton;
	@FXML Button setWeightButton;
	
	
	
	static String algorithm = "brak";
	static String algorithm2 = "brak";
	String maxWeightField;
	int maxWeight;
	float value;
	int weight;
	int index=0;

	@FXML private TextField maxWeightTextField;
	@FXML private TextField valueTextField;
	@FXML private TextField weightTextField;
	@FXML private TableView<Przedmiot> tableView;
	@FXML private TableColumn<Przedmiot, Integer> indexColumn;
	@FXML private TableColumn<Przedmiot, Float> valueColumn;
	@FXML private TableColumn<Przedmiot, Integer> weightColumn;
	
	
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
		index +=1;		
		value = Float.parseFloat(valueTextField.getText());
		weight = Integer.parseInt(weightTextField.getText());	
		Przedmiot addItem = new Przedmiot(index, value, weight);
		tableView.getItems().add(addItem);
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
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		indexColumn.setCellValueFactory(new PropertyValueFactory<Przedmiot, Integer>("index"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<Przedmiot, Float>("value"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<Przedmiot, Integer>("weight"));
           
	}
	

}
