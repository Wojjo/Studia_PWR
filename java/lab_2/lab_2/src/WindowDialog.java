import java.awt.Color;
import java.awt.Dialog;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Program: Aplikacja okienkowa z GUI, która umo¿liwia testowanie 
 *          operacji wykonywanych na obiektach klasy data.
 *    Plik: dataWindowDialog.java
 *          
 *   Autor: Pawe³ Rogalinski
 *    Data: pazdziernik 2017 r.
 *
 *
 * Klasa dataWindowDialog implementuje pomocnicze okna dialogowe
 * umo¿liwiaj¹ce utworzenie i wype³nienie danymi nowego obiektu klasy data
 * oraz modyfikacjê danych dla istniej¹cego obiektu klasy data.
 */




public class WindowDialog extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	private Data data;
	
	// Utworzenie i inicjalizacja komponentów do do budowy
	// okienkowego interfejsu u¿ytkownika
	JLabel brandLabel =    new JLabel(" Marka: ");
	JLabel modelLabel =    new JLabel("  Model: ");
	JLabel yearLabel =     new JLabel("    Rok:  ");
	JLabel stateLabel =    new JLabel(" Stan: ");

	JTextField brandField = new JTextField(10);
	JTextField modelField = new JTextField(10);
	JTextField yearField = new JTextField(10);
	JComboBox<CarState> jobBox = new JComboBox<CarState>(CarState.values());

	JButton OKButton = new JButton("  OK  ");
	JButton CancelButton = new JButton("Anuluj");
	
	
	
	
	/*
	 * Konstruktor klasy dataWindowDialog.
	 *     parent - referencja do okna aplikacji, z którego
     *              zosta³o wywo³ane to okno dialogowe.
     *     data - referencja do obiektu reprezentuj¹cego osobê,
     *              której dane maj¹ byæ modyfikowane. 
     *              Jeœli data jest równe null to zostanie utworzony 
     *              nowy obiekt klasy data
	 */
	private WindowDialog(Window parent, Data data)
	{
		// Wywo³anie konstruktora klasy bazowej JDialog.
		// Ta instrukcja pododuje ustawienie jako rodzica nowego okna dialogowego
		// referencji do tego okna, z którego wywo³ano to okno dialogowe.
		// Drugi parametr powoduje ustawienie trybu modalnoœci nowego okna diakogowego
		//       - DOCUMENT_MODAL oznacza, ¿e okno rodzica bêdzie blokowane.
		super(parent, Dialog.ModalityType.DOCUMENT_MODAL);
		
		// Konfiguracja parametrów tworzonego okna dialogowego
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(220, 220);
		setLocationRelativeTo(parent);
		
		// zapamiêtanie referencji do osoby, której dane bêd¹ modyfikowane.
		this.data = data;
		
		// Ustawienie tytu³u okna oraz wype³nienie zawartoœci pól tekstowych
		if (data==null){
			setTitle("Dodaj");
		} else{
			setTitle(data.toString());
			brandField.setText(data.getBrand());
			modelField.setText(data.getModel());
			yearField.setText(""+data.getYear());
			jobBox.setSelectedItem(data.getState());
		}
		
		// Dodanie s³uchaczy zdarzeñ do przycisków.
		// UWAGA: s³uchaczem zdarzeñ bêdzie metoda actionPerformed
		//        zaimplementowana w tej klasie i wywo³ana dla
		//        bie¿¹cej instancji okna dialogowego - referencja this
		OKButton.addActionListener( this );
		CancelButton.addActionListener( this );
		
		// Utworzenie g³ównego panelu okna dialogowego.
		// Domyœlnym mened¿erem rozd³adu dla panelu bêdzie
		// FlowLayout, który uk³ada wszystkie komponenty jeden za drugim.
		JPanel panel = new JPanel();
		
		// Zmiana koloru t³a g³ównego panelu okna dialogowego
		panel.setBackground(Color.green);

		// Dodanie i rozmieszczenie na panelu wszystkich komponentów GUI.
		panel.add(brandLabel);
		panel.add(brandField);
		
		panel.add(modelLabel);
		panel.add(modelField);
		
		panel.add(yearLabel);
		panel.add(yearField);
		
		panel.add(stateLabel);
		panel.add(jobBox);
		
		panel.add(OKButton);
		panel.add(CancelButton);
		
		// Umieszczenie Panelu w oknie dialogowym.
		setContentPane(panel);
		
		
		// Pokazanie na ekranie okna dialogowego
		// UWAGA: T¹ instrukcjê nale¿y wykonaæ jako ostatni¹
		// po zainicjowaniu i rozmieszczeniu na panelu
		// wszystkich komponentów GUI.
		// Od tego momentu aplikacja wyœwietla nowe okno dialogowe
		// i bokuje g³ówne okno aplikacji, z którego wywo³ano okno dialogowe
		setVisible(true);
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
		Object source = event.getSource();
		
		if (source == OKButton) {
			try {
				if (data == null) { // Utworzenie nowej osoby
					data = new Data(brandField.getText(), modelField.getText());
				} else { // Aktualizacji imienia i nazwiska istniej¹cej osoby
					data.setBrand(brandField.getText());
					data.setModel(modelField.getText());
				}
				// Aktualizacja pozosta³ych danych osoby
				data.setYear(yearField.getText());
				data.setState((CarState) jobBox.getSelectedItem());
				
				// Zamkniêcie okna i zwolnienie wszystkich zasobów.
				dispose();
			} catch (ErrorMessages e) {
				// Tu s¹ wychwytywane wyj¹tki zg³aszane przez metody klasy data
				// gdy nie s¹ spe³nione ograniczenia na³o¿one na dopuszczelne wartoœci 
				// poszczególnych atrybutów.
				// Wyœwietlanie modalnego okna dialogowego
				// z komunikatem o b³êdzie zg³oszonym za pomoc¹ wyj¹tku dataException.
				JOptionPane.showMessageDialog(this, e.getMessage(), "B³¹d", JOptionPane.ERROR_MESSAGE);
			}
		}

		if (source == CancelButton) {
			// Zamkniêcie okna i zwolnienie wszystkich zasobów.
			dispose();
		}
	}
	

	/* 
	 * Meoda tworzy pomocnicze okno dialogowe, które tworzy 
	 * nowy obiekt klasy data i umo¿liwia wprowadzenie danych
	 * dla nowo utworzonej osoby.
	 * Jako pierwszy parametr nale¿y przekazaæ referencjê do g³ównego okna
	 * aplikacji, z którego ta metoda jest wywo³ywana.
	 * G³ówne okno aplikacji zostanie zablokowane do momentu zamkniêcia
	 * okna dialogowego.
	 * Po zatwierdzeniu zmian przyciskiem OK odbywa siê  walidacja poprawnoœci 
	 * danych w konstruktorze i setterach klasy data. 
	 * Jeœli zostan¹ wykryte niepoprawne dane to zostanie przechwycony wyj¹tek 
	 * dataException i zostanie wyœwietlony komunikat o b³êdzie.
	 * 
	 *  Po poprawnym wype³nieniu danych metoda zamyka okno dialogowe
	 *  i zwraca referencjê do nowo utworzonego obiektu klasy data.
	 */
	public static Data createNewCar(Window parent) {
		WindowDialog dialog = new WindowDialog(parent, null);
		return dialog.data;
	}

	/* 
	 * Meoda tworzy pomocnicze okno dialogowe, które umo¿liwia 
	 * modyfikacjê danych osoby reprezentowanej przez obiekt klasy data,
	 * który zosta³ przekazany jako drugi parametr.
	 * Jako pierwszy parametr nale¿y przekazaæ referencjê do g³ównego okna
	 * aplikacji, z którego ta metoda jest wywo³ywana.
	 * G³ówne okno aplikacji zostanie zablokowane do momentu zamkniêcia
	 * okna dialogowego.
	 * Po zatwierdzeniu zmian przyciskiem OK odbywa siê  walidacja poprawnoœci 
	 * danych w konstruktorze i setterach klasy data. 
	 * Jeœli zostan¹ wykryte niepoprawne dane to zostanie przechwycony wyj¹tek 
	 * dataException i zostanie wyœwietlony komunikat o b³êdzie.
	 * 
	 *  Po poprawnym wype³nieniu danych metoda aktualizuje dane w obiekcie data
	 *  i zamyka okno dialogowe
	 */
	public static void changeCarData(Window parent, Data data) {
		new WindowDialog(parent, data);
	}

	
	

}
