package visual.classes.panels_main_screens_teaching_secretary;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableRowSorter;

import definitions.VisualErrors;
import dto.StudentDTO;
import raven.glasspanepopup.GlassPanePopup;
import raven.glasspanepopup.Option;
import raven.toast.Notifications;
import raven.toast.Notifications.Location;
import sample.message.MessageSinCancel;
import sample.message.OptionConstructor;
import services.ServicesLocator;
import utils.Auxiliar;
import utils.ColorScheme;
import visual.classes.aux_screens.JDialogAddStudent;
import visual.classes.aux_screens.JDialogPromotion;
import visual.classes.aux_screens.JDialogUpdateStudent;
import visual.classes.panels_main_screen_basic.BasicPanelMainScreen;
import visual.classes.panels_main_screen_student.PanelEscalafonStudent;
import visual.components.PromotionButton;
import visual.models.comboboxs.GroupComboBoxModel;
import visual.models.comboboxs.YearComboBoxModel;
import visual.models.tables.StudentTableModel;

/**
 * JPanel that models the students screen of the TS's appart.
 * @author Lilian Rojas
 * @author Eduardo González
 *
 */
public class PanelStudentsTS extends BasicPanelMainScreen{
	private JLabel evaluationsLbl;
	private JTable table;
	private static final long serialVersionUID = 1L;
	private JButton btnReset;
	private JButton botonAyudaBusq;
	private JButton btnEdit;
	private static JComboBox<String> year;
	private static JComboBox<String> group;
	private static StudentTableModel modelTable;
	private TableRowSorter<StudentTableModel> rowSorter;
	private static List<StudentDTO> list;
	private static List<StudentDTO> filteredList;
	private PromotionButton promoted;
	public PanelStudentsTS(ColorScheme e, JFrame root) {

		try {
			list = ServicesLocator.getStudentServices().getAllStudent();
		} catch (ClassNotFoundException | SQLException e2) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
		}
		filteredList = new ArrayList<>(list);
		
		JButton btnAdd = new JButton("");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ef) {
				JDialogAddStudent dialog = new JDialogAddStudent(e, root);
				dialog.setVisible(true);
			}
		});
		
		promoted = new PromotionButton();
		promoted.setIcon(Auxiliar.adjustImage(new Dimension(40,40), PanelEscalafonStudent.class.getResource("/visual/icons/prom1.png")));
		promoted.setRolloverIcon(Auxiliar.adjustImage(new Dimension(40,40), PanelEscalafonStudent.class.getResource("/visual/icons/prom2.png")));
		promoted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ef) {
				try {
					JDialogPromotion dialog = new JDialogPromotion(e, root);
					dialog.setVisible(true);
				} catch (ParseException | ClassNotFoundException | SQLException e) {
					Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
				}
			}
		});
		try {
			promoted.setEnabled(ServicesLocator.getStudentServices().validatePromotion());
		} catch (ClassNotFoundException | SQLException e2) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
		}
		promoted.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		promoted.setToolTipText("Promoción");
		promoted.setContentAreaFilled(false);
		promoted.setBorder(null);
		promoted.setBounds(1150, 34, 40, 40);
		add(promoted);
		
		btnAdd.setToolTipText("Agregar Alumno");
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.setIcon(Auxiliar.adjustImage(new Dimension(36,36), PanelEscalafonStudent.class.getResource("/visual/icons/add01.png")));
		btnAdd.setRolloverIcon(Auxiliar.adjustImage(new Dimension(36,36), PanelEscalafonStudent.class.getResource("/visual/icons/add02.png")));
		btnAdd.setContentAreaFilled(false);
		btnAdd.setBorder(null);
		btnAdd.setBounds(1062, 199, 36, 36);
		add(btnAdd);
		
		btnEdit = new JButton("");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ef) {
				if(table.getSelectedRow()!=-1) {
					JDialogUpdateStudent dialog = new JDialogUpdateStudent(e, root, filteredList.get(rowSorter.convertRowIndexToModel(table.getSelectedRow())));
					dialog.setVisible(true);
				}
				else {
					Notifications.getInstance().show(Notifications.Type.WARNING, Location.BOTTOM_RIGHT, 3500, "Seleccione correctamente el estudiante baja que desea eliminar");
				}
				
			}
		});
		btnEdit.setToolTipText("Editar Alumno");
		btnEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEdit.setIcon(Auxiliar.adjustImage(new Dimension(36,36), PanelEscalafonStudent.class.getResource("/visual/icons/edit0.png")));
		btnEdit.setRolloverIcon(Auxiliar.adjustImage(new Dimension(36,36), PanelEscalafonStudent.class.getResource("/visual/icons/edit1.png")));
		btnEdit.setContentAreaFilled(false);
		btnEdit.setBorder(null);
		btnEdit.setBounds(1108, 199, 36, 36);
		add(btnEdit);
		
		JButton btnHelpUs = new JButton("");
		btnHelpUs.setToolTipText("Ayuda");
		btnHelpUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				Option o = OptionConstructor.constructOption(e.getPanelMovilBase(), false);
				MessageSinCancel m = new MessageSinCancel("Ayuda", "Puede hacer uso del botón agregar para poder insertar nuevos alumnos. El botón editar permite "
						+ "modificar los datos del alumno.");
				m.eventOK(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						GlassPanePopup.closePopupLast();
					}
				});
				GlassPanePopup.showPopup(m, o);
				
			}
		});
		btnHelpUs.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHelpUs.setIcon(Auxiliar.adjustImage(new Dimension(36,36), PanelEscalafonStudent.class.getResource("/visual/icons/help0.png")));
		btnHelpUs.setRolloverIcon(Auxiliar.adjustImage(new Dimension(36,36), PanelEscalafonStudent.class.getResource("/visual/icons/help1.png")));
		btnHelpUs.setContentAreaFilled(false);
		btnHelpUs.setBorder(null);
		btnHelpUs.setBounds(1154, 199, 36, 36);
		add(btnHelpUs);
		
		
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
				MessageSinCancel m = new MessageSinCancel("Ayuda", "Seleccione el año académico y el grupo para poder filtrar la tabla de estudiantes.");
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
		
		evaluationsLbl = new JLabel("Alumnos");
		evaluationsLbl.setFont(new Font("Roboto Medium", Font.BOLD, 28));
//		evaluationsLbl.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		evaluationsLbl.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		evaluationsLbl.setBounds(10, 35, 1180, 45);
		add(evaluationsLbl);
		
		JLabel buscLbl = new JLabel("Seleccionar A\u00F1o/Grupo");
		buscLbl.setFont(new Font("Roboto Medium", Font.BOLD, 24));
		buscLbl.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		buscLbl.setBounds(10, 91, 1180, 45);
		add(buscLbl);
		
		JLabel lblListadoDeEvaluaciones = new JLabel("Listado de Alumnos");
		lblListadoDeEvaluaciones.setFont(new Font("Roboto Medium", Font.BOLD, 24));
		lblListadoDeEvaluaciones.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		lblListadoDeEvaluaciones.setBounds(10, 199, 1180, 45);
		add(lblListadoDeEvaluaciones);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 255, 1180, 309);
		add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modelTable = new StudentTableModel();
		rowSorter = new TableRowSorter<>(modelTable);
		rowSorter.setComparator(1, Auxiliar.getCompInteger());
		rowSorter.setComparator(0, new Comparator<String>() {

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
		
		group = new JComboBox<String>();
		group.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filter();
			}
		});
		group.setEnabled(false);
		try {
			group.setModel(new GroupComboBoxModel(ServicesLocator.getGroupServices().getGroups()));
		} catch (ClassNotFoundException | SQLException e1) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
		}
		group.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		group.setBounds(479, 151, 312, 22);
		add(group);
		
		year = new JComboBox<String>();
		year.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(year.getSelectedIndex()>0) {
					int y = ServicesLocator.getAcademicYearServices().getYearsInt().get((String)year.getSelectedItem());
					group.setEnabled(true);
					try {
						group.setModel(new GroupComboBoxModel(ServicesLocator.getGroupServices().getGroups(y)));
					} catch (ClassNotFoundException | SQLException e1) {
						Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
					}
				}
				else {
					group.setSelectedIndex(0);
					group.setEnabled(false);
				}
				filter();
			}
		});
		year.setModel(new YearComboBoxModel(ServicesLocator.getAcademicYearServices().getAcademicYears()));
		year.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		year.setBounds(59, 151, 340, 22);
		add(year);
		
		JLabel lblAo = new JLabel("A\u00F1o:");
		lblAo.setFont(new Font("Roboto Medium", Font.PLAIN, 20));
		lblAo.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		lblAo.setBounds(10, 147, 389, 31);
		add(lblAo);
		
		JLabel lblGrupo = new JLabel("Grupo:");
		lblGrupo.setFont(new Font("Roboto Medium", Font.PLAIN, 20));
		lblGrupo.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		lblGrupo.setBounds(409, 147, 382, 31);
		add(lblGrupo);
		
		reload();
	}
	
	private static void reload() {
		year.setSelectedIndex(0);
		group.setSelectedIndex(0);
		filteredList = list;
		modelTable.reload(list);
	}
	
	public static void reset() {
		try {
			list = ServicesLocator.getStudentServices().getAllStudent();
		} catch (ClassNotFoundException | SQLException e2) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
		}
		filteredList = new ArrayList<>(list);
		reload();
	}
	
	private void filter() {
		filteredList = new ArrayList<StudentDTO>(list);
		List<StudentDTO> aux = new ArrayList<>();
		
		if(year.getSelectedIndex()>0) {
			int y = ServicesLocator.getAcademicYearServices().getYearsInt().get((String)year.getSelectedItem());
			
			for(StudentDTO std : filteredList) {
				if(std.getGroup().getYear().getYear()==y)
					aux.add(std);
			}
			
			filteredList = new ArrayList<>(aux);
			aux = new ArrayList<>();
		}
		
		if(group.isEnabled() && group.getSelectedIndex()>0) {
			int g = Integer.valueOf((String)group.getSelectedItem());
			
			for(StudentDTO std : filteredList) {
				if(std.getGroup().getNumGroup()==g)
					aux.add(std);
			}
			
			filteredList = new ArrayList<>(aux);
			aux = new ArrayList<>();
		}
		 
		modelTable.reload(filteredList);
	}
}
