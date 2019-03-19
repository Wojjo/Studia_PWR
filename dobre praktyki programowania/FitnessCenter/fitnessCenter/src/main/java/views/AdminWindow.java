package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controllers.AdminPanelController;
import models.CourseModel;
import models.Worker;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;


/**
 * Admin view
 *
 */
public class AdminWindow extends JFrame
{

	private AdminPanelController adminController;
	private Worker loggedWorker;

	/** Content pane*/
	private JPanel contentPane;
	/** Buttons on courses panel*/
	private MyPanel panelCoursesButtons;
	/** Return button*/
	private JButton btnBackToMain;
	/** Add new course button*/
	private JButton btnAddNewCourse;

	public AdminWindow(AdminPanelController adminController)
	{
		this.adminController = adminController;
		loggedWorker = adminController.getLoggedWorker();

		initComponents();

		initListeners();
	}

	/**
	 * Method that loads window's components
	 */
	public void initComponents()
	{

		setTitle("Admin Panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 420);
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

		JLabel fixLabelFriday = new JLabel("Friday");
		fixLabelFriday.setBounds(12, 254, 56, 16);
		contentPane.add(fixLabelFriday);

		JLabel fixLabelSaturday = new JLabel("Saturday");
		fixLabelSaturday.setBounds(12, 293, 56, 16);
		contentPane.add(fixLabelSaturday);

		JLabel fixLabelSunday = new JLabel("Sunday");
		fixLabelSunday.setBounds(12, 331, 56, 16);
		contentPane.add(fixLabelSunday);

		JLabel fixLabelThursday = new JLabel("Thursday");
		fixLabelThursday.setBounds(12, 216, 56, 16);
		contentPane.add(fixLabelThursday);

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
		btnAddNewCourse.setBounds(559, 32, 140, 25);
		contentPane.add(btnAddNewCourse);
		
		JLabel fixLabelWorkerName = new JLabel("Logged as: "+loggedWorker.getName()+" "+loggedWorker.getSurname()
		+"  id: "+loggedWorker.getId());
		fixLabelWorkerName.setFont(new Font("Tahoma", Font.BOLD, 13));
		fixLabelWorkerName.setBounds(105, 3, 398, 16);
		contentPane.add(fixLabelWorkerName);
	}

	/**
	 * Method that enables admin to add new courses
	 */
	private void initListeners()
	{
		btnBackToMain.addActionListener((e) -> {
			adminController.backToMainMenu();
		});

		btnAddNewCourse.addActionListener((e) -> {
			MyJDialogCourseCreation dialog = new MyJDialogCourseCreation(getLocation(), null);
			dialog.setVisible(true);
		});
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
			CourseModel[][] courses = adminController.getCourses();

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
						if (course.getCourseType().equals("Strength"))
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

	/**
	 * 
	 * Course dialog window
	 *
	 */
	class MyJDialogCourseInfo extends JDialog
	{
		private JTextField textFieldCourseName;
		private JTextField textFieldAdmissionFee;
		private JTextField textFieldNumOfMembers;

		private JComboBox<String> comboBoxHours;
		private JComboBox<String> comboBoxDayOfWeek;

		/**Cancel button */
		private JButton btnCancel;
		/**Modify button */
		private JButton btnModifyCourse;

		private CourseModel courseModel;

		private JRadioButton radioFitness;
		private JRadioButton radioStrength;

		private ButtonGroup buttonGroup = new ButtonGroup();
		
		
		private int day = 0;
		private int hour = 0;

		public MyJDialogCourseInfo(Point position, CourseModel courseModel)
		{
			super();

			this.courseModel = courseModel;

			initComponents(position);
			initListeners();
		}

		/**
		 * Method that loads window's components
		 */
		private void initComponents(Point position)
		{
			this.setModalityType(ModalityType.MODELESS);
			this.setTitle("Course info");
			getContentPane().setLayout(null);
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
			comboBoxHours.setSelectedIndex(courseModel.getCourseHour());
			hour = courseModel.getCourseHour();

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
			comboBoxDayOfWeek.setSelectedIndex(courseModel.getCourseDay());
			day = courseModel.getCourseDay();

			radioFitness = new JRadioButton("Fitness");
			radioFitness.setBounds(90, 38, 116, 22);
			getContentPane().add(radioFitness);
			radioFitness.setSelected(true);
			buttonGroup.add(radioFitness);

			radioStrength = new JRadioButton("Strength");
			radioStrength.setBounds(207, 38, 116, 22);
			getContentPane().add(radioStrength);
			buttonGroup.add(radioStrength);
			
			if(courseModel.getCourseType().equals("Strength"))
				radioStrength.setSelected(true);

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
			textFieldCourseName.setText(courseModel.getCourseName());

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
			textFieldAdmissionFee.setText(courseModel.getAdmissionFee()+"");

			textFieldNumOfMembers = new JTextField();
			textFieldNumOfMembers.setColumns(10);
			textFieldNumOfMembers.setBounds(174, 170, 116, 22);
			getContentPane().add(textFieldNumOfMembers);
			textFieldNumOfMembers.setText(courseModel.getMembersAmount()+"");

			btnCancel = new JButton("Cancel");
			btnCancel.setBounds(241, 216, 97, 25);
			getContentPane().add(btnCancel);

			btnModifyCourse = new JButton("Modify");
			btnModifyCourse.setBounds(41, 216, 97, 25);
			getContentPane().add(btnModifyCourse);
		}

		private void initListeners()
		{
			btnCancel.addActionListener((e) -> {
				this.dispose();
			});

			btnModifyCourse.addActionListener((e) -> {
				
				adminController.deleteCourse(day, hour);

				CourseModel courseToCreate = new CourseModel();
				courseToCreate.setCourseName(textFieldCourseName.getText());
				courseToCreate.setCourseDay(comboBoxDayOfWeek.getSelectedIndex());
				courseToCreate.setCourseHour(comboBoxHours.getSelectedIndex());
				System.out.println(courseToCreate.getCourseDay() + "\t" + courseToCreate.getCourseHour());
				courseToCreate.setAdmissionFee(Integer.parseInt(textFieldAdmissionFee.getText()));
				courseToCreate.setMembersAmount(Integer.parseInt(textFieldNumOfMembers.getText()));
				courseToCreate.setCourseLeader(loggedWorker.getName()+" "+loggedWorker.getSurname());

				if (radioFitness.isSelected())
					courseToCreate.setCourseType("Fitness");
				else
					courseToCreate.setCourseType("Strength");

				adminController.addCourse(courseToCreate);

				this.dispose();

			});
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
		
		/**
		 * Method that loads window's components
		 */
		private void initComponents(Point position)
		{
			this.setModalityType(ModalityType.MODELESS);
			this.setTitle("Course info");
			getContentPane().setLayout(null);
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
				courseToCreate.setCourseLeader(loggedWorker.getName()+" "+loggedWorker.getSurname());

				if (radioFitness.isSelected())
					courseToCreate.setCourseType("Fitness");
				else
					courseToCreate.setCourseType("Strength");

				adminController.addCourse(courseToCreate);

				this.dispose();

			});
		}
	}
}
