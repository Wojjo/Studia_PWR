package zad1;

public class przedmiot 
{
	public float value;
	public int weight;
	public int index;
	
	public przedmiot(int index, float value, int weight)
	{
		this.index=index;
		this.value=value;
		this.weight=weight;
	}
	public String toString() {
        return "{KnapsackItem:" + this.index + " value:" 
            + this.value + " weight: " + this.weight + "}";
	}
}
