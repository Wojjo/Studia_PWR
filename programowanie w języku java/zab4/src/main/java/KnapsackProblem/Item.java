package KnapsackProblem;

import java.io.Serializable;

public class Item implements Serializable {

	private static final long serialVersionUID = 1L;
	int index;
	int weight;
	int value;

	public Item(int index, int weight, int value) {
		super();
		this.index = index;
		this.weight = weight;
		this.value = value;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public double getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Nazwa przedmiotu: " + index + ", waga: " + weight + ",wartoa " + value;
	}
	
}
