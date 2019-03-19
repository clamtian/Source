package FactoryPattern_01;


public class CarFactory{
	public Car getCar(int num){
		switch(num){
		case 0 :
			return new BMW();
		case 1 :
			return new OxCart();
		default :
			return null;
		}
	}
}