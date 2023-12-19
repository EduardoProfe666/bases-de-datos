package visual.models.comboboxs;

import java.util.LinkedList;
import java.util.List;

import dto.CauseOfDismissalDTO;

public class CauseOfDismissalComboBoxModel extends MainModelComboBoxModel{
	private static final long serialVersionUID = 1L;
	
	public CauseOfDismissalComboBoxModel(List<CauseOfDismissalDTO> list) {
		super(toList(list), true);
	}
	
	private static List<String> toList(List<CauseOfDismissalDTO> list){
		List<String> l = new LinkedList<String>();
		for(CauseOfDismissalDTO t : list) {
			l.add(t.getCause());
		}

		
		return l;
	}

	@Override
	protected void initialize() {
		this.addElement("<Seleccionar una causa de baja>");
	}
}
