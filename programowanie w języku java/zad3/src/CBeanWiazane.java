import java.awt.*;
import java.io.*;
import java.beans.*;

public class CBeanWiazane extends Label {

	private static final long serialVersionUID = 1L;
	private String a;
	private transient PropertyChangeSupport propertyChangeListeners = new PropertyChangeSupport(this);

	public CBeanWiazane() {
	}
	// metoda z wlasciwoscia wiazana
	// metoda ustawia dane na temat autora programu
	public void setAuthor(String author) {
		String old = a;
		this.a = author;
		propertyChangeListeners.firePropertyChange("author", old, author);
	}
	// metoda zwraca dane na temat autora programu
	public String getAuthor() {
		return a;
	}

	public synchronized void removePropertyChangeListener(PropertyChangeListener l) {
		super.removePropertyChangeListener(l);
		propertyChangeListeners.removePropertyChangeListener(l);
	}

	public synchronized void addPropertyChangeListener(PropertyChangeListener l) {
		super.addPropertyChangeListener(l);
		propertyChangeListeners.addPropertyChangeListener(l);
	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.defaultWriteObject();
	}

	private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		ois.defaultReadObject();
	}
}