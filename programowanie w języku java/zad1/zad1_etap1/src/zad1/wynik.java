/**
 * Ta klasa tworzy oraz pozwala wyœwietlæ wyniki rozwi¹zañ problemu plecakowego
 * @autor Przemys³aw Wojcinowicz
 */

package zad1;

import java.util.ResourceBundle;

import javafx.scene.control.Alert;

public class Wynik {
	
	/**
	 * Metoda printResult przyjmuje wartoœci algorithmName, bestValue, bestWeight, numItems, solution, bundle. 
	 * @param algorithmName przechowuje informacje na temat jaki algorytm zosta³ u¿yty do rozwi¹zania problemu
	 * @param bestValue przechowuje jak¹ najwiêksz¹ wartoœæ plecaka znaleziono
	 * @param bestWeight przechowuje jak¹ wagê ma plecak przy najlpeszym rozwi¹zaniu
	 * @param numItems przechowuje iloœæ wszystkich przedmiotów 
	 * @param solution przechowuje które przedmioty zosta³y spakowane do plecaka
	 * @param bundle przechowuje informacjê jaki jêzyk zosta³ wybrany 
	 */
	
	public static void printResult(String algorithmName, float bestValue, int bestWeight, int numItems,
			boolean[] solution, ResourceBundle bundle) 
	{
		String ind = bundle.getString("score4");

		for (int i = 0; i < numItems; i++) {
			if (solution[i]) {
				ind += Instancja.itemList.get(i).index + " ";
			}
		}
		
		String wynik = bundle.getString("score") + algorithmName + bundle.getString("score2") + bestValue + bundle.getString("score3")
				+ bestWeight + ind;
		new Alert(Alert.AlertType.INFORMATION, wynik).showAndWait();

		
	}
}
