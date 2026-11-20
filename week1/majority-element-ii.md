# Majority Element II

**Question Link:** [LeetCode 229 - Majority Element II](https://leetcode.com/problems/majority-element-ii/)

**Pattern:** Boyer-Moore Voting

![Medium](https://img.shields.io/badge/Level-Medium-f0ad00?style=flat-square) ![Pattern](https://img.shields.io/badge/Pattern-Boyer--Moore_Voting-3b82f6?style=flat-square) ![Time](https://img.shields.io/badge/Time-O(n)-22c55e?style=flat-square) ![Space](https://img.shields.io/badge/Space-O(1)-22c55e?style=flat-square)

---

> [!TIP]
> **Key insight:** At most 2 numbers can appear more than `n/3` times. We can find them by tracking two candidates and cancelling votes whenever we encounter a different number.

## The Question

Given an array `nums` of size n, return all numbers that appear **more than n/3 times**.

> Example: `[3, 2, 3]` gives `[3]`. `[1, 2]` gives `[1, 2]`.

<details>
<summary><b>How to Think About It</b></summary>

<br>

1. Brute force: Use a HashMap to count each number and find those appearing more than `n/3` times. This takes O(n) time and O(n) space.
2. Find a pattern: How many numbers can actually appear more than `n/3` times?
3. At most 2: Three numbers would need more than `n` elements altogether, which is impossible.
4. Optimise: Instead of storing every number's count, track just two candidates and their counts using the extended Boyer-Moore Voting Algorithm.

Key idea: Only two numbers can qualify, so we only need to keep track of two candidates.

</details>

## Ways to Solve It

| Approach | Idea | Note | Time | Space |
| --- | --- | --- | --- | --- |
| HashMap | Count every number in a map, keep those above n/3. | Needs extra memory. | O(n) | O(n) |
| **Voting with two candidates (best)** | **Two candidates and two counts, then verify both.** | **One pass, no extra memory.** | **O(n)** | **O(1)** |

## The Logic — Extended Boyer-Moore Voting

1. **Initialise:** `num1`, `num2` and their counts `count1`, `count2`.
2. **Empty slot:** If `count1 == 0` and `num != num2`, assign `num1 = num`. Same idea for `num2`.
3. **Match:** If `num == num1` or `num == num2`, increment that count.
4. **No match:** If `num` equals neither, do `count1--` and `count2--`.
5. **Verify:** At the end, loop again to count the actual frequency of both. Return those with frequency greater than `n/3`.

## Code (Java)

```java
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        // start values that cannot clash with a real number in a normal case
        int num1 = Integer.MIN_VALUE;
        int num2 = Integer.MAX_VALUE;

        int count1 = 0;
        int count2 = 0;

        for (int num : nums) {
            // free slot for a new candidate (not the other candidate)
            if (count1 == 0 && num != num2) {
                num1 = num;
                count1 = 1;
            }
            else if (count2 == 0 && num != num1) {
                num2 = num;
                count2 = 1;
            }
            // a vote for an existing candidate
            else if (num == num1) count1++;
            else if (num == num2) count2++;
            // a third different number cancels one vote from each
            else {
                count1--;
                count2--;
            }
        }

        // the candidates are only suspects until counted
        int checkcount1 = 0;
        int checkcount2 = 0;

        for (int i : nums) {
            if (i == num1) checkcount1++;
            else if (i == num2) checkcount2++;
        }

        List<Integer> ans = new ArrayList<>();
        if (checkcount1 > nums.length / 3) ans.add(num1);
        if (checkcount2 > nums.length / 3) ans.add(num2);

        return ans;
    }
}
```

<details>
<summary><b>Dry run</b></summary>

<br>

Input `[1, 1, 1, 3, 3, 2, 2, 2]`, n = 8, so a number needs more than 2 appearances.

| Number | num1 | count1 | num2 | count2 | What happened |
| --- | --- | --- | --- | --- | --- |
| 1 | 1 | 1 | max | 0 | `count1` was 0, so 1 becomes `num1` |
| 1 | 1 | 2 | max | 0 | matches `num1`, so one more vote |
| 1 | 1 | 3 | max | 0 | matches `num1` again |
| 3 | 1 | 3 | 3 | 1 | `count2` was 0 and 3 is not `num1`, so 3 becomes `num2` |
| 3 | 1 | 3 | 3 | 2 | matches `num2`, so one more vote |
| 2 | 1 | 2 | 3 | 1 | matches neither: a third number, so both counts go down |
| 2 | 1 | 1 | 3 | 0 | a third number again |
| 2 | 1 | 1 | 2 | 1 | `count2` is 0 and 2 is not `num1`, so 2 becomes `num2` |

Candidates: 1 and 2. Check pass: 1 appears 3 times, 2 appears 3 times. Both are more than 2.

Answer: `[1, 2]`

</details>

## Why It Works

> [!TIP]
> At most two numbers can appear more than `n/3` times. Cancelling votes removes less frequent candidates, leaving possible majority candidates to verify at the end.

## Complexity

|  | Cost |
| --- | --- |
| Time | O(n), two passes |
| Extra space | O(1), four variables and the answer list |
