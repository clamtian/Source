package ProxyPattern_JDKDynamic;

import java.lang.reflect.Proxy;

public class Test {
	public static void main(String[] args) {
		Buy buy = new BuyCar();
		BuyInvocationHandler handler = new BuyInvocationHandler(buy);
		
		Buy buy_ = (Buy)Proxy.newProxyInstance(Buy.class.getClassLoader(), 
				new Class[]{Buy.class}, handler);
		buy_.look();
		buy_.buy();
		
	}
}
