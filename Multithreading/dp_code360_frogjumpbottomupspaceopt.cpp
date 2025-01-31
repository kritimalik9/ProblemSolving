#include <bits/stdc++.h> 
int frogjump_bottomup(const int n,
    const vector<int>& heights,
    vector<int>& dp)
{
    // std::cout << "Entering n: " << n << std::endl;
    if(n == 1)
    {
        return 0;
    }
    dp[1] = 0;
    int j_plus_1 = 100000000;
    int j_plus_2 = 100000000;
    int prev=0,pprev=0;
    for(int i=2; i<=n; i++)
    {
        j_plus_1 =  std::abs(heights[i-1] - heights[i-2])
            + prev;
        if(i > 2)
        {
            j_plus_2 =  std::abs(heights[i-1] - heights[i-3])
                + pprev;
        }
        auto min = std::min(j_plus_1, j_plus_2);
        dp[i] = min;
        pprev = prev;
        prev = dp[i];
    }
    // std::cout << "n: " << n
    //     <<", rec1: " << rec1
    //     <<", min_now: " << std::abs(heights[n-1] - heights[n-2])
    //     <<", j_plus_1: " << j_plus_1
    //     <<", j_plus_2: " << j_plus_2
    //     <<", min: " << min
    //     << std::endl;
    return dp[n];
}
int frogJump(int n, vector<int> &heights)
{
    std::vector<int> dp(n+1, -1);
    return frogjump_bottomup(n, heights, dp);
    return 0;
}