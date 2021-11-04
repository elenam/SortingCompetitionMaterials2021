
public class Helper11 {

	public static void main(String [] args) {
		// Testing the longest repeated non-overlapping substring
		if (lengthLongestRepeatedSubstring(0b1) != 0) {
			System.out.println("Unexpected result on \"0b1\": " + lengthLongestRepeatedSubstring(1));
		}

		if (lengthLongestRepeatedSubstring(0b11) != 1) {
			System.out.println("Unexpected result on \"0b11\": " + lengthLongestRepeatedSubstring(3));
		}

		if (lengthLongestRepeatedSubstring(0b111) != 1) {
			System.out.println("Unexpected result on \"0b111\": " + lengthLongestRepeatedSubstring(7));
		}

		int[] tests = {0b1010, 0b1111, 0b11111, 0b101010, 0b1010101, 0b1010001010, 0b1110111111,
				0b11100010000, 0b1110000100001, 0b111000010001, 0b100101010};
		int [] answers = {2, 2, 2, 2, 3, 4, 3, 4, 5, 4, 3};

		if (tests.length != answers.length) {
			System.out.println("The number of tests and answers don't match");
		}

		for(int i = 0; i < tests.length; ++i) {
			if (lengthLongestRepeatedSubstring(tests[i]) != answers[i]) {
				System.out.println("Unexpected result on " + tests[i] + ":" +
						lengthLongestRepeatedSubstring(tests[i]) + " instead of " + answers[i]);
			}
		}
	}

	public static int lengthLongestRepeatedSubstring(int number) {
    int binaryLength = binaryLength(number);
		int longestSubstringLengthSoFar = 0;
		// iterate over possible lengths
		// the longest length is length/2 (rounded down) since they are non-overlapping
		for (int n = 1; n <= binaryLength / 2; ++n) {
      // An integer with the lower n bits set.
      int mask = (1 << n) - 1;

      int upperBoundForI = binaryLength - 2 * n + 1;
      int upperBoundForJ = binaryLength - n + 1;

			boolean found = false;
			// first index (the first index of the first copy):
      lookingForSubstringOfLengthN:
			for (int i = 0; i < upperBoundForI; i++) {
				// second index (substrings are non-overlapping):
				for (int j = i + n; j < upperBoundForJ; j++) {
          if (
            ((number >> i) & mask)
            == ((number >> j) & mask)
          ) {
						found = true;
            break lookingForSubstringOfLengthN;
					}
				}
			}

      if (found) {
				longestSubstringLengthSoFar = n;
			} else {
				return longestSubstringLengthSoFar;
			}
		}

		return longestSubstringLengthSoFar;
	}

  /**
   * Return the length of the binary representation of an integer.
   *
   * Note that in the degenerate case, length(0) returns 0.
   *
   * Source: https://stackoverflow.com/a/680040
   */
  private static int binaryLength(int i) {
    return Integer.SIZE - Integer.numberOfLeadingZeros(i);
  }

}
