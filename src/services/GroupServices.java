package services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.AcademicYearDTO;
import dto.GroupDTO;
import utils.FunctionBuilder;
import utils.FunctionBuilder.FunctionType;

public class GroupServices {
	private static final String TABLE_NAME = "group_st";

	protected GroupServices() {}

	public GroupDTO getGroup(int idGroup, int idYear) throws SQLException, ClassNotFoundException {
		GroupDTO a;
		Connection c = ServicesLocator.getConnection();
		c.setAutoCommit(false);

		String function = FunctionBuilder.constructCrudFunction(FunctionType.GET_BY_ID, TABLE_NAME, 2);
		CallableStatement cs = c.prepareCall(function);
		cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
		cs.setInt(2, idGroup);
		cs.setInt(3, idYear);
		cs.execute();

		ResultSet rs = (ResultSet) cs.getObject(1);
		rs.next();

		a = new GroupDTO(rs.getInt(1), rs.getInt(3), rs.getInt(4), new AcademicYearDTO(rs.getInt(2), rs.getInt(5), rs.getInt(6), rs.getInt(7)));

		rs.close();
		cs.close();
		c.close();

		return a;
	}


	public List<GroupDTO> getAllGroup() throws ClassNotFoundException, SQLException{
		ArrayList<GroupDTO> a = new ArrayList<>();
		Connection c = ServicesLocator.getConnection();
		c.setAutoCommit(false);

		String function = FunctionBuilder.constructCrudFunction(FunctionType.GET_ALL, TABLE_NAME, 0);
		CallableStatement cs = c.prepareCall(function);
		cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);
		while(rs.next()) {
			a.add(new GroupDTO(rs.getInt(1), rs.getInt(3), rs.getInt(4), new AcademicYearDTO(rs.getInt(2), rs.getInt(5), rs.getInt(6), rs.getInt(7))));
		}
		rs.close();
		cs.close();
		c.close();

		return a;
	}

	public List<String> getGroups(int year) throws ClassNotFoundException, SQLException{
		List<GroupDTO> l = getAllGroup();
		List<String> list = new ArrayList<String>();

		for(GroupDTO g : l) {
			if(g.getYear().getYear()==year)
				list.add(String.valueOf(g.getNumGroup()));
		}

		return list;
	}

	public List<String> getGroups() throws ClassNotFoundException, SQLException{
		List<GroupDTO> l = getAllGroup();
		List<String> list = new ArrayList<String>();

		for(GroupDTO g : l) {
			list.add(String.valueOf(g.getNumGroup()));
		}

		return list;
	}

}
