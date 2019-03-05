package zad1;

import javafx.scene.control.Alert;

public class Wynik {
	public static void printResult(String algorithmName, float BestValue, int BestWeight, int numItems,
			boolean[] solution) {
		String ind = "\nPrzedmioty: ";
		System.out.println("Using " + algorithmName + " solution found: " + BestValue + " Weight: " + BestWeight);

		for (int i = 0; i < numItems; i++) {
			if (solution[i]) {
				System.out.print(Instancja.itemList.get(i).index + " ");
				ind += Instancja.itemList.get(i).index + " ";
			}
		}

		String wynik = "Using " + algorithmName + " solution found\n " + "Value: " + BestValue + " Weight: "
				+ BestWeight + ind;
		new Alert(Alert.AlertType.INFORMATION, wynik).showAndWait();

		System.out.println();
	}
}
