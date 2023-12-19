package visual.models.comboboxs;

import java.util.LinkedList;
import java.util.List;

import dto.StudentDTO;

public class StudentComboBoxModel extends MainModelComboBoxModel{
	private static final long serialVersionUID = 1L;

	public StudentComboBoxModel(List<StudentDTO> list) {
		super(toList(list), true);
	}
	
	private static List<String> toList(List<StudentDTO> list){
		List<String> l = new LinkedList<>();
		
		for(StudentDTO s: list) {
			l.add(s.getNames() + " " + s.getLastNames());
		}
		
		return l;
	}
	
	@Override
	protected void initialize() {
		this.addElement("<Seleccionar un estudiante>");
		
	}

}
