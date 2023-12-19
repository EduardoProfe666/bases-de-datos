package services;

import java.sql.SQLException;
import java.util.Iterator;

import definitions.LogicDefinitions;
import definitions.VisualDefinitions;
import definitions.VisualErrors;
import dto.UserDTO;
import utils.Encription;
import utils.Validations;

public class AuthServices {
	protected AuthServices() {}
	
	public UserDTO authSecurity(String mail, String password) throws ClassNotFoundException, SQLException {
		boolean userNotEmpty = Validations.validateStringNotEmptyOrNull(mail) && !mail.equals(VisualDefinitions.PLACEHOLDER_USER);
		boolean passwordNotEmpty = Validations.validateStringNotEmptyOrNull(password) && !password.equals(VisualDefinitions.PLACEHOLDER_PASSWORD);
		
		if(!userNotEmpty) {
			if(!passwordNotEmpty)
				throw new IllegalArgumentException(VisualErrors.ERROR_CAMPO_VACIO);
			else
				throw new IllegalArgumentException(VisualErrors.ERROR_CAMPO_VACIO_USUARIO);
		}
		else if(!passwordNotEmpty)
			throw new IllegalArgumentException(VisualErrors.ERROR_CAMPO_VACIO_CONTRASENYA);
		
		UserDTO u = null;
		UserDTO x = null;
		
		Iterator<UserDTO> iter = ServicesLocator.getUserServices().getAllUser().iterator();
		while(iter.hasNext() && u==null) {
			x = iter.next();
			if(x.getMail().equals(mail))
				u = x;
		}
		
		if(u == null)
			throw new IllegalArgumentException(VisualErrors.ERROR_CORREO_NO_VALIDO);
		
		if(!Encription.decode(LogicDefinitions.SECRET_KEY_PASSWORD, u.getPassword()).equals(password))
			throw new IllegalArgumentException(VisualErrors.ERROR_CONTRASENYA_NO_VALIDA);
		
		return u;
	}

}
