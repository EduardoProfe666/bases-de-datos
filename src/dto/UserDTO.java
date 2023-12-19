package dto;

public class UserDTO {
	private int id;
	private String mail;
	private String password;
	private int idStd;
	private RoleDTO role;
	
	public UserDTO(int id, String mail, String password, int idStd, RoleDTO role) {
		this.id = id;
		setMail(mail);
		setPassword(password);
		setIdStd(idStd);
		this.role = role;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getIdStd() {
		return idStd;
	}
	public void setIdStd(int idStd) {
		this.idStd = idStd;
	}
	public int getId() {
		return id;
	}
	public RoleDTO getRole() {
		return role;
	}
	
	@Override
	public String toString() {
		return mail;
	}
	
}
