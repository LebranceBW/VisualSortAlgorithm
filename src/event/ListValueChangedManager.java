package event;

import java.util.ArrayList;
import java.util.List;

public class ListValueChangedManager
{
	private List<ListValueChangedListener> listeners = null;
    
    public ListValueChangedManager()
    {
        this.listeners = new ArrayList<ListValueChangedListener>();
    }    
    
    //���һ������
    public void addRunPerformEventListener(ListValueChangedListener e)
    {
        this.listeners.add(e);
    }
    
    //ɾ��һ������
    
    public void deleteRunPerformEventListener(ListValueChangedListener e)
    {
        this.listeners.remove(e);
    }
    
    //��������¼�
    public void fireRunPerformEventListener(ListValueChangedEvent event)
    {
        for(ListValueChangedListener listener : this.listeners)
        {
            listener.onListValueChanged(event);
        }
    }
}
