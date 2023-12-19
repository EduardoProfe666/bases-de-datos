package services;

import java.awt.Frame;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReportServices {
	protected ReportServices() {}
	
	public void loadReport1() throws ClassNotFoundException, SQLException, JRException {
		Connection con = ServicesLocator.getConnection();
		File file = new File(ReportServices.class.getResource("/reports/r1_alumnos.jasper").getFile());
		JasperReport jr = (JasperReport) JRLoader.loadObject(file);
		JasperPrint print = JasperFillManager.fillReport(jr, null,con);
		JasperViewer view = new JasperViewer(print,false,new Locale("es"));
		view.setTitle("Reporte 1. Listado de Alumnos");
		view.setAlwaysOnTop(true);
		view.setExtendedState(Frame.MAXIMIZED_BOTH);
		view.setLocationRelativeTo(null);
		view.setVisible(true);
	}
	
	public void loadReport2() throws ClassNotFoundException, SQLException, JRException {
		Connection con = ServicesLocator.getConnection();
		File file = new File(ReportServices.class.getResource("/reports/r2_asignatura.jasper").getFile());
		JasperReport jr = (JasperReport) JRLoader.loadObject(file);
		JasperPrint print = JasperFillManager.fillReport(jr, null,con);
		JasperViewer view = new JasperViewer(print,false,new Locale("es"));
		view.setTitle("Reporte 2. Listado de Asignaturas");
		view.setAlwaysOnTop(true);
		view.setExtendedState(Frame.MAXIMIZED_BOTH);
		view.setLocationRelativeTo(null);
		view.setVisible(true);
	}
	
	public void loadReport3Unparam() throws ClassNotFoundException, SQLException, JRException {
		Connection con = ServicesLocator.getConnection();
		File file = new File(ReportServices.class.getResource("/reports/r3_evaluaciones.jasper").getFile());
		JasperReport jr = (JasperReport) JRLoader.loadObject(file);
		JasperPrint print = JasperFillManager.fillReport(jr, null,con);
		JasperViewer view = new JasperViewer(print,false,new Locale("es"));
		view.setTitle("Reporte 3. Listado de Evaluaciones");
		view.setAlwaysOnTop(true);
		view.setExtendedState(Frame.MAXIMIZED_BOTH);
		view.setLocationRelativeTo(null);
		view.setVisible(true);
	}
	
	public void loadReport3Param(int course_start, int course_end) throws ClassNotFoundException, SQLException, JRException {
		HashMap<String,Object> params = new HashMap<>();
		params.put("course_start", course_start);
		params.put("course_end", course_end);
		
		Connection con = ServicesLocator.getConnection();
		File file = new File(ReportServices.class.getResource("/reports/r3_evaluacionesParam.jasper").getFile());
		JasperReport jr = (JasperReport) JRLoader.loadObject(file);
		JasperPrint print = JasperFillManager.fillReport(jr, params,con);
		JasperViewer view = new JasperViewer(print,false,new Locale("es"));
		view.setTitle("Reporte 3. Listado de Evaluaciones del curso "+course_start+"-"+course_end);
		view.setExtendedState(Frame.MAXIMIZED_BOTH);
		view.setAlwaysOnTop(true);
		view.setLocationRelativeTo(null);
		view.setVisible(true);
	}
	
	public void loadReport4A() throws ClassNotFoundException, SQLException, JRException {
		Connection con = ServicesLocator.getConnection();
		File file = new File(ReportServices.class.getResource("/reports/r4A_promedio_anual.jasper").getFile());
		JasperReport jr = (JasperReport) JRLoader.loadObject(file);
		JasperPrint print = JasperFillManager.fillReport(jr, null,con);
		JasperViewer view = new JasperViewer(print,false,new Locale("es"));
		view.setTitle("Reporte 4. Promedio anual de alumnos");
		view.setExtendedState(Frame.MAXIMIZED_BOTH);
		view.setAlwaysOnTop(true);
		view.setLocationRelativeTo(null);
		view.setVisible(true);
	}
	
	public void loadReport4F() throws ClassNotFoundException, SQLException, JRException {
		Connection con = ServicesLocator.getConnection();
		File file = new File(ReportServices.class.getResource("/reports/r4F_promedio_final.jasper").getFile());
		JasperReport jr = (JasperReport) JRLoader.loadObject(file);
		JasperPrint print = JasperFillManager.fillReport(jr, null,con);
		JasperViewer view = new JasperViewer(print,false,new Locale("es"));
		view.setTitle("Reporte 4. Promedio final de alumnos");
		view.setExtendedState(Frame.MAXIMIZED_BOTH);
		view.setAlwaysOnTop(true);
		view.setLocationRelativeTo(null);
		view.setVisible(true);
	}
	
	public void loadReport5A() throws ClassNotFoundException, SQLException, JRException {
		Connection con = ServicesLocator.getConnection();
		File file = new File(ReportServices.class.getResource("/reports/r5A_escalafon_anual.jasper").getFile());
		JasperReport jr = (JasperReport) JRLoader.loadObject(file);
		JasperPrint print = JasperFillManager.fillReport(jr, null,con);
		JasperViewer view = new JasperViewer(print,false,new Locale("es"));
		view.setTitle("Reporte 5. Escalafón Anual");
		view.setExtendedState(Frame.MAXIMIZED_BOTH);
		view.setAlwaysOnTop(true);
		view.setLocationRelativeTo(null);
		view.setVisible(true);
	}
	
	public void loadReport5G() throws ClassNotFoundException, SQLException, JRException {
		Connection con = ServicesLocator.getConnection();
		File file = new File(ReportServices.class.getResource("/reports/r5G_escalafon_grupo.jasper").getFile());
		JasperReport jr = (JasperReport) JRLoader.loadObject(file);
		JasperPrint print = JasperFillManager.fillReport(jr, null,con);
		JasperViewer view = new JasperViewer(print,false,new Locale("es"));
		view.setTitle("Reporte 5. Escalafón por grupo");
		view.setExtendedState(Frame.MAXIMIZED_BOTH);
		view.setAlwaysOnTop(true);
		view.setLocationRelativeTo(null);
		view.setVisible(true);
	}
	
	public void loadReport6Unparam() throws ClassNotFoundException, SQLException, JRException {
		Connection con = ServicesLocator.getConnection();
		File file = new File(ReportServices.class.getResource("/reports/r6_certificacion_de_notas.jasper").getFile());
		JasperReport jr = (JasperReport) JRLoader.loadObject(file);
		JasperPrint print = JasperFillManager.fillReport(jr, null,con);
		JasperViewer view = new JasperViewer(print,false,new Locale("es"));
		view.setTitle("Reporte 6. Listado de Certificación de Notas");
		view.setExtendedState(Frame.MAXIMIZED_BOTH);
		view.setAlwaysOnTop(true);
		view.setLocationRelativeTo(null);
		view.setVisible(true);
	}
	
	public void loadReport6Param(int idStd) throws ClassNotFoundException, SQLException, JRException {
		HashMap<String,Object> params = new HashMap<>();
		params.put("idStd", idStd);
		
		Connection con = ServicesLocator.getConnection();
		File file = new File(ReportServices.class.getResource("/reports/r6_certificacion_de_notasParam.jasper").getFile());
		JasperReport jr = (JasperReport) JRLoader.loadObject(file);
		JasperPrint print = JasperFillManager.fillReport(jr, params,con);
		JasperViewer view = new JasperViewer(print,false,new Locale("es"));
		view.setTitle("Reporte 6. Listado de Certificación de Notas de un estudiante");
		view.setExtendedState(Frame.MAXIMIZED_BOTH);
		view.setAlwaysOnTop(true);
		view.setLocationRelativeTo(null);
		view.setVisible(true);
	}
	
	public void loadReport7Param(int course_start, int course_end) throws ClassNotFoundException, SQLException, JRException {
		HashMap<String,Object> params = new HashMap<>();
		params.put("course_start", course_start);
		params.put("course_end", course_end);
		
		Connection con = ServicesLocator.getConnection();
		File file = new File(ReportServices.class.getResource("/reports/r7_desaprobados.jasper").getFile());
		JasperReport jr = (JasperReport) JRLoader.loadObject(file);
		JasperPrint print = JasperFillManager.fillReport(jr, params,con);
		JasperViewer view = new JasperViewer(print,false,new Locale("es"));
		view.setTitle("Reporte 7. Listado de desaprobados en el rango de "+course_start+"-"+course_end);
		view.setExtendedState(Frame.MAXIMIZED_BOTH);
		view.setAlwaysOnTop(true);
		view.setLocationRelativeTo(null);
		view.setVisible(true);
	}
	
	public void loadReport8A() throws ClassNotFoundException, SQLException, JRException {
		Connection con = ServicesLocator.getConnection();
		File file = new File(ReportServices.class.getResource("/reports/r8A_baja_anual.jasper").getFile());
		JasperReport jr = (JasperReport) JRLoader.loadObject(file);
		JasperPrint print = JasperFillManager.fillReport(jr, null,con);
		JasperViewer view = new JasperViewer(print,false,new Locale("es"));
		view.setTitle("Reporte 8. Listado histórico de bajas por año");
		view.setExtendedState(Frame.MAXIMIZED_BOTH);
		view.setAlwaysOnTop(true);
		view.setLocationRelativeTo(null);
		view.setVisible(true);
	}
	
	public void loadReport8G() throws ClassNotFoundException, SQLException, JRException {
		Connection con = ServicesLocator.getConnection();
		File file = new File(ReportServices.class.getResource("/reports/r8G_baja_grupo.jasper").getFile());
		JasperReport jr = (JasperReport) JRLoader.loadObject(file);
		JasperPrint print = JasperFillManager.fillReport(jr, null,con);
		JasperViewer view = new JasperViewer(print,false,new Locale("es"));
		view.setTitle("Reporte 8. Listado histórico de bajas por año");
		view.setExtendedState(Frame.MAXIMIZED_BOTH);
		view.setAlwaysOnTop(true);
		view.setLocationRelativeTo(null);
		view.setVisible(true);
	}
	
	public void loadReport9() throws ClassNotFoundException, SQLException, JRException {
		Connection con = ServicesLocator.getConnection();
		File file = new File(ReportServices.class.getResource("/reports/r9_repitentes.jasper").getFile());
		JasperReport jr = (JasperReport) JRLoader.loadObject(file);
		JasperPrint print = JasperFillManager.fillReport(jr, null,con);
		JasperViewer view = new JasperViewer(print,false,new Locale("es"));
		view.setTitle("Reporte 9. Listado de repitentes por año");
		view.setExtendedState(Frame.MAXIMIZED_BOTH);
		view.setAlwaysOnTop(true);
		view.setLocationRelativeTo(null);
		view.setVisible(true);
	}
	
}
