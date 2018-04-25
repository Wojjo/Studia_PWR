import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;





enum CarState
{
	UNKNOWN("-------"),
	New("Nowe"),
	Used("Uzywane - bezwypadkowe"),
	Used2("Uzywane - kolizyjne");
	
	String State;
	
	private CarState(String state)
	{
		State = state;
	}
	
	public String toString()
	{
		return State;
	}
}

class ErrorMessages extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public ErrorMessages(String message)
	{
		super(message);
	}
}

public class Data 
{
	private String brand;
	private String model;
	private int year;
	private CarState state;
	
	public Data(String brand, String model ) throws ErrorMessages
	{
		setBrand(brand);
		setModel(model);
		state = CarState.UNKNOWN;
		
	}

	public String getBrand()
	{
		return brand;
	}
	
	public void setBrand(String set_brand) throws ErrorMessages
	{
		if((set_brand == null) || set_brand.equals(""))
			throw new ErrorMessages("Podaj marke samochodu!");
		this.brand = set_brand;
		
	}
	
	public String getModel()
	{
		return model;
	}
	
	public void setModel(String set_model) throws ErrorMessages
	{
		if((set_model == null) || (set_model.equals("")))
			throw new ErrorMessages("Podaj model samochodu!");
		this.model = set_model;
	}
	

	public int getYear()
	{
		return year;
	}
	
	public void setYear(int set_year) throws ErrorMessages
	{
		if(set_year < 1950 || set_year > 2018) 
			throw new ErrorMessages("Rok produkcji moze byc w przedziale od 1950 do 2018.");
		this.year = set_year;
	}
	
	public void setYear(String set_year) throws ErrorMessages
	{
		if(set_year == null || set_year.equals(""))
			throw new ErrorMessages("Podaj rok produkcji samochodu");
		try	
		{
			setYear(Integer.parseInt(set_year));
		} 
		catch (NumberFormatException e)
		{
			throw new ErrorMessages("Podaj poprawny rok produkcji samochodu");
		}
	}
	
	public CarState getState()
	{
		return state;
	}
	
	public void setState(CarState state)
	{
		this.state = state;
	}
	
	// Dla aplikacji w konsoli
	
	public void setState (String set_state) throws ErrorMessages
	{
		if(set_state == null || set_state.equals(""))
				throw new ErrorMessages("Wprowadz dane");
		for(CarState state : CarState.values())
		{
			this.state=state;
			return;
		}
	
	throw new ErrorMessages("Zle dane");

}
	
	public String toString()
	{
		return brand + " " + model;
	}
	public static void saveToFile(PrintWriter writer, Data data ) throws ErrorMessages
	{
		writer.println(data.brand + "#" + data.model + "#"
				+ data.year + "#" + data.state);
	}
	public static void printToFile(String file_name, Data data) throws ErrorMessages {
		try (PrintWriter writer = new PrintWriter(file_name)) {
			saveToFile(writer, data);
		} catch (FileNotFoundException e){
			throw new ErrorMessages("Nie odnaleziono pliku " + file_name);
		}
	}
	
	
	public static Data readFromFile(BufferedReader reader) throws ErrorMessages{
		try {
			String line = reader.readLine();
			String[] txt = line.split("#");
			Data data = new Data(txt[0], txt[1]);
			data.setYear(txt[2]);
			data.setState(txt[3]);	
			return data;
		} catch(IOException e){
			throw new ErrorMessages("Wyst¹pi³ b³¹d podczas odczytu danych z pliku.");
		}	
	}
	
	public static Data readFromFile(String file_name) throws ErrorMessages {
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(file_name)))) {
			return Data.readFromFile(reader);
		} catch (FileNotFoundException e){
			throw new ErrorMessages("Nie odnaleziono pliku " + file_name);
		} catch(IOException e){
			throw new ErrorMessages("Wyst¹pi³ b³¹d podczas odczytu danych z pliku.");
		}	
	}
	
}
