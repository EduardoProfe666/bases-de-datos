package visual.classes.aux_screens;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import definitions.VisualDefinitions;
import utils.ColorScheme;
import visual.components.TopPanelJDialog;

/**
 * Clase que permitirá generalizar la clase JDialog con las características necesarias 
 * en la aplicación para este tipo de pantallas modales.
 * 
 * @version 2023.06.06
 * @author Lilian Rojas
 * @author Eduardo González
 *
 */
public abstract class JDialogGeneral extends JDialog{
	private static final long serialVersionUID = 1L;
	protected JPanel panelBase;
	protected TopPanelJDialog panelSuperior;
	protected JPanel panelContenedor;
	
	public JDialogGeneral(String titulo,ColorScheme e,JFrame padre){
		super(padre, true);
		this.setUndecorated(true);
		this.setBounds(100, 100, VisualDefinitions.DIMENSION_DIALOGS.width, VisualDefinitions.DIMENSION_DIALOGS.height);
		this.setBackground(new Color(255,255,255,0));
		
		panelBase = new JPanel();
		panelBase.setOpaque(false);
		setContentPane(panelBase);
		panelBase.setLayout(null);
		
		panelSuperior = new TopPanelJDialog(e.getPanelMovilBase(), this, titulo);
//		panelSuperior = new TopPanelJDialog(Color.BLACK, this, titulo);
		setContentPane(panelBase);
		panelBase.add(panelSuperior);
		
		panelContenedor = new JPanel();
		panelContenedor.setBorder(new LineBorder(new Color(0,0,0), 2));
		panelContenedor.setBounds(0, 45, VisualDefinitions.DIMENSION_DIALOGS.width, VisualDefinitions.DIMENSION_DIALOGS.height-45);
		panelContenedor.setLayout(null);
		panelContenedor.setBackground(Color.WHITE);
		panelBase.add(panelContenedor);
		
		this.setLocationRelativeTo(null);
	}

}
