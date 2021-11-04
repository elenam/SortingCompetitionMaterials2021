// Updated 11/4/21

public class Helper6 {

    public static int numBinaryOnes(int n){
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
        for (int n = 1; n <= Math.floor(binary.length()/2.0); ++n) {
            //System.out.println("n = " + n);
            boolean found = false;
            // first index (the first index of the first copy):
            for (int i = 0; i < binary.length() - 2*n + 1; ++i) {
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

}