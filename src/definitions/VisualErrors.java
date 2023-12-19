package definitions;

/**
 * 
 * Class that contains the message errors of all the visual exceptions/errors 
 * 
 * @author Eduardo González
 * @author Lilian Rojas
 *
 */
public final class VisualErrors {
	private VisualErrors() {}

	//Login
	public static final String ERROR_CAMPO_VACIO = "No pueden existir campos de entrada vacíos";
	public static final String ERROR_CAMPO_VACIO_USUARIO = "El correo no puede estar vacío";
	public static final String ERROR_CAMPO_VACIO_CONTRASENYA = "La contraseña no puede estar vacía";
	public static final String ERROR_CORREO_NO_VALIDO = "El correo no es válido";
	public static final String ERROR_CONTRASENYA_NO_VALIDA = "La contraseña no es correcta";
	
	public static final String ERROR_SQL = "Error Interno. Por favor, vuelva a intentarlo más tarde";
}
