package FactoryPattern_02;

public class Tset {
	public static void main(String[] args) {
		ICarFactory factory = new BMWCarFactory();
		ICar car;
		car = factory.getCar();
		System.out.println(car.getType());
		car.run();
		car.voice();
		factory = new OxCarFactory();
		car = factory.getCar();
		System.out.println(car.getType());
	}
}
