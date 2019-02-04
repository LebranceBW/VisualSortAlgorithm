package sortAlgorithm;

import entity.MonitoredList;

public class HeapSort extends SortAlgorithm
{

	@Override
	public void Sort(MonitoredList list) {
		HeapFromList.constructHeap(list);
		for(int i=list.size()-1; i>0; i--)
		{
			list.Swap(0, i);
			/* 把值沉下来 */
			{
				int k = 0;
				while(true)
				{
					int leftson = HeapFromList.leftSon(k);
					int rightson = HeapFromList.rightSon(k);
					/* 检查是不是叶子节点 */
					if(leftson >= i && rightson >= i)
						break;
					int localMax = 0;
					if(rightson < i)
					{
						localMax = list.get(leftson) > list.get(rightson) ? leftson:rightson;
						localMax = list.get(localMax) > list.get(k) ? localMax:k;
					}
					else if(leftson < i)
						localMax = list.get(leftson) > list.get(k)? leftson:k;
					else
						localMax = k;
					if(localMax == k)
						break;
					else
					{
						list.Swap(localMax, k);
						k = localMax;
					}
				}
				
					
			}
		}
	}

	@Override
	public String toString() {
		return "堆排序";
	}

	public static class HeapFromList
	{
		
		private static void constructHeap(MonitoredList list)
		{
			int N = list.size();
			for(int i=1; i<N; i++)
			{
				int k = i;
				while(k > 0 && list.get(k) > list.get(Parent(k)))
				{
					list.Swap(k, Parent(k));
					k = Parent(k);
				}
			}
		}
		
		private static int Parent(int i)
		{
			if(i == 0) return 0;
			return (i+1) / 2 - 1;
		}
		
		public static int rightSon(int i)
		{
			return (i + 1) * 2;
		}
		
		public static int leftSon(int i)
		{
			return (i + 1) * 2 - 1;
		}
	}
}
