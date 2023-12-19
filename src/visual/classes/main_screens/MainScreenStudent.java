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
	private PanelOpcion optionHome;
	private PanelOpcion selectedPanel;
	private PanelOpcion optionEvaluations;
	private PanelOpcion optionEscalafon;
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
		
		optionHome = new PanelOpcion();
		optionHome.setBorder(new MatteBorder(0, 2, 0, 0, new Color(0,0,0)));
		optionHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		optionHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent es) {
				optionHome.setBackground(color_scheme.getPanelOpcionHover());
			}
			@Override
			public void mouseExited(MouseEvent eS) {
				if(optionHome.equals(selectedPanel))
					optionHome.setBackground(color_scheme.getPanelOpcionSeleccionado());
				else
					optionHome.setBackground(color_scheme.getPanelContenedorOpciones());
			}
			@Override
			public void mouseClicked(MouseEvent es) {
				panelPrincipall.setSelectedIndex(0);
				opcionLbl.setText("INICIO");
				selectedPanel.setBackground(color_scheme.getPanelContenedorOpciones());
				selectedPanel = optionHome;
				selectedPanel.setBackground(color_scheme.getPanelOpcionSeleccionado());
			}
		});
		optionHome.setLayout(null);
		optionHome.setSeleccionado(true);
		optionHome.setOpaque(true);
		optionHome.setBackground(color_scheme.getPanelContenedorOpciones());
		optionHome.setBounds(0, 11, 300, 66);
		optionsPanel.add(optionHome);
		selectedPanel = optionHome;
		selectedPanel.setBackground(color_scheme.getPanelOpcionSeleccionado());
		
		Imagen logoInicio = new Imagen(new ImageIcon(MainScreenBasic.class.getResource("/visual/icons/inicioBlack.png")));
		logoInicio.setBounds(67, 14, 38, 38);
		optionHome.add(logoInicio);

		JLabel textoInicio = new JLabel("INICIO");
		textoInicio.setForeground(color_scheme.getPanelOpcionTexto());
		textoInicio.setHorizontalTextPosition(SwingConstants.CENTER);
		textoInicio.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		textoInicio.setBounds(126, 16, 168, 33);
		optionHome.add(textoInicio);
		
		optionEvaluations = new PanelOpcion();
		optionEvaluations.setBorder(new MatteBorder(0, 2, 0, 0, new Color(0,0,0)));
		optionEvaluations.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		optionEvaluations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent es) {
				optionEvaluations.setBackground(color_scheme.getPanelOpcionHover());
			}
			@Override
			public void mouseExited(MouseEvent eS) {
				if(optionEvaluations.equals(selectedPanel))
					optionEvaluations.setBackground(color_scheme.getPanelOpcionSeleccionado());
				else
					optionEvaluations.setBackground(color_scheme.getPanelContenedorOpciones());
			}
			@Override
			public void mouseClicked(MouseEvent es) {
				panelPrincipall.setSelectedIndex(1);
				opcionLbl.setText("EVALUACIONES");
				selectedPanel.setBackground(color_scheme.getPanelContenedorOpciones());
				selectedPanel = optionEvaluations;
				selectedPanel.setBackground(color_scheme.getPanelOpcionSeleccionado());
			}
		});
		optionEvaluations.setLayout(null);
		optionEvaluations.setSeleccionado(true);
		optionEvaluations.setOpaque(true);
		optionEvaluations.setBackground(color_scheme.getPanelContenedorOpciones());
		optionEvaluations.setBounds(0, 77, 300, 66);
		optionsPanel.add(optionEvaluations);

		Imagen logoEvaluaciones = new Imagen(new ImageIcon(MainScreenBasic.class.getResource("/visual/icons/eval.png")));
		logoEvaluaciones.setBounds(67, 14, 38, 38);
		optionEvaluations.add(logoEvaluaciones);

		JLabel textoEvaluaciones = new JLabel("EVALUACIONES");
		textoEvaluaciones.setForeground(color_scheme.getPanelOpcionTexto());
		textoEvaluaciones.setHorizontalTextPosition(SwingConstants.CENTER);
		textoEvaluaciones.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		textoEvaluaciones.setBounds(126, 16, 168, 33);
		optionEvaluations.add(textoEvaluaciones);
		
		
		optionEscalafon = new PanelOpcion();
		optionEscalafon.setBorder(new MatteBorder(0, 2, 0, 0, new Color(0,0,0)));
		optionEscalafon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		optionEscalafon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent es) {
				optionEscalafon.setBackground(color_scheme.getPanelOpcionHover());
			}
			@Override
			public void mouseExited(MouseEvent eS) {
				if(optionEscalafon.equals(selectedPanel))
					optionEscalafon.setBackground(color_scheme.getPanelOpcionSeleccionado());
				else
					optionEscalafon.setBackground(color_scheme.getPanelContenedorOpciones());
			}
			@Override
			public void mouseClicked(MouseEvent es) {
				panelPrincipall.setSelectedIndex(2);
				opcionLbl.setText("ESCALAFÓN");
				selectedPanel.setBackground(color_scheme.getPanelContenedorOpciones());
				selectedPanel = optionEscalafon;
				selectedPanel.setBackground(color_scheme.getPanelOpcionSeleccionado());
			}
		});
		optionEscalafon.setLayout(null);
		optionEscalafon.setSeleccionado(true);
		optionEscalafon.setOpaque(true);
		optionEscalafon.setBackground(color_scheme.getPanelContenedorOpciones());
		optionEscalafon.setBounds(0, 143, 300, 66);
		optionsPanel.add(optionEscalafon);

		Imagen logoEscalafon = new Imagen(new ImageIcon(MainScreenBasic.class.getResource("/visual/icons/escalafon.png")));
		logoEscalafon.setBounds(67, 14, 38, 38);
		optionEscalafon.add(logoEscalafon);

		JLabel textoEscalafon = new JLabel("ESCALAFÓN");
		textoEscalafon.setForeground(color_scheme.getPanelOpcionTexto());
		textoEscalafon.setHorizontalTextPosition(SwingConstants.CENTER);
		textoEscalafon.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		textoEscalafon.setBounds(126, 16, 168, 33);
		optionEscalafon.add(textoEscalafon);
		
		if(std.getTypeStd()=='D')
			optionEscalafon.setVisible(false);
	}
}
