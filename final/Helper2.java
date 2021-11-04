// Updated

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//Richard Lussier, Audrey LeMeur, Natasha Zebrev
public class Helper2 {

    protected static ArrayList<Integer> output = new ArrayList<>();

    /**
     * This method will read the given file and add its contents line by line
     * into the output ArrayList
     * @param path the provided file
     * @throws FileNotFoundException
     */
    protected final static Integer[] readFile(String path) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(path));
        while(scan.hasNext()) {
            output.add(scan.nextInt());
        }
        scan.close();
        return output.toArray(new Integer[0]);
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
    //Old method for finding the longest substring
//    /**
//     * This method takes an integer and returns the longest repeated pattern
//     * in the binary string (not including overlap)
//     * @param num the number we are searching
//     * @return the length of the longest pattern
//     */
//    protected static int longestPattern(int num) {
//        String binary = Integer.toBinaryString(num);
//        int longestPossiblePattern = (int) Math.floor(binary.length() / 2);
//        for(int patternLength = longestPossiblePattern; patternLength > 0; patternLength--) {
//            for(int i = 0; i < binary.length() - patternLength; i++) {
//                String currentPattern = binary.substring(i, i + patternLength);
//                for(int j = i + patternLength; j < binary.length() - patternLength + 1; j++) {
//                    if(currentPattern.equals(binary.substring(j, j + patternLength))) {
//                        return patternLength;
//                    }
//                }
//            }
//        }
//        return 0;
//    }

    /**
     * This method uses dynamic programming to find the longest repeating substring
     * of the binary representation of a given integer. This method was inspired
     * by the implementation found here: https://iq.opengenus.org/longest-repeating-non-overlapping-substring/
     * @param number The decimal representation of the number we want the longest pattern for
     * @return the length of the longest substring in the binary representation
     */
    protected static int longestRepeatingSubstring(int number) {
        String binary = Integer.toBinaryString(number);
        int length = binary.length();

        int table[][] = new int[length + 1][length + 1];
        int max = 0;
        int index = 0;

        for(int i = 1; i <= length; i++){
            for(int j = i + 1; j <= length; j++){
                if(binary.charAt(i - 1) == binary.charAt(j - 1)
                        && j-i > table[i - 1][j - 1]) {
                    table[i][j] = table[i - 1][j - 1] + 1;
                    if(max < table[i][j]) {
                        max = table[i][j];
                        index = Math.max(i, index);
                    }
                } else {
                    table[i][j] = 0;
                }
            }
        }
        return max;
    }
}
