import java.util.*;

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int num1=Integer.MIN_VALUE;
        int num2=Integer.MAX_VALUE;

        int count1=0;
        int count2=0;

        for(int num:nums){
            if(count1==0 && num!=num2) {
                num1=num;
                count1=1;
            }
            else if(count2==0 && num!=num1) {
                num2=num;
                count2=1;
            }
            else if(num==num1) count1++;
            else if(num==num2) count2++;
            else {
                count1--;
                count2--;
            }
        }

        int checkcount1=0;
        int checkcount2=0;

        for(int i:nums){
            if(i==num1) checkcount1++;
            else if(i==num2) checkcount2++;
        }

        List<Integer> ans = new ArrayList<>();
        if(checkcount1>nums.length/3) ans.add(num1);
        if(checkcount2>nums.length/3) ans.add(num2);

        return ans;
    }
}
