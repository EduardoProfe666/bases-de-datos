package utils;

import java.awt.Color;

/**
 * Class that models the level of customization of the different roles in the app. It includes border colors, font colors,
 * backgrounds, images, logos, etc.
 * 
 * @author Lilian Rojas
 * @author Eduardo González
 *
 */
public class ColorScheme {
	//User Panel
	private Color panelUsuarioGradienteInicio;
	private Color panelUsuarioGradienteFin;
	private Color panelUsuarioTexto;
	private Color panelUsuarioBtnTexto;
	private Color panelUsuarioBtnColorEfecto;
	private Color panelUsuarioBtn;
	private Color bordeAvatar;
	private String dirUrlImagenAvatar;
	
	//Options Panel
	private Color panelContenedorOpciones;
	private Color panelOpcionHover;
	private Color panelOpcionSeleccionado;
	private Color panelOpcionTexto;
	
	//Top Panel
	private Color panelSupGradienteInicio;
	private Color panelSupGradienteFin;
	private Color panelSupGradienteTexto;
	private String dirUrlBtnAtras;
	private String dirUrlBtnAtrasHover;
	
	//Movable Panel
	private Color panelMovilBase; 
	
	//General Panel
	private Color bordeLbl;
	private Color seleccionFondoTabla;
	private Color seleccionTextoTabla;
	
	
	public ColorScheme(Color panelUsuarioGradienteInicio, Color panelUsuarioGradienteFin, Color panelUsuarioTexto,
			Color panelUsuarioBtnTexto, Color panelUsuarioBtnColorEfecto, Color panelUsuarioBtn, Color bordeAvatar,
			Color panelContenedorOpciones, Color panelOpcionHover, Color panelOpcionSeleccionado,
			Color panelOpcionTexto, Color panelSupGradienteInicio, Color panelSupGradienteFin,
			Color panelSupGradienteTexto, String dirUrlImagenAvatar, Color panelMovilBase, Color bordeLbl,
			String dirUrlBtnAtras, String dirUrlBtnAtrasHover, Color seleccionFondoTabla, Color seleccionTextoTabla) {
		super();
		this.panelUsuarioGradienteInicio = panelUsuarioGradienteInicio;
		this.panelUsuarioGradienteFin = panelUsuarioGradienteFin;
		this.panelUsuarioTexto = panelUsuarioTexto;
		this.panelUsuarioBtnTexto = panelUsuarioBtnTexto;
		this.panelUsuarioBtnColorEfecto = panelUsuarioBtnColorEfecto;
		this.panelUsuarioBtn = panelUsuarioBtn;
		this.bordeAvatar = bordeAvatar;
		this.panelContenedorOpciones = panelContenedorOpciones;
		this.panelOpcionHover = panelOpcionHover;
		this.panelOpcionSeleccionado = panelOpcionSeleccionado;
		this.panelOpcionTexto = panelOpcionTexto;
		this.panelSupGradienteInicio = panelSupGradienteInicio;
		this.panelSupGradienteFin = panelSupGradienteFin;
		this.panelSupGradienteTexto = panelSupGradienteTexto;
		this.dirUrlImagenAvatar = dirUrlImagenAvatar;
		this.panelMovilBase = panelMovilBase;
		this.bordeLbl = bordeLbl;
		this.dirUrlBtnAtras = dirUrlBtnAtras;
		this.dirUrlBtnAtrasHover = dirUrlBtnAtrasHover;
		this.seleccionFondoTabla = seleccionFondoTabla;
		this.seleccionTextoTabla = seleccionTextoTabla;
	}

	public Color getPanelUsuarioGradienteInicio() {
		return panelUsuarioGradienteInicio;
	}

	public void setPanelUsuarioGradienteInicio(Color panelUsuarioGradienteInicio) {
		this.panelUsuarioGradienteInicio = panelUsuarioGradienteInicio;
	}

	public Color getPanelUsuarioGradienteFin() {
		return panelUsuarioGradienteFin;
	}

	public void setPanelUsuarioGradienteFin(Color panelUsuarioGradienteFin) {
		this.panelUsuarioGradienteFin = panelUsuarioGradienteFin;
	}

	public Color getPanelUsuarioTexto() {
		return panelUsuarioTexto;
	}

	public void setPanelUsuarioTexto(Color panelUsuarioTexto) {
		this.panelUsuarioTexto = panelUsuarioTexto;
	}

	public Color getPanelUsuarioBtnTexto() {
		return panelUsuarioBtnTexto;
	}

	public void setPanelUsuarioBtnTexto(Color panelUsuarioBtnTexto) {
		this.panelUsuarioBtnTexto = panelUsuarioBtnTexto;
	}

	public Color getPanelUsuarioBtnColorEfecto() {
		return panelUsuarioBtnColorEfecto;
	}

	public void setPanelUsuarioBtnColorEfecto(Color panelUsuarioBtnColorEfecto) {
		this.panelUsuarioBtnColorEfecto = panelUsuarioBtnColorEfecto;
	}

	public Color getPanelUsuarioBtn() {
		return panelUsuarioBtn;
	}

	public void setPanelUsuarioBtn(Color panelUsuarioBtn) {
		this.panelUsuarioBtn = panelUsuarioBtn;
	}

	public Color getBordeAvatar() {
		return bordeAvatar;
	}

	public void setBordeAvatar(Color bordeAvatar) {
		this.bordeAvatar = bordeAvatar;
	}

	public Color getPanelContenedorOpciones() {
		return panelContenedorOpciones;
	}

	public void setPanelContenedorOpciones(Color panelContenedorOpciones) {
		this.panelContenedorOpciones = panelContenedorOpciones;
	}

	public Color getPanelOpcionHover() {
		return panelOpcionHover;
	}

	public void setPanelOpcionHover(Color panelOpcionHover) {
		this.panelOpcionHover = panelOpcionHover;
	}

	public Color getPanelOpcionSeleccionado() {
		return panelOpcionSeleccionado;
	}

	public void setPanelOpcionSeleccionado(Color panelOpcionSeleccionado) {
		this.panelOpcionSeleccionado = panelOpcionSeleccionado;
	}

	public Color getPanelOpcionTexto() {
		return panelOpcionTexto;
	}

	public void setPanelOpcionTexto(Color panelOpcionTexto) {
		this.panelOpcionTexto = panelOpcionTexto;
	}

	public Color getPanelSupGradienteInicio() {
		return panelSupGradienteInicio;
	}

	public void setPanelSupGradienteInicio(Color panelSupGradienteInicio) {
		this.panelSupGradienteInicio = panelSupGradienteInicio;
	}

	public Color getPanelSupGradienteFin() {
		return panelSupGradienteFin;
	}

	public void setPanelSupGradienteFin(Color panelSupGradienteFin) {
		this.panelSupGradienteFin = panelSupGradienteFin;
	}

	public Color getPanelSupGradienteTexto() {
		return panelSupGradienteTexto;
	}

	public void setPanelSupGradienteTexto(Color panelSupGradienteTexto) {
		this.panelSupGradienteTexto = panelSupGradienteTexto;
	}

	public String getDirUrlImagenAvatar() {
		return dirUrlImagenAvatar;
	}

	public void setDirUrlImagenAvatar(String dirUrlImagenAvatar) {
		this.dirUrlImagenAvatar = dirUrlImagenAvatar;
	}

	public Color getPanelMovilBase() {
		return panelMovilBase;
	}

	public void setPanelMovilBase(Color panelMovilBase) {
		this.panelMovilBase = panelMovilBase;
	}

	public Color getBordeLbl() {
		return bordeLbl;
	}

	public void setBordeLbl(Color bordeLbl) {
		this.bordeLbl = bordeLbl;
	}

	public String getDirUrlBtnAtras() {
		return dirUrlBtnAtras;
	}

	public void setDirUrlBtnAtras(String dirUrlBtnAtras) {
		this.dirUrlBtnAtras = dirUrlBtnAtras;
	}

	public String getDirUrlBtnAtrasHover() {
		return dirUrlBtnAtrasHover;
	}

	public void setDirUrlBtnAtrasHover(String dirUrlBtnAtrasHover) {
		this.dirUrlBtnAtrasHover = dirUrlBtnAtrasHover;
	}

	public Color getSeleccionFondoTabla() {
		return seleccionFondoTabla;
	}

	public void setSeleccionFondoTabla(Color seleccionFondoTabla) {
		this.seleccionFondoTabla = seleccionFondoTabla;
	}

	public Color getSeleccionTextoTabla() {
		return seleccionTextoTabla;
	}

	public void setSeleccionTextoTabla(Color seleccionTextoTabla) {
		this.seleccionTextoTabla = seleccionTextoTabla;
	}
	
	
}
