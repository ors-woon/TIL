package martifowler.refactoring.chap01;

import martinfowler.refactoring.chap01.Customer;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CustomerTest {

	@Test
	public void test_statement() {
		Customer customer = new Customer("name");
		String expect = "name 고객님의 대여 기록\n"
			+ "누적 대여료 :0.0\n"
			+ "적립 포인트 :0";

		String actual = customer.statement();

		assertThat(actual, is(expect));
	}
}
