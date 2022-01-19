package coverage;

public class CoverageSample {
	public int sample(boolean isSample) {
		String str = null;
		if (isSample) {
			str = "hello";
		}

		return str.length();
	}

}
