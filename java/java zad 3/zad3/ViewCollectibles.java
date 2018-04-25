/* Klasa ViewCollectibles
 * Autor Przemyslaw Wojcinowicz
 * Data 23.11.2016 
*/
import javax.swing.*;
import java.awt.*;
import java.util.*;


class ViewCollectibles extends JScrollPane
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList<Object> list;
	private DefaultListModel<Object> listModel;
    Collection<String> collection;
    
    ViewCollectibles(Collection<String> coll, int width, int high, String depiction)
    { 
    	
    	listModel = new DefaultListModel<Object>();
    	list = new JList<Object>(listModel);
        list = new JList<Object>(this.listModel);
        collection = coll;
        setViewportView(list);
        setPreferredSize(new Dimension(width, high));
        setBorder(BorderFactory.createTitledBorder(depiction));
       
      }
  
    
    void remove()
    { 
    	listModel.clear();
    }
    
    void addData(Object obj)
    { 
    	listModel.addElement(obj);
    }
  
}
