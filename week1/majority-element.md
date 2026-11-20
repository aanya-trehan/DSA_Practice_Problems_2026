# Majority Element

**Question Link:** [LeetCode 169 - Majority Element](https://leetcode.com/problems/majority-element/)

**Pattern:** Boyer-Moore Voting

![Easy](https://img.shields.io/badge/Level-Easy-22c55e?style=flat-square) ![Pattern](https://img.shields.io/badge/Pattern-Boyer--Moore_Voting-3b82f6?style=flat-square) ![Time](https://img.shields.io/badge/Time-O(n)-22c55e?style=flat-square) ![Space](https://img.shields.io/badge/Space-O(1)-22c55e?style=flat-square)

---

> [!TIP]
> **Key insight:** every cancellation removes one candidate vote and one different number. A number that appears more than n/2 times has more votes than everything else put together, so it cannot be fully cancelled.

## The Question

Given an array `nums` of size n, return the number that appears **more than n/2 times**. You can assume it always exists.

> Example: `[2, 2, 1, 1, 2]` gives `2` (3 times out of 5).

<details>
<summary><b>How to think about it</b></summary>

<br>

1. Start simple: for each number, count how many times it appears. That is two loops, O(n²).
2. Spot the waste: the counting is repeated for every number, and storing every count takes extra space.
3. Ask the question that fixes it: if one number is in the majority, do I need to know every count, or only who is winning right now?
4. So keep a single "current winner" and a count of its lead. A different number takes one vote away. When the lead hits 0, the next number becomes the winner.
5. **Why "more than half"?** If a number could be exactly half, it could be cancelled out fully.

</details>

## Ways to Solve It

| Approach | Idea | Note | Time | Space |
| --- | --- | --- | --- | --- |
| Two loops | Count every number with a second loop. | Slow when the array is large. | O(n²) | O(1) |
| HashMap | Count each number once in a map. | Needs extra memory. | O(n) | O(n) |
| **Voting (best)** | **Keep one candidate and one count.** | **One pass, no extra memory.** | **O(n)** | **O(1)** |

## The Logic

1. Start with `count = 0` and any `num`.
2. For each number `i`: if `count == 0`, set `num = i`.
3. If `num == i`, add 1 to `count`. Otherwise, subtract 1.
4. At the end, `num` is the answer.

## Code (Java)

```java
class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        int num = 0;

        for (int i : nums) {
            // no lead left, so this number becomes the candidate
            if (count == 0) num = i;

            // same as candidate: one more vote, different: one cancelled
            if (num == i) count++;
            else count--;
        }

        return num;
    }
}
```

<details>
<summary><b>Dry run</b></summary>

<br>

Input `[2, 2, 1, 1, 2]`.

| Number | Count before | num after the check | Count after |
| --- | --- | --- | --- |
| 2 | 0 | 2 | 1 |
| 2 | 1 | 2 | 2 |
| 1 | 2 | 2 | 1 |
| 1 | 1 | 2 | 0 |
| 2 | 0 | 2 | 1 |

Answer: `2`

</details>

## Complexity

|  | Cost |
| --- | --- |
| Time | O(n), one pass |
| Extra space | O(1), two variables |
