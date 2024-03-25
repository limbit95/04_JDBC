package animal.model.dto;

import animal.model.dto.Tiger;

public class Tiger {

	private String name;
	private char gender;
	private int age;
	private int hungry;
	private char alive;
	
	public Tiger() {}

	public Tiger(String name, char gender, int age, int hungry, char alive) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.hungry = hungry;
		this.alive = alive;
	}

	@Override
	public String toString() {
		return "Tiger [name=" + name + ", gender=" + gender + ", age=" + age + ", hungry=" + hungry + ", alive=" + alive
				+ "]";
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getHungry() {
		return hungry;
	}
	public void setHungry(int hungry) {
		this.hungry = hungry;
	}
	public char isAlive() {
		return alive;
	}
	public void setAlive(char alive) {
		this.alive = alive;
	}
}