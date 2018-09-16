package sortAlgorithm;

import application.MonitoredList;

public class BubbleSort implements SortAlgorithm {

	@Override
	public void Sort(MonitoredList lst) throws Exception{
		int size = lst.size();
		for(int i=0;i<size;i++)
			for(int j=0;j<size-i-1;j++)
				if(lst.get(j)>lst.get(j+1))
					{
						lst.Swap(j, j+1);
						Thread.sleep(100);
					}
	}

}
