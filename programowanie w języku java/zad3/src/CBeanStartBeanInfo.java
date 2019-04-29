
import java.awt.Image;
import java.beans.*;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Klasa informacyjna ziarenka. Specyfikuje edytory w³aœciwoœci i customizer
 */

public class CBeanStartBeanInfo extends SimpleBeanInfo {
	
	public BeanDescriptor getBeanDescriptor() {
		return new BeanDescriptor(CBeanStart.class, CBeanStartCustomizer.class);
	}

/*
	public Image getIcon(int iconType) {
		String name = "";
		if (iconType == BeanInfo.ICON_COLOR_16x16)
			name = "COLOR_16x16";
		else if (iconType == BeanInfo.ICON_COLOR_32x32)
			name = "COLOR_32x32";
		else
			return null;
		Image im = null;
		try {
			im = ImageIO.read(CBeanStartBeanInfo.class.getClassLoader().getResourceAsStream("ImageBean_" + name + ".gif"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return im;
	}
*/
	public PropertyDescriptor[] getPropertyDescriptors() {
		try {
			PropertyDescriptor valuesDescriptor2 = new PropertyDescriptor("titlePosition", CBeanStart.class);
			valuesDescriptor2.setPropertyEditorClass(TitlePositionEditor.class);
						
			PropertyDescriptor valuesDescriptor = new PropertyDescriptor("iconPosition", CBeanStart.class);
			valuesDescriptor.setPropertyEditorClass(IconPositionEditor.class);
			

			return new PropertyDescriptor[] { 
			valuesDescriptor,
			valuesDescriptor2
			
					};
		} catch (IntrospectionException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
