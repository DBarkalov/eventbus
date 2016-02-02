import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

public class EventBus {

	private final Subcsribers mSubscribers = new Subcsribers();
	private final Dispatcher mDispatcher = new Dispatcher();

	public void register(Object o) {
		mSubscribers.register(o);
	}

	public void unregister(Object o) {
		mSubscribers.unregister(o);
	}

	public void post(Object event) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Collection<Subscriber> subcsribers = mSubscribers.getSubscribers(event);
		if (subcsribers != null & !subcsribers.isEmpty()) {
			mDispatcher.dispatch(event, subcsribers);
		}
	}

}
