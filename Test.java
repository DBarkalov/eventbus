import java.lang.reflect.InvocationTargetException;

public class Test {

	public static void main(String[] args) {
		
		EventBus bus = new EventBus();
		StringTarget t1 = new StringTarget();
		StringTarget2 t2 = new StringTarget2();

		bus.register(t1);
		bus.register(t2);
		bus.register(new Object() {
			@Subscribe
			void onEvent(String str) {
				System.out.println("arg='" + str + "'  " + this.getClass().getName());
			}
		});
		
		try {
			bus.post(new String("string 1"));
			bus.post(new String("string 2"));
			bus.post(new Integer(55));
			bus.unregister(t1);
			bus.post(new String("only target2 - string 1"));
			bus.post(new String("only target2 - string 2"));
			bus.unregister(t2);
			bus.post(new String("string string string"));
		
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

	}
	
	public static class StringTarget{
		@Subscribe
		void onEvent(String str){
			System.out.println("arg='"+str + "'  " + this.getClass().getSimpleName());
		}
		
		@Subscribe
		void onEvent(Integer i){
			System.out.println("arg='"+i + "'  " + this.getClass().getSimpleName());
		}
	}
	
	public static class StringTarget2{
		@Subscribe
		void onEvent(String str){
			System.out.println("arg='"+str + "'  " + this.getClass().getSimpleName());
		}
	}

}
