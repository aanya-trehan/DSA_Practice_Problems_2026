# Largest Subarray with K Sum

**Source:** GFG

**Pattern:** Hashing / Prefix Sum

![Medium](https://img.shields.io/badge/Level-Medium-f0ad00?style=flat-square) ![Pattern](https://img.shields.io/badge/Pattern-Hashing_%2F_Prefix_Sum-3b82f6?style=flat-square) ![Time](https://img.shields.io/badge/Time-O(n)-22c55e?style=flat-square) ![Space](https://img.shields.io/badge/Space-O(n)-22c55e?style=flat-square)

---

> [!TIP]
> **Key insight:** the sum of a subarray is the difference of two running sums. So "find the subarray that adds to `k`" becomes "find an earlier running sum equal to `sum - k`", and that is a lookup, not a search.

## The Question

Given an array `nums` that can hold negative numbers and zeros, and a number `k`, return the length of the longest subarray whose sum is exactly `k`. Return `0` if there is none.

> Example: `nums = [10, 5, 2, 7, 1, 9]`, `k = 15` gives `4`, from `5 + 2 + 7 + 1`.

<details>
<summary><b>How to think about it</b></summary>

<br>

1. **Start simple.** For every start position, keep adding numbers to the right and note each time the sum hits `k`. That works, but it is two loops, so O(n²).
2. **Spot the waste.** Each inner loop re-adds numbers that an earlier loop already added. A running total (prefix sum) already holds that work: the sum from `i` to `j` is `prefix[j] - prefix[i-1]`.
3. **Ask what you are hunting for.** At a fixed end `j`, you want a start where `prefix[j] - prefix[start] = k`. That means the earlier running sum must be exactly `prefix[j] - k`. You already know that value before you look.
4. **Turn the search into a lookup.** Whenever a loop searches for a value you can calculate, remember what you have seen in a map so the check is instant. Store each running sum with the index where it first appeared.
5. **Let the question decide what to store.** We want the longest subarray, so keep the earliest index for each sum and never overwrite it. The algorithm follows from that.
6. **Small details.** Keep only the first index of each sum, because an earlier start gives a longer subarray. The `sum == k` check covers subarrays that start at index 0. Negative numbers are fine, because the map only compares totals.

</details>

## Ways to Solve It

| Approach | Idea | Note | Time | Space |
| --- | --- | --- | --- | --- |
| Brute force | Try every start and end and add up the numbers. | Works with negatives. | O(n²) | O(1) |
| **HashMap (best)** | **Look up sum - k in a map of earlier sums.** | **Works with negatives.** | **O(n)** | **O(n)** |
| Sliding window | Grow the window, shrink it when the sum is too big. | Wrong with negatives. | O(n) | O(1) |

This problem has negatives, so use the HashMap version. The sliding window is explained below, only to show why it fails here.

## The Logic

1. Make an empty map from running sum to the first index where it appeared.
2. Set `sum = 0` and `maxLen = 0`.
3. For each index `i`, add `nums[i]` to `sum`.
4. If `sum == k`, the subarray from index 0 to `i` works. Update `maxLen` with `i + 1`.
5. Look up `sum - k` in the map. If it is there, the subarray after that index up to `i` adds to `k`. Update `maxLen` with `i - thatIndex`.
6. If `sum` is not in the map yet, store it with index `i`. Do this after the lookup, and never overwrite.
7. Return `maxLen`.

## Code (Java)

```java
import java.util.*;

class Solution {
    public int longestSubarray(int[] nums, int k) {
        Map<Integer, Integer> preSumMap = new HashMap<>(); // running sum -> first index
        int sum = 0;
        int maxLen = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];                      // running sum up to index i

            if (sum == k) {                      // subarray from index 0 to i
                maxLen = Math.max(maxLen, i + 1);
            }

            int rem = sum - k;                   // the earlier running sum we are hunting for
            if (preSumMap.containsKey(rem)) {
                maxLen = Math.max(maxLen, i - preSumMap.get(rem));
            }

            if (!preSumMap.containsKey(sum)) {   // keep the first index only
                preSumMap.put(sum, i);
            }
        }

        return maxLen;
    }
}
```

<details>
<summary><b>Dry run</b></summary>

<br>

Input `[10, 5, 2, 7, 1, 9]` with `k = 15`.

| i | num | sum | sum - k | Found in map? | maxLen | Map after |
| --- | --- | --- | --- | --- | --- | --- |
| 0 | 10 | 10 | -5 | no | 0 | 10→0 |
| 1 | 5 | 15 | 0 | no, but `sum == k` gives 2 | 2 | 10→0, 15→1 |
| 2 | 2 | 17 | 2 | no | 2 | adds 17→2 |
| 3 | 7 | 24 | 9 | no | 2 | adds 24→3 |
| 4 | 1 | 25 | 10 | yes, index 0, so 4 - 0 = 4 | 4 | adds 25→4 |
| 5 | 9 | 34 | 19 | no | 4 | adds 34→5 |

Answer: `4`

</details>

<details>
<summary><b>Another way: sliding window (non-negative numbers only)</b></summary>

<br>

Keep a window `[left..right]` and its sum. If the sum is above `k`, drop numbers from the left. If it equals `k`, record the length.

```java
import java.util.*;

class Solution {
    public int longestSubarray(int[] nums, int k) {
        int n = nums.length;
        int maxLen = 0;
        int left = 0, right = 0;
        int sum = nums[0];

        while (right < n) {
            while (left <= right && sum > k) {   // too big: shrink from the left
                sum -= nums[left];
                left++;
            }

            if (sum == k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }

            right++;
            if (right < n) sum += nums[right];
        }

        return maxLen;
    }
}
```

This only works when no number is negative. Shrinking assumes that dropping a number can only lower the sum and adding one can only raise it. A negative number breaks that.

Counterexample: `[1, -1, 5, -2, 3]` with `k = 3`. The right answer is `4` (`1 - 1 + 5 - 2`). The window reaches `[1, -1, 5]` with sum 5, shrinks away its left side, and never finds the answer, so this code returns `0`. The HashMap version returns `4`.

</details>

## Complexity

| Time | Extra space |
| --- | --- |
| O(n): one pass, each map lookup is O(1) | O(n): the map can hold one entry per index |
