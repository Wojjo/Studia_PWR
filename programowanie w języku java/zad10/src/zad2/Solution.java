package zad2;

public class Solution {

	public long weight;
	public String items;

	public Solution(String items, long weight) {
		this.items = items;
		this.weight = weight;
	}

	public String getIndex() {
		return items;
	}


	public long getWeight() {
		return weight;
	}

}
