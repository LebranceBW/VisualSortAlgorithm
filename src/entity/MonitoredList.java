package entity;

import java.util.ArrayList;

import swapUilitity.SwapOperation;
import swapUilitity.SwapOperationQueue;

public class MonitoredList extends ArrayList<Integer>
{
	private static final long serialVersionUID = 1L;
	
	public void Swap(int index1, int index2)
	{
		Integer temp = this.get(index1);
		this.set(index1, this.get(index2));
		this.set(index2, temp);
		
		SwapOperationQueue.add(new SwapOperation(index1, this.get(index1), index2, this.get(index2)));
	}


}
