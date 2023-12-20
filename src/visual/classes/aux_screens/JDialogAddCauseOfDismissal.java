package visual.classes.aux_screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import componentes.BotonAnimacion;
import definitions.VisualDefinitions;
import raven.toast.Notifications;
import raven.toast.Notifications.Location;
import services.ServicesLocator;
import utils.ColorScheme;
import utils.Validations;

public class JDialogAddCauseOfDismissal extends JDialogGeneral {
	private static final long serialVersionUID = 1L;
	private JTextField cause;

	public JDialogAddCauseOfDismissal(ColorScheme e, JFrame padre) {
		super("Agregar causa de baja", e, padre); 
		panelContenedor.setBounds(0, 45, 700, 165);
		this.setBounds(100, 100, VisualDefinitions.DIMENSION_DIALOGS.width, 210);
		this.setLocationRelativeTo(null);
		setContentPane(panelBase);

		cause = new JTextField();
		cause.putClientProperty("JTextField.placeholderText", "Ingresa la causa de baja");
		cause.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				cause.putClientProperty("JComponent.outline", null);
			}
		});
		cause.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		cause.setBounds(145, 57, 545, 25);
		panelContenedor.add(cause);
		cause.setColumns(10);
		
		JLabel pLbl_1 = new JLabel("Causa de Baja:");
		pLbl_1.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		pLbl_1.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		pLbl_1.setBounds(20, 60, 670, 26);
		panelContenedor.add(pLbl_1);

		BotonAnimacion cancelar = new BotonAnimacion();
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JDialogUpdateStudent.reset();
			}
		});
		cancelar.setText("Cancelar");
		cancelar.setForeground(Color.BLACK);
		cancelar.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		cancelar.setEffectColor(Color.GRAY);
		cancelar.setBorder(null);
		cancelar.setBackground(Color.LIGHT_GRAY);
		cancelar.setBounds(420, 120, 130, 26);
		panelContenedor.add(cancelar);

		BotonAnimacion aceptar = new BotonAnimacion();
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validateInterface()) {
					try {
						ServicesLocator.getCauseOfDismissalServices().addCauseOfDismissal(cause.getText());
						dispose();
						JDialogUpdateStudent.reset();
						Notifications.getInstance().show(Notifications.Type.SUCCESS, Location.BOTTOM_RIGHT, 3500, "Causa de baja insertada con éxito");
					} catch (ClassNotFoundException | SQLException e1) {
						Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, "Causa de baja ya existente");
					}
					
				}
				else {
					Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, "Seleccione una de las evaluaciones existentes");
				}
			}
		});
		aceptar.setText("Aceptar");
		aceptar.setForeground(Color.WHITE);
		aceptar.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		aceptar.setEffectColor(VisualDefinitions.P11);
		aceptar.setBorder(null);
		aceptar.setBackground(new Color(255, 46, 150));
		aceptar.setBounds(560, 120, 130, 26);
		panelContenedor.add(aceptar);
		
		JLabel lblNewLabel = new JLabel("Agregar causa de baja");
		lblNewLabel.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		lblNewLabel.setFont(new Font("Roboto Black", Font.PLAIN, 21));
		lblNewLabel.setBounds(10, 24, 680, 25);
		panelContenedor.add(lblNewLabel);
	}

	public boolean validateInterface() {
		boolean v = true;
		
		if(!Validations.validateStringNotEmptyOrNull(cause.getText()) || !Validations.validateStringNotAllSpace(cause.getText()) || !Validations.validateTamString(cause.getText(), 1, 128)) {
			v = false;
			cause.putClientProperty("JComponent.outline", "error");
		}
		

		return v;
	}
}
