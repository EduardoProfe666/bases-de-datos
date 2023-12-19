package visual.classes.panels_main_screen_admin;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
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
import dto.UserDTO;
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
import visual.classes.aux_screens.JDialogAddUser;
import visual.classes.panels_main_screen_basic.BasicPanelMainScreen;
import visual.classes.panels_main_screen_student.PanelEscalafonStudent;
import visual.models.comboboxs.RoleComboBoxModel;
import visual.models.tables.UsersTableModel;

/**
 * JPanel that models the users screen of the Admin's appart.
 * @author Lilian Rojas
 * @author Eduardo González
 *
 */
public class PanelUsersAdmin extends BasicPanelMainScreen{
	private JLabel evaluationsLbl;
	private JTable table;
	private static final long serialVersionUID = 1L;
	private JButton btnReset;
	private JButton botonAyudaBusq;
	private static UsersTableModel modelTable;
	private TableRowSorter<UsersTableModel> rowSorter;
	private RoleComboBoxModel model;
	private static JComboBox<String> rol;
	private static List<UserDTO> users;

	public PanelUsersAdmin(ColorScheme e, JFrame parent) {

		JButton btnAdd = new JButton("");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ef) {
				JDialogAddUser dialog = new JDialogAddUser(e, parent);
				dialog.setVisible(true);
			}
		});
		btnAdd.setToolTipText("Agregar Usuario");
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.setIcon(Auxiliar.adjustImage(new Dimension(36,36), PanelEscalafonStudent.class.getResource("/visual/icons/add01.png")));
		btnAdd.setRolloverIcon(Auxiliar.adjustImage(new Dimension(36,36), PanelEscalafonStudent.class.getResource("/visual/icons/add02.png")));
		btnAdd.setContentAreaFilled(false);
		btnAdd.setBorder(null);
		btnAdd.setBounds(1062, 199, 36, 36);
		add(btnAdd);

		JButton btnDel = new JButton("");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ef) {
				if(table.getSelectedRow()!=-1) {
					Option o = OptionConstructor.constructOption(e.getPanelMovilBase(), false);
					Message m = new Message("Eliminar Usuario", "¿Está seguro que desea eliminar el usuario seleccionado?");
					m.eventOK(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							GlassPanePopup.closePopupLast();
							int idUser = users.get(rowSorter.convertRowIndexToModel(table.getSelectedRow())).getId();
							try {
								ServicesLocator.getUserServices().deleteUser(idUser);
								reload();
								Notifications.getInstance().show(Notifications.Type.SUCCESS, Location.BOTTOM_RIGHT, 3500, "El usuario fue eliminado con éxito");
							} catch (ClassNotFoundException | SQLException e1) {
								Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, "No es posible eliminar el usuario seleccionado");
							}
						}
					});
					GlassPanePopup.showPopup(m, o);
				}
				else {
					Notifications.getInstance().show(Notifications.Type.WARNING, Location.BOTTOM_RIGHT, 3500, "Seleccione correctamente el usuario que desea eliminar");
				}
			}
		});
		btnDel.setToolTipText("Eliminar Usuario");
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
				MessageSinCancel m = new MessageSinCancel("Ayuda", "Puede hacer uso del botón agregar para poder insertar nuevos usuarios. El botón eliminar permite dar de baja al usuario seleccionado en la tabla");
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
				try {
					reload();
				} catch (ClassNotFoundException | SQLException e1) {
					Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
				}
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
				MessageSinCancel m = new MessageSinCancel("Ayuda", "Seleccione los datos deseados para poder filtrar el listado de usuarios. La tabla se actualizará con los datos de los usuarios.");
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
		
		try {
			model = new RoleComboBoxModel(ServicesLocator.getRoleServices().getAllRolesStrings());
		} catch (ClassNotFoundException | SQLException e2) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
		}
		rol = new JComboBox<>();
		rol.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(rol.getSelectedIndex()>0) {
					try {
						users = ServicesLocator.getUserServices().usersFilter((String)rol.getSelectedItem());
						modelTable.reload(users);
					} catch (ClassNotFoundException | SQLException e1) {
						Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
					}
				} else
					try {
						users = ServicesLocator.getUserServices().getAllUser();
						modelTable.reload(users);
					} catch (ClassNotFoundException | SQLException e1) {
						Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
					}
			}
		});
		rol.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rol.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		rol.setModel(model);
		rol.setSelectedIndex(0);
		rol.setBounds(150, 151, 249, 22);
		add(rol);

		evaluationsLbl = new JLabel("Usuarios");
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

		JLabel lblAsignatura = new JLabel("Rol de Usuario:");
		lblAsignatura.setFont(new Font("Roboto Medium", Font.PLAIN, 20));
		lblAsignatura.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
		lblAsignatura.setBounds(10, 147, 389, 31);
		add(lblAsignatura);

		JLabel lblListadoDeEvaluaciones = new JLabel("Listado de Usuarios");
		lblListadoDeEvaluaciones.setFont(new Font("Roboto Medium", Font.BOLD, 24));
		lblListadoDeEvaluaciones.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		lblListadoDeEvaluaciones.setBounds(10, 199, 1180, 45);
		add(lblListadoDeEvaluaciones);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 255, 1180, 309);
		add(scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modelTable = new UsersTableModel();
		rowSorter = new TableRowSorter<>(modelTable);
		rowSorter.toggleSortOrder(0);
		table.setModel(modelTable);
		table.setRowSorter(rowSorter);
		table.getTableHeader().setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		table.setSelectionForeground(e.getSeleccionTextoTabla());
		table.setSelectionBackground(e.getSeleccionFondoTabla());
		table.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		scrollPane.setViewportView(table);
		
		try {
			reload();
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void reload() throws ClassNotFoundException, SQLException {
		rol.setSelectedIndex(0);
		users = ServicesLocator.getUserServices().getAllUser();
		modelTable.reload(users);
	}

}
