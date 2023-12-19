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
	private PanelOpcion opcionAlumnos;
	private JComponent panelSeleccionado;
	private PanelOpcion opcionAsignaturas;
	private PanelOpcion opcionEvaluaciones;
	private PanelOpcion opcionEscalafon;
	private PanelOpcion opcionReportes;
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
		
		opcionAlumnos = new PanelOpcion();
		opcionAlumnos.setBorder(new MatteBorder(0, 2, 0, 0, new Color(0,0,0)));
		opcionAlumnos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		opcionAlumnos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent es) {
				opcionAlumnos.setBackground(color_scheme.getPanelOpcionHover());
			}
			@Override
			public void mouseExited(MouseEvent eS) {
				if(opcionAlumnos.equals(panelSeleccionado))
					opcionAlumnos.setBackground(color_scheme.getPanelOpcionSeleccionado());
				else
					opcionAlumnos.setBackground(color_scheme.getPanelContenedorOpciones());
			}
			@Override
			public void mouseClicked(MouseEvent es) {
				panelPrincipall.setSelectedIndex(0);
				opcionLbl.setText("ALUMNOS");
				panelSeleccionado.setBackground(color_scheme.getPanelContenedorOpciones());
				panelSeleccionado = opcionAlumnos;
				panelSeleccionado.setBackground(color_scheme.getPanelOpcionSeleccionado());
			}
		});
		opcionAlumnos.setLayout(null);
		opcionAlumnos.setSeleccionado(true);
		opcionAlumnos.setOpaque(true);
		opcionAlumnos.setBackground(color_scheme.getPanelContenedorOpciones());
		opcionAlumnos.setBounds(0, 11, 300, 57);
		optionsPanel.add(opcionAlumnos);
		panelSeleccionado = opcionAlumnos;
		panelSeleccionado.setBackground(color_scheme.getPanelOpcionSeleccionado());
		
		Imagen logoAlumnos = new Imagen(new ImageIcon(MainScreenBasic.class.getResource("/visual/icons/std.png")));
		logoAlumnos.setBounds(67, 9, 38, 38);
		opcionAlumnos.add(logoAlumnos);

		JLabel textoAlumnos = new JLabel("ALUMNOS");
		textoAlumnos.setForeground(color_scheme.getPanelOpcionTexto());
		textoAlumnos.setHorizontalTextPosition(SwingConstants.CENTER);
		textoAlumnos.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		textoAlumnos.setBounds(126, 12, 168, 33);
		opcionAlumnos.add(textoAlumnos);
		
		opcionAsignaturas = new PanelOpcion();
		opcionAsignaturas.setBorder(new MatteBorder(0, 2, 0, 0, new Color(0,0,0)));
		opcionAsignaturas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		opcionAsignaturas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent es) {
				opcionAsignaturas.setBackground(color_scheme.getPanelOpcionHover());
			}
			@Override
			public void mouseExited(MouseEvent eS) {
				if(opcionAsignaturas.equals(panelSeleccionado))
					opcionAsignaturas.setBackground(color_scheme.getPanelOpcionSeleccionado());
				else
					opcionAsignaturas.setBackground(color_scheme.getPanelContenedorOpciones());
			}
			@Override
			public void mouseClicked(MouseEvent es) {
				panelPrincipall.setSelectedIndex(2);
				opcionLbl.setText("ASIGNATURAS");
				panelSeleccionado.setBackground(color_scheme.getPanelContenedorOpciones());
				panelSeleccionado = opcionAsignaturas;
				panelSeleccionado.setBackground(color_scheme.getPanelOpcionSeleccionado());
			}
		});
		opcionAsignaturas.setLayout(null);
		opcionAsignaturas.setSeleccionado(true);
		opcionAsignaturas.setOpaque(true);
		opcionAsignaturas.setBackground(color_scheme.getPanelContenedorOpciones());
		opcionAsignaturas.setBounds(0, 127, 300, 57);
		optionsPanel.add(opcionAsignaturas);

		Imagen logoAsignaturas = new Imagen(new ImageIcon(MainScreenBasic.class.getResource("/visual/icons/sub.png")));
		logoAsignaturas.setBounds(67, 9, 38, 38);
		opcionAsignaturas.add(logoAsignaturas);

		JLabel textoAsignaturas = new JLabel("ASIGNATURAS");
		textoAsignaturas.setForeground(color_scheme.getPanelOpcionTexto());
		textoAsignaturas.setHorizontalTextPosition(SwingConstants.CENTER);
		textoAsignaturas.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		textoAsignaturas.setBounds(126, 12, 168, 33);
		opcionAsignaturas.add(textoAsignaturas);
		
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
				panelPrincipall.setSelectedIndex(3);
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
		opcionEvaluaciones.setBounds(0, 185, 300, 57);
		optionsPanel.add(opcionEvaluaciones);

		Imagen logoEvaluaciones = new Imagen(new ImageIcon(MainScreenBasic.class.getResource("/visual/icons/eval.png")));
		logoEvaluaciones.setBounds(67, 9, 38, 38);
		opcionEvaluaciones.add(logoEvaluaciones);

		JLabel textoEvaluaciones = new JLabel("EVALUACIONES");
		textoEvaluaciones.setForeground(color_scheme.getPanelOpcionTexto());
		textoEvaluaciones.setHorizontalTextPosition(SwingConstants.CENTER);
		textoEvaluaciones.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		textoEvaluaciones.setBounds(126, 12, 168, 33);
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
				panelPrincipall.setSelectedIndex(4);
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
		opcionEscalafon.setBounds(0, 243, 300, 57);
		optionsPanel.add(opcionEscalafon);

		Imagen logoEscalafon = new Imagen(new ImageIcon(MainScreenBasic.class.getResource("/visual/icons/escalafon.png")));
		logoEscalafon.setBounds(67, 9, 38, 38);
		opcionEscalafon.add(logoEscalafon);

		JLabel textoEscalafon = new JLabel("ESCALAFÓN");
		textoEscalafon.setForeground(color_scheme.getPanelOpcionTexto());
		textoEscalafon.setHorizontalTextPosition(SwingConstants.CENTER);
		textoEscalafon.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		textoEscalafon.setBounds(126, 12, 168, 33);
		opcionEscalafon.add(textoEscalafon);
		
		opcionReportes = new PanelOpcion();
		opcionReportes.setBorder(new MatteBorder(0, 2, 0, 0, new Color(0,0,0)));
		opcionReportes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		opcionReportes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent es) {
				opcionReportes.setBackground(color_scheme.getPanelOpcionHover());
			}
			@Override
			public void mouseExited(MouseEvent eS) {
				if(opcionReportes.equals(panelSeleccionado))
					opcionReportes.setBackground(color_scheme.getPanelOpcionSeleccionado());
				else
					opcionReportes.setBackground(color_scheme.getPanelContenedorOpciones());
			}
			@Override
			public void mouseClicked(MouseEvent es) {
				panelPrincipall.setSelectedIndex(5);
				opcionLbl.setText("REPORTES");
				panelSeleccionado.setBackground(color_scheme.getPanelContenedorOpciones());
				panelSeleccionado = opcionReportes;
				panelSeleccionado.setBackground(color_scheme.getPanelOpcionSeleccionado());
			}
		});
		opcionReportes.setLayout(null);
		opcionReportes.setSeleccionado(true);
		opcionReportes.setOpaque(true);
		opcionReportes.setBackground(color_scheme.getPanelContenedorOpciones());
		opcionReportes.setBounds(0, 300, 300, 57);
		optionsPanel.add(opcionReportes);

		Imagen logoReportes = new Imagen(new ImageIcon(MainScreenBasic.class.getResource("/visual/icons/reports.png")));
		logoReportes.setBounds(67, 9, 38, 38);
		opcionReportes.add(logoReportes);

		JLabel textoReportes = new JLabel("REPORTES");
		textoReportes.setForeground(color_scheme.getPanelOpcionTexto());
		textoReportes.setHorizontalTextPosition(SwingConstants.CENTER);
		textoReportes.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		textoReportes.setBounds(126, 12, 168, 33);
		opcionReportes.add(textoReportes);
		
		optionDismissal = new PanelOpcion();
		optionDismissal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent es) {
				optionDismissal.setBackground(color_scheme.getPanelOpcionHover());
			}
			@Override
			public void mouseExited(MouseEvent eS) {
				if(optionDismissal.equals(panelSeleccionado))
					optionDismissal.setBackground(color_scheme.getPanelOpcionSeleccionado());
				else
					optionDismissal.setBackground(color_scheme.getPanelContenedorOpciones());
			}
			@Override
			public void mouseClicked(MouseEvent es) {
				panelPrincipall.setSelectedIndex(1);
				opcionLbl.setText("BAJAS");
				panelSeleccionado.setBackground(color_scheme.getPanelContenedorOpciones());
				panelSeleccionado = optionDismissal;
				panelSeleccionado.setBackground(color_scheme.getPanelOpcionSeleccionado());
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
