package dto;

public class RoleDTO {
	private int id;
	private String role;
	
	public RoleDTO(int id, String role) {
		super();
		this.id = id;
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return role;
	}
	
	
}
