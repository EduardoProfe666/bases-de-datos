package visual.models.tables;

import dto.EvaluationDTO;
import services.ServicesLocator;

public class EvaluationsStudentTableModel extends MainModelTableModel<EvaluationDTO>{
	public EvaluationsStudentTableModel() {
		super(new String[] { "Año", "Curso Escolar", "Asignatura", "Calificación"});
	}

	private static final long serialVersionUID = 1L;


	@Override
	public void add(EvaluationDTO t) {
		this.addRow(new Object[] {ServicesLocator.getAcademicYearServices().getYearsString().get(t.getAcademicSubject().getYear().getYear()), 
				t.getAcademicSubject().getYear().getSchoolCourseStart()+"-"+t.getAcademicSubject().getYear().getSchoolCourseEnd(),
				t.getAcademicSubject().getName(),
				t.getTypeOfEvaluation() == null ? "--------" : t.getTypeOfEvaluation().getEvaluation() });
	}



}
