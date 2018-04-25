/* 
 *  Product  
 *  Autor: Przemys³aw Wojcinowicz
 *  Indeks: 225943
 *  Grupa Czwartek 9:15
 *  Data: 16-26 pazdziernika 2016 r.
 */


import java.io.Serializable;
public class Product implements Serializable 
{
	private static final long serailVersionUID = 1L;
		private String model;
		private String brand;
		private double price;
		private int quantity;


		Product(String model, String brand, double price, int quantity) {
			this.model = model;
			this.brand = brand;
			this.price = price;
			this.quantity = quantity;
		}
		
	

		public String getModel() {
			return model;
		}


		public String getBrand() {
			return brand;
		}


		public double getPrice() {
			return price;
		}


		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		
	
		public void sell(int howMany) throws Exception
		{
			if(howMany >  quantity ) throw new Exception("Za malo par w magazynie. Poczekaj na dostawe.");
			
			quantity -= howMany;
		}
		
		public void supply(int howMuch) throws Exception
		{
			if(howMuch <  0 ) throw new Exception("Blad.");
			
			quantity += howMuch;
		}
		public void changePrice(int howMuch) throws Exception
		{
			if(howMuch <  0 ) throw new Exception("Blad.");
			
			price = howMuch;
		}
		
		public String toString(){
			return String.format("   %s  [ %s ] [ %s]  [ %s ] ", model, brand, price, quantity );
		}
	}


