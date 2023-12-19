package dto;

public class DismissalStudentDTO extends StudentDTO{
	private CauseOfDismissalDTO causeOfDismissal;
	
	public DismissalStudentDTO(int id, String names, String lastNames, char sex, String municipal, GroupDTO group, CauseOfDismissalDTO causeOfDismissal) {
		super(id, names, lastNames, sex, municipal, group, 'D');
		setCauseOfDismissal(causeOfDismissal);
	}

	public CauseOfDismissalDTO getCauseOfDismissal() {
		return causeOfDismissal;
	}

	public void setCauseOfDismissal(CauseOfDismissalDTO causeOfDismissal) {
		this.causeOfDismissal = causeOfDismissal;
	}
	
	

}
