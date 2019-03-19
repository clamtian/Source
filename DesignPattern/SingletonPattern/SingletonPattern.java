package SingletonPattern;

/**
 * 单例模式的实现
 *@author clam
 *
 */
public class SingletonPattern {
	/**
	 * 懒汉式，调用时初始化
	 * 在getInstance()前加synchronized变为线程安全
	 */
	
//	private static SingletonPattern instance;
//	
//	private SingletonPattern(){};
//	
//	public static SingletonPattern getInstance(){
//		if(instance == null){
//			instance = new SingletonPattern();
//		}
//		return instance;
//	}
	
	/**
	 * 饿汉式，在类加载时初始化
	 * 用classloader的机制保证同步安全
	 * 问题：什么机制？
	 * 复习：在类的初始化过程中会执行类构造器的<clinit>()方法，执行静态变量的赋值动作和静态块中语句
	 * <clinit>()方法在多线程中是加锁的，同步安全
	 */
//	private static SingletonPattern instance = new SingletonPattern();
//	private SingletonPattern(){};
//	public static SingletonPattern getInstance(){
//		return instance;
//	}
	
	/**
	 * 静态内部类
	 * 同步安全
	 * 静态内部类的加载不需要依附外部类，在使用时才加载。不过在加载静态内部类的过程中也会加载外部类。
	 * 想象一下，如果实例化instance很消耗资源，我想让他延迟加载，另外一方面，我不希望在
	 * Singleton类加载时就实例化，因为我不能确保Singleton类还可能在其他的地方被主动使用从而被加载，
	 * 那么这个时候实例化instance显然是不合适的。
	 * 实现lazy loading
	 */
//	private static class SingletonHolder{
//		private static final SingletonPattern instance = new SingletonPattern();
//	}
//	private SingletonPattern(){};
//	public static SingletonPattern getInstance(){
//		return SingletonHolder.instance;
//	}
	/**
	 * 双重校验锁
	 * 线程安全
	 */
	 private volatile static SingletonPattern instance;
	    private SingletonPattern(){};
	    public static SingletonPattern getInstance(){
	        if (instance == null) {
	            synchronized(SingletonPattern.class){
	                if (instance == null) {
	                	instance = new SingletonPattern();
	                }
	            }
	        }
	        return instance;
	    }
}
