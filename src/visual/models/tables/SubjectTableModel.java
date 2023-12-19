package visual.models.tables;

import dto.AcademicSubjectDTO;
import services.ServicesLocator;

public class SubjectTableModel extends MainModelTableModel<AcademicSubjectDTO>{

	private static final long serialVersionUID = 1L;

	public SubjectTableModel() {
		super(new String[] {"Curso Escolar", "Año", "Nombre", "Cantidad de horas"});
	}

	@Override
	public void add(AcademicSubjectDTO t) {
		this.addRow(new Object[] {
				t.getYear().getSchoolCourseStart()+"-"+t.getYear().getSchoolCourseEnd(),
				ServicesLocator.getAcademicYearServices().getYearsString().get(t.getYear().getYear()),
				t.getName(),
				t.getHoursPlan()
		});
	}

}
