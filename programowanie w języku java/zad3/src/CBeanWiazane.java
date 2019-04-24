import java.awt.*;
import java.io.*;
import java.beans.*;
import java.awt.event.*;

public class CBeanWiazane extends Label {
	private String a;
	private transient PropertyChangeSupport propertyChangeListeners = new PropertyChangeSupport(this);

	public CBeanWiazane() {
	}

	public void setAuthor(String author) {
		String old = a;
		this.a = author;
		propertyChangeListeners.firePropertyChange("author", old, author);
	}

	public String getZmiennawiazana() {
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