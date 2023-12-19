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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;

import definitions.VisualErrors;
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
import visual.models.comboboxs.GroupComboBoxModel;
import visual.models.comboboxs.YearComboBoxModel;
import visual.models.tables.EscalafonGroupTableModel;
import visual.models.tables.EscalafonYearTableModel;

/**
 * JPanel that models the escalafon screen of the TS's appart.
 * @author Lilian Rojas
 * @author Eduardo González
 *
 */
public class PanelEscalafonTS extends BasicPanelMainScreen{
	private static final long serialVersionUID = 1L;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton btnGroup;
	private JRadioButton btnYear;
	private JLabel lblListadoDeEstudiantes;
	private JTable table;
	private JButton botonAyudaBusq;
	private JButton btnReset;
	private JLabel lblGrupo;
	private JComboBox<String> group;
	private JComboBox<String> year;
	private EscalafonGroupTableModel modelTableGroup;
	private EscalafonYearTableModel modelTableYear;

	public PanelEscalafonTS(ColorScheme e) {

		modelTableGroup = new EscalafonGroupTableModel();
		modelTableYear = new EscalafonYearTableModel();
		
		year = new JComboBox<String>();
		year.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(btnGroup.isSelected() && year.getSelectedIndex()>0) {
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
				load();
			}
		});
		year.setModel(new YearComboBoxModel(ServicesLocator.getAcademicYearServices().getAcademicYears()));
		year.setSelectedIndex(0);
		year.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		year.setBounds(59, 146, 340, 22);
		add(year);

		group = new JComboBox<String>();
		group.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				load();
			}
		});
		group.setEnabled(false);
		try {
			group.setModel(new GroupComboBoxModel(ServicesLocator.getGroupServices().getGroups(1)));
		} catch (ClassNotFoundException | SQLException e1) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
		}
		group.setSelectedIndex(0);
		group.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		group.setBounds(477, 146, 314, 22);
		add(group);
		btnReset = new JButton("");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
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
				MessageSinCancel m = new MessageSinCancel("Ayuda", "Seleccione una de las 2 opciones de alcance del escalafón. "
						+ "Si selecciona por grupo, debe seleccionar el año y el grupo que desea. Si por el contrario, "
						+ "se selecciona por año, debe seleccionar el año académico. La tabla se actualizará con el escalafón correspondiente");
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

		btnYear = new JRadioButton("A\u00F1o");
		btnYear.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		btnYear.setBounds(430, 101, 90, 23);
		buttonGroup.add(btnYear);
		add(btnYear);

		table = new JTable();
		lblGrupo = new JLabel();

		btnGroup = new JRadioButton("Grupo");
		btnGroup.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(btnGroup.isSelected()) {
					year.setSelectedIndex(0);
					group.setSelectedIndex(0);
					table.setModel(modelTableGroup);
					
				}
				else {
					year.setSelectedIndex(0);
					group.setSelectedIndex(0);
					table.setModel(modelTableYear);

				}
				modelTableGroup.deleteRows();
				modelTableYear.deleteRows();
			}
		});
		btnGroup.setSelected(true);
		btnGroup.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		btnGroup.setBounds(332, 101, 90, 23);
		buttonGroup.add(btnGroup);
		add(btnGroup);


		JLabel infoGeneralLbl = new JLabel("Escalafón");
		infoGeneralLbl.setFont(new Font("Roboto Medium", Font.BOLD, 28));
		infoGeneralLbl.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		//		infoGeneralLbl.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		infoGeneralLbl.setBounds(10, 35, 1180, 45);
		add(infoGeneralLbl);

		JLabel slcLbl = new JLabel("Seleccionar alcance del Escalaf\u00F3n:");
		slcLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 20));
		slcLbl.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		slcLbl.setBounds(10, 100, 1180, 31);
		add(slcLbl);

		JLabel lblAo = new JLabel("A\u00F1o:");
		lblAo.setFont(new Font("Roboto Medium", Font.PLAIN, 20));
		lblAo.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		lblAo.setBounds(10, 142, 389, 31);
		add(lblAo);

		lblListadoDeEstudiantes = new JLabel("Listado de Estudiantes");
		lblListadoDeEstudiantes.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		lblListadoDeEstudiantes.setFont(new Font("Roboto Medium", Font.BOLD, 24));
		lblListadoDeEstudiantes.setBounds(10, 197, 1180, 45);
		add(lblListadoDeEstudiantes);


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 253, 1180, 311);
		add(scrollPane);

		table = new JTable();
		table.setModel(modelTableGroup);
		table.getTableHeader().setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		table.setSelectionForeground(e.getSeleccionTextoTabla());
		table.setSelectionBackground(e.getSeleccionFondoTabla());
		table.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		scrollPane.setViewportView(table);

		lblGrupo = new JLabel("Grupo:");
		lblGrupo.setFont(new Font("Roboto Medium", Font.PLAIN, 20));
		lblGrupo.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		lblGrupo.setBounds(409, 142, 382, 31);
		add(lblGrupo);	

	}

	private void reset() {
		year.setSelectedIndex(0);
		group.setSelectedIndex(0);
		modelTableGroup.deleteRows();
		modelTableYear.deleteRows();
		btnGroup.doClick();
	}

	private void load() {
		if(btnGroup.isSelected()) {
			if(year.getSelectedIndex()>0 && group.getSelectedIndex()>0) {
				try {
					int y = ServicesLocator.getAcademicYearServices().getYearsInt().get(year.getSelectedItem());
					y = ServicesLocator.getTeachingSecretaryServices().getLastIdYear(y);
					int g = ServicesLocator.getTeachingSecretaryServices().getIdGroup(y, Integer.valueOf((String)group.getSelectedItem()));
					modelTableGroup.reload(ServicesLocator.getTeachingSecretaryServices().getEscalafonGroup(g, y));
				} catch (ClassNotFoundException | SQLException e) {
					Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
				}
			}
			else {
				modelTableGroup.deleteRows();
			}
		}
		else {
			if(year.getSelectedIndex()>0) {
				try {

					int y = ServicesLocator.getAcademicYearServices().getYearsInt().get((String)year.getSelectedItem());
					y = ServicesLocator.getTeachingSecretaryServices().getLastIdYear(y);
					modelTableYear.reload(ServicesLocator.getTeachingSecretaryServices().getEscalafonYear(y));
				} catch (ClassNotFoundException | SQLException e) {
					Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
				}
			}
			else {
				modelTableYear.deleteRows();
			}
		}
	}
}
