package sortAlgorithm;

import entity.MonitoredList;

public class ShellSort extends SortAlgorithm {

	@Override
	public void Sort(MonitoredList lst) {
		final int shellDistance = 3;
		int N = lst.size();
		int h = shellDistance * (N / shellDistance);
		while(h >= 1)
		{
			for(int i = h; i < N; i++)
				for(int j = i; j >= h; j -= h)
					if(lst.get(j) < lst.get(j-h))
						lst.Swap(j, j-h);
			
			h /= shellDistance;
		}
	}

	@Override
	public String toString()
	{
		return "Ï£¶ûÅÅÐò";
	}
}
