package FactoryPattern_02;

public class BMWCarFactory implements ICarFactory{
	private Car car;
	public Car getCar(){
		this.car = new BMW();
		return car;
	}
}
