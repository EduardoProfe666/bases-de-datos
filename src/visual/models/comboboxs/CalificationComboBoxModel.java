package visual.models.comboboxs;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import dto.TypeOfEvaluationDTO;

public class CalificationComboBoxModel extends MainModelComboBoxModel{
	private static final long serialVersionUID = 1L;
	
	public CalificationComboBoxModel(List<TypeOfEvaluationDTO> list) {
		super(toList(list), false);
	}
	
	private static List<String> toList(List<TypeOfEvaluationDTO> list){
		Collections.sort(list, new Comparator<TypeOfEvaluationDTO>() {

			@Override
			public int compare(TypeOfEvaluationDTO o1, TypeOfEvaluationDTO o2) {
				return Integer.compare(o2.getNumber(), o1.getNumber());
			}
		});
		List<String> l = new LinkedList<String>();
		for(TypeOfEvaluationDTO t : list) {
			l.add(t.getEvaluation());
		}
		
		l.add("Sin asignar");
		
		return l;
	}

	@Override
	protected void initialize() {
		this.addElement("<Seleccionar una calificación>");
	}

}
