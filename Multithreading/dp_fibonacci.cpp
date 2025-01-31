#include <iostream>
#include <vector>
#include <cstring> 

// int fib_recursive(int n, std::vector<int> dp)
int fib_recursive(int n, int dp[])
{
    // std::cout << "enter with dp[" << n << "]=" << dp[n] << std::endl;
    if(n <= 1)
    {
        return n;
    }
    if(dp[n] != -1)
    {
        return dp[n];
    }
    dp[n] = fib_recursive(n-1, dp) + fib_recursive(n-2, dp);
    // std::cout << "dp[" << n << "]=" << dp[n] << std::endl;
    return dp[n];
}

int fib_tabular(int n, int dp[])
{
    dp[0] = 0;
    dp[1] = 1;
    for(int i=2; i<=n; i++)
    {
        dp[i] = dp[i-1] + dp[i-2];
    }
    return dp[n];
}

int fib_space_optimal(int n)
{
    if(n == 0)
    {
        return 0;
    }
    int prev = 0;
    int sum = 1;
    for(int i=2; i<=n; i++)
    {
        int last_sum = sum;
        sum = sum + prev;
        prev = last_sum;
    }
    return sum;
}

int main(int argc, const char* argv[])
{
    std::cout << "Enter fibonacci number index: " << std::endl;
    int n=0;
    std::cin >> n;
    // IMP_NOTE: this console written n wont change, so array can be declared with size n
    // However, if n can change, like when we get n from a wrapper function to this fn,
    // we can use vector instead of array here
    // std::vector<int> dp(n+1, -1); // n+1 to store 0 result;

    // IMP_NOTE:  {-1} DONT WORK, only {0} initialization works with arrays.
    int dp[n+1];
    memset(dp, -1, sizeof(dp)); // IMP_NOTE: include cstring for memset call
    // auto out = fib_recursive(n, dp);
    // auto out = fib_tabular(n, dp);
    auto out = fib_space_optimal(n);
    std::cout << "Fib number: " << out << std::endl;
    return 0;
}
// fib_recursive below
// Time Complexity - O(n)
// Space complexity - O(n for recursion stack base and n for dp array) - O(n)
// fib_tabular below
// Time Complexity - O(n)
// Space complexity - O(n for dp array) - O(n)
// fib_space_optimal below
// Time Complexity - O(n)
// Space complexity - O(1)
