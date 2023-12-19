package dto;

public class RepeaterStudentDTO extends StudentDTO{

	public RepeaterStudentDTO(int id, String names, String lastNames, char sex, String municipal, GroupDTO group) {
		super(id, names, lastNames, sex, municipal, group, 'R');
	}

}
