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
import java.util.Comparator;
import java.util.LinkedList;
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
import dto.EvaluationDTO;
import dto.TypeOfEvaluationDTO;
import raven.glasspanepopup.GlassPanePopup;
import raven.glasspanepopup.Option;
import raven.toast.Notifications;
import raven.toast.Notifications.Location;
import sample.message.MessageSinCancel;
import sample.message.OptionConstructor;
import services.ServicesLocator;
import utils.Auxiliar;
import utils.ColorScheme;
import visual.classes.aux_screens.JDialogUpdateEvaluation;
import visual.classes.panels_main_screen_basic.BasicPanelMainScreen;
import visual.classes.panels_main_screen_student.PanelEscalafonStudent;
import visual.models.comboboxs.AcademicCourseComboBoxModel;
import visual.models.comboboxs.CalificationComboBoxModel;
import visual.models.comboboxs.GroupComboBoxModel;
import visual.models.comboboxs.SubjectComboBoxModel;
import visual.models.comboboxs.YearComboBoxModel;
import visual.models.tables.EvaluationsTSTableModel;

/**
 * JPanel that models the evaluations screen of the TS's appart.
 * @author Lilian Rojas
 * @author Eduardo González
 *
 */
public class PanelEvaluationsTS extends BasicPanelMainScreen{
	private static final long serialVersionUID = 1L;
	private JLabel evaluationsLbl;
	private static JTable table;
	private JButton btnReset;
	private JButton botonAyudaBusq;
	private JButton btnEdit;
	private static JComboBox<String> course;
	private static JComboBox<String> year;
	private static JComboBox<String> group;
	private static JComboBox<String> subject;
	private static JComboBox<String> evaluation;
	private static List<EvaluationDTO> evaluations;
	private static List<EvaluationDTO> listEvaluations;
	private static EvaluationsTSTableModel modelTable;
	private static TableRowSorter<EvaluationsTSTableModel> rowSorter;
	private static boolean bool = true;

	public PanelEvaluationsTS(ColorScheme e, JFrame root) {
		
		btnEdit = new JButton("");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ef) {
				if(table.getSelectedRow()!=-1) {
					JDialogUpdateEvaluation dialog = new JDialogUpdateEvaluation(e, root);
					dialog.setVisible(true);
				}
				else
					Notifications.getInstance().show(Notifications.Type.WARNING, Location.BOTTOM_RIGHT, 3500, "Seleccione correctamente la evaluación que desea actualizar");
			}
		});
		btnEdit.setToolTipText("Actualizar Evaluación");
		btnEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEdit.setIcon(Auxiliar.adjustImage(new Dimension(36,36), PanelEscalafonStudent.class.getResource("/visual/icons/edit0.png")));
		btnEdit.setRolloverIcon(Auxiliar.adjustImage(new Dimension(36,36), PanelEscalafonStudent.class.getResource("/visual/icons/edit1.png")));
		btnEdit.setContentAreaFilled(false);
		btnEdit.setBorder(null);
		btnEdit.setBounds(1108, 231, 36, 36);
		add(btnEdit);

		JButton btnHelpUs = new JButton("");
		btnHelpUs.setToolTipText("Ayuda");
		btnHelpUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				Option o = OptionConstructor.constructOption(e.getPanelMovilBase(), false);
				MessageSinCancel m = new MessageSinCancel("Ayuda", "Puede hacer uso del botón agregar/editar para poder modificar o agregar una evaluación al alumno seleccionado");
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
		btnHelpUs.setBounds(1154, 231, 36, 36);
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
				MessageSinCancel m = new MessageSinCancel("Ayuda", "Seleccione el curso escolar, el año académico, el grupo y la asignatura para poder filtrar la tabla de estudiantes. "
						+ "Además puede filtrar los estudiantes con una evaluación específica");
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
		} catch (ClassNotFoundException | SQLException e2) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
		}
		course.setSelectedIndex(0);
		course.setBounds(150, 151, 311, 22);
		add(course);

		evaluationsLbl = new JLabel("Evaluaciones");
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
		lblAsignatura.setBounds(10, 147, 451, 31);
		add(lblAsignatura);

		JLabel lblListadoDeEvaluaciones = new JLabel("Listado de Evaluaciones");
		lblListadoDeEvaluaciones.setFont(new Font("Roboto Medium", Font.BOLD, 24));
		lblListadoDeEvaluaciones.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		lblListadoDeEvaluaciones.setBounds(10, 231, 1180, 45);
		add(lblListadoDeEvaluaciones);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 287, 1180, 277);
		add(scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modelTable = new EvaluationsTSTableModel();
		rowSorter = new TableRowSorter<>(modelTable);
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
				if(year.getSelectedIndex()>0) {
					try {
						int y = ServicesLocator.getAcademicYearServices().getYearsInt().get((String)year.getSelectedItem());
						subject.setModel(new SubjectComboBoxModel(ServicesLocator.getAcademicSubjectServices().getAllAcademicSubjectDistinctYear(y)));
					} catch (ClassNotFoundException | SQLException e1) {
						Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
					}
				}
				else {
					try {
						subject.setModel(new SubjectComboBoxModel(ServicesLocator.getAcademicSubjectServices().getAllAcademicSubjectDistinct()));
					} catch (ClassNotFoundException | SQLException e1) {
						Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
					}
				}
				filter();
			}
		});
		year.setModel(new YearComboBoxModel(ServicesLocator.getAcademicYearServices().getAcademicYears()));
		year.setSelectedIndex(0);
		year.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		year.setBounds(520, 151, 266, 22);
		add(year);

		JLabel lblAo = new JLabel("A\u00F1o:");
		lblAo.setFont(new Font("Roboto Medium", Font.PLAIN, 20));
		lblAo.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		lblAo.setBounds(471, 147, 315, 31);
		add(lblAo);

		group = new JComboBox<String>();
		group.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				filter();
			}
		});
		try {
			group.setModel(new GroupComboBoxModel(ServicesLocator.getGroupServices().getGroups()));
		} catch (ClassNotFoundException | SQLException e1) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
		}
		group.setSelectedIndex(0);
		group.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		group.setBounds(924, 151, 266, 22);
		add(group);

		JLabel lblGrupo = new JLabel("Grupo Actual:");
		lblGrupo.setFont(new Font("Roboto Medium", Font.PLAIN, 20));
		lblGrupo.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		lblGrupo.setBounds(796, 147, 394, 31);
		add(lblGrupo);

		subject = new JComboBox<String>();
		subject.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				filter();
			}
		});
		try {
			subject.setModel(new SubjectComboBoxModel(ServicesLocator.getAcademicSubjectServices().getAllAcademicSubjectDistinct()));
		} catch (ClassNotFoundException | SQLException e1) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
		}
		subject.setSelectedIndex(0);
		subject.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		subject.setBounds(121, 193, 278, 22);
		add(subject);

		JLabel lblAsignatura_1 = new JLabel("Asignatura:");
		lblAsignatura_1.setFont(new Font("Roboto Medium", Font.PLAIN, 20));
		lblAsignatura_1.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		lblAsignatura_1.setBounds(10, 189, 389, 31);
		add(lblAsignatura_1);

		evaluation = new JComboBox<String>();
		evaluation.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				filter();
			}
		});
		try {
			evaluation.setModel(new CalificationComboBoxModel(ServicesLocator.getTypeOfEvaluationServices().getAllTypeOfEvaluation()));
		} catch (ClassNotFoundException | SQLException e2) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
		}
		evaluation.setSelectedIndex(0);
		evaluation.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		evaluation.setBounds(520, 193, 278, 22);
		add(evaluation);

		JLabel lblAo_1 = new JLabel("Evaluaci\u00F3n:");
		lblAo_1.setFont(new Font("Roboto Medium", Font.PLAIN, 20));
		lblAo_1.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		lblAo_1.setBounds(409, 189, 389, 31);
		add(lblAo_1);

		try {
			evaluations = ServicesLocator.getEvaluationServices().getAllEvaluationTS();
		} catch (ClassNotFoundException | SQLException e1) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
		}

		reload();
	}
	public static void reload() {
		bool = false;
		course.setSelectedIndex(0);
		year.setSelectedIndex(0);
		group.setSelectedIndex(0);
		subject.setSelectedIndex(0);
		evaluation.setSelectedIndex(0);
		listEvaluations = evaluations;
		modelTable.reload(evaluations);
		bool = true;
	}

	private void filter() {
		if(bool) {
			listEvaluations = new LinkedList<EvaluationDTO>(evaluations);
			List<EvaluationDTO> aux = new LinkedList<>();

			if(course.getSelectedIndex()>0) {
				String c = (String)course.getSelectedItem();

				for(EvaluationDTO e : listEvaluations) {
					String s = e.getAcademicSubject().getYear().getSchoolCourseStart()+"-"+e.getAcademicSubject().getYear().getSchoolCourseEnd();
					if(c.equals(s))
						aux.add(e);
				}

				listEvaluations = new LinkedList<>(aux);
				aux = new LinkedList<>();
			}

			if(year.getSelectedIndex()>0) {
				int y = ServicesLocator.getAcademicYearServices().getYearsInt().get((String)year.getSelectedItem());

				for(EvaluationDTO e: listEvaluations) {
					if(y == e.getAcademicSubject().getYear().getYear())
						aux.add(e);
				}

				listEvaluations = new LinkedList<>(aux);
				aux = new LinkedList<>();
			}

			if(subject.isEnabled() && subject.getSelectedIndex()>0) {
				String s = (String)subject.getSelectedItem();

				for(EvaluationDTO e:listEvaluations) {
					if(s.equals(e.getAcademicSubject().getName()))
						aux.add(e);
				}

				listEvaluations = new LinkedList<>(aux);
				aux = new LinkedList<>();
			}

			if(group.isEnabled() && group.getSelectedIndex()>0) {
				int g = Integer.valueOf((String)group.getSelectedItem());

				for(EvaluationDTO e : listEvaluations) {
					if(g==e.getStudent().getGroup().getNumGroup())
						aux.add(e);
				}

				listEvaluations = new LinkedList<>(aux);
				aux = new LinkedList<>();
			}

			if(evaluation.getSelectedIndex()>0) {
				String ev = (String)evaluation.getSelectedItem();
				for(EvaluationDTO e : listEvaluations) {
					if(ev.equals("Sin asignar")) {
						if(e.getTypeOfEvaluation()==null)
							aux.add(e);
					}
					else if(e.getTypeOfEvaluation()!=null && e.getTypeOfEvaluation().getEvaluation().compareToIgnoreCase(ev)==0)
						aux.add(e);
				}

				listEvaluations = new LinkedList<>(aux);
				aux = new LinkedList<>();
			}

			modelTable.reload(listEvaluations);
		}
	}

	public static EvaluationDTO aux(String calification) throws ClassNotFoundException, SQLException {
		int index = rowSorter.convertRowIndexToModel(table.getSelectedRow());
		EvaluationDTO ev = listEvaluations.get(index);
		List<TypeOfEvaluationDTO> ts = ServicesLocator.getTypeOfEvaluationServices().getAllTypeOfEvaluation();
		TypeOfEvaluationDTO t = null;

		for(int i=0;i<ts.size() && t==null;i++) {
			if(ts.get(i).getEvaluation().equals(calification))
				t = ts.get(i);
		}

		ev.setTypeOfEvaluation(t);
		return ev;
	}
}
