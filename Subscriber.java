import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Subscriber {

	private final Method method;
	private final Object target;
	
	public Subscriber(Object target, Method method){
		this.target = target;
		this.method = method;
	}
	
	public void dispatchEvent(Object event) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		method.invoke(target, event);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subscriber other = (Subscriber) obj;
		if (method == null) {
			if (other.method != null)
				return false;
		} else if (!method.equals(other.method))
			return false;
		if (target == null) {
			if (other.target != null)
				return false;
		} else if (!target.equals(other.target))
			return false;
		return true;
	}	
	
	
	
}
