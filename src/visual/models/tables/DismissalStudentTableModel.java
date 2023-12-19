package visual.models.tables;

import dto.DismissalStudentDTO;
import services.ServicesLocator;

public class DismissalStudentTableModel extends MainModelTableModel<DismissalStudentDTO>{
	public DismissalStudentTableModel() {
		super(new String[] {"Año", "Último Grupo", "Nombre y Apellidos", "Municipio", "Causa de Baja"});
	}

	private static final long serialVersionUID = 1L;


	@Override
	public void add(DismissalStudentDTO t) {
		this.addRow(new Object[] {
				ServicesLocator.getAcademicYearServices().getYearsString().get(t.getGroup().getYear().getYear()),
				t.getGroup().getNumGroup(),
				t.getNames()+" "+t.getLastNames(),
				t.getMunicipal(),
				t.getCauseOfDismissal().getCause()
		});
	}
}
