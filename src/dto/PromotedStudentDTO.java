package dto;

public class PromotedStudentDTO extends StudentDTO{

	public PromotedStudentDTO(int id, String names, String lastNames, char sex, String municipal, GroupDTO group) {
		super(id, names, lastNames, sex, municipal, group, 'P');
	}

}
