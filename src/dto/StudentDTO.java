package dto;

public class StudentDTO {
	private int id;
	private String names;
	private String lastNames;
	private char sex;
	private String municipal;
	private GroupDTO group;
	private char typeStd;
	
	public StudentDTO(int id, String names, String lastNames, char sex, String municipal, GroupDTO group, char typeStd) {
		super();
		this.id = id;
		setNames(names);
		setLastNames(lastNames);
		setSex(sex);
		setMunicipal(municipal);
		this.group = group;
		this.typeStd = typeStd;
	}
	
	public char getTypeStd() {
		return typeStd;
	}
	
	public void setTypeStd(char typeStd) {
		this.typeStd = typeStd;
	}

	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public String getLastNames() {
		return lastNames;
	}
	public void setLastNames(String lastNames) {
		this.lastNames = lastNames;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public String getMunicipal() {
		return municipal;
	}
	public void setMunicipal(String municipal) {
		this.municipal = municipal;
	}
	public int getId() {
		return id;
	}
	public GroupDTO getGroup() {
		return group;
	}
	
	
}
