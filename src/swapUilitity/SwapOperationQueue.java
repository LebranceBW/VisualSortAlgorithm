package swapUilitity;

import java.util.ArrayDeque;
import java.util.Iterator;

public class SwapOperationQueue{
	private static ArrayDeque<SwapOperation> queue = new ArrayDeque<>(10000);
	public static void add(SwapOperation so)
	{
		queue.add(so);
	}
	
	public static SwapOperation poll()
	{
		return queue.poll();
	}
	
	public static void clear()
	{
		queue.clear();
	}
	
	public static Iterator<SwapOperation> getIterator() {
		return queue.iterator();
	}
}
