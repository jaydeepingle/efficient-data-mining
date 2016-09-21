//import java.util.ArrayList;
//import java.util.List;

public class MinedData {
	private String age;
	private String gender;
	private String zipCode;
	private String disease;

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public MinedData() {

	}

	public MinedData(String age, String gender, String zipCode, String disease) {
		// TODO Auto-generated constructor stub
		this.age = age;
		this.gender = gender;
		this.zipCode = zipCode;
		this.disease = disease;
	}
}
