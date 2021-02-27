package inaction;

import book.inaction.JvmOverloads;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JvmOverloadsTest {

	@Test
	public void useNotJvmOverload(){
		String expected = "hello world";

		var str = new JvmOverloads("world");

		assertEquals(expected, str.hello("hello"));
	}

	@Test
	public void useJvmOverload(){
		String expected = "hello world";

		var str = new JvmOverloads("world");

		assertEquals(expected, str.helloJvm());
	}


}
