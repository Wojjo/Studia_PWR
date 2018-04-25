import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GraphicApp extends JFrame implements ActionListener
{
	protected Picture picture;
	protected Stoper stoper;
	
	private JButton resetButton = new JButton("Reset");
	private JButton autorButton = new JButton("Autor");
	private JButton exitButton = new JButton("Exit");
	private JButton upButton = new JButton("Gora");
	
	JTextField firstText = new JTextField(5);
	
	JMenu [] menu = 
		{
				new JMenu("Menu"),
				new JMenu("Info")
				
		};
	
	JMenuItem [] items = 
		{
				new JMenuItem("Zakoncz"),
				new JMenuItem("Autor")
		};
	
	public GraphicApp()
	{
		setSize(500,500);
		setTitle("Przemon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		picture = new Picture();
		stoper = new Stoper();
		
		JMenuBar menuBar = new JMenuBar();
		
		setJMenuBar(menuBar);
		menuBar.add(menu[0]);
		menuBar.add(menu[1]);
		
		
		for(int i =0; i < items.length; i++)
		{
			items[i].addActionListener(this);
		}
		menu[0].add(items[0]);
		menu[1].add(items[1]);
		
		stoper.start();
		
		picture.addMouseListener(picture);
		picture.addMouseMotionListener(picture);
		picture.addKeyListener(picture);
		
		picture.setFocusable(true);
		
		resetButton.addActionListener(this);
		autorButton.addActionListener(this);
		exitButton.addActionListener(this);
		upButton.addActionListener(this);
		
		firstText.setEditable(false);
		
		picture.add(firstText);
		picture.add(resetButton);
		picture.add(autorButton);
		picture.add(exitButton);
		
		
		setContentPane(picture);
		setVisible(true);
		showStoper();
	}
	
	public void showStoper()
	{
		while(true)
		{
			firstText.setText(stoper.toString());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source == autorButton) JOptionPane.showMessageDialog(null, picture.autor(), "Autor", 1);
		if(source == resetButton) stoper.reset();
		if(source == exitButton) System.exit(0);
		
		if(source == items[0]) System.exit(0);
		if(source == items[1]) JOptionPane.showMessageDialog(null, picture.autor(), "Autor", 1);
		
	}
	
	
	public static void main(String[] args)
	{
		GraphicApp app = new GraphicApp();
	}


	

}
