/*
For this question we will use Dutch National Flag problem.

We use 3 pointers -> low, mid and high.

0 0 1 1 0 1 2 2
    l   m     h

(0...low-1) = all sorted 0s.
(low) = next index to place a 0.
(low...mid-1) = sorted 1s.
(mid) = next index to check.
(mid...high) = unsorted values.
(high) = next index to place a sorted 2.
(high+1...n-1) = sorted 2s.

Initially keep low = 0, mid = 0 and high = n-1.

Loop until mid <= high.

if nums[mid]
    = 0 -> swap nums[low], nums[mid]; low++; mid++;
    = 1 -> mid++;
    = 2 -> swap nums[high], nums[mid]; high--;

*/

class Solution {
    public void sortColors(int[] nums) {
        
        int low = 0; 
        int mid = 0; 
        int high = nums.length -1; 

        while(mid<=high){
            if(nums[mid]==0){
                swap(nums,low,mid);
                low++;
                mid++; // why ? because mid gets a one placed at it as low is positioned at 1
            }
            else if(nums[mid]==1){
                mid++;
            }
            else{
                swap(nums,high,mid);
                high--; // why not increment mid? because at high unsorted element is there
            }
        }

        
    }

    private void swap(int[] nums, int i, int j) {

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}