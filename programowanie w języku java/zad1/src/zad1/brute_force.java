package zad1;

import java.util.ArrayList;

public class brute_force
{
	
	private int maxWeight;
	private boolean[] solution;
	private boolean[] current;
	private float curBestValue;
	private int curBestWeight;
	private int curWeight;
	private float curValue;
	
	public brute_force(int maxWeight, boolean[] solution, boolean[] current)
	{
		this.maxWeight = maxWeight;
		this.solution = solution;
		this.current = current;
	}
	
	
	void algorithmBF(int numSize)
	{ 
		if(numSize < 0)
		{
			curWeight = 0;
			curValue = 0;
			
			for(int i =0; i < numSize; i++)
			{
				if(current[i])
				{
					curWeight += instancja.getItemList().get(i).weight;
					curValue += instancja.getItemList().get(i).value;
				}
			}
			
			if(curWeight <= maxWeight && curValue > curBestValue)
			{
				curBestValue = curValue;
				curBestWeight = curWeight;
				for(int j = 0; j < solution.length; j++)
				{
					solution[j] = current[j];
				}
			}
		} else
		{
			current[numSize] = true;
			algorithmBF(numSize - 1);
			current[numSize] = false;
			algorithmBF(numSize - 1);
					
		}	
	}
	
	public void printBruteForceKnapsack()
	{
		System.out.println("Using Brute force the best feasible" +
                " solution found: " + this.curBestValue + 
                " " + this.curBestWeight);
	}
	
	
	
}
