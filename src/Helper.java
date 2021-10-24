import java.util.HashMap;

public class Helper {
	
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
		String binary = Integer.toBinaryString(n);
		
		//System.out.println(binary);
		
		int count = 0;
		for (int i = 0; i < binary.length(); ++i) {
			if (binary.charAt(i) == '1') {
				count++;
			}
		}
		
		return count;
	}
	
	
	public static int lengthLongestRepeatedSubstring(String binary) {
		int length = 0;
		// iterate over possible lengths
		// the longest length is length/2 (rounded down) since they are non-overlapping
		for (int n = 1; n <= Math.floor(binary.length()/2.0); ++n) {
			//System.out.println("n = " + n);
			boolean found = false;
			// first index (the first index of the first copy): 
			for (int i = 0; i < binary.length() - 2*n + 1; ++i) {
				// second index (substrings are non-overlapping):
				for (int j = i + n; j < binary.length() - n + 1; ++j) {
					// iterating over the substring length:
					int k = 0; // need the index after the loop to see if it finished
					for (; k < n; k++) {
						//System.out.println("i = " + i + ", j = " + j + ", k = " + k);
						if (binary.charAt(i + k) != binary.charAt(j + k)) {
							break;
						}
					}	
					//System.out.println("k = " + k + ",n = " + n);
					if (k == n) {
						found = true; 
					}
				}
			}
			//System.out.println(found);
			if (found) {
				length++;
			} else {
				return length;
			}
		}
		
		return length;
	}

	// static int lengthLongestRepeatedSubstring(String binary)
	// {
   
	//   // Creating a set to store the last positions of occurrence
	//   HashMap<Character, Integer> seen = new HashMap<>(); 
	//   int maximum_length = 0;
   
	//   // starting the initial point of window to index 0
	//   int start = 0;
   
	//   for(int end = 0; end < binary.length(); end++)
	//   {
	// 	// Checking if we have already seen the element or not
	// 	if(seen.containsKey(binary.charAt(end)))
	// 	{
	// 	  // If we have seen the number, move the start pointer
	// 	  // to position after the last occurrence
	// 	  start = Math.max(start, seen.get(binary.charAt(end)) + 1);
	// 	}
   
	// 	// Updating the last seen value of the character
	// 	seen.put(binary.charAt(end), end);
	// 	maximum_length = Math.max(maximum_length, end-start + 1);
	//   }
	//   return maximum_length;
	// }

}
