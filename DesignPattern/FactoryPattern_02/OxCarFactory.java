package FactoryPattern_02;

public class OxCarFactory implements ICarFactory{
	private Car car;
	public Car getCar(){
		this.car = new OxCar();
		return car;
	}
}
