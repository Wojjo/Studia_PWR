import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


/* Autor: Przemys³aw Wojcinowicz
 * Data: 10.10.2017
 * Plik: WindowsApp
 * 
 * Aplikacja okienkowa z GUI
 * 
 */

public class WindowApp extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	private static final String GREETING_MESSAGE = 
			"Program Komis \n" +
			"Autor: Przemyslaw Wojcinowicz" +
			"Data: 10.10.2017";
	
	public static void main(String[] args)
	{
		
		new WindowApp();
		
	}
		
	
	private Data currentCar;
	
	Font font = new Font("MonoSpaced", Font.BOLD, 12);
	
	// Przyciski
	JButton newButton = new JButton("Nowy pojazd");
	JButton editButton = new JButton("Edytuj dane");
	JButton saveButton = new JButton("Zapisz dane");
	JButton loadButton = new JButton("Wczytaj");
	JButton deleteButton = new JButton("Usuñ pojazd");
	JButton infoButton = new JButton("Informacje");
	JButton exitButton = new JButton("Wyjœcie");
	
	// Etykiety wyœwietlane na panelu w g³ównym oknie aplikacji
	JLabel brandLabel = new JLabel("      Marka: ");
	JLabel modelLabel = new JLabel("      Model: ");
	JLabel yearLabel  = new JLabel("        Rok: ");
	JLabel stateLabel = new JLabel("     Status: ");

	// Pola tekstowe wyœwietlane na panelu w g³ównym oknie aplikacji
	JTextField brandField = new JTextField(10);
	JTextField modelField    = new JTextField(10);
	JTextField yearField    = new JTextField(10);
	JTextField stateField     = new JTextField(10);
	
	public WindowApp()
	{
		// Konfiguracja parametrów g³ównego okna aplikacji
		setTitle("Menu");  
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(270, 270);
		setResizable(false);
		setLocationRelativeTo(null);
		
		newButton.addActionListener(this);
		editButton.addActionListener(this);
		saveButton.addActionListener(this);
		loadButton.addActionListener(this);
		deleteButton.addActionListener(this);
		infoButton.addActionListener(this);
		exitButton.addActionListener(this);
		
		// Zmiana domyœlnego fontu dla wszystkich etykiet
		// U¿yto fontu o sta³ej szerokoœci znaków, by wyrównaæ
		// szerokoœæ wszystkich etykiet.
		brandLabel.setFont(font);
		modelLabel.setFont(font);
		yearLabel.setFont(font);
		stateLabel.setFont(font);
		
		// Zablokowanie mo¿liwoœci edycji tekstów we wszystkich 
		// polach tekstowych.  (pola nieedytowalne)
		brandField.setEditable(false);
		modelField.setEditable(false);
		yearField.setEditable(false);
		stateField.setEditable(false);
		
		
		// Utworzenie g³ównego panelu okna aplikacji.
		// Domyœlnym mened¿erem rozd³adu dla panelu bêdzie
		// FlowLayout, który uk³ada wszystkie komponenty jeden za drugim.
		JPanel panel = new JPanel();
		
		// Dodanie i rozmieszczenie na panelu wszystkich
				// komponentów GUI.
				panel.add(brandLabel);
				panel.add(brandField);
				
				panel.add(modelLabel);
				panel.add(modelField);
				
				panel.add(yearLabel);
				panel.add(yearField);
				
				panel.add(stateLabel);
				panel.add(stateField);

				panel.add(newButton);
				panel.add(deleteButton);
				panel.add(saveButton);
				panel.add(loadButton);
				panel.add(editButton);
				panel.add(infoButton);
				panel.add(exitButton);
				
				
				// Umieszczenie Panelu w g³ównym oknie aplikacji.
				setContentPane(panel);
				
				// Wype³nienie pól tekstowych danymi aktualnej osoby.
				showCurrentCar();
				
				// Pokazanie na ekranie g³ównego okna aplikacji
				// UWAGA: T¹ instrukcjê nale¿y wykonaæ jako ostatni¹
				// po zainicjowaniu i rozmieszczeniu na panelu
				// wszystkich komponentów GUI.
				// Od tego momentu aplikacja uruchamia g³ówn¹ pêtlê zdarzeñ
				// która dzia³a w nowym w¹tku niezale¿nie od pozosta³ej czêœci programu.
				setVisible(true);
		
	}
	
	
	/*
	 * Metoda wype³nia wszystkie pola tekstowe danymi
	 * aktualnej osoby.
	 */
	void showCurrentCar() {
		if (currentCar == null) {
			brandField.setText("");
			modelField.setText("");
			yearField.setText("");
			stateField.setText("");
		} else {
			brandField.setText(currentCar.getBrand());
			modelField.setText(currentCar.getModel());
			yearField.setText("" + currentCar.getYear());
			stateField.setText("" + currentCar.getState());
		}
	}
	
	/*
	 * Implementacja interfejsu ActionListener.
	 * 
	 * Metoda actionPerformrd bedzie automatycznie wywo³ywana
	 * do obs³ugi wszystkich zdarzeñ od obiektów, którym jako s³uchacza zdarzeñ
	 * do³¹czono obiekt reprezentuj¹cy bie¿¹c¹ instancjê okna aplikacji (referencja this) 
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// Odczytanie referencji do obiektu, który wygenerowa³ zdarzenie.
		Object eventSource = event.getSource();

		try {
			if (eventSource == newButton) { 
				currentCar = WindowDialog.createNewCar(this);
			}
			if (eventSource == deleteButton) {
				currentCar = null;
			}
			if (eventSource == saveButton) {
				String fileName = JOptionPane.showInputDialog("Podaj nazwê pliku");
				if (fileName == null || fileName.equals("")) return;  // Cancel lub pusta nazwa pliku.
				Data.printToFile(fileName, currentCar);
			}
			if (eventSource == loadButton) {
				String fileName = JOptionPane.showInputDialog("Podaj nazwê pliku");
				if (fileName == null || fileName.equals("")) return;  // Cancel lub pusta nazwa pliku. 
				currentCar = Data.readFromFile(fileName);
			}
			if (eventSource == editButton) {
				if (currentCar == null) throw new ErrorMessages("Brak danych."); 
				WindowDialog.changeCarData(this, currentCar);
			}
			if (eventSource == infoButton) {
				JOptionPane.showMessageDialog(this, GREETING_MESSAGE);
			}
			if (eventSource == exitButton) {
				System.exit(0);
			}
		} catch (ErrorMessages e) {
			// Tu s¹ wychwytywane wyj¹tki zg³aszane przez metody klasy Data
			// gdy nie s¹ spe³nione ograniczenia na³o¿one na dopuszczelne wartoœci 
			// poszczególnych atrybutów.
			// Wyœwietlanie modalnego okna dialogowego
			// z komunikatem o b³êdzie zg³oszonym za pomoc¹ wyj¹tku ErrorMessages.
			JOptionPane.showMessageDialog(this, e.getMessage(), "B³¹d", JOptionPane.ERROR_MESSAGE);
		}
		
		// Aktualizacja zawartoœci wszystkich pól tekstowych.
		showCurrentCar();

	
	}	
}
