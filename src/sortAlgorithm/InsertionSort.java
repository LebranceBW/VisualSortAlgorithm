package sortAlgorithm;

import entity.MonitoredList;

public class InsertionSort implements SortAlgorithm {

	@Override
	public void Sort(MonitoredList lst){
		int size = lst.size();
		for(int i=0; i<size; i++)
			for(int j=i; j>0; j--)
				if(lst.get(j) < lst.get(j-1))
					lst.Swap(j, j-1);
	}
	
	
	@Override
	public String toString()
	{
		return "²åÈëÅÅÐò";
	}
}
