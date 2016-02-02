import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Subcsribers {

	// event type -> Subscriber
	private Map<Class<?>, Set<Subscriber>> mSubscribers = new HashMap<>();

	public void register(Object listener) {
		Class<?> clazz = listener.getClass();
		for (Method method : getAnnotatedMethods(clazz)) {
			Class<?>[] paramTypes = method.getParameterTypes();
			Class<?> eventType = paramTypes[0];
			Subscriber subcsriber = new Subscriber(listener, method);
			addSubscriber(eventType, subcsriber);
		}
	}

	private void addSubscriber(Class<?> eventType, Subscriber subcsriber) {
		Set<Subscriber> subscribers = mSubscribers.get(eventType);
		if (subscribers == null) {
			subscribers = new HashSet<>();
			mSubscribers.put(eventType, subscribers);
		}
		subscribers.add(subcsriber);
	}

	public void unregister(Object listener) {
		Class<?> clazz = listener.getClass();
		for (Method method : getAnnotatedMethods(clazz)) {
			Subscriber subcsriber = new Subscriber(listener, method);
			removeSubscriber(subcsriber);
		}
	}

	private void removeSubscriber(Subscriber subcsriber) {
		for (Set<Subscriber> set : mSubscribers.values()) {
			set.remove(subcsriber);
		}
	}

	public Collection<Subscriber> getSubscribers(Object event) {
		return mSubscribers.get(event.getClass());
	}

	private List<Method> getAnnotatedMethods(Class<?> clazz) {
		// todo see in superclasses
		List<Method> out = new ArrayList<>();
		for (Method method : clazz.getDeclaredMethods()) {
			if (method.isAnnotationPresent(Subscribe.class) && !method.isSynthetic()) {
				Class<?>[] paramTypes = method.getParameterTypes();
				if (paramTypes.length == 1) {
					out.add(method);
				}
			}
		}
		return out;
	}

}
