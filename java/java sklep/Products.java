/* 
 * Products 
 *  Autor: Przemys³aw Wojcinowicz
 *  Indeks: 225943
 *  Grupa Czwartek 9:15
 *  Data: 16-26 pazdziernika 2016 r.
 */




import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.Serializable;
public class Products implements Serializable 
{
	
	private ArrayList<Product> listOfShoes = new ArrayList<Product>();
	private ArrayList<Product> listOfBuyers = new ArrayList<Product>();
	
	public Product createProduct(String model, String brand,  double price, int quantity) throws Exception {
		if (model==null || model.equals("")) throw(new Exception("Nazwa produktu nie moze byc pusta"));
		if (findProduct(brand)!=null) throw(new Exception("Produkt juz istnieje"));
		Product newProduct = new Product(model, brand, price, quantity);
		listOfShoes.add( newProduct );
		return newProduct;
	}

	public void removeProduct(Product product) throws Exception {
		if (product==null) throw(new Exception("Brak produktu"));
		listOfShoes.remove(product);
	}
	
	public Product findProduct(String model)
	{
		
		for (Product product : listOfShoes)
			if (product.getModel().equals(model)) return product;
		return null;
	}
	
	public String listProduct(){
		StringBuilder sh = new StringBuilder();
		int n = 0;
		for (Product product : listOfShoes){
			if (n++ != 0) sh.append("\n");		
			sh.append(product.toString());
		}
		return sh.toString();
	}
	
	public Product addToBuyersList(String model, String brand,  double price, int quantity) throws Exception {
		Product newBuyer = new Product(model, brand, price, quantity);
		listOfBuyers.add( newBuyer);
		return newBuyer;
	}
	
	
	
	public String listBuyers(){
		StringBuilder sh = new StringBuilder();
		int n = 0;
		for (Product product : listOfBuyers){
			if (n++ != 0) sh.append("\n");		
			sh.append(product.toString());
		}
		return sh.toString();
	}
	
	
	
	
	
	void saveProductsToFile(String fileName2) throws Exception {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName2));
		out.writeObject(listOfShoes);
		out.close();
	}
	
	
	void loadSklepFromFile(String fileName2) throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName2));
		listOfShoes = (ArrayList<Product>)in.readObject();
		in.close();
	}

	void saveBuyersToFile(String fileName3) throws Exception {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName3));
		out.writeObject(listOfBuyers);
		out.close();
	}
	
	
	void loadBuyersFromFile(String fileName3) throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName3));
		listOfBuyers = (ArrayList<Product>)in.readObject();
		in.close();
	}

	
	



	
	
}
