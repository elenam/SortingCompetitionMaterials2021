// Updated 11/4/21

import java.util.Scanner;

public class Helper3 {

    public static void main(String[] args) {
        // Testing helper methods
        if (numBinaryOnes(5) != 2) {
            System.out.println("Unexpected result: " + numBinaryOnes(5));
        }

        if (numBinaryOnes(1023) != 10) {
            System.out.println("Unexpected result: " + numBinaryOnes(1023));
        }

        if (numBinaryOnes(1024) != 1) {
            System.out.println("Unexpected result: " + numBinaryOnes(1024));
        }

        // Testing the longest repeated non-overlapping substring
        if (lengthLongestRepeatedSubstring("01") != 0) {
            System.out.println("Unexpected result on \"01\": " + lengthLongestRepeatedSubstring("01"));
        }

        if (lengthLongestRepeatedSubstring("00") != 1) {
            System.out.println("Unexpected result on \"00\": " + lengthLongestRepeatedSubstring("00"));
        }

        if (lengthLongestRepeatedSubstring("000") != 1) {
            System.out.println("Unexpected result on \"000\": " + lengthLongestRepeatedSubstring("000"));
        }

        String[] tests = {"0101", "1111", "11111", "010101", "0101010", "0101110101", "1110111111",
                "11100010000", "1110000100001", "111000010001", "011010101"};
        int[] answers = {2, 2, 2, 2, 3, 4, 3, 4, 5, 4, 3};

        if (tests.length != answers.length) {
            System.out.println("The number of tests and answers don't match");
        }

        for (int i = 0; i < tests.length; ++i) {
            if (lengthLongestRepeatedSubstring(tests[i]) != answers[i]) {
                System.out.println("Unexpected result on " + tests[i] + ":" +
                        lengthLongestRepeatedSubstring(tests[i]) + " instead of " + answers[i]);
            }
        }
    }

    /*
     * Takes an integer number and returns the number of 1s
     * in its binary representation.
     */
    public static int numBinaryOnes(int n) {
        String binary = Integer.toBinaryString(n);


        //System.out.println(binary);

        int count = 0;
        for (int i = 0; i < binary.length(); ++i) {
            if (binary.charAt(i) == '1') {
                count++;
            }
        }

        return count;
    }


    public static int lengthLongestRepeatedSubstring(String binary) {
        int length = 0;
        // iterate over possible lengths
        // the longest length is length/2 (rounded down) since they are non-overlapping
        for (int n = 1; n <= Math.floor(binary.length() / 2.0); ++n) {
            //System.out.println("n = " + n);
            boolean found = false;
            // first index (the first index of the first copy):
            for (int i = 0; i < binary.length() - 2 * n + 1; ++i) {
                // second index (substrings are non-overlapping):
                for (int j = i + n; j < binary.length() - n + 1; ++j) {
                    // iterating over the substring length:
                    int k = 0; // need the index after the loop to see if it finished
                    for (; k < n; k++) {
                        //System.out.println("i = " + i + ", j = " + j + ", k = " + k);
                        if (binary.charAt(i + k) != binary.charAt(j + k)) {
                            break;
                        }
                    }
                    //System.out.println("k = " + k + ",n = " + n);
                    if (k == n) {
                        found = true;
                    }
                }
            }
            //System.out.println(found);
            if (found) {
                length++;
            } else {
                return length;
            }
        }

        return length;
    }


    /**
     * LNRNOS (Longest Non-Repeating Non-Overlapping Substring)
     * Base code from https://iq.opengenus.org/longest-repeating-non-overlapping-substring/
     * @param str  String.
     * @return integer length, the size of the longest non-repeating non-overlapping substring.
     */
    public static int LNRNOS(String str) {
        int n = str.length();
        int dp[][] = new int[n + 1][n + 1];
        int max = 0, index = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = i + 1; j <= n; ++j) {
                if (str.charAt(i - 1) == str.charAt(j - 1) && j - i > dp[i - 1][j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (max < dp[i][j]) {
                        max = dp[i][j];
                        //save last index of substring
                        index = Math.max(i, index);
                    }
                } else
                    dp[i][j] = 0;
            }
        }
        int length = str.substring(index - max, index).length();
        return length;
    }


}

