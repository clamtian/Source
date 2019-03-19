package DecoratorPattern;

public class Iron extends Look{
	Car car;
	public double price = 2.2;
	public String s = "金属外壳";
	public Iron(Car car){
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
