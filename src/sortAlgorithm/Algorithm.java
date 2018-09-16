package sortAlgorithm;

import application.MonitoredList;

public class Algorithm 
{
	public static void bubbleSortAlgorithm(MonitoredList lst) throws Exception
	{
		// bubbleSort
		int size = lst.size();
		for(int i=0;i<size;i++)
			for(int j=0;j<size-i-1;j++)
				if(lst.get(j)>lst.get(j+1))
					{
						lst.Swap(j, j+1);
						Thread.sleep(50);
					}
		
	}
	
	public static void selectSortAlgorithm(MonitoredList lst) throws Exception
	{
		int size = lst.size();
		for(int i=0;i<size;i++)
		{
			int temp = i;
			for(int j=i;j<size;j++)
				if(lst.get(j)<lst.get(temp))
					temp = j;
			lst.Swap(temp, i);
			Thread.sleep(50);
		}
	}
	
	public static void quickSortAlgorithm(MonitoredList lst) throws Exception
	{
		assist(0, lst.size(), lst);
	}
	
	public static void assist(int index1, int index2, MonitoredList lst) throws Exception
	{
		if(index1 == index2)
			return;
		int anchor = lst.get(index1);
		int j = index1;
		for(int i=index1;i<index2;i++)
			if(lst.get(i) < anchor)
			{
				lst.Swap(++j, i);
				Thread.sleep(50);
			}
		lst.Swap(index1, j);
		Thread.sleep(50);
		assist(index1, j, lst);
		assist(j+1, index2, lst);
	}
}
