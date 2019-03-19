package ProxyPattern_Static;

public class Test {
	public static void main(String[] args) {
		Buy buy = new Car();
		buy.buy();
		buy = new CarProxy(buy);
		buy.buy();
	}
}
