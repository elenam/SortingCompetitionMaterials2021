// Updated 

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;

//Richard Lussier, Audrey LeMeur, Natasha Zebrev
public class Group2 {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        if (args.length < 2) {
            System.out.println("Please run with two command line arguments: input and output file names");
            System.exit(0);
        }

        String inputFileName = args[0];
        String outFileName = args[1];

        // read the data
        Integer [] data = Helper2.readFile(inputFileName);

        Integer [] toSort = data.clone();

        sort(toSort);

        toSort = data.clone();

        Thread.sleep(10); //to let other things finish before timing; adds stability of runs

        long start = System.currentTimeMillis();

        sort(toSort);

        long end = System.currentTimeMillis();

        System.out.println(end - start);

        Helper2.writeToFile(toSort, outFileName);

    }

    // YOUR SORTING METHOD GOES HERE.
    // You may call other methods and use other classes.
    // Note: you may change the return type of the method.
    // You would need to provide your own function that prints your sorted array to
    // a file in the exact same format that my program outputs
    private static void sort(Integer[] toSort) {
        Arrays.sort(toSort, new BinaryComparator());
    }

    /**
     * This class overrides the compare method so we can compare integers in
     * the way we need to for the sorting competition
     */
    private static class BinaryComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer n1, Integer n2) {
            int numOnes1 = Helper2.countOnes(n1);
            int numOnes2 = Helper2.countOnes(n2);

            int pattern1 = Helper2.longestRepeatingSubstring(n1);
            int pattern2 = Helper2.longestRepeatingSubstring(n2);

            // Compares the amount of 1's in the binary representation
            if (numOnes1 != numOnes2) return (numOnes1 - numOnes2);
            // Compares the length of the longest non-overlapping pattern in the binary representation
            if (pattern1 != pattern2) return (pattern1 - pattern2);

            // Compares the actual value of the two integers
            return (n1 - n2);
        }
    }
}
