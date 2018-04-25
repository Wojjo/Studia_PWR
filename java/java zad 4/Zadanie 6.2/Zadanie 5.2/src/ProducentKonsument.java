/* 
 *  Klasa ProducentKonsument
 *
 *  Autor: Przemys³aw Wojcinowicz 225943
 *  Wersja: 3 koñcowa
 */

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.util.ArrayList;
import java.util.List;


public class ProducentKonsument
extends JFrame
implements ActionListener {
	
     static final long serialVersionUID = 1L;
     JPanel main = new JPanel();
     JMenuBar menulist = new JMenuBar();
     boolean play;
    
    List<Producent> producent = new ArrayList<Producent>();
    List<Konsument> konsument = new ArrayList<Konsument>();
    
    Animacja wizu = new Animacja();
    JTextArea text = new JTextArea();
    Bufor bufor;
    
    JMenu[] menu = new JMenu[]{new JMenu("Opcje"), new JMenu("Wiecej")};
    JMenuItem[] options = new JMenuItem[]{new JMenuItem("Koniec"), new JMenuItem("Info"), new JMenuItem("Wiecej"), new JMenuItem("Od nowa")};
    
    JLabel first = new JLabel("Rozmiar bufora:");
    JLabel secound = new JLabel("Ilosc producentow: ");
    JLabel third = new JLabel("Ilosc konsumentow");
    
    JComboBox<Integer> newBufor = new JComboBox<Integer>();
    JComboBox<Integer> newProducent = new JComboBox<Integer>();
    JComboBox<Integer> newKonsument = new JComboBox<Integer>();
    
    JButton start = new JButton("Start");
    JButton stop = new JButton("Stop");
    
  
    Component buttons = Box.createHorizontalStrut(3000);
    Component width = Box.createHorizontalStrut(550);
    Component height = Box.createVerticalStrut(290);

    public ProducentKonsument() 
    {
        
        this.setSize(700, 700);
        this.setDefaultCloseOperation(3);
        int size;
       for (size = 0; size < this.options.length; ++size)
        {
            this.options[size].addActionListener(this);
        }
        
        this.menu[0].add(this.options[0]);
        this.menu[0].add(this.options[3]);
        this.menu[1].add(this.options[1]);
        
        for (size = 0; size < this.menu.length; ++size)
        {
            this.menulist.add(this.menu[size]);
        }
        this.setJMenuBar(this.menulist);
        for (size = 1; size < 9; ++size) {
            this.newBufor.addItem(size);
        }
        for (size = 1; size < 8; ++size) {
            this.newProducent.addItem(size);
            this.newKonsument.addItem(size);
        }
        JScrollPane scrollPane = new JScrollPane(this.text);
        scrollPane.setHorizontalScrollBarPolicy(31);
        this.setContentPane(this.main);
        this.setVisible(true);
        
        this.newBufor.addActionListener(this);
        this.newProducent.addActionListener(this);
        this.newKonsument.addActionListener(this);
        this.start.addActionListener(this);
        this.stop.addActionListener(this);
        
        this.main.add(this.first);
        this.main.add(this.newBufor);
        this.main.add(this.secound);
        this.main.add(this.newProducent);
        this.main.add(this.third);
        this.main.add(this.newKonsument);
       
        this.text.setEditable(false);
        this.text.setFont(new Font("", 0, 12));
        this.text.setColumns(30);
        this.text.setRows(15);
        
     
        this.main.add(scrollPane);
        this.main.add(this.buttons);
        this.main.add(this.start);
        this.main.add(this.stop);
      
        this.wizu.add(this.height);
        this.wizu.add(this.width);
        this.main.add(this.wizu);
        
    }

    
    public void actionPerformed(ActionEvent evt)
    {
        Object src = evt.getSource();
        if (src == this.start)
        {
            if (!this.play)
            {
                int variable;
                this.bufor = new Bufor(this.text, this.wizu, (Integer)this.newBufor.getSelectedItem(), (Integer)this.newKonsument.getSelectedItem(), (Integer)this.newProducent.getSelectedItem());
                for (variable = 1; variable <= (Integer)this.newProducent.getSelectedItem(); ++variable) {
                    this.producent.add(new Producent(this.bufor, variable));
                }
                for (variable = 1; variable <= (Integer)this.newKonsument.getSelectedItem(); ++variable) {
                    this.konsument.add(new Konsument(this.bufor, variable));
                }
                for (Producent p : this.producent) {
                    p.start();
                }
                for (Konsument k : this.konsument) {
                    k.start();
                }
                this.play = true;
                this.newBufor.setEnabled(false);
                this.newProducent.setEnabled(false);
                this.newKonsument.setEnabled(false);
            } else {
                for (Producent p : this.producent) {
                    p.play();
                }
                for (Konsument k : this.konsument) {
                    k.play();
                }
            }
        }
        
        
      if (src == this.options[3]) {
            this.dispose();
            new ProducentKonsument();
        }
        if (src == this.options[0]) {
            this.dispose();
        }
       
        if (src == this.stop) {
            for (Producent p : this.producent) {
                p.pause();
            }
            for (Konsument k : this.konsument) {
                k.pause();
            }
        }
     
        if (src == this.options[1]) {
            JOptionPane.showMessageDialog(null, "Zadanie 5\n Autor: Przemyslaw Wojcinowicz\n Indeks: 225943\n Wersja: koncowa", "Ogolne", this.getDefaultCloseOperation());
        }
      
    }

    public static void main(String[] args) {
        new ProducentKonsument();
    }
}

