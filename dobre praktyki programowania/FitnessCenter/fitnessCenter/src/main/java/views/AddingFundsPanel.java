package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.AdminPanelController;
import database.SQLConnection;
import views.AdminWindow.MyJDialogCourseCreation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
/**
 * 
 * Panel that allows user to add funds
 *
 */
public class AddingFundsPanel extends JFrame {

	private static final long serialVersionUID = 1L;
	private SQLConnection sql = new SQLConnection();
	/**	Add funds button */
	private JButton btnAddFunds;
	/**	Cancel button */
	private JButton btnCancel;
	/**	 Content pane*/
	private JPanel contentPane;
	/**	Funds text field */
	private JTextField textFieldFunds;
	/**	 Total balance */
	private JLabel lblTotalBalanceTitle;
	/**	Total balance */
	private JLabel lblTotalBalance;
	
	public AddingFundsPanel(long customerId)
	{

		initComponents(customerId);

		initListeners(customerId);
	}
	
	private void initComponents(long customerId) {
		setTitle("Add funds panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 284);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(278, 174, 127, 35);
		contentPane.add(btnCancel);
		
		btnAddFunds = new JButton("Add funds");
		btnAddFunds.setBounds(89, 174, 124, 35);
		contentPane.add(btnAddFunds);
		
		textFieldFunds = new JTextField();
		textFieldFunds.setBounds(44, 103, 241, 28);
		contentPane.add(textFieldFunds);
		textFieldFunds.setColumns(10);
		
		JLabel lblTopUpAmout = new JLabel("Amount to top-up");
		lblTopUpAmout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTopUpAmout.setBounds(44, 83, 206, 16);
		contentPane.add(lblTopUpAmout);
		
		lblTotalBalanceTitle = new JLabel("Current walet balance:");
		lblTotalBalanceTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalBalanceTitle.setBounds(43, 25, 153, 16);
		contentPane.add(lblTotalBalanceTitle);
		
		lblTotalBalance = new JLabel("");
		lblTotalBalance.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotalBalance.setBounds(184, 25, 77, 16);
		lblTotalBalance.setText(Long.toString(sql.getTotalBalance(customerId)) + " zł" ); 
		contentPane.add(lblTotalBalance);
		
	}
	/**
	 * Method that collects amount given by client in textfield and passes it to database
	 * @param customerId
	 */
	private void initListeners(long customerId)
	{
		btnAddFunds.addActionListener((e) -> {
			if(Integer.parseInt(textFieldFunds.getText()) > 0) {
				sql.addFundsToYourAccount(customerId, Integer.parseInt(textFieldFunds.getText()));
			}else {
				JOptionPane.showMessageDialog(null,"Value must be higher than 0 zł");
			}
				
		});
		
		btnCancel.addActionListener((e) -> {
			this.dispose();
		});

	}
}
