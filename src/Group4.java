// Updated 11/4/21

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Group4 {

	// Initial time taken for data1 is ~ 2192ms

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
//		quickSort(toSort,0,toSort.length -1);
		Arrays.sort(toSort, new BinaryComparator());
	}
//
//	public static void quickSort(Integer[] arr, int begin, int end) {
//		if (begin < end) {
//			int partitionIndex = partition(arr, begin, end);
//
//			quickSort(arr, begin, partitionIndex-1);
//			quickSort(arr, partitionIndex+1, end);
//		}
//	}
//
//	private static int partition(Integer[ ] arr, int begin, int end) {
//		int pivot = arr[end];
//		int i = (begin-1);
//
//		Comparator<Integer> comparator = new BinaryComparator();
//
//		for (int j = begin; j < end; j++) {
//			if (comparator.compare(arr[j], pivot) <= 0) {
//				i++;
//
//				int swapTemp = arr[i];
//				arr[i] = arr[j];
//				arr[j] = swapTemp;
//			}
//		}
//
//		int swapTemp = arr[i+1];
//		arr[i+1] = arr[end];
//		arr[end] = swapTemp;
//
//		return i+1;
//	}

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
			int digits1 = Helper4.numBinaryOnes(n1);
			int digits2 = Helper4.numBinaryOnes(n2);

			if (digits1 != digits2) return (digits1 - digits2);

			String binary1 = Integer.toBinaryString(n1);
			String binary2 = Integer.toBinaryString(n2);

			int lengthSubstring1 = LongestRepeatedSubstring.lrs(binary1).length();
			int lengthSubstring2 = LongestRepeatedSubstring.lrs(binary2).length();

//			int lengthSubstring1 = Helper.lengthLongestRepeatedSubstring(binary1);
//			int lengthSubstring2 = Helper.lengthLongestRepeatedSubstring(binary2);

			// executed only of the number of 1s is the same
			if (lengthSubstring1 != lengthSubstring2) return (lengthSubstring1 - lengthSubstring2);
			
			// executed only if both of the other ones were the same:
			return (n1 - n2);
		}
		
	}
	

}
