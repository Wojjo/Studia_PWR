package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jasiczek.fitnessCenter.AddingFundsPanel;

import controllers.AdminPanelController;
import controllers.UserPanelController;
import database.SQLConnection;
import models.CourseModel;
import views.AdminWindow.MyJDialogCourseCreation;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class UserWindow extends JFrame
{

	private UserPanelController userController;

	private JPanel contentPane;
	private MyPanel panelCoursesButtons;
	private JButton btnBackToMain;
	private JButton payIn;
	private static SQLConnection sql = new SQLConnection();
	private JButton btnAddNewCourse;
	public static long balance;

	public UserWindow(UserPanelController userController)
	{
		this.userController = userController;

		initComponents();

		initListeners();
	}

	public void initComponents()
	{

		setTitle("Customer Panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 990, 620);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setVisible(true);

		panelCoursesButtons = new MyPanel();
		panelCoursesButtons.setBounds(80, 93, 707, 268);
		contentPane.add(panelCoursesButtons);

		JLabel fixLabelMonday = new JLabel("Monday");
		fixLabelMonday.setBounds(12, 106, 56, 16);
		contentPane.add(fixLabelMonday);

		JLabel fixLabelTuesday = new JLabel("Tuesday");
		fixLabelTuesday.setBounds(12, 144, 56, 16);
		contentPane.add(fixLabelTuesday);

		JLabel fixLabelWednesday = new JLabel("Wednesday");
		fixLabelWednesday.setBounds(12, 178, 67, 16);
		contentPane.add(fixLabelWednesday);
		
		JLabel fixLabelThursday = new JLabel("Thursday");
		fixLabelThursday.setBounds(12, 216, 56, 16);
		contentPane.add(fixLabelThursday);

		JLabel fixLabelFriday = new JLabel("Friday");
		fixLabelFriday.setBounds(12, 254, 56, 16);
		contentPane.add(fixLabelFriday);

		JLabel fixLabelSaturday = new JLabel("Saturday");
		fixLabelSaturday.setBounds(12, 293, 56, 16);
		contentPane.add(fixLabelSaturday);

		JLabel fixLabelSunday = new JLabel("Sunday");
		fixLabelSunday.setBounds(12, 331, 56, 16);
		contentPane.add(fixLabelSunday);



		JLabel fixLabel8_10 = new JLabel("        8-10");
		fixLabel8_10.setBounds(94, 70, 67, 16);
		contentPane.add(fixLabel8_10);

		JLabel fixLabel10_12 = new JLabel("      10-12");
		fixLabel10_12.setBounds(194, 70, 67, 16);
		contentPane.add(fixLabel10_12);

		JLabel fixLabel12_14 = new JLabel("      12-14");
		fixLabel12_14.setBounds(292, 70, 67, 16);
		contentPane.add(fixLabel12_14);

		JLabel fixLabel14_16 = new JLabel("     14-16");
		fixLabel14_16.setBounds(394, 70, 56, 16);
		contentPane.add(fixLabel14_16);

		JLabel fixLabel16_18 = new JLabel("       16-18");
		fixLabel16_18.setBounds(494, 70, 67, 16);
		contentPane.add(fixLabel16_18);

		JLabel fixLabel18_20 = new JLabel("    18-20");
		fixLabel18_20.setBounds(594, 70, 56, 16);
		contentPane.add(fixLabel18_20);

		JLabel fixLabel20_22 = new JLabel("    20-22");
		fixLabel20_22.setBounds(694, 70, 56, 16);
		contentPane.add(fixLabel20_22);

		btnBackToMain = new JButton("Back to main Screen");
		btnBackToMain.setBounds(57, 32, 162, 25);
		contentPane.add(btnBackToMain);
		
		btnAddNewCourse = new JButton("Add new Course");
		btnAddNewCourse.setBounds(359, 32, 140, 25);
		contentPane.add(btnAddNewCourse);

		payIn = new JButton("Deposit money");
		payIn.setBounds(559, 32, 140, 25);
		contentPane.add(payIn);
		
		balance = (sql.getTotalBalance(5));
		JLabel accountBalance = new JLabel();
		accountBalance.setBounds(750, 32, 140, 25);
		accountBalance.setText("Account balance: " + Long.toString(balance) + " zł" ); 
		contentPane.add(accountBalance);
		
	}

	private void initListeners()
	{
		btnBackToMain.addActionListener((e) -> {
			userController.backToMainMenu();
		});

		payIn.addActionListener((e) -> {
			new AddingFundsPanel(5);
		});
		
		
		btnAddNewCourse.addActionListener((e) -> {
			MyJDialogCourseCreation dialog = new MyJDialogCourseCreation(getLocation(), null);
			dialog.setVisible(true);
		});
		
	}
	
	
	public static void payForCouse(int cost, int customerId)
	{
		balance = (sql.getTotalBalance(5));
		balance = balance - cost;
		System.out.println(balance);
		sql.removeFundsToYourAccount(customerId, balance);
	}

	public void updateVisuals()
	{
		panelCoursesButtons.update();
	}

	public void closeWindows()
	{
		this.dispose();
	}

	class MyPanel extends JPanel
	{

		private JButton[][] buttonsArray = new JButton[7][7];

		public MyPanel()
		{
			super();

			this.setLayout(null);

			update();

		}

		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);

			for (int i = 0; i < 8; i++)
			{
				g.drawLine(0, 38 * i, 700, 38 * i);
				g.drawLine(100 * i, 0, 100 * i, 280);
			}
		};

		public void update()
		{
			CourseModel[][] courses = userController.getCourses();

			for (int i = 0; i < 7; i++)
			{
				for (int j = 0; j < 7; j++)
				{
					if (buttonsArray[i][j] != null)
						buttonsArray[i][j].setVisible(false);

					if (courses[i][j] != null)
					{
						CourseModel course = courses[i][j];

						JButton button = new JButton();
						button.setBounds(i * 100, j * 38, 100, 38);
						this.add(button);
						buttonsArray[i][j] = button;
						button.setText(course.getCourseName());
						if (course.getCourseType() == "Strength")
							button.setBackground(Color.RED);
						else
							button.setBackground(Color.GREEN);

						button.addActionListener((e) -> {
							MyJDialogCourseInfo dialog = new MyJDialogCourseInfo(this.getLocation(), course);
							dialog.setVisible(true);

						});
					}

				}
			}

			this.repaint();
		}

	}

	class MyJDialogCourseInfo extends JDialog
	{
		private JTextField textFieldCourseName;
		private JTextField textFieldAdmissionFee;
		private JTextField textFieldNumOfMembers;
		private JLabel hourLabel;
		private JLabel dayLabel;
		private JComboBox<String> comboBoxHours;
		private JComboBox<String> comboBoxDayOfWeek;
		private JButton btnCancel;
		private JButton btnSingUp;
		private CourseModel courseModel;
		private JRadioButton radioFitness;
		private JRadioButton radioStrength;
		private ButtonGroup buttonGroup = new ButtonGroup();
		private int numberOfMembers;
		public int cost;
		private int index, index2;
		

		public MyJDialogCourseInfo(Point position, CourseModel courseModel)
		{
			super();

			this.courseModel = courseModel;

			initComponents(position);
			initListeners();
		}

		private void initComponents(Point position)
		{
			CourseModel course = new CourseModel();
			this.setModalityType(ModalityType.MODELESS);
			this.setTitle("Course info");
			this.setLayout(null);
			this.setBounds(position.x + 230, position.y + 100, 400, 300);
			index = course.getCourseHour();
			
			hourLabel = new JLabel();
			hourLabel.setBounds(185, 104, 96, 22);
			getContentPane().add(hourLabel);	
			if(index==0)
			{
				hourLabel.setText("8-10");
			}
			if(index==1)
			{
				hourLabel.setText("10-12");
			}
			if(index==2)
			{
				hourLabel.setText("12-14");
			}
			if(index==3)
			{
				hourLabel.setText("14-16");
			}
			if(index==4)
			{
				hourLabel.setText("16-18");
			}
			if(index==5)
			{
				hourLabel.setText("18-20");
			}
			if(index==6)
			{
				hourLabel.setText("20-22");
			}
			index2 = course.getCourseDay();
			dayLabel = new JLabel();
			dayLabel.setBounds(185, 65, 96, 22);
			getContentPane().add(dayLabel);
			
			if(index2==0)
			{
				dayLabel.setText("Monday");
			}
			if(index2==1)
			{
				dayLabel.setText("Tuesday");
			}
			if(index2==2)
			{
				dayLabel.setText("Wendnesday");
			}
			if(index2==3)
			{
				dayLabel.setText("Thrusday");
			}
			if(index2==4)
			{
				dayLabel.setText("Friday");
			}
			if(index2==5)
			{
				dayLabel.setText("Saturday");
			}
			if(index2==6)
			{
				dayLabel.setText("Sunday");
			}

			radioFitness = new JRadioButton("Fitness");
			radioFitness.setBounds(90, 38, 116, 22);
			getContentPane().add(radioFitness);
			radioFitness.setSelected(true);
			radioFitness.setFocusable(false);
			buttonGroup.add(radioFitness);

			radioStrength = new JRadioButton("Strength");
			radioStrength.setBounds(207, 38, 116, 22);
			radioStrength.setFocusable(false);
			getContentPane().add(radioStrength);
			buttonGroup.add(radioStrength);

			JLabel fixLabelDayOfWeek = new JLabel("Day of the week:");
			fixLabelDayOfWeek.setBounds(41, 68, 133, 16);
			fixLabelDayOfWeek.setFocusable(false);
			getContentPane().add(fixLabelDayOfWeek);

			JLabel fixLabelHours = new JLabel("Hours: ");
			fixLabelHours.setBounds(101, 107, 133, 16);
			fixLabelHours.setFocusable(false);
			getContentPane().add(fixLabelHours);

			JLabel fixLabelCourseName = new JLabel("Course name:");
			fixLabelCourseName.setBounds(58, 15, 111, 16);
			fixLabelCourseName.setFocusable(false);
			getContentPane().add(fixLabelCourseName);

			textFieldCourseName = new JTextField();
			textFieldCourseName.setBounds(174, 15, 116, 22);
			textFieldCourseName.setEditable(false);
			getContentPane().add(textFieldCourseName);
			textFieldCourseName.setColumns(10);
			textFieldCourseName.setText(courseModel.getCourseName());

			JLabel fixLabelAdmissionFee = new JLabel("Admission fee:");
			fixLabelAdmissionFee.setBounds(58, 144, 86, 16);
			fixLabelAdmissionFee.setFocusable(false);
			getContentPane().add(fixLabelAdmissionFee);

			JLabel fixLabelNumOfMembers = new JLabel("Number of members:");
			fixLabelNumOfMembers.setBounds(23, 173, 133, 16);
			fixLabelNumOfMembers.setFocusable(false);
			getContentPane().add(fixLabelNumOfMembers);

			textFieldAdmissionFee = new JTextField();
			textFieldAdmissionFee.setColumns(10);
			textFieldAdmissionFee.setBounds(174, 141, 116, 22);
			textFieldAdmissionFee.setEditable(false);
			getContentPane().add(textFieldAdmissionFee);
			textFieldAdmissionFee.setText(courseModel.getAdmissionFee()+"");

			textFieldNumOfMembers = new JTextField();
			textFieldNumOfMembers.setColumns(10);
			textFieldNumOfMembers.setBounds(174, 170, 116, 22);
			textFieldNumOfMembers.setEditable(false);
			getContentPane().add(textFieldNumOfMembers);
			textFieldNumOfMembers.setText(courseModel.getMembersAmount()+"");

			btnCancel = new JButton("Cancel");
			btnCancel.setBounds(241, 216, 97, 25);
			getContentPane().add(btnCancel);

			btnSingUp = new JButton("Sing up");
			btnSingUp.setBounds(41, 216, 97, 25);
			getContentPane().add(btnSingUp);
		}

		private void initListeners()
		{
			btnCancel.addActionListener((e) -> {
				this.dispose();
			});

			btnSingUp.addActionListener((e) -> {
				numberOfMembers = Integer.parseInt(textFieldNumOfMembers.getText());
				numberOfMembers -= 1;
				
				if(numberOfMembers>=0)
				{
				CourseModel courseToCreate = new CourseModel();
				courseToCreate.setCourseName(textFieldCourseName.getText());
				courseToCreate.setCourseDay(index2);
				courseToCreate.setCourseHour(index);
				System.out.println(courseToCreate.getCourseDay() + "\t" + courseToCreate.getCourseHour());
				courseToCreate.setAdmissionFee(Integer.parseInt(textFieldAdmissionFee.getText()));
				courseToCreate.setMembersAmount(Integer.parseInt(textFieldNumOfMembers.getText()));
				
				
				cost = Integer.parseInt(textFieldAdmissionFee.getText());
				
				UserWindow.payForCouse(cost, 5);
				

				if (radioFitness.isSelected())
					courseToCreate.setCourseType("Strength");
				else
					courseToCreate.setCourseType("Fitness");

				userController.chooseCourse(courseToCreate);
				this.dispose();
				}else
				{
					System.out.println("Brak miejsc");
					this.dispose();
				}

			});
		}

	}
	
	

public class AddingFundsPanel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame addingFundsPanel;
	private SQLConnection sql = new SQLConnection();
	private JButton btnAddFunds;
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
				addingFundsPanel.dispose();
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
	
	
class MyJDialogCourseCreation extends JDialog
{
	private JTextField textFieldCourseName;
	private JTextField textFieldAdmissionFee;
	private JTextField textFieldNumOfMembers;

	private JComboBox<String> comboBoxHours;
	private JComboBox<String> comboBoxDayOfWeek;

	private JButton btnCancel;
	private JButton btnCreateCourse;

	private JRadioButton radioFitness;
	private JRadioButton radioStrength;

	private ButtonGroup buttonGroup = new ButtonGroup();

	public MyJDialogCourseCreation(Point position, String[] data)
	{
		super();

		initComponents(position);
		initListeners();
	}

	private void initComponents(Point position)
	{
		this.setModalityType(ModalityType.MODELESS);
		this.setTitle("Course info");
		this.setLayout(null);
		this.setBounds(position.x + 230, position.y + 100, 400, 300);

		comboBoxHours = new JComboBox<>();
		comboBoxHours.setBounds(185, 104, 96, 22);
		getContentPane().add(comboBoxHours);
		comboBoxHours.addItem("8-10");
		comboBoxHours.addItem("10-12");
		comboBoxHours.addItem("12-14");
		comboBoxHours.addItem("14-16");
		comboBoxHours.addItem("16-18");
		comboBoxHours.addItem("18-20");
		comboBoxHours.addItem("20-22");

		comboBoxDayOfWeek = new JComboBox<>();
		comboBoxDayOfWeek.setBounds(185, 65, 96, 22);
		getContentPane().add(comboBoxDayOfWeek);
		comboBoxDayOfWeek.addItem("Monday");
		comboBoxDayOfWeek.addItem("Tuesday");
		comboBoxDayOfWeek.addItem("Wendnesday");
		comboBoxDayOfWeek.addItem("Thrusday");
		comboBoxDayOfWeek.addItem("Friday");
		comboBoxDayOfWeek.addItem("Saturday");
		comboBoxDayOfWeek.addItem("Sunday");

		JLabel fixLabelDayOfWeek = new JLabel("Day of the week:");
		fixLabelDayOfWeek.setBounds(41, 68, 133, 16);
		getContentPane().add(fixLabelDayOfWeek);

		JLabel fixLabelHours = new JLabel("Hours: ");
		fixLabelHours.setBounds(101, 107, 133, 16);
		getContentPane().add(fixLabelHours);

		JLabel fixLabelCourseName = new JLabel("Course name:");
		fixLabelCourseName.setBounds(58, 15, 111, 16);
		getContentPane().add(fixLabelCourseName);

		textFieldCourseName = new JTextField();
		textFieldCourseName.setBounds(174, 15, 116, 22);
		getContentPane().add(textFieldCourseName);
		textFieldCourseName.setColumns(10);

		radioFitness = new JRadioButton("Fitness");
		radioFitness.setBounds(90, 38, 116, 22);
		getContentPane().add(radioFitness);
		radioFitness.setSelected(true);
		buttonGroup.add(radioFitness);

		radioStrength = new JRadioButton("Strength");
		radioStrength.setBounds(207, 38, 116, 22);
		getContentPane().add(radioStrength);
		buttonGroup.add(radioStrength);

		JLabel fixLabelAdmissionFee = new JLabel("Admission fee:");
		fixLabelAdmissionFee.setBounds(58, 144, 86, 16);
		getContentPane().add(fixLabelAdmissionFee);

		JLabel fixLabelNumOfMembers = new JLabel("Number of members:");
		fixLabelNumOfMembers.setBounds(23, 173, 133, 16);
		getContentPane().add(fixLabelNumOfMembers);

		textFieldAdmissionFee = new JTextField();
		textFieldAdmissionFee.setColumns(10);
		textFieldAdmissionFee.setBounds(174, 141, 116, 22);
		getContentPane().add(textFieldAdmissionFee);

		textFieldNumOfMembers = new JTextField();
		textFieldNumOfMembers.setColumns(10);
		textFieldNumOfMembers.setBounds(174, 170, 116, 22);
		getContentPane().add(textFieldNumOfMembers);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(241, 216, 97, 25);
		getContentPane().add(btnCancel);

		btnCreateCourse = new JButton("Create");
		btnCreateCourse.setBounds(41, 216, 97, 25);
		getContentPane().add(btnCreateCourse);
	}

	private void initListeners()
	{
		btnCancel.addActionListener((e) -> {
			this.dispose();
		});

		btnCreateCourse.addActionListener((e) -> {

			CourseModel courseToCreate = new CourseModel();
			courseToCreate.setCourseName(textFieldCourseName.getText());
			courseToCreate.setCourseDay(comboBoxDayOfWeek.getSelectedIndex());
			courseToCreate.setCourseHour(comboBoxHours.getSelectedIndex());
			System.out.println(courseToCreate.getCourseDay() + "\t" + courseToCreate.getCourseHour());
			courseToCreate.setAdmissionFee(Integer.parseInt(textFieldAdmissionFee.getText()));
			courseToCreate.setMembersAmount(Integer.parseInt(textFieldNumOfMembers.getText()));

			if (radioFitness.isSelected())
				courseToCreate.setCourseType("Fitness");
			else
				courseToCreate.setCourseType("Strength");

			userController.addCourse(courseToCreate);

			this.dispose();

		});
	}
}
	
	

	
}
