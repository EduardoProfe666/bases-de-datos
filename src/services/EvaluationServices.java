package services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import dto.AcademicSubjectDTO;
import dto.AcademicYearDTO;
import dto.EvaluationDTO;
import dto.GroupDTO;
import dto.StudentDTO;
import dto.TypeOfEvaluationDTO;
import utils.FunctionBuilder;
import utils.FunctionBuilder.FunctionType;

public class EvaluationServices {
	private static final String TABLE_NAME = "evaluation";

	protected EvaluationServices() {}
	
	public EvaluationDTO getEvaluation(int idSub, int idStd) throws SQLException, ClassNotFoundException {
		EvaluationDTO a;
		Connection c = ServicesLocator.getConnection();
		c.setAutoCommit(false);

		String function = FunctionBuilder.constructCrudFunction(FunctionType.GET_BY_ID, TABLE_NAME, 2);
		CallableStatement cs = c.prepareCall(function);
		cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
		cs.setInt(2, idSub);
		cs.setInt(3, idStd);
		cs.execute();

		ResultSet rs = (ResultSet) cs.getObject(1);
		rs.next();

		AcademicSubjectDTO as = new AcademicSubjectDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), new AcademicYearDTO(rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7)));
		GroupDTO gr = new GroupDTO(rs.getInt(15), rs.getInt(16), rs.getInt(17), null);
		StudentDTO st = new StudentDTO(rs.getInt(8), rs.getString(9), rs.getString(13), rs.getString(10).charAt(0), rs.getString(11), gr, rs.getString(14).charAt(0));
		TypeOfEvaluationDTO type = null;
		if(rs.getInt(18) != 0)
			type = new TypeOfEvaluationDTO(rs.getInt(18), rs.getString(19), rs.getInt(20));

		a = new EvaluationDTO(type,as,st);

		rs.close();
		cs.close();
		c.close();

		return a;
	}
	
	public StudentDTO getCorrectDataOfStudentGroup(int idStd) throws ClassNotFoundException, SQLException {
		return ServicesLocator.getStudentServices().getStudent(idStd);
	}

	public List<EvaluationDTO> getAllEvaluation() throws ClassNotFoundException, SQLException{
		ArrayList<EvaluationDTO> a = new ArrayList<>();
		Connection c = ServicesLocator.getConnection();
		c.setAutoCommit(false);

		String function = FunctionBuilder.constructCrudFunction(FunctionType.GET_ALL, TABLE_NAME, 0);
		CallableStatement cs = c.prepareCall(function);
		cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);
		
		while(rs.next()) {
			AcademicSubjectDTO as = new AcademicSubjectDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), new AcademicYearDTO(rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7)));
			GroupDTO gr = new GroupDTO(rs.getInt(15), rs.getInt(17), rs.getInt(16), null);
			StudentDTO st = new StudentDTO(rs.getInt(8), rs.getString(9), rs.getString(13), rs.getString(10).charAt(0), rs.getString(11), gr, rs.getString(14).charAt(0));
			TypeOfEvaluationDTO type = null;
			if(rs.getInt(18) != 0)
				type = new TypeOfEvaluationDTO(rs.getInt(18), rs.getString(19), rs.getInt(20));

			a.add(new EvaluationDTO(type,as,st));
		}
		rs.close();
		cs.close();
		c.close();

		return a;
	}
	
	public List<EvaluationDTO> getAllEvaluationUnasigned() throws ClassNotFoundException, SQLException{
		ArrayList<EvaluationDTO> a = new ArrayList<>();
		Connection c = ServicesLocator.getConnection();
		c.setAutoCommit(false);

		String function = FunctionBuilder.constructCrudFunction(FunctionType.GET_ALL, "evaluation_null", 0);
		CallableStatement cs = c.prepareCall(function);
		cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);
		
		while(rs.next()) {
			AcademicSubjectDTO as = new AcademicSubjectDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), new AcademicYearDTO(rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7)));
			GroupDTO gr = new GroupDTO(rs.getInt(15), rs.getInt(17), rs.getInt(16), null);
			StudentDTO st = new StudentDTO(rs.getInt(8), rs.getString(9), rs.getString(13), rs.getString(10).charAt(0), rs.getString(11), gr, rs.getString(14).charAt(0));
			TypeOfEvaluationDTO type = null;

			a.add(new EvaluationDTO(type,as,st));
		}
		rs.close();
		cs.close();
		c.close();

		return a;
	}

	public void updateEvaluation(EvaluationDTO evaluation) throws ClassNotFoundException, SQLException{
		Connection c = ServicesLocator.getConnection();

		String function = FunctionBuilder.constructCrudFunction(FunctionType.UPDATE, TABLE_NAME, 3);
		CallableStatement cs = c.prepareCall(function);
		cs.setInt(1, evaluation.getAcademicSubject().getId());
		cs.setInt(2, evaluation.getStudent().getId());
		cs.setInt(3, evaluation.getTypeOfEvaluation().getId());
		cs.execute();

		cs.close();
		c.close();
	}
	
	public List<EvaluationDTO>  getEvaluationsStd(StudentDTO std) throws ClassNotFoundException, SQLException {
		List<EvaluationDTO> list = getAllEvaluation();
		List<EvaluationDTO> listU = getAllEvaluationUnasigned();
		List<EvaluationDTO> l = new LinkedList<EvaluationDTO>();
		
		for(EvaluationDTO e: list) {
			if(e.getStudent().getId() == std.getId())
				l.add(e);
		}
		
		for(EvaluationDTO e: listU) {
			if(e.getStudent().getId() == std.getId())
				l.add(e);
		}
		
		return l;
	}
	
	public List<EvaluationDTO> getAllEvaluationTS() throws ClassNotFoundException, SQLException{
		List<EvaluationDTO> list = getAllEvaluation();
		list.addAll(getAllEvaluationUnasigned());
		return list;
	}
}
