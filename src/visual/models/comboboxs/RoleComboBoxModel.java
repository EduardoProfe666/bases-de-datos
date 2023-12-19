package visual.models.comboboxs;

import java.util.List;

public class RoleComboBoxModel extends MainModelComboBoxModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoleComboBoxModel(List<String> list) {
		super(list, true);
	}
	
	@Override
	protected void initialize() {
		this.addElement("<Seleccionar un rol>");
		
	}
}
