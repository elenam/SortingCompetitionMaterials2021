import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Group0 {

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
	

}
