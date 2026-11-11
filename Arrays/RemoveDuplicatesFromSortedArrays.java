class Solution {
    public int removeDuplicates(int[] nums) {
        int slow=0;
        int fast=1;

        while(fast<nums.length){
            if(nums[slow]!=nums[fast]){
                slow++;
                nums[slow]=nums[fast];
            }
            fast++;
        }
        return slow+1;
    }
}

/*
observations 
1. sorted means duplicates are side by side and cant show up randomly -> use 2 pointers 
2. we dont care about what happens post k elements 

keep two pointers 
i = index where last unique element is 
j = the searching pointer that tries to find the next element such that i!=j

if(nums[i]==nums[j]) 
    we just increment j to find a unique element 
else if != 
    i++ (next index for next unique element)
    nums[i]=nums[j]
    j++;
now j tries to find an element that does match the new element at ith index 
this loops until j is out of bounds 

after the loop 
i is at last index of unique element 
due to it being zero based indexing we return i+1 as the total number of unique elements 
*/