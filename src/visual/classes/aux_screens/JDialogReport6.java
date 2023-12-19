package visual.classes.aux_screens;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import componentes.BotonAnimacion;
import definitions.VisualDefinitions;
import definitions.VisualErrors;
import dto.StudentDTO;
import net.sf.jasperreports.engine.JRException;
import raven.toast.Notifications;
import raven.toast.Notifications.Location;
import services.ServicesLocator;
import utils.ColorScheme;

public class JDialogReport6 extends JDialogGeneral {
	private static final long serialVersionUID = 1L;
	private JSpinner idStd;
	private JRadioButton restringido;
	private JRadioButton complete;
	private JLabel courseLbl;
	private BotonAnimacion aceptar;
	private JPanel panel;
	private JLabel names;
	private JLabel mun;
	private JLabel sex;
	private JLabel year;
	private JLabel group;
	private List<StudentDTO> l;
	private StudentDTO st;
	
	private static final String namess = "Nombres y apellidos: ";
	private static final String munn = "Municipio: ";
	private static final String sexx = "Sexo: ";
	private static final String yearr = "Año: ";
	private static final String groupp = "Grupo: ";

	public JDialogReport6(ColorScheme e, JFrame padre) throws ClassNotFoundException, SQLException {
		super("Reporte de Certificación de Notas", e, padre); 
		panelContenedor.setBounds(0, 45, 700, 355);
		this.setBounds(100, 100, VisualDefinitions.DIMENSION_DIALOGS.width, 400);
		this.setLocationRelativeTo(null);
		setContentPane(panelBase);

		ButtonGroup btnGroup = new ButtonGroup();
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Selecci\u00F3n del estudiante", TitledBorder.LEADING, TitledBorder.TOP, new Font("Roboto Medium", Font.PLAIN, 16), null));
		panel.setBounds(10, 97, 680, 199);
		panelContenedor.add(panel);
		panel.setLayout(null);
		
		idStd = new JSpinner();
		
		l = ServicesLocator.getStudentServices().getAllStudentNotDismissal();
		Collections.sort(l, new Comparator<StudentDTO>() {

			@Override
			public int compare(StudentDTO o1, StudentDTO o2) {
				return Integer.compare(o1.getId(), o2.getId());
			}
		});
		st = l.isEmpty() ? null : l.get(0);
		idStd.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				int a = Integer.valueOf(idStd.getValue().toString());
				st = null;
				for(int i=0;i<l.size() && st==null;i++) {
					if(l.get(i).getId()==a)
						st = l.get(i);
				}
				
				if(st==null) {
					idStd.putClientProperty("JComponent.outline", "error");
					names.setEnabled(false);
					mun.setEnabled(false);
					sex.setEnabled(false);
					year.setEnabled(false);
					group.setEnabled(false);
					
					names.setText(namess);
					mun.setText(munn);
					sex.setText(sexx);
					year.setText(yearr);
					group.setText(groupp);
				}
				else {
					idStd.putClientProperty("JComponent.outline", null);
					names.setEnabled(true);
					mun.setEnabled(true);
					sex.setEnabled(true);
					year.setEnabled(true);
					group.setEnabled(true);
					
					names.setText(namess+st.getNames()+" "+st.getLastNames());
					mun.setText(munn+st.getMunicipal());
					sex.setText(sexx+(st.getSex()=='F' ? "Femenino" : "Masculino"));
					year.setText(yearr+ServicesLocator.getAcademicYearServices().getYearsString().get(st.getGroup().getYear().getYear()));
					group.setText(groupp+st.getGroup().getNumGroup()+"");
				}
				
			} 
		});
		idStd.setModel(new SpinnerNumberModel(Integer.valueOf(l.isEmpty() ? 0 : l.get(0).getId()), Integer.valueOf(l.isEmpty() ? 0 : l.get(0).getId()), Integer.valueOf(l.isEmpty() ? 0 : l.get(l.size()-1).getId()), Integer.valueOf(1)));
		idStd.setValue(l.isEmpty() ? 0 : l.get(0).getId());
		idStd.setBounds(158, 26, 302, 23);
		panel.add(idStd);
		idStd.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		
		courseLbl = new JLabel("Id del estudiante:");
		courseLbl.setBackground(Color.WHITE);
		courseLbl.setBounds(10, 29, 450, 26);
		panel.add(courseLbl);
		courseLbl.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		courseLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 18));

		restringido = new JRadioButton("Restringido");
		restringido.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		restringido.setBounds(316, 61, 130, 23);
		panelContenedor.add(restringido);
		btnGroup.add(restringido);

		complete = new JRadioButton("Completo");
		complete.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(aceptar!=null) {
					idStd.setValue(l.isEmpty() ? 0 : l.get(0).getId());
					boolean b = complete.isSelected();
					panel.setEnabled(!b);
					for(Component c : panel.getComponents())
						c.setEnabled(!b);
				}
			}
		});
		complete.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		complete.setSelected(true);
		complete.setBounds(196, 61, 118, 23);
		panelContenedor.add(complete);
		btnGroup.add(complete);

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
		cancelar.setBounds(420, 318, 130, 26);
		panelContenedor.add(cancelar);

		aceptar = new BotonAnimacion();
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validateInterface()) {
					try {
						if(complete.isSelected()) {
							ServicesLocator.getReportServices().loadReport6Unparam();
							dispose();
						}
						else {

							ServicesLocator.getReportServices().loadReport6Param(st.getId());
							dispose();
						}

					} catch (ClassNotFoundException | SQLException | JRException e1) {
						Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
					}

				}
				else {
					Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, "Seleccione correctamente uno de los estudiantes");
				}
			}
		});
		aceptar.setText("Aceptar");
		aceptar.setForeground(Color.WHITE);
		aceptar.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		aceptar.setEffectColor(VisualDefinitions.P11);
		aceptar.setBorder(null);
		aceptar.setBackground(new Color(255, 46, 150));
		aceptar.setBounds(560, 318, 130, 26);
		panelContenedor.add(aceptar);

		JLabel lblNewLabel = new JLabel("Configuraci\u00F3n del Reporte");
		lblNewLabel.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		lblNewLabel.setFont(new Font("Roboto Black", Font.PLAIN, 21));
		lblNewLabel.setBounds(10, 24, 680, 25);
		panelContenedor.add(lblNewLabel);
		
		panel.setEnabled(false);
		
		names = new JLabel("Nombres y apellidos:");
		names.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		names.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		names.setBackground(Color.WHITE);
		names.setBounds(10, 66, 660, 26);
		panel.add(names);
		
		mun = new JLabel("Municipio:");
		mun.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		mun.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		mun.setBackground(Color.WHITE);
		mun.setBounds(10, 103, 378, 26);
		panel.add(mun);
		
		sex = new JLabel("Sexo:");
		sex.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		sex.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		sex.setBackground(Color.WHITE);
		sex.setBounds(398, 103, 272, 26);
		panel.add(sex);
		
		year = new JLabel("A\u00F1o:");
		year.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		year.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		year.setBackground(Color.WHITE);
		year.setBounds(10, 140, 378, 26);
		panel.add(year);
		
		group = new JLabel("Grupo:");
		group.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		group.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		group.setBackground(Color.WHITE);
		group.setBounds(398, 140, 272, 26);
		panel.add(group);
		for(Component c : panel.getComponents())
			c.setEnabled(false);
		
		if(st!=null) {		
			names.setText(namess+st.getNames()+" "+st.getLastNames());
			mun.setText(munn+st.getMunicipal());
			sex.setText(sexx+(st.getSex()=='F' ? "Femenino" : "Masculino"));
			year.setText(yearr+ServicesLocator.getAcademicYearServices().getYearsString().get(st.getGroup().getYear().getYear()));
			group.setText(groupp+st.getGroup().getNumGroup()+"");
		} else {			
			names.setText(namess);
			mun.setText(munn);
			sex.setText(sexx);
			year.setText(yearr);
			group.setText(groupp);
		}
	}

	public boolean validateInterface() {
		boolean v = true;
		
		if(idStd.isEnabled() && st==null) {
			v = false;
			idStd.putClientProperty("JComponent.outline", "error");
		}


		return v;
	}
}
