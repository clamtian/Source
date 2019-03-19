package ProxyPattern_JDKDynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class BuyInvocationHandler implements InvocationHandler{
	Buy buy;
	public BuyInvocationHandler(Buy buy){
		this.buy = buy;
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Exception{
		if(method.getName().equals("buy")){
			System.out.println("买车");
			method.invoke(buy, args);
			
		}else{
			System.out.println("看车");
			method.invoke(buy, args);
			
		}
		return null;
	}
}
