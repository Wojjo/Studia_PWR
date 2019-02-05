package studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database.Assortment;
import studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database.MaintenanceHistory;
import studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database.RepairHistory;

public class ManageMaintenanceRepair {
	private boolean isRepair;
	private ArrayList<Integer> arrayListMRID;
	private ArrayList<MaintenanceHistory> arrayListMaintenances;
	private ArrayList<RepairHistory> arrayListRepairs;
	private ArrayList<Assortment> arrayListAssortment;
	private ArrayList<JButton> arrayListButtons;
	private int loggedID;
	private boolean editting;
	private MaintenanceHistory maintenance;
	private RepairHistory repair;

	JDialog dialogManageMaintenanceRepair;
	JLabel labelMRID;
	JTextField textFieldMRID;
	JLabel labelMRDate;
	JTextField textFieldMRDate;
	JLabel labelPrice;
	JTextField textFieldPrice;
	JLabel labelWorkerID;
	JTextField textFieldWorkerID;
	JPanel panelMRItems;
	JScrollPane scrollPaneMRItems;
	GridBagConstraints gridBagConstraints;
	JButton buttonOK;
	JButton buttonCancel;

	public ManageMaintenanceRepair(boolean isRepair, ArrayList<Assortment> arrayListAssortment,
			MaintenanceHistory maintenance, RepairHistory repair) {
		this.isRepair = isRepair;
		this.arrayListAssortment = arrayListAssortment;
		this.maintenance = maintenance;
		this.repair = repair;
		this.editting = true;

		this.arrayListButtons = new ArrayList<JButton>();

		initComponents();
		initListeners();

		dialogManageMaintenanceRepair.setVisible(true);
	}

	public ManageMaintenanceRepair(boolean isRepair, int loggedID, ArrayList<MaintenanceHistory> arrayListMaintenances,
			ArrayList<RepairHistory> arrayListRepairs, ArrayList<Assortment> arrayListAssortment) {
		this.isRepair = isRepair;
		this.editting = false;

		this.arrayListRepairs = arrayListRepairs;
		this.arrayListMaintenances = arrayListMaintenances;
		this.arrayListAssortment = arrayListAssortment;
		this.loggedID = loggedID;
		this.arrayListButtons = new ArrayList<JButton>();
		this.arrayListMRID = new ArrayList<Integer>();

		initComponents();
		initListeners();

		dialogManageMaintenanceRepair.setVisible(true);
	}

	private void initComponents() {
		dialogManageMaintenanceRepair = new JDialog();
		dialogManageMaintenanceRepair.setSize(new Dimension(1000, 500));
		if (isRepair)
			dialogManageMaintenanceRepair.setTitle("Zarzadzanie naprawa");
		else
			dialogManageMaintenanceRepair.setTitle("Zarzadzanie konserwacja");
		dialogManageMaintenanceRepair.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialogManageMaintenanceRepair.setModal(true);
		dialogManageMaintenanceRepair.setLayout(null);
		dialogManageMaintenanceRepair.setResizable(false);

		labelMRID = new JLabel("Numer konserwacji:");
		if (isRepair)
			labelMRID.setText("Data konserwacji:");
		labelMRID.setBounds(20, 30, 150, 30);
		dialogManageMaintenanceRepair.add(labelMRID);
		textFieldMRID = new JTextField();
		textFieldMRID.setBounds(170, 30, 150, 30);
		textFieldMRID.setEnabled(false);
		if (editting)
			if (isRepair)
				textFieldMRID.setText("" + repair.getRepairNumber());
			else
				textFieldMRID.setText("" + maintenance.getMaintenanceNumber());
		else if (isRepair)
			textFieldMRID.setText("" + (arrayListRepairs.size() + 1));
		else
			textFieldMRID.setText("" + (arrayListMaintenances.size() + 1));
		dialogManageMaintenanceRepair.add(textFieldMRID);

		labelMRDate = new JLabel("Data konserwacji");
		if (isRepair)
			labelMRDate.setText("Data naprawy");
		labelMRDate.setBounds(20, 70, 150, 30);
		dialogManageMaintenanceRepair.add(labelMRDate);
		textFieldMRDate = new JTextField();
		textFieldMRDate.setBounds(170, 70, 150, 30);
		textFieldMRDate.setEnabled(false);
		String[] parts = new Date().toString().split(" ");
		if (editting)
			if (isRepair)
				parts = repair.getRepairDate().toString().split(" ");
			else
				parts = maintenance.getMaintenanceDate().toString().split(" ");
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
		textFieldMRDate.setText(parts[2] + "/" + parts[1] + "/" + parts[5]);
		dialogManageMaintenanceRepair.add(textFieldMRDate);

		labelPrice = new JLabel("Cena:");
		labelPrice.setBounds(20, 110, 150, 30);
		dialogManageMaintenanceRepair.add(labelPrice);
		textFieldPrice = new JTextField();
		textFieldPrice.setBounds(170, 110, 150, 30);
		if (editting) {
			textFieldPrice.setEnabled(false);
			if (isRepair)
				textFieldPrice.setText("" + repair.getPrice());
			else
				textFieldPrice.setText("" + maintenance.getPrice());
		}
		dialogManageMaintenanceRepair.add(textFieldPrice);

		labelWorkerID = new JLabel("ID pracownika:");
		labelWorkerID.setBounds(20, 150, 150, 30);
		dialogManageMaintenanceRepair.add(labelWorkerID);
		textFieldWorkerID = new JTextField("" + loggedID);
		textFieldWorkerID.setBounds(170, 150, 150, 30);
		textFieldWorkerID.setEnabled(false);
		if (editting)
			if (isRepair)
				textFieldWorkerID.setText("" + repair.getWorkerID());
			else
				textFieldWorkerID.setText("" + maintenance.getWorkerID());
		dialogManageMaintenanceRepair.add(textFieldWorkerID);

		buttonOK = new JButton("OK");
		buttonOK.setBounds(50, 190, 80, 30);
		dialogManageMaintenanceRepair.add(buttonOK);

		buttonCancel = new JButton("Anuluj");
		buttonCancel.setBounds(150, 190, 150, 30);
		dialogManageMaintenanceRepair.add(buttonCancel);

		panelMRItems = new JPanel();
		panelMRItems.setLayout(new GridBagLayout());

		scrollPaneMRItems = new JScrollPane(panelMRItems);
		scrollPaneMRItems.setBounds(340, 20, 250, 250);
		dialogManageMaintenanceRepair.add(scrollPaneMRItems);

		gridBagConstraints = new GridBagConstraints();

		int i = 1;
		for (Assortment a : arrayListAssortment) {
			JButton temp_button = new JButton("ID: " + a.getItemID() + " Stan: " + a.getCondition());
			temp_button.setPreferredSize(new Dimension(150, 30));
			temp_button.setBackground(Color.LIGHT_GRAY);

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = i;

			arrayListButtons.add(temp_button);
			panelMRItems.add(temp_button, gridBagConstraints);
			i++;
		}
		if (editting)
			if (isRepair)
				for (Integer in : repair.getListEqupimentID())
					for (JButton b : arrayListButtons) {
						String[] parts2 = b.getText().split(" ");
						if (in == Integer.parseInt(parts2[1]))
							b.setBackground(new Color(100, 100, 100));
					}
			else
				for (Integer in : maintenance.getListEquipmentID())
					for (JButton b : arrayListButtons) {
						String[] parts2 = b.getText().split(" ");
						if (in == Integer.parseInt(parts2[1]))
							b.setBackground(new Color(100, 100, 100));
					}
	}

	private void initListeners() {
		if (!editting)
			for (JButton b : arrayListButtons) {
				b.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (b.getBackground() == Color.LIGHT_GRAY)
							b.setBackground(new Color(100, 100, 100));
						else
							b.setBackground(Color.LIGHT_GRAY);

						String[] parts = b.getText().split(" ");

						boolean is_in_array = false;
						Integer id = null;
						id = Integer.parseInt(parts[1]);

						for (Integer i : arrayListMRID)
							if (i == id)
								is_in_array = true;

						if (!is_in_array)
							arrayListMRID.add(id);
						else
							arrayListMRID.remove(id);
					}
				});
			}

		buttonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (editting) {
					dialogManageMaintenanceRepair.dispose();
					return;
				}

				int price = 0;
				try {
					price = Integer.parseInt(textFieldPrice.getText());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(dialogManageMaintenanceRepair, "Zly format ceny!");
					return;
				}
				if (price < 0) {
					JOptionPane.showMessageDialog(dialogManageMaintenanceRepair, "Zly format ceny!");
					return;
				}
				if (isRepair) {
					int nextRepairID;
					if (arrayListRepairs.size() == 0)
						nextRepairID = 1;
					else
						nextRepairID = arrayListRepairs.get(arrayListRepairs.size() - 1).getRepairNumber() + 1;

					for(Assortment a : arrayListAssortment)
						for(Integer i : arrayListMRID)
							if(a.getItemID() == i)
								a.setCondition("Dobry");
					
					arrayListRepairs.add(new RepairHistory(nextRepairID, new Date(), arrayListMRID, loggedID, price));
				} else {
					int nextMaintenanceID;
					if (arrayListMaintenances.size() == 0)
						nextMaintenanceID = 1;
					else
						nextMaintenanceID = arrayListMaintenances.get(arrayListMaintenances.size() - 1)
								.getMaintenanceNumber() + 1;
					
					Date date = new Date();
					date.setTime(date.getTime() + (315569088 * 100));
					System.out.println(date.getTime());
					for(Assortment a : arrayListAssortment)
						for(Integer i : arrayListMRID)
							if(a.getItemID() == i) {
								a.setNextMaintenanceDate(date);
								System.out.println(i + " " + a.getItemID() + " " + date);
							}
					arrayListMaintenances
							.add(new MaintenanceHistory(nextMaintenanceID, new Date(), arrayListMRID, loggedID, price));
				}
				dialogManageMaintenanceRepair.dispose();
			}
		});

		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialogManageMaintenanceRepair.dispose();
			}
		});
	}
}
