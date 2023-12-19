package visual.models.tables;

import dto.StudentDTO;
import services.ServicesLocator;

public class StudentTableModel  extends MainModelTableModel<StudentDTO>{
	public StudentTableModel() {
		super(new String[] {"Año", "Grupo", "Nombre y Apellidos", "Sexo", "Municipio", "Estado"});
	}

	private static final long serialVersionUID = 1L;


	@Override
	public void add(StudentDTO t) {
		this.addRow(new Object[] {
				ServicesLocator.getAcademicYearServices().getYearsString().get(t.getGroup().getYear().getYear()),
				t.getGroup().getNumGroup(),
				t.getNames()+" "+t.getLastNames(),
				t.getSex()=='F' ? "Femenino" : "Masculino",
				t.getMunicipal(),
				t.getTypeStd()=='P' ? "Promovido" : (t.getTypeStd()=='D' ? "Baja" : "Repitente")  
		});
	}
}
