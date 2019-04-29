import java.awt.*;
import java.beans.*;
import java.lang.String;

public class CBeanOgranicz extends TextField {

	private static final long serialVersionUID = 1L;
	String salary[] = new String[11];
	String oldSalary[] = new String[11];
	private PropertyChangeSupport changes = new PropertyChangeSupport(this);
	private VetoableChangeSupport veto = new VetoableChangeSupport(this);

	public CBeanOgranicz() {
	}

	// metoda ustawia wynagrodzenie pracownika
	public void setSalary(String newSalary, int i) throws PropertyVetoException {
		oldSalary[i] = salary[i];
		veto.fireVetoableChange("salary", oldSalary[i], newSalary);
		this.salary[i] = newSalary;
		changes.firePropertyChange("salary", oldSalary[i], newSalary);
	}
	// metoda zwraca wynagrodzenie danego pracownika
	public String getSalary(int i) {
		return salary[i];
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		changes.removePropertyChangeListener(l);
	}

	public void addPropertyChangeListener(PropertyChangeListener l) {
		changes.addPropertyChangeListener(l);
	}

	public void removeVetoableChangeListener(VetoableChangeListener l) {
		veto.removeVetoableChangeListener(l);
	}

	public void addVetoableChangeListener(VetoableChangeListener l) {
		veto.addVetoableChangeListener(l);
	}

}