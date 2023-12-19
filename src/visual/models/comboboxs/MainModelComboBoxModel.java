package visual.models.comboboxs;

import java.util.Collections;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

public abstract class MainModelComboBoxModel extends DefaultComboBoxModel<String>{

	private static final long serialVersionUID = 1L;
	
	protected abstract void initialize();
	public MainModelComboBoxModel(List<String> list, boolean sort) {
		initialize();
		if(sort)
			Collections.sort(list);
		if(list!=null)
			for(String s : list)
				this.addElement(s);
	}
	
	public void reload(List<String> list, boolean sort) {
		this.removeAllElements();
		initialize();
		if(sort)
			Collections.sort(list);
		if(list!=null)
			for(String s : list)
				this.addElement(s);
	}

}
