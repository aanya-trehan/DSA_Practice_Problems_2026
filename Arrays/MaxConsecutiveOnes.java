class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxcount=0;
        int currcount=0;

        for(int i=0;i<nums.length;i++){
            if(nums[i]==0){
                maxcount=Math.max(maxcount,currcount);
                currcount=0;
            }
            else{
                currcount++;
            }
        }
        maxcount=Math.max(maxcount,currcount);
        return maxcount;
    }
}