package visual.models.comboboxs;

import java.util.LinkedList;
import java.util.List;

import dto.AcademicSubjectDTO;

public class SubjectComboBoxModel extends MainModelComboBoxModel{
	private static final long serialVersionUID = 1L;

	public SubjectComboBoxModel(List<AcademicSubjectDTO> list) {
		super(toList(list), true);
	}
	
	private static List<String> toList(List<AcademicSubjectDTO> list){
		List<String> l = new LinkedList<String>();
		
		for(AcademicSubjectDTO s : list) {
			l.add(s.getName());
		}
		return l;
	}

	@Override
	protected void initialize() {
		this.addElement("<Seleccionar una asignatura>");
		
	}
	
	public void reload(List<AcademicSubjectDTO> l) {
		reload(toList(l), true);
	}

}
