package application;

import java.util.ArrayList;

import event.ListValueChangedEvent;
import event.ListValueChangedListener;
import event.ListValueChangedManager;

public class MonitoredList extends ArrayList<Integer>
{
	private ListValueChangedManager manager = new ListValueChangedManager();
	private static final long serialVersionUID = 1L;
	
	public void addListener(ListValueChangedListener listener)
	{
		manager.addRunPerformEventListener(listener);
	}
	public void Swap(int index1, int index2)
	{
		Integer temp = this.get(index1);
		this.set(index1, this.get(index2));
		this.set(index2, temp);
		ListValueChangedEvent e = new ListValueChangedEvent(this);
		e.setIndex(index1, index2);
		manager.fireRunPerformEventListener(e);
	}


}
