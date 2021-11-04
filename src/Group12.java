import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Group12 {


    public static void main(String[] args) throws InterruptedException, FileNotFoundException {

        if (args.length < 2) {
            System.out.println("Please run with two command line arguments: input and output file names");
            System.exit(0);
        }

        String inputFileName = args[0];
        String outFileName = args[1];

        // read as strings
        Integer[] data = readInts(inputFileName);

        Integer[] toSort = data.clone();
        int[] sorted;

        sorted = sort(toSort);
        //sort(toSort);

        //printArray(sorted, 100);

        toSort = data.clone();

        Thread.sleep(10); //to let other things finish before timing; adds stability of runs

        long start = System.currentTimeMillis();
        sorted = sort(toSort);

        long end = System.currentTimeMillis();

        System.out.println(end - start);

        writeOutResult(sorted, outFileName);

    }


    /**
     * The lrs takes ~75% of the time. Moving to a radix/bucket sort doesn't really shave off enough time,
     * nor does copying back to toSort.
     *
     * @param toSort
     * @return
     */
    private static int[] sort(final Integer[] toSort) {
        return Arrays.stream(toSort).mapToLong(i ->
                (numBinaryOnes(i) + 1 << 29) + (lengthLongestRepeatedSubstringBits(i) + 1 << 24) + i
        ).sorted().mapToInt(i -> (int) i & 0xFFFFFF).toArray();
    }


    private static Integer[] readInts(String inFile) throws FileNotFoundException {
        ArrayList<Integer> input = new ArrayList<>();
        Scanner in = new Scanner(new File(inFile));

        // we don't know how many input numbers, so we add them to an array list
        while (in.hasNext()) {
            input.add(in.nextInt());
        }

        in.close();

        // move input to an array of Integers
        return input.toArray(new Integer[0]);
    }


    private static void writeOutResult(int[] sorted, String outputFilename) throws FileNotFoundException {

        PrintWriter out = new PrintWriter(outputFilename);
        for (Integer s : sorted) {
            out.println(s);
        }
        out.close();

    }

    private static long numBinaryOnes(final int n) {
        return Integer.bitCount(n);
    }


    /**
     * I converged on this solution after dozens of JMH tests.
     * 1. byte[] vs String.charAt doesn't have a noticeable difference, and can be slower due to allocations.
     * 2. The original implementation can bail out after find any match of length n
     * 3. By comparing ints, this removes an expensive loop.
     * 4. I built a full on dynamic programming bit-comparison that is 2-4x faster then the original, but it's still slower than this.
     * 5. Building a Suffix Tree in O(n) time is a pain, I abandoned that after figuring the allocations
     * would kill performance.
     *
     * @param input integer
     * @return length of the longest repeating bit string.
     */
    private static long lengthLongestRepeatedSubstringBits(int input) {
        int length = 0;
        int binaryLength = 32 - Integer.numberOfLeadingZeros(input);
        boolean found;
        int i;
        int j;

        // iterate over possible lengths
        // the longest length is length/2 (rounded down) since they are non-overlapping
        for (int n = 1; n <= binaryLength / 2; ++n) {
            //System.out.println("n = " + n);
            found = false;

            // first index (the first index of the first copy):
            inner:
            for (i = 0; i < binaryLength - 2 * n + 1; ++i) {
                // second index (substrings are non-overlapping):
                for (j = i + n; j < binaryLength - n + 1; ++j) {
                    if ((((input >> i) ^ (input >> j)) & ((1 << n) - 1)) == 0) {
                        length = n;
                        found = true;
                        //We don't need to check further if we've already found one matching substring
                        break inner;
                    }
                }
            }
            //System.out.println(found);
            if (!found) {
                return length;
            }
        }
        return length;
    }

    /**
     * Unused, but I'm showing it off anyway...
     * If I can remove the allocations, I can cut the runtime in half it would slightly beat the modified reference
     * algorithm.
     * <p>
     * Credit:
     * A Fast and Practical Bit-Vector Algorithm for the Longest Common Subsequence Problem; Crochemore, et. al (2001)
     * A linear space algorithm for computing maximal common subsequences.; Hirschberg, D. S. (1975)
     *
     * @param input
     * @return
     */
    private static int lrs(final int input) {
        int length = 32 - Integer.numberOfLeadingZeros(input);
        int mask = (1 << (length - 1)) - 1;
        int max = 0;
        int a;
        int column;
        int c2;
        byte[] boxPre = new byte[length + 1];
        byte[] boxPost;

        for (int i = 0; i < length; i++) {
            boxPost = new byte[length + 1];
            a = ((input >> (length - 1 - i)) & 1) == 0 ? ~input & mask : input & mask;
            mask >>= 1;
            while (a != 0) {
                c2 = 32 - Integer.numberOfLeadingZeros(a);
                column = (length - c2) + 1;
                //System.out.println("c: " + column + " c2: " + c2);
                if (column - (i + 1) > boxPre[column - 1]) {
                    boxPost[column] = (byte) (boxPre[column - 1] + 1);
                    if (boxPost[column] > max) {
                        max = boxPost[column];
                        if (max >= length / 2) {
                            return max;
                        }
                    }
                }
                a = a ^ (1 << c2 - 1);
            }
            boxPre = boxPost;

        }
        return max;
    }
}