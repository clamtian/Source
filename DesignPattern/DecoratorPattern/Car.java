package DecoratorPattern;

public abstract class Car {
	double price;
	String s = "我只是一个模型";
	public void getDesc(){
		System.out.println(s);
	}
	public abstract double getPrice();
}
