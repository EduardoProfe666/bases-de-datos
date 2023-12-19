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
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import componentes.BotonAnimacion;
import definitions.VisualDefinitions;
import definitions.VisualErrors;
import dto.CauseOfDismissalDTO;
import dto.DismissalStudentDTO;
import dto.StudentDTO;
import raven.toast.Notifications;
import raven.toast.Notifications.Location;
import services.ServicesLocator;
import utils.ColorScheme;
import utils.Validations;
import visual.classes.main_screens.MainScreenTeachingSecretary;
import visual.models.comboboxs.CauseOfDismissalSpecialComboBoxModel;
import visual.models.comboboxs.GroupSpecialComboBoxModel;
import visual.models.comboboxs.StateComboBoxModel;

public class JDialogUpdateStudent extends JDialogGeneral{
	private static final long serialVersionUID = 1L;
	private JTextField names;
	private JTextField lastnames;
	private JComboBox<String> group;
	private JComboBox<String> state;
	private JLabel stdLbl;
	private JTextField municipal;
	private JComboBox<String> sex;
	private StudentDTO std;
	private static JComboBox<String> cause;

	public JDialogUpdateStudent(final ColorScheme e, final JFrame padre, final StudentDTO std) {
		super("Modificar Estudiante", e, padre); 
		this.std = std;
		panelContenedor.setBounds(0, 45, 700, 305);
		this.setBounds(100, 100, VisualDefinitions.DIMENSION_DIALOGS.width, 350);
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

		state = new JComboBox<String>();
		state.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(state.getSelectedIndex()==2) {
					cause.setEnabled(true);
					cause.setSelectedIndex(0);
				}
				else
					cause.setEnabled(false);
				state.putClientProperty("JComponent.outline", null);
			}});
		state.setModel(new StateComboBoxModel());
		state.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		state.setBounds(73, 211, 212, 20);
		panelContenedor.add(state);

		JLabel pLbl_1 = new JLabel("Estado:");
		pLbl_1.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		pLbl_1.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		pLbl_1.setBounds(10, 208, 275, 26);
		panelContenedor.add(pLbl_1);

		group = new JComboBox<String>();
		try {
			group.setModel(new GroupSpecialComboBoxModel(ServicesLocator.getGroupServices().getGroups(std.getGroup().getYear().getYear())));
		} catch (ClassNotFoundException | SQLException e3) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
		}
		group.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				group.putClientProperty("JComponent.outline", null);
			}
		});
		group.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		group.setBounds(73, 177, 617, 20);
		panelContenedor.add(group);

		stdLbl = new JLabel("Grupo:");
		stdLbl.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		stdLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		stdLbl.setBounds(10, 174, 680, 26);
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
		cancelar.setBounds(420, 254, 130, 26);
		panelContenedor.add(cancelar);

		BotonAnimacion aceptar = new BotonAnimacion();
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validateInterface()) {
					String n = names.getText();
					String ln = lastnames.getText();
					String mun = municipal.getText();
					char s = ((String)sex.getSelectedItem()).equals("Femenino") ? 'F' : 'M';
					int g = 0;
					try {
						g = ServicesLocator.getTeachingSecretaryServices().getIdGroup(std.getGroup().getYear().getId(), Integer.valueOf((String)group.getSelectedItem()));
					} catch (NumberFormatException | ClassNotFoundException | SQLException e4) {
						Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
					}
					char st = state.getSelectedIndex()==0 ? 'P' : (state.getSelectedIndex()==1) ? 'R' : 'D';
					int cd = -1;
					if(st=='D') {
						try {
							List<CauseOfDismissalDTO> l = ServicesLocator.getCauseOfDismissalServices().getAllCauseOfDismissal();
							String c = (String)cause.getSelectedItem();
							
							for(int i=0;i<l.size() && cd==-1;i++) {
								if(l.get(i).getCause().equals(c))
									cd = l.get(i).getId();
							}
						} catch (ClassNotFoundException | SQLException e1) {
							Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
						}
						
					}
					
					std.setNames(n);
					std.setLastNames(ln);
					std.setMunicipal(mun);
					std.setSex(s);
					std.getGroup().setNumGroup(g);
					std.setTypeStd(st);
					
					try {
						ServicesLocator.getStudentServices().updateStudent(std, cd);
						dispose();
						MainScreenTeachingSecretary.reset();
						Notifications.getInstance().show(Notifications.Type.SUCCESS, Location.BOTTOM_RIGHT, 3500, "Se modificó correctamente el estudiante");
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
		aceptar.setBounds(560, 254, 130, 26);
		panelContenedor.add(aceptar);

		JLabel lblNewLabel = new JLabel("Modificar Estudiante");
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
		sex.setModel(new DefaultComboBoxModel<>(new String[] {"Femenino", "Masculino"}));
		sex.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		sex.setBounds(420, 137, 270, 20);
		panelContenedor.add(sex);

		JLabel lblContrasea_1_1 = new JLabel("Sexo:");
		lblContrasea_1_1.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		lblContrasea_1_1.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		lblContrasea_1_1.setBounds(368, 136, 322, 26);
		panelContenedor.add(lblContrasea_1_1);
		
		cause = new JComboBox<String>();
		cause.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ef) {
				cause.putClientProperty("JComponent.outline", null);
				if(cause.getSelectedIndex()==cause.getModel().getSize()-1) {
					JDialogAddCauseOfDismissal dialog = new JDialogAddCauseOfDismissal(e, padre);
					dialog.setVisible(true);
				}
			}
		});
		try {
			cause.setModel(new CauseOfDismissalSpecialComboBoxModel(ServicesLocator.getCauseOfDismissalServices().getAllCauseOfDismissal()));
		} catch (ClassNotFoundException | SQLException e1) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
		}
		cause.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		cause.setBounds(420, 211, 270, 20);
		panelContenedor.add(cause);
		
		JLabel pLbl_1_1 = new JLabel("Causa de baja:");
		pLbl_1_1.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		pLbl_1_1.setBounds(295, 208, 395, 26);
		panelContenedor.add(pLbl_1_1);
		
		try {
			initInterface();
		} catch (ClassNotFoundException | SQLException e1) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
		}
	}
	
	public static void reset() {
		try {
			cause.setModel(new CauseOfDismissalSpecialComboBoxModel(ServicesLocator.getCauseOfDismissalServices().getAllCauseOfDismissal()));
		} catch (ClassNotFoundException | SQLException e1) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
		}
	}
	
	private void initInterface() throws ClassNotFoundException, SQLException {
		names.setText(std.getNames());
		lastnames.setText(std.getLastNames());
		municipal.setText(std.getMunicipal());
		sex.setSelectedIndex(std.getSex()=='F' ? 0 : 1 );
		group.setSelectedIndex((std.getGroup().getNumGroup()%10)-1);
		state.setSelectedIndex(std.getTypeStd()=='P' ? 0 : (std.getTypeStd()=='R' ? 1 : 2));
		if(std.getTypeStd()=='D') {
			DismissalStudentDTO d = ServicesLocator.getStudentServices().getDismissalStudent(std.getId());
			cause.setEnabled(true);
			int index = -1;
			List<String> l = CauseOfDismissalSpecialComboBoxModel.toList(ServicesLocator.getCauseOfDismissalServices().getAllCauseOfDismissal());
			Collections.sort(l);
			
			for(int i=0;i<l.size() && index==-1;i++) {
				if(l.get(i).equals(d.getCauseOfDismissal().getCause()))
					index = i;
			}
			
			cause.setSelectedIndex(index);
		}
		else
			cause.setEnabled(false);
	}

	private boolean validateInterface() {
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
		
		if(cause.getSelectedIndex()==cause.getModel().getSize()-1) {
			v = false;
			cause.putClientProperty("JComponent.outline", "error");
		}

		return v;
	}
}
