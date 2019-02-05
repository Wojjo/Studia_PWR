package studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.app;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database.Assortment;

public class ManageAssortment {

	private ArrayList<Assortment> arrayListAssortment;
	private boolean create;
	private Assortment assortment;
	private String[] dividedModel;
	JPanel panelDBContent;

	JDialog dialogManageAssortment;

	JLabel labelAssortmentID;
	JTextField textFieldAssortmentID;
	JLabel labelModelID;
	JTextField textFieldModelID;
	JLabel labelBuyDate;
	JTextField textFieldBuyDate;
	JLabel labelRentNumber;
	JTextField textFieldRentNumber;
	JLabel labelLastRentDate;
	JTextField textFieldLastRentDate;
	JLabel LabelAvailability;
	JCheckBox checkBoxAvailability;
	JLabel labelDateNextMaintenance;
	JTextField textFieldDateNextMaintenance;
	JLabel labelCondition;
	JTextField textFieldCondition;
	JLabel labelNumberOrdersNumber;
	JComboBox<Integer> comboBoxOrdersNumber;
	JLabel labelMaintenanceNumber;
	JComboBox<Integer> comboBoxMaintenanceNumber;
	JLabel labelRepairNumber;
	JComboBox<Integer> comboBoxRepairNumber;
	JButton buttonCancel;
	JButton buttonCreateAssortment;

	public ManageAssortment() {
	}

	public ManageAssortment(ArrayList<Assortment> arrayListAssortment, boolean create) {

		this.arrayListAssortment = arrayListAssortment;
		this.create = create;

		initComponents();
		initListeners();

		dialogManageAssortment.setVisible(true);
	}

	public ManageAssortment(ArrayList<Assortment> arrayListAssortment, boolean create, Assortment assortment,
			JPanel panelDBContent) {

		this.arrayListAssortment = arrayListAssortment;
		this.create = create;
		this.assortment = assortment;
		this.panelDBContent = panelDBContent;

		divideModel();
		initComponents();
		initListeners();

		dialogManageAssortment.setVisible(true);
	}

	private void divideModel() {

		dividedModel = assortment.toString().split(Pattern.quote(" !&#*&% "));
	}

	private void initComponents() {
		dialogManageAssortment = new JDialog();
		dialogManageAssortment.setTitle("Dodanie sprzętu");
		dialogManageAssortment.setSize(new Dimension(800, 400));
		dialogManageAssortment.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialogManageAssortment.setResizable(false);
		dialogManageAssortment.setModal(true);
		if (!create)
			dialogManageAssortment.setTitle("Edycja sprzętu");
		dialogManageAssortment.setLayout(null);

		labelAssortmentID = new JLabel("ID sprzetu:");
		labelAssortmentID.setBounds(20, 30, 180, 30);
		dialogManageAssortment.add(labelAssortmentID);
		textFieldAssortmentID = new JTextField();
		textFieldAssortmentID.setBounds(200, 30, 150, 30);
		if (!create) {
			textFieldAssortmentID.setText(dividedModel[0]);
			textFieldAssortmentID.setEnabled(false);
		}
		dialogManageAssortment.add(textFieldAssortmentID);

		labelModelID = new JLabel("ID modelu:");
		labelModelID.setBounds(20, 70, 180, 30);
		dialogManageAssortment.add(labelModelID);
		textFieldModelID = new JTextField();
		textFieldModelID.setBounds(200, 70, 150, 30);
		if (!create) {
			textFieldModelID.setText(dividedModel[7]);
			textFieldModelID.setEnabled(false);
		}
		dialogManageAssortment.add(textFieldModelID);

		labelBuyDate = new JLabel("Data zakupu:");
		labelBuyDate.setBounds(20, 110, 180, 30);
		dialogManageAssortment.add(labelBuyDate);
		textFieldBuyDate = new JTextField();
		textFieldBuyDate.setBounds(200, 110, 150, 30);
		if (!create) {
			String[] parts = dividedModel[1].split(" ");

			if (parts[1].equals("Jan"))
				parts[1] = "01";
			else if (parts[1].equals("Feb"))
				parts[1] = "02";
			else if (parts[1].equals("Mar"))
				parts[1] = "03";
			else if (parts[1].equals("Apr"))
				parts[1] = "04";
			else if (parts[1].equals("May"))
				parts[1] = "05";
			else if (parts[1].equals("Jun"))
				parts[1] = "06";
			else if (parts[1].equals("Jul"))
				parts[1] = "07";
			else if (parts[1].equals("Aug"))
				parts[1] = "08";
			else if (parts[1].equals("Oct"))
				parts[1] = "09";
			else if (parts[1].equals("Sep"))
				parts[1] = "10";
			else if (parts[1].equals("Nov"))
				parts[1] = "11";
			else if (parts[1].equals("Dec"))
				parts[1] = "12";

			textFieldBuyDate.setText(parts[2] + "/" + parts[1] + "/" + parts[5]);
		}
		dialogManageAssortment.add(textFieldBuyDate);

		labelRentNumber = new JLabel("Liczba wypozyczen:");
		labelRentNumber.setBounds(20, 150, 180, 30);
		dialogManageAssortment.add(labelRentNumber);
		textFieldRentNumber = new JTextField();
		textFieldRentNumber.setBounds(200, 150, 150, 30);
		if (!create) {
			textFieldRentNumber.setText(dividedModel[2]);
			textFieldRentNumber.setEnabled(false);
		}
		dialogManageAssortment.add(textFieldRentNumber);

		labelLastRentDate = new JLabel("Data ostatniego wypozyczenia:");
		labelLastRentDate.setBounds(20, 190, 180, 30);
		dialogManageAssortment.add(labelLastRentDate);
		textFieldLastRentDate = new JTextField();
		textFieldLastRentDate.setBounds(200, 190, 150, 30);
		if (!create) {
			textFieldLastRentDate.setEnabled(false);

			try {
				String[] parts = dividedModel[3].split(" ");

				if (parts[1].equals("Jan"))
					parts[1] = "01";
				else if (parts[1].equals("Feb"))
					parts[1] = "02";
				else if (parts[1].equals("Mar"))
					parts[1] = "03";
				else if (parts[1].equals("Apr"))
					parts[1] = "04";
				else if (parts[1].equals("May"))
					parts[1] = "05";
				else if (parts[1].equals("Jun"))
					parts[1] = "06";
				else if (parts[1].equals("Jul"))
					parts[1] = "07";
				else if (parts[1].equals("Aug"))
					parts[1] = "08";
				else if (parts[1].equals("Oct"))
					parts[1] = "09";
				else if (parts[1].equals("Sep"))
					parts[1] = "10";
				else if (parts[1].equals("Nov"))
					parts[1] = "11";
				else if (parts[1].equals("Dec"))
					parts[1] = "12";

				textFieldLastRentDate.setText(parts[2] + "/" + parts[1] + "/" + parts[5]);
			} catch (Exception e) {
			}
		}
		dialogManageAssortment.add(textFieldLastRentDate);

		LabelAvailability = new JLabel("Dostepnosc:");
		LabelAvailability.setBounds(20, 230, 180, 30);
		dialogManageAssortment.add(LabelAvailability);
		checkBoxAvailability = new JCheckBox();
		checkBoxAvailability.setBounds(200, 230, 150, 30);
		if (!create && dividedModel[4].equals("true"))
			checkBoxAvailability.setSelected(true);
		dialogManageAssortment.add(checkBoxAvailability);

		labelDateNextMaintenance = new JLabel("Data nastepnej konserwacji:");
		labelDateNextMaintenance.setBounds(20, 310, 180, 30);
		dialogManageAssortment.add(labelDateNextMaintenance);
		textFieldDateNextMaintenance = new JTextField();
		textFieldDateNextMaintenance.setBounds(200, 310, 150, 30);
		if (!create) {
			try {
				String[] parts = dividedModel[5].split(" ");

				if (parts[1].equals("Jan"))
					parts[1] = "01";
				else if (parts[1].equals("Feb"))
					parts[1] = "02";
				else if (parts[1].equals("Mar"))
					parts[1] = "03";
				else if (parts[1].equals("Apr"))
					parts[1] = "04";
				else if (parts[1].equals("May"))
					parts[1] = "05";
				else if (parts[1].equals("Jun"))
					parts[1] = "06";
				else if (parts[1].equals("Jul"))
					parts[1] = "07";
				else if (parts[1].equals("Aug"))
					parts[1] = "08";
				else if (parts[1].equals("Oct"))
					parts[1] = "09";
				else if (parts[1].equals("Sep"))
					parts[1] = "10";
				else if (parts[1].equals("Nov"))
					parts[1] = "11";
				else if (parts[1].equals("Dec"))
					parts[1] = "12";

				textFieldDateNextMaintenance.setText(parts[2] + "/" + parts[1] + "/" + parts[5]);
			} catch (Exception e) {
			}
		}
		dialogManageAssortment.add(textFieldDateNextMaintenance);

		labelCondition = new JLabel("Stan:");
		labelCondition.setBounds(20, 270, 150, 30);
		dialogManageAssortment.add(labelCondition);
		textFieldCondition = new JTextField();
		textFieldCondition.setBounds(200, 270, 150, 30);
		if (!create)
			textFieldCondition.setText(dividedModel[6]);
		dialogManageAssortment.add(textFieldCondition);

		buttonCancel = new JButton("Anuluj");
		buttonCancel.setBounds(500, 310, 80, 30);
		dialogManageAssortment.add(buttonCancel);

		buttonCreateAssortment = new JButton("Utworz");
		buttonCreateAssortment.setBounds(410, 310, 80, 30);
		if (!create)
			buttonCreateAssortment.setText("Zapisz");
		dialogManageAssortment.add(buttonCreateAssortment);

		labelNumberOrdersNumber = new JLabel("Lista zamowien: ");
		labelNumberOrdersNumber.setBounds(380, 20, 150, 30);
		dialogManageAssortment.add(labelNumberOrdersNumber);
		comboBoxOrdersNumber = new JComboBox<Integer>();
		comboBoxOrdersNumber.setBounds(560, 20, 150, 30);
		if (!create)
			for (Integer i : assortment.getListOrdersNumber())
				comboBoxOrdersNumber.addItem(i);
		dialogManageAssortment.add(comboBoxOrdersNumber);

		labelMaintenanceNumber = new JLabel("Lista konserwacji: ");
		labelMaintenanceNumber.setBounds(380, 60, 150, 30);
		dialogManageAssortment.add(labelMaintenanceNumber);
		comboBoxMaintenanceNumber = new JComboBox<Integer>();
		comboBoxMaintenanceNumber.setBounds(560, 60, 150, 30);
		if (!create)
			for (Integer i : assortment.getListMaintenancesNumber())
				comboBoxOrdersNumber.addItem(i);
		dialogManageAssortment.add(comboBoxMaintenanceNumber);

		labelRepairNumber = new JLabel("Lista napraw: ");
		labelRepairNumber.setBounds(380, 100, 150, 30);
		dialogManageAssortment.add(labelRepairNumber);
		comboBoxRepairNumber = new JComboBox<Integer>();
		comboBoxRepairNumber.setBounds(560, 100, 150, 30);
		if (!create)
			for (Integer i : assortment.getListRepairsNumber())
				comboBoxOrdersNumber.addItem(i);
		dialogManageAssortment.add(comboBoxRepairNumber);
	}

	private void initListeners() {

		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialogManageAssortment.dispose();
			}
		});

		buttonCreateAssortment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (create)
					createAssortment();
				else
					editAssortment();
			}

		});
	}

	private void createAssortment() {
		Connect oracle = new Connect();
		String assortmentID = textFieldAssortmentID.getText();
		String modelID = textFieldModelID.getText();
		java.util.Date buyDate = null;
		try {
			buyDate = new SimpleDateFormat("dd/MM/yyyy").parse(textFieldBuyDate.getText());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		String rentNumber = textFieldRentNumber.getText();
		java.util.Date lastRentDate = null;
		try {
			lastRentDate = new SimpleDateFormat("dd/MM/yyyy").parse(textFieldLastRentDate.getText());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		boolean availability = checkBoxAvailability.isSelected();
		java.util.Date dateNextMaintenance = null;
		try {
			dateNextMaintenance = new SimpleDateFormat("dd/MM/yyyy").parse(textFieldDateNextMaintenance.getText());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		String condition = textFieldCondition.getText();

		if (assortmentID.equals(""))
			JOptionPane.showMessageDialog(dialogManageAssortment, "Pole ID sprzetu nie moze byc puste");
		else if (modelID.equals(""))
			JOptionPane.showMessageDialog(dialogManageAssortment, "Pole ID modelu nie moze byc puste");
		else if (condition.equals(""))
			JOptionPane.showMessageDialog(dialogManageAssortment, "Pole stan nie moze byc puste");
		else if (buyDate == null)
			JOptionPane.showMessageDialog(dialogManageAssortment, "Wprowadz date zakupu");

		else {
			for (Assortment a : arrayListAssortment)
				if (Integer.toString(a.getItemID()).equals(assortmentID)) {
					JOptionPane.showMessageDialog(dialogManageAssortment,
							"Sprzet o podanym ID znajduje sie juz w bazie");
					return;
				}
		}
		int modelID2 = Integer.parseInt(textFieldModelID.getText());
		int rentNumber2 = Integer.parseInt(textFieldRentNumber.getText());
		int assortmentID2 = Integer.parseInt(textFieldAssortmentID.getText());
		int availability2 = availability ? 1 : 0;;
		
			oracle.db_connect();
			oracle.db_createAssortment(assortmentID2, buyDate, rentNumber2, lastRentDate, availability2,
					dateNextMaintenance, condition, modelID2);
			oracle.db_disconnect();
			

		arrayListAssortment.add(new Assortment(assortmentID2, buyDate, rentNumber2, lastRentDate, true,
				dateNextMaintenance, condition, modelID2));
		JOptionPane.showMessageDialog(dialogManageAssortment, "Dodano sprzet");
		dialogManageAssortment.dispose();
	}

	private void editAssortment() {
		String assortmentID = textFieldAssortmentID.getText();
		String modelID = textFieldModelID.getText();
		String rentNumber = textFieldRentNumber.getText();
		boolean availability = checkBoxAvailability.isSelected();
		String condition = textFieldCondition.getText();
		java.util.Date dateNextMaintenance = null;
		try {
			dateNextMaintenance = new SimpleDateFormat("dd/MM/yyyy").parse(textFieldDateNextMaintenance.getText());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		java.util.Date lastRentDate = null;
		try {
			lastRentDate = new SimpleDateFormat("dd/MM/yyyy").parse(textFieldLastRentDate.getText());
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("MA WYRZUCAC BLAD BO DATA OSTATNIEGO WYPOZYCZENIA JEST NULL CZYLI OKE XD");
		}
		java.util.Date buyDate = null;
		try {
			buyDate = new SimpleDateFormat("dd/MM/yyyy").parse(textFieldBuyDate.getText());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (assortmentID.equals(""))
			JOptionPane.showMessageDialog(dialogManageAssortment, "Pole ID sprzetu nie moze byc puste");
		else if (modelID.equals(""))
			JOptionPane.showMessageDialog(dialogManageAssortment, "Pole ID modelu nie moze byc puste");
		else if (condition.equals(""))
			JOptionPane.showMessageDialog(dialogManageAssortment, "Pole stan nie moze byc puste");
		else if (buyDate == null)
			JOptionPane.showMessageDialog(dialogManageAssortment, "Wprowadz date zakupu");

		else
			try {
				assortment.setPucharseDate(buyDate);
				assortment.setNextMaintenanceDate(dateNextMaintenance);
				assortment.setCondition(condition);
				dialogManageAssortment.dispose();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(dialogManageAssortment, "Pole z ceną lub kaucją zawiera błędne dane!");
			}

	}

}
