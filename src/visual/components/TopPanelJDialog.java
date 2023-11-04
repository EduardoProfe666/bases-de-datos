package visual.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import componentes.PanelBordeOval;
import definitions.VisualDefinitions;
import utils.Auxiliar;

/**
 * Graphical Component that models the top movable panel of the app's JDialogs
 * 
 * @author Eduardo González
 * @author Lilian Rojas
 *
 */
public class TopPanelJDialog extends PanelBordeOval{
	private static final long serialVersionUID = 1L;
	private Color colorBase;
	private Color colorClicked;
	private int xMouseDrag;
	private int yMouseDrag;
	private JLabel tag;
	private JButton exitBtn;

	public TopPanelJDialog(Color color, JDialog root, String tag) {
		super(VisualDefinitions.ROUND_BORDER, VisualDefinitions.ROUND_BORDER,0,0);
		if(root==null)
			throw new RuntimeException("La raiz no puede ser null");
		
		colorBase = new Color(color.getRed(),color.getGreen(),color.getBlue(),150);
		colorClicked = new Color(color.getRed(),color.getGreen(),color.getBlue(),200);
		
		this.setBounds(0, 0, root.getBounds().width, 45);
		this.setBackground(colorBase);
		
		
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				root.setLocation(x-xMouseDrag,y-yMouseDrag);
			}
		});
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setBackground(colorClicked);
				xMouseDrag = e.getX();
				yMouseDrag = e.getY();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				setBackground(colorBase);
			}
		});
		
		this.tag = new JLabel(tag);
		this.tag.setBounds(16, 6, 400, 38);
		this.tag.setFont(new Font("Roboto Black", Font.PLAIN, 21));
		
		exitBtn = new JButton("");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				root.dispose();
			}
		});
		exitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitBtn.setIcon(Auxiliar.adjustImage(new Dimension(32,32), TopPanel.class.getResource("/visual/icons/exit1.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitBtn.setIcon(Auxiliar.adjustImage(new Dimension(32,32), TopPanel.class.getResource("/visual/icons/exit0.png")));
			}
		});
		exitBtn.setContentAreaFilled(false);
		exitBtn.setBounds(root.getBounds().width-43, 4, 36, 36);
		exitBtn.setHorizontalAlignment(SwingConstants.CENTER);
		exitBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		exitBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitBtn.setIcon(Auxiliar.adjustImage(new Dimension(32,32), TopPanel.class.getResource("/visual/icons/exit0.png")));
	
		
		
		this.setLayout(null);
		this.add(this.tag);
		this.add(exitBtn);
	}	

}
