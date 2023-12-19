package dto;

public class AcademicYearDTO {
	private int id;
	private int year;
	private int schoolCourseStart;
	private int schoolCourseEnd;

	
	public AcademicYearDTO(int id, int year, int schoolCourseStart, int schoolCourseEnd) {
		this.id = id;
		setYear(year);
		setSchoolCourseEnd(schoolCourseEnd);
		setSchoolCourseStart(schoolCourseStart);
	}
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getSchoolCourseStart() {
		return schoolCourseStart;
	}
	public void setSchoolCourseStart(int schoolCourseStart) {
		this.schoolCourseStart = schoolCourseStart;
	}
	public int getSchoolCourseEnd() {
		return schoolCourseEnd;
	}
	public void setSchoolCourseEnd(int schoolCourseEnd) {
		this.schoolCourseEnd = schoolCourseEnd;
	}
	public int getId() {
		return id;
	}
	
	
	
}
