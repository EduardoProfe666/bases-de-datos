package visual.classes.main_screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDeepOceanIJTheme;

import componentes.Imagen;
import componentes.PanelAnimacionCurvas;
import definitions.VisualDefinitions;
import java.awt.Toolkit;

/**
 * JDialog that models the app's SplashScreen
 * 
 * @author Lilian Rojas
 * @author Eduardo González
 *
 */
public class SplashScreen extends JDialog {

	private static final long serialVersionUID = 1L;
	private PanelAnimacionCurvas basePanel;
	private Imagen logo;
	private JProgressBar progressBar;
	private JLabel progressState;


	public SplashScreen() {
		setTitle("Control Docente");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("/visual/icons/favicon.png")));
		FlatMaterialDeepOceanIJTheme.setup();
		
		UIManager.put( "ProgressBar.arc", 20);
		
		this.setBounds(100, 100, VisualDefinitions.DIMENSION_SPLASH_SCREEN.width, VisualDefinitions.DIMENSION_SPLASH_SCREEN.height);
		this.setUndecorated(true);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent evt) {
				stateProgram();
			}
		});
		
		basePanel = new PanelAnimacionCurvas(VisualDefinitions.GRADIENT_START_SP,VisualDefinitions.GRADIENT_END_SP, VisualDefinitions.COLOR_ANIM_START_SP, VisualDefinitions.COLOR_ANIM_END_SP);
		basePanel.iniciarAnimacion(); 
		basePanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(basePanel);
		
		logo = new Imagen(null);
		logo.setImagen(new ImageIcon(SplashScreen.class.getResource("/visual/img/logo.png")));
		logo.setBounds(131, 29, 250, 250);
		
	    progressBar = new JProgressBar();
	    progressBar.setForeground(VisualDefinitions.COLOR_PROGRESS_BAR);
	    progressBar.setBounds(144, 285, 223, 17);
		
		progressState = new JLabel("Cargando ...");
		progressState.setBounds(40, 313, 431, 36);
		progressState.setHorizontalAlignment(SwingConstants.CENTER);
		progressState.setForeground(Color.BLACK);
		progressState.setFont(new Font("Roboto Medium", Font.PLAIN, 19));
		basePanel.setLayout(null);
		basePanel.add(logo);
		basePanel.add(progressBar);
		basePanel.add(progressState);
		
		this.setLocationRelativeTo(null);
		
	}
	
	private void updateState(int n) {
		this.progressState.setText(printState(n));
		this.progressBar.setValue(n);
	}
	private void stateProgram() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Random r = new Random();
					for(int n=0;n<=100;n++) {
						Thread.sleep(r.nextInt(11)+(long)20);
						if(r.nextBoolean())
							Thread.sleep(VisualDefinitions.DELAY_PROGRESS_BAR);
						updateState(n);
					}
					Thread.sleep(450);
					closeScreen();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	private String printState(int n) {
		String r = "";
		
		if(n<=20) 
			r = "Cargando Sistema ...";
		else if(n<=45) 
			r = "Conectando ...";
		else if(n<=68) 
			r = "Sincronizando con la Base de Datos ...";
		else if(n<=90) 
			r = "Inicializando Interfaz ...";
		else 
			r = "Lanzando sistema";
			
		return r;
	}
	private void closeScreen() {
		try {
			basePanel.detenerAnimacion();
			this.dispose();
			Autentication login = new Autentication();
			login.setVisible(true);
		}catch(Exception e) {
			stateProgram();
		}
	}
}
