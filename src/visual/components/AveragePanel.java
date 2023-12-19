package visual.components;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

/**
 * JPanel that models the component that displays the accumulated average of a student.
 * 
 * @author Lilian Rojas
 * @author Eduardo Gonzalez
 *
 */
public class AveragePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public AveragePanel(Color borderColor, double avg) {
		setBackground(Color.WHITE);
		setLayout(null);
		setBorder(new LineBorder(borderColor, 2));
		setBounds(0, 0, 190, 190);
		setToolTipText("Promedio Acumulado");
		
		JLabel avgNumber = new JLabel(String.valueOf(Math.floor(avg*100)/100));
		avgNumber.setFont(new Font("Roboto Black", Font.PLAIN, 50));
		avgNumber.setHorizontalAlignment(SwingConstants.CENTER);
		avgNumber.setBounds(22, 35, 145, 120);
		add(avgNumber);
	}
	
}
