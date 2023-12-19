package dto;

public class EvaluationDTO {
	private TypeOfEvaluationDTO typeOfEvaluation;
	private AcademicSubjectDTO academicSubject;
	private StudentDTO student;
	
	public EvaluationDTO(TypeOfEvaluationDTO typeOfEvaluation, AcademicSubjectDTO academicSubject, StudentDTO student) {
		super();
		setTypeOfEvaluation(typeOfEvaluation);
		setAcademicSubject(academicSubject);
		setStudent(student);
	}
	public TypeOfEvaluationDTO getTypeOfEvaluation() {
		return typeOfEvaluation;
	}
	public void setTypeOfEvaluation(TypeOfEvaluationDTO typeOfEvaluation) {
		this.typeOfEvaluation = typeOfEvaluation;
	}
	public AcademicSubjectDTO getAcademicSubject() {
		return academicSubject;
	}
	public void setAcademicSubject(AcademicSubjectDTO academicSubject) {
		this.academicSubject = academicSubject;
	}
	public StudentDTO getStudent() {
		return student;
	}
	public void setStudent(StudentDTO student) {
		this.student = student;
	}
	
	@Override
	public String toString() {
		return typeOfEvaluation.getEvaluation()+" "+academicSubject.getName()+" "+student.getNames();
	}
}
