class Solution {

    public List<List<Integer>> threeSum(int[] nums) {

        // sort the array -> needed for two pointer method
        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();

        int len = nums.length;

        for (int i = 0; i < len - 2; i++) { // fix the first num

            if(nums[i]>0) break;
            // skip duplicates
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // use two pointer for j and k
            int left = i + 1;
            int right = len - 1;

            while (left < right) {

                int sum = nums[left] + nums[right] + nums[i];

                // triplet found
                if (sum == 0) {

                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // increment and decrement
                    left++;
                    right--;

                    // skip duplicates
                    while (left < right && nums[left - 1] == nums[left]) left++;
                    while (left < right && nums[right + 1] == nums[right]) right--;
                }

                else if (sum < 0) {
                    left++;
                }

                else {
                    right--;
                }
            }
        }

        return ans;
    }
}