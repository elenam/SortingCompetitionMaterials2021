// Updated 11/4/21

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Iterator;

public class Group10 {

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
	
	public static void sort(Integer[] toSort) {
		// Take the array. seperate it into groups of equvalante # of ones into 
		// a hashmap<int, treemap<int, arraylist<int>>>. Then for each treemap<int, arraylist<int>>
		// add to the treemap with the key as LLRS, and the value to the associated array. Then sort each of those.
		HashMap<Integer,TreeMap<Integer, ArrayList<Integer>>> hm = new HashMap<>();
		for(int i = 0; i< toSort.length; i++) {
			int b = numBinaryOnes(toSort[i]);
			int LLRS = lengthLongestRepeatedSubstring(Integer.toBinaryString(toSort[i]));
			hm.putIfAbsent(b, new TreeMap<Integer,ArrayList<Integer>>());
			hm.get(b).putIfAbsent(LLRS, new ArrayList<Integer>());
			hm.get(b).get(LLRS).add(toSort[i]);
		}
		int i = 0; // index to put next value
		Iterator<TreeMap<Integer, ArrayList<Integer>>> it = hm.values().iterator();
		while(it.hasNext()) { // for every treemap in the hashmap
			Iterator<ArrayList<Integer>> itit = it.next().values().iterator(); // it.next().values().iterator() = treemap iterator 
			while(itit.hasNext()) { // for every array in the treemap
				ArrayList<Integer> a = itit.next();
				a.sort(null);
				for(int j = 0; j<a.size();j++) {// for every value in the arraylist<int>
					toSort[i++]= a.get(j);
				}
			}
		}
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
	
	private static <T> void writeOutResult(T[] sorted, String outputFilename) throws FileNotFoundException {

		PrintWriter out = new PrintWriter(outputFilename);
		for (T s : sorted) {
			out.println(s);
		}
		out.close();

	}
	public static int lengthLongestRepeatedSubstring(String binary) {
		if(binary.length() == 1) {
			return 0;
		}
		int l = 0;
		int h = 1;
		String substring = binary.substring(l, h);
		String afterstring = binary.substring(h);
		while(afterstring.contains(substring)||afterstring.length()>(h-l)) {
			if(afterstring.contains(substring)) {
				substring = binary.substring(l, ++h);
				afterstring = binary.substring(h);
			}else {
				substring = binary.substring(++l,++h);
				afterstring = binary.substring(h);
			}
		}
		
		return h-l-1;
	}
	// yoinked from https://www.geeksforgeeks.org/count-set-bits-in-an-integer/
	public static int numBinaryOnes(int n){
	        int count = 0;
	        while (n > 0) {
	            count += n & 1;
	            n >>= 1;
	        }
	        return count;
	}
	

}
