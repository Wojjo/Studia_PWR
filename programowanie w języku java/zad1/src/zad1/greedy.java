package zad1;

import java.util.ArrayList;

public class Greedy
{
	ArrayList<Przedmiot> orderedList = new ArrayList<Przedmiot>();
	Przedmiot curItem;
	private int maxWeight;
	private int curWeight;
	private int numItems;
	float curRatio;
	float orderedCurRatio;
	float curValue;
	boolean solution[];
	boolean added;
	
	
	
	public Greedy(int maxWeight, boolean[] solution, int numItems)
	{
		this.maxWeight = maxWeight;
		this.solution = solution;
		this.numItems = numItems;
	}
	
	public void startAlgorithm()
	{
		algorithmGreedy();
		printBruteForceKnapsack();
		
	}
	
	public void algorithmGreedy()
	{
	curWeight = 0;	
	orderedList.add(Instancja.itemList.get(0));
	for(int i = 1; i < Instancja.itemList.size(); i++)
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
	
	public void printBruteForceKnapsack() {
		
		 System.out.println("Greedy solution: " + 
         this.curValue + " Weight " + this.curWeight);
		
		
        for(int i = 0; i < numItems; i++) {
            if(solution[i]) {
                System.out.print(Instancja.itemList.get(i).index + " ");
            }
        }
        System.out.println();
    }
}
