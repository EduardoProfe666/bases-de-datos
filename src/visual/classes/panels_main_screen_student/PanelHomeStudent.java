package visual.classes.panels_main_screen_student;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

import definitions.VisualErrors;
import dto.StudentDTO;
import raven.toast.Notifications;
import raven.toast.Notifications.Location;
import services.ServicesLocator;
import utils.ColorScheme;
import visual.classes.panels_main_screen_basic.BasicPanelMainScreen;
import visual.components.AveragePanel;

/**
 * JPanel that models the home screen of the Student's appart.
 * @author Lilian Rojas
 * @author Eduardo González
 *
 */
public class PanelHomeStudent extends BasicPanelMainScreen{
	private static final long serialVersionUID = 1L;
	private JLabel generalInfoLbl;
	
	public PanelHomeStudent(ColorScheme e, StudentDTO std) {
		
		generalInfoLbl = new JLabel("Informaci\u00F3n General");
		generalInfoLbl.setFont(new Font("Roboto Medium", Font.BOLD, 28));
		generalInfoLbl.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
//		generalInfoLbl.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		generalInfoLbl.setBounds(10, 35, 1180, 45);
		add(generalInfoLbl);
		
		JLabel nameStd = new JLabel(std.getNames() + " " + std.getLastNames());
		nameStd.setFont(new Font("Roboto Medium", Font.PLAIN, 24));
		nameStd.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		nameStd.setBounds(299, 127, 611, 45);
		add(nameStd);
		
		JLabel nameStdLbl_1 = new JLabel("Nombre del Estudiante: ");
		nameStdLbl_1.setFont(new Font("Roboto Medium", Font.BOLD, 24));
		nameStdLbl_1.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		nameStdLbl_1.setBounds(10, 127, 900, 45);
		add(nameStdLbl_1);
		
		JLabel municipalStd = new JLabel(std.getMunicipal());
		municipalStd.setFont(new Font("Roboto Medium", Font.PLAIN, 24));
		municipalStd.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		municipalStd.setBounds(151, 215, 299, 45);
		add(municipalStd);
		
		JLabel municipalStdLbl = new JLabel("Municipio:");
		municipalStdLbl.setFont(new Font("Roboto Medium", Font.BOLD, 24));
		municipalStdLbl.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		municipalStdLbl.setBounds(10, 215, 440, 45);
		add(municipalStdLbl);
		
		JLabel groupStd = new JLabel(""+std.getGroup().getNumGroup());
		groupStd.setFont(new Font("Roboto Medium", Font.PLAIN, 24));
		groupStd.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		groupStd.setBounds(891, 311, 299, 45);
		add(groupStd);
		
		JLabel groupStdLbl = new JLabel("Grupo:");
		groupStdLbl.setFont(new Font("Roboto Medium", Font.BOLD, 24));
		groupStdLbl.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		groupStdLbl.setBounds(810, 311, 380, 45);
		add(groupStdLbl);
		
		JLabel sexStd = new JLabel(std.getSex()=='F' ? "Femenino" : "Masculino");
		sexStd.setFont(new Font("Roboto Medium", Font.PLAIN, 24));
		sexStd.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		sexStd.setBounds(542, 215, 368, 45);
		add(sexStd);
		
		JLabel sexStdLbl = new JLabel("Sexo:");
		sexStdLbl.setFont(new Font("Roboto Medium", Font.BOLD, 24));
		sexStdLbl.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		sexStdLbl.setBounds(460, 215, 450, 45);
		add(sexStdLbl);
		
		String state = "";
		switch (std.getTypeStd()) {
		case 'P':
			state = "Promovido";
			break;
		case 'R':
			state = "Repitente";
			break;
		case 'D':
			state = "Baja";
			break;
		default:
			break;
		}
		JLabel stateStd = new JLabel(state);
		stateStd.setFont(new Font("Roboto Medium", Font.PLAIN, 24));
		stateStd.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		stateStd.setBounds(107, 396, 343, 45);
		add(stateStd);
		
		JLabel stateStdLbl = new JLabel("Estado:");
		stateStdLbl.setFont(new Font("Roboto Medium", Font.BOLD, 24));
		stateStdLbl.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		stateStdLbl.setBounds(10, 396, 440, 45);
		add(stateStdLbl);
		
		String anno = "";
		switch (std.getGroup().getYear().getYear()) {
		case 1:
			anno = "Primero";
			break;
		case 2:
			anno = "Segundo";
			break;
		case 3:
			anno = "Tercero";
			break;
		case 4:
			anno = "Cuarto";
		default:
			break;
		}
		JLabel yearStd = new JLabel(anno);
		yearStd.setFont(new Font("Roboto Medium", Font.PLAIN, 24));
		yearStd.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		yearStd.setBounds(485, 311, 315, 45);
		add(yearStd);
		
		JLabel yearStdLbl = new JLabel("A\u00F1o:");
		yearStdLbl.setFont(new Font("Roboto Medium", Font.BOLD, 24));
		yearStdLbl.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		yearStdLbl.setBounds(418, 311, 380, 45);
		add(yearStdLbl);
		
		JLabel courseStd = new JLabel(std.getGroup().getYear().getSchoolCourseStart()+"-"+std.getGroup().getYear().getSchoolCourseEnd());
		courseStd.setFont(new Font("Roboto Medium", Font.PLAIN, 24));
		courseStd.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		courseStd.setBounds(187, 311, 221, 45);
		add(courseStd);
		
		JLabel courseStdLbl = new JLabel("Curso Escolar:");
		courseStdLbl.setFont(new Font("Roboto Medium", Font.BOLD, 24));
		courseStdLbl.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		courseStdLbl.setBounds(10, 311, 398, 45);
		add(courseStdLbl);
		
		double avg = 0;
		try {
			avg = ServicesLocator.getStudentServices().getAvg(std.getId());
		} catch (ClassNotFoundException | SQLException e1) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
		}
		
		AveragePanel panel = new AveragePanel(e.getBordeLbl(),avg);
		panel.setBounds(963, 120, 190, 190);
		add(panel);
		
	}
}
