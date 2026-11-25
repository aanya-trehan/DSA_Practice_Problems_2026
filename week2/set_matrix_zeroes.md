## Problem Statement

Given an `m x n` matrix, if an element is `0`, set every element in its row and column to `0`.

**Important:** Only zeroes present in the original matrix should trigger the operation. A zero created during the process must not trigger another row or column.

The matrix is modified in place.

---

## Quick Revision

| Approach | Main idea | Time | Extra space |
|---|---|---:|---:|
| Brute force | Mark affected non-zero cells as `-1`, then convert them to `0` | `O(mn(m + n))` | `O(1)` |
| Better | Use separate row and column marker arrays | `O(mn)` | `O(m + n)` |
| Optimal | Use the first row and first column as markers, with two flags | `O(mn)` | `O(1)` |

Here, `m` = number of rows and `n` = number of columns.

**Core rule:** Record which rows and columns need to become zero before changing the matrix values that are still needed for marking.

---

## 1. Brute Force — Use `-1` as a Marker

### Idea

Directly changing cells to `0` while traversing is incorrect. A newly created zero could be mistaken for an original zero and trigger extra rows or columns.

Instead:

1. Traverse the matrix.
2. When an original `0` is found:
   - Mark every non-zero cell in that row as `-1`.
   - Mark every non-zero cell in that column as `-1`.
   - Do not change existing zeroes.
3. Traverse the matrix again.
4. Convert every `-1` into `0`.

`-1` works as a marker only when it cannot already be a valid matrix value (for example, the binary matrix in the video).

### Java Code

```java
class Solution {

    // Mark every non-zero element in the given row with -1.
    private void markRow(int[][] matrix, int row, int n) {
        for (int j = 0; j < n; j++) {
            if (matrix[row][j] != 0) {
                matrix[row][j] = -1;
            }
        }
    }

    // Mark every non-zero element in the given column with -1.
    private void markCol(int[][] matrix, int col, int m) {
        for (int i = 0; i < m; i++) {
            if (matrix[i][col] != 0) {
                matrix[i][col] = -1;
            }
        }
    }

    public void setZeroes(int[][] matrix) {
        int m = matrix.length;     // Number of rows
        int n = matrix[0].length;  // Number of columns

        // First pass: find original zeroes and mark their rows and columns.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    markRow(matrix, i, n);
                    markCol(matrix, j, m);
                }
            }
        }

        // Second pass: convert all marked cells (-1) into zeroes.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == -1) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
```

### Complexity

- **Time:** `O(mn(m + n))`
  - Matrix traversal: `O(mn)`.
  - For each original zero, marking its row costs `O(n)` and its column costs `O(m)`.
  - Final conversion: `O(mn)`.
- **Extra space:** `O(1)`.

<details>
<summary>Deeper revision: Why not mark directly with zero?</summary>

A zero created while marking one row or column could be encountered later during traversal. If treated as an original zero, it would incorrectly cause another row and column to become zero.

Using `-1` keeps the meanings separate:

- `0` = an original zero.
- `-1` = a cell that must become zero later.
- Any other value = not marked.

Existing zeroes are not changed to `-1`, so they remain identifiable.

**Limitation:** If the input can already contain `-1`, this marker is unsafe. Use a different marker strategy or the better/optimal approach instead.

</details>

---

## 2. Better Approach — Separate Row and Column Arrays

### Idea

Instead of marking every affected cell immediately, record which rows and columns need to become zero.

Create:

- `rows[m]` — tracks whether a row must become zero.
- `cols[n]` — tracks whether a column must become zero.

Initially, every entry is `false`.

### Steps

1. Traverse the matrix.
2. Whenever `matrix[i][j] == 0`:
   - Set `rows[i] = true`.
   - Set `cols[j] = true`.
3. Traverse the matrix again.
4. If `rows[i]` or `cols[j]` is `true`, set `matrix[i][j] = 0`.

### Java Code

```java
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;     // Number of rows
        int n = matrix[0].length;  // Number of columns

        boolean[] rows = new boolean[m];
        boolean[] cols = new boolean[n];

        // Step 1: Record every affected row and column.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = true; // Mark this row.
                    cols[j] = true; // Mark this column.
                }
            }
        }

        // Step 2: Set cells to zero using the recorded markers.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rows[i] || cols[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
```

### Complexity

- **Time:** `O(mn)` — two full matrix traversals.
- **Extra space:** `O(m + n)` — one row array and one column array.

<details>
<summary>Deeper revision: Why are the arrays enough?</summary>

A cell `(i, j)` must become zero if either condition is true:

- `rows[i] == true`: its row contains an original zero.
- `cols[j] == true`: its column contains an original zero.

Therefore:

```java
if (rows[i] || cols[j]) {
    matrix[i][j] = 0;
}
```

The first traversal only records information. It does not modify the matrix, so newly created zeroes cannot affect the marking process.

</details>

---

## 3. Optimal Approach — Use the First Row and First Column as Markers

### Idea

The separate arrays in the better approach take `O(m + n)` extra space.

To reduce auxiliary space to `O(1)`, use the matrix itself:

- `matrix[i][0]` marks whether row `i` must become zero.
- `matrix[0][j]` marks whether column `j` must become zero.

The first row and first column also need to be checked independently.

- `firstRowZero` — whether the original first row contains a zero.
- `firstColZero` — whether the original first column contains a zero.

### Steps

1. Check the first column. If it contains a zero, set `firstColZero = true`.
2. Check the first row. If it contains a zero, set `firstRowZero = true`.
3. Traverse only the inner matrix, starting at row `1` and column `1`.
   - When a zero is found at `(i, j)`, mark its row using `matrix[i][0] = 0`.
   - Mark its column using `matrix[0][j] = 0`.
4. Traverse the inner matrix again.
   - If `matrix[i][0] == 0` or `matrix[0][j] == 0`, set `matrix[i][j] = 0`.
5. If `firstRowZero` is true, set the first row to zero.
6. If `firstColZero` is true, set the first column to zero.

### Java Code

```java
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;     // Number of rows
        int n = matrix[0].length;  // Number of columns

        // Save whether the original first row and first column contain a zero.
        boolean firstRowZero = false;
        boolean firstColZero = false;

        // Step 1: Check the first column separately.
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        // Step 2: Check the first row separately.
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }

        // Step 3: Use the first cell of each row/column as a marker.
        // Start at 1 because the first row and column are reserved for markers.
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0; // Mark row i.
                    matrix[0][j] = 0; // Mark column j.
                }
            }
        }

        // Step 4: Zero inner cells whose row marker OR column marker is zero.
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Step 5: Zero the first row if required.
        if (firstRowZero) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        // Step 6: Zero the first column if required.
        if (firstColZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
```

### Complexity

- **Time:** `O(mn)` — the matrix is traversed a constant number of times.
- **Extra space:** `O(1)` — only two boolean variables are used.

<details>
<summary>Deeper revision: Why are firstRowZero and firstColZero separate?</summary>

`matrix[0][0]` belongs to both the first row and the first column. If it were used as the only marker for both, the two conditions could become confused.

Avoid this conflict by checking the first row and first column before using them as marker storage.

- `firstRowZero` remembers whether the original first row contained a zero.
- `firstColZero` remembers whether the original first column contained a zero.

The inner matrix is marked and processed separately. The first row and first column are zeroed at the end using their saved flags.

</details>

<details>
<summary>Deeper revision: Why does marking start at index 1?</summary>

The first row and first column are being used to store markers. The marking pass therefore starts at:

```java
for (int i = 1; i < m; i++) {
    for (int j = 1; j < n; j++) {
        // Process inner matrix.
    }
}
```

This prevents the marker-storage area from being treated as part of the inner matrix.

The first row and first column were already checked and saved in `firstRowZero` and `firstColZero`, so they can safely be handled after the inner matrix.

</details>

<details>
<summary>Deeper revision: Why is the inner matrix processed before the first row and column?</summary>

The first row and first column contain the markers needed to decide which inner cells become zero.

If they are zeroed too early, marker information may be overwritten. The order matters:

1. Save the original first-row and first-column conditions.
2. Create markers in the first row and column.
3. Use those markers to zero the inner matrix.
4. Apply the saved flags to the first row and first column.

</details>

---

## Final Comparison

| Detail | Brute force | Better | Optimal |
|---|---|---|---|
| How zeroes are tracked | `-1` in the matrix | Separate arrays | First row and first column |
| Extra space | `O(1)` | `O(m + n)` | `O(1)` |
| Time | `O(mn(m + n))` | `O(mn)` | `O(mn)` |
| Main concern | Do not confuse marked cells with original zeroes | Record markers before modifying cells | Save first-row and first-column conditions separately |

## Key Points to Remember

- Only original zeroes should trigger the operation.
- **Brute force:** Mark with `-1`, then convert to `0`.
- **Better:** Use `rows[]` and `cols[]`.
- **Optimal:** Use `matrix[i][0]` and `matrix[0][j]` as markers.
- Save first-row and first-column conditions in `firstRowZero` and `firstColZero`.
- In the optimal approach, mark and process the inner matrix first; zero the first row and first column at the end.
'''

