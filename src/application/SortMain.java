package application;

import java.util.Random;

import javafx.concurrent.Task;
import sortAlgorithm.Algorithm;

public class SortMain
{
	public Controller ct = null;
	
	public void launch()
	{
		MonitoredList lst = new MonitoredList();
		Random rd = new Random();
		final int size = 100;
		// 将1~size加入数组
		for(int i = 0;i < size;i++)
			lst.add(i+1);
		// 打乱顺序
		for(int i=size;i>0;i--)
			lst.Swap(rd.nextInt(i), i-1);
		
		ct.associateWithList(lst);
		ct.draw();
		lst.addListener(ct);
		
		    	
		Task<Void> sleeper = new Task<Void>()
			{

				@Override
				protected Void call() throws Exception {
					// 可视化的冒泡算法
//					bubbleSortAlgorithm(lst);
//					Algorithm.selectSortAlgorithm(lst);
					Algorithm.quickSortAlgorithm(lst);
					return null;
				}
		
			};
		new Thread(sleeper).start();
		
	}
	

}
