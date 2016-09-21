
public class Preserve {
	private String name;
	private Double age;
	private String gender;
	private Double zipCode;
	private String disease;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getAge() {
		return age;
	}

	public void setAge(Double age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	
	public Double getZipCode() {
		return zipCode;
	}

	public void setZipCode(Double zipCode) {
		this.zipCode = zipCode;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public Preserve() {

	}

	public Preserve(String name, Double age, String gender, Double zipCode, String disease) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.zipCode = zipCode;
		this.disease = disease;
	}
}
