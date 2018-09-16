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
    
    //添加一个监听
    public void addRunPerformEventListener(ListValueChangedListener e)
    {
        this.listeners.add(e);
    }
    
    //删除一个监听
    
    public void deleteRunPerformEventListener(ListValueChangedListener e)
    {
        this.listeners.remove(e);
    }
    
    //激活监听事件
    public void fireRunPerformEventListener(ListValueChangedEvent event)
    {
        for(ListValueChangedListener listener : this.listeners)
        {
            listener.onListValueChanged(event);
        }
    }
}
