package DecoratorPattern;

public class Red extends Look{
	double price = 1.1;
	String s = "红色外观";
	Car car;
	public Red(Car car){
		this.car = car;
		price += car.getPrice();
	}
	public void getDesc(){
		car.getDesc();
		System.out.println(s);
	}
	public double getPrice(){
		return price;
	}
}
