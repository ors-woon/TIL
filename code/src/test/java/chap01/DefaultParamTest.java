package chap01;

import cookbook.chap01.Coffee;
import cookbook.chap01.DefaultParameterKt;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DefaultParamTest {

	@Test
	@DisplayName("default param from kotlin")
	void defaultParam() {
		String name = "철운";
		String age = "28";
		int weight = 83;

		//DefaultParameterKt.buildPerson(name, age);
		Person person = DefaultParameterKt.buildPersonSupportedJava(name, age);

		assertEquals(name, person.getName());
		assertEquals(age, person.getAge());
		assertEquals(0, person.getWeight());
	}


	@Test
	@DisplayName("default param with constructor from kotlin")
	void defaultParamConstructor() {
		Coffee coffee = new Coffee("americano");

		// super 가 다르게 동작함.
		// 2개의 param 을 받고 default param 을 설정하면 그만큼의 생성자가 생김
		// 다만 생성된 생성자에서는 super 를 호출하지 않음
		// 코드 짜자
		assertTrue(coffee.getHasCaffeine());
		
	}
}
