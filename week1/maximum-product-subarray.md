# Maximum Product Subarray

**Question Link:** [LeetCode 152 - Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray/)

**Pattern:** Kadane's Algorithm

![Medium](https://img.shields.io/badge/Level-Medium-f0ad00?style=flat-square) ![Pattern](https://img.shields.io/badge/Pattern-Kadanes_Algorithm-3b82f6?style=flat-square) ![Time](https://img.shields.io/badge/Time-O(n)-22c55e?style=flat-square) ![Space](https://img.shields.io/badge/Space-O(1)-22c55e?style=flat-square)

---

## The Question

Given an integer array `nums`, find a contiguous subarray with the largest product, and return that product.

> Example: `nums = [2, 3, -2, 4]` gives `6`, from the subarray `[2, 3]`.
>
> Example: `nums = [-2, 0, -1]` gives `0`.

## Ways to Solve It

| Approach | Idea | Time | Space |
| --- | --- | --- | --- |
| Brute force | Generate all subarrays and keep the running product. | O(n²) | O(1) |
| **Prefix and suffix (best)** | **Multiply from both ends, reset to 1 at a zero.** | **O(n)** | **O(1)** |

### Brute Force - Generate all subarrays

```java
class Solution {
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;

        // start a subarray at every index i
        for (int i = 0; i < nums.length; i++) {
            int product = 1;

            // extend it to every end index j and keep the running product
            for (int j = i; j < nums.length; j++) {
                product = product * nums[j];
                max = Math.max(max, product);
            }
        }

        return max;
    }
}
```

| Complexity | Why? |
| --- | --- |
| **Time:** O(n²) | generating all subarrays |
| **Space:** O(1) | Only `max` and `product` are stored. |

### Optimal Solution

**Intuition**

The array can contain positive numbers, negative numbers and zeros.

- **All positive:** Product of all numbers.
- **Even number of negatives:** Product of all numbers.
- **Odd number of negatives:** Exclude one negative. The maximum product will be either the prefix before it or the suffix after it.
- **Zeros:** Split the array into smaller parts, because a subarray containing zero has product zero.

**Example:**

`[1, 2, 5, 6]` | `0` | `[3, 4, 5]` | `0` | `[2, 1, 4]`

Each zero separates the array into independent parts.

**Approach:**

1. **Initialise:** `prefix = 1`, `suffix = 1` and `maxProduct = Integer.MIN_VALUE`.
2. **Traverse:** Go through the array from both directions.
3. **Multiply:** Multiply the current element into `prefix` and `suffix`.
4. **Update:** Update `maxProduct` using the larger of the two.
5. **Reset:** If either running product becomes 0, reset it to 1 to start a new subarray.
6. **Return:** `maxProduct`.

```java
class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int prefix = 1;
        int suffix = 1;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            // a 0 splits the array, so start a fresh product
            if (prefix == 0) prefix = 1;
            if (suffix == 0) suffix = 1;

            // prefix goes left to right, suffix goes right to left
            prefix = prefix * nums[i];
            suffix = suffix * nums[n - 1 - i];

            // the best product so far
            max = Math.max(max, Math.max(prefix, suffix));
        }

        return max;
    }
}
```

| Complexity | Why? |
| --- | --- |
| **Time:** O(n) | One traversal, checking both ends. |
| **Space:** O(1) | Only `prefix`, `suffix` and `max` are stored. |

**Quick comparison:**

- **Brute force:** O(n²) time, O(1) space.
- **Prefix and suffix:** O(n) time, O(1) space.
