package ProxyPattern_CGlibDynamic;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CGlibProxy implements MethodInterceptor{
	Object target;
	public CGlibProxy(Object target){
		this.target = target;
	}
	
	@Override
	public Object intercept(Object o, Method method, Object[] objects, 
			MethodProxy methodProxy) throws Throwable {
		System.out.println("代理前");
		Object result = method.invoke(target, objects);
		System.out.println("代理完成");
		return result;
	}
	
	public static Object getProxy(Object target){
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(target.getClass());
		enhancer.setCallback(new CGlibProxy(target));
		return enhancer.create();
	}
}
