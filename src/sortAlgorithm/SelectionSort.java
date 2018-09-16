package sortAlgorithm;

import application.MonitoredList;

public class SelectionSort implements SortAlgorithm {

	@Override
	public void Sort(MonitoredList lst) throws Exception {
		int size = lst.size();
		for(int i=0;i<size;i++)
		{
			int temp = i;
			for(int j=i;j<size;j++)
				if(lst.get(j)<lst.get(temp))
					temp = j;
			lst.Swap(temp, i);
			Thread.sleep(100);
		}
	}

}