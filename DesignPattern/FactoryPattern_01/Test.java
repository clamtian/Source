package FactoryPattern_01;



public class Test {
	
	public static void main(String[] args) {
		CarFactory carFactory = new CarFactory();
		Car car = carFactory.getCar(1);
		car.run();
		Car car1 = carFactory.getCar(0);
		car1.run();
	}
}
