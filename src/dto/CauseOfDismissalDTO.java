package dto;

public class CauseOfDismissalDTO {
	private int id;
	private String cause;

	public CauseOfDismissalDTO(int id, String cause) {
		this.id=id;
		this.cause=cause;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return cause;
	}
}
