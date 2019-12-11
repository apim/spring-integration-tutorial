package apim.github.tutorial;

public class Item {

	private String name;

	private int dimension;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public String toString() {
		return name + "-" + dimension;
	}

}
