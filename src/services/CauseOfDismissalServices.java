package services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.CauseOfDismissalDTO;
import utils.FunctionBuilder;
import utils.FunctionBuilder.FunctionType;

public class CauseOfDismissalServices {
	private static final String TABLE_NAME = "cause_of_dismissal";
	
	protected CauseOfDismissalServices() {}
	
	public CauseOfDismissalDTO getCauseOfDismissal(int idCauseOfDismissal) throws SQLException, ClassNotFoundException {
		CauseOfDismissalDTO a;
		Connection c = ServicesLocator.getConnection();
		c.setAutoCommit(false);

		String function = FunctionBuilder.constructCrudFunction(FunctionType.GET_BY_ID, TABLE_NAME, 1);
		CallableStatement cs = c.prepareCall(function);
		cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
		cs.setInt(2, idCauseOfDismissal);
		cs.execute();

		ResultSet rs = (ResultSet) cs.getObject(1);
		rs.next();

		a = new CauseOfDismissalDTO(rs.getInt(1), rs.getString(2));

		rs.close();
		cs.close();
		c.close();

		return a;
	}
	
	
	public List<CauseOfDismissalDTO> getAllCauseOfDismissal() throws ClassNotFoundException, SQLException{
		ArrayList<CauseOfDismissalDTO> a = new ArrayList<>();
		Connection c = ServicesLocator.getConnection();
		c.setAutoCommit(false);
		
		String function = FunctionBuilder.constructCrudFunction(FunctionType.GET_ALL, TABLE_NAME, 0);
		CallableStatement cs = c.prepareCall(function);
		cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);
		while(rs.next()) {
			a.add(new CauseOfDismissalDTO(rs.getInt(1), rs.getString(2)));
		}
		rs.close();
		cs.close();
		c.close();

		return a;
	}
	
	public void addCauseOfDismissal(String cause) throws ClassNotFoundException, SQLException{
		Connection c = ServicesLocator.getConnection();

		String function = FunctionBuilder.constructCrudFunction(FunctionType.ADD, TABLE_NAME, 1);
		CallableStatement cs = c.prepareCall(function);
		cs.setString(1, cause);
		cs.execute();

		cs.close();
		c.close();
	}
}
