package event;

import java.util.EventObject;

public class ListValueChangedEvent extends EventObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int index1, index2;
	public ListValueChangedEvent(Object source) {
		super(source);
	}
	public int getIndex1() {
		return index1;
	}
	public int getIndex2()
	{
		return index2;
	}
	public void setIndex(int index1, int index2) {
		this.index1 = index1;
		this.index2 = index2;
	}
	public void onListValueChanged(ListValueChangedEvent event) {
		// TODO Auto-generated method stub
		
	}

}
