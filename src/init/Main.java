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
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					SplashScreen ss = new SplashScreen();
					ss.setVisible(true);
				}
			});
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error Fatal", JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
