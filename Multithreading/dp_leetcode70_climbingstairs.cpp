
class Solution {
    int climbStairs_recursive(int n)
    {
        if(n <= 2)
        {
            return n; 
        }
        return climbStairs_recursive(n-1) + climbStairs_recursive(n-2);
    }
    int climbStairs_topdown(int n, int dp[])
    {
        if(n <= 2)
        {
            dp[n] = n; 
        }
        else
        {
            if(dp[n] == -1)
            {
               dp[n] = climbStairs_topdown(n-1, dp) + climbStairs_topdown(n-2, dp);
            }
        }
        return dp[n];
    }
    int climbStairs_bottomup(int n, int dp[])
    {
        dp[0] = 0;
        dp[1] = 1;
        if(n > 1)
        {
            dp[2] = 2;
        }
        for(int i=3; i<=n; i++)
        {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
    int climbStairs_bottomup_space_opt(int n)
    {
        if(n <= 2)
        {
            return n;
        }
        int pprev = 1;  // one stairs case
        int prev = 2; // two stairs case
        int output = 0;
        for(int i=3; i<=n; i++)
        {
            output = prev + pprev;
            pprev = prev;
            prev = output;
        }
        return output;
    }
public:
    int climbStairs(int n) {
        int dp[n+1];
        memset(dp, -1, sizeof(dp));
        // return climbStairs_recursive(n); // Time Limit Exceeded
        // return climbStairs_topdown(n, dp);
        // return climbStairs_bottomup(n, dp);
        return climbStairs_bottomup_space_opt(n);
        
    }
};