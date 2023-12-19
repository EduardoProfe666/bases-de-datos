package dto;

public class GroupDTO {
	private int id;
	private int numGroup;
	private int classroom;
	private AcademicYearDTO year;
	
	public GroupDTO(int id, int numGroup, int classroom, AcademicYearDTO year) {
		super();
		this.id = id;
		setNumGroup(numGroup);
		setClassroom(classroom);
		this.year = year;
	}
	public int getClassroom() {
		return classroom;
	}
	public void setClassroom(int classroom) {
		this.classroom = classroom;
	}
	public int getId() {
		return id;
	}
	public AcademicYearDTO getYear() {
		return year;
	}
	public int getNumGroup() {
		return numGroup;
	}
	public void setNumGroup(int numGroup) {
		this.numGroup = numGroup;
	}
	
}
