package coverage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class Sample {
	@Test
	void lineCoverage() {
		CoverageSample coverageSample = new CoverageSample();

		assertEquals(5, coverageSample.sample(true));
		assertThrows(NullPointerException.class, () -> {
			coverageSample.sample(false);
		});
	}
}
