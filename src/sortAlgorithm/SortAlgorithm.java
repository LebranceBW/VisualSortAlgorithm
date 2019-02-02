package sortAlgorithm;

import entity.MonitoredList;

public interface SortAlgorithm {
	public void Sort(MonitoredList lst);
	
	@Override
	public abstract String toString();
}
