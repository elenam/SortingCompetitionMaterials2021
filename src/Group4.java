// Updated 

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

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

		//printArray(sorted, 100);
		
		toSort = data.clone();

		warmUp(toSort);

		toSort = data.clone();

		Thread.sleep(10); //to let other things finish before timing; adds stability of runs

		long start = System.currentTimeMillis();
		
		sort(toSort);
		
		long end = System.currentTimeMillis();
		
		System.out.println(end - start);
		
		writeOutResult(toSort, outFileName);

	}
	

	private static void warmUp(Integer[] toSort) {
		Arrays.sort(Arrays.copyOfRange(toSort,0,toSort.length/2), new BinaryComparator());
	}

	private static void runOldImplementation(Integer[] toSort) {
		Arrays.sort(toSort, new OldComparator());
	}

	// YOUR SORTING METHOD GOES HERE.
	// You may call other methods and use other classes.
	// Note: you may change the return type of the method.
	// You would need to provide your own function that prints your sorted array to
	// a file in the exact same format that my program outputs
	private static void sort(Integer[] toSort) {
		Arrays.sort(toSort,new BinaryComparator());
	}
	
	private static String[] readInteger(String inFile) throws FileNotFoundException {
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

	// We tried using quicksort but it seems that mergesort handles the data better. Here for reference.
	public static void optimizedQuickSort(Integer[] toSort, int p, int r)
	{
		while (p < r)
		{
			// do insertion sort if 9 or smaller
			if(r - p < 9)
			{
				insertionSort(toSort, p, r);
				break;
			}
			else
			{
				int pivot = partition(toSort, p, r);

				if (pivot - p < r - pivot) {
					optimizedQuickSort(toSort, p, pivot - 1);
					p = pivot + 1;
				} else {
					optimizedQuickSort(toSort, pivot + 1, r);
					r = pivot - 1;
				}
			}
		}
	}



	public static int partition(Integer[] toSortInteger, int p, int r) {
		Integer x = toSortInteger[r];
		int i = p - 1;
		BinaryComparator comparator = new BinaryComparator();

		for (int j = p; j <= r - 1; j++) {
			if ((comparator.compare(toSortInteger[j], x)) <= 0) {
				i = i + 1;
				Integer swap = toSortInteger[i];
				toSortInteger[i] = toSortInteger[j];
				toSortInteger[j] = swap;

			}
		}
		Integer swap = toSortInteger[i + 1];
		toSortInteger[i + 1] = toSortInteger[r];
		toSortInteger[r] = swap;
		return i + 1;
	}
	public static void insertionSort(Integer[] toSort, int p, int n)
	{
		// Start from second element (element at index 0
		// is already sorted)
		for (int i = p + 1; i <= n; i++)
		{
			Integer value = toSort[i];
			int j = i;
			BinaryComparator comparator = new BinaryComparator();

			// Find the index j within the sorted subset arr[0..i-1]
			// where element arr[i] belongs
			while (j > p && (comparator.compare(toSort[j-1], value) > 0))
			{
				toSort[j] = toSort[j - 1];
				j--;
			}
			// Note that subarray arr[j..i-1] is shifted to
			// the right by one position

			toSort[j] = value;
		}
	}
	
	private static class BinaryComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer n1, Integer n2) {
			int diff = Integer.bitCount(n1) - Integer.bitCount(n2);
			if (diff != 0) return diff;

			String binary1 = Integer.toBinaryString(n1);
			String binary2 = Integer.toBinaryString(n2);

			int lengthSubstring1 = Helper.lengthLongestRepeatedSubstring(binary1);
			int lengthSubstring2 = Helper.lengthLongestRepeatedSubstring(binary2);
			
			// executed only of the number of 1s is the same
			if (lengthSubstring1 != lengthSubstring2) return (lengthSubstring1 - lengthSubstring2);
			
			// executed only if both of the other ones were the same:
			return (n1 - n2);
		}
		
	}

	private static class OldComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer n1, Integer n2) {
			int digits1 = Helper4.numBinaryOnes(n1);
			int digits2 = Helper4.numBinaryOnes(n2);

			int lengthSubstring1 = Helper4.lengthLongestRepeatedSubstring(Integer.toBinaryString(n1));
			int lengthSubstring2 = Helper4.lengthLongestRepeatedSubstring(Integer.toBinaryString(n2));

			if (digits1 != digits2) return (digits1 - digits2);
			// executed only of the number of 1s is the same
			if (lengthSubstring1 != lengthSubstring2) return (lengthSubstring1 - lengthSubstring2);

			// executed only if both of the other ones were the same:
			return (n1 - n2);
		}

	}
	

}
