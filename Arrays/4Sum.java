import java.util.*;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> result = new ArrayList<>();

        int n = nums.length;
        if (n < 4) return result;

        // Step 1: Sort the array to enable Two-Pointer logic and duplicate skipping
        Arrays.sort(nums);

        for (int i = 0; i < n - 3; i++) {
            //  If smallest possible 4-sum is > target break
            if ((long)nums[i] + nums[i+1] + nums[i+2] + nums[i+3] > target) break;

            //  If nums[i] + largest 3 is < target i is too small
            if ((long)nums[i] + nums[n-3] + nums[n-2] + nums[n-1] < target) continue;

            // Step 2: Skip duplicate fixed elements
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < n - 2; j++) {
                // Same logic for j optimizations 
                if ((long)nums[i] + nums[j] + nums[j+1] + nums[j+2] > target) break;
                if ((long)nums[i] + nums[j] + nums[n-2] + nums[n-1] < target) continue;

                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                // Step 3: Reduce to 2sum problem
                long newTarget = (long)target - nums[i] - nums[j];
                
                List<List<Integer>> pairs = twoSum(nums, newTarget, j + 1);

                for (List<Integer> pair : pairs) {
                    result.add(Arrays.asList(nums[i], nums[j], pair.get(0), pair.get(1)));
                }
            }
        }
        return result;
    }

    private List<List<Integer>> twoSum(int[] nums, long target, int start) {
        List<List<Integer>> results = new ArrayList<>();
        int left = start;
        int right = nums.length - 1;

        while (left < right) {
            long sum = (long)nums[left] + nums[right];
            if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            } else {
                results.add(Arrays.asList(nums[left], nums[right]));
                
                // Step 4: Skip duplicate values for both pointers
                while (left < right && nums[left] == nums[left + 1]) left++;
                while (left < right && nums[right] == nums[right - 1]) right--;
                
                left++;
                right--;
            }
        }
        return results;
    }
}

   
