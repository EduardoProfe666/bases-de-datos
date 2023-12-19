package visual.models.tables;

import dto.EvaluationDTO;
import services.ServicesLocator;

public class EvaluationsTSTableModel extends MainModelTableModel<EvaluationDTO>{
	public EvaluationsTSTableModel() {
		super(new String[] { "Curso Escolar", "Año", "Asignatura", "Grupo Actual", "Nombre y Apellidos", "Calificación"});
	}

	private static final long serialVersionUID = 1L;


	@Override
	public void add(EvaluationDTO t) {
		this.addRow(new Object[] {
				t.getAcademicSubject().getYear().getSchoolCourseStart()+"-"+t.getAcademicSubject().getYear().getSchoolCourseEnd(),
				ServicesLocator.getAcademicYearServices().getYearsString().get(t.getAcademicSubject().getYear().getYear()), 
				t.getAcademicSubject().getName(),
				t.getStudent().getGroup().getNumGroup(),
				t.getStudent().getNames()+" "+t.getStudent().getLastNames(),
				t.getTypeOfEvaluation() == null ? "--------" : t.getTypeOfEvaluation().getEvaluation() });
	}
}
