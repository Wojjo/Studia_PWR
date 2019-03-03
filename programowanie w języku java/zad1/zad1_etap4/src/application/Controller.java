package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;

public class Controller implements Initializable
{
	@FXML
	 CheckBox checkBoxBF;
	@FXML
	 CheckBox checkBoxGr;
	
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
