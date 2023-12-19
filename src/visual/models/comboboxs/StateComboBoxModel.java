package visual.models.comboboxs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StateComboBoxModel extends MainModelComboBoxModel{

	private static final long serialVersionUID = 1L;

	public StateComboBoxModel() {
		super(list(), false);
	}
	
	public static List<String> list(){
		return new ArrayList<>(Arrays.asList("Promovido","Repitente","Baja"));
	}

	@Override
	protected void initialize() {
		
	}

}
