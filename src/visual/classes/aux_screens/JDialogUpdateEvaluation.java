package visual.classes.aux_screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

import componentes.BotonAnimacion;
import definitions.VisualDefinitions;
import definitions.VisualErrors;
import dto.StudentDTO;
import raven.toast.Notifications;
import raven.toast.Notifications.Location;
import services.ServicesLocator;
import utils.ColorScheme;
import visual.classes.main_screens.MainScreenTeachingSecretary;
import visual.classes.panels_main_screens_teaching_secretary.PanelEvaluationsTS;
import visual.models.comboboxs.CalificationSpecialComboBoxModel;

public class JDialogUpdateEvaluation extends JDialogGeneral {
	private static final long serialVersionUID = 1L;
	private List<StudentDTO> stds;
	private JComboBox<String> evaluation;

	public JDialogUpdateEvaluation(ColorScheme e, JFrame padre) {
		super("Actualizar evaluación", e, padre); 
		panelContenedor.setBounds(0, 45, 700, 165);
		this.setBounds(100, 100, VisualDefinitions.DIMENSION_DIALOGS.width, 210);
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

		evaluation = new JComboBox<String>();
		evaluation.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				evaluation.putClientProperty("JComponent.outline", null);
			}
		});
		try {
			evaluation.setModel(new CalificationSpecialComboBoxModel(ServicesLocator.getTypeOfEvaluationServices().getAllTypeOfEvaluation()));
		} catch (ClassNotFoundException | SQLException e3) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
		}
		evaluation.setSelectedIndex(0);
		evaluation.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
		evaluation.setBounds(126, 63, 564, 20);
		panelContenedor.add(evaluation);

		JLabel pLbl_1 = new JLabel("Evaluaci\u00F3n:");
		pLbl_1.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		pLbl_1.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		pLbl_1.setBounds(20, 60, 670, 26);
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
		cancelar.setBounds(420, 120, 130, 26);
		panelContenedor.add(cancelar);

		BotonAnimacion aceptar = new BotonAnimacion();
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validateInterface()) {
					try {
						ServicesLocator.getEvaluationServices().updateEvaluation(PanelEvaluationsTS.aux((String)evaluation.getSelectedItem()));
						dispose();
						MainScreenTeachingSecretary.reset();
						Notifications.getInstance().show(Notifications.Type.SUCCESS, Location.BOTTOM_RIGHT, 3500, "Evaluación actualizada con éxito");
					} catch (ClassNotFoundException | SQLException e1) {
						Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
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
		
		JLabel lblNewLabel = new JLabel("Cambiar Evaluaci\u00F3n");
		lblNewLabel.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		lblNewLabel.setFont(new Font("Roboto Black", Font.PLAIN, 21));
		lblNewLabel.setBounds(10, 24, 680, 25);
		panelContenedor.add(lblNewLabel);
	}

	public boolean validateInterface() {
		boolean v = true;
		
		if(evaluation.getSelectedIndex()<=0) {
			v = false;
			evaluation.putClientProperty("JComponent.outline", "error");
		}
		

		return v;
	}
}
