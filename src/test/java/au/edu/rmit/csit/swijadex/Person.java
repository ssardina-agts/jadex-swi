package au.edu.rmit.csit.swijadex;

public class Person {

	private String name;
	private int age;
	private String gender;
	private Person spouse;
	
	public Person(String name, int age, String gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	public Person(String name, int age) {
		this(name, age, null);
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public String getGender() {
		return this.gender;
	}
	
	public Person getSpouse() {
		return this.spouse;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setSpouse(Person spouse) {
		this.spouse = spouse;
	}
	
	public String toString() {
		return "{"
				+ "name: " + this.getName()
				+ ", age: " + this.getAge()
				+ ", gender: " + this.getGender()
				+ ", spouse: " + this.getSpouse()
			+ "}";
	}

	public void dumpDetails() {
		System.out.println(this);
	}

}
