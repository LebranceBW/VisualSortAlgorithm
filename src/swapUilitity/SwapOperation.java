package swapUilitity;

public class SwapOperation {
	public final int previousIndex;
	public final int previousNumber;
	public final int afterIndex;
	public final int afterNumber;
	
	public SwapOperation(int previousIndex, int previousNumber, int afterIndex, int afterNumber)
	{
		this.previousIndex = previousIndex;
		this.previousNumber = previousNumber;
		this.afterIndex = afterIndex;
		this.afterNumber = afterNumber;
	}
}
