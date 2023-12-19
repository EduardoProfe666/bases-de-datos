package visual.classes.aux_screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.MatteBorder;

import componentes.BotonAnimacion;
import definitions.VisualDefinitions;
import definitions.VisualErrors;
import raven.toast.Notifications;
import raven.toast.Notifications.Location;
import services.ServicesLocator;
import utils.ColorScheme;
import visual.classes.main_screens.MainScreenTeachingSecretary;

public class JDialogPromotion extends JDialogGeneral {
	private static final long serialVersionUID = 1L;
	private JSpinner course_start;
	private JSpinner course_end;

	public JDialogPromotion(ColorScheme e, JFrame padre) throws ParseException, ClassNotFoundException, SQLException {
		super("Realizar Promoción", e, padre); 
		panelContenedor.setBounds(0, 45, 700, 165);
		this.setBounds(100, 100, VisualDefinitions.DIMENSION_DIALOGS.width, 210);
		this.setLocationRelativeTo(null);
		setContentPane(panelBase);
		
		List<String> l =  ServicesLocator.getAcademicYearServices().getCourses();
		int start = 2023;
		int end = 2023;
		if(l.size()>0) {
			Collections.sort(l);
			Collections.reverse(l);
			String s = l.get(0);
			start = Integer.valueOf(s.substring(0, 4));
			end = Integer.valueOf(s.substring(5,9));
		}
		
		course_start = new JSpinner();
		course_start.setModel(new SpinnerNumberModel(new Integer(start), new Integer(1900), new Integer(2200), new Integer(1)));
		course_start.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				course_start.putClientProperty("JComponent.outline", null);
			} 
		});
		
		course_end = new JSpinner();
		course_end.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				course_end.putClientProperty("JComponent.outline", null);
			}
		});
		course_end.setModel(new SpinnerNumberModel(new Integer(end), new Integer(1900), new Integer(2200), new Integer(1)));
		course_end.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		course_end.setBounds(543, 57, 147, 25);
		panelContenedor.add(course_end);
		course_start.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		course_start.setBounds(196, 57, 161, 25);
		panelContenedor.add(course_start);

		JLabel pLbl_1 = new JLabel("Inicio Curso Escolar:");
		pLbl_1.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		pLbl_1.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		pLbl_1.setBounds(20, 60, 337, 26);
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
		cancelar.setBounds(420, 120, 130, 26);
		panelContenedor.add(cancelar);

		BotonAnimacion aceptar = new BotonAnimacion();
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(validateInterface()) {
						if(ServicesLocator.getStudentServices().validatePromotion()) {
							int cs = Integer.valueOf(String.valueOf(course_start.getValue()));
							int ce = Integer.valueOf(String.valueOf(course_end.getValue()));
							ServicesLocator.getStudentServices().makePromotion(cs, ce);
							dispose();
							MainScreenTeachingSecretary.reset();
							Notifications.getInstance().show(Notifications.Type.SUCCESS, Location.BOTTOM_RIGHT, 3500, "Promoción realizada con éxito");
						}
						else {
							Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, "El servicio de promoción no se encuentra disponible.");
						}
						
					}
					else {
						Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, "El curso introducido no es válido");
					}
				} catch (ClassNotFoundException | SQLException e1) {
					Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
				}
			}
		});
		aceptar.setText("Aceptar");
		aceptar.setForeground(Color.WHITE);
		aceptar.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		aceptar.setEffectColor(VisualDefinitions.P11);
		aceptar.setBorder(null);
		aceptar.setBackground(new Color(255, 46, 150));
		aceptar.setBounds(560, 120, 130, 26);
		panelContenedor.add(aceptar);
		
		JLabel lblNewLabel = new JLabel("Realizar Promoci\u00F3n");
		lblNewLabel.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		lblNewLabel.setFont(new Font("Roboto Black", Font.PLAIN, 21));
		lblNewLabel.setBounds(10, 24, 680, 25);
		panelContenedor.add(lblNewLabel);
		
		JLabel pLbl_1_1 = new JLabel("Fin Curso Escolar:");
		pLbl_1_1.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		pLbl_1_1.setBounds(389, 60, 301, 26);
		panelContenedor.add(pLbl_1_1);
	}

	public boolean validateInterface() throws ClassNotFoundException, SQLException {
		boolean v = true;
		int cs = Integer.valueOf(String.valueOf(course_start.getValue()));
		int ce = Integer.valueOf(String.valueOf(course_end.getValue()));
		
		if(!ServicesLocator.getStudentServices().validateCourse(cs, ce)) {
			v = false;
			course_start.putClientProperty("JComponent.outline", "error");
			course_end.putClientProperty("JComponent.outline", "error");
		}

		return v;
	}
}
