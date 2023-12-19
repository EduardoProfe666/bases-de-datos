package visual.classes.aux_screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import componentes.BotonAnimacion;
import definitions.VisualDefinitions;
import definitions.VisualErrors;
import dto.StudentDTO;
import raven.toast.Notifications;
import raven.toast.Notifications.Location;
import services.ServicesLocator;
import utils.ColorScheme;
import utils.Validaciones;
import visual.classes.panels_main_screen_admin.PanelUsersAdmin;
import visual.models.comboboxs.RoleComboBoxModel;
import visual.models.comboboxs.StudentComboBoxModel;

public class JDialogAddUser extends JDialogGeneral{
	private static final long serialVersionUID = 1L;
	private JTextField correo;
	private JPasswordField password;
	private List<StudentDTO> stds;
	private JComboBox<String> std;
	private JComboBox<String> rol;
	private JLabel stdLbl;
	private JPasswordField passwordR;

	public JDialogAddUser(ColorScheme e, JFrame padre) {
		super("Agregar Usuario", e, padre); 
		panelContenedor.setBounds(0, 45, 700, 275);
		this.setBounds(100, 100, VisualDefinitions.DIMENSION_DIALOGS.width, 320);
		this.setLocationRelativeTo(null);
		setContentPane(panelBase);


		try {
			stds = ServicesLocator.getStudentServices().getAllStudent();
			Collections.sort(stds, new Comparator<StudentDTO>() {

				@Override
				public int compare(StudentDTO o1, StudentDTO o2) {
					int i = o1.getNames().compareToIgnoreCase(o2.getNames());
					if(i==0)
						i = o1.getLastNames().compareToIgnoreCase(o2.getLastNames());
					return i;
				}
			});
		} catch (ClassNotFoundException | SQLException e4) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
		}
		correo = new JTextField();
		correo.putClientProperty("JTextField.placeholderText", "Ingresa el correo");
		correo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				correo.putClientProperty("JComponent.outline", null);
			}
		});
		correo.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		correo.setBounds(77, 60, 613, 25);
		panelContenedor.add(correo);
		correo.setColumns(10);

		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		lblCorreo.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		lblCorreo.setBounds(10, 63, 680, 26);
		panelContenedor.add(lblCorreo);

		password = new JPasswordField();
		password.putClientProperty("JTextField.placeholderText", "Ingresa la contraseña");
		password.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				password.putClientProperty("JComponent.outline", null);
			}
		});
		password.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		password.setColumns(10);
		password.setBounds(120, 96, 570, 25);
		panelContenedor.add(password);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		lblContrasea.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		lblContrasea.setBounds(10, 99, 680, 26);
		panelContenedor.add(lblContrasea);

		rol = new JComboBox<String>();
		rol.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				std.setEnabled(((String)rol.getSelectedItem()).equals("Estudiante"));
				stdLbl.setEnabled(((String)rol.getSelectedItem()).equals("Estudiante"));
			}
		});
		rol.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rol.putClientProperty("JComponent.outline", null);
			}
		});
		try {
			rol.setModel(new RoleComboBoxModel(ServicesLocator.getRoleServices().getAllRolesStrings()));
		} catch (ClassNotFoundException | SQLException e3) {

		}
		rol.setSelectedIndex(0);
		rol.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		rol.setBounds(53, 195, 227, 20);
		panelContenedor.add(rol);

		JLabel pLbl_1 = new JLabel("Rol:");
		pLbl_1.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		pLbl_1.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		pLbl_1.setBounds(10, 192, 270, 26);
		panelContenedor.add(pLbl_1);

		std = new JComboBox<String>();
		std.setModel(new StudentComboBoxModel(stds));
		std.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				std.putClientProperty("JComponent.outline", null);
			}
		});
		std.setEnabled(false);
		std.setSelectedIndex(0);
		std.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		std.setBounds(405, 195, 285, 20);
		panelContenedor.add(std);

		stdLbl = new JLabel("Estudiante:");
		stdLbl.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		stdLbl.setEnabled(false);
		stdLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		stdLbl.setBounds(307, 192, 383, 26);
		panelContenedor.add(stdLbl);

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
		cancelar.setBounds(420, 226, 130, 26);
		panelContenedor.add(cancelar);

		BotonAnimacion aceptar = new BotonAnimacion();
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validateInterface()) {
					String m = correo.getText();
					String p = String.valueOf(password.getPassword());
					int idRole = 0;
					try {
						idRole = ServicesLocator.getRoleServices().getRoleByName((String)rol.getSelectedItem()).getId();
					} catch (ClassNotFoundException | SQLException e2) {
						Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
					}


					Integer idStd = null;
					if(((String) rol.getSelectedItem()).equals("Estudiante")) {
						idStd = stds.get(std.getSelectedIndex()-1).getId();
					}
					boolean b = true;
					try {
						ServicesLocator.getUserServices().addUser(idRole, m, p, idStd);
						dispose();
						Notifications.getInstance().show(Notifications.Type.SUCCESS, Location.BOTTOM_RIGHT, 3500, "Se insertó correctamente el usuario");
					} catch (ClassNotFoundException | SQLException e2) {
						b=false;
						Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, "El correo ya se encuentra registrado. Intente con otro");
					}

					try {
						if(b)
							PanelUsersAdmin.reload();
					} catch (ClassNotFoundException | SQLException e1) {
						Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
					}
					
				}
				else {
					Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, "Revise los campos marcados en rojo");
				}
			}
		});
		aceptar.setText("Aceptar");
		aceptar.setForeground(Color.WHITE);
		aceptar.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		aceptar.setEffectColor(VisualDefinitions.P11);
		aceptar.setBorder(null);
		aceptar.setBackground(new Color(255, 46, 150));
		aceptar.setBounds(560, 226, 130, 26);
		panelContenedor.add(aceptar);
		
		JLabel lblNewLabel = new JLabel("Agregar Usuario");
		lblNewLabel.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		lblNewLabel.setFont(new Font("Roboto Black", Font.PLAIN, 21));
		lblNewLabel.setBounds(10, 24, 680, 25);
		panelContenedor.add(lblNewLabel);
		
		passwordR = new JPasswordField();
		passwordR.putClientProperty("JTextField.placeholderText", "Repite la contraseña");
		passwordR.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				passwordR.putClientProperty("JComponent.outline", null);
			}
		});
		passwordR.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		passwordR.setColumns(10);
		passwordR.setBounds(182, 132, 508, 25);
		panelContenedor.add(passwordR);
		
		JLabel lblContrasea_1 = new JLabel("Repetir Contrase\u00F1a:");
		lblContrasea_1.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		lblContrasea_1.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		lblContrasea_1.setBounds(10, 136, 680, 26);
		panelContenedor.add(lblContrasea_1);
	}

	public boolean validateInterface() {
		boolean v = true;

		if(!Validaciones.validarStringNoVacio(correo.getText()) || !Validaciones.validarStringNoTodoEspacio(correo.getText()) || !Validaciones.validarTamString(correo.getText(), 1, 128)) {
			v = false;
			correo.putClientProperty("JComponent.outline", "error");
		}

		String p = String.valueOf(password.getPassword());
		if(!Validaciones.validarStringNoVacio(p) || !Validaciones.validarStringNoTodoEspacio(p) ||  !Validaciones.validarTamString(p, 1, 64)) {
			v = false;
			password.putClientProperty("JComponent.outline", "error");
		}
		
		String p1 = String.valueOf(passwordR.getPassword());
		if(!p.equals(p1)) {
			v = false;
			passwordR.putClientProperty("JComponent.outline", "error");
		}

		if(rol.getSelectedIndex()<=0) {
			v = false;
			rol.putClientProperty("JComponent.outline", "error");
		}

		if(((String)rol.getSelectedItem()).equals("Estudiante") && std.getSelectedIndex()<=0) {
			v = false;
			std.putClientProperty("JComponent.outline", "error");
		}

		return v;
	}
}
