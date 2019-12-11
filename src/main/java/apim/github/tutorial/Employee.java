package apim.github.tutorial;

public class Employee {

	private String fullName;

	private LevelEnum band;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public LevelEnum getBand() {
		return band;
	}

	public void setBand(LevelEnum band) {
		this.band = band;
	}

	public enum LevelEnum {
		HIGH, MEDIUM, LOW
	}

}
