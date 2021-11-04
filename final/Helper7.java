// Updated 

public class Helper7 {
	
	public static void main(String [] args) {
		// Testing helper methods
		if (numBinaryOnes(5) != 2) {
			System.out.println("Unexpected result: " + numBinaryOnes(5));
		}
		
		if (numBinaryOnes(1023) != 10) {
			System.out.println("Unexpected result: " + numBinaryOnes(1023));
		}
		
		if (numBinaryOnes(1024) != 1) {
			System.out.println("Unexpected result: " + numBinaryOnes(1024));
		}
		
		// Testing the longest repeated non-overlapping substring
		if (lengthLongestRepeatedSubstring("01") != 0) {
			System.out.println("Unexpected result on \"01\": " + lengthLongestRepeatedSubstring("01"));
		}
		
		if (lengthLongestRepeatedSubstring("00") != 1) {
			System.out.println("Unexpected result on \"00\": " + lengthLongestRepeatedSubstring("00"));
		}
		
		if (lengthLongestRepeatedSubstring("000") != 1) {
			System.out.println("Unexpected result on \"000\": " + lengthLongestRepeatedSubstring("000"));
		}
		
		String[] tests = {"0101", "1111", "11111", "010101", "0101010", "0101110101", "1110111111",
				"11100010000", "1110000100001", "111000010001", "011010101"};
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
	
	/*
	 * Takes an integer number and returns the number of 1s
	 * in its binary representation. 
	 */
	public static int numBinaryOnes(int n){
		int count = 0;
		while(n>0){
			count++;
			n = n & (n-1);
		}
		return count;
	}
	
	// returns the length of longest repeating substring
	public static int lengthLongestRepeatedSubstring(String binary) {
        int n = binary.length();
		//creating a dynamic programming table
        int LCSRe[][] = new int[n + 1][n + 1];
 
		// to store the length of the result
        int resultLength = 0; 
 
        // building table in bottom-up manner
        int i, index = 0;
        for (i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                // (j-i) > LCSRe[i-1][j-1] to remove any overlapping
                if (binary.charAt(i - 1) == binary.charAt(j - 1)
                        && LCSRe[i - 1][j - 1] < (j - i)) {
                    LCSRe[i][j] = LCSRe[i - 1][j - 1] + 1;
 
                    // updating maximum length of the
                    // substring and updating the finishing
                    // index of the suffix
                    if (LCSRe[i][j] > resultLength) {
                        resultLength = LCSRe[i][j];
                        index = Math.max(i, index);
                    }
                } else {
                    LCSRe[i][j] = 0;
                }
            }
        }
		// return the length
        return resultLength;
    }
}
