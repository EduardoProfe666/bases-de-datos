package visual.components;

import java.awt.Color;

import javax.swing.JPanel;

/**
 * JPanel that models a semi transparent panel.
 * 
 * @author Lilian Rojas
 * @author Eduardo Gonzalez
 *
 */
public class SemiGlassPane extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public SemiGlassPane(Color color) {
		this.setLayout(null);
		this.setBackground(new Color(color.getRed(),color.getGreen(),color.getBlue(),150));
		this.setBounds(0, 0, 1198, 628);
	}
}
