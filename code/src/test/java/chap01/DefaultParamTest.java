package chap01;

import cookbook.chap01.CustomMap;
import cookbook.chap01.DefaultParameterKt;
import cookbook.chap02.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultParamTest {

	@Test
	void defaultParameter() {
		String hhkb = "HHKB";
		CustomMap<String, String> keyboardGroupCountry = new CustomMap<>(new HashMap<>());
		keyboardGroupCountry.put("japan", hhkb);
		keyboardGroupCountry.put("korea", "한성");

		// annotation 을 달지 않으면, default param 지원 x
		//DefaultParameterKt.add(keyboardGroupCountry,"japan","real-force")
		DefaultParameterKt.add(keyboardGroupCountry, "japan", "real-force", "!?");

		assertEquals(hhkb, keyboardGroupCountry.get("japan"));
	}

	@Test
	@DisplayName("kotlin 의 constructor keyword 필요")
	void defaultParameterWithConstructor() {
		String hhkb = "HHKB";
		CustomMap<String, String> keyboardGroupCountry = new CustomMap<>();
		keyboardGroupCountry.put("japan", hhkb);
		keyboardGroupCountry.put("korea", "한성");

		DefaultParameterKt.add(keyboardGroupCountry, "japan", "real-force", "notMean");

		assertEquals(hhkb, keyboardGroupCountry.get("japan"));
	}

}
