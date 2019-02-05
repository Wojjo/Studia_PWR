package studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.app;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database.Person;

public class RemindPassword {
	MainWindow mainWindow;
	
	JDialog dialogRemindPassword;
	JLabel labelLogin;
	JTextField textFieldLogin;
	JButton buttonRemindToThisLogin;
	JLabel labelSecurityQuestion;
	JLabel labelSecurityAnswer;
	JTextField textFieldSecurityAnswer;
	JButton buttonSendAnswer;
	JLabel labelRemindedPassword;
	JButton buttonClose;
	
	public RemindPassword(){
	}
	
	public RemindPassword(MainWindow mainWindow){
		this.mainWindow = mainWindow;
		
		initComponents();
		initListeners();

		dialogRemindPassword.setVisible(true);
	}
	
	private void initComponents(){
		dialogRemindPassword = new JDialog();
		dialogRemindPassword.setTitle("Zapomniales hasla?");
		dialogRemindPassword.setSize(new Dimension(330,360));
		dialogRemindPassword.setResizable(false);
		dialogRemindPassword.setModal(true);
		dialogRemindPassword.setLayout(null);
		dialogRemindPassword.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		labelLogin = new JLabel("Login:");
		labelLogin.setBounds(30, 30, 150, 30);
		dialogRemindPassword.add(labelLogin);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(130, 30, 150, 30);
		dialogRemindPassword.add(textFieldLogin);
		
		buttonRemindToThisLogin = new JButton("Prześlij login");
		buttonRemindToThisLogin.setBounds(90, 70, 150, 30);
		dialogRemindPassword.add(buttonRemindToThisLogin);
		
		labelSecurityQuestion = new JLabel();
		labelSecurityQuestion.setBounds(30, 110, 350, 30);
		dialogRemindPassword.add(labelSecurityQuestion);
		
		labelSecurityAnswer = new JLabel("Odpowiedz:");
		labelSecurityAnswer.setBounds(30, 150, 150, 30);
		dialogRemindPassword.add(labelSecurityAnswer);
		
		textFieldSecurityAnswer = new JTextField();
		textFieldSecurityAnswer.setBounds(130, 150, 150, 30);
		dialogRemindPassword.add(textFieldSecurityAnswer);
		
		buttonSendAnswer = new JButton("Przypomnij");
		buttonSendAnswer.setBounds(90, 190, 150, 30);
		buttonSendAnswer.setEnabled(false);
		dialogRemindPassword.add(buttonSendAnswer);
		
		labelRemindedPassword = new JLabel();
		labelRemindedPassword.setBounds(30, 230, 350, 30);
		dialogRemindPassword.add(labelRemindedPassword);
		
		buttonClose = new JButton("Zamknij");
		buttonClose.setBounds(90, 270, 150, 30);
		dialogRemindPassword.add(buttonClose);
	}
	
	private void initListeners(){
		buttonRemindToThisLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String login = textFieldLogin.getText();
				boolean found_login = false;
				
				for(Person c : mainWindow.getArrayListPeople()){
					if(c.getLogin().equals(login)){
						labelSecurityQuestion.setText("Pytanie: " + c.getSecurityQuestion());
						found_login = true;
						buttonSendAnswer.setEnabled(true);
						break;
					}
				}
				
				if(!found_login)
					labelSecurityQuestion.setText("Nie znaleziono konta o danym loginie!");
			}
		});
		
		buttonSendAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String answer = textFieldSecurityAnswer.getText();
				boolean correct_answer = false;
				
				for(Person c : mainWindow.getArrayListPeople()){
					if(c.getSecurityAnswer().equals(answer)){
						labelRemindedPassword.setText("Haslo: " + c.getPassword());
						correct_answer = true;
						break;
					}
				}
				
				if(!correct_answer)
					labelRemindedPassword.setText("Zla odpowiedz!");
			}
		});
		
		buttonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialogRemindPassword.dispose();
			}
		});
	}
}
