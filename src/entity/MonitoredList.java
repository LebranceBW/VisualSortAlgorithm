package entity;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import swapUilitity.SwapOperation;

public class MonitoredList extends ArrayList<Integer>
{
	private static final long serialVersionUID = 1L;
	private Deque<SwapOperation> queue = new ArrayDeque<>(500);
	
	public MonitoredList(List<Integer> list)
	{
		super(list);
	}
	
	public void Swap(int index1, int index2)
	{
		Integer temp = this.get(index1);
		this.set(index1, this.get(index2));
		this.set(index2, temp);
		
		queue.add(new SwapOperation(index1, this.get(index1), index2, this.get(index2)));
	}

	public Deque<SwapOperation> getOperationsQueue()
	{
		return this.queue;
	}
}
