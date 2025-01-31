#include <bits/stdc++.h> 
int frogjump_topdown(const int n,
    const vector<int>& heights,
    vector<int>& dp)
{
    // std::cout << "Entering n: " << n << std::endl;
    if(n == 1)
    {
        return 0;
    }
    if(dp[n] != -1)
    {
        return dp[n];
    }
    int j_plus_1 = 100000000;
    int j_plus_2 = 100000000;
    auto rec1 = frogjump_topdown(n-1, heights, dp);
    j_plus_1 =  std::abs(heights[n-1] - heights[n-2])
        + rec1;
    if(n > 2)
    {
        auto rec2 = frogjump_topdown(n-2, heights, dp);
        j_plus_2 =  std::abs(heights[n-1] - heights[n-3])
            + rec2;
    }
    auto min = std::min(j_plus_1, j_plus_2);
    // std::cout << "n: " << n
    //     <<", rec1: " << rec1
    //     <<", min_now: " << std::abs(heights[n-1] - heights[n-2])
    //     <<", j_plus_1: " << j_plus_1
    //     <<", j_plus_2: " << j_plus_2
    //     <<", min: " << min
    //     << std::endl;
    dp[n] = min;
    return min;
}
int frogJump(int n, vector<int> &heights)
{
    std::vector<int> dp(n+1, -1);
    return frogjump_topdown(n, heights, dp);
    return 0;
}