package studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.app;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database.Model;

public class ManageModel {
	private ArrayList<Model> arrayListModels;
	private boolean create;
	private Model model;
	private String[] dividedModel;
	JPanel panelDBContent;

	JDialog dialogCreateModel;
	JLabel labelModelID;
	JTextField textFieldModelID;
	JLabel labelModelName;
	JTextField textFieldModelName;
	JLabel labelProducer;
	JTextField textFieldProducer;
	JLabel labelEquipmentType;
	JTextField textFieldEquipmentType;
	JLabel labelSeasonOfUse;
	JCheckBox checkBoxSeasonOfUse;
	JLabel labelPrice;
	JTextField textFieldPrice;
	JLabel labelDeposit;
	JTextField textFieldDeposit;
	JButton buttonCancel;
	JButton buttonCreateModel;

	public ManageModel() {
	}

	public ManageModel(ArrayList<Model> arrayListModels, boolean create) {
		this.arrayListModels = arrayListModels;
		this.create = create;

		initComponents();
		initListeners();

		dialogCreateModel.setVisible(true);
	}

	public ManageModel(ArrayList<Model> arrayListModels, boolean create, Model model, JPanel panelDBContent) {
		this.arrayListModels = arrayListModels;
		this.create = create;
		this.model = model;
		this.panelDBContent = panelDBContent;

		divideModel();
		initComponents();
		initListeners();

		dialogCreateModel.setVisible(true);
	}

	private void divideModel() {
		// id[0], nazwa[1], producent[2], typ[3], sezon[4], koszt[5], koszt
		// uszk.[6]
		dividedModel = model.toString().split(Pattern.quote(" !&#*&% "));
	}

	private void initComponents() {
		dialogCreateModel = new JDialog();
		dialogCreateModel.setTitle("Dodanie modelu");
		if (!create)
			dialogCreateModel.setTitle("Edycja modelu");
		dialogCreateModel.setSize(new Dimension(480, 400));
		dialogCreateModel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialogCreateModel.setResizable(false);
		dialogCreateModel.setModal(true);
		dialogCreateModel.setLayout(null);

		labelModelID = new JLabel("Model ID:");
		labelModelID.setBounds(20, 30, 180, 30);
		dialogCreateModel.add(labelModelID);
		textFieldModelID = new JTextField();
		textFieldModelID.setBounds(200, 30, 150, 30);
		textFieldModelID.setEnabled(false);
		textFieldModelID.setText("" + (arrayListModels.get(arrayListModels.size() - 1).getModelID() + 1));
		if (!create) {
			textFieldModelID.setText(dividedModel[0]);
		}
		dialogCreateModel.add(textFieldModelID);

		labelModelName = new JLabel("Nazwa modelu:");
		labelModelName.setBounds(20, 70, 180, 30);
		dialogCreateModel.add(labelModelName);
		textFieldModelName = new JTextField();
		textFieldModelName.setBounds(200, 70, 150, 30);
		if (!create)
			textFieldModelName.setText(dividedModel[1]);
		dialogCreateModel.add(textFieldModelName);

		labelProducer = new JLabel("Producent:");
		labelProducer.setBounds(20, 110, 180, 30);
		dialogCreateModel.add(labelProducer);
		textFieldProducer = new JTextField();
		textFieldProducer.setBounds(200, 110, 150, 30);
		if (!create)
			textFieldProducer.setText(dividedModel[2]);
		dialogCreateModel.add(textFieldProducer);

		labelEquipmentType = new JLabel("Rodzaj sprzętu:");
		labelEquipmentType.setBounds(20, 150, 180, 30);
		dialogCreateModel.add(labelEquipmentType);
		textFieldEquipmentType = new JTextField();
		textFieldEquipmentType.setBounds(200, 150, 150, 30);
		if (!create)
			textFieldEquipmentType.setText(dividedModel[3]);
		dialogCreateModel.add(textFieldEquipmentType);

		labelSeasonOfUse = new JLabel("Sezon (lato/zima):");
		labelSeasonOfUse.setBounds(20, 190, 180, 30);
		dialogCreateModel.add(labelSeasonOfUse);
		checkBoxSeasonOfUse = new JCheckBox();
		checkBoxSeasonOfUse.setBounds(200, 190, 150, 30);
		if (!create)
			if (dividedModel[4].equals("lato"))
				checkBoxSeasonOfUse.setSelected(true);
		dialogCreateModel.add(checkBoxSeasonOfUse);

		labelPrice = new JLabel("Cena za dzien:");
		labelPrice.setBounds(20, 230, 180, 30);
		dialogCreateModel.add(labelPrice);
		textFieldPrice = new JTextField();
		textFieldPrice.setBounds(200, 230, 150, 30);
		if (!create)
			textFieldPrice.setText(dividedModel[5]);
		dialogCreateModel.add(textFieldPrice);

		labelDeposit = new JLabel("Kaucja za zniszczenie:");
		labelDeposit.setBounds(20, 270, 180, 30);
		dialogCreateModel.add(labelDeposit);
		textFieldDeposit = new JTextField();
		textFieldDeposit.setBounds(200, 270, 150, 30);
		if (!create)
			textFieldDeposit.setText(dividedModel[6]);
		dialogCreateModel.add(textFieldDeposit);

		buttonCancel = new JButton("Anuluj");
		buttonCancel.setBounds(260, 320, 180, 30);
		dialogCreateModel.add(buttonCancel);

		buttonCreateModel = new JButton("Utworz");
		buttonCreateModel.setBounds(20, 320, 180, 30);
		dialogCreateModel.add(buttonCreateModel);

	}

	private void initListeners() {

		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialogCreateModel.dispose();
			}
		});

		buttonCreateModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (create)
					createModel();
				else
					editModel();
			}
		});
	}

	private void createModel() {
		Connect oracle = new Connect();
		String model_ID = textFieldModelID.getText();
		String model_name = textFieldModelName.getText();
		String producer = textFieldProducer.getText();
		String equipment_type = textFieldEquipmentType.getText();
		boolean season_of_use = checkBoxSeasonOfUse.isSelected();
		String price = textFieldPrice.getText();
		String deposit = textFieldDeposit.getText();

		if (model_name.equals(""))
			JOptionPane.showMessageDialog(dialogCreateModel, "Pole nazwa modelu nie moze byc puste");
		else if (producer.equals(""))
			JOptionPane.showMessageDialog(dialogCreateModel, "Pole producent nie moze byc puste");
		else if (equipment_type.equals(""))
			JOptionPane.showMessageDialog(dialogCreateModel, "Pole sezon uzytkowy nie moze byc puste");
		else if (price == null)
			JOptionPane.showMessageDialog(dialogCreateModel, "Pole cena za dzien nie moze byc puste");
		else if (deposit == null)
			JOptionPane.showMessageDialog(dialogCreateModel, "Pole kaucja za zniszczenie nie moze byc puste");

		else {
			for (Model m : arrayListModels)
				if (Integer.toString(m.getModelID()).equals(model_ID)) {
					JOptionPane.showMessageDialog(dialogCreateModel, "Model o podanym ID znajduje sie juz w bazie");
					return;
				}
			int modelID2 = Integer.parseInt(textFieldModelID.getText());
			int costPerDay = Integer.parseInt(textFieldPrice.getText());
			int damageDeposit = Integer.parseInt(textFieldDeposit.getText());
			int season_of_use2 = season_of_use ? 1 : 0;

			try {
				oracle.db_connect();
				oracle.db_createModel(modelID2, model_name, producer, equipment_type, season_of_use2, costPerDay,
						damageDeposit);
				oracle.db_disconnect();
			} catch (Exception ex) {
			}

			arrayListModels.add(new Model(modelID2, model_name, producer, equipment_type, season_of_use, costPerDay,
					damageDeposit));
			JOptionPane.showMessageDialog(dialogCreateModel, "Dodano model");

			dialogCreateModel.dispose();
		}
	}

	private void editModel() {
		String model_ID = textFieldModelID.getText();
		String model_name = textFieldModelName.getText();
		String producer = textFieldProducer.getText();
		String equipment_type = textFieldEquipmentType.getText();
		boolean season_of_use = checkBoxSeasonOfUse.isSelected();
		String price = textFieldPrice.getText();
		String deposit = textFieldDeposit.getText();

		if (model_name.equals(""))
			JOptionPane.showMessageDialog(dialogCreateModel, "Pole nazwa modelu nie moze byc puste");
		else if (producer.equals(""))
			JOptionPane.showMessageDialog(dialogCreateModel, "Pole producent nie moze byc puste");
		else if (equipment_type.equals(""))
			JOptionPane.showMessageDialog(dialogCreateModel, "Pole sezon uzytkowy nie moze byc puste");
		else if (price == null)
			JOptionPane.showMessageDialog(dialogCreateModel, "Pole cena za dzien nie moze byc puste");
		else if (deposit == null)
			JOptionPane.showMessageDialog(dialogCreateModel, "Pole kaucja za zniszczenie nie moze byc puste");
		else
			try {
				int costPerDay = Integer.parseInt(textFieldPrice.getText());
				int damageDeposit = Integer.parseInt(textFieldDeposit.getText());

				model.setModelName(model_name);
				model.setProducer(producer);
				model.setEquipmentType(equipment_type);
				model.setSeason(season_of_use);
				model.setCostPerDay(costPerDay);
				model.setDamageDeposit(damageDeposit);

				dialogCreateModel.dispose();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(dialogCreateModel, "Pole z ceną lub kaucją zawiera błędne dane!");
			}
	}
}
