package KnapsackProblem;

public class Item {
	private int index;
	private int weight;
	private int value;

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

	public double getItemValue() {
		return value;
	}

	public void setItemValue(int value) {
		this.value = value;
	}
	public String toString()
	{
		return "Index: " + index + ", waga: " + weight + ",wartosc: " + value;
	}
	
}
