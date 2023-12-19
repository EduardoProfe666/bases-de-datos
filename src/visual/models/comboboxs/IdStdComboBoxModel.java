package visual.models.comboboxs;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import dto.StudentDTO;

public class IdStdComboBoxModel extends MainModelComboBoxModel{
	private static final long serialVersionUID = 1L;
	
	public IdStdComboBoxModel(List<StudentDTO> list) {
		super(toList(list), false);
	}
	
	private static List<String> toList(List<StudentDTO> list){
		Collections.sort(list, new Comparator<StudentDTO>() {

			@Override
			public int compare(StudentDTO o1, StudentDTO o2) {
				return Integer.compare(o1.getId(), o2.getId());
			}
		});
		List<String> l = new LinkedList<String>();
		for(StudentDTO t : list) {
			l.add(String.valueOf(t.getId()));
		}
		
		return l;
	}

	@Override
	protected void initialize() {
		this.addElement("<Seleccionar un id de estudiante>");
	}
}
