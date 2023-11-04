package visual.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import componentes.PanelBordeOval;
import definitions.VisualDefinitions;
import raven.glasspanepopup.GlassPanePopup;
import raven.glasspanepopup.Option;
import sample.message.Message;
import sample.message.OptionConstructor;
import utils.Auxiliar;

/**
 * Graphical Component that models the top movable panel of the app
 * 
 * @author Eduardo González
 * @author Lilian Rojas
 *
 */
public class TopPanel extends PanelBordeOval{
	private static final long serialVersionUID = 1L;
	private Color colorBase;
	private Color colorClicked;
	private int xMouseDrag;
	private int yMouseDrag;
	private JLabel tag;
	private JButton exitBtn;
	private JButton minimizeBtn;
	private boolean b;

	public TopPanel(Color color, JFrame root, String tag) {
		super(VisualDefinitions.ROUND_BORDER, VisualDefinitions.ROUND_BORDER,0,0);
		if(root==null)
			throw new RuntimeException("La raiz no puede ser null");
		
		colorBase = new Color(color.getRed(),color.getGreen(),color.getBlue(),150);
		colorClicked = new Color(color.getRed(),color.getGreen(),color.getBlue(),200);
		
		this.setBounds(0, 0, root.getBounds().width, 45);
		this.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 0, 0)));
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
		b = false;
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Option o = OptionConstructor.constructOption(color, false);
				Message m = new Message("Salida de la Aplicación", VisualDefinitions.EXIT_QUESTION);
				m.eventOK(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						GlassPanePopup.closePopupLast();
						System.exit(0);
						b = true;
					}
				});
				
				GlassPanePopup.showPopup(m, o);
				if(!b)
					exitBtn.setIcon(Auxiliar.adjustImage(new Dimension(32,32), TopPanel.class.getResource("/visual/icons/exit0.png")));
				
					
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
	
		minimizeBtn = new JButton("");
		minimizeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				root.setState(Frame.ICONIFIED);
			}
		});
		minimizeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				minimizeBtn.setIcon(Auxiliar.adjustImage(new Dimension(32,32), TopPanel.class.getResource("/visual/icons/minimize1.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				minimizeBtn.setIcon(Auxiliar.adjustImage(new Dimension(32,32), TopPanel.class.getResource("/visual/icons/minimize0.png")));
			}
		});
		minimizeBtn.setContentAreaFilled(false);
		minimizeBtn.setBounds(root.getBounds().width-80, 4, 36, 36);
		minimizeBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		minimizeBtn.setHorizontalAlignment(SwingConstants.CENTER);
		minimizeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		minimizeBtn.setIcon(Auxiliar.adjustImage(new Dimension(32,32), TopPanel.class.getResource("/visual/icons/minimize0.png")));
		
		this.setLayout(null);
		this.add(this.tag);
		this.add(exitBtn);
		this.add(minimizeBtn);
	}
	
	public JButton getMinimizeBtn() {
		return minimizeBtn;
	}
	

}
