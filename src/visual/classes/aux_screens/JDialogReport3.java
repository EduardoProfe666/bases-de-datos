package visual.classes.aux_screens;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.border.MatteBorder;

import componentes.BotonAnimacion;
import definitions.VisualDefinitions;
import definitions.VisualErrors;
import net.sf.jasperreports.engine.JRException;
import raven.toast.Notifications;
import raven.toast.Notifications.Location;
import services.ServicesLocator;
import utils.ColorScheme;
import visual.models.comboboxs.AcademicCourseComboBoxModel;

public class JDialogReport3 extends JDialogGeneral {
	private static final long serialVersionUID = 1L;
	private JComboBox<String> course;
	private JRadioButton restringido;
	private JRadioButton complete;
	private JLabel courseLbl;

	public JDialogReport3(ColorScheme e, JFrame padre) {
		super("Reporte de Evaluaciones", e, padre); 
		panelContenedor.setBounds(0, 45, 700, 185);
		this.setBounds(100, 100, VisualDefinitions.DIMENSION_DIALOGS.width, 230);
		this.setLocationRelativeTo(null);
		setContentPane(panelBase);

		ButtonGroup btnGroup = new ButtonGroup();

		course = new JComboBox<>();
		course.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				course.putClientProperty("JComponent.outline", null);
			}
		});
		course.setEnabled(false);
		course.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		course.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		try {
			course.setModel(new AcademicCourseComboBoxModel(ServicesLocator.getAcademicYearServices().getCourses()));
		} catch (ClassNotFoundException | SQLException e1) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
		}
		course.setSelectedIndex(0);
		course.setBounds(144, 97, 302, 23);
		panelContenedor.add(course);
		
		courseLbl = new JLabel("Curso Escolar:");
		courseLbl.setEnabled(false);
		courseLbl.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		courseLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		courseLbl.setBounds(20, 100, 670, 26);
		panelContenedor.add(courseLbl);

		restringido = new JRadioButton("Restringido");
		restringido.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		restringido.setBounds(316, 61, 130, 23);
		panelContenedor.add(restringido);
		btnGroup.add(restringido);

		complete = new JRadioButton("Completo");
		complete.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				course.setEnabled(!complete.isSelected());
				courseLbl.setEnabled(!complete.isSelected());
				course.setSelectedIndex(0);
			}
		});
		complete.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		complete.setSelected(true);
		complete.setBounds(196, 61, 118, 23);
		panelContenedor.add(complete);
		btnGroup.add(complete);

		JLabel pLbl_1 = new JLabel("Alcance del Reporte:");
		pLbl_1.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		pLbl_1.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		pLbl_1.setBounds(20, 60, 432, 26);
		panelContenedor.add(pLbl_1);

		BotonAnimacion cancelar = new BotonAnimacion();
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelar.setText("Cancelar");
		cancelar.setForeground(Color.BLACK);
		cancelar.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		cancelar.setEffectColor(Color.GRAY);
		cancelar.setBorder(null);
		cancelar.setBackground(Color.LIGHT_GRAY);
		cancelar.setBounds(420, 137, 130, 26);
		panelContenedor.add(cancelar);

		BotonAnimacion aceptar = new BotonAnimacion();
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validateInterface()) {
					try {
						if(complete.isSelected()) {
							ServicesLocator.getReportServices().loadReport3Unparam();
							dispose();
						}
						else {
							String s = (String)course.getSelectedItem();
							int start = Integer.valueOf(s.substring(0, 4));
							int end = Integer.valueOf(s.substring(5,9));

							ServicesLocator.getReportServices().loadReport3Param(start, end);
							dispose();
						}

					} catch (ClassNotFoundException | SQLException | JRException e1) {
						Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
					}

				}
				else {
					Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, "Seleccione uno de los cursos existentes");
				}
			}
		});
		aceptar.setText("Aceptar");
		aceptar.setForeground(Color.WHITE);
		aceptar.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		aceptar.setEffectColor(VisualDefinitions.P11);
		aceptar.setBorder(null);
		aceptar.setBackground(new Color(255, 46, 150));
		aceptar.setBounds(560, 137, 130, 26);
		panelContenedor.add(aceptar);

		JLabel lblNewLabel = new JLabel("Configuraci\u00F3n del Reporte");
		lblNewLabel.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		lblNewLabel.setFont(new Font("Roboto Black", Font.PLAIN, 21));
		lblNewLabel.setBounds(10, 24, 680, 25);
		panelContenedor.add(lblNewLabel);

	}

	public boolean validateInterface() {
		boolean v = true;

		if(course.isEnabled() && course.getSelectedIndex()<=0) {
			v = false;
			course.putClientProperty("JComponent.outline", "error");
		}


		return v;
	}
}
