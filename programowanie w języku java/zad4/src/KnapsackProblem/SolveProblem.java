package KnapsackProblem;

import java.util.ArrayList;

public class SolveProblem {
	private ArrayList<Item> listOfItems;
	private static ArrayList<String> listOfAlgorithms;
	private int weight;
	private String result;

	public SolveProblem(int weight, ArrayList<Item> listOfItems) {
		this.weight = weight;
		this.listOfItems = listOfItems;
	}

	public void chooseAlgorithmToRun(String algorithm, int repetitions) {
		if (algorithm.equals("Algorytm zachlanny")) {
			Greedy gr = new Greedy(listOfItems, weight);
			result = gr.solution();
		} else {
			BruteForce bf = new BruteForce(listOfItems, weight);
			result = bf.solution();
		}
	}

}
