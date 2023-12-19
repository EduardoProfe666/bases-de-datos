package visual.classes.panels_main_screens_teaching_secretary;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

import definitions.VisualErrors;
import net.sf.jasperreports.engine.JRException;
import raven.toast.Notifications;
import raven.toast.Notifications.Location;
import services.ServicesLocator;
import utils.ColorScheme;
import visual.classes.aux_screens.JDialogReport3;
import visual.classes.aux_screens.JDialogReport4;
import visual.classes.aux_screens.JDialogReport5;
import visual.classes.aux_screens.JDialogReport6;
import visual.classes.aux_screens.JDialogReport7;
import visual.classes.aux_screens.JDialogReport8;
import visual.classes.panels_main_screen_basic.BasicPanelMainScreen;
import visual.components.ReportComponentPanel;

/**
 * JPanel that models the reports screen of the TS's appart.
 * @author Lilian Rojas
 * @author Eduardo González
 *
 */
public class PanelReportsTS extends BasicPanelMainScreen{
	private static final long serialVersionUID = 1L;
	private JLabel evaluationsLbl;
	public PanelReportsTS(ColorScheme ef, JFrame root) {
		
		evaluationsLbl = new JLabel("Selecci\u00F3n de Reportes");
		evaluationsLbl.setFont(new Font("Roboto Medium", Font.BOLD, 28));
//		evaluationsLbl.setBorder(new MatteBorder(0, 0, 2, 0, e.getBordeLbl()));
		evaluationsLbl.setBorder(new MatteBorder(0, 0, 2, 0, ef.getBordeLbl()));
		evaluationsLbl.setBounds(10, 35, 1180, 45);
		add(evaluationsLbl);
		
		ReportComponentPanel reporte1 = new ReportComponentPanel(ef, "Reporte 01", "Listado de alumnos por grupo", "/visual/icons/r1.png");
		reporte1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					ServicesLocator.getReportServices().loadReport1();
				} catch (ClassNotFoundException | SQLException | JRException e1) {
//					JOptionPane.showMessageDialog(null, e1.getMessage());
					Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
				}
			}
		});
		reporte1.setBounds(16, 91, 220, 220);
		add(reporte1);
		
		ReportComponentPanel reporte2 = new ReportComponentPanel(ef, "Reporte 02", "Listado de asignaturas de cada año en todos los cursos", "/visual/icons/r2.png");
		reporte2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					ServicesLocator.getReportServices().loadReport2();
				} catch (ClassNotFoundException | SQLException | JRException e1) {
					Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
				}
			}
		});
		reporte2.setBounds(252, 91, 220, 220);
		add(reporte2);
		
		ReportComponentPanel reporte3 = new ReportComponentPanel(ef, "Reporte 03", "Listado de evaluaciones por curso escolar y por grupo en cada asignatura, pudiendo ser de un curso específico", "/visual/icons/r3.png");
		reporte3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JDialogReport3 dialog = new JDialogReport3(ef, root);
				dialog.setVisible(true);
			}
		});
		reporte3.setBounds(488, 91, 220, 220);
		add(reporte3);
		
		ReportComponentPanel reporte4 = new ReportComponentPanel(ef, "Reporte 04", "Listado los promedios anuales/finales obtenidos por los alumnos en cada grupo", "/visual/icons/r4.png");
		reporte4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JDialogReport4 dialog = new JDialogReport4(ef, root);
				dialog.setVisible(true);
			}
		});
		reporte4.setBounds(724, 91, 220, 220);
		add(reporte4);
		
		ReportComponentPanel reporte5 = new ReportComponentPanel(ef, "Reporte 05", "Escalafón anual o de grupo", "/visual/icons/r5.png");
		reporte5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JDialogReport5 dialog = new JDialogReport5(ef, root);
				dialog.setVisible(true);
			}
		});
		reporte5.setBounds(960, 91, 220, 220);
		add(reporte5);
		
		ReportComponentPanel reporte6 = new ReportComponentPanel(ef, "Reporte 06", "Certificación de notas de todos los estudiantes o de un estudiante específico", "/visual/icons/r6.png");
		reporte6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					JDialogReport6 dialog = new JDialogReport6(ef, root);
					dialog.setVisible(true);
				} catch (ClassNotFoundException | SQLException e1) {
					Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
				}
			}
		});
		reporte6.setBounds(64, 333, 220, 220);
		add(reporte6);
		
		ReportComponentPanel reporte7 = new ReportComponentPanel(ef, "Reporte 07", "Listado de alumnos desaprobados por grupos para un rango de cursos", "/visual/icons/r7.png");
		reporte7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					JDialogReport7 dialog = new JDialogReport7(ef, root);
					dialog.setVisible(true);
				} catch (ClassNotFoundException | SQLException e1) {
					Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
				}
			}
		});
		reporte7.setBounds(348, 333, 220, 220);
		add(reporte7);
		
		ReportComponentPanel reporte8 = new ReportComponentPanel(ef, "Reporte 08", "Listado histórico de los alumnos que causan baja por año/grupo", "/visual/icons/r8.png");
		reporte8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JDialogReport8 dialog = new JDialogReport8(ef, root);
				dialog.setVisible(true);
			}
		});
		reporte8.setBounds(632, 333, 220, 220);
		add(reporte8);
		
		ReportComponentPanel reporte9 = new ReportComponentPanel(ef, "Reporte 09", "Listado de repitentes por año", "/visual/icons/r9.png");
		reporte9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					ServicesLocator.getReportServices().loadReport9();
				} catch (ClassNotFoundException | SQLException | JRException e1) {
					Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
				}
			}
		});
		reporte9.setBounds(916, 333, 220, 220);
		add(reporte9);
		
	}
}
