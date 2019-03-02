package zad1;

import java.util.ArrayList;

public class Greedy implements Interfejs
{
	ArrayList<Przedmiot> orderedList = new ArrayList<Przedmiot>();
	Przedmiot curItem;
	private int maxWeight;
	private int curWeight;
	private int numItems;
	private float curRatio;
	private float orderedCurRatio;
	private float curValue;
	private boolean solution[];
	private boolean added;
	private String algorithmName = "Greedy";
	
	
	
	public Greedy(int maxWeight, boolean[] solution, int numItems)
	{
		this.maxWeight = maxWeight;
		this.solution = solution;
		this.numItems = numItems;
	}
	
	public void startAlgorithm()
	{
		solution = new boolean[numItems];
		algorithm(numItems);
		Wynik.printResult(algorithmName, curValue, curWeight, numItems, solution);
		
	}
	
	public void algorithm(int numSize)
	{
	curWeight = 0;	
	orderedList.add(Instancja.itemList.get(0));
	for(int i = 1; i < numSize; i++)
	{
		added = false;
		curItem = Instancja.itemList.get(i);
		curRatio = curItem.value / curItem.weight;
		
		for(int j = 0; j < orderedList.size(); j++)
		{
			Przedmiot orderedItem = orderedList.get(j);
			orderedCurRatio = orderedItem.value / orderedItem.weight;
			
			if(curRatio < orderedCurRatio)
			{
				orderedList.add(j, curItem);
				added = true; 
				break;
			}
		}
		
		if(!added)
		{
			orderedList.add(curItem);
		}
		
	}
	
	while(curWeight < maxWeight && !orderedList.isEmpty())
	{
		int highestValueIndex = orderedList.size() - 1;
		curItem = orderedList.remove(highestValueIndex);
		
		if(curItem.weight + curWeight <= maxWeight)
		{
			solution[curItem.index - 1] = true;
            curValue += curItem.value;
            curWeight += curItem.weight;
		}
	}
	}
	

}
