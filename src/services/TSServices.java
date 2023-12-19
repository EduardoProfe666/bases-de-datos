package services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dto.AcademicYearDTO;
import dto.GroupDTO;
import dto.StudentDTO;
import utils.FunctionBuilder;

public class TSServices {
	protected TSServices() {}
	
	public int getLastIdYear(int year) throws ClassNotFoundException, SQLException {
		List<AcademicYearDTO> l = ServicesLocator.getAcademicYearServices().getAllAcademicYear();
		Collections.reverse(l);
		int idYear = -1;
		
		for(int i=0;i<l.size() && idYear == -1;i++) {
			if(l.get(i).getYear()==year)
				idYear = l.get(i).getId();
		}
		return idYear;
	}
	
	public int getIdGroup(int idYear, int group) throws ClassNotFoundException, SQLException {
		List<GroupDTO> l = ServicesLocator.getGroupServices().getAllGroup();
		int idGroup = -1;
		
		for(int i=0;i<l.size() && idGroup == -1;i++) {
			if(l.get(i).getYear().getId() == idYear && l.get(i).getNumGroup()==group)
				idGroup = l.get(i).getId();
		}
		
		return idGroup;
	}
	
	public List<AuxEscalafon> getEscalafonGroup(int idGroup, int idYear) throws ClassNotFoundException, SQLException{
		ArrayList<AuxEscalafon> a = new ArrayList<>();
		Connection c = ServicesLocator.getConnection();
		c.setAutoCommit(false);
		
		String function = FunctionBuilder.constructFunction(true, "get_escalafon_group", 2);
		CallableStatement cs = c.prepareCall(function);
		cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
		cs.setInt(2, idYear);
		cs.setInt(3, idGroup);
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);
		while(rs.next()) {
			StudentDTO std = new StudentDTO(rs.getInt(1), rs.getString(2), rs.getString(6), rs.getString(3).charAt(0), rs.getString(4),
								new GroupDTO(rs.getInt(8), rs.getInt(10), rs.getInt(9), 
										new AcademicYearDTO(rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getInt(14))), rs.getString(7).charAt(0));
			double avg = rs.getDouble(15);
			a.add(new AuxEscalafon(std, 0, 0, avg));
		}
		rs.close();
		cs.close();
		c.close();
		
		Collections.sort(a, new Comparator<AuxEscalafon>() {

			@Override
			public int compare(AuxEscalafon o1, AuxEscalafon o2) {
				String n1 = o1.getStd().getLastNames() +" " + o1.getStd().getNames();
				String n2 = o2.getStd().getLastNames() +" " + o2.getStd().getNames();
				return n1.compareTo(n2);
			}
		});
		
		for(int i=1;i<=a.size();i++) {
			a.get(i-1).setNumGroup(i);
		}
		
		Collections.sort(a);
		
		for(int i=1;i<=a.size();i++) {
			a.get(i-1).setNumOrder(i);
		}
		
		return a;
	}
	
	public List<AuxEscalafon> getEscalafonYear(int idYear) throws ClassNotFoundException, SQLException{
		ArrayList<AuxEscalafon> a = new ArrayList<>();
		Connection c = ServicesLocator.getConnection();
		c.setAutoCommit(false);
		
		String function = FunctionBuilder.constructFunction(true, "get_escalafon_year", 1);
		CallableStatement cs = c.prepareCall(function);
		cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
		cs.setInt(2, idYear);
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);
		while(rs.next()) {
			StudentDTO std = new StudentDTO(rs.getInt(1), rs.getString(2), rs.getString(6), rs.getString(3).charAt(0), rs.getString(4),
								new GroupDTO(rs.getInt(8), rs.getInt(10), rs.getInt(9), 
										new AcademicYearDTO(rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getInt(14))), rs.getString(7).charAt(0));
			double avg = rs.getDouble(15);
			a.add(new AuxEscalafon(std, 0, 0, avg));
		}
		rs.close();
		cs.close();
		c.close();
		
		Collections.sort(a);
		
		for(int i=1;i<=a.size();i++) {
			a.get(i-1).setNumOrder(i);
		}
		
		return a;
	}
	
	public static class AuxEscalafon implements Comparable<AuxEscalafon>{
		private StudentDTO std;
		private int numOrder;
		private int numGroup;
		private double avg;
		
		public AuxEscalafon(StudentDTO std, int numOrder, int numGroup, double avg) {
			super();
			this.std = std;
			this.numOrder = numOrder;
			this.numGroup = numGroup;
			this.avg = avg;
		}
		public StudentDTO getStd() {
			return std;
		}
		public void setStd(StudentDTO std) {
			this.std = std;
		}
		public int getNumOrder() {
			return numOrder;
		}
		public void setNumOrder(int numOrder) {
			this.numOrder = numOrder;
		}
		public double getAvg() {
			return avg;
		}
		public void setAvg(double avg) {
			this.avg = avg;
		}
		public int getNumGroup() {
			return numGroup;
		}
		public void setNumGroup(int numGroup) {
			this.numGroup = numGroup;
		}
		@Override
		public int compareTo(AuxEscalafon o) {
			return Double.compare(o.avg, this.avg);
		}
		
	}
}	
