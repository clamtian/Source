package ProxyPattern_Static;

public class CarProxy implements Buy{
	private Buy buy;
	public CarProxy(Buy buy){
		this.buy = buy;
	}
	public void buy(){
		System.out.println("凑够钱");
		buy.buy();
		System.out.println("开开心心");
	}
}
