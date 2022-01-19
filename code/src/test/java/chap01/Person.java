package chap01;

import org.springframework.lang.NonNull;

public class Person {
	private String name;
	private String age;
	private int weight;

	public Person(String name, String age, int weight) {
		this.name = name;
		this.age = age;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public String getAge() {
		return age;
	}

	public int getWeight() {
		return weight;
	}

	public String getNameWithPrefix(@NonNull String prefix){
		return prefix + name;
	}
}
