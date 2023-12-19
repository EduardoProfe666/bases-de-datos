package services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dto.AcademicYearDTO;
import dto.StudentDTO;
import utils.FunctionBuilder;
import utils.FunctionBuilder.FunctionType;

public class AcademicYearServices {
	private static final String TABLE_NAME = "academic_year";

	protected AcademicYearServices() {}
	
	public HashMap<Integer, String> getYearsString(){
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		
		map.put(1, "Primero");
		map.put(2, "Segundo");
		map.put(3, "Tercero");
		map.put(4, "Cuarto");
		
		return map;
	}
	
	public HashMap<String, Integer> getYearsInt(){
		HashMap<String, Integer> map = new HashMap<>();
		
		map.put("Primero", 1);
		map.put("Segundo",2);
		map.put("Tercero", 3);
		map.put("Cuarto", 4);
		
		return map;
	}
	
	public AcademicYearDTO getAcademicYear(int idAcademicYear) throws SQLException, ClassNotFoundException {
		AcademicYearDTO a;
		Connection c = ServicesLocator.getConnection();
		c.setAutoCommit(false);

		String function = FunctionBuilder.constructCrudFunction(FunctionType.GET_BY_ID, TABLE_NAME, 1);
		CallableStatement cs = c.prepareCall(function);
		cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
		cs.setInt(2, idAcademicYear);
		cs.execute();

		ResultSet rs = (ResultSet) cs.getObject(1);
		rs.next();

		a = new AcademicYearDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));

		rs.close();
		cs.close();
		c.close();

		return a;
	}
	
	public List<AcademicYearDTO> getAllAcademicYear() throws ClassNotFoundException, SQLException{
		ArrayList<AcademicYearDTO> a = new ArrayList<>();
		Connection c = ServicesLocator.getConnection();
		c.setAutoCommit(false);
		
		String function = FunctionBuilder.constructCrudFunction(FunctionType.GET_ALL, TABLE_NAME, 0);
		CallableStatement cs = c.prepareCall(function);
		cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);
		while(rs.next()) {
			a.add(new AcademicYearDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));
		}
		rs.close();
		cs.close();
		c.close();

		return a;
	}
	
	public List<String> getAcademicYearsStd(StudentDTO std){
		List<String> l = new ArrayList<String>();
		HashMap<Integer, String> map = getYearsString();
		int year = std.getGroup().getYear().getYear();
		int a = 1;
		
		while(a<=year) {
			l.add(map.get(a));
			a++;
		}
		
		return l;
	}
	
	public List<String> getAcademicYears(){
		return new ArrayList<String>(getYearsString().values());
	}
	
	public List<String> getCourses() throws ClassNotFoundException, SQLException{
		List<String> courses = new ArrayList<>();
		
		List<AcademicYearDTO> list = getAllAcademicYear();
		
		for(AcademicYearDTO a: list) {
			String s = a.getSchoolCourseStart()+"-"+a.getSchoolCourseEnd();
			if(!courses.contains(s))
				courses.add(s);
		}
		
		return courses;
	}
	
}
