package zad2;

public class test {
int a, b, wynik;
	public test(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	public void start()
	{
		oblicz();
		wynik();
	}
	
	public void oblicz()
	{
		System.out.println(" a " + a + " b " + b);
		wynik = a - b;
	}
	public void wynik()
	{
		System.out.println("Wynik " + wynik);
	}
	
}
