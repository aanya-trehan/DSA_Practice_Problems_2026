class Solution {
    public int maxArea(int[] height) {

        int ans = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {

            // Calculate the current area
            int width = right - left;
            int minHeight = Math.min(height[left], height[right]);
            int currArea = width * minHeight;

            // Update maximum area
            ans = Math.max(ans, currArea);

            // Move the pointer with the smaller height
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return ans;
    }
}