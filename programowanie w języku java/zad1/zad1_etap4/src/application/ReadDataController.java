package application;

import java.io.FileNotFoundException;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import zad1.Instancja;

public class ReadDataController implements Initializable {
	
	String filename;
	static String algorithm = "brak";
	static String algorithm2 = "brak";

	@FXML TextField readTextField;
	@FXML Button OKButtonClicked;
	@FXML Button CancleButton;
	
	public static void setAlg(int alg1, int alg2) {
		if (alg1 == 1) {
			algorithm = "Brute Force";

		}

		if (alg2 == 1) {
			algorithm2 = "Greedy";

		}

	}

	public void OKButtonClicked() {
		filename = readTextField.getText();
		Instancja problem = new Instancja();

		if (algorithm.equals("Brute Force")) {

			try {
				ResourceBundle b = Main.getBundle();
				problem.readfile(filename);
				problem.solveKnapsackProblem(algorithm,b);

			} catch (FileNotFoundException e) {
				System.out.println("ERROR: File not found.");
			}

		}

		if (algorithm2.equals("Greedy")) {

			try {
				ResourceBundle b = Main.getBundle();
				problem.readfile(filename);
				problem.solveKnapsackProblem(algorithm2,b);

			} catch (FileNotFoundException e) {
				System.out.println("ERROR: File not found.");
			}
		}

	}

	public void cancleButtonClicked() {
		System.exit(0);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
