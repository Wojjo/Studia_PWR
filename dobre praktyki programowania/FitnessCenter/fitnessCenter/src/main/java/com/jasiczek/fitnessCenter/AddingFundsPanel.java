package com.jasiczek.fitnessCenter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.SQLConnection;
import models.CourseModel;

import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
@Deprecated
public class AddingFundsPanel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame addingFundsPanel;
	private SQLConnection sql = new SQLConnection();
	private JButton btnAddFunds;
	//private JPanel contentPane;
	private JTextField textFieldFunds;
	private JLabel lblTotalBalanceTitle;
	private JLabel lblTotalBalance;

	public AddingFundsPanel(long customerId)
	{
		super();
		initComponents(customerId);
		addingFundsPanel.setVisible(true);
		
	}
	
	private void initComponents(long customerId) {
		addingFundsPanel = new JFrame("Panel dodawania środków");
		addingFundsPanel.setBounds(100, 100, 531, 284);
		addingFundsPanel.setResizable(false);
		addingFundsPanel.setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblTopUpAmout = new JLabel("Kwota doładowania:");
		lblTopUpAmout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTopUpAmout.setBounds(44, 83, 206, 16);
		addingFundsPanel.add(lblTopUpAmout);
		
		btnAddFunds = new JButton("Dodaj środki");
		btnAddFunds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sql.addFundsToYourAccount(customerId, Long.parseLong(textFieldFunds.getText()));
			}
		});
		btnAddFunds.setBounds(198, 174, 124, 35);
		addingFundsPanel.add(btnAddFunds);
		
		textFieldFunds = new JTextField();
		textFieldFunds.setBounds(44, 103, 241, 28);
		addingFundsPanel.add(textFieldFunds);
		textFieldFunds.setColumns(10);
		
		lblTotalBalanceTitle = new JLabel("Łączne środki:");
		lblTotalBalanceTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalBalanceTitle.setBounds(43, 25, 118, 16);
		addingFundsPanel.add(lblTotalBalanceTitle);
		
		lblTotalBalance = new JLabel("");
		lblTotalBalance.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotalBalance.setBounds(137, 25, 77, 16);
		lblTotalBalance.setText(Long.toString(sql.getTotalBalance(customerId)) + " zł" ); 
		addingFundsPanel.add(lblTotalBalance);
	}

}
