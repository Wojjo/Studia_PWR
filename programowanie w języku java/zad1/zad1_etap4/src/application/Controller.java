package application;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;

import zad1.Instancja;
import zad1.Wynik;

public class Controller implements Initializable
{
	@FXML
	 CheckBox checkBoxBF;
	@FXML
	 CheckBox checkBoxGr;
	String filename;
	
	public void exitButtonClicked()
	{
		System.exit(0);
	}
	

	
	
	public void readDataButtonClicked()
	{
		if(checkBoxBF.isSelected() && checkBoxGr.isSelected())
		{
			new Alert(Alert.AlertType.INFORMATION, "Wybrano BF i GR").showAndWait();
		}
		else
		if(checkBoxBF.isSelected())
		{
			Instancja problem = new Instancja();
			try {
				problem.readfile(filename);
				problem.solveKnapsackProblem("Brute Force");
				
			} catch (FileNotFoundException e) {
				new Alert(Alert.AlertType.ERROR, "File not found.").showAndWait();
				
			}
		}
		else
		if(checkBoxGr.isSelected())
		{
			new Alert(Alert.AlertType.INFORMATION, "Wybrano GR").showAndWait();
		}
		else
		{
		new Alert(Alert.AlertType.INFORMATION, "Wybierz algorytm!").showAndWait();
		}
	}
	
	
	public void enterDataButtonClicked()
	{
		if(checkBoxBF.isSelected() && checkBoxGr.isSelected())
		{
			new Alert(Alert.AlertType.INFORMATION, "Wybrano BF i GR").showAndWait();
		}
		else
		if(checkBoxBF.isSelected())
		{
			new Alert(Alert.AlertType.INFORMATION, "Wybrano BF").showAndWait();
		}
		else
		if(checkBoxGr.isSelected())
		{
			new Alert(Alert.AlertType.INFORMATION, "Wybrano GR").showAndWait();
		}
		else
		{
		new Alert(Alert.AlertType.INFORMATION, "Wybierz algorytm!").showAndWait();
		}
	}



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
}
