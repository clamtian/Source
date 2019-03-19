package FactoryPattern_02;

public class OxCar extends Car{
	public void run(){
		System.out.println("OxCar is running!!");
	}
	public String getType(){
		return "OxCar";
	}
}
