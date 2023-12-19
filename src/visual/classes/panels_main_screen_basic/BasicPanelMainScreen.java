package visual.classes.panels_main_screen_basic;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

/**
 * JPanel that helps to make abstractions about the main screens panels 
 * @author Lilian Rojas
 * @author Eduardo González
 *
 */
public class BasicPanelMainScreen extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public BasicPanelMainScreen() {
		setBounds(300, 55, 1200, 575);
		setBackground(Color.WHITE);
		setLayout(null);
		setBorder(new MatteBorder(0, 2, 2, 2, new Color(0,0,0)));
	}

}
