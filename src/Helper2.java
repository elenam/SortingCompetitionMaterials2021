// Updated 11/4/21

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Helper2 {

    protected static ArrayList<Integer> output = new ArrayList<>();

    /**
     * This method will read the given file and add its contents line by line
     * into the output ArrayList
     * @param path the provided file
     * @throws FileNotFoundException
     */
    protected final static void readFile(String path) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(path));
        while(scan.hasNext()) {
            output.add(scan.nextInt());
        }
        scan.close();
    }

    /**
     * This method takes an array and a file path and will print the given array to the file in the path
     * @param array the array we want to print out
     * @param outputFile the file we want to output into
     * @throws FileNotFoundException
     */
    protected static void writeToFile(Integer[] array, String outputFile) throws FileNotFoundException {

        PrintWriter out = new PrintWriter(outputFile);
        for (Integer num : array) {
            out.println(num);
            //out.printf("%d has %d ones and a pattern length of %d\n",
            //       num, countOnes(num), longestPattern(num));
        }
        out.close();
    }

    /**
     * This method counts the amount of 1's that appear in a given integer converted to binary
     * @param num the integer provided to check
     * @return the number of 1's inside of the binary version of the integer
     */
    protected static int countOnes(Integer num) {
        int counter = 0;
        String binary = Integer.toBinaryString(num);
        for(int i = 0; i < binary.length(); i++) {
            if(binary.substring(i, i + 1).equals("1")) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * This method takes an integer and returns the longest repeated pattern
     * in the binary string (not including overlap)
     * @param num the number we are searching
     * @return the length of the longest pattern
     */
    protected static int longestPattern(int num) {
        String binary = Integer.toBinaryString(num);
        int longestPossiblePattern = (int) Math.floor(binary.length() / 2);
        for(int patternLength = longestPossiblePattern; patternLength > 0; patternLength--) {
            for(int i = 0; i < binary.length() - patternLength; i++) {
                String currentPattern = binary.substring(i, i + patternLength);
                for(int j = i + patternLength; j < binary.length() - patternLength + 1; j++) {
                    if(currentPattern.equals(binary.substring(j, j + patternLength))) {
                        return patternLength;
                    }
                }
            }
        }
        return 0;
    }
}
