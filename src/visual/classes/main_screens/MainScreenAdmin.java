package visual.classes.main_screens;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import componentes.Imagen;
import componentes.PanelOpcion;
import definitions.VisualDefinitions;
import dto.UserDTO;
import utils.ColorScheme;
import visual.classes.panels_main_screen_admin.PanelUsersAdmin;

/**
 * JFrame that models the admin's main screen
 * 
 * @author Lilian Rojas
 * @author Eduardo González
 *
 */
public class MainScreenAdmin extends MainScreenBasic{
	private static final long serialVersionUID = 1L;
	private PanelOpcion opcionUsuarios;
	private PanelOpcion panelSeleccionado;
	private static final ColorScheme e  = new ColorScheme(VisualDefinitions.P12, VisualDefinitions.P8, Color.BLACK, Color.BLACK,
			VisualDefinitions.P8, VisualDefinitions.P11, VisualDefinitions.P8, VisualDefinitions.P11, 
			VisualDefinitions.P2, VisualDefinitions.P9, Color.BLACK, VisualDefinitions.P8, VisualDefinitions.P12, 
			Color.BLACK, "/visual/icons/favicon.png", VisualDefinitions.P7, VisualDefinitions.P12,
			"/visual/icons/backBlack01.png", "/visual/icons/backBlack02.png",
			VisualDefinitions.P9, Color.BLACK);

	public MainScreenAdmin(String nombreUsuario, UserDTO u) {
		super(nombreUsuario, "Administrador", e, u);
		
		panelPrincipall.addTab("a", new PanelUsersAdmin(e, this));
		opcionLbl.setText("USUARIOS");
		
		opcionUsuarios = new PanelOpcion();
		opcionUsuarios.setBorder(new MatteBorder(0, 2, 0, 0, new Color(0,0,0)));
		opcionUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		opcionUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent es) {
				opcionUsuarios.setBackground(color_scheme.getPanelOpcionHover());
			}
			@Override
			public void mouseExited(MouseEvent eS) {
				if(opcionUsuarios.equals(panelSeleccionado))
					opcionUsuarios.setBackground(color_scheme.getPanelOpcionSeleccionado());
				else
					opcionUsuarios.setBackground(color_scheme.getPanelContenedorOpciones());
			}
			@Override
			public void mouseClicked(MouseEvent es) {
				panelPrincipall.setSelectedIndex(0);
				opcionLbl.setText("USUARIOS");
				panelSeleccionado.setBackground(color_scheme.getPanelContenedorOpciones());
				panelSeleccionado = opcionUsuarios;
				panelSeleccionado.setBackground(color_scheme.getPanelOpcionSeleccionado());
			}
		});
		opcionUsuarios.setLayout(null);
		opcionUsuarios.setSeleccionado(true);
		opcionUsuarios.setOpaque(true);
		opcionUsuarios.setBackground(color_scheme.getPanelContenedorOpciones());
		opcionUsuarios.setBounds(0, 11, 300, 66);
		optionsPanel.add(opcionUsuarios);
		panelSeleccionado = opcionUsuarios;
		panelSeleccionado.setBackground(color_scheme.getPanelOpcionSeleccionado());
		
		Imagen logoUsuarios =  new Imagen(new ImageIcon(MainScreenBasic.class.getResource("/visual/icons/user_1.png")));
		logoUsuarios.setBounds(67, 14, 38, 38);
		opcionUsuarios.add(logoUsuarios);

		JLabel textoUsuarios = new JLabel("USUARIOS");
		textoUsuarios.setForeground(color_scheme.getPanelOpcionTexto());
		textoUsuarios.setHorizontalTextPosition(SwingConstants.CENTER);
		textoUsuarios.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		textoUsuarios.setBounds(126, 16, 168, 33);
		opcionUsuarios.add(textoUsuarios);
		
		
		
	}
}
