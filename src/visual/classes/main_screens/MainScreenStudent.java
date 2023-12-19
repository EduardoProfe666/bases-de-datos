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
import dto.StudentDTO;
import dto.UserDTO;
import utils.ColorScheme;
import visual.classes.panels_main_screen_student.PanelEscalafonStudent;
import visual.classes.panels_main_screen_student.PanelEvaluationsStudent;
import visual.classes.panels_main_screen_student.PanelHomeStudent;

/**
 * JFrame that models the student's main screen
 * 
 * @author Lilian Rojas
 * @author Eduardo González
 *
 */
public class MainScreenStudent extends MainScreenBasic{

	private static final long serialVersionUID = 1L;
	private PanelOpcion opcionInicio;
	private PanelOpcion panelSeleccionado;
	private PanelOpcion opcionEvaluaciones;
	private PanelOpcion opcionEscalafon;
	protected StudentDTO student;
	private static final ColorScheme e = new ColorScheme(VisualDefinitions.P6, VisualDefinitions.P8, Color.BLACK, Color.BLACK,
			VisualDefinitions.P3, VisualDefinitions.P6, VisualDefinitions.P8, VisualDefinitions.P6, 
			VisualDefinitions.P2, VisualDefinitions.P9, Color.BLACK, VisualDefinitions.P8, VisualDefinitions.P6, 
			Color.BLACK, "/visual/icons/favicon.png", VisualDefinitions.P7, VisualDefinitions.P6,
			"/visual/icons/backBlack01.png", "/visual/icons/backBlack02.png",
			VisualDefinitions.P9, Color.BLACK);

	public MainScreenStudent(StudentDTO std, UserDTO u) {
		super(std.getNames() + " " + std.getLastNames(), "Estudiante", e, u);
		
		student = std;
		panelPrincipall.addTab("a", new PanelHomeStudent(e, student));
		panelPrincipall.addTab("a", new PanelEvaluationsStudent(e, student));
		panelPrincipall.addTab("a", std.getTypeStd()!='D' ? new PanelEscalafonStudent(e,student) : null);
		
		opcionInicio = new PanelOpcion();
		opcionInicio.setBorder(new MatteBorder(0, 2, 0, 0, new Color(0,0,0)));
		opcionInicio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		opcionInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent es) {
				opcionInicio.setBackground(color_scheme.getPanelOpcionHover());
			}
			@Override
			public void mouseExited(MouseEvent eS) {
				if(opcionInicio.equals(panelSeleccionado))
					opcionInicio.setBackground(color_scheme.getPanelOpcionSeleccionado());
				else
					opcionInicio.setBackground(color_scheme.getPanelContenedorOpciones());
			}
			@Override
			public void mouseClicked(MouseEvent es) {
				panelPrincipall.setSelectedIndex(0);
				opcionLbl.setText("INICIO");
				panelSeleccionado.setBackground(color_scheme.getPanelContenedorOpciones());
				panelSeleccionado = opcionInicio;
				panelSeleccionado.setBackground(color_scheme.getPanelOpcionSeleccionado());
			}
		});
		opcionInicio.setLayout(null);
		opcionInicio.setSeleccionado(true);
		opcionInicio.setOpaque(true);
		opcionInicio.setBackground(color_scheme.getPanelContenedorOpciones());
		opcionInicio.setBounds(0, 11, 300, 66);
		optionsPanel.add(opcionInicio);
		panelSeleccionado = opcionInicio;
		panelSeleccionado.setBackground(color_scheme.getPanelOpcionSeleccionado());
		
		Imagen logoInicio = new Imagen(new ImageIcon(MainScreenBasic.class.getResource("/visual/icons/inicioBlack.png")));
		logoInicio.setBounds(67, 14, 38, 38);
		opcionInicio.add(logoInicio);

		JLabel textoInicio = new JLabel("INICIO");
		textoInicio.setForeground(color_scheme.getPanelOpcionTexto());
		textoInicio.setHorizontalTextPosition(SwingConstants.CENTER);
		textoInicio.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		textoInicio.setBounds(126, 16, 168, 33);
		opcionInicio.add(textoInicio);
		
		opcionEvaluaciones = new PanelOpcion();
		opcionEvaluaciones.setBorder(new MatteBorder(0, 2, 0, 0, new Color(0,0,0)));
		opcionEvaluaciones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		opcionEvaluaciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent es) {
				opcionEvaluaciones.setBackground(color_scheme.getPanelOpcionHover());
			}
			@Override
			public void mouseExited(MouseEvent eS) {
				if(opcionEvaluaciones.equals(panelSeleccionado))
					opcionEvaluaciones.setBackground(color_scheme.getPanelOpcionSeleccionado());
				else
					opcionEvaluaciones.setBackground(color_scheme.getPanelContenedorOpciones());
			}
			@Override
			public void mouseClicked(MouseEvent es) {
				panelPrincipall.setSelectedIndex(1);
				opcionLbl.setText("EVALUACIONES");
				panelSeleccionado.setBackground(color_scheme.getPanelContenedorOpciones());
				panelSeleccionado = opcionEvaluaciones;
				panelSeleccionado.setBackground(color_scheme.getPanelOpcionSeleccionado());
			}
		});
		opcionEvaluaciones.setLayout(null);
		opcionEvaluaciones.setSeleccionado(true);
		opcionEvaluaciones.setOpaque(true);
		opcionEvaluaciones.setBackground(color_scheme.getPanelContenedorOpciones());
		opcionEvaluaciones.setBounds(0, 77, 300, 66);
		optionsPanel.add(opcionEvaluaciones);

		Imagen logoEvaluaciones = new Imagen(new ImageIcon(MainScreenBasic.class.getResource("/visual/icons/eval.png")));
		logoEvaluaciones.setBounds(67, 14, 38, 38);
		opcionEvaluaciones.add(logoEvaluaciones);

		JLabel textoEvaluaciones = new JLabel("EVALUACIONES");
		textoEvaluaciones.setForeground(color_scheme.getPanelOpcionTexto());
		textoEvaluaciones.setHorizontalTextPosition(SwingConstants.CENTER);
		textoEvaluaciones.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		textoEvaluaciones.setBounds(126, 16, 168, 33);
		opcionEvaluaciones.add(textoEvaluaciones);
		
		
		opcionEscalafon = new PanelOpcion();
		opcionEscalafon.setBorder(new MatteBorder(0, 2, 0, 0, new Color(0,0,0)));
		opcionEscalafon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		opcionEscalafon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent es) {
				opcionEscalafon.setBackground(color_scheme.getPanelOpcionHover());
			}
			@Override
			public void mouseExited(MouseEvent eS) {
				if(opcionEscalafon.equals(panelSeleccionado))
					opcionEscalafon.setBackground(color_scheme.getPanelOpcionSeleccionado());
				else
					opcionEscalafon.setBackground(color_scheme.getPanelContenedorOpciones());
			}
			@Override
			public void mouseClicked(MouseEvent es) {
				panelPrincipall.setSelectedIndex(2);
				opcionLbl.setText("ESCALAFÓN");
				panelSeleccionado.setBackground(color_scheme.getPanelContenedorOpciones());
				panelSeleccionado = opcionEscalafon;
				panelSeleccionado.setBackground(color_scheme.getPanelOpcionSeleccionado());
			}
		});
		opcionEscalafon.setLayout(null);
		opcionEscalafon.setSeleccionado(true);
		opcionEscalafon.setOpaque(true);
		opcionEscalafon.setBackground(color_scheme.getPanelContenedorOpciones());
		opcionEscalafon.setBounds(0, 143, 300, 66);
		optionsPanel.add(opcionEscalafon);

		Imagen logoEscalafon = new Imagen(new ImageIcon(MainScreenBasic.class.getResource("/visual/icons/escalafon.png")));
		logoEscalafon.setBounds(67, 14, 38, 38);
		opcionEscalafon.add(logoEscalafon);

		JLabel textoEscalafon = new JLabel("ESCALAFÓN");
		textoEscalafon.setForeground(color_scheme.getPanelOpcionTexto());
		textoEscalafon.setHorizontalTextPosition(SwingConstants.CENTER);
		textoEscalafon.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		textoEscalafon.setBounds(126, 16, 168, 33);
		opcionEscalafon.add(textoEscalafon);
		
		if(std.getTypeStd()=='D')
			opcionEscalafon.setVisible(false);
	}
}
