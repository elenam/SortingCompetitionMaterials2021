// Updated 

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Group3 {

	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		
		if (args.length < 2) {
			System.out.println("Please run with two command line arguments: input and output file names");
			System.exit(0);
		}

		String inputFileName = args[0];
		String outFileName = args[1];
		
		// read as strings
		Integer [] data = readInts(inputFileName);
		
		Integer [] toSort = data.clone();
		
		sort(toSort);
		
		//printArray(sorted, 100);
		
		toSort = data.clone();
		
		Thread.sleep(10); //to let other things finish before timing; adds stability of runs

		long start = System.currentTimeMillis();
		
		sort(toSort);
		
		long end = System.currentTimeMillis();
		
		System.out.println(end - start);
		
		writeOutResult(toSort, outFileName);

	}
	
	// YOUR SORTING METHOD GOES HERE. 
	// You may call other methods and use other classes. 
	// Note: you may change the return type of the method. 
	// You would need to provide your own function that prints your sorted array to 
	// a file in the exact same format that my program outputs
	private static void sort(Integer[] toSort) {
		Arrays.sort(toSort, new BinaryComparator());
	}
	
	private static String[] readData(String inFile) throws FileNotFoundException {
		ArrayList<String> input = new ArrayList<>();
		Scanner in = new Scanner(new File(inFile));
		
		while(in.hasNext()) {
			input.add(in.next());
		}
				
		in.close();
		
		// the string array is passed just so that the correct type can be created
		return input.toArray(new String[0]);
	}
	
	private static Integer[] readInts(String inFile) throws FileNotFoundException {
		ArrayList<Integer> input = new ArrayList<>();
		Scanner in = new Scanner(new File(inFile));
		
		// we don't know how many input numbers, so we add them to an array list
		while(in.hasNext()) {
			input.add(in.nextInt());
		}
				
		in.close();
		
		// move input to an array of Integers
		return (Integer[]) input.toArray(new Integer[0]);
	}
	
	private static void writeOutResult(Integer[] sorted, String outputFilename) throws FileNotFoundException {

		PrintWriter out = new PrintWriter(outputFilename);
		for (Integer s : sorted) {
			out.println(s);
		}
		out.close();

	}
	
	private static class BinaryComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer n1, Integer n2) {
//			int digits1 = Helper3.numBinaryOnes(n1);
//			int digits2 = Helper3.numBinaryOnes(n2);

			int digits1 = Integer.bitCount(n1);
			int digits2 = Integer.bitCount(n2);
			
			if (digits1 != digits2) return (digits1 - digits2);
			// executed only of the number of 1s is the same
			
			// Moved the code that finds the length of the LNRNOS to after we check to see if
			// the number of 1s in it's binary representation are the same or not, because we
			// only want to calculate the length of the LNRNOS if the number of 1s is the same.

//			int lengthSubstring1 = Helper3.lengthLongestRepeatedSubstring(Integer.toBinaryString(n1));
//			int lengthSubstring2 = Helper3.lengthLongestRepeatedSubstring(Integer.toBinaryString(n2));

			int lengthSubstring1 = LNRNOS(Integer.toBinaryString(n1));
			int lengthSubstring2 = LNRNOS(Integer.toBinaryString(n2));

			if (lengthSubstring1 != lengthSubstring2) return (lengthSubstring1 - lengthSubstring2);
			
			// executed only if both of the other ones were the same:
			return (n1 - n2);
		}
		
	}

	/**
	 * LNRNOS (Longest Non-Repeating Non-Overlapping Substring)
	 * Base code from https://iq.opengenus.org/longest-repeating-non-overlapping-substring/
	 * @param str  String.
	 * @return integer length, the size of the longest non-repeating non-overlapping substring.
	 */
	public static int LNRNOS(String str) {
		int n = str.length();
		int dp[][] = new int[n + 1][n + 1];
		int max = 0, index = 0;
		for (int i = 1; i <= n; ++i) {
			for (int j = i + 1; j <= n; ++j) {
				if (str.charAt(i - 1) == str.charAt(j - 1) && j - i > dp[i - 1][j - 1]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					if (max < dp[i][j]) {
						max = dp[i][j];
						//save last index of substring
						index = Math.max(i, index);
					}
				} else
					dp[i][j] = 0;
			}
		}
		int length = str.substring(index - max, index).length();
		return length;
	}

}

/**
Other approaches/exploration

We definitely believed that there were improvements to be had when it came to counting the number of 0s in the binary 
representation of a number, and finding the longest non-repeating, non-overlapping substring.
We found a built in Java method for Integers called bitCount that has a time complexity of O(1).  Whereas, the original
implentation was reliant on the length of the binary string.
We found the LNRNOS suffix-tree implementation, but believed that we wouldn't have enough time to implement it, so we went with
the dynamic programming implementation instead.  

We talked about bucket-sort, since we know the distribution of the data.

Also, in binaryComparator we moved the calculation of LNRNOS to after we check to see if the number of 1s in the binary representations
are the same or not, since if they aren't the same, we don't need to calculate LNRNOS or the stuff after.

**/
