package sortAlgorithm;

import java.util.Deque;
import java.util.List;

import entity.MonitoredList;
import swapUilitity.SwapOperation;

public abstract class SortAlgorithm {
	public abstract void Sort(MonitoredList lst);
	
	@Override
	public abstract String toString();

	public Deque<SwapOperation> recordedSort(List<Integer> list)
	{
		MonitoredList monitoredList = new MonitoredList(list);
		Sort(monitoredList);
		return monitoredList.getOperationsQueue();
	}
}
