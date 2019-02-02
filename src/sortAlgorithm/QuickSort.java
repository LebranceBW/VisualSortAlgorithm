package sortAlgorithm;

import entity.MonitoredList;

public class QuickSort implements SortAlgorithm {

	@Override
	public void Sort(MonitoredList lst) {
		assist(0, lst.size(), lst);
	}
	public static void assist(int index1, int index2, MonitoredList lst)
	{
		if(index1 == index2)
			return;
		int anchor = lst.get(index1);
		int j = index1;
		for(int i=index1;i<index2;i++)
			if(lst.get(i) < anchor)
			{
				lst.Swap(++j, i);
			}
		lst.Swap(index1, j);
		assist(index1, j, lst);
		assist(j+1, index2, lst);
	}

	@Override
	public String toString()
	{
		return "¿ìËÙÅÅÐò";
	}
}
