package definitions;

/**
 * 
 * Class that contains the message errors of all the visual exceptions/errors 
 * 
 * @author Eduardo Gonz�lez
 * @author Lilian Rojas
 *
 */
public final class VisualErrors {
	private VisualErrors() {}

	//Login
	public static final String ERROR_CAMPO_VACIO = "No pueden existir campos de entrada vac�os";
	public static final String ERROR_CAMPO_VACIO_USUARIO = "El correo no puede estar vac�o";
	public static final String ERROR_CAMPO_VACIO_CONTRASENYA = "La contrase�a no puede estar vac�a";
	public static final String ERROR_CORREO_NO_VALIDO = "El correo no es v�lido";
	public static final String ERROR_CONTRASENYA_NO_VALIDA = "La contrase�a no es correcta";
	
	public static final String ERROR_SQL = "Error Interno. Por favor, vuelva a intentarlo m�s tarde";
}
