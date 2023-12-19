package visual.classes.aux_screens;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class JDialogReport7 extends JDialogGeneral {
	private static final long serialVersionUID = 1L;
	private JComboBox<String> course_start;
	private JLabel course_startLbl;
	private JComboBox<String> course_end;
	private List<String> l;
	private JLabel course_endLbl2;

	public JDialogReport7(ColorScheme e, JFrame padre) throws ClassNotFoundException, SQLException {
		super("Reporte de Desaprobados", e, padre); 
		panelContenedor.setBounds(0, 45, 700, 185);
		this.setBounds(100, 100, VisualDefinitions.DIMENSION_DIALOGS.width, 230);
		this.setLocationRelativeTo(null);
		setContentPane(panelBase);
		
		l = ServicesLocator.getAcademicYearServices().getCourses();
		Collections.sort(l);
		
		course_end = new JComboBox<String>();
		course_end.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				course_end.putClientProperty("JComponent.outline", null);
			}
			});
		course_end.setModel(new AcademicCourseComboBoxModel(l));
		course_end.setSelectedIndex(0);
		course_end.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		course_end.setEnabled(false);
		course_end.setBounds(153, 97, 537, 23);
		panelContenedor.add(course_end);

		course_endLbl2 = new JLabel("Curso de Inicio:");
		course_endLbl2.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		course_endLbl2.setEnabled(false);
		course_endLbl2.setBounds(20, 100, 670, 26);
		panelContenedor.add(course_endLbl2);
		
		
		
		course_start = new JComboBox<>();
		course_start.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				course_start.putClientProperty("JComponent.outline", null);
					if(course_start.getSelectedIndex()<=0) {
						course_end.setEnabled(false);
						course_end.setSelectedIndex(0);
						course_endLbl2.setEnabled(false);
					}
					else {
						course_end.setEnabled(true);
						course_end.setSelectedIndex(0);
						course_endLbl2.setEnabled(true);
						course_end.setModel(new AcademicCourseComboBoxModel(l.subList(course_start.getSelectedIndex()-1, l.size())));
					}
				}
			});
		course_start.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		course_start.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		course_start.setModel(new AcademicCourseComboBoxModel(l));
		course_start.setSelectedIndex(0);
		course_start.setBounds(153, 60, 537, 23);
		panelContenedor.add(course_start);
		
		course_startLbl = new JLabel("Curso de Inicio:");
		course_startLbl.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		course_startLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		course_startLbl.setBounds(20, 63, 670, 26);
		panelContenedor.add(course_startLbl);

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
						int start = Integer.valueOf(((String)course_start.getSelectedItem()).substring(0,4));
						int end = Integer.valueOf(((String)course_end.getSelectedItem()).substring(5,9));
						ServicesLocator.getReportServices().loadReport7Param(start, end);
						dispose();
					} catch (ClassNotFoundException | SQLException | JRException e1) {
						Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
					}
				}
				else {
					Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, "Seleccione correctamente el rango de cursos");
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

			if(course_start.getSelectedIndex()<=0) {
				v = false;
				course_start.putClientProperty("JComponent.outline", "error");
			}
			
			if(course_end.isEnabled() && course_end.getSelectedIndex()<=0) {
				v = false;
				course_end.putClientProperty("JComponent.outline", "error");
			}


			return v;
		}
	}
