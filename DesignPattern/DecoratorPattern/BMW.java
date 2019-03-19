package DecoratorPattern;

public class BMW extends Car{
	double price = 100;
	String s = "宝马车";
	public void getDesc(){
		System.out.println(s);
	}
	public double getPrice(){
		return price;
	}
}
