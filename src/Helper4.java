import java.util.Arrays;

public class Helper4 {

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
        // `count` stores the total bits set in `n`
        int count = 0;

        while (n != 0)
        {
            n = n & (n - 1);    // clear the least significant bit set
            count++;
        }

        return count;
    }


    public static int lengthLongestRepeatedSubstring(String str) {
        int n = str.length();
        int[][] vector = new int[n + 1][n + 1];

        StringBuilder result = new StringBuilder();
        int resultLength = 0;


        int i, index = 0;
        for (i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (str.charAt(i - 1) == str.charAt(j - 1) && vector[i - 1][j - 1] < (j - i)) {
                    vector[i][j] = vector[i - 1][j - 1] + 1;
                    if (vector[i][j] > resultLength) {
                        resultLength = vector[i][j];
                        index = Math.max(i, index);
                    }
                }
                else vector[i][j] = 0;
            }
        }

        if (resultLength > 0) {
            for (i = index - resultLength + 1; i <= index; i++) {
                result.append(str.charAt(i - 1));
            }
        }

        return result.toString().length();
    }


    // This was an approach we tried to get the lrs that was more efficient but was not correct
//    public static String lcp(String s, String t) {
//        int n = Math.min(s.length(), t.length());
//        for (int i = 0; i < n; i++) {
//            if (s.charAt(i) != t.charAt(i))
//                return s.substring(0, i);
//        }
//        return s.substring(0, n);
//    }
//
//
//    // return the longest repeated string in s
//    public static int lrs(String s) {
//
//        // form the N suffixes
//        int N  = s.length();
//        String[] suffixes = new String[N];
//        for (int i = 0; i < N; i++) {
//            suffixes[i] = s.substring(i, N);
//        }
//
//        // sort them
////        System.out.println(Arrays.toString(suffixes));
//        Arrays.sort(suffixes);
////        System.out.println("Sorted: \n" + Arrays.toString(suffixes));
//
//        // find longest repeated substring by comparing adjacent sorted suffixes
//        String lrs = "";
//        for (int i = 0; i < N - 1; i++) {
//            String x = lcp(suffixes[i], suffixes[i+1]);
//            if (x.length() > lrs.length())
//                lrs = x;
//        }
////        System.out.println("Sorted: \n" + lrs.length());
//        return lrs.length();
//    }

}
