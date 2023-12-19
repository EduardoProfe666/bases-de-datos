package visual.models.comboboxs;

import java.util.List;

public class AcademicCourseComboBoxModel extends MainModelComboBoxModel{
	private static final long serialVersionUID = 1L;

	public AcademicCourseComboBoxModel(List<String> list) {
		super(list, true);
	}

	@Override
	protected void initialize() {
		this.addElement("<Seleccionar un curso escolar>");
		
	}

}
