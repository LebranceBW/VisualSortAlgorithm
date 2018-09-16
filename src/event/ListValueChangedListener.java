package event;

import java.util.EventListener;

public interface ListValueChangedListener extends EventListener {
	void onListValueChanged(ListValueChangedEvent e);
}
