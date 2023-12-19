package visual.classes.panels_main_screen_student;

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
import dto.EvaluationDTO;
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
import visual.classes.panels_main_screen_basic.BasicPanelMainScreen;
import visual.models.comboboxs.CalificationComboBoxModel;
import visual.models.comboboxs.YearComboBoxModel;
import visual.models.tables.EvaluationsStudentTableModel;


/**
 * JPanel that models the evaluation's screen of the Student's appart.
 * @author Lilian Rojas
 * @author Eduardo González
 *
 */
public class PanelEvaluationsStudent extends BasicPanelMainScreen{
	private JLabel evaluationsLbl;
	private JTable table;
	private static final long serialVersionUID = 1L;
	private JComboBox<String> calification;
	private JComboBox<String> year;
	private JButton btnReset;
	private JButton botonAyudaBusq;
	private CalificationComboBoxModel calificationModel;
	private YearComboBoxModel modelYear;
	private EvaluationsStudentTableModel modelTable;
	private TableRowSorter<EvaluationsStudentTableModel> rowSorter;
	private List<EvaluationDTO> evaluations;
	private List<EvaluationDTO> listEvaluations;

	public PanelEvaluationsStudent(ColorScheme e, StudentDTO std) {

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
				MessageSinCancel m = new MessageSinCancel("Ayuda", "Seleccione entre los datos deseados para poder filtrar las evaluaciones. La tabla se actualizará con los datos de las evaluaciones.");
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

		calification = new JComboBox<>();
		calification.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				filter();
			}
		});
		calification.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		try {
			calificationModel = new CalificationComboBoxModel(ServicesLocator.getTypeOfEvaluationServices().getAllTypeOfEvaluation());
		} catch (ClassNotFoundException | SQLException e2) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
		}
		calification.setModel(calificationModel);
		calification.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		calification.setSelectedIndex(0);
		calification.setBounds(479, 151, 312, 22);
		add(calification);

		year = new JComboBox<>();
		year.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				filter();
			}
		});
		year.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		year.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		modelYear = new YearComboBoxModel(ServicesLocator.getAcademicYearServices().getAcademicYearsStd(std));
		year.setModel(modelYear);
		year.setSelectedIndex(0);
		year.setBounds(59, 151, 296, 22);
		add(year);

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

		JLabel lblAsignatura_1 = new JLabel("A\u00F1o:");
		lblAsignatura_1.setFont(new Font("Roboto Medium", Font.PLAIN, 20));
		lblAsignatura_1.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		lblAsignatura_1.setBounds(10, 147, 345, 31);
		add(lblAsignatura_1);

		JLabel lblAsignatura_2 = new JLabel("Calificaci\u00F3n:");
		lblAsignatura_2.setFont(new Font("Roboto Medium", Font.PLAIN, 20));
		lblAsignatura_2.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		lblAsignatura_2.setBounds(365, 147, 426, 31);
		add(lblAsignatura_2);

		JLabel lblListadoDeEvaluaciones = new JLabel("Listado de Evaluaciones");
		lblListadoDeEvaluaciones.setFont(new Font("Roboto Medium", Font.BOLD, 24));
		lblListadoDeEvaluaciones.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		lblListadoDeEvaluaciones.setBounds(10, 199, 1180, 45);
		add(lblListadoDeEvaluaciones);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 255, 1180, 309);
		add(scrollPane);


		table = new JTable();
		modelTable = new EvaluationsStudentTableModel();
		rowSorter = new TableRowSorter<>(modelTable);
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

		try {
			evaluations = ServicesLocator.getEvaluationServices().getEvaluationsStd(std);
		} catch (ClassNotFoundException | SQLException e1) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
		}

		reload();
	}

	private void reload() {
		listEvaluations = evaluations;
		modelTable.reload(evaluations);
		calification.setSelectedIndex(0);
		year.setSelectedIndex(0);
	}

	private void filter() {
		listEvaluations = new ArrayList<EvaluationDTO>(evaluations);
		List<EvaluationDTO> aux = new ArrayList<>();
		
		if(calification.getSelectedIndex()>0) {
			String ev = (String)calification.getSelectedItem();
			for(EvaluationDTO e : listEvaluations) {
				if(ev.equals("Sin asignar")) {
					if(e.getTypeOfEvaluation()==null)
						aux.add(e);
				}
				else if(e.getTypeOfEvaluation()!=null && e.getTypeOfEvaluation().getEvaluation().compareToIgnoreCase(ev)==0)
					aux.add(e);
			}

			listEvaluations = new ArrayList<>(aux);
			aux = new ArrayList<>();
		}

		if(year.getSelectedIndex()>0) {
			int y = ServicesLocator.getAcademicYearServices().getYearsInt().get((String)year.getSelectedItem());
			for(EvaluationDTO e : listEvaluations) {
				if(e.getAcademicSubject().getYear().getYear()==y)
					aux.add(e);
			}

			listEvaluations = new ArrayList<>(aux);
			aux = new ArrayList<>();
		}

		modelTable.reload(listEvaluations);
	}
}
