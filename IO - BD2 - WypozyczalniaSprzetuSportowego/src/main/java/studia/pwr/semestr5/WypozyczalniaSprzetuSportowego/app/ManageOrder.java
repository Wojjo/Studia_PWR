package studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database.OrderHistory;
import studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database.Worker;

public class ManageOrder {
	MainWindow window;
	OrderHistory order;

	JDialog dialogManageOrder;
	JLabel labelOrderNumber;
	JTextField textFieldOrderNumber;
	JLabel labelClientID;
	JTextField textFieldClientID;
	JLabel labelConfirmed;
	JCheckBox checkBoxConfirmed;
	JLabel labelOrderDate;
	JTextField textFieldOrderDate;
	JLabel labelCost;
	JTextField textFieldCost;
	JLabel labelItemIDAndLoanLength;
	JComboBox<String> comboBoxItemIDAndLoanLength;
	JButton buttonSave;
	JButton buttonCancel;

	public ManageOrder(MainWindow window, OrderHistory order) {
		this.window = window;
		this.order = order;

		initComponents();
		initListeners();

		dialogManageOrder.setVisible(true);
	}

	private void initComponents() {
		dialogManageOrder = new JDialog();
		dialogManageOrder.setTitle("Zarzadzanie zamowieniem");
		dialogManageOrder.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialogManageOrder.setResizable(false);
		dialogManageOrder.setModal(true);
		dialogManageOrder.setLayout(null);
		dialogManageOrder.setSize(680, 270);

		labelOrderNumber = new JLabel("Numer zamowienia: ");
		labelOrderNumber.setBounds(20, 20, 150, 30);
		dialogManageOrder.add(labelOrderNumber);
		textFieldOrderNumber = new JTextField();
		textFieldOrderNumber.setBounds(170, 20, 150, 30);
		textFieldOrderNumber.setText("" + order.getOrderNumber());
		textFieldOrderNumber.setEnabled(false);
		dialogManageOrder.add(textFieldOrderNumber);

		labelClientID = new JLabel("ID klienta: ");
		labelClientID.setBounds(20, 60, 150, 30);
		dialogManageOrder.add(labelClientID);
		textFieldClientID = new JTextField();
		textFieldClientID.setBounds(170, 60, 150, 30);
		textFieldClientID.setEnabled(false);
		textFieldClientID.setText("" + order.getClientID());
		dialogManageOrder.add(textFieldClientID);

		labelConfirmed = new JLabel("Potwierdz zamowienie: ");
		labelConfirmed.setBounds(360, 20, 150, 30);
		dialogManageOrder.add(labelConfirmed);
		checkBoxConfirmed = new JCheckBox();
		checkBoxConfirmed.setBounds(510, 20, 150, 30);
		dialogManageOrder.add(checkBoxConfirmed);

		labelOrderDate = new JLabel("Data zamowienia: ");
		labelOrderDate.setBounds(360, 60, 150, 30);
		dialogManageOrder.add(labelOrderDate);
		textFieldOrderDate = new JTextField();
		textFieldOrderDate.setBounds(510, 60, 150, 30);
		textFieldOrderDate.setEnabled(false);
		int[] parsedDate = parseDate(order.getOrderDate());
		textFieldOrderDate.setText(parsedDate[0] + "/" + parsedDate[1] + "/" + parsedDate[2]);
		dialogManageOrder.add(textFieldOrderDate);
		
		labelCost = new JLabel("Koszt: ");
		labelCost.setBounds(20, 100, 150, 30);
		dialogManageOrder.add(labelCost);
		textFieldCost = new JTextField();
		textFieldCost.setBounds(170, 100, 150, 30);
		textFieldCost.setEnabled(false);
		textFieldCost.setText((order.getCost() / 100) + "zl");
		dialogManageOrder.add(textFieldCost);

		labelItemIDAndLoanLength = new JLabel("Wypozyczone sprzety i dlugosc wypozyczenia: ");
		labelItemIDAndLoanLength.setBounds(110, 140, 300, 30);
		dialogManageOrder.add(labelItemIDAndLoanLength);
		comboBoxItemIDAndLoanLength = new JComboBox<String>();
		comboBoxItemIDAndLoanLength.setBounds(430, 140, 150, 30);
		for (int i = 0; i < order.getListEquipmentID().size(); i++) {
			comboBoxItemIDAndLoanLength.addItem("ID: " + order.getListEquipmentID().get(i) + ", Dlugosc:"
					+ order.getListEquipmentLoanLength().get(i));
		}
		dialogManageOrder.add(comboBoxItemIDAndLoanLength);

		buttonSave = new JButton("Zapisz");
		buttonSave.setBounds(230, 190, 100, 30);
		dialogManageOrder.add(buttonSave);

		buttonCancel = new JButton("Anuluj");
		buttonCancel.setBounds(350, 190, 100, 30);
		dialogManageOrder.add(buttonCancel);
	}

	private void initListeners() {
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (checkBoxConfirmed.isSelected())
					order.setWorkerID(window.getLoggedID());
				dialogManageOrder.dispose();
			}
		});

		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialogManageOrder.dispose();
			}
		});
	}
	
	private int[] parseDate(Date date) {
		String[] parts = date.toString().split(" ");
		int day = Integer.parseInt(parts[2]);
		int month;
		if (parts[1].equals("Jan"))
			month = 1;
		else if (parts[1].equals("Feb"))
			month = 2;
		else if (parts[1].equals("Mar"))
			month = 3;
		else if (parts[1].equals("Apr"))
			month = 4;
		else if (parts[1].equals("May"))
			month = 5;
		else if (parts[1].equals("Jun"))
			month = 6;
		else if (parts[1].equals("Jul"))
			month = 7;
		else if (parts[1].equals("Aug"))
			month = 8;
		else if (parts[1].equals("Oct"))
			month = 9;
		else if (parts[1].equals("Sep"))
			month = 10;
		else if (parts[1].equals("Nov"))
			month = 11;
		else
			month = 12;
		int year = Integer.parseInt(parts[5]);

		int[] parsedDate = new int[3];
		parsedDate[0] = day;
		parsedDate[1] = month;
		parsedDate[2] = year;

		return parsedDate;
	}
}
