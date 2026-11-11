class Solution {
    public int longestConsecutive(int[] nums) {
        int largest = 0;
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        for (int num : set) {
            if (set.contains(num - 1)) continue;

            int ele = num;
            int currcount = 0;

            while (set.contains(ele)) {
                currcount++;
                ele++;
            }

            largest = Math.max(largest, currcount);
        }

        return largest;
    }
}