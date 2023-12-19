package visual.classes.panels_main_screens_teaching_secretary;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableRowSorter;

import definitions.VisualErrors;
import dto.DismissalStudentDTO;
import raven.glasspanepopup.GlassPanePopup;
import raven.glasspanepopup.Option;
import raven.toast.Notifications;
import raven.toast.Notifications.Location;
import sample.message.Message;
import sample.message.MessageSinCancel;
import sample.message.OptionConstructor;
import services.ServicesLocator;
import utils.Auxiliar;
import utils.ColorScheme;
import visual.classes.main_screens.MainScreenTeachingSecretary;
import visual.classes.panels_main_screen_basic.BasicPanelMainScreen;
import visual.classes.panels_main_screen_student.PanelEscalafonStudent;
import visual.models.comboboxs.CauseOfDismissalComboBoxModel;
import visual.models.tables.DismissalStudentTableModel;

public class PanelDismissalTS  extends BasicPanelMainScreen{
	private JLabel evaluationsLbl;
	private JTable table;
	private static final long serialVersionUID = 1L;
	private JButton btnReset;
	private JButton botonAyudaBusq;
	private JButton btnDel;
	private List<DismissalStudentDTO> list;
	private List<DismissalStudentDTO> filteredList;
	private DismissalStudentTableModel modelTable;
	private TableRowSorter<DismissalStudentTableModel> rowSorter;
	private JComboBox<String> cause;
	public PanelDismissalTS(ColorScheme e) {
		
		try {
			list = ServicesLocator.getStudentServices().getAllDismissalStudent();
		} catch (ClassNotFoundException | SQLException e2) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
		}
		filteredList = new ArrayList<>(list);
		
		btnDel = new JButton("");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ef) {
				if(table.getSelectedRow()!=-1) {
					Option o = OptionConstructor.constructOption(e.getPanelMovilBase(), false);
					Message m = new Message("Eliminar Baja", "¿Está seguro que desea eliminar del sistema al estudiante baja seleccionado?");
					m.eventOK(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							GlassPanePopup.closePopupLast();
							try {
								ServicesLocator.getStudentServices().deleteDismissalStudent(filteredList.get(rowSorter.convertRowIndexToModel(table.getSelectedRow())));
								list = ServicesLocator.getStudentServices().getAllDismissalStudent();
								MainScreenTeachingSecretary.reset();
								Notifications.getInstance().show(Notifications.Type.SUCCESS, Location.BOTTOM_RIGHT, 3500, "El estudiante baja fue eliminado con éxito");
							} catch (ClassNotFoundException | SQLException e1) {
								Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
							}
						}
					});
					GlassPanePopup.showPopup(m, o);
				}
				else {
					Notifications.getInstance().show(Notifications.Type.WARNING, Location.BOTTOM_RIGHT, 3500, "Seleccione correctamente el estudiante baja que desea eliminar");
				}
			}
		});
		btnDel.setToolTipText("Eliminar Baja");
		btnDel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDel.setIcon(Auxiliar.adjustImage(new Dimension(36,36), PanelEscalafonStudent.class.getResource("/visual/icons/erase0.png")));
		btnDel.setRolloverIcon(Auxiliar.adjustImage(new Dimension(36,36), PanelEscalafonStudent.class.getResource("/visual/icons/erase1.png")));
		btnDel.setContentAreaFilled(false);
		btnDel.setBorder(null);
		btnDel.setBounds(1108, 199, 36, 36);
		add(btnDel);
		
		JButton btnHelpUs = new JButton("");
		btnHelpUs.setToolTipText("Ayuda");
		btnHelpUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				Option o = OptionConstructor.constructOption(e.getPanelMovilBase(), false);
				MessageSinCancel m = new MessageSinCancel("Ayuda", "Puede hacer uso del botón eliminar, para borrar del sistema el estudiante baja seleccionado");
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
				MessageSinCancel m = new MessageSinCancel("Ayuda", "Seleccione la causa de baja para poder filtrar a los estudiantes");
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
		
		evaluationsLbl = new JLabel("Bajas");
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
		modelTable = new DismissalStudentTableModel();
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
		
		cause = new JComboBox<String>();
		cause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filter();
			}
		});
		try {
			cause.setModel(new CauseOfDismissalComboBoxModel(ServicesLocator.getCauseOfDismissalServices().getAllCauseOfDismissal()));
		} catch (ClassNotFoundException | SQLException e1) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
		}
		cause.setSelectedIndex(0);
		cause.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		cause.setBounds(148, 151, 354, 22);
		add(cause);
		
		JLabel lblAo = new JLabel("Causa de baja:");
		lblAo.setFont(new Font("Roboto Medium", Font.PLAIN, 20));
		lblAo.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		lblAo.setBounds(10, 147, 492, 31);
		add(lblAo);
		
		reload();
	}
	
	private void reload() {
		cause.setSelectedIndex(0);
		filteredList = list;
		modelTable.reload(list);
	}
	
	private void filter() {
		filteredList = new ArrayList<DismissalStudentDTO>(list);
		List<DismissalStudentDTO> aux = new ArrayList<>();
		
		if(cause.getSelectedIndex()>0) {
			String c = (String)cause.getSelectedItem();
			for(DismissalStudentDTO d : filteredList) {
				if(d.getCauseOfDismissal().getCause().equals(c))
					aux.add(d);
			}
			
			filteredList = new ArrayList<>(aux);
			aux = new ArrayList<>();
		}
		
		 
		modelTable.reload(filteredList);
	}
}
