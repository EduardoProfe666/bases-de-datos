package visual.models.comboboxs;

import java.util.List;

public class YearComboBoxModel extends MainModelComboBoxModel{
	private static final long serialVersionUID = 1L;

	public YearComboBoxModel(List<String> list) {
		super(list, false);
	}
	
	@Override
	protected void initialize() {
		this.addElement("<Seleccionar un año>");
		
	}

}
