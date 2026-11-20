# Pow(x, n)

**Question Link:** [LeetCode 50 - Pow(x, n)](https://leetcode.com/problems/powx-n/)

**Pattern:** Math / Binary Exponentiation

![Medium](https://img.shields.io/badge/Level-Medium-f0ad00?style=flat-square) ![Pattern](https://img.shields.io/badge/Pattern-Math_%2F_Binary_Exponentiation-3b82f6?style=flat-square) ![Time](https://img.shields.io/badge/Time-O(log_n)-22c55e?style=flat-square) ![Space](https://img.shields.io/badge/Space-O(log_n)-22c55e?style=flat-square)

---

> [!TIP]
> **In one line:** Calculate xⁿ efficiently by repeatedly reducing the exponent and squaring the base.
>
> **Key insight:** For an even exponent, xⁿ is the half power squared. For an odd exponent, xⁿ = x × xⁿ⁻¹, leaving an even exponent to calculate.

## The Question

Write `pow(x, n)`: x raised to the power n. n can be negative, and it can be as big as 2 billion.

> Example: x = 2, n = 10 gives `1024`. x = 2, n = -2 gives `0.25`.

<details>
<summary><b>How to think about it</b></summary>

<br>

1. **Brute force:** Multiply x by itself n times. This works, but it's too slow for large n.
2. **Look for a shortcut:** Instead of calculating 2¹⁰ by multiplying ten 2s, split it into two equal powers: 2⁵ × 2⁵.
3. **Reuse the result:** Calculate 2⁵ once and multiply it by itself.
4. **What if the exponent is odd?** Take one x out. For example, 2⁵ = 2 × 2⁴. Now the remaining exponent is even.
5. **Repeat:** Keep reducing the exponent until it reaches 1 or 0. This takes only O(log n) steps.
6. **Negative exponent:** Calculate the positive power, then take its reciprocal: x⁻ⁿ = 1/xⁿ.
7. **The main idea:** Don't multiply x repeatedly. Keep squaring the base and reducing the exponent to do the same work in fewer steps.

</details>

## Ways to Solve It

| Approach | Idea | Note | Time | Space |
| --- | --- | --- | --- | --- |
| Loop | Multiply x by itself n times. | Too slow for n = 2 billion. | O(n) | O(1) |
| **Halve and square (best)** | **Square x, halve n, then ask the same function.** | **About log n calls.** | **O(log n)** | **O(log n)** |
| Halve in a loop | Same idea, no recursion. Only 3 variables. | Best for memory. | O(log n) | O(1) |

## Best Idea: halve the power

## The Logic

1. **Store `n` as a `long`:** Prevents overflow when **`n`** is **`Integer.MIN_VALUE`**.
2. **Handle negative `n`:** Calculate the positive power, then take its reciprocal (**`1.0 / power(x, -n)`**).
3. **Base cases:** If **`n`** is **`0`**, return **`1`**. If **`n`** is **`1`**, return **`x`**.
4. **If `n` is even:** Square **`x`** and divide **`n`** by 2 (**`power(x*x, n/2)`**).
5. **If `n` is odd:** Take one **`x`** out and reduce **`n`** by 1 (**`x * power(x, n-1)`**).

## Code (Java)

```java
class Solution {
    public double myPow(double x, int n) {
          // long, because -Integer.MIN_VALUE does not fit in an int
          long num = n;
          // negative power: x^-n = 1 / x^n
          if(num<0){
            return (1.0/power(x,-num));
          }
          return power(x,num);
    }

    private double power(double x, long n){
        // x^0 = 1
        if(n==0) return 1.0;
        // x^1 = x
        if(n==1) return x;

        // even: x^n = (x*x)^(n/2), square the base and halve the power
        if(n%2==0) return power(x*x,n/2);
        // odd: take one x out, what is left is even
        return x*power(x,n-1);
    }
}
```

## Complexity

| Complexity | Why? |
| --- | --- |
| **Time:** O(log n) | Each recursive call halves the exponent `n`, or reduces it by 1 first when it is odd. So there are only about log n calls. |
| **Space:** O(log n) | Each recursive call uses stack memory. The maximum depth is the number of times `n` can be halved. |

**Quick comparison:**

- **Recursive:** O(log n) time, O(log n) space.
- **Iterative:** O(log n) time, O(1) space.
