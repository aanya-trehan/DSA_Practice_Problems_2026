import java.util.*;

class Solution {
    public static int lenOfLongSubarr(int A[], int N, int K) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        int maxlength = 0;

        for (int i = 0; i < N; i++) {
            sum += A[i];

            if (map.containsKey(sum - K)) {
                maxlength = Math.max(maxlength, i - map.get(sum - K));
            }

            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }

        return maxlength;
    }
}
