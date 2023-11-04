package utils;

import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Utility Class that offers different methods to handle both logic and visuals

 * @author Lilian Rojas
 * @author Eduardo González
 *
 */
public final class Auxiliar {
	private Auxiliar() {}
	
	public static Icon adjustImage(Dimension dimCmp, URL urlImg) {
		ImageIcon img = new ImageIcon(urlImg);
		
		return new ImageIcon(img.getImage().getScaledInstance(dimCmp.width, dimCmp.height, Image.SCALE_SMOOTH));
	}
	
//	public static Usuario seguridad(String usuario,String contrasenya) {
//		
//		boolean usuarioNoVacio = Validaciones.validarStringNoVacio(usuario) && !usuario.equals(DefinicionesInterfaz.CAMPO_USUARIO_TEXTO_BASE);
//		boolean contrasenyaNoVacia = Validaciones.validarStringNoVacio(contrasenya) && !contrasenya.equals(DefinicionesInterfaz.CAMPO_CONTRASENYA_TEXTO_BASE);
//		
//		if(!usuarioNoVacio) {
//			if(!contrasenyaNoVacia)
//				throw new IllegalArgumentException(ErroresInterfazGrafica.ERROR_CAMPO_VACIO);
//			else
//				throw new IllegalArgumentException(ErroresInterfazGrafica.ERROR_CAMPO_VACIO_USUARIO);
//		}
//		else if(!contrasenyaNoVacia)
//			throw new IllegalArgumentException(ErroresInterfazGrafica.ERROR_CAMPO_VACIO_CONTRASENYA);
//			
//		Usuario u = null;
//		Usuario x = null;
//		Iterator<Usuario> iter = Usuarios.getInstancia().getUsuarios().iterator();
//		while(iter.hasNext() && u==null) {
//			x = iter.next();
//			if(x.getCorreoUsuario().equals(usuario))
//				u = x;
//		}
//		
//		
//		if(u==null)
//			throw new IllegalArgumentException(ErroresInterfazGrafica.ERROR_CORREO_NO_VALIDO);
//		
//		if(!u.getContrasenya().equals(contrasenya))
//			throw new IllegalArgumentException(ErroresInterfazGrafica.ERROR_CONTRASENYA_NO_VALIDA);
//			
//		return u;
//	}
	
	public LocalDate convertToLocalDate(Calendar c) {
		return c!=null ? LocalDateTime.ofInstant(c.toInstant(), c.getTimeZone().toZoneId()).toLocalDate() : null;
	}
}
