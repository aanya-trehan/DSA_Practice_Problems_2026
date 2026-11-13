class Solution {
    public int maxProfit(int[] prices) {
        int minprice=Integer.MAX_VALUE;
        int maxprofit=0;

        for(int i:prices){
            maxprofit=Math.max(maxprofit,i-minprice);
            minprice=Math.min(minprice,i);
        }

        return maxprofit;
    }
}

//solution 2 - kadane's

int maxprofit = 0, currsum = 0;
for (int i = 1; i < prices.length; i++) {
    currsum += prices[i] - prices[i - 1];  // change since yesterday
    currsum = Math.max(0, currsum);        // reset
    maxprofit = Math.max(maxprofit, currsum);
}
