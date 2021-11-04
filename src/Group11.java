import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Group11 {

	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		if (args.length < 2) {
			System.out.println("Please run with two command line arguments: input and output file names");
			System.exit(0);
		}

		String inputFileName = args[0];
		String outFileName = args[1];

		Integer[] data = readInts(inputFileName);

		Integer[] toSort = data.clone();

		sort(toSort);

		//printArray(sorted, 100);

		toSort = data.clone();

		Thread.sleep(10); //to let other things finish before timing; adds stability of runs

		long start = System.currentTimeMillis();

		Data[] sorted = sort(toSort);

		long end = System.currentTimeMillis();

		System.out.println(end - start);

		writeOutResult(sorted, outFileName);

	}

	// YOUR SORTING METHOD GOES HERE.
	// You may call other methods and use other classes.
	// Note: you may change the return type of the method.
	// You would need to provide your own function that prints your sorted array to
	// a file in the exact same format that my program outputs
	private static Data[] sort(Integer[] toSort) {
    // We'll use a merge sort, since those tend to require fewer comparisons
    // than quicksorts. (The number of comparisons won't be asymptotically
    // smaller, but it should at least be smaller by a constant factor.)
    Data[] data = intsToData(toSort);
    mergeSort(
      data,
      Arrays.copyOf(data, data.length),
      0,
      toSort.length,
      new BinaryComparator()
    );
    return data;
	}

  /**
   * Sort a subrange of an array of integers.
   *
   * @param toSort The array that we're going to sort a subrange of.
   * @param copyOfToSort An array with the same elements as toSort (at least,
   *   between the indices start and end).
   * @param start The start of the subrange to sort (inclusive)
   * @param end The end of the subrange to sort (exclusive)
   * @param comparator How to compare elements of toSort.
   *
   * This method mutates the array toSort; after this method is called, the
   * subrange of toSort from start to end will be sorted.
   *
   * (This method also mutates copyOfToSort, which will contain garbage after
   * this method is called.)
   */
  private static void mergeSort(
    Data[] toSort,
    Data[] copyOfToSort,
    int start,
    int end,
    Comparator<Data> comparator
  ) {
    // The idea behind this merge sort implementation is that:
    // - the (2k)th call takes copyOfToSort, merges, and writes to toSort;
    // - the (2k-1)th call takes toSort, merges, and writes to copyOfToSort;
    // - ...
    // - the 1st call takes toSort, merges, and writes to copyOfToSort;
    // - the 0th call takes copyOfToSort, merges, and writes to toSort.

    // Even-numbered calls write to toSort; odd-numbered calls write to
    // copyOfToSort.

    if (end - start < 2) {
      return;
    }

    int midpoint = (start / 2) + (end / 2) + (start & end & 0x1);
    mergeSort(copyOfToSort, toSort, start, midpoint, comparator);
    mergeSort(copyOfToSort, toSort, midpoint, end, comparator);
    int i = start;
    int j = midpoint;
    int k = start;
    while (i < midpoint && j < end) {
      if (comparator.compare(copyOfToSort[i], copyOfToSort[j]) < 0) {
        toSort[k] = copyOfToSort[i];
        i++;
        k++;
      } else {
        toSort[k] = copyOfToSort[j];
        j++;
        k++;
      }
    }
    while (i < midpoint) {
      toSort[k] = copyOfToSort[i];
      i++;
      k++;
    }
    while (j < end) {
      toSort[k] = copyOfToSort[j];
      j++;
      k++;
    }
  }

	private static Data[] intsToData(Integer[] ints) {
    Data[] data = new Data[ints.length];
    for (int i = 0; i < ints.length; i++) {
      data[i] = new Data(ints[i]);
    }
    return data;
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

	private static void writeOutResult(Data[] sorted, String outputFilename) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(outputFilename);
		for (Data s : sorted) {
			out.println(s.value);
		}
		out.close();
	}

	private static class BinaryComparator implements Comparator<Data> {
		@Override
		public int compare(Data n1, Data n2) {
			int digits1 = Integer.bitCount(n1.value);
			int digits2 = Integer.bitCount(n2.value);

			if (digits1 != digits2) {
        return (digits1 - digits2);
      }

      // executed only of the number of 1s is the same
      int lengthSubstring1 = n1.getLengthLongestRepeatedSubstring();
      int lengthSubstring2 = n2.getLengthLongestRepeatedSubstring();

			if (lengthSubstring1 != lengthSubstring2) {
        return (lengthSubstring1 - lengthSubstring2);
      }

			// executed only if both of the other ones were the same:
			return (n1.value - n2.value);
		}
	}

  private static class Data {
    public int value;
    private Integer lengthSubstring = null;

    Data(int value) {
      this.value = value;
    }

    public int getLengthLongestRepeatedSubstring() {
      if (lengthSubstring == null) {
        lengthSubstring = Helper11.lengthLongestRepeatedSubstring(value);
      }
      return lengthSubstring;
    }
  }
}
