// Updated 11/4/21

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;

public class Group2 {

    public static void main(String[] args) throws FileNotFoundException {

        if(args.length != 2) {
            System.out.println("Invalid usage: please use two arguments- the first being " +
                    "the input file path and the second being the output file path.");
            System.exit(1);
        }

        // Name the arguments more convenient names
        String inputFile = args[0];
        String outputFile = args[1];

        // Read from the input file and transfer the data into an array
        Helper2.readFile(inputFile);
        Integer[] toSort = Helper2.output.toArray(new Integer[0]);


        // Begin sorting while tracking the start and end time
        Long start = System.currentTimeMillis();

        Arrays.sort(toSort, new BinaryComparator());

        Long end = System.currentTimeMillis();

        Helper2.writeToFile(toSort, outputFile);

        System.out.println((end - start));
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

            int pattern1 = Helper2.longestPattern(n1);
            int pattern2 = Helper2.longestPattern(n2);

            // Compares the amount of 1's in the binary representation
            if (numOnes1 != numOnes2) return (numOnes1 - numOnes2);
            // Compares the length of the longest non-overlapping pattern in the binary representation
            if (pattern1 != pattern2) return (pattern1 - pattern2);

            // Compares the actual value of the two integers
            return (n1 - n2);
        }
    }
}
