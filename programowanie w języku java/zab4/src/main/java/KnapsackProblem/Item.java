package KnapsackProblem;

import java.io.Serializable;

/**
 * Class designed to simulate items that can be put into knapsack in Knapsack Problem.
 * 
 * @author Kamil Kluba 226016
 * @see KnapsackProblem
 * @see Knapsack
 * @see GreedyAlgorithm
 * @see RandomAlgorithm
 * @see BruteForceAlgorithm
 */
public class Item implements Serializable {
	/*
	 * Name of the item.
	 */
	int index;
	/*
	 * Weight of the item.
	 */
	int weight;
	/*
	 * Value of the item.
	 */
	int value;
	
	/**
	 * Constructor contains three parameters of item: name, weight and value.
	 * 
	 * @param name Name of the item.
	 * @param weight Weight of the item.
	 * @param value Value of the item.
	 */
	public Item(int index, int weight, int value) {
		super();
		this.index = index;
		this.weight = weight;
		this.value = value;
	}

	/**
	 * Returns name of the item.
	 * @return 
	 * @return Name of the item.
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Sets name of an item.
	 * @param name Name of the item.
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Returns weight of the item.
	 * @return Weight of the item.
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * Sets weight of the item.
	 * @param weight Weight of the item.
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	/**
	 * Returns value of the item.
	 * @return Value of the item.
	 */
	public double getValue() {
		return value;
	}
	
	/**
	 * Sets value of the item.
	 * @param value Value of the item.
	 */
	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Nazwa przedmiotu: " + index + ", waga: " + weight + ",wartoa " + value;
	}
	
}
