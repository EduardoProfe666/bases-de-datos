package visual.classes.aux_screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.border.MatteBorder;

import componentes.BotonAnimacion;
import definitions.VisualDefinitions;
import definitions.VisualErrors;
import net.sf.jasperreports.engine.JRException;
import raven.toast.Notifications;
import raven.toast.Notifications.Location;
import services.ServicesLocator;
import utils.ColorScheme;

public class JDialogReport5 extends JDialogGeneral {
	private static final long serialVersionUID = 1L;
	private JRadioButton group;
	private JRadioButton annual;

	public JDialogReport5(ColorScheme e, JFrame padre) {
		super("Reporte de Escalafón", e, padre); 
		panelContenedor.setBounds(0, 45, 700, 185);
		this.setBounds(100, 100, VisualDefinitions.DIMENSION_DIALOGS.width, 230);
		this.setLocationRelativeTo(null);
		setContentPane(panelBase);

		ButtonGroup btnGroup = new ButtonGroup();

		group = new JRadioButton("Por Grupo");
		group.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		group.setBounds(284, 61, 119, 23);
		panelContenedor.add(group);
		btnGroup.add(group);

		annual = new JRadioButton("Anual");
		annual.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		annual.setSelected(true);
		annual.setBounds(196, 61, 86, 23);
		panelContenedor.add(annual);
		btnGroup.add(annual);

		JLabel pLbl_1 = new JLabel("Alcance del Reporte:");
		pLbl_1.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		pLbl_1.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		pLbl_1.setBounds(20, 60, 432, 26);
		panelContenedor.add(pLbl_1);

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
		cancelar.setBounds(420, 137, 130, 26);
		panelContenedor.add(cancelar);

		BotonAnimacion aceptar = new BotonAnimacion();
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(annual.isSelected()) {
						ServicesLocator.getReportServices().loadReport5A();
						dispose();
					}
					else {
						ServicesLocator.getReportServices().loadReport5G();
						dispose();
					}

				} catch (ClassNotFoundException | SQLException | JRException e1) {
					Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);


				}
			}
		});
		aceptar.setText("Aceptar");
		aceptar.setForeground(Color.WHITE);
		aceptar.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		aceptar.setEffectColor(VisualDefinitions.P11);
		aceptar.setBorder(null);
		aceptar.setBackground(new Color(255, 46, 150));
		aceptar.setBounds(560, 137, 130, 26);
		panelContenedor.add(aceptar);

		JLabel lblNewLabel = new JLabel("Configuraci\u00F3n del Reporte");
		lblNewLabel.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		lblNewLabel.setFont(new Font("Roboto Black", Font.PLAIN, 21));
		lblNewLabel.setBounds(10, 24, 680, 25);
		panelContenedor.add(lblNewLabel);

	}
}
