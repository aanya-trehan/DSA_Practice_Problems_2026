class Solution {
    public int lengthOfLongestSubstring(String s) {
        int left=0;
        int maxlength=0;
        Set<Character> set = new HashSet<>();

        for(int right=0;right<s.length();right++){
            char c = s.charAt(right);
            while(set.contains(c)){
                set.remove(s.charAt(left));
                left++;
            }
            set.add(c);
            maxlength=Math.max(maxlength,right-left+1);
        }

        return maxlength;
    }

}

/*
**2 pointers + hashing (Set)**

- First pointer (`f`) -> marks the start of the substring we're currently considering.
- Second pointer (`s`) -> moves forward to explore the string.

We start with `f = 0` and `s = 1`.

We add the characters to a Set (a HashSet works here since we only need to check whether a character exists).

Then we keep moving the second pointer forward.
If it lands on a character that's already in the Set, we move the first pointer forward until  duplicate character is no longer in the Set. 
As we move the first pointer, we remove the characters we're passing from the Set.

If the second pointer lands on a character that isn't already in the Set, we simply add it and continue.

For the length, we can keep updating the maximum in every iteration using `s - f + 1`.
*/