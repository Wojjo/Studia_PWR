import java.awt.*;
import java.beans.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CBeanWiazane2 extends Label implements PropertyChangeListener {

	private static final long serialVersionUID = 1L;

	public CBeanWiazane2() {
	}

	public void propertyChange(PropertyChangeEvent evt) {
		this.setText(evt.getNewValue().toString());

	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.defaultWriteObject();
	}

	private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		ois.defaultReadObject();
	}
}