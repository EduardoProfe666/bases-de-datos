package services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import dto.RoleDTO;
import utils.FunctionBuilder;
import utils.FunctionBuilder.FunctionType;

public class RoleServices {
	private static final String TABLE_NAME = "role_user";
	
	protected RoleServices() {}
	
	public RoleDTO getRole(int idRole) throws SQLException, ClassNotFoundException {
		RoleDTO role;
		Connection c = ServicesLocator.getConnection();
		c.setAutoCommit(false);
		
		String function = FunctionBuilder.constructCrudFunction(FunctionType.GET_BY_ID, TABLE_NAME, 1);
		CallableStatement cs = c.prepareCall(function);
		cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
		cs.setInt(2, idRole);
		cs.execute();
		
		ResultSet rs = (ResultSet) cs.getObject(1);
		rs.next();
		
		role = new RoleDTO(rs.getInt(1), rs.getString(2));
		
		rs.close();
		cs.close();
		c.close();
		
		return role;
	}
	
	public List<RoleDTO> getAllRole() throws ClassNotFoundException, SQLException{
		ArrayList<RoleDTO> roles = new ArrayList<>();
		Connection c = ServicesLocator.getConnection();
		c.setAutoCommit(false);
		String function = FunctionBuilder.constructCrudFunction(FunctionType.GET_ALL, TABLE_NAME, 0);
		CallableStatement cs = c.prepareCall(function);
		cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);
		while(rs.next()) {
			roles.add(new RoleDTO(rs.getInt(1), rs.getString(2)));
		}
		rs.close();
		cs.close();
		c.close();
		
		return roles;
	}
	
	
	public List<String> getAllRolesStrings() throws ClassNotFoundException, SQLException{
		List<RoleDTO> roles = getAllRole();
		List<String> l = new LinkedList<>();
		
		for(RoleDTO r: roles)
			l.add(r.getRole());
		
		return l;
	}
	
	public RoleDTO getRoleByName(String roleName) throws ClassNotFoundException, SQLException {
		List<RoleDTO> roles = getAllRole();
		RoleDTO role = null;
		
		for(int i=0;i<roles.size() && role == null;i++) {
			if(roles.get(i).getRole().equals(roleName)) {
				role = roles.get(i);
			}
		}
		
		return role;
	}

}
