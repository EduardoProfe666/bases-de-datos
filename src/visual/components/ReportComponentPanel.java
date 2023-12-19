package visual.components;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import componentes.Imagen;
import componentes.PanelBordeOval;
import definitions.VisualDefinitions;
import utils.ColorScheme;

public class ReportComponentPanel extends PanelBordeOval{
	private static final long serialVersionUID = 1L;
	
	public ReportComponentPanel(ColorScheme ef, String title, String description, String rutaImagen) {
		super(VisualDefinitions.ROUND_BORDER);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(ef.getPanelMovilBase());
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(ef.getBordeAvatar());
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		setBackground(ef.getBordeAvatar());
		setBounds(10, 80, 220, 220);
		setLayout(null);
		
		Imagen logo = new Imagen(new ImageIcon(ReportComponentPanel.class.getResource(rutaImagen)));
		logo.setBounds(178, 3, 32, 32);
		add(logo);
		
		JLabel titleLbl = new JLabel(title);
		titleLbl.setBorder(new MatteBorder(0, 0, 2, 0, ef.getBordeLbl()));
		titleLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		titleLbl.setBounds(10, 11, 200, 24);
		add(titleLbl);
		
		JLabel descriptionLbl = new JLabel("<html>"+description+"<html>");
		descriptionLbl.setVerticalAlignment(SwingConstants.TOP);
		descriptionLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		descriptionLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		descriptionLbl.setBounds(10, 46, 200, 161);
		add(descriptionLbl);
	}
}
