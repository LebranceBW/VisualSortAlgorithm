package sortAlgorithm;

import entity.AlgorithmType;

public class SortAlgorithmFactory {
	
	public static SortAlgorithm assemble(AlgorithmType at)
	{
		switch (at) {
		case BubbleSort:
			return new BubbleSort();
		case QuickSort:
			return new QuickSort();
		case SelectionSort:
			return new SelectionSort();
		default:
			return null;
		}
	}
}
