// Updated 11/4/21

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Group1 {
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
		mergeSort(toSort, toSort.length);
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
			//out.println(s + " " + Integer.toBinaryString(s));
		}
		out.close();

	}
	//------------------------------------------------------------------
	public static int countOnes(int num){
        {
            // `This is Brian Kernighan's Algorithm for counting bits
            int count = 0;
            while (num != 0)
            {
                num = num & (num - 1);    // the & operator is comparing every single bit and then if two are different it removes it
                count++;
            }
            return count;
        }    }

		public static int findLongestSequence(String binaryStr, int length){
			{
				int table[][]=new int[length+1][length+1];
				int max=0;
				int index=0;
				for(int i=1;i<=length;++i)
				{
				 for(int j=i+1;j<=length;++j)
				 {
				  if(binaryStr.charAt(i-1)==binaryStr.charAt(j-1) && j-i>table[i-1][j-1])
				  {
					table[i][j]=table[i-1][j-1]+1;
					if(max<table[i][j])
					  {
						max=table[i][j];
						index=Math.max(i,index);
					  }
				  }
				  else
				   table[i][j]=0;
				 }
				}
				return binaryStr.substring(index-max,index).length();
			}
				
	}
	public static int compare(Integer num1, Integer num2 ){
		int n1 = countOnes(num1);
		int n2 = countOnes(num2);
		if(n1 != n2){
				return n1-n2;
		}
		String binString1 = Integer.toBinaryString(num1);
		String binString2 = Integer.toBinaryString(num2);
	
		n1 = findLongestSequence(binString1, binString1.length());
		n2 = findLongestSequence(binString2, binString2.length());
		if(n1 != n2){
				return n1-n2;
		}           
		return num1 - num2;           
			}
	
	
//mergesort
// idea of coding mergesort in to save time with using algorithms found online
public static void mergeSort(Integer[] arr, int length) {
    if (length < 2) {
        return;
    }
    int mid = length / 2;
    Integer[] left = new Integer[mid];
    Integer[] right = new Integer[length - mid];

    for (int i = 0; i < mid; i++) {
        left[i] = arr[i];
    }
    for (int i = mid; i < length; i++) {
        right[i - mid] = arr[i];
    }
    mergeSort(left, mid);
    mergeSort(right, length - mid);

    merge(arr, left, right, mid, length - mid);
}
public static void merge(
	Integer[] arr, Integer[] leftArray, Integer[] rightArray, int leftIndex, int rightIndex) {
 
    int i = 0, j = 0, k = 0;
    while (i < leftIndex && j < rightIndex) {
		int comparison = compare(leftArray[i],rightArray[j]); //l <=r
        if (comparison <= 0) {// 0 indicates they are of the same value while a negaitive number indicate the second number was larger
            arr[k++] = leftArray[i++];
        }
        else {
            arr[k++] = rightArray[j++];
        }
    }
    while (i < leftIndex) {
        arr[k++] = leftArray[i++];
    }
    while (j < rightIndex) {
        arr[k++] = rightArray[j++];
    }
}


	}
	

