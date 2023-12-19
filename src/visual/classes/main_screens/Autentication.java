package visual.classes.main_screens;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

import com.formdev.flatlaf.FlatLightLaf;

import componentes.AvatarCircular;
import componentes.BotonAnimacion;
import componentes.ImagenAnim;
import componentes.JPasswordFieldModificado;
import componentes.JTextFieldModificado;
import definitions.VisualDefinitions;
import definitions.VisualErrors;
import dto.StudentDTO;
import dto.UserDTO;
import raven.glasspanepopup.GlassPanePopup;
import raven.glasspanepopup.Option;
import raven.toast.Notifications;
import raven.toast.Notifications.Location;
import sample.message.MessageSinCancel;
import sample.message.OptionConstructor;
import services.ServicesLocator;
import utils.Auxiliar;
import visual.components.TopPanel;

/**
 * JFrame that models the app's autentication
 * 
 * 
 * @author Lilian Rojas
 * @author Eduardo González
 *
 */
public class Autentication extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panelBase;
	private TopPanel topPanel;
	private ImagenAnim image;
	private JPanel panelLogin;
	private AvatarCircular avatar;
	private JTextFieldModificado userField;
	private JPasswordFieldModificado passwordField;
	private JLabel userLabel;
	private JLabel passwordLabel;
	private BotonAnimacion signInBtn;
	private JButton passwordBtn;
	private UserDTO user;
	private char echoCharContrasenya;
	private boolean contrasenyaVisible;
	private JPanel panel;

	public Autentication() {
		Notifications.getInstance().setJFrame(this);
		GlassPanePopup.install(this);
		this.setTitle("Conest");
		FlatLightLaf.setup();
		UIManager.put("PasswordField.showRevealButton", false);
		UIManager.put("PasswordField.showCapsLock", false);
		UIManager.put("Component.selectionBackground", VisualDefinitions.P9);
		UIManager.put("TextField.selectionBackground", VisualDefinitions.P9);
		UIManager.put("Spinner.selectionBackground", VisualDefinitions.P9);
		UIManager.put("TextField.caretForeground", VisualDefinitions.P6);
		UIManager.put("PasswordField.selectionBackground", VisualDefinitions.P9);
		UIManager.put("PasswordField.caretForeground", VisualDefinitions.P6);
		this.setUndecorated(true);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(Autentication.class.getResource("/visual/icons/favicon.png")));
		this.setBounds(100, 100, VisualDefinitions.DIMENSION_AUTENTICATION.width, VisualDefinitions.DIMENSION_AUTENTICATION.height);
		this.setBackground(new Color(255,255,255,0));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				topPanel.getMinimizeBtn().requestFocus();
			}
		});

		panelBase = new JPanel();
		panelBase.setOpaque(false);
		setContentPane(panelBase);

		topPanel = new TopPanel(VisualDefinitions.TOP_PANEL_COLOR, this, "Autenticación");

		panelLogin = new JPanel();
		panelLogin.setBackground(Color.WHITE);
		panelLogin.setBounds(507, 45, 293, 467);
		panelLogin.setBorder(new MatteBorder(0, 0, 2, 2, (Color) new Color(0, 0, 0)));

		avatar = new AvatarCircular(new ImageIcon(Autentication.class.getResource("/visual/icons/user.png")), VisualDefinitions.SIZE_AVATAR_BORDER);
		avatar.setBounds(70, 45, 150, 150);
		avatar.setForeground(VisualDefinitions.COLOR_AVATAR_BORDER);

		userLabel = new JLabel("Correo");
		userLabel.setBounds(44, 217, 203, 32);
		userLabel.setForeground(Color.BLACK);
		userLabel.setFont(new Font("Roboto Black", Font.PLAIN, 20));

		userField = new JTextFieldModificado();
		userField.setLimite(VisualDefinitions.MAX_CARACT_USER);
		userField.setBounds(44, 249, 203, 32);
		userField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					passwordField.requestFocus();
				}
			}
		});
		userField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(userField.getText().equals(VisualDefinitions.PLACEHOLDER_USER)) {
					userField.setText("");
					userField.setForeground(Color.BLACK);
					userField.setBorder(new MatteBorder(0, 0, 2, 0, VisualDefinitions.COLOR_USER_BORDER));
				}
				else
					userField.setBorder(new MatteBorder(0, 0, 2, 0, VisualDefinitions.COLOR_USER_BORDER));
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(userField.getText().isEmpty()) {
					userField.setText(VisualDefinitions.PLACEHOLDER_USER);
					userField.setForeground(Color.GRAY);
					userField.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
				}
			}
		});
		userField.setText(VisualDefinitions.PLACEHOLDER_USER);
		userField.setOpaque(false);
		userField.setForeground(Color.GRAY);
		userField.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
		userField.setColumns(10);
		userField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 0, 0)));

		passwordLabel = new JLabel("Contrase\u00F1a");
		passwordLabel.setBounds(44, 292, 203, 32);
		passwordLabel.setForeground(Color.BLACK);
		passwordLabel.setFont(new Font("Roboto Black", Font.PLAIN, 20));

		passwordField = new JPasswordFieldModificado();
		passwordField.setLimite(VisualDefinitions.MAX_CARACT_PASSWORD);
		passwordField.setBounds(44, 324, 171, 32);
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					signInBtn.doClick();
				}
			}
		});
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(String.valueOf(passwordField.getPassword()).equals(VisualDefinitions.PLACEHOLDER_PASSWORD)) {
					passwordField.setText("");
					passwordField.setForeground(Color.BLACK);
					passwordField.setBorder(new MatteBorder(0, 0, 2, 0, VisualDefinitions.COLOR_PASSWORD_BORDER));
					passwordBtn.setBorder(new MatteBorder(0, 0, 2, 0, VisualDefinitions.COLOR_PASSWORD_BORDER));
					if(contrasenyaVisible)
						passwordField.setEchoChar((char)0);
					else
						passwordField.setEchoChar(echoCharContrasenya);
				}
				else {
					passwordField.setBorder(new MatteBorder(0, 0, 2, 0, VisualDefinitions.COLOR_PASSWORD_BORDER));
					passwordBtn.setBorder(new MatteBorder(0, 0, 2, 0, VisualDefinitions.COLOR_PASSWORD_BORDER));
				}

			}
			@Override
			public void focusLost(FocusEvent e) {
				if(String.valueOf(passwordField.getPassword()).isEmpty()) {
					passwordField.setEchoChar(echoCharContrasenya);
					passwordField.setText(VisualDefinitions.PLACEHOLDER_PASSWORD);
					passwordField.setForeground(Color.GRAY);
					passwordField.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
					passwordBtn.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
				}
			}
		});
		passwordField.setText(VisualDefinitions.PLACEHOLDER_PASSWORD);
		passwordField.setOpaque(false);
		passwordField.setForeground(Color.GRAY);
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordField.setColumns(10);
		passwordField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 0, 0)));

		passwordBtn = new JButton("");
		passwordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contrasenyaVisible = !contrasenyaVisible;

				if(!String.valueOf(passwordField.getPassword()).equals(VisualDefinitions.PLACEHOLDER_PASSWORD)) {
					if(contrasenyaVisible)
						passwordField.setEchoChar((char)0);
					else
						passwordField.setEchoChar(echoCharContrasenya);
				}

				if(contrasenyaVisible) 
					passwordBtn.setIcon(Auxiliar.adjustImage(new Dimension(32,32), Autentication.class.getResource("/visual/icons/hideP1.png")));
				else 
					passwordBtn.setIcon(Auxiliar.adjustImage(new Dimension(32,32), Autentication.class.getResource("/visual/icons/showP1.png")));
			}
		});
		echoCharContrasenya = passwordField.getEchoChar();
		contrasenyaVisible = false;
		passwordBtn.setContentAreaFilled(false);
		passwordBtn.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 0, 0)));
		passwordBtn.setBounds(215, 324, 32, 32);
		passwordBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(contrasenyaVisible)
					passwordBtn.setIcon(Auxiliar.adjustImage(new Dimension(32,32), Autentication.class.getResource("/visual/icons/hideP1.png")));
				else
					passwordBtn.setIcon(Auxiliar.adjustImage(new Dimension(32,32), Autentication.class.getResource("/visual/icons/showP1.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(contrasenyaVisible)
					passwordBtn.setIcon(Auxiliar.adjustImage(new Dimension(32,32), Autentication.class.getResource("/visual/icons/hideP0.png")));
				else
					passwordBtn.setIcon(Auxiliar.adjustImage(new Dimension(32,32), Autentication.class.getResource("/visual/icons/showP0.png")));
			}

		});
		passwordBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		passwordBtn.setIcon(Auxiliar.adjustImage(new Dimension(32,32), Autentication.class.getResource("/visual/icons/showP0.png")));

		signInBtn = new BotonAnimacion();
		user = null;
		signInBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean authenticated = true;
				try {
					user = ServicesLocator.getAuthServices().authSecurity(userField.getText(), String.valueOf(passwordField.getPassword()));
				}
				catch(Exception ex) {
					authenticated = false;
					String message = ex.getMessage();
					if(ex instanceof SQLException || ex instanceof ClassNotFoundException)
						message = VisualErrors.ERROR_SQL;
					if(message.equals(VisualErrors.ERROR_CAMPO_VACIO)) {
						userField.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
						passwordField.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
						passwordBtn.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
					}
					else if(message.equals(VisualErrors.ERROR_CORREO_NO_VALIDO) || message.equals(VisualErrors.ERROR_CAMPO_VACIO_USUARIO))
						userField.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
					else if(message.equals(VisualErrors.ERROR_CONTRASENYA_NO_VALIDA) || message.equals(VisualErrors.ERROR_CAMPO_VACIO_CONTRASENYA)) {
						passwordField.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
						passwordBtn.setBorder(new MatteBorder(0, 0, 2, 0, Color.RED));
					}
					Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.BOTTOM_LEFT, 3500, message);

				}
				finally{
					if(authenticated) {
						Option o = OptionConstructor.constructOption(VisualDefinitions.TOP_PANEL_COLOR, false);
						MessageSinCancel m = new MessageSinCancel("Bienvenido/a", "Bienvenido/a de nuevo " + getWelcomeName());
						m.eventOK(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								GlassPanePopup.closePopupLast();
								terminarVentanaLogin();
							}
						});
						m.addKeyListener(new KeyAdapter() {
							@Override
							public void keyTyped(KeyEvent e) {
								GlassPanePopup.closePopupLast();
								terminarVentanaLogin();
							}
						});
						m.requestFocus();
						GlassPanePopup.showPopup(m, o);
						m.requestFocus();

					}
				}
			}
		});
		signInBtn.setBounds(44, 388, 203, 32);
		signInBtn.setEffectColor(VisualDefinitions.COLOR_BTN_EFfECT);
		signInBtn.setText("Ingresar");
		signInBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				signInBtn.setBackground(VisualDefinitions.COLOR_BTN_HOVER);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				signInBtn.setBackground(VisualDefinitions.COLOR_BTN_BASE);
			}

		});
		signInBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signInBtn.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		signInBtn.setBorder(null);
		signInBtn.setBackground(VisualDefinitions.COLOR_BTN_BASE);
		panelBase.setLayout(null);
		panelBase.add(topPanel);

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 45, 507, 467);
		panelBase.add(panel);
		panel.setLayout(null);

		image = new ImagenAnim(3500);
		image.setBounds(0, 0, 507, 467);
		panel.add(image);
		image.addImage(new ImageIcon(Autentication.class.getResource("/visual/img/logo.png")));
		image.addImage(new ImageIcon(Autentication.class.getResource("/visual/img/a02.png")));

		image.iniciarAnimacion();
		image.setBackground(Color.WHITE);
		image.setBorder(new MatteBorder(0, 2, 2, 2, (Color) new Color(0, 0, 0)));
		panelBase.add(panelLogin);
		panelLogin.setLayout(null);
		panelLogin.add(avatar);
		panelLogin.add(passwordLabel);
		panelLogin.add(userField);
		panelLogin.add(userLabel);
		panelLogin.add(signInBtn);
		panelLogin.add(passwordField);
		panelLogin.add(passwordBtn);

		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}


	private void terminarVentanaLogin() {
		image.detenerAnimacion();
		MainScreenBasic main = null;
		String role = user.getRole().getRole();
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		if(role.equals("Estudiante")) {
			try {
				StudentDTO std = ServicesLocator.getStudentServices().getStudent(user.getIdStd());
				main = new MainScreenStudent(std, user);
			} catch (ClassNotFoundException | SQLException e) {
				main = new MainScreenStudent(null, null);
			}
		}
		else if(role.equals("Secretario Docente"))
			main = new MainScreenTeachingSecretary("Máster", user);
		else 
			main = new MainScreenAdmin("Admin", user);
		
		main.setVisible(true);
		this.dispose();
		if(role.equals("Secretario Docente")) {
			try {
				if(ServicesLocator.getStudentServices().validatePromotion()) {
					Notifications.getInstance().show(Notifications.Type.INFO, Location.BOTTOM_RIGHT, 4500, "La funcionalidad de promoción de los estudiantes\n se encuentra actualmente disponible.\n Para poder realizar esta operación seleccione\n la promoción en la pestaña de <Alumnos>,\n en la esquina superior derecha.");
				}
			} catch (ClassNotFoundException | SQLException e1) {
				Notifications.getInstance().show(Notifications.Type.ERROR, Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
			}
		}
	}

	private String getWelcomeName() {
		String s = "";
		String role = user.getRole().getRole();
		if(role.equals("Estudiante")) {
			try {
				StudentDTO std = ServicesLocator.getStudentServices().getStudent(user.getIdStd());
				s = std.getNames() + " " + std.getLastNames();
			} catch (ClassNotFoundException | SQLException e) {
				s = "NONE";
			}
		}
		else if(role.equals("Secretario Docente"))
			s = "Máster";
		else 
			s = "Admin";
		
		
		return s;
	}
}
