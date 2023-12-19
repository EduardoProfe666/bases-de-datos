package services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.AcademicYearDTO;
import dto.CauseOfDismissalDTO;
import dto.DismissalStudentDTO;
import dto.GroupDTO;
import dto.PromotedStudentDTO;
import dto.RepeaterStudentDTO;
import dto.StudentDTO;
import utils.FunctionBuilder;
import utils.FunctionBuilder.FunctionType;

public class StudentServices {
	private static final String TABLE_NAME = "student";

	protected StudentServices() {}

	//------------- Student ---------------//
	public StudentDTO getStudent(int idStudent) throws ClassNotFoundException, SQLException {
		StudentDTO st = null;
		Connection c = ServicesLocator.getConnection();
		c.setAutoCommit(false);

		String function = FunctionBuilder.constructCrudFunction(FunctionType.GET_BY_ID, TABLE_NAME, 1);
		CallableStatement cs = c.prepareCall(function);
		cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
		cs.setInt(2, idStudent);
		cs.execute();

		ResultSet rs = (ResultSet) cs.getObject(1);
		rs.next();

		st = new StudentDTO(rs.getInt(1), rs.getString(2), rs.getString(6), rs.getString(3).charAt(0), rs.getString(4),
				new GroupDTO(rs.getInt(8), rs.getInt(10), rs.getInt(9), 
						new AcademicYearDTO(rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getInt(14))), rs.getString(7).charAt(0));

		rs.close();
		cs.close();
		c.close();

		return st;

	}
	
	public List<StudentDTO> getAllStudentNotDismissal() throws ClassNotFoundException, SQLException {
		List<StudentDTO> l = getAllStudent();
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		
		for(StudentDTO st : l) {
			if(st.getTypeStd()!='D')
				list.add(st);
		}
		
		return list;
	}

	public List<StudentDTO> getAllStudent() throws ClassNotFoundException, SQLException{
		ArrayList<StudentDTO> a = new ArrayList<>();
		Connection c = ServicesLocator.getConnection();
		c.setAutoCommit(false);

		String function = FunctionBuilder.constructCrudFunction(FunctionType.GET_ALL, TABLE_NAME, 0);
		CallableStatement cs = c.prepareCall(function);
		cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);
		while(rs.next()) {
			a.add(new StudentDTO(rs.getInt(1), rs.getString(2), rs.getString(6), rs.getString(3).charAt(0), rs.getString(4),
					new GroupDTO(rs.getInt(8), rs.getInt(10), rs.getInt(9), 
							new AcademicYearDTO(rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getInt(14))), rs.getString(7).charAt(0)));
		}
		rs.close();
		cs.close();
		c.close();

		return a;
	}

	public void addStudent(String names, String sex, String mun, int idYear, String lastnames, String type, int idGroup, int idCauseDismissal) throws ClassNotFoundException, SQLException{
		Connection c = ServicesLocator.getConnection();

		String function = FunctionBuilder.constructCrudFunction(FunctionType.ADD, TABLE_NAME, 8);
		CallableStatement cs = c.prepareCall(function);
		cs.setString(1, names);
		cs.setString(2, sex);
		cs.setString(3, mun);
		cs.setInt(4, idYear);
		cs.setString(5, lastnames);
		cs.setString(6, type);
		cs.setInt(7, idGroup);
		cs.setInt(8, idCauseDismissal);
		cs.execute();

		cs.close();
		c.close();
	}

	public void updateStudent(StudentDTO student, int idCauseDismissal) throws ClassNotFoundException, SQLException{
		Connection c = ServicesLocator.getConnection();

		String function = FunctionBuilder.constructCrudFunction(FunctionType.UPDATE, TABLE_NAME, 9);
		CallableStatement cs = c.prepareCall(function);
		cs.setInt(1, student.getId());
		cs.setString(2, student.getNames());
		cs.setString(3, String.valueOf(student.getSex()));
		cs.setString(4, student.getMunicipal());
		cs.setInt(5, student.getGroup().getYear().getId());
		cs.setString(6, student.getLastNames());
		cs.setString(7, String.valueOf(student.getTypeStd()));
		cs.setInt(8, student.getGroup().getId());
		cs.setInt(9, idCauseDismissal);
		cs.execute();

		cs.close();
		c.close();
	}

	public void deleteDismissalStudent(DismissalStudentDTO std) throws ClassNotFoundException, SQLException{
		Connection c = ServicesLocator.getConnection();

		String function = FunctionBuilder.constructCrudFunction(FunctionType.DELETE, TABLE_NAME, 1);
		CallableStatement cs = c.prepareCall(function);
		cs.setInt(1, std.getId());
		cs.execute();

		cs.close();
		c.close();
	}

	public double getAvg(int idStd) throws SQLException, ClassNotFoundException {
		Connection c = ServicesLocator.getConnection();

		String function = FunctionBuilder.constructFunction(true, "get_student_average", 1);
		CallableStatement cs = c.prepareCall(function);
		cs.registerOutParameter(1, java.sql.Types.DOUBLE);
		cs.setInt(2, idStd);
		cs.execute();

		Double rs = (Double) cs.getObject(1);

		cs.close();
		c.close();

		return rs.doubleValue();
	}

	
	public boolean validateCourse(int start, int end) throws ClassNotFoundException, SQLException {
		Connection c = ServicesLocator.getConnection();

		String function = FunctionBuilder.constructFunction(true, "validate_course", 2);
		CallableStatement cs = c.prepareCall(function);
		cs.registerOutParameter(1, java.sql.Types.BOOLEAN);
		cs.setInt(2, start);
		cs.setInt(3, end);
		cs.execute();

		Boolean rs = (Boolean) cs.getObject(1);

		cs.close();
		c.close();

		return rs.booleanValue();
	}

	public boolean validatePromotion() throws ClassNotFoundException, SQLException {
		Connection c = ServicesLocator.getConnection();

		String function = FunctionBuilder.constructFunction(true, "validate_promotion", 0);
		CallableStatement cs = c.prepareCall(function);
		cs.registerOutParameter(1, java.sql.Types.BOOLEAN);
		cs.execute();

		Boolean rs = (Boolean) cs.getObject(1);

		cs.close();
		c.close();

		return rs.booleanValue();
	}

	public void makePromotion(int start, int end) throws ClassNotFoundException, SQLException {
		Connection c = ServicesLocator.getConnection();

		String function = FunctionBuilder.constructFunction(false, "make_promotion", 2);
		CallableStatement cs = c.prepareCall(function);
		cs.setInt(1, start);
		cs.setInt(2, end);
		cs.execute();
		
		cs.close();
		c.close();
	}


	//----------- Dismissal ----------//
	public DismissalStudentDTO getDismissalStudent(int idDismissalStudent) throws SQLException, ClassNotFoundException {
		DismissalStudentDTO a;
		Connection c = ServicesLocator.getConnection();
		c.setAutoCommit(false);

		String function = FunctionBuilder.constructCrudFunction(FunctionType.GET_BY_ID, "dismissal", 1);
		CallableStatement cs = c.prepareCall(function);
		cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
		cs.setInt(2, idDismissalStudent);
		cs.execute();

		ResultSet rs = (ResultSet) cs.getObject(1);
		rs.next();

		a = new DismissalStudentDTO(rs.getInt(1), rs.getString(2), rs.getString(6), rs.getString(3).charAt(0), rs.getString(4),
				new GroupDTO(rs.getInt(8), rs.getInt(10), rs.getInt(9), 
						new AcademicYearDTO(rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getInt(14))), 
				new CauseOfDismissalDTO(rs.getInt(15), rs.getString(16)));

		rs.close();
		cs.close();
		c.close();

		return a;
	}


	public List<DismissalStudentDTO> getAllDismissalStudent() throws ClassNotFoundException, SQLException{
		ArrayList<DismissalStudentDTO> a = new ArrayList<>();
		Connection c = ServicesLocator.getConnection();
		c.setAutoCommit(false);

		String function = FunctionBuilder.constructCrudFunction(FunctionType.GET_ALL, "dismissal", 0);
		CallableStatement cs = c.prepareCall(function);
		cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);
		while(rs.next()) {
			a.add(new DismissalStudentDTO(rs.getInt(1), rs.getString(2), rs.getString(6), rs.getString(3).charAt(0), rs.getString(4),
					new GroupDTO(rs.getInt(8), rs.getInt(10), rs.getInt(9), 
							new AcademicYearDTO(rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getInt(14))), 
					new CauseOfDismissalDTO(rs.getInt(15), rs.getString(16))));
		}
		rs.close();
		cs.close();
		c.close();

		return a;
	}

	//---------------- Repeater -------------//
	public RepeaterStudentDTO getRepeaterStudent(int idRepeaterStudent) throws SQLException, ClassNotFoundException {
		RepeaterStudentDTO a;
		Connection c = ServicesLocator.getConnection();
		c.setAutoCommit(false);

		String function = FunctionBuilder.constructCrudFunction(FunctionType.GET_BY_ID, "repeater", 1);
		CallableStatement cs = c.prepareCall(function);
		cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
		cs.setInt(2, idRepeaterStudent);
		cs.execute();

		ResultSet rs = (ResultSet) cs.getObject(1);
		rs.next();

		a = new RepeaterStudentDTO(rs.getInt(1), rs.getString(2), rs.getString(6), rs.getString(3).charAt(0), rs.getString(4),
				new GroupDTO(rs.getInt(8), rs.getInt(10), rs.getInt(9), 
						new AcademicYearDTO(rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getInt(14))));

		rs.close();
		cs.close();
		c.close();

		return a;
	}


	public List<RepeaterStudentDTO> getAllRepeaterStudent() throws ClassNotFoundException, SQLException{
		ArrayList<RepeaterStudentDTO> a = new ArrayList<>();
		Connection c = ServicesLocator.getConnection();
		c.setAutoCommit(false);

		String function = FunctionBuilder.constructCrudFunction(FunctionType.GET_ALL, "repeater", 0);
		CallableStatement cs = c.prepareCall(function);
		cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);
		while(rs.next()) {
			a.add(new RepeaterStudentDTO(rs.getInt(1), rs.getString(2), rs.getString(6), rs.getString(3).charAt(0), rs.getString(4),
					new GroupDTO(rs.getInt(8), rs.getInt(10), rs.getInt(9), 
							new AcademicYearDTO(rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getInt(14)))));
		}
		rs.close();
		cs.close();
		c.close();

		return a;
	}

	//----------------- Promoted --------------//
	public PromotedStudentDTO getPromotedStudent(int idPromotedStudent) throws SQLException, ClassNotFoundException {
		PromotedStudentDTO a;
		Connection c = ServicesLocator.getConnection();
		c.setAutoCommit(false);

		String function = FunctionBuilder.constructCrudFunction(FunctionType.GET_BY_ID, "promoted", 1);
		CallableStatement cs = c.prepareCall(function);
		cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
		cs.setInt(2, idPromotedStudent);
		cs.execute();

		ResultSet rs = (ResultSet) cs.getObject(1);
		rs.next();

		a = new PromotedStudentDTO(rs.getInt(1), rs.getString(2), rs.getString(6), rs.getString(3).charAt(0), rs.getString(4),
				new GroupDTO(rs.getInt(8), rs.getInt(10), rs.getInt(9), 
						new AcademicYearDTO(rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getInt(14))));

		rs.close();
		cs.close();
		c.close();

		return a;
	}


	public List<PromotedStudentDTO> getAllPromotedStudent() throws ClassNotFoundException, SQLException{
		ArrayList<PromotedStudentDTO> a = new ArrayList<>();
		Connection c = ServicesLocator.getConnection();
		c.setAutoCommit(false);

		String function = FunctionBuilder.constructCrudFunction(FunctionType.GET_ALL, "promoted", 0);
		CallableStatement cs = c.prepareCall(function);
		cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);
		while(rs.next()) {
			a.add(new PromotedStudentDTO(rs.getInt(1), rs.getString(2), rs.getString(6), rs.getString(3).charAt(0), rs.getString(4),
					new GroupDTO(rs.getInt(8), rs.getInt(10), rs.getInt(9), 
							new AcademicYearDTO(rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getInt(14)))));
		}
		rs.close();
		cs.close();
		c.close();

		return a;
	}

}
