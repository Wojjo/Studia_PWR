package application;

import java.net.URL;
import java.text.ChoiceFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import zad1.Instancja;
import zad1.Przedmiot;

public class EnterDataController implements Initializable {
	@FXML Button addItemButton;
	@FXML Button deleteItemButton;
	@FXML Button backButton;
	@FXML Button doButton;
	@FXML Button setWeightButton;
	@FXML Label itemLabel;

	static String algorithm = "brak";
	static String algorithm2 = "brak";
	String maxWeightField;
	int maxWeight;
	float value;
	int weight;
	int index = 0;
	private ResourceBundle bundle;

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

	public void setMaxWeightButtonClicked() {

		if (!maxWeightTextField.getText().isEmpty()) {
			maxWeight = Integer.parseInt(maxWeightTextField.getText());
			if (maxWeight > 0) {
				Instancja.setMaxWeight(maxWeight);
			} else {
				new Alert(Alert.AlertType.INFORMATION, bundle.getString("alert2")).showAndWait();
			}
		} else {
			new Alert(Alert.AlertType.INFORMATION, bundle.getString("alert2")).showAndWait();
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
				showLabel(); 
			} else {
				new Alert(Alert.AlertType.INFORMATION, bundle.getString("alert3")).showAndWait();
			}
		} else {
			new Alert(Alert.AlertType.INFORMATION, bundle.getString("alert3")).showAndWait();
		}

	}

	public void DoButtonClicked() {
		if (index > 0) {
			Instancja problem = new Instancja();

			if (algorithm.equals("Brute Force")) {
				problem.solveKnapsackProblem(algorithm, bundle);

			}

			if (algorithm2.equals("Greedy")) {
				problem.solveKnapsackProblem(algorithm2,bundle);

			}
		}

		else {
			new Alert(Alert.AlertType.INFORMATION, bundle.getString("alert4")).showAndWait();
		}

	}
	
	public void backButtonClicked()
	{
		Stage s = Main.getPrimaryStage();
		s.close();
		Platform.runLater(() -> new Main().start(new Stage()));
	}
	
	 private void showLabel() {
		 	
	        MessageFormat messForm = new MessageFormat("");
	        messForm.setLocale(Locale.getDefault());

	        double[] fileLimits = {0, 1, 2, 3, 4, 5};
	        String[] fileStrings = 
	        {
	                bundle.getString("no_items"),
	                bundle.getString("one_item"),
	                bundle.getString("2-4_items"),
	                bundle.getString("2-4_items"),
	                bundle.getString("2-4_items"),
	                bundle.getString("more_than_5")
	        };

	          ChoiceFormat choiceform = new ChoiceFormat(fileLimits, fileStrings);

	          String pattern = bundle.getString("pattern");
	          messForm.applyPattern(pattern);

	        Format[] form = {choiceform, null, NumberFormat.getInstance()};
	        messForm.setFormats(form);

	        Object[] messageArguments = {null, "list", null};

	        messageArguments[0] = Instancja.itemList.size();
	        messageArguments[2] = Instancja.itemList.size();

	        itemLabel.setText(messForm.format(messageArguments));

	    }
	
	

	 

	@Override
	public void initialize(URL arg0, ResourceBundle b)
	{
		bundle = ResourceBundle.getBundle("lang");
		indexColumn.setCellValueFactory(new PropertyValueFactory<Przedmiot, Integer>("index"));
		valueColumn.setCellValueFactory(new PropertyValueFactory<Przedmiot, Float>("value"));
		weightColumn.setCellValueFactory(new PropertyValueFactory<Przedmiot, Integer>("weight"));
		showLabel();

	}

}
