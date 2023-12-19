package visual.classes.panels_main_screen_student;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
import services.TSServices.AuxEscalafon;
import utils.Auxiliar;
import utils.ColorScheme;
import visual.classes.panels_main_screen_basic.BasicPanelMainScreen;
import visual.models.tables.EscalafonGroupTableModel;
import visual.models.tables.EscalafonYearTableModel;
import javax.swing.ListSelectionModel;

/**
 * JPanel that models the escalafon screen of the Student's appart.
 * @author Lilian Rojas
 * @author Eduardo González
 *
 */
public class PanelEscalafonStudent extends BasicPanelMainScreen{
	private static final long serialVersionUID = 1L;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton btnGroup;
	private JRadioButton btnYear;
	private JLabel lblListadoDeEstudiantes;
	private JTable table;
	private JButton botonAyudaBusq;
	private EscalafonGroupTableModel modelTableGroup;
	private EscalafonYearTableModel modelTableYear;
	private TableRowSorter<EscalafonGroupTableModel> rowSorterGroup;
	private TableRowSorter<EscalafonYearTableModel> rowSorterYear;
	private int indexStdGroup = -1;
	private int indexStdYear = -1;

	public PanelEscalafonStudent(ColorScheme e, StudentDTO std) {
		modelTableGroup = new EscalafonGroupTableModel();
		rowSorterGroup = new TableRowSorter<>(modelTableGroup);
		
		modelTableYear = new EscalafonYearTableModel();
		rowSorterYear = new TableRowSorter<>(modelTableYear);
		
		try {
			List<AuxEscalafon> list = ServicesLocator.getTeachingSecretaryServices().getEscalafonGroup(std.getGroup().getId(),std.getGroup().getYear().getId());
			modelTableGroup.reload(list);
			for(int i=0;i<list.size() && indexStdGroup==-1;i++) {
				if(list.get(i).getStd().getId()==std.getId())
					indexStdGroup=i;
			}
		} catch (ClassNotFoundException | SQLException e1) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
		}
		
		try {
			List<AuxEscalafon> list = ServicesLocator.getTeachingSecretaryServices().getEscalafonYear(std.getGroup().getYear().getId());
			modelTableYear.reload(list);
			for(int i=0;i<list.size() && indexStdYear==-1;i++) {
				if(list.get(i).getStd().getId()==std.getId())
					indexStdYear=i;
			}
		} catch (ClassNotFoundException | SQLException e1) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
		}
		
		botonAyudaBusq = new JButton("");
		botonAyudaBusq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				Option o = OptionConstructor.constructOption(e.getPanelMovilBase(), false);
				MessageSinCancel m = new MessageSinCancel("Ayuda", "Seleccione una de las 2 opciones de alcance del escalafón. La tabla se actualizará con el escalafón correspondiente");
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
		botonAyudaBusq.setBounds(1154, 88, 36, 36);
		add(botonAyudaBusq);

		btnYear = new JRadioButton("A\u00F1o");
		btnYear.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		btnYear.setBounds(430, 101, 90, 23);
		buttonGroup.add(btnYear);
		add(btnYear);
		
		table = new JTable();
		
		btnGroup = new JRadioButton("Grupo");
		btnGroup.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(btnGroup.isSelected()) {
					table.setModel(modelTableGroup);
					table.setRowSorter(rowSorterGroup);
					rowSorterGroup.setComparator(0,Auxiliar.getCompInteger());
					rowSorterGroup.setComparator(1, Auxiliar.getCompInteger());
					rowSorterGroup.setComparator(5, Auxiliar.getCompDouble());
					rowSorterGroup.toggleSortOrder(0);
					
					table.setRowSelectionInterval(indexStdGroup, indexStdGroup);
				}
				else {
					table.setModel(modelTableYear);
					table.setRowSorter(rowSorterYear);
					rowSorterYear.setComparator(0,Auxiliar.getCompInteger());
					rowSorterYear.setComparator(1, Auxiliar.getCompInteger());
					rowSorterYear.setComparator(5, Auxiliar.getCompDouble());
					rowSorterYear.toggleSortOrder(0);
					
					table.setRowSelectionInterval(indexStdYear, indexStdYear);
				}
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

		lblListadoDeEstudiantes = new JLabel("Listado de Estudiantes");
		lblListadoDeEstudiantes.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		lblListadoDeEstudiantes.setFont(new Font("Roboto Medium", Font.BOLD, 24));
		lblListadoDeEstudiantes.setBounds(10, 154, 1180, 45);
		add(lblListadoDeEstudiantes);


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 210, 1180, 354);
		add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				e.consume();
			}
		});
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if(btnGroup.isSelected()) {
					table.setRowSelectionInterval(indexStdGroup, indexStdGroup);
				}
				else {
					table.setRowSelectionInterval(indexStdYear, indexStdYear);
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(btnGroup.isSelected()) {
					table.setRowSelectionInterval(indexStdGroup, indexStdGroup);
				}
				else {
					table.setRowSelectionInterval(indexStdYear, indexStdYear);
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(btnGroup.isSelected()) {
					table.setRowSelectionInterval(indexStdGroup, indexStdGroup);
				}
				else {
					table.setRowSelectionInterval(indexStdYear, indexStdYear);
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if(btnGroup.isSelected()) {
					table.setRowSelectionInterval(indexStdGroup, indexStdGroup);
				}
				else {
					table.setRowSelectionInterval(indexStdYear, indexStdYear);
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnGroup.isSelected()) {
					table.setRowSelectionInterval(indexStdGroup, indexStdGroup);
				}
				else {
					table.setRowSelectionInterval(indexStdYear, indexStdYear);
				}
			}
		});
		table.setModel(modelTableGroup);
		
		table.setRowSorter(rowSorterGroup);
		table.getTableHeader().setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		table.setSelectionForeground(e.getSeleccionTextoTabla());
		table.setSelectionBackground(e.getSeleccionFondoTabla());
		table.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		scrollPane.setViewportView(table);
		
		btnYear.doClick();
		btnGroup.doClick();
	}

	
}
