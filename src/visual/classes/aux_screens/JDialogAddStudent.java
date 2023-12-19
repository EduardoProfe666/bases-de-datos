package visual.classes.aux_screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import componentes.BotonAnimacion;
import definitions.VisualDefinitions;
import definitions.VisualErrors;
import raven.toast.Notifications;
import raven.toast.Notifications.Location;
import services.ServicesLocator;
import utils.ColorScheme;
import utils.Validations;
import visual.classes.main_screens.MainScreenTeachingSecretary;
import visual.models.comboboxs.GroupComboBoxModel;
import visual.models.comboboxs.YearComboBoxModel;

public class JDialogAddStudent extends JDialogGeneral{
	private static final long serialVersionUID = 1L;
	private JTextField names;
	private JTextField lastnames;
	private JComboBox<String> group;
	private JComboBox<String> year;
	private JLabel stdLbl;
	private JTextField municipal;
	private JComboBox<String> sex;

	public JDialogAddStudent(ColorScheme e, JFrame padre) {
		super("Agregar Estudiante", e, padre); 
		panelContenedor.setBounds(0, 45, 700, 275);
		this.setBounds(100, 100, VisualDefinitions.DIMENSION_DIALOGS.width, 320);
		this.setLocationRelativeTo(null);
		setContentPane(panelBase);

		names = new JTextField();
		names.putClientProperty("JTextField.placeholderText", "Ingresa los nombres");
		names.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				names.putClientProperty("JComponent.outline", null);
			}
		});
		names.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		names.setBounds(95, 60, 595, 25);
		panelContenedor.add(names);
		names.setColumns(10);

		JLabel lblCorreo = new JLabel("Nombres:");
		lblCorreo.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		lblCorreo.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		lblCorreo.setBounds(10, 63, 680, 26);
		panelContenedor.add(lblCorreo);

		lastnames = new JTextField();
		lastnames.putClientProperty("JTextField.placeholderText", "Ingresa los apellidos");
		lastnames.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				lastnames.putClientProperty("JComponent.outline", null);
			}
		});
		lastnames.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		lastnames.setColumns(10);
		lastnames.setBounds(95, 96, 595, 25);
		panelContenedor.add(lastnames);

		JLabel lblContrasea = new JLabel("Apellidos:");
		lblContrasea.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		lblContrasea.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		lblContrasea.setBounds(10, 99, 680, 26);
		panelContenedor.add(lblContrasea);

		year = new JComboBox<String>();
		year.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(year.getSelectedIndex()>0) {
					int y = ServicesLocator.getAcademicYearServices().getYearsInt().get(year.getSelectedItem());
					try {
						group.setModel(new GroupComboBoxModel(ServicesLocator.getGroupServices().getGroups(y)));
					} catch (ClassNotFoundException | SQLException e3) {
						Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
					}
					group.setSelectedIndex(0);
					group.setEnabled(true);
					stdLbl.setEnabled(true);
				}
				else {
					group.setSelectedIndex(0);
					group.setEnabled(false);
					stdLbl.setEnabled(false);
				}
				year.putClientProperty("JComponent.outline", null);
			}
		});
		year.setModel(new YearComboBoxModel(ServicesLocator.getAcademicYearServices().getAcademicYears()));
		year.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		year.setBounds(53, 181, 279, 20);
		panelContenedor.add(year);

		JLabel pLbl_1 = new JLabel("A\u00F1o:");
		pLbl_1.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		pLbl_1.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		pLbl_1.setBounds(10, 178, 322, 26);
		panelContenedor.add(pLbl_1);

		group = new JComboBox<String>();
		try {
			group.setModel(new GroupComboBoxModel(ServicesLocator.getGroupServices().getGroups()));
		} catch (ClassNotFoundException | SQLException e3) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
		}
		group.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				group.putClientProperty("JComponent.outline", null);
			}
		});
		group.setEnabled(false);
		group.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		group.setBounds(405, 181, 285, 20);
		panelContenedor.add(group);

		stdLbl = new JLabel("Grupo:");
		stdLbl.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		stdLbl.setEnabled(false);
		stdLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		stdLbl.setBounds(342, 178, 348, 26);
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
					String n = names.getText();
					String ln = lastnames.getText();
					String mun = municipal.getText();
					String s = ((String)sex.getSelectedItem()).equals("Femenino") ? "F" : "M";
					int y = ServicesLocator.getAcademicYearServices().getYearsInt().get(year.getSelectedItem());
					int g = 0;
					try {
						y = ServicesLocator.getTeachingSecretaryServices().getLastIdYear(y);
					} catch (ClassNotFoundException | SQLException e3) {
						Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
					}
					try {
						g = ServicesLocator.getTeachingSecretaryServices().getIdGroup(y, Integer.valueOf((String)group.getSelectedItem()));
					} catch (NumberFormatException | ClassNotFoundException | SQLException e4) {
						Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
					}
					
					try {
						ServicesLocator.getStudentServices().addStudent(n, s, mun, y, ln, "P", g, 1);
						dispose();
						MainScreenTeachingSecretary.reset();
						Notifications.getInstance().show(Notifications.Type.SUCCESS, Location.BOTTOM_RIGHT, 3500, "Se insertó correctamente el estudiante");
					} catch (ClassNotFoundException | SQLException e2) {
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

		JLabel lblNewLabel = new JLabel("Agregar Estudiante");
		lblNewLabel.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		lblNewLabel.setFont(new Font("Roboto Black", Font.PLAIN, 21));
		lblNewLabel.setBounds(10, 24, 680, 25);
		panelContenedor.add(lblNewLabel);

		municipal = new JTextField();
		municipal.putClientProperty("JTextField.placeholderText", "Ingresa el municipio");
		municipal.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				municipal.putClientProperty("JComponent.outline", null);
			}
		});
		municipal.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		municipal.setColumns(10);
		municipal.setBounds(105, 132, 253, 25);
		panelContenedor.add(municipal);

		JLabel lblContrasea_1 = new JLabel("Municipio:");
		lblContrasea_1.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		lblContrasea_1.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		lblContrasea_1.setBounds(10, 136, 348, 26);
		panelContenedor.add(lblContrasea_1);

		sex = new JComboBox<>();
		sex.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				sex.putClientProperty("JComponent.outline", null);
			}
		});
		sex.setModel(new DefaultComboBoxModel<String>(new String[] {"<Seleccionar el sexo>", "Femenino", "Masculino"}));
		sex.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		sex.setBounds(420, 137, 270, 20);
		panelContenedor.add(sex);

		JLabel lblContrasea_1_1 = new JLabel("Sexo:");
		lblContrasea_1_1.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		lblContrasea_1_1.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		lblContrasea_1_1.setBounds(368, 136, 322, 26);
		panelContenedor.add(lblContrasea_1_1);
	}

	public boolean validateInterface() {
		boolean v = true;

		if(!Validations.validateStringNotEmptyOrNull(names.getText()) || !Validations.validateStringNotAllSpace(names.getText()) || !Validations.validateTamString(names.getText(), 1, 256)) {
			v = false;
			names.putClientProperty("JComponent.outline", "error");
		}


		if(!Validations.validateStringNotEmptyOrNull(lastnames.getText()) || !Validations.validateStringNotAllSpace(lastnames.getText()) ||  !Validations.validateTamString(lastnames.getText(), 1, 256)) {
			v = false;
			lastnames.putClientProperty("JComponent.outline", "error");
		}


		if(!Validations.validateStringNotEmptyOrNull(municipal.getText()) || !Validations.validateStringNotAllSpace(municipal.getText()) ||  !Validations.validateTamString(municipal.getText(), 1, 64)) {
			v = false;
			municipal.putClientProperty("JComponent.outline", "error");
		}

		if(sex.getSelectedIndex()<=0) {
			v = false;
			sex.putClientProperty("JComponent.outline", "error");
		}

		if(year.getSelectedIndex()<=0) {
			v = false;
			year.putClientProperty("JComponent.outline", "error");
		}

		if(group.isEnabled() && group.getSelectedIndex()<=0) {
			v = false;
			group.putClientProperty("JComponent.outline", "error");
		}

		return v;
	}
}
