/**
 * Klasa ta implementuje metody pozwalaj¹ce:
 * wczytaæ listê przedmiotów z pliku,
 * tworzyæ przedmioty,
 * ustawiæ maksymaln¹ wagê plecaka,
 * wybraæ, który algorytm ma rozwi¹zaæ problem plecakowy
 * @autor Przemys³aw Wojcinowicz
 */

package zad1;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Instancja {
	public static ArrayList<Przedmiot> itemList;
	private static int numItems = 0;
	private static int maxWeight;
	
/**
 * Metoda ta pozwala wczytaæ dane z pliku
 * @param filename przechowuje nazwê pliku, z którego chcemy wczytaæ dane
 * @throws FileNotFoundException wyœwietli b³¹d, jeœli metoda nie znajdzie danego pliku, lub dane w pliku nie bêd¹ pe³ne
 */
	public void readfile(String filename) throws FileNotFoundException {
		FileInputStream in = new FileInputStream(new File(filename));

		Scanner sc = new Scanner(in);
		itemList = new ArrayList<Przedmiot>();
		numItems = sc.nextInt();

		for (int i = 0; i < numItems; i++) {
			itemList.add(new Przedmiot(sc.nextInt(), sc.nextFloat(), sc.nextInt()));
		}
		maxWeight = sc.nextInt();
		sc.close();
	}
	/**
	 * Metoda pozwala stworzyæ przedmioty jeœli chcemy wprowadziæ je rêcznie z aplikacji
	 * @param value przechowuje wartoœæ przedmiotu
	 * @param weight przechowuje wagê przedmiotu
	 */

	public static void enterData(float value, int weight) {

		numItems += 1;
		itemList.add(new Przedmiot(numItems, value, weight));

	}
	
	/**
	 * Metoda pozwala ustawiæ maksymaln¹ wagê plecaka przy wprowadzaniu danych rêcznie
	 * @param max przechowuje wartoœæ maksymalnej wagi plecaka
	 */
	public static void setMaxWeight(int max)
	{
		maxWeight = max;
	}
	/**
	 * Metoda inicjalizuje listê przedmiotów
	 */
	public static void setItemList()
	{
		itemList = new ArrayList<Przedmiot>();
	}
	/**
	 * Metoda pozwala wybraæ jaki algorytm ma rozwi¹zaæ problem plecakowy
	 * @param option przechowuje, który algorytm wybrano
	 * @param bundle przechowuje, który jêzyk wybrano
	 */
	public void solveKnapsackProblem(String option, ResourceBundle bundle) {

		if (option.equals("Brute Force")) {
			Brute_force bf = new Brute_force(maxWeight, numItems);
			bf.startAlgorithm(bundle);

		} else if (option.equals("Greedy")) {
			Greedy gr = new Greedy(maxWeight, numItems);
			gr.startAlgorithm(bundle);
		}
	}

	

}
