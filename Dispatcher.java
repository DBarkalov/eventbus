import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

public class Dispatcher {

	void dispatch(Object event, Collection<Subscriber> subcsribers) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for(Subscriber s: subcsribers){
			s.dispatchEvent(event);
		}	
	}

}
