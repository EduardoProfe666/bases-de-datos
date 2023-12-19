package utils;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * Clase de utilidades que incluye diferentes métodos para la validación de los datos 
 * de la aplicación.
 * 
 * @version 2023.05.29
 * @author Eduardo González
 *
 */
public final class Validaciones {

	private Validaciones() {}

	public static boolean validarEnteroRango(int i, int min, int max) {
		boolean esValido = min<=max;

		if(esValido)
			esValido = i>=min && i<=max;

			return esValido;
	}

	public static boolean validarTamString(String s, int min, int max) {
		boolean esValido = s!=null;

		if(esValido)
			esValido= min<=max;
		if(esValido)
			esValido = s.length()>=min && s.length()<=max;

			return esValido;
	}
	public static boolean validarStringTodoLetra(String s) {
		boolean esValido = s!=null;

		for(int i=0;i<s.length() && esValido;i++)
			esValido = Character.isLetter(s.charAt(i)) || Character.isWhitespace(s.charAt(i));

		return esValido;
	}
	public static boolean validarStringTodoDigito(String s) {
		boolean esValido = s!=null;

		for(int i=0;i<s.length() && esValido;i++)
			esValido = Character.isDigit(s.charAt(i));

		return esValido;
	}
	public static boolean validarStringTodoDigitoLetraSinEspacio(String s) {
		boolean esValido = s!=null;

		for(int i=0;i<s.length() && esValido;i++)
			esValido = Character.isDigit(s.charAt(i)) || Character.isUpperCase(s.charAt(i)) || Character.isLowerCase(s.charAt(i));

		return esValido;
	}
	public static boolean validarStringNoTodoEspacio(String s) {
		boolean esValido = false;  
		
		if(s!=null) {
			for(int i=0;i<s.length() && !esValido;i++)
				if(!Character.isWhitespace(s.charAt(i)))
					esValido = true;
		}
		
		return esValido;
	}
	public static boolean validarStringNoVacio(String s) {
		return (s!=null && !s.isEmpty());
	}
	public static boolean validarStringNoEspacio(String s) {
		boolean esValido = (s!=null);

		for(int i=0;i<s.length() && esValido;i++)
			esValido = !Character.isWhitespace(s.charAt(i));

		return esValido;
	}
	
	//Validaciones de fecha
	public static boolean validarRangoFecha(Date fecha, Date fechaMin, Date fechaMax) {
		boolean esValida = (fecha!=null && fechaMin!=null && fechaMax!=null);
		
		if(esValida) {
			esValida = (fecha.after(fechaMin) && fecha.before(fechaMax));
			
			if(!esValida) {
				Calendar c = Calendar.getInstance();
				c.setTime(fecha);
				Calendar cMin = Calendar.getInstance();
				cMin.setTime(fechaMin);
				Calendar cMax = Calendar.getInstance();
				cMax.setTime(fechaMax);
				
				esValida = validarCalendarsMismoDia(c, cMin) || validarCalendarsMismoDia(c, cMax);
			}
		}
		

		return esValida;
	}

	//Otras Validaciones
	public static boolean validarNoRepeticionLista(List<?> lista) {
		boolean esValida = lista!=null;

		lista.sort(null);

		for(int i=0;i<lista.size()-1 && esValida;i++)
			esValida = !(lista.get(i).equals(lista.get(i+1)));

		return esValida;
	}

	public static boolean validarNoRepeticionStringLista(List<String> lista, String s) {
		boolean esValida = lista!=null && s!=null;

		if(esValida) {
			lista.sort(null);
			esValida = Collections.binarySearch(lista,s)<0;
		}

		return esValida;
	}

	public static boolean validarNoRepeticionDosListasString(List<String> lista1, List<String> lista2) {
		boolean esValida = (lista1!=null && lista2!=null);

		if(esValida)
			esValida = Collections.disjoint(lista1, lista2);

		return esValida;
	}

	public static boolean validarRepeticionStringLista(List<String> lista, String s) {
		return !validarNoRepeticionStringLista(lista, s);
	}

	
	public static boolean validarCalendarsMismoDia(Calendar c1, Calendar c2) {
		boolean esValido = c1!=null && c2!=null;
		
		if(esValido)
			esValido = c1.get(Calendar.YEAR)==c2.get(Calendar.YEAR);
		if(esValido)
			esValido = c1.get(Calendar.MONTH)==c2.get(Calendar.MONTH);
		if(esValido)
			esValido = c1.get(Calendar.DAY_OF_MONTH)==c2.get(Calendar.DAY_OF_MONTH);
		
		return esValido;
	}

}
