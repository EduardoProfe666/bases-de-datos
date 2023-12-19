package visual.classes.main_screens;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ColorUIResource;

import com.formdev.flatlaf.FlatLightLaf;

import componentes.AvatarCircular;
import componentes.BotonAnimacion;
import componentes.PanelGradienteH;
import componentes.PanelGradienteV;
import definitions.LogicDefinitions;
import definitions.VisualDefinitions;
import definitions.VisualErrors;
import dto.UserDTO;
import raven.glasspanepopup.GlassPanePopup;
import raven.glasspanepopup.Option;
import raven.toast.Notifications;
import sample.message.Message;
import sample.message.MessagePassword;
import sample.message.OptionConstructor;
import services.ServicesLocator;
import utils.Auxiliar;
import utils.ColorScheme;
import utils.Encription;
import utils.Validations;
import visual.components.PromotionButton;
import visual.components.SemiGlassPane;
import visual.components.TopPanel;

/**
 * JFrame that models the basic structure of the mains screens of the app
 * 
 * @author Lilian Rojas
 * @author Eduardo González
 *
 */
public abstract class MainScreenBasic extends JFrame {
	private static final long serialVersionUID = 1L;
	protected JPanel panelBase;
	protected TopPanel topPanel;
	protected JPanel containerPanel;
	protected PanelGradienteH topInPanel;
	protected PanelGradienteV userPanel;
	protected AvatarCircular avatar;
	protected JLabel nombreUsuariolbl;
	protected JLabel tipoUsuariolbl;
	protected BotonAnimacion cerrarSesionBtn;
	protected JLabel opcionLbl;
	protected JPanel optionsPanel;
	protected static JTabbedPane panelPrincipall;
	protected JButton botonAtras;
	private JButton botonMenu;
	protected ColorScheme color_scheme;
	protected JPanel sidePanel;
	protected boolean isActiveSidePanel;
	protected SemiGlassPane glass;
	protected JButton btnPassw;
	protected UserDTO usuario;

	public MainScreenBasic(String nombreUsuario, String tipoUsuario, ColorScheme esquema, UserDTO usuari) {
		this.usuario = usuari;
		isActiveSidePanel=false;
		Notifications.getInstance().setJFrame(this);
		GlassPanePopup.install(this);
		this.setTitle("Conest");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(Autentication.class.getResource("/visual/icons/favicon.png")));
		color_scheme = esquema;
		FlatLightLaf.setup();
		
		UIManager.put("ToolTip.foreground", VisualDefinitions.P6);
		UIManager.put("ToolTip.border", new LineBorder(VisualDefinitions.P9, 2));
		UIManager.put("ToolTip.font", new Font("Roboto Medium", Font.PLAIN, 14));
		UIManager.put("Component.focusColor", VisualDefinitions.P9);
		UIManager.put("Component.focusedBorderColor", VisualDefinitions.P9);
		UIManager.put("TitlePane.closeHoverBackground", VisualDefinitions.P5);
		UIManager.put("TitlePane.closePressedBackground", VisualDefinitions.P6);
		UIManager.put("TitlePane.font", new Font("Roboto Medium", Font.PLAIN, 15));
		UIManager.put("CheckBox.icon.selectedBorderColor", VisualDefinitions.P9);
		UIManager.put("CheckBox.icon.checkmarkColor", VisualDefinitions.P9);
		UIManager.put("CheckBox.icon.focusColor", VisualDefinitions.P9);
		UIManager.put("Button.hoverBackground", VisualDefinitions.P9);
		UIManager.put("ComboBox.selectionBackground", new ColorUIResource(VisualDefinitions.P9));
		UIManager.put( "Component.arc", 10);
		UIManager.put("PasswordField.showRevealButton", true);
		UIManager.put("Spinner.disableOnBoundaryValues", true);
		UIManager.put("PasswordField.showCapsLock", true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, VisualDefinitions.DIMENSION_MAIN_APP.width, VisualDefinitions.DIMENSION_MAIN_APP.height);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setBackground(new Color(255,255,255,0));
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				requestFocus();
			}

		});

		
		panelBase = new JPanel();
		panelBase.setOpaque(false);
		panelBase.setLayout(null);

		topPanel = new TopPanel(color_scheme.getPanelMovilBase(), this, "Conest");
		topPanel.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0,0,0)));

		setContentPane(panelBase);
		panelBase.add(topPanel);

		containerPanel = new JPanel();
		containerPanel.setBounds(0, 45, 1200, 630);
		containerPanel.setBackground(new Color(Color.LIGHT_GRAY.getRed(),Color.LIGHT_GRAY.getGreen(),Color.LIGHT_GRAY.getBlue(),180));
		panelBase.add(containerPanel);
		containerPanel.setLayout(null);

		topInPanel = new PanelGradienteH(color_scheme.getPanelSupGradienteInicio(), color_scheme.getPanelSupGradienteFin());
		//panelSup = new PanelGradienteH(Color.LIGHT_GRAY, Color.DARK_GRAY);
		topInPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				topInPanel.requestFocus();
			}
		});

		sidePanel = new JPanel();
		sidePanel.setBounds(-300, 0, 300, 630);
		containerPanel.add(sidePanel);
		sidePanel.setLayout(null);

		userPanel = new PanelGradienteV(color_scheme.getPanelUsuarioGradienteInicio(), color_scheme.getPanelUsuarioGradienteFin());
		userPanel.setBounds(0, 0, 300, 237);
		sidePanel.add(userPanel);
		//panelUsuario = new PanelGradienteV(Color.LIGHT_GRAY, Color.DARK_GRAY);
		userPanel.setBorder(new MatteBorder(0, 2, 0, 0, new Color(0,0,0)));
		userPanel.setLayout(null);
		
		btnPassw = new JButton("");
		btnPassw.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Option o = OptionConstructor.constructOption(VisualDefinitions.TOP_PANEL_COLOR, false);
				MessagePassword m = new MessagePassword();
				m.eventOK(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(String.valueOf(m.lastP.getPassword()).equals(Encription.decode(LogicDefinitions.SECRET_KEY_PASSWORD, usuario.getPassword()))) {
							if(Validations.validateStringNotEmptyOrNull(String.valueOf(m.passwordField.getPassword()))) {
								if(String.valueOf(m.passwordField.getPassword()).equals(String.valueOf(m.passwordField_1.getPassword()))) {
									GlassPanePopup.closePopupLast();
									try {
										ServicesLocator.getUserServices().updateUserPassword(usuario.getId(), String.valueOf(m.passwordField.getPassword()));
										usuario = ServicesLocator.getUserServices().getUser(usuario.getId());
										Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.BOTTOM_RIGHT, 3500, "Contraseña actualizada con éxito");
									} catch (ClassNotFoundException | SQLException e1) {
										Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.BOTTOM_RIGHT, 3500, VisualErrors.ERROR_SQL);
									}
								} else {
									Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.BOTTOM_RIGHT, 3500, "No concuerdan las contraseñas nuevas");
								}
							} else {
								Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.BOTTOM_RIGHT, 3500, "La contraseña nueva no es válida");
							}
						} else {
							Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.BOTTOM_RIGHT, 3500, "La contraseña antigua es errónea");
						}
						
					}
				});
				GlassPanePopup.showPopup(m, o);
				botonMenu.doClick();
			}
		});
		btnPassw.setIcon(Auxiliar.adjustImage(new Dimension(32, 32), MainScreenBasic.class.getResource("/visual/icons/password.png")));
		btnPassw.setRolloverIcon(Auxiliar.adjustImage(new Dimension(32, 32), MainScreenBasic.class.getResource("/visual/icons/password1.png")));
		btnPassw.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPassw.setToolTipText("Cambiar Contraseña");
		btnPassw.setContentAreaFilled(false);
		btnPassw.setBorder(null);
		btnPassw.setBounds(135,169, 32, 32);
		userPanel.add(btnPassw);
		
		avatar = new AvatarCircular(new ImageIcon(MainScreenBasic.class.getResource(color_scheme.getDirUrlImagenAvatar())), VisualDefinitions.SIZE_AVATAR_BORDER);
		avatar.setForeground(color_scheme.getBordeAvatar());
		avatar.setBounds(95, 5, 110, 110);
		userPanel.add(avatar);

		nombreUsuariolbl = new JLabel(nombreUsuario);
		nombreUsuariolbl.setForeground(color_scheme.getPanelUsuarioTexto());
		nombreUsuariolbl.setHorizontalAlignment(SwingConstants.CENTER);
		nombreUsuariolbl.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		nombreUsuariolbl.setBounds(31, 115, 238, 28);
		userPanel.add(nombreUsuariolbl);

		tipoUsuariolbl = new JLabel(tipoUsuario);
		tipoUsuariolbl.setForeground(color_scheme.getPanelUsuarioTexto());
		tipoUsuariolbl.setHorizontalAlignment(SwingConstants.CENTER);
		tipoUsuariolbl.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		tipoUsuariolbl.setBounds(45, 140, 210, 28);
		userPanel.add(tipoUsuariolbl);

		cerrarSesionBtn = new BotonAnimacion();
		cerrarSesionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				Option o = OptionConstructor.constructOption(color_scheme.getPanelMovilBase(), false);
				Message m = new Message("Cerrar Sesión", VisualDefinitions.SIGN_OFF_QUESTION);
				m.eventOK(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						GlassPanePopup.closePopupLast();
						dispose();
						Autentication l = new Autentication();
						l.setVisible(true);
					}
				});

				GlassPanePopup.showPopup(m, o);	
			}
		});
		cerrarSesionBtn.setText("Cerrar Sesi\u00F3n");
		cerrarSesionBtn.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		cerrarSesionBtn.setForeground(color_scheme.getPanelUsuarioBtnTexto());
		cerrarSesionBtn.setEffectColor(color_scheme.getPanelUsuarioBtnColorEfecto());
		cerrarSesionBtn.setBackground(color_scheme.getPanelUsuarioBtn());
		cerrarSesionBtn.setBounds(80, 205, 140, 21);
		userPanel.add(cerrarSesionBtn);

		optionsPanel = new JPanel();
		optionsPanel.setBorder(new MatteBorder(0, 2, 2, 0, new Color(0,0,0)));
		optionsPanel.setLayout(null);
		optionsPanel.setBackground(color_scheme.getPanelContenedorOpciones());
		optionsPanel.setBounds(0, 237, 300, 393);
		sidePanel.add(optionsPanel);
		topInPanel.setBorder(new MatteBorder(0, 2, 0, 2, new Color(0,0,0)));
		topInPanel.setBounds(0, 0, 1200, 55);
		containerPanel.add(topInPanel);
		topInPanel.setLayout(null);

		opcionLbl = new JLabel("INICIO");
		opcionLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		opcionLbl.setForeground(color_scheme.getPanelSupGradienteTexto());
		opcionLbl.setBounds(104, 11, 396, 33);
		topInPanel.add(opcionLbl);

		botonMenu = new JButton("");
		botonMenu.addActionListener(new ActionListener() {

			private Timer temporizador;
			private double x_sidePanel;
			private double x_botonMenu;
			private double x_botonAtras;
			private double x_label;

			public void actionPerformed(ActionEvent e) {
				isActiveSidePanel = !isActiveSidePanel;
				botonAtras.setEnabled(!isActiveSidePanel);
				glass.setVisible(isActiveSidePanel);
				Component[] componentes = ((JPanel)panelPrincipall.getSelectedComponent()).getComponents();
				final int ancho_sidePanel = sidePanel.getWidth();
				final int largo_sidePanel = sidePanel.getHeight();
				final int y_sidePanel = sidePanel.getY();

				final int ancho_botonMenu = botonMenu.getWidth();
				final int largo_botonMenu = botonMenu.getHeight();
				final int y_botonMenu = botonMenu.getY();

				final int ancho_botonAtras = botonAtras.getWidth();
				final int largo_botonAtras = botonAtras.getHeight();
				final int y_botonAtras = botonAtras.getY();

				final int ancho_label = opcionLbl.getWidth();
				final int largo_label = opcionLbl.getHeight();
				final int y_label = opcionLbl.getY();

				final boolean direccion = Integer.signum(sidePanel.getX())!=-1; //<-true, false->

				x_sidePanel = sidePanel.getX();
				x_botonMenu = botonMenu.getX();
				x_botonAtras = botonAtras.getX();
				x_label = opcionLbl.getX();

				temporizador = new Timer(1, new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						double cantidad = 0;
						if((!direccion && -x_sidePanel<13))
							cantidad = -x_sidePanel;
						else if((direccion && 300-(-x_sidePanel)<13))
							cantidad = 300-(-x_sidePanel);
						else
							cantidad = 13;

						if(direccion) {
							x_sidePanel-=cantidad;
							x_botonMenu-=cantidad;
							x_botonAtras-=cantidad;
							x_label-=cantidad;
						}
						else {
							x_sidePanel+=cantidad;
							x_botonMenu+=cantidad;
							x_botonAtras+=cantidad;
							x_label+=cantidad;
						}

						botonMenu.setBounds((int) x_botonMenu, y_botonMenu, ancho_botonMenu, largo_botonMenu);
						botonAtras.setBounds((int) x_botonAtras, y_botonAtras, ancho_botonAtras, largo_botonAtras);
						opcionLbl.setBounds((int) x_label, y_label, ancho_label, largo_label);
						sidePanel.setBounds((int) x_sidePanel,y_sidePanel,ancho_sidePanel,largo_sidePanel);

						if((direccion && x_sidePanel<=-300) || (!direccion && x_sidePanel>=0))
							temporizador.stop();
					}
				});
				temporizador.start();
				for(Component c : componentes) {
					if(!(c instanceof JComboBox) && !(c instanceof PromotionButton))
						c.setEnabled(!isActiveSidePanel);
				}
			}
		});
		botonMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonMenu.setBorder(null);
		botonMenu.setIcon(Auxiliar.adjustImage(new Dimension(32,32), MainScreenBasic.class.getResource("/visual/icons/menu0.png")));
		botonMenu.setRolloverIcon(Auxiliar.adjustImage(new Dimension(32,32), MainScreenBasic.class.getResource("/visual/icons/menu1.png")));
		botonMenu.setContentAreaFilled(false);
		botonMenu.setBounds(10, 9, 36, 36);
		topInPanel.add(botonMenu);

		botonAtras = new JButton("");
		botonAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPrincipall.setSelectedIndex(2);
			}
		});
		botonAtras.setVisible(false);
		botonAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonAtras.setBorder(null);
		botonAtras.setIcon(Auxiliar.adjustImage(new Dimension(32,32), MainScreenBasic.class.getResource(color_scheme.getDirUrlBtnAtras())));
		botonAtras.setRolloverIcon(Auxiliar.adjustImage(new Dimension(32,32), MainScreenBasic.class.getResource(color_scheme.getDirUrlBtnAtrasHover())));
		botonAtras.setContentAreaFilled(false);
		botonAtras.setBounds(56, 9, 36, 36);
		topInPanel.add(botonAtras);
		
		glass = new SemiGlassPane(color_scheme.getPanelMovilBase());
		glass.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isActiveSidePanel)
					botonMenu.doClick();
				
			}
		});
		this.glass.setVisible(false);
		containerPanel.add(glass);
		
		panelPrincipall = new JTabbedPane(JTabbedPane.TOP);
		panelPrincipall.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		panelPrincipall.setBackground(Color.WHITE);
		panelPrincipall.setBounds(0, 0, 1200, 630);
		panelPrincipall.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(isActiveSidePanel) {
					Component[] componentes = ((JPanel)panelPrincipall.getSelectedComponent()).getComponents();
					for(Component c : componentes) {
						if(!(c instanceof JComboBox) && !(c instanceof PromotionButton))
							c.setEnabled(!isActiveSidePanel);
					}
				}
					
			}
		});
		containerPanel.add(panelPrincipall);


	}

	public JFrame getThis() {
		return this;
	}

}
