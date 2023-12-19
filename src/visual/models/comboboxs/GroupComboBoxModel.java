package visual.models.comboboxs;

import java.util.List;

public class GroupComboBoxModel extends MainModelComboBoxModel {
	private static final long serialVersionUID = 1L;
	
	public GroupComboBoxModel(List<String> list) {
		super(list, true);
	}

	@Override
	protected void initialize() {
		this.addElement("<Seleccionar un grupo>");
		
	}
}
