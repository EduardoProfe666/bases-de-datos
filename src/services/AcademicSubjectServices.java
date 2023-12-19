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
import dto.StudentDTO;
import utils.FunctionBuilder;
import utils.FunctionBuilder.FunctionType;

public class AcademicSubjectServices {
	private static final String TABLE_NAME = "academic_subject";

	protected AcademicSubjectServices() {}
	
	public AcademicSubjectDTO getAcademicSubject(int idAcademicSubject) throws SQLException, ClassNotFoundException {
		AcademicSubjectDTO a;
		Connection c = ServicesLocator.getConnection();
		c.setAutoCommit(false);

		String function = FunctionBuilder.constructCrudFunction(FunctionType.GET_BY_ID, TABLE_NAME, 1);
		CallableStatement cs = c.prepareCall(function);
		cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
		cs.setInt(2, idAcademicSubject);
		cs.execute();

		ResultSet rs = (ResultSet) cs.getObject(1);
		rs.next();

		a = new AcademicSubjectDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), new AcademicYearDTO(rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7)));

		rs.close();
		cs.close();
		c.close();

		return a;
	}
	
	public List<AcademicSubjectDTO> getAllAcademicSubject() throws ClassNotFoundException, SQLException{
		ArrayList<AcademicSubjectDTO> a = new ArrayList<>();
		Connection c = ServicesLocator.getConnection();
		c.setAutoCommit(false);
		
		String function = FunctionBuilder.constructCrudFunction(FunctionType.GET_ALL, TABLE_NAME, 0);
		CallableStatement cs = c.prepareCall(function);
		cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);
		while(rs.next()) {
			a.add(new AcademicSubjectDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), new AcademicYearDTO(rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7))));
		}
		rs.close();
		cs.close();
		c.close();

		return a;
	}
	
	public List<AcademicSubjectDTO> getAllAcademicSubjectStd(StudentDTO std) throws ClassNotFoundException, SQLException{
		List<AcademicSubjectDTO> l = getAllAcademicSubject();
		int year = std.getGroup().getYear().getYear();
		int idYear = std.getGroup().getYear().getId();
		List<AcademicSubjectDTO> lista = new LinkedList<AcademicSubjectDTO>();
		
		while(year>0) {
			
			for(AcademicSubjectDTO a : l) {
				if(a.getYear().getId() == idYear) {
					lista.add(a);
				}
			}
			
			year--;
			idYear-=5;
		}
		
		return lista;
	}
	
	public List<AcademicSubjectDTO> getAllAcademicSubjectStdYear(StudentDTO std, int year_) throws ClassNotFoundException, SQLException{
		List<AcademicSubjectDTO> l = getAllAcademicSubject();
		int year = std.getGroup().getYear().getYear();
		int idYear = std.getGroup().getYear().getId();
		List<AcademicSubjectDTO> list = new LinkedList<AcademicSubjectDTO>();
		
		while(year>0) {
			
			if(year_ == year)
				for(AcademicSubjectDTO a : l) {
					if(a.getYear().getId() == idYear) {
						list.add(a);
					}
				}
			
			year--;
			idYear-=5;
		}
		
		return list;
	}
	
	public List<AcademicSubjectDTO> getAllAcademicSubjectDistinctYear(int year) throws ClassNotFoundException, SQLException{
		List<AcademicSubjectDTO> l = getAllAcademicSubject();
		List<AcademicSubjectDTO> list = new LinkedList<>();
		
		for(AcademicSubjectDTO a : l) {
			if(a.getYear().getYear()==year && !list.contains(a)) {
				list.add(a);
			}
		}
		
		return list;
		
	}
	
	public List<AcademicSubjectDTO> getAllAcademicSubjectDistinct() throws ClassNotFoundException, SQLException{
		List<AcademicSubjectDTO> l = getAllAcademicSubject();
		List<AcademicSubjectDTO> list = new LinkedList<>();
		
		for(AcademicSubjectDTO a : l) {
			if(!list.contains(a)) {
				list.add(a);
			}
		}
		
		return list;
		
	}
	
	
}