class Solution {
    public int trap(int[] height) {

        int leftmax=height[0];
        int rightmax=height[height.length-1];

        int left=0;
        int right=height.length-1;

        int water=0;

        while(left<=right){

            if(leftmax<rightmax){ //left height limits so we process left side
                leftmax=Math.max(leftmax,height[left]);
                water+=Math.max(0,leftmax-height[left]);
                left++;
            }
            else{
                rightmax=Math.max(rightmax,height[right]);
                water+=Math.max(0,rightmax-height[right]);
                right--;
            }
        }

        return water;
        
    }
}

/*
observations
1. area = width x height
2. width of each bar is 1 so we only need water height
3. water needs a boundary on both sides
4.water level depends on the smaller of the max heights on both sides
5. water trapped = min(leftMax, rightMax) - height[i]


brute force
1. for every index find the max height on the left
2. find the max height on the right
3.calculate water and add it to total
4. we are repeatedly scanning the same elements

tc = O(n^2)
sc = O(1)


better approach
1. instead of finding leftMax and rightMax again and again we can store them
2. create a leftMax array and store the max height from the start till i
3. create a rightMax array and store the max height from the end till i
4. use both arrays to calculate the water at each index

tc = O(n)
sc = O(n)


optimal approach
1. we only need the current max heights so no need to store entire arrays
2. use left and right pointers
3. keep track of leftMax and rightMax
4. if leftMax < rightMax process the left side
   because the right side is already tall enough to hold water up to leftMax
5. otherwise process the right side

tc = O(n)
sc = O(1)
*/