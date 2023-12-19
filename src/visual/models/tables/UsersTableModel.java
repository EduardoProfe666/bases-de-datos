package visual.models.tables;

import dto.UserDTO;

public class UsersTableModel  extends MainModelTableModel<UserDTO>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsersTableModel() {
		super(new String[] {
				"Rol de Usuario", "Correo Electrónico"
			});
	}

	@Override
	public void add(UserDTO t) {
		this.addRow(new Object[] {t.getRole().getRole(), t.getMail()});
		
	}

}
