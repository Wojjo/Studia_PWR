import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/*
 *  Klasa GraphicEditor
 *
 *  Autor: Przemyslaw Wojcinowicz
 *   Data: 8.11.2016
 */

/**
 * Klasa GraphicEditor
 * <br>
 *  
 *
 *Program demonstruje nastepujace zagadnienia:
 * <ul>
 *  <li> Klasa impementuje interfejs ActionListener oraz rozszerza klase JFrame,</li>
 *  <li> co w nastepstwie umozliwia obsluge zdarzen generowanych przez przyciski umieszczone w menu </li>
 *  <li> glownym aplikacji </li>
 * </ul>
 *
 * @author Przemyslaw Wojcinowicz
 * @version 9.11.2016 r 
 */
public class GraphicEditor extends JFrame implements ActionListener 
{
   
	private static final long serialVersionUID = 1L;


	public GraphicEditor()
    { 
    	
    	super("Edytor graficzny"); 
    	setSize(500,500);   
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        for (int i = 0; i < items.length; i++)
        	items[i].addActionListener(this);
        menu[0].add(items[0]); 
        menu[0].add(items[1]);
        menu[0].add(items[2]);
        menu[0].add(items[3]);
        menu[0].add(items[4]);
        menu[0].add(items[5]);
        menu[0].add(items[6]);
        menu[0].add(items[7]);
        menu[0].addSeparator();
        menu[0].add(items[8]);
        menu[1].add(items[9]); 
        menu[1].add(items[10]);
        menu[1].add(items[11]);
        menu[1].add(items[12]); 
        menu[1].addSeparator();
        menu[1].add(items[13]);
        menu[1].add(items[14]);
        menu[2].add(items[15]);
        menu[2].add(items[16]);
        menu[2].add(items[17]);
        menu[2].add(items[18]);
        menu[3].add(items[19]);
        menu[3].add(items[20]);
        
        JMenuBar menubar = new JMenuBar();
        for (int i = 0; i < menu.length; i++)
      		menubar.add(menu[i]);
      	setJMenuBar(menubar); 
      	
      	picture=new Picture(); 
      	picture.addKeyListener(picture); 
      	picture.addMouseListener(picture);
      	picture.addMouseMotionListener(picture);
      	picture.setFocusable(true);  
      	picture.setLayout(new FlowLayout(3));
      	pointButton.addActionListener(this);
      	triangleButton.addActionListener(this);
      	squareButton.addActionListener(this);
      	squareFilledButton.addActionListener(this);
      	rectangleButton.addActionListener(this);
      	rectangleFilledButton.addActionListener(this);
      	circleButton.addActionListener(this);
      	circleFilledButton.addActionListener(this);
      	picture.add(pointButton);
      	picture.add(triangleButton);
      	picture.add(squareButton);
      	picture.add(squareFilledButton);
      	picture.add(rectangleButton);
      	picture.add(rectangleFilledButton);
      	picture.add(circleButton);
      	picture.add(circleFilledButton);
      	
      	setContentPane(picture);
      	setVisible(true);
    } 
    protected Picture picture;
    private JButton pointButton   = new JButton ("Punkt");
    private JButton triangleButton = new JButton("Trojkat");
    private JButton squareButton = new JButton("Kwadrat");
    private JButton squareFilledButton = new JButton("Kwadrat wypelniony");
    private JButton rectangleButton = new JButton("Prostokat");
    private JButton rectangleFilledButton = new JButton("Prostokat Wypelniony");
    private JButton circleButton = new JButton("Kolo");
    private JButton circleFilledButton = new JButton("Kolo wypelnione");
      
    private JMenu[] menu = { new JMenu("Figury"), 
                             new JMenu("Edytuj"),
                             new JMenu("Koloruj"),
                             new JMenu("Pomoc") };
    private JMenuItem[] items = { new JMenuItem("Punkt"),  
                                  new JMenuItem("Trojkat"),
                                  new JMenuItem("Kwadrat"),
                                  new JMenuItem("Kwadrat wypelniony"),
                                  new JMenuItem("Prostokat"),
                                  new JMenuItem("Prostokat wypelniony"),
                                  new JMenuItem("Kolo"),
                                  new JMenuItem("Kolo wypelnione"),
                                  new JMenuItem("Wypisz wszystkie"),
                                  new JMenuItem("Przesun w gore"),
                                  new JMenuItem("Przesun w dol"),
                                  new JMenuItem("Przesun w lewo"),
                                  new JMenuItem("Przesun w prawo"),
                                  new JMenuItem("Powieksz"),
                                  new JMenuItem("Pomniejsz"),
                                  new JMenuItem("Koloruj na zielono"),
                                  new JMenuItem("Koloruj na niebiesko"),
                                  new JMenuItem("Koloruj na zolto"),
                                  new JMenuItem("Koloruj na czerwono"),
                                  new JMenuItem("Klawisze funkcyjne"),
                                  new JMenuItem("Autor") };


    public void actionPerformed (ActionEvent evt)
    { 
    	Object source = evt.getSource();
    	if (source==pointButton) picture.addPoint();
    	if (source==triangleButton) picture.addTriangle();
    	if (source==squareButton) picture.addjSquare();
    	if (source==squareFilledButton) picture.addjSquareFilled();
    	if (source==rectangleButton) picture.addRectangle();
    	if (source==rectangleFilledButton) picture.addRectangleFilled();
    	if (source==circleButton) picture.addCircle();
    	if (source==circleFilledButton) picture.addCircleFilled();
    	
    	if(source==items[0]) picture.addPoint();
    	if(source==items[1]) picture.addTriangle();
    	if(source==items[2]) picture.addjSquare();
    	if(source==items[3]) picture.addjSquareFilled();
    	if(source==items[4]) picture.addRectangle();
    	if(source==items[5]) picture.addRectangleFilled();
    	if(source==items[6]) picture.addCircle();
    	if(source==items[7]) picture.addCircleFilled();
    	if(source==items[8]) JOptionPane.showMessageDialog(null, picture.drawAll());
        
        if(source==items[9]) picture.moveAllFigures(0, -10);
        if(source==items[10]) picture.moveAllFigures( 10, 10);
        if(source==items[11]) picture.moveAllFigures( -10, 0);
        if(source==items[12]) picture.moveAllFigures( 10,  0);
        if(source==items[13]) picture.scaleAllFigures(1.1f);
        if(source==items[14]) picture.scaleAllFigures(0.9f);
        
        if(source==items[15]) picture.setColorGreen();
        if(source==items[16]) picture.setColorBlue();
        if(source==items[17]) picture.setColorYellow();
        if(source==items[18]) picture.setColorRed();
        if(source==items[19]) JOptionPane.showMessageDialog(null, picture.functionKeys());
        if(source==items[20]) JOptionPane.showMessageDialog(null, picture.autor());
        picture.requestFocus(); 
        repaint();
    }

    /** Metoda wywoluje okno Edytora */
	public static void main(String[] args) 
    {
    
		GraphicEditor editor = new GraphicEditor();
    } 
      
}
                    
                 