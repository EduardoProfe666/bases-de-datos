package init;

import java.awt.EventQueue;
import java.util.Locale;

import javax.swing.JOptionPane;

import com.formdev.flatlaf.FlatLightLaf;

import visual.classes.main_screens.SplashScreen;

/**
 * Initialization of the app
 * 
 * @author Lilian Rojas
 * @author Eduardo González
 *
 */
public class Main {

	public static void main(String[] args) {
		Locale.setDefault(new Locale("es"));
		FlatLightLaf.setup();
		
		
		try {
//			File f = new File("db.env");
//			if(!f.exists()) {
//				throw new RuntimeException("No se encuentra el fichero <db.env> con la información de acceso a la base de datos");
//			}
//			f = new File("favicon.png");
//			if(!f.exists()) {
//				throw new RuntimeException("No se encuentra el fichero <favicon.png> con el logo de la aplicación");
//			}
//			f  = new File("reports/r1_alumnos.jasper");
//			if(!f.exists()) {
//				throw new RuntimeException("No se encuentran los ficheros de los reportes");
//			}
//			
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					SplashScreen ss = new SplashScreen();
					ss.setVisible(true);
					
//					Autentication aa = new Autentication();
//					aa.setVisible(true);
				}
			});
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error Fatal", JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
