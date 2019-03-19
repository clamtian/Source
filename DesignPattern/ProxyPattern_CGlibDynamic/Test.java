package ProxyPattern_CGlibDynamic;

public class Test {
	public static void main(String[] args) {
		Car car = (Car)CGlibProxy.getProxy(new Car());
		car.run();
	}
}
