package utils;

/**
 * Class that contains several validations
 * 
 * @version 2023.05.29
 * @author Eduardo González
 *
 */
public final class Validations {

	private Validations() {}

	public static boolean validateTamString(String s, int min, int max) {
		boolean esValido = s!=null;

		if(esValido)
			esValido= min<=max;
		if(esValido)
			esValido = s.length()>=min && s.length()<=max;

			return esValido;
	}
	public static boolean validateStringAllDigitsOrLettersWithoutSpace(String s) {
		boolean esValido = s!=null;

		for(int i=0;i<s.length() && esValido;i++)
			esValido = Character.isDigit(s.charAt(i)) || Character.isUpperCase(s.charAt(i)) || Character.isLowerCase(s.charAt(i));

		return esValido;
	}
	public static boolean validateStringNotAllSpace(String s) {
		boolean esValido = false;  
		
		if(s!=null) {
			for(int i=0;i<s.length() && !esValido;i++)
				if(!Character.isWhitespace(s.charAt(i)))
					esValido = true;
		}
		
		return esValido;
	}
	public static boolean validateStringNotEmptyOrNull(String s) {
		return (s!=null && !s.isEmpty());
	}

}
