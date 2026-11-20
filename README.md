# DSA Notes

Short notes always visible, long notes inside dropdowns. Click a problem name to open its file.

Status: **Notes** = already written in my notes, file being built. **New** = from the extra list, notes not written yet.

## Pattern 1: Two Pointers (Converge / Partition)

| # | Problem | Ref | Level | Status |
|---|---|---|---|---|
| 1 | [Sort Colors](01-two-pointers-converge/sort-colors.md) | #75 | Med | Notes |
| 2 | [3Sum](01-two-pointers-converge/3sum.md) | #15 | Med | Notes |
| 3 | [4Sum](01-two-pointers-converge/4sum.md) | #18 | Med | Notes |
| 4 | [Container With Most Water](01-two-pointers-converge/container-with-most-water.md) | #11 | Med | Notes |
| 5 | [Trapping Rain Water](01-two-pointers-converge/trapping-rain-water.md) | #42 | Hard | Notes |

## Pattern 2: Two Pointers (Read / Write)

| # | Problem | Ref | Level | Status |
|---|---|---|---|---|
| 1 | [Remove Duplicates from Sorted Array](02-two-pointers-read-write/remove-duplicates.md) | #26 | Easy | Notes |
| 2 | [Max Consecutive Ones](02-two-pointers-read-write/max-consecutive-ones.md) | #485 | Easy | Notes |
| 3 | [Remove Element](02-two-pointers-read-write/remove-element.md) | #27 | Easy | Notes |

## Pattern 3: Hashing / Prefix Sum

| # | Problem | Ref | Level | Status |
|---|---|---|---|---|
| 1 | [Two Sum](03-hashing-prefix-sum/two-sum.md) | #1 | Easy | Notes |
| 2 | [Longest Consecutive Sequence](03-hashing-prefix-sum/longest-consecutive-sequence.md) | #128 | Med | Notes |
| 3 | [Longest Substring Without Repeating Characters](03-hashing-prefix-sum/longest-substring-without-repeating.md) | #3 | Med | Notes |
| 4 | [Largest Subarray with K Sum](03-hashing-prefix-sum/largest-subarray-k-sum.md) | GFG | Med | Notes |
| 5 | [Subarray with Given XOR](03-hashing-prefix-sum/subarray-with-given-xor.md) | InterviewBit | Med | Notes |

## Pattern 4: Kadane's Algorithm

| # | Problem | Ref | Level | Status |
|---|---|---|---|---|
| 1 | [Maximum Subarray](04-kadane/maximum-subarray.md) | #53 | Med | Notes |
| 2 | [Best Time to Buy and Sell Stock](04-kadane/best-time-to-buy-sell-stock.md) | #121 | Easy | Notes |
| 3 | [Maximum Product Subarray](04-kadane/maximum-product-subarray.md) | #152 | Med | Notes |

## Pattern 5: Boyer-Moore Voting

| # | Problem | Ref | Level | Status |
|---|---|---|---|---|
| 1 | [Majority Element](05-boyer-moore-voting/majority-element.md) | #169 | Easy | Notes |
| 2 | [Majority Element II](05-boyer-moore-voting/majority-element-ii.md) | #229 | Med | Notes |

## Pattern 6: Math / Binary Exponentiation

| # | Problem | Ref | Level | Status |
|---|---|---|---|---|
| 1 | [Pow(x, n)](06-math-binary-exponentiation/pow-x-n.md) | #50 | Med | Notes |
| 2 | [Next Permutation](06-math-binary-exponentiation/next-permutation.md) | #31 | Med | Notes |

## Pattern 7: Sliding Window

Expand right, shrink left when the rule is broken. O(n).

| # | Problem | Ref | Level | Status |
|---|---|---|---|---|
| 1 | [Longest Substring Without Repeating Characters](03-hashing-prefix-sum/longest-substring-without-repeating.md) | #3 | Med | Notes (see Pattern 3) |
| 2 | [Minimum Window Substring](07-sliding-window/minimum-window-substring.md) | #76 | Hard | New |
| 3 | [Longest Repeating Character Replacement](07-sliding-window/longest-repeating-char-replacement.md) | #424 | Med | New |
| 4 | [Permutation in String](07-sliding-window/permutation-in-string.md) | #567 | Med | New |

## Pattern 8: Matrix Traversal

Row/column markers, layer rotation, spiral walk, binary search on a matrix.

| # | Problem | Ref | Level | Status |
|---|---|---|---|---|
| 1 | [Set Matrix Zeroes](08-matrix-traversal/set-matrix-zeroes.md) | #73 | Med | New |
| 2 | [Rotate Image](08-matrix-traversal/rotate-image.md) | #48 | Med | New |
| 3 | [Spiral Matrix](08-matrix-traversal/spiral-matrix.md) | #54 | Med | New |
| 4 | [Search a 2D Matrix](08-matrix-traversal/search-a-2d-matrix.md) | #74 | Med | New |
| 5 | [Pascal's Triangle](08-matrix-traversal/pascals-triangle.md) | #118 | Med | New |
| 6 | [Set Mismatch](08-matrix-traversal/set-mismatch.md) | #645 | Easy | New |
| 7 | [Unique Paths](08-matrix-traversal/unique-paths.md) | #62 | Med | New |

## Pattern 9: Merge Sort Counting

Count inversions or reverse pairs during the merge step. O(n log n).

| # | Problem | Ref | Level | Status |
|---|---|---|---|---|
| 1 | [Inversion of Array](09-merge-sort-counting/inversion-of-array.md) | CN | Hard | New |
| 2 | [Reverse Pairs](09-merge-sort-counting/reverse-pairs.md) | #493 | Hard | New |

## Pattern 10: Interval Merge + Prefix Products

Sort intervals and merge overlaps. Prefix and suffix products.

| # | Problem | Ref | Level | Status |
|---|---|---|---|---|
| 1 | [Merge Intervals](10-interval-merge-prefix-products/merge-intervals.md) | #56 | Med | New |
| 2 | [Merge Sorted Array](10-interval-merge-prefix-products/merge-sorted-array.md) | #88 | Med | New |
| 3 | [Find Duplicate Number](10-interval-merge-prefix-products/find-duplicate-number.md) | #287 | Med | New |
| 4 | [Repeat and Missing Number](10-interval-merge-prefix-products/repeat-and-missing-number.md) | IB | Hard | New |
| 5 | [Product of Array Except Self](10-interval-merge-prefix-products/product-of-array-except-self.md) | #238 | Med | New |

## Pattern 11: Greedy on Arrays

Jump Game and Gas Station: two must-know greedy patterns.

| # | Problem | Ref | Level | Status |
|---|---|---|---|---|
| 1 | [Jump Game](11-greedy-on-arrays/jump-game.md) | #55 | Med | New |
| 2 | [Jump Game II](11-greedy-on-arrays/jump-game-ii.md) | #45 | Med | New |
| 3 | [Gas Station](11-greedy-on-arrays/gas-station.md) | #134 | Med | New |
