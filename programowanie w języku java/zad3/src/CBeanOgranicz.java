import java.awt.*;
import java.beans.*;
import java.lang.String;

public class CBeanOgranicz extends TextField {
	String salary[] = new String[11];
	String oldSalary[] = new String[11];
	private String temp;
	private PropertyChangeSupport zmiany = new PropertyChangeSupport(this);
	private VetoableChangeSupport veto = new VetoableChangeSupport(this);
	public CBeanOgranicz() {
	}

	private void jbInit() {
	}

	public void setSalary(String newSalary, int i) throws PropertyVetoException {
		oldSalary[i] = salary[i];
		veto.fireVetoableChange("salary", oldSalary[i], newSalary);
		this.salary[i] = newSalary;
		zmiany.firePropertyChange("salary", oldSalary[i], newSalary);
	}

	public String getSalary(int i) {
		return salary[i];
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		zmiany.removePropertyChangeListener(l);
	}

	public void addPropertyChangeListener(PropertyChangeListener l) {
		zmiany.addPropertyChangeListener(l);
	}

	public void removeVetoableChangeListener(VetoableChangeListener l) {
		veto.removeVetoableChangeListener(l);
	}

	public void addVetoableChangeListener(VetoableChangeListener l) {
		veto.addVetoableChangeListener(l);
	}

	
}