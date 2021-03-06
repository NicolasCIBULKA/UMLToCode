package data;

public class Variable {

	// -------------------
	// Attributs
	// -------------------

	private String name;
	private String type;
	private String visibility;

	// -------------------
	// Methods
	// -------------------

	public Variable(String name, String type, String visibility) {
		this.name = name;
		this.type = type;
		this.visibility = visibility;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String toString() {
		return visibility + " " + type + " " + name;
	}
}
