/* 
 *  Product  
 *  Przyjmuje wartosci model obuwia, firme, cene i ilosc dostepnych butow.
 *  Autor: Przemys³aw Wojcinowicz
 *  Indeks: 225943
 *  Grupa Czwartek 9:15
 *  Data: 16-26 pazdziernika 2016 r.
 */

/**
 * Klasa Account
 * <br>
 *  
 *
 *Klasa posiada funkcje:
 * <ul>
 *  <li> Przyjmuje wartosci model obuwia, firme, dane kupujacych, cene i ilosc dostepnych butow..</li>
 *  <li>  Umozliwia zmiane ceny, uzupelnienia zapasow produktow </li>
 *  <br>
 * </ul>
 *
 * @author Przemyslaw Wojcinowicz
 * @version 9.11.2016 r 
 */


import java.io.Serializable;
public class Product implements Serializable 
{
	private static final long serailVersionUID = 1L;
		private String model;
		private String brand;
		private double price;
		private int quantity;

        /**Konstruktor*/
		Product(String model, String brand, double price, int quantity) {
			this.model = model;
			this.brand = brand;
			this.price = price;
			this.quantity = quantity;
		}
		/** Metoda utowrzyc osobe kupujaca i sprawdzic co kupila */
		public void Buyer(String model, String who, double price, int quantity) {
			this.model = model;
			this.brand = who;
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
		
		/** Metoda umozliwia sprzedanie produktu */
		public void sell(int howMany) throws Exception
		{
			if(howMany >  quantity ) throw new Exception("Za malo par w magazynie. Poczekaj na dostawe.");
			
			quantity -= howMany;
		}
		/** Metoda umozliwia uzupelnic produkty w magazynie */
		public void supply(int howMuch) throws Exception
		{
			if(howMuch <  0 ) throw new Exception("Blad.");
			
			quantity += howMuch;
		}
		/** Metoda umozliwia zmienic cene produktu */
		public void changePrice(int howMuch) throws Exception
		{
			if(howMuch <  0 ) throw new Exception("Blad.");
			
			price = howMuch;
		}
		
		public String toString(){
			return String.format("   %s  [ %s ] [ %s]  [ %s ] ", model, brand, price, quantity );
		}
	}


