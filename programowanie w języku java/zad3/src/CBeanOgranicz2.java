import java.beans.*;
import java.lang.String;

public class CBeanOgranicz2 implements VetoableChangeListener {

	String a;
	String b = new String("");
	int v;
	public CBeanOgranicz2() {
	}
	// metoda zwraca 
	public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
		a = evt.getNewValue().toString();
		v = Integer.parseInt(a);
		if (v < 2500)
			throw new PropertyVetoException("", evt);
		else
		System.out.println(evt.getNewValue().toString());
		b = evt.getNewValue().toString();
	}
}