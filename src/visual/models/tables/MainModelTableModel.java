package visual.models.tables;

import java.util.List;

import javax.swing.table.DefaultTableModel;


public abstract class MainModelTableModel<T> extends DefaultTableModel{
	
	private static final long serialVersionUID = 1L;
	
	public MainModelTableModel(Object[] columnIdentifiers) {
		this.setColumnIdentifiers(columnIdentifiers);
	}
	
	public void deleteRows() {
		this.setRowCount(0);
	}
	
	public abstract void add(T t);
	
	
	public void reload(List<T> list){
		if(list!=null) {
			this.deleteRows();
			for(T t : list) 
				add(t);
		}
	}
	
	@Override
	public boolean isCellEditable(int row, int column) { 
		return false;
	}
}
