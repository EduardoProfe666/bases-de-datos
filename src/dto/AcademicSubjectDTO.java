package dto;

public class AcademicSubjectDTO {
	private int id;
	private String name;
	private int hoursPlan;
	private AcademicYearDTO year;

	public AcademicSubjectDTO(int id, String name, int hoursPlan, AcademicYearDTO year) {
		super();
		this.id = id;
		setName(name);
		setHoursPlan(hoursPlan);
		this.year = year;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHoursPlan() {
		return hoursPlan;
	}

	public void setHoursPlan(int hoursPlan) {
		this.hoursPlan = hoursPlan;
	}

	public int getId() {
		return id;
	}
	public AcademicYearDTO getYear() {
		return year;
	}
	
	@Override
	public boolean equals(Object obj) {
		return ((AcademicSubjectDTO)obj).getName().equals(this.getName());
	}
	
}
