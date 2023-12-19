package visual.classes.main_screens;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import componentes.Imagen;
import componentes.PanelOpcion;
import definitions.VisualDefinitions;
import definitions.VisualErrors;
import dto.UserDTO;
import raven.toast.Notifications;
import raven.toast.Notifications.Location;
import services.ServicesLocator;
import utils.ColorScheme;
import visual.classes.panels_main_screens_teaching_secretary.PanelDismissalTS;
import visual.classes.panels_main_screens_teaching_secretary.PanelEscalafonTS;
import visual.classes.panels_main_screens_teaching_secretary.PanelEvaluationsTS;
import visual.classes.panels_main_screens_teaching_secretary.PanelReportsTS;
import visual.classes.panels_main_screens_teaching_secretary.PanelStudentsTS;
import visual.classes.panels_main_screens_teaching_secretary.PanelSubjectsTS;

/**
 * JFrame that models the teaching secretary's main screen
 * 
 * @author Lilian Rojas
 * @author Eduardo González
 *
 */
public class MainScreenTeachingSecretary extends MainScreenBasic{
	private static final long serialVersionUID = 1L;
	private PanelOpcion optionStudents;
	private JComponent selectedPanel;
	private PanelOpcion optionSubjects;
	private PanelOpcion optionEvaluations;
	private PanelOpcion optionEscalafon;
	private PanelOpcion optionReports;
	private static final ColorScheme e = new ColorScheme(VisualDefinitions.P1, VisualDefinitions.P8, Color.BLACK, Color.BLACK,
			VisualDefinitions.P8, VisualDefinitions.P1, VisualDefinitions.P8, VisualDefinitions.P1, 
			VisualDefinitions.P2, VisualDefinitions.P9, Color.BLACK, VisualDefinitions.P8, VisualDefinitions.P1, 
			Color.BLACK, "/visual/icons/favicon.png", VisualDefinitions.P7, VisualDefinitions.P1,
			"/visual/icons/backBlack01.png", "/visual/icons/backBlack02.png",
			VisualDefinitions.P9, Color.BLACK);
	private PanelOpcion optionDismissal;
	private static JFrame root;

	public MainScreenTeachingSecretary(String nombreUsuario, UserDTO u) {
		super(nombreUsuario, "Secretario Docente",e, u);
		opcionLbl.setText("ALUMNOS");
		
		root = this;
		
		panelPrincipall.addTab("a", new PanelStudentsTS(e, this));
		panelPrincipall.addTab("a", new PanelDismissalTS(e));
		panelPrincipall.addTab("a", new PanelSubjectsTS(e));
		panelPrincipall.addTab("a", new PanelEvaluationsTS(e, this));
		panelPrincipall.addTab("a", new PanelEscalafonTS(e));
		panelPrincipall.addTab("a", new PanelReportsTS(e,this));
		
		panelPrincipall.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int n = panelPrincipall.getSelectedIndex();

				if(n>5) {
					botonAtras.setVisible(true);
					botonAtras.removeActionListener(botonAtras.getActionListeners()[0]);
					botonAtras.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							panelPrincipall.setSelectedIndex(4);
							
						}
					});
				}
				else {
					botonAtras.setVisible(false);
				}

			}
		});
		
		optionStudents = new PanelOpcion();
		optionStudents.setBorder(new MatteBorder(0, 2, 0, 0, new Color(0,0,0)));
		optionStudents.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		optionStudents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent es) {
				optionStudents.setBackground(color_scheme.getPanelOpcionHover());
			}
			@Override
			public void mouseExited(MouseEvent eS) {
				if(optionStudents.equals(selectedPanel))
					optionStudents.setBackground(color_scheme.getPanelOpcionSeleccionado());
				else
					optionStudents.setBackground(color_scheme.getPanelContenedorOpciones());
			}
			@Override
			public void mouseClicked(MouseEvent es) {
				panelPrincipall.setSelectedIndex(0);
				opcionLbl.setText("ALUMNOS");
				selectedPanel.setBackground(color_scheme.getPanelContenedorOpciones());
				selectedPanel = optionStudents;
				selectedPanel.setBackground(color_scheme.getPanelOpcionSeleccionado());
			}
		});
		optionStudents.setLayout(null);
		optionStudents.setSeleccionado(true);
		optionStudents.setOpaque(true);
		optionStudents.setBackground(color_scheme.getPanelContenedorOpciones());
		optionStudents.setBounds(0, 11, 300, 57);
		optionsPanel.add(optionStudents);
		selectedPanel = optionStudents;
		selectedPanel.setBackground(color_scheme.getPanelOpcionSeleccionado());
		
		Imagen logoAlumnos = new Imagen(new ImageIcon(MainScreenBasic.class.getResource("/visual/icons/std.png")));
		logoAlumnos.setBounds(67, 9, 38, 38);
		optionStudents.add(logoAlumnos);

		JLabel textoAlumnos = new JLabel("ALUMNOS");
		textoAlumnos.setForeground(color_scheme.getPanelOpcionTexto());
		textoAlumnos.setHorizontalTextPosition(SwingConstants.CENTER);
		textoAlumnos.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		textoAlumnos.setBounds(126, 12, 168, 33);
		optionStudents.add(textoAlumnos);
		
		optionSubjects = new PanelOpcion();
		optionSubjects.setBorder(new MatteBorder(0, 2, 0, 0, new Color(0,0,0)));
		optionSubjects.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		optionSubjects.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent es) {
				optionSubjects.setBackground(color_scheme.getPanelOpcionHover());
			}
			@Override
			public void mouseExited(MouseEvent eS) {
				if(optionSubjects.equals(selectedPanel))
					optionSubjects.setBackground(color_scheme.getPanelOpcionSeleccionado());
				else
					optionSubjects.setBackground(color_scheme.getPanelContenedorOpciones());
			}
			@Override
			public void mouseClicked(MouseEvent es) {
				panelPrincipall.setSelectedIndex(2);
				opcionLbl.setText("ASIGNATURAS");
				selectedPanel.setBackground(color_scheme.getPanelContenedorOpciones());
				selectedPanel = optionSubjects;
				selectedPanel.setBackground(color_scheme.getPanelOpcionSeleccionado());
			}
		});
		optionSubjects.setLayout(null);
		optionSubjects.setSeleccionado(true);
		optionSubjects.setOpaque(true);
		optionSubjects.setBackground(color_scheme.getPanelContenedorOpciones());
		optionSubjects.setBounds(0, 127, 300, 57);
		optionsPanel.add(optionSubjects);

		Imagen logoAsignaturas = new Imagen(new ImageIcon(MainScreenBasic.class.getResource("/visual/icons/sub.png")));
		logoAsignaturas.setBounds(67, 9, 38, 38);
		optionSubjects.add(logoAsignaturas);

		JLabel textoAsignaturas = new JLabel("ASIGNATURAS");
		textoAsignaturas.setForeground(color_scheme.getPanelOpcionTexto());
		textoAsignaturas.setHorizontalTextPosition(SwingConstants.CENTER);
		textoAsignaturas.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		textoAsignaturas.setBounds(126, 12, 168, 33);
		optionSubjects.add(textoAsignaturas);
		
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
				panelPrincipall.setSelectedIndex(3);
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
		optionEvaluations.setBounds(0, 185, 300, 57);
		optionsPanel.add(optionEvaluations);

		Imagen logoEvaluaciones = new Imagen(new ImageIcon(MainScreenBasic.class.getResource("/visual/icons/eval.png")));
		logoEvaluaciones.setBounds(67, 9, 38, 38);
		optionEvaluations.add(logoEvaluaciones);

		JLabel textoEvaluaciones = new JLabel("EVALUACIONES");
		textoEvaluaciones.setForeground(color_scheme.getPanelOpcionTexto());
		textoEvaluaciones.setHorizontalTextPosition(SwingConstants.CENTER);
		textoEvaluaciones.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		textoEvaluaciones.setBounds(126, 12, 168, 33);
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
				panelPrincipall.setSelectedIndex(4);
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
		optionEscalafon.setBounds(0, 243, 300, 57);
		optionsPanel.add(optionEscalafon);

		Imagen logoEscalafon = new Imagen(new ImageIcon(MainScreenBasic.class.getResource("/visual/icons/escalafon.png")));
		logoEscalafon.setBounds(67, 9, 38, 38);
		optionEscalafon.add(logoEscalafon);

		JLabel textoEscalafon = new JLabel("ESCALAFÓN");
		textoEscalafon.setForeground(color_scheme.getPanelOpcionTexto());
		textoEscalafon.setHorizontalTextPosition(SwingConstants.CENTER);
		textoEscalafon.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		textoEscalafon.setBounds(126, 12, 168, 33);
		optionEscalafon.add(textoEscalafon);
		
		optionReports = new PanelOpcion();
		optionReports.setBorder(new MatteBorder(0, 2, 0, 0, new Color(0,0,0)));
		optionReports.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		optionReports.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent es) {
				optionReports.setBackground(color_scheme.getPanelOpcionHover());
			}
			@Override
			public void mouseExited(MouseEvent eS) {
				if(optionReports.equals(selectedPanel))
					optionReports.setBackground(color_scheme.getPanelOpcionSeleccionado());
				else
					optionReports.setBackground(color_scheme.getPanelContenedorOpciones());
			}
			@Override
			public void mouseClicked(MouseEvent es) {
				panelPrincipall.setSelectedIndex(5);
				opcionLbl.setText("REPORTES");
				selectedPanel.setBackground(color_scheme.getPanelContenedorOpciones());
				selectedPanel = optionReports;
				selectedPanel.setBackground(color_scheme.getPanelOpcionSeleccionado());
			}
		});
		optionReports.setLayout(null);
		optionReports.setSeleccionado(true);
		optionReports.setOpaque(true);
		optionReports.setBackground(color_scheme.getPanelContenedorOpciones());
		optionReports.setBounds(0, 300, 300, 57);
		optionsPanel.add(optionReports);

		Imagen logoReportes = new Imagen(new ImageIcon(MainScreenBasic.class.getResource("/visual/icons/reports.png")));
		logoReportes.setBounds(67, 9, 38, 38);
		optionReports.add(logoReportes);

		JLabel textoReportes = new JLabel("REPORTES");
		textoReportes.setForeground(color_scheme.getPanelOpcionTexto());
		textoReportes.setHorizontalTextPosition(SwingConstants.CENTER);
		textoReportes.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		textoReportes.setBounds(126, 12, 168, 33);
		optionReports.add(textoReportes);
		
		optionDismissal = new PanelOpcion();
		optionDismissal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent es) {
				optionDismissal.setBackground(color_scheme.getPanelOpcionHover());
			}
			@Override
			public void mouseExited(MouseEvent eS) {
				if(optionDismissal.equals(selectedPanel))
					optionDismissal.setBackground(color_scheme.getPanelOpcionSeleccionado());
				else
					optionDismissal.setBackground(color_scheme.getPanelContenedorOpciones());
			}
			@Override
			public void mouseClicked(MouseEvent es) {
				panelPrincipall.setSelectedIndex(1);
				opcionLbl.setText("BAJAS");
				selectedPanel.setBackground(color_scheme.getPanelContenedorOpciones());
				selectedPanel = optionDismissal;
				selectedPanel.setBackground(color_scheme.getPanelOpcionSeleccionado());
			}
		});
		optionDismissal.setLayout(null);
		optionDismissal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		optionDismissal.setSeleccionado(true);
		optionDismissal.setOpaque(true);
		optionDismissal.setBorder(new MatteBorder(0, 2, 0, 0, new Color(0,0,0))); 
		optionDismissal.setBackground(color_scheme.getPanelContenedorOpciones());
		optionDismissal.setBounds(0, 69, 300, 57);
		optionsPanel.add(optionDismissal);
		
		Imagen logoDismissal = new Imagen(new ImageIcon(MainScreenBasic.class.getResource("/visual/icons/dismissals.png")));
		logoDismissal.setBounds(67, 9, 38, 38);
		optionDismissal.add(logoDismissal);
		
		JLabel textDismissal = new JLabel("BAJAS");
		textDismissal.setHorizontalTextPosition(SwingConstants.CENTER);
		textDismissal.setForeground(color_scheme.getPanelOpcionTexto());
		textDismissal.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		textDismissal.setBounds(126, 12, 168, 33);
		optionDismissal.add(textDismissal);
		
	}
	
	public static void reset() {
		root.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		panelPrincipall.setComponentAt(0, new PanelStudentsTS(e, root));
		panelPrincipall.setComponentAt(1, new PanelDismissalTS(e));
		panelPrincipall.setComponentAt(2, new PanelSubjectsTS(e));
		panelPrincipall.setComponentAt(3, new PanelEvaluationsTS(e, root));
		panelPrincipall.setComponentAt(4, new PanelEscalafonTS(e));
		panelPrincipall.setComponentAt(5, new PanelReportsTS(e,root));
		root.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		
		try {
			if(ServicesLocator.getStudentServices().validatePromotion()) {
				Notifications.getInstance().show(Notifications.Type.INFO, Location.BOTTOM_RIGHT, 5500,  "La funcionalidad de promoción de los estudiantes\n se encuentra actualmente disponible.\n Para poder realizar esta operación seleccione\n la promoción en la pestaña de <Alumnos>,\n en la esquina superior derecha.");
			}
		} catch (ClassNotFoundException | SQLException e1) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
		}
	}
}
