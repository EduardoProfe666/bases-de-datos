package visual.classes.panels_main_screens_teaching_secretary;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableRowSorter;

import definitions.VisualErrors;
import dto.AcademicSubjectDTO;
import raven.glasspanepopup.GlassPanePopup;
import raven.glasspanepopup.Option;
import raven.toast.Notifications;
import raven.toast.Notifications.Location;
import sample.message.MessageSinCancel;
import sample.message.OptionConstructor;
import services.ServicesLocator;
import utils.Auxiliar;
import utils.ColorScheme;
import visual.classes.panels_main_screen_basic.BasicPanelMainScreen;
import visual.classes.panels_main_screen_student.PanelEscalafonStudent;
import visual.models.comboboxs.AcademicCourseComboBoxModel;
import visual.models.comboboxs.YearComboBoxModel;
import visual.models.tables.SubjectTableModel;

/**
 * JPanel that models the subjects screen of the TS's appart.
 * @author Lilian Rojas
 * @author Eduardo González
 *
 */
public class PanelSubjectsTS extends BasicPanelMainScreen{
	private JLabel evaluationsLbl;
	private JTable table;
	private static final long serialVersionUID = 1L;
	private JButton btnReset;
	private JButton botonAyudaBusq;
	private JComboBox<String> course;
	private JComboBox<String> year;
	private SubjectTableModel modelTable;
	private List<AcademicSubjectDTO> list;
	private List<AcademicSubjectDTO> filteredList;
	private TableRowSorter<SubjectTableModel> rowSorter;
	
	public PanelSubjectsTS(ColorScheme e) {
		
		try {
			list = ServicesLocator.getAcademicSubjectServices().getAllAcademicSubject();
		} catch (ClassNotFoundException | SQLException e2) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
		}
		filteredList = new ArrayList<>(list);
		
		btnReset = new JButton("");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reload();
			}
		});
		btnReset.setToolTipText("Reiniciar Buscador");
		btnReset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReset.setIcon(Auxiliar.adjustImage(new Dimension(36,36), PanelEscalafonStudent.class.getResource("/visual/icons/reset0.png")));
		btnReset.setRolloverIcon(Auxiliar.adjustImage(new Dimension(36,36), PanelEscalafonStudent.class.getResource("/visual/icons/reset1.png")));
		btnReset.setContentAreaFilled(false);
		btnReset.setBorder(null);
		btnReset.setBounds(1108, 91, 36, 36);
		add(btnReset);
		
		botonAyudaBusq = new JButton("");
		botonAyudaBusq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				Option o = OptionConstructor.constructOption(e.getPanelMovilBase(), false);
				MessageSinCancel m = new MessageSinCancel("Ayuda", "Seleccione el curso escolar y el año académico para poder filtrar los datos de la tabla de asignaturas.");
				m.eventOK(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						GlassPanePopup.closePopupLast();
					}
				});
				GlassPanePopup.showPopup(m, o);
				
			}
		});
		botonAyudaBusq.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonAyudaBusq.setIcon(Auxiliar.adjustImage(new Dimension(36,36), PanelEscalafonStudent.class.getResource("/visual/icons/help0.png")));
		botonAyudaBusq.setRolloverIcon(Auxiliar.adjustImage(new Dimension(36,36), PanelEscalafonStudent.class.getResource("/visual/icons/help1.png")));
		botonAyudaBusq.setToolTipText("Ayuda");
		botonAyudaBusq.setContentAreaFilled(false);
		botonAyudaBusq.setBorder(null);
		botonAyudaBusq.setBounds(1154, 91, 36, 36);
		add(botonAyudaBusq);
		
		course = new JComboBox<>();
		course.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				filter();
			}
		});
		course.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		course.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		try {
			course.setModel(new AcademicCourseComboBoxModel(ServicesLocator.getAcademicYearServices().getCourses()));
		} catch (ClassNotFoundException | SQLException e1) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
		}
		course.setSelectedIndex(0);
		course.setBounds(150, 151, 327, 22);
		add(course);
		
		evaluationsLbl = new JLabel("Asignaturas");
		evaluationsLbl.setFont(new Font("Roboto Medium", Font.BOLD, 28));
//		evaluationsLbl.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		evaluationsLbl.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		evaluationsLbl.setBounds(10, 35, 1180, 45);
		add(evaluationsLbl);
		
		JLabel buscLbl = new JLabel("Buscador");
		buscLbl.setFont(new Font("Roboto Medium", Font.BOLD, 24));
		buscLbl.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		buscLbl.setBounds(10, 91, 1180, 45);
		add(buscLbl);
		
		JLabel lblAsignatura = new JLabel("Curso Escolar:");
		lblAsignatura.setFont(new Font("Roboto Medium", Font.PLAIN, 20));
		lblAsignatura.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		lblAsignatura.setBounds(10, 147, 467, 31);
		add(lblAsignatura);
		
		JLabel lblListadoDeEvaluaciones = new JLabel("Listado de Asignaturas");
		lblListadoDeEvaluaciones.setFont(new Font("Roboto Medium", Font.BOLD, 24));
		lblListadoDeEvaluaciones.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		lblListadoDeEvaluaciones.setBounds(10, 199, 1180, 45);
		add(lblListadoDeEvaluaciones);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 255, 1180, 309);
		add(scrollPane);
		
		table = new JTable();
		modelTable = new SubjectTableModel();
		rowSorter = new TableRowSorter<SubjectTableModel>(modelTable);
		rowSorter.setComparator(3, Auxiliar.getCompInteger());
		rowSorter.setComparator(1, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				int a = ServicesLocator.getAcademicYearServices().getYearsInt().get(o1);
				int b = ServicesLocator.getAcademicYearServices().getYearsInt().get(o2);
				return Integer.compare(a, b);
			}
		});
		rowSorter.toggleSortOrder(0);
		table.setModel(modelTable);
		table.setRowSorter(rowSorter);
		table.getTableHeader().setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		table.setSelectionForeground(e.getSeleccionTextoTabla());
		table.setSelectionBackground(e.getSeleccionFondoTabla());
		table.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		scrollPane.setViewportView(table);
		
		year = new JComboBox<String>();
		year.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				filter();
			}
		});
		year.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		year.setModel(new YearComboBoxModel(ServicesLocator.getAcademicYearServices().getAcademicYears()));
		year.setSelectedIndex(0);
		year.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		year.setBounds(536, 151, 419, 22);
		add(year);
		
		JLabel lblAo = new JLabel("A\u00F1o:");
		lblAo.setFont(new Font("Roboto Medium", Font.PLAIN, 20));
		lblAo.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		lblAo.setBounds(487, 147, 468, 31);
		add(lblAo);
		
		reload();
	}
	
	private void reload() {
		filteredList = new ArrayList<>(list);
		modelTable.reload(filteredList);
		course.setSelectedIndex(0);
		year.setSelectedIndex(0);
	}
	
	private void filter() {
		filteredList = new ArrayList<AcademicSubjectDTO>(list);
		List<AcademicSubjectDTO> aux = new ArrayList<>();
		
		if(course.getSelectedIndex()>0) {
			String c = (String) course.getSelectedItem();
			
			for(AcademicSubjectDTO a: filteredList) {
				String ca = a.getYear().getSchoolCourseStart() + "-" + a.getYear().getSchoolCourseEnd();
				if(c.equals(ca))
					aux.add(a);
			}
			
			filteredList = new ArrayList<>(aux);
			aux = new ArrayList<>();
		}
		
		
		if(year.getSelectedIndex()>0) {
			int y = ServicesLocator.getAcademicYearServices().getYearsInt().get((String)year.getSelectedItem());
			
			for(AcademicSubjectDTO a : filteredList) {
				if(a.getYear().getYear()==y)
					aux.add(a);
			}
			
			filteredList = new ArrayList<>(aux);
			aux = new ArrayList<>();
		}
		 
		modelTable.reload(filteredList);
	}

}
