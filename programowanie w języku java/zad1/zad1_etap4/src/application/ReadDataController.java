package application;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import zad1.Instancja;

public class ReadDataController implements Initializable {

	String filename;
	static String algorithm = "brak";
	static String algorithm2 = "brak";

	@FXML TextField readTextField;
	@FXML Button OKButtonClicked;
	@FXML Button CancleButton;

	private ResourceBundle bundle;

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
				problem.readfile(filename);
				problem.solveKnapsackProblem(algorithm, bundle);

			} catch (FileNotFoundException e) {
				System.out.println("ERROR: File not found.");
			}

		}

		if (algorithm2.equals("Greedy")) {

			try {
				problem.readfile(filename);
				problem.solveKnapsackProblem(algorithm2, bundle);

			} catch (FileNotFoundException e) {
				System.out.println("ERROR: File not found.");
			}
		}

	}

	public void cancleButtonClicked() {
		Stage s = Main.getPrimaryStage();
		s.close();
		Platform.runLater(() -> new Main().start(new Stage()));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		bundle = ResourceBundle.getBundle("lang");

	}

}
