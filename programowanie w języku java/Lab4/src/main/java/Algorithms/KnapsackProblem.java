package Algorithms;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KnapsackProblem {
	private static Scanner scanner;
	private ArrayList<Item> listOfItems;
	private int capacity;
	private static ArrayList<String> listOfAlgorithms;
	private String result;

	public KnapsackProblem(int capacity, ArrayList<Item> listOfItems) {
		this.capacity = capacity;
		this.listOfItems = listOfItems;
	}

	public void chooseAlgorithmToRun(String algorithm, int repetitions) {
		if (algorithm.equals("Algorytm zach�anny")) {
			GreedyAlgorithm ga = new GreedyAlgorithm(listOfItems, capacity);
			result = ga.description();
		} else {
			BruteForceAlgorithm bfa = new BruteForceAlgorithm(listOfItems, capacity);
			result = bfa.description();
		}
	}

	public static ArrayList<String> getListOfAlgorithms() {
		listOfAlgorithms = new ArrayList<String>();
		listOfAlgorithms.add("Algorytm Greedy");
		listOfAlgorithms.add("Algorytm Brute Force");
		return listOfAlgorithms;
	}

	public String getResult() {
		return result;
	}

	public static void main(String args[]) {

	}

}
