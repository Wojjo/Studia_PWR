package zad2;

public class Items {

	public long value;
	public long weight;
	public int index;

	public Items(int index, long value, long weight) {
		this.index = index;
		this.value = value;
		this.weight = weight;
	}

	public int getIndex() {
		return index;
	}

	public long getValue() {
		return value;
	}

	public long getWeight() {
		return weight;
	}

}
