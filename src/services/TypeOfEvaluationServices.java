package services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.TypeOfEvaluationDTO;
import utils.FunctionBuilder;
import utils.FunctionBuilder.FunctionType;

public class TypeOfEvaluationServices {
	private static final String TABLE_NAME = "type_of_evaluation";

	protected TypeOfEvaluationServices() {}
	
	
	public TypeOfEvaluationDTO getTypeOfEvaluation(int idTypeOfEvaluation) throws SQLException, ClassNotFoundException {
		TypeOfEvaluationDTO a;
		Connection c = ServicesLocator.getConnection();
		c.setAutoCommit(false);

		String function = FunctionBuilder.constructCrudFunction(FunctionType.GET_BY_ID, TABLE_NAME, 1);
		CallableStatement cs = c.prepareCall(function);
		cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
		cs.setInt(2, idTypeOfEvaluation);
		cs.execute();

		ResultSet rs = (ResultSet) cs.getObject(1);
		rs.next();
		
		a = new TypeOfEvaluationDTO(rs.getInt(1), rs.getString(2), rs.getInt(3));

		rs.close();
		cs.close();
		c.close();

		return a;
	}
	
	
	public List<TypeOfEvaluationDTO> getAllTypeOfEvaluation() throws ClassNotFoundException, SQLException{
		ArrayList<TypeOfEvaluationDTO> a = new ArrayList<>();
		Connection c = ServicesLocator.getConnection();
		c.setAutoCommit(false);
		
		String function = FunctionBuilder.constructCrudFunction(FunctionType.GET_ALL, TABLE_NAME, 0);
		CallableStatement cs = c.prepareCall(function);
		cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);
		while(rs.next()) {
			a.add(new TypeOfEvaluationDTO(rs.getInt(1), rs.getString(2), rs.getInt(3)));
		}
		rs.close();
		cs.close();
		c.close();

		return a;
	}
}
