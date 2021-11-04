// Updated

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
		insertionMergeSort(toSort, 0, toSort.length -1);
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
            // `This is Brian Kernighan’s Algorithm for counting bits
            int count = 0;
            while (num != 0)
            {
                num = num & (num - 1);    // the & opereator is comparing every single bit and then if two are different it remeoves it
                count++;
            }
            return count;
        }    }

		public static int findLongestSequence(String str){
			{
				int n = str.length();
				int table[][] = new int[n + 1][n + 1];
		 
				int res_length = 0; // To store length of result
		 
				for (int i = 1; i <= n; i++) {
					for (int j = i + 1; j <= n; j++) {
						if (str.charAt(i - 1) == str.charAt(j - 1)
								&& table[i - 1][j - 1] < (j - i)) {
							table[i][j] = table[i - 1][j - 1] + 1;
		 
							if (table[i][j] > res_length) {
								res_length = table[i][j];
							}
						} else {
							table[i][j] = 0;
						}
					}
				}
				if(res_length == 0){// 1 would be the shortest possible length techincally
					return 1;
				}
				return res_length;
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
	
		n1 = findLongestSequence(binString1);
		n2 = findLongestSequence(binString2);
		if(n1 != n2){
				return n1-n2;
		}           
		return num1 - num2;           
			}
	
	

//mergesort

private static void merge(Integer[] arr, int left, int mid, int right) {
    int i = 0, j = 0, k = left;

    int n1 = (mid - left) + 1;
    int n2 = (right - mid);

    Integer[] leftTemp = Arrays.copyOfRange(arr, left, mid + 1);
    Integer[] rightTemp = Arrays.copyOfRange(arr, mid + 1, right + 1);

    while (i < n1 && j < n2) {
        if (compare(leftTemp[i],rightTemp[j]) < 0) {
            arr[k++] = leftTemp[i++];
        } else {
            arr[k++] = rightTemp[j++];
        }
    }

    while (i < n1) {
        arr[k++] = leftTemp[i++];
    }
    while (j < n2) {
        arr[k++] = rightTemp[j++];
    }
}
private static void insertionMergeSort(Integer[] arr, int left, int right) {
    // 25 is the threshold for switching to insertionsort
    if (right-left <= 54) { // 54 waas chosen through rigorous testing but for some reason it tended to be getting a stable 320ms during testing here
        insertionSort(arr, left, right);
    } else {
        int mid = left + (right - left) / 2;
        insertionMergeSort(arr, left, mid);
        insertionMergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }
}
private static void insertionSort(Integer [] arr, int left, int right){
    for (int i = left + 1; i <= right; i++) {
        int key = arr[i];
        int j = i - 1;

		while (j >= left && compare(arr[j], key) > 0) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;
    }
    }

	}
	


