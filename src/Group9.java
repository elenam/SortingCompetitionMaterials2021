// Updated 11/4/21

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.HashMap;

public class Group9{

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

		SubstrComparator.resetMemoization();
		
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
		Arrays.sort(toSort, new NumOnesComparator());
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
		return input.toArray(new Integer[0]);
	}
	
	private static void writeOutResult(Integer[] sorted, String outputFilename) throws FileNotFoundException {

		PrintWriter out = new PrintWriter(outputFilename);
		for (Integer s : sorted) {
			out.println(s);
			//out.println(s + " " + Integer.toBinaryString(s));
		}
		out.close();

	}
	
	private static class BinaryComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer n1, Integer n2) {
			int digits1 = Helper.numBinaryOnes(n1);
			int digits2 = Helper.numBinaryOnes(n2);
			
			int lengthSubstring1 = Helper.lengthLongestRepeatedSubstring(Integer.toBinaryString(n1));
			int lengthSubstring2 = Helper.lengthLongestRepeatedSubstring(Integer.toBinaryString(n2));
			
			if (digits1 != digits2) return (digits1 - digits2);
			// executed only of the number of 1s is the same
			if (lengthSubstring1 != lengthSubstring2) return (lengthSubstring1 - lengthSubstring2);
			
			// executed only if both of the other ones were the same:
			return (n1 - n2);
		}
		
	}

	protected static class SubstrComparator implements Comparator<Integer> {

		private static HashMap<Integer, Integer> substringLengthMap = new HashMap<>(10000);

		private static Integer memoizeLength(Integer n){
			Integer substringLength = Integer.valueOf(Helper.lengthLongestRepeatedSubstring(Integer.toBinaryString(n)));
			substringLengthMap.put(n, substringLength);
			return substringLength;
		}

		private static Integer memoizedSubstrLength(Integer n){
			Integer output = substringLengthMap.get(n);
			if(output == null){
				output = memoizeLength(n);
			}
			return output;
		}

		public static void resetMemoization(){
			substringLengthMap = new HashMap<>(10000);
		}

		@Override
		public int compare(Integer n1, Integer n2) {
			int substrDif = memoizedSubstrLength(n1).compareTo(memoizedSubstrLength(n2));
			if (substrDif != 0) {
				return substrDif;
			} else {
				return n1.compareTo(n2);
			}
		}
	}

	private static class NumOnesComparator implements Comparator<Integer> {

		private static SubstrComparator innerComparator = new SubstrComparator();

		@Override
		public int compare(Integer n1, Integer n2){
			int numOnesDifference = Integer.bitCount(n1) - Integer.bitCount(n2);
			if(numOnesDifference != 0){
				return numOnesDifference;
			} else {
				return innerComparator.compare(n1, n2);
			}
		}
	}
	

} 
