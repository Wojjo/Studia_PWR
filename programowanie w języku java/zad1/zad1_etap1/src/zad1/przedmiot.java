package zad1;

public class Przedmiot 
{
	public float value;
	public int weight;
	public int index;
	
	public Przedmiot(int index, float value, int weight)
	{
		this.index=index;
		this.value=value;
		this.weight=weight;
	}
	public int getIndex()
	{
		return index;
	}
	
	public float getValue()
	{
		return value;
	}
	
	public int getWeight()
	{
		return weight;
	}
}
