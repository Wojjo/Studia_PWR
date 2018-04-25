/* Klasa CompareCollectibles
 * Autor Przemyslaw Wojcinowicz
 * Data 23.11.2016 
*/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.*;

public class CompareCollectibles 
extends JFrame implements ActionListener
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Vector<String> vector = new Vector<String>();
	LinkedList<String> linkedList = new LinkedList<String>();
	ArrayList<String> arrayList = new ArrayList<String>();
	HashSet<String> hashSet = new HashSet<String>();
	TreeSet<String> treeSet = new TreeSet<String>();
	
	JLabel title = new JLabel ("Tytul: ");
	JTextField data = new JTextField(8);
	
	JButton author = new JButton ("Autor: ");
	JButton add = new JButton("Dodaj: "); 
	JButton remove =  new JButton ("Usun: ");
	JButton removeAll = new JButton ("Wyczysc: ");
	JButton sort =  new JButton ("Sortuj: ");
	
	
	ViewCollectibles viewVector;
	ViewCollectibles viewArrayList;
	ViewCollectibles viewLinkedList;
	ViewCollectibles viewHashSet;
	ViewCollectibles viewTreeSet;
	
	
	CompareCollectibles()
	{
		super("Zadanie 3 - dzialanie kolekcji");
		JPanel panel = new JPanel();
		
		setSize(700,500);
		panel.add(this.author);
		panel.add(this.title);
		panel.add(this.data);
		panel.add(this.add);
		panel.add(this.remove);
		panel.add(this.removeAll);
		panel.add(this.sort);
		
		this.author.addActionListener(this);
		this.add.addActionListener(this);
		this.remove.addActionListener(this);
		this.removeAll.addActionListener(this);
		this.sort.addActionListener(this);
		
		
		this.viewVector = new ViewCollectibles(this.vector, 150, 180, "vector:");
		 panel.add(this.viewVector);
		 
		 this.viewLinkedList = new ViewCollectibles(this.linkedList, 150, 180, "linkedList:");
	     panel.add(this.viewLinkedList);
	     
	     this.viewArrayList = new ViewCollectibles(this.arrayList, 150, 180, "arrayList:");
	     panel.add(this.viewArrayList);
	     
	     this.viewHashSet = new ViewCollectibles(this.hashSet, 150, 180, "hashSet:");
	     panel.add(this.viewHashSet);
	        
	     this.viewTreeSet = new ViewCollectibles(this.treeSet, 150, 180, "treeSet:");
	     panel.add(this.viewTreeSet);
	     
	     setContentPane(panel);  
	     setVisible(true);
		 	
	}
	
	
	public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        
        String text;
        if (source == author)
        {
            JOptionPane.showMessageDialog(this, "Przemyslaw Wojcinowicz \n" + " 23.11.2016 \n" + "Index number: 225943");
        }
        if(source==this.add)
        {
        	text = this.data.getText();
        	if (!text.equals(""))
       	{ 
        		this.vector.add(text);
        		this.arrayList.add(text);
        		this.linkedList.add(text);
        		this.hashSet.add(text);
        		this.treeSet.add(text);
        }    
        }
        
         if (source == this.removeAll)
        {
        	 this.vector.clear();
        	 this.arrayList.clear();
        	 this.linkedList.clear();
        	 this.hashSet.clear();
        	 this.treeSet.clear();  
        } 
         if (source == this.remove)
        {
        	text = this.data.getText();
        	this.vector.remove(text);
        	this.arrayList.remove(text);
        	this.linkedList.remove(text);
        	this.hashSet.remove(text);
        	this.treeSet.remove(text);
        }
         if (source == this.sort) 
        {
            Collections.sort(this.vector);
            Collections.sort(this.arrayList);
            Collections.sort(this.linkedList);
        }
      
    
        Iterator<String> iterator;
        this.viewVector.remove();
        this.viewArrayList.remove();
        this.viewLinkedList.remove();
        this.viewHashSet.remove();
        this.viewTreeSet.remove();
        iterator = this.hashSet.iterator();
        while(iterator.hasNext())
        { 
        	text=(String)iterator.next();
        	this.viewHashSet.addData(text); 
        }
        iterator = this.treeSet.iterator();
        while(iterator.hasNext())
        { 
        	text=(String)iterator.next();
        	this.viewTreeSet.addData(text); 
        }

            iterator = this.vector.iterator();
            while(iterator.hasNext())
            {   
            	text=(String)iterator.next();
            	this.viewVector.addData(text); 
            }
            
            iterator = this.arrayList.iterator();
            while(iterator.hasNext())
            { 
            	text=(String)iterator.next();
            	this.viewArrayList.addData(text); 
            }
            
            iterator = this.linkedList.iterator();
            while(iterator.hasNext())
            { 
            	text=(String)iterator.next();
            	this.viewLinkedList.addData(text); 
            }
            
        }
        
	

	 public static void main(String[] args) 
	 {
	        new CompareCollectibles();
	 }
	
	
}

