package DecoratorPattern;

public class Test {
	public static void main(String[] args) {
		Car car = new BMW();
		car.getDesc();
		System.out.println(car.getPrice());
		System.out.println("~~~~~");
		car = new Red(car);
		car.getDesc();
		System.out.println(car.getPrice());
		System.out.println("~~~~~");
		car = new BMW();
		car = new Iron(car);
		car.getDesc();
		System.out.println(car.getPrice());
		System.out.println("~~~~~");
		car = new BMW();
		car = new Red(new Iron(car));
		car.getDesc();
		System.out.println(car.getPrice());
	}
}
