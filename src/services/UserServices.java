package services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import definitions.LogicDefinitions;
import dto.RoleDTO;
import dto.UserDTO;
import utils.Encription;
import utils.FunctionBuilder;
import utils.FunctionBuilder.FunctionType;

public class UserServices {
	private static final String TABLE_NAME = "user_app";

	protected UserServices() {}
	
	public UserDTO getUser(int idUser) throws SQLException, ClassNotFoundException {
		UserDTO a;
		Connection c = ServicesLocator.getConnection();
		c.setAutoCommit(false);

		String function = FunctionBuilder.constructCrudFunction(FunctionType.GET_BY_ID, TABLE_NAME, 1);
		CallableStatement cs = c.prepareCall(function);
		cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
		cs.setInt(2, idUser);
		cs.execute();

		ResultSet rs = (ResultSet) cs.getObject(1);
		rs.next();
		
		
		a = new UserDTO(rs.getInt(1), rs.getString(3), rs.getString(4), 
				rs.getInt(5),
				new RoleDTO(rs.getInt(2), rs.getString(6)));
		rs.close();
		cs.close();
		c.close();

		return a;
	}
	
	
	public List<UserDTO> getAllUser() throws ClassNotFoundException, SQLException{
		ArrayList<UserDTO> a = new ArrayList<>();
		Connection c = ServicesLocator.getConnection();
		c.setAutoCommit(false);
		
		String function = FunctionBuilder.constructCrudFunction(FunctionType.GET_ALL, TABLE_NAME, 0);
		CallableStatement cs = c.prepareCall(function);
		cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);
		while(rs.next()) {
			a.add(new UserDTO(rs.getInt(1), rs.getString(3), rs.getString(4), 
					rs.getInt(5),
					new RoleDTO(rs.getInt(2), rs.getString(6))));
		}
		rs.close();
		cs.close();
		c.close();

		return a;
	}
	
	public void updateUserPassword(int idUser, String password) throws ClassNotFoundException, SQLException{
		Connection c = ServicesLocator.getConnection();
		password = Encription.encode(LogicDefinitions.SECRET_KEY_PASSWORD, password);
		String query = "UPDATE user_app SET password_user=? WHERE user_app.id_user=?;";
		PreparedStatement ps = c.prepareStatement(query);
		ps.setString(1, password);
		ps.setInt(2, idUser);
		ps.execute();
		ps.close();
		c.close();
	}
	
	public void addUser(int idRole, String mail, String password, Integer idStudent) throws ClassNotFoundException, SQLException{
		Connection c = ServicesLocator.getConnection();

		String function = FunctionBuilder.constructCrudFunction(FunctionType.ADD, TABLE_NAME, 4);
		CallableStatement cs = c.prepareCall(function);
		cs.setInt(1, idRole);
		cs.setString(2, mail);
		cs.setString(3, Encription.encode(LogicDefinitions.SECRET_KEY_PASSWORD, password));
		if(idStudent == null)
			cs.setNull(4, Types.INTEGER);
		else
			cs.setInt(4, idStudent);
		cs.execute();

		cs.close();
		c.close();
	}
	
	public void deleteUser(int idUser) throws ClassNotFoundException, SQLException{
		Connection c = ServicesLocator.getConnection();

		String function = FunctionBuilder.constructCrudFunction(FunctionType.DELETE, TABLE_NAME, 1);
		CallableStatement cs = c.prepareCall(function);
		cs.setInt(1, idUser);
		cs.execute();

		cs.close();
		c.close();
	}
	
	public List<UserDTO> usersFilter(String roleName) throws ClassNotFoundException, SQLException {
		LinkedList<UserDTO> l = new LinkedList<>();
		List<UserDTO> aux = getAllUser();
		
		for(UserDTO u: aux) {
			if(u.getRole().getRole().equals(roleName))
				l.add(u);
		}
		
		return l;
	}

}
