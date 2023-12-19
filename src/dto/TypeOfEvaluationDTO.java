package dto;

public class TypeOfEvaluationDTO {
	private int id;
	private String evaluation;
	private int number;
	
	public TypeOfEvaluationDTO(int id, String evaluation, int number) {
		super();
		this.id = id;
		this.evaluation = evaluation;
		this.number = number;
	}

	public String getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return evaluation;
	}
	
	
	
}

